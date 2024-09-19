package com.sky.hogwarts.Controller;

import com.sky.hogwarts.Model.Faculty;
import com.sky.hogwarts.Model.Student;
import com.sky.hogwarts.Repository.StudentRepository;
import com.sky.hogwarts.Service.StudentService;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("students")
public class StudentController {

    private final StudentRepository studentRepository;
    private final StudentService studentService;

    public StudentController(StudentService studentService, StudentRepository studentRepository) {
        this.studentService = studentService;
        this.studentRepository = studentRepository;
    }

    @GetMapping("/count")
    public long getTotalStudents() {
        return studentRepository.getTotalNumberOfStudents();
    }

    @GetMapping("/last-five")
    public List<Student> getLastFiveStudents() {
        Pageable pageable = PageRequest.of(0, 5); // Правильный класс Pageable
        return studentRepository.findLastFiveStudents(pageable);
    }

    @PostMapping
    public Student create(@RequestBody Student student) {
        return studentService.create(student);
    }

    @GetMapping("{id}")
    public Student read(@PathVariable Long id) {
        return studentService.read(id);
    }

    @PutMapping("{id}")
    public Student update(@PathVariable Long id, @RequestBody Student student) {
        return studentService.update(id, student);
    }

    @DeleteMapping("{id}")
    public Student delete(@PathVariable Long id) {
        return studentService.delete(id);
    }

    @GetMapping
    public List<Student> filterByAge(@RequestParam int age) {
        return studentService.filterByAge(age);
    }

    @GetMapping("byAgeBetween")
    public List<Student> findAllByAgeBetween(int fromAge, int toAge) {
        return studentService.findAllByAgeBetween(fromAge, toAge);
    }

    @GetMapping("{id}/faculty")
    public Faculty getFaculty(@PathVariable Long id) {
        return studentService.getFaculty(id);
    }

    @GetMapping("NameStartingWithLetterA")
    public List<String> getNamesStartingWithA() {
        List<Student> students = studentRepository.findByNameStartingWith("A");
        return students.stream()
                .map(student -> student.getName().toUpperCase())
                .sorted()
                .collect(Collectors.toList());
    }

    @GetMapping("average-age")
    public double getAverageAge() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .mapToInt(Student::getAge)
                .average()
                .orElse(0.0);
    }


}