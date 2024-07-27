package com.example.convert_file_base64.controllers;

import com.example.convert_file_base64.model.FileDTO;
import com.example.convert_file_base64.entity.FileEntity;
import com.example.convert_file_base64.service.FileService;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.*;

class FileControllerTest {

    @Mock
    private FileService fileService;

    @InjectMocks
    private FileController fileController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createFile() {
        FileDTO fileDTO = new FileDTO();
        fileDTO.setFile(Base64.getEncoder().encodeToString("Zmlyc3QKc2Vjb25kCnRoaXJk".getBytes()));
        fileDTO.setTitle("Test title");
        fileDTO.setCreationDate(LocalDateTime.now());
        fileDTO.setDescription("Test description");

        FileEntity fileEntity = new FileEntity();
        fileEntity.setId(1L);

        when(fileService.saveFile(any(FileEntity.class))).thenReturn(1L);

        ResponseEntity<Long> response = fileController.createFile(fileDTO);

        assertEquals(OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody());
        verify(fileService, times(1)).saveFile(any(FileEntity.class));
    }

    @Test
    void getFile() {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setId(1L);
        fileEntity.setFile("Zmlyc3QKc2Vjb25kCnRoaXJk".getBytes());
        fileEntity.setTitle("Test title");
        fileEntity.setCreationDate(LocalDateTime.now());
        fileEntity.setDescription("Test description");

        when(fileService.getFile(1L)).thenReturn(Optional.of(fileEntity));

        ResponseEntity<FileDTO> response = fileController.getFile(1L);

        assertEquals(OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Test title", response.getBody().getTitle());
        assertEquals("Test description", response.getBody().getDescription());
        assertEquals(Base64.getEncoder().encodeToString("Zmlyc3QKc2Vjb25kCnRoaXJk".getBytes()), response.getBody().getFile());
        verify(fileService, times(1)).getFile(1L);
    }

    @Test
    public void testGetAllFiles() {
        FileEntity fileEntity1 = new FileEntity();
        fileEntity1.setId(1L);
        fileEntity1.setFile("test content 1".getBytes());
        fileEntity1.setTitle("Test Title 1");
        fileEntity1.setCreationDate(LocalDateTime.now());
        fileEntity1.setDescription("Test Description 1");

        FileEntity fileEntity2 = new FileEntity();
        fileEntity2.setId(2L);
        fileEntity2.setFile("test content 2".getBytes());
        fileEntity2.setTitle("Test Title 2");
        fileEntity2.setCreationDate(LocalDateTime.now());
        fileEntity2.setDescription("Test Description 2");

        List<FileEntity> fileEntities = new ArrayList<>();
        fileEntities.add(fileEntity1);
        fileEntities.add(fileEntity2);

        when(fileService.getAllFiles()).thenReturn(fileEntities);

        ResponseEntity<List<FileDTO>> response = fileController.getAllFiles();

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }
}
