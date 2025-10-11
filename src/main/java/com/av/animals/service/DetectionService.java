package com.av.animals.service;

import com.av.animals.helper.MultipartInputStreamFileResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class DetectionService {
    private static final Logger logger = LoggerFactory.getLogger(DetectionService.class);

    private static final String PYTHON_SERVER_URL = "http://localhost:5001/predict";
    private static final String FILE = "file";

    public String sendToYoloServer(MultipartFile file) throws IOException {

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add(FILE, new MultipartInputStreamFileResource(file.getInputStream(), file.getOriginalFilename()));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.postForEntity(PYTHON_SERVER_URL, requestEntity, String.class).getBody();
    }
}
