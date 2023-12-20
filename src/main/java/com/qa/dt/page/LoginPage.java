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
public class LoginPage extends BaseClass{
	static Xls_Reader reader;
	
	public LoginPage() {
		reader	= new Xls_Reader();
		PageFactory.initElements(driver, this);
	}

	
	public static WebElement invicaraLogo() {
		return driver.findElement(By.xpath("(//div[@class='logo'])[1]"));
	}	
	public static WebElement loginButton() {
		return driver.findElement(By.xpath("//button[text()='Login']"));
	}
	public static WebElement userName() {
		return driver.findElement(By.xpath("//input[@id='l-username']"));
	}
	public static WebElement passWord() {
		return driver.findElement(By.xpath("//input[@id='password']"));
	}	
	public static WebElement invalidUserNameErrorMsg() {
		return driver.findElement(By.xpath("//div[@class='alert alert-danger alert-warning']"));
	}
	public static WebElement SignUpButton() {
		return driver.findElement(By.xpath("(//button[@class='secondary'])[1]"));
	}
	public static WebElement SignInHeaderName() {
		return driver.findElement(By.xpath("//h1[text()='Sign In']"));
	}
	public static WebElement logOutButton() {
		return driver.findElement(By.xpath("//span[text()='Logout']"));
	}	
	public static WebElement userAccountDropdownButton() {
		return driver.findElement(By.xpath("//div[@class='session-dropdown']"));
	}
	

	public boolean invicaroLogoIsDisplayed() throws Exception {
		boolean displayed = invicaraLogo().isDisplayed();
		return displayed;
	}
	public boolean SignInNameIsDisplayed() throws Exception {
		boolean displayed = SignInHeaderName().isDisplayed();
		return displayed;
	}
	/**
	 * @author 
	 * @description This method is used to launch browser
	 */
	public void browserLaunch() throws IOException, Exception {
		try {
		browser_LaunchIgnoreCase(loadProperties().getProperty("Browser"));
		Properties loadProperties = loadProperties();
		System.out.println(loadProperties.getProperty("RfUrl"));
		launchURL(loadProperties.getProperty("RfUrl"));
		}catch (Exception e) {
			e.printStackTrace();
			failedStep(e.getMessage());
		}
	}
	/**
	 * @author 
	 * @description This method is used to log into the application
	 */
	public void loginTheApplication(String username, String password) throws Exception {
		try {
			browserLaunch();
			enterUserName(username);
			Thread.sleep(10000);
			enterPassword(password);
		} catch (Exception e) {
			e.printStackTrace();
			failedStep(e.getMessage());
		}
	}
	/**
	 * Enter The User Name For Login
	 * 
	 * @param uName Login User Name
	 * @throws Exception
	 */
	public void enterUserName(String uName) throws Exception {
		elementSendKeys(userName(), uName);
		elementClick(loginButton());
	}
	public void enterPassword(String pwd) throws Exception {
		elementSendKeys(passWord(), pwd);
		elementClick(loginButton());
	}

	/**
	 * To Click The SignUp Button
	 * @return
	 * @throws Exception
	 */
	
	public void clickSignUp() throws Exception {
		elementClick(SignUpButton());
	}
	/**
	 * @author Iyappan.Kasinathan
	 * @description This method used to log into the application
	 */
	public void logIntoApplication() throws Throwable {		
		browserLaunch();
		enterUserName(loadProperties().getProperty("Rfusername")); 
		Thread.sleep(10000);
		enterPassword(loadProperties().getProperty("RfPassword"));
		ExtentManager.test.log(Status.PASS, "Logged into the D-Code application successfully by "+loadProperties().getProperty("Rfusername"));
	}

	/**
	 * @author Iyappan.Kasinathan
	 * @description This method used to logout from the application
	 */
	public void logout() {
		try {
			driver.navigate().refresh();
			waitForpageLoad();
			waitUntilElementVisibility(userAccountDropdownButton());
			elementClick(userAccountDropdownButton());
			if(logOutButton().isDisplayed()) {
				elementClick(logOutButton());
				waitUntilElementVisibility(SignInHeaderName());
				Assert.assertEquals(SignInNameIsDisplayed(), true);		
				passedStep("Logged out from the application successfully");
			}else {
				failedStep("User profile dropdown is not displayed");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			failedStep(e.getMessage());
		}
	}


}
