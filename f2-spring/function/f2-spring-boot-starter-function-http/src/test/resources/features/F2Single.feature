Feature:
  Background:
    Given I start a valid spring application context

  Scenario: Catalog single contains f2 function
    Then Single: Function catalog contains
      | functionSingle |
      | supplierSingle |
      | consumerSingle |

  Scenario: Execute single function from the catalog
    When Single: Execute function "functionSingle" with
      | reverse |
    Then Single: The result for "functionSingle" is
      | esrever |

#  Scenario: Execute single supplier from the catalog
#    When Single: Execute supplier "supplierSingle"
#    Then Single: The result for "supplierSingle" is
#      | supplierValuePureKotlinValue |

  Scenario: Execute single consumer from the catalog
    When Single: Execute consumer "consumerSingle" with
      | alpine |

    Then Single: The result for "consumerSingle" is
      | alpine |

#  Scenario: Execute single consumer from the catalog
#    When Single: Execute consumer "consumerSingle" with
#      | alpine |
#
#    When Single: Execute consumer "consumerSingle" with
#      | renault  |
#
#    Then Single: The result for "consumerSingle" is
#      | alpine, renault |
