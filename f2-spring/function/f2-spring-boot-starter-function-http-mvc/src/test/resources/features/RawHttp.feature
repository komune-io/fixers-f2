Feature: Raw HTTP behaviour of the vendored spring-cloud-function fixes
  Background:
    Given I start a valid spring application context

  Scenario: Raw: happy path POST reaches the function
    When Raw: I send "POST" to "/rawEchoFunction" with body "{\"name\":\"a\",\"count\":1}" and content type "application/json"
    Then Raw: The response status is 200
    And Raw: The response body contains "\"name\":\"a\""

  # Unlike the reactive runtime, MVC's OPTIONS handling is clean: once findFunction
  # returns null for OPTIONS (the fix), no handler mapping claims the request and the
  # DispatcherServlet's own default OPTIONS handling answers with 200 and an empty body.
  # Without the fix, findFunction throws IllegalStateException and the body says
  # "HTTP method 'OPTIONS' is not supported;". Assert the upstream failure signature
  # is absent.
  Scenario: Raw: OPTIONS falls through instead of failing with "not supported"
    When Raw: I send "OPTIONS" to "/rawEchoFunction"
    Then Raw: The response status is 200
    And Raw: The response body does not contain "is not supported"

  Scenario: Raw: an error mid-stream is propagated rather than silently truncated
    When Raw: I send "GET" to "/rawBangSupplier"
    Then Raw: The response status is 500
    And Raw: The response body does not contain "foo"

  @jacksonOnly
  Scenario: Raw: a type-mismatched JSON body is rejected with 400
    When Raw: I send "POST" to "/rawEchoFunction" with body "{\"name\":\"a\",\"count\":[]}" and content type "application/json"
    Then Raw: The response status is 400
    And Raw: The response body contains "\"refusal\":\"http.INVALID_PAYLOAD\""
    And Raw: The response body contains "\"message\":\"Unreadable request body\""
    And Raw: The response body contains "\"parameter\":\"count\""
    And Raw: The response body does not contain "Error parsing json"

  @jacksonOnly
  Scenario: Raw: a truncated JSON body is rejected with 400
    When Raw: I send "POST" to "/rawEchoFunction" with body "{\"name\":\"a\",\"count\":1" and content type "application/json"
    Then Raw: The response status is 400
    And Raw: The response body contains "\"refusal\":\"http.INVALID_PAYLOAD\""
    And Raw: The response body contains "\"message\":\"Unreadable request body\""
    And Raw: The response body does not contain "Error parsing json"

  @jacksonOnly
  Scenario: Raw: a truncated CloudEvents JSON body is rejected with 400
    When Raw: I send "POST" to "/rawEchoFunction" with body "{\"name\":\"a\",\"count\":1" and content type "application/cloudevents+json"
    Then Raw: The response status is 400
    And Raw: The response body contains "\"refusal\":\"http.INVALID_PAYLOAD\""
