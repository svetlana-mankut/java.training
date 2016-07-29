package ua.qa.adressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.qa.adressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by polkota on 29.07.2016.
 */
public class ContactEmailsTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().HomePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstname("David").withLastname("Green").withAdress("adress")
                    .withHomephone("123").withMobile("987").withWorkPhone("555")
                    .withEmail1("david1@david").withEmail2("david2@david").withEmail3("david3@david")
                    .withGroup("test1"));
        }
    }
    @Test
    public void testContactEmails(){
        app.goTo().HomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));

    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail1(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> ! s.equals(""))
                //.map(ContactPhoneTests::cleaned)
                .collect(Collectors.joining("\n"));
    }


   // public static String cleaned(String email){
    //    return email.replaceAll("\\s", "").replaceAll("-()", "");
    //}
}
