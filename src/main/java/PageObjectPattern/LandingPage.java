package PageObjectPattern;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent {
        //this page should be focused on elements and actions

        WebDriver driver;
        public LandingPage(WebDriver driver){  //as in standalone test we created object for that class with driver

                super(driver); //this is to send driver in our case from child (LandingPage) to parent (AbstractCOmponent)
            // as an argument, we can use it here but driver from an argument is visible now only in this method,
            // this is why below we are assigning it to our variable webdriver in loadingPage class
            this.driver =driver; //we are passing driver from standalone test to driver here
            PageFactory.initElements(driver, this);
        }

        // without PageFactory: WebElement userEmail = driver.findElement(By.id("userEmail")).sendKeys(userEmail);

        //PageFactory
        @FindBy(id="userEmail")
        WebElement userEmail;

        @FindBy(id="userPassword")
        WebElement passwordElement;

        @FindBy(id="login")
        WebElement submit;

        public ProductCataloguePage loginApplication(String email, String password){
                userEmail.sendKeys(email);
                passwordElement.sendKeys(password);
                submit.click();
                ProductCataloguePage productCataloguePage = new ProductCataloguePage(driver); //object for new page where we will land after LandingPage
                return productCataloguePage;
        }

        public void goTo(){
                driver.get("https://rahulshettyacademy.com/client");

        }
}
