//Author - lapai
package com.oracle.sim.testcases.SupplierLookup;

import java.util.Map;
import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.oracle.core.dataProvider.ExcelReader;
import com.oracle.core.utils.PropertyReader;

import com.oracle.sim.pageFactory.PageFactory;
import com.oracle.sim.pages.Base.SimBasePage;

import com.oracle.sim.utils.SIMWebdriverBase;

public class SupplierLookupName {

	public static Logger logger = Logger.getLogger(SupplierLookupName.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pageFactory = new PageFactory();

	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage SupplierLookupPage;

	@BeforeClass()
	public void setup(ITestContext context) throws Exception {
		logger.info("TestCase Name: " +logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
		// Initialize the pages that are to be used
		LoginPage = pageFactory.initialize("LoginPage");
		HomePage = pageFactory.initialize("HomePage");
		SupplierLookupPage = pageFactory.initialize("SupplierLookupPage");

		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username", LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password", LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");
		HomePage.verifyTextValue("Title", HomePage.getProperty("Title"));
		HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
		HomePage.storeIdCheck();

	}

	@DataProvider(name = "SupplierSearchCriteriaFields")
	public Object[][] getTestDataForSupplierSearchCriteriaScreen() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("SupplierDetailTestData"),"SupplierSearchCriteriaFields");
		return testObjArray;
	}

	@Test(dataProvider = "SupplierSearchCriteriaFields", priority = 1)
	public void supplierSearchCriteriaScreenVerify(Map<String, String> map) throws Exception {
  
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.click("Navigation");

		HomePage.click("Lookups");
		HomePage.click("SupplierLookups");
		SupplierLookupPage.explicitWaitForElementToBeClickable("Search");
		SupplierLookupPage.click("Search");
		
		// To Verify the title and fields in search criteria screen.
		SupplierLookupPage.verifyTextValue("CriteriaScreenName", map.get("Title"));
	    SupplierLookupPage.verifyTextValue("SupplierIdLabel", map.get("SupplierId"));
		SupplierLookupPage.verifyTextValue("SupplierNameLabel", map.get("SupplierName"));
		SupplierLookupPage.verifyTextValue("ItemLabel", map.get("Item"));
		SupplierLookupPage.verifyTextValue("IncludeInactiveLabel", map.get("IncludeInactive"));
		SupplierLookupPage.verifyTextValue("SearchLimitLabel", map.get("SearchLimit"));
		SupplierLookupPage.verifyElementIsPresent("SearchButton");
		SupplierLookupPage.verifyElementIsPresent("ResetButton");
		SupplierLookupPage.verifyElementIsPresent("CancelButton");

	}

	@DataProvider(name = "SupplierNameVerify")
	public Object[][] getTestDataForSupplierName() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("SupplierLookupIdName"),"SupplierName");
		
		return testObjArray;
	}

	@Test(dataProvider = "SupplierNameVerify", priority = 2)
	public void supplierNameVerify(Map<String, String> map) throws Exception {

		SupplierLookupPage.click("SupplierNameSearchCriteria");

		// Enter a valid supplier
		SupplierLookupPage.enterIntoTextBox("SupplierNameSearchCriteria", map.get("SupplierName1"));
		SupplierLookupPage.explicitWaitForElementToBeClickable("SearchButton");
		SupplierLookupPage.click("SearchButton");
		SupplierLookupPage.verifyTextValue("DetailSCreen", "Supplier Detail");
		SupplierLookupPage.click("BackButton");

		SupplierLookupPage.click("RefreshButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		SupplierLookupPage.verifyTableRowContentsForName();
		String supplierName1 = SupplierLookupPage.getText("SupplierNameDisplay");
		String suppName1= map.get("SupplierNameDisplay1");
		System.out.println(supplierName1);
		Assert.assertEquals(supplierName1,suppName1);
		System.out.println("The supplier name entered is equal to the name displayed on lookup");
		// Enter an invalid supplier
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));

		SupplierLookupPage.click("Search");
		SupplierLookupPage.click("SupplierNameSearchCriteria");
		SupplierLookupPage.enterIntoTextBox("SupplierNameSearchCriteria", map.get("SupplierName2"));
		SupplierLookupPage.click("SearchButton");
		/*
		 * Tried implicit wait- giving 30,50 seconds,contents of table are
		 * showing values for valid supplier.Table not getting refreshed.Tried
		 * Fluent wait too-dint work
		 */
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
		SupplierLookupPage.click("RefreshButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
		SupplierLookupPage.verifyTableRowContentsForName();
		String supplierName2 = SupplierLookupPage.getText("SupplierNameDisplay");
		System.out.println(supplierName2);
		String suppName2=map.get("SupplierNameDisplay2");
		Assert.assertEquals(supplierName2,suppName2);
		System.out.println("The supplier name entered is equal to the name displayed on lookup");
		

		// Do not enter any supplier name
		SupplierLookupPage.click("Search");
		SupplierLookupPage.click("SupplierNameSearchCriteria");
		SupplierLookupPage.enterIntoTextBox("SupplierNameSearchCriteria", "");

		SupplierLookupPage.click("SearchButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		SupplierLookupPage.click("RefreshButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		SupplierLookupPage.verifyTableRowContentsForName();

		// Enter a supplier substring
		SupplierLookupPage.click("Search");
		SupplierLookupPage.click("SupplierNameSearchCriteria");
		SupplierLookupPage.click("SupplierNameSearchCriteria");
		SupplierLookupPage.enterIntoTextBox("SupplierNameSearchCriteria", map.get("SupplierName3"));
		SupplierLookupPage.click("SearchButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		SupplierLookupPage.click("RefreshButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		SupplierLookupPage.verifyTableRowContentsForName();
		String supplierName3 = SupplierLookupPage.getText("SupplierNameDisplay");
		System.out.println(supplierName3);
		String suppName3=map.get("SupplierNameDisplay3");
		Assert.assertEquals(supplierName3,suppName3);
		System.out.println("The supplier name entered is equal to the name displayed on lookup");
		
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
		} finally {
			SIMWebdriverBase.close();
		}
	}

}
