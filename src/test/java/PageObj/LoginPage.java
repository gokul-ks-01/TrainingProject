package PageObj;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
    WebDriver driver;
    @FindBy(id = "email")
    WebElement email;
    @FindBy(id = "pass")
    WebElement password;
    @FindBy(id = "send2")
    WebElement submitButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUserName(String userName) {
        email.clear();
        email.sendKeys(userName);
        email.sendKeys(Keys.TAB);
    }

    public void enterPassword(String userPassword) {
        password.clear();
        password.sendKeys(userPassword);
        password.sendKeys(Keys.TAB);
    }

    public void submit() {
        submitButton.click();
    }


}
