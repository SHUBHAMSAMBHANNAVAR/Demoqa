package com.demoqa.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.JavascriptExecutor;

public class SliderPage {
    public WebDriver sdriver;
    
    public SliderPage(WebDriver ldriver) {
        this.sdriver = ldriver;
        PageFactory.initElements(ldriver, this);
    }
    
    public void moveSliderUsingKeyboard(int targetValue) {
        try {
            WebElement slider = sdriver.findElement(By.cssSelector("input[type='range']"));
            
            // Set focus to slider
            slider.click();
          
            // Set value directly using JavaScript
            JavascriptExecutor js = (JavascriptExecutor) sdriver;
            js.executeScript("arguments[0].value = arguments[1]", slider, String.valueOf(targetValue));
            js.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", slider);
            js.executeScript("arguments[0].dispatchEvent(new Event('change', { bubbles: true }));", slider);
            
            System.out.println("Slider value set to: " + slider.getAttribute("value"));
            
        } catch (Exception e) {
            System.out.println("Error setting slider value: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
//public void moveSliderToTargetValue(int targetValue) {
//    try {
//        WebElement slider = sdriver.findElement(By.cssSelector("input[type='range']"));
//        
//        // Get slider width and calculate offset
//        int width = slider.getSize().getWidth();
//        int currentValue = Integer.parseInt(slider.getAttribute("value"));
//        
//        // Calculate exact pixel offset needed
//        double pixelsPerStep = width / 100.0;  // Assuming slider range is 0-100
//        int pixelsToMove = (int)((targetValue - currentValue) * pixelsPerStep);
//        
//        // Use Actions class to move slider
//        Actions actions = new Actions(sdriver);
//        actions.clickAndHold(slider)
//               .moveByOffset(-width/2, 0)  // Move to start
//               .moveByOffset(pixelsToMove, 0)  // Move to target
//               .release()
//               .perform();
//        
//        // Verify movement with JavaScript
//        JavascriptExecutor js = (JavascriptExecutor) sdriver;
//        js.executeScript("arguments[0].value = arguments[1]", slider, String.valueOf(targetValue));
//        js.executeScript("arguments[0].dispatchEvent(new Event('change', { bubbles: true }));", slider);
//        
//        // Print final value
//        String finalValue = slider.getAttribute("value");
//        System.out.println("Final slider value: " + finalValue);
//        
//    } catch (Exception e) {
//        System.out.println("Error moving slider: " + e.getMessage());
//        e.printStackTrace();
//    }
//}
