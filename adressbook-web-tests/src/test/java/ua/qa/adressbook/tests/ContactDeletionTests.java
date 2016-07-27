package ua.qa.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.qa.adressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {


    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().goToHome();
        int before = app.getContactHelper().getContactCount();
        if (! app.getContactHelper().isThereAcontact()){
            app.getContactHelper().createContact(new ContactData("David", "r", "Green", "polkota", "title", "company", "adress", "123456789", "987654321", "1988", "test1"),true);
        }
        app.getNavigationHelper().goToHome();
        app.getContactHelper().selectContact(0);
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().acceptAlert();
        app.getNavigationHelper().goToHome();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before - 1);

    }
}
