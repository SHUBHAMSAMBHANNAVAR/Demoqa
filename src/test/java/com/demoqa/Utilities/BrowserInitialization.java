package com.demoqa.Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.matryxsoft.reports.ReportGenerator;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

/**
 * Utility class for initializing and managing the WebDriver for different browsers.
 * This class reads configuration properties (like browser type and URL) from a properties file,
 * sets up the appropriate WebDriver, and provides methods to launch and quit the browser.
 */
public class BrowserInitialization {

    // Static WebDriver instance to be used throughout the test execution
    public static WebDriver driver;

    // Static Properties object to load configuration values from the properties file
    public static Properties prop;
    public ReportGenerator reportGenerator = new ReportGenerator();
    /**
     * Constructor for BrowserInitialization.
     * This loads the properties file that contains configuration details
     * like the browser type (chrome, FF, edge) and the base URL for the application.
     */
    public BrowserInitialization() {
        try {
            // Initialize the Properties object and load the config file
            prop = new Properties();
            // Load the properties file from the specified path
            FileInputStream ip = new FileInputStream("./Configuration/Config_properties");
            prop.load(ip);
        } catch (FileNotFoundException e) {
            // Handle case when properties file is not found
            e.printStackTrace();
        } catch (IOException e) {
            // Handle I/O exception when reading the properties file
            e.printStackTrace();
        }
    }

    /**
     * Initializes the WebDriver based on the browser type specified in the config file.
     * It launches the respective browser (Chrome, Firefox) and maximizes the window.
     * Then it navigates to the specified URL from the properties file.
     */
    @BeforeClass
    public static void initialization() {
        // Fetch the browser name from the properties file
        String browserName = prop.getProperty("browserName");

        // Initialize the appropriate WebDriver based on the browser type
        if (browserName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe"); // Specify ChromeDriver path
            ChromeOptions BrowserForTesting = new ChromeOptions();
            BrowserForTesting.setBinary("./Browser/chrome.exe");

            // Add headless mode and other options for Chrome
          //  BrowserForTesting.addArguments("--headless");
            

            BrowserForTesting.addArguments("--disable-gpu"); // Optional: To avoid GPU-related errors
            BrowserForTesting.addArguments("--disable-extensions");
            BrowserForTesting.addArguments("--window-size=1920,1080"); // Set resolution for headless mode

            driver = new ChromeDriver(BrowserForTesting); // Create WebDriver instance with options
            driver.manage().window().setSize(new Dimension(1920, 950));
        } else if (browserName.equalsIgnoreCase("FF")) {
            // Set up WebDriver for Firefox
            System.setProperty("webdriver.gecko.driver", "./Drivers/geckodriver.exe"); // Specify GeckoDriver path
            driver = new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }

        // Maximize the browser window if not in headless mode
        if (!(driver instanceof ChromeDriver && ((ChromeDriver) driver).getCapabilities().toString().contains("headless"))) {
            driver.manage().window().maximize();
            driver.manage().window().setSize(new Dimension(1920, 950));
        }

        // Navigate to the URL specified in the properties file
        driver.get(prop.getProperty("url"));
        driver.manage().window().setSize(new Dimension(1920, 950));
    }
    @BeforeMethod
    public void EvenListner(Method method) throws IOException {
	  driver = reportGenerator.eventListener(driver);
    }
    
    @AfterMethod
	  public void genrateReport(ITestResult result) throws java.text.ParseException { 
	     reportGenerator.genrateReport(result, driver);  // Generate report safely
	  }
    
    /**
     * Closes the WebDriver and quits the browser session after test execution.
//     */
    @AfterClass
    public static void quitDriver() {
        // Close the current browser window
        if (driver != null) {
            driver.quit();
        }
    }
}
