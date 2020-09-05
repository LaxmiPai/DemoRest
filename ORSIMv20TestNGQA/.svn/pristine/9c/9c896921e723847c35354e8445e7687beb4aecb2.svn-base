/**
 * * @author vsurti 
 *
 */
package com.oracle.sim.testcases.TranslationSetUp;

import java.util.Map;
import java.util.Random;
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

public class TranslationSetUpEdit {
	public static Logger logger = Logger.getLogger(TranslationSetUpEdit.class.getName());
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
		
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("TranslationSetUpTestData"),"TransEdit");
		return testObjArray;
	}
	
	@Test(dataProvider="TranslationsFiterSearchTestData", priority=2)
	public void validateSetUpEdit(Map<String, String> map) throws Exception {
		
		//Random value generation
		Random rand = new Random();
		char[] chars=null;
		
		chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
        StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 5; i++) {
		    char c = chars[rand.nextInt(chars.length)];
		sb.append(c);
		}
		String ranval = sb.toString();
		System.out.println("Random Value -> " +ranval);
		
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
		
		//Before clicking to record - edit -disable
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TranslationSetUpPage.verifyElementIsDisabled("EditButton");
		TranslationSetUpPage.click("FirstRow");
		
		//After clicking to record - edit enable ,apply,cancle -disable
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TranslationSetUpPage.verifyElementIsEnabled("EditButton");
		TranslationSetUpPage.verifyElementIsDisabled("AplyButton");
		TranslationSetUpPage.verifyElementIsDisabled("CnclButton");
		
		//Topic,Key,Translation,Description fields are not editable
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TranslationSetUpPage.verifyReadOnly("TopicField");
		TranslationSetUpPage.verifyReadOnly("KeyField");
		TranslationSetUpPage.verifyReadOnly("TransField");
		TranslationSetUpPage.verifyReadOnly("DescField");
		
		//Click on edit button- apply ,cancel - enabled
		TranslationSetUpPage.click("EditButton");
		TranslationSetUpPage.verifyElementIsEnabled("AplyButton");
		TranslationSetUpPage.verifyElementIsEnabled("CnclButton");
		
		//Translation,Description fields are editable 
		TranslationSetUpPage.verifyElementIsEditable("TransField");
		TranslationSetUpPage.verifyElementIsEditable("DescField");
		
		//Enter translation & description
		TranslationSetUpPage.clearElement("TransField");
		TranslationSetUpPage.enterIntoTextBox("TransField",ranval);
		TranslationSetUpPage.clearElement("DescField");
		TranslationSetUpPage.enterIntoTextBox("DescField",ranval);
		TranslationSetUpPage.click("AplyButton");
		TranslationSetUpPage.click("SaveButton");
		
		//Verify Confirmation msg
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TranslationSetUpPage.explicitWaitForVisibility("ConfirmationMsg");
		TranslationSetUpPage.verifyPartialTextValue("ConfirmationMsg", map.get("Msg"));
				
		//Click NO- save button will b enabled
		//Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TranslationSetUpPage.click("NoButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TranslationSetUpPage.explicitWaitForVisibility("TransSetUpTitle");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TranslationSetUpPage.explicitWaitForVisibility("SaveButton");
		TranslationSetUpPage.verifyElementIsEnabled("SaveButton");
		TranslationSetUpPage.verifyElementIsEnabled("EditButton");
				
		//Click YES - save & edit buttons will b disabled
		TranslationSetUpPage.click("SaveButton");
		TranslationSetUpPage.click("YesButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TranslationSetUpPage.explicitWaitForVisibility("TransSetUpTitle");
	
		//Edit the transaltion record -more than 1800 char
		TranslationSetUpPage.explicitWaitForVisibility("TransSetUpTitle");
		TranslationSetUpPage.explicitWaitForElementToBeClickable("FirstRow");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TranslationSetUpPage.click("FirstRow");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TranslationSetUpPage.explicitWaitForElementToBeClickable("EditButton");
		TranslationSetUpPage.click("EditButton");
		TranslationSetUpPage.clearElement("DescField");
		TranslationSetUpPage.enterIntoTextBox("DescField", map.get("Maxium"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TranslationSetUpPage.click("AplyButton");
				
		//Verify error msg:
		TranslationSetUpPage.explicitWaitForVisibility("ErrorMsg");
		TranslationSetUpPage.verifyPartialTextValue("ErrorMsg", map.get("ErrorMsg"));
				
		//Edit the transaltion record -more than 200 char
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TranslationSetUpPage.click("EditButton");
		TranslationSetUpPage.clearElement("DescField");
		TranslationSetUpPage.enterIntoTextBox("DescField", map.get("Max"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TranslationSetUpPage.click("AplyButton");
				
		//Verify error msg:
		TranslationSetUpPage.explicitWaitForVisibility("ErrorMsg");
		TranslationSetUpPage.verifyPartialTextValue("ErrorMsg", map.get("ErrorMsg"));
		TranslationSetUpPage.click("CnclButton");
		
		//Remove translation & description 
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TranslationSetUpPage.explicitWaitForElementToBeClickable("EditButton");
		TranslationSetUpPage.click("EditButton");
		TranslationSetUpPage.clearElement("TransField");
		TranslationSetUpPage.clearElement("DescField");
		TranslationSetUpPage.click("AplyButton");
		TranslationSetUpPage.click("SaveButton");
		
		//Verify Confirmation msg
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TranslationSetUpPage.explicitWaitForVisibility("ConfirmationMsg");
		TranslationSetUpPage.verifyPartialTextValue("ConfirmationMsg", map.get("Msg"));
		//Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TranslationSetUpPage.click("YesButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
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
