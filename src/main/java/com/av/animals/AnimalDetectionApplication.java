package com.av.animals;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Java Animal Service API", version = "1.0", description = "Java service OpenAPI docs"))
public class AnimalDetectionApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnimalDetectionApplication.class, args);
    }
}
