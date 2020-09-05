package com.oracle.sim.testcases.UinLookup;

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

public class UinLookupSearchField {
	public static Logger logger=Logger.getLogger(UinLookupSearchField.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pagefactory=new PageFactory();

	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage RoleMaintenancePage;
	SimBasePage UinLookupPage;

	@BeforeClass()
	public void setup(ITestContext context) throws Exception {
		logger.info("TestCase Name: " +logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
		LoginPage=pagefactory.initialize("LoginPage");
		HomePage=pagefactory.initialize("HomePage");
		RoleMaintenancePage=pagefactory.initialize("RoleMaintenancePage");
		UinLookupPage=pagefactory.initialize("UinLookupPage");
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
		String roleName=propReader.getApplicationproperty("UserRole");
		RoleMaintenancePage.verifyUserRole(roleName, map.get("ViewUinHistory"), map.get("AssignedDataNo"));
	}

	@DataProvider(name = "UinSearchFieldTestData")
	public Object[][] getUinSearchFieldTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("UinLookupTestData"),
				"UinSearchField");
		return testObjArray;
	}

	@Test(dataProvider="UinSearchFieldTestData", priority=2 ,dependsOnMethods = {"verifyRole"})
	public void verifyUinSearchField(Map<String,String> map) throws Exception {
		//Navigating to UIN lookup page
		logger.info("Method Name: verifyUinSearchField");
		HomePage.click("Lookup");
		HomePage.click("UinLookup");
		HomePage.click("Navigation");

		//Verifying Page Title
		UinLookupPage.explicitWaitForElementToBeClickable("Title");
		UinLookupPage.verifyTextValue("Title",map.get("Title"));

		//Verifying all fields name
		UinLookupPage.verifyTextValue("StoreLabel",map.get("StoreLabel"));
		UinLookupPage.verifyTextValue("UinLookupItemLabel",map.get("ItemLabel"));
		UinLookupPage.verifyTextValue("UinLabel",map.get("UinLabel"));
		UinLookupPage.verifyTextValue("StatusLabel",map.get("StatusLabel"));
		UinLookupPage.verifyTextValue("UserLabel",map.get("UserLabel"));
		UinLookupPage.verifyTextValue("LastUpdateFromDateLabel",map.get("LastUpdateFromDateLabel"));
		UinLookupPage.verifyTextValue("LastUpdateToDateLabel",map.get("LastUpdateToDateLabel"));
		UinLookupPage.verifyTextValue("SearchLimitLabel",map.get("SearchLimitLabel"));

		//Verifying the field input elements are present
		UinLookupPage.verifyElementIsPresent("StoreDropDown");
		UinLookupPage.verifyElementIsPresent("UinLookupItemTextBox");
		UinLookupPage.verifyElementIsPresent("UinTextBox");
		UinLookupPage.verifyElementIsPresent("StatusDropDown");
		UinLookupPage.verifyElementIsPresent("UserTextBox");
		UinLookupPage.verifyElementIsPresent("LastUpdateFromDateDatePicker");
		UinLookupPage.verifyElementIsPresent("LastUpdateToDateDatePicker");
		UinLookupPage.verifyElementIsPresent("SearchLimitTextBox");
		UinLookupPage.verifyElementIsPresent("SearchButton");
		UinLookupPage.verifyElementIsPresent("ResetButton");

		//Verifying field default values
		String currentStore=UinLookupPage.getText("CurrentStore");
		System.out.println(currentStore);
		UinLookupPage.verifyTextValue("StoreDropDown",currentStore);
		UinLookupPage.verifyValuesAreEqual(UinLookupPage.getPlaceholderValue("UinLookupItemTextBox"),map.get("ItemPlaceholder"));
		UinLookupPage.verifyValuesAreEqual(UinLookupPage.getPlaceholderValue("UinTextBox"),map.get("UinPlaceholder"));
		UinLookupPage.verifyValuesAreEqual(UinLookupPage.getPlaceholderValue("StatusDropDown"),map.get("StatusPlaceholder"));
		UinLookupPage.verifyValuesAreEqual(UinLookupPage.getPlaceholderValue("UserTextBox"),map.get("UserPlaceholder"));
		UinLookupPage.verifyDefaultDateWithCurrentDate("LastUpdateFromDateDatePicker");
		UinLookupPage.verifyValuesAreEqual(UinLookupPage.getPlaceholderValue("LastUpdateToDateDatePicker"),map.get("LastUpdateToDatePlaceholder"));
		Assert.assertEquals(UinLookupPage.getAttributeValue("SearchLimitTextBox",map.get("Value")),map.get("SearchLimitValue"),"The search limit value is not correct");

		//Searching for an item
		UinLookupPage.explicitWaitForElementToBeClickable("UinLookupItemTextBox");
		UinLookupPage.click("UinLookupItemTextBox");
		UinLookupPage.enterIntoTextBox("UinLookupItemTextBox",map.get("ItemId"));

		//Clearing last update from date text box for getting a record
		UinLookupPage.clearElement("LastUpdateFromDateDatePickerInput");

		//Click on a search button
		UinLookupPage.explicitWaitForElementToBeClickable("SearchButton");
		UinLookupPage.click("SearchButton");		
	}

