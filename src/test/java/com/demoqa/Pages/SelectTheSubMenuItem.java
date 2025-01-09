package com.demoqa.Pages;

import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SelectTheSubMenuItem {

    public WebDriver sdriver;

    public SelectTheSubMenuItem(WebDriver ldriver) {
        this.sdriver = ldriver;
        PageFactory.initElements(ldriver, this); // Initialize the page elements using PageFactory
    }

    @FindBy(xpath = "//span[@class='text']")
    List<WebElement> List_Submenu;

    @FindBy(xpath = "//h1[@class='text-center']")
    WebElement txt_Submenu;

    public String navigatesubmenuitem(String sItemText) {
        JavascriptExecutor js = (JavascriptExecutor) sdriver;
        WebDriverWait wait = new WebDriverWait(sdriver, java.time.Duration.ofSeconds(10)); // Explicit wait

        // Click the menu item that matches the provided text
        boolean found = false;
        for (WebElement item : List_Submenu) {
            if (item.getText().equalsIgnoreCase(sItemText)) {
                try {
                    // Scroll into view
                    js.executeScript("arguments[0].scrollIntoView(true);", item);

                    // Wait until the element is clickable
                    wait.until(ExpectedConditions.elementToBeClickable(item));

                    // Attempt to click
                    item.click();
                    System.out.println("Menu item with text '" + sItemText + "' is found.");

                    // Wait for the content to load or for the header text to change
                    wait.until(ExpectedConditions.visibilityOf(txt_Submenu));

                    // Return the updated header text
                    return txt_Submenu.getText();
                    
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

        // Return the text if no item was found or clicked
        return txt_Submenu.getText();
    }
}
