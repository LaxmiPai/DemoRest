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

public class TranslationSetUpFiterSearch {
	public static Logger logger = Logger.getLogger(TranslationSetUpFiterSearch.class.getName());
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
	
	@DataProvider(name = "TranslationsFiterSearchTestData")
	public Object[][] getTestDataForCustomerPhone() throws Exception{
		
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("TranslationSetUpTestData"),"TransFilter");
		return testObjArray;
	}
	
	@Test(dataProvider="TranslationsFiterSearchTestData", priority=2)
	public void validateFiterSearch(Map<String, String> map) throws Exception {
		
		//Go to TranslationSetUp screen
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.click("Admin");
		TranslationSetUpPage.click("Translations");
		TranslationSetUpPage.click("TranslationSetUp");
		
		//Check by default dropdown value- for locale - SELECT
		TranslationSetUpPage.explicitWaitForVisibility("LocaleDropdown");
		TranslationSetUpPage.verifyElementIsPresent("SelectLocaleDropdown");
		
		//Select locale & search
		TranslationSetUpPage.selectDropDownValue("LocaleDropdown",map.get("Locale"));
		TranslationSetUpPage.explicitWaitForElementToBeClickable("LocaleDropdown");
		TranslationSetUpPage.selectDropDownValue("BundleDropdown",map.get("Bundle"));
		TranslationSetUpPage.click("ApplyButton");
		TranslationSetUpPage.explicitWaitForVisibility("TransSetUpTitle");
		TranslationSetUpPage.verifyElementIsPresent("TransSetUpTitle");
		TranslationSetUpPage.verifyPartialTextValue("TransSetUpTitle", map.get("Title"));
		TranslationSetUpPage.verifyPartialTextValue("LocaleHeader",map.get("Locale"));
		HomePage.click("Navigation");
		
		///////Translation filter -sub string/////
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TranslationSetUpPage.explicitWaitForElementToBeClickable("TransFilter");
		TranslationSetUpPage.click("TransFilter");
		TranslationSetUpPage.enterIntoTextBox("TransFilter", map.get("SubTran"));

		//verifying name highlighted value
		TranslationSetUpPage.explicitWaitForVisibility("TransRecord");
		TranslationSetUpPage.verifyTableRowUsingColumnValue("TransRecord",map.get("SubTran"));
		TranslationSetUpPage.click("RefreshButton");
		
		//////Translation filter -complete string////
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TranslationSetUpPage.explicitWaitForElementToBeClickable("TransFilter");
		TranslationSetUpPage.click("TransFilter");
		TranslationSetUpPage.enterIntoTextBox("TransFilter", map.get("ComTran"));

		//verifying name highlighted value
		TranslationSetUpPage.explicitWaitForVisibility("TransRecord");
		TranslationSetUpPage.verifyTableRowUsingColumnValue("TransRecord",map.get("ComTran"));
		TranslationSetUpPage.click("RefreshButton");
		
		//////Topic filter -sub string/////
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TranslationSetUpPage.explicitWaitForElementToBeClickable("TopicFilter");
		TranslationSetUpPage.click("TopicFilter");
		TranslationSetUpPage.enterIntoTextBox("TopicFilter", map.get("SubTopic"));

		//verifying name highlighted value
		TranslationSetUpPage.explicitWaitForVisibility("TopicRecord");
		TranslationSetUpPage.verifyTableRowUsingColumnValue("TopicRecord",map.get("SubTopic"));
		TranslationSetUpPage.click("RefreshButton");

		//////Topic filter -complete string//////
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TranslationSetUpPage.explicitWaitForElementToBeClickable("TopicFilter");
		TranslationSetUpPage.click("TopicFilter");
		TranslationSetUpPage.enterIntoTextBox("TopicFilter", map.get("ComTopic"));

		//verifying name highlighted value
		TranslationSetUpPage.explicitWaitForVisibility("TopicRecord");
		TranslationSetUpPage.verifyTableRowUsingColumnValue("TopicRecord",map.get("ComTopic"));
		TranslationSetUpPage.click("RefreshButton");
		
		//////key filter -sub string////
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TranslationSetUpPage.explicitWaitForElementToBeClickable("KeyFilter");
		TranslationSetUpPage.click("KeyFilter");
		TranslationSetUpPage.enterIntoTextBox("KeyFilter", map.get("SubKey"));

		//verifying name highlighted value
		TranslationSetUpPage.explicitWaitForVisibility("KeyRecord");
		TranslationSetUpPage.verifyTableRowUsingColumnValue("KeyRecord",map.get("SubKey"));
		TranslationSetUpPage.click("RefreshButton");
		
		//////key filter -complete string////
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TranslationSetUpPage.explicitWaitForElementToBeClickable("KeyFilter");
		TranslationSetUpPage.click("KeyFilter");
		TranslationSetUpPage.enterIntoTextBox("KeyFilter", map.get("ComKey"));

		//verifying name highlighted value
		TranslationSetUpPage.explicitWaitForVisibility("KeyRecord");
		TranslationSetUpPage.verifyTableRowUsingColumnValue("KeyRecord",map.get("ComKey"));
		TranslationSetUpPage.click("RefreshButton");
		
		/////Description filter -sub string////
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TranslationSetUpPage.explicitWaitForElementToBeClickable("DescFilter");
		TranslationSetUpPage.click("DescFilter");
		TranslationSetUpPage.enterIntoTextBox("DescFilter", map.get("SubDesc"));

		//verifying name highlighted value
		TranslationSetUpPage.explicitWaitForVisibility("DescRecord");
		TranslationSetUpPage.verifyTableRowUsingColumnValue("DescRecord",map.get("SubDesc"));
		TranslationSetUpPage.click("RefreshButton");
		
		//////Description filter -complete string/////
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TranslationSetUpPage.explicitWaitForElementToBeClickable("DescFilter");
		TranslationSetUpPage.click("DescFilter");
		TranslationSetUpPage.enterIntoTextBox("DescFilter", map.get("ComDesc"));

		//verifying name highlighted value
		TranslationSetUpPage.explicitWaitForVisibility("DescRecord");
		TranslationSetUpPage.verifyTableRowUsingColumnValue("DescRecord",map.get("ComDesc"));
		TranslationSetUpPage.click("RefreshButton");
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
