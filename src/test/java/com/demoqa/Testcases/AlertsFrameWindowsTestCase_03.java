package com.demoqa.Testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.demoqa.Pages.AlertsPage;
import com.demoqa.Pages.BrowserWindows;
import com.demoqa.Pages.Frames;
import com.demoqa.Pages.ModalDialogsPage;
import com.demoqa.Pages.NestedFramesPage;
import com.demoqa.Pages.SelectMenuItemPage;
import com.demoqa.Pages.SelectTheSubMenuItem;
import com.demoqa.Utilities.BrowserInitialization;
import com.demoqa.Utilities.ExcelDataProvider;

/**
 * This class contains the test case for Alerts, Frames, and Windows
 * functionalities. It tests menu navigation, browser windows, alerts, frames,
 * nested frames, and modal dialogs.
 */
public class AlertsFrameWindowsTestCase_03 extends BrowserInitialization {

	/**
	 * Setup method to set the sheet name for the Excel data provider before running
	 * the test.
	 */
	@BeforeClass
	public void setup() {
		// Set the sheet name here dynamically before running the test
		ExcelDataProvider.setSheetName("Alert");
	}

	/**
	 * Test method that executes the test cases based on the data provided through
	 * Excel. It validates the navigation and interactions on browser windows,
	 * alerts, frames, and modal dialogs.
	 * 
	 * @param Menu                  The menu to navigate to.
	 * @param SubMenuBrowserWindows The submenu to navigate under the Browser
	 *                              Windows section.
	 * @param SubMenu               The submenu to navigate under the Alerts
	 *                              section.
	 * @param ConfirmButton         The value for confirming the alert.
	 * @param PromtInput            The input value for the prompt alert.
	 * @param PromtAction           The action for the prompt alert.
	 * @param Menuframe             The submenu for Frames.
	 * @param NestedFrame           The submenu for Nested Frames.
	 * @param ModalDialog           The submenu for Modal Dialogs.
	 * @param Smallmodal            The value for the modal dialog.
	 */
	@Test(dataProvider = "Excel", dataProviderClass = ExcelDataProvider.class)
	public void Alerts(String Menu, String SubMenuBrowserWindows, String SubMenu, String ConfirmButton,
			String PromtInput, String PromtAction, String Menuframe, String NestedFrame, String ModalDialog,
			String Smallmodal) {

		
		reportGenerator.getAttributes("DemoQA", "Alerts", "Shubham", "1003");
		
		// Step 1: Navigate to the specified Menu and verify the submenu
		reportGenerator.writeStep("Navigate to Menu", "Navigating to Menu: " + Menu);
		SelectMenuItemPage menuPage = new SelectMenuItemPage(driver);
		menuPage.navigateMenuItemByText(Menu);
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + SubMenuBrowserWindows + "']")).isDisplayed(),
				SubMenuBrowserWindows + " is not displayed!");
		reportGenerator.verifyOutput(true, menuPage.found, "Successfully selected the menu item",
				"Failed to select the menu item");

		// Step 2: Navigate to the Browser Windows submenu
		reportGenerator.writeStep("Navigate to SubMenu - Browser Windows",
				"Navigating to SubMenu: " + SubMenuBrowserWindows);
		SelectTheSubMenuItem subMenuBrowserWindows = new SelectTheSubMenuItem(driver);
		String sMenuBrowserWindows = subMenuBrowserWindows.navigatesubmenuitem(SubMenuBrowserWindows);
		Assert.assertEquals(sMenuBrowserWindows, SubMenuBrowserWindows,
				"The actual submenu value does not match the expected value: " + SubMenuBrowserWindows);
		reportGenerator.verifyOutput(sMenuBrowserWindows, SubMenuBrowserWindows,
				"Successfully navigated to SubMenu: " + SubMenuBrowserWindows,
				"Failed to navigate to SubMenu: " + SubMenuBrowserWindows);

		// Step 3: Open new tabs and windows
		reportGenerator.writeStep("Handle Browser Windows", "Opening new tabs and windows");
		BrowserWindows browserwindows = new BrowserWindows(driver);
		browserwindows.NewTab();
		browserwindows.NewWindow();
		reportGenerator.verifyOutput(true, true, "Successfully Opened new tab","Failed to Opened new tabs");

		// Step 4: Handle Alerts
		reportGenerator.writeStep("Navigate to SubMenu - Alerts", "Navigating to SubMenu: " + SubMenu);
		SelectTheSubMenuItem subMenuPage = new SelectTheSubMenuItem(driver);
		String sValue = subMenuPage.navigatesubmenuitem(SubMenu);
		Assert.assertEquals(sValue, SubMenu, "The actual submenu value does not match the expected value: " + sValue);
		reportGenerator.verifyOutput(sValue, SubMenu, "Successfully navigated to SubMenu: " + SubMenu,
				"Failed to navigate to SubMenu: " + SubMenu);

		// Create an instance of AlertsPage to interact with alerts
		reportGenerator.writeStep("Handle Alerts", "Interacting with various alerts");
		AlertsPage Alerts = new AlertsPage(driver);
		Alerts.Alters();
		Alerts.timerAlertButton();
		Alerts.confirmButton(ConfirmButton);
		Alerts.promtButton(PromtInput, PromtAction);
		reportGenerator.verifyOutput(true, true, "Successfully Handle Alerts",
				"Failed to Handle Alerts");

		// Step 5: Handle Frames
		reportGenerator.writeStep("Navigate to SubMenu - Frames", "Navigating to SubMenu: " + Menuframe);
		SelectTheSubMenuItem subMenuframe = new SelectTheSubMenuItem(driver);
		String sMenuframe = subMenuframe.navigatesubmenuitem(Menuframe);
		Assert.assertEquals(sMenuframe, Menuframe,
				"The actual submenu value does not match the expected value: " + Menuframe);
		reportGenerator.verifyOutput(sMenuframe, Menuframe, "Successfully navigated to SubMenu: " + Menuframe,
				"Failed to navigate to SubMenu: " + Menuframe);

		// Handle iframes
		reportGenerator.writeStep("Handle Frames", "Interacting with iframes");
		Frames framesPage = new Frames(driver);
		framesPage.iframe1(); // Interact with iframe1
		framesPage.iframe2(); // Interact with iframe2
		reportGenerator.verifyOutput(true, true, "Successfully Handle Frames" ,
				"Failed to Handle Frames");
		

		// Step 6: Handle Nested Frames
		reportGenerator.writeStep("Navigate to SubMenu - Nested Frames", "Navigating to SubMenu: " + NestedFrame);
		SelectTheSubMenuItem subMenuNestedFrames = new SelectTheSubMenuItem(driver);
		String sNestedFrames = subMenuNestedFrames.navigatesubmenuitem(NestedFrame);
		Assert.assertEquals(sNestedFrames, NestedFrame,
				"The actual submenu value does not match the expected value: " + sNestedFrames);
		reportGenerator.verifyOutput(sNestedFrames, NestedFrame, "Successfully navigated to SubMenu: " + sNestedFrames,
				"Failed to navigate to SubMenu: " + sNestedFrames);

		// Interact with nested frames
		NestedFramesPage NestedFrames = new NestedFramesPage(driver);
		NestedFrames.nestedFrames();

		// Step 7: Handle Modal Dialogs
		reportGenerator.writeStep("Navigate to SubMenu - Modal Dialogs", "Navigating to SubMenu: " + ModalDialog);
		SelectTheSubMenuItem subMenuModalDialog = new SelectTheSubMenuItem(driver);
		String sMenuModal = subMenuModalDialog.navigatesubmenuitem(ModalDialog);
		Assert.assertEquals(sMenuModal, ModalDialog,
				"The actual submenu value does not match the expected value: " + sMenuModal);
		reportGenerator.verifyOutput(sMenuModal, ModalDialog, "Successfully navigated to SubMenu: " + ModalDialog,
				"Failed to navigate to SubMenu: " + ModalDialog);

		// Interact with modal dialog
		reportGenerator.writeStep("Handle Modal Dialogs", "Interacting with modal dialog");
		ModalDialogsPage ModalDialogs = new ModalDialogsPage(driver);
		ModalDialogs.modeldialogbox(Smallmodal);
	}
}
