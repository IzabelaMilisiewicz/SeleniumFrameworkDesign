package PageObjectPattern;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCataloguePage extends AbstractComponent {

    WebDriver driver;

    public ProductCataloguePage(WebDriver driver){
        super(driver);
        //as in standalone test we created object for that class with driver
        // as an argument, we can use it here but driver from an argument is visible now only in this method,
        // this is why below we are assigning it to our variable webdriver in loadingPage class
        this.driver = driver; //we are passing driver from standalone test to driver here
        PageFactory.initElements(driver, this);
    }

    //        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
    //Page factory can be used only for webelements so when we start locator from driver.findelentbY
    @FindBy(css=".mb-3")
    List<WebElement> products;

    @FindBy(css=".ng-animiting")
    WebElement animation;

    By productsBy = By.cssSelector(".mb-3");
    By addToCart = By.cssSelector(".card-body button:last-of-type");
    By toastMessage = By.cssSelector("#toast-container");

    public List<WebElement> getProductList(){
        waitForElementToAppear(productsBy);
        return products;
    }

    public WebElement getProductByName(String productName){
        WebElement prod1 = getProductList().stream().filter(product->product.findElement(By.cssSelector("b"))
                .getText().equals(productName)).findFirst().orElse(null);
        return prod1;
    }

    public void addProductToCart(String productName){
        WebElement prod1 = getProductByName(productName);
        prod1.findElement(addToCart).click();
        waitForElementToAppear(toastMessage);
        waitElementToDisappear(animation);
    }
}

