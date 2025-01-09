package com.demoqa.Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Buttons {
    private WebDriver driver;
    private JavascriptExecutor jsExecutor;

    // Constructor to initialize WebDriver and JavascriptExecutor
    public Buttons(WebDriver driver) {
        this.driver = driver;
        this.jsExecutor = (JavascriptExecutor) driver;
    }

    /**
     * Perform the specified action on a button based on the action type.
     * 
     * @param actionType The type of action to perform: "doubleClick", "rightClick", or "click"
     */
    public void performAction(String actionType) {
        String xpath = "";

        // Determine the XPath and action to perform based on the action type
        switch (actionType.toLowerCase()) {
            case "doubleclick":
                xpath = "//button[@id='doubleClickBtn']";
                break;

            case "rightclick":
                xpath = "//button[@id='rightClickBtn']";
                break;

            case "click":
                xpath = "//button[text()='Click Me']";
                break;

            default:
                System.out.println("Invalid action type specified. Please use 'doubleClick', 'rightClick', or 'click'.");
                return;
        }

        // Locate the button element
        WebElement button = driver.findElement(By.xpath(xpath));

        // Perform the appropriate action based on actionType using JavaScript
        if (actionType.equalsIgnoreCase("doubleclick")) {
            jsExecutor.executeScript("var event = new MouseEvent('dblclick', {bubbles: true}); arguments[0].dispatchEvent(event);", button);
            System.out.println("JavaScript double-click performed on the button.");
        } else if (actionType.equalsIgnoreCase("rightclick")) {
            jsExecutor.executeScript("var event = new MouseEvent('contextmenu', {bubbles: true}); arguments[0].dispatchEvent(event);", button);
            System.out.println("JavaScript right-click performed on the button.");
        } else if (actionType.equalsIgnoreCase("click")) {
            jsExecutor.executeScript("arguments[0].click();", button);
            System.out.println("JavaScript single click performed on the button.");
        }
    }
    
 @FindBy(xpath="//p[contains(@id, 'ClickMessage')]")
 WebElement txt_Clickmessage;
		 
 public String verificationactionbuttons() {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("rightClickMessage")));
	        
	        // Fetch the text
	        String sMessage = element.getText();
	        System.out.println("Message: " + sMessage);
	        return sMessage;
	    } catch (Exception e) {
	        System.out.println("Error: Element not found or not visible.");
	        e.printStackTrace();
	        return null;
	    }}
	}