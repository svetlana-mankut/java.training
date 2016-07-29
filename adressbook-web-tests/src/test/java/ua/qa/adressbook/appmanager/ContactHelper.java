package ua.qa.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ua.qa.adressbook.model.ContactData;
import ua.qa.adressbook.model.Contacts;

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
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("address"), contactData.getAdress());
        type(By.name("home"), contactData.getHomephone());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("email"), contactData.getEmail());

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


    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value = '" + id + "']")).click();
    }

    public void returnToContactPage() {
        click(By.linkText("home"));
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

    public void create(ContactData contact) {
        createNewContact();
        fillContactTextFields(contact, true);
        setDate();
        setMonth();
        submitContactCreation();

    }

    public void modify(ContactData contact) {
        returnToContactPage();
        selectContactById(contact.getId());
        initContactModification();
        fillContactTextFields(contact, false);
        submitContactModification();
        returnToContactPage();
    }


    public void delete(ContactData contact) {
        returnToContactPage();
        selectContactById(contact.getId());
        deleteSelectedContact();
        acceptAlert();
        returnToContactPage();
    }


    public boolean isThereAcontact() {
        return (isElementPresent(By.name("selected[]")));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }


    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));

        for (WebElement element : elements) {
            String lastname = element.findElement(By.xpath("td[2]")).getText();
            String firstname = element.findElement(By.xpath("td[3]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));

            contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
        }
        return contacts;
    }
}


