package com.hogwarts.scool.Repository;

import com.hogwarts.scool.Model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}
