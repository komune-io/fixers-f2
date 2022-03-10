Feature:
  Background:
    Given I start a valid spring application context

  Scenario: Catalog contains f2 function
    Then Vehicle: Function catalog contains
      | fixVehicle      |
      | supplierVehicle |
      | consumerVehicle |

  Scenario: Execute vehicle function from the catalog
    When Vehicle: Function catalog execute "fixVehicle" with
      | name   | broken |
      | alpine | false  |
      | tesla  | true   |
    Then Vehicle: The function catalog result for "fixVehicle" is
      | name   | broken |
      | alpine | false  |
      | tesla  | false  |

  Scenario: Execute vehicle supplier from the catalog
    When Vehicle: Function catalog execute "supplierVehicle"
    Then Vehicle: The function catalog result for "supplierVehicle" is
      | name | broken |
      | Car  | false  |
      | Moto | true   |
      | Bike | true   |

  Scenario: Execute vehicle consumer from the catalog
    When Vehicle: Function catalog execute "consumerVehicle" with
      | name   | broken |
      | alpine | false  |
      | tesla  | true   |

    Then Vehicle: The function catalog result for "consumerVehicle" is
      | name   | broken |
      | alpine | false  |
      | tesla  | true      |

#  Scenario: Execute f2 pipeline from the catalog
#    When Vehicle: Function catalog execute "supplierVehicle|fixVehicle|consumerVehicle"
#    Then Vehicle: The function catalog result for "supplierVehicle|fixVehicle|consumerVehicle" is
#      | name | broken |
#      | Car  | false  |
#      | Moto | false  |
#      | Bike | false  |
#
#
#  Scenario: Execute f2 pipeline from the catalog
#    When Vehicle: Function catalog execute "fixVehicle|consumerVehicle" with
#      | name   | broken |
#      | alpine | false  |
#      | tesla  | true   |
#
#    Then Vehicle: The function catalog result for "fixVehicle|consumerVehicle" is
#      | name   | broken |
#      | alpine | false  |
#      | tesla  | false  |
