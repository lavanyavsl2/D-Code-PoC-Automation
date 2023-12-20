package com.qa.dt.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.qa.dt.base.BaseClass;
import com.qa.dt.util.Xls_Reader;


public class ProjectSelectionPage extends BaseClass {
	static Xls_Reader reader;

	public ProjectSelectionPage() {
		reader = new Xls_Reader();
		PageFactory.initElements(driver, this);
	}

	public static WebElement projectSelectionLabel() {
		return driver.findElement(By.xpath("//span[text()='Project Selection']"));
	}

	public static WebElement selectProjectDD() {
		return driver.findElement(
				By.xpath("//div[@class='select__value-container select__value-container--has-value css-1hwfws3']"));
	}

	public static WebElement chooseProject(String projectName) {
		return driver.findElement(By.xpath("//div[text()='" + projectName + "']"));
	}

	public static WebElement loadProjectBtn() {
		return driver.findElement(By.xpath("//button[text()='Load Project']"));
	}

	/**
	 * @author Iyappan.Kasinathan
	 * @description This method used to select and load the project
	 */
	public void selectProject() {
		try {
			elementDisplayed(projectSelectionLabel(), "Failed to load project selection page");
			elementClick(selectProjectDD());
			chooseProject(loadProperties().getProperty("ProjectName"));
			elementClick(loadProjectBtn());
			Thread.sleep(1000);
			if (loadProjectBtn().isDisplayed()) {
				elementClick(loadProjectBtn());
			}
			passedStep(loadProperties().getProperty("ProjectName") + " Project loaded into the application");
		} catch (Exception e) {
			e.printStackTrace();
			failedStep(e.getMessage());
		}
	}

}
