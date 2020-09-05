package com.oracle.sim.testcases.ItemLookup;

/**
 * * @author vsurti 
 *
 */


import java.util.Map;
import java.util.logging.Logger;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.oracle.core.dataProvider.ExcelReader;
import com.oracle.core.utils.PropertyReader;
import com.oracle.sim.pageFactory.PageFactory;
import com.oracle.sim.pages.Base.SimBasePage;
import com.oracle.sim.utils.SIMWebdriverBase;

public class ItemDetailItemLocationsNavigationUI {
	public static Logger logger = Logger.getLogger(ItemDetailItemLocationsNavigationUI.class.getName());
	protected static PropertyReader propReader= new PropertyReader();
	PageFactory pageFactory = new PageFactory();
	
	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage LookupPage;
	SimBasePage ItemLookupPage;
	SimBasePage ItemDetailPage;
	SimBasePage RoleMaintenancePage;
	
	@BeforeClass()
	public void setup(ITestContext context) throws Exception {
		logger.info("TestCase Name: " +logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
		LoginPage= pageFactory.initialize("LoginPage");
		HomePage = pageFactory.initialize("HomePage");
		LookupPage = pageFactory.initialize("LookupPage");
		ItemLookupPage = pageFactory.initialize("ItemLookupPage");
		ItemDetailPage = pageFactory.initialize("ItemDetailPage");
		RoleMaintenancePage=pageFactory.initialize("RoleMaintenancePage");
		
		//SIM application login
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username", LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password", LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");
		HomePage.verifyTextValue("Title", HomePage.getProperty("Title"));
		HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
		HomePage.storeIdCheck();
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
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.storeIdCheck();
		HomePage.click("Navigation");
		HomePage.click("Security");
		HomePage.click("RoleMaintenance");

		//Verifying RoleMaintenance Page Title
		RoleMaintenancePage.explicitWaitForElementToBeClickable("Title");
		RoleMaintenancePage.verifyTextValue("Title",map.get("Title"));

		//Granting the user permission
		String userRole=propReader.getApplicationproperty("UserRole");
		RoleMaintenancePage.verifyUserRole(userRole, map.get("AccessItemLookup"), map.get("AssignedDataNo"));	
	}
	
	@DataProvider(name = "ItemDetailNavigationUITestData")
	public Object[][] getTestDataForCustomerPhone() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("ItemDetailTestData"),"IDNavigationUI");
		return testObjArray;
	}
	
	@Test(dataProvider="ItemDetailNavigationUITestData",priority=2)
	public void validateNavigationUI(Map<String, String> map) throws Exception {
		
		//Navigating to Item lookup
		HomePage.explicitWaitForElementToBeClickable("Lookup");
		//HomePage.click("Navigation");
		HomePage.click("Lookup");
		ItemLookupPage.click("ItemLookUpOption");
		
		//Search for an item & verify item detail screen title
		ItemLookupPage.click("ItemIdTextBox");
		ItemLookupPage.enterIntoTextBox("ItemIdTextBox", map.get("ItemId"));
		ItemLookupPage.click("SearchHeading");
		ItemDetailPage.explicitWaitForVisibility("ItemDetailTitle");
		ItemDetailPage.verifyElementIsPresent("ItemDetailTitle");
		ItemDetailPage.verifyPartialTextValue("ItemDetailTitle", map.get("Title"));
		
		
		//Scroll to stock locator field
		ItemDetailPage.scrollToViewElement("StockLocatorTab");
		ItemDetailPage.explicitWaitForElementToBeClickable("StockLocatorTab");
		ItemDetailPage.click("StockLocatorTab");
		ItemLookupPage.verifyTableRecordsCount("TableRows", "BuddyStoreHeader", "FilterCell");
		logger.info("Grid structure");
		
		//Verifying stock locator grid fields
		ItemDetailPage.verifyElementIsPresent("LocationTypeHeader");
		ItemDetailPage.verifyElementIsPresent("LocationHeader");
		ItemDetailPage.verifyElementIsPresent("AvailableSOHHeader");
		ItemDetailPage.verifyElementIsPresent("ReceivedTodayHeader");
		ItemDetailPage.verifyElementIsPresent("BuddyStoreHeader");
		
		
	}


	@AfterClass
	public void tearDown() {
		try {	
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
