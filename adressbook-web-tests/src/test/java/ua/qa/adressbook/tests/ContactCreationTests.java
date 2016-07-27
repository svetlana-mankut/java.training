package ua.qa.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.qa.adressbook.model.ContactData;
import ua.qa.adressbook.model.GroupData;

import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().goToHome();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().createContact
                (new ContactData("David", "r", "Green", "polkota", "title", "company", "adress", "123456789", "987654321", "1988", "test1"), true);
        app.getNavigationHelper().goToHome();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);
    }
}
