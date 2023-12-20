package com.qa.dt.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.qa.dt.base.BaseClass;
import com.qa.dt.util.Xls_Reader;


public class AdminPage extends BaseClass {
	static Xls_Reader reader;

	public AdminPage() {
		reader = new Xls_Reader();
		PageFactory.initElements(driver, this);
	}

	public static WebElement modelSelection() {
		return driver.findElement(By.xpath("//div[text()='EX11034-INV-Federated (bimpk)']"));
	}


	/**
	 * @author Iyappan.Kasinathan
	 * @description This method used to select and load the project
	 */
	public void verifySelectionOfModel() {
		try {
			if(modelSelection().isDisplayed()) {
				passedStep("Bimpk files are available in model manager");
			}
		} catch (Exception e) {
			e.printStackTrace();
			failedStep(e.getMessage());
		}
	}

}
