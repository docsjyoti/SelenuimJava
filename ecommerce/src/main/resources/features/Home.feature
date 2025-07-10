Feature: Amazon HomePage features

  Background:
    Given User is on the Amazon homepage

  @Smoke @UI
  Scenario: Verify visibility of core elements on homepage
    Then Amazon logo should be visible
    And Search bar should be visible
    And Search icon should be functional
    And Top banner should be visible
    And Menu bar with categories should be visible
    And Headlines or deals section should be visible

  @Smoke @Interaction
  Scenario: Validate navigation to category and back to homepage
    When User clicks on any category in menu bar
    Then Category page should be displayed
    And User navigates back to homepage
    Then Amazon logo should be visible

  @Smoke @Interaction
  Scenario: Perform a search and return to homepage
    When User enters "laptop" in search bar and clicks search icon
    Then Search results should be displayed
    And User clicks Amazon logo to return to homepage
    Then Top banner should be visible
