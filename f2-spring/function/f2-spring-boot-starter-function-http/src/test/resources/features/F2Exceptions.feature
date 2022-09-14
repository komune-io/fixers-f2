Feature:
  Background:
    Given I start a valid spring application context

  Scenario: Catalog contains f2 function
    Then Exceptions: Function catalog contains
      | basicFunction     |
      | notFoundFunction  |
      | forbiddenFunction |

  Scenario: Trigger 400 Bad Request Exception
    When Exceptions: Execute function "basicFunction" with
      | name   | wrongParam |
      | blblbl | fake       |
    Then Exceptions: An exception with code 400 has been thrown for "basicFunction"

  Scenario: Trigger 404 Not Found Exception
    When Exceptions: Execute supplier "notFoundFunction"
    Then Exceptions: An exception with code 404 has been thrown for "notFoundFunction"

  Scenario: Trigger 403 Forbidden Exception
    When Exceptions: Execute supplier "forbiddenFunction"
    Then Exceptions: An exception with code 403 has been thrown for "forbiddenFunction"
