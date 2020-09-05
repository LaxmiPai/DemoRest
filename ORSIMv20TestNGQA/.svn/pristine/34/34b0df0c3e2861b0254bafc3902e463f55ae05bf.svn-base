package com.oracle.sim.testcases.MPSWorkType;

/**
 * @author vidhsank
 *
 * 
 */

import java.util.Map;
import java.util.logging.Logger;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.oracle.core.dataProvider.ExcelReader;
import com.oracle.core.utils.PropertyReader;
import com.oracle.sim.pageFactory.PageFactory;
import com.oracle.sim.pages.Base.SimBasePage;
import com.oracle.sim.utils.SIMWebdriverBase;

public class MpsWorkTypeDetailApplyBlockEdit {
	public static Logger logger=Logger.getLogger(MpsWorkTypeDetailApplyBlockEdit.class.getName());
	protected static PropertyReader propReader=new PropertyReader();
	PageFactory pagefactory=new PageFactory();

	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage RoleMaintenancePage;
	SimBasePage MpsWorkTypePage;

	@BeforeClass()
	public void setup(ITestContext context) throws Exception {
		logger.info("TestCase Name: " +logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
		LoginPage=pagefactory.initialize("LoginPage");
		HomePage=pagefactory.initialize("HomePage");
		RoleMaintenancePage=pagefactory.initialize("RoleMaintenancePage");
		MpsWorkTypePage=pagefactory.initialize("MpsWorkTypePage");
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username",LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password",LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");	
		HomePage.verifyTextValue("Title", "Store Inventory Operations Cloud Service");
		HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
		HomePage.storeIdCheck();
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.click("Navigation");
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
		HomePage.click("Security");
		HomePage.click("RoleMaintenance");
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

		//Clicking on a Access MPS Work Type Info permission
		RoleMaintenancePage.explicitWaitForElementToBeClickable("FilterPermission");
		RoleMaintenancePage.explicitWaitForElementToBeClickable("AssignedData");		
		RoleMaintenancePage.click("FilterPermission");
		RoleMaintenancePage.enterIntoTextBox("FilterPermission",map.get("MPSWorkType"));
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
			RoleMaintenancePage.enterIntoTextBox("FilterPermission",map.get("MPSWorkType"));
			System.out.println(RoleMaintenancePage.getTitle("AssignedData"));
		}

		//Navigating to MPS Work type Screen
		RoleMaintenancePage.click("BackLink");
		HomePage.click("Admin");
		HomePage.click("TechnicalMaintenance");
		HomePage.click("MPSWorkType");

		//Verifying the permission denied dialog box message
		MpsWorkTypePage.explicitWaitForElementToBeClickable("MsgDialog");
		MpsWorkTypePage.verifyTextValue("MsgDialog",map.get("PermissionMsg"));
		MpsWorkTypePage.click("OkButton");
		MpsWorkTypePage.implicitWait();

		//Navigating to Role Maintenance Screen
		MpsWorkTypePage.click("BackLink");
		MpsWorkTypePage.implicitWait();
		MpsWorkTypePage.click("BackLink");
		MpsWorkTypePage.implicitWait();
		HomePage.click("Security");
		HomePage.explicitWaitForElementToBeClickable("RoleMaintenance");
		HomePage.click("RoleMaintenance");
		RoleMaintenancePage.implicitWait();

		//Clicking on a user Role Name
		RoleMaintenancePage.explicitWaitForElementToBeClickable("Title");
		RoleMaintenancePage.explicitWaitForVisibility("RoleNameColumnRecords");
		RoleMaintenancePage.explicitWaitForElementToBeClickable("FirstTableRecord");
		RoleMaintenancePage.explicitWaitForElementToBeClickable("FilterRoleName");
		RoleMaintenancePage.click("FilterRoleName");
		RoleMaintenancePage.implicitWait();
		RoleMaintenancePage.enterIntoTextBox("FilterRoleName",userRole);
		RoleMaintenancePage.explicitWaitForElementToBeClickable("GridRecord");
		RoleMaintenancePage.click("GridRecord");

		//Clicking on a Access MPS Work Type Info permission
		RoleMaintenancePage.explicitWaitForElementToBeClickable("DetailTitle");
		RoleMaintenancePage.explicitWaitForElementToBeClickable("FirstTableRecord");
		RoleMaintenancePage.explicitWaitForElementToBeClickable("FilterPermission");
		RoleMaintenancePage.click("FilterPermission");
		RoleMaintenancePage.enterIntoTextBox("FilterPermission",map.get("MPSWorkType"));
		RoleMaintenancePage.click("GridRecord");

		//Grant the permission for the user 
		RoleMaintenancePage.explicitWaitForElementToBeClickable("AssignedData");
		if(RoleMaintenancePage.getTitle("AssignedData").equals(map.get("AssignedDataNo"))) {
			RoleMaintenancePage.click("AssignSelected");
			RoleMaintenancePage.click("SaveButton");
			RoleMaintenancePage.click("YesButton");
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
			RoleMaintenancePage.click("FilterPermission");
			RoleMaintenancePage.enterIntoTextBox("FilterPermission",map.get("MPSWorkType"));
			System.out.println(RoleMaintenancePage.getTitle("AssignedData"));
		}
		RoleMaintenancePage.explicitWaitForElementToBeClickable("BackLink");
		RoleMaintenancePage.click("BackLink");		
	}

