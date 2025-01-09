package com.demoqa.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class ResizablePage {
	 public WebDriver sdriver;

	    // Constructor to initialize the WebDriver
	    public ResizablePage(WebDriver ldriver) {
	        this.sdriver = ldriver;
	        PageFactory.initElements(sdriver, this);
	    }

	    public void resizable(int x, int y) {
	        // Locate the resizable element and its handle
	        WebElement resizableElement = sdriver.findElement(By.id("resizableBoxWithRestriction")); // Update the ID if necessary
	        WebElement resizeHandle = resizableElement.findElement(By.cssSelector(".react-resizable-handle.react-resizable-handle-se")); // Corrected CSS selector

	        // Scroll the element into view (optional if necessary)
	        ((JavascriptExecutor) sdriver).executeScript("arguments[0].scrollIntoView(true);", resizeHandle);

	        // Perform the resize action
	        Actions actions = new Actions(sdriver);
	        actions.clickAndHold(resizeHandle)
	               .moveByOffset(x, y) // Adjust the offsets for desired resize size
	               .release()
	               .perform();
	       
	        System.out.println("Element resized successfully.");
	    }

}
	    
	    
	    
	    

