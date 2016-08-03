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
public class ContactInformationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().HomePage();
        if ( ! app.contact().isThereAspecial()) {
            app.contact().createSpecialContact((new ContactData()
                    .withFirstname("Special").withLastname("Green")
                    .withAdress("Kyriakou Matsi, 10, Liliana Building, 2nd floor, office 203")
                    .withHomephone("123").withMobile("9871").withWorkPhone("555")));
        }
    }

    @Test
    public void testContactInfotmation() {
        app.goTo().HomePage();

        ContactData specialContact = app.contact().allSpecial().iterator().next();;
        ContactData contactInfoFromEditForm = app.contact().getEditInfo(specialContact);
        ContactData contactInfoFromDetailsForm = app.contact().getDetailsInfo(specialContact);
        assertThat(cleaned(mergeDetails(contactInfoFromEditForm)), equalTo(contactInfoFromDetailsForm.getAllDetails()));
    }



    private String mergeDetails(ContactData contact) {
        String homePhone = "";
        String mobile = "";
        String workPhone = "";
        if(!contact.getHomephone().equals("")){
            homePhone = "H: " + contact.getHomephone();
        }
        if (!contact.getMobile().equals("")){
            mobile = "M: " + contact.getMobile();
        }
        if (!contact.getWorkphone().equals("")){
            workPhone = "W: " + contact.getWorkphone();
        }
        return  Arrays.asList(contact.getFirstname() + " " + contact.getLastname(), contact.getAdress() + "\n", homePhone,
                mobile, workPhone)
                .stream().filter((s) -> !s.equals(""))
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String details){
        return details.replace("  ", " ");


    }
}
