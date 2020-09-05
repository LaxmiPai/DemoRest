//Author : shhg

package com.oracle.sim.testcases.ticketFilter;

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

public class TicketFilterItemFieldValidation {
	public static Logger logger=Logger.getLogger(TicketFilterItemFieldValidation.class.getName());
	public static PropertyReader propReader = new PropertyReader();
	PageFactory pageFactory = new PageFactory();
	
	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage RoleMaintenancePage;
	SimBasePage ticketFilterPage;
		
	@BeforeClass()
	public void setup(ITestContext context) throws Exception{
		logger.info("TestCase Name: "+logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
		LoginPage=pageFactory.initialize("LoginPage");
		HomePage=pageFactory.initialize("HomePage");
		RoleMaintenancePage=pageFactory.initialize("RoleMaintenancePage");
		ticketFilterPage=pageFactory.initialize("ticketFilterPage");
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username", LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password", LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");
		HomePage.verifyTextValue("Title", HomePage.getProperty("Title"));
		HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
		HomePage.storeIdCheck();
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.click("Navigation");
	}
	
	@DataProvider (name="ticketFilterAccessTestData")
	public Object[][] getticketFilterAccessTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("ticketFilterTestData"), "Permission");
		return testObjArray;
	}
	
	@Test(dataProvider="ticketFilterAccessTestData", priority=1)
	public void verifyRole(Map<String,String> map) throws Exception{
	//Navigating to role maintenance page
	logger.info("Method Name: verifyRole");
	HomePage.click("Security");
	HomePage.click("RoleMaintenance");
		
	//Verifying RoleMaintenance Page Title
 	RoleMaintenancePage.explicitWaitForElementToBeClickable("Title");
	RoleMaintenancePage.verifyTextValue("Title",map.get("Title"));
		
	//Verifying a user permission
	String roleName=propReader.getApplicationproperty("UserRole");
	logger.info(roleName);
	RoleMaintenancePage.verifyUserRole(roleName, map.get("AccessTicketFilter"), map.get("AssignedDataNo"));
	
	}	
		
	@DataProvider (name="ticketFilterItemTestData")
	public Object[][] getticketFilterItemTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("ticketFilterTestData"), "Item");
		return testObjArray;
	}	
	
	@Test(dataProvider = "ticketFilterItemTestData", priority=2)
	public void verifyTicketFilterItemField(Map<String, String> map) throws Exception {
		
		logger.info("Method Name: verifyTicketFilterItemField");
		ticketFilterPage.explicitWaitForElementToBeClickable("InventoryMgmt");
		ticketFilterPage.click("InventoryMgmt");
		ticketFilterPage.explicitWaitForElementToBeClickable("TicketMenu");
		ticketFilterPage.click("TicketMenu");
		ticketFilterPage.verifyTextValue("SearchHeading", map.get("SearchHeader"));
		ticketFilterPage.enterIntoTextBox("ItemInput", map.get("InvalidItem"));
		ticketFilterPage.click("SearchButton");
		ticketFilterPage.verifyTextValue("ItemError", map.get("InvalidItemError"));
		ticketFilterPage.enterIntoTextBox("ItemInput", map.get("ValidItem"));
		/*ticketFilterPage.click("PrintedDropdown");
		ticketFilterPage.click("PintedAll");*/
		ticketFilterPage.clearElement("FromPrintDValue");
		ticketFilterPage.clearElement("ToPrintDValue");
		ticketFilterPage.clearElement("FromActiveDValue");
		ticketFilterPage.clearElement("ToActiveDValue");
		ticketFilterPage.clearElement("FromEndDValue");
		ticketFilterPage.clearElement("ToEndDValue");
		//ticketFilterPage.clearElement("FromPrintedDValue");
		//ticketFilterPage.clearElement("ToPrintedDValue");
		ticketFilterPage.click("SearchLimitValue");
		ticketFilterPage.enterIntoTextBox("SearchLimitValue", map.get("SearchLimit"));
		ticketFilterPage.click("SearchButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		ticketFilterPage.verifyTableRowContentsOfColumn("ItemColumnIndex", map.get("ValidItem"));
		
		//To check for invalid item ID
		ticketFilterPage.explicitWaitForElementToBeClickable("SummarySearch");
		ticketFilterPage.click("SummarySearch");
		ticketFilterPage.verifyTextValue("SearchHeading", map.get("SearchHeader"));
		ticketFilterPage.enterIntoTextBox("ItemInput", map.get("InvalidItemSearch"));
		ticketFilterPage.click("SearchButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		
		//Need to check if no results are available
		ticketFilterPage.verifyNoRecordsAreInSummary("ItemDataIndex");
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