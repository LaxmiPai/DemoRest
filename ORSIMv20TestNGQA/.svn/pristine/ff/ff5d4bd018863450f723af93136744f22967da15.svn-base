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

public class TranslationSetUpAccessUiNavigation {
	public static Logger logger = Logger.getLogger(TranslationSetUpAccessUiNavigation.class.getName());
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
	
	@DataProvider(name = "TranslationsPermissionTestData")
	public Object[][] getTestDataForCustomerPhone() throws Exception{
		
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("TranslationSetUpTestData"),"TransPermission");
		return testObjArray;
	}
	
	@Test(dataProvider="TranslationsPermissionTestData", priority=2)
	public void validateTranslationUiNavigation(Map<String, String> map) throws Exception {
		
		//Go to Translation set up screen
				HomePage.explicitWaitForElementToBeClickable("Admin");
				HomePage.click("Admin");
				TranslationSetUpPage.click("Translations");
				TranslationSetUpPage.click("TranslationSetUp");
				TranslationSetUpPage.explicitWaitForElementToBeClickable("LocaleDropdown");
				TranslationSetUpPage.selectDropDownValue("LocaleDropdown",map.get("Locale"));
				TranslationSetUpPage.explicitWaitForElementToBeClickable("LocaleDropdown");
				TranslationSetUpPage.selectDropDownValue("BundleDropdown",map.get("Bundle"));
				TranslationSetUpPage.click("ApplyButton");
				TranslationSetUpPage.explicitWaitForVisibility("TransSetUpTitle");
				TranslationSetUpPage.verifyElementIsPresent("TransSetUpTitle");
				TranslationSetUpPage.verifyPartialTextValue("TransSetUpTitle", map.get("Title"));
				HomePage.click("Navigation");
				
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				TranslationSetUpPage.explicitWaitForElementToBeClickable("TransFilter");
				TranslationSetUpPage.click("TransFilter");
				TranslationSetUpPage.enterIntoTextBox("TransFilter", map.get("Carrier"));

				//verifying name highlighted value
				TranslationSetUpPage.explicitWaitForVisibility("TransRecord");
				TranslationSetUpPage.verifyTableRowContentsOfColumn("TransRecord",map.get("Carrier"));
				TranslationSetUpPage.click("RefreshButton");
				
				
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				 TranslationSetUpPage.explicitWaitForElementToBeClickable("TransFilter");
				TranslationSetUpPage.click("TransFilter");
				TranslationSetUpPage.enterIntoTextBox("TransFilter", map.get("Error"));

				//verifying name highlighted value
				TranslationSetUpPage.explicitWaitForVisibility("TransRecord");
				TranslationSetUpPage.verifyTableRowContentsOfColumn("TransRecord",map.get("Error"));
				TranslationSetUpPage.click("RefreshButton");
				
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				TranslationSetUpPage.explicitWaitForElementToBeClickable("TransFilter");
				TranslationSetUpPage.click("TransFilter");
				TranslationSetUpPage.enterIntoTextBox("TransFilter", map.get("Item"));

				//verifying name highlighted value
				TranslationSetUpPage.explicitWaitForVisibility("TransRecord");
				TranslationSetUpPage.verifyTableRowContentsOfColumn("TransRecord",map.get("Item"));
				TranslationSetUpPage.click("RefreshButton");
				
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				TranslationSetUpPage.explicitWaitForElementToBeClickable("TransFilter");
				TranslationSetUpPage.click("TransFilter");
				TranslationSetUpPage.enterIntoTextBox("TransFilter", map.get("Container"));

				//verifying name highlighted value
				TranslationSetUpPage.explicitWaitForVisibility("TransRecord");
				TranslationSetUpPage.verifyTableRowContentsOfColumn("TransRecord",map.get("Container"));
				TranslationSetUpPage.click("RefreshButton");
				
				//sorting of records-as per topic coloumn
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				TranslationSetUpPage.explicitWaitForVisibility("TopicCol");
				TranslationSetUpPage.columnSorting("TopicLogic","TopicCol","ascending");
				TranslationSetUpPage.explicitWaitForVisibility("TransSetUpTitle");
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
