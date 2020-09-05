package com.oracle.sim.testcases.ItemBasket;

import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.oracle.core.dataProvider.ExcelReader;
import com.oracle.core.utils.PropertyReader;
import com.oracle.sim.pageFactory.PageFactory;
import com.oracle.sim.pages.Base.SimBasePage;
import com.oracle.sim.utils.SIMWebdriverBase;

public class ItemBasketCreateInAllStores {
	public static Logger logger = Logger.getLogger(ItemBasketCopy.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pageFactory = new PageFactory();

	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage RoleMaintenancePage;
	SimBasePage ItemBasketPage;
	SoftAssert softAssertion = new SoftAssert();

	@BeforeClass
	public void setUp(ITestContext context) throws Exception {
		logger.info("TestCase Name: " + logger.getName());
		// logger.info("Before Class");
		SIMWebdriverBase.loadInitialURL(context);
		
		// Login Steps
		LoginPage = pageFactory.initialize("LoginPage");
		ItemBasketPage = pageFactory.initialize("ItemBasketPage");
		HomePage = pageFactory.initialize("HomePage");
		RoleMaintenancePage=pageFactory.initialize("RoleMaintenancePage");
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username", LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password", LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");
		HomePage.verifyTextValue("Title", "Store Inventory Operations Cloud Service");
		HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
		HomePage.storeIdCheck();

	}
	
	@DataProvider(name="ItemBasketCreateAllStore")
	public Object[][] getTestDataForItemBasketPermission() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("ItemBasketTestData"),
				"CreateAllStore");
		return testObjArray;

	}
	
	@Test(dataProvider="ItemBasketCreateAllStore", priority=1)
	public void verifyRole(Map<String,String> map) throws Exception {
		logger.info("TestCase Name : "+ logger.getName());
		//Navigating to role maintenance page
		logger.info("Method Name: verifyRole");
		HomePage.click("Navigation");
		HomePage.click("Security");
		HomePage.click("RoleMaintenance");

		//Verifying RoleMaintenance Page Title
		RoleMaintenancePage.explicitWaitForElementToBeClickable("Title");
		RoleMaintenancePage.verifyTextValue("Title",map.get("Title"));

		//grant all permissions to the user
		String userRole=propReader.getApplicationproperty("UserRole");
		RoleMaintenancePage.grantAllPermissions(userRole);
	}

	
	@Test(dataProvider = "ItemBasketCreateAllStore", priority = 2)
	public void ItemBasketAllStoreCreate(Map<String, String> map) throws Exception {
		logger.info("TestCase Name : "+ logger.getName());
		
		HomePage.click("InventoryManagement");
		HomePage.click("ItemBasketPage");
		
		ItemBasketPage.explicitWaitForElementToBeClickable("ItemBasketListTitle");
		ItemBasketPage.verifyTextValue("ItemBasketListTitle", map.get("TitleItemBasket"));
		
		//To click on create button
		ItemBasketPage.verifyElementIsEnabled("ListScreenCreateButton");
		ItemBasketPage.click("ListScreenCreateButton");
		
		//To verify the title of the create dialog
		ItemBasketPage.explicitWaitForVisibility("CreateDialogTitle");
		ItemBasketPage.verifyTextValue("CreateDialogTitle", map.get("DilgTitle"));
		
		//To check whether all the parameters are displayed
		ItemBasketPage.verifyElementIsDisplayed("TypeDropDown");
		ItemBasketPage.verifyElementIsDisplayed("DescriptionTextBox");
		ItemBasketPage.verifyElementIsDisplayed("AlternateIdTextBox");
		ItemBasketPage.verifyElementIsDisplayed("ExpirationDateLabel");
		ItemBasketPage.verifyElementIsDisplayed("StoreDropDown");
		//softAssertion.assertNotNull("ExpirationDateInput");
		
		//TO check for the default store in the store text field
		String userStre=propReader.getApplicationproperty("StoreId");
		String DefaultValue =ItemBasketPage.getText("StoreDropDownDefault");
		softAssertion.assertSame(userStre, DefaultValue);
		
		//To enter all the details to create itemBasket
		ItemBasketPage.selectDropDownValue("TypeDropDown",map.get("Type"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		ItemBasketPage.enterIntoTextBox("DescriptionTextBox", map.get("Description"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		ItemBasketPage.selectDropDownValue("StoreDropDown",map.get("Store"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		ItemBasketPage.click("CreateButton");
		ItemBasketPage.implicitWait();
		
		//To verify the ItemBasketDetail Title
		ItemBasketPage.explicitWaitForVisibility("ItemBasketDetailTitle");
		ItemBasketPage.verifyTextValue("ItemBasketDetailTitle", map.get("ItemBasketDetailTitle"));
		
		ItemBasketPage.click("AddComponentsButton");
		ItemBasketPage.selectDropDownValue("ComponentTypeDropDown",map.get("ComponentType"));
		ItemBasketPage.enterIntoTextBox("ItemScanBox",map.get("Item"));
		ItemBasketPage.click("ScanBoxArrow");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		ItemBasketPage.click("ApplyButton");
		ItemBasketPage.click("SaveButton");
		ItemBasketPage.verifyElementIsDisplayed("SaveConfirmationDialog");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		ItemBasketPage.click("SaveYesbutton");
		
		
		//To verify the title of the eItembasket list screen
		ItemBasketPage.explicitWaitForElementToBeClickable("ItemBasketListTitle");
		ItemBasketPage.verifyTextValue("ItemBasketListTitle", map.get("TitleItemBasket"));
		
		//To login to a different store and check for the availability of the item basket created
		ItemBasketPage.ChangeUserStr(map.get("ChangeStoreIde"));
		HomePage.explicitWaitForVisibility("Titlelogo");
		
		//Navigate to the item basket list screen
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		HomePage.click("Navigation");
		HomePage.click("InventoryManagement");
		HomePage.click("ItemBasketPage");
		
		
		//Filter it by the item basket that was created
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
		ItemBasketPage.explicitWaitForElementToBeClickable("FilterDescription");
		ItemBasketPage.click("FilterDescription");
		ItemBasketPage.enterIntoTextBox("FilterDescription",map.get("Description"));
		ItemBasketPage.enterIntoTextBox("FilterStatus",map.get("Status"));
		ItemBasketPage.click("GridHighLightRecord");
		logger.info("The store is available");
		ItemBasketPage.click("DeleteButton");
		ItemBasketPage.verifyElementIsDisplayed("DeleteConfirmationMsg");
		ItemBasketPage.implicitWait();
		ItemBasketPage.click("YesButton");
		ItemBasketPage.implicitWait();
		
		//To change the store of the ID logged into previously
		ItemBasketPage.ChangeUserStr(map.get("StoreId"));
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



