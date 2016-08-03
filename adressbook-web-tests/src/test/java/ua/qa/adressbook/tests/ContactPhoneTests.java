package ua.qa.adressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.qa.adressbook.appmanager.ContactHelper;
import ua.qa.adressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

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
                    .withFirstname("David").withLastname("Green")
                    .withAdress("Kyriakou Matsi, 10, Liliana Building, 2nd floor, office 203")
                    .withHomephone("123").withMobile("987").withWorkPhone("555")
                    .withEmail1("david1@david").withEmail2("david2@david").withEmail3("david3@david")
                    );
        }
    }

    @Test
    public void testContactPhones(){
        app.goTo().HomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));

    }

    public static String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomephone(), contact.getMobile(), contact.getWorkphone())
                .stream().filter((s) -> !s.equals(""))
                .map(ContactPhoneTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    private static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("-()", " ");
    }

}
