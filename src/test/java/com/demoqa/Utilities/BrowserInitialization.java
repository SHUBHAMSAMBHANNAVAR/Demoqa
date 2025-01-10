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
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import com.matryxsoft.reports.ReportGenerator;

public class BrowserInitialization {
    public static WebDriver driver;
    public static Properties prop;
    public ReportGenerator reportGenerator = new ReportGenerator();

    public BrowserInitialization() {
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream("./Configuration/Config_properties");
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeClass
    public static void initialization() {
        String browserName = prop.getProperty("browserName");

        try {
            if (browserName.equalsIgnoreCase("chrome")) {
                // Setup ChromeDriver using WebDriverManager
                WebDriverManager.chromedriver().setup();
                
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-gpu");
                options.addArguments("--disable-extensions");
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--remote-allow-origins=*");
                
                driver = new ChromeDriver(options);
                driver.manage().window().setSize(new Dimension(1920, 950));
                
            } else if (browserName.equalsIgnoreCase("FF")) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            } else {
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
            }

            // Maximize window for non-headless mode
            if (driver != null && 
                !(driver instanceof ChromeDriver && 
                  ((ChromeDriver) driver).getCapabilities().toString().contains("headless"))) {
                driver.manage().window().maximize();
            }

            // Navigate to the URL from properties file
            String url = prop.getProperty("url");
            if (url != null && !url.isEmpty()) {
                driver.get(url);
            } else {
                throw new IllegalStateException("URL not specified in properties file");
            }
            
        } catch (Exception e) {
            System.out.println("Error initializing driver: " + e.getMessage());
            throw e;
        }
    }

    @BeforeMethod
    public void eventListener(Method method) throws IOException {
        driver = reportGenerator.eventListener(driver);
    }
    
    @AfterMethod
    public void generateReport(ITestResult result) throws java.text.ParseException {
        reportGenerator.genrateReport(result, driver);
    }
    
    @AfterClass
    public static void quitDriver() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.out.println("Error while quitting driver: " + e.getMessage());
            } finally {
                driver = null;
            }
        }
    }
}
