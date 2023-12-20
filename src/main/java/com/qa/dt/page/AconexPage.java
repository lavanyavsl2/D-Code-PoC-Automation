package com.qa.dt.page;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.qa.dt.base.BaseClass;
import com.qa.dt.util.Xls_Reader;


public class AconexPage extends BaseClass{
	static Xls_Reader reader;
	
	public AconexPage() {
		reader	= new Xls_Reader();
		PageFactory.initElements(driver, this);
	}

	
	public static WebElement aconexSyncLabel() {
		return driver.findElement(By.xpath("//h6[text()='Aconex Sync Request']"));
	}
	public static WebElement runSyncbutton() {
		return driver.findElement(By.xpath("//span[text()='Run Sync']/parent::button"));
	}
	public static WebElement importFileButton() {
		return driver.findElement(By.xpath("//span[text()='Import File']/parent::button"));
	}
	public static WebElement progressStatus() {
		return driver.findElement(By.xpath("//div[text()='100%']"));
	}
	public static WebElement aconexSyncCompleteStatusMsg() {
		return driver.findElement(By.xpath("//div[text()='Aconex Sync Complete']"));
	}
	public static WebElement aconexImportCompleteStatusMsg() {
		return driver.findElement(By.xpath("//div[text()='Completed successfully!']"));
	}
	public static WebElement closebtn() {
		return driver.findElement(By.xpath("//button[@aria-label='close']"));
	}
	public static WebElement importBtn(String value) {
		return driver.findElement(By.xpath("//td[text()='"+value+"']/parent::tr/td/*[@title='Import']"));
	}
	
	/**
	 * @author Iyappan.Kasinathan
	 * @description This method used to verify the navigation aconex sync page
	 */
	public void verifyAconexSyncPageDisplayed() {
		try {
			elementDisplayed(aconexSyncLabel(),"Navigated to Aconex sync page sucessfully","Failed to navigate to aconex sync page");
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	/**
	 * @author Iyappan.Kasinathan
	 * @description This method used to Sync the files from aconex server
	 */
	public void syncRequest() {
		try {		
		elementClick(runSyncbutton());
		waitUntilElementVisibilityForSec(progressStatus(),90);
		elementDisplayed(aconexSyncCompleteStatusMsg(),"Files are synced from the Aconex server sucessfully","Failed to sync the files");
		elementClick(closebtn());
		}catch (Exception e) {
			e.printStackTrace();
			failedStep(e.getMessage());
		}
	}
	/**
	 * @author Iyappan.Kasinathan
	 * @description This method used to import the BimPk file.
	 */
	public void importFile(String fileName) {
		try {
			elementClick(importBtn(fileName));
			if(importFileButton().isDisplayed()) {
				elementClick(importFileButton());
				Thread.sleep(150000);// Import file action takes appx 3 min
				waitUntilElementVisibility(progressStatus());
			    elementDisplayed(aconexImportCompleteStatusMsg(),"Files are imported successfully","Failed to import the files");
			}else {
				failedStep("Import file dialog box doesn't appear");
			}
		}catch (Exception e) {
			e.printStackTrace();
			failedStep(e.getMessage());
		}
	}

	
	


}
