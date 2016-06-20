package ua.qa.adressbook.tests;

import org.testng.annotations.Test;
import ua.qa.adressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {

        app.getNavigationHelper().goToGroup();
        app.getGroupHelper().createNewGroup();
        app.getGroupHelper().fillGroupForm(new GroupData("test1", "test2", "test3"));
        app.getGroupHelper().submitGroupCreation();
        app.getGroupHelper().returnToGroupPage();
    }

}
