Feature:
  Background:
    Given I start a valid spring application context

  Scenario: Catalog flow contains f2 function
    Then Flow: Function catalog contains
      | functionFlow |
      | supplierFlow |
      | consumerFlow |

  Scenario: Execute flow function from the catalog
    When Flow: Execute function "functionFlow" with
      | reverse |
    Then Flow: The result for "functionFlow" is
      | esrever |

  Scenario: Execute flow supplier from the catalog
    When Flow: Execute supplier "supplierFlow"
    Then Flow: The result for "supplierFlow" is
      | supplierFlow |

  Scenario: Execute flow consumer from the catalog
    When Flow: Execute consumer "consumerFlow" with
      | alpine |

    Then Flow: The result for "consumerFlow" is
      | alpine |

  Scenario: Execute flow consumer from the catalog
    When Flow: Execute consumer "consumerFlow" with
      | alpine |

    When Flow: Execute consumer "consumerFlow" with
      | renault  |

    Then Flow: The result for "consumerFlow" is
      | alpine  |
      | renault |
