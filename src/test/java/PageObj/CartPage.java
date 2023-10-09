package PageObj;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage {
    WebDriver driver;
    public void CartVerificationTest(WebDriver driver){
        this.driver = driver;
    }
    @FindBy(css = "[class='action showcart']")
    WebElement cart;
}
