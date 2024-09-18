package com.sky.hogwarts.Impl;

import com.sky.hogwarts.Model.Avatar;
import com.sky.hogwarts.Model.Student;
import com.sky.hogwarts.Repository.AvatarRepository;
import com.sky.hogwarts.Repository.StudentRepository;
import com.sky.hogwarts.Service.AvatarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class AvatarServiceImpl implements AvatarService {

    Logger logger = LoggerFactory.getLogger(AvatarServiceImpl.class);


    @Value("${path.to.avatars.folder}")
    private String avatarsDir;

    private final int BUFFER_SIZE = 1024;

    private final StudentRepository studentRepository;
    private final AvatarRepository avatarRepository;

    public AvatarServiceImpl(StudentRepository studentRepository, AvatarRepository avatarRepository) {
        this.studentRepository = studentRepository;
        this.avatarRepository = avatarRepository;
    }

    @Override
    public Avatar uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException {
        logger.info("Was evoked method for uploading the avatar for student with id {}", studentId);
        Student student = studentRepository.findById(studentId).orElseThrow(() -> {
            logger.error("Student with id {} is not in database", studentId);
            return new IllegalArgumentException("Student with id " + studentId + " is not found in database");
                });

        Path avatarPath = saveToLocalDirectory(student, avatarFile);
        Avatar avatar = saveToDb(student, avatarPath, avatarFile);

        return avatar;
    }

    @Override
    public Avatar findAvatar(Long avatarId) {
        logger.info("Was evoked method for finding the avatar with id {}", avatarId);
        return avatarRepository.findById(avatarId).orElseThrow(() -> {
            logger.error("Avatar with id {} is not in database", avatarId);
            return new IllegalArgumentException("Avatar with id " + avatarId + " is not found in database");
        });
    }

    private Path saveToLocalDirectory(Student student, MultipartFile avatarFile) throws IOException {
        Path avatarPath = Path.of(avatarsDir, "student" + student.getId() + "." + getExtensions(avatarFile.getOriginalFilename()));
        Files.createDirectories(avatarPath.getParent());
        Files.deleteIfExists(avatarPath);

        try (
                InputStream is = avatarFile.getInputStream();
                OutputStream os = Files.newOutputStream(avatarPath, CREATE_NEW);

                BufferedInputStream bis = new BufferedInputStream(is, BUFFER_SIZE);
                BufferedOutputStream bos = new BufferedOutputStream(os, BUFFER_SIZE)
        ) {
            bis.transferTo(bos);
        }

        return avatarPath;
    }

    private Avatar saveToDb(Student student, Path avatarPath, MultipartFile avatarFile) throws IOException {
        Avatar avatar = getAvatarByStudent(student);
        avatar.setFilePath(avatarPath.toString());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setData(avatarFile.getBytes());

        return avatarRepository.save(avatar);
    }

    private Avatar getAvatarByStudent(Student student) {
        logger.info("was evoked method for finding avatar by student: {}", student);
        return avatarRepository.findByStudent(student).orElseGet(() -> {
            logger.warn("Student : {} has no avatar, making new avatar",student);
            Avatar avatar = new Avatar();
            avatar.setStudent(student);
            return avatar;
        });
    }

    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
