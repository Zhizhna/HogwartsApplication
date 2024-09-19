package com.sky.hogwarts.Impl;


import com.sky.hogwarts.Model.Faculty;
import com.sky.hogwarts.Model.Student;
import com.sky.hogwarts.Repository.StudentRepository;
import com.sky.hogwarts.Service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student create(Student student) {
        logger.info("Was invoked method for creating a student: {}", student);
        try {
            return studentRepository.save(student);
        } catch (Exception exp) {
            logger.error("Error occurred while creating a student: {}", exp.getMessage());
            throw exp;
        }
    }

    @Override
    public Student read(Long id) {
        logger.info("Was invoked by a method for reading a student with ID: {}", id);
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Student update(Long id, Student student) {
        logger.info("Was invoked method for updating a student with id: {}", id);
        return studentRepository.findById(id).map(studentFromDb -> {
            studentFromDb.setName(student.getName());
            studentFromDb.setAge(student.getAge());
            studentRepository.save(studentFromDb);
            logger.debug("Updated student: {}", studentFromDb);
            return studentFromDb;
        }).orElseGet(() -> {
            logger.warn("No student found with id: {}", id);
            return null;
        });
    }

    @Override
    public Student delete(Long id) {
        logger.info("Was invoked method for deleting a student with id {}", id);
        return studentRepository.findById(id).map(student -> {
            studentRepository.deleteById(id);
            logger.debug("Deleted student with id: {}", id);
            return student;
        }).orElseGet(()->{
            logger.warn("No student was deleted with id: {}", id);
            return null;
        });
    }

    @Override
    public List<Student> filterByAge(int age) {
        logger.info("Was invoked method for filtering a student at age {}", age);
        return studentRepository.findAllByAge(age);
    }

    @Override
    public List<Student> findAllByAgeBetween(int fromAge, int toAge) {
        logger.info("Was invoked method for finding students at age from {} to age {}", fromAge,toAge);
        return studentRepository.findAllByAgeBetween(fromAge, toAge);
    }

    @Override
    public Faculty getFaculty(Long studentId) {
        logger.info("Was evoked method for finding a student with id: {}", studentId);
        return studentRepository.findById(studentId)
                .map(Student::getFaculty)
                .orElseGet(()->{
                    logger.warn("No faculties found for student with id: {}", studentId);
                    return null;
                });
    }
}