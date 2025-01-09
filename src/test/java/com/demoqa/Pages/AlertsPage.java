package com.demoqa.Pages;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlertsPage {

	
	WebDriver sdriver;
	public AlertsPage(WebDriver ldriver) {
		this.sdriver=ldriver;
		PageFactory.initElements(ldriver, this);
	}
	
	
	@FindBy(xpath="//button[@id='alertButton']")
	WebElement btn_alertButton;
	
	public void Alters() {
	    // Use WebDriverWait to wait for the alert to be present
	    WebDriverWait wait = new WebDriverWait(sdriver, Duration.ofSeconds(10));

	 // Use JavascriptExecutor to click the button
	    JavascriptExecutor js = (JavascriptExecutor) sdriver;
	    js.executeScript("arguments[0].click();", btn_alertButton); // JavaScript click


	    try {
	        // Wait for the alert and switch to it
	        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

	        // Get alert text and accept it
	        System.out.println("Alert Text: " + alert.getText());
	        alert.accept();
	    } catch (org.openqa.selenium.NoAlertPresentException e) {
	        System.out.println("No alert is present.");
	    }
	}
	@FindBy(xpath="//button[@id='timerAlertButton']")
	WebElement btn_timerAlertButton;
	
	
	public void timerAlertButton() {
		
		JavascriptExecutor js = (JavascriptExecutor) sdriver;
		js.executeScript("arguments[0].click();", btn_timerAlertButton);

	        // Wait for the alert to appear (use WebDriverWait)
	        WebDriverWait wait = new WebDriverWait(sdriver,Duration.ofSeconds(10)); // Maximum wait time: 10 seconds
	        wait.until(ExpectedConditions.alertIsPresent());

	        // Switch to the alert and handle it
	        Alert timerAlert = sdriver.switchTo().alert();
	        System.out.println("Alert Text: " + timerAlert.getText()); // Print alert text

	        // Accept the alert (click "OK")
	        timerAlert.accept();
	        sdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	    }
	
	@FindBy(xpath="//button[@id='confirmButton']")
	WebElement txt_confirmButton;
	
	public void confirmButton(String sAction) {
		JavascriptExecutor js = (JavascriptExecutor) sdriver;
		js.executeScript("arguments[0].click();", txt_confirmButton);
		//.click();
		    
		    // Switch to the alert
		    Alert alert = sdriver.switchTo().alert();
		    
		    // Perform the action based on the parameter
		    if (sAction.equalsIgnoreCase("OK")) {
		        alert.accept(); // Click OK
		        System.out.println("You selected Ok.");
		    } else if (sAction.equalsIgnoreCase("Cancel")) {
		        alert.dismiss(); // Click Cancel
		        System.out.println("You selected Cancel.");
		    } else {
		        System.out.println("Invalid action specified. Use 'OK' or 'Cancel'.");
		    }
		}
	
	
	@FindBy(xpath="//button[@id='promtButton']")
	WebElement btn_promtButton;
	
	@FindBy(xpath="//span[@id='promptResult']")
	WebElement txt_promptResult;
	
	public void promtButton( String inputText,String action) {
		 ((JavascriptExecutor) sdriver).executeScript("arguments[0].scrollIntoView(true);", btn_promtButton);
	    // Click the button to trigger the prompt alert
	    btn_promtButton.click();

	    // Switch to the alert box
	    Alert alert = sdriver.switchTo().alert();

	    // Send text input if provided
	    if (inputText != null && !inputText.isEmpty()) {
        alert.sendKeys(inputText);
	    }

	    // Perform action based on the parameter ("ok" or "cancel")
	    if ("ok".equalsIgnoreCase(action)) {
	        alert.accept(); // Click OK
	    } else if ("cancel".equalsIgnoreCase(action)) {
	        alert.dismiss(); // Click Cancel
	    } else {
	        throw new IllegalArgumentException("Invalid action provided. Use 'ok' or 'cancel'.");
	    }
	    
	    // Wait for the prompt result element to be updated
	    WebDriverWait wait = new WebDriverWait(sdriver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOf(txt_promptResult));

	    // Fetch and print the result text
	    String promptResultText = txt_promptResult.getText();
	    System.out.println("Prompt Result: " + promptResultText);
	    
	    
	    
	}
	
	}

	
