package ua.qa.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.qa.adressbook.model.ContactData;
import ua.qa.adressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().goToHome();
        List<ContactData> before = app.getContactHelper().getContactList();
        ContactData contact = new ContactData("David", "Green", "adress", "123456789", "987654321", "david@david", "test1");
        app.getContactHelper().createContact(contact, true);
        app.getNavigationHelper().goToHome();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);


        before.add(contact);

        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

    }
}
