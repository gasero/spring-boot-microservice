package com.ibm.project.service.container.steps;

import com.ibm.project.service.container.SpringIntegrationTest;
import com.ibm.project.service.container.clients.ContainerClient;
import com.ibm.project.service.container.models.ContainerContext;
import com.ibm.project.service.container.models.GlobalResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenericSteps extends SpringIntegrationTest {

    static final String WHEN_RESPONSE_KEY = "when_response";
    final GlobalResponses globalResponses;
    final ContainerClient containerClient;

    @Autowired
    ContainerContext containerContext;

    public GenericSteps(GlobalResponses globalResponses, ContainerClient client) {
        this.globalResponses = globalResponses;
        this.containerClient = client;
    }
}
