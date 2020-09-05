/**
 * 
 */
package com.oracle.sim.testcases.PrinterSetup;

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

/**
 * @author lapai
 *
 */
public class PrinterNavigationAndUi {


	public static Logger logger=Logger.getLogger(PrinterNavigationAndUi.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pagefactory=new PageFactory();

	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage RoleMaintenancePage;
	SimBasePage PrinterSetupPage;

	@BeforeClass
	public void setup(ITestContext context) throws Exception {
		logger.info("TestCase Name: " +logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
		LoginPage=pagefactory.initialize("LoginPage");
		HomePage=pagefactory.initialize("HomePage");
		RoleMaintenancePage=pagefactory.initialize("RoleMaintenancePage");
		PrinterSetupPage=pagefactory.initialize("PrinterSetupPage");
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username",LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password",LoginPage.getProperty("Password"));
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
		RoleMaintenancePage.verifyUserRole(userRole, map.get("AccessPrinterSetup"), map.get("AssignedDataNo"));
		HomePage.explicitWaitForElementToBeClickable("Admin");
		HomePage.click("Admin");
		HomePage.explicitWaitForElementToBeClickable("TechnicalMaintenance");
		HomePage.click("TechnicalMaintenance");
		HomePage.explicitWaitForElementToBeClickable("PrinterSetup");
		HomePage.click("PrinterSetup");
	}
	@DataProvider(name = "NavigationAndUi")
	public Object[][] getTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("PrinterTestData"),
				"PrinterValidation");
		return testObjArray;
	}

	@Test(dataProvider="NavigationAndUi",priority=2, dependsOnMethods={"verifyRole"})
	public void verifyNavigationAndUi(Map<String,String> map) throws Exception {
	   
		logger.info("Method Name: verifyNavigationAndUi");
		PrinterSetupPage.verifyTextValue("Title",map.get("Title"));
		
		//Verify Buttons and Grid
		PrinterSetupPage.verifyElementIsEnabled("RefreshButton");
		PrinterSetupPage.verifyElementIsDisabled("Save");
		PrinterSetupPage.verifyElementIsEnabled("Add");
		PrinterSetupPage.verifyElementIsEnabled("Delete");
		
		//Verify Filter option 
		PrinterSetupPage.click("GridViewMenu");
		PrinterSetupPage.verifyElementIsPresent("ColumnFilter");
		PrinterSetupPage.click("ColumnFilter");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		PrinterSetupPage.checkElementIsNotPresent("FilterName");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		PrinterSetupPage.click("GridViewMenu");
		PrinterSetupPage.click("ColumnFilter");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		PrinterSetupPage.verifyElementIsPresent("FilterName");
		
		//Verify Row selector option
		PrinterSetupPage.click("GridViewMenu");
		PrinterSetupPage.click("RowSelector");
		PrinterSetupPage.verifyElementIsEnabled("RowSelectorCheckBox1");
		PrinterSetupPage.verifyElementIsEnabled("RowSelectorCheckBox2");
		PrinterSetupPage.click("RowSelectorCheckBox1");
		PrinterSetupPage.click("RowSelectorCheckBox2");
		PrinterSetupPage.verifyCheckBoxIsSelected("RowSelectorCheckBox1");
		PrinterSetupPage.verifyCheckBoxIsSelected("RowSelectorCheckBox2");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		PrinterSetupPage.click("RowSelectorCheckBox1");
		PrinterSetupPage.click("RowSelectorCheckBox2");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		PrinterSetupPage.click("GridViewMenu");
		PrinterSetupPage.explicitWaitForElementToBeClickable("RowSelector");
		PrinterSetupPage.click("RowSelector");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		PrinterSetupPage.checkElementIsNotPresent("RowSelectorCheckBox1");
		PrinterSetupPage.checkElementIsNotPresent("RowSelectorCheckBox2");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		PrinterSetupPage.sort("PrinterNameColumnElements", "PrinterNameColumn", "ascending");
		/*Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		PrinterSetupPage.click("NameGrid");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		PrinterSetupPage.columnSorting("PrinterNameColumnElements", "PrinterNameColumn", "ascending");*/
		
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
		} finally {
			SIMWebdriverBase.close();
		}
	}
	
}
