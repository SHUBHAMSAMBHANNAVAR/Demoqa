package com.demoqa.Pages;

import java.time.Duration;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebTables {
	public WebDriver sdriver;

	// Constructor to initialize the WebDriver and PageFactory elements
	public WebTables(WebDriver ldriver) {
		this.sdriver = ldriver;
		PageFactory.initElements(ldriver, this);
	}

	public void handleTableAction(String firstName, String fieldToEdit, String newValue) {
		WebDriverWait wait = new WebDriverWait(sdriver, Duration.ofSeconds(10)); // Explicit wait for handling dynamic
																					// elements

		// Locate the table rows
		List<WebElement> rows = sdriver.findElements(By.xpath("//div[@class='rt-tbody']/div[@class='rt-tr-group']"));

		boolean actionPerformed = false;

		for (WebElement row : rows) {
			// Get the first cell value (e.g., first name)
			String name = row.findElement(By.xpath(".//div[@class='rt-td'][1]")).getText();

			if (name.equals(firstName)) {
				// Locate the Edit button within the current row
				WebElement editButton = row.findElement(By.xpath(".//span[@title='Edit']"));
				wait.until(ExpectedConditions.elementToBeClickable(editButton)).click();

				// Wait for the dialog box to appear
				WebElement inputField = null;
				switch (fieldToEdit.toLowerCase()) {
				case "firstname":
					inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstName")));
					break;
				case "lastname":
					inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lastName")));
					break;
				case "useremail":
					inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userEmail")));
					break;
				case "age":
					inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("age")));
					break;
				case "salary":
					inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("salary")));
					break;
				case "department":
					inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("department")));
					break;
				default:
					throw new IllegalArgumentException("Invalid field: " + fieldToEdit);
				}

				// Select all text, clear the field, and enter the new value
				inputField.sendKeys(Keys.CONTROL + "a");
				inputField.sendKeys(Keys.DELETE);
				inputField.sendKeys(newValue);

				// Click the Submit button
				WebElement submitButton = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='submit']")));
				submitButton.click();

				actionPerformed = true;
				break;
			}
		}

		if (!actionPerformed) {
			throw new RuntimeException("Row with first name '" + firstName + "' not found!");
		}
	
	}
	@FindBy(xpath ="//button[text()='Add']")
	WebElement btn_Add;

	@FindBy(xpath ="//input[@id='firstName']")
	WebElement txt_firstName;

	@FindBy(xpath ="//input[@id='lastName']")
	WebElement txt_lastName;

	@FindBy(xpath ="//input[@id='userEmail']")
	WebElement txt_userEmail;

	@FindBy(xpath ="//input[@id='age']")
	WebElement txt_age;

	@FindBy(xpath ="//input[@id='salary']")
	WebElement txt_salary;

	@FindBy(xpath ="//input[@id='department']")
	WebElement txt_department;

	@FindBy(xpath ="//button[@id='submit']")
	WebElement btn_submit;

	public void adddetails(String firstName, String lastName, String email, String age, String salary,
			String department) throws InterruptedException {
		Thread.sleep(4000);
		btn_Add.click();
		Thread.sleep(4000);
		// Set values to the input fields
		txt_firstName.click();
		txt_firstName.sendKeys(firstName);
		
		txt_lastName.click();
		txt_lastName.sendKeys(lastName);
		Thread.sleep(2000);
		
		txt_userEmail.click();
		txt_userEmail.sendKeys(email);
		
		txt_age.click();
		txt_age.sendKeys(age);
		
		txt_salary.click();
		txt_salary.sendKeys(salary);
		Thread.sleep(2000);
		
		txt_department.click();
		txt_department.sendKeys(department);
		Thread.sleep(2000);
	    // Submit the form
		JavascriptExecutor js = (JavascriptExecutor) sdriver;
		js.executeScript("arguments[0].click();", btn_submit);

		//btn_submit.click();
	}

	 // Method to delete a row based on the first name
    public void deleteRowByFirstName(String firstName) {
        // Construct the dynamic XPath for the delete button
        String deleteButtonXPath = "//div[@class='rt-tbody']/div[@class='rt-tr-group'][.//div[text()='"
                                    + firstName
                                    + "']]//span[contains(@title, 'Delete')]";

        // Check if the element exists before clicking
        if (!sdriver.findElements(By.xpath(deleteButtonXPath)).isEmpty()) {
            // Locate the delete button using the dynamic XPath
            WebElement deleteButton = sdriver.findElement(By.xpath(deleteButtonXPath));

            // Click the delete button
            deleteButton.click();
            System.out.println("Row with first name '" + firstName + "' has been deleted.");
        } else {
            System.out.println("Row with first name '" + firstName + "' not found.");
        }
    }
}