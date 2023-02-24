package TestComponents;

import PageObjectPattern.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    public LandingPage landingPage;
    //we careated object for class landingPage above, so we can use methods from that class - below

    public WebDriver initializeDriver() throws IOException {

        //properties class - thanks to it we can use global data file with properties for all our code
        Properties prop = new Properties();
        //System.getProperty("ser.dir")we can use instead of path on our local device
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//Recources//GlobalData.properities");
        prop.load(fis); //we needed to use fileInputStream because this method load expects that
        String browserName = prop.getProperty("browser");

        if(browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        else if(browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        else if(browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }

    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {
        driver = initializeDriver();
        landingPage = new LandingPage(driver); //sending argument to class - all argument you can catch in constructor in the destination class
        landingPage.goTo();
        return landingPage;
    }

    @AfterMethod
    public void TearDown(){
        driver.close();
    }
}
