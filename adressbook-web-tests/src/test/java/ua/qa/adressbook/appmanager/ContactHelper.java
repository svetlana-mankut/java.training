package ua.qa.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import ua.qa.adressbook.model.ContactData;

import java.util.List;
import java.util.Random;

/**
 * Created by polkota on 26.07.2016.
 */
public class ContactHelper {
    private FirefoxDriver wd;

    public ContactHelper(FirefoxDriver wd) {
        this.wd = wd;
    }

    public void setMonth() {
        randomChoiceFromDropdown("//select[@name='bmonth']", "//select[@name='bmonth']/option[@value]");
    }

    public void setDate() {
        randomChoiceFromDropdown("//select[@name='bday']", "//select[@name='bday']/option[@value]");
    }

    public void setYear(String year) {
        wd.findElement(By.name("byear")).click();
        wd.findElement(By.name("byear")).clear();
        wd.findElement(By.name("byear")).sendKeys(year);
    }

    public void fillContactTextFields(ContactData contactData) {
        wd.findElement(By.name("firstname")).click();
        wd.findElement(By.name("firstname")).clear();
        wd.findElement(By.name("firstname")).sendKeys(contactData.getUssername());
        wd.findElement(By.name("middlename")).click();
        wd.findElement(By.name("middlename")).clear();
        wd.findElement(By.name("middlename")).sendKeys(contactData.getMiddlename());
        wd.findElement(By.name("lastname")).click();
        wd.findElement(By.name("lastname")).clear();
        wd.findElement(By.name("lastname")).sendKeys(contactData.getLastname());
        wd.findElement(By.name("nickname")).click();
        wd.findElement(By.name("nickname")).clear();
        wd.findElement(By.name("nickname")).sendKeys(contactData.getNickname());
        wd.findElement(By.name("title")).click();
        wd.findElement(By.name("title")).clear();
        wd.findElement(By.name("title")).sendKeys(contactData.getTitle());
        wd.findElement(By.name("company")).click();
        wd.findElement(By.name("company")).clear();
        wd.findElement(By.name("company")).sendKeys(contactData.getCompany());
        wd.findElement(By.name("address")).click();
        wd.findElement(By.name("address")).clear();
        wd.findElement(By.name("address")).sendKeys(contactData.getAdress());
        wd.findElement(By.name("home")).click();
        wd.findElement(By.name("home")).clear();
        wd.findElement(By.name("home")).sendKeys(contactData.getHomephone());
        wd.findElement(By.name("mobile")).click();
        wd.findElement(By.name("mobile")).clear();
        wd.findElement(By.name("mobile")).sendKeys(contactData.getMobile());
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

    public void submitContactCreation() {
        wd.findElement(By.name("submit")).click();
    }

    public void createNewContact() {
        wd.findElement(By.linkText("add new")).click();
    }

    public void deleteSelectedContact() {
        wd.findElement(By.xpath("//input[@value='Delete']")).click();
    }

    public void selectContact() {
        wd.findElement(By.name("selected[]")).click();
    }

    public void acceptAlert(){ wd.switchTo().alert().accept();}
}
