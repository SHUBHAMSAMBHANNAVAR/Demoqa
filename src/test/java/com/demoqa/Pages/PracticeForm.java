package com.demoqa.Pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PracticeForm {

	public WebDriver sdriver;

	public PracticeForm(WebDriver ldriver) {
		this.sdriver = ldriver;
		PageFactory.initElements(ldriver, this);
	}

	@FindBy(xpath = "//input[@id='firstName']")
	WebElement txt_userName;

	@FindBy(xpath = "//input[@id='lastName']")
	WebElement txt_lastName;

	@FindBy(xpath = "//input[@id='userEmail']")
	WebElement txt_UserEmail;

	@FindBy(xpath = "//label[text()='Male']")
	WebElement txt_gender;

	@FindBy(xpath = "//input[@id='userNumber']")
	WebElement txt_userNumber;

	@FindBy(xpath = "//div[@id='subjectsContainer']")
	WebElement txt_subjectsContainer;

	@FindBy(xpath = "//input[@id='uploadPicture']")
	WebElement btn_uploadfile;

	@FindBy(xpath = "//textarea[@id='currentAddress']")
	WebElement txt_currentAddress;

	@FindBy(xpath = "//div[text()='Select State']")
	WebElement txt_selectstate;

	@FindBy(xpath = "//input[@id='dateOfBirthInput']")
	WebElement dateOfBirthInput;
	
	@FindBy(xpath="//button[@id='submit']")
	WebElement btn_submit;

	public void practiceform(String sUserName, String sLastName, String sEmail, String gender, String sUserNumber,
			String sMonth, String sYearValue, String sDayValue, String sSports, String sReading, String sMusic,
			String sPath, String sCurrentAddress, String sState, String sCity)
			throws AWTException, InterruptedException {

		txt_userName.click();
		txt_userName.sendKeys(sUserName);

		txt_lastName.click();
		txt_lastName.sendKeys(sLastName);

		txt_UserEmail.click();
		txt_UserEmail.sendKeys(sEmail);

		// Construct the XPath dynamically using the parameter
		String dynamicXPath = "//label[text()='" + gender + "']";

		// Find the element using the dynamically constructed XPath
		WebElement genderOption = sdriver.findElement(By.xpath(dynamicXPath));

		// Perform the click action
		genderOption.click();

		((JavascriptExecutor) sdriver).executeScript("arguments[0].scrollIntoView(true);", txt_userNumber);
		txt_userNumber.click();
		txt_userNumber.sendKeys(sUserNumber);
		dateOfBirthInput.click();
		Map<String, String> monthMap = new HashMap<>();
		monthMap.put("January", "0");
		monthMap.put("February", "1");
		monthMap.put("March", "2");
		monthMap.put("April", "3");
		monthMap.put("May", "4");
		monthMap.put("June", "5");
		monthMap.put("July", "6");
		monthMap.put("August", "7");
		monthMap.put("September", "8");
		monthMap.put("October", "9");
		monthMap.put("November", "10");
		monthMap.put("December", "11");

		// Get the value for the given month name
		String monthValue = monthMap.get(sMonth);

		// Locate the dropdown
		WebElement monthDropdown = sdriver.findElement(By.className("react-datepicker__month-select"));
		Select select = new Select(monthDropdown);

		// Select the month by value
		if (monthValue != null) {
			select.selectByValue(monthValue);
		} else {
			System.out.println("Invalid month name: " + sMonth);
		}

		// Locate the year dropdown element
		WebElement yearDropdown = sdriver.findElement(By.className("react-datepicker__year-select"));

		// Use Select class to interact with the dropdown
		Select yearselect = new Select(yearDropdown);

		// Select the year by its value
		yearselect.selectByValue(sYearValue);

		String dynamicdate = "//div[text()='" + sDayValue + "']";

		WebElement dayElement = sdriver.findElement(By.xpath(dynamicdate));

		// Perform a click or any other action
		dayElement.click();

		// Helper method to click a label if it's not null
		clickLabelIfNotNull(sSports);
		clickLabelIfNotNull(sReading);
		clickLabelIfNotNull(sMusic);

		((JavascriptExecutor) sdriver).executeScript("arguments[0].click();", btn_uploadfile);

		Robot rb = new Robot();
		rb.delay(2000);

		StringSelection ss = new StringSelection(sPath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);

		rb.keyRelease(KeyEvent.VK_CONTROL);
		rb.keyRelease(KeyEvent.VK_V);

		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);

		txt_currentAddress.click();
		txt_currentAddress.sendKeys(sCurrentAddress);

		WebElement dropdown = sdriver.findElement(By.id("react-select-3-input"));
		dropdown.sendKeys(sState); // Pass the state name dynamically
		dropdown.sendKeys(Keys.ENTER);

		WebElement city = sdriver.findElement(By.id("react-select-4-input"));
		city.sendKeys(sCity); // Pass the state name dynamically
		city.sendKeys(Keys.ENTER);
		
		btn_submit.click();

	}

	// Helper method to handle null check and click action
	private void clickLabelIfNotNull(String label) {
		if (label != null) {
			String dynamicXPath = "//label[text()='" + label + "']";
			WebElement labelElement = sdriver.findElement(By.xpath(dynamicXPath));
			labelElement.click();

		}
	}

}
