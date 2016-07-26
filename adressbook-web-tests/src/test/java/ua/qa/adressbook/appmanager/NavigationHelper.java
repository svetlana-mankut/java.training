package ua.qa.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by polkota on 21.06.2016.
 */
public class NavigationHelper {
    private FirefoxDriver wd;

    public NavigationHelper(FirefoxDriver wd) {
        this.wd = wd;
    }

    public void goToGroup() {
        wd.findElement(By.linkText("groups")).click();
    }

    public void goToHome() {
        wd.findElement(By.linkText("home")).click();
    }
}
