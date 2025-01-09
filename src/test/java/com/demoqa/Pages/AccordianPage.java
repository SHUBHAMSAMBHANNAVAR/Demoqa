package com.demoqa.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AccordianPage {
    public WebDriver sdriver;

    public AccordianPage(WebDriver ldriver) {
        this.sdriver = ldriver;
        PageFactory.initElements(ldriver, this);
        // Setting implicit wait for the entire session
        sdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));  // Example: 10 seconds
    }

    public void accordian() {
        try {
            // Step 3: Verify that Section 1 content is displayed
            WebDriverWait wait = new WebDriverWait(sdriver, Duration.ofSeconds(10)); // Explicit wait

            // Wait for section 1 content to be visible
            WebElement section1Content = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='section1Content']")));
            if (section1Content.isDisplayed()) {
                System.out.println("Section 1 content is displayed successfully.");
            } else {
                System.out.println("Section 1 content is NOT displayed.");
            }

            // Step 4: Locate and click on Section 2 header to expand it
            WebElement section2Header = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='section2Heading']")));
            ((JavascriptExecutor) sdriver).executeScript("arguments[0].scrollIntoView(true);", section2Header);
            section2Header.click();
            System.out.println("Clicked Section 2 Header.");

            // Step 5: Verify that Section 2 content is displayed
            WebElement section2Content = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='section2Content']")));
            if (section2Content.isDisplayed()) {
                System.out.println("Section 2 content is displayed successfully.");
            } else {
                System.out.println("Section 2 content is NOT displayed.");
            }

            // Step 6: Locate and click on Section 3 header to expand it
            WebElement section3Header = sdriver.findElement(By.xpath("//div[@id='section3Heading']"));
            ((JavascriptExecutor) sdriver).executeScript("arguments[0].scrollIntoView(true);", section3Header);
            section3Header.click();
            System.out.println("Clicked Section 3 Header.");

            // Step 7: Verify that Section 3 content is displayed
            WebElement section3Content = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='section3Content']")));
            if (section3Content.isDisplayed()) {
                System.out.println("Section 3 content is displayed successfully.");
            } else {
                System.out.println("Section 3 content is NOT displayed.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
