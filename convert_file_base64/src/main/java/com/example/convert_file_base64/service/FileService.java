package com.example.convert_file_base64.service;

import com.example.convert_file_base64.entity.FileEntity;
import com.example.convert_file_base64.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    public Long saveFile(FileEntity fileEntity) {
        FileEntity savedFile = fileRepository.save(fileEntity);
        return savedFile.getId();
    }

    public Optional<FileEntity> getFile(Long id) {
        return fileRepository.findById(id);
    }

    public List<FileEntity> getAllFiles() {
        return fileRepository.findAll();
    }
}
