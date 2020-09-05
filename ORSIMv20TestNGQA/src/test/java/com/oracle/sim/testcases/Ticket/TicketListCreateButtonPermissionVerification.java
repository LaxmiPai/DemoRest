package com.oracle.sim.testcases.Ticket;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

public class TicketListCreateButtonPermissionVerification {

	public static Logger logger = Logger.getLogger(TicketListCreateButtonPermissionVerification.class.getName());
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

	@DataProvider(name = "RoleMaintenanceTestData")
	public Object[][] getRoleMaintenanceTestData() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("SecurityTestData"),
				"RoleMaintenance");
		return testObjArray;
	}

	@Test(dataProvider = "RoleMaintenanceTestData", priority = 1)
	public void verifyRole(Map<String, String> map) throws Exception {
		// Navigating to role maintenance page
		logger.info("Method Name: verifyRole");
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.storeIdCheck();
		HomePage.click("Navigation");
		HomePage.click("Security");
		HomePage.click("RoleMaintenance");

		// Verifying RoleMaintenance Page Title
		RoleMaintenancePage.explicitWaitForElementToBeClickable("Title");
		RoleMaintenancePage.verifyTextValue("Title", map.get("Title"));

		// Granting the user permission
		String userRole = propReader.getApplicationproperty("UserRole");
		RoleMaintenancePage.verifyUserRole(userRole, map.get("AccessTicketList"), map.get("AssignedDataNo"));
	}

	// DataProvider for Security Permission of Ticket
	@DataProvider(name = "TicketListScreenTestData")
	public Object[][] getTestDataForUI() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("TicketListTestData"),
				"TicketListCreateButton");
		return testObjArray;
	}

	// verify the Security Permission of Ticket
	@Test(dataProvider = "TicketListScreenTestData", priority = 2)
	public void verifyTicketListCreatePermission(Map<String, String> map) throws Exception {

		logger.info("Method Name: verifyTicketListCreatePermission Started..!");
		HomePage.explicitWaitForElementToBeClickable("SecurityPage");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
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
		TicketPage.enterIntoTextBox("FilterByPermission", map.get("CreatePermission"));
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
			TicketPage.enterIntoTextBox("FilterByPermission", map.get("CreatePermission"));
			TicketPage.explicitWaitForElementToBeClickable("AssignedData");
			System.out.println("Revoke Permission:" + TicketPage.getTitle("AssignedData"));
		}

		TicketPage.RefreshWebPage();
		// Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		// TicketListPage.RefreshWebPage();

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

		// verify the Create Button in Ticket List Screen
		TicketPage.checkElementIsNotPresent("ListScreenCreateButton");

		// Assign Permission back --> Create Ticket
		HomePage.explicitWaitForElementToBeClickable("Backlink");
		HomePage.click("Backlink");
		HomePage.explicitWaitForElementToBeClickable("SecurityPage");
		HomePage.click("SecurityPage");
		HomePage.click("RoleMaintenancePage");
		TicketPage.explicitWaitForElementToBeClickable("RoleNameLink");
		TicketPage.click("FilterByRoleName");
		TicketPage.enterIntoTextBox("FilterByRoleName", userRole);
		TicketPage.explicitWaitForElementToBeClickable("RoleNameLink");

		TicketPage.click("RoleNameLink");
		TicketPage.explicitWaitForElementToBeClickable("FilterByPermission");
		TicketPage.explicitWaitForElementToBeClickable("AssignedData");

		// Assign the Data Value
		TicketPage.click("FilterByPermission");
		TicketPage.enterIntoTextBox("FilterByPermission", map.get("CreatePermission"));
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
			TicketPage.enterIntoTextBox("FilterByPermission", map.get("CreatePermission"));
			TicketPage.explicitWaitForElementToBeClickable("AssignedData");
			System.out.println("Assign Permission:" + TicketPage.getTitle("AssignedData"));
		}

		// Refresh the Web-Page..
		TicketPage.RefreshWebPage();
		// Navigate to Ticket page...

		HomePage.click("Navigation");
		HomePage.click("InventoryManagement");
		HomePage.click("TicketPage");
		TicketPage.click("NavigationSearchBar");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		// verify the Title of Ticket Search Criteria
		TicketPage.explicitWaitForVisibility("TicketSearchCriteriaTitle");
		TicketPage.verifyTextValue("TicketSearchCriteriaTitle", map.get("SearchCriteriaTitle"));
		// Enter the From Print Date and To Print Date
		TicketPage.clearElement("FromPrintDateTextBox");
		TicketPage.clearElement("ToPrintDateTextBox");
		TicketPage.clearElement("FromActiveDateTextBox");
		TicketPage.clearElement("ToActiveDateTextBox");
		TicketPage.clearElement("FromEndDateTextBox");
		TicketPage.clearElement("ToEndDateTextBox");
		TicketPage.click("SearchButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		// Verify the Title of Ticket List Screen
		TicketPage.explicitWaitForVisibility("TicketListTitle");
		TicketPage.verifyTextValue("TicketListTitle", map.get("TicketListTitle"));

		// Verify the Present of the Ticket List Create Button
		TicketPage.verifyElementIsPresent("ListScreenCreateButton");
		TicketPage.click("ListScreenCreateButton");

		// Create the Ticket
		// Create the Ticket and Save
		TicketPage.click("ItemScanBox");
		TicketPage.enterIntoTextBox("ItemScanBox", map.get("Item"));
		TicketPage.click("ScanBoxArrow");
		// TicketListPage.SelectDropdownOption(value);("FormatTypeDropDown",
		// map.get("FormatType"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.searchDropDownValue("FormatTypeDropDown", map.get("FormatType"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.searchDropDownValue("FormatTypeDropDownList", map.get("Format"));
		Date todaydate = TicketPage.getCurrentDate();
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String strDate = dateFormat.format(todaydate);
		TicketPage.selectDateFromDatePicker("PrintDateTextBox", strDate);
		int Randomnumber = random.nextInt(123);
		String randquantity = Integer.toString(Randomnumber);
		TicketPage.enterIntoTextBox("PrintQuantityTextBox", randquantity);
		TicketPage.click("SaveButton");
		TicketPage.click("YesButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.click("SearchIcon");
		TicketPage.selectDateFromDatePicker("FromPrintDateTextBox", strDate);
		TicketPage.click("SearchButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.explicitWaitForElementToBeClickable("FilterByQuantity");
		TicketPage.click("FilterByQuantity");
		TicketPage.enterIntoTextBox("FilterByQuantity", randquantity);
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		TicketPage.click("GridHighLightRecord");
		
		// Click on Delete Button
		TicketPage.click("ListScreenDeleteButton");
		TicketPage.click("YesButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.click("RefreshButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));

		logger.info("Method Name: verifyTicketListCreatePermission Successfully executed..!");

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
