package com.oracle.sim.testcases.UinLookup;

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

public class UinLookupStoreSearchField {
	public static Logger logger=Logger.getLogger(UinLookupStoreSearchField.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pagefactory=new PageFactory();

	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage UinLookupPage;

	@BeforeClass()
	public void setup(ITestContext context) throws Exception {
		logger.info("TestCase Name: " +logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
		LoginPage=pagefactory.initialize("LoginPage");
		HomePage=pagefactory.initialize("HomePage");
		UinLookupPage=pagefactory.initialize("UinLookupPage");
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

	@DataProvider(name = "StoreSearchFieldTestData")
	public Object[][] getStoreSearchFieldTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("UinLookupTestData"),
				"StoreSearchField");
		return testObjArray;
	}

	@Test(dataProvider="StoreSearchFieldTestData")
	public void verifyStoreSearchField(Map<String,String> map) throws Exception {
		//Navigating to UIN lookup page
		logger.info("Method Name: verifyStoreSearchField");
		HomePage.click("Lookup");
		HomePage.click("UinLookup");

		//Verifying the Page Title
		UinLookupPage.explicitWaitForElementToBeClickable("Title");
		UinLookupPage.verifyTextValue("Title",map.get("Title"));

		//Clicking on a store drop down
		UinLookupPage.explicitWaitForVisibility("StoreDropDown");
		UinLookupPage.explicitWaitForElementToBeClickable("StoreDropDown");
		UinLookupPage.click("StoreDropDown");

		//Verifying the store drop down stores 
		UinLookupPage.verifyDropDownValues("StoreDropDownValues");
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
