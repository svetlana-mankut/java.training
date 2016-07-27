package ua.qa.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.qa.adressbook.model.GroupData;

import java.util.List;

/**
 * Created by polkota on 26.07.2016.
 */
public class GroupModificationTests extends TestBase {
    @Test
    public void testGroupModification(){
        app.getNavigationHelper().goToGroup();

        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("test1",null,null));
        }
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(before.size() - 1);
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("test1", null, null));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());

    }
}
