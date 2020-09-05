/**
 * 
 */
package com.oracle.sim.testcases.Ticket;

import java.util.Map;
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
public class TicketDetailUiAndNavigation {

	public static Logger logger = Logger.getLogger(TicketDetailUiAndNavigation.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
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
	public void TicketPermissionVerify(Map<String, String> map) throws Exception {
		HomePage.explicitWaitForVisibility("Titlelogo");
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

		// Assign the Data Value
		TicketPage.click("FilterByPermission");
		TicketPage.enterIntoTextBox("FilterByPermission", map.get("Permission1"));
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
			TicketPage.enterIntoTextBox("FilterByPermission", map.get("Permission1"));
			TicketPage.explicitWaitForElementToBeClickable("AssignedData");
			System.out.println("Revoke Permission:" + TicketPage.getTitle("AssignedData"));
		}
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.click("FilterByPermission");
		TicketPage.enterIntoTextBox("FilterByPermission", map.get("Permission2"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.click("GridHighLightRecord");

		if (TicketPage.getTitle("AssignedData").equals(map.get("AssignedDataYes"))) {
			TicketPage.explicitWaitForElementToBeClickable("RevokeSelectedButton");
			TicketPage.click("RevokeSelectedButton");
			TicketPage.click("SaveButton");
			TicketPage.click("YesButton");
			// wait for DB commit to perform
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
			TicketPage.explicitWaitForElementToBeClickable("FilterByPermission");
			TicketPage.click("FilterByPermission");
			TicketPage.enterIntoTextBox("FilterByPermission", map.get("Permission2"));
			TicketPage.explicitWaitForElementToBeClickable("AssignedData");
			System.out.println("Revoke Permission:" + TicketPage.getTitle("AssignedData"));
		}
		TicketPage.click("BackLink");
		TicketPage.RefreshWebPage();

		HomePage.click("Navigation");
		HomePage.click("InventoryManagement");

		//TicketPage.VerifyRevokePermissionPage(map.get("PageName"));
        TicketPage.VerifyPageMenuIsNotPresent(map.get("PageName"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));

		TicketPage.explicitWaitForElementToBeClickable("FilterByPermission");

		// Assign the Data Value
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.click("FilterByPermission");
		TicketPage.enterIntoTextBox("FilterByPermission", map.get("Permission1"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.click("GridHighLightRecord");
		// Assign the permission for the user
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
		// Assign the permission for the user
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
		// Refresh the Web-Page..
		TicketPage.RefreshWebPage();
		// Navigate to Item Basket page...
		HomePage.click("Navigation");
		HomePage.click("InventoryManagement");
		HomePage.explicitWaitForElementToBeClickable("Ticket");
		HomePage.click("Ticket");
		// Verify the Title of the Screen..
		TicketPage.verifyTextValue("TicketSearchCriteriaTitle", map.get("Title"));

	}

	@DataProvider(name = "TicketDetailUiandNavigation")
	public Object[][] getUiAndNavigation() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("TicketDetailTestData"),
				"UiAndNavigation");
		return testObjArray;
	}

	@Test(dataProvider = "TicketDetailUiandNavigation", priority = 2)
	public void verifyTicketDetailUiandNavigation(Map<String, String> map) throws Exception {

		logger.info("Method Name: verifyTicketDetailUiandNavigation");
       TicketPage.explicitWaitForElementToBeClickable("SearchCriteriaCloseButton");
		TicketPage.click("SearchCriteriaCloseButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.verifyTextValue("TicketListTitle", map.get("TicketList"));
		TicketPage.explicitWaitForElementToBeClickable("CreateButton");
		TicketPage.click("CreateButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		TicketPage.verifyTextValue("TicketDetailScreenTitle", map.get("TicketDetail"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		// Verify buttons not present in the detail screen
		TicketPage.checkElementIsNotPresent("SaveButton");
		TicketPage.checkElementIsNotPresent("DeleteButton");
		TicketPage.verifyElementIsPresent("BackButton");
		TicketPage.verifyElementIsDisabled("PrintButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
		TicketPage.enterIntoTextBox("ItemScanBox", map.get("Item"));
		TicketPage.click("ScanBoxArrow");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));

		// Verify buttons present in the detail screen
		TicketPage.verifyElementIsEnabled("SaveButton");
		TicketPage.verifyElementIsEnabled("PrintButton");
		TicketPage.verifyElementIsDisabled("DeleteButton");

		// Verify fields displayed on detail screen
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
		TicketPage.verifyTextValue("ItemLabel", map.get("ItemLabel"));
		TicketPage.verifyTextValue("DescriptionLabel", map.get("Description"));
		TicketPage.verifyTextValue("PriceTypeLabel", map.get("PriceType"));
		TicketPage.verifyTextValue("OriginTypeLabel", map.get("OriginType"));
		TicketPage.verifyTextValue("PrintQuantityLabel", map.get("PrintQuantity"));
		TicketPage.verifyTextValue("CurrentPriceLabel", map.get("CurrentPrice"));
		TicketPage.verifyTextValue("CountryOfManufactureLabel", map.get("CountryOfManufacture"));
		TicketPage.verifyTextValue("PrintDefaultLabel", map.get("PrintDefault"));
		TicketPage.verifyTextValue("ItemQuantityLabel", map.get("ItemQuantity"));
		TicketPage.verifyTextValue("OverridePriceLabel", map.get("OverridePrice"));
		TicketPage.verifyTextValue("UserLabel", map.get("User"));
		TicketPage.verifyTextValue("FormatTypeLabel", map.get("FormatType"));
		TicketPage.verifyTextValue("FormatLabel", map.get("Format"));
		TicketPage.verifyTextValue("CreateDateLabel", map.get("CreateDate"));
		TicketPage.verifyTextValue("PrintDateLabel", map.get("PrintDate"));
		TicketPage.verifyTextValue("PrintedDateLabel", map.get("PrintedDate"));
		TicketPage.verifyTextValue("PrintedLabel", map.get("Printed"));
		TicketPage.verifyTextValue("ActiveDateLabel", map.get("ActiveDate"));
		TicketPage.verifyTextValue("EndDateLabel", map.get("EndDate"));
		

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
