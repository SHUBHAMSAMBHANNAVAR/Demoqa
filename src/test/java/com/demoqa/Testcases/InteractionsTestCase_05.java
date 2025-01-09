package com.demoqa.Testcases;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.demoqa.Pages.DragabbleStyle;
import com.demoqa.Pages.DroppableSample;
import com.demoqa.Pages.ResizablePage;
import com.demoqa.Pages.SelectMenuItemPage;
import com.demoqa.Pages.SelectTheSubMenuItem;
import com.demoqa.Pages.SelectablePage;
import com.demoqa.Utilities.BrowserInitialization;
import com.demoqa.Utilities.ExcelDataProvider;

public class InteractionsTestCase_05 extends BrowserInitialization {

	/**
	 * Sets up the test environment by initializing the Excel sheet name for the
	 * "Interactions" test case.
	 */
	@BeforeClass
	public void setup() {
		// Set the sheet name dynamically for data-driven testing
		ExcelDataProvider.setSheetName("Interactions");
	}

	/**
	 * Tests the interaction elements in the DemoQA application.
	 * 
	 * @param Menu          Main menu to navigate.
	 * @param SubMenu       Submenu to select.
	 * @param selectOptions Options to select from the list.
	 * @param selectList    Grid items to select.
	 * @param SubResizable  Submenu for resizing.
	 * @param Xaxise        X-axis value for resizing.
	 * @param Yaxise        Y-axis value for resizing.
	 * @param droppable     Submenu for droppable actions.
	 * @param dragabble     Submenu for draggable actions.
	 * @param Yaxisedrag    Y-axis value for dragging.
	 * @param Xaxisedrag    X-axis value for dragging.
	 * @param Top           Top margin value for dragging within a container.
	 * @param Left          Left margin value for dragging within a container.
	 * @throws Exception If any error occurs during the test execution.
	 */
	@Test(dataProvider = "Excel", dataProviderClass = ExcelDataProvider.class)
	public void Interactions(String Menu, String SubMenu, String selectOptions, String selectList, String SubResizable,
			String Xaxise, String Yaxise, String droppable, String dragabble, String Yaxisedrag, String Xaxisedrag,
			String Top, String Left) throws Exception {

		reportGenerator.getAttributes("DemoQA", "Interactions", "Shubham", "1005");
		
		
		// Step 1: Navigate to the specified Menu and verify the Menu
		reportGenerator.writeStep("Navigate to Menu", "Navigating to Menu: " + Menu);
		SelectMenuItemPage menuPage = new SelectMenuItemPage(driver);
		menuPage.navigateMenuItemByText(Menu);
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + SubMenu + "']")).isDisplayed(),
				SubMenu + " is not displayed!");
		reportGenerator.verifyOutput(true, menuPage.found, "Successfully selected the menu item",
				"Failed to select the menu item");

		// Step 2: Navigating to the specified Menu and verify the submenu
		reportGenerator.writeStep("Navigate to SubMenu", "Navigating to SubMenu: " + SubMenu);
		SelectTheSubMenuItem subMenuPage = new SelectTheSubMenuItem(driver);
		String sValue = subMenuPage.navigatesubmenuitem(SubMenu);
		Assert.assertEquals(sValue, SubMenu, "The actual submenu value does not match the expected value: " + sValue);
		reportGenerator.verifyOutput(sValue, SubMenu, "Successfully navigated to SubMenu: " + SubMenu,
				"Failed to navigate to SubMenu: " + SubMenu);

		// Step 3: Select items from the list and grid
		reportGenerator.writeStep("Select Items", "Selecting items: " + selectOptions + " and " + selectList);
		SelectablePage Selectable = new SelectablePage(driver);
		List<String> selectableList = Arrays.asList(selectOptions.split(","));
		Selectable.selectListItems(selectableList);

		List<String> SelectList = Arrays.asList(selectList.split(","));
		Selectable.selectGridItems(SelectList);
		reportGenerator.verifyOutput(true, true, "Items selected successfully", "Item selection failed");

		// Step 4: Perform resize operation
		reportGenerator.writeStep("Resize Element", "Resizing with X: " + Xaxise + ", Y: " + Yaxise);
		SelectTheSubMenuItem subResizable = new SelectTheSubMenuItem(driver);
		String sResizable = subResizable.navigatesubmenuitem(SubResizable);
		Assert.assertEquals(sResizable, SubResizable,
				"The actual submenu value does not match the expected value: " + SubResizable);
		reportGenerator.verifyOutput(sResizable, SubResizable, "Navigated to Resizable successfully",
				"Failed to navigate to Resizable");
        
		
		ResizablePage Resizable = new ResizablePage(driver);
		int Xnumber = Integer.parseInt(Xaxise);
		int Ynumber = Integer.parseInt(Yaxise);
		Resizable.resizable(Xnumber, Ynumber);
		

		// Step 5: Perform drag-and-drop operation
		reportGenerator.writeStep("Drag and Drop", "Performing drag and drop");
		SelectTheSubMenuItem subDroppable = new SelectTheSubMenuItem(driver);
		String sDroppable = subDroppable.navigatesubmenuitem(droppable);
		Assert.assertEquals(sDroppable, droppable,"The actual submenu value does not match the expected value: " + droppable);
		reportGenerator.verifyOutput(sDroppable, droppable, "Navigated to Droppable successfully","Failed to navigate to Droppable");

		DroppableSample Draganddrop = new DroppableSample(driver);
		Draganddrop.sampleDragAndDrop();
		Draganddrop.testPreventPropagation();
	
		// Perform draggable operations
		reportGenerator.writeStep("Draggable Operations", "Performing draggable actions");
		SelectTheSubMenuItem subDragabble = new SelectTheSubMenuItem(driver);
		String sDragabble = subDragabble.navigatesubmenuitem(dragabble);
		Assert.assertEquals(sDragabble, dragabble,
				"The actual submenu value does not match the expected value: " + dragabble);
		reportGenerator.verifyOutput(true, true, "Droppable action verified successfully","Droppable action verification failed");

		// Step 6: Handle Dragabble Page
		reportGenerator.writeStep("Handle Dragabble Page","Dragging element to X: " + Xaxisedrag + ", Y: " + Yaxisedrag);
		DragabbleStyle Dragabble = new DragabbleStyle(driver);
		// X-axis restricted drag
		int iYaxise = Integer.parseInt(Yaxisedrag);
		Dragabble.draggableXAxisRestricted(iYaxise);

		// Y-axis restricted drag
		int iXaxise = Integer.parseInt(Xaxisedrag);
		Dragabble.draggableYAxisRestricted(iXaxise);

		// Drag within a container
		int iTop = Integer.parseInt(Top);
		int iLeft = Integer.parseInt(Left);
		//Dragabble.dragWithinContainer(iTop, iLeft);
		Dragabble.testDraggableElements();
		reportGenerator.verifyOutput(true, true, "Dragging verified successfully", "Dragging verification failed");
	}
}
