Feature:

  Scenario: Inject LambdaF2 lambda
    When I build a valid spring application context
    Then Instance is an injectable bean
      | name               |
      | functionCatalog    |
      | functionBasic      |
      | supplierBasic      |
      | consumerBasic      |

    Then Instance is not injectable bean
      | name    |
      | UNKNOWN |

  Scenario: Catalog contains LambdaF2 functionBasic function
    When I start a valid spring application context
    Then Function catalog contains
      | functionBasic |
      | supplierBasic |
      | consumerBasic |

  Scenario: Execute LambdaF2 function function
    Given I start a valid spring application context
    When Execute function functionBasic with
      | reverse |
      | test    |
    Then The result for "functionBasic" is
      | esrever |
      | tset    |

  Scenario: Execute LambdaF2 supplier supplier function
    Given I start a valid spring application context
    When Execute supplier supplierBasic
    Then The result for "supplierBasic" is
      | supplierValuePureKotlinValue |

  Scenario: Execute LambdaF2 consumer function
    Given I start a valid spring application context
    When Execute consumer consumerBasic with
      | reverse |
    Then The result for "consumerBasic" is
      | reverse |