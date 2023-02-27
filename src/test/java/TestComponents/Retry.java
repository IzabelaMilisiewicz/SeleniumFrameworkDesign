package TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

    //it will rerun filed test to check if it really generates an error

    int count = 0;
    int maxTry = 1;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if(count<maxTry){
            count++;
            return true;
        }
        return false;
    }
}
