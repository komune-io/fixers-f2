#Feature:
#
#  Scenario: Catalog contains function
#    When I start a valid spring application context
#    Then Function catalog contains
#      | functionList |
#      | supplierList |
#      | consumerList |
#
#  Scenario: Execute FunctionList function
#    Given I start a valid spring application context
#    When Execute function functionList with
#      | reverse |
#      | test    |
#    Then The result for "functionList" is
#      | esrever |
#      | tset    |
#
#  Scenario: Execute SupplierList supplier function
#    Given I start a valid spring application context
#    When Execute supplier supplierList
#    Then The result for "supplierList" is
#      | supplierValuePureKotlinValue |
#
#  Scenario: Execute Single consumer function
#    Given I start a valid spring application context
#    When Execute consumer consumerList with
#      | reverse |
#    Then The result for "consumerList" is
#      | reverse |