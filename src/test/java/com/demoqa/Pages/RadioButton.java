package com.demoqa.Pages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RadioButton {
    private WebDriver sdriver;
    private WebDriverWait wait;

    // Constructor
    public RadioButton(WebDriver ldriver) {
        this.sdriver = ldriver;
        this.wait = new WebDriverWait(sdriver, Duration.ofSeconds(10));
        PageFactory.initElements(ldriver, this);
    }

    @FindBy(xpath = "//label[contains(@class, 'custom-control-label')]")
    List<WebElement> txt_radioButtonLabel;

    @FindBy(xpath = "//input[@type='radio']")
    List<WebElement> radioButtons;

    @FindBy(xpath = "//span[contains(@class, 'text-success')]")
    WebElement txt_Rbnvalue;

    public void radiobtn(String sRadio) throws InterruptedException {
        for (int i = 0; i < txt_radioButtonLabel.size(); i++) {
            WebElement label = txt_radioButtonLabel.get(i);
            if (label.getText().equalsIgnoreCase(sRadio)) {
                // Use JavascriptExecutor to ensure the click
                JavascriptExecutor jsExecutor = (JavascriptExecutor) sdriver;
                jsExecutor.executeScript("arguments[0].click();", label);
                Thread.sleep(3000);
                break;
            }
        }
    }

    public String rbnverification() {
        wait.until(ExpectedConditions.visibilityOf(txt_Rbnvalue));
        String sValue = txt_Rbnvalue.getText();
        System.out.println("The Selected radio button: " + sValue);
        return sValue;
    }
}
