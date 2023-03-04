package PageObjectPattern;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent {

    WebDriver driver;
    public CartPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //PageFactory
    @FindBy(css=".cartSection h3")
    List<WebElement> cartProducts;

    @FindBy(css=".totalRow button")
    private WebElement checkoutBtn;


    public List<WebElement> getProductsFromCart(){
        return cartProducts;
    }

    public Boolean isMyProductInCart(String productName){
        Boolean cartCheck = getProductsFromCart().stream().anyMatch(cartProduct -> cartProduct.getText()
                .equalsIgnoreCase(productName));
        return cartCheck;
    }

    public CheckoutPage goToCheckoutPage(){
        checkoutBtn.click();
        return new CheckoutPage(driver);
    }

}
