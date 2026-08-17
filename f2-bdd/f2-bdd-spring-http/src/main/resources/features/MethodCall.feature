Feature:
  Background:
    Given I start a valid spring application context

  Scenario: MethodCall: Catalog contains f2 function
    Then MethodCall: Function catalog contains
      | functionMethodCall |
      | supplierMethodCall |
      | consumerMethodCall |

  Scenario: MethodCall: Execute function from the catalog
    When MethodCall: Execute function "functionMethodCall" with
      | reverse |
    Then MethodCall: The result for "functionMethodCall" is
      | esrever |

  Scenario: MethodCall: Execute supplier from the catalog
    When MethodCall: Execute supplier "supplierMethodCall"
    Then MethodCall: The result for "supplierMethodCall" is
      | supplierValuePureKotlinValue |

  Scenario: MethodCall: Execute consumer from the catalog
    When MethodCall: Execute consumer "consumerMethodCall" with
      | alpine |

    Then MethodCall: The result for "consumerMethodCall" is
      | alpine |
