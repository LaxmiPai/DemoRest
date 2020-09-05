/**
 * @author lapai
 *
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

public class SupplierLookupAddressExpansion {

	public static Logger logger = Logger.getLogger(SupplierLookupAddressExpansion.class.getName());
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

		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.click("Navigation");
		HomePage.click("Lookups");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		HomePage.click("SupplierLookups");
	}

	@DataProvider(name = "SupplierAddressFields")
	public Object[][] getTestDataForSupplierAddressFields() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("SupplierDetailTestData"),"SupplierAddressFields");
		
		return testObjArray;

	}

	@Test(dataProvider = "SupplierAddressFields", priority = 1)
	public void supplierAddressFieldsVerify(Map<String, String> map) throws Exception {
		
	
		SupplierLookupPage.verifyTextValue("SupplierLookupScreen", "Supplier Lookup");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		SupplierLookupPage.click("SupplierIdLink");
	
		SupplierLookupPage.verifyTextValue("DetailSCreen", "Supplier Detail");
		SupplierLookupPage.isElementPresent("BackButton");

		// Verify the Business Address fields
		
		SupplierLookupPage.click("BusinessAddressButton");
		String businessAddress=SupplierLookupPage.getText("BusinessAddressLabel");
		System.out.println("The Address is :\n" +businessAddress);
		SupplierLookupPage.verifyTextValue("BusinessAddressLine1Label", map.get("AddressLine1"));
		SupplierLookupPage.verifyTextValue("BusinessAddressLine2Label", map.get("AddressLine2"));
		SupplierLookupPage.verifyTextValue("BusinessAddressLine3Label", map.get("AddressLine3"));
		SupplierLookupPage.verifyTextValue("BusinessAddressCityLabel", map.get("City"));
		SupplierLookupPage.verifyTextValue("BusinessAddressCountyLabel", map.get("County"));
		SupplierLookupPage.verifyTextValue("BusinessAddressStateLabel", map.get("State"));
		SupplierLookupPage.verifyTextValue("BusinessAddressCountryLabel", map.get("Country"));
		SupplierLookupPage.verifyTextValue("BusinessAddressPostalCodeLabel", map.get("PostalCode"));
		SupplierLookupPage.verifyTextValue("BusinessAddressContactLabel", map.get("Contact"));
		SupplierLookupPage.verifyTextValue("BusinessAddressEmailLabel", map.get("Email"));
		SupplierLookupPage.verifyTextValue("BusinessAddressTelephoneLabel", map.get("Telephone"));
		SupplierLookupPage.verifyTextValue("BusinessAddressFaxLabel", map.get("Fax"));
		SupplierLookupPage.click("BusinessAddressButton");
		
		// Verify the Return Address Fields
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		SupplierLookupPage.explicitWaitForElementToBeClickable("ReturnAddressButton");
		SupplierLookupPage.click("ReturnAddressButton");
		String returnAddress=SupplierLookupPage.getText("ReturnAddressLabel");
		System.out.println("The Address is :\n" +returnAddress);
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
		SupplierLookupPage.verifyTextValue("ReturnAddressLine1Label", map.get("AddressLine1"));
		SupplierLookupPage.verifyTextValue("ReturnAddressLine2Label", map.get("AddressLine2"));
		SupplierLookupPage.verifyTextValue("ReturnAddressLine3Label", map.get("AddressLine3"));
		SupplierLookupPage.verifyTextValue("ReturnAddressCityLabel", map.get("City"));
		SupplierLookupPage.verifyTextValue("ReturnAddressCountyLabel", map.get("County"));
		SupplierLookupPage.verifyTextValue("ReturnAddressStateLabel", map.get("State"));
		SupplierLookupPage.verifyTextValue("ReturnAddressCountryLabel", map.get("Country"));
		SupplierLookupPage.verifyTextValue("ReturnAddressPostalCodeLabel", map.get("PostalCode"));
		SupplierLookupPage.verifyTextValue("ReturnAddressContactLabel", map.get("Contact"));
		SupplierLookupPage.verifyTextValue("ReturnAddressEmailLabel", map.get("Email"));
		SupplierLookupPage.verifyTextValue("ReturnAddressTelephoneLabel", map.get("Telephone"));
		SupplierLookupPage.verifyTextValue("ReturnAddressFaxLabel", map.get("Fax"));
		SupplierLookupPage.explicitWaitForElementToBeClickable("ReturnAddressButton");
		SupplierLookupPage.click("ReturnAddressButton");
		//Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
		// Verify the Order Address Fields
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		SupplierLookupPage.explicitWaitForElementToBeClickable("OrderAddressButton");
		SupplierLookupPage.click("OrderAddressButton");
		String orderAddress=SupplierLookupPage.getText("OrderAddressLabel");
		System.out.println("The Address is: \n" +orderAddress);
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
		SupplierLookupPage.verifyTextValue("OrderAddressLine1Label", map.get("AddressLine1"));
		SupplierLookupPage.verifyTextValue("OrderAddressLine2Label", map.get("AddressLine2"));
		SupplierLookupPage.verifyTextValue("OrderAddressLine3Label", map.get("AddressLine3"));
		SupplierLookupPage.verifyTextValue("OrderAddressCityLabel", map.get("City"));
		SupplierLookupPage.verifyTextValue("OrderAddressCountyLabel", map.get("County"));
		SupplierLookupPage.verifyTextValue("OrderAddressStateLabel", map.get("State"));
		SupplierLookupPage.verifyTextValue("OrderAddressCountryLabel", map.get("Country"));
		SupplierLookupPage.verifyTextValue("OrderAddressPostalCodeLabel", map.get("PostalCode"));
		SupplierLookupPage.verifyTextValue("OrderAddressContactLabel", map.get("Contact"));
		SupplierLookupPage.verifyTextValue("OrderAddressEmailLabel", map.get("Email"));
		SupplierLookupPage.verifyTextValue("OrderAddressTelephoneLabel", map.get("Telephone"));
		SupplierLookupPage.verifyTextValue("OrderAddressFaxLabel", map.get("Fax"));
		SupplierLookupPage.explicitWaitForElementToBeClickable("OrderAddressButton");
		
		SupplierLookupPage.click("OrderAddressButton");
		
		// Verify the Invoice Address Fields
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		SupplierLookupPage.explicitWaitForElementToBeClickable("InvoiceAddressButton");
		SupplierLookupPage.click("InvoiceAddressButton");
		String invoiceAddress=SupplierLookupPage.getText("InvoiceAddressLabel");
		System.out.println("The Address is:\n" +invoiceAddress);
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
		SupplierLookupPage.verifyTextValue("InvoiceAddressLine1Label", map.get("AddressLine1"));
		SupplierLookupPage.verifyTextValue("InvoiceAddressLine2Label", map.get("AddressLine2"));
		SupplierLookupPage.verifyTextValue("InvoiceAddressLine3Label", map.get("AddressLine3"));
		SupplierLookupPage.verifyTextValue("InvoiceAddressCityLabel", map.get("City"));
		SupplierLookupPage.verifyTextValue("InvoiceAddressCountyLabel", map.get("County"));
		SupplierLookupPage.verifyTextValue("InvoiceAddressStateLabel", map.get("State"));
		SupplierLookupPage.verifyTextValue("InvoiceAddressCountryLabel", map.get("Country"));
		SupplierLookupPage.verifyTextValue("InvoiceAddressPostalCodeLabel", map.get("PostalCode"));
		SupplierLookupPage.verifyTextValue("InvoiceAddressContactLabel", map.get("Contact"));
		SupplierLookupPage.verifyTextValue("InvoiceAddressEmailLabel", map.get("Email"));
		SupplierLookupPage.verifyTextValue("InvoiceAddressTelephoneLabel", map.get("Telephone"));
		SupplierLookupPage.verifyTextValue("InvoiceAddressFaxLabel", map.get("Fax"));
		SupplierLookupPage.click("InvoiceAddressButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
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
