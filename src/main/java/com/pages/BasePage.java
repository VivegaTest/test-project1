package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.TestBase;

public class BasePage extends TestBase {

    public BasePage() {
        super();

    }

    @Override
    public void closeBrowser() {
        super.closeBrowser();
    }

    public WebElement executeFindElement(String element) {
        return  driver.findElement(By.cssSelector(element));
    }

    public By callSelector(String element){
       return By.cssSelector(element);
    }
}