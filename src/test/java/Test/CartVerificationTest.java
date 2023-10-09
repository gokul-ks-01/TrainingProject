package Test;

import Base.DriverInitiator;
import PageObj.HomePage;
import PageObj.LoginPage;
import Util.PropertyReader;
import Util.ReportUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

@Listeners(
        {
                ReportUtil.class
        }
)
public class CartVerificationTest extends DriverInitiator implements ITest {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    @BeforeTest
    public void setup() {
        driver = super.createWebDriverSession();
    }

    @Test
    public void searchBoxTest() throws IOException, InterruptedException {
        driver.get(PAGE_URL);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        PropertyReader propertyReader = new PropertyReader();
        loginPage.enterUserName(propertyReader.getProperty("user"));
        loginPage.enterPassword(propertyReader.getProperty("password"));
        loginPage.submit();
        homePage = PageFactory.initElements(driver, HomePage.class);
        Thread.sleep(2000);
        Assert.assertEquals(homePage.getCartItemsCount(),"3");
    }
    @AfterTest
    public void tearDown() {
        driver.quit();

    }
    @Override
    public String getTestName() {
        return "cart items count verification";
    }
}
