package com.demoqa.Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SelectablePage {
	public WebDriver sdriver;

    // Constructor to initialize the WebDriver
    public SelectablePage(WebDriver ldriver) {
        this.sdriver = ldriver;
        PageFactory.initElements(sdriver, this);
    }
    // Method to select list items based on passed values
    public void selectListItems(List<String> values) {
        // Get all list items inside the container
        List<WebElement> listItems = sdriver.findElements(By.cssSelector("#verticalListContainer .list-group-item"));

        // Loop through all the values passed in the method
        for (String value : values) {
            for (WebElement item : listItems) {
                // If the list item text matches the passed value, click it
                if (item.getText().equals(value)) {
                	((JavascriptExecutor) sdriver).executeScript("arguments[0].scrollIntoView(true);", item);
                    item.click();
                    System.out.println("Clicked on: " + value);
                    break; // Exit the inner loop once clicked
                }
            }
        }
    }
    
    @FindBy(xpath="//a[@id='demo-tab-grid']")
    WebElement txt_grid;
    // Method to select list items from a grid based on passed values
    public void selectGridItems(List<String> values) {
    	
    	txt_grid.click();
    	
        // Wait until the grid container is visible
        WebDriverWait wait = new WebDriverWait(sdriver, Duration.ofSeconds(10));
        List<WebElement> rows = sdriver.findElements(By.cssSelector("#gridContainer .list-group"));

        // Loop through the values passed and select the corresponding items
        for (String value : values) {
            boolean isClicked = false;

            for (WebElement row : rows) {
                // Get all list items in the current row
                List<WebElement> listItems = row.findElements(By.cssSelector(".list-group-item"));
                
                for (WebElement item : listItems) {
                    if (item.getText().equals(value)) {
                        try {
                            // Wait for the element to be clickable
                            wait.until(ExpectedConditions.elementToBeClickable(item));
                            item.click();
                            //System.out.println("Clicked on: " + value);
                            isClicked = true;
                            break;
                        } catch (Exception e) {
                            System.out.println("Error clicking on " + value + ": " + e.getMessage());
                        }
                    }
                }

                // Break the loop if the value is found and clicked
                if (isClicked) {
                    break;
                }
            }
        }
    }
    
}
