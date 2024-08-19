package com.hogwarts.scool.Impl;

import com.hogwarts.scool.Model.Student;
import com.hogwarts.scool.Service.StudentService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final Map<Long, Student> students = new HashMap<>();

    private Long currentId = 1L;

    @Override
    public Student add(Student student) {
        Long id = ++currentId;
        student.setId(id);
        students.put(id, student);

        return students.get(id);
    }

    @Override
    public Student get(Long id) {
        return students.get(id);
    }

    @Override
    public Student update(Long id, Student student) {
        if (students.containsKey(id)) {
            Student studentById = students.get(id);
            studentById.setName(student.getName());
            studentById.setAge(student.getAge());
            students.put(id, studentById);
            return students.get(id);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        students.remove(id);
    }

    @Override
    public List<Student> getByAge(int age) {
        return students.values()
                .stream()
                .filter(it -> it.getAge() == age)
                .collect(Collectors.toList());
    }
}