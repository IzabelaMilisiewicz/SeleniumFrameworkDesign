package TestComponents;

import Recources.ExtentReporterNg;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestListener;
import org.testng.ITestResult;

//ITestListener - interface provided by TestNG, giving methods for example onTestStart, onTestSuccess ect

public class Listeners implements ITestListener {

    ExtentReports extent = ExtentReporterNg.getReportObject();
    ExtentTest test;

    @Override
    public void onTestStart(ITestResult result){ //result variable holds all information about our test
        test = extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result){
        test.fail(result.getThrowable());

        //screenshot, Attach it to the report
    }

    @Override
    public void onTestSuccess(ITestResult result){
        test.log(Status.PASS, "Test passed"); //it's not needed, but for fail we have to add test.fail
    }
}
