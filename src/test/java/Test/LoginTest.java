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
import org.testng.ITest;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class LoginTest extends DriverInitiator {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    ExtentTest extentTest;
    ExtentReports exentReport;
    @BeforeTest
    public void setup() {
        driver = super.createWebDriverSession();
        ReportUtil report = new ReportUtil();
        exentReport = report.createTestReport();
    }

    @Test(dataProvider = "credentials")
    public void loginTest(String user, String pass) {

         extentTest= exentReport.createTest("loginTest","Starting");
        driver.get(PAGE_URL);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.enterUserName(user);
        loginPage.enterPassword(pass);
        loginPage.submit();
        homePage = PageFactory.initElements(driver, HomePage.class);
        Assert.assertEquals(homePage.getPageTitle(), "My Account");
        homePage.logout();
    }
    @AfterMethod
    public void getResult(ITestResult result) {
        if(result.getStatus() == ITestResult.FAILURE) {
            extentTest.log(Status.FAIL,result.getThrowable());
        }
        else if(result.getStatus() == ITestResult.SUCCESS) {
            extentTest.log(Status.PASS, result.getTestName());
        }
        else {
            extentTest.log(Status.SKIP, result.getTestName());
        }
    }
    @AfterTest
    public void tearDown() {
        driver.quit();
        exentReport.flush();
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
