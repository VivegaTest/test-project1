package com.functions;

import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import java.awt.*;
import java.util.List;

//Purpose of this class to defined methods related to action
// e.x - Click button, setvalue, submit, clear

public class ButtonAction extends Button {

    public ButtonAction(String selector) {
    }

         WebElement element = new WebElement() {

            public void clickButton(String buttonLabel) {
                element.findElement(By.cssSelector(buttonLabel)).click();

            }

             @Override
             public void click() {

             }

             @Override
            public void submit() {

            }

            @Override
            public void sendKeys(CharSequence... charSequences) {

            }

            @Override
            public void clear() {

            }

            @Override
            public String getTagName() {
                return null;
            }

            @Override
            public String getAttribute(String s) {
                return null;
            }

            @Override
            public boolean isSelected() {
                return false;
            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public String getText() {
                return null;
            }

            @Override
            public List<WebElement> findElements(By by) {
                return null;
            }

            @Override
            public WebElement findElement(By by) {
                return null;
            }

            @Override
            public boolean isDisplayed() {
                return false;
            }

            @Override
            public Point getLocation() {
                return null;
            }

            @Override
            public Dimension getSize() {
                return null;
            }

            @Override
            public Rectangle getRect() {
                return null;
            }

            @Override
            public String getCssValue(String s) {
                return null;
            }

            @Override
            public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
                return null;
            }

        };


}




