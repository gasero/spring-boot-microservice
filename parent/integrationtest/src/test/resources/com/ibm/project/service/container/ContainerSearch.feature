Feature: Container Search

  @smoke-tests
  Scenario: Verify API schema for search results
    Given valid container has been searched and found
    When the search is submitted
    Then the endpoint responds with "OK"
    And response contains "totalSize" property
    And response contains "result" property

  @smoke-tests
  Scenario Outline: Verify all responses for bad requests
    Given I select a container with name of "<ContainerName>" and container version of "<ContainerVersion>"
    When the search is submitted
    Then the endpoint responds with "<Response>"
    Examples:
      | ContainerName | ContainerVersion | Response    |
      |               |                  | BAD_REQUEST |
      | abc           |                  | BAD_REQUEST |
      |               | 123              | BAD_REQUEST |
      | abcd          | abcd             | BAD_REQUEST |


  Scenario: Search one container with match
    Given I select a container with name of "name 1" and container version of "1"
    When the search is submitted
    Then the endpoint responds with "OK"
    And response contains "totalSize" property with number value 1
    And response contains "result" array property with length of 1
    And "result" property contains "name" subproperty with value "name 1"
    And "result" property contains "version" subproperty with number value 1

  Scenario: More entries on same container name
    Given I select a container with name of "temp" and container version of "1"
    And result set is limited to 5 records
    When the search is submitted
    Then the endpoint responds with "OK"
    And response contains "totalSize" property with value 51
    And response contains "result" array property with length of 5

  Scenario: Response time when doing search for few containers
    Given I select a container with name of "temp" and container version of "1"
    And result set is limited to 25 records
    When the search is submitted
    Then the endpoint responds with "OK"
    And response contains "totalSize" property with value 51
    And response contains "result" array property with length of 25
    And the result must be shown within 1 seconds

  Scenario: Search one container with no match
    Given I select a container with name of "non_existing" and container version of "1"
    When the search is submitted
    Then the endpoint responds with "OK"
    And response contains "totalSize" property with value 0
    And response contains "result" array property with length of 0
