Feature:

  Scenario: Inject FunctionCatalog function
    Given The application parameters
      | ssm.chaincode.url     | http://localhost:9090  |
      | ssm.signer.admin.name | ssm-admin              |
      | ssm.signer.admin.key  | local/admin/ssm-admin |
    When I build a valid spring application context
    Then Instance is an injectable bean
      | FunctionCatalog |
    Then Instance is not injectable bean
      | UNKNOWN      |
