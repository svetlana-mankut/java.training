package ua.qa.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.qa.adressbook.model.ContactData;

import java.util.Set;

/**
 * Created by polkota on 26.07.2016.
 */
public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().HomePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstname("David").withLastname("Green").withAdress("adress").withHomephone("123456789").withMobile("987654321").withEmail("david@david").withGroup("test1"), true);
        }
    }


    @Test
    public void testContactModification() {

        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withFirstname("David").withLastname("Green");

        app.contact().modify(contact);

        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedContact);
        before.add(contact);

        Assert.assertEquals(before, after);


    }


}
