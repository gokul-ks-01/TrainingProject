package Test;

import Base.DriverInitiator;
import PageObj.HomePage;
import PageObj.LoginPage;
import PageObj.ResultsPage;
import Util.PropertyReader;
import Util.ReportUtil;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

@Listeners(Util.ReportUtil.class)
public class SearchBoxTest extends DriverInitiator  {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    ResultsPage resultsPage;
    private static ReportUtil reportUtil;
    ExtentTest extentTest;

    @BeforeTest
    @Parameters({"browser"})
    public void setup(String browser) {
        driver = super.createWebDriverSession(browser);
    }

    @Test
    public void searchBoxTest() throws IOException {
        extentTest =reportUtil.getExtentReports().createTest("SearchBoxTest", "Test to verify seach function");

        driver.get(PAGE_URL);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        PropertyReader propertyReader = new PropertyReader();
        loginPage.enterUserName(propertyReader.getProperty("user"));
        loginPage.enterPassword(propertyReader.getProperty("password"));
        loginPage.submit();
        homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.search("Proteus Fitness Jackshirt");
        resultsPage = PageFactory.initElements(driver, ResultsPage.class);
        Assert.assertTrue(resultsPage.isProductPresent("Proteus Fitness Jackshirt"));
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }


}
