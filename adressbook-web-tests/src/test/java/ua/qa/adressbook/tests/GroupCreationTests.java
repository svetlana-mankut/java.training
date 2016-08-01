package ua.qa.adressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ua.qa.adressbook.model.GroupData;
import ua.qa.adressbook.model.Groups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroups() {
        List<Object[]> list = new ArrayList<Object[]>();
        list.add(new Object[]{new GroupData().withName("test1").withHeader("header1").withFooter("footer1") });
        list.add(new Object[]{new GroupData().withName("test2").withHeader("header2").withFooter("footer2") });
        list.add(new Object[]{new GroupData().withName("test3").withHeader("header3").withFooter("footer3") });
        return list.iterator();
    }

    @Test(dataProvider = "validGroups")
    public void testGroupCreation(GroupData group) {

        app.goTo().GroupPage();
        Groups before = app.group().all();

        app.group().create(group);
        assertThat((app.group().count()), equalTo(before.size() + 1));
        Groups after = app.group().all();
        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    }



    @Test
    public void n() {
        app.goTo().GroupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("test2'");
        app.group().create(group);
        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.group().all();
        assertThat(after, equalTo(before));

    }
}
