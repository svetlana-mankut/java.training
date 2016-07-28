package ua.qa.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.qa.adressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by polkota on 26.07.2016.
 */
public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().HomePage();
        if (app.contact().list().size() == 0) {
            app.contact().create
                    (new ContactData("David", "Green", "adress", "123456789", "987654321", "david@david", "test1"), true);
        }
    }



    @Test
    public void testContactModification() {

        List<ContactData> before = app.contact().list();
        ContactData contact = new ContactData
                (before.get(before.size() - 1).getId(), "David", "Green", null, null, null, null, null);
        int index = before.size() - 1;

        app.contact().modify(contact, index);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);

        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);


    }


}
