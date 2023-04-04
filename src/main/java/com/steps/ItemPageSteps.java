package com.steps;

import com.pages.ItemPage;
import cucumber.api.java.en.And;

/*****
 * Steps for interacting with the selected Item Steps
 *****/
public class ItemPageSteps {

    private final ItemPage itemPage;

    public ItemPageSteps(ItemPage itemPage) {
        this.itemPage = itemPage;
    }

    /**
     * Method to select color and size of the selected item on the specific Item page
     *
     * @param color - Color of the item
     * @param size  - Size of the item
     */
    @And("the user selects the color '([\\w\\s]+)' and size '([\\w\\s]+)'")
    public void selectSizeAndColor(String color, String size) {
        itemPage.selectSizeAndColor(color, size);
    }

    /**
     * Method to enter or amend the quantity of the selected item on the specific Item page
     *
     * @param action - Add quantity or Amend the quantity
     * @param qty    - Quantity of selected item
     */
    @And("^the user (enter|update) qty '([\\d]+)' on the Page$")
    public void actionQty(String action, int qty) {
        itemPage.actionQty(action, qty);
    }

    /**
     * Method to click Add to Cart of the selected item on the specific Item page
     */
    @And("the user clicks 'Add to Cart' on the Item Page")
    public void clickAddToCard() {
        itemPage.clickAddToCard();
    }

    /**
     * Method to verify the expected success message after added item into the add to cart on the specific Item page
     *
     * @param expectedMessage - After added item into add to cart
     * @param value           - expected message displayed or not
     *                        true or false
     */
    @And("the user waits for expected success message appear '([\\w\\s\\S]+)' on the Item Page:(true|false)$")
    public void verifySuccessMessage(String expectedMessage, boolean value) {
        itemPage.verifySuccessMessage(expectedMessage, value);
    }

    /**
     * Method to verify whether user will be on the shopping cart page by clicking the shopping cart link on the success message
     *
     * @param value - User is on the shopping cart page or not
     *              true or false
     */
    @And("the user will be on the Shopping Cart Page by clicking the Shopping Cart link on the Item Page:(true|false)$")
    public void navigateToShoppingCartByClickingLink(boolean value) {
        itemPage.navigateToShoppingCartByClickingLink(value);
    }
}