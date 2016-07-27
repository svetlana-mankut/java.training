package ua.qa.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.qa.adressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {


    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().goToHome();
        if (! app.getContactHelper().isThereAcontact()){
            app.getContactHelper().createContact(new ContactData("David", "r", "Green", "polkota", "title", "company", "adress", "123456789", "987654321", "1988", "test1"),true);
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getNavigationHelper().goToHome();
        app.getContactHelper().selectContact(0);
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().acceptAlert();
        app.getNavigationHelper().goToHome();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

    }
}
