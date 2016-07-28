package ua.qa.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.qa.adressbook.model.ContactData;

import java.util.Set;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.goTo().HomePage();
        Set<ContactData> before = app.contact().all();
        ContactData contact = new ContactData()
                .withFirstname("David").withLastname("Green").withAdress("adress").withHomephone("123456789").withMobile("987654321").withEmail("david@david").withGroup("test1");
        app.contact().create(contact, true);
        app.goTo().HomePage();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() + 1);
        contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        before.add(contact);

        Assert.assertEquals(before, after);

    }
}
