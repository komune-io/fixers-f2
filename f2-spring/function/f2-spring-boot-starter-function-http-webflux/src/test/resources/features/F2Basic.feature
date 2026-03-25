Feature:
  Background:
    Given I start a valid spring application context

  Scenario: Catalog basic contains f2 function
    Then Basic: Function catalog contains
      | functionBasic |
      | supplierBasic |
      | consumerBasic |

  Scenario: Execute basic function from the catalog
    When Basic: Execute function "functionBasic" with
      | reverse |
    Then Basic: The result for "functionBasic" is
      | esrever |

  Scenario: Execute basic supplier from the catalog
    When Basic: Execute supplier "supplierBasic"
    Then Basic: The result for "supplierBasic" is
      | supplierValuePureKotlinValue |

  Scenario: Execute basic consumer from the catalog
    When Basic: Execute consumer "consumerBasic" with
      | alpine |

    Then Basic: The result for "consumerBasic" is
      | alpine |

#  Scenario: Execute basic consumer from the catalog
#    When Basic: Execute consumer "consumerBasic" with
#      | alpine |
#
#    When Basic: Execute consumer "consumerBasic" with
#      | renault  |
#
#    Then Basic: The result for "consumerBasic" is
#      | alpine, renault |
