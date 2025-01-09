package com.demoqa.Testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.demoqa.Utilities.ExcelDataProvider;

import com.demoqa.Pages.Buttons;
import com.demoqa.Pages.Checkbox;
import com.demoqa.Pages.DynamicProperties;
import com.demoqa.Pages.Links;
import com.demoqa.Pages.RadioButton;
import com.demoqa.Pages.SelectMenuItemPage;
import com.demoqa.Pages.SelectTheSubMenuItem;
import com.demoqa.Pages.TextBoxPage;
import com.demoqa.Pages.Uploadfile;
import com.demoqa.Pages.WebTables;
import com.demoqa.Utilities.BrowserInitialization;

/**
 * Test Class: ElementsTestcase_01
 * 
 * This class automates and verifies various functionalities under the
 * "Elements" section of the demoqa application, such as Text Box, CheckBox,
 * Radio Button, WebTables, Buttons, Links, Upload and Download, and Dynamic
 * Properties.
 * 
 * Preconditions: - Sheet name is dynamically set to "ElementValue" for Excel
 * data provider. - Browser is initialized using BrowserInitialization class.
 * 
 * Test Steps: 1. Navigate to the "Elements" menu item and its respective
 * submenus. 2. Perform and validate actions such as entering details, clicking
 * buttons, handling tables, uploading files, and interacting with dynamic
 * elements.
 */
public class ElementsTestcase_01 extends BrowserInitialization {

	/**
	 * Prepares the test environment by setting the Excel sheet name dynamically.
	 */
	@BeforeClass
	public void setup() {
		// Step: Set the sheet name for data provider
		ExcelDataProvider.setSheetName("ElementValue");
	}

