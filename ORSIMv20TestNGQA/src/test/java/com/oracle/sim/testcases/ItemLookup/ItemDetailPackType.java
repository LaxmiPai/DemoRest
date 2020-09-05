package com.oracle.sim.testcases.ItemLookup;

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

public class ItemDetailPackType {
	public static Logger logger=Logger.getLogger(ItemDetailPackType.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pagefactory=new PageFactory();

	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage RoleMaintenancePage;
	SimBasePage ItemLookupPage;

	@BeforeClass()
	public void setup(ITestContext context) throws Exception {
		logger.info("TestCase Name: " +logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
		LoginPage=pagefactory.initialize("LoginPage");	
		HomePage=pagefactory.initialize("HomePage");
		RoleMaintenancePage=pagefactory.initialize("RoleMaintenancePage");
		ItemLookupPage=pagefactory.initialize("ItemLookupPage");

		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username",LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password",LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");
		HomePage.explicitWaitForVisibility("Title");
		HomePage.verifyTextValue("Title", LoginPage.getProperty("Title"));
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
		HomePage.explicitWaitForElementToBeClickable("Security");
		HomePage.click("Security");
		HomePage.click("RoleMaintenance");

		//Verifying RoleMaintenance Page Title
		RoleMaintenancePage.explicitWaitForElementToBeClickable("Title");
		RoleMaintenancePage.verifyTextValue("Title",map.get("Title"));

		//Granting the user permission
		String userRole=propReader.getApplicationproperty("UserRole");
		RoleMaintenancePage.verifyUserRole(userRole, map.get("AccessItemLookup"), map.get("AssignedDataNo"));	
	}

	@DataProvider(name = "PackItemTestData")
	public Object[][] getAddAttributeTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("ItemLookupTestData"),"PackItem");
		return testObjArray;
	}

	@Test(dataProvider="PackItemTestData",priority=2,dependsOnMethods= {"verifyRole"})
	public void packTypeSearch(Map<String, String> map) {
		//Navigating to item lookup page 
		logger.info("Method Name: packTypeSearch");
		HomePage.explicitWaitForElementToBeClickable("Lookup");
		HomePage.click("Lookup");
		HomePage.explicitWaitForElementToBeClickable("ItemLookup");
		HomePage.click("ItemLookup");

		//Verifying the item lookup title
		ItemLookupPage.explicitWaitForVisibility("Title");
		ItemLookupPage.verifyTextValue("Title",map.get("ItemLookupTitle"));

		//Clicking on the search icon
		ItemLookupPage.explicitWaitForElementToBeClickable("SearchLimitInputBox");
		ItemLookupPage.explicitWaitForElementToBeClickable("SearchIcon");
		ItemLookupPage.click("SearchIcon");		

		//Clicking on a pack item
		ItemLookupPage.explicitWaitForVisibility("ItemIdColumnRecords");
		ItemLookupPage.explicitWaitForElementToBeClickable("ItemFilter");
		ItemLookupPage.click("ItemFilter");
		ItemLookupPage.enterIntoTextBox("ItemFilter",map.get("PackItemNo"));
		ItemLookupPage.click("GridRecord");

		//Verifying item detail title
		ItemLookupPage.explicitWaitForElementToBeClickable("ItemDetailTitle");
		ItemLookupPage.verifyTextValue("ItemDetailTitle",map.get("ItemDetailTitle")+""+map.get("PackItemNo"));

		//pack item type verification
		ItemLookupPage.explicitWaitForVisibility("TotalSohLabel");
		ItemLookupPage.scrollToViewElement("PackItemsTab");
		ItemLookupPage.explicitWaitForElementToBeClickable("PackItemsTab");
		ItemLookupPage.click("PackItemsTab");
		if(ItemLookupPage.isVisible("TabMessage")) {
			System.out.println(ItemLookupPage.getText("TabMessage"));
		}
		else {
			//Verifying the simple pack type item
			ItemLookupPage.explicitWaitForElementToBeClickable("FilterPackType");
			ItemLookupPage.click("FilterPackType");
			ItemLookupPage.enterIntoTextBox("FilterPackType",map.get("SimplePackType"));
			if(ItemLookupPage.isVisible("GridRecord")) {
				ItemLookupPage.verifyTextValue("GridRecordPackType",map.get("SimplePackType"));
			}
			//Verifying the complex pack type item
			ItemLookupPage.explicitWaitForElementToBeClickable("FilterPackType");
			ItemLookupPage.click("FilterPackType");
			ItemLookupPage.enterIntoTextBox("FilterPackType",map.get("ComplexPackType"));
			ItemLookupPage.explicitWaitForInvisibility("GridRecordPackType");
			if(ItemLookupPage.isVisible("GridRecord")) {
				ItemLookupPage.verifyTextValue("GridRecordPackType",map.get("ComplexPackType"));
			}
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
