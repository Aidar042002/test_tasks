package com.example.convert_file_base64.controllers;

import com.example.convert_file_base64.model.FileDTO;
import com.example.convert_file_base64.entity.FileEntity;
import com.example.convert_file_base64.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/files")
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * Добавление нового файла
     * @param fileDTO
     * @return
     */
    @PostMapping
    public ResponseEntity<Long> createFile(@RequestBody FileDTO fileDTO) {

        if (fileDTO.getFile() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        FileEntity fileEntity = new FileEntity();
        fileEntity.setFile(Base64.getDecoder().decode(fileDTO.getFile()));
        fileEntity.setTitle(fileDTO.getTitle());
        fileEntity.setCreationDate(fileDTO.getCreationDate());
        fileEntity.setDescription(fileDTO.getDescription());

        Long id = fileService.saveFile(fileEntity);
        return ResponseEntity.ok(id);
    }

    /**
     * Получение файла
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<FileDTO> getFile(@PathVariable Long id) {
        Optional<FileEntity> fileEntityOpt = fileService.getFile(id);
        if (fileEntityOpt.isPresent()) {
            FileEntity fileEntity = fileEntityOpt.get();
            FileDTO fileDTO = new FileDTO();
            fileDTO.setId(fileEntity.getId());
            fileDTO.setFile(Base64.getEncoder().encodeToString(fileEntity.getFile()));
            fileDTO.setTitle(fileEntity.getTitle());
            fileDTO.setCreationDate(fileEntity.getCreationDate());
            fileDTO.setDescription(fileEntity.getDescription());
            return ResponseEntity.ok(fileDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Получение списка файлов
     * @return
     */
    @GetMapping
    public ResponseEntity<List<FileDTO>> getAllFiles() {
        List<FileEntity> fileEntities = fileService.getAllFiles();
        List<FileDTO> fileDTOs = new ArrayList<>();

        for (FileEntity fileEntity : fileEntities) {
            FileDTO fileDTO = new FileDTO();
            fileDTO.setId(fileEntity.getId());
            fileDTO.setFile(Base64.getEncoder().encodeToString(fileEntity.getFile()));
            fileDTO.setTitle(fileEntity.getTitle());
            fileDTO.setCreationDate(fileEntity.getCreationDate());
            fileDTO.setDescription(fileEntity.getDescription());
            fileDTOs.add(fileDTO);
        }

        return ResponseEntity.ok(fileDTOs);
    }

}
