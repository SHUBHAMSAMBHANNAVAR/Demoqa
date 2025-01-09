package com.demoqa.Pages;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Links {
    public WebDriver sdriver;

    public Links(WebDriver ldriver) {
        this.sdriver = ldriver;
        PageFactory.initElements(ldriver, this);
    }

    public void handleLink(String linkName) {
        String linkXpath = "";

        // Determine XPath based on the link name
        if (linkName.equalsIgnoreCase("Home")) {
            linkXpath = "//a[@id='simpleLink' and text()='Home']";
        } else if (linkName.equalsIgnoreCase("DynamicHome")) {
            linkXpath = "//a[starts-with(text(), 'Home') and @id='dynamicLink']";
        } else {
            System.out.println("Invalid link name.");
            return;
        }

        try {
            // Wait for the element to be clickable
            WebDriverWait wait = new WebDriverWait(sdriver, Duration.ofSeconds(10));
            WebElement link = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(linkXpath)));
            link.click();

            // Handle new tab logic
            String originalTab = sdriver.getWindowHandle();
            Set<String> windowHandles = sdriver.getWindowHandles();

            for (String handle : windowHandles) {
                if (!handle.equals(originalTab)) {
                    sdriver.switchTo().window(handle);
                    break;
                }
            }

            // Print the new tab title and close it
            //System.out.println("New tab title: " + sdriver.getTitle());
            sdriver.close();

            // Switch back to the original tab
            sdriver.switchTo().window(originalTab);
            System.out.println("Back to original tab title: " + sdriver.getTitle());
        } catch (Exception e) {
            System.out.println("Error handling the link: " + e.getMessage());
            
            
        }
        
        
    }
    public void clickLinkByText(String linkText) {
        try {
            // Create a dynamic XPath based on the link text
            String dynamicXpath = "//a[text()='" + linkText + "']";

            // Wait for the element to be clickable
            WebDriverWait wait = new WebDriverWait(sdriver, Duration.ofSeconds(30));
            WebElement link = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dynamicXpath)));

            // Click the link
            link.click();
            System.out.println("Clicked the link with text: " + linkText);
        } catch (Exception e) {
            System.out.println("Error: Unable to find or click the link with text: " + linkText);
            e.printStackTrace();
        }
    }
    
    @FindBy(xpath="//p[@id='linkResponse']")
    WebElement Apicallmsg;
    public void gettheApicallmsg() {
    	
    	// Using WebDriverWait to ensure the element is present
    	WebDriverWait wait = new WebDriverWait(sdriver, Duration.ofSeconds(10));
    	WebElement Apicallmsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='linkResponse']")));

    	// Fetching the text from the element after it's visible
    	String sApicallmsg = Apicallmsg.getText();
    	System.out.println(sApicallmsg);
    }}
