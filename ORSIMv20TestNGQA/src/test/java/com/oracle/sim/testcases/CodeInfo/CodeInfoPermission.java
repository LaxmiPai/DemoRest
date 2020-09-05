/**
 * * @author lapai
 * */
package com.oracle.sim.testcases.CodeInfo;

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

public class CodeInfoPermission {

	// TODO Auto-generated method stub
	public static Logger logger = Logger.getLogger(CodeInfoPermission.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pageFactory = new PageFactory();

	SimBasePage LoginPage;
	SimBasePage HomePage;
	
	SimBasePage CodeInfoPage;

	@BeforeClass
	public void setUp(ITestContext context) throws Exception {
		logger.info("TestCase Name: " +logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
		// Login Steps
		LoginPage = pageFactory.initialize("LoginPage");
		HomePage = pageFactory.initialize("HomePage");
		CodeInfoPage= pageFactory.initialize("CodeInfoPage");
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username", LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password", LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");
		HomePage.verifyTextValue("Title", HomePage.getProperty("Title"));
		HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
		HomePage.storeIdCheck();

	}

	@DataProvider(name = "CodeInfoPermissionVerify")
	public Object[][] getTestDataForCodeInfoPermission() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("CodeInfo"),
				"CodeInfoPermission");
		return testObjArray;

	}

	@Test(dataProvider = "CodeInfoPermissionVerify")
	public void codeInfoPermissionVerify(Map<String, String>map) throws Exception {
		HomePage.explicitWaitForVisibility("Titlelogo");
		HomePage.click("Navigation");
		HomePage.click("Security");
		HomePage.click("RoleMaintenancePage");
		CodeInfoPage.explicitWaitForElementToBeClickable("RoleNameLink");
		CodeInfoPage.click("FilterByRoleName");
		CodeInfoPage.enterIntoTextBox("FilterByRoleName", map.get("RoleName"));
		CodeInfoPage.explicitWaitForElementToBeClickable("RoleNameLink");
		CodeInfoPage.click("RoleNameLink");
		CodeInfoPage.explicitWaitForElementToBeClickable("FilterByPermission");
		CodeInfoPage.explicitWaitForElementToBeClickable("AssignedData");

		// Assign the Data Value
		CodeInfoPage.click("FilterByPermission");
		CodeInfoPage.enterIntoTextBox("FilterByPermission", map.get("Permission"));
		CodeInfoPage.click("GridRecord");
		
		// Remove the permission for the user
		CodeInfoPage.explicitWaitForElementToBeClickable("AssignedData");
		if (CodeInfoPage.getTitle("AssignedData").equals(map.get("AssignedDataYes"))) {
			CodeInfoPage.explicitWaitForElementToBeClickable("RevokeSelectedButton");
			CodeInfoPage.click("RevokeSelectedButton");
			CodeInfoPage.click("SaveIcon");
			CodeInfoPage.click("YesButton");
			// wait for DB commit to perform
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
			CodeInfoPage.explicitWaitForElementToBeClickable("FilterByPermission");
			CodeInfoPage.click("FilterByPermission");
			CodeInfoPage.enterIntoTextBox("FilterByPermission", map.get("Permission"));
			CodeInfoPage.explicitWaitForElementToBeClickable("AssignedData");
			System.out.println("Revoke Permission:" + CodeInfoPage.getTitle("AssignedData"));
		}

		CodeInfoPage.click("BackLink");
		CodeInfoPage.RefreshWebPage();

		HomePage.click("Navigation");
		HomePage.click("Admin");
		HomePage.click("DataSetup");

		CodeInfoPage.VerifyRevokePermissionPage(map.get("PageName"));

		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));

		CodeInfoPage.explicitWaitForElementToBeClickable("FilterByPermission");
		CodeInfoPage.explicitWaitForElementToBeClickable("AssignedData");

		// Assign the Data Value
		CodeInfoPage.click("FilterByPermission");
		CodeInfoPage.enterIntoTextBox("FilterByPermission", map.get("Permission"));
		CodeInfoPage.click("GridRecord");
		// Assign the permission for the user
		CodeInfoPage.explicitWaitForElementToBeClickable("AssignedData");
		if (CodeInfoPage.getTitle("AssignedData").equals(map.get("AssignedDataNo"))) {
			CodeInfoPage.explicitWaitForElementToBeClickable("AssigneSelectedButton");
			CodeInfoPage.click("AssigneSelectedButton");
			CodeInfoPage.click("SaveIcon");
			CodeInfoPage.click("YesButton");
			
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
			CodeInfoPage.explicitWaitForElementToBeClickable("FilterByPermission");
			CodeInfoPage.click("FilterByPermission");
			CodeInfoPage.enterIntoTextBox("FilterByPermission", map.get("Permission"));
			CodeInfoPage.explicitWaitForElementToBeClickable("AssignedData");
			System.out.println("Assign Permission:" + CodeInfoPage.getTitle("AssignedData"));
		}

		// Refresh the Web-Page..
		CodeInfoPage.RefreshWebPage();
		// Navigate to Code Info page...

		HomePage.click("Navigation");
		HomePage.click("Admin");
		HomePage.click("DataSetup");
		HomePage.click("CodeInfo");
		CodeInfoPage.click("NavigationSearchBar");

		// Verify the Title of the Screen..
		CodeInfoPage.verifyTextValue("Title", map.get("Title"));
		
		CodeInfoPage.verifyElementIsPresent("RefreshButton");

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
