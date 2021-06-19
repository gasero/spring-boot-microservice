Feature: Container service health

  @smoke-tests
  Scenario: Check Container endpoint availability
    When I check if the service is up
    Then the endpoint responds with "OK"