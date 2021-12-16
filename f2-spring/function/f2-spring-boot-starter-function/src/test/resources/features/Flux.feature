Feature:

  Scenario: Inject Flux function
    When I build a valid spring application context
    Then Instance is an injectable bean
      | name            |
      | functionCatalog |
      | functionFlux    |
      | supplierFlux    |
      | consumerFlux    |

    Then Instance is not injectable bean
      | name    |
      | UNKNOWN |

  Scenario: Execute FunctionFlux function
    Given I build a valid spring application context
    When Execute functionFlux with
      | reverse |
      | test    |
    Then The flux result for "functionFlux" is
      | esrever |
      | tset    |
  Scenario: Execute SupplierFlux supplier function
    Given I build a valid spring application context
    When Execute supplierFlux
    Then The flux result for "supplierFlux" is
      | supplierFlux |
