package com.oracle.sim.testcases.UinLookup;

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

public class UinLookupViewHistoryLink {
	public static Logger logger=Logger.getLogger(UinLookupViewHistoryLink.class.getName());
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

		//Granting the user permission
		String userRole=propReader.getApplicationproperty("UserRole");
		RoleMaintenancePage.verifyUserRole(userRole, map.get("ViewUinHistory"), map.get("AssignedDataNo"));	
	}

	@DataProvider(name = "UinHistoryTestData")
	public Object[][] getUinHistoryTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("UinLookupTestData"),
				"ViewHistoryLink");
		return testObjArray;
	}

	@Test(dataProvider="UinHistoryTestData",priority=2 ,dependsOnMethods = {"verifyRole"})
	public void verifyUinHistory(Map<String,String> map) throws Exception {
		//Navigating to UIN lookup page
		logger.info("Method Name: verifyUinHistory");
		HomePage.click("Lookup");
		HomePage.click("UinLookup");

		//Verifying the Page Title
		UinLookupPage.explicitWaitForVisibility("Title");
		UinLookupPage.verifyTextValue("Title",map.get("Title"));

		//Searching for an item
		UinLookupPage.explicitWaitForVisibility("UinLookupItemTextBox");
		UinLookupPage.explicitWaitForElementToBeClickable("UinLookupItemTextBox");
		UinLookupPage.click("UinLookupItemTextBox");
		UinLookupPage.enterIntoTextBox("UinLookupItemTextBox",map.get("ItemId"));

		//Clearing last update from date text box for getting a record
		UinLookupPage.clearElement("LastUpdateFromDateDatePickerInput");

		//Click on a search button
		UinLookupPage.explicitWaitForElementToBeClickable("SearchButton");
		UinLookupPage.click("SearchButton");

		//verifying UIN list records are displayed
		UinLookupPage.explicitWaitForVisibility("ItemUinListTitle");
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

			//Verifying back button and clicking on back button 
			UinLookupPage.verifyElementIsPresent("BackButton");
			UinLookupPage.explicitWaitForElementToBeClickable("BackButton");
			UinLookupPage.click("BackButton");

			//Verifying Item UIN List title
			UinLookupPage.explicitWaitForVisibility("ItemUinListTitle");
			UinLookupPage.verifyTextValue("ItemUinListTitle",map.get("ItemUinListTitle"));
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
