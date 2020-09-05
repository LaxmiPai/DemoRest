package com.oracle.sim.testcases.MPSStagedMessage;

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

public class MpsStagedMessageMultipleFilterCriteria {
	public static Logger logger=Logger.getLogger(MpsStagedMessageMultipleFilterCriteria.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pagefactory=new PageFactory();

	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage RoleMaintenancePage;
	SimBasePage MpsStagedMessagePage;

	@BeforeClass()
	public void setup(ITestContext context) throws Exception {
		logger.info("TestCase Name: " +logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
		LoginPage=pagefactory.initialize("LoginPage");
		HomePage=pagefactory.initialize("HomePage");
		RoleMaintenancePage=pagefactory.initialize("RoleMaintenancePage");
		MpsStagedMessagePage=pagefactory.initialize("MpsStagedMessagePage");
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

		//Verifying a user permission
		String userRole=propReader.getApplicationproperty("UserRole");
		RoleMaintenancePage.verifyUserRole(userRole, map.get("AccessMpsStagedMessages"), map.get("AssignedDataNo"));	

		//Navigating to MPS staged screen
		HomePage.click("Admin");
		HomePage.click("TechnicalMaintenance");
		HomePage.click("MpsStagedMessage");
		HomePage.click("Navigation");
	}

	@DataProvider(name = "MultipleFilterCriteriaTestData")
	public Object[][] getMultipleFilterCriteriaTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("MpsStagedMessageTestData"),
				"MultipleFilterCriteria");
		return testObjArray;
	}

	@Test(dataProvider="MultipleFilterCriteriaTestData",priority=2 ,dependsOnMethods = {"verifyRole"})
	public void verifyMultipleFilterCriteria(Map<String,String> map) throws Exception {
		//Verifying the page title
		logger.info("Method Name: verifyMultipleFilterCriteria");
		MpsStagedMessagePage.explicitWaitForElementToBeClickable("Title");
		MpsStagedMessagePage.verifyTextValue("Title",map.get("Title"));

		//Verifying MPS staged messages count
		MpsStagedMessagePage.verifyTableRecordsCount("RecordIdColumnData","Description","FilterRecordId");

		//Clicking on Search button
		MpsStagedMessagePage.explicitWaitForElementToBeClickable("SearchIcon");
		MpsStagedMessagePage.click("SearchIcon");

		//Verifying MPS Staged messages filter screen is displayed
		MpsStagedMessagePage.explicitWaitForElementToBeClickable("SearchCriteriaTitle");
		MpsStagedMessagePage.verifyTextValue("SearchCriteriaTitle",map.get("SearchCriteriaTitle"));

		//Selecting family drop down value
		MpsStagedMessagePage.explicitWaitForElementToBeClickable("FamilyDropDown");
		MpsStagedMessagePage.selectDropDownValue("FamilyDropDown",map.get("Family"));

		//Selecting In/Out drop down value
		MpsStagedMessagePage.explicitWaitForElementToBeClickable("InOutDropDown");
		MpsStagedMessagePage.selectDropDownValue("InOutDropDown",map.get("InOut"));

		//Entering search limit value
		MpsStagedMessagePage.explicitWaitForElementToBeClickable("SearchLimitTextBox");
		MpsStagedMessagePage.enterIntoTextBox("SearchLimitTextBox",map.get("SearchLimit"));

		//Selecting show pending status as 'yes'
		MpsStagedMessagePage.explicitWaitForElementToBeClickable("ShowPendingButton");		
		if((MpsStagedMessagePage.getAttributeValue("ShowPendingButton",map.get("ValueAttribute"))).equalsIgnoreCase(map.get("ShowPendingNo"))){
			MpsStagedMessagePage.click("ShowPendingButton");
		}

		//Selecting show retry status as 'yes'
		MpsStagedMessagePage.explicitWaitForElementToBeClickable("ShowRetryButton");
		if((MpsStagedMessagePage.getAttributeValue("ShowRetryButton",map.get("ValueAttribute"))).equalsIgnoreCase(map.get("ShowRetryNo"))){
			MpsStagedMessagePage.click("ShowRetryButton");
		}

		//Clicking on search button
		MpsStagedMessagePage.explicitWaitForElementToBeClickable("SearchButton");
		MpsStagedMessagePage.click("SearchButton");

		//Verifying the page title
		MpsStagedMessagePage.explicitWaitForElementToBeClickable("Title");
		MpsStagedMessagePage.verifyTextValue("Title",map.get("Title"));

		//Verifying  the filter criteria is applied or not
		MpsStagedMessagePage.verifyTextValue("StatusText",map.get("Status"));

		//Verifying MPS staged message count
		MpsStagedMessagePage.verifyTableRecordsCount("RecordIdColumnData","Description","FilterRecordId");
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
