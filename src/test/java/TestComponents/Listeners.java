package TestComponents;

import Recources.ExtentReporterNg;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

//ITestListener - interface provided by TestNG, giving methods for example onTestStart, onTestSuccess ect

public class Listeners extends BaseTest implements ITestListener {

    ExtentReports extent = ExtentReporterNg.getReportObject();
    ExtentTest test;
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); //ThreadSafe = each object creation have it's own thread

    @Override
    public void onTestStart(ITestResult result){ //result variable holds all information about our test
        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);  //set is a method which will set our object into ThreadLocal and for each object it will create unique thread id, they are not going to be override by next test
        //it solves issues with parallel run of mulitple test
    }

    @Override
    public void onTestFailure(ITestResult result){ //result hold all information about test case, also information about the driver
        extentTest.get().fail(result.getThrowable()); // extentTest.get() instead of test to get out particular thread id which had an error

        //getting live of driver from result
        //fields are associated with classes not with methods
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        }
        catch (Exception e1){
            e1.printStackTrace();
        }

        //take a screenshot, Attach it to the report
        //before using surround/catch from ocntext actions, this one from below looked like that:   String filePath = getScreenshot(result.getMethod().getMethodName());
        String filePath = null;
        try {
            filePath = getScreenshot(result.getMethod().getMethodName(), driver);
        } catch (IOException e) {
            e.printStackTrace();
        }

        extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result){
        extentTest.get().log(Status.PASS, "Test passed"); //it's not needed, but for fail we have to add test.fail
    }

    @Override
    public void onFinish(ITestContext context){
        extent.flush();
    }
}
