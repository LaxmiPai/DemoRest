
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

/**
 * @author lapai
 *
 */
public class TicketDetailEditTicketPermission {

	public static Logger logger = Logger.getLogger(TicketDetailEditTicketPermission.class.getName());
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
	public void ticketEditPermissionVerify(Map<String, String> map) throws Exception {

		logger.info("Method Name:TicketDetailEditPermission");
		HomePage.explicitWaitForElementToBeClickable("Navigation");
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
		TicketPage.click("FilterByPermission");
		TicketPage.enterIntoTextBox("FilterByPermission", map.get("Permission5"));
		TicketPage.click("GridHighLightRecord");
		TicketPage.explicitWaitForElementToBeClickable("AssignedData");
		if (TicketPage.getTitle("AssignedData").equals(map.get("AssignedDataYes"))) {
			TicketPage.explicitWaitForElementToBeClickable("RevokeSelectedButton");
			TicketPage.click("RevokeSelectedButton");
			TicketPage.click("SaveButton");
			TicketPage.click("YesButton");
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
			TicketPage.explicitWaitForElementToBeClickable("FilterByPermission");
			TicketPage.click("FilterByPermission");
			TicketPage.enterIntoTextBox("FilterByPermission", map.get("Permission5"));
			TicketPage.explicitWaitForElementToBeClickable("AssignedData");
			System.out.println("Revoke Permission:" + TicketPage.getTitle("AssignedData"));
		}

		TicketPage.RefreshWebPage();
		HomePage.click("Navigation");
		HomePage.click("InventoryManagement");
		HomePage.click("InventoryAdjustment");
		HomePage.explicitWaitForElementToBeClickable("Ticket");
		HomePage.click("Ticket");
		TicketPage.verifyTextValue("TicketListTitle", map.get("TicketList"));
		TicketPage.explicitWaitForElementToBeClickable("SearchButton");
		TicketPage.click("SearchButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.explicitWaitForElementToBeClickable("ItemIdLink");
		TicketPage.click("ItemIdLink");

		// On removing Edit Ticket Permission, user should not be able to edit
		// fields
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.verifyTextValue("TicketDetailScreenTitle", map.get("TicketDetail"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		String itemQuantity1 = TicketPage.getAttributeValue("ItemQuantityText", "value");
		int randomNumber = random.nextInt(123);
		String randQuantity = Integer.toString(randomNumber);
		TicketPage.enterIntoTextBox("ItemQuantityTextBox", randQuantity);
		TicketPage.explicitWaitForElementToBeClickable("SaveButton");
		TicketPage.click("SaveButton");
		TicketPage.explicitWaitForElementToBeClickable("YesButton");
		TicketPage.click("YesButton");
		TicketPage.verifyTextValue("EditErrorMessage", map.get("EditErrorMsg"));
		TicketPage.explicitWaitForElementToBeClickable("OkButton");
		TicketPage.click("OkButton");
		TicketPage.explicitWaitForElementToBeClickable("BackButton");
		TicketPage.click("BackButton");
		TicketPage.explicitWaitForElementToBeClickable("OkButton");
		TicketPage.click("OkButton");
		TicketPage.verifyTextValue("TicketListTitle", map.get("TicketList"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.click("ItemIdLink");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.verifyTextValue("TicketDetailScreenTitle", map.get("TicketDetail"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		String itemQuantity2 = TicketPage.getAttributeValue("ItemQuantityText", "value");
		TicketPage.verifyValuesAreEqual(itemQuantity1, itemQuantity2);

		HomePage.explicitWaitForElementToBeClickable("Backlink");
		HomePage.click("Backlink");
		HomePage.click("Security");
		HomePage.click("RoleMaintenancePage");
		TicketPage.explicitWaitForElementToBeClickable("RoleNameLink");
		TicketPage.click("FilterByRoleName");
		TicketPage.enterIntoTextBox("FilterByRoleName", map.get("RoleName"));
		TicketPage.explicitWaitForElementToBeClickable("RoleNameLink");
		TicketPage.click("RoleNameLink");
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
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.click("FilterByPermission");
		TicketPage.enterIntoTextBox("FilterByPermission", map.get("Permission5"));
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
			TicketPage.enterIntoTextBox("FilterByPermission", map.get("Permission5"));
			TicketPage.explicitWaitForElementToBeClickable("AssignedData");
			System.out.println("Assign Permission:" + TicketPage.getTitle("AssignedData"));
		}
		TicketPage.RefreshWebPage();
		HomePage.click("Navigation");
		HomePage.click("InventoryManagement");
		HomePage.click("InventoryAdjustment");
		HomePage.explicitWaitForElementToBeClickable("Ticket");
		HomePage.click("Ticket");
		TicketPage.verifyTextValue("TicketListTitle", map.get("TicketList"));
		TicketPage.explicitWaitForElementToBeClickable("SearchButton");
		TicketPage.click("SearchButton");
		
		/*TicketPage.RefreshWebPage();
		TicketPage.explicitWaitForElementToBeClickable("SearchButton");
		TicketPage.click("SearchButton");
		TicketPage.explicitWaitForElementToBeClickable("RefreshButton");
		TicketPage.click("RefreshButton");*/
	}

	@DataProvider(name = "TicketDetailEdit")
	public Object[][] getDataForSaveAndDelete() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("TicketDetailTestData"),
				"Edit");
		return testObjArray;
	}

	@Test(dataProvider = "TicketDetailEdit", priority = 2)
	public void verifyTicketDetailEdit(Map<String, String> map) throws Exception {

		logger.info("Method Name:TicketDetailEdit");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.explicitWaitForElementToBeClickable("ItemIdLink");
		TicketPage.click("ItemIdLink");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
		String printStatus = TicketPage.getAttributeValue("PrintedStatusValue", "value");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.verifyValuesAreEqual(printStatus, map.get("Printed"));
		TicketPage.verifyTextValue("TicketDetailScreenTitle", map.get("TicketDetail"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		
		//Verify the editable fields
		int randomNumber2 = random.nextInt(123);
		String randQuantity2 = Integer.toString(randomNumber2);
		TicketPage.enterIntoTextBox("ItemQuantityTextBox", randQuantity2);
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.searchDropDownValue("FormatTypeDropDown", map.get("FormatType"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.searchDropDownValue("FormatTypeDropDownList", map.get("Format"));
		String strDate = TicketPage.getPresentDate();
		TicketPage.selectDateFromDatePicker("PrintDateTextBox", strDate);
		TicketPage.enterIntoTextBox("OverridePriceTextBox", map.get("OverridePrice"));
		TicketPage.explicitWaitForElementToBeClickable("SaveButton");
		TicketPage.click("SaveButton");
		TicketPage.explicitWaitForElementToBeClickable("YesButton");
		TicketPage.click("YesButton");
		TicketPage.verifyTextValue("TicketListTitle", map.get("TicketList"));
		
		
		//Verify values after saving
		TicketPage.explicitWaitForElementToBeClickable("ItemIdLink");
		TicketPage.click("ItemIdLink");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		String  itemQuantity = TicketPage.getAttributeValue("ItemQuantityTextBox", "value");
		String  overridePrice = TicketPage.getAttributeValue("OverridePriceTextBox", "aria-valuenow");
		String  formatTypeText = TicketPage.getText("FormatTypeTextBox");
		String  formatText = TicketPage.getText("FormatTextBox");
		TicketPage.verifyValuesAreEqual(itemQuantity, randQuantity2);
		TicketPage.verifyValuesAreEqual( overridePrice, map.get("OverridePrice"));
		TicketPage.verifyValuesAreEqual(formatTypeText, map.get("FormatType"));
		TicketPage.verifyValuesAreEqual(formatText, map.get("Format"));
		
		TicketPage.explicitWaitForElementToBeClickable("DeleteButton");
		TicketPage.click("DeleteButton");
		TicketPage.explicitWaitForElementToBeClickable("YesButton");
		TicketPage.click("YesButton");
		
		
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
