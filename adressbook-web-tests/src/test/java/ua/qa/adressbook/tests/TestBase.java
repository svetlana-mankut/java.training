package ua.qa.adressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ua.qa.adressbook.appmanager.ApplicationManager;

/**
 * Created by polkota on 20.06.2016.
 */
public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);


    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite
    public void tearDown() {
        app.stop();
    }


}
