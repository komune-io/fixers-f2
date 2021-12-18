Feature:
  Background:
    Given I start a valid spring application context
  Scenario: Catalog contains function
    Then Function catalog contains
      | functionF2 |
      | supplierF2 |
      | consumerF2 |

  Scenario: Execute f2 function from the catalog
    When Function catalog execute "functionF2" with
      | reverse |
      | test    |
    Then The function catalog result for "functionF2" is
      | esrever |
      | tset    |

  Scenario: Execute f2 supplier from the catalog
    When Function catalog execute "supplierF2"
    Then The function catalog result for "supplierF2" is
      | supplierValuePureKotlinValue |

  Scenario: Execute f2 consumer from the catalog
    When Function catalog execute "consumerF2" with
      | reverse |
      | test    |
    Then The function catalog result for "consumerF2" is
      | reverse |
      | test    |

  Scenario: Execute f2 pipeline from the catalog
    When Function catalog execute "supplierF2|functionF2|consumerF2"
    Then The function catalog result for "supplierF2|functionF2|consumerF2" is
      | eulaVniltoKeruPeulaVreilppus |

  Scenario: Execute f2 pipeline from the catalog
    When Function catalog execute "functionF2|consumerF2" with
      | reverse |
    Then The function catalog result for "functionF2|consumerF2" is
      | esrever |
