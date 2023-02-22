package Tests;

import PageObjectPattern.*;
import TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class SubmitOrderTest extends BaseTest { //all tests class should be extended by BaseTest to be possibe to use driver from there

    @Test
    public void SubmitOrder() throws IOException {

        String productName= "ZARA COAT 3";
        String countryName ="pol";

        ProductCataloguePage productCataloguePage = landingPage.loginApplication("izaecabs@gmail.com", "Summer01");

        List<WebElement> products = productCataloguePage.getProductList();
        productCataloguePage.addProductToCart(productName);
        CartPage cartPage = productCataloguePage.goToCartPage();

        Boolean match = cartPage.isMyProductInCart(productName);
        Assert.assertTrue(match); //all assertions have to stay in our test case
        CheckoutPage checkoutPage = cartPage.goToCheckoutPage();

        checkoutPage.selectCountry(countryName);
        ConfirmationPage confirmationPage = checkoutPage.placeAnOrder();
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        System.out.println("Assertion passed");
    }
}