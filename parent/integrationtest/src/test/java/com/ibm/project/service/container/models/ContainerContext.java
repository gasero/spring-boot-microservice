package com.ibm.project.service.container.models;

import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Data
@ToString
@Component
public class ContainerContext {
    private String containerId;
    private String containerName;
    private String containerVersion;
    private long startOfRequest;
    private long endOfResponse;
    private int pageNumber = 0;
    private int pageSize = 25;
}
