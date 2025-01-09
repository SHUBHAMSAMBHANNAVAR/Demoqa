package com.demoqa.Pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

public class Checkbox {
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Constructor
    public Checkbox(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    
    // Page Elements
    @FindBy(xpath = "//span[text()='Home']")
    WebElement homeNode;
    
    @FindBy(xpath = "//button[@title='Toggle']")
    WebElement homeToggle;
    
    // Improved method to expand Home first
    public void expandHome() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(homeToggle));
            if(!homeToggle.getAttribute("class").contains("expanded")) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", homeToggle);
               Thread.sleep(1000); // Wait for animation
            }
        } catch (Exception e) {
            System.out.println("Error expanding Home: " + e.getMessage());
        }
    }
    
    // Method to click specific node
    public void clickNode(String nodeName) {
        try {
            // First expand Home if not already expanded
         //   expandHome();
            
            // Wait for node to be visible and clickable
            String nodeXpath = String.format("//span[text()='%s']", nodeName);
            WebElement node = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(nodeXpath)
            ));
            

            
            // If node has toggle button, click it
            String toggleXpath = String.format("//span[text()='%s']/parent::label/preceding-sibling::button", nodeName);
            WebElement toggleButton = driver.findElement(By.xpath(toggleXpath));
            if(toggleButton.isDisplayed()) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", toggleButton);
                Thread.sleep(500);
            }
        } catch (Exception e) {
            System.out.println("Error clicking node " + nodeName + ": " + e.getMessage());
        }
    }
    
    // Method to select checkbox
    public void selectCheckbox(String itemText) {
        try {
            // First expand Home
           // expandHome();
            
            // Locate and click the checkbox label
            String checkboxXpath = String.format("//span[text()='%s']/parent::label", itemText);
            WebElement checkboxLabel = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(checkboxXpath)
            ));
            
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkboxLabel);
            Thread.sleep(500);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkboxLabel);
            
        } catch (Exception e) {
            System.out.println("Error selecting checkbox " + itemText + ": " + e.getMessage());
        }
    }
    
 @FindBy(xpath="//span[@class='text-success'][1]")
 WebElement txt_value;
    
    public String verification() {
    	String sName=txt_value.getText();
    	System.out.println("The Selected check box value is :-" + sName);
    	return sName;
    }
}