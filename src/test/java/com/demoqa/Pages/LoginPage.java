package com.demoqa.Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	public WebDriver sdriver;
	
	public LoginPage(WebDriver ldriver) {
		this.sdriver=ldriver;
		PageFactory.initElements(ldriver, this);
	}
	
	   
	 @FindBy(xpath = "//input[@id='userName']")
	    WebElement txt_usrname;
	
	 @FindBy(xpath="//input[@id='password']")
	 WebElement txt_password;
	 
	 @FindBy(xpath="//button[@id='login']")
	 WebElement btn_NewUser;
	
	
	
	public String login(String sUsername, String sPassword) {
		
		txt_usrname.click();
		txt_usrname.sendKeys(sUsername);
		JavascriptExecutor js = (JavascriptExecutor) sdriver;
        js.executeScript("arguments[0].scrollIntoView(true);", txt_password);
		txt_password.click();
		txt_password.sendKeys(sPassword);
		String sPas= txt_password.getAttribute("value");
		btn_NewUser.click();
		return sPas;
		
	}
}
