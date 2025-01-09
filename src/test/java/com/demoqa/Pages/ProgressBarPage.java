package com.demoqa.Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProgressBarPage {
    public WebDriver sdriver;
    
    public ProgressBarPage(WebDriver ldriver) {
        this.sdriver = ldriver;
        PageFactory.initElements(ldriver, this);
    }
    
    public String progressbar(String sBarValue) {
        // Locate the start button and click it
        WebElement startButton = sdriver.findElement(By.xpath("//button[@id='startStopButton']"));
     // Execute JavaScript to click the element
        JavascriptExecutor jsExecutor = (JavascriptExecutor) sdriver;
        jsExecutor.executeScript("arguments[0].click();", startButton);
        
        // Wait for the progress bar to reach a value (e.g., 73%)
        WebDriverWait wait = new WebDriverWait(sdriver, Duration.ofSeconds(10)); // 10 seconds timeout
        
        // Locate the progress bar element
        WebElement progressBar = sdriver.findElement(By.xpath("//div[@role='progressbar']"));
        
        // Check the progress bar value and stop it when it reaches the desired value (e.g., 73%)
        while (true) {
            // Get the current value of the progress bar
            String progressValue = progressBar.getAttribute("aria-valuenow");
           // System.out.println("Current progress value: " + progressValue);
            
            // Check if the value matches the target (e.g., 73)
            if (progressValue.equals(sBarValue)) {
                // Click the stop button to stop the progress bar
                startButton.click();
                System.out.println("Progress bar stopped at: " + progressValue);
                return progressValue; // Return the progress value for assertion
            }
        }
    }
}
