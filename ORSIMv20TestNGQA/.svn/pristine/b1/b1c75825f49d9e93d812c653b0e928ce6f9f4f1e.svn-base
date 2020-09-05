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

public class ItemBasketEditInUserStore {

	public static Logger logger = Logger.getLogger(ItemBasketEditInUserStore.class.getName());
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
		ItemBasketPage.enterIntoTextBox("FilterByPermission", map.get("Permission4"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		ItemBasketPage.click("GridHighLightRecord");

		if (ItemBasketPage.getTitle("AssignedData").equals(map.get("AssignedDataYes"))) {
			ItemBasketPage.explicitWaitForElementToBeClickable("RevokeSelectedButton");
			ItemBasketPage.click("RevokeSelectedButton");
			ItemBasketPage.click("SaveButton");
			ItemBasketPage.click("YesButton");
			// wait for DB commit to perform
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
			ItemBasketPage.explicitWaitForElementToBeClickable("FilterByPermission");
			ItemBasketPage.click("FilterByPermission");
			ItemBasketPage.enterIntoTextBox("FilterByPermission", map.get("Permission4"));
			ItemBasketPage.explicitWaitForElementToBeClickable("AssignedData");
			System.out.println("Revoke Permission:" + ItemBasketPage.getTitle("AssignedData"));
		}

		ItemBasketPage.click("BackLink");
		ItemBasketPage.RefreshWebPage();

		HomePage.click("Navigation");
		HomePage.click("InventoryManagement");
        HomePage.click("InventoryAdjustment");
        Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		//ItemBasketPage.VerifyRevokePermissionPage(map.get("PageName"));
		HomePage.click("ItemBasketPage");
		//HomePage.click("ItemBasketPage");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
		ItemBasketPage.verifyTextValue("ItemBasketListTitle", map.get("Title"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		ItemBasketPage.explicitWaitForElementToBeClickable("ItemBasketIdColumnFirstCell");
		ItemBasketPage.click("ItemBasketIdColumnFirstCell");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
		//Item Basket Detail screen validations
		ItemBasketPage.verifyElementIsPresent("DeleteButton");
		ItemBasketPage.checkElementIsNotPresent("EditInfoButton");
		ItemBasketPage.checkElementIsNotPresent("SaveButton");
		ItemBasketPage.checkElementIsNotPresent("AddComponentsButton");
		ItemBasketPage.checkElementIsNotPresent("DeleteComponentsButton");
		
		ItemBasketPage.click("BackLink");
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
	
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		ItemBasketPage.click("FilterByPermission");
		ItemBasketPage.enterIntoTextBox("FilterByPermission", map.get("Permission4"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
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
			ItemBasketPage.enterIntoTextBox("FilterByPermission", map.get("Permission4"));
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

	}

	@DataProvider(name = "ItemBasketEditInUserStore")
	public Object[][] getItemBasketEditInUserStore() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("ItemBasketTestData"),
				"ItemBasketEditUserStore");
		return testObjArray;
	}

	@Test(dataProvider = "ItemBasketEditInUserStore", priority = 2)
	public void verifyItemBasketEditUserStore(Map<String, String> map) throws Exception {

		logger.info("Method Name: verifyItemBasketEditUserStore");
		ItemBasketPage.verifyElementIsPresent("RefreshButton");
		ItemBasketPage.explicitWaitForElementToBeClickable("RefreshButton");
		ItemBasketPage.click("RefreshButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		// ItemBasketPage.selectDropDownValue("TypeDropDown", map.get("Type"));
		ItemBasketPage.click("ListScreenCreateButton");
		ItemBasketPage.selectDropDownValue("TypeDropDown", map.get("Type"));
		ItemBasketPage.enterIntoTextBox("DescriptionTextBox", map.get("Description"));
		ItemBasketPage.enterIntoTextBox("AlternateIdTextBox", map.get("AlternateId"));
		ItemBasketPage.click("CreateButton");

		// ItemId click should navigate to the Item Basket Detail screen
		// ItemBasketPage.click("ItemBasketId");
		ItemBasketPage.verifyTextValue("ItemBasketDetailTitle", map.get("DetailTitle"));
		String itemBasketId = ItemBasketPage.getAttributeValue("ItemBasketId", "aria-valuenow");
		System.out.println("Item Basket Id :" + itemBasketId);
		ItemBasketPage.verifyTextValue("CopyStatus", map.get("Status"));
		
		//Verify the presence of Save, EditInfo, Add and Delete buttons
		ItemBasketPage.verifyElementIsPresent("SaveButton");
		ItemBasketPage.verifyElementIsPresent("AddComponentsButton");
		ItemBasketPage.verifyElementIsPresent("DeleteComponentsButton");
		ItemBasketPage.verifyElementIsEnabled("EditInfoButton");
		ItemBasketPage.explicitWaitForElementToBeClickable("EditInfoButton");
		ItemBasketPage.click("EditInfoButton");

		// Verify the fields in Edit Info
		ItemBasketPage.verifyTextValue("TypeEditInfoDropdownBox", map.get("Type"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		String description = ItemBasketPage.getAttributeValue("DescriptinEditInfoTextBox", "value");
		System.out.println(description);
		ItemBasketPage.verifyValuesAreEqual(description, map.get("Description"));
		String alternateId = ItemBasketPage.getAttributeValue("AlternateIdEditInfoTextBox", "value");
		System.out.println(alternateId);
		ItemBasketPage.verifyValuesAreEqual(alternateId, map.get("AlternateId"));

		// Edit value for Description field
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		ItemBasketPage.enterIntoTextBox("DescriptinEditTextBox", map.get("Description1"));
		ItemBasketPage.click("EditInfoApplyButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		ItemBasketPage.explicitWaitForElementToBeClickable("SaveButton");
		ItemBasketPage.click("SaveButton");
		ItemBasketPage.explicitWaitForElementToBeClickable("YesButton");
		ItemBasketPage.click("YesButton");
		ItemBasketPage.click("BackButton");
		ItemBasketPage.explicitWaitForElementToBeClickable("OkButton");
		ItemBasketPage.click("OkButton");
		ItemBasketPage.explicitWaitForElementToBeClickable("SearchIcon");
		ItemBasketPage.click("SearchIcon");
		ItemBasketPage.enterIntoTextBox("ItemBasketIdTextBox", itemBasketId);
		ItemBasketPage.selectDropDownValue("StatusDropDown", map.get("Status"));
		ItemBasketPage.click("SearchButton");
		ItemBasketPage.click("FilterStatus");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		ItemBasketPage.enterIntoTextBox("FilterStatus", map.get("Status"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		logger.info(" Item basket Created and Edited Successfully!!!");

		// Log into some other store
		HomePage.click("UserStore");
		HomePage.click("TextBoxFilter");
		HomePage.enterIntoTextBox("TextBoxFilter", map.get("Store2"));
		HomePage.explicitWaitForElementToBeClickable("GridRecord");
		HomePage.click("GridRecord");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		HomePage.explicitWaitForElementToBeClickable("Apply");
		HomePage.click("Apply");
		HomePage.explicitWaitForElementToBeClickable("StoreIdYes");
		HomePage.click("StoreIdYes");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.click("Navigation");
		HomePage.click("InventoryManagement");
		HomePage.explicitWaitForElementToBeClickable("ItemBasketPage");
		HomePage.click("ItemBasketPage");
		//ItemBasketPage.click("RefreshButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
		ItemBasketPage.explicitWaitForElementToBeClickable("FilterItemBasketId");
		ItemBasketPage.click("FilterItemBasketId");
		ItemBasketPage.enterIntoTextBox("FilterItemBasketId", itemBasketId);
		ItemBasketPage.verifyTextValue("NoRowsMsgText", map.get("Message"));

		// Login to the user store
		HomePage.click("UserStore");
		HomePage.click("TextBoxFilter");
		HomePage.enterIntoTextBox("TextBoxFilter", map.get("Store1"));
		HomePage.explicitWaitForElementToBeClickable("GridRecord");
		HomePage.click("GridRecord");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		HomePage.explicitWaitForElementToBeClickable("Apply");
		HomePage.click("Apply");
		HomePage.explicitWaitForElementToBeClickable("StoreIdYes");
		HomePage.click("StoreIdYes");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.click("Navigation");
		HomePage.click("InventoryManagement");
		HomePage.explicitWaitForElementToBeClickable("ItemBasketPage");
		HomePage.click("ItemBasketPage");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		ItemBasketPage.explicitWaitForElementToBeClickable("RefreshButton");
		ItemBasketPage.click("RefreshButton");
		ItemBasketPage.click("SearchIcon");
		ItemBasketPage.enterIntoTextBox("ItemBasketIdTextBox", itemBasketId);
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		ItemBasketPage.click("SearchButton");
		ItemBasketPage.explicitWaitForElementToBeClickable("FilterItemBasketId");
		ItemBasketPage.click("FilterItemBasketId");
		ItemBasketPage.enterIntoTextBox("FilterItemBasketId", itemBasketId);
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		ItemBasketPage.explicitWaitForElementToBeClickable("ItemBasketIdColumnFirstCell");
		ItemBasketPage.click("ItemBasketIdColumnFirstCell");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		ItemBasketPage.explicitWaitForElementToBeClickable("DeleteButton");
		ItemBasketPage.click("DeleteButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		ItemBasketPage.explicitWaitForElementToBeClickable("YesButton");
		ItemBasketPage.click("YesButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		ItemBasketPage.click("SearchIcon");
		ItemBasketPage.enterIntoTextBox("ItemBasketIdTextBox", itemBasketId);
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		ItemBasketPage.selectDropDownValue("StatusDropDown", map.get("Status1"));
		ItemBasketPage.click("SearchButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		ItemBasketPage.click("FilterStatus");
		ItemBasketPage.enterIntoTextBox("FilterStatus", map.get("Status1"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		logger.info("Item basket cancelled Successfully!!!");

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
