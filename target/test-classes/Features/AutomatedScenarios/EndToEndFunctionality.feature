Feature: Add to card Functionality of Magento Website

  Background:
    Given the user navigates to "https://magento.softwaretestingboard.com"
    And the user will wait to see the Default welcome msg header

  @TestCase:001
  Scenario: Verify total price list in the Summary section by adding items and updating quantity as necessary by guest users
#    Clicks the search icon after entered text
    When the user enter 'Gwyn Endurance Tee' text on search box and clickSearchIcon
#    Select an item by the item name link
    And the user selects a item 'Gwyn Endurance Tee' to view in a page
#    Verifies the page
    Then the user will be on the 'Gwyn Endurance Tee' Page:true
#    select color and size
    And the user selects the color 'Green' and size 'M'
#    Enter quantity
    * the user enter qty '4' on the Page
#    Clicks Add to cart button
    * the user clicks 'Add to Cart' on the Item Page
#    Verifies the expected message
    Then the user waits for expected success message appear 'You added Gwyn Endurance Tee to your shopping cart.' on the Item Page:true
#    Verifies the page by clicking the shopping cart link
    And the user will be on the Shopping Cart Page by clicking the Shopping Cart link on the Item Page:true
#    Verifies total qty display on show cart
    * the user verifies the expected qty counts '4' display on action show cart:true
#    Add shipping details by clicking the section
    When the user clicks Estimate Shipping and Tax section and sets the fields on the section:
      | dropdown | shippingAddress.country_id | GB    |
      | textarea | shippingAddress.region     | Essex |
      | textarea | shippingAddress.postcode   | CO15  |
#    Added step to verify the total amount details on Summary section
    Then the user verifies the following total details on Summary:
      | totals sub           | $96.00  |
      | totals               | -$24.00 |
      | totals shipping excl | $20.00  |
      | grand totals         | $92.00  |
#    Amend the quantity on Shopping Cart Page
    When the user update qty '3' on the Page
#    Verifies total qty display on show cart
    Then the user verifies the expected qty counts '3' display on action show cart:true
#    Select an item from the dynamic dropdown list after entered text
    When the user enter 'Gwyn Endurance Tee Small Yellow' text on search box and selectItemFromDropdown
#    Select an item by the item name link
    And the user selects a item 'Gwyn Endurance Tee' to view in a page
#    Verifies the page
    * the user will be on the 'Gwyn Endurance Tee' Page:true
#    select color and size
    * the user selects the color 'Yellow' and size 'S'
#    Enter quantity
    * the user enter qty '1' on the Page
#    Clicks Add to cart button
    * the user clicks 'Add to Cart' on the Item Page
#    Verifies the expected message
    Then the user waits for expected success message appear 'You added Gwyn Endurance Tee to your shopping cart.' on the Item Page:true
#    Clicks the search icon after entered text
    When the user enter 'Quest Lumaflex™ Band' text on search box and clickSearchIcon
#    Clicks Add To Cart by hover over on the item
    And the user hoverOver a item 'quest lumaflex trade band' and click add To item on Search page
#    Verifies the expected message
    Then the user waits for expected success message appear 'You added Quest Lumaflex™ Band to your shopping cart.' on the Item Page:true
#    Verifies the page by clicking the shopping cart link
    And the user will be on the Shopping Cart Page by clicking the Shopping Cart link on the Item Page:true
#    Verifies total qty display on show cart
    * the user verifies the expected qty counts '5' display on action show cart:true
#    Verifies added item details on Shopping Cart page
    * the user verifies the selected items details are displayed correctly on Shopping Cart table:
      | 1 | Gwyn Endurance Tee   | M | Green  |
      | 2 | Gwyn Endurance Tee   | S | Yellow |
      | 3 | Quest Lumaflex™ Band |   |        |