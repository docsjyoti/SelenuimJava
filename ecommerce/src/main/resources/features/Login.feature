Feature: Amazon Login Using Config Test Data

  Background:Amazon website loaded successfully

  @Smoke
  Scenario Outline: Login using test data from config
    Given User is on the Amazon login page
    When User select login option from landing page
    Then User logs in using "<userKey>"
    And User should be logged in successfully
    Examples:
      | userKey  |
      | user1    |
      | user2    |