package com.oracle.sim.testcases.JobAdmin;

/**
 * @author vidhsank
 *
 * 
 */

import java.util.Map;
import java.util.logging.Logger;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.oracle.core.dataProvider.ExcelReader;
import com.oracle.core.utils.PropertyReader;
import com.oracle.sim.pageFactory.PageFactory;
import com.oracle.sim.pages.Base.SimBasePage;
import com.oracle.sim.utils.SIMWebdriverBase;

public class AdhocJobButtonCancel {
	public static Logger logger=Logger.getLogger(AdhocJobButtonCancel.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pagefactory=new PageFactory();

	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage RoleMaintenancePage;
	SimBasePage JobAdminPage;

	@BeforeClass()
	public void setup(ITestContext context) throws Exception {
		logger.info("TestCase Name: " +logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
		LoginPage=pagefactory.initialize("LoginPage");
		HomePage=pagefactory.initialize("HomePage");
		RoleMaintenancePage=pagefactory.initialize("RoleMaintenancePage");
		JobAdminPage=pagefactory.initialize("JobAdminPage");
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username",LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password",LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");

		HomePage.verifyTextValue("Title", "Store Inventory Operations Cloud Service");
		HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
		HomePage.storeIdCheck();
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.click("Navigation");
	}

	@DataProvider(name = "RoleMaintenanceTestData")
	public Object[][] getRoleMaintenanceTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("SecurityTestData"),
				"RoleMaintenance");
		return testObjArray;
	}

	@Test(dataProvider="RoleMaintenanceTestData", priority=1)
	public void verifyRole(Map<String,String> map) throws Exception {
		//Navigating to role maintenance page
		logger.info("Method Name: verifyRole");
		HomePage.click("Security");
		HomePage.click("RoleMaintenance");

		//Verifying RoleMaintenance Page Title
		RoleMaintenancePage.explicitWaitForElementToBeClickable("Title");
		RoleMaintenancePage.verifyTextValue("Title",map.get("Title"));

		//grant all permissions to the user
		String userRole=propReader.getApplicationproperty("UserRole");
		RoleMaintenancePage.grantAllPermissions(userRole);
	}

	@DataProvider(name="AdhocJobButtonCancelTestData")
	public Object[][] getAdhocJobButtonCancelTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("JobAdminTestData"),
				"AdhocJobButtonCancel");
		return testObjArray;
	}

	@Test(dataProvider="AdhocJobButtonCancelTestData", priority=2 ,dependsOnMethods = {"verifyRole"})
	public void verifyCancel(Map<String, String> map) throws Exception {
		//Navigating to job admin page
		logger.info("Method Name: verifyCancel");
		HomePage.explicitWaitForVisibility("Admin");
		HomePage.explicitWaitForElementToBeClickable("Admin");
		HomePage.click("Admin");
		HomePage.explicitWaitForElementToBeClickable("TechnicalMaintenance");
		HomePage.click("TechnicalMaintenance");
		HomePage.explicitWaitForElementToBeClickable("JobAdmin");
		HomePage.click("JobAdmin");

		//Verifying the title of the screen
		JobAdminPage.explicitWaitForVisibility("JobAdminTitle");
		JobAdminPage.verifyTextValue("JobAdminTitle",map.get("Title"));

		//Clicking on the adhoc button
		JobAdminPage.explicitWaitForElementToBeClickable("AdhocJobButton");
		JobAdminPage.click("AdhocJobButton");

		//Verifying adhoc job screen title
		JobAdminPage.explicitWaitForElementToBeClickable("AdhocJobTitle");
		JobAdminPage.verifyTextValue("AdhocJobTitle",map.get("AdhocJobTitle"));		

		//Verifying Cancel button
		JobAdminPage.verifyElementIsPresent("CancelButton");
		JobAdminPage.explicitWaitForElementToBeClickable("CancelButton");		

		//Clicking on a cancel job button
		JobAdminPage.click("CancelButton");

		//Verifying the job admin screen title
		JobAdminPage.explicitWaitForVisibility("JobAdminTitle");
		JobAdminPage.verifyTextValue("JobAdminTitle",map.get("Title"));

		//Clicking on the adhoc button
		JobAdminPage.explicitWaitForElementToBeClickable("AdhocJobButton");
		JobAdminPage.click("AdhocJobButton");

		//Selecting a job name
		JobAdminPage.explicitWaitForInvisibility("JobAdminTitle");
		JobAdminPage.explicitWaitForElementToBeClickable("JobNameDropDown");
		JobAdminPage.selectDropDownValue("JobNameDropDown",map.get("JobName"));

		//Clicking on a cancel job button
		JobAdminPage.explicitWaitForElementToBeClickable("CancelButton");
		JobAdminPage.click("CancelButton");

		//Verifying the job admin screen title
		JobAdminPage.explicitWaitForVisibility("JobAdminTitle");
		JobAdminPage.verifyTextValue("JobAdminTitle",map.get("Title"));
	}

	@AfterClass()
	public void tearDown() {
		try {
			logger.info("After Test: Logging out");
			HomePage.click("UserMenu");
			HomePage.explicitWaitForVisibility("Logout");
			HomePage.click("Logout");
			HomePage.explicitWaitForVisibility("Yes");
			HomePage.click("Yes");
		}
		finally {
			SIMWebdriverBase.close();
		}
	}
}
