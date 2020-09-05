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

public class TransactionHistoryListRecordValidation {
	public static Logger logger=Logger.getLogger(TransactionHistoryListRecordValidation.class.getName());
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

	@DataProvider(name = "TransactionHistoryRecordsTestData")
	public Object[][] getTransactionHistoryRecordsTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("TransactionHistoryTestData"),"RecordValidation");
		return testObjArray;
	}

	@Test(dataProvider="TransactionHistoryRecordsTestData", priority=2 ,dependsOnMethods = {"verifyRole"})
	public void validateRecords(Map<String, String> map) throws Exception {
		//Navigating to transaction history page
		logger.info("Method Name: validateRecords");
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

		//Clicking on a search button for selecting a record- this is not mentioned in the test steps
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

			//Verifying the Fields
			TransactionHistoryPage.explicitWaitForElementToBeClickable("Date");
			TransactionHistoryPage.verifyTextValue("Date",map.get("Date"));
			TransactionHistoryPage.verifyTextValue("Type",map.get("Type"));
			TransactionHistoryPage.verifyTextValue("TransactionId",map.get("TransactionId"));
			TransactionHistoryPage.verifyTextValue("Item",map.get("Item"));
			TransactionHistoryPage.verifyTextValue("Description",map.get("Description"));
			TransactionHistoryPage.verifyTextValue("Reason",map.get("Reason"));
			TransactionHistoryPage.verifyTextValue("SOH",map.get("SOH"));
			TransactionHistoryPage.scrollToViewElement("User");
			TransactionHistoryPage.verifyTextValue("Unavailable",map.get("Unavailable"));
			TransactionHistoryPage.verifyTextValue("User",map.get("User"));

			//Verifying all Transaction records are read-only
			TransactionHistoryPage.verifyAllTableRecordsReadOnly("DateRecord","Date","FilterDate");
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
