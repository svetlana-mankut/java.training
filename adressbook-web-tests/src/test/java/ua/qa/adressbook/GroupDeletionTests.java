package ua.qa.adressbook;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {


    @Test
    public void testGroupDeletion() {
        goToGroup();
        selectGroup();
        deleteSelectedGroup();
        returnToGroupPage();
    }


}
