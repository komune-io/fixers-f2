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

  Scenario: Execute vehicle consumer from the catalog
    When Vehicle: Execute consumer "consumerVehicle" with
      | alpine |

    When Vehicle: Execute consumer "consumerVehicle" with
      | renault  |

    Then Vehicle: The result for "consumerVehicle" is
      | alpine,renault |
