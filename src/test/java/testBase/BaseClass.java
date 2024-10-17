package testBase;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

public class BaseClass {

    public static WebDriver driver;
    public Logger logger;  // Log4j
    public Properties properties;         // parameterization

    @BeforeClass(groups = {"sanity", "regression", "master"})
    @Parameters({"browser", "os"})
    public void setUp(String browser, @Optional("windows") String os) throws IOException {
        // print logs
        logger = LogManager.getLogger(this.getClass());

        //Reading properties file
        properties = new Properties();
        FileReader fileReader = new FileReader("./src//test//resources//config.properties");
        properties.load(fileReader);


        if (properties.getProperty("execution_env").equalsIgnoreCase("remote")) {

            DesiredCapabilities capabilities = new DesiredCapabilities();
            if (os.equalsIgnoreCase("window")) {
                capabilities.setPlatform(Platform.WIN10);
            } else if (os.equalsIgnoreCase("mac")) {
                capabilities.setPlatform(Platform.MAC);

            } else {
                System.out.println("No matching Operating System");
            }

            switch (browser.toLowerCase()) {
                case "chrome":
                    capabilities.setBrowserName("chrome");
                    break;
                case "firefox":
                    capabilities.setBrowserName("firefox");
                    break;
                case "edge":
                    capabilities.setBrowserName("MicrosoftEdge");
                    break;
                default:
                    System.out.println("No matching browser name :");
                    return;
            }

            driver = new RemoteWebDriver(new URL(" http://192.168.0.207:4444/wd/hub"), capabilities);
        } else { // local
            // Parallel Testing
            switch (browser.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                default:
                    System.out.println("Invalid browser name :");
                    return;
            }
        }


        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        // Reading url from properties file
        // driver.get("https://tutorialsninja.com/demo/index.php?route=account/register");
        driver.get(properties.getProperty("appUrl"));
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        driver.quit() ;
    }


    public String randomString() {
        String randomStr = RandomStringUtils.randomAlphabetic(5);
        return randomStr;
    }

    public String captureTheScreenShot(String testName) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

        // Capture the screenshot
        TakesScreenshot ts = (TakesScreenshot) driver;
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);

        // Specify the target file path with the correct extension (.png)
        String targetFilepath = System.getProperty("user.dir") + "\\screenshots\\" + testName + "_" + timeStamp + ".jpeg";
        System.out.println("FilePath : " + targetFilepath);

        File targetFile = new File(targetFilepath);
        // Use FileUtils to copy the file
//        FileUtils.copyFile(sourceFile, targetFile);
        // Use FileUtils to copy the file
        sourceFile.renameTo(targetFile);
        return targetFilepath; // Return the absolute path instead of the relative path
    }

}
