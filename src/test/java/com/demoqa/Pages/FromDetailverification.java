package com.demoqa.Pages;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FromDetailverification {
    WebDriver sdriver;

    // Constructor
    public FromDetailverification(WebDriver ldriver) {
        this.sdriver = ldriver;
        PageFactory.initElements(ldriver, this);
    }

    // Create a method for waiting for elements
    private WebElement waitForElementToBeVisible(WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(sdriver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    @FindBy(xpath = "//td[text()='Student Name']//following-sibling::td")
    WebElement txt_studentname;

    public String studentname() {
        // Wait for element visibility
        waitForElementToBeVisible(txt_studentname, 10);
        String sStudentName = txt_studentname.getText();
        return sStudentName;
    }

    @FindBy(xpath = "//td[text()='Student Email']//following-sibling::td")
    WebElement txt_StudentEmail;

    public String Email() {
        // Wait for element visibility
        waitForElementToBeVisible(txt_StudentEmail, 10);
        String sEmail = txt_StudentEmail.getText();
        return sEmail;
    }

    @FindBy(xpath = "//td[text()='Gender']//following-sibling::td")
    WebElement txt_Gender;

    public String Gender() {
        // Wait for element visibility
        waitForElementToBeVisible(txt_Gender, 10);
        String sGender = txt_Gender.getText();
        return sGender;
    }

    @FindBy(xpath = "//td[text()='Mobile']//following-sibling::td")
    WebElement txt_Mobile;

    public String Mobile() {
        // Wait for element visibility
        waitForElementToBeVisible(txt_Mobile, 10);
        String sMobile = txt_Mobile.getText();
        return sMobile;
    }

    @FindBy(xpath = "//td[text()='Address']//following-sibling::td")
    WebElement txt_Address;

    @FindBy(xpath = "//button[@id='closeLargeModal']")
    WebElement btn_Close;

    public String Address() {
        // Wait for element visibility
        waitForElementToBeVisible(txt_Address, 10);
        String sAddress = txt_Address.getText();

        // Use JavascriptExecutor to click the element after it becomes visible
        JavascriptExecutor js = (JavascriptExecutor) sdriver;
        js.executeScript("arguments[0].click();", btn_Close);

        return sAddress;
    }
}
