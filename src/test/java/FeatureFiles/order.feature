# new feature
# Tags: optional

Feature: User is making order of jacket
  Background:
    Given User is logged in

  Scenario: Making a order
      When the user searches for a product by name
      And selects the desired product variant
      And adds the product to the cart
      And proceeds to checkout
      And provides shipping details
      And confirms the order
      Then the order is placed successfully and order id is generated


