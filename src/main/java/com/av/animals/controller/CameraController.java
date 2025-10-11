
package com.av.animals.controller;

import com.av.animals.service.DetectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/camera")
public class CameraController {
    private static final Logger logger = LoggerFactory.getLogger(CameraController.class);

    private final DetectionService detectionService;

    @Autowired
    public CameraController(DetectionService detectionService) {
        this.detectionService = detectionService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> detect(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("No file uploaded");
        }
        logger.info("File received: {}",file.getOriginalFilename());

        try {
            return ResponseEntity.badRequest().body(detectionService.sendToYoloServer(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


