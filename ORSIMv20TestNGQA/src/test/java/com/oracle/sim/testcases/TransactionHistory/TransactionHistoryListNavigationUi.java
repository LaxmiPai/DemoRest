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

public class TransactionHistoryListNavigationUi {
	public static Logger logger=Logger.getLogger(TransactionHistoryListNavigationUi.class.getName());
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

	@DataProvider(name = "ListNavigationUiTestData")
	public Object[][] getListNavigationUiTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("TransactionHistoryTestData"),
				"ListNavigationUi");
		return testObjArray;
	}

	@Test(dataProvider="ListNavigationUiTestData", priority=2,dependsOnMethods = {"verifyRole"})
	public void verifyListNavigationUi(Map<String, String> map) throws Exception {
		//Navigating to transaction history page
		logger.info("Method Name: verifyListNavigationUi");
		HomePage.explicitWaitForElementToBeClickable("InventoryManagement");
		HomePage.click("InventoryManagement");
		HomePage.explicitWaitForElementToBeClickable("TransactionHistory");
		HomePage.click("TransactionHistory");

		//Verifying the title of the screen
		TransactionHistoryPage.explicitWaitForVisibility("Title");
		TransactionHistoryPage.verifyTextValue("Title",map.get("Title"));

		//Filtering Transaction History by selecting a date for selecting a record- this is not mentioned in the test steps
		TransactionHistoryPage.explicitWaitForElementToBeClickable("SearchCriteriaDialogTitle");
		TransactionHistoryPage.selectDateFromDatePicker("FromDateTextBox",map.get("FromDate"));
		TransactionHistoryPage.selectDateFromDatePicker("ToDateTextBox",map.get("ToDate"));

		//Clicking on search button for selecting a record- this is not mentioned in the test steps
		TransactionHistoryPage.explicitWaitForElementToBeClickable("SearchButton");
		TransactionHistoryPage.click("SearchButton");
		HomePage.click("Navigation");

		//Verifying the records displayed on the screen
		TransactionHistoryPage.explicitWaitForElementToBeClickable("Date");
		int rowCount=TransactionHistoryPage.getTableRowCount("DateRecord","User","FilterDate");
		if(rowCount==0) {
			System.out.println("No table records found");
		}
		else {
			System.out.println("Table Records Count: "+rowCount);

			//Verifying the sorting order of the Transaction history records
			TransactionHistoryPage.columnSorting("DateRecord","Date",map.get("SortingOrder"),map.get("DataType"));
		}

		//Verifying the table Bar and buttons
		TransactionHistoryPage.explicitWaitForElementToBeClickable("SearchIcon");
		TransactionHistoryPage.verifyElementIsPresent("SearchIcon");
		TransactionHistoryPage.click("SearchIcon");

		//Verifying the title of the transaction history search criteria dialog box
		TransactionHistoryPage.explicitWaitForElementToBeClickable("SearchCriteriaDialogTitle");
		TransactionHistoryPage.verifyTextValue("SearchCriteriaDialogTitle",map.get("SearchCriteriaTitle"));

		//Filtering Transaction History by selecting a date
		TransactionHistoryPage.selectDateFromDatePicker("FromDateTextBox",map.get("FromDate"));
		TransactionHistoryPage.selectDateFromDatePicker("ToDateTextBox",map.get("ToDate"));

		//Clicking on a search button	
		TransactionHistoryPage.explicitWaitForElementToBeClickable("SearchButton");
		TransactionHistoryPage.click("SearchButton");

		//Verifying selected search criteria record
		TransactionHistoryPage.explicitWaitForElementToBeClickable("DateRecord");
		TransactionHistoryPage.explicitWaitForElementToBeClickable("SearchToolBarText");
		TransactionHistoryPage.verifyElementIsPresent("SearchToolBarText");
		TransactionHistoryPage.verifyTextValue("SearchToolBarText",map.get("SearchToolBarText"));

		//Verifying Refresh button
		TransactionHistoryPage.verifyElementIsPresent("RefreshButton");
		TransactionHistoryPage.verifyElementIsPresent("GridViewMenu");

		//Verifying grid view menu options
		TransactionHistoryPage.explicitWaitForElementToBeClickable("GridViewMenu");
		TransactionHistoryPage.click("GridViewMenu");
		TransactionHistoryPage.explicitWaitForElementToBeClickable("ResetView");
		TransactionHistoryPage.verifyTextValue("ResetView",map.get("ResetView"));
		TransactionHistoryPage.verifyTextValue("ColumnFilter",map.get("ColumnFilter"));
		TransactionHistoryPage.verifyTextValue("ExportToCsv",map.get("ExportToCsv"));
		TransactionHistoryPage.click("GridViewMenu");

		//Verifying column name
		TransactionHistoryPage.verifyTextValue("DateColumnName",map.get("Date"));
		TransactionHistoryPage.verifyTextValue("TypeColumnName",map.get("Type"));
		TransactionHistoryPage.verifyTextValue("TransactionIdColumnName",map.get("TransactionId"));
		TransactionHistoryPage.verifyTextValue("ItemColumnName",map.get("Item"));
		TransactionHistoryPage.verifyTextValue("DescriptionColumnName",map.get("Description"));
		TransactionHistoryPage.verifyTextValue("ReasonColumnName",map.get("Reason"));
		TransactionHistoryPage.verifyTextValue("SohColumnName",map.get("Soh"));
		TransactionHistoryPage.scrollToViewElement("User");
		TransactionHistoryPage.verifyTextValue("UnavailableColumnName",map.get("Unavailable"));
		TransactionHistoryPage.verifyTextValue("UserColumnName",map.get("User"));
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
