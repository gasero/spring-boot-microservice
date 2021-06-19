package com.ibm.project.service.container.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "spring.application.pagination")
@Data
public class PaginationConfiguration {

    private Integer pageSize = 25;
}
