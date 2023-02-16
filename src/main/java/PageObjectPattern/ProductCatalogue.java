package PageObjectPattern;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends AbstractComponent {

    WebDriver driver;

    public ProductCatalogue (WebDriver driver){
        super(driver);
        //as in standalone test we created object for that class with driver
        // as an argument, we can use it here but driver from an argument is visible now only in this method,
        // this is why below we are assigning it to our variable webdriver in loadingPage class
        this.driver =driver; //we are passing driver from standalone test to driver here
        PageFactory.initElements(driver, this);
    }

    //        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
    @FindBy(css=".mb-3")
    List<WebElement> products;

    By productsBy = By.cssSelector(".mb-3");

    public List<WebElement> getProductList(){
        waitElementToAppear(productsBy);
        return products;
    }

}

