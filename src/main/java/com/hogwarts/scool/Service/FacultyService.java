package com.hogwarts.scool.Service;

import com.hogwarts.scool.Model.Faculty;

import java.util.List;

public interface FacultyService {

    Faculty add(Faculty faculty);

    Faculty get(Long id);

    Faculty update(Long id, Faculty student);

    void delete(Long id);

    List<Faculty> getByColor(String color);
}