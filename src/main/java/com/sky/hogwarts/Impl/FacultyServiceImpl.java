package com.sky.hogwarts.Impl;

import com.sky.hogwarts.Error.ResourceNotFoundException;
import com.sky.hogwarts.Model.Faculty;
import com.sky.hogwarts.Repository.FacultyRepository;
import com.sky.hogwarts.Service.FacultyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty add(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty get(Long id) {
        return facultyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Faculty not found with id " + id));
    }

    @Override
    public Faculty update(Long id, Faculty faculty) {
        Faculty existingFaculty = facultyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Faculty not found with id " + id));
        existingFaculty.setName(faculty.getName());
        existingFaculty.setColor(faculty.getColor());
        return facultyRepository.save(existingFaculty);
    }

    @Override
    public void delete(Long id) {
        if (!facultyRepository.existsById(id)) {
            throw new ResourceNotFoundException("Faculty not found with id " + id);
        }
        facultyRepository.deleteById(id);
    }

    @Override
    public List<Faculty> findByNameOrColor(String query) {
        return facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(query, query);
    }
}
