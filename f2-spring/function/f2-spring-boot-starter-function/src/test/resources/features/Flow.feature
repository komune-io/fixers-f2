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

  Scenario: Catalog contains function
    When I start a valid spring application context
    Then Function catalog contains
      | functionFlow |
      | supplierFlow |
      | consumerFlow |

  Scenario: Execute flow function function
    Given I start a valid spring application context
    When Execute function functionFlow with
      | reverse |
      | test    |
    Then The result for "functionFlow" is
      | esrever |
      | tset    |

  Scenario: Execute flow supplier supplier function
    Given I start a valid spring application context
    When Execute supplier supplierFlow
    Then The result for "supplierFlow" is
      | supplierFlow |

  Scenario: Execute flow consumer function
    Given I start a valid spring application context
    When Execute consumer consumerFlow with
      | reverse |
    Then The result for "consumerFlow" is
      | reverse |