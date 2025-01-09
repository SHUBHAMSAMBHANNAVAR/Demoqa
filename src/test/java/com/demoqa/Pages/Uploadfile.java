package com.demoqa.Pages;

import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;

public class Uploadfile {
    public WebDriver sdriver;

    // Constructor to initialize the WebDriver and PageFactory elements
    public Uploadfile(WebDriver ldriver) {
        this.sdriver = ldriver;
        PageFactory.initElements(ldriver, this);
    }

    @FindBy(xpath = "//input[@id='uploadFile']")
    WebElement btn_upload;

    // Method to click the upload button and perform actions
    public void uploadFile(String sFilepath) throws InterruptedException, AWTException {
    	
        // Wait for the element to be clickable
        WebDriverWait wait = new WebDriverWait(sdriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(btn_upload));

        // Use JavaScriptExecutor to click the element
        JavascriptExecutor js = (JavascriptExecutor) sdriver;
        js.executeScript("arguments[0].click();", btn_upload);
      
        Robot rb =new Robot();
        rb.delay(2000);
        
        StringSelection ss=new  StringSelection(sFilepath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
        
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);
                
        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyRelease(KeyEvent.VK_V);
                
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);

    }
}
