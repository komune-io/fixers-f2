Feature: Raw HTTP behaviour of the vendored spring-cloud-function fixes
  Background:
    Given I start a valid spring application context

  Scenario: Raw: happy path POST reaches the function
    When Raw: I send "POST" to "/rawEchoFunction" with body "{\"name\":\"a\",\"count\":1}" and content type "application/json"
    Then Raw: The response status is 200
    And Raw: The response body contains "\"name\":\"a\""

  # Without the fix, findFunction throws IllegalStateException and the body says
  # "HTTP method 'OPTIONS' is not supported;". With the fix, findFunction returns null,
  # the request falls through to normal routing, and Spring's own static-resource
  # handler answers with a 404-shaped "No static resource" body instead.
  Scenario: Raw: OPTIONS falls through instead of failing with "not supported"
    When Raw: I send "OPTIONS" to "/rawEchoFunction"
    Then Raw: The response status is 404
    And Raw: The response body does not contain "is not supported"

  Scenario: Raw: an error mid-stream is propagated rather than silently truncated
    When Raw: I send "GET" to "/rawBangSupplier"
    Then Raw: The response status is 500
    And Raw: The response body does not contain "foo"

  @jacksonOnly
  Scenario: Raw: a type-mismatched JSON body is rejected with 400
    When Raw: I send "POST" to "/rawEchoFunction" with body "{\"name\":\"a\",\"count\":[]}" and content type "application/json"
    Then Raw: The response status is 400
    And Raw: The response body contains "Error parsing json"
