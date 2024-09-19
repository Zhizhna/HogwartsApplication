package com.sky.hogwarts.Repository;

import com.sky.hogwarts.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAllByAge(int age);

    List<Student> findAllByAgeBetween(int fromAge, int toAge);

    @Query("SELECT COUNT(s) FROM Student s")
    long getTotalNumberOfStudents();

    @Query("SELECT AVG(s.age) FROM Student s")
    double getAverageAgeOfStudents();

    @Query("SELECT s FROM Student s ORDER BY s.id DESC")
    List<Student> findLastFiveStudents(Pageable pageable);

    List<Student> findByNameStartingWith(String prefix);
}
