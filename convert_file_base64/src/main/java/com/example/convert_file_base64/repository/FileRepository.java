package com.example.convert_file_base64.repository;

import com.example.convert_file_base64.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
}