	@DataProvider(name = "MPSWorkTypeTestData")
	public Object[][] getMPSWorkTypeTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("MPSWorkTypeTestData"),
				"DetailEdit");
		return testObjArray;
	}

	@Test(dataProvider="MPSWorkTypeTestData", priority=2,dependsOnMethods= { "verifyRole" })
	public void editMpsWorkType(Map<String,String> map) throws Exception {
		//Navigating to MPS Work type Screen
		logger.info("Method Name: editMpsWorkType");
		HomePage.explicitWaitForElementToBeClickable("Admin");
		HomePage.click("Admin");
		HomePage.click("TechnicalMaintenance");
		HomePage.click("MPSWorkType");

		//Verifying the MPS work type page title
		MpsWorkTypePage.explicitWaitForElementToBeClickable("Title");
		MpsWorkTypePage.verifyTextValue("Title",map.get("MPSWorkTypeTitle"));

		//Without selecting any record click on Edit button
		MpsWorkTypePage.verifyElementIsDisabled("EditButton");

		//Select a record and validate
		MpsWorkTypePage.explicitWaitForElementToBeClickable("FilterWorkType");
		MpsWorkTypePage.click("FilterWorkType");
		MpsWorkTypePage.enterIntoTextBox("FilterWorkType",map.get("WorkTypeData"));		
		MpsWorkTypePage.click("GridRecord");
		RoleMaintenancePage.explicitWaitForElementToBeClickable("FirstTableRecord");
		//To click on Edit button 
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		MpsWorkTypePage.verifyElementIsEnabled("EditButton");
		MpsWorkTypePage.explicitWaitForElementToBeClickable("EditButton");
		MpsWorkTypePage.click("EditButton");

		//Verifying RetryLimit field is editable
		MpsWorkTypePage.explicitWaitForElementToBeClickable("RetryLimitTextbox");
		MpsWorkTypePage.click("RetryLimitTextbox");
		MpsWorkTypePage.verifyElementIsEditable("RetryLimitTextbox");

		//Verifying RetryDelaySecs field is editable
		MpsWorkTypePage.explicitWaitForElementToBeClickable("RetryDelaySecsTextbox");
		MpsWorkTypePage.click("RetryDelaySecsTextbox");
		MpsWorkTypePage.verifyElementIsEditable("RetryDelaySecsTextbox");

		//Verifying RetryDelayMaxSecs field is editable		
		MpsWorkTypePage.explicitWaitForElementToBeClickable("RetryDelayMaxSecsTextbox");
		MpsWorkTypePage.click("RetryDelayMaxSecsTextbox");
		MpsWorkTypePage.verifyElementIsEditable("RetryDelayMaxSecsTextbox");

		//Verifying RetryDelayFactor field is editable		
		MpsWorkTypePage.explicitWaitForElementToBeClickable("RetryDelayFactorTextbox");
		MpsWorkTypePage.click("RetryDelayFactorTextbox");
		MpsWorkTypePage.verifyElementIsEditable("RetryDelayFactorTextbox");

		//Verifying RetryDelayRandom field is editable		
		MpsWorkTypePage.explicitWaitForElementToBeClickable("RetryDelayRandomTextbox");
		MpsWorkTypePage.click("RetryDelayRandomTextbox");
		MpsWorkTypePage.verifyElementIsEditable("RetryDelayRandomTextbox");		
	}	

	@AfterClass()
	public void tearDown() {
		try {
			logger.info("After Test: Logging out");
			HomePage.click("UserMenu");
			HomePage.explicitWaitForVisibility("Logout");
			HomePage.click("Logout");
			HomePage.explicitWaitForVisibility("Yes");
			HomePage.click("Yes");
		}
		finally {
			SIMWebdriverBase.close();
		}
	}
}
