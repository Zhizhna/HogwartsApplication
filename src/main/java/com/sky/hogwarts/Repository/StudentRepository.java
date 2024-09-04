package com.sky.hogwarts.Repository;

import com.sky.hogwarts.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> getByAge(int age);
    List<Student> findByAgeBetween(int min, int max);


}
