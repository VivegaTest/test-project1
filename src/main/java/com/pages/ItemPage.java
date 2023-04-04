package com.pages;

import org.apache.commons.lang3.Range;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*****
 * Methods for interacting with the Item Page
 *****/
public class ItemPage extends BasePage {

    private int minValue = 1;
    private int maxValue = 10000;
    WebDriverWait wait = new WebDriverWait(driver, 3);
    private final HomePage homePage = new HomePage();


    public ItemPage() {
    }

    /**
     * Method to select color and size of the selected item on the specific Item page
     *
     * @param color - Color of the item
     * @param size  - Size of the item
     */
    public void selectSizeAndColor(String color, String size) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".swatch-option")));
        selectSize(size);
        selectColor(color);

    }

    /**
     * Method to select  size of the selected item on the specific Item page
     *
     * @param size - Size of the item
     */
    public String selectSize(String size) {
        String selectedSize = executeFindElement(locator.getProperty("selectedSizeColorValue").replace("{0}", "size")).getText();
        if (!selectedSize.equalsIgnoreCase(size)) {
            executeFindElement(locator.getProperty("selectSizeColor").replace("{0}", size)).click();
            wait.until(ExpectedConditions.visibilityOf(executeFindElement(locator.getProperty("selectedSizeColorValue").replace("{0}", "size"))));
        }
        Assert.assertEquals(String.format("Expected size:  %s, Actual size : %s", size, (executeFindElement(locator.getProperty("selectedSizeColorValue").replace("{0}", "size")).getText())), size, (executeFindElement(locator.getProperty("selectedSizeColorValue").replace("{0}", "size"))).getText());
        return size;
    }

    /**
     * Method to select color of the selected item on the specific Item page
     *
     * @param color - Color of the item
     */
    public String selectColor(String color) {
        String selectedColor = executeFindElement(locator.getProperty("selectedSizeColorValue").replace("{0}", "color")).getText();
        if (!selectedColor.equalsIgnoreCase(color)) {
            executeFindElement(locator.getProperty("selectSizeColor").replace("{0}", color)).click();
            wait.until(ExpectedConditions.visibilityOf(executeFindElement(locator.getProperty("selectedSizeColorValue").replace("{0}", "color"))));
        }
        Assert.assertEquals(String.format("Expected color:  %s, Actual color : %s", color, (executeFindElement(locator.getProperty("selectedSizeColorValue").replace("{0}", "color")).getText())), color, (executeFindElement(locator.getProperty("selectedSizeColorValue").replace("{0}", "color")).getText()));
        return color;
    }

    /**
     * Method to enter or amend the quantity of the selected item on the specific Item page
     *
     * @param action - Add quantity or Amend the quantity
     * @param qty    - Quantity of selected item
     */
    public void actionQty(String action, int qty) {
        WebElement element = executeFindElement(locator.getProperty("quantity"));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        isInRange(qty, minValue, maxValue);
        element.clear();
        element.sendKeys(String.valueOf(qty));
        if (action.equalsIgnoreCase("update")) {
            executeFindElement(locator.getProperty("shoppingCart")).click();
            executeFindElement(locator.getProperty("quantity") + locator.getProperty("EnterValue").replace("{0}", String.valueOf(qty)));
        }
    }

    /**
     * Method to verify the qty is in between the range
     *
     * @param number     - Quantity to add
     *                   Boundary value
     * @param lowerBound - Min Quantity value
     * @param upperBound - Max Quantity value
     */
    public boolean isInRange(Integer number, Integer lowerBound, Integer upperBound) {
        try {
            final Range<Integer> range = Range.between(lowerBound, upperBound);
            return range.contains(number);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Range is out of bound :" + executeFindElement(locator.getProperty("errorMsgQty")).getText());
            return false;
        }
    }

    /**
     * Method to click Add to Cart of the selected item on the specific Item page
     */
    public void clickAddToCard() {
        executeFindElement(locator.getProperty("addToCart")).click();
    }

    /**
     * Method to verify the expected success message after added item into the add to cart on the specific Item page
     *
     * @param expectedMessage - After added item into add to cart
     * @param value           - expected message displayed or not
     *                        true or false
     */
    public void verifySuccessMessage(String expectedMessage, boolean value) {
        WebElement element = executeFindElement(locator.getProperty("banner"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        String message = element.getText();
        if (expectedMessage.equals(message)) {
            try {
                Assert.assertEquals(String.format("Expected message:  %s, Actual message : %s", expectedMessage, message), expectedMessage.equalsIgnoreCase(message), value);
            } catch (AssertionError e) {
                Assert.fail(String.format("Actual message : %s", message));
            }
        }
    }

    /**
     * Method to verify whether user will be on the shopping cart page by clicking the shopping cart link on the success message
     *
     * @param value - User is on the shopping cart page or not
     *              true or false
     */
    public ShoppingCartPage navigateToShoppingCartByClickingLink(boolean value) {
        wait.until(ExpectedConditions.elementToBeClickable(callSelector(locator.getProperty("shoppingCartLink")))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(callSelector(locator.getProperty("cartSummary"))));
        homePage.waitForPageLoad("Shopping Cart", value);
        return new ShoppingCartPage();
    }
}