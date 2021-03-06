package com.oracle.sim.testcases.ItemBasket;

/**
 * @author vidhsank
 *
 * 
 */

import java.util.Map;
import java.util.logging.Logger;

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

public class ItemBasketSearchCriteriaItem {
	public static Logger logger = Logger.getLogger(ItemBasketSearchCriteriaItem.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pagefactory=new PageFactory();
	SoftAssert softAssertion = new SoftAssert();

	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage RoleMaintenancePage;
	SimBasePage ItemBasketPage;

	@BeforeClass
	public void setup(ITestContext context) throws Exception {
		logger.info("TestCase Name: " +logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
		LoginPage=pagefactory.initialize("LoginPage");
		HomePage=pagefactory.initialize("HomePage");
		RoleMaintenancePage=pagefactory.initialize("RoleMaintenancePage");
		ItemBasketPage=pagefactory.initialize("ItemBasketPage");
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username",LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password",LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");		
		HomePage.verifyTextValue("Title", "Store Inventory Operations Cloud Service");
		HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
		HomePage.storeIdCheck();
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.click("Navigation");
	}

	@DataProvider(name = "RoleMaintenanceTestData")
	public Object[][] getRoleMaintenanceTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("SecurityTestData"),
				"RoleMaintenance");
		return testObjArray;
	}

	@Test(dataProvider="RoleMaintenanceTestData", priority=1)
	public void verifyRole(Map<String,String> map) throws Exception {
		//Navigating to role maintenance page		
		logger.info("Method Name: verifyRole");
		HomePage.explicitWaitForElementToBeClickable("Security");
		HomePage.click("Security");
		HomePage.click("RoleMaintenance");	

		//Verifying RoleMaintenance Page Title
		RoleMaintenancePage.explicitWaitForElementToBeClickable("Title");
		RoleMaintenancePage.verifyTextValue("Title",map.get("Title"));

		//Granting the user permission
		String userRole=propReader.getApplicationproperty("UserRole");
		RoleMaintenancePage.verifyUserRole(userRole, map.get("AccessItemBasket"), map.get("AssignedDataNo"));
	}

	@DataProvider(name = "SearchCriteriaItemTestData")
	public Object[][] getSearchCriteriaItemTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("ItemBasketTestData"),
				"SearchCriteriaItem");
		return testObjArray;
	}

	@Test(dataProvider="SearchCriteriaItemTestData", priority=2,dependsOnMethods= { "verifyRole" })
	public void verifySearchCriteriaItem(Map<String,String> map) throws Exception {
		//Navigating to ItemBasket List screen
		logger.info("Method Name: verifySearchCriteriaItem");
		HomePage.explicitWaitForElementToBeClickable("InventoryManagement");
		HomePage.click("InventoryManagement");
		HomePage.click("ItemBasketPage");

		//Verifying the page title
		ItemBasketPage.explicitWaitForElementToBeClickable("ItemBasketListTitle");
		ItemBasketPage.verifyTextValue("ItemBasketListTitle",map.get("ItemBasketListTitle"));

		//clicking on search icon to display search criteria dialog
		ItemBasketPage.explicitWaitForElementToBeClickable("SearchIcon");
		ItemBasketPage.click("SearchIcon");

		//Verifying the search criteria dialog title
		ItemBasketPage.explicitWaitForElementToBeClickable("SearchCriteriaTitle");
		ItemBasketPage.verifyTextValue("SearchCriteriaTitle",map.get("SearchCriteriaTitle"));

		//Verify the Item Field
		String tagName=ItemBasketPage.getAttributeValue("ItemTextBox",map.get("TagNameAttribute"));
		ItemBasketPage.verifyValuesAreEqual(map.get("TagName"),tagName);
		ItemBasketPage.verifyElementIsEditable("ItemTextBox");

		if(tagName.equalsIgnoreCase(map.get("TagName"))){
			//Verify the default value of Item field
			String itemId=ItemBasketPage.getAttributeValue("ItemTextBox",map.get("ValueAttribute"));
			ItemBasketPage.verifyValuesAreEqual(map.get("DefaultItemValue"),itemId);

			//Verify the Maximum Length of the Item field
			ItemBasketPage.click("ItemTextBox");
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
			ItemBasketPage.verifyTextValue("ItemTextBoxToolTipText",map.get("ItemTextBoxToolTipText"));
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("High")));

			//Enter Item length greater than 25
			ItemBasketPage.enterIntoTextBox("ItemTextBox",map.get("ItemMaxInput"));
			ItemBasketPage.pressEnterKey("ItemTextBox");

			//Verifying the error message
			ItemBasketPage.explicitWaitForVisibility("ErrorMessageDialogBox");
			ItemBasketPage.verifyTextValue("ErrorMessageDialogBox",map.get("MaxLengthErrorMessage"));

			//Enter any non-existing Item
			ItemBasketPage.click("ItemTextBox");
			ItemBasketPage.enterIntoTextBox("ItemTextBox",map.get("NonExistingItem"));

			//Clicking on search button
			ItemBasketPage.explicitWaitForElementToBeClickable("SearchButton");
			ItemBasketPage.click("SearchButton");

			//Verify no records in Item Basket list screen if entered Item is non-existing.
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
			ItemBasketPage.explicitWaitForElementToBeClickable("FilterItemBasketId");

			int rowCount=ItemBasketPage.getTableRowCount("TableRows","UserColumnName","FilterItemBasketId");
			softAssertion.assertTrue(rowCount==0,"Expected : Non exisiting item Actual: The item is exists");
			softAssertion.assertAll();

			//clicking on search icon to display search criteria dialog
			ItemBasketPage.explicitWaitForElementToBeClickable("SearchIcon");
			ItemBasketPage.click("SearchIcon");

			//Enter any existing Item
			ItemBasketPage.explicitWaitForElementToBeClickable("ItemTextBox");
			ItemBasketPage.click("ItemTextBox");
			ItemBasketPage.enterIntoTextBox("ItemTextBox",map.get("ExistingItem"));

			//Clicking on search button
			ItemBasketPage.explicitWaitForElementToBeClickable("SearchButton");
			ItemBasketPage.click("SearchButton");

			//Verify no records in Item Basket list screen if entered Item is non-existing.
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
			ItemBasketPage.explicitWaitForElementToBeClickable("FilterItemBasketId");
			ItemBasketPage.verifyTableRecordsCount("TableRows","UserColumnName","FilterItemBasketId");
		}		
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
		}
		finally {
			SIMWebdriverBase.close();
		}
	}
}
