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

public class ItemBasketCreateInUserStore {

	public static Logger logger = Logger.getLogger(ItemBasketCreateInUserStore.class.getName());
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
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		ItemBasketPage.click("FilterByPermission");
		ItemBasketPage.enterIntoTextBox("FilterByPermission", map.get("Permission3"));
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
			ItemBasketPage.enterIntoTextBox("FilterByPermission", map.get("Permission3"));
			ItemBasketPage.explicitWaitForElementToBeClickable("AssignedData");
			System.out.println("Revoke Permission:" + ItemBasketPage.getTitle("AssignedData"));
		}
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		ItemBasketPage.click("FilterByPermission");
		ItemBasketPage.enterIntoTextBox("FilterByPermission", map.get("Permission2"));
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
			ItemBasketPage.enterIntoTextBox("FilterByPermission", map.get("Permission2"));
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
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		ItemBasketPage.click("FilterByPermission");
		ItemBasketPage.enterIntoTextBox("FilterByPermission", map.get("Permission1"));
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
			ItemBasketPage.enterIntoTextBox("FilterByPermission", map.get("Permission1"));
			ItemBasketPage.explicitWaitForElementToBeClickable("AssignedData");
			System.out.println("Assign Permission:" + ItemBasketPage.getTitle("AssignedData"));
		}
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		ItemBasketPage.click("FilterByPermission");
		ItemBasketPage.enterIntoTextBox("FilterByPermission", map.get("Permission2"));
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
			ItemBasketPage.enterIntoTextBox("FilterByPermission", map.get("Permission2"));
			ItemBasketPage.explicitWaitForElementToBeClickable("AssignedData");
			System.out.println("Assign Permission:" + ItemBasketPage.getTitle("AssignedData"));
		}
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		ItemBasketPage.click("FilterByPermission");
		ItemBasketPage.enterIntoTextBox("FilterByPermission", map.get("Permission3"));
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
			ItemBasketPage.enterIntoTextBox("FilterByPermission", map.get("Permission3"));
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

	@DataProvider(name = "ItemBasketCreateInUserStore")
	public Object[][] getItemBasketCreateInUserStore() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("ItemBasketTestData"),
				"ItemBasketUserStore");
		return testObjArray;
	}

	@Test(dataProvider = "ItemBasketCreateInUserStore", priority = 2)
	public void verifyItemBasketCreateInUserStore(Map<String, String> map) throws Exception {

		logger.info("Method Name: VerifyItemBasketCreateInUserStore");

		ItemBasketPage.verifyElementIsPresent("RefreshButton");
		ItemBasketPage.explicitWaitForElementToBeClickable("RefreshButton");
		ItemBasketPage.click("RefreshButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));

		ItemBasketPage.click("ListScreenCreateButton");
		// Verify the fields displayed in create item basket screen
		ItemBasketPage.verifyTextValue("TypeTextLabel", map.get("TypeLabel"));
		ItemBasketPage.verifyTextValue("DescriptionTextLabel", map.get("DescriptionLabel"));
		ItemBasketPage.verifyTextValue("AlternateIdTextLabel", map.get("AlternateIdLabel"));
		ItemBasketPage.verifyTextValue("ExpirationDateLabel", map.get("ExpirationDateLAbel"));
		ItemBasketPage.verifyTextValue("StoreTextLabel", map.get("StoreLabel"));

		// Enter values
		ItemBasketPage.selectDropDownValue("TypeDropDown", map.get("Type"));
		ItemBasketPage.enterIntoTextBox("DescriptionTextBox", map.get("Description"));
		ItemBasketPage.enterIntoTextBox("AlternateIdTextBox", map.get("AlternateId"));
		ItemBasketPage.click("CreateButton");

		// ItemId click should navigate to the Item Basket Detail screen
		ItemBasketPage.verifyTextValue("ItemBasketDetailTitle", map.get("DetailTitle"));
		String itemBasketId = ItemBasketPage.getAttributeValue("ItemBasketId", "aria-valuenow");
		System.out.println("Item Basket Id :" + itemBasketId);
		ItemBasketPage.click("BackButton");
		ItemBasketPage.explicitWaitForElementToBeClickable("SearchIcon");
		ItemBasketPage.click("SearchIcon");
		ItemBasketPage.enterIntoTextBox("ItemBasketIdTextBox", itemBasketId);
		ItemBasketPage.selectDropDownValue("StatusDropDown", map.get("Status"));
		ItemBasketPage.click("SearchButton");
		ItemBasketPage.click("FilterStatus");
		ItemBasketPage.enterIntoTextBox("FilterStatus", map.get("Status"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		logger.info(" Item basket Created Successfully!!!");

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
		ItemBasketPage.click("RefreshButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
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
		ItemBasketPage.click("RefreshButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low"))); 
		ItemBasketPage.click("SearchIcon");
		ItemBasketPage.enterIntoTextBox("ItemBasketIdTextBox", itemBasketId);
		ItemBasketPage.selectDropDownValue("StatusDropDown", map.get("Status"));
		ItemBasketPage.click("SearchButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		/*ItemBasketPage.explicitWaitForElementToBeClickable("FilterItemBasketId");
		ItemBasketPage.click("FilterItemBasketId");
		ItemBasketPage.enterIntoTextBox("FilterItemBasketId", itemBasketId);*/
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
