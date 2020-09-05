package com.oracle.sim.testcases.SupplierLookup;

import java.util.Map;
import java.util.logging.Logger;

import org.openqa.selenium.By;
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

public class SupplierLookupFilterAndSort {

	public static Logger logger = Logger.getLogger(SupplierLookupFilterAndSort.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pageFactory = new PageFactory();

	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage RoleMaintenancePage;
	SimBasePage SupplierLookupPage;

	@BeforeClass()
	public void setup(ITestContext context) throws Exception {
		logger.info("TestCase Name: " + logger.getName());
		SIMWebdriverBase.loadInitialURL(context);

		// Initialize the pages that are to be used
		LoginPage = pageFactory.initialize("LoginPage");
		HomePage = pageFactory.initialize("HomePage");
		SupplierLookupPage = pageFactory.initialize("SupplierLookupPage");
		RoleMaintenancePage = pageFactory.initialize("RoleMaintenancePage");

		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username", LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password", LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");
		HomePage.verifyTextValue("Title", HomePage.getProperty("Title"));
		HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
		HomePage.storeIdCheck();
	}

	@DataProvider(name = "SupplierLookupPermission")
	public Object[][] getRoleMaintenanceTestData() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("SupplierDetailTestData"),
				"AccessSupplierLookup");
		return testObjArray;
	}

	@Test(dataProvider = "SupplierLookupPermission", priority = 1)
	public void verifySupplierLookupPermission(Map<String, String> map) throws Exception {
		HomePage.explicitWaitForVisibility("Titlelogo");
		HomePage.click("Navigation");
		HomePage.click("Security");
		HomePage.click("RoleMaintenancePage");
		SupplierLookupPage.explicitWaitForElementToBeClickable("RoleNameLink");
		SupplierLookupPage.click("FilterByRoleName");
		SupplierLookupPage.enterIntoTextBox("FilterByRoleName", map.get("RoleName"));
		SupplierLookupPage.explicitWaitForElementToBeClickable("RoleNameLink");
		SupplierLookupPage.click("RoleNameLink");
		SupplierLookupPage.explicitWaitForElementToBeClickable("FilterByPermission");
		SupplierLookupPage.explicitWaitForElementToBeClickable("AssignedData");

		// Assign the Data Value
		SupplierLookupPage.click("FilterByPermission");
		SupplierLookupPage.enterIntoTextBox("FilterByPermission", map.get("Permission"));
		SupplierLookupPage.click("GridRecord");

		// Remove the permission for the user
		SupplierLookupPage.explicitWaitForElementToBeClickable("AssignedData");
		if (RoleMaintenancePage.getTitle("AssignedData").equals(map.get("AssignedDataYes"))) {
			RoleMaintenancePage.click("RevokeSelected");
			RoleMaintenancePage.click("SaveButton");
			RoleMaintenancePage.click("YesButton");
			// wait for DB commit to perform
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
			RoleMaintenancePage.click("FilterPermission");
			RoleMaintenancePage.enterIntoTextBox("FilterPermission", map.get("AccessSupplierLookup"));
			System.out.println(RoleMaintenancePage.getTitle("AssignedData"));
		}
		RoleMaintenancePage.click("BackLink");
		SupplierLookupPage.RefreshWebPage();
		HomePage.click("Navigation");
		HomePage.click("Lookups");
		SupplierLookupPage.VerifyRevokePermissionPage(map.get("PageName"));
		SupplierLookupPage.explicitWaitForElementToBeClickable("FilterByPermission");
		SupplierLookupPage.explicitWaitForElementToBeClickable("AssignedData");

		// Assign the Data Value
		SupplierLookupPage.click("FilterByPermission");
		SupplierLookupPage.enterIntoTextBox("FilterByPermission", map.get("Permission"));
		SupplierLookupPage.click("GridRecord");
		// Assign the permission for the user
		SupplierLookupPage.explicitWaitForElementToBeClickable("AssignedData");
		if (SupplierLookupPage.getTitle("AssignedData").equals(map.get("AssignedDataNo"))) {
			SupplierLookupPage.explicitWaitForElementToBeClickable("AssigneSelectedButton");
			SupplierLookupPage.click("AssigneSelectedButton");
			SupplierLookupPage.click("SaveIcon");
			SupplierLookupPage.click("YesButton");

			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
			SupplierLookupPage.explicitWaitForElementToBeClickable("FilterByPermission");
			SupplierLookupPage.click("FilterByPermission");
			SupplierLookupPage.enterIntoTextBox("FilterByPermission", map.get("Permission"));
			SupplierLookupPage.explicitWaitForElementToBeClickable("AssignedData");
			System.out.println("Assign Permission:" + SupplierLookupPage.getTitle("AssignedData"));
		}

		// Refresh the Web-Page..
		SupplierLookupPage.RefreshWebPage();
		// Navigate to Code Info page...
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.click("Navigation");
		HomePage.click("Lookups");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		HomePage.click("SupplierLookups");

		// Verify the Title of the Screen..
		SupplierLookupPage.verifyTextValue("Title", map.get("Title"));
		SupplierLookupPage.verifyElementIsPresent("RefreshButton");

	}

	@DataProvider(name = "SupplierFilterSortVerify")
	public Object[][] getTestDataForSupplierFilterSort() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("SupplierLookupIdName"),
				"SupplierFilterSort");

		return testObjArray;
	}

	@Test(dataProvider = "SupplierFilterSortVerify", priority = 2)
	public void FilterAndSort1(Map<String, String> map) throws Exception {
        
		
		SupplierLookupPage.explicitWaitForElementToBeClickable("SupplierNameColumn");
		SupplierLookupPage.explicitWaitForElementToBeClickable("SupplierNameColumnElements");
		SupplierLookupPage.columnSorting("SupplierNameColumnElements", "SupplierNameColumn", "ascending");

		// Filter by entering a valid supplier name
		SupplierLookupPage.click("SupplierNameFilterBox");
		SupplierLookupPage.enterIntoTextBox("SupplierNameFilterBox", map.get("SupplierName"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		SupplierLookupPage.verifyTableRowContentsForName();
		SupplierLookupPage.verifyTextValue("SupplierNameColumnElements", map.get("SupplierName"));
		SupplierLookupPage.click("RefreshButton");

		// Filter by entering a valid supplier id
		SupplierLookupPage.click("RefreshButton");
		SupplierLookupPage.explicitWaitForElementToBeClickable("SupplierIdFilterBox");
		SupplierLookupPage.click("SupplierIdFilterBox");
		SupplierLookupPage.enterIntoTextBox("SupplierIdFilterBox", map.get("SupplierId"));
		System.out.println(SupplierLookupPage.getText("GridRecordSupplierId"));
		SupplierLookupPage.verifyTextValue("GridRecordSupplierId", map.get("SupplierId"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
		SupplierLookupPage.verifyTableRowContents();
		SupplierLookupPage.click("RefreshButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		
		// Filter by entering a valid supplier status
		SupplierLookupPage.click("RefreshButton");
		SupplierLookupPage.explicitWaitForElementToBeClickable("SupplierStatusFilterBox");
		SupplierLookupPage.click("SupplierStatusFilterBox");
		SupplierLookupPage.enterIntoTextBox("SupplierStatusFilterBox", map.get("SupplierStatus"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		SupplierLookupPage.verifyTableRowContentsForStatus(map.get("SupplierStatus"));

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
