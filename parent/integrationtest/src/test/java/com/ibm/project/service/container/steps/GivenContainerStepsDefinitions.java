package com.ibm.project.service.container.steps;

import com.ibm.project.service.container.clients.ContainerClient;
import com.ibm.project.service.container.domains.Container;
import com.ibm.project.service.container.models.GlobalResponses;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;

public class GivenContainerStepsDefinitions extends GenericSteps {

    public GivenContainerStepsDefinitions(GlobalResponses globalResponses, ContainerClient client) {
        super(globalResponses, client);
    }

    @Given("^valid container has been searched and found$")
    public void validContainerHasBeenFound() {
        Slice<Container> findFirst = getContainerRepository().findAll(PageRequest.of(0, 1));
        Container container = findFirst.getContent().get(0);
        containerContext.setContainerId(container.getId());
        containerContext.setContainerName(container.getName());
        containerContext.setContainerVersion("" + container.getVersion());
    }

    @And("^that I have selected a container by container id of \"([^\"]*)\"$")
    public void thatIHaveSelectedAContainerById(String id) {
        containerContext.setContainerId(id);
    }

    @Given("^I select a container with name of \"([^\"]*)\" and container version of \"([^\"]*)\"$")
    public void iSelectAContainerWithNameOfAndContainerVersionOf(String name, String version) {
        containerContext.setContainerName(name);
        containerContext.setContainerVersion(version);
    }
}
