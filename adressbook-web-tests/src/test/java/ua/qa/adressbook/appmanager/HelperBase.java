package ua.qa.adressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.util.List;
import java.util.Random;

/**
 * Created by polkota on 26.07.2016.
 */
public class HelperBase {
    protected WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        click(locator);
        if (text != null) {
            String existingText = wd.findElement(locator).getAttribute("value");
            if (!text.equals(existingText)) {
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
        }
    }

    protected void attach(By locator, File file){
        if (file != null){
            wd.findElement(locator).sendKeys(file.getAbsolutePath());
        }
    }

    public WebElement randomChoiceFromDropdown(String arg, String xpath) {
        WebElement openDropdown = wd.findElement(By.xpath(arg));
        openDropdown.click();
        List<WebElement> listOfElements = wd.findElements(By.xpath(xpath));
        // select a random one
        Random random = new Random();
        WebElement someRandomElement = listOfElements.get(random.nextInt(listOfElements.size()));
        someRandomElement.click();
        // openDropdown.click();
        return someRandomElement;

    }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    protected boolean isElementPresent(By locator) {
        try {
        wd.findElement(locator);
            return true;
    }
        catch (NoSuchElementException ex) {
            return false;
        }
            }
}
