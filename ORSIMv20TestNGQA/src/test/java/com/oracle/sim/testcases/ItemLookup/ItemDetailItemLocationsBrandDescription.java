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

public class ItemDetailItemLocationsBrandDescription {
	public static Logger logger = Logger.getLogger(ItemDetailItemLocationsBrandDescription.class.getName());
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
	
	@DataProvider(name = "ItemDetailBrandDescriptionTestData")
	public Object[][] getTestDataForCustomerPhone() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("ItemDetailTestData"),"IDNavigationUI");
		return testObjArray;
	}
	
	@Test(dataProvider="ItemDetailBrandDescriptionTestData",priority=2)
	public void validateBrandDescription(Map<String, String> map) throws Exception {
		
		//Navigating to Item lookup
		HomePage.explicitWaitForElementToBeClickable("Lookup");
		//HomePage.click("Navigation");
		HomePage.click("Lookup");
		ItemLookupPage.click("ItemLookUpOption");
		
		//Verify Brand Description field on Item lookup page
		ItemLookupPage.verifyElementIsPresent("BrandDescription");
		ItemLookupPage.verifyElementIsEditable("BrandDescriptionTextBox");
		logger.info("BrandDescription field is present & editable");
		
		//Search for an item & verify item detail screen title
		ItemLookupPage.click("ItemIdTextBox");
		ItemLookupPage.enterIntoTextBox("ItemIdTextBox", map.get("ItemId"));
		ItemLookupPage.click("SearchHeading");
		ItemDetailPage.explicitWaitForVisibility("ItemDetailTitle");
		ItemDetailPage.verifyElementIsPresent("ItemDetailTitle");
		ItemDetailPage.verifyPartialTextValue("ItemDetailTitle", map.get("Title"));
		
		//Verify Brand Description field inside Item attribute tab
		ItemDetailPage.scrollToViewElement("ItemAttributesTab");
		ItemDetailPage.explicitWaitForElementToBeClickable("ItemAttributesTab");
		ItemDetailPage.click("ItemAttributesTab");
		ItemDetailPage.verifyElementIsPresent("BrandDescriptionLabel");

		
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
