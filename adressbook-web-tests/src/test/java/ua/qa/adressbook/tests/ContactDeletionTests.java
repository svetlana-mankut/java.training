package ua.qa.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.qa.adressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.getNavigationHelper().goToHome();
        if (!app.getContactHelper().isThereAcontact()) {
            app.getContactHelper().createContact
                    (new ContactData("David", "Green", "adress", "123456789", "987654321", "david@david", "test1"), true);
        }
    }

    @Test
    public void testContactDeletion() {

        List<ContactData> before = app.getContactHelper().getContactList();
        app.getNavigationHelper().goToHome();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().acceptAlert();
        app.getNavigationHelper().goToHome();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);

    }
}
