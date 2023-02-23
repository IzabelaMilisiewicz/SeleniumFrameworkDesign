package PageObjectPattern;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrdersPage extends AbstractComponent {
    WebDriver driver;
    public OrdersPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //PageFactory
    @FindBy(css="tr td:nth-child(3)")
    List<WebElement> ordersProducts;

    public List<WebElement> getProductsFromOrders(){
        return ordersProducts;
    }

    public Boolean IsMyProductInOrders(String productName){
        Boolean ordersCheck = getProductsFromOrders().stream().anyMatch(ordersProduct -> ordersProduct.getText()
                .equalsIgnoreCase(productName));
        return ordersCheck;
    }
}

