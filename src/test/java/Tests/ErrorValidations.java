package Tests;

import PageObjectPattern.ProductCataloguePage;
import TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ErrorValidations extends BaseTest {

    @Test
    public void SubmitOrder() throws IOException {

        String productName = "ZARA COAT 3";
        String countryName = "pol";

        landingPage.loginApplication("izaecabs@gmail.com", "Summer0111");
        Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
    }
}