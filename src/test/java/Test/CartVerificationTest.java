package Test;

import Base.DriverInitiator;
import PageObj.HomePage;
import PageObj.LoginPage;
import Util.PropertyReader;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

public class CartVerificationTest extends DriverInitiator {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    ExtentTest extentTest;
    ExtentReports extentReport;

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
        Assert.assertEquals(homePage.getCartItemsCount(), "4");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

}

