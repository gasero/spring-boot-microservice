Feature: Container

  @smoke-tests
  Scenario: Valid container selected
    Given valid container has been searched and found
    When I request a container
    Then the endpoint responds with "OK"
    And response contains "id" property
    And response contains "name" property

  Scenario: Specific container selected
    Given that I have selected a container by container id of "abc"
    When I request a container
    Then the endpoint responds with "OK"
    And response contains "id" property with value "abc"
    And response contains "name" property with value "name 1"
    And response contains "version" property with value 1
    And response contains "created" property with value "2016-08-22T12:30:00Z"

  @smoke-tests
  Scenario Outline: Verify all responses for bad requests
    Given that I have selected a container by container id of "<ContainerId>"
    When I request a container
    Then the endpoint responds with "<Response>"
    Examples:
      | ContainerId  | Response  |
      | non_existing | NOT_FOUND |
      |              | NOT_FOUND |

  @smoke-tests
  Scenario: Response time when request for details
    Given valid container has been searched and found
    When I request a container
    Then the result must be shown within 1 seconds