package PageObjectPattern;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends AbstractComponent{

        WebDriver driver;
        public CheckoutPage(WebDriver driver){
            super(driver);
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }

    @FindBy(xpath="//input[@placeholder='Select Country']")
    WebElement selectCountryField;

    @FindBy(xpath="//span[contains(text(), 'Poland')]")
    WebElement selectCountry;

    @FindBy(css=".action__submit")
    WebElement submitBtn;

    By listOfCountries =  By.cssSelector(".ta-results");


        public void selectCountry(String countryName){
            Actions a = new Actions(driver);
            a.sendKeys(selectCountryField, countryName).build().perform();
            waitForElementToAppear(listOfCountries);
            selectCountry.click();
        }

        public ConfirmationPage placeAnOrder(){
            waitForElementToAppear(By.cssSelector(".action__submit"));
            submitBtn.click();
            return new ConfirmationPage(driver);
        }
}