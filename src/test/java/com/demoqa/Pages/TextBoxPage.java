package com.demoqa.Pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class TextBoxPage {

    public WebDriver sdriver;

    public TextBoxPage(WebDriver ldriver) {
        this.sdriver = ldriver;
        PageFactory.initElements(ldriver, this);
    }

    @FindBy(xpath = "//input[@id='userName']")
    WebElement txt_userName;

    @FindBy(xpath = "//input[@id='userEmail']")
    WebElement txt_userEmail;

    @FindBy(xpath = "//textarea[@id='currentAddress']")
    WebElement txt_currentAddress;

    @FindBy(xpath = "//textarea[@id='permanentAddress']")
    WebElement txt_permanentAddress;

    @FindBy(xpath = "//button[@id='submit']")
    WebElement btn_submit;

   

    // Method to fill the details and click the submit button
    public void details(String sUserName, String sUserEmail, String sCurrentAddress, String sPermanentAddress) {
        WebDriverWait wait = new WebDriverWait(sdriver, Duration.ofSeconds(50));

        // Wait until userName field is visible and interactable, then fill it
        wait.until(ExpectedConditions.visibilityOf(txt_userName));
        txt_userName.sendKeys(sUserName);

        // Wait until userEmail field is visible and interactable, then fill it
        wait.until(ExpectedConditions.visibilityOf(txt_userEmail));
        txt_userEmail.sendKeys(sUserEmail);

        // Wait until currentAddress field is visible and interactable, then fill it
        wait.until(ExpectedConditions.visibilityOf(txt_currentAddress));
        txt_currentAddress.sendKeys(sCurrentAddress);

        // Wait until permanentAddress field is visible and interactable, then fill it
        wait.until(ExpectedConditions.visibilityOf(txt_permanentAddress));
        txt_permanentAddress.sendKeys(sPermanentAddress);

        // Scroll to the submit button before clicking it
        ((JavascriptExecutor) sdriver).executeScript("arguments[0].scrollIntoView(true);", btn_submit);

        // Wait until the submit button is clickable, then click it
        wait.until(ExpectedConditions.elementToBeClickable(btn_submit));
        btn_submit.click();
    }
//========================================================================================================
    
    @FindBy(xpath = "//p[@id='name']")
    WebElement txt_Name;
    
    @FindBy(xpath="//p[@id='email']")
    WebElement txt_Email;
   
    @FindBy(xpath="//p[@id='currentAddress']")
    WebElement txt_CurrentAddress;
    
    @FindBy(xpath="//p[@id='permanentAddress']")
    WebElement txt_PermanentAddress;
    
    
    
    
    // Verification method
    public void verification(String expectedUserName, String expectedUserEmail, String CurrentAddress, String PermanentAddress) {
    	
        WebDriverWait wait = new WebDriverWait(sdriver, Duration.ofSeconds(10));
        
        wait.until(ExpectedConditions.visibilityOf(txt_Name));
        String sNameText = txt_Name.getText();
        // Extract the name part after "Name:"
        String sExtractedName = sNameText.split(":")[1].trim();
        System.out.println("The Selected Name is:-"+sExtractedName);
        // Assert to verify the extracted name matches the expected value
        Assert.assertEquals(sExtractedName, expectedUserName,"The" +sExtractedName +" name and "+expectedUserName +"not same");
        
        
        
        wait.until(ExpectedConditions.visibilityOf(txt_Email));
        String sEmailText = txt_Email.getText();
        // Extract the name part after "Name:"
        String sExtractedEmail = sEmailText.split(":")[1].trim();
        System.out.println("The Selected Email is:-"+sExtractedEmail);
        // Assert to verify the extracted name matches the expected value
        Assert.assertEquals(sExtractedEmail, expectedUserEmail,"The" +sExtractedEmail +" Email and "+expectedUserEmail +"not same");
       
        
        
        wait.until(ExpectedConditions.visibilityOf(txt_CurrentAddress));
        String sCurrentAddressText = txt_CurrentAddress.getText();
        // Extract the name part after "Name:"
        String sExtractedCurrentAddress = sCurrentAddressText.split(":")[1].trim();
        System.out.println("The Selected CurrentAddress is:-"+sExtractedCurrentAddress);
        // Assert to verify the extracted name matches the expected value
        Assert.assertEquals(sExtractedCurrentAddress, CurrentAddress,"The" +sExtractedCurrentAddress +" CurrentAddress and "+CurrentAddress +"not same"); 
        
        
        wait.until(ExpectedConditions.visibilityOf(txt_PermanentAddress));
        String sPermanentAddressText = txt_PermanentAddress.getText();
        // Extract the name part after "Name:"
        String sExtractedPermanentAddress = sPermanentAddressText.split(":")[1].trim();
        System.out.println("The Selected PermanentAddress  is:-"+sExtractedPermanentAddress);
        // Assert to verify the extracted name matches the expected value
        Assert.assertEquals(sExtractedPermanentAddress.trim(), PermanentAddress.trim(),"The" +PermanentAddress +" PermanentAddress and "+sExtractedPermanentAddress +"not same"); 
        
        
        
        
        
    }
}
