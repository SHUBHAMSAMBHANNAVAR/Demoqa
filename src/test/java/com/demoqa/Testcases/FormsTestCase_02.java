package com.demoqa.Testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.demoqa.Pages.FromDetailverification;
import com.demoqa.Pages.PracticeForm;
import com.demoqa.Pages.SelectMenuItemPage;
import com.demoqa.Pages.SelectTheSubMenuItem;
import com.demoqa.Utilities.BrowserInitialization;
import com.demoqa.Utilities.ExcelDataProvider;

/**
 * FormsTestCase_02 automates the Practice Form functionality on the demo site.
 * This test case verifies form submission and ensures all input fields are
 * validated successfully.
 */
public class FormsTestCase_02 extends BrowserInitialization {

	/**
	 * BeforeClass method to set up the Excel sheet name before the test runs. This
	 * ensures the data provider fetches the correct data.
	 */
	@BeforeClass
	public void setup() {
		// Set the sheet name here dynamically before running the test
		ExcelDataProvider.setSheetName("Formvalue");
	}

	/**
	 * Test to validate the Practice Form functionality.
	 * 
	 * @param Menu           The main menu to navigate.
	 * @param SubMenu        The submenu under the main menu.
	 * @param FirstName      First name input for the form.
	 * @param LastName       Last name input for the form.
	 * @param Email          Email input for the form.
	 * @param Gender         Gender input for the form.
	 * @param MobileNumber   Mobile number input for the form.
	 * @param Month          Month of birth.
	 * @param Year           Year of birth.
	 * @param Day            Day of birth.
	 * @param Hobbie1        First hobby input.
	 * @param Hobbie2        Second hobby input.
	 * @param Hobbie3        Third hobby input.
	 * @param Picturepath    Path to the file to upload.
	 * @param Currectaddress Current address input.
	 * @param State          State input for the form.
	 * @param City           City input for the form.
	 * @throws Exception Exception thrown if any issue occurs during execution.
	 */
	@Test(dataProvider = "Excel", dataProviderClass = ExcelDataProvider.class)
	public void Forms(String Menu, String SubMenu, String FirstName, String LastName, String Email, String Gender,
			String MobileNumber, String Month, String Year, String Day, String Hobbie1, String Hobbie2, String Hobbie3,
			String Picturepath, String Currectaddress, String State, String City) throws Exception {

		
		reportGenerator.getAttributes("DemoQA", "Forms", "Shubham", "1002");
		// Step 1: Skip test if FirstName is null or empty
		if (FirstName == null || FirstName.isEmpty()) {
			throw new SkipException("Test skipped because the first name is null or empty.");
		}

		// Step 2: Navigate to the specified Menu and SubMenu
		reportGenerator.writeStep("Navigate to Menu", "Navigating to Menu: " + Menu + ", SubMenu: " + SubMenu);
		SelectMenuItemPage menuPage = new SelectMenuItemPage(driver);
		menuPage.navigateMenuItemByText(Menu);
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + SubMenu + "']")).isDisplayed(),
				SubMenu + " is not displayed!");
		   reportGenerator.verifyOutput(true, menuPage.found, "Successfully selected the menu item",
	                "Failed to select the menu item");

		// Step 3: Navigate to SubMenu and verify
	    reportGenerator.writeStep("Navigate to SubMenu", "Navigating to SubMenu: " + SubMenu);  
		SelectTheSubMenuItem subMenuPage = new SelectTheSubMenuItem(driver);
		String sValue = subMenuPage.navigatesubmenuitem(SubMenu);
		Assert.assertEquals(sValue, SubMenu, "The actual submenu value does not match the expected value: " + sValue);
		reportGenerator.verifyOutput(sValue, SubMenu, "Successfully navigated to SubMenu: " + SubMenu,
                "Failed to navigate to SubMenu: " + SubMenu);

		// Verify Full Name
        reportGenerator.writeStep("Verify Full Name", "Validating submitted Full Name");
		PracticeForm form = new PracticeForm(driver);
		form.practiceform(FirstName, LastName, Email, Gender, MobileNumber, Month, Year, Day, Hobbie1, Hobbie2, Hobbie3,
				Picturepath, Currectaddress, State, City);

		// Step 5: Verify the submitted details
		FromDetailverification Verification = new FromDetailverification(driver);

		// Verify Full Name
		String sFullname = Verification.studentname();
		String sActName = FirstName + " " + LastName;
		Assert.assertEquals(sFullname, sActName, "The actual Name does not match the expected value: " + sActName);
		reportGenerator.verifyOutput(sFullname, sActName, "Full Name is correct", "Full Name verification failed");
		
		 // Verify Email
        reportGenerator.writeStep("Verify Email", "Validating submitted Email");
		String sEmail = Verification.Email();
		 reportGenerator.verifyOutput(sEmail, Email, "Email is correct", "Email verification failed");
		Assert.assertEquals(sEmail, Email, "The Email does not match the expected Email: " + Email);

		// Verify Gender
        reportGenerator.writeStep("Verify Gender", "Validating submitted Gender");
		String sGender = Verification.Gender();
		Assert.assertEquals(sGender, Gender, "The actual Gender does not match the expected Gender: " + Gender);
		reportGenerator.verifyOutput(sGender, Gender, "Gender is correct", "Gender verification failed");

		 // Verify Mobile Number
        reportGenerator.writeStep("Verify Mobile Number", "Validating submitted Mobile Number");
		String sMobile = Verification.Mobile();
		Assert.assertEquals(MobileNumber, sMobile, "The MobileNumber does not match the expected value: " + sMobile);
		reportGenerator.verifyOutput(sMobile, MobileNumber, "Mobile Number is correct", "Mobile Number verification failed");
		
		 // Verify Address
        reportGenerator.writeStep("Verify Address", "Validating submitted Current Address");
		String sAddress = Verification.Address();
		Assert.assertEquals(sAddress, Currectaddress,"The Current address does not match the expected Current address: " + Currectaddress);
		reportGenerator.verifyOutput(sAddress, Currectaddress, "Current Address is correct", "Address verification failed");

	}
}
