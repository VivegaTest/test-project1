package com.steps;

import com.pages.HomePage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;


/*****
 * Steps for interacting with the Home Page
 *****/
public class HomeScreenSteps {

    private final HomePage homePage;

    public HomeScreenSteps(HomePage homePage) {
        this.homePage = homePage;
    }

    /**
     * Navigates to the application using this url
     */
    @Given("the user navigates to \"([^\"]*)\"")
    public void navigateToApp(String url) {
        homePage.navigateToApp();

    }

    /**
     * Method to perform search action by clicking search icon or select value from the view listed
     *
     * @param itemName - The text for enter in the search box
     * @param action   -based on the requirement it performs the order i,e selectItemFromDropdown or clickSearchIcon
     */
    @And("^the user enter '([\\w\\s\\S]+)' text on search box and (selectItemFromDropdown|clickSearchIcon)$")
    public void searchRelatedItem(String itemName, String action) throws InterruptedException {
        homePage.searchRelatedItem(action, itemName);

    }

    /**
     * Method to verify whether the User will be on the right page
     *
     * @param value - true/false
     */
    @And("the user will be on the '([\\w\\s\\S]+)' Page:(true|false)$")
    public void verifyThePage(String pageName, boolean value) {
        homePage.waitForPageLoad(pageName, value);
    }

    /**
     * User will wait for page to load
     */
    @And("the user will wait to see the Default welcome msg header$")
    public void waitForDefaultWelcomeMsgLoad() throws InterruptedException {
        homePage.waitForDefaultWelcomeMsgLoad();
    }
}