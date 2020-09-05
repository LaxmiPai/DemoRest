package com.oracle.sim.testcases.ISN;

import java.util.Map;
import java.util.logging.Logger;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.oracle.core.dataProvider.ExcelReader;
import com.oracle.core.utils.PropertyReader;
import com.oracle.sim.pageFactory.PageFactory;
import com.oracle.sim.pages.Base.SimBasePage;
import com.oracle.sim.utils.SIMWebdriverBase;

public class IsnTypeSetUpScript {
	public static Logger logger = Logger.getLogger(IsnTypeSetUpScript.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pageFactory = new PageFactory();
	
	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage RoleMaintenancePage;
	SimBasePage IsnPage;
	SoftAssert softAssertion = new SoftAssert();
	
	@BeforeClass
	public void setUp(ITestContext context) throws Exception {
		logger.info("TestCase Name: " + logger.getName());
		// logger.info("Before Class");
		SIMWebdriverBase.loadInitialURL(context);
		
		// Login Steps
		LoginPage = pageFactory.initialize("LoginPage");
		IsnPage=pageFactory.initialize("IsnPage");
		HomePage = pageFactory.initialize("HomePage");
		RoleMaintenancePage=pageFactory.initialize("RoleMaintenancePage");
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username", LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password", LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");
		HomePage.verifyTextValue("Title", "Store Inventory Operations Cloud Service");
		HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
		HomePage.storeIdCheck();

	}
	

	@DataProvider(name="SetUpIsn")
	public Object[][] getTestDataForItemBasketPermission() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("IsnTestData"),
				"SetUpIsn");
		return testObjArray;

	}
	
	
	@Test(dataProvider="SetUpIsn",priority=1)
	public void verifyRole(Map<String,String> map) throws Exception {
		logger.info("TestCase Name : "+ logger.getName());
		//Navigating to role maintenance page
		logger.info("Method Name: verifyRole");
		HomePage.click("Navigation");
		HomePage.click("Security");
		HomePage.click("RoleMaintenance");

		//Verifying RoleMaintenance Page Title
		RoleMaintenancePage.explicitWaitForElementToBeClickable("Title");
		RoleMaintenancePage.verifyTextValue("Title",map.get("Title"));

		//grant all permissions to the user
		String userRole=propReader.getApplicationproperty("UserRole");
		RoleMaintenancePage.grantAllPermissions(userRole);
	}
	
	@Test(dataProvider="SetUpIsn",priority=2)
	public void SetUPISN(Map<String,String> map) throws Exception {
		
		
		//Navigating to role maintenance page
		logger.info("Method Name: verifyRole");
		
		HomePage.click("Admin");
		HomePage.click("DataSetup");
		HomePage.click("IsnType");
		
		//Title Verification
		 IsnPage.explicitWaitForElementToBeClickable("ISNTypeTitle");
		 IsnPage.verifyTextValue("ISNTypeTitle",map.get("ISNTypeTitle"));
		 
		 //Check if the ISN type is present
		 IsnPage.click("ISNTypeLalFilterInput");
		 IsnPage.enterIntoTextBox("ISNTypeLalFilterInput",map.get("IsnLabel"));
		 if(IsnPage.isElementPresent("ISNTypeNorecrdMsg")) {
		//Click on add button to add the ISN types
		IsnPage.click("ISNTypeAddButton");
		
		//To enter details into the label field
		IsnPage.enterIntoTextBox("ISNTypeDetlLblInput",map.get("IsnLabel"));
		
		//To click on apply and Save button
		IsnPage.click("ISNTpesDtlApplyButon");
		IsnPage.click("ISNTypeSaveButton");
		IsnPage.click("ConfirmYesButton");
		
		//To filter by newly added ISn Type 
		IsnPage.enterIntoTextBox("ISNTypeLalFilterInput",map.get("IsnLabel"));
		
		//To click on the first row
		IsnPage.isElementPresent("ISNTypeFrstRow");
		IsnPage.click("ISNTypeFrstRow");
		logger.info("The required ISN Type is added");
		softAssertion.assertTrue(true);
		 }
	
	else {
		logger.info("The required ISN ype is already added");
		
	}
	}
	@AfterClass
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
	

