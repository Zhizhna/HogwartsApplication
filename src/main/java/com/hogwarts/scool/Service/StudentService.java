package com.hogwarts.scool.Service;

import com.hogwarts.scool.Model.Student;

import java.util.List;

public interface StudentService {

    Student add(Student student);

    Student get(Long id);

    Student update(Long id, Student student);

    void delete(Long id);

    List<Student> getByAge(int age);

    List<Student> findStudentsByAgeBetween(int min, int max);
}