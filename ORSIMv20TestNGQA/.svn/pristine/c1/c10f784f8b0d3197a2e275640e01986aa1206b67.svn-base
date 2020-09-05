package com.oracle.sim.testcases.PrinterSetup;

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

public class PrinterPreshipmentDefault {
	public static Logger logger=Logger.getLogger(PrinterPreshipmentDefault.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pagefactory=new PageFactory();

	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage RoleMaintenancePage;
	SimBasePage PrinterSetupPage;

	@BeforeClass()
	public void setup(ITestContext context) throws Exception {
		logger.info("TestCase Name: " +logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
		LoginPage=pagefactory.initialize("LoginPage");
		HomePage=pagefactory.initialize("HomePage");
		RoleMaintenancePage=pagefactory.initialize("RoleMaintenancePage");
		PrinterSetupPage=pagefactory.initialize("PrinterSetupPage");
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
		RoleMaintenancePage.verifyUserRole(userRole, map.get("AccessPrinterSetup"), map.get("AssignedDataNo"));	
	}

	@DataProvider(name = "PreshipmentDefaultTestData")
	public Object[][] getPreshipmentDefaultTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("PrinterTestData"),
				"PreshipmentDefault");
		return testObjArray;
	}

	@Test(dataProvider="PreshipmentDefaultTestData",priority=2 ,dependsOnMethods = {"verifyRole"})
	public void verifyPreshipmentDefaultPrinter(Map<String,String> map) throws Exception {
		//Navigating to printer setup screen
		logger.info("Method Name: verifyPreshipmentDefaultPrinter");
		HomePage.explicitWaitForElementToBeClickable("Admin");
		HomePage.click("Admin");
		HomePage.explicitWaitForElementToBeClickable("TechnicalMaintenance");
		HomePage.click("TechnicalMaintenance");
		HomePage.explicitWaitForElementToBeClickable("PrinterSetup");
		HomePage.click("PrinterSetup");

		//Verifying the Page Title
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.click("Navigation");
		PrinterSetupPage.explicitWaitForElementToBeClickable("Title");
		PrinterSetupPage.verifyTextValue("Title",map.get("Title"));

		//Verifying the records are displayed
		PrinterSetupPage.explicitWaitForVisibility("PreShipmentColumnName");
		PrinterSetupPage.explicitWaitForElementToBeClickable("FilterName");
		int size=PrinterSetupPage.getTableRowCount("NameColumnRecords","PreShipmentColumnName","FilterName");
		if(size==0) {
			System.out.println("No records found");
		}else {
			//Clicking on the add button to add a new printer
			PrinterSetupPage.explicitWaitForElementToBeClickable("Add");
			PrinterSetupPage.click("Add");

			//Entering Values in Detail section
			PrinterSetupPage.explicitWaitForElementToBeClickable("Name");
			PrinterSetupPage.enterIntoTextBox("Name", map.get("FirstName"));
			PrinterSetupPage.enterIntoTextBox("Description", map.get("FirstDescription"));
			PrinterSetupPage.selectDropDownValue("Type_dropdown", map.get("FirstType"));
			PrinterSetupPage.enterIntoTextBox("Address", map.get("FirstAddress"));
			PrinterSetupPage.click("PreshipmentDefaultButton");

			//clicking on apply button
			PrinterSetupPage.explicitWaitForElementToBeClickable("Apply");
			PrinterSetupPage.click("Apply");

			//clicking on save button
			PrinterSetupPage.explicitWaitForElementToBeClickable("Save");
			PrinterSetupPage.click("Save");
			PrinterSetupPage.explicitWaitForElementToBeClickable("Yes");
			PrinterSetupPage.click("Yes");
			//Wait for db commit
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));

			//Clicking on the add button to add a new printer
			PrinterSetupPage.explicitWaitForElementToBeClickable("Add");
			PrinterSetupPage.click("Add");

			//Entering Values in Detail section
			PrinterSetupPage.explicitWaitForElementToBeClickable("Name");
			PrinterSetupPage.enterIntoTextBox("Name", map.get("SecondName"));
			PrinterSetupPage.enterIntoTextBox("Description", map.get("SecondDescription"));
			PrinterSetupPage.selectDropDownValue("Type_dropdown", map.get("SecondType"));
			PrinterSetupPage.enterIntoTextBox("Address", map.get("SecondAddress"));
			PrinterSetupPage.click("PreshipmentDefaultButton");

			//clicking on apply button
			PrinterSetupPage.explicitWaitForElementToBeClickable("Apply");
			PrinterSetupPage.click("Apply");

			//Filtering existing printer name
			PrinterSetupPage.explicitWaitForElementToBeClickable("FilterName");
			PrinterSetupPage.click("FilterName");
			PrinterSetupPage.enterIntoTextBox("FilterName", map.get("FirstName"));

			//Filtering printer preshipment
			PrinterSetupPage.explicitWaitForElementToBeClickable("FilterPreShipment");
			PrinterSetupPage.click("FilterPreShipment");
			PrinterSetupPage.enterIntoTextBox("FilterPreShipment", map.get("PreShipmentDefaultNo"));

			//Verifying the existing printer preshipment default set to no  
			PrinterSetupPage.explicitWaitForVisibility("GridRecord");
			PrinterSetupPage.verifyTextValue("PreShipmentGridRecord",map.get("PreShipmentDefaultNo"));

			//clicking on save button
			PrinterSetupPage.explicitWaitForElementToBeClickable("Save");
			PrinterSetupPage.click("Save");

			//Verifying save message
			PrinterSetupPage.explicitWaitForElementToBeClickable("SaveMessage");
			PrinterSetupPage.verifyTextValue("SaveMessage",map.get("SaveMessage"));
			PrinterSetupPage.verifyElementIsPresent("Yes");
			PrinterSetupPage.verifyElementIsPresent("No");

			//Clicking on yes button
			PrinterSetupPage.explicitWaitForElementToBeClickable("Yes");
			PrinterSetupPage.click("Yes");
			//Wait for db commit
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));

			//Filtering existing printer name
			PrinterSetupPage.explicitWaitForElementToBeClickable("FilterName");
			PrinterSetupPage.click("FilterName");
			PrinterSetupPage.enterIntoTextBox("FilterName", map.get("FirstName"));

			//Clicking on a grid record
			PrinterSetupPage.explicitWaitForElementToBeClickable("GridRecord");
			PrinterSetupPage.click("GridRecord");

			//Clicking on a edit button
			PrinterSetupPage.explicitWaitForElementToBeClickable("Edit");
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
			PrinterSetupPage.click("Edit");

			//Edit existing printer with Default PreShipment as True,click on apply
			PrinterSetupPage.explicitWaitForElementToBeClickable("PreshipmentDefaultButton");
			PrinterSetupPage.click("PreshipmentDefaultButton");
			PrinterSetupPage.explicitWaitForElementToBeClickable("Apply");
			PrinterSetupPage.click("Apply");

			//Filtering existing printer name - second name
			PrinterSetupPage.explicitWaitForElementToBeClickable("FilterName");
			PrinterSetupPage.click("FilterName");
			PrinterSetupPage.enterIntoTextBox("FilterName", map.get("SecondName"));

			//Filtering printer preshipment value by 'no'
			PrinterSetupPage.explicitWaitForElementToBeClickable("FilterPreShipment");
			PrinterSetupPage.click("FilterPreShipment");
			PrinterSetupPage.enterIntoTextBox("FilterPreShipment", map.get("PreShipmentDefaultNo"));

			//Verifying the existing printer preshipment default set to no  
			PrinterSetupPage.explicitWaitForVisibility("GridRecord");
			PrinterSetupPage.verifyTextValue("PreShipmentGridRecord",map.get("PreShipmentDefaultNo"));

			//clicking on save button
			PrinterSetupPage.explicitWaitForElementToBeClickable("Save");
			PrinterSetupPage.click("Save");

			//Verifying save message
			PrinterSetupPage.explicitWaitForElementToBeClickable("SaveMessage");
			PrinterSetupPage.verifyTextValue("SaveMessage",map.get("SaveMessage"));
			PrinterSetupPage.verifyElementIsPresent("Yes");
			PrinterSetupPage.verifyElementIsPresent("No");

			//Clicking on yes button
			PrinterSetupPage.explicitWaitForElementToBeClickable("Yes");
			PrinterSetupPage.click("Yes");
			//Wait for db commit
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));

			//Removing the added printer
			PrinterSetupPage.explicitWaitForElementToBeClickable("FilterName");
			PrinterSetupPage.click("FilterName");
			PrinterSetupPage.enterIntoTextBox("FilterName", map.get("FirstName"));

			//Clicking on a grid record
			PrinterSetupPage.explicitWaitForVisibility("GridRecord");
			PrinterSetupPage.click("GridRecord");

			//Clicking on a remove button
			PrinterSetupPage.explicitWaitForVisibility("RemoveButton");
			PrinterSetupPage.click("RemoveButton");
			PrinterSetupPage.explicitWaitForVisibility("Yes");
			PrinterSetupPage.click("Yes");

			//Removing the added printer
			PrinterSetupPage.explicitWaitForElementToBeClickable("FilterName");
			PrinterSetupPage.click("FilterName");
			PrinterSetupPage.enterIntoTextBox("FilterName", map.get("SecondName"));

			//Clicking on a grid record
			PrinterSetupPage.explicitWaitForVisibility("GridRecord");
			PrinterSetupPage.click("GridRecord");

			//Clicking on a remove button
			PrinterSetupPage.explicitWaitForVisibility("RemoveButton");
			PrinterSetupPage.click("RemoveButton");
			PrinterSetupPage.explicitWaitForVisibility("Yes");
			PrinterSetupPage.click("Yes");

			//Verifying records are removed
			PrinterSetupPage.explicitWaitForElementToBeClickable("FilterName");
			PrinterSetupPage.click("FilterName");
			PrinterSetupPage.enterIntoTextBox("FilterName", map.get("FirstName"));
			PrinterSetupPage.explicitWaitForVisibility("NoRowsMessage");
			PrinterSetupPage.verifyTextValue("NoRowsMessage",map.get("NoRowsMessage"));

			//Verifying records are removed
			PrinterSetupPage.explicitWaitForElementToBeClickable("FilterName");
			PrinterSetupPage.click("FilterName");
			PrinterSetupPage.enterIntoTextBox("FilterName", map.get("SecondName"));
			PrinterSetupPage.explicitWaitForVisibility("NoRowsMessage");
			PrinterSetupPage.verifyTextValue("NoRowsMessage",map.get("NoRowsMessage"));

			//clicking on save button yes
			PrinterSetupPage.explicitWaitForElementToBeClickable("Save");
			PrinterSetupPage.click("Save");
			PrinterSetupPage.explicitWaitForElementToBeClickable("Yes");
			PrinterSetupPage.click("Yes");
			//Wait for db commit
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
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
