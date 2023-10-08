# new feature
# Tags: optional

Feature: Login to website
  Background: User is at dashboard screen

  Scenario: Validate as a user I am able to login
    Given User is at dashboard screen
    When User navigates to signin screen
    And User enters email and password
    Then User is routed to Dashboard page as signin user