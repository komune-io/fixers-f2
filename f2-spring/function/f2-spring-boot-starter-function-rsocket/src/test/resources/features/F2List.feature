Feature:

  Background:
    Given I start a valid spring application context

  Scenario: Catalog list contains f2 function
    Then List: Function catalog contains
      | functionList |
      | supplierList |
      | consumerList |

  Scenario: Execute list function from the catalog
    When List: Execute function "functionList" with
      | reverse,test |
    Then List: The result for "functionList" is
      | esrever,tset |

  Scenario: Execute list supplier from the catalog
    When List: Execute supplier "supplierList"
    Then List: The result for "supplierList" is
      | supplierValuePureKotlinValue |

  Scenario: Execute list consumer from the catalog
    When List: Execute consumer "consumerList" with
      | alpine,tesla |

    Then List: The result for "consumerList" is
      | alpine,tesla |

  Scenario: Execute vehicle consumer from the catalog
    When Vehicle: Execute consumer "consumerVehicle" with
      | alpine,tesla |

    When Vehicle: Execute consumer "consumerVehicle" with
      | renault,citroen  |

    Then Vehicle: The result for "consumerVehicle" is
      | alpine,tesla,renault,citroen |
