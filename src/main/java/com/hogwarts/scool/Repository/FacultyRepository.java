package com.hogwarts.scool.Repository;

import com.hogwarts.scool.Model.Faculty;
import com.hogwarts.scool.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    List<Faculty> findByNameIgnoreCaseOrColorIgnoreCase(String name, String color);
}
