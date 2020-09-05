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

public class TicketDetailPrintQuantityField {

	public static Logger logger = Logger.getLogger(TicketDetailPrintQuantityField.class.getName());
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
	public Object[][] getTestDataForFormatFieldPermission() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("TicketDetailTestData"),
				"TicketDetailPermission");
		return testObjArray;

	}

	@Test(dataProvider = "TicketPermissionVerify", priority = 1)
	public void ticketPermissionVerify(Map<String, String> map) throws Exception {

		logger.info("Method Name:TicketPermissionVerify ");
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
	}

	@DataProvider(name = "TicketDetailSaveAndDetail")
	public Object[][] getDataForFormatField() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("TicketDetailTestData"),
				"SaveAndDelete");
		return testObjArray;
	}

	@Test(dataProvider = "TicketDetailSaveAndDetail", priority = 2)
	public void verifyTicketDetailPrintQuantity(Map<String, String> map) throws Exception {

		logger.info("Method Name:TicketDetailPrintQuantity");
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

		// Verify Printed field set to 'NO'
		String printStatusNo = TicketPage.getAttributeValue("PrintedStatusValue", "value");
		TicketPage.verifyValuesAreEqual(printStatusNo, map.get("Printed"));
		TicketPage.verifyElementIsEditable("PrintQuantityTextBox");

		// Verify Print Quantity default is 1.
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		String printQuantity = TicketPage.getAttributeValue("PrintQuantityTextBox", "aria-valuenow");
		System.out.println(printQuantity);
		TicketPage.verifyValuesAreEqual(printQuantity, map.get("PrintQuantityDefault"));

		// Remove the default value to verify the error msg.
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.searchDropDownValue("FormatTypeDropDown", map.get("FormatType"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.searchDropDownValue("FormatTypeDropDownList", map.get("Format"));
		String strDate = TicketPage.getPresentDate();
		TicketPage.selectDateFromDatePicker("PrintDateTextBox", strDate);
		TicketPage.clearElement("PrintQuantityTextBox");
		TicketPage.explicitWaitForElementToBeClickable("SaveButton");
		TicketPage.click("SaveButton");
		TicketPage.verifyTextValue("ErrorMessage4", map.get("ErrorMsg"));

		// Enter any value in print quantity field.
		int randomNumber = random.nextInt(123);
		String randQuantity = Integer.toString(randomNumber);
		TicketPage.enterIntoTextBox("PrintQuantityTextBox", randQuantity);
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
		TicketPage.enterIntoTextBox("FilterByQuantity", randQuantity);
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.explicitWaitForElementToBeClickable("GridHighLightRecord");
		TicketPage.click("GridHighLightRecord");
		TicketPage.explicitWaitForElementToBeClickable("ItemIdLink");
		TicketPage.click("ItemIdLink");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));

		//Verify the values are getting reflected after Save
		String printQuantity2 = TicketPage.getAttributeValue("PrintQuantityTextBox", "aria-valuenow");
		System.out.println(printQuantity);
		TicketPage.verifyValuesAreEqual(printQuantity2,randQuantity );
     
		// Verify the Print default quantity button
		TicketPage.verifyTextValue("PrintDefaultQuantityNo", map.get("PrintQuantityNo"));
		TicketPage.verifyElementIsEditable("PrintQuantityTextBox");
		TicketPage.explicitWaitForElementToBeClickable("PrintDefaultQuantitybutton");
		TicketPage.click("PrintDefaultQuantitybutton");
		TicketPage.verifyTextValue("PrintDefaultQuantityYes", map.get("PrintQuantityYes"));
		TicketPage.verifyElementIsDisabled("PrintQuantityTextBox");

		// Delete the created ticket
		TicketPage.explicitWaitForElementToBeClickable("DeleteButton");
		TicketPage.click("DeleteButton");
		TicketPage.explicitWaitForElementToBeClickable("YesButton");
		TicketPage.click("YesButton");
		TicketPage.explicitWaitForElementToBeClickable("OkButton");
		TicketPage.click("OkButton");
		TicketPage.verifyTextValue("TicketListTitle", map.get("TicketList"));

		// Verify print quantity is disabled for already printed tickets
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.explicitWaitForElementToBeClickable("SearchIcon");
		TicketPage.click("SearchIcon");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.clearElement("FromPrintDateTextBox");
		TicketPage.searchDropDownValue("PrintedDropDown", map.get("PrintedStatus"));
		TicketPage.explicitWaitForElementToBeClickable("SearchButton");
		TicketPage.click("SearchButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.explicitWaitForElementToBeClickable("ItemIdLink");
		TicketPage.click("ItemIdLink");
		TicketPage.verifyTextValue("TicketDetailScreenTitle", map.get("TicketDetail"));
		String printStatusYes = TicketPage.getAttributeValue("PrintedStatusValue", "value");
		TicketPage.verifyValuesAreEqual(printStatusYes, map.get("PrintedStatus"));
		TicketPage.verifyElementIsDisabled("PrintQuantityTextBox");

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
