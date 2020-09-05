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

public class JobAdminAdhocJob {
	public static Logger logger=Logger.getLogger(JobAdminAdhocJob.class.getName());
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

	@DataProvider(name = "JobAdminUiTestData")
	public Object[][] getJobAdminUiTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("JobAdminTestData"),
				"Ui");
		return testObjArray;
	}

	@Test(dataProvider="JobAdminUiTestData", priority=2 ,dependsOnMethods = {"verifyRole"})
	public void verifyJobAdminUi(Map<String, String> map) throws Exception {
		//Navigating to job admin page
		logger.info("Method Name: verifyJobAdminUi");
		HomePage.explicitWaitForElementToBeClickable("Admin");
		HomePage.click("Admin");
		HomePage.explicitWaitForElementToBeClickable("TechnicalMaintenance");
		HomePage.click("TechnicalMaintenance");
		HomePage.explicitWaitForElementToBeClickable("JobAdmin");
		HomePage.click("JobAdmin");

		//Verifying the title of the screen
		JobAdminPage.explicitWaitForVisibility("JobAdminTitle");
		JobAdminPage.verifyTextValue("JobAdminTitle",map.get("Title"));

		//Verifying tool bar elements
		JobAdminPage.verifyElementIsPresent("SearchIcon");
		JobAdminPage.verifyTextValue("Status",map.get("StatusLabel"));

		//Verifying buttons
		JobAdminPage.verifyElementIsPresent("RefreshButton");
		JobAdminPage.verifyElementIsPresent("AdhocJobButton");
		JobAdminPage.verifyElementIsPresent("DataSeedButton");
		JobAdminPage.verifyElementIsPresent("StopJobButton");

		//Verifying grid view menu options
		JobAdminPage.explicitWaitForElementToBeClickable("GridViewMenu");
		JobAdminPage.click("GridViewMenu");
		JobAdminPage.explicitWaitForElementToBeClickable("ResetView");
		JobAdminPage.verifyTextValue("ResetView",map.get("ResetView"));
		JobAdminPage.verifyTextValue("ColumnFilter",map.get("ColumnFilter"));
		JobAdminPage.verifyTextValue("ExportToCsv",map.get("ExportToCsv"));
		JobAdminPage.click("GridViewMenu");

		//Verifying column name
		JobAdminPage.explicitWaitForElementToBeClickable("GridViewMenu");
		JobAdminPage.verifyTextValue("ExecutionIdColumnName",map.get("ExecutionId"));
		JobAdminPage.verifyTextValue("JobNameColumnName",map.get("JobName"));
		JobAdminPage.verifyTextValue("InstanceIdColumnName",map.get("InstanceId"));
		JobAdminPage.verifyTextValue("StatusColumnName",map.get("Status"));
		JobAdminPage.verifyTextValue("StartTimeColumnName",map.get("StartTime"));
		JobAdminPage.verifyTextValue("EndTimeColumnName",map.get("EndTime"));
		JobAdminPage.verifyTextValue("UserColumnName",map.get("User"));
		JobAdminPage.scrollToViewElement("ParameterColumnName");
		JobAdminPage.verifyTextValue("ReviewedColumnName",map.get("Reviewed"));
		JobAdminPage.verifyTextValue("ParameterColumnName",map.get("Parameter"));

		//Clicking on adhoc job button
		JobAdminPage.explicitWaitForElementToBeClickable("AdhocJobButton");
		JobAdminPage.click("AdhocJobButton");

		//Verifying adhoc job screen title
		JobAdminPage.explicitWaitForElementToBeClickable("AdhocJobTitle");
		JobAdminPage.verifyTextValue("AdhocJobTitle",map.get("AdhocJobTitle"));	

		//Verifying buttons in adhoc job screen
		JobAdminPage.verifyElementIsPresent("StartJobButton");
		JobAdminPage.verifyElementIsPresent("CancelButton");
		JobAdminPage.verifyElementIsPresent("JobNameDropDown");
		JobAdminPage.verifyElementIsPresent("StoreIdTextBox");
		JobAdminPage.verifyElementIsPresent("ExecutionDateDatePicker");
		JobAdminPage.verifyElementIsPresent("DatePicker");
		JobAdminPage.verifyElementIsPresent("DataSetIdTextBox");

		//Verifying the text boxes in adhoc screen is editable or not
		JobAdminPage.verifyElementIsEditable("StoreIdTextBox");
		JobAdminPage.verifyElementIsEditable("ExecutionDateDatePicker");
		JobAdminPage.verifyElementIsEditable("DataSetIdTextBox");

		//Verifying Labels in adhoc screen
		JobAdminPage.verifyTextValue("JobNameLabel",map.get("JobName"));
		JobAdminPage.verifyTextValue("StoreIdLabel",map.get("StoreId"));
		JobAdminPage.verifyTextValue("ExecutionDateLabel",map.get("ExecutionDate"));
		JobAdminPage.verifyTextValue("DataSetIdLabel",map.get("DataSetId"));

		//clicking on DataSeed button
		JobAdminPage.click("CancelButton");
		JobAdminPage.explicitWaitForElementToBeClickable("DataSeedButton");
		JobAdminPage.click("DataSeedButton");

		//Verifying buttons in data seed screen
		JobAdminPage.explicitWaitForElementToBeClickable("JobNameDropDown");
		JobAdminPage.verifyElementIsPresent("StartJobButton");
		JobAdminPage.verifyElementIsPresent("CancelButton");
		JobAdminPage.verifyElementIsPresent("JobNameDropDown");
		JobAdminPage.verifyElementIsPresent("StoreIdTextBox");
		JobAdminPage.verifyElementIsPresent("SeedOptionDropDown");
		JobAdminPage.verifyElementIsPresent("DataSetIdTextBox");

		//Verifying the text boxes in adhoc screen is editable or not
		JobAdminPage.verifyElementIsEditable("StoreIdTextBox");
		JobAdminPage.verifyElementIsEditable("DataSetIdTextBox");

		//Verifying Labels in data seed screen
		JobAdminPage.verifyTextValue("JobNameLabel",map.get("JobName"));
		JobAdminPage.verifyTextValue("StoreIdLabel",map.get("StoreId"));
		JobAdminPage.verifyTextValue("SeedOptionLabel",map.get("SeedOption"));
		JobAdminPage.verifyTextValue("DataSetIdLabel",map.get("DataSetId"));
		JobAdminPage.click("CancelButton");
	}

	@DataProvider(name = "JobAdminAdhocJobTestData")
	public Object[][] getJobAdminAdhocJobTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("JobAdminTestData"),
				"AdhocJob");
		return testObjArray;
	}

	@Test(dataProvider="JobAdminAdhocJobTestData", priority=3 ,dependsOnMethods = {"verifyJobAdminUi"})
	public void verifyAdhocJob(Map<String, String> map) throws Exception {
		//Verifying the title of the screen
		logger.info("Method Name: verifyAdhocJob");
		JobAdminPage.explicitWaitForVisibility("JobAdminTitle");
		JobAdminPage.verifyTextValue("JobAdminTitle",map.get("Title"));

		//Verifying adhoc button is present
		JobAdminPage.verifyElementIsPresent("AdhocJobButton");
		JobAdminPage.explicitWaitForElementToBeClickable("AdhocJobButton");		

		//Verify adhoc button is enabled
		JobAdminPage.verifyElementIsEnabled("AdhocJobButton");

		//Clicking on the adhoc button
		JobAdminPage.click("AdhocJobButton");

		//Verifying adhoc job screen title
		JobAdminPage.explicitWaitForElementToBeClickable("AdhocJobTitle");
		JobAdminPage.verifyTextValue("AdhocJobTitle",map.get("AdhocJobTitle"));		
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
