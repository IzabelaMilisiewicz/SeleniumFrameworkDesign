package Tests;

import PageObjectPattern.*;
import TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest { //all tests class should be extended by BaseTest to be possibe to use driver from there

    String productName= "ZARA COAT 3";
    String countryName ="pol";

    @Test(dataProvider = "getData",groups={"Purchase"})
//    public void SubmitOrderTest(String email, String password, String productName) throws IOException {
    public void SubmitOrderTest(HashMap<String,String> input) throws IOException {

        ProductCataloguePage productCataloguePage = landingPage.loginApplication(input.get("email"), input.get("password"));

        List<WebElement> products = productCataloguePage.getProductList();
        productCataloguePage.addProductToCart(input.get("product"));
        CartPage cartPage = productCataloguePage.goToCartPage();

        Boolean match = cartPage.isMyProductInCart(input.get("product"));
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

    public String getScreenshot(String testCaseName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
        FileUtils.copyFile(source, file);
        return System.getProperty("user.dir")+"//reports//"+testCaseName+".png"; //here we will get path where our screenshot is stored
    }

        @DataProvider //runing test with data directly from Hash Maps
    public Object[][] getData() throws IOException {
        HashMap <String, String> map = new HashMap<String,String>();

        List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//Data//PurchaseOrder.json");
        return new Object[][] {{data.get(0)}, {data.get(1)}}; //one hashMap object is one set of data, in our json we have two hashmaps

//    @DataProvider //runing test with data directly from Hash Maps
//    public Object[][] getData(){
//        HashMap <String, String> map = new HashMap<String,String>();
//        map.put("email", "izaecabs@gmail.com");
//        map.put("password", "Summer01");
//        map.put("product", "ZARA COAT 3");
//
//        HashMap <String, String> map1 = new HashMap<String,String>();
//        map1.put("email", "iza.ecabs@gmail.com");
//        map1.put("password", "Summer01");
//        map1.put("product", "ADIDAS ORIGINAL");
//        return new Object[][] {{map}, {map1}};  //two demensional data

        //TestNG DataProvider
        //object below accepts all types of data type int, double, string etc
//        return new Object[][]{{"izaecabs@gmail.com", "Summer01", "ZARA COAT 3"}, {"iza.ecabs@gmail.com", "Summer01", "ADIDAS ORIGINAL"}};  //two demensional data
    }
}