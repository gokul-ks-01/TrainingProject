package Test;

import Base.DriverInitiator;
import PageObj.HomePage;
import PageObj.LoginPage;
import Util.ExcelUtil;
import Util.ReportUtil;
import Util.RetryAnalyzer;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.TestException;
import org.testng.annotations.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Listeners(Util.ReportUtil.class)
public class LoginTest extends DriverInitiator {

    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private static ReportUtil reportUtil;
    private ExtentTest extentTest;


    @BeforeTest
    @Parameters({"browser"})
    public void setup(String browser) {
        driver = super.createWebDriverSession(browser);
    }

    @Test(dataProvider = "credentials", testName = "LoginTest", retryAnalyzer = RetryAnalyzer.class)
    public void loginTest(String user, String pass) {
        extentTest = reportUtil.getExtentReports().createTest("LoginTest");
        driver.get(PAGE_URL);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        extentTest.log(Status.INFO, "Launched URL");
        loginPage.enterUserName(user);
        loginPage.enterPassword(pass);
        loginPage.submit();
        homePage = PageFactory.initElements(driver, HomePage.class);
        try {
            Assert.assertEquals(homePage.getPageTitle(), "My Account");
            extentTest.log(Status.PASS, "Successful login to Account page");
            homePage.logout();
            extentTest.log(Status.PASS, "Logged out from the application");
        } catch (AssertionError assertionError) {
            extentTest.log(Status.FAIL, "Login Test Failed: Invalid Credentials");
            extentTest.log(Status.FAIL, "Expected: 'My Account', Actual: '" + homePage.getPageTitle() + "'");
            throw new TestException("Assertion Error");
        }
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    @DataProvider(name = "credentials")
    public Object[][] credentials() throws IOException {
        List<String[]> dataList = new ArrayList<>();
        ExcelUtil util = new ExcelUtil();
        for (int i = 1; i < 3; i++) {
            String usr = util.ReadExcel(i, 0);
            String pass = util.ReadExcel(i, 1);
            dataList.add(new String[]{usr, pass});
        }
        return dataList.toArray(new String[dataList.size()][]);
    }
}
