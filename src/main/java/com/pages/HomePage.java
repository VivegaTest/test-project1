package com.pages;

import com.Selectors.CommonSelectors;
import com.functions.ButtonAction;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*****
 * Page for interacting with the Home Page
 *****/

public class HomePage extends BasePage {

    private ButtonAction searchField = new ButtonAction(CommonSelectors.SEARCH_BAR);

    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    /**
     * Navigates to the application using this url
     */
    public void navigateToApp() {
        initialization();
    }

    /**
     * Method to perform search action by clicking search icon or select value from the view listed
     *
     * @param itemName - The text for enter in the search box
     * @param action   -based on the requirement it performs the order i,e selectItemFromDropdown or clickSearchIcon
     */
    public SearchPage searchRelatedItem(String action, String itemName) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        searchField.isEnabled();
        WebElement search_Field = executeFindElement(locator.getProperty("search"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(callSelector(locator.getProperty("searchBox"))));
        search_Field.click();
        search_Field.clear();
        search_Field.sendKeys(itemName);
        Thread.sleep(3000);
        if (!action.equalsIgnoreCase("selectItemFromDropdown")) {
            wait.until(ExpectedConditions.elementToBeClickable(callSelector(locator.getProperty("searchIcon")))).click();
        } else if (action.equalsIgnoreCase("selectItemFromDropdown")) {
            WebElement element = executeFindElement(locator.getProperty("search"));
            String value = "return document.getElementById('search').value";
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String text = (String) js.executeScript(value);
            while (!(text.equals(itemName))) {
                element.sendKeys(Keys.DOWN);
                Thread.sleep(2000);
                text = (String) js.executeScript(value);

            }
            element.sendKeys(Keys.ENTER);
            Thread.sleep(2000);
        }
        return new SearchPage();
    }

    /**
     * Method to verify whether the User will be on the right page
     *
     * @param value - true/false
     */
    public void waitForPageLoad(String text, boolean value) {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(callSelector(locator.getProperty("pageTitle"))));
        String headerText = driver.findElement(callSelector(locator.getProperty("pageTitle"))).getText();
        if (headerText.equalsIgnoreCase(text)) {
            Assert.assertEquals("User is on the Shopping Cart Page", text.equalsIgnoreCase(headerText), value);
        } else {
            Assert.fail(String.format("Actual message : %s", headerText));
        }
    }

    /**
     * User will wait for page to load
     */
    public void waitForDefaultWelcomeMsgLoad() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(callSelector(locator.getProperty("defaultWelcomeMsgHeader"))));
        Thread.sleep(10000);
    }
}