package com.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/*****
 * Page for interacting with the Shopping Cart Functionality
 *****/
public class ShoppingCartPage extends BasePage {

    WebDriverWait wait = new WebDriverWait(driver, 3);
    private final String ORDER_TOTAL = "grand totals";
    JavascriptExecutor js = (JavascriptExecutor) driver;

    /**
     * Method to select country details by clicking the estimate shipping and tax section on the Shopping Cart Page
     *
     * @param rawTable the raw table
     *                 Set field given a list of{Input,Label,Value}
     *                 E.x -  | dropdown | shippingAddress.country_id | GB    |
     */
    public void setField(List<List<String>> rawTable) throws InterruptedException {
        driver.findElement(By.cssSelector("[id='block-shipping']")).click();

        for (List<String> row : rawTable) {
            String input = row.get(0);
            String label = row.get(1);
            String value = row.get(2);
            switch (input) {
                case "textarea":
                    wait.until(ExpectedConditions.visibilityOfElementLocated(callSelector(locator.getProperty("name").replace("{0}", label))));
                    Thread.sleep(3000);
                    js.executeScript("document.querySelector('[name=\"" + label + "\"]>div>input').value=\"" + value + "\";");
                    break;
                case "dropdown":
                    wait.until(ExpectedConditions.visibilityOfElementLocated(callSelector(locator.getProperty("name").replace("{0}", label))));
                    WebElement drop = executeFindElement((locator.getProperty("name").replace("{0}", label)) + ">div>select");
                    Select data = new Select(drop);
                    data.selectByValue(value);
                    break;
                default:
                    System.out.print(input + "is not identified ");
            }
        }
    }

    /**
     * user verifies the price details on on the Shopping Cart Page
     * Set field given a list of{Input , Value}
     * E.x -     | totals sub           | $96.00  |
     *
     * @param rawTable the raw table
     */
    public void verifyTotalPrices(List<List<String>> rawTable) {
        for (List<String> row : rawTable) {
            String label = row.get(0);
            String value = row.get(1);
            if (ORDER_TOTAL.equals(label)) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(callSelector(locator.getProperty("summaryText").replace("{0}", label))));
                String currentValue = executeFindElement((locator.getProperty("summaryText").replace("{0}", label)) + ">td>strong>span").getText();
                Assert.assertEquals(String.format("Expected Price:  %s, Actual Price : %s", value, currentValue), value, currentValue);
            } else {
                try {
                    wait.until(ExpectedConditions.visibilityOfElementLocated(callSelector(locator.getProperty("summaryText").replace("{0}", label))));
                    String currentSubValue = executeFindElement((locator.getProperty("summaryText").replace("{0}", label)) + ">td > span").getText();
                    Assert.assertEquals(String.format("Expected " + label + " Price:  %s, Actual Price : %s", value, currentSubValue), value, currentSubValue);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Method to verify the expected items count is displayed or not on the Show Cart
     *
     * @param count - The total count of items display
     * @param value - expected count displayed or not
     *              true or false
     */
    public void verifyCountOnActionShowCart(int count, boolean value) {
        WebElement element = executeFindElement(locator.getProperty("itemCountOnShowCart"));
        boolean countPlace = element.isDisplayed();
        int actualCount = Integer.parseInt(element.getText());
        if (countPlace) {
            Assert.assertEquals("Expected qty counts on ShowCar:", count == actualCount, value);
        } else {
            Assert.assertNotSame("Actual and expected qty count not same in the ShowCar:", count == actualCount, value);
        }
    }

    /**
     * Method to verify the selected items are displayed or not in the Shopping Cart Page
     *
     * @param rawTable the raw table
     *                 Set field given a list of{order, itemName,Size,Color, Quantity}
     *                 E.x -   | 1 | Gwyn Endurance Tee   | M | Green  | 3 |
     */
    public void verifyShoppingCartTable(List<List<String>> rawTable) {
        for (List<String> row : rawTable) {
            String order = row.get(0);
            String itemName = row.get(1);
            String size = row.get(2);
            String color = row.get(3);
            int i = Integer.parseInt(order);
            i = i + 2;


//                        Verify the item Name
            String actualName = executeFindElement((locator.getProperty("itemTable").replace("{0}", String.valueOf(i))) + (locator.getProperty("itemName"))).getText();
            Assert.assertEquals("Expected item Name:", itemName, actualName);

            if (!((size.isEmpty()) && (color.isEmpty()))) {
                //            Verify the Size
                String actualSize = executeFindElement((locator.getProperty("itemTable").replace("{0}", String.valueOf(i))) + (locator.getProperty("itemSizeAndColor").replace("{0}", "2"))).getText();
                Assert.assertEquals("Expected size of selected item:", size, actualSize);


                //            Verify the color
                String actualColor = executeFindElement((locator.getProperty("itemTable").replace("{0}", String.valueOf(i))) + (locator.getProperty("itemSizeAndColor").replace("{0}", "4"))).getText();
                Assert.assertEquals("Expected color of selected item:", color, actualColor);
            }
        }
        closeBrowser();
    }
}