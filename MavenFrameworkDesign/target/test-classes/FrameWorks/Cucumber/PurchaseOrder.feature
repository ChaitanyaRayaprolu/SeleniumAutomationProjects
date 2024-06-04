
@tag
Feature: Purchase an order from Ecommerce website
  I want to use this template for my feature file

	Background:
	Given I landed on Ecommerce Page

 
  @tag1
  Scenario Outline: Positive Test for order submission
    Given Logged in with username <name> and password <password> 
    When I add product <productName> into cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | name  										 | password     | productName |
      | rahulshettytest1@gmail.com | Thisistest@1 | ZARA COAT 3 |
  
