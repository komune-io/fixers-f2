Feature:
  Scenario: Inject F2 function
    When I build a valid spring application context
    Then Instance is an injectable bean
      | name            |
      | functionCatalog |
      | functionF2    |
      | supplierF2    |
      | consumerF2    |

    Then Instance is not injectable bean
      | name    |
      | UNKNOWN |

  Scenario: Execute f2 function function
    Given I start a valid spring application context
    When Execute functionF2 with
      | reverse |
      | test    |
    Then The f2 result for "functionF2" is
      | esrever |
      | tset    |
  Scenario: Execute f2 supplier supplier function
    Given I start a valid spring application context
    When Execute supplierF2
    Then The f2 result for "supplierF2" is
      | supplierValuePureKotlinValue |

  Scenario: Execute f2 consumer function
    Given I start a valid spring application context
    When Execute consumerF2 with
      | reverse |
    Then The result for "consumerSingle" is "reverse"