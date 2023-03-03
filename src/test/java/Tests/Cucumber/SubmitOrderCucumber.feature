Feature: Purchase the order from ecommerce Website

  //This executes before evey scenario
  Background:
  Given I landed on ecommerce Page

    @Regression
  Scenario Outline: Positive Test of Submitting the order
    Given I log in with username <name> and password <password>
    When I add the product <productName> to the Cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage
    Examples:
      | name                        | password  | productName
      | izaecabs@gmail.com          | Summer01  | ZARA COAT 3
#      | iza.ecabs@gmail.com          | Summer01  | ADIDAS ORIGINAL
