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

public class TicketDetailPrintButton {
	
	public static Logger logger = Logger.getLogger(TicketDetailPrintButton.class.getName());
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
	@DataProvider(name = "TicketDetailPrintButtonVerify")
	public Object[][] getTestDataForTicketPermission() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("TicketDetailTestData"),
				"TicketDetailPermission");
		return testObjArray;

	}

	@Test(dataProvider = "TicketDetailPrintButtonVerify", priority = 1)
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
		TicketPage.enterIntoTextBox("FilterByPermission", map.get("Permission4"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.click("GridHighLightRecord");

		if (TicketPage.getTitle("AssignedData").equals(map.get("AssignedDataYes"))) {
			TicketPage.explicitWaitForElementToBeClickable("RevokeSelectedButton");
			TicketPage.click("RevokeSelectedButton");
			TicketPage.click("SaveButton");
			TicketPage.click("YesButton");
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
			TicketPage.explicitWaitForElementToBeClickable("FilterByPermission");
			TicketPage.click("FilterByPermission");
			TicketPage.enterIntoTextBox("FilterByPermission", map.get("Permission4"));
			TicketPage.explicitWaitForElementToBeClickable("AssignedData");
			System.out.println("Revoke Permission:" + TicketPage.getTitle("AssignedData"));
		}
		HomePage.click("Backlink");
		TicketPage.RefreshWebPage();

		HomePage.click("Navigation");
		HomePage.click("InventoryManagement");
		HomePage.explicitWaitForElementToBeClickable("Ticket");
		HomePage.click("Ticket");
		TicketPage.verifyTextValue("TicketListTitle", map.get("TicketList"));
		TicketPage.explicitWaitForElementToBeClickable("SearchButton");
		TicketPage.click("SearchButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));

		// Verify print button is not present
		TicketPage.checkElementIsNotPresent("PrintButton");
		TicketPage.explicitWaitForElementToBeClickable("ItemIdLink");
		TicketPage.click("ItemIdLink");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.verifyTextValue("TicketDetailScreenTitle", map.get("TicketDetail"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.checkElementIsNotPresent("PrintButton");
		TicketPage.click("BackButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));

		HomePage.explicitWaitForElementToBeClickable("Backlink");
		HomePage.click("Backlink");
		HomePage.click("Security");
		HomePage.click("RoleMaintenancePage");
		TicketPage.RefreshWebPage();
		TicketPage.explicitWaitForElementToBeClickable("RoleNameLink");
		TicketPage.click("FilterByRoleName");
		TicketPage.enterIntoTextBox("FilterByRoleName", map.get("RoleName"));
		TicketPage.explicitWaitForElementToBeClickable("RoleNameLink");
		TicketPage.click("RoleNameLink");
		TicketPage.explicitWaitForElementToBeClickable("FilterByPermission");
		TicketPage.explicitWaitForElementToBeClickable("AssignedData");
		TicketPage.explicitWaitForElementToBeClickable("FilterByPermission");

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
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.click("FilterByPermission");
		TicketPage.enterIntoTextBox("FilterByPermission", map.get("Permission4"));
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
			TicketPage.enterIntoTextBox("FilterByPermission", map.get("Permission4"));
			TicketPage.explicitWaitForElementToBeClickable("AssignedData");
			System.out.println("Assign Permission:" + TicketPage.getTitle("AssignedData"));
		}
		TicketPage.RefreshWebPage();
		HomePage.click("Navigation");
		HomePage.click("InventoryManagement");
		HomePage.explicitWaitForElementToBeClickable("Ticket");
		HomePage.click("Ticket");
		TicketPage.verifyTextValue("TicketListTitle", map.get("TicketList"));
		TicketPage.explicitWaitForElementToBeClickable("SearchButton");
		TicketPage.click("SearchButton");
		
		//Create a new ticket and save it 
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.explicitWaitForElementToBeClickable("CreateButton");
		TicketPage.click("CreateButton");
		TicketPage.verifyTextValue("TicketDetailScreenTitle", map.get("TicketDetail"));
		TicketPage.enterIntoTextBox("ItemScanBox", map.get("Item1"));
		TicketPage.click("ScanBoxArrow");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.searchDropDownValue("FormatTypeDropDown", map.get("FormatType"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.searchDropDownValue("FormatTypeDropDownList", map.get("Format"));
		String strDate = TicketPage.getPresentDate();
		TicketPage.selectDateFromDatePicker("PrintDateTextBox", strDate);
		int randomNumber = random.nextInt(123);
		String randQuantity = Integer.toString(randomNumber);
		TicketPage.enterIntoTextBox("PrintQuantityTextBox", randQuantity);
		
		// Click on Print Button, verify pop-up
		TicketPage.verifyElementIsPresent("PrintButton");
		TicketPage.click("PrintButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.verifyTextValue("PrintPopUpTitle", map.get("PrintPopUpTitle"));
		TicketPage.searchDropDownValue("PrintDropdown", map.get("PrinterDropdown"));
		TicketPage.explicitWaitForElementToBeClickable("PrinterSelectionApplyButton");
		TicketPage.click("PrinterSelectionApplyButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.verifyTextValue("PrintInfo", map.get("PrintInfo"));
		TicketPage.explicitWaitForElementToBeClickable("OkButton");
		TicketPage.click("OkButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.verifyTextValue("TicketListTitle", map.get("TicketList"));
		
		//Filter by already printed tickets and verify record is present
		TicketPage.explicitWaitForElementToBeClickable("SearchIcon");
		TicketPage.click("SearchIcon");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.explicitWaitForElementToBeClickable("FromPrintDateTextBox");
		TicketPage.enterIntoTextBox("FromPrintDateTextBox", strDate);
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.explicitWaitForElementToBeClickable("PrintedDropDown");
		TicketPage.searchDropDownValue("PrintedDropDown", map.get("PrintedDropdown"));
		TicketPage.explicitWaitForElementToBeClickable("SearchButton");
		TicketPage.click("SearchButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.click("FilterByQuantity");
		TicketPage.enterIntoTextBox("FilterByQuantity",randQuantity );
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.verifyElementIsPresent("GridHighLightRecord");
				

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
