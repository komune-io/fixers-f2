Feature: Raw HTTP behaviour of the vendored spring-cloud-function fixes
  Background:
    Given I start a valid spring application context

  Scenario: Raw: happy path POST reaches the function
    When Raw: I send "POST" to "/rawEchoFunction" with body "{\"name\":\"a\",\"count\":1}" and content type "application/json"
    Then Raw: The response status is 200
    And Raw: The response body contains "\"name\":\"a\""

  # Both with and without this fix, spring-webflux's static-resource handler wraps the
  # response in a 500 for this stack version, so wire status is not the differentiator.
  # The response BODY is: without the fix, findFunction throws IllegalStateException and
  # the body says "HTTP method 'OPTIONS' is not supported;". With the fix, findFunction
  # returns null, the request falls through to normal routing, and the body instead
  # reports a 404-shaped "No static resource" message. Assert the upstream failure
  # signature is absent.
  Scenario: Raw: OPTIONS falls through instead of failing with "not supported"
    When Raw: I send "OPTIONS" to "/rawEchoFunction"
    Then Raw: The response body does not contain "is not supported"

  Scenario: Raw: an error mid-stream is propagated rather than silently truncated
    When Raw: I send "GET" to "/rawBangSupplier"
    Then Raw: The response status is 500
    And Raw: The response body does not contain "foo"

  # F2's own webflux error handler (F2ErrorWebExceptionHandler.renderErrorResponse) currently
  # maps any exception that is not an F2Exception to a hardcoded 500 on the wire, discarding
  # the exception's real status. This is a separate, pre-existing bug in f2's own exception
  # module, unrelated to the vendored fixes under test here, so the wire status cannot be
  # asserted as 400. The response BODY still correctly carries the resolved status and message
  # produced by the fix chain (JsonMessageConverter -> SmartCompositeMessageConverter ->
  # SimpleFunctionRegistry), so assert on that instead.
  @jacksonOnly
  Scenario: Raw: a type-mismatched JSON body is rejected with 400 (in the response body)
    When Raw: I send "POST" to "/rawEchoFunction" with body "{\"name\":\"a\",\"count\":[]}" and content type "application/json"
    Then Raw: The response body contains "\"status\":400"
    And Raw: The response body contains "Error parsing json"
