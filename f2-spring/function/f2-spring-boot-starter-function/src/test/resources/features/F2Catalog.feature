Feature:
  Scenario: Catalog contains f2 function
    When I start a valid spring application context
    Then Function catalog contains
      | functionF2    |
      | supplierF2    |
      | consumerF2    |

  Scenario: Execute f2 function from the catalog
    Given I start a valid spring application context
    When Function catalog execute function "functionF2" with
      | reverse |
      | test    |
    Then The function catalog result for "functionF2" is
      | esrever |
      | tset    |

  Scenario: Execute f2 supplier from the catalog
    Given I start a valid spring application context
    When Function catalog execute supplier "supplierF2"
    Then The function catalog result for "supplierF2" is
      | supplierValuePureKotlinValue |

  Scenario: Execute f2 consumer from the catalog
    Given I start a valid spring application context
    When Function catalog execute consumer "consumerF2" with
      | reverse |
      | test    |
    Then The function catalog result for "consumerF2" is
      | reverse |
      | test    |
