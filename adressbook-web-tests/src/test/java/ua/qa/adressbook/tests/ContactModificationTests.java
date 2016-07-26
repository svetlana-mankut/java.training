package ua.qa.adressbook.tests;

import org.testng.annotations.Test;
import ua.qa.adressbook.model.ContactData;

/**
 * Created by polkota on 26.07.2016.
 */
public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactTextFields
                (new ContactData("Sveta1", "Sveta2", "Sveta3", "pokota", "new title", "new company", "new adress", "09876", "12345"));
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().goToHome();


    }
}
