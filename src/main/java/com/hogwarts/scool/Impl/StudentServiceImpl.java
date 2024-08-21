package com.hogwarts.scool.Impl;

import com.hogwarts.scool.Model.Student;
import com.hogwarts.scool.Repository.StudentRepository;
import com.hogwarts.scool.Service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student add(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student get(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Student update(Long id, Student student) {
        return studentRepository.findById(id).map(student1 -> {
            student1.setAge(student.getAge());
            student1.setName(student.getName());
            studentRepository.save(student1);
            return student1;
        }).orElse(null);
    }

    @Override
    public Student delete(Long id) {
        return studentRepository.findById(id).map(student ->{
            studentRepository.deleteById(id);
            return student;
        }).orElse(null);
    }

    @Override
    public List<Student> getByAge(int age) {
        return studentRepository.getByAge(age);
    }
}