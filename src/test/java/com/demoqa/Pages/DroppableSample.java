package com.demoqa.Pages;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DroppableSample {
    public WebDriver sdriver;

    public DroppableSample(WebDriver ldriver) {
        this.sdriver = ldriver;
 
        PageFactory.initElements(ldriver, this);
    }

    public void sampleDragAndDrop() {
        try {
            WebDriverWait wait = new WebDriverWait(sdriver, Duration.ofSeconds(50));
            WebElement sourceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("draggable")));
            WebElement targetElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("droppable")));

//            // Debugging: Print element visibility
//            System.out.println("Source Element Found: " + sourceElement.isDisplayed());
//            System.out.println("Target Element Found: " + targetElement.isDisplayed());
            
            ((JavascriptExecutor) sdriver).executeScript("arguments[0].scrollIntoView(true);", sourceElement);
            ((JavascriptExecutor) sdriver).executeScript("arguments[0].scrollIntoView(true);", targetElement);
            
            
            // Create an Actions object
            Actions actions = new Actions(sdriver);

            actions.clickAndHold(sourceElement)
            .pause(Duration.ofSeconds(2))
            .moveToElement(targetElement)
            .pause(Duration.ofSeconds(2))
            .release()
            .build()
            .perform();
        // Print success message
            System.out.println("Drag and drop performed successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
    public void testPreventPropagation() throws Exception {
    	try {
    		 WebDriverWait wait = new WebDriverWait(sdriver, Duration.ofSeconds(50));
            
            // Navigate to Prevent Propagation tab
            WebElement preventPropagation = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@data-rb-event-key='preventPropogation']")));
            preventPropagation.click();
            
            // Wait for page load after clicking tab
            Thread.sleep(1000);

            // Locate elements with retry mechanism
            WebElement dragBox = waitForElementWithRetry(By.id("dragBox"));
            WebElement notGreedyOuterBox = waitForElementWithRetry(By.id("notGreedyDropBox"));
            WebElement notGreedyInnerBox = waitForElementWithRetry(By.id("notGreedyInnerDropBox"));

            // Ensure elements are visible
            scrollIntoView(dragBox);
            scrollIntoView(notGreedyOuterBox);
            scrollIntoView(notGreedyInnerBox);

            Actions actions = new Actions(sdriver);

            // Test not greedy outer box
            performDragAndDropWithRetry(actions, dragBox, notGreedyOuterBox);
            
            // Verify drop using multiple approaches
            verifyDropWithRetry(notGreedyOuterBox);

        } catch (Exception e) {
            System.err.println("Test failed: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    private WebElement waitForElementWithRetry(By locator) {
    	 WebDriverWait wait = new WebDriverWait(sdriver, Duration.ofSeconds(50));
        int retries = 3;
        WebElement element = null;
        
        while (retries > 0) {
            try {
                element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
                wait.until(ExpectedConditions.visibilityOf(element));
                return element;
            } catch (Exception e) {
                retries--;
                if (retries == 0) throw e;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        return element;
    }

    private void performDragAndDropWithRetry(Actions actions, WebElement source, WebElement target) {
        int retries = 3;
        while (retries > 0) {
            try {
                // Clear any existing classes/styles
                ((JavascriptExecutor) sdriver).executeScript(
                    "arguments[0].className = arguments[0].className.replace('dropped', '')", target);
                
                // Ensure elements are ready
                waitForElementToBeReady(source);
                waitForElementToBeReady(target);

                // Perform drag and drop
                actions.clickAndHold(source)
                       .pause(Duration.ofMillis(500))
                       .moveToElement(target)
                       .pause(Duration.ofMillis(500))
                       .release()
                       .build()
                       .perform();

                Thread.sleep(1000);
                return;
            } catch (Exception e) {
                retries--;
                if (retries == 0) throw new RuntimeException("Drag and drop failed after retries", e);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    private void waitForElementToBeReady(WebElement element) {
    	 WebDriverWait wait = new WebDriverWait(sdriver, Duration.ofSeconds(50));
        wait.until(driver -> {
            try {
                return element.isDisplayed() && element.isEnabled();
            } catch (StaleElementReferenceException e) {
                return false;
            }
        });
    }

    private void verifyDropWithRetry(WebElement dropTarget) throws Exception {
    	 WebDriverWait wait = new WebDriverWait(sdriver, Duration.ofSeconds(50));
        int retries = 3;
        
        while (retries > 0) {
            try {
                // Wait for any animations to complete
                Thread.sleep(1000);
                
                // Check for visual changes
                Boolean hasDroppedClass = (Boolean) ((JavascriptExecutor) sdriver).executeScript(
                    "return arguments[0].classList.contains('dropped') || " +
                    "arguments[0].classList.contains('ui-droppable-active') || " +
                    "arguments[0].classList.contains('ui-state-highlight')", dropTarget);
                
                // Check element attributes
                String droppedState = dropTarget.getAttribute("data-dropped");
                String styles = dropTarget.getAttribute("style");
                
                if (hasDroppedClass || "true".equals(droppedState) || 
                    (styles != null && styles.contains("background"))) {
                    return;
                }
                
                retries--;
                if (retries == 0) {
                    throw new AssertionError("Drop verification failed - no visual changes detected");
                }
            } catch (Exception e) {
                retries--;
                if (retries == 0) throw e;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    private void scrollIntoView(WebElement element) {
        try {
            ((JavascriptExecutor) sdriver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", 
                element);
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    public void performDragAndDropWithRevert() {
        try {
            Thread.sleep(1000);  // Consider reducing sleep time or removing it
            WebDriverWait wait = new WebDriverWait(sdriver, Duration.ofSeconds(50));

            // Navigate to Prevent Propagation tab
            WebElement preventPropagation = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@data-rb-event-key='revertable']")));
            preventPropagation.click();

            // Locate elements
            WebElement revertableElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("revertable")));
            WebElement notRevertableElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("notRevertable")));
            WebElement droppableElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("droppable")));

            // Scroll into view
            ((JavascriptExecutor) sdriver).executeScript("arguments[0].scrollIntoView(true);", droppableElement);

            // Perform drag-and-drop for 'revertable'
            Actions actions = new Actions(sdriver);
            actions.dragAndDrop(revertableElement, droppableElement).perform();
            System.out.println("Dragged 'revertable' element to the droppable area.");

            // Validate revert behavior
            if (!revertableElement.getLocation().equals(droppableElement.getLocation())) {
                System.out.println("'revertable' element reverted to its original position.");
            } else {
                System.out.println("Error: 'revertable' element did not revert!");
            }

            // Perform drag-and-drop for 'notRevertable'
            actions.dragAndDrop(notRevertableElement, droppableElement).perform();
            System.out.println("Dragged 'notRevertable' element to the droppable area.");

            // Validate 'notRevertable' stays
            if (notRevertableElement.getLocation().equals(droppableElement.getLocation())) {
                System.out.println("'notRevertable' element is in the droppable area.");
            } else {
                System.out.println("Error: 'notRevertable' element did not stay in the droppable area!");
            }
        } catch (NoSuchElementException e) {
            System.err.println("Element not found: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred during the drag-and-drop operation: " + e.getMessage());
            e.printStackTrace();
        }}
    }
