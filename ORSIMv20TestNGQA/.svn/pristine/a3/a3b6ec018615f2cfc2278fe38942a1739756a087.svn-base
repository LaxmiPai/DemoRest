/**
 * @author niudupa
 *
 */

package com.oracle.sim.testcases.PrinterSetup;

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

public class Printer {
	public static Logger logger = Logger.getLogger(Printer.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pageFactory = new PageFactory();

	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage PrinterSetup;

	@BeforeClass()
	public void setup(ITestContext context) throws Exception {
		logger.info("TestCase Name: " +logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
		LoginPage = pageFactory.initialize("LoginPage");
		HomePage = pageFactory.initialize("HomePage");
		PrinterSetup = pageFactory.initialize("PrinterSetupPage");
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username", LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password", LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.verifyTextValue("Title", "Store Inventory Operations Cloud Service");
		HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
		HomePage.storeIdCheck();
	}

	@DataProvider(name = "AddPrinterTestData")
	public Object[][] getTestDataForCustomerPhone() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("PrinterTestData"),"AddPrinter");
		return testObjArray;
	}

	
	
	
	@Test(dataProvider = "AddPrinterTestData", priority = 1)
	public void printerAdd(Map<String, String> map) throws Exception {
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.click("Navigation");
		HomePage.click("AdminMenu");
		HomePage.click("TechnicalMaintenance");

		// Go to Printer Setup Page
		HomePage.click("PrinterSetup");
//		PrinterSetup = pageFactory.initialize("PrinterSetup");
		PrinterSetup.explicitWaitForElementToBeClickable("Title");
		PrinterSetup.click("Add");

		// Input Values in Detail section
		PrinterSetup.explicitWaitForElementToBeClickable("Name");
		PrinterSetup.enterIntoTextBox("Name", map.get("Name"));
		PrinterSetup.enterIntoTextBox("Description", map.get("Description"));
		PrinterSetup.selectDropDownValue("Type_dropdown", map.get("Type"));
		PrinterSetup.enterIntoTextBox("Address", map.get("Address"));
		if(map.get("ManifestDefault").equalsIgnoreCase("yes")) 
		PrinterSetup.click("ManifestDefault");
		if(map.get("PreShipmentDefault").equalsIgnoreCase("yes")) 
		PrinterSetup.click("PreShipmentDefault");
		PrinterSetup.click("Apply");
		PrinterSetup.click("Save");

		// Click Yes in Confirmation pop up
		PrinterSetup.explicitWaitForElementToBeClickable("Yes");
		PrinterSetup.click("Yes");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));

		// Refreshing the page

		PrinterSetup.click("Refresh");

	}

	@DataProvider(name = "SearchPrinterTestData")
	public Object[][] getPrinterData() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("PrinterTestData"),"AddPrinter");
		return testObjArray;
	}

	@Test(dataProvider = "SearchPrinterTestData", priority = 2, dependsOnMethods = { "printerAdd" })
	public void printerSearch(Map<String, String> map) throws Exception {

		PrinterSetup.explicitWaitForElementToBeClickable("Title");
		PrinterSetup.explicitWaitForElementToBeClickable("Add");
		PrinterSetup.explicitWaitForElementToBeClickable("Refresh");
		PrinterSetup.explicitWaitForElementToBeClickable("Delete");

		// Verifying the inserted record
		PrinterSetup.explicitWaitForElementToBeClickable("FilterName");
		PrinterSetup.click("FilterName");
		PrinterSetup.enterIntoTextBox("FilterName", map.get("Name"));
		System.out.println(PrinterSetup.getText("GridRecord"));
		PrinterSetup.verifyTextValue("GridRecord", map.get("Name"));
	}

	@DataProvider(name = "EditPrinterData")
	public Object[][] getEditPrinterData() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("PrinterTestData"),"EditPrinter");
		return testObjArray;
	}

	@Test(dataProvider = "EditPrinterData", priority = 3, dependsOnMethods = { "printerAdd" })
	public void printerEdit(Map<String, String> map) throws Exception {
		PrinterSetup.explicitWaitForElementToBeClickable("GridRecord");
		PrinterSetup.click("GridRecord");
		PrinterSetup.explicitWaitForElementToBeClickable("Edit");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		PrinterSetup.click("Edit");
		PrinterSetup.click("Edit");
		PrinterSetup.explicitWaitForElementToBeClickable("DetailName");
		PrinterSetup.enterIntoTextBox("DetailName", map.get("NewName"));
		PrinterSetup.enterIntoTextBox("DetailDescription", map.get("Description"));
		PrinterSetup.selectDropDownValue("Type_dropdown", map.get("Type"));
		PrinterSetup.enterIntoTextBox("DetailAddress", map.get("Address"));
		PrinterSetup.click("ManifestDefault");
		PrinterSetup.click("PreShipmentDefault");
		PrinterSetup.click("Apply");
		PrinterSetup.explicitWaitForElementToBeClickable("Save");
		PrinterSetup.click("Save");

		// Click Yes in Confirmation pop up
		PrinterSetup.explicitWaitForElementToBeClickable("Yes");
		PrinterSetup.click("Yes");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		PrinterSetup.explicitWaitForElementToBeClickable("Refresh");
		PrinterSetup.click("Refresh");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		PrinterSetup.explicitWaitForElementToBeClickable("FilterName");
		PrinterSetup.click("FilterName");
		PrinterSetup.enterIntoTextBox("FilterName", map.get("NewName"));
		logger.info("Printer name entered in Filter column for searching is " + PrinterSetup.getText("GridRecord"));
		PrinterSetup.verifyTextValue("GridRecord", map.get("NewName"));

	}

	@Test(dataProvider = "EditPrinterData", priority = 4, dependsOnMethods = { "printerAdd" })
	public void printerDelete(Map<String, String> map) throws Exception {

		PrinterSetup.explicitWaitForElementToBeClickable("Refresh");
		PrinterSetup.click("Refresh");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		PrinterSetup.explicitWaitForElementToBeClickable("FilterName");
		PrinterSetup.click("FilterName");
		PrinterSetup.enterIntoTextBox("FilterName", map.get("NewName"));
		logger.info("Printer name entered in Filter column for searching is " + PrinterSetup.getText("GridRecord"));
		PrinterSetup.verifyTextValue("GridRecord", map.get("NewName"));
		PrinterSetup.explicitWaitForElementToBeClickable("GridRecord");
		PrinterSetup.click("GridRecord");
		PrinterSetup.explicitWaitForElementToBeClickable("Delete");
		PrinterSetup.click("Delete");
		PrinterSetup.explicitWaitForElementToBeClickable("Yes");
		PrinterSetup.click("Yes");
		PrinterSetup.explicitWaitForElementToBeClickable("Save");
//		Thread.sleep(2000);
		PrinterSetup.click("Save");
		PrinterSetup.click("Save");

		// Click Yes in Confirmation pop up
		PrinterSetup.explicitWaitForElementToBeClickable("Yes");
		PrinterSetup.click("Yes");
		PrinterSetup.explicitWaitForElementToBeClickable("FilterName");
		PrinterSetup.click("FilterName");
		PrinterSetup.enterIntoTextBox("FilterName", map.get("NewName"));
		PrinterSetup.verifyTextValue("NorecordsMsg", map.get("DeleteValidationMsg"));

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
