package ua.qa.adressbook.tests;

import org.testng.annotations.Test;
import ua.qa.adressbook.model.ContactData;

/**
 * Created by polkota on 29.07.2016.
 */
public class ContactPhoneTests extends TestBase {

    @Test
    public void testContactPhones(){
        app.goTo().HomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    }
}
