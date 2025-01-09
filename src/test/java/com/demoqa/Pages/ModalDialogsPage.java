package com.demoqa.Pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ModalDialogsPage {

	WebDriver sdriver;

	public ModalDialogsPage(WebDriver ldriver) {
		this.sdriver = ldriver;
		PageFactory.initElements(ldriver, this);
	}

	@FindBy(xpath = "//button[@id='showSmallModal']")
	WebElement Btn_showSmallModal;

	@FindBy(xpath = "//div[@class='modal-body']")
	WebElement txt_modalbody;

	@FindBy(xpath = "//button[@id='showLargeModal']")
	WebElement Btn_showLargeModal;

	@FindBy(xpath = "//button[@class='btn btn-primary']")
	WebElement btn_closeModal;

	public void modeldialogbox(String modal) {

		// Perform action based on the parameter ("ok" or "cancel")
		if ("Small modal".equalsIgnoreCase(modal)) {
			JavascriptExecutor js = (JavascriptExecutor) sdriver;
			js.executeScript("arguments[0].click();", Btn_showSmallModal);
			String sSmallModalbody = txt_modalbody.getText();
			sdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
			System.out.println(sSmallModalbody);
			sdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

			btn_closeModal.click();

		} else if ("Large modal".equalsIgnoreCase(modal)) {
			JavascriptExecutor js = (JavascriptExecutor) sdriver;
			js.executeScript("arguments[0].click();", Btn_showLargeModal);
			String sLargeModalbody = txt_modalbody.getText();
			System.out.println(sLargeModalbody);
			sdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(70));
			sdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
			btn_closeModal.click();

		} else {
			throw new IllegalArgumentException("Invalid action provided. Use 'ok' or 'cancel'.");
		}
	}
}
