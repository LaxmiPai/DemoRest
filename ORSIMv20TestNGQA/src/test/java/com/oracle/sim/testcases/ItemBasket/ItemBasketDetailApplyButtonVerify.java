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

public class ItemBasketDetailApplyButtonVerify {
	public static Logger logger = Logger.getLogger(ItemBasketDetailApplyButtonVerify.class.getName());
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

	@DataProvider(name = "ApplyButtonTestData")
	public Object[][] getDetailApplyButtonTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("ItemBasketTestData"),
				"DetailApplyButton");
		return testObjArray;
	}	

	@Test(dataProvider="ApplyButtonTestData", priority=2,dependsOnMethods= { "verifyRole" })
	public void applyItemBasket(Map<String,String> map) throws Exception {
		//Navigating to ItemBasket List screen
		logger.info("Method Name: applyItemBasket");
		HomePage.explicitWaitForElementToBeClickable("InventoryManagement");
		HomePage.click("InventoryManagement");
		HomePage.click("ItemBasketPage");

		//Verifying the page title
		ItemBasketPage.explicitWaitForElementToBeClickable("ItemBasketListTitle");
		ItemBasketPage.verifyTextValue("ItemBasketListTitle",map.get("ItemBasketListTitle"));
		HomePage.click("Navigation");

		//Creating Item Basket
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

		//Verify apply button should be disabled
		ItemBasketPage.verifyElementIsDisabled("ApplyButton");

		//click on add button
		ItemBasketPage.explicitWaitForVisibility("ItemBasketDetailTitle");
		ItemBasketPage.explicitWaitForElementToBeClickable("AddComponentsButton");
		ItemBasketPage.click("AddComponentsButton");

		//Select component type as hierarchy 
		ItemBasketPage.explicitWaitForElementToBeClickable("ComponentTypeDropDown");
		ItemBasketPage.selectDropDownValue("ComponentTypeDropDown",map.get("HierarchyComponentType"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));

		//Verify drop down is present or not 
		ItemBasketPage.verifyElementIsPresent("DetailPanelDepartmentDropDown");
		ItemBasketPage.verifyElementIsPresent("DetailPanelClassDropdown");
		ItemBasketPage.verifyElementIsPresent("DetailPanelSubClassDropDown");

		//Select dept, class, subclass values
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));	
		ItemBasketPage.selectDropDownValue("DetailPanelDepartmentDropDown",map.get("DepartmentType"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		ItemBasketPage.selectDropDownValue("DetailPanelClassDropdown",map.get("ClassType"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		ItemBasketPage.selectDropDownValue("DetailPanelSubClassDropDown",map.get("SubClassType"));

		//Click on apply button
		ItemBasketPage.explicitWaitForElementToBeClickable("ApplyButton");
		ItemBasketPage.click("ApplyButton");

		//Verifying the hierarchy should be added in the component grid.
		ItemBasketPage.explicitWaitForElementToBeClickable("FilterDepartment");
		ItemBasketPage.click("FilterDepartment");
		ItemBasketPage.enterIntoTextBox("FilterDepartment",map.get("DepartmentType"));
		ItemBasketPage.explicitWaitForElementToBeClickable("DepartmentColumnFirstCell");
		ItemBasketPage.verifyTextValue("DepartmentColumnFirstCell",map.get("DepartmentType"));
		ItemBasketPage.clearElement("FilterDepartment");
		ItemBasketPage.pressEnterKey("FilterDepartment");		

		//click on add button
		ItemBasketPage.explicitWaitForVisibility("ItemBasketDetailTitle");
		ItemBasketPage.explicitWaitForElementToBeClickable("AddComponentsButton");
		ItemBasketPage.click("AddComponentsButton");

		//Select component type
		ItemBasketPage.explicitWaitForElementToBeClickable("ComponentTypeDropDown");
		ItemBasketPage.selectDropDownValue("ComponentTypeDropDown",map.get("ItemComponentType"));

		//Entering item for which the hierarchy is already exist
		ItemBasketPage.explicitWaitForElementToBeClickable("ItemScanBox");
		ItemBasketPage.enterIntoTextBox("ItemScanBox",map.get("HierarchyItemId"));
		ItemBasketPage.click("ScanBoxArrow");

		//Click on apply button
		ItemBasketPage.explicitWaitForVisibility("DetailEditTitle");
		ItemBasketPage.explicitWaitForElementToBeClickable("ApplyButton");
		ItemBasketPage.click("ApplyButton");

		//Verifying the hierarchy exists error message
		ItemBasketPage.explicitWaitForElementToBeClickable("HierarchyExistsErrorMsgDialog");
		ItemBasketPage.verifyTextValue("HierarchyExistsErrorMsgDialog",map.get("HierarchyErrorMsg"));

		//Clicking on ok button
		ItemBasketPage.explicitWaitForElementToBeClickable("OkButton");
		ItemBasketPage.click("OkButton");

		//Verifying item basket detail screen title
		ItemBasketPage.verifyTextValue("ItemBasketDetailTitle",map.get("ItemBasketDetailTitle"));

		//Filtering the item hierarchy item
		ItemBasketPage.explicitWaitForElementToBeClickable("FilterItem");
		ItemBasketPage.click("FilterItem");
		ItemBasketPage.enterIntoTextBox("FilterItem",map.get("HierarchyItemId"));

		//Verify the values updated for quantity should be reflected on the component grid
		ItemBasketPage.explicitWaitForElementToBeClickable("NoRowsMsgText");
		ItemBasketPage.verifyTextValue("NoRowsMsgText",map.get("NoRowsMsgText"));
		ItemBasketPage.clearElement("FilterItem");
		ItemBasketPage.pressEnterKey("FilterItem");		

		//Clicking on cancel button
		ItemBasketPage.explicitWaitForElementToBeClickable("CancelButton");
		ItemBasketPage.click("CancelButton");

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

		//Filtering the item
		ItemBasketPage.explicitWaitForElementToBeClickable("FilterItem");
		ItemBasketPage.click("FilterItem");
		ItemBasketPage.enterIntoTextBox("FilterItem",map.get("ItemId"));

		//Verify the updated item value
		ItemBasketPage.explicitWaitForElementToBeClickable("GridHighLightRecord");
		ItemBasketPage.verifyTextValue("GridHighLightRecord",map.get("ItemId"));

		//click on add button
		ItemBasketPage.explicitWaitForElementToBeClickable("AddComponentsButton");
		ItemBasketPage.click("AddComponentsButton");

		//Select component type
		ItemBasketPage.explicitWaitForElementToBeClickable("ComponentTypeDropDown");
		ItemBasketPage.selectDropDownValue("ComponentTypeDropDown",map.get("ItemComponentType"));

		//Entering the existing item
		ItemBasketPage.explicitWaitForElementToBeClickable("ItemScanBox");
		ItemBasketPage.enterIntoTextBox("ItemScanBox",map.get("ItemId"));
		ItemBasketPage.click("ScanBoxArrow");

		//Change quantity value
		ItemBasketPage.explicitWaitForVisibility("DetailEditTitle");
		ItemBasketPage.explicitWaitForElementToBeClickable("QuantityTextBox");
		ItemBasketPage.enterIntoTextBox("QuantityTextBox",map.get("Quantity"));

		//Click on apply button
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
