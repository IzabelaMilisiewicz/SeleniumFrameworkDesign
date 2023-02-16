import PageObjectPattern.LandingPage;
import PageObjectPattern.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class SubmitOrderTest {

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        LandingPage landingPage = new LandingPage(driver); //sending argument to class - all argument you can catch in constructor in the destination class
        //we careated object for class landingPage above, so we can use methods from that class - below
        landingPage.goTo();
        landingPage.loginApplication("izaecabs@gmail.com", "Summer01");

        ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        List<WebElement> products = productCatalogue.getProductList();

//BELOW TO REFACTOR
//        //we have list of whole webelements, we can use with strems another locator which helps us too go deeper
//        WebElement prod1 = products.stream().filter(product->product.findElement(By.cssSelector("b")).getText()
//                .equals("ZARA COAT 3")).findFirst().orElse(null);
//        //we can search now only in the scope of previous search so only in our matched webelement
//        prod1.findElement(By.cssSelector(".card-body button:last-of-type")).click();
//        //xpath - button[contains(text(),'Add To Cart')]
//
//        //explicit wait to wait until toaster will be visible
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
//        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
////        invisibilityOfElementLocated(By.cssSelector(".ng-animiting"))); -> this solutions was much slower than invisibilityOF
//        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
//
//        List <WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
//        Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase("ZARA COAT 3"));
//        System.out.println(match);
//        Assert.assertTrue(match);
//        driver.findElement(By.cssSelector(".totalRow button")).click();
//
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