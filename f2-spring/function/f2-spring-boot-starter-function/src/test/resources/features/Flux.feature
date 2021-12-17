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
      | functionFlux |
      | supplierFlux |
      | consumerFlux |

  Scenario: Execute flux function function
    Given I start a valid spring application context
    When Execute function functionFlux with
      | reverse |
      | test    |
    Then The result for "functionFlux" is
      | esrever |
      | tset    |

  Scenario: Execute flux supplier function
    Given I start a valid spring application context
    When Execute supplier supplierFlux
    Then The result for "supplierFlux" is
      | supplierFlux |

  Scenario: Execute flux consumer function
    Given I start a valid spring application context
    When Execute consumer consumerFlux with
      | reverse |
    Then The result for "consumerFlux" is
      | reverse |