package com.ibm.project.service.container.steps;

import com.ibm.project.service.container.clients.ContainerClient;
import com.ibm.project.service.container.models.GlobalResponses;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import io.restassured.response.Response;

import java.time.Instant;

public class WhenContainerStepsDefinitions extends GenericSteps {

    public WhenContainerStepsDefinitions(GlobalResponses globalResponses, ContainerClient client) {
        super(globalResponses, client);
    }

    @When("^I check if the service is up$")
    public void iCheckIfTheServiceIsUp() {
        Response response = containerClient.getHealthEndpoint();
        globalResponses.addToGlobalResponses(WHEN_RESPONSE_KEY, response);
    }

    @When("^I request a container$")
    public void iRequestAContainer() {
        containerContext.setStartOfRequest(Instant.now().toEpochMilli());
        Response response = containerClient.getContainerEndpoint(containerContext.getContainerId());
        containerContext.setEndOfResponse(Instant.now().toEpochMilli());
        globalResponses.addToGlobalResponses(WHEN_RESPONSE_KEY, response);
    }


    @When("^container name \"([^\"]*)\" is specified as criteria$")
    public void containerNameIsSpecifiedAsCriteria(String containerNumber) {
        containerContext.setContainerName(containerNumber);
    }

    @When("^the search is submitted$")
    public void theSearchIsSubmitted() {
        containerContext.setStartOfRequest(Instant.now().toEpochMilli());
        Response response = containerClient.getSearchEndpoint(containerContext.getContainerName(),
                containerContext.getContainerVersion(), containerContext.getPageNumber(),
                containerContext.getPageSize());
        containerContext.setEndOfResponse(Instant.now().toEpochMilli());
        globalResponses.addToGlobalResponses(WHEN_RESPONSE_KEY, response);
    }

    @And("^result set is limited to (\\d+) records$")
    public void resultSetIsLimitedToRecords(int pageSize) {
        containerContext.setPageSize(pageSize);
    }
}
