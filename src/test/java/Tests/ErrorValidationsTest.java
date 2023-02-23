package Tests;

import PageObjectPattern.CartPage;
import PageObjectPattern.ProductCataloguePage;
import TestComponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ErrorValidationsTest extends BaseTest {

    @Test
    public void LoginErrorValidation() throws IOException {

        String productName = "ZARA COAT 3";
        String countryName = "pol";

        landingPage.loginApplication("izaecabs@gmail.com", "Summer0111");
        Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
    }

    @Test
    public void ProductErrorValidation() throws IOException {

        String productName= "ZARA COAT 3";
        ProductCataloguePage productCataloguePage = landingPage.loginApplication("iza.ecabs@gmail.com", "Summer01");

        List<WebElement> products = productCataloguePage.getProductList();
        productCataloguePage.addProductToCart(productName);
        CartPage cartPage = productCataloguePage.goToCartPage();

        Boolean match = cartPage.isMyProductInCart("ZARA COAT 33");
        Assert.assertFalse(match); //all assertions have to stay in our test case
    }
}