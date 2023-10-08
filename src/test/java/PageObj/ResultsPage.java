package PageObj;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ResultsPage {
    WebDriver driver;

    public ResultsPage(WebDriver driver) {
        this.driver = driver;
    }

   /* @FindBy(xpath="//div[@class='product details product-item-details']//child::a[contains(text(),'Proteus Fitness Jackshirt')]")
    WebElement jackshirt;*/

    @FindBy(xpath = "//div[@class='product details product-item-details']//child::a[@class='product-item-link']")
    List<WebElement> results;

    @FindBy(xpath = "//button[@title='Add to Cart']")
    WebElement addToCart;
    public boolean isProductPresent(String productName) {
        for (WebElement product: results) {
            if(product.getText().equalsIgnoreCase(productName)){
                return true;
            }
        }
        return false;
    }
    public void addToCart(String productName){
        for (WebElement product: results) {
            if(product.getText().equalsIgnoreCase(productName)){
                addToCart.click();
            }
        }

    }
}
