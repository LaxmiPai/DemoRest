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

public class PrinterFilterSearch {
	public static Logger logger=Logger.getLogger(PrinterFilterSearch.class.getName());
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

	@DataProvider(name = "FilterSearchTestData")
	public Object[][] getFilterSearchTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("PrinterTestData"),
				"FilterSearch");
		return testObjArray;
	}

	@Test(dataProvider="FilterSearchTestData",priority=2 ,dependsOnMethods = {"verifyRole"})
	public void searchFilter(Map<String,String> map) throws Exception {
		//Navigating to printer setup screen
		logger.info("Method Name: searchFilter");
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
			PrinterSetupPage.enterIntoTextBox("Name", map.get("Name"));
			PrinterSetupPage.enterIntoTextBox("Description", map.get("Description"));
			PrinterSetupPage.selectDropDownValue("Type_dropdown", map.get("Type"));
			PrinterSetupPage.enterIntoTextBox("Address", map.get("Address"));

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

			//Filtering printer name
			PrinterSetupPage.explicitWaitForElementToBeClickable("FilterName");
			PrinterSetupPage.click("FilterName");
			PrinterSetupPage.enterIntoTextBox("FilterName", map.get("Name"));

			//verifying name highlighted value
			PrinterSetupPage.explicitWaitForVisibility("GridRecord");
			PrinterSetupPage.verifyHighlightedTextValue("GridRecord",map.get("Name"));
			PrinterSetupPage.click("RefreshButton");

			//Filtering printer description
			PrinterSetupPage.explicitWaitForElementToBeClickable("FilterDescription");
			PrinterSetupPage.click("FilterDescription");
			PrinterSetupPage.enterIntoTextBox("FilterDescription", map.get("Description"));

			//verifying description highlighted value
			PrinterSetupPage.explicitWaitForVisibility("GridRecord");
			PrinterSetupPage.verifyHighlightedTextValue("GridRecord",map.get("Description"));
			PrinterSetupPage.click("RefreshButton");

			//Filtering printer address
			PrinterSetupPage.explicitWaitForElementToBeClickable("FilterAddress");
			PrinterSetupPage.click("FilterAddress");
			PrinterSetupPage.enterIntoTextBox("FilterAddress", map.get("Address"));

			//verifying address highlighted value
			PrinterSetupPage.explicitWaitForVisibility("GridRecord");
			PrinterSetupPage.verifyHighlightedTextValue("GridRecord",map.get("Address"));
			PrinterSetupPage.click("RefreshButton");

			//Filtering printer manifest default
			PrinterSetupPage.explicitWaitForElementToBeClickable("FilterManifestDefault");
			PrinterSetupPage.click("FilterManifestDefault");
			PrinterSetupPage.enterIntoTextBox("FilterManifestDefault", map.get("ManifestDefault"));

			//verifying manifest default highlighted value
			PrinterSetupPage.explicitWaitForVisibility("GridRecord");
			PrinterSetupPage.verifyHighlightedTextValue("GridRecord",map.get("ManifestDefault"));
			PrinterSetupPage.click("RefreshButton");

			//Filtering printer preshipment
			PrinterSetupPage.explicitWaitForElementToBeClickable("FilterPreShipment");
			PrinterSetupPage.click("FilterPreShipment");
			PrinterSetupPage.enterIntoTextBox("FilterPreShipment", map.get("PreShipmentDefault"));

			//verifying preshipment highlighted value
			PrinterSetupPage.explicitWaitForVisibility("GridRecord");
			PrinterSetupPage.verifyHighlightedTextValue("GridRecord",map.get("PreShipmentDefault"));

			//Filtering printer name
			PrinterSetupPage.explicitWaitForElementToBeClickable("FilterName");
			PrinterSetupPage.click("FilterName");
			PrinterSetupPage.enterIntoTextBox("FilterName", map.get("Name"));

			//Filtering printer description
			PrinterSetupPage.explicitWaitForElementToBeClickable("FilterDescription");
			PrinterSetupPage.click("FilterDescription");
			PrinterSetupPage.enterIntoTextBox("FilterDescription", map.get("Description"));

			//Filtering printer address
			PrinterSetupPage.explicitWaitForElementToBeClickable("FilterAddress");
			PrinterSetupPage.click("FilterAddress");
			PrinterSetupPage.enterIntoTextBox("FilterAddress", map.get("Address"));

			//Filtering printer manifest default
			PrinterSetupPage.explicitWaitForElementToBeClickable("FilterManifestDefault");
			PrinterSetupPage.click("FilterManifestDefault");
			PrinterSetupPage.enterIntoTextBox("FilterManifestDefault", map.get("ManifestDefault"));

			//verifying all grid records
			PrinterSetupPage.explicitWaitForVisibility("GridRecord");
			PrinterSetupPage.verifyTextValue("NameGridRecord",map.get("Name"));
			PrinterSetupPage.verifyTextValue("DescriptionGridRecord",map.get("Description"));
			PrinterSetupPage.verifyTextValue("AddressGridRecord",map.get("Address"));
			PrinterSetupPage.verifyTextValue("ManifestDefaultGridRecord",map.get("ManifestDefault"));
			PrinterSetupPage.verifyTextValue("PreShipmentGridRecord",map.get("PreShipmentDefault"));	

			//Clicking on a grid record
			PrinterSetupPage.explicitWaitForVisibility("GridRecord");
			PrinterSetupPage.click("GridRecord");

			//Clicking on a remove button
			PrinterSetupPage.explicitWaitForVisibility("RemoveButton");
			PrinterSetupPage.click("RemoveButton");
			PrinterSetupPage.explicitWaitForVisibility("Yes");
			PrinterSetupPage.click("Yes");

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
