/* @author lrathnak **/

package com.oracle.sim.testcases.Security;

import java.io.IOException;
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

public class UserAssignmentUIAndNavigation {
	public static Logger logger = Logger.getLogger(UserAssignmentUIAndNavigation.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pageFactory = new PageFactory();

	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage UserAssignmentPage;
	SimBasePage RoleMaintenancePage;
	SoftAssert softAssertion = new SoftAssert();
	
	@BeforeClass()
	public void setup(ITestContext context) throws Exception {
		logger.info("TestCase Name : "+ logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
		LoginPage = pageFactory.initialize("LoginPage");
		HomePage = pageFactory.initialize("HomePage");
		UserAssignmentPage = pageFactory.initialize("UserAssignmentPage");
		RoleMaintenancePage=pageFactory.initialize("RoleMaintenancePage");
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username", LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password", LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		
		//Store title to be retrived from excel
		HomePage.verifyTextValue("Title", "Store Inventory Operations Cloud Service");
		HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
//		HomePage.storeIdCheck();
	}
	
	@DataProvider(name = "RoleMaintenanceTestData")
	public Object[][] getRoleMaintenanceTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("SecurityTestData"),
				"RoleMaintanenceUser");
		return testObjArray;
	}
	
	@Test(dataProvider="RoleMaintenanceTestData", priority=1)
	public void verifyRole(Map<String,String> map) throws Exception {
		logger.info("TestCase Name : "+ logger.getName());
		//Navigating to role maintenance page
		logger.info("Method Name: verifyRole");
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.storeIdCheck();
		HomePage.click("Navigation");
		HomePage.click("Security");
		HomePage.click("RoleMaintenance");
		
		RoleMaintenancePage.implicitWait();


		//Verifying RoleMaintenance Page Title
		RoleMaintenancePage.explicitWaitForElementToBeClickable("Title");
		RoleMaintenancePage.verifyTextValue("Title",map.get("Title"));
		String userRole=propReader.getApplicationproperty("UserRole");

		
		//Clicking on a user Role Name
		RoleMaintenancePage.explicitWaitForVisibility("RoleNameColumnRecords");
		RoleMaintenancePage.explicitWaitForElementToBeClickable("FilterRoleName");
		RoleMaintenancePage.explicitWaitForElementToBeClickable("RoleNameColumnRecords");
		RoleMaintenancePage.click("FilterRoleName");
		RoleMaintenancePage.implicitWait();
		RoleMaintenancePage.enterIntoTextBox("FilterRoleName",userRole);
		RoleMaintenancePage.click("GridRecord");
		
		//Verifying RoleDetail Page Title
		RoleMaintenancePage.explicitWaitForVisibility("DetailTitle");
		RoleMaintenancePage.verifyTextValue("DetailTitle",map.get("DetailTitle"));
		
		//Clicking on a permission
		RoleMaintenancePage.explicitWaitForElementToBeClickable("FilterPermission");
		RoleMaintenancePage.explicitWaitForElementToBeClickable("AssignedData");		
		RoleMaintenancePage.click("FilterPermission");
		RoleMaintenancePage.enterIntoTextBox("FilterPermission",map.get("AccessUserMaintanancePermission"));
		RoleMaintenancePage.click("GridRecord");
		
		//Remove the permission for the user
		RoleMaintenancePage.explicitWaitForElementToBeClickable("AssignedData");
		if(RoleMaintenancePage.getTitle("AssignedData").equals(map.get("AssignedDataYes"))) {
			RoleMaintenancePage.click("RevokeSelected");
			RoleMaintenancePage.click("SaveButton");
			RoleMaintenancePage.click("YesButton");
			//wait for DB commit to perform 
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
			RoleMaintenancePage.click("FilterPermission");
			RoleMaintenancePage.enterIntoTextBox("FilterPermission",map.get("AccessUserMaintanancePermission"));
			System.out.println(RoleMaintenancePage.getTitle("AssignedData"));
				}
		
		RoleMaintenancePage.RefreshWebPage();
		HomePage.click("Navigation");
		RoleMaintenancePage.VerifyPageMenuIsNotPresent("User Assignment");
	
		//Clicking on a Access User Assignment Info permission
				RoleMaintenancePage.explicitWaitForElementToBeClickable("DetailTitle");
				RoleMaintenancePage.explicitWaitForElementToBeClickable("FirstTableRecord");
				RoleMaintenancePage.explicitWaitForElementToBeClickable("FilterPermission");
				RoleMaintenancePage.click("FilterPermission");
				RoleMaintenancePage.enterIntoTextBox("FilterPermission",map.get("AccessUserMaintanancePermission"));
				RoleMaintenancePage.click("GridRecord");

				//Grant the permission for the user 
				RoleMaintenancePage.explicitWaitForElementToBeClickable("AssignedData");
				if(RoleMaintenancePage.getTitle("AssignedData").equals(map.get("AssignedDataNo"))) {
					RoleMaintenancePage.click("AssignSelected");
					RoleMaintenancePage.click("SaveButton");
					RoleMaintenancePage.click("YesButton");
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
					RoleMaintenancePage.click("FilterPermission");
					RoleMaintenancePage.enterIntoTextBox("FilterPermission",map.get("AccessUserMaintanancePermission"));
					RoleMaintenancePage.RefreshWebPage();
					System.out.println(RoleMaintenancePage.getTitle("AssignedData"));
					RoleMaintenancePage.RefreshWebPage();
				}
					
			}
	
	
	@DataProvider(name = "UserRoleTestData")
	public Object[][] getUserAssignmentTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("UserRoleTestData"),
				"UserRoleUI&Navigation");
		return testObjArray;
	}		
	
	@Test (dataProvider="UserRoleTestData",priority = 2,dependsOnMethods = { "verifyRole" })
	public void UIAndNavigation(Map<String,String> map) throws NumberFormatException, InterruptedException, IOException {
		logger.info("TestCase Name : "+ logger.getName());
		
		logger.info("Method Name: UI&Navigation");
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.click("Navigation");
		HomePage.click("Security");
		HomePage.click("UserAssignment");
		
		RoleMaintenancePage.implicitWait();
		
		//To verify the title
		UserAssignmentPage.explicitWaitForElementToBeClickable("Title");
		UserAssignmentPage.verifyTextValue("Title",map.get("Title"));
		
		//To verify search buttion is present and enabled
		
		UserAssignmentPage.verifyElementIsPresent("SearchButton");
		UserAssignmentPage.verifyElementIsEnabled("SearchButton");
		
		//To verify refresh button is present and enabled
		UserAssignmentPage.verifyElementIsPresent("RefreshButton");
		UserAssignmentPage.verifyElementIsEnabled("RefreshButton");
		
		//To verify delete profile button is present and enabled
		UserAssignmentPage.verifyElementIsPresent("DeleteButton");
		UserAssignmentPage.verifyElementIsEnabled("DeleteButton");
		
		//To verify delete profile button is present and enabled
		UserAssignmentPage.verifyElementIsPresent("ImportButton");
		UserAssignmentPage.verifyElementIsEnabled("ImportButton");
		
		//To verify if the grid menu is present
		UserAssignmentPage.verifyElementIsPresent("GridViewMenu");
		UserAssignmentPage.verifyElementIsEnabled("GridViewMenu");
		
		//Click on the record to check whether it goes to user details screen
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
		String heading=	UserAssignmentPage.getText("FirstTableRecord");
		UserAssignmentPage.click("FirstTableRecord");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
		
		
		//To verify the title
		String detailHeading=UserAssignmentPage.getText("DetailUserScreenTitle");
		if(detailHeading.contains(heading)) {
			softAssertion.assertTrue(true);
		}else {
			softAssertion.fail("The user did not navigate to the detail screen");
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

