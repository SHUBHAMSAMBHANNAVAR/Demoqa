package com.demoqa.Pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DynamicProperties {
	
	public WebDriver sdriver;
	
	public DynamicProperties(WebDriver ldriver) {
	this.sdriver=ldriver;
	PageFactory.initElements(ldriver, this);
	}
	
	@FindBy(xpath="//h1[@class='text-center']/parent::div//child::p")
	WebElement txt_Dynamic;
	
	@FindBy(xpath="//button[@id='visibleAfter']")
	WebElement txt_visibleAfter;
	public void DynamicElemenet() {
		WebDriverWait wait= new WebDriverWait(sdriver, Duration.ofSeconds(20));
		
		String sText=txt_Dynamic.getText();
		System.out.println(sText);
		 String randomId = txt_Dynamic.getAttribute("id");

	        // Print the value
	        System.out.println("Random ID value: " + randomId);
	        
	     // Set the implicit wait for the WebDriver instance
	        sdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

	        wait.until(ExpectedConditions.visibilityOf(txt_visibleAfter));
	        
	       String sVisible= txt_visibleAfter.getText();
	       System.out.println(sVisible);
	       
	        
	}
}