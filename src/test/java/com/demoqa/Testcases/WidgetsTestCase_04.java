package com.demoqa.Testcases;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.demoqa.Pages.AccordianPage;
import com.demoqa.Pages.AutoCompletePage;
import com.demoqa.Pages.ProgressBarPage;
import com.demoqa.Pages.SelectMenuItemPage;
import com.demoqa.Pages.SelectMenuPage;
import com.demoqa.Pages.SelectTheSubMenuItem;
import com.demoqa.Pages.SliderPage;
import com.demoqa.Pages.ToolTipspage;
import com.demoqa.Utilities.BrowserInitialization;
import com.demoqa.Utilities.ExcelDataProvider;

/**
 * Test class for verifying widget functionalities on the DemoQA website. The
 * class includes methods to navigate through menus, interact with widgets, and
 * validate their behavior using Selenium and TestNG.
 */
public class WidgetsTestCase_04 extends BrowserInitialization {

	/**
	 * Sets up the Excel sheet name to "Widgets" before running the test cases.
	 */
	@BeforeClass
	public void setup() {
		// Set the sheet name dynamically
		ExcelDataProvider.setSheetName("Widgets");
	}

	/**
	 * Test method to navigate through menus, interact with widgets, and validate
	 * their functionality. Data is provided by an Excel data provider.
	 *
	 * @param Menu                The main menu item to navigate
	 * @param SubMenu             The submenu item to navigate
	 * @param SubAutoComplete     The submenu for AutoComplete
	 * @param Colors              The colors for multi-select
	 * @param SingleColor         The single color for selection
	 * @param SubSlider           The submenu for the slider
	 * @param Slidermoverange     The slider range value
	 * @param SubProgressbar      The submenu for the progress bar
	 * @param Progressbarvalue    The target progress bar value
	 * @param SubToolTip          The submenu for tooltips
	 * @param SubSelectMenu       The submenu for select menus
	 * @param SelectValue         The value to select in the dropdown
	 * @param SelectOne           The option to select from the dropdown
	 * @param MenuColour          The color menu to interact with
	 * @param selectColorOptions  The color options to select from
	 * @param Standardmultiselect The standard multi-select options
	 * @throws Exception If an error occurs during execution
	 */
	@Test(dataProvider = "Excel", dataProviderClass = ExcelDataProvider.class)
	public void Widgets(String Menu, String SubMenu, String SubAutoComplete, String Colors, String SingleColor,
			String SubSlider, String Slidermoverange, String SubProgressbar, String Progressbarvalue, String SubToolTip,
			String SubSelectMenu, String SelectValue, String SelectOne, String MenuColour, String selectColorOptions,
			String Standardmultiselect) throws Exception {

		reportGenerator.getAttributes("DemoQA", "Widgets", "Shubham", "1004");
		
		// Step 1: Navigate to the specified Menu and SubMenu
		
        reportGenerator.writeStep("Navigate to Menu", "Navigating to Menu: " + Menu);
		SelectMenuItemPage menuPage = new SelectMenuItemPage(driver);
		menuPage.navigateMenuItemByText(Menu);

		// Assert submenu visibility
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + SubMenu + "']")).isDisplayed(),
				SubMenu + " is not displayed!");
		reportGenerator.verifyOutput(true, menuPage.found, "Successfully selected the menu item",
				"Failed to select the menu item");

		// Step 2: Navigate to submenu and validate
        reportGenerator.writeStep("Navigate to SubMenu", "Navigating to SubMenu: " + SubMenu);
		SelectTheSubMenuItem subMenuPage = new SelectTheSubMenuItem(driver);
		String sValue = subMenuPage.navigatesubmenuitem(SubMenu);
		Assert.assertEquals(sValue, SubMenu, "The actual submenu value does not match the expected value: " + sValue);
		reportGenerator.verifyOutput(sValue.equals(SubMenu), true, "SubMenu navigated successfully", "SubMenu navigation failed!");

		// Step 3: Interact with the Accordion widget
        reportGenerator.writeStep("Interact with Accordion", "Expanding sections in Accordion widget");
		AccordianPage accordian = new AccordianPage(driver);
		accordian.accordian();
		reportGenerator.verifyOutput(true, true, "Accordion interactions successful", "Accordion interactions failed");

		// Step 4: Navigate to AutoComplete submenu
        reportGenerator.writeStep("Navigate to AutoComplete SubMenu", "Navigating to: " + SubAutoComplete);
		SelectTheSubMenuItem subMenuAutoComplete = new SelectTheSubMenuItem(driver);
		String sAutoComplete = subMenuAutoComplete.navigatesubmenuitem(SubAutoComplete);
		Assert.assertEquals(sAutoComplete, SubAutoComplete,
				"The actual submenu value does not match: " + SubAutoComplete);
		reportGenerator.verifyOutput(sAutoComplete.equals(SubAutoComplete), true,
                "AutoComplete SubMenu navigated successfully", "AutoComplete SubMenu navigation failed!");

	    // Step 5: Interact with AutoComplete widget
        reportGenerator.writeStep("AutoComplete Widget", "Selecting multiple and single colors");
		AutoCompletePage autoCompletePage = new AutoCompletePage(driver);
		List<String> colorsList = Arrays.asList(Colors.split(","));
		autoCompletePage.typeMultipleColors(colorsList);
		//System.out.println("Multiple color selection completed.");
		reportGenerator.verifyOutput(true, true, "Multiple color selection completed", "Multiple color selection failed");

		autoCompletePage.typeSingleColor(SingleColor);
		//System.out.println("Single color selection completed.");

		// Step 6: Navigate to Slider submenu and interact with the slider
        reportGenerator.writeStep("Navigate to Slider SubMenu", "Navigating to: " + SubSlider);
		SelectTheSubMenuItem subMenuSlider = new SelectTheSubMenuItem(driver);
		String sSlider = subMenuSlider.navigatesubmenuitem(SubSlider);
		Assert.assertEquals(sSlider, SubSlider, "The actual submenu value does not match: " + sSlider);
		reportGenerator.verifyOutput(sSlider, SubSlider, "Slider SubMenu navigated successfully", "Slider SubMenu navigation failed!");

		SliderPage slider = new SliderPage(driver);
		int sliderRange = Integer.parseInt(Slidermoverange);
		slider.moveSliderUsingKeyboard(sliderRange);

		// Step 7: Navigate to ProgressBar submenu and validate
        reportGenerator.writeStep("Navigate to ProgressBar SubMenu", "Navigating to: " + SubProgressbar);
		SelectTheSubMenuItem subMenuProgressBar = new SelectTheSubMenuItem(driver);
		String sProgressBar = subMenuProgressBar.navigatesubmenuitem(SubProgressbar);
		Assert.assertEquals(sProgressBar, SubProgressbar, "The actual submenu value does not match: " + SubProgressbar);
		reportGenerator.verifyOutput(sProgressBar, SubProgressbar,
	                "ProgressBar SubMenu navigated successfully", "ProgressBar SubMenu navigation failed!");
		
		reportGenerator.writeStep("Navigate to ProgressBar SubMenu", "Navigating to: " + Progressbarvalue);
		ProgressBarPage progressBar = new ProgressBarPage(driver);
		String progressBarValue = progressBar.progressbar(Progressbarvalue);
		Assert.assertEquals(progressBarValue, Progressbarvalue, "Progress bar value mismatch!");
		reportGenerator.verifyOutput(progressBarValue, Progressbarvalue,
                "Progress bar value validated successfully", "Progress bar value mismatch!");

		// Step 8: Navigate to ToolTip submenu and validate
        reportGenerator.writeStep("Navigate to ToolTip SubMenu", "Navigating to: " + SubToolTip);
		SelectTheSubMenuItem subMenuToolTips = new SelectTheSubMenuItem(driver);
		String sToolTips = subMenuToolTips.navigatesubmenuitem(SubToolTip);
		Assert.assertEquals(sToolTips, SubToolTip, "The actual submenu value does not match: " + SubToolTip);
		reportGenerator.verifyOutput(sToolTips, SubToolTip, "ToolTip SubMenu navigated successfully", "ToolTip SubMenu navigation failed!");
		

