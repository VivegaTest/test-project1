package com.steps;

import com.pages.ShoppingCartPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

import java.util.List;

/*****
 * Steps for interacting with the Shopping Cart Functionality
 *****/
public class ShoppingCartSteps {

    private final ShoppingCartPage shoppingCartPage;

    public ShoppingCartSteps(ShoppingCartPage shoppingCartPage) {
        this.shoppingCartPage = shoppingCartPage;
    }

    /**
     * Method to select country details by clicking the estimate shipping and tax section on the Shopping Cart Page
     *
     * @param rawTable the raw table
     *                 Set field given a list of{Input,Label,Value}
     *                 E.x -  | dropdown | shippingAddress.country_id | GB    |
     */
    @And("^the user clicks Estimate Shipping and Tax section and sets the fields on the section:$")
    public void setFieldsOnPage(List<List<String>> rawTable) throws InterruptedException {
        shoppingCartPage.setField(rawTable);
    }

    /**
     * user verifies the price details on on the Shopping Cart Page
     * Set field given a list of{Input , Value}
     * E.x -     | totals sub           | $96.00  |
     *
     * @param rawTable the raw table
     */
    @And("^the user verifies the following total details on Summary:$")
    public void verifyTotalPrices(List<List<String>> rawTable) {
        shoppingCartPage.verifyTotalPrices(rawTable);
    }

    /**
     * Method to verify the expected items count is displayed or not on the Show Cart
     *
     * @param count - The total count of items display
     * @param value - expected count displayed or not
     *              true or false
     */
    @Then("the user verifies the expected qty counts '([\\d]+)' display on action show cart:(true|false)$")
    public void verifyCountOnActionShowCart(int count, boolean value) {
        shoppingCartPage.verifyCountOnActionShowCart(count, value);
    }

    /**
     * Method to verify the selected items are displayed or not in the Shopping Cart Page
     *
     * @param rawTable the raw table
     *                 Set field given a list of{order, itemName,Size,Color}
     *                 E.x -   | 1 | Gwyn Endurance Tee   | M | Green  |
     */
    @Then("the user verifies the selected items details are displayed correctly on Shopping Cart table:$")
    public void verifyShoppingCartTable(List<List<String>> rawTable) {
        shoppingCartPage.verifyShoppingCartTable(rawTable);
    }
}