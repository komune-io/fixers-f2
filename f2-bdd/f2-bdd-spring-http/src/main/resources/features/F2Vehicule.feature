Feature:
  Background:
    Given I start a valid spring application context

  Scenario: Catalog contains f2 function
    Then Vehicle: Function catalog contains
      | fixVehicle      |
      | supplierVehicle |
      | consumerVehicle |

  Scenario: Execute vehicle function from the catalog
    When Vehicle: Execute function "fixVehicle" with
      | name   | isBroken |
      | alpine | false    |
      | tesla  | true     |
    Then Vehicle: The result for "fixVehicle" is
      | name   | isBroken |
      | alpine | false    |
      | tesla  | false    |

  Scenario: Execute vehicle supplier from the catalog
    When Vehicle: Execute supplier "supplierVehicle"
    Then Vehicle: The result for "supplierVehicle" is
      | name | isBroken |
      | Car  | false    |
      | Moto | true     |
      | Bike | true     |

  Scenario: Execute vehicle consumer from the catalog
    When Vehicle: Execute consumer "consumerVehicle" with
      | name   | isBroken |
      | alpine | false    |
      | tesla  | true     |

    Then Vehicle: The result for "consumerVehicle" is
      | name   | isBroken |
      | alpine | false    |
      | tesla  | true     |
