Feature:
  Scenario: Inject Flow function
    When I build a valid spring application context
    Then Instance is an injectable bean
      | name            |
      | functionCatalog |
      | functionFlow    |
      | supplierFlow    |
      | consumerFlow    |

    Then Instance is not injectable bean
      | name    |
      | UNKNOWN |

  Scenario: Execute flow function function
    Given I start a valid spring application context
    When Execute functionFlow with
      | reverse |
      | test    |
    Then The flow result for "functionFlow" is
      | esrever |
      | tset    |
  Scenario: Execute flow supplier supplier function
    Given I start a valid spring application context
    When Execute supplierFlow
    Then The flow result for "supplierFlow" is
      | supplierFlow |

  Scenario: Execute flow consumer function
    Given I start a valid spring application context
    When Execute consumerFlow with
      | reverse |
    Then The result for "consumerSingle" is "reverse"