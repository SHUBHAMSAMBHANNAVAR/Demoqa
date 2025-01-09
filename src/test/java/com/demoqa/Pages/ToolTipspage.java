package com.demoqa.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;

public class ToolTipspage {
    public WebDriver sdriver;

    public ToolTipspage(WebDriver ldriver) {
        this.sdriver = ldriver;
        PageFactory.initElements(sdriver, this);
    }

    public void tooltip() {
        // Set explicit wait
        WebDriverWait wait = new WebDriverWait(sdriver, Duration.ofSeconds(60));

        // Set implicit wait (though explicit wait is preferred for specific conditions)
        sdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        // Locate the element that triggers the tooltip (e.g., a button or icon)
        WebElement hoverElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Hover me to see']")));

        // Check if the element is displayed before performing the hover action
        if (hoverElement.isDisplayed()) {
            // Ensure the element is in the viewport (if necessary)
            ((JavascriptExecutor) sdriver).executeScript("arguments[0].scrollIntoView(true);", hoverElement);

            JavascriptExecutor js = (JavascriptExecutor) sdriver;
            js.executeScript("arguments[0].click();", hoverElement);
            // Using Actions class to hover
            Actions actions = new Actions(sdriver);

            // Try to hover
            actions.moveToElement(hoverElement).perform();  // Perform the hover action

            // Optionally, if the hover doesn't work, use JavaScript to hover over the element
            //((JavascriptExecutor) sdriver).executeScript("arguments[0].dispatchEvent(new MouseEvent('mouseover', { bubbles: true }));", hoverElement);
            
        } else {
            System.out.println("The element is not displayed");
        }

        // Wait for the tooltip to appear after hover action
        WebElement tooltip = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@aria-describedby='buttonToolTip']")));

        // Verify that the tooltip is visible
        if (tooltip.isDisplayed()) {
            System.out.println("Tooltip successfully displayed after hover.");
        } else {
            System.out.println("Tooltip not displayed.");
        }
    }
}
