package com.qa.dt.page;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import com.aventstack.extentreports.Status;
import com.qa.dt.base.BaseClass;
import com.qa.dt.util.ExtentManager;
import com.qa.dt.util.Xls_Reader;

/**
 * Login Page Elements
 * 
 * @author IyappanKasinathan
 *
 */
public class HomePage extends BaseClass {
	static Xls_Reader reader;

	public HomePage() {
		reader = new Xls_Reader();
		PageFactory.initElements(driver, this);
	}

	public static WebElement sidePaneMenu(String mainMenu) {
		return driver.findElement(By.xpath("//div[text()='" + mainMenu + "']/ancestor::li/div/i"));
	}

	public static WebElement selectsidePaneMenu(String subMenu) {
		return driver.findElement(By.xpath("//span[text()='" + subMenu + "']"));
	}

	public static WebElement invicaraLogo() {
		return driver.findElement(By.xpath("//div[@id='logo']"));
	}
	
	public static WebElement tileNavigation(String value) {
		return driver.findElement(By.xpath("///h1[text()='"+value+"']"));
	}

	/**
	 * @author Iyappan.Kasinathan
	 * @description This method used to navigate to different services
	 * @param mainMenu Side pane main menu
	 * @param subMenu  Side pane side menu
	 */
	public void navigateToServicesThroughSidePane(String mainMenu, String subMenu) {
		try {
			elementClick(sidePaneMenu(mainMenu));
			elementClick(selectsidePaneMenu(subMenu));
			moveToElement(invicaraLogo());
		} catch (Exception e) {
			e.printStackTrace();
			failedStep(e.getMessage());
		}
	}
	/**
	 * @author Iyappan.Kasinathan
	 * @description This method used to navigate to different services
	 * @param mainMenu Navigation service name
	 */
	public void navigateToServicesThroughTiles(String value) {
		try {
			String url = "https://dcode-poc.pages.dev/dcode/#/";
			String currentUrl= getCurrenturl();
			if(!url.equalsIgnoreCase(currentUrl)){
				driver.get(url);
			}
			elementClick(tileNavigation(value));
		} catch (Exception e) {
			e.printStackTrace();
			failedStep(e.getMessage());
		}
	}

}
