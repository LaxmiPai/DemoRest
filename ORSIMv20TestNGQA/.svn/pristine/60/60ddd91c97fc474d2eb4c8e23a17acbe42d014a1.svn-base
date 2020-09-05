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

public class TicketDetailItemQuantity {
	
	public static Logger logger = Logger.getLogger(TicketDetailItemQuantity .class.getName());
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

	@Test(dataProvider = "TicketPermissionVerify")
	public void ticketDetailItemFieldVerify(Map<String, String> map) throws Exception {

		logger.info("Method Name:TicketDetailItemFieldVerify ");
		HomePage.click("Navigation");
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
		TicketPage.RefreshWebPage();
		HomePage.click("Navigation");
		HomePage.click("InventoryManagement");
		HomePage.explicitWaitForElementToBeClickable("Ticket");
		HomePage.click("Ticket");
		TicketPage.verifyTextValue("TicketListTitle", map.get("TicketList"));
		TicketPage.explicitWaitForElementToBeClickable("SearchCriteriaCloseButton");
		TicketPage.click("SearchCriteriaCloseButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		
		TicketPage.explicitWaitForElementToBeClickable("CreateButton");
		TicketPage.click("CreateButton");
		TicketPage.verifyTextValue("TicketDetailScreenTitle", map.get("TicketDetail"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.enterIntoTextBox("ItemScanBox", map.get("Item1"));
		TicketPage.click("ScanBoxArrow");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
		
		//Verify Quantity field is editable
		TicketPage.verifyElementIsEditable("ItemQuantityTextBox");
		int randomNumber= random.nextInt(123);
		String randQuantity = Integer.toString(randomNumber);
		TicketPage.enterIntoTextBox("ItemQuantityTextBox",randQuantity);
		
		//Enter other mandatory fields
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
		
		//Verify the item quantity value after editing
		String itemQuantity = TicketPage.getAttributeValue("ItemQuantityTextBox", "aria-valuenow");
		TicketPage.verifyValuesAreEqual(itemQuantity, randQuantity);
		
		//Delete the ticket to avoid excess data in UI
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.explicitWaitForElementToBeClickable("DeleteButton");
		TicketPage.click("DeleteButton");
		TicketPage.explicitWaitForElementToBeClickable("YesButton");
		TicketPage.click("YesButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.verifyTextValue("TicketListTitle", map.get("TicketList"));
		
		//Verify Item quantity not editable for already printed tickets
		TicketPage.explicitWaitForElementToBeClickable("SearchIcon");
		TicketPage.click("SearchIcon");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.explicitWaitForElementToBeClickable("FromPrintDateTextBox");
		TicketPage.clearElement("FromPrintDateTextBox");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.explicitWaitForElementToBeClickable("PrintedDropDown");
		TicketPage.searchDropDownValue("PrintedDropDown", map.get("PrintedDropdown"));
		TicketPage.explicitWaitForElementToBeClickable("SearchButton");
		TicketPage.click("SearchButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.explicitWaitForElementToBeClickable("ItemIdLink");
		TicketPage.click("ItemIdLink");
		TicketPage.verifyTextValue("TicketDetailScreenTitle", map.get("TicketDetail"));
		TicketPage.verifyElementIsNotPresent("ItemQuantityTextBox");
		

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
