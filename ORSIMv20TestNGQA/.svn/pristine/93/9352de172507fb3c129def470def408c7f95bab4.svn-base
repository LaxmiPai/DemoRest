/* @author shhg **/

package com.oracle.sim.testcases.Security;

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


public class UserAssignmentUpdate {
	public static Logger logger = Logger.getLogger(UserAssignmentUpdate.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pageFactory = new PageFactory();

	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage updateRole;
	
	

	@BeforeClass()
	public void setup(ITestContext context) throws Exception {
		logger.info("TestCase Name : "+ logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
		LoginPage = pageFactory.initialize("LoginPage");
		HomePage = pageFactory.initialize("HomePage");
		updateRole = pageFactory.initialize("UserAssignmentPage");
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username", LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password", LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		
		//Store title to be retrived from excel
		HomePage.verifyTextValue("Title", "Store Inventory Operations Cloud Service");
		HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
		HomePage.storeIdCheck();
	}
	
	@DataProvider(name = "UserRoleTestData")
	public Object[][] getUserAssignmentTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("UserRoleTestData"),
				"StoreData");
		return testObjArray;
	}		
	@Test (dataProvider="UserRoleTestData",priority = 1)
	public void updateRole(Map<String,String> map) throws Exception {
		logger.info("TestCase Name : "+ logger.getName());
		updateRole.explicitWaitForElementToBeClickable("MainMenu");
		updateRole.click("MainMenu");
		updateRole.explicitWaitForElementToBeClickable("SecurityMainMenu");
		updateRole.click("SecurityMainMenu");
		updateRole.explicitWaitForElementToBeClickable("UserassignmentMainMenu");
		updateRole.click("UserassignmentMainMenu");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		updateRole.explicitWaitForElementToBeClickable("FirstTableRecord");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		updateRole.click("FirstTableRecord");
		updateRole.explicitWaitForElementToBeClickable("RoleButton");
		updateRole.click("RoleButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		
		//Create new role
		updateRole.explicitWaitForElementToBeClickable("CreateNewRoleButton");
		updateRole.click("CreateNewRoleButton");
		updateRole.verifyTextValue("CreateRoleHeading", map.get("CreateRolePopupHeading"));
		updateRole.click("ScopeSelect");
		updateRole.click("StoreCheckBox");
		updateRole.click("RoleSelect");
		updateRole.explicitWaitForElementToBeClickable("UpdateExistingSwitch");
		String enabledValue=updateRole.getText("UpdateExistingSwitch");
		System.out.println(enabledValue);
		updateRole.click("ApplyButton");
		updateRole.click("SaveButton");
		updateRole.explicitWaitForElementToBeClickable("MessageConfirm");
		updateRole.click("MessageConfirm");
		
		//Delete role
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		updateRole.click("GridMenuRole");
		updateRole.click("RowSelector");
		updateRole.explicitWaitForElementToBeClickable("FirstRecordRole");
		updateRole.click("FirstRecordRole");
		updateRole.click("DeleteRole");
		updateRole.explicitWaitForElementToBeClickable("MessageConfirm");
		updateRole.click("MessageConfirm");
		updateRole.click("SaveButton");
		updateRole.explicitWaitForElementToBeClickable("MessageConfirm");
		updateRole.click("MessageConfirm");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
		updateRole.click("BackButton");
	} 
	
	
	@Test (dataProvider="UserRoleTestData",priority = 2)
	public void revokeRole(Map<String,String> map) throws Exception {
		logger.info("TestCase Name : "+ logger.getName());
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		updateRole.explicitWaitForElementToBeClickable("UserNameFilter");
		updateRole.click("UserNameFilter");
		updateRole.enterIntoTextBox("UserNameFilter",map.get("UserName"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		updateRole.click("FirstTableRecord");
		updateRole.explicitWaitForElementToBeClickable("StoresButton");
		updateRole.click("StoresButton");
		
		//Revoke permission
		updateRole.explicitWaitForElementToBeClickable("RevokePermission");
		updateRole.click("RevokePermission");
		updateRole.verifyTextValue("RevokeError", map.get("RevokeErrorMessage"));
		updateRole.explicitWaitForElementToBeClickable("ErrorOK");
		updateRole.click("ErrorOK");
		updateRole.explicitWaitForElementToBeClickable("GridMenu");
		updateRole.click("GridMenu");
		updateRole.click("RowSelector");
		updateRole.explicitWaitForElementToBeClickable("FilterBox");
		updateRole.click("FilterBox");
		updateRole.enterIntoTextBox("FilterBox", map.get("StoreID1"));
		updateRole.click("FirstRecord");
		updateRole.click("SecondRecord");
		updateRole.explicitWaitForElementToBeClickable("RevokePermission");
		updateRole.click("RevokePermission");
		updateRole.explicitWaitForElementToBeClickable("SaveButton");
		updateRole.click("SaveButton");
		updateRole.explicitWaitForElementToBeClickable("MessageConfirm");
		updateRole.click("MessageConfirm");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		updateRole.explicitWaitForElementToBeClickable("FilterBox");
		updateRole.click("FilterBox");
		updateRole.enterIntoTextBox("FilterBox", map.get("StoreID1"));
		updateRole.click("FirstRecord");
		updateRole.explicitWaitForElementToBeClickable("RevokePermission");
		updateRole.click("RevokePermission");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
		updateRole.click("BackButton");
		
		}
	
	@Test (dataProvider="UserRoleTestData",priority = 3,dependsOnMethods = { "revokeRole" })
	public void assignRole(Map<String,String> map) throws Exception {
		logger.info("TestCase Name : "+ logger.getName());
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		updateRole.explicitWaitForElementToBeClickable("UserNameFilter");
		updateRole.click("UserNameFilter");
		updateRole.enterIntoTextBox("UserNameFilter",map.get("UserName"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		updateRole.click("FirstTableRecord");
		updateRole.explicitWaitForElementToBeClickable("StoresButton");
		updateRole.click("StoresButton");
		updateRole.click("AssignPermission");
		updateRole.verifyTextValue("AssignError", map.get("AssignErrorMessage"));
		updateRole.explicitWaitForElementToBeClickable("ErrorOK");
		updateRole.click("ErrorOK");
		updateRole.explicitWaitForElementToBeClickable("GridMenu");
		updateRole.click("GridMenu");
		updateRole.click("RowSelector");
		updateRole.explicitWaitForElementToBeClickable("FilterBox");
		updateRole.click("FilterBox");
		updateRole.enterIntoTextBox("FilterBox", map.get("StoreID1"));
		updateRole.click("FirstRecord");
		updateRole.click("SecondRecord");
		updateRole.click("AssignPermission");
		updateRole.click("SaveButton");
		updateRole.explicitWaitForElementToBeClickable("MessageConfirm");
		updateRole.click("MessageConfirm");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		updateRole.explicitWaitForElementToBeClickable("FilterBox");
		updateRole.click("FilterBox");
		updateRole.enterIntoTextBox("FilterBox", map.get("StoreID1"));
		updateRole.click("FirstRecord");
		updateRole.click("AssignPermission");
		
				
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


