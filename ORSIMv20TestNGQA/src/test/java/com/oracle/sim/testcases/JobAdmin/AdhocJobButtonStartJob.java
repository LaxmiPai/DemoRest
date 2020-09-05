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

public class AdhocJobButtonStartJob {
	public static Logger logger=Logger.getLogger(AdhocJobButtonStartJob.class.getName());
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
		RoleMaintenancePage.explicitWaitForVisibility("Title");
		RoleMaintenancePage.explicitWaitForElementToBeClickable("Title");
		RoleMaintenancePage.verifyTextValue("Title",map.get("Title"));

		//grant all permissions to the user
		String userRole=propReader.getApplicationproperty("UserRole");
		RoleMaintenancePage.grantAllPermissions(userRole);
	}

	@DataProvider(name = "AdhocJobButtonStartJobTestData")
	public Object[][] getAdhocJobButtonStartJobTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("JobAdminTestData"),
				"AdhocJobButtonStartJob");
		return testObjArray;
	}

	@Test(dataProvider="AdhocJobButtonStartJobTestData", priority=2 ,dependsOnMethods = {"verifyRole"})
	public void verifyStartJob(Map<String, String> map) throws Exception {
		//Navigating to job admin page
		logger.info("Method Name: verifyStartJob");
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
		JobAdminPage.explicitWaitForInvisibility("AdhocJobButton");
		JobAdminPage.explicitWaitForVisibility("AdhocJobTitle");
		JobAdminPage.verifyTextValue("AdhocJobTitle",map.get("AdhocJobTitle"));		

		//Verifying Start Job button
		JobAdminPage.verifyElementIsPresent("StartJobButton");
		JobAdminPage.explicitWaitForElementToBeClickable("StartJobButton");		

		//Clicking on a start job button
		JobAdminPage.click("StartJobButton");

		//Verifying the error message
		JobAdminPage.explicitWaitForElementToBeClickable("JobNameErrorMessage");
		JobAdminPage.verifyTextValue("JobNameErrorMessage",map.get("JobNameErrorMessage"));

		//Selecting a job name
		JobAdminPage.explicitWaitForElementToBeClickable("StartJobButton");
		JobAdminPage.explicitWaitForElementToBeClickable("JobNameDropDown");
		JobAdminPage.selectDropDownValue("JobNameDropDown",map.get("JobName"));

		//Clicking on a start job button
		JobAdminPage.explicitWaitForElementToBeClickable("StartJobButton");
		JobAdminPage.click("StartJobButton");

		//Verifying the start job message
		JobAdminPage.verifyTextValue("StartJobMessage",map.get("StartJobMessage"));
		JobAdminPage.verifyElementIsPresent("NoButton");
		JobAdminPage.verifyElementIsPresent("YesButton");

		//Clicking on a no button
		JobAdminPage.explicitWaitForElementToBeClickable("NoButton");
		JobAdminPage.click("NoButton");
		JobAdminPage.explicitWaitForInvisibility("StartJobMessage");

		//Verifying user remains on the same job
		JobAdminPage.verifyElementIsNotPresent("StartJobMessage");
		JobAdminPage.verifyTextValue("AdhocJobTitle",map.get("AdhocJobTitle"));

		//Selecting a job name
		JobAdminPage.explicitWaitForElementToBeClickable("JobNameDropDown");
		JobAdminPage.selectDropDownValue("JobNameDropDown",map.get("JobName"));

		//Clicking on a start job button
		JobAdminPage.explicitWaitForElementToBeClickable("StartJobButton");
		JobAdminPage.click("StartJobButton");

		//Verifying the start job message
		JobAdminPage.verifyTextValue("StartJobMessage",map.get("StartJobMessage"));
		JobAdminPage.verifyElementIsPresent("NoButton");
		JobAdminPage.verifyElementIsPresent("YesButton");

		//Clicking on a yes button
		JobAdminPage.explicitWaitForElementToBeClickable("YesButton");
		JobAdminPage.click("YesButton");

		//Verifying the job admin screen title
		JobAdminPage.explicitWaitForInvisibility("StartJobButton");
		JobAdminPage.explicitWaitForVisibility("JobAdminTitle");
		JobAdminPage.verifyTextValue("JobAdminTitle",map.get("Title"));

		//Clicking on the adhoc button
		JobAdminPage.explicitWaitForElementToBeClickable("AdhocJobButton");
		JobAdminPage.click("AdhocJobButton");
		JobAdminPage.explicitWaitForElementToBeClickable("AdhocJobTitle");

		//Entering Store id data
		JobAdminPage.explicitWaitForElementToBeClickable("StoreIdTextBox");
		JobAdminPage.enterIntoTextBox("StoreIdTextBox",map.get("StoreId"));

		//Entering data set id data
		JobAdminPage.explicitWaitForElementToBeClickable("DataSetIdTextBox");
		JobAdminPage.enterIntoTextBox("DataSetIdTextBox",map.get("DataSetId"));		

		//Entering execution date data
		JobAdminPage.explicitWaitForElementToBeClickable("ExecutionDateDatePicker");
		JobAdminPage.selectDateFromDatePicker("ExecutionDateDatePicker",map.get("ExecutionDate"));

		//Clicking on a start job button
		JobAdminPage.explicitWaitForElementToBeClickable("StartJobButton");
		JobAdminPage.click("StartJobButton");

		//Verifying the error message
		JobAdminPage.verifyTextValue("JobNameErrorMessage",map.get("JobNameErrorMessage"));
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
