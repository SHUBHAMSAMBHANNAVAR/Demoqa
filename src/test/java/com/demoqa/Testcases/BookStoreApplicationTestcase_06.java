package com.demoqa.Testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.demoqa.Pages.ClickOptionInBookStore;
import com.demoqa.Pages.LoginPage;
import com.demoqa.Pages.SelectMenuItemPage;
import com.demoqa.Pages.SelectTheSubMenuItem;
import com.demoqa.Utilities.BrowserInitialization;
import com.demoqa.Utilities.ExcelDataProvider;

public class BookStoreApplicationTestcase_06 extends BrowserInitialization {

    /**
     * Setup method to set the sheet name for the Excel data provider before running
     * the test.
     */
    @BeforeClass
    public void setup() {
        // Set the sheet name here dynamically before running the test
        ExcelDataProvider.setSheetName("BookStore");
    }

    /**
     * Test method that executes the test cases based on the data provided through
     * Excel. It validates the navigation and interactions on browser windows,
     * alerts, frames, and modal dialogs.
     * 
     * @param Menu        The menu to navigate to.
     * @param SubMenu     The submenu to navigate under the Alerts section.
     * @param UserName    The username for login.
     * @param Password    The password for login.
     * @param BookName    The book name to select.
     */
    @Test(dataProvider = "Excel", dataProviderClass = ExcelDataProvider.class)
    public void BookStore(String Menu, String SubMenu, String UserName, String Password, String BookName) {

    	 reportGenerator.getAttributes("DemoQA", "BookStore", "Shubham", "1006");
    	// Step 1: Create an instance of SelectMenuItemPage to navigate to the menu item
        reportGenerator.writeStep("Create an instance of SelectMenuItemPage", "Navigate to menu item.");
        SelectMenuItemPage menuPage = new SelectMenuItemPage(driver);
        menuPage.navigateMenuItemByText(Menu);
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + SubMenu + "']")).isDisplayed(),
                SubMenu + " is not displayed!");
        reportGenerator.verifyOutput(true, menuPage.found, "Successfully selected the menu item",
                "Failed to select the menu item");

        // Step 2: Navigate to the submenu item
        reportGenerator.writeStep("Create an instance of SelectTheSubMenuItem", "Navigate to submenu item.");
        SelectTheSubMenuItem subMenuPage = new SelectTheSubMenuItem(driver);
        String sValue = subMenuPage.navigatesubmenuitem(SubMenu);
        Assert.assertEquals(sValue, SubMenu, "The actual submenu value does not match the expected value: " + sValue);
        reportGenerator.verifyOutput(sValue, SubMenu, "Successfully selected the submenu item",
                "Failed to select the submenu item");

        // Step 3: Login to the bookstore
        reportGenerator.writeStep("Login to the bookstore", "Navigate to Login page.");
        LoginPage login = new LoginPage(driver);
        String sPass = login.login(UserName, Password);
        Assert.assertEquals(sPass, Password, "The actual password does not match the expected password: " + Password);
        reportGenerator.verifyOutput(sPass, Password, "Successful login to bookstore", "Failed login to bookstore");

        // Step 4: Select the book in the list
        reportGenerator.writeStep("Select the book in the list", "Navigate to book.");
        ClickOptionInBookStore inBookStore = new ClickOptionInBookStore(driver);
        String sBook = inBookStore.SelectBook(BookName);
        Assert.assertEquals(sBook, BookName, "The actual book does not match the expected book: " + BookName);
        reportGenerator.verifyOutput(sBook, BookName, "Successfully selected the book", "Failed to select the book");

       
    }
}
