package com.sky.hogwarts.Repository;

import com.sky.hogwarts.Model.Avatar;
import com.sky.hogwarts.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {

    Optional<Avatar> findByStudent(Student student);

}