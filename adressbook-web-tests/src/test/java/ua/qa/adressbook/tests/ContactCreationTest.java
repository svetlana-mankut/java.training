package ua.qa.adressbook.tests;

import org.testng.annotations.Test;
import ua.qa.adressbook.model.ContactData;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() {
        app.getContactHelper().createNewContact();
        app.getContactHelper().fillContactTextFields(new ContactData("David", "r", "Green", "polkota", "title", "company", "adress", "123456789", "987654321"));
        app.getContactHelper().setDate();
        app.getContactHelper().setMonth();
        app.getContactHelper().setYear("1988");
        app.getContactHelper().submitContactCreation();
    }

}