	/**
	 * Test Case: Elements
	 * 
	 * @param Menu                   Main menu to navigate.
	 * @param SubMenu                Submenu for TextBox.
	 * @param UserName               User's name for TextBox.
	 * @param UserEmail              User's email for TextBox.
	 * @param CurrentAddress         User's current address.
	 * @param PermanentAddress       User's permanent address.
	 * @param Submnu                 Submenu for CheckBox.
	 * @param ClickNode              Node to click in CheckBox.
	 * @param SelectCheckbox         Checkbox to select.
	 * @param MenuRadio              Submenu for RadioButton.
	 * @param RadioValue             Value to select in RadioButton.
	 * @param MenuWebTable           Submenu for WebTables.
	 * @param Firstname              First name for WebTable action.
	 * @param Fieldname              WebTable field name for update.
	 * @param Updatedvalue           Updated value for WebTable field.
	 * @param FirstName              User's first name for WebTable addition.
	 * @param LastName               User's last name for WebTable addition.
	 * @param Email                  User's email for WebTable addition.
	 * @param Age                    User's age for WebTable addition.
	 * @param Salary                 User's salary for WebTable addition.
	 * @param Department             User's department for WebTable addition.
	 * @param DeleteRecord           Record to delete in WebTable.
	 * @param MenusButtons           Submenu for Buttons.
	 * @param ButtonAction           Action to perform on Buttons.
	 * @param Link                   Submenu for Links.
	 * @param HandleLink             Link to handle.
	 * @param Apicall                API call link.
	 * @param MenusUploadandDownload Submenu for Upload and Download.
	 * @param Filepath               File path for upload action.
	 * @param MenusDynamicProperties Submenu for Dynamic Properties.
	 * 
	 * @throws Exception if an error occurs during the test execution.
	 */
	@Test(dataProvider = "Excel", dataProviderClass = ExcelDataProvider.class)
	public void Elements(String Menu, String SubMenu, String UserName, String UserEmail, String CurrentAddress,
			String PermanentAddress, String Submnu, String ClickNode, String SelectCheckbox, String MenuRadio,
			String RadioValue, String MenuWebTable, String Firstname, String Fieldname, String Updatedvalue,
			String FirstName, String LastName, String Email, String Age, String Salary, String Department,
			String DeleteRecord, String MenusButtons, String ButtonAction, String Link, String HandleLink,
			String Apicall, String MenusUploadandDownload, String Filepath, String MenusDynamicProperties)
			throws Exception {

		reportGenerator.getAttributes("DemoQA", "Elements", "Shubham", "1001");
		// Step 1: Navigate to Menu and SubMenu
		reportGenerator.writeStep("Navigate to Menu and SubMenu", "Navigate to: " + Menu + ", SubMenu: " + SubMenu);
		SelectMenuItemPage menuPage = new SelectMenuItemPage(driver);
		menuPage.navigateMenuItemByText(Menu);
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + SubMenu + "']")).isDisplayed(),
				SubMenu + " is not displayed!");
		reportGenerator.verifyOutput(true, menuPage.found, "Successfully selected the menu item",
				"Failed to select the menu item");

		// Step 2: Text Box
		reportGenerator.writeStep("Text Box", "Fill and verify text box details");
		SelectTheSubMenuItem subMenuPage = new SelectTheSubMenuItem(driver);
		String sValue = subMenuPage.navigatesubmenuitem(SubMenu);
		Assert.assertEquals(sValue, SubMenu, "The actual submenu value does not match the expected value: " + sValue);
		reportGenerator.verifyOutput(sValue, SubMenu, "Successfully navigated to SubMenu: " + SubMenu,
				"Failed to navigate to SubMenu: " + SubMenu);

		TextBoxPage textboxdetails = new TextBoxPage(driver);
		textboxdetails.details(UserName, UserEmail, CurrentAddress, PermanentAddress);
		textboxdetails.verification(UserName, UserEmail, CurrentAddress, PermanentAddress);

		// Step 3: CheckBox
		reportGenerator.writeStep("CheckBox", "Expand and verify checkbox selection");
		String sSubmnuCheckBox = subMenuPage.navigatesubmenuitem(Submnu);
		Assert.assertEquals(sSubmnuCheckBox, Submnu, "The actual submenu value does not match the expected value.");
		Checkbox TreeViewCheckbox = new Checkbox(driver);
		TreeViewCheckbox.expandHome();
		TreeViewCheckbox.clickNode(ClickNode);
		TreeViewCheckbox.selectCheckbox(SelectCheckbox);
		Assert.assertEquals(TreeViewCheckbox.verification().toLowerCase(), SelectCheckbox.toLowerCase(),
				"Checkbox verification failed.");
		reportGenerator.verifyOutput(TreeViewCheckbox.verification().toLowerCase(), SelectCheckbox.toLowerCase(),
				"Checkbox selection verified successfully", "Checkbox selection verification failed");

		// Step 4: RadioButton
		reportGenerator.writeStep("RadioButton", "Select and verify radio button value");
		String sRadio = subMenuPage.navigatesubmenuitem(MenuRadio);
		Assert.assertEquals(MenuRadio, sRadio, "RadioButton submenu mismatch.");
		reportGenerator.verifyOutput(sRadio, MenuRadio, "Successfully navigated to RadioButton submenu",
				"Failed to navigate to RadioButton submenu");

		reportGenerator.writeStep("selecting the RadioButton", "Verify radio button value");
		RadioButton rbnvalue = new RadioButton(driver);
		rbnvalue.radiobtn(RadioValue);
		Assert.assertEquals(rbnvalue.rbnverification(), RadioValue, "RadioButton selection mismatch.");
		reportGenerator.verifyOutput(rbnvalue.rbnverification(), RadioValue,
				"RadioButton selection verified successfully", "RadioButton selection verification failed");

		// Step 5: WebTables
		reportGenerator.writeStep("WebTables", "Handle actions on WebTables");
		String sWebTables = subMenuPage.navigatesubmenuitem(MenuWebTable);
		Assert.assertEquals(sWebTables, MenuWebTable, "WebTables submenu mismatch.");
		reportGenerator.verifyOutput(sWebTables, MenuWebTable, "Successfully navigated to WebTables submenu",
				"Failed to navigate to WebTables submenu");

		WebTables table = new WebTables(driver);
		table.handleTableAction(Firstname, Fieldname, Updatedvalue);
		table.adddetails(FirstName, LastName, Email, Age, Salary, Department);
		table.deleteRowByFirstName(DeleteRecord);

		// Step 6: Buttons
		reportGenerator.writeStep("Buttons", "Perform actions on buttons");
		String sButtons = subMenuPage.navigatesubmenuitem(MenusButtons);
		Assert.assertEquals(sButtons, MenusButtons, "Buttons submenu mismatch.");
		reportGenerator.verifyOutput(sButtons, MenusButtons, "Successfully navigated to Buttons submenu",
				"Failed to navigate to Buttons submenu");
		Buttons clickaction = new Buttons(driver);
		clickaction.performAction(ButtonAction);

		// Step 7: Links
		reportGenerator.writeStep("Links", "Handle and verify links");
		String sLinks = subMenuPage.navigatesubmenuitem(Link);
		Assert.assertEquals(sLinks, Link, "Links submenu mismatch.");
		reportGenerator.verifyOutput(sLinks, Link, "Successfully navigated to Links submenu",
				"Failed to navigate to Links submenu");
		Links handlelinks = new Links(driver);
		handlelinks.handleLink(HandleLink);

		// Step 8: Upload and Download
		reportGenerator.writeStep("Upload and Download", "Upload a file and verify");
		String sUploadandDownload = subMenuPage.navigatesubmenuitem(MenusUploadandDownload);
		Assert.assertEquals(sUploadandDownload, MenusUploadandDownload, "Upload and Download submenu mismatch.");
		reportGenerator.verifyOutput(sUploadandDownload, MenusUploadandDownload,
				"Successfully navigated to Upload and Download submenu",
				"Failed to navigate to Upload and Download submenu");
		Uploadfile Download = new Uploadfile(driver);
		Download.uploadFile(Filepath);

		// Step 9: Dynamic Properties
		reportGenerator.writeStep("Dynamic Properties", "Verify dynamic elements");
		String sDynamicProperties = subMenuPage.navigatesubmenuitem(MenusDynamicProperties);
		Assert.assertEquals(sDynamicProperties, MenusDynamicProperties, "Dynamic Properties submenu mismatch.");
		reportGenerator.verifyOutput(sDynamicProperties, MenusDynamicProperties,
				"Successfully navigated to Dynamic Properties submenu",
				"Failed to navigate to Dynamic Properties submenu");
		DynamicProperties dynamic = new DynamicProperties(driver);
		dynamic.DynamicElemenet();
	}
}
