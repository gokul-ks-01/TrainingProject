package Base;

import Util.ReportUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITest;
import org.testng.annotations.Listeners;

import java.sql.SQLOutput;


public class DriverInitiator {
    public static final String PAGE_URL = "https://magento.softwaretestingboard.com/customer/account/login/" ;

    public WebDriver createWebDriverSession(String browserName) {
        WebDriver driver;
        switch (browserName){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;

            default:
                WebDriverManager.iedriver().setup();
                driver= new InternetExplorerDriver();
                break;
        }
        return driver;

    }
}
