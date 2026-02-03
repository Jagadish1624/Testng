Feature: Amazon Basic Functionalities

  Scenario: Verify Amazon home page title
    Given user launches Amazon website
    Then page title should contain "Amazon"

  Scenario: Search for a product
    Given user launches Amazon website
    When user searches for "Laptop"
    Then search results should be displayed

  Scenario: Apply category filter
    Given user launches Amazon website
    When user searches for "Mobile Phones"
    And user applies brand filter
    Then filtered results should be displayed

  Scenario: Open product details page
    Given user launches Amazon website
    When user searches for "Headphones"
    And user opens first product
    Then product title and price should be displayed

  Scenario: Add product to cart without login
    Given user launches Amazon website
    When user searches for "USB Cable"
    And user opens first product
    And user adds product to cart
    Then cart should be updated
