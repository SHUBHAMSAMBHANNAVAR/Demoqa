package com.demoqa.Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;



public class DragabbleStyle {
    private WebDriver driver;

    public DragabbleStyle(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void draggableXAxisRestricted(int pixelsToDrag) {
        try {
        	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
            WebElement preventPropagation = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@data-rb-event-key='axisRestriction']")));
            preventPropagation.click();

            WebElement draggableElement = driver.findElement(By.id("restrictedX"));
            Actions actions = new Actions(driver);
            actions.dragAndDropBy(draggableElement, pixelsToDrag, 0).perform();

            logStyleAttribute(draggableElement);
        } catch (Exception e) {
            System.err.println("Error in X-axis drag: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void draggableYAxisRestricted(int pixelsToDrag) {
        try {
        	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            System.out.println("Attempting to locate Y-axis restricted draggable element...");
            WebElement draggableElement = driver.findElement(By.id("restrictedY"));
            System.out.println("Element located successfully!");

            System.out.println("Attempting to drag element along Y-axis by " + pixelsToDrag + " pixels...");
            Actions actions = new Actions(driver);
            
            actions.dragAndDropBy(draggableElement, 0, pixelsToDrag).perform();
            
            System.out.println("Drag action performed!");

            logStyleAttribute(draggableElement);
        } catch (Exception e) {
            System.err.println("Error in Y-axis drag: " + e.getMessage());
            e.printStackTrace();
        }
    }


    private void logStyleAttribute(WebElement element) {
        String styleAttribute = element.getAttribute("style");
        System.out.println("Updated 'style' attribute: " + styleAttribute);
    }
    
    
    public void dragWithinContainer(int offsetX, int offsetY) {
        try {
        	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
             WebElement preventPropagation = wait.until(ExpectedConditions.elementToBeClickable(
                 By.xpath("//a[@id='draggableExample-tab-containerRestriction']")));
             preventPropagation.click();

        	
        	        	        	        	
            // Locate the container and the draggable element
            WebElement container = driver.findElement(By.id("containmentWrapper"));
            WebElement draggable = container.findElement(By.cssSelector(".draggable.ui-widget-content.ui-draggable"));

            // Get the dimensions of the container
            Dimension containerSize = container.getSize();
            Point containerLocation = container.getLocation();

            // Get the current position of the draggable element
            Point draggableLocation = draggable.getLocation();

            // Calculate the maximum allowed offset
            int maxOffsetX = containerSize.getWidth() - (draggableLocation.getX() - containerLocation.getX());
            int maxOffsetY = containerSize.getHeight() - (draggableLocation.getY() - containerLocation.getY());

            // Adjust the offsets to stay within boundaries
            offsetX = Math.min(offsetX, maxOffsetX);
            offsetY = Math.min(offsetY, maxOffsetY);

            // Perform the drag operation
            Actions actions = new Actions(driver);
            actions.dragAndDropBy(draggable, offsetX, offsetY).perform();

            // Log the new position
            Point newLocation = draggable.getLocation();
            System.out.println("New position of draggable: " + newLocation);

        } catch (Exception e) {
            System.err.println("Error in dragging within container: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void testDraggableElements() {
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
         WebElement preventPropagation = wait.until(ExpectedConditions.elementToBeClickable(
             By.xpath("//a[@id='draggableExample-tab-cursorStyle']")));
         preventPropagation.click();

    	
    	
        // Locate the draggable elements
    	//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        WebElement cursorCenter = wait.until(ExpectedConditions.elementToBeClickable(
            By.id("cursorCenter")));
        WebElement cursorTopLeft = wait.until(ExpectedConditions.elementToBeClickable(
            By.id("cursorTopLeft")));
        WebElement cursorBottom = wait.until(ExpectedConditions.elementToBeClickable(
            By.id("cursorBottom")));

        // Verify initial state
        Assert.assertTrue(cursorCenter.getText().contains("stick to the center"));
        Assert.assertTrue(cursorTopLeft.getText().contains("at top left"));
        Assert.assertTrue(cursorBottom.getText().contains("at bottom"));

        // Perform drag and drop operations
        dragAndDrop(cursorCenter, 50, 50);
        dragAndDrop(cursorTopLeft, -100, -100);
        dragAndDrop(cursorBottom, 0, 100);

        // Add verification steps as needed
        verifyElementPosition(cursorCenter);
        verifyElementPosition(cursorTopLeft);
        verifyElementPosition(cursorBottom);
    }

    private void dragAndDrop(WebElement element, int xOffset, int yOffset) {
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Actions actions = new Actions(driver);
        actions.clickAndHold(element)
               .moveByOffset(xOffset, yOffset)
               .release()
               .build()
               .perform();
        
        // Add small delay to allow animation to complete
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void verifyElementPosition(WebElement element) {
        // Get element's current position
        String style = element.getAttribute("style");
        Assert.assertTrue(style.contains("position: relative"),
            "Element should have relative positioning");
    }
    
    
}

