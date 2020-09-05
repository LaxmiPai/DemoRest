package com.oracle.sim.testcases.MPSStagedMessage;

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

public class MpsStagedMessageFilterFamily {
	public static Logger logger=Logger.getLogger(MpsStagedMessageFilterFamily.class.getName());
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
	}

	@DataProvider(name = "UiTestData")
	public Object[][] getUiTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("MpsStagedMessageTestData"),
				"Ui");
		return testObjArray;
	}

	@Test(dataProvider="UiTestData",priority=2 ,dependsOnMethods = {"verifyRole"})
	public void verifyUi(Map<String,String> map) throws Exception {
		//Navigating to MPS staged screen
		logger.info("Method Name: verifyUi");
		HomePage.click("Admin");
		HomePage.click("TechnicalMaintenance");
		HomePage.click("MpsStagedMessage");
		HomePage.click("Navigation");

		//Verifying the page title
		MpsStagedMessagePage.explicitWaitForElementToBeClickable("Title");
		MpsStagedMessagePage.verifyTextValue("Title",map.get("Title"));

		//Verifying search icon is present or not
		MpsStagedMessagePage.verifyElementIsPresent("SearchIcon");

		//Verifying status text
		MpsStagedMessagePage.verifyTextValue("StatusText",map.get("Status"));

		//Verifying tool bar buttons
		MpsStagedMessagePage.verifyElementIsPresent("RefreshButton");
		MpsStagedMessagePage.verifyElementIsPresent("RetryButton");
		MpsStagedMessagePage.verifyElementIsPresent("DeleteSelectedButton");
		MpsStagedMessagePage.verifyElementIsPresent("GridViewMenu");

		//Verifying grid view menu options
		MpsStagedMessagePage.explicitWaitForElementToBeClickable("GridViewMenu");
		MpsStagedMessagePage.click("GridViewMenu");
		MpsStagedMessagePage.explicitWaitForElementToBeClickable("ResetView");
		MpsStagedMessagePage.verifyTextValue("ResetView",map.get("ResetView"));
		MpsStagedMessagePage.verifyTextValue("ColumnFilter",map.get("ColumnFilter"));
		MpsStagedMessagePage.verifyTextValue("RowSelector",map.get("RowSelector"));
		MpsStagedMessagePage.verifyTextValue("ExportToCsv",map.get("ExportToCsv"));
		MpsStagedMessagePage.click("GridViewMenu");

		//Verifying column names
		MpsStagedMessagePage.verifyTextValue("RecordId",map.get("RecordId"));
		MpsStagedMessagePage.verifyTextValue("InOutColumnName",map.get("InOut"));
		MpsStagedMessagePage.verifyTextValue("TypeColumnName",map.get("Type"));
		MpsStagedMessagePage.verifyTextValue("FamilyColumnName",map.get("Family"));
		MpsStagedMessagePage.verifyTextValue("CreateTimeColumnName",map.get("CreateTime"));
		MpsStagedMessagePage.verifyTextValue("UpdateTimeColumnName",map.get("UpdateTime"));
		MpsStagedMessagePage.verifyTextValue("ExecutionCountColumnName",map.get("ExecutionCount"));
		MpsStagedMessagePage.scrollToViewElement("Description");
		MpsStagedMessagePage.verifyTextValue("BusinessIdColumnName",map.get("BusinessId"));
		MpsStagedMessagePage.verifyTextValue("StoreIdColumnName",map.get("StoreId"));
		MpsStagedMessagePage.verifyTextValue("JobIdColumnName",map.get("JobId"));
		MpsStagedMessagePage.verifyTextValue("Description",map.get("Description"));

		//Clicking on search icon
		MpsStagedMessagePage.explicitWaitForElementToBeClickable("SearchIcon");
		MpsStagedMessagePage.click("SearchIcon");

		//Verifying MPS Staged messages filter screen is displayed
		MpsStagedMessagePage.explicitWaitForElementToBeClickable("SearchCriteriaTitle");
		MpsStagedMessagePage.verifyTextValue("SearchCriteriaTitle",map.get("SearchCriteriaTitle"));

		//Verifying search criteria labels
		MpsStagedMessagePage.verifyTextValue("FamilyLabel",map.get("FamilyLabel"));
		MpsStagedMessagePage.verifyTextValue("InOutLabel",map.get("InOutLabel"));
		MpsStagedMessagePage.verifyTextValue("SearchLimitLabel",map.get("SearchLimitLabel"));
		MpsStagedMessagePage.verifyTextValue("ShowPendingLabel",map.get("ShowPendingLabel"));
		MpsStagedMessagePage.verifyTextValue("ShowRetryLabel",map.get("ShowRetryLabel"));

		//Verifying text box
		MpsStagedMessagePage.verifyElementIsPresent("FamilyDropDown");
		MpsStagedMessagePage.verifyElementIsPresent("InOutDropDown");
		MpsStagedMessagePage.verifyElementIsPresent("SearchLimitTextBox");
		MpsStagedMessagePage.verifyElementIsPresent("ShowPendingButton");
		MpsStagedMessagePage.verifyElementIsPresent("ShowRetryButton");
		MpsStagedMessagePage.verifyElementIsPresent("SearchButton");
		MpsStagedMessagePage.verifyElementIsPresent("ResetButton");
		MpsStagedMessagePage.verifyElementIsPresent("CancelButton");

		//Verifying default values
		MpsStagedMessagePage.verifyTextValue("FamilyDropDown",map.get("FamilyValue"));
		MpsStagedMessagePage.verifyTextValue("InOutDropDown",map.get("InOutValue"));
		Assert.assertEquals(MpsStagedMessagePage.getAttributeValue("SearchLimitTextBox",map.get("ValueAttribute")),map.get("SearchLimitValue"));
		MpsStagedMessagePage.verifyTextValue("ShowPendingButtonValue",map.get("ShowPendingValue"));
		MpsStagedMessagePage.verifyTextValue("ShowRetryButtonValue",map.get("ShowRetryValue"));

		//Clicking on cancel button
		MpsStagedMessagePage.click("CancelButton");
		MpsStagedMessagePage.explicitWaitForElementToBeClickable("RefreshButton");
		MpsStagedMessagePage.click("RefreshButton");
	}

	@DataProvider(name = "FilterFamilyTestData")
	public Object[][] getFilterFamilyTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("MpsStagedMessageTestData"),
				"FilterFamily");
		return testObjArray;
	}

	@Test(dataProvider="FilterFamilyTestData",priority=3 ,dependsOnMethods = {"verifyUi"})
	public void verifyFilterFamily(Map<String,String> map) throws Exception {
		//Verifying the page title
		logger.info("Method Name: verifyFilterFamily");
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

		//Verifying family drop down default value
		MpsStagedMessagePage.explicitWaitForElementToBeClickable("FamilyDropDown");
		MpsStagedMessagePage.verifyTextValue("FamilyDropDown",map.get("Family"));

		//Verifying family drop down values
		MpsStagedMessagePage.click("FamilyDropDown");
		MpsStagedMessagePage.explicitWaitForElementToBeClickable("FamilyDropDownValues");
		MpsStagedMessagePage.verifyDropDownValues("FamilyDropDownValues",map.get("FamilyDropDownValuesCount"));
		MpsStagedMessagePage.click("FamilyDropDown");

		//Selecting family drop down value
		MpsStagedMessagePage.explicitWaitForElementToBeClickable("FamilyDropDown");
		MpsStagedMessagePage.selectDropDownValue("FamilyDropDown",map.get("FamilyValue1"));

		//Selecting show pending status as 'yes'
		MpsStagedMessagePage.explicitWaitForElementToBeClickable("ShowPendingButton");	
		if((MpsStagedMessagePage.getAttributeValue("ShowPendingButton",map.get("Value"))).equalsIgnoreCase(map.get("ShowPendingNo"))){
			MpsStagedMessagePage.click("ShowPendingButton");
		}

		//Clicking on search button
		MpsStagedMessagePage.explicitWaitForElementToBeClickable("SearchButton");
		MpsStagedMessagePage.click("SearchButton");

		//Verifying the page title
		MpsStagedMessagePage.explicitWaitForElementToBeClickable("Title");
		MpsStagedMessagePage.verifyTextValue("Title",map.get("Title"));

		//Verifying  the filter criteria is applied or not
		MpsStagedMessagePage.verifyTextValue("StatusText",map.get("Status"));

		//Verifying MPS staged messages count
		MpsStagedMessagePage.verifyTableRecordsMessageFamily("RecordIdColumnData","Description","FilterRecordId",map.get("FamilyValue1"));

		//Clicking on Search button
		MpsStagedMessagePage.explicitWaitForElementToBeClickable("SearchIcon");
		MpsStagedMessagePage.click("SearchIcon");

		//Clicking on reset button in filter screen
		MpsStagedMessagePage.explicitWaitForElementToBeClickable("SearchCriteriaTitle");
		MpsStagedMessagePage.explicitWaitForElementToBeClickable("ResetButton");
		MpsStagedMessagePage.click("ResetButton");

		//Verifying family drop down default value
		MpsStagedMessagePage.explicitWaitForElementToBeClickable("FamilyDropDown");
		MpsStagedMessagePage.verifyTextValue("FamilyDropDown",map.get("Family"));
		MpsStagedMessagePage.verifyTextValue("InOutDropDown",map.get("InOut"));
		Assert.assertEquals(MpsStagedMessagePage.getAttributeValue("SearchLimitTextBox",map.get("Value")),map.get("SearchLimit"),"The search limit value is not correct");

		//Selecting family drop down value
		MpsStagedMessagePage.selectDropDownValue("FamilyDropDown",map.get("FamilyValue2"));

		//Clicking on cancel button
		MpsStagedMessagePage.click("CancelButton");
		MpsStagedMessagePage.verifyTextValue("StatusText",map.get("Status"));
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
