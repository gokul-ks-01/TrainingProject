package Base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverInitiator {

    WebDriver driver;
    public static final String PAGE_URL = "https://magento.softwaretestingboard.com/customer/account/login/" ;

    public WebDriver createWebDriverSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        return driver;
    }
}
