Feature:
  Scenario: Catalog contains function
    When I start a valid spring application context
    Then Function catalog contains
      | functionSingle |
      | supplierSingle |
      | consumerSingle |

  Scenario: Execute Single function
    Given I start a valid spring application context
    When Execute function functionSingle with
      | reverse |
    Then The result for "functionSingle" is
      | esrever |

  Scenario: Execute Single supplier function
    Given I start a valid spring application context
    When Execute supplier supplierSingle
    Then The result for "supplierSingle" is
      | supplierValuePureKotlinValue |

  Scenario: Execute Single consumer function
    Given I start a valid spring application context
    When Execute consumer consumerSingle with
      | reverse |
    Then The result for "consumerSingle" is
      | reverse |
