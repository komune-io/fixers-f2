Feature:
  Background:
    Given I start a valid spring application context
  Scenario: Catalog contains function
    Then Function catalog contains
      | functionBasic |
      | supplierBasic |
      | consumerBasic |

  Scenario: Execute LambdaF2 function from the catalog
    When Function catalog execute "functionBasic" with
      | reverse |
      | test    |
    Then The function catalog result for "functionBasic" is
      | esrever |
      | tset    |

  Scenario: Execute LambdaF2 supplier from the catalog
    When Function catalog execute "supplierBasic"
    Then The function catalog result for "supplierBasic" is
      | supplierValuePureKotlinValue |

  Scenario: Execute LambdaF2 consumer from the catalog
    When Function catalog execute "consumerBasic" with
      | reverse |
      | test    |
    Then The function catalog result for "consumerBasic" is
      | reverse |
      | test    |
