package ua.qa.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ua.qa.adressbook.model.ContactData;
import ua.qa.adressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by polkota on 26.07.2016.
 */
public class ContactHelper extends HelperBase {


    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void setMonth() {
        randomChoiceFromDropdown("//select[@name='bmonth']", "//select[@name='bmonth']/option[@value]");
    }

    public void setDate() {
        randomChoiceFromDropdown("//select[@name='bday']", "//select[@name='bday']/option[@value]");
    }


    public void fillContactTextFields(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getUssername());
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("title"), contactData.getTitle());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAdress());
        type(By.name("home"), contactData.getHomephone());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("byear"), contactData.getYear());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }


    public void submitContactCreation() {
        click(By.name("submit"));

    }

    public void createNewContact() {
        click(By.linkText("add new"));

    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));

    }

    public void selectContact(int index) {

        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void acceptAlert() {
        wd.switchTo().alert().accept();
    }

    public void initContactModification() {
        click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void createContact(ContactData contact, boolean creation) {
        createNewContact();
        fillContactTextFields(contact, creation);
        setDate();
        setMonth();
        submitContactCreation();

    }

    public boolean isThereAcontact() {
        return (isElementPresent(By.name("selected[]")));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }


    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name = 'entry']//td[3]"));
        for (WebElement element : elements) {
            String lastname = element.getText();

            ContactData contact = new ContactData
                    (lastname, null, null, null, null, null, null, null, null, null, null);
            contacts.add(contact);
        }
        return contacts;
    }
    }


