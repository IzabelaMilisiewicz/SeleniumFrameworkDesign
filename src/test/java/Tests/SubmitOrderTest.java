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

    String productName= "ZARA COAT 3";
    String countryName ="pol";

    @Test
    public void SubmitOrderTest() throws IOException {

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

    //new test to verify if ZARA COAT 3 is displayed in orders page
    @Test(dependsOnMethods = {"SubmitOrderTest"}) //thanks to testng this test will be run only if SubmitOrderTest was run before and it passed
    public void OrderHistoryTest(){
        ProductCataloguePage productCataloguePage = landingPage.loginApplication("izaecabs@gmail.com", "Summer01");
        OrdersPage ordersPage = productCataloguePage.goToOrdersPage();
        Assert.assertTrue(ordersPage.IsMyProductInOrders(productName));
    }
}