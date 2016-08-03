package ua.qa.adressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.qa.adressbook.model.ContactData;
import ua.qa.adressbook.model.Contacts;
import ua.qa.adressbook.model.GroupData;
import ua.qa.adressbook.model.Groups;

import static org.testng.AssertJUnit.assertEquals;

/**
 * Created by polkota on 03.08.2016.
 */
public class ContactAddToGroupTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.contact().goToContact();
        if (app.db().contacts().size() == 0) {
            Groups groups = app.db().groups();
            app.contact().create(new ContactData().withId(0)
                    .withFirstname("David").withLastname("Green").withAdress("adress")
                    .withHomephone("123456789").withMobile("987654321").inGroup(groups.iterator().next()));
            if (app.db().groups().size() == 0) {
                app.goTo().GroupPage();
                app.group().create(new GroupData().withName("test1"));
                app.contact().goToContact();
            }
        }
        }

        @Test
        public void testContactAddToGroup () {

            Contacts before = app.db().contacts();
            Groups groups = app.db().groups();
            ContactData modifiedContact = before.iterator().next();
            app.contact().addSomeGroup(modifiedContact, groups);

            if (!app.contact().isThereContactReadyToAdd()) {
                System.out.println("В группе нет контактов " + groups.iterator().next().getName());

            } else {
                Contacts after = app.db().contacts();
                assertEquals(after.size(), before.size());
                assertEquals(after.iterator().next().getGroups().size(), modifiedContact.getGroups().size() + 1);

            }
        }
    }
