package ua.qa.adressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ua.qa.adressbook.appmanager.ApplicationManager;

/**
 * Created by polkota on 20.06.2016.
 */
public class TestBase {

    protected final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);


    @BeforeMethod
    public void setUp() throws Exception {
        app.init();
    }

    @AfterMethod
    public void tearDown() {
        app.stop();
    }


}
