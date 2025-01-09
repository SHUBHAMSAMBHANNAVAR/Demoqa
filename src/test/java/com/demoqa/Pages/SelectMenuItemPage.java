package com.demoqa.Pages;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SelectMenuItemPage {
    public WebDriver sdriver;
    public boolean found = false;
    /**
     * Constructor to initialize WebDriver and page elements using PageFactory.
     * 
     * @param ldriver the WebDriver instance passed from the test script
     */
    public SelectMenuItemPage(WebDriver ldriver) {
        this.sdriver = ldriver;
        PageFactory.initElements(ldriver, this); // Initialize the page elements using PageFactory
    }

    @FindBy(xpath = "//div[@class='card-body']")
    List<WebElement> List_MenuItem;

    /**
     * Prints all menu items' text and clicks the menu item based on the provided text.
     * Includes handling for scrolling and JavaScript click to bypass obstructions.
     * 
     * @param sItemText the text of the menu item to click
     */
    public void navigateMenuItemByText(String sItemText) {
        JavascriptExecutor js = (JavascriptExecutor) sdriver;
        WebDriverWait wait = new WebDriverWait(sdriver, java.time.Duration.ofSeconds(10)); // Explicit wait


        // Click the menu item that matches the provided text
        
        for (WebElement item : List_MenuItem) {
            if (item.getText().equalsIgnoreCase(sItemText)) {
                try {
                    // Scroll into view
                    js.executeScript("arguments[0].scrollIntoView(true);", item);
                    
                    // Wait until the element is clickable
                    wait.until(ExpectedConditions.elementToBeClickable(item));
                    
                    // Attempt to click
                    item.click();
                    System.out.println("Menu item with text '" + sItemText + "' is found.");
                    
                } catch (Exception e) {
                    System.out.println("Standard click failed, using JavaScript click as fallback.");
                    // Use JavaScript click if standard click fails
                    js.executeScript("arguments[0].click();", item);
                }
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Menu item with text '" + sItemText + "' not found.");
        }
    }
}
