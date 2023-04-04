package com.steps;

import com.pages.HomePage;
import com.pages.SearchPage;
import cucumber.api.java.en.And;

/*****
 * Steps for interacting with the Search box functionality
 *****/
public class SearchSteps {

    private final HomePage homePage;
    private final SearchPage searchPage;

    public SearchSteps(HomePage homePage, SearchPage searchPage) {
        this.homePage = homePage;
        this.searchPage = searchPage;
    }

    /**
     * Method to select a item by hyperlink
     *
     * @param itemName - The item name to enter in the href link
     */
    @And("the user selects a item '([\\w\\s]+)' to view in a page")
    public void selectItemByLink(String itemName) {
        searchPage.selectItemByLink(itemName);
    }

    /**
     * Method to click add to cart button on a specific item by hover over
     *
     * @param itemName - The item name to enter in the href link
     */
    @And("the user hoverOver a item '([\\w\\s\\S]+)' and click add To item on Search page")
    public void selectItemByHoverOver(String itemName) throws InterruptedException {
        searchPage.selectItemByHoverOver(itemName);
    }
}