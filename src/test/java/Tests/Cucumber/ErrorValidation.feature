Feature: Error Validation

  @ErrorValidation
  Scenario Outline:
    Given I landed on ecommerce Page
    When I log in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed
    Examples:
      | name               | password
      | izaecabs@gmail.com | Summer0111