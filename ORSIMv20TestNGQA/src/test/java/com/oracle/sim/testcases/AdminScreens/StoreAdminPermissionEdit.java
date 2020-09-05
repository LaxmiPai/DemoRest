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

public class StoreAdminPermissionEdit {
	
	public static Logger logger=Logger.getLogger(StoreAdminPermissionEdit.class.getName());
	public static PropertyReader propReader = new PropertyReader();
	PageFactory pageFactory = new PageFactory();
	
	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage RoleMaintenancePage;
	SimBasePage StoreAdminPage;
		
	@BeforeClass()
	public void setup(ITestContext context) throws Exception{
		logger.info("TestCase Name: "+logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
		LoginPage=pageFactory.initialize("LoginPage");
		HomePage=pageFactory.initialize("HomePage");
		RoleMaintenancePage=pageFactory.initialize("RoleMaintenancePage");
		StoreAdminPage=pageFactory.initialize("StoreAdministrationPage");
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
	@DataProvider (name="StoreAdminAccessTestData")
	public Object[][] getStoreAdminAccessTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("StoreAdminTestData"), "Permission");
		return testObjArray;
	}
	
	@Test(dataProvider="StoreAdminAccessTestData", priority=1)
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
	RoleMaintenancePage.verifyUserRole(roleName, map.get("AccessStoreAdminStore"), map.get("AssignedDataNo"));
	
	}
	
	@DataProvider (name="StoreAdminEditTestData")
	public Object[][] getStoreAdminEditTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("StoreAdminTestData"), "StoreEdit");
		return testObjArray;
	}	
	
	@Test(dataProvider = "StoreAdminEditTestData", priority=2)
	public void verifySystemAdminEdit(Map<String, String> map) throws Exception {
		
		logger.info("Method Name: verifyStoreAdminEdit");
		StoreAdminPage.explicitWaitForElementToBeClickable("AdminMenu");
		StoreAdminPage.click("AdminMenu");
		StoreAdminPage.explicitWaitForElementToBeClickable("ConfigurationMenu");
		StoreAdminPage.click("ConfigurationMenu");
		StoreAdminPage.explicitWaitForElementToBeClickable("StoreAdministrationMenu");
		StoreAdminPage.click("StoreAdministrationMenu");
		
		//Screen elements verification
		StoreAdminPage.verifyTextValue("ScreenHeading", map.get("ScreenHeadingStore"));
		StoreAdminPage.click("TopicColumnFilter");
		StoreAdminPage.enterIntoTextBox("TopicColumnFilter", map.get("EditTopic"));
		StoreAdminPage.click("OptionColumnFilter");
		StoreAdminPage.enterIntoTextBox("OptionColumnFilter", map.get("EditPermission"));
		StoreAdminPage.click("FirstRecord");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		StoreAdminPage.click("EditButton");
		
		//Verifying error when no data is entered in 'Value' field
		StoreAdminPage.explicitWaitForElementToBeClickable("DaysValue");
		StoreAdminPage.click("DaysValue");
		StoreAdminPage.clearElement("DaysValue");
		StoreAdminPage.explicitWaitForElementToBeClickable("ApplyButton");
		StoreAdminPage.click("ApplyButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
//		StoreAdminPage.explicitWaitForVisibility("Error");
		StoreAdminPage.verifyTextValue("Error", map.get("MandatoryError"));
		
		//Verifying error when alphabet is entered in 'Value' field
		StoreAdminPage.explicitWaitForElementToBeClickable("DaysValue");
		StoreAdminPage.click("DaysValue");
		StoreAdminPage.clearElement("DaysValue");
		StoreAdminPage.enterIntoTextBox("DaysValue", map.get("InvalidValue"));
		StoreAdminPage.explicitWaitForElementToBeClickable("ApplyButton");
		StoreAdminPage.click("ApplyButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
//		StoreAdminPage.explicitWaitForVisibility("Error");
		StoreAdminPage.verifyTextValue("Error", map.get("InvalidError"));
		
		//Verifying error when negative value is entered in 'Value' field
		StoreAdminPage.explicitWaitForElementToBeClickable("DaysValue");
		StoreAdminPage.click("DaysValue");
    	StoreAdminPage.clearElement("DaysValue");
		StoreAdminPage.enterIntoTextBox("DaysValue", map.get("NegativeValue"));
		StoreAdminPage.explicitWaitForElementToBeClickable("ApplyButton");
		StoreAdminPage.click("ApplyButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
//		StoreAdminPage.explicitWaitForVisibility("Error");
		StoreAdminPage.verifyTextValue("Error", map.get("NegativeError"));
		
		//Verifying error when higher value is entered in 'Value' field
		StoreAdminPage.explicitWaitForElementToBeClickable("DaysValue");
		StoreAdminPage.click("DaysValue");
		StoreAdminPage.clearElement("DaysValue");
		StoreAdminPage.enterIntoTextBox("DaysValue", map.get("HighValue"));
		StoreAdminPage.explicitWaitForElementToBeClickable("ApplyButton");
		StoreAdminPage.click("ApplyButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
//		StoreAdminPage.explicitWaitForVisibility("Error");
		StoreAdminPage.verifyTextValue("Error", map.get("HighError"));
		
		//Entering valid value and saving the change
		StoreAdminPage.click("DaysValue");
		StoreAdminPage.clearElement("DaysValue");
		StoreAdminPage.enterIntoTextBox("DaysValue", map.get("EditDays"));
		StoreAdminPage.explicitWaitForElementToBeClickable("ApplyButton");
		StoreAdminPage.click("ApplyButton");
		StoreAdminPage.explicitWaitForElementToBeClickable("SaveButton");
		StoreAdminPage.click("SaveButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		StoreAdminPage.verifyTextValue("SaveMessage", map.get("SaveConfirm"));
		StoreAdminPage.click("SaveConfirm");
		
		//Reverting the change for further automation runs
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		StoreAdminPage.click("TopicColumnFilter");
		StoreAdminPage.enterIntoTextBox("TopicColumnFilter", map.get("EditTopic"));
		StoreAdminPage.click("OptionColumnFilter");
		StoreAdminPage.enterIntoTextBox("OptionColumnFilter", map.get("EditPermission"));
		StoreAdminPage.click("FirstRecord");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		StoreAdminPage.click("EditButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		String Topic = StoreAdminPage.getAttributeValue("TopicValue", "value");
		StoreAdminPage.verifyValuesAreEqual(Topic, map.get("ExpectedTopic"));
		String Value = StoreAdminPage.getAttributeValue("OptionValue", "value");
		StoreAdminPage.verifyValuesAreEqual(Value, map.get("ExpectedOption"));
		StoreAdminPage.click("DaysValue");
		StoreAdminPage.clearElement("DaysValue");
		StoreAdminPage.enterIntoTextBox("DaysValue", map.get("OldEditDays"));
		StoreAdminPage.explicitWaitForElementToBeClickable("ApplyButton");
		StoreAdminPage.click("ApplyButton");
		StoreAdminPage.explicitWaitForElementToBeClickable("SaveButton");
		StoreAdminPage.click("SaveButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		StoreAdminPage.verifyTextValue("SaveMessage", map.get("SaveConfirm"));
		StoreAdminPage.click("SaveConfirm");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		StoreAdminPage.click("TopicColumnFilter");
		StoreAdminPage.enterIntoTextBox("TopicColumnFilter", map.get("EditTopic"));
		StoreAdminPage.click("OptionColumnFilter");
		StoreAdminPage.enterIntoTextBox("OptionColumnFilter", map.get("EditPermission"));
		StoreAdminPage.click("FirstRecord");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		String oldValue = StoreAdminPage.getAttributeValue("DaysValue", "value");
		StoreAdminPage.verifyValuesAreEqual(oldValue, map.get("OldEditDays"));
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


