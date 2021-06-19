package com.ibm.project.service.container;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * The application class for the container spring boot service.
 */
@SpringBootApplication
public class ContainerApplication {

    /**
     * Standalone spring boot starter.
     *
     * @param args arguments for the spring boot app run.
     */
    public static void main(String... args) {
        SpringApplication.run(ContainerApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
