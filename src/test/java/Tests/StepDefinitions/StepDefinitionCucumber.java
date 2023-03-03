package Tests.StepDefinitions;

import PageObjectPattern.*;
import TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class StepDefinitionCucumber extends BaseTest {

    public LandingPage landingPage; //we create an object to give life to the object in our method below accros all the methods
    public ProductCataloguePage productCataloguePage;
    public ConfirmationPage confirmationPage;

    @Given("I landed on ecommerce Page") //this is static expression
    public void I_landed_on_ecommerce_Page() throws IOException {
        landingPage = launchApplication();
    }

    @Given("^I log in with username (.+) and password (.+)$") //this is regular expression (regex) - we will use example data
    public void I_log_in_with_username_and_password(String username, String password){
        productCataloguePage = landingPage.loginApplication(username, password);
    }

    @When("^I add the product (.+) to the Cart$")
    public void I_add_product_to_the_cart(String productName) throws InterruptedException {
        List<WebElement> products = productCataloguePage.getProductList();
        productCataloguePage.addProductToCart(productName);
    }

    @When("^Checkout (.+) and submit the order$") //you can use @And too
    public void Checkout_and_submit_order(String productName){
        CartPage cartPage = productCataloguePage.goToCartPage();

        Boolean match = cartPage.isMyProductInCart(productName);
        Assert.assertTrue(match); //all assertions have to stay in our test case
        CheckoutPage checkoutPage = cartPage.goToCheckoutPage();

        checkoutPage.selectCountry("pol");
        confirmationPage = checkoutPage.placeAnOrder();
    }

    @Then("{string} message is displayed on ConfirmationPage") //pure regular expression
    public void message_is_displayed_on_ConfirmationPage(String string){ //we are capturing our text in string
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
        driver.close();
    }

    @Then("{string} message is displayed")
    public void message_is_displayed(String stringErroIncorrectLoginData){ //we are capturing our text in string
        Assert.assertEquals(stringErroIncorrectLoginData, landingPage.getErrorMessage());
        driver.close();
    }
}
