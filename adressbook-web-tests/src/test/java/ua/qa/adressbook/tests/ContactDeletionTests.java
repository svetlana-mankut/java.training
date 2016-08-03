package ua.qa.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.qa.adressbook.model.ContactData;
import ua.qa.adressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().HomePage();
        if (app.db().contacts().size() == 0) {
            app.goTo().HomePage();
            app.contact().create(new ContactData()
                    .withFirstname("David").withLastname("Green")
                    .withAdress("Kyriakou Matsi, 10, Liliana Building, 2nd floor, office 203")
                    .withHomephone("123").withMobile("987").withWorkPhone("555")
                    .withEmail1("david1@david").withEmail2("david2@david").withEmail3("david3@david")
                    .withGroup(null));
        }
    }

    @Test
    public void testContactDeletion() {
        app.goTo().HomePage();
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        assertThat(app.contact().count(), equalTo(before.size() - 1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(deletedContact)));

    }


}
