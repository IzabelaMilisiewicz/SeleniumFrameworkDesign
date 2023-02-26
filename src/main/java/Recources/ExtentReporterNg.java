package Recources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNg {

    public static ExtentReports getReportObject(){ //thanks to static we can access method without creating object of this class, I can acces it directly with class name.method

    String destinationPathForReports = System.getProperty("user.dir") + "\\reports\\index.html";
    ExtentSparkReporter reporter = new ExtentSparkReporter(destinationPathForReports); //this is a class which help us to configure how we would like our report to look like
        reporter.config().setReportName("Web Automation Results (.config().setReportName)");
        reporter.config().setDocumentTitle("Test Results (.config().setDocumentTitle)");

    ExtentReports extent = new ExtentReports(); //this is our main class, it will drive all our report execution
    //with ExtentSparkReporter we created our report and now we have to attach it to our main class
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Iza (.setSystemInfo)");
        return extent;
}
}