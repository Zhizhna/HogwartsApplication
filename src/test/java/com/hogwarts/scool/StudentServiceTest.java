package com.hogwarts.scool;

import com.hogwarts.scool.Impl.StudentServiceImpl;
import com.hogwarts.scool.Model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StudentServiceTest {

    private StudentServiceImpl studentService;

    @BeforeEach
    public void setUp() {
        studentService = new StudentServiceImpl();
    }

    @Test
    public void testAddStudent() {
        Student student = new Student();
        student.setName("John Doe");
        student.setAge(20);

        Student addedStudent = studentService.add(student);

        assertNotNull(addedStudent.getId());
        assertEquals("John Doe", addedStudent.getName());
        assertEquals(20, addedStudent.getAge());
    }

    @Test
    public void testGetStudent() {
        Student student = new Student();
        student.setName("Jane Doe");
        student.setAge(22);

        Student addedStudent = studentService.add(student);
        Student retrievedStudent = studentService.get(addedStudent.getId());

        assertNotNull(retrievedStudent);
        assertEquals(addedStudent.getId(), retrievedStudent.getId());
        assertEquals("Jane Doe", retrievedStudent.getName());
        assertEquals(22, retrievedStudent.getAge());
    }

    @Test
    public void testUpdateStudent() {
        Student student = new Student();
        student.setName("Alice");
        student.setAge(23);

        Student addedStudent = studentService.add(student);

        Student updatedStudent = new Student();
        updatedStudent.setName("Alice Updated");
        updatedStudent.setAge(24);

        Student result = studentService.update(addedStudent.getId(), updatedStudent);

        assertNotNull(result);
        assertEquals(addedStudent.getId(), result.getId());
        assertEquals("Alice Updated", result.getName());
        assertEquals(24, result.getAge());
    }

    @Test
    public void testDeleteStudent() {
        Student student = new Student();
        student.setName("Bob");
        student.setAge(21);

        Student addedStudent = studentService.add(student);
        studentService.delete(addedStudent.getId());

        Student deletedStudent = studentService.get(addedStudent.getId());
        assertNull(deletedStudent);
    }

    @Test
    public void testGetByAge() {
        Student student1 = new Student();
        student1.setName("Chris");
        student1.setAge(20);

        Student student2 = new Student();
        student2.setName("Diana");
        student2.setAge(20);

        Student student3 = new Student();
        student3.setName("Edward");
        student3.setAge(22);

        studentService.add(student1);
        studentService.add(student2);
        studentService.add(student3);

        List<Student> studentsByAge = studentService.getByAge(20);

        assertEquals(2, studentsByAge.size());
        assertTrue(studentsByAge.stream().anyMatch(s -> s.getName().equals("Chris")));
        assertTrue(studentsByAge.stream().anyMatch(s -> s.getName().equals("Diana")));
    }

    @Test
    public void testUpdateNonExistingStudent() {
        Student student = new Student();
        student.setName("Frank");
        student.setAge(25);

        Student updatedStudent = studentService.update(999L, student);

        assertNull(updatedStudent);
    }
}
