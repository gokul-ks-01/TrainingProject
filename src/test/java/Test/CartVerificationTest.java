package Test;

import Base.DriverInitiator;
import PageObj.HomePage;
import PageObj.LoginPage;
import Util.PropertyReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

public class CartVerificationTest extends DriverInitiator {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;

    @BeforeTest
    @Parameters({"browser"})
    public void setup(String browser) {
        driver = super.createWebDriverSession(browser);
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

