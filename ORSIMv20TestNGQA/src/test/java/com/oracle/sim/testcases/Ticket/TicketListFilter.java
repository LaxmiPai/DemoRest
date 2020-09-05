/**
 * @author lapai
 *
 */
package com.oracle.sim.testcases.Ticket;

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

public class TicketListFilter {

	public static Logger logger = Logger.getLogger(TicketListFilter.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	Random random = new Random();
	PageFactory pagefactory = new PageFactory();

	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage RoleMaintenancePage;
	SimBasePage TicketPage;

	@BeforeClass
	public void setup(ITestContext context) throws Exception {

		logger.info("TestCase Name: " + logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
		LoginPage = pagefactory.initialize("LoginPage");
		HomePage = pagefactory.initialize("HomePage");
		RoleMaintenancePage = pagefactory.initialize("RoleMaintenancePage");
		TicketPage = pagefactory.initialize("TicketPage");
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username", LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password", LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");
		HomePage.verifyTextValue("Title", HomePage.getProperty("Title"));
		HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
		HomePage.storeIdCheck();
	}

	@DataProvider(name = "TicketPermissionVerify")
	public Object[][] getTestDataForTicketPermission() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("TicketDetailTestData"),
				"TicketDetailPermission");
		return testObjArray;

	}

	@Test(dataProvider = "TicketPermissionVerify", priority = 1)
	public void ticketPrintPermissionVerify(Map<String, String> map) throws Exception {

		logger.info("Method Name:TicketPrintPermissionVerify");
		HomePage.click("Navigation");
		HomePage.click("Security");
		HomePage.click("RoleMaintenancePage");
		TicketPage.explicitWaitForElementToBeClickable("RoleNameLink");
		TicketPage.click("FilterByRoleName");
		TicketPage.enterIntoTextBox("FilterByRoleName", map.get("RoleName"));
		TicketPage.explicitWaitForElementToBeClickable("RoleNameLink");
		TicketPage.click("RoleNameLink");
		TicketPage.explicitWaitForElementToBeClickable("FilterByPermission");
		TicketPage.explicitWaitForElementToBeClickable("AssignedData");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.click("FilterByPermission");
		TicketPage.enterIntoTextBox("FilterByPermission", map.get("Permission1"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.click("GridHighLightRecord");
		TicketPage.explicitWaitForElementToBeClickable("AssignedData");
		if (TicketPage.getTitle("AssignedData").equals(map.get("AssignedDataNo"))) {
			TicketPage.explicitWaitForElementToBeClickable("AssigneSelectedButton");
			TicketPage.click("AssigneSelectedButton");
			TicketPage.click("SaveButton");
			TicketPage.click("YesButton");
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
			TicketPage.explicitWaitForElementToBeClickable("FilterByPermission");
			TicketPage.click("FilterByPermission");
			TicketPage.enterIntoTextBox("FilterByPermission", map.get("Permission1"));
			TicketPage.explicitWaitForElementToBeClickable("AssignedData");
			System.out.println("Assign Permission:" + TicketPage.getTitle("AssignedData"));
		}
		HomePage.click("Backlink");
		TicketPage.RefreshWebPage();
	}

	@DataProvider(name = "TicketFilterVerify")
	public Object[][] getTestDataForTicketFilter() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("TicketDetailTestData"),
				"TicketFilter");
		return testObjArray;

	}

	@Test(dataProvider = "TicketFilterVerify", priority = 2)
	public void ticketFilterVerify(Map<String, String> map) throws Exception {

		HomePage.click("Navigation");
		HomePage.click("InventoryManagement");
		HomePage.click("InventoryAdjustment");
		HomePage.explicitWaitForElementToBeClickable("Ticket");
		HomePage.click("Ticket");
		TicketPage.verifyTextValue("TicketListTitle", map.get("TicketList"));
		TicketPage.explicitWaitForElementToBeClickable("SearchButton");
		TicketPage.click("SearchButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));

		// Verify Item records
		TicketPage.explicitWaitForElementToBeClickable("ItemFilterBox");
		TicketPage.click("ItemFilterBox");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.enterIntoTextBox("ItemFilterBox", map.get("Item"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.verifyTableRowUsingColumnValue("ItemRecord", map.get("Item"));
		TicketPage.click("RefreshButton");

		// Verify Description records
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.explicitWaitForElementToBeClickable("DescriptionFilterBox");
		TicketPage.click("DescriptionFilterBox");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.enterIntoTextBox("DescriptionFilterBox", map.get("Description"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.verifyTableRowUsingColumnValue("DescriptionRecord", map.get("Description"));
		TicketPage.click("RefreshButton");

		// Verify Format Type records
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.explicitWaitForElementToBeClickable("FormatTypeFilterBox");
		TicketPage.click("FormatTypeFilterBox");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.enterIntoTextBox("FormatTypeFilterBox", map.get("FormatType"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.verifyTableRowUsingColumnValue("FormatTypeRecord", map.get("FormatType"));
		TicketPage.click("RefreshButton");

		// Verify Origin Type records
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.explicitWaitForElementToBeClickable("OriginTypeFilterBox");
		TicketPage.click("OriginTypeFilterBox");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.enterIntoTextBox("OriginTypeFilterBox", map.get("OriginType"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.verifyTableRowUsingColumnValue("OriginTypeRecord", map.get("OriginType"));
		TicketPage.click("RefreshButton");

		// Verify Print Quantity records
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.explicitWaitForElementToBeClickable("PrintQuantityFilterBox");
		TicketPage.click("PrintQuantityFilterBox");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.enterIntoTextBox("PrintQuantityFilterBox", map.get("PrintQuantity"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.verifyTableRowUsingColumnValue("PrintQuantityRecord", map.get("PrintQuantity"));
		TicketPage.click("RefreshButton");

		// Verify Price Type records
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.explicitWaitForElementToBeClickable("PriceTypeFilterBox");
		TicketPage.click("PriceTypeFilterBox");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.enterIntoTextBox("PriceTypeFilterBox", map.get("PriceType"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.verifyTableRowUsingColumnValue("PriceTypeRecord", map.get("PriceType"));
		TicketPage.click("RefreshButton");

		// Verify Active DAte records
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.explicitWaitForElementToBeClickable("ActiveDateFilterBox");
		TicketPage.click("ActiveDateFilterBox");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.enterIntoTextBox("ActiveDateFilterBox", map.get("ActiveDate"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.verifyTableRowUsingColumnValue("ActiveDateRecord", map.get("ActiveDate"));
		TicketPage.click("RefreshButton");

		// Verify End Date records
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.explicitWaitForElementToBeClickable("EndDateFilterBox");
		TicketPage.click("EndDateFilterBox");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.enterIntoTextBox("EndDateFilterBox", map.get("EndDate"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.verifyTableRowUsingColumnValue("EndDateRecord", map.get("EndDate"));
		TicketPage.click("RefreshButton");

		// Verify printed records
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.explicitWaitForElementToBeClickable("PrintedFilterBox");
		TicketPage.click("PrintedFilterBox");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.enterIntoTextBox("PrintedFilterBox", map.get("Printed"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.verifyTableRowUsingColumnValue("PrintedRecord", map.get("Printed"));
		TicketPage.click("RefreshButton");

		// Verify print date records
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.explicitWaitForElementToBeClickable("PrintDateFilterBox");
		TicketPage.click("PrintDateFilterBox");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.enterIntoTextBox("PrintDateFilterBox", map.get("PrintDate"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.verifyTableRowUsingColumnValue("PrintDateRecord", map.get("PrintDate"));
		TicketPage.click("RefreshButton");

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