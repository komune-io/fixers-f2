Feature:

  Scenario: MethodCall: Inject function
    When I build a valid spring application context
    Then Instance is an injectable bean
      | name            |
      | functionCatalog |
      | functionMethodCall    |
      | supplierMethodCall    |
      | consumerMethodCall    |

    Then Instance is not injectable bean
      | name    |
      | UNKNOWN |

  Scenario: MethodCall: Catalog contains function
    When I start a valid spring application context
    Then Function catalog contains
      | functionMethodCall |
      | supplierMethodCall |
      | consumerMethodCall |

  Scenario: MethodCall: Execute function
    Given I start a valid spring application context
    When Execute function functionMethodCall with
      | reverse |
      | test    |
    Then The result for "functionMethodCall" is
      | esrever |
      | tset    |

  Scenario: MethodCall: Execute supplier
    Given I start a valid spring application context
    When Execute supplier supplierMethodCall
    Then The result for "supplierMethodCall" is
      | supplierValuePureKotlinValue |

  Scenario: MethodCall: Execute consumer
    Given I start a valid spring application context
    When Execute consumer consumerMethodCall with
      | reverse |
    Then The result for "consumerMethodCall" is
      | reverse |