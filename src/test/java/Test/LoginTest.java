package Test;

import Base.DriverInitiator;
import PageObj.HomePage;
import PageObj.LoginPage;
import Util.ExcelUtil;
import Util.ReportUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITest;
import org.testng.annotations.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Listeners(
        {
                ReportUtil.class
        }
)
public class LoginTest extends DriverInitiator implements ITest {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;

    @BeforeTest
    public void setup() {
        driver = super.createWebDriverSession();
    }

    @Test(dataProvider = "credentials")
    public void loginTest(String user, String pass) {
        driver.get(PAGE_URL);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.enterUserName(user);
        loginPage.enterPassword(pass);
        loginPage.submit();
        homePage = PageFactory.initElements(driver, HomePage.class);
        Assert.assertEquals(homePage.getPageTitle(), "My Account");
        homePage.logout();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
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

    @Override
    public String getTestName() {
        return "LoginTest";
    }
}
