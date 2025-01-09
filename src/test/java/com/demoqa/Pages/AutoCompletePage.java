package com.demoqa.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class AutoCompletePage {
    WebDriver driver;

    public AutoCompletePage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to handle multiple color names
    public void typeMultipleColors(List<String> colors) {
        WebElement autoCompleteInput = driver.findElement(By.id("autoCompleteMultipleInput")); // Replace with actual locator
        
        for (String color : colors) {
            autoCompleteInput.sendKeys(color); // Type the color name
            autoCompleteInput.sendKeys(Keys.ENTER); // Press Enter to select the suggestion
            System.out.println("Typed and selected color: " + color);
        }}
    
        // Method to type and select a single color
        public void typeSingleColor(String color) {
            WebElement autoCompleteInput = driver.findElement(By.id("autoCompleteSingleInput")); // Replace with actual locator
            
            autoCompleteInput.sendKeys(color); // Type the color name
            autoCompleteInput.sendKeys(Keys.ARROW_DOWN); // Navigate to the suggestion
            autoCompleteInput.sendKeys(Keys.ENTER); // Select the suggestion
            System.out.println("Typed and selected single color: " + color);
        
    }
}
