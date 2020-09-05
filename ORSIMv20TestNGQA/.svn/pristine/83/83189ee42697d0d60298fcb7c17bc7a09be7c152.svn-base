package com.oracle.sim.testcases.TransactionHistory;

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

public class TransactionHistorySearchUsingFromAndToDates {
	public static Logger logger=Logger.getLogger(TransactionHistorySearchUsingFromAndToDates.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pagefactory=new PageFactory();

	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage RoleMaintenancePage;
	SimBasePage TransactionHistoryPage;

	@BeforeClass()
	public void setup(ITestContext context) throws Exception {
		logger.info("TestCase Name: " +logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
		LoginPage=pagefactory.initialize("LoginPage");
		HomePage=pagefactory.initialize("HomePage");
		RoleMaintenancePage=pagefactory.initialize("RoleMaintenancePage");
		TransactionHistoryPage=pagefactory.initialize("TransactionHistoryPage");
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
		RoleMaintenancePage.verifyUserRole(userRole, map.get("AccessTransactionHistory"), map.get("AssignedDataNo"));
	}

	@DataProvider(name = "FromToDateSearchTestData")
	public Object[][] getFromToDateSearchTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("TransactionHistoryTestData"),
				"FromToDateSearch");
		return testObjArray;
	}

	@Test(dataProvider="FromToDateSearchTestData" ,priority=2 ,dependsOnMethods = {"verifyRole"})
	public void verifyFromToDateSearch(Map<String, String> map) throws Exception {
		//Navigating to transaction history page
		logger.info("Method Name: verifyFromToDateSearch");
		HomePage.explicitWaitForElementToBeClickable("InventoryManagement");
		HomePage.click("InventoryManagement");
		HomePage.explicitWaitForElementToBeClickable("TransactionHistory");
		HomePage.click("TransactionHistory");

		//Verifying the title of the screen
		TransactionHistoryPage.explicitWaitForVisibility("Title");
		TransactionHistoryPage.verifyTextValue("Title",map.get("Title"));

		//Verifying the title of the transaction history search criteria dialog box
		TransactionHistoryPage.explicitWaitForElementToBeClickable("SearchCriteriaDialogTitle");
		TransactionHistoryPage.verifyTextValue("SearchCriteriaDialogTitle",map.get("SearchCriteriaTitle"));

		//Verifying the default from date is equal to current date
		TransactionHistoryPage.explicitWaitForElementToBeClickable("FromDateTextBox");
		TransactionHistoryPage.verifyDefaultDateWithCurrentDate("FromDateTextBox");

		//Verifying the default to date is equal to current date
		TransactionHistoryPage.explicitWaitForElementToBeClickable("ToDateTextBox");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
		TransactionHistoryPage.verifyDefaultDateWithCurrentDate("ToDateTextBox");

		//Clearing From and To dates for verifying table records
		TransactionHistoryPage.clearElement("FromDateTextBox");
		TransactionHistoryPage.clearElement("ToDateTextBox");

		//Clicking on search button
		TransactionHistoryPage.explicitWaitForElementToBeClickable("SearchButton");
		TransactionHistoryPage.click("SearchButton");

		//Verifying the records displayed on the screen
		TransactionHistoryPage.explicitWaitForElementToBeClickable("DateRecord");
		TransactionHistoryPage.verifyTableRecordsCount("DateRecord","UserColumnName","FilterDate");

		//Clicking on search icon
		TransactionHistoryPage.explicitWaitForElementToBeClickable("SearchIcon");
		TransactionHistoryPage.click("SearchIcon");

		//Entering from date
		TransactionHistoryPage.selectDateFromDatePicker("FromDateTextBox",map.get("FromDate"));

		//giving to date lesser than To date
		TransactionHistoryPage.selectDateFromDatePicker("ToDateTextBox",map.get("ToDate"));

		//Clicking on search button
		TransactionHistoryPage.explicitWaitForElementToBeClickable("SearchButton");
		TransactionHistoryPage.click("SearchButton");

		//Verifying the error message for From date greater than To date
		TransactionHistoryPage.verifyTextValue("ToDateErrorMessage",map.get("ErrorMessage"));

		//Clicking on reset button
		TransactionHistoryPage.explicitWaitForElementToBeClickable("ResetButton");
		TransactionHistoryPage.click("ResetButton");

		//Verifying all values are defaulted to default values after clicking on reset button
		TransactionHistoryPage.explicitWaitForElementToBeClickable("TypeDropDown");
		TransactionHistoryPage.verifyTextValue("TypeDropDown",map.get("TypeDropDown"));
		TransactionHistoryPage.verifyTextValue("ReasonDropDown",map.get("ReasonDropDown"));
		TransactionHistoryPage.verifyDefaultDateWithCurrentDate("FromDateTextBox");
		//Added time due to Sanity failure
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TransactionHistoryPage.verifyDefaultDateWithCurrentDate("ToDateTextBox");

		//Verifying the title of the transaction history search criteria dialog Box
		TransactionHistoryPage.explicitWaitForElementToBeClickable("SearchCriteriaDialogTitle");
		TransactionHistoryPage.verifyTextValue("SearchCriteriaDialogTitle",map.get("SearchCriteriaTitle"));

		//Clicking on cancel button
		TransactionHistoryPage.explicitWaitForElementToBeClickable("CancelButton");
		TransactionHistoryPage.click("CancelButton");
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
