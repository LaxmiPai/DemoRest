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

public class SystemAdminUIAndNavigation {
	public static Logger logger=Logger.getLogger(SystemAdminUIAndNavigation.class.getName());
	public static PropertyReader propReader = new PropertyReader();
	PageFactory pageFactory = new PageFactory();
	
	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage RoleMaintenancePage;
	SimBasePage SystemAdminPage;
		
	@BeforeClass()
	public void setup(ITestContext context) throws Exception{
		logger.info("TestCase Name: In before class");
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
	
	@DataProvider (name="SystemAdminUITestData")
	public Object[][] getSystemAdminUITestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("SystemAdminTestData"), "AdminUI");
		return testObjArray;
	}	
	
	@Test(dataProvider = "SystemAdminUITestData", priority=2)
	public void verifySystemAdminUI(Map<String, String> map) throws Exception {
		
		logger.info("Method Name: verifySystemAdminUI");
		SystemAdminPage.explicitWaitForElementToBeClickable("AdminMenu");
		SystemAdminPage.click("AdminMenu");
		SystemAdminPage.explicitWaitForElementToBeClickable("ConfigurationMenu");
		SystemAdminPage.click("ConfigurationMenu");
		SystemAdminPage.explicitWaitForElementToBeClickable("SystemAdministrationMenu");
		SystemAdminPage.click("SystemAdministrationMenu");
		
		//Screen elements verification
		SystemAdminPage.verifyTextValue("ScreenHeading", map.get("ScreenHeading"));
		SystemAdminPage.isElementPresent("SaveButton");
		SystemAdminPage.verifyElementIsDisabled("SaveButton");
		SystemAdminPage.isElementPresent("RefreshButton");
		SystemAdminPage.isEnabled("RefreshButton");
		SystemAdminPage.explicitWaitForElementToBeClickable("GridViewMenu");
		SystemAdminPage.click("GridViewMenu");
		SystemAdminPage.explicitWaitForElementToBeClickable("ResetView");
		SystemAdminPage.verifyTextValue("ResetView",map.get("ResetView"));
		SystemAdminPage.verifyTextValue("ColumnFilter",map.get("ColumnFilter"));
		SystemAdminPage.verifyTextValue("ExportToCsv",map.get("ExportToCsv"));
		SystemAdminPage.click("GridViewMenu");
		
		//Column verification
		SystemAdminPage.verifyTextValue("TopicColumn", map.get("Topic"));
		SystemAdminPage.verifyTextValue("OptionColumn", map.get("Option"));
		SystemAdminPage.verifyTextValue("ValueColumn", map.get("Value"));
		
		//Edit section verification
		SystemAdminPage.verifyTextValue("EditHeading", map.get("EditHeading"));
		SystemAdminPage.isElementPresent("EditButton");
		SystemAdminPage.isElementPresent("ApplyButton");
		SystemAdminPage.isElementPresent("CancelButton");
		SystemAdminPage.verifyElementIsDisabled("EditButton");
		SystemAdminPage.verifyElementIsDisabled("ApplyButton");
		SystemAdminPage.verifyElementIsDisabled("CancelButton");
		SystemAdminPage.verifyTextValue("TopicEdit", map.get("TopicField"));
		SystemAdminPage.verifyTextValue("OptionEdit", map.get("OptionField"));
		
		//Sort verification topic column
		SystemAdminPage.explicitWaitForElementToBeClickable("TopicColumn");
		SystemAdminPage.click("TopicColumn");
		SystemAdminPage.implicitWait();
		SystemAdminPage.click("TopicColumn");
		SystemAdminPage.implicitWait();
		SystemAdminPage.click("TopicColumn");
		SystemAdminPage.implicitWait();
		SystemAdminPage.columnSorting("TopicColumnValues", "TopicColumn", "ascending", "String");
		
		//Sort verification Option column
		SystemAdminPage.explicitWaitForElementToBeClickable("OptionColumn");
		SystemAdminPage.click("OptionColumn");
		SystemAdminPage.implicitWait();
		SystemAdminPage.columnSorting("OptionColumnValues", "OptionColumn", "ascending", "String");
		
		//Sort verification value column
		SystemAdminPage.explicitWaitForElementToBeClickable("ValueColumn");
		SystemAdminPage.click("ValueColumn");
		SystemAdminPage.implicitWait();
		SystemAdminPage.columnSorting("ValueColumnValues", "ValueColumn", "ascending", "String");
		
		//To verify filtering of column
//		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		SystemAdminPage.explicitWaitForElementToBeClickable("TopicColumnFilter");
		SystemAdminPage.click("TopicColumnFilter");
		SystemAdminPage.enterIntoTextBox("TopicColumnFilter", map.get("TopicFilter"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		SystemAdminPage.verifyTableRowContentsOfColumn("TopicColumnValues", map.get("TopicFilter"));
		SystemAdminPage.clearElement("TopicColumnFilter");
		SystemAdminPage.click("RefreshButton");
		
		//To verify filtering of column
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		SystemAdminPage.explicitWaitForElementToBeClickable("OptionColumnFilter");
		SystemAdminPage.click("OptionColumnFilter");
		SystemAdminPage.enterIntoTextBox("OptionColumnFilter", map.get("OptionFilter"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		SystemAdminPage.verifyTableRowContentsOfColumn("OptionColumnValues", map.get("OptionFilter"));
		SystemAdminPage.clearElement("OptionColumnFilter");
		SystemAdminPage.click("RefreshButton");
		
		//To verify filtering of column
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		SystemAdminPage.explicitWaitForElementToBeClickable("ValueColumnFilter");
		SystemAdminPage.click("ValueColumnFilter");
		SystemAdminPage.enterIntoTextBox("ValueColumnFilter", map.get("ValueFilter"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		SystemAdminPage.verifyTableRowContentsOfColumn("ValueColumnValues", map.get("ValueFilter"));
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
