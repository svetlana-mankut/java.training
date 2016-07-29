package ua.qa.adressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.qa.adressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by polkota on 29.07.2016.
 */
public class ContactPhoneTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().HomePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstname("David").withLastname("Green").withAdress("adress")
                    .withHomephone("123").withMobile("987").withWorkPhone("555").withEmail("david@david").withGroup("test1"));
        }
    }

    @Test
    public void testContactPhones(){
        app.goTo().HomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getHomephone(), equalTo(contactInfoFromEditForm.getHomephone()));
        assertThat(contact.getMobile(), equalTo(contactInfoFromEditForm.getMobile()));
        assertThat(contact.getWorkphone(), equalTo(contactInfoFromEditForm.getWorkphone()));
    }
}
