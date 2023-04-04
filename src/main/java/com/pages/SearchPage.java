package com.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/*****
 * Methods for interacting with the Search box functionality page
 *****/
public class SearchPage extends BasePage {
    Actions action = new Actions(driver);

    public SearchPage() {
        super();
    }

    /**
     * Method to select a item by hyperlink
     *
     * @param itemName - The item name to enter in the href link
     */
    public ItemPage selectItemByLink(String itemName) {
        String url = prop.getProperty("URL");
        executeFindElement(locator.getProperty("itemLink").replace("{0}",url).replace("{1}",itemName).replaceAll(" ", "-").toLowerCase().trim()).click();
        return new ItemPage();
    }

    /**
     * Method to click add to cart button on a specific item by hover over
     *
     * @param itemName - The item name to enter in the href link
     */
    public ItemPage selectItemByHoverOver(String itemName) throws InterruptedException {
        String url = prop.getProperty("URL");
        WebElement element= executeFindElement(locator.getProperty("itemLink").replace("{0}",url).replace("{1}",itemName).replaceAll(" ", "-").toLowerCase().trim());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        action.moveToElement(element).perform();
        WebElement clickAction = executeFindElement(locator.getProperty("itemList")+":nth-child(1)>div>div>div:last-of-type>div>div:first-of-type>form>button"+locator.getProperty("addToCart"));
        Thread.sleep(500);
        clickAction.click();
        return new ItemPage();
    }
}