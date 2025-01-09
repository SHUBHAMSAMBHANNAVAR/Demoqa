package com.demoqa.Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SelectMenuPage {

	 public WebDriver sdriver;

	    // Constructor to initialize the WebDriver
	    public SelectMenuPage(WebDriver ldriver) {
	        this.sdriver = ldriver;
	        PageFactory.initElements(sdriver, this);
	    }

	    // Method to select an option from the custom dropdown
	    public void SelectValue(String valueToSelect) {
	    	
	    	 sdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
	    	 
	    	 
	        // Locate the dropdown container and click to open the options
	        WebElement dropdown = sdriver.findElement(By.xpath("//div[text()='Select Option']"));
	        if(dropdown.isDisplayed()) {
	        	// JavaScript click to open the dropdown
		        JavascriptExecutor js = (JavascriptExecutor) sdriver;
		        js.executeScript("arguments[0].click();", dropdown);
		        dropdown.click();
		       }else {
		    	   System.out.println("the ellement is not found");
		       }
		    	  
	       
	     // JavaScript click to open the dropdown
	        JavascriptExecutor js = (JavascriptExecutor) sdriver;
	        js.executeScript("arguments[0].click();", dropdown);
	        

	        // Locate the option using the value (or text) you want to select and click on it
	        WebElement option = sdriver.findElement(By.xpath("//div[text()='" + valueToSelect + "']"));
	        option.click();

	        // Optionally, verify that the correct option was selected
	        System.out.println("Selected option: " + valueToSelect);
	    }
	
	    public void SelectOne(String sValueSelectOne) {
	        // Locate the dropdown container and click to open the options
	       
	    	
	    	 sdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
	    	 
	    	 
	        // Locate the dropdown container and click to open the options
	    	 WebElement dropdown = sdriver.findElement(By.xpath("//div[text()='Select Title']"));
	        if(dropdown.isDisplayed()) {
	        	// JavaScript click to open the dropdown
		        JavascriptExecutor js = (JavascriptExecutor) sdriver;
		        js.executeScript("arguments[0].click();", dropdown);
		        dropdown.click();
		       }else {
		    	   System.out.println("the ellement is not found");
		       }
		    	  
	       
	     // JavaScript click to open the dropdown
	        JavascriptExecutor js = (JavascriptExecutor) sdriver;
	        js.executeScript("arguments[0].click();", dropdown);

	        // Locate the option using the value (or text) you want to select and click on it
	        WebElement option = sdriver.findElement(By.xpath("//div[text()='" + sValueSelectOne + "']"));
	        option.click();

	        // Optionally, verify that the correct option was selected
	        System.out.println("Selected option: " + sValueSelectOne);   
	        	        
}public void MenuColour(String colorToSelect) {
    try {
        // Wait until the dropdown is present
        WebDriverWait wait = new WebDriverWait(sdriver, Duration.ofSeconds(40));
        WebElement dropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("oldSelectMenu")));


        // Use the Select class to interact with the dropdown
        Select select = new Select(dropdown);
        select.selectByVisibleText(colorToSelect);

        // Verify the selected option
        String selectedColor = select.getFirstSelectedOption().getText();
        if (selectedColor.equals(colorToSelect)) {
            System.out.println("Successfully selected color: " + selectedColor);
        } else {
            System.out.println("Failed to select the color. Selected: " + selectedColor);
        }
    } catch (Exception e) {
        System.out.println("Exception occurred: " + e.getMessage());
    }
}

// Method to select multiple options dynamically
public void selectOptions(WebDriver driver, WebElement dropdownInput,List<String> options) {
    // Wait for the dropdown to become visible
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    WebElement dropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='Select...']")));

    // Click the dropdown to activate it
    dropdown.click();

    // Loop through the provided options and select them one by one
    for (String option : options) {
        try {
            // Send the option text to the input field
            dropdownInput.sendKeys(option);

            // Press 'Enter' to select the option
            dropdownInput.sendKeys(Keys.ENTER);

            System.out.println("Successfully selected: " + option);
        } catch (Exception e) {
            System.out.println("Failed to select option: " + option + " due to: " + e.getMessage());
        }
    }

//    // Optional: Print all currently selected options
//    try {
//        List<WebElement> selectedOptions = driver.findElements(By.cssSelector(".css-class-for-selected-options")); // Update selector if necessary
//        System.out.println("Currently selected options:");
//        for (WebElement selectedOption : selectedOptions) {
//            System.out.println(selectedOption.getText());
//        }
//    } catch (Exception e) {
//        System.out.println("Failed to fetch selected options: " + e.getMessage());
//    }
}

public void Standardmultiselect(String sCar) {
	
	WebElement dropdown = sdriver.findElement(By.id("cars"));

	Select select = new Select(dropdown);

	// Select multiple options
	select.selectByVisibleText(sCar);
}
}


	    
	    
