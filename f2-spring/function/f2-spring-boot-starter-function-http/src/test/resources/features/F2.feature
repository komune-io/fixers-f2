Feature:

  Scenario: Catalog contains f2 function
    When I start a valid spring application context
    Then Function catalog contains
      | functionF2 |
      | supplierF2 |
      | consumerF2 |

  Scenario: Execute f2 function function
    Given I start a valid spring application context
    When Execute function functionF2 with
      | reverse |
      | test    |
    Then The result for "functionF2" is
      | esrever |
      | tset    |

  Scenario: Execute f2 supplier supplier function
    Given I start a valid spring application context
    When Execute supplier supplierF2
    Then The result for "supplierF2" is
      | supplierValuePureKotlinValue |

  Scenario: Execute f2 consumer function
    Given I start a valid spring application context
    When Execute consumer consumerF2 with
      | reverse |
    Then The result for "consumerF2" is
      | reverse |