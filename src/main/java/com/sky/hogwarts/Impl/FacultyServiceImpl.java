package com.sky.hogwarts.Impl;

import com.sky.hogwarts.Model.Faculty;
import com.sky.hogwarts.Model.Student;
import com.sky.hogwarts.Repository.FacultyRepository;
import com.sky.hogwarts.Service.FacultyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    Logger logger = LoggerFactory.getLogger(FacultyServiceImpl.class);


    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty create(Faculty faculty) {
        logger.info("Was invoked method for creating a faculty: {}", faculty);
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty read(Long id) {
        logger.info("Was evoked method for finding a faculty with id: {}", id);
        return facultyRepository.findById(id).orElse(null);
    }

    @Override
    public Faculty update(Long id, Faculty faculty) {
        return facultyRepository.findById(id).map(facultyFromDb -> {
            logger.debug("Updated Faculty: {} with id {}",faculty,id);
            facultyFromDb.setName(faculty.getName());
            facultyFromDb.setColor(faculty.getColor());
            facultyRepository.save(facultyFromDb);
            return facultyFromDb;
        }).orElseGet(()->{
            logger.warn("No faculty was found with id {}", id);
            return null;
        });
    }

    @Override
    public Faculty delete(Long id) {
        logger.info("Was evoked method for deleting a faculty with id: {}", id);
        return facultyRepository.findById(id).map(faculty -> {
            logger.debug("The faculty {} with id: {} was deleted", faculty,id);
            facultyRepository.deleteById(id);
            return faculty;
        }).orElseGet(()->{
            logger.warn("No faculty was deleted with id {}", id);
            return null;
        });
    }

    @Override
    public List<Faculty> filterByColor(String color) {
        logger.info("Was evoked method for filtering a faculty in color: {}", color);
        return facultyRepository.findAllByColor(color);
    }

    @Override
    public List<Faculty> findAllByNameIgnoreCaseOrColorIgnoreCase(String name, String color) {
        logger.info("Was evoked method for finding a all faculties ignoring case or color ignore case");
        return facultyRepository.findAllByNameIgnoreCaseOrColorIgnoreCase(name, color);
    }

    @Override
    public List<Student> getStudents(Long facultyId) {
        logger.info("Was evoked method for finding students in faculty with id: {}", facultyId);
        return facultyRepository.findById(facultyId)
                .map(Faculty::getStudents)
                .orElseGet(()->{
                    logger.warn("No students was found found in faculty with id {}", facultyId);
                    return null;
                });
    }
}