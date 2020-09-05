/**
 * @author lapai
 *
 */
package com.oracle.sim.testcases.ItemBasket;

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


public class ItemBasketCopy {

	public static Logger logger = Logger.getLogger(ItemBasketCopy.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pageFactory = new PageFactory();

	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage ItemBasketPage;

	@BeforeClass
	public void setUp(ITestContext context) throws Exception {
		logger.info("TestCase Name: " + logger.getName());
		// logger.info("Before Class");
		SIMWebdriverBase.loadInitialURL(context);
		// Login Steps

		LoginPage = pageFactory.initialize("LoginPage");
		ItemBasketPage = pageFactory.initialize("ItemBasketPage");
		HomePage = pageFactory.initialize("HomePage");
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username", LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password", LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");
		HomePage.verifyTextValue("Title", HomePage.getProperty("Title"));
		HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
		HomePage.storeIdCheck();

	}

	@DataProvider(name = "ItemBasketPermissionVerify")
	public Object[][] getTestDataForItemBasketPermission() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("ItemBasketTestData"),
				"ItemBasketPermission");
		return testObjArray;

	}

	@Test(dataProvider = "ItemBasketPermissionVerify", priority = 1)
	public void ItemBasketPermissionVerify(Map<String, String> map) throws Exception {
		HomePage.explicitWaitForVisibility("Titlelogo");
		HomePage.click("Navigation");
		HomePage.click("Security");
		HomePage.click("RoleMaintenancePage");
		ItemBasketPage.explicitWaitForElementToBeClickable("RoleNameLink");
		ItemBasketPage.click("FilterByRoleName");
		ItemBasketPage.enterIntoTextBox("FilterByRoleName", map.get("RoleName"));
		ItemBasketPage.explicitWaitForElementToBeClickable("RoleNameLink");
		ItemBasketPage.click("RoleNameLink");
		ItemBasketPage.explicitWaitForElementToBeClickable("FilterByPermission");
		ItemBasketPage.explicitWaitForElementToBeClickable("AssignedData");

		// Assign the Data Value
		ItemBasketPage.click("FilterByPermission");
		ItemBasketPage.enterIntoTextBox("FilterByPermission", map.get("Permission1"));
		ItemBasketPage.click("GridHighLightRecord");

		// Remove the permission for the user
		ItemBasketPage.explicitWaitForElementToBeClickable("AssignedData");
		if (ItemBasketPage.getTitle("AssignedData").equals(map.get("AssignedDataYes"))) {
			ItemBasketPage.explicitWaitForElementToBeClickable("RevokeSelectedButton");
			ItemBasketPage.click("RevokeSelectedButton");
			ItemBasketPage.click("SaveButton");
			ItemBasketPage.click("YesButton");
			// wait for DB commit to perform
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
			ItemBasketPage.explicitWaitForElementToBeClickable("FilterByPermission");
			ItemBasketPage.click("FilterByPermission");
			ItemBasketPage.enterIntoTextBox("FilterByPermission", map.get("Permission1"));
			ItemBasketPage.explicitWaitForElementToBeClickable("AssignedData");
			System.out.println("Revoke Permission:" + ItemBasketPage.getTitle("AssignedData"));
		}

		ItemBasketPage.click("BackLink");
		ItemBasketPage.RefreshWebPage();

		HomePage.click("Navigation");
		HomePage.click("InventoryManagement");

		ItemBasketPage.VerifyRevokePermissionPage(map.get("PageName"));

		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));

		ItemBasketPage.explicitWaitForElementToBeClickable("FilterByPermission");
		ItemBasketPage.explicitWaitForElementToBeClickable("AssignedData");

		// Assign the Data Value
		ItemBasketPage.click("FilterByPermission");
		ItemBasketPage.enterIntoTextBox("FilterByPermission", map.get("Permission1"));
		ItemBasketPage.click("GridHighLightRecord");
		// Assign the permission for the user
		ItemBasketPage.explicitWaitForElementToBeClickable("AssignedData");
		if (ItemBasketPage.getTitle("AssignedData").equals(map.get("AssignedDataNo"))) {
			ItemBasketPage.explicitWaitForElementToBeClickable("AssigneSelectedButton");
			ItemBasketPage.click("AssigneSelectedButton");
			ItemBasketPage.click("SaveButton");
			ItemBasketPage.click("YesButton");

			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
			ItemBasketPage.explicitWaitForElementToBeClickable("FilterByPermission");
			ItemBasketPage.click("FilterByPermission");
			ItemBasketPage.enterIntoTextBox("FilterByPermission", map.get("Permission1"));
			ItemBasketPage.explicitWaitForElementToBeClickable("AssignedData");
			System.out.println("Assign Permission:" + ItemBasketPage.getTitle("AssignedData"));
		}

		// Refresh the Web-Page..
		ItemBasketPage.RefreshWebPage();
		// Navigate to Item Basket page...

		HomePage.click("Navigation");
		HomePage.click("InventoryManagement");
		HomePage.explicitWaitForElementToBeClickable("ItemBasketPage");
		HomePage.click("ItemBasketPage");
		// Verify the Title of the Screen..
		ItemBasketPage.verifyTextValue("ItemBasketListTitle", map.get("Title"));

		ItemBasketPage.verifyElementIsPresent("RefreshButton");
		

	}

	@DataProvider(name = "ItemBasketCopy")
	public Object[][] getItemBasketCopy() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("ItemBasketTestData"),
				"ItemBasketCopy");
		return testObjArray;
	}

	@Test(dataProvider = "ItemBasketCopy", priority = 2)
	public void verifyItemBasketCopy(Map<String, String> map) throws Exception {
       
		logger.info("Method Name: VerifyItemBasketCopy");
		//Create the Item Basket
		ItemBasketPage.click("ListScreenCreateButton");
		ItemBasketPage.selectDropDownValue("TypeDropDown", map.get("Type"));
		ItemBasketPage.enterIntoTextBox("DescriptionTextBox",map.get("Description"));
		ItemBasketPage.enterIntoTextBox("AlternateIdTextBox",map.get("AlternateId"));
		ItemBasketPage.click("CreateButton");
		
		//Get the Item Basket Id at Run time.
		String itemBasketId = ItemBasketPage.getAttributeValue("ItemBasketId","aria-valuenow");
		System.out.println("Item Basket Id :"+itemBasketId);
		
		
		// Add the Components in Item Basket
		ItemBasketPage.explicitWaitForElementToBeClickable("AddComponentsButton");
		ItemBasketPage.click("AddComponentsButton");
		
		//Select the ComponentType Drop-down Value
		ItemBasketPage.explicitWaitForElementToBeClickable("ComponentTypeDropDown");
		ItemBasketPage.selectDropDownValue("ComponentTypeDropDown", map.get("ComponentType"));
		ItemBasketPage.enterIntoTextBox("ItemScanBox", map.get("ItemId"));
		ItemBasketPage.explicitWaitForElementToBeClickable("ScanBoxArrow");
		ItemBasketPage.click("ScanBoxArrow");
		ItemBasketPage.click("ScanBoxArrow");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
		ItemBasketPage.explicitWaitForElementToBeClickable("ApplyButton");
		ItemBasketPage.click("ApplyButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		ItemBasketPage.explicitWaitForElementToBeClickable("ConfirmButton");
		ItemBasketPage.click("ConfirmButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		ItemBasketPage.explicitWaitForElementToBeClickable("YesButton");
		ItemBasketPage.click("YesButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
		ItemBasketPage.explicitWaitForElementToBeClickable("SearchIcon");
		ItemBasketPage.click("SearchIcon");
		ItemBasketPage.enterIntoTextBox("ItemBasketIdTextBox",itemBasketId );
		ItemBasketPage.selectDropDownValue("StatusDropDown",map.get("Status1"));
		ItemBasketPage.click("SearchButton");
		
		//Verify the Status of entered Item Basket
		ItemBasketPage.click("FilterStatus");
		ItemBasketPage.enterIntoTextBox("FilterStatus",map.get("Status1"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		logger.info("Confirmed Item basket Successfully!!!");
		ItemBasketPage.click("ItemBasketIdColumnFirstCell");
		ItemBasketPage.verifyTextValue("ItemBasketDetailTitle", map.get("DetailTitle"));
		ItemBasketPage.verifyElementIsEnabled("CopyButton");
		ItemBasketPage.click("CopyButton");
		ItemBasketPage.verifyTextValue("CopyConfirmMsg", map.get("CopyConfirmMsg"));
		ItemBasketPage.click("NoButton");
		ItemBasketPage.verifyTextValue("ItemBasketDetailTitle", map.get("DetailTitle"));
		ItemBasketPage.click("CopyButton");
		ItemBasketPage.click("YesButton");
		
		//Verify ItemID, Copy Status and ReferenceID
		ItemBasketPage.verifyTextValue("ItemIdLink", map.get("ItemId"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		ItemBasketPage.verifyTextValue("CopyStatus", map.get("CopyStatus"));
		ItemBasketPage.verifyElementIsPresent("ItemBasketId");
		logger.info("Item Basket id after copy: "+ItemBasketPage.getText("ItemBasketId"));
		ItemBasketPage.explicitWaitForElementToBeClickable("InfoButton");
		ItemBasketPage.click("InfoButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
		String referenceID = ItemBasketPage.getAttributeValue("ReferenceId", "aria-valuenow");
		logger.info("Reference Id is:"+referenceID);
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		ItemBasketPage.verifyValuesAreEqual(referenceID,itemBasketId );
		ItemBasketPage.explicitWaitForElementToBeClickable("CloseButton");
		ItemBasketPage.click("CloseButton");
		ItemBasketPage.explicitWaitForElementToBeClickable("BackButton");
		ItemBasketPage.click("BackButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		
	}

	@AfterClass
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
