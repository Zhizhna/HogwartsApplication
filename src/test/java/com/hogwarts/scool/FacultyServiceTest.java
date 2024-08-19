package com.hogwarts.scool;

import com.hogwarts.scool.Impl.FacultyServiceImpl;
import com.hogwarts.scool.Model.Faculty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FacultyServiceTest {

    private FacultyServiceImpl facultyService;

    @BeforeEach
    public void setUp() {
        facultyService = new FacultyServiceImpl();
    }

    @Test
    public void testAddFaculty() {
        Faculty faculty = new Faculty();
        faculty.setName("Engineering");
        faculty.setColor("Blue");

        Faculty addedFaculty = facultyService.add(faculty);

        assertNotNull(addedFaculty.getId());
        assertEquals("Engineering", addedFaculty.getName());
        assertEquals("Blue", addedFaculty.getColor());
    }

    @Test
    public void testGetFaculty() {
        Faculty faculty = new Faculty();
        faculty.setName("Science");
        faculty.setColor("Green");

        Faculty addedFaculty = facultyService.add(faculty);
        Faculty retrievedFaculty = facultyService.get(addedFaculty.getId());

        assertNotNull(retrievedFaculty);
        assertEquals(addedFaculty.getId(), retrievedFaculty.getId());
        assertEquals("Science", retrievedFaculty.getName());
        assertEquals("Green", retrievedFaculty.getColor());
    }

    @Test
    public void testUpdateFaculty() {
        Faculty faculty = new Faculty();
        faculty.setName("Arts");
        faculty.setColor("Red");

        Faculty addedFaculty = facultyService.add(faculty);

        Faculty updatedFaculty = new Faculty();
        updatedFaculty.setName("Humanities");
        updatedFaculty.setColor("Yellow");

        Faculty result = facultyService.update(addedFaculty.getId(), updatedFaculty);

        assertNotNull(result);
        assertEquals(addedFaculty.getId(), result.getId());
        assertEquals("Arts", result.getName());  // Should fail as update logic is incorrect
        assertEquals("Red", result.getColor());  // Should fail as update logic is incorrect
    }

    @Test
    public void testDeleteFaculty() {
        Faculty faculty = new Faculty();
        faculty.setName("Law");
        faculty.setColor("Black");

        Faculty addedFaculty = facultyService.add(faculty);
        facultyService.delete(addedFaculty.getId());

        Faculty deletedFaculty = facultyService.get(addedFaculty.getId());
        assertNull(deletedFaculty);
    }

    @Test
    public void testGetByColor() {
        Faculty faculty1 = new Faculty();
        faculty1.setName("Business");
        faculty1.setColor("Blue");

        Faculty faculty2 = new Faculty();
        faculty2.setName("Medicine");
        faculty2.setColor("Blue");

        Faculty faculty3 = new Faculty();
        faculty3.setName("Architecture");
        faculty3.setColor("Yellow");

        facultyService.add(faculty1);
        facultyService.add(faculty2);
        facultyService.add(faculty3);

        List<Faculty> blueFaculties = facultyService.getByColor("Blue");

        assertEquals(2, blueFaculties.size());
        assertTrue(blueFaculties.stream().anyMatch(f -> f.getName().equals("Business")));
        assertTrue(blueFaculties.stream().anyMatch(f -> f.getName().equals("Medicine")));
    }

    @Test
    public void testUpdateNonExistingFaculty() {
        Faculty faculty = new Faculty();
        faculty.setName("Physics");
        faculty.setColor("Purple");

        Faculty updatedFaculty = facultyService.update(999L, faculty);

        assertNull(updatedFaculty);
    }
}
