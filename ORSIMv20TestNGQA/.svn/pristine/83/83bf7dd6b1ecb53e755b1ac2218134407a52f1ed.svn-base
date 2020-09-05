//Author - lapai
package com.oracle.sim.testcases.SupplierLookup;

import java.util.Map;
import java.util.logging.Logger;

import org.testng.Assert;
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

public class SupplierLookupId {

	public static Logger logger = Logger.getLogger(SupplierLookupId.class.getName());
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

	@DataProvider(name = "SupplierId")
	public Object[][] getTestDataForSupplierID() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("SupplierLookupIdName"),"SupplierId");
		return testObjArray;
	}

	@Test(dataProvider = "SupplierId", priority = 1)
	public void supplierIdVerify(Map<String, String> map) throws Exception {

		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.click("Navigation");
		HomePage.click("Lookups");
		HomePage.click("SupplierLookups");
		SupplierLookupPage.click("Search");
		SupplierLookupPage.verifyTextValue("CriteriaScreenName", "Supplier Lookup Search Criteria");
		SupplierLookupPage.click("SupplierId");
		SupplierLookupPage.enterIntoTextBox("SupplierId", map.get("SupplierId1"));

		SupplierLookupPage.click("SearchButton");
		SupplierLookupPage.verifyTextValue("DetailSCreen", "Supplier Detail");

		// SupplierLookupPage.verifyTextValue("Supplier_id","2500");
		String s = SupplierLookupPage.getText("SupplierIdDetailScreen");
		System.out.println(s);
		SupplierLookupPage.verifyTextValue("SupplierIdDetailScreen", s);

		SupplierLookupPage.click("BackButton");
		SupplierLookupPage.verifyTableRowContents();
		SupplierLookupPage.getText("SearchText");
		SupplierLookupPage.click("Search");
		SupplierLookupPage.click("SupplierId");

		SupplierLookupPage.enterIntoTextBox("SupplierId", map.get("SupplierId3"));
		SupplierLookupPage.click("SearchButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		SupplierLookupPage.verifyTableRowContents();
		SupplierLookupPage.click("Search");

		SupplierLookupPage.enterIntoTextBox("SupplierId", map.get("SupplierId4"));
		SupplierLookupPage.click("SearchButton");
		String s1=SupplierLookupPage.getText("ErrorMessage2");
		System.out.println(s1);
		String ErrorMessage1= map.get("SupplierErrorMsg1");
		Assert.assertEquals(s1,ErrorMessage1);
		
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		//SupplierLookupPage.verifyTextValue("ErrorMessage2",map.get("SupplierErrorMsg1"));
		SupplierLookupPage.click("CancelButton");
		SupplierLookupPage.click("Search");
		SupplierLookupPage.enterIntoTextBox("SupplierId", map.get("SupplierId2"));
		SupplierLookupPage.click("SearchButton");
		String s2=SupplierLookupPage.getText("ErrorMessage1");
		System.out.println(s2);
		String ErrorMessage2= map.get("SupplierErrorMsg2");
		Assert.assertEquals(s2,ErrorMessage2);
		//SupplierLookupPage.verifyTextValue("ErrorMessage1","'yyy' is not in the expected number format.");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		SupplierLookupPage.click("CancelButton");

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
