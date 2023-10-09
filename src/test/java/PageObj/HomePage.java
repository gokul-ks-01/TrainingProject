package PageObj;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
public class HomePage {
    WebDriver driver;


    @FindBy(xpath = "//li[@class='customer-welcome']//button[@class='action switch']")
    WebElement customerActionSwitch;
    @FindBy(partialLinkText = "Sign Out")
    WebElement signOut;
    @FindBy(id="search")
    WebElement searchBox;

    @FindBy(xpath = "//div[@data-block='minicart']//child::span[@class='counter-number']")
    WebElement cartItems;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void logout() {
        customerActionSwitch.click();
        signOut.click();

    }

    public String getPageTitle(){
       return driver.getTitle();
    }

    public void search(String searchText){
        searchBox.clear();
        searchBox.sendKeys(searchText);
        searchBox.sendKeys(Keys.ENTER);
    }

    public String getCartItemsCount(){
        return cartItems.getText();
    }
}
