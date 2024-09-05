package com.sky.hogwarts.Repository;

import com.sky.hogwarts.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAllByAge(int age);

    List<Student> findAllByAgeBetween(int fromAge, int toAge);

}
