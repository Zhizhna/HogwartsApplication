package com.hogwarts.scool.Impl;

import com.hogwarts.scool.Model.Faculty;
import com.hogwarts.scool.Service.FacultyService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final Map<Long, Faculty> faculties = new HashMap<>();

    private Long currentId = 1L;

    @Override
    public Faculty add(Faculty faculty) {
        Long id = ++currentId;
        faculty.setId(id);
        faculties.put(id, faculty);

        return faculties.get(id);
    }

    @Override
    public Faculty get(Long id) {
        return faculties.get(id);
    }

    @Override
    public Faculty update(Long id, Faculty faculty) {
        if (faculties.containsKey(id)) {
            Faculty facultyById = faculties.get(id);
            facultyById.setName(facultyById.getName());
            facultyById.setColor(facultyById.getColor());
            faculties.put(id, facultyById);
            return faculties.get(id);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        faculties.remove(id);
    }

    @Override
    public List<Faculty> getByColor(String color) {
        return faculties.values()
                .stream()
                .filter(it -> it.getColor().equals(color))
                .collect(Collectors.toList());
    }
}