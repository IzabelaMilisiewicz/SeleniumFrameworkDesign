package AbstractComponents;

import PageObjectPattern.CartPage;
import PageObjectPattern.OrdersPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponent {
    //this is class to put all methods which can be used by all pages

    WebDriver driver;
    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
        //this is constructor to chatch driver from child (LangingPage)
        PageFactory.initElements(driver, this); //without this PageFactory won't work
    }

    @FindBy(css="[routerlink*='cart']")
    WebElement cartHeader;

    @FindBy(css="[routerlink*='myorders']")
    WebElement ordersHeader;

    public void waitForElementToAppear(By findBy){
        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitForWebelementToAppear(WebElement findBy){
        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(findBy));
    }

    public void waitElementToDisappear(WebElement ele){
        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOf(ele));
    }

    public CartPage goToCartPage(){
        cartHeader.click();
        CartPage cartPage = new CartPage(driver);
        return cartPage;
    }

    public OrdersPage goToOrdersPage(){
        ordersHeader.click();
        OrdersPage ordersPage = new OrdersPage(driver);
        return ordersPage;
    }
       }
