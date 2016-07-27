package ua.qa.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.qa.adressbook.model.ContactData;

/**
 * Created by polkota on 26.07.2016.
 */
public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        app.getNavigationHelper().goToHome();
        int before = app.getContactHelper().getContactCount();
        if (! app.getContactHelper().isThereAcontact()){
            app.getContactHelper().createContact(new ContactData("David", "r", "Green", "polkota", "title", "company", "adress", "123456789", "987654321", "1988", "test1"),true);
        }
        app.getNavigationHelper().goToHome();
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactTextFields
                (new ContactData("Sveta1", "Sveta2", "Sveta3", "pokota", "new title", "new company", "new adress", "09876", "12345","1999", null),false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().goToHome();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before);



    }
}
