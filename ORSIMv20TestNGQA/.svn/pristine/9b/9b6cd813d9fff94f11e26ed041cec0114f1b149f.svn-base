package com.oracle.sim.testcases.ItemBasket;

/**
 * @author vidhsank
 *
 * 
 */

import java.util.Map;
import java.util.Random;
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

public class ItemBasketDetailEditButtonVerify {
	public static Logger logger = Logger.getLogger(ItemBasketDetailEditButtonVerify.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pagefactory=new PageFactory();
	SoftAssert softAssertion = new SoftAssert();

	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage RoleMaintenancePage;
	SimBasePage ItemBasketPage;
	int randomNo;

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
		RoleMaintenancePage.grantAllPermissions(userRole);
	}


	@DataProvider(name = "EditItemBasketTestData")
	public Object[][] getEditItemBasketTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("ItemBasketTestData"),
				"DetailEditButton");
		return testObjArray;
	}

	@Test(dataProvider="EditItemBasketTestData", priority=2,dependsOnMethods= { "verifyRole" })
	public void editItemBasket(Map<String,String> map) throws Exception {
		//Item basket create
		//Navigating to ItemBasket List screen
		logger.info("Method Name: editItemBasket");		
		HomePage.explicitWaitForElementToBeClickable("InventoryManagement");
		HomePage.click("InventoryManagement");
		HomePage.click("ItemBasketPage");
		HomePage.click("Navigation");

		//Verifying the page title
		ItemBasketPage.explicitWaitForElementToBeClickable("ItemBasketListTitle");
		ItemBasketPage.verifyTextValue("ItemBasketListTitle",map.get("ItemBasketListTitle"));

		//Clicking on create button
		ItemBasketPage.explicitWaitForElementToBeClickable("ListScreenCreateButton");
		ItemBasketPage.click("ListScreenCreateButton");

		//Select type drop down
		ItemBasketPage.explicitWaitForElementToBeClickable("TypeDropDown");
		ItemBasketPage.selectDropDownValue("TypeDropDown",map.get("Type"));

		//Enter description value click on create
		Random r= new Random();
		randomNo=r.nextInt(100);
		ItemBasketPage.enterIntoTextBox("DescriptionTextBox",map.get("Description")+randomNo);
		ItemBasketPage.explicitWaitForElementToBeClickable("CreateButton");
		ItemBasketPage.click("CreateButton");

		//Verifying item basket detail screen title
		ItemBasketPage.explicitWaitForVisibility("ItemBasketDetailTitle");
		ItemBasketPage.verifyTextValue("ItemBasketDetailTitle",map.get("ItemBasketDetailTitle"));

		//Verify Edit option should be disabled
		ItemBasketPage.verifyElementIsDisabled("EditButton");

		//click on add button
		ItemBasketPage.explicitWaitForElementToBeClickable("AddComponentsButton");
		ItemBasketPage.click("AddComponentsButton");

		//Select component type
		ItemBasketPage.explicitWaitForElementToBeClickable("ComponentTypeDropDown");
		ItemBasketPage.selectDropDownValue("ComponentTypeDropDown",map.get("HierarchyComponentType"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		ItemBasketPage.explicitWaitForElementToBeClickable("DetailPanelDepartmentDropDown");		
		ItemBasketPage.selectDropDownValue("DetailPanelDepartmentDropDown",map.get("DepartmentType1"));

		//Click on apply button
		ItemBasketPage.explicitWaitForElementToBeClickable("ApplyButton");
		ItemBasketPage.click("ApplyButton");

		//click on add button
		ItemBasketPage.explicitWaitForElementToBeClickable("AddComponentsButton");
		ItemBasketPage.click("AddComponentsButton");

		//Select component type
		ItemBasketPage.explicitWaitForElementToBeClickable("ComponentTypeDropDown");
		ItemBasketPage.selectDropDownValue("ComponentTypeDropDown",map.get("ItemComponentType"));

		//Entering item and do search
		ItemBasketPage.explicitWaitForElementToBeClickable("ItemScanBox");
		ItemBasketPage.enterIntoTextBox("ItemScanBox",map.get("ItemId"));
		ItemBasketPage.click("ScanBoxArrow");

		//Click on apply button
		ItemBasketPage.explicitWaitForVisibility("DetailEditTitle");
		ItemBasketPage.explicitWaitForElementToBeClickable("ApplyButton");
		ItemBasketPage.click("ApplyButton");

		//Select a row from table
		ItemBasketPage.explicitWaitForElementToBeClickable("FilterDepartment");
		ItemBasketPage.click("FilterDepartment");
		ItemBasketPage.enterIntoTextBox("FilterDepartment",map.get("DepartmentType1"));
		ItemBasketPage.explicitWaitForElementToBeClickable("DepartmentColumnFirstCell");
		ItemBasketPage.click("DepartmentColumnFirstCell");

		//Verify Edit option should be enabled
		ItemBasketPage.explicitWaitForVisibility("DepartmentLable");
		ItemBasketPage.explicitWaitForElementToBeClickable("EditButton");
		ItemBasketPage.verifyElementIsEnabled("EditButton");

		//Click on Edit
		ItemBasketPage.explicitWaitForElementToBeClickable("EditButton");
		ItemBasketPage.click("EditButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));

		//Edit the hierarchy
		ItemBasketPage.explicitWaitForElementToBeClickable("DetailPanelDepartmentDropDown");
		ItemBasketPage.selectDropDownValue("DetailPanelDepartmentDropDown",map.get("DepartmentType2"));

		//Click on apply
		ItemBasketPage.explicitWaitForElementToBeClickable("ApplyButton");
		ItemBasketPage.click("ApplyButton");

		//Filtering the department  
		ItemBasketPage.explicitWaitForElementToBeClickable("DepartmentColumnFirstCell");
		ItemBasketPage.explicitWaitForElementToBeClickable("FilterDepartment");
		ItemBasketPage.click("FilterDepartment");
		ItemBasketPage.enterIntoTextBox("FilterDepartment",map.get("DepartmentType2"));

		//Verify the values updated for hierarchy should be reflected on the component grid
		ItemBasketPage.explicitWaitForVisibility("DepartmentGridRecord");
		ItemBasketPage.verifyTextValue("DepartmentGridRecord",map.get("DepartmentType2"));
		ItemBasketPage.clearElement("FilterDepartment");
		ItemBasketPage.pressEnterKey("FilterDepartment");

		//Select a row from table
		ItemBasketPage.click("FilterItem");
		ItemBasketPage.enterIntoTextBox("FilterItem",map.get("ItemId"));
		ItemBasketPage.explicitWaitForElementToBeClickable("DepartmentColumnFirstCell");
		ItemBasketPage.click("DepartmentColumnFirstCell");

		//Click on Edit
		ItemBasketPage.explicitWaitForElementToBeClickable("EditButton");
		ItemBasketPage.click("EditButton");

		//Edit quantity
		ItemBasketPage.explicitWaitForVisibility("DetailEditTitle");
		ItemBasketPage.explicitWaitForElementToBeClickable("QuantityTextBox");
		ItemBasketPage.click("QuantityTextBox");
		ItemBasketPage.enterIntoTextBox("QuantityTextBox",map.get("Quantity"));

		//Click on apply
		ItemBasketPage.explicitWaitForElementToBeClickable("ApplyButton");
		ItemBasketPage.click("ApplyButton");

		//Filtering the quantity
		ItemBasketPage.scrollToViewElement("FilterQuantity");
		ItemBasketPage.explicitWaitForElementToBeClickable("FilterQuantity");
		ItemBasketPage.click("FilterQuantity");
		ItemBasketPage.enterIntoTextBox("FilterQuantity",map.get("Quantity"));

		//Verify the values updated for quantity should be reflected on the component grid
		ItemBasketPage.explicitWaitForElementToBeClickable("QuantityColumnGridRecord");
		ItemBasketPage.verifyTextValue("QuantityColumnGridRecord",map.get("Quantity"));

		//Delete Item Basket
		ItemBasketPage.explicitWaitForElementToBeClickable("DeleteButton");
		ItemBasketPage.click("DeleteButton");
		ItemBasketPage.explicitWaitForElementToBeClickable("YesButton");
		ItemBasketPage.click("YesButton");
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
