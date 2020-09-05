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

public class TransactionHistorySearchCriteriaNavigationUi {
	public static Logger logger=Logger.getLogger(TransactionHistorySearchCriteriaNavigationUi.class.getName());
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

	@DataProvider(name = "SearchCriteriaUiTestData")
	public Object[][] getSearchCriteriaUiTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("TransactionHistoryTestData"),
				"SearchCriteriaNavigation");
		return testObjArray;
	}

	@Test(dataProvider="SearchCriteriaUiTestData",priority=2 ,dependsOnMethods = {"verifyRole"})
	public void verifySearchCriteriaUi(Map<String, String> map) throws Exception {
		//Navigating to transaction history page
		logger.info("Method Name: verifySearchCriteriaUi");
		HomePage.explicitWaitForElementToBeClickable("InventoryManagement");
		HomePage.click("InventoryManagement");
		HomePage.explicitWaitForElementToBeClickable("TransactionHistory");
		HomePage.click("TransactionHistory");

		//Verifying the title of the screen
		TransactionHistoryPage.explicitWaitForVisibility("Title");
		TransactionHistoryPage.verifyTextValue("Title",map.get("Title"));

		//verify transaction history search criteria screen is displayed
		TransactionHistoryPage.verifyElementIsPresent("SearchCriteriaDialogTitle");

		//Verifying the title of the transaction history search criteria dialog box
		TransactionHistoryPage.explicitWaitForElementToBeClickable("SearchCriteriaDialogTitle");
		TransactionHistoryPage.verifyTextValue("SearchCriteriaDialogTitle",map.get("SearchCriteriaTitle"));

		//Verifying the different search criteria
		TransactionHistoryPage.verifyElementIsPresent("FromDate");
		TransactionHistoryPage.verifyElementIsPresent("ToDate");
		TransactionHistoryPage.verifyElementIsPresent("TypeDropDown");
		TransactionHistoryPage.verifyElementIsPresent("ItemTextBox");
		TransactionHistoryPage.verifyElementIsPresent("ReasonDropDown");		
		TransactionHistoryPage.verifyElementIsPresent("UserTextBox");		
		TransactionHistoryPage.verifyElementIsPresent("SearchLimit");

		//Verifying the buttons available on the screen
		TransactionHistoryPage.verifyElementIsPresent("SearchButton");
		TransactionHistoryPage.verifyElementIsPresent("ResetButton");
		TransactionHistoryPage.verifyElementIsPresent("CancelButton");	
		TransactionHistoryPage.click("CloseButton");
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
