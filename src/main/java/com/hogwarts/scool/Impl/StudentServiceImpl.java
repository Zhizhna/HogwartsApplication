package com.hogwarts.scool.Impl;

import com.hogwarts.scool.Error.ResourceNotFoundException;
import com.hogwarts.scool.Model.Faculty;
import com.hogwarts.scool.Model.Student;
import com.hogwarts.scool.Repository.FacultyRepository;
import com.hogwarts.scool.Repository.StudentRepository;
import com.hogwarts.scool.Service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;

    public StudentServiceImpl(StudentRepository studentRepository, FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Student add(Student student) {
        if (student.getFaculty() != null && student.getFaculty().getId() != null) {
            Faculty faculty = facultyRepository.findById(student.getFaculty().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Faculty not found with id " + student.getFaculty().getId()));
            student.setFaculty(faculty);
        }
        return studentRepository.save(student);
    }

    @Override
    public Student get(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + id));
    }

    @Override
    public Student update(Long id, Student student) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + id));
        existingStudent.setName(student.getName());
        existingStudent.setAge(student.getAge());

        if (student.getFaculty() != null && student.getFaculty().getId() != null) {
            Faculty faculty = facultyRepository.findById(student.getFaculty().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Faculty not found with id " + student.getFaculty().getId()));
            existingStudent.setFaculty(faculty);
        } else {
            existingStudent.setFaculty(null);
        }

        return studentRepository.save(existingStudent);
    }

    @Override
    public void delete(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Student not found with id " + id);
        }
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getByAge(int age) {
        return studentRepository.getByAge(age);
    }

    @Override
    public List<Student> findStudentsByAgeBetween(int min, int max) {
        return studentRepository.findByAgeBetween(min, max);
    }
}