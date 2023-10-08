# new feature
# Tags: optional

Feature: User is making order of jacket

  Scenario: Making a order
    Given User is logged in
      And User searches for product "Adrienne Trek Jacket"
      And User selects size "S" , color "Gray" and quantity "1" for searched product
      And User adds selected product to cart
      When User proceeds to checkout
      Then The order is placed successfully and Order id is generated

