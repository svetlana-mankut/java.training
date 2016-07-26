package ua.qa.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by polkota on 21.06.2016.
 */
public class NavigationHelper extends HelperBase {


    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void goToGroup() {
        click(By.linkText("groups"));
    }

    public void goToHome() {
        click(By.linkText("home"));
    }
}
