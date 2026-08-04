Feature: Raw HTTP behaviour of the vendored spring-cloud-function fixes
  Background:
    Given I start a valid spring application context

  Scenario: Raw: happy path POST reaches the function
    When Raw: I send "POST" to "/rawEchoFunction" with body "{\"name\":\"a\",\"count\":1}" and content type "application/json"
    Then Raw: The response status is 200
    And Raw: The response body contains "\"name\":\"a\""