	@DataProvider(name = "ItemUinListUiTestData")
	public Object[][] getItemUinListUiTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("UinLookupTestData"),
				"ItemUinList");
		return testObjArray;
	}

	@Test(dataProvider="ItemUinListUiTestData", priority=3 ,dependsOnMethods = {"verifyUinSearchField"})
	public void verifyItemUinListUi(Map<String,String> map) throws Exception {
		//Verifying item UIN list title
		logger.info("Method Name: verifyItemUinListUi");
		UinLookupPage.explicitWaitForVisibility("ItemUinListTitle");
		UinLookupPage.verifyTextValue("ItemUinListTitle",map.get("ItemUinListTitle"));

		//Verifying Buttons
		UinLookupPage.verifyElementIsPresent("BackButton");
		UinLookupPage.verifyElementIsPresent("RefreshButton");
		UinLookupPage.verifyElementIsPresent("GridViewMenu");

		//Verifying grid view menu options
		UinLookupPage.explicitWaitForElementToBeClickable("GridViewMenu");
		UinLookupPage.click("GridViewMenu");
		UinLookupPage.explicitWaitForElementToBeClickable("ResetView");
		UinLookupPage.verifyTextValue("ResetView",map.get("ResetView"));
		UinLookupPage.verifyTextValue("ColumnFilter",map.get("ColumnFilter"));
		UinLookupPage.verifyTextValue("ExportToCsv",map.get("ExportToCsv"));
		UinLookupPage.click("GridViewMenu");

		//Verifying Item label and text box
		UinLookupPage.verifyTextValue("ItemLabel",map.get("ItemLabel"));
		UinLookupPage.verifyElementIsPresent("ItemTextBox");
		UinLookupPage.verifyTextValue("ItemDescriptionLabel",map.get("ItemDescriptionLabel"));
		UinLookupPage.verifyElementIsPresent("ItemDescriptionTextBox");
		UinLookupPage.verifyTextValue("UinTypeLabel",map.get("UinTypeLabel"));
		UinLookupPage.verifyElementIsPresent("UinTypeTextBox");
		UinLookupPage.verifyTextValue("SohLabel",map.get("SohLabel"));
		UinLookupPage.verifyElementIsPresent("SohTextBox");

		//Verifying column name
		UinLookupPage.verifyTextValue("StoreColumnName",map.get("Store"));
		UinLookupPage.verifyTextValue("UinColumnName",map.get("Uin"));
		UinLookupPage.verifyTextValue("StatusColumnName",map.get("Status"));
		UinLookupPage.verifyTextValue("TransactionIdColumnName",map.get("TransactionId"));
		UinLookupPage.verifyTextValue("FunctionalAreaColumnName",map.get("FunctionalArea"));
		UinLookupPage.verifyTextValue("UserColumnName",map.get("User"));
		UinLookupPage.verifyTextValue("LastUpdateColumnName",map.get("LastUpdate"));
		UinLookupPage.verifyTextValue("OpenColumnName",map.get("Open"));

		//Verifying Filters
		UinLookupPage.verifyElementIsPresent("FilterStore");
		UinLookupPage.verifyElementIsPresent("FilterUin");
		UinLookupPage.verifyElementIsPresent("FilterStatus");
		UinLookupPage.verifyElementIsPresent("FilterTransactionId");
		UinLookupPage.verifyElementIsPresent("FilterFunctionalArea");
		UinLookupPage.verifyElementIsPresent("FilterUser");
		UinLookupPage.verifyElementIsPresent("FilterLastUpdate");
		UinLookupPage.verifyElementIsPresent("FilterOpen");

		//verifying UIN list records are displayed
		UinLookupPage.explicitWaitForElementToBeClickable("StoreColumnData");
		int size=UinLookupPage.getTableRowCount("StoreColumnData","OpenColumnName","FilterStore");

		if(size==0) {
			System.out.println("No records found");
		}else {
			//Clicking a UIN record
			UinLookupPage.explicitWaitForElementToBeClickable("FirstUinLink");
			UinLookupPage.click("FirstUinLink");

			//Verifying UIN history title
			UinLookupPage.explicitWaitForVisibility("UinHistoryTitle");
			UinLookupPage.verifyTextValue("UinHistoryTitle",map.get("UinHistoryTitle"));

			//Verifying UIN history title
			UinLookupPage.explicitWaitForVisibility("UinHistoryTitle");
			UinLookupPage.verifyTextValue("UinHistoryTitle",map.get("UinHistoryTitle"));

			//Verifying buttons 
			UinLookupPage.verifyElementIsPresent("BackButton");
			UinLookupPage.verifyElementIsPresent("SaveButton");
			UinLookupPage.verifyElementIsPresent("ResetButton");
			UinLookupPage.verifyElementIsPresent("RefreshButton");
			UinLookupPage.verifyElementIsPresent("GridViewMenu");

			//Verifying grid view menu options
			UinLookupPage.explicitWaitForElementToBeClickable("GridViewMenu");
			UinLookupPage.click("GridViewMenu");
			UinLookupPage.explicitWaitForElementToBeClickable("ResetView");
			UinLookupPage.verifyTextValue("ResetView",map.get("ResetView"));
			UinLookupPage.verifyTextValue("ColumnFilter",map.get("ColumnFilter"));
			UinLookupPage.verifyTextValue("RowSelector",map.get("RowSelector"));
			UinLookupPage.verifyTextValue("ExportToCsv",map.get("ExportToCsv"));
			UinLookupPage.click("GridViewMenu");

			//Verifying labels
			UinLookupPage.verifyTextValue("ItemLabel",map.get("ItemLabel"));
			UinLookupPage.verifyTextValue("ItemDescriptionLabel",map.get("ItemDescriptionLabel"));
			UinLookupPage.verifyTextValue("SerialNumberLabel",map.get("SerialNumberLabel"));
			UinLookupPage.verifyTextValue("UinHistoryStatusLabel",map.get("Status"));

			//Verifying text box
			UinLookupPage.verifyElementIsPresent("ItemTextBox");
			UinLookupPage.verifyElementIsPresent("ItemDescriptionTextBox");
			UinLookupPage.verifyElementIsPresent("SerialNumberTextBox");
			UinLookupPage.verifyElementIsPresent("UinHistoryStatusDropDown");

			//Verifying column name
			UinLookupPage.verifyTextValue("StoreColumnName",map.get("Store"));
			UinLookupPage.verifyTextValue("DateColumnName",map.get("Date"));
			UinLookupPage.verifyTextValue("StatusColumnName",map.get("Status"));
			UinLookupPage.verifyTextValue("FunctionalAreaColumnName",map.get("FunctionalArea"));
			UinLookupPage.verifyTextValue("TransactionIdColumnName",map.get("TransactionId"));
			UinLookupPage.verifyTextValue("UserColumnName",map.get("User"));

			//Verifying Filters
			UinLookupPage.verifyElementIsPresent("FilterStore");
			UinLookupPage.verifyElementIsPresent("FilterDate");
			UinLookupPage.verifyElementIsPresent("FilterStatus");
			UinLookupPage.verifyElementIsPresent("FilterFunctionalArea");
			UinLookupPage.verifyElementIsPresent("FilterTransactionId");
			UinLookupPage.verifyElementIsPresent("FilterUser");		
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
