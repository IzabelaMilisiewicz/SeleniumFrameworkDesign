import PageObjectPattern.CartPage;
import PageObjectPattern.LandingPage;
import PageObjectPattern.ProductCataloguePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class SubmitOrderTest {

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        String productName= "ZARA COAT 3";
        LandingPage landingPage = new LandingPage(driver); //sending argument to class - all argument you can catch in constructor in the destination class
        //we careated object for class landingPage above, so we can use methods from that class - below
        landingPage.goTo();
        ProductCataloguePage productCataloguePage = landingPage.loginApplication("izaecabs@gmail.com", "Summer01");

        List<WebElement> products = productCataloguePage.getProductList();
        productCataloguePage.addProductToCart(productName);
        CartPage cartPage = productCataloguePage.goToCartPage();

        Boolean match = cartPage.isMyProductInCart(productName);
        Assert.assertTrue(match); //all assertions have to stay in our test case
        cartPage.goToCheckoutPage();


//BELOW TO REFACTOR
//        Actions a = new Actions(driver);
//        a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "Pol").build().perform();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
//        driver.findElement(By.xpath("//span[contains(text(), 'Poland')]")).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".action__submit")));
//        driver.findElement(By.cssSelector(".action__submit")).click();
//        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
//        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
//        driver.close();
    }
}