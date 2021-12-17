Feature:

  Scenario: Inject list function
    When I build a valid spring application context
    Then Instance is an injectable bean
      | name            |
      | functionCatalog |
      | functionList    |
      | supplierList    |
      | consumerList    |

    Then Instance is not injectable bean
      | name    |
      | UNKNOWN |

  Scenario: Execute FunctionList function
    Given I start a valid spring application context
    When Execute functionList with
      | reverse |
      | test    |
    Then The list result for "functionList" is
      | esrever |
      | tset    |

  Scenario: Execute SupplierList supplier function
    Given I start a valid spring application context
    When Execute supplierList
    Then The list result for "supplierList" is
      | supplierValuePureKotlinValue |

  Scenario: Execute Single consumer function
    Given I start a valid spring application context
    When Execute consumerList with
      | reverse |
    Then The result for "consumerSingle" is "reverse"