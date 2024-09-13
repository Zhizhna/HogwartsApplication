package com.sky.hogwarts.Repository;

import com.sky.hogwarts.Model.Avatar;
import com.sky.hogwarts.Model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {

    Optional<Avatar> findByStudent(Student student);

    Page<Avatar> findAll(Pageable pageable);

}