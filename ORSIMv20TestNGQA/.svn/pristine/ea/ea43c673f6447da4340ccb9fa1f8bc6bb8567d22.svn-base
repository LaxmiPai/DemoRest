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

public class ItemBasketDetailNoComponentsConfirmVerify {
	public static Logger logger = Logger.getLogger(ItemBasketDetailNoComponentsConfirmVerify.class.getName());
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
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.storeIdCheck();
		HomePage.click("Navigation");
		HomePage.click("Security");
		HomePage.click("RoleMaintenance");	

		//Verifying RoleMaintenance Page Title
		RoleMaintenancePage.explicitWaitForElementToBeClickable("Title");
		RoleMaintenancePage.verifyTextValue("Title",map.get("Title"));

		//Granting the user permission
		String userRole=propReader.getApplicationproperty("UserRole");
		RoleMaintenancePage.grantAllPermissions(userRole);
	}

	@DataProvider(name = "NoComponentsConfirmTestData")
	public Object[][] getNoComponentsConfirmTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("ItemBasketTestData"),
				"NoComponentsConfirm");
		return testObjArray;
	}

	@Test(dataProvider="NoComponentsConfirmTestData", priority=2,dependsOnMethods= { "verifyRole" })
	public void verifyNoComponentsConfirm(Map<String,String> map) throws Exception {
		//Navigating to ItemBasket List screen
		logger.info("Method Name: verifyNoComponentsConfirm");
		HomePage.explicitWaitForElementToBeClickable("InventoryManagement");
		HomePage.click("InventoryManagement");
		HomePage.click("ItemBasketPage");

		//Verifying the page title
		ItemBasketPage.explicitWaitForElementToBeClickable("ItemBasketListTitle");
		ItemBasketPage.verifyTextValue("ItemBasketListTitle",map.get("ItemBasketListTitle"));

		//Verifying table rows are displayed or not
		ItemBasketPage.explicitWaitForVisibility("UserColumnName");
		ItemBasketPage.verifyTableRecordsCount("TableRows","UserColumnName","FilterItemBasketId");

		//Clicking on create button
		ItemBasketPage.explicitWaitForElementToBeClickable("ListScreenCreateButton");
		ItemBasketPage.click("ListScreenCreateButton");

		//Select type drop down
		ItemBasketPage.explicitWaitForElementToBeClickable("TypeDropDown");
		ItemBasketPage.selectDropDownValue("TypeDropDown",map.get("Type"));

		//Enter description value click on create
		ItemBasketPage.enterIntoTextBox("DescriptionTextBox",map.get("Description"));
		ItemBasketPage.explicitWaitForElementToBeClickable("CreateButton");
		ItemBasketPage.click("CreateButton");

		//Filtering the created item basket
		logger.info("Method Name: verifyDepartmentHierarchy");

		//Verifying item basket detail screen title
		ItemBasketPage.explicitWaitForVisibility("ItemBasketDetailTitle");
		ItemBasketPage.verifyTextValue("ItemBasketDetailTitle",map.get("ItemBasketDetailTitle"));

		//Clicking on confirm button
		ItemBasketPage.explicitWaitForElementToBeClickable("ConfirmButton");
		ItemBasketPage.click("ConfirmButton");

		//Verifying the confirmation dialog box
		ItemBasketPage.explicitWaitForElementToBeClickable("NoComponentsConfirmationMsg");
		ItemBasketPage.verifyTextValue("NoComponentsConfirmationMsg",map.get("ConfirmMsg"));
		ItemBasketPage.verifyElementIsPresent("YesButton");
		ItemBasketPage.verifyElementIsPresent("NoButton");

		//clicking on no button in confirmation pop up
		ItemBasketPage.explicitWaitForElementToBeClickable("NoButton");
		ItemBasketPage.click("NoButton");

		//Verifying the user should return to item detail screen
		ItemBasketPage.verifyTextValue("ItemBasketDetailTitle",map.get("ItemBasketDetailTitle"));

		//Clicking on confirm button
		ItemBasketPage.explicitWaitForElementToBeClickable("ConfirmButton");
		ItemBasketPage.click("ConfirmButton");

		//clicking on yes button in confirmation pop up
		ItemBasketPage.explicitWaitForElementToBeClickable("NoComponentsConfirmationMsg");
		ItemBasketPage.verifyTextValue("NoComponentsConfirmationMsg",map.get("ConfirmMsg"));
		ItemBasketPage.explicitWaitForElementToBeClickable("YesButton");
		ItemBasketPage.click("YesButton");

		//Verifying the user should return back to item list screen
		ItemBasketPage.explicitWaitForVisibility("ItemBasketListTitle");
		ItemBasketPage.verifyTextValue("ItemBasketListTitle",map.get("ItemBasketListTitle"));

		//Filtering the created item basket
		ItemBasketPage.explicitWaitForVisibility("ItemBasketListTitle");
		ItemBasketPage.explicitWaitForElementToBeClickable("FilterDescription");
		ItemBasketPage.click("FilterDescription");
		ItemBasketPage.enterIntoTextBox("FilterDescription",map.get("Description"));
		ItemBasketPage.explicitWaitForElementToBeClickable("FilterType");
		ItemBasketPage.click("FilterType");
		ItemBasketPage.enterIntoTextBox("FilterType",map.get("Type"));
		ItemBasketPage.explicitWaitForElementToBeClickable("NoRowsMsgText");
		ItemBasketPage.verifyTextValue("NoRowsMsgText",map.get("NoRowsMsgText"));
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
