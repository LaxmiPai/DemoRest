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

public class TicketListSort {

	public static Logger logger = Logger.getLogger(TicketListSort.class.getName());
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

	@DataProvider(name = "TicketListSortVerify")
	public Object[][] getTestDataForTicketPermission() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("TicketDetailTestData"),
				"TicketDetailPermission");
		return testObjArray;

	}

	@Test(dataProvider = "TicketListSortVerify", priority = 1)
	public void ticketPrintSortVerify(Map<String, String> map) throws Exception {

		logger.info("Method Name:TicketPrintSortVerify");
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

		HomePage.click("Navigation");
		HomePage.click("InventoryManagement");
		HomePage.explicitWaitForElementToBeClickable("InventoryAdjustment");
		HomePage.click("InventoryAdjustment");
		HomePage.explicitWaitForElementToBeClickable("Ticket");
		HomePage.click("Ticket");
		TicketPage.verifyTextValue("TicketListTitle", map.get("TicketList"));
		TicketPage.explicitWaitForElementToBeClickable("SearchButton");
		TicketPage.click("SearchButton");
		HomePage.click("Navigation");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		
		//Verify the Print date column elements sorted in descending order
		TicketPage.columnSorting("PrintDateColumnElements", "PrintDateHeader", "descending", "Date");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));

		// Verify Print Quantity field are sorted in ascending order
		TicketPage.click("PrintQuantityHeader");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
		TicketPage.columnSorting("PrintQuantityColumnElements", "PrintQuantityHeader", "ascending", "Integer");

		// Verify Print Quantity field are sorted in descending order
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.click("PrintQuantityHeader");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
		TicketPage.columnSorting("PrintQuantityColumnElements", "PrintQuantityHeader", "descending",
				"Integer");

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
