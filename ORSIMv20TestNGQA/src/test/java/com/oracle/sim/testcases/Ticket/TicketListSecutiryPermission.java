package com.oracle.sim.testcases.Ticket;

/**
 * * @author dsorthiy
 *
 */
import java.util.Map;
import java.util.Random;
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

public class TicketListSecutiryPermission {

	public static Logger logger = Logger.getLogger(TicketListSecutiryPermission.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pageFactory = new PageFactory();
	Random random = new Random();
	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage TicketPage;
	SimBasePage RoleMaintenancePage;

	@BeforeClass()
	public void setup(ITestContext context) throws Exception {

		logger.info("TestCase Name: " + logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
		LoginPage = pageFactory.initialize("LoginPage");
		HomePage = pageFactory.initialize("HomePage");
		TicketPage = pageFactory.initialize("TicketPage");
		RoleMaintenancePage = pageFactory.initialize("RoleMaintenancePage");
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username", LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password", LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");
		HomePage.verifyTextValue("Title", HomePage.getProperty("Title"));
		HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
		HomePage.storeIdCheck();

	}

	// DataProvider for Security Permission of Ticket
	@DataProvider(name = "TicketListScreenTestData")
	public Object[][] getTestDataForUI() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("TicketListTestData"),
				"TicketListPermission");
		return testObjArray;
	}

	// verify the Security Permission of Ticket
	@Test(dataProvider = "TicketListScreenTestData", priority = 1)
	public void verifyAccessTicketListPermission(Map<String, String> map) throws Exception {
		logger.info("TestCase TicketList Access Permission Started..! ");
		HomePage.click("Navigation");
		HomePage.click("SecurityPage");
		HomePage.click("RoleMaintenancePage");
		String userRole = propReader.getApplicationproperty("UserRole");
		TicketPage.explicitWaitForElementToBeClickable("RoleNameLink");
		TicketPage.click("FilterByRoleName");
		TicketPage.enterIntoTextBox("FilterByRoleName", userRole);
		TicketPage.explicitWaitForElementToBeClickable("RoleNameLink");

		TicketPage.click("RoleNameLink");
		TicketPage.explicitWaitForElementToBeClickable("FilterByPermission");
		TicketPage.explicitWaitForElementToBeClickable("AssignedData");

		// Assign the Data Value
		TicketPage.click("FilterByPermission");
		TicketPage.enterIntoTextBox("FilterByPermission", map.get("Permission"));

		TicketPage.click("GridHighLightRecord");
		// Remove the permission for the user
		TicketPage.explicitWaitForElementToBeClickable("AssignedData");
		if (TicketPage.getTitle("AssignedData").equals(map.get("AssignedDataYes"))) {
			TicketPage.explicitWaitForElementToBeClickable("RevokeSelectedButton");
			TicketPage.click("RevokeSelectedButton");
			TicketPage.click("SaveButton");
			TicketPage.click("YesButton");
			// wait for DB commit to perform
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
			TicketPage.explicitWaitForElementToBeClickable("FilterByPermission");
			TicketPage.click("FilterByPermission");
			TicketPage.enterIntoTextBox("FilterByPermission", map.get("Permission"));
			TicketPage.explicitWaitForElementToBeClickable("AssignedData");
			System.out.println("Revoke Permission:" + TicketPage.getTitle("AssignedData"));
		}

		TicketPage.click("BackLink");
		TicketPage.RefreshWebPage();

		HomePage.click("Navigation");
		HomePage.click("InventoryManagement");

		// Verify the Ticket Page is not Present under Inventory Management
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.VerifyPageMenuIsNotPresent(map.get("PageName"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));

		TicketPage.explicitWaitForElementToBeClickable("FilterByPermission");
		TicketPage.explicitWaitForElementToBeClickable("AssignedData");

		// Assign the Data Value
		TicketPage.click("FilterByPermission");
		TicketPage.enterIntoTextBox("FilterByPermission", map.get("Permission"));

		TicketPage.click("GridHighLightRecord");
		// Assign the permission for the user
		TicketPage.explicitWaitForElementToBeClickable("AssignedData");
		if (TicketPage.getTitle("AssignedData").equals(map.get("AssignedDataNo"))) {
			TicketPage.explicitWaitForElementToBeClickable("AssigneSelectedButton");
			TicketPage.click("AssigneSelectedButton");
			TicketPage.click("SaveButton");
			TicketPage.click("YesButton");
			// wait for DB commit to perform
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
			TicketPage.explicitWaitForElementToBeClickable("FilterByPermission");
			TicketPage.click("FilterByPermission");
			TicketPage.enterIntoTextBox("FilterByPermission", map.get("Permission"));
			TicketPage.explicitWaitForElementToBeClickable("AssignedData");
			System.out.println("Assign Permission:" + TicketPage.getTitle("AssignedData"));
		}
		TicketPage.click("BackLink");
		// Refresh the Web-Page..
		TicketPage.RefreshWebPage();

		// Navigate to Ticket Page
		HomePage.click("Navigation");
		HomePage.click("InventoryManagement");
		HomePage.click("TicketPage");
		TicketPage.click("NavigationSearchBar");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		TicketPage.click("SearchCriteriaCloseButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));

		// Verify the Title of Ticket List Screen
		TicketPage.explicitWaitForVisibility("TicketListTitle");
		TicketPage.verifyTextValue("TicketListTitle", map.get("TicketListTitle"));
		System.out.println("Verify the Refresh Button");
		TicketPage.verifyElementIsPresent("RefreshButton");
		logger.info("Successufully verified Security Permission of Ticket List Screen..!");

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
