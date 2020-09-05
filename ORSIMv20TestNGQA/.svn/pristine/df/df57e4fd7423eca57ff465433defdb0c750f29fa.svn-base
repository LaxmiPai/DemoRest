package com.oracle.sim.testcases.JobAdmin;

/**
 * @author vidhsank
 *
 * 
 */

import java.util.Map;
import java.util.logging.Logger;

import org.testng.Assert;
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

public class ViewDetailUi {
	public static Logger logger=Logger.getLogger(ViewDetailUi.class.getName());
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

	@DataProvider(name = "ViewDetailUiTestData")
	public Object[][] getViewDetailUiTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("JobAdminTestData"),
				"ViewDetailUi");
		return testObjArray;
	}

	@Test(dataProvider="ViewDetailUiTestData", priority=2, dependsOnMethods= {"verifyRole"})
	public void verifyViewDetailUi(Map<String, String> map) throws Exception {
		//Navigating to job admin page
		logger.info("Method Name: verifyViewDetailUi");
		HomePage.explicitWaitForElementToBeClickable("Admin");
		HomePage.click("Admin");
		HomePage.explicitWaitForElementToBeClickable("TechnicalMaintenance");
		HomePage.click("TechnicalMaintenance");
		HomePage.explicitWaitForElementToBeClickable("JobAdmin");
		HomePage.click("JobAdmin");

		//Verifying the title of the screen
		JobAdminPage.explicitWaitForVisibility("JobAdminTitle");
		JobAdminPage.verifyTextValue("JobAdminTitle",map.get("Title"));

		//Clicking on navigation 
		HomePage.click("Navigation");

		//Verifying job records are displayed are not
		int size=JobAdminPage.getTableRowCount("ParametersColumnRecords","ParameterColumnName","FilterExecutionId");
		if(size==0) {
			System.out.println("No table records found");
		}else {
			//Clicking on the execution id
			JobAdminPage.explicitWaitForElementToBeClickable("FirstExecutionIdRecord");
			String firstExecutionIdRecord=JobAdminPage.getText("FirstExecutionIdRecord");
			JobAdminPage.click("FirstExecutionIdRecord");

			//Verifying the detail screen is presented
			JobAdminPage.explicitWaitForElementToBeClickable("JobExecutionTitle");
			JobAdminPage.verifyTextValue("JobExecutionTitle",map.get("JobExecutionTitle")+firstExecutionIdRecord);

			//Verifying tool bar buttons
			JobAdminPage.verifyElementIsPresent("MarkReviewButton");	
			JobAdminPage.verifyElementIsPresent("CancelButton");

			//Verifying grid view menu options
			JobAdminPage.verifyElementIsPresent("GridViewMenu");
			JobAdminPage.click("GridViewMenu");
			JobAdminPage.explicitWaitForElementToBeClickable("ResetView");
			JobAdminPage.verifyElementIsPresent("ResetView");
			JobAdminPage.verifyElementIsPresent("ColumnFilter");
			JobAdminPage.verifyElementIsPresent("ExportToCsv");
			JobAdminPage.explicitWaitForElementToBeClickable("GridViewMenu");
			JobAdminPage.click("GridViewMenu");

			//Verifying the table column name
			JobAdminPage.verifyTextValue("DetailIdColumnName",map.get("DetailIdColumnName"));
			JobAdminPage.verifyTextValue("ProcessedColumnName",map.get("ProcessedColumnName"));
			JobAdminPage.verifyTextValue("FailedColumnName",map.get("FailedColumnName"));
			JobAdminPage.verifyTextValue("MessageColumnName",map.get("MessageColumnName"));
			JobAdminPage.verifyTextValue("DataFileColumnName",map.get("DataFileColumnName"));
			JobAdminPage.verifyTextValue("AdditionalInfoColumnName",map.get("AdditionalInfoColumnName"));

			//Verifying the message detail title
			JobAdminPage.verifyTextValue("MessageDetailsTitle",map.get("MessageDetailsTitle"));

			//Verifying the record is available or not
			if(JobAdminPage.isVisible("TableFirstRecord")) {				
				//Clicking on the first batch record
				JobAdminPage.click("TableFirstRecord");

				//Verifying the element is highlighted
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
				JobAdminPage.verifyElementIsFocused("TableFirstRecord");

				//verify the message detail displays message
				Assert.assertNotNull(JobAdminPage.getAttributeValue("MessageDetailTextBox",map.get("Value")),"There is no message");
				JobAdminPage.verifyElementIsPresent("MessageDetailTextBox");				
			}else {
				Assert.fail("Job Execution table should have minimum one record");
			}				
		}		
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
