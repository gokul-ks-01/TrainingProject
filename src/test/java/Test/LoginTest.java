package Test;

import Base.DriverInitiator;
import PageObj.HomePage;
import PageObj.LoginPage;
import Util.ExcelUtil;
import Util.ReportUtil;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.TestException;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginTest extends DriverInitiator {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    ReportUtil report;
    ExtentReports extentReport;
    ExtentTest extentTest;


    @BeforeTest
    public void setup() {
        driver = super.createWebDriverSession();
        report = new ReportUtil("testReport.html");
        extentReport = report.createTestReport();
    }

    @Test(dataProvider = "credentials", testName = "LoginTest")
    public void loginTest(String user, String pass) {
        extentTest = extentReport.createTest("loginTest",
                "Test to verify one valid credential and one incorrect credential");
        driver.get(PAGE_URL);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        extentTest.log(Status.PASS, "Launched URL");
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
            extentTest.log(Status.FAIL, "Invalid Credentials");
            throw new TestException("Assertion Error");
        }

    }

    @AfterMethod
    public void getResult(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            extentTest.log(Status.FAIL, result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            extentTest.log(Status.PASS, result.getName());
        } else {
            extentTest.log(Status.SKIP, result.getName());
        }
    }

    @AfterTest
    public void tearDown() {
        driver.close();
        extentReport.flush();
    }


    @DataProvider(name = "credentials")
    public Object[][] credentials() throws IOException {

        List<String[]> dataList = new ArrayList<String[]>();
        ExcelUtil util = new ExcelUtil();
        for (int i = 1; i < 3; i++) {
            String usr = util.ReadExcel(i, 0);
            String pass = util.ReadExcel(i, 1);
            dataList.add(new String[]{usr, pass});
        }
        return dataList.toArray(new String[dataList.size()][]);

    }

}
