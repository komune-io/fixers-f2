#Feature:
#
#  Scenario: Catalog contains function
#    When I start a valid spring application context
#    Then Function catalog contains
#      | functionFlow |
#      | supplierFlow |
#      | consumerFlow |
#
#  Scenario: Execute flow function function
#    Given I start a valid spring application context
#    When Execute function functionFlow with
#      | reverse |
#      | test    |
#    Then The result for "functionFlow" is
#      | esrever |
#      | tset    |
#
#  Scenario: Execute flow supplier supplier function
#    Given I start a valid spring application context
#    When Execute supplier supplierFlow
#    Then The result for "supplierFlow" is
#      | supplierFlow |
#
#  Scenario: Execute flow consumer function
#    Given I start a valid spring application context
#    When Execute consumer consumerFlow with
#      | reverse |
#    Then The result for "consumerFlow" is
#      | reverse |