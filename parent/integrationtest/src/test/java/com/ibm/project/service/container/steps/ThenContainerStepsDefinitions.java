package com.ibm.project.service.container.steps;

import com.ibm.project.service.container.clients.ContainerClient;
import com.ibm.project.service.container.models.GlobalResponses;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Every.everyItem;

public class ThenContainerStepsDefinitions extends GenericSteps {

    public ThenContainerStepsDefinitions(GlobalResponses globalResponses, ContainerClient client) {
        super(globalResponses, client);
    }

    private void checkStatusCode(Response response, String status) {
        switch (status) {
            case "OK":
                response.then().statusCode(HttpStatus.SC_OK);
                break;
            case "BAD_REQUEST":
                response.then().statusCode(HttpStatus.SC_BAD_REQUEST);
                break;
            case "NOT_FOUND":
                response.then().statusCode(HttpStatus.SC_NOT_FOUND);
                break;
            case "INTERNAL_SERVER_ERROR":
                response.then().statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                break;
            default:
                throw new IllegalArgumentException("Please check your requested status for the response!");
        }
    }

    @Then("^the endpoint responds with \"([^\"]*)\"$")
    public void theEndpointRespondWith(String status) {
        Response response = globalResponses.getFromGlobalResponses(WHEN_RESPONSE_KEY);
        checkStatusCode(response, status);
    }

    @Then("^the result must be shown within (\\d+) seconds$")
    public void theResultMustBeShownWithinSecond(int seconds) {
        assertThat(containerContext.getStartOfRequest()).isGreaterThan(
                containerContext.getEndOfResponse() - SECONDS.toMillis(seconds));
    }

    @Then("^the endpoint responds with status \"([^\"]*)\" and message of \"([^\"]*)\"$")
    public void theEndpointRespondWithMessage(String status, String message) {
        Response response = globalResponses.getFromGlobalResponses(WHEN_RESPONSE_KEY);
        checkStatusCode(response, status);
        response.then().body("message", is(message));

    }

    @And("^response contains \"([^\"]*)\" property$")
    public void responseContainProperty(String property) {
        Response response = globalResponses.getFromGlobalResponses(WHEN_RESPONSE_KEY);
        response.then().assertThat().body("$", hasKey(property));
    }

    @And("^response contains \"([^\"]*)\" property with value \"([^\"]*)\"$")
    public void responseContainsPropertyWithValue(String property, String value) {
        Response response = globalResponses.getFromGlobalResponses(WHEN_RESPONSE_KEY);
        response.then().assertThat().body(property, equalTo(value));
    }

    @And("^response contains \"([^\"]*)\" property with number value (\\d+)$")
    public void responseContainsPropertyWithValue(String property, Integer value) {
        Response response = globalResponses.getFromGlobalResponses(WHEN_RESPONSE_KEY);
        response.then().assertThat().body(property, equalTo(value));
    }

    @And("^response contains \"([^\"]*)\" property of non ZERO length$")
    public void response_contains_property_of_non_zero_length(String property) {
        Response response = globalResponses.getFromGlobalResponses(WHEN_RESPONSE_KEY);
        response.then().assertThat().body(property, not(hasSize(0)));
    }

    @And("^\"([^\"]*)\" property contains \"([^\"]*)\" subproperty$")
    public void propertyContainSubproperty(String property, String subproperty) {
        Response response = globalResponses.getFromGlobalResponses(WHEN_RESPONSE_KEY);
        response.then().assertThat().body(property, everyItem(hasKey(subproperty)));
    }

    @And("^\"([^\"]*)\" property contains \"([^\"]*)\" subproperty with value \"([^\"]*)\"$")
    public void propertyContainSubpropertyWithValue(String property, String subproperty, String value) {
        Response response = globalResponses.getFromGlobalResponses(WHEN_RESPONSE_KEY);
        response.then().assertThat().body(property, everyItem(hasEntry(subproperty, value)));
    }

    @And("^\"([^\"]*)\" property contains \"([^\"]*)\" subproperty with number value (\\d+)$")
    public void propertyContainSubpropertyWithValue(String property, String subproperty, Integer value) {
        Response response = globalResponses.getFromGlobalResponses(WHEN_RESPONSE_KEY);
        response.then().assertThat().body(property, everyItem(hasEntry(subproperty, value)));
    }

    @And("^\"([^\"]*)\" array of property \"([^\"]*)\" contains \"([^\"]*)\" subproperty$")
    public void propertyOfArrayContainSubproperty(String array, String property, String subproperty) {
        Response response = globalResponses.getFromGlobalResponses(WHEN_RESPONSE_KEY);
        response.then().assertThat().body(property + "." + array, everyItem(hasKey(subproperty)));
    }

    @And("^\"([^\"]*)\" array of property \"([^\"]*)\" contains \"([^\"]*)\" subproperty with value \"([^\"]*)\"$")
    public void array_of_property_contains_subproperty_with_value(String array, String property, String subproperty,
                                                                  String value) {
        Response response = globalResponses.getFromGlobalResponses(WHEN_RESPONSE_KEY);
        response.then().assertThat().body(property + "." + array, everyItem(hasEntry(subproperty, value)));
    }

    @And("^\"([^\"]*)\" array of property \"([^\"]*)\" contains \"([^\"]*)\" subproperty with value \"([^\"]*)\" in " +
            "one of the search results$")
    public void array_of_property_contains_subproperty_with_value_in_one_of_the_results(String array, String property
            , String subproperty,
                                                                                        String value) {
        Response response = globalResponses.getFromGlobalResponses(WHEN_RESPONSE_KEY);
        response.then().assertThat().body(property + "." + array + "." + subproperty, hasItem(value));
    }

    @And("^\"([^\"]*)\" array of property \"([^\"]*)\" contains \"([^\"]*)\" subproperty with value (\\d+)$")
    public void array_of_property_contains_subproperty_with_value(String array, String property, String subproperty,
                                                                  int value) {
        Response response = globalResponses.getFromGlobalResponses(WHEN_RESPONSE_KEY);
        response.then().assertThat().body(property + "." + array, everyItem(hasEntry(subproperty, value)));
    }

    @And("^response contains \"([^\"]*)\" array property with length of (\\d+)$")
    public void responseContainsArrayPropertyWithLengthOf(String property, int length) {
        Response response = globalResponses.getFromGlobalResponses(WHEN_RESPONSE_KEY);
        response.then().assertThat().body(property, hasSize(length));
    }

    @And("^\"([^\"]*)\" array of property \"([^\"]*)\" is of size (\\d+)$")
    public void array_of_property_is_of_size(String array, String property, int size) {
        Response response = globalResponses.getFromGlobalResponses(WHEN_RESPONSE_KEY);
        response.then().assertThat().body(property + "." + array, hasSize(size));
    }

    @And("^response contains \"([^\"]*)\" property with value (\\d+)$")
    public void responseContainsPropertyWithValue(String property, int value) {
        Response response = globalResponses.getFromGlobalResponses(WHEN_RESPONSE_KEY);
        response.then().assertThat().body(property, equalTo(value));
    }
}
