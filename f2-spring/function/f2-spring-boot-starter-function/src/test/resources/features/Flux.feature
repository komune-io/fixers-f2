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

  Scenario: Catalog contains function
    When I start a valid spring application context
    Then Function catalog contains
      | functionFlux    |
      | supplierFlux    |
      | consumerFlux    |

  Scenario: Execute flux function function
    Given I start a valid spring application context
    When Execute functionFlux with
      | reverse |
      | test    |
    Then The flux result for "functionFlux" is
      | esrever |
      | tset    |
  Scenario: Execute flux supplier supplier function
    Given I start a valid spring application context
    When Execute supplierFlux
    Then The flux result for "supplierFlux" is
      | supplierFlux |

  Scenario: Execute flux consumer function
    Given I start a valid spring application context
    When Execute consumerFlux with
      | reverse |
    Then The result for "consumerSingle" is "reverse"