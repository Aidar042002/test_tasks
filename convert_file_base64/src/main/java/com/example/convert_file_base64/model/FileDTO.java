package com.example.convert_file_base64.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileDTO {

    private Long id;
    private String file;
    private String title;
    private LocalDateTime creationDate;
    private String description;
}

