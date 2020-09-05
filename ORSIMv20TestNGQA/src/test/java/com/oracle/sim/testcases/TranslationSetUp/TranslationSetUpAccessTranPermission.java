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

public class TranslationSetUpAccessTranPermission {
	public static Logger logger = Logger.getLogger(TranslationSetUpAccessTranPermission.class.getName());
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
		
		logger.info("Method Name: verifyRole");
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.storeIdCheck();
		HomePage.click("Navigation");
		HomePage.click("Security");
		HomePage.click("RoleMaintenance");
		//Verifying RoleMaintenance Page Title
		RoleMaintenancePage.explicitWaitForVisibility("Title");
		RoleMaintenancePage.verifyTextValue("Title",map.get("Title"));
		String userRole=propReader.getApplicationproperty("UserRole");

		//Clicking on a user Role Name
		RoleMaintenancePage.explicitWaitForElementToBeClickable("FirstTableRecord");
		RoleMaintenancePage.explicitWaitForElementToBeClickable("FilterRoleName");
		RoleMaintenancePage.click("FilterRoleName");
		RoleMaintenancePage.implicitWait();
		RoleMaintenancePage.enterIntoTextBox("FilterRoleName",userRole);
		RoleMaintenancePage.click("GridRecord");

		//Verifying RoleDetail Page Title
		RoleMaintenancePage.explicitWaitForVisibility("DetailTitle");
		RoleMaintenancePage.verifyTextValue("DetailTitle",map.get("DetailTitle"));

		//Clicking on a Access MPS Work Type Info permission
		RoleMaintenancePage.explicitWaitForElementToBeClickable("FilterPermission");
		RoleMaintenancePage.explicitWaitForElementToBeClickable("AssignedData");		
		RoleMaintenancePage.click("FilterPermission");
		RoleMaintenancePage.enterIntoTextBox("FilterPermission",map.get("AccessTranslationSetup"));
		RoleMaintenancePage.click("GridRecord");

		//Remove the permission for the user
		RoleMaintenancePage.explicitWaitForElementToBeClickable("AssignedData");
		if(RoleMaintenancePage.getTitle("AssignedData").equals(map.get("AssignedDataYes"))) {
			RoleMaintenancePage.click("RevokeSelected");
			RoleMaintenancePage.click("SaveButton");
			RoleMaintenancePage.click("YesButton");
			//wait for DB commit to perform 
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
			//Refreshing the webpage
			RoleMaintenancePage.RefreshWebPage();
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
			RoleMaintenancePage.click("FilterPermission");
			RoleMaintenancePage.enterIntoTextBox("FilterPermission",map.get("AccessTranslationSetup"));
			System.out.println(RoleMaintenancePage.getTitle("AssignedData"));
		}
		
		
		//Navigating to translation set up  Screen
		//RoleMaintenancePage.click("BackLink");
		HomePage.click("Navigation");
		HomePage.click("Admin");
		TranslationSetUpPage.verifyElementIsPresent("Translations");
		RoleMaintenancePage.click("BackLink");
		HomePage.click("Security");
		HomePage.click("RoleMaintenance");

		//Verifying RoleMaintenance Page Title
		RoleMaintenancePage.explicitWaitForElementToBeClickable("Title");
		RoleMaintenancePage.verifyTextValue("Title",map.get("Title"));

		//Granting the user permission
		String userRole1=propReader.getApplicationproperty("UserRole");
		RoleMaintenancePage.verifyUserRole(userRole1, map.get("AccessTranslationSetup"), map.get("AssignedDataNo"));	
		//Refreshing the webpage
		//RoleMaintenancePage.RefreshWebPage();
	}
	
	@DataProvider(name = "TranslationsPermissionTestData")
	public Object[][] getTestDataForCustomerPhone() throws Exception{
		
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("TranslationSetUpTestData"),"TransPermission");
		return testObjArray;
	}
	
	@Test(dataProvider="TranslationsPermissionTestData", priority=2)
	public void validateTranPermission(Map<String, String> map) throws Exception {
		
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
