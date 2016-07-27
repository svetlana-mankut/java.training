package ua.qa.adressbook.tests;

import org.testng.annotations.Test;
import ua.qa.adressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getContactHelper().createContact
                (new ContactData("David", "r", "Green", "polkota", "title", "company", "adress", "123456789", "987654321", "1988", "test1"), true);
        app.getNavigationHelper().goToHome();
    }


}
