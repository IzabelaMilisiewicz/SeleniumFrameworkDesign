import org.apache.commons.lang3.ObjectUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class StandaloneTest {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/client");

        String username = "izaecabs@gmail.com";
        String password = "Summer01";

        driver.findElement(By.id("userEmail")).sendKeys(username);
        driver.findElement(By.id("userPassword")).sendKeys(password);
        driver.findElement(By.id("login")).click();

        WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

        //we have list of whole webelements, we can use with strems another locator which helps us too go deeper
        WebElement prod1 = products.stream().filter(product->product.findElement(By.cssSelector("b")).getText()
                .equals("ZARA COAT 3")).findFirst().orElse(null);

        //we can search now only in the scope of previous search so only in our matched webelement
        prod1.findElement(By.cssSelector(".card-body button:last-of-type")).click();
        //xpath - button[contains(text(),'Add To Cart')]

        //explicit wait to wait until toaster will be visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
//        invisibilityOfElementLocated(By.cssSelector(".ng-animiting"))); -> this solutions was much slower than invisibilityOF
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

    }
}
