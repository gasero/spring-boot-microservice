package com.ibm.project.service.container.clients;

import com.ibm.project.service.container.utils.EnvironmentReader;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static io.restassured.RestAssured.given;

@Component
@Slf4j
public class ContainerClient {

    private static final String TOKEN = "token";

    private RequestSpecification getClient() {
        return given().baseUri(EnvironmentReader.getRoot());
    }

    public Response getContainerEndpoint(String containerId) {
        Response response;
        try {
            String endpoint = "/container/" + containerId;
            response = getClient().header("Authorization", TOKEN)
                    .basePath(endpoint)
                    .get();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("No records were found!", e);
        }
        return response;
    }

    public Response getSearchEndpoint(String name, String version, int pageNumber, int pageSize) {
        Response response;
        try {
            String endpoint = "/container/findByNameAndVersion";
            RequestSpecification params = getClient().header("Authorization", TOKEN)
                    .param("pageNumber", pageNumber)
                    .param("pageSize", pageSize);
            Optional.ofNullable(name).filter(x -> !x.isEmpty()).map(x -> params.param("name", x));
            Optional.ofNullable(version).filter(x -> !x.isEmpty()).map(x -> params.param("version", x));
            response = params.basePath(endpoint).get();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("No records were found!", e);
        }
        return response;
    }

    public Response getHealthEndpoint() {
        String endpoint = "/actuator/health";
        return getClient().get(endpoint);
    }
}
