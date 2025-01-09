package com.demoqa.Pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ClickOptionInBookStore {

	
	  public WebDriver sdriver;
	
	
	public ClickOptionInBookStore(WebDriver ldriver) {
		this.sdriver=ldriver;
		PageFactory.initElements(sdriver, this);
		
		}
	
	
	@FindBy(xpath="//button[@id='gotoStore']")
	WebElement gotoStore;
	
	@FindBy(xpath="//input[@id='searchBox']")
	WebElement txt_searchBox;
	
	@FindBy(xpath="//div[@class='action-buttons']//span//a")
	WebElement txt_Bookname;
	
	@FindBy(xpath="//button[@id='submit']")
	WebElement btn_LogOut;
	

public String  SelectBook(String sBookName) {
	 WebDriverWait wait = new WebDriverWait(sdriver, Duration.ofSeconds(10));

     // Wait until userName field is visible and interactable, then fill it
     wait.until(ExpectedConditions.visibilityOf(gotoStore));
	
	JavascriptExecutor js = (JavascriptExecutor) sdriver;
    js.executeScript("arguments[0].scrollIntoView(true);", gotoStore);
	
	gotoStore.click();
	 wait.until(ExpectedConditions.visibilityOf(txt_searchBox));
	 //JavascriptExecutor js = (JavascriptExecutor) driver;
	 js.executeScript("arguments[0].click();", txt_searchBox);
	 
	 
	//txt_searchBox.click();
	
	txt_searchBox.sendKeys(sBookName);
	
	String sBook=txt_Bookname.getText();
	btn_LogOut.click();
	
	return sBook;
	
	
}
	
}