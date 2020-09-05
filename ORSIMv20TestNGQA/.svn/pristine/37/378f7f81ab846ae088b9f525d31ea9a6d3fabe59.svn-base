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

public class TransactionHistoryListNavigationToActualTransaction {
	public static Logger logger=Logger.getLogger(TransactionHistoryListNavigationToActualTransaction.class.getName());
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

		//Granting the user permission
		String userRole=propReader.getApplicationproperty("UserRole");
		RoleMaintenancePage.verifyUserRole(userRole, map.get("AccessTransactionHistory"), map.get("AssignedDataNo"));
	}

	@DataProvider(name = "NavigationToActualTransactionTestData")
	public Object[][] getNavigationToActualTransactionTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("TransactionHistoryTestData"),
				"NavigationToActualTransaction");
		return testObjArray;
	}

	@Test(dataProvider="NavigationToActualTransactionTestData", priority=2,dependsOnMethods = {"verifyRole"})
	public void navigateToActualTransaction(Map<String, String> map) throws Exception {
		//Navigating to transaction history page
		logger.info("Method Name: navigateToActualTransaction");
		HomePage.explicitWaitForElementToBeClickable("InventoryManagement");
		HomePage.click("InventoryManagement");
		HomePage.explicitWaitForElementToBeClickable("TransactionHistory");
		HomePage.click("TransactionHistory");

		//Verifying the title of the screen
		TransactionHistoryPage.explicitWaitForVisibility("Title");
		TransactionHistoryPage.verifyTextValue("Title",map.get("Title"));		

		//Filtering Transaction History by Type
		TransactionHistoryPage.explicitWaitForElementToBeClickable("TypeDropDown");
		TransactionHistoryPage.selectDropDownValue("TypeDropDown",map.get("Type"));

		//Filtering Transaction History by selecting a date - this is not in test steps
		TransactionHistoryPage.explicitWaitForElementToBeClickable("SearchCriteriaDialogTitle");
		TransactionHistoryPage.selectDateFromDatePicker("FromDateTextBox",map.get("AvailableRecordFromDate"));
		TransactionHistoryPage.selectDateFromDatePicker("ToDateTextBox",map.get("AvailableRecordToDate"));

		//Clicking on search button
		TransactionHistoryPage.explicitWaitForElementToBeClickable("SearchButton");
		TransactionHistoryPage.click("SearchButton");
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.click("Navigation");

		//Verifying the records displayed on the screen
		TransactionHistoryPage.explicitWaitForElementToBeClickable("Date");
		int rowCount=TransactionHistoryPage.getTableRowCount("DateRecord","User","FilterDate");
		if(rowCount==0) {
			System.out.println("No table records found");
		}
		else {
			System.out.println("Table Records Count: "+rowCount);

			//fetching transaction history first record
			TransactionHistoryPage.explicitWaitForElementToBeClickable("TransactionIdFirstRecord");
			String firstRecord=TransactionHistoryPage.getText("TransactionIdFirstRecord");

			//Filtering the fetched transaction id
			TransactionHistoryPage.explicitWaitForElementToBeClickable("FilterTransactionId");
			TransactionHistoryPage.click("FilterTransactionId");
			TransactionHistoryPage.enterIntoTextBox("FilterTransactionId",firstRecord);

			//Clicking on grid record
			TransactionHistoryPage.explicitWaitForElementToBeClickable("GridRecord");
			TransactionHistoryPage.click("GridRecord");

			//Verifying stock count authorization page title
			TransactionHistoryPage.explicitWaitForElementToBeClickable("StockCountAuthorizationDetailTitle");
			TransactionHistoryPage.verifyTextValue("StockCountAuthorizationDetailTitle",map.get("StockCountAuthorizationDetailTitle"));

			//Clicking on back button and Verifying transaction history page title
			TransactionHistoryPage.explicitWaitForElementToBeClickable("BackButton");
			TransactionHistoryPage.click("BackButton");
			TransactionHistoryPage.explicitWaitForVisibility("Title");
			TransactionHistoryPage.verifyTextValue("Title",map.get("Title"));

			//Clicking on search icon
			TransactionHistoryPage.explicitWaitForElementToBeClickable("SearchIcon");
			TransactionHistoryPage.click("SearchIcon");

			//Filtering Transaction History by selecting a date
			TransactionHistoryPage.explicitWaitForElementToBeClickable("FromDateTextBox");
			TransactionHistoryPage.selectDateFromDatePicker("FromDateTextBox",map.get("UnavailableRecordFromDate"));

			//Clicking on search button
			TransactionHistoryPage.explicitWaitForElementToBeClickable("SearchButton");
			TransactionHistoryPage.click("SearchButton");
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));

			//fetching transaction history last record
			TransactionHistoryPage.explicitWaitForInvisibility("SearchButton");
			TransactionHistoryPage.explicitWaitForElementToBeClickable("FilterTransactionId");
			int size=TransactionHistoryPage.getWebElementList("PurgedTransactionId").size();
			String lastRecord=TransactionHistoryPage.getWebElementList("PurgedTransactionId").get(size-1).getAttribute("innerText");
			//System.out.println("Last Record:"+lastRecord);

			//Filtering the fetched transaction id
			TransactionHistoryPage.explicitWaitForElementToBeClickable("FilterTransactionId");
			TransactionHistoryPage.click("FilterTransactionId");
			TransactionHistoryPage.enterIntoTextBox("FilterTransactionId",lastRecord);

			//Clicking on grid record
			TransactionHistoryPage.explicitWaitForElementToBeClickable("GridRecord");
			TransactionHistoryPage.click("GridRecord");

			//verifying the record that doesn't have any original transaction details 
			TransactionHistoryPage.explicitWaitForElementToBeClickable("NoTransactionHistoryDialogBoxText");
			TransactionHistoryPage.verifyTextValue("NoTransactionHistoryDialogBoxText",map.get("NoTransactionHistoryDialogBoxText"));

			//Clicking on ok button
			TransactionHistoryPage.click("OkButton");
			TransactionHistoryPage.explicitWaitForVisibility("Title");
			TransactionHistoryPage.verifyTextValue("Title",map.get("Title"));
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