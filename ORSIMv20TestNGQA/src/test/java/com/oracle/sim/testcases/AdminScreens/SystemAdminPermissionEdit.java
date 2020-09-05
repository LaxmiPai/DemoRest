//Author : shhg

package com.oracle.sim.testcases.AdminScreens;

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

public class SystemAdminPermissionEdit {
	public static Logger logger=Logger.getLogger(SystemAdminPermissionEdit.class.getName());
	public static PropertyReader propReader = new PropertyReader();
	PageFactory pageFactory = new PageFactory();
	
	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage RoleMaintenancePage;
	SimBasePage SystemAdminPage;
		
	@BeforeClass()
	public void setup(ITestContext context) throws Exception{
		logger.info("TestCase Name: "+logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
		LoginPage=pageFactory.initialize("LoginPage");
		HomePage=pageFactory.initialize("HomePage");
		RoleMaintenancePage=pageFactory.initialize("RoleMaintenancePage");
		SystemAdminPage=pageFactory.initialize("SystemAdministrationPage");
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username", LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password", LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");
		HomePage.verifyTextValue("Title", HomePage.getProperty("Title"));
		HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
		HomePage.storeIdCheck();
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.click("Navigation");
	}
	@DataProvider (name="SystemAdminAccessTestData")
	public Object[][] getSystemAdminAccessTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("SystemAdminTestData"), "Permission");
		return testObjArray;
	}
	
	@Test(dataProvider="SystemAdminAccessTestData", priority=1)
	public void verifyRole(Map<String,String> map) throws Exception{
	//Navigating to role maintenance page
	logger.info("Method Name: verifyRole");
	HomePage.click("Security");
	HomePage.click("RoleMaintenance");
		
	//Verifying RoleMaintenance Page Title
 	RoleMaintenancePage.explicitWaitForElementToBeClickable("Title");
	RoleMaintenancePage.verifyTextValue("Title",map.get("Title"));
		
	//Verifying a user permission
	String roleName=propReader.getApplicationproperty("UserRole");
	logger.info(roleName);
	RoleMaintenancePage.verifyUserRole(roleName, map.get("AccessSystemAdmin"), map.get("AssignedDataNo"));
	
	}
	
	@DataProvider (name="SystemAdminEditTestData")
	public Object[][] getSystemAdminEditTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("SystemAdminTestData"), "Edit");
		return testObjArray;
	}	
	
	@Test(dataProvider = "SystemAdminEditTestData", priority=2)
	public void verifySystemAdminEdit(Map<String, String> map) throws Exception {
		
		logger.info("Method Name: verifySystemAdminEdit");
		SystemAdminPage.explicitWaitForElementToBeClickable("AdminMenu");
		SystemAdminPage.click("AdminMenu");
		SystemAdminPage.explicitWaitForElementToBeClickable("ConfigurationMenu");
		SystemAdminPage.click("ConfigurationMenu");
		SystemAdminPage.explicitWaitForElementToBeClickable("SystemAdministrationMenu");
		SystemAdminPage.click("SystemAdministrationMenu");
		
		//Screen elements verification
		SystemAdminPage.verifyTextValue("ScreenHeading", map.get("ScreenHeading"));
		SystemAdminPage.click("OptionColumnFilter");
		SystemAdminPage.enterIntoTextBox("OptionColumnFilter", map.get("EditPermission"));
		SystemAdminPage.click("FirstRecord");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		SystemAdminPage.click("EditButton");
		
		//Verifying error when no data is entered in 'Value' field
		SystemAdminPage.explicitWaitForElementToBeClickable("DaysValue");
		SystemAdminPage.click("DaysValue");
		SystemAdminPage.clearElement("DaysValue");
		SystemAdminPage.explicitWaitForElementToBeClickable("ApplyButton");
		SystemAdminPage.click("ApplyButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
//		SystemAdminPage.explicitWaitForVisibility("Error");
		SystemAdminPage.verifyTextValue("Error", map.get("MandatoryError"));
		
		//Verifying error when alphabet is entered in 'Value' field
		SystemAdminPage.explicitWaitForElementToBeClickable("DaysValue");
		SystemAdminPage.click("DaysValue");
		SystemAdminPage.clearElement("DaysValue");
		SystemAdminPage.enterIntoTextBox("DaysValue", map.get("InvalidValue"));
		SystemAdminPage.explicitWaitForElementToBeClickable("ApplyButton");
		SystemAdminPage.click("ApplyButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
//		SystemAdminPage.explicitWaitForVisibility("Error");
		SystemAdminPage.verifyTextValue("Error", map.get("InvalidError"));
		
		//Verifying error when negative value is entered in 'Value' field
		SystemAdminPage.explicitWaitForElementToBeClickable("DaysValue");
		SystemAdminPage.click("DaysValue");
    	SystemAdminPage.clearElement("DaysValue");
		SystemAdminPage.enterIntoTextBox("DaysValue", map.get("NegativeValue"));
		SystemAdminPage.explicitWaitForElementToBeClickable("ApplyButton");
		SystemAdminPage.click("ApplyButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
//		SystemAdminPage.explicitWaitForVisibility("Error");
		SystemAdminPage.verifyTextValue("Error", map.get("NegativeError"));
		
		//Verifying error when high value is entered in 'Value' field
		SystemAdminPage.explicitWaitForElementToBeClickable("DaysValue");
		SystemAdminPage.click("DaysValue");
		SystemAdminPage.clearElement("DaysValue");
		SystemAdminPage.enterIntoTextBox("DaysValue", map.get("HighValue"));
		SystemAdminPage.explicitWaitForElementToBeClickable("ApplyButton");
		SystemAdminPage.click("ApplyButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
//		SystemAdminPage.explicitWaitForVisibility("Error");
		SystemAdminPage.verifyTextValue("Error", map.get("HighError"));
		
		//Entering valid value and saving the change
		SystemAdminPage.click("DaysValue");
		SystemAdminPage.clearElement("DaysValue");
		SystemAdminPage.enterIntoTextBox("DaysValue", map.get("EditDays"));
		SystemAdminPage.explicitWaitForElementToBeClickable("ApplyButton");
		SystemAdminPage.click("ApplyButton");
		SystemAdminPage.explicitWaitForElementToBeClickable("SaveButton");
		SystemAdminPage.click("SaveButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		SystemAdminPage.verifyTextValue("SaveMessage", map.get("SaveConfirm"));
		SystemAdminPage.click("SaveConfirm");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		
		//Reverting the change for further automation runs
		SystemAdminPage.click("OptionColumnFilter");
		SystemAdminPage.enterIntoTextBox("OptionColumnFilter", map.get("EditPermission"));
		SystemAdminPage.implicitWait();
		SystemAdminPage.click("FirstRecord");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		SystemAdminPage.click("EditButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		String Topic = SystemAdminPage.getAttributeValue("TopicValue", "value");
		SystemAdminPage.verifyValuesAreEqual(Topic, map.get("ExpectedTopic"));
		String Value = SystemAdminPage.getAttributeValue("OptionValue", "value");
		SystemAdminPage.verifyValuesAreEqual(Value, map.get("ExpectedOption"));
		SystemAdminPage.click("DaysValue");
		SystemAdminPage.clearElement("DaysValue");
		SystemAdminPage.enterIntoTextBox("DaysValue", map.get("OldEditDays"));
		SystemAdminPage.explicitWaitForElementToBeClickable("ApplyButton");
		SystemAdminPage.click("ApplyButton");
		SystemAdminPage.explicitWaitForElementToBeClickable("SaveButton");
		SystemAdminPage.click("SaveButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		SystemAdminPage.verifyTextValue("SaveMessage", map.get("SaveConfirm"));
		SystemAdminPage.click("SaveConfirm");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		SystemAdminPage.click("OptionColumnFilter");
		SystemAdminPage.enterIntoTextBox("OptionColumnFilter", map.get("EditPermission"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		SystemAdminPage.click("FirstRecord");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		String oldValue = SystemAdminPage.getAttributeValue("DaysValue", "value");
		SystemAdminPage.verifyValuesAreEqual(oldValue, map.get("OldEditDays"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		
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