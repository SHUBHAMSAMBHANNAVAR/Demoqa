package com.demoqa.Pages;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserWindows {
	 WebDriver sdriver;
	    WebDriverWait wait;

	    public BrowserWindows(WebDriver ldriver) {
	        this.sdriver = ldriver;
	        PageFactory.initElements(ldriver, this);
	        wait = new WebDriverWait(sdriver, Duration.ofSeconds(10)); // Declare WebDriverWait
	        
	          }
	    
	    public void  NewTab() {
	    	   try {

	               // Store the parent window handle
	               String parentWindow = sdriver.getWindowHandle();
	               System.out.println("Parent Window Title: " + sdriver.getTitle());

	               // Click on "New Tab" button
	               WebElement newTabButton = sdriver.findElement(By.id("tabButton"));
	               newTabButton.click();

	               // Switch to new tab
	               Set<String> allHandles = sdriver.getWindowHandles();
	               for (String handle : allHandles) {
	                   if (!handle.equals(parentWindow)) {
	                       sdriver.switchTo().window(handle);
	                       System.out.println("New Tab Title: " + sdriver.getTitle());
	                       sdriver.close(); // Close new tab
	                   }
	               }

	               // Switch back to parent window
	               sdriver.switchTo().window(parentWindow);
	               System.out.println("Parent Window Title after New Tab: " + sdriver.getTitle());

	           } catch (Exception e) {
	               e.printStackTrace();
	           
	       }
	   }
	    public void NewWindow() {
	    	try {
	            
	            // Store the parent window handle
	            String parentWindow = sdriver.getWindowHandle();
	            System.out.println("Parent Window Title: " + sdriver.getTitle());

	            // Click on "New Window" button
	            WebElement newWindowButton = sdriver.findElement(By.id("windowButton"));
	            newWindowButton.click();

	            // Switch to new window
	            Set<String> allHandles = sdriver.getWindowHandles();
	            for (String handle : allHandles) {
	                if (!handle.equals(parentWindow)) {
	                    sdriver.switchTo().window(handle);
	                    System.out.println("New Window Title: " + sdriver.getTitle());
	                    sdriver.close(); // Close new window
	                }
	            }

	            // Switch back to parent window
	            sdriver.switchTo().window(parentWindow);
	            System.out.println("Parent Window Title after New Window: " + sdriver.getTitle());

	        } catch (Exception e) {
	            e.printStackTrace();
	        
	        }
	    }
	    public void NewWindowMessage() {
	    	 try {
	    	        WebElement newMessageWindowButton = sdriver.findElement(By.id("messageWindowButton"));
	    	        System.out.println("Found button"); // Debug print
	    	        newMessageWindowButton.click();
	    	        System.out.println("Clicked button"); // Debug print
	    	        
	    	        String parentWindow = sdriver.getWindowHandle();
	    	        Set<String> allWindows = sdriver.getWindowHandles();
	    	        System.out.println("Number of windows: " + allWindows.size()); // Debug print
	    	        
	    	        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
	    	        
	    	        for (String window : allWindows) {
	    	            if (!window.equals(parentWindow)) {
	    	                sdriver.switchTo().window(window);
	    	                System.out.println("Switched to new window"); // Debug print
	    	                
	    	                WebElement messageElement = wait.until(
	    	                    ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
	    	                System.out.println("Found body element"); // Debug print
	    	                
	    	                String messageText = messageElement.getText();
	    	                System.out.println("Message length: " + messageText.length()); // Debug print
	    	                System.out.println("New Window Message Content: " + messageText);
	    	                sdriver.close();
	    	                break;
	    	            }
	    	        }
	    	        sdriver.switchTo().window(parentWindow);
	    	        System.out.println("Back to Parent Window Title: " + sdriver.getTitle());
	    	    } catch (Exception e) {
	    	        System.out.println("Error occurred: " + e.getMessage()); // Better error handling
	    	        e.printStackTrace();
	    	    }
	    	}}