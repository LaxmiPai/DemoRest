/**
  @author lapai
 */
/**TO DO
 * Supplier id name field values as well as other fields values in detail screen to be fetched using xpath tags.
 * Currently not possible to fetch values for fields using existing xpath.
 * 
 */
package com.oracle.sim.testcases.SupplierLookup;

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

public class SupplierLookupHeaderFields {

	public static Logger logger = Logger.getLogger(SupplierLookupHeaderFields.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pageFactory = new PageFactory();

	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage SupplierLookupPage;

	@BeforeClass()
	public void setup(ITestContext context) throws Exception {
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

	@DataProvider(name = "SupplierHeaderFields")
	public Object[][] getTestDataForSupplierHeaderFields() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("SupplierDetailTestData"),"SupplierHeaderFields");
		return testObjArray;

	}

	@Test(dataProvider = "SupplierHeaderFields", priority = 1)
	public void supplierHeaderFieldsVerify(Map<String, String> map) throws Exception {

		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.click("Navigation");
		HomePage.click("Lookups");
		HomePage.click("SupplierLookups");
		SupplierLookupPage.verifyTextValue("SupplierLookupScreen", "Supplier Lookup");
		
		//Verify Supplier Header Fields
		SupplierLookupPage.click("SupplierIdLink");
		SupplierLookupPage.verifyTextValue("DetailSCreen", "Supplier Detail");
		SupplierLookupPage.isElementPresent("BackButton");
		SupplierLookupPage.verifyTextValue("SupplierIdLabel", map.get("SupplierID"));
		SupplierLookupPage.verifyTextValue("SupplierNameLabel", map.get("SupplierName"));
		SupplierLookupPage.verifyTextValue("SupplierStatusLabel", map.get("Status"));
		SupplierLookupPage.verifyTextValue("ReturnsAllowedLabel", map.get("ReturnsAllowed"));
		SupplierLookupPage.verifyTextValue("ReturnAuthorizationRequiredLabel", map.get("ReturnAuthorizationRequired"));
		SupplierLookupPage.verifyTextValue("DeliveryDiscrepancyTypeLabel", map.get("DeliveryDiscrepancyType"));

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