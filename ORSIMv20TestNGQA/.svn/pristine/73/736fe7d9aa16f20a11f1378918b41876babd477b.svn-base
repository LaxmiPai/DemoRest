/* @author lrathnak **/
package com.oracle.sim.testcases.ItemBasket;

import java.io.IOException;
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

public class ItemBasketUIAndNavigation {
	public static Logger logger = Logger.getLogger(ItemBasketUIAndNavigation.class.getName());
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
	
	@DataProvider(name="ItemBasketUINavigation")
	public Object[][] getTestDataForItemBasketPermission() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("ItemBasketTestData"),
				"ItemBasketUI&Navigation");
		return testObjArray;

	}
	
	@Test(dataProvider="ItemBasketUINavigation", priority=1)
	public void verifyRole(Map<String,String> map) throws Exception {
		//Navigating to role maintenance page
		logger.info("Method Name: verifyRole");
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.click("Navigation");
		HomePage.click("Security");
		HomePage.click("RoleMaintenance");

		//Verifying RoleMaintenance Page Title
		RoleMaintenancePage.explicitWaitForElementToBeClickable("Title");
		RoleMaintenancePage.verifyTextValue("Title",map.get("Title"));

		//Granting the user permission
		String userRole=propReader.getApplicationproperty("UserRole");
		RoleMaintenancePage.verifyUserRole(userRole, map.get("AccessPermission"), map.get("AssignedDataNo"));	
	}
	
	@Test(dataProvider="ItemBasketUINavigation", priority=2)
	public void ItmbsktListUiVerify(Map<String,String> map) throws Exception {
		logger.info("Method Name: verifyRole");
		
		
		HomePage.click("InventoryManagement");
		HomePage.click("ItemBasketPage");
		
		//Title Verification
		ItemBasketPage.explicitWaitForVisibility("ItemBasketListTitle");
		ItemBasketPage.verifyTextValue("ItemBasketListTitle",map.get("ListScreenTitle"));
		
		//Verification of the header buttons and fields
		ItemBasketPage.verifyElementIsPresent("SearchIcon");
		ItemBasketPage.verifyElementIsEnabled("SearchIcon");
		ItemBasketPage.verifyElementIsDisplayed("StatusListlabel");
		
		//To verify the default status is in progress
		String StatusVal=ItemBasketPage.getText("StatusListlabel");
		softAssertion.assertEquals(StatusVal,map.get("Status"));
		
		ItemBasketPage.verifyElementIsDisplayed("ListResultsLabel");
		String ResValue=ItemBasketPage.getText("ListResultsLabel");
		String TrmResValue=ResValue.replaceAll("Results: ","").trim();
		int Value=Integer.parseInt(TrmResValue);
		int RcdCnt=ItemBasketPage.getTableRowCount("TableRows", "UserColumnName", "FilterItemBasketId");
		softAssertion.assertEquals(Value, RcdCnt,"The Results do not match");
		
		ItemBasketPage.verifyElementIsPresent("RefreshButton");
		ItemBasketPage.verifyElementIsEnabled("RefreshButton");
		ItemBasketPage.verifyElementIsPresent("ListScreenCreateButton");
		ItemBasketPage.verifyElementIsEnabled("ListScreenCreateButton");
		ItemBasketPage.verifyElementIsPresent("DeleteButton");
		ItemBasketPage.verifyElementIsEnabled("DeleteButton");
		ItemBasketPage.verifyElementIsPresent("GridViewMenu");
	}
	
	@Test(dataProvider="ItemBasketUINavigation", priority=3)
	public void ItemBasketCreateVerify(Map<String,String> map) throws IOException, NumberFormatException, InterruptedException {
		ItemBasketPage.click("ListScreenCreateButton");
		
		//To verify title of the create dialogue
		ItemBasketPage.click("ListScreenCreateButton");
		
		ItemBasketPage.explicitWaitForElementToBeClickable("CreateDialogTitle");
		ItemBasketPage.verifyTextValue("CreateDialogTitle", map.get("DilgTitle"));
		
		ItemBasketPage.verifyElementIsPresent("TypeDropDown");
		ItemBasketPage.verifyElementIsPresent("DescriptionTextBox");
		ItemBasketPage.verifyElementIsPresent("AlternateIdTextBox");
		ItemBasketPage.verifyElementIsPresent("ExpirationDate");
		ItemBasketPage.verifyElementIsPresent("StoreDropDown");
		softAssertion.assertNotNull("ExpirationDateInput");
		ItemBasketPage.verifyElementIsPresent("CreateButton");
		
		
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
		ItemBasketPage.click("CreateCreateButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
		
	}
	
	@Test(dataProvider="ItemBasketUINavigation",priority=4)
	public void ItemBasketDetailVerify(Map<String,String> map) {
		
		//To verify header buttons
		ItemBasketPage.verifyElementIsPresent("BackButton");
		ItemBasketPage.verifyElementIsPresent("SaveButton");
		ItemBasketPage.verifyElementIsDisabled("SaveButton");
		ItemBasketPage.verifyElementIsPresent("ConfirmButton");
		ItemBasketPage.verifyElementIsPresent("DeleteButton");
		ItemBasketPage.verifyElementIsPresent("InfoButton");
		ItemBasketPage.verifyElementIsPresent("EditInfoButton");
		ItemBasketPage.verifyElementIsPresent("DownloadReportButton");
		ItemBasketPage.verifyElementIsDisplayed("ItemBasketIDLabel");
		ItemBasketPage.verifyElementIsDisplayed("NotesLabel");
		ItemBasketPage.verifyElementIsDisplayed("StatusLabel");
		ItemBasketPage.verifyElementIsDisplayed("TypeLabel");
		ItemBasketPage.verifyElementIsDisplayed("DescriptionLabel");
		
		//Buttons on the detail block
		ItemBasketPage.verifyElementIsPresent("EditButton");
		ItemBasketPage.verifyElementIsDisabled("EditButton");
		ItemBasketPage.verifyElementIsPresent("ApplyButton");
		ItemBasketPage.verifyElementIsDisabled("ApplyButton");
		ItemBasketPage.verifyElementIsPresent("CancelButton");
		ItemBasketPage.verifyElementIsDisabled("CancelButton");
		
		ItemBasketPage.click("DeleteButton");
		
		ItemBasketPage.verifyElementIsDisplayed("DeleteConfirmationDialog");
		ItemBasketPage.click("ConfirmYesButton");
		
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

