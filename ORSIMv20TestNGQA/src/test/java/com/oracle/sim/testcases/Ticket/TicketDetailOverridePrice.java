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

public class TicketDetailOverridePrice {

	public static Logger logger = Logger.getLogger(TicketDetailOverridePrice.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	Random random = new Random();
	PageFactory pagefactory = new PageFactory();

	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage RoleMaintenancePage;
	SimBasePage TicketPage;
	SimBasePage ItemLookupPage;

	@BeforeClass
	public void setup(ITestContext context) throws Exception {

		logger.info("TestCase Name: " + logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
		LoginPage = pagefactory.initialize("LoginPage");
		HomePage = pagefactory.initialize("HomePage");
		RoleMaintenancePage = pagefactory.initialize("RoleMaintenancePage");
		TicketPage = pagefactory.initialize("TicketPage");
		ItemLookupPage = pagefactory.initialize("ItemLookupPage");
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username", LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password", LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");
		HomePage.verifyTextValue("Title", HomePage.getProperty("Title"));
		HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
		HomePage.storeIdCheck();

	}

	@DataProvider(name = "TicketOverridePricePermissionVerify")
	public Object[][] getTestDataForPermission() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("TicketDetailTestData"),
				"TicketDetailPermission");
		return testObjArray;

	}

