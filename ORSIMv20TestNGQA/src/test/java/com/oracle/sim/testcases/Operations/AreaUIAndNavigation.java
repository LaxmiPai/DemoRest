/**
 * @author lrathnak *
 **/

package com.oracle.sim.testcases.Operations;

import java.io.IOException;
import java.text.ParseException;
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

public class AreaUIAndNavigation {
	
	public static Logger logger = Logger.getLogger(AreaUIAndNavigation.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pageFactory = new PageFactory();
	
	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage RoleMaintenancePage;
	SimBasePage AreaPage;
	SoftAssert softAssertion = new SoftAssert();
	
	@BeforeClass
	public void setUp(ITestContext context) throws Exception {
		logger.info("TestCase Name: " + logger.getName());
		// logger.info("Before Class");
		SIMWebdriverBase.loadInitialURL(context);
		
		// Login Steps
		LoginPage = pageFactory.initialize("LoginPage");
		AreaPage=pageFactory.initialize("AreaPage");
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
	
	
	@DataProvider(name="AreaUIAndNavigation")
	public Object[][] getTestDataForItemBasketPermission() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("AreaTestData"),
				"AreaUI&Navigation");
		return testObjArray;

	}
	
	@Test(dataProvider="AreaUIAndNavigation",priority=1)
	public void verifyRole(Map<String,String> map) throws Exception {
		logger.info("TestCase Name : "+ logger.getName());
		//Navigating to role maintenance page
		logger.info("Method Name: verifyRole");
		HomePage.click("Navigation");
		HomePage.click("Security");
		HomePage.click("RoleMaintenance");

		//Verifying RoleMaintenance Page Title
		RoleMaintenancePage.explicitWaitForVisibility("Title");
		RoleMaintenancePage.verifyTextValue("Title",map.get("Title"));

		//grant all permissions to the user
		String userRole=propReader.getApplicationproperty("UserRole");
		RoleMaintenancePage.grantAllPermissions(userRole);
	}
	
	
	
	@Test(dataProvider="AreaUIAndNavigation",priority=2)
	public void CreateAreaUIVerify(Map<String,String> map) throws NumberFormatException, InterruptedException, IOException, ParseException {
		
		logger.info("TestCase Name : "+ logger.getName());
		//Navigation to the Area option
		logger.info("Method Name: CreateAreaUIVerify");
		HomePage.click("Operations");
		HomePage.click("Area");
		
		//Title Verification of the list screen
		AreaPage.explicitWaitForElementToBeClickable("AreaListScreenTitle");
		AreaPage.verifyTextValue("AreaListScreenTitle",map.get("ListScreenTitle"));
		
		//verification of the header buttons and fields
		AreaPage.verifyElementIsPresent("ListSearchButton");
		AreaPage.verifyElementIsDisplayed("ListStatusField");
		AreaPage.verifyElementIsDisplayed("ListResultField");
		AreaPage.verifyElementIsDisplayed("ListRefreshButton");
		AreaPage.verifyElementIsDisplayed("ListDeleteButton");
		AreaPage.verifyElementIsPresent("GridViewMenu");
		AreaPage.verifyElementIsPresent("ListCreateButton");
		
		//Clicking on the create button
		AreaPage.click("ListCreateButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
		
		//Verifying the Area detail title
		AreaPage.explicitWaitForVisibility("AreaDetailScreeTitle");
		AreaPage.verifyTextValue("AreaDetailScreeTitle", map.get("DetailScreenTitle"));
		
		//Verification of all the buttons  present
		AreaPage.verifyElementIsPresent("DetailBackButton");
		AreaPage.verifyElementIsEnabled("DetailBackButton");
		AreaPage.verifyElementIsPresent("DetailSaveButton");
		AreaPage.verifyElementIsDisabled("DetailSaveButton");
		AreaPage.verifyElementIsPresent("DetailConfirmButton");
		AreaPage.verifyElementIsEnabled("DetailConfirmButton");
		AreaPage.verifyElementIsPresent("DetailDeleteButton");
		AreaPage.verifyElementIsEnabled("DetailDeleteButton");
		
		//Verify all the header fields present
		AreaPage.verifyElementIsDisplayed("DetailAreaIDLable");
		AreaPage.verifyElementIsDisplayed("DetailDescriptionLable");
		AreaPage.verifyElementIsDisplayed("DetailItemBasketLable");
		AreaPage.verifyElementIsDisplayed("DetailStatusLable");
		AreaPage.verifyElementIsDisplayed("DetailCreateDateLable");
		AreaPage.verifyElementIsDisplayed("DetailNotesLabel");
		
		//to verify the field's date is set to current date
		AreaPage.verifyDefaultDateWithCurrentDate("DetailCreateDateInput");
		
		//To verify Location title is present
		AreaPage.explicitWaitForVisibility("DetailsLocationTitle");
		AreaPage.verifyElementIsDisplayed("DetailsLocationTitle");
		
		
		//To verify the buttons location 
		AreaPage.verifyElementIsPresent("DetailAssignSelectedButton");
		AreaPage.verifyElementIsEnabled("DetailAssignSelectedButton");
		AreaPage.verifyElementIsPresent("DetailRevokeSelectedButton");
		
		//To verify the columns of the location
		AreaPage.verifyElementIsPresent("DetailAssignedColumn");
		AreaPage.verifyElementIsPresent("DetailStoreColumn");
		
		
		
		//Click on the Back button to navigate to the Area list screen
		AreaPage.click("DetailBackButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		
		//Confirming the confirmation popup
		AreaPage.verifyElementIsDisplayed("DetailConfirmationDialog");
		AreaPage.click("ConfirmYesButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
		
		
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
