package com.hogwarts.scool.Controller;

import com.hogwarts.scool.Model.Faculty;
import com.hogwarts.scool.Service.FacultyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faculties")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{id}")
    public Faculty get(@PathVariable("id") Long id) {
        return facultyService.get(id);
    }

    @PostMapping
    public Faculty add(@RequestBody Faculty faculty) {
        return facultyService.add(faculty);
    }

    @PutMapping("{id}")
    public Faculty update(@PathVariable("id") Long id, @RequestBody Faculty faculty) {
        return facultyService.update(id, faculty);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        facultyService.delete(id);
    }

    @GetMapping
    public List<Faculty> getByColor(@RequestParam("color") String color) {
        return facultyService.getByColor(color);
    }
}