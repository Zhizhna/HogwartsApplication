package com.sky.hogwarts.Service;

import com.sky.hogwarts.Model.Avatar;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AvatarService {

    Avatar uploadAvatar(Long studentId, MultipartFile avatar) throws IOException;

    Avatar findAvatar(Long avatarId);
}
