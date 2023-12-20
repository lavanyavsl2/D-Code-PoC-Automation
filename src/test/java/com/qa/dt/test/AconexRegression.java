package com.qa.dt.test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.dt.base.BaseClass;
import com.qa.dt.page.AconexPage;
import com.qa.dt.page.AdminPage;
import com.qa.dt.page.FilesPage;
import com.qa.dt.page.HomePage;
import com.qa.dt.page.LoginPage;
import com.qa.dt.page.ProjectSelectionPage;

@Listeners(com.qa.dt.util.ListenerClass.class)
public class AconexRegression extends BaseClass{ 	
	LoginPage lp;
	ProjectSelectionPage ps;
	HomePage hp;
	AconexPage ap;
	FilesPage fp;
	AdminPage adp;
	
	@BeforeMethod
	public void precondition() {
		lp= new LoginPage();
		ps = new ProjectSelectionPage();
		hp = new HomePage();
		ap = new AconexPage();
		fp = new FilesPage();
		adp = new AdminPage();
	}
	@Test(priority = 1,groups = "Regression")
	public void verifyFilesSyncedAndImported() throws Throwable {	
		stepInfo("TC-01: Verify the files are synced from aconex server and the files can be imported");
		String fileName = retrieveTestData("FileName","Filename", 2);
		lp.logIntoApplication();	
		ps.selectProject();
		hp.navigateToServicesThroughSidePane("Aconex","Aconex Sync");
		ap.verifyAconexSyncPageDisplayed();
		ap.syncRequest();
		hp.navigateToServicesThroughSidePane("Files","Files");
		fp.verifyFilesSynced();	
		hp.navigateToServicesThroughSidePane("Admin","Manage Model");
		adp.verifySelectionOfModel();
		hp.navigateToServicesThroughSidePane("Aconex","Aconex Import");
		ap.importFile(fileName);				
	}
	
	@AfterMethod
	public void logout() {
		lp.logout();
		driverQuit();
	}
	
	
	
	

}
