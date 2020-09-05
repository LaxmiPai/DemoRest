/**
 * * @author vsurti 
 *
 */
package com.oracle.sim.testcases.TranslationSetUp;

import java.util.Map;
import java.util.logging.Logger;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.oracle.core.dataProvider.ExcelReader;
import com.oracle.core.utils.PropertyReader;
import com.oracle.sim.pageFactory.PageFactory;
import com.oracle.sim.pages.Base.SimBasePage;
import com.oracle.sim.utils.SIMWebdriverBase;

public class TranslationSetUpAccessSearch {
	public static Logger logger = Logger.getLogger(TranslationSetUpAccessSearch.class.getName());
	protected static PropertyReader propReader= new PropertyReader();
	PageFactory pageFactory = new PageFactory();
	
	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage LookupPage;
	SimBasePage TranslationSetUpPage;
	SimBasePage RoleMaintenancePage;
	
	@BeforeClass()
	public void setup(ITestContext context) throws Exception {
		logger.info("TestCase Name: " +logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
		LoginPage= pageFactory.initialize("LoginPage");
		HomePage = pageFactory.initialize("HomePage");
		LookupPage = pageFactory.initialize("LookupPage");
		TranslationSetUpPage = pageFactory.initialize("TranslationSetUpPage");
		RoleMaintenancePage=pageFactory.initialize("RoleMaintenancePage");
		
		//SIM application login
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username", LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password", LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");
		
		HomePage.verifyTextValue("Title", HomePage.getProperty("Title"));
		HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
		HomePage.storeIdCheck();
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
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.storeIdCheck();
		HomePage.click("Navigation");
		HomePage.click("Security");
		HomePage.click("RoleMaintenance");

		//Verifying RoleMaintenance Page Title
		RoleMaintenancePage.explicitWaitForElementToBeClickable("Title");
		RoleMaintenancePage.verifyTextValue("Title",map.get("Title"));

		//Granting the user permission
		String userRole=propReader.getApplicationproperty("UserRole");
		RoleMaintenancePage.verifyUserRole(userRole, map.get("AccessTranslationSetup"), map.get("AssignedDataNo"));	
	}
	
	@DataProvider(name = "TranslationsSearchTestData")
	public Object[][] getTestDataForCustomerPhone() throws Exception{
		
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("TranslationSetUpTestData"),"TransSearch");
		return testObjArray;
	}
	
	@Test(dataProvider="TranslationsSearchTestData", priority=2)
	public void validateTranSearch(Map<String, String> map) throws Exception {
		
		//Go to TranslationSetUp screen
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.click("Admin");
		TranslationSetUpPage.click("Translations");
		TranslationSetUpPage.click("TranslationSetUp");
		
		//Check by default dropdown value- for locale - SELECT
		TranslationSetUpPage.explicitWaitForVisibility("LocaleDropdown");
		TranslationSetUpPage.verifyElementIsPresent("SelectLocaleDropdown");
		
		//Select locale & search
		TranslationSetUpPage.selectDropDownValue("LocaleDropdown",map.get("Locale1"));
		TranslationSetUpPage.explicitWaitForElementToBeClickable("LocaleDropdown");
		TranslationSetUpPage.selectDropDownValue("BundleDropdown",map.get("Bundle"));
		TranslationSetUpPage.click("ApplyButton");
		TranslationSetUpPage.explicitWaitForVisibility("TransSetUpTitle");
		TranslationSetUpPage.verifyElementIsPresent("TransSetUpTitle");
		TranslationSetUpPage.verifyPartialTextValue("TransSetUpTitle", map.get("Title"));
		TranslationSetUpPage.verifyPartialTextValue("LocaleHeader",map.get("Locale1"));
		
		//Refreshing the webpage
		TranslationSetUpPage.RefreshWebPage();
		
		//Search through different locale
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("High")));
		TranslationSetUpPage.explicitWaitForVisibility("LocaleDropdown");
		TranslationSetUpPage.selectDropDownValue("LocaleDropdown",map.get("Locale"));
		TranslationSetUpPage.explicitWaitForElementToBeClickable("LocaleDropdown");
		TranslationSetUpPage.selectDropDownValue("BundleDropdown",map.get("Bundle"));
		TranslationSetUpPage.click("ApplyButton");
		TranslationSetUpPage.explicitWaitForVisibility("TransSetUpTitle");
		TranslationSetUpPage.verifyPartialTextValue("TransSetUpTitle", map.get("Title"));
		TranslationSetUpPage.verifyPartialTextValue("LocaleHeader",map.get("Locale"));
		
		//Click on refresh button
		TranslationSetUpPage.explicitWaitForElementToBeClickable("RefreshButton");
		TranslationSetUpPage.click("RefreshButton");
		TranslationSetUpPage.verifyPartialTextValue("LocaleHeader",map.get("Locale"));
	}

	
	@AfterClass()
	public void tearDown()
	{
		logger.info("After Test");
		try {
			//SIM application logout
			HomePage.click("UserMenu");
			HomePage.explicitWaitForVisibility("Logout");
			HomePage.click("Logout");
			HomePage.explicitWaitForVisibility("Yes");
			HomePage.click("Yes");



		} finally
		{
			SIMWebdriverBase.close();
		}
	}


}
