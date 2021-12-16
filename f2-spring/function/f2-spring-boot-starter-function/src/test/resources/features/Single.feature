Feature:

  Scenario: Inject Single function
    When I build a valid spring application context
    Then Instance is an injectable bean
      | name                     |
      | functionCatalog          |
      | lambdaSingleReceiver |
      | functionSingle           |
      | supplierSingle           |
      | consumerSingle           |

    Then Instance is not injectable bean
      | name    |
      | UNKNOWN |

  Scenario: Execute LambdaSingle function
    Given I build a valid spring application context
    When Execute functionSingle with "reverse"
    Then The result for "functionSingle" is "esrever"

  Scenario: Execute LambdaSingle supplier function
    Given I build a valid spring application context
    When Execute supplierSingle
    Then The result for "supplierSingle" is "supplierValuePureKotlinValue"
