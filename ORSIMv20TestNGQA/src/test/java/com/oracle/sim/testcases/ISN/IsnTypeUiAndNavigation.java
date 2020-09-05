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

public class IsnTypeUiAndNavigation {
	public static Logger logger = Logger.getLogger(IsnTypeUiAndNavigation.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pageFactory = new PageFactory();
	
	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage RoleMaintenancePage;
	SimBasePage IsnPage;
	
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
		HomePage.verifyTextValue("Title", HomePage.getProperty("Title"));
		HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
		HomePage.storeIdCheck();

	}
	@DataProvider(name="UiAndNavigationVerify")
	public Object[][] getTestDataForItemBasketPermission() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("IsnTestData"),
				"UiAndNavigation");
		return testObjArray;

	}
	/*@Test(dataProvider="UiAndNavigationVerify",priority=1)
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
	}*/
	@Test(dataProvider="UiAndNavigationVerify",priority=2)
	public void verifyUiandNavigation(Map<String,String> map) throws Exception {
		
		
		//Navigating to role maintenance page
		logger.info("Method Name: verifyUiandNavigation");
		HomePage.click("Navigation");
		HomePage.click("Admin");
		HomePage.click("DataSetup");
		HomePage.click("IsnType");
		
		//Title Verification
		 IsnPage.explicitWaitForElementToBeClickable("ISNTypeTitle");
		 IsnPage.verifyTextValue("ISNTypeTitle",map.get("ISNTypeTitle"));
		 
		 //Button Verification
		 IsnPage.verifyElementIsDisabled("ISNTypeSaveButton");
		 IsnPage.verifyElementIsEnabled("ISNTypeRefrshButton");
		 IsnPage.verifyElementIsEnabled("ISNTypeAddButton");
		 IsnPage.verifyElementIsEnabled("ISNTypeDeleteButton");
		 
		 //Grid fields verification
		 IsnPage.verifyElementIsPresent("IDGridFieldlLabel");
		 IsnPage.verifyElementIsPresent("LabelGridFieldlLabel");
		 IsnPage.verifyElementIsPresent("RestrictGridFieldlLabel");
		 
		 //Detail Block verification
		 IsnPage.verifyElementIsPresent("DetailBlockTitle");
		 IsnPage.verifyElementIsDisabled("ISNTypeDtlEditButton");
		 IsnPage.verifyElementIsDisabled("ISNTypeDtlApplyButton");
		 IsnPage.verifyElementIsDisabled("ISNTypeDtlCancelButton");
		 IsnPage.verifyTextValue("LabelField",map.get("Label"));
		 IsnPage.verifyTextValue("RestrictField",map.get("Restrict"));
		 
	   //Grid View button verify
		 IsnPage.verifyElementIsEnabled("GridViewButton");
		 IsnPage.explicitWaitForElementToBeClickable("GridViewButton");
		 IsnPage.click("GridViewButton");
		 IsnPage.verifyElementIsEnabled("ColumnFilterButton");
		 IsnPage.verifyElementIsEnabled("RowSelectorButton");
		 
		 
	}
	/*@AfterClass
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
	}*/

}
