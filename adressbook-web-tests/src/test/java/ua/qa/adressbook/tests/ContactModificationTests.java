package ua.qa.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.qa.adressbook.model.ContactData;
import ua.qa.adressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by polkota on 26.07.2016.
 */
public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        app.getNavigationHelper().goToHome();
        if (! app.getContactHelper().isThereAcontact()) app.getContactHelper().createContact
                (new ContactData("David", "Green", "adress", "123456789", "987654321", "david@david", "test1"),true);

        List<ContactData> before = app.getContactHelper().getContactList();
        app.getNavigationHelper().goToHome();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().initContactModification();
        ContactData contact = new ContactData
                (before.get(before.size() - 1).getId(), "David", "Green", null, null, null, null, null);
        app.getContactHelper().fillContactTextFields(contact, false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().goToHome();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);

        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId()) ;
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);



    }
}