	@Test(dataProvider = "TicketOverridePricePermissionVerify")
	public void ticketOverridePriceVerify(Map<String, String> map) throws Exception {

		logger.info("Method Name:TicketOverridePriceVerify");
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
		TicketPage.explicitWaitForElementToBeClickable("FilterByPermission");

		// Assigning permission
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
		TicketPage.enterIntoTextBox("FilterByPermission", map.get("Permission2"));
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
			TicketPage.enterIntoTextBox("FilterByPermission", map.get("Permission2"));
			TicketPage.explicitWaitForElementToBeClickable("AssignedData");
			System.out.println("Assign Permission:" + TicketPage.getTitle("AssignedData"));
		}
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.click("FilterByPermission");
		TicketPage.enterIntoTextBox("FilterByPermission", map.get("Permission6"));
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
			TicketPage.enterIntoTextBox("FilterByPermission", map.get("Permission6"));
			TicketPage.explicitWaitForElementToBeClickable("AssignedData");
			System.out.println("Assign Permission:" + TicketPage.getTitle("AssignedData"));
		}
		TicketPage.RefreshWebPage();
		HomePage.click("Navigation");

		// Navigate to lookup to fetch Item price value
		HomePage.click("Lookups");
		HomePage.click("ItemLookup");
		ItemLookupPage.explicitWaitForElementToBeClickable("ItemTextBox");
		ItemLookupPage.enterIntoTextBox("ItemTextBox", map.get("Item1"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		ItemLookupPage.explicitWaitForElementToBeClickable("SearchIcon");
		ItemLookupPage.click("SearchIcon");
		ItemLookupPage.click("SearchIcon");
		// Capture the Item price value
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		String itemPrice1 = ItemLookupPage.getAttributeValue("ItemPriceValue", "value");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		ItemLookupPage.explicitWaitForElementToBeClickable("BackButton");
		HomePage.click("Backlink");
		HomePage.click("InventoryManagement");

		// Navigate to Ticket screen
		HomePage.explicitWaitForElementToBeClickable("Ticket");
		HomePage.click("Ticket");
		TicketPage.verifyTextValue("TicketListTitle", map.get("TicketList"));
		TicketPage.explicitWaitForElementToBeClickable("SearchButton");
		TicketPage.click("SearchButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.explicitWaitForElementToBeClickable("CreateButton");
		TicketPage.click("CreateButton");
		TicketPage.verifyTextValue("TicketDetailScreenTitle", map.get("TicketDetail"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.enterIntoTextBox("ItemScanBox", map.get("Item1"));
		TicketPage.click("ScanBoxArrow");

		// Verify Override Text box field is editable
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
		TicketPage.verifyElementIsEditable("OverridePriceTextBox");
		String priceOverride1 = TicketPage.getAttributeValue("OverridePriceTextBox", "aria-valuetext");
		System.out.println(priceOverride1);

		// Compare the price in Item lookup to the price in ticket detail screen

		TicketPage.verifyValuesAreEqual(itemPrice1, priceOverride1);
		int randomNumber1 = random.nextInt(123);
		String randPrice = Integer.toString(randomNumber1);
		TicketPage.enterIntoTextBox("OverridePriceTextBox", randPrice);
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.searchDropDownValue("FormatTypeDropDown", map.get("FormatType"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.searchDropDownValue("FormatTypeDropDownList", map.get("Format"));
		String strDate = TicketPage.getPresentDate();
		TicketPage.selectDateFromDatePicker("PrintDateTextBox", strDate);
		int randomNumber2 = random.nextInt(123);
		String randPrintQuantity = Integer.toString(randomNumber2);
		TicketPage.enterIntoTextBox("PrintQuantityTextBox", randPrintQuantity);
		TicketPage.click("SaveButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.explicitWaitForElementToBeClickable("YesButton");
		TicketPage.click("YesButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.click("SearchIcon");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.enterIntoTextBox("FromPrintDateTextBox", strDate);
		TicketPage.click("SearchButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.click("FilterByQuantity");
		TicketPage.enterIntoTextBox("FilterByQuantity", randPrintQuantity);
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.explicitWaitForElementToBeClickable("GridHighLightRecord");
		TicketPage.click("GridHighLightRecord");
		TicketPage.explicitWaitForElementToBeClickable("ItemIdLink");
		TicketPage.click("ItemIdLink");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
		String priceOverride2 = TicketPage.getAttributeValue("OverridePriceTextBox", "aria-valuenow");
		System.out.println(priceOverride2);
		TicketPage.verifyValuesAreEqual(priceOverride2, randPrice);

		// Delete the ticket to avoid excess data in UI
		TicketPage.explicitWaitForElementToBeClickable("DeleteButton");
		TicketPage.click("DeleteButton");
		TicketPage.explicitWaitForElementToBeClickable("YesButton");
		TicketPage.click("YesButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.verifyTextValue("TicketListTitle", map.get("TicketList"));

		// Revoking permission
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		HomePage.click("Backlink");
		HomePage.click("Security");
		HomePage.click("RoleMaintenancePage");
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
		TicketPage.enterIntoTextBox("FilterByPermission", map.get("Permission6"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.click("GridHighLightRecord");
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
			TicketPage.enterIntoTextBox("FilterByPermission", map.get("Permission6"));
			TicketPage.explicitWaitForElementToBeClickable("AssignedData");
			System.out.println("Revoke Permission:" + TicketPage.getTitle("AssignedData"));
		}
		TicketPage.RefreshWebPage();
		HomePage.click("Navigation");
		HomePage.click("InventoryManagement");
		HomePage.explicitWaitForElementToBeClickable("Ticket");
		HomePage.click("Ticket");
		TicketPage.verifyTextValue("TicketListTitle", map.get("TicketList"));
		TicketPage.explicitWaitForElementToBeClickable("SearchButton");
		TicketPage.click("SearchButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.explicitWaitForElementToBeClickable("CreateButton");
		TicketPage.click("CreateButton");
		TicketPage.enterIntoTextBox("ItemScanBox", map.get("Item1"));
		TicketPage.click("ScanBoxArrow");

		// Verify Override Text box field is not present
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.verifyElementIsNotPresent("OverridePriceTextBox");
		String priceOverride3 = TicketPage.getAttributeValue("OverridePriceTextBox", "aria-valuetext");
		System.out.println(priceOverride3);
		TicketPage.verifyValuesAreEqual(itemPrice1, priceOverride3);
		TicketPage.explicitWaitForElementToBeClickable("BackButton");
		TicketPage.click("BackButton");
		TicketPage.explicitWaitForElementToBeClickable("OkButton");
		TicketPage.click("OkButton");

		// Assigning the permission back
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		HomePage.click("Backlink");
		HomePage.click("Security");
		HomePage.click("RoleMaintenancePage");
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
		TicketPage.enterIntoTextBox("FilterByPermission", map.get("Permission6"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.click("GridHighLightRecord");
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
			TicketPage.enterIntoTextBox("FilterByPermission", map.get("Permission6"));
			TicketPage.explicitWaitForElementToBeClickable("AssignedData");
			System.out.println("Assign permission:" + TicketPage.getTitle("AssignedData"));
		}
		TicketPage.RefreshWebPage();
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
