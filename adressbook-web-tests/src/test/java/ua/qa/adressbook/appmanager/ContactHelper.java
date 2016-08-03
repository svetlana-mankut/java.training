package ua.qa.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ua.qa.adressbook.model.ContactData;
import ua.qa.adressbook.model.Contacts;

import java.io.File;
import java.util.List;

/**
 * Created by polkota on 26.07.2016.
 */
public class ContactHelper extends HelperBase {


    public ContactHelper(WebDriver wd) {
        super(wd);
    }


    public void fillContactTextFields(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("address"), contactData.getAdress());
        type(By.name("home"), contactData.getHomephone());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("work"), contactData.getWorkphone());
        type(By.name("email"), contactData.getEmail1());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        //setDate();
        //setMonth();
//        attach(By.name("photo"), contactData.getPhoto());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }



    private void fillSpecialContactInfo(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("address"), contactData.getAdress());
        type(By.name("home"), contactData.getHomephone());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("work"), contactData.getWorkphone());
        wd.findElement(By.name("email")).clear();
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

    public void setMonth() {
        randomChoiceFromDropdown("//select[@name='bmonth']", "//select[@name='bmonth']/option[@value]");
    }

    public void setDate() {
        randomChoiceFromDropdown("//select[@name='bday']", "//select[@name='bday']/option[@value]");
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
        submitContactCreation();
        contactCache = null;
        returnToContactPage();

    }

    public void createSpecialContact(ContactData specialContact) {
        createNewContact();
        fillSpecialContactInfo(specialContact);
        submitContactCreation();
        contactCache = null;
        returnToContactPage();

    }


    public void modify(ContactData contact) {
        returnToContactPage();
        selectContactById(contact.getId());
        initContactModification();
        fillContactTextFields(contact, false);
        submitContactModification();
        contactCache = null;
        returnToContactPage();
    }


    public void delete(ContactData contact) {
        returnToContactPage();
        selectContactById(contact.getId());
        deleteSelectedContact();
        acceptAlert();
        contactCache = null;
        returnToContactPage();
    }


    public boolean isThereAcontact() {
        return (isElementPresent(By.name("selected[]")));
    }
    public boolean isThereAspecial() {
        return (isElementPresent(By.xpath("//tr[@name='entry']/td[text()='Special']")));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Contacts contactCache = null;


    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String lastname = cells.get(1).getText();
            String firstname = cells.get(2).getText();
            String allPhones = cells.get(5).getText();
            String allemails = cells.get(4).getText();
            String adress = cells.get(3).getText();
            contactCache.add(new ContactData().withId(id).withFirstname(firstname)
                    .withLastname(lastname).withAllPhones(allPhones).withAllEmails(allemails).withAdress(adress));

        }
        return new Contacts(contactCache);
    }

    public Contacts allSpecial() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[td[text()='Special']]"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String lastname = cells.get(1).getText();
            String firstname = cells.get(2).getText();
            //String allPhones = cells.get(5).getText();
            // String allemails = cells.get(4).getText();
            // String adress = cells.get(3).getText();
            contactCache.add(new ContactData().withId(id).withFirstname(firstname)
                    .withLastname(lastname));//.withAllPhones(allPhones).withAllEmails(allemails).withAdress(adress));
        }
            return new Contacts(contactCache);
        }


    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email1 = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String adress = wd.findElement(By.name("address")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(contact.getFirstname()).withLastname(contact.getLastname())
                .withHomephone(home).withMobile(mobile).withWorkPhone(work)
                .withEmail1(email1).withEmail2(email2).withEmail3(email3).withAdress(adress);
    }

    private void initContactModificationById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();
    }


    public ContactData getDetailsInfo(ContactData contact) {
        openContactDetails(contact.getId());
        String allDetails = wd.findElement(By.xpath(".//div[@id='content']")).getText();
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withAllDetails(allDetails);
    }

    public ContactData getEditInfo(ContactData contact) {
        openContactEdit(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String adress = wd.findElement(By.name("address")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(contact.getFirstname()).withLastname(contact.getLastname())
                .withHomephone(home).withMobile(mobile).withWorkPhone(work).withAdress(adress);
    }

    private void openContactDetails(int id) {
        /*WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.xpath("//td[text()='Special']/.."));
        cells.get(6).findElement(By.tagName("a")).click();*/
        wd.findElement(By.xpath(String.format("//tr[@name='entry']/td[text()='Special']/../td[7]/a", id))).click();
        // click(By.xpath("//input[@id = '" + id + "']/../following-sibling::td[6]/a"));
    }


    private void openContactEdit(int id) {
        wd.findElement(By.xpath(String.format("//tr[@name='entry']/td[text()='Special']/../td[8 ]/a", id))).click();
        /*WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.xpath("//td[text()='Special']/.."));
        cells.get(7).findElement(By.tagName("a")).click();
        //cells.findElement(By.xpath("//td[8]/a"));
        //click(By.xpath("//tr[@name='entry']/td[text()='Special']/../td[8]/a"));*/
    }

}

