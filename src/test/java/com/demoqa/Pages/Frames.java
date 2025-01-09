package com.demoqa.Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Frames {

    WebDriver sdriver;
    WebDriverWait wait;

    public Frames(WebDriver ldriver) {
        this.sdriver = ldriver;
        PageFactory.initElements(ldriver, this);
        wait = new WebDriverWait(sdriver, Duration.ofSeconds(10)); // Declare WebDriverWait
    }

    public void iframe1() {
        try {
            // Switch to iframe1 and perform operations
            sdriver.switchTo().frame("frame1"); // Correct iframe ID
            WebElement heading1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sampleHeading")));
            System.out.println("Iframe1 Heading Text: " + heading1.getText());
            sdriver.switchTo().defaultContent(); // Switch back to main content
        } catch (NoSuchSessionException e) {
            System.out.println("Session expired, reinitializing WebDriver...");
            reinitializeDriver(); // Method to reinitialize WebDriver
        } catch (Exception e) {
            System.out.println("Exception occurred in iframe1: " + e.getMessage());
        }
    }

    public void iframe2() {
        try {
            // Switch to iframe2 and perform operations
            sdriver.switchTo().frame("frame2"); // Assuming iframe ID is "frame2"
            WebElement heading2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sampleHeading")));
            System.out.println("Iframe2 Heading Text: " + heading2.getText());
            sdriver.switchTo().defaultContent(); // Switch back to main content
        } catch (NoSuchSessionException e) {
            System.out.println("Session expired, reinitializing WebDriver...");
            reinitializeDriver(); // Method to reinitialize WebDriver
        } catch (Exception e) {
            System.out.println("Exception occurred in iframe2: " + e.getMessage());
        }
    }

    public void reinitializeDriver() {
        // Reinitializing driver logic (example)
        sdriver.quit(); // Clean up old session
        System.out.println("Reinitializing WebDriver...");
        sdriver = new org.openqa.selenium.chrome.ChromeDriver(); // Replace with proper setup
        sdriver.get("https://demoqa.com/frames"); // Replace with correct URL
    }
}
