package ua.qa.adressbook.tests;

import org.testng.annotations.Test;
import ua.qa.adressbook.model.ContactData;
import ua.qa.adressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test//(enabled = false)
    public void testContactCreation() {
        app.goTo().HomePage();
        Contacts before = app.contact().all();
        File photo = new File("src/test/resources/ph.jpg");

        ContactData contact = new ContactData()
                .withFirstname("Deen").withLastname("Green")
                .withAdress("Kyriakou Matsi, 10, Liliana Building, 2nd floor, office 203")
                .withHomephone("123").withMobile("9871").withWorkPhone("555")
                .withEmail1("david1@david").withEmail2("david2@david").withEmail3("david3@david")
                .withGroup("test1").withPhoto(photo);

        app.contact().create(contact);
        app.goTo().HomePage();
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

   /* @Test
    public void testCurrentDir(){
        File currentDir = new File(".");
        System.out.println(currentDir.getAbsolutePath());
        File photo = new File("src/test/resources/ph.jpg");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());
    }*/
}
