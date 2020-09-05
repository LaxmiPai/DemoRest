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

public class TranslationSetUpAccessTranLanguages {
	public static Logger logger = Logger.getLogger(TranslationSetUpAccessTranLanguages.class.getName());
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
	
	@DataProvider(name = "TranslationsLanguagesTestData")
	public Object[][] getTestDataForCustomerPhone() throws Exception{
		
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("TranslationSetUpTestData"),"TransLang");
		return testObjArray;
	}
	
	@Test(dataProvider="TranslationsLanguagesTestData", priority=2)
	public void validateTranLanguages(Map<String, String> map) throws Exception {
		
		//Go to TranslationSetUp screen
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.click("Admin");
		TranslationSetUpPage.click("Translations");
		TranslationSetUpPage.click("TranslationSetUp");
		
		//Check by default dropdown value- for locale - SELECT
		TranslationSetUpPage.explicitWaitForVisibility("LocaleDropdown");
		TranslationSetUpPage.verifyElementIsPresent("SelectLocaleDropdown");
		
		//Drop down count & print values - Languages
		TranslationSetUpPage.explicitWaitForElementToBeClickable("LocaleDropdown");
		TranslationSetUpPage.click("LocaleDropdown");
		TranslationSetUpPage.verifyDropDownValues("DropDownValues");
		
		//sorting dropdown values
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TranslationSetUpPage.verifyDropDownSortingOrder("DropDropDownList");
		
		//Checking for dropdown filter- partial search
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TranslationSetUpPage.click("DropDownFilter");
		TranslationSetUpPage.enterIntoTextBox("DropDownFilter", map.get("Lang"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TranslationSetUpPage.explicitWaitForVisibility("DropDownHighlightedValue");
		TranslationSetUpPage.verifyHighlightedTextValue("DropDownHighlightedValue",map.get("Lang"));
		
		//Checking for dropdown filter- locale search
		TranslationSetUpPage.clearElement("DropDownFilter");
		TranslationSetUpPage.click("DropDownFilter");
		TranslationSetUpPage.enterIntoTextBox("DropDownFilter", map.get("Locale"));
		TranslationSetUpPage.explicitWaitForVisibility("DropDownHighlightedValue");
		TranslationSetUpPage.verifyHighlightedTextValue("DropDownHighlightedValue",map.get("Locale"));
		TranslationSetUpPage.click("DropDownHighlightedValue");
		
		TranslationSetUpPage.explicitWaitForElementToBeClickable("LocaleDropdown");
		TranslationSetUpPage.selectDropDownValue("BundleDropdown",map.get("Bundle"));
		TranslationSetUpPage.click("ApplyButton");
		TranslationSetUpPage.explicitWaitForVisibility("TransSetUpTitle");
		TranslationSetUpPage.verifyElementIsPresent("TransSetUpTitle");
		TranslationSetUpPage.verifyPartialTextValue("TransSetUpTitle", map.get("Title"));
		logger.info("completed");
		
		
		
		
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
