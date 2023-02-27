package TestComponents;

import PageObjectPattern.LandingPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
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

    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

        //read json to string
        String jsonContent = FileUtils.readFileToString(new File(filePath),
                StandardCharsets.UTF_8);
        //String to hashmap - Jacson Databind is a dependency which helps to convert string content into Hash Map
        ObjectMapper mapper = new ObjectMapper();
        //we created an object of class ObjectMapper because we wanted to usee method .readValue()
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
        return data; //we have list if hashmaps
    }

    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
        FileUtils.copyFile(source, file);
        return System.getProperty("user.dir")+"//reports//"+testCaseName+".png"; //here we will get path where our screenshot is stored
    }

    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {
        driver = initializeDriver();
        landingPage = new LandingPage(driver); //sending argument to class - all argument you can catch in constructor in the destination class
        landingPage.goTo();
        return landingPage;
    }

    @AfterMethod(alwaysRun = true)
    public void TearDown(){
        driver.close();
    }
}
