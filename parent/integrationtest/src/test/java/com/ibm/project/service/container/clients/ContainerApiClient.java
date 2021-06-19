package com.ibm.project.service.container.clients;

import com.ibm.project.service.container.controllers.ContainerApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="container", url="http://localhost:8080/api/v1")
public interface ContainerApiClient extends ContainerApi {
}