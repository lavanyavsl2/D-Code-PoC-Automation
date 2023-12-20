package com.qa.dt.page;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.qa.dt.base.BaseClass;
import com.qa.dt.util.Xls_Reader;

public class FilesPage extends BaseClass{
	static Xls_Reader reader;
	
	public FilesPage() {
		reader	= new Xls_Reader();
		PageFactory.initElements(driver, this);
	}

	
	public static WebElement aconexDownloadsFolder() {
		return driver.findElement(By.xpath("//span[text()='Aconex Downloads']"));
	}
	
	public static List<WebElement> noOfFiles() {
		return driver.findElements(By.xpath("//div[@class='listDiv']/div[@id]"));
	}
	
	/**
	 * @author Iyappan.Kasinathan
	 * @description This method used to verify the files are synced from Aconex server
	 */
	public void verifyFilesSynced() {
		try {		
		elementDisplayed(aconexDownloadsFolder(),"Aconex downloads folder is displayed as expected","Aconex downloads folder is not synced");		
		javascriptClick(aconexDownloadsFolder());
		int files = noOfFiles().size();
		Assert.assertEquals(files, 2);
		passedStep(files+" files are downloaded under aconex downloads folder");
		}catch (Exception e) {
			e.printStackTrace();
			failedStep(e.getMessage());
		}
	}

	
	


}