//        ToolTipspage tooltips = new ToolTipspage(driver);
//        tooltips.tooltip();

		// Step 9: Navigate to SelectMenu submenu and interact
        reportGenerator.writeStep("Navigate to SelectMenu SubMenu", "Navigating to: " + SubSelectMenu);
		SelectTheSubMenuItem subMenuSelectMenu = new SelectTheSubMenuItem(driver);
		String sSelectMenu = subMenuSelectMenu.navigatesubmenuitem(SubSelectMenu);
		Assert.assertEquals(sSelectMenu, SubSelectMenu, "The actual submenu value does not match: " + SubSelectMenu);
		reportGenerator.verifyOutput(sSelectMenu, SubSelectMenu,
                "SelectMenu SubMenu navigated successfully", "SelectMenu SubMenu navigation failed!");
		
		SelectMenuPage selectMenu = new SelectMenuPage(driver);
		selectMenu.SelectValue(SelectValue);
		selectMenu.SelectOne(SelectOne);
		selectMenu.MenuColour(MenuColour);

		// Locate and interact with the dropdown input field
		 reportGenerator.writeStep("Dropdown Interaction", "Selecting color options from dropdown");
		WebElement dropdownInput = driver.findElement(By.id("react-select-6-input"));
		List<String> colorOptionsList = Arrays.asList(selectColorOptions.split(","));
		selectMenu.selectOptions(driver, dropdownInput, colorOptionsList);
		selectMenu.Standardmultiselect(Standardmultiselect);
		 reportGenerator.verifyOutput(true, true, "Standard multi-select completed successfully", "Standard multi-select failed");
    }	
}
