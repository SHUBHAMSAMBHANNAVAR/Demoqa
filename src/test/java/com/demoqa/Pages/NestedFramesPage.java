package com.demoqa.Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NestedFramesPage {
	  WebDriver sdriver;
	    WebDriverWait wait;

	    public NestedFramesPage(WebDriver ldriver) {
	        this.sdriver = ldriver;
	        PageFactory.initElements(ldriver, this);
	        wait = new WebDriverWait(sdriver, Duration.ofSeconds(10)); // Declare WebDriverWait
	    }
	    
	    public void nestedFrames() {
	    	sdriver.switchTo().frame("frame1").switchTo().frame(0);
	    	String dispalyText=sdriver.findElement(By.tagName("p")).getText();
	    	System.out.println("Child iFrame text:"+ dispalyText);
	    	sdriver.switchTo().parentFrame();
	    	System.out.println("Parent iFrame source:" + sdriver.getPageSource());
	    	
	    	sdriver.switchTo().defaultContent();
	    	//System.out.println("Main Doc:"+ sdriver.getPageSource());
	    	
	    	
	    }
}
