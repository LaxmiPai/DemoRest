package com.oracle.sim.testcases.InventoryAdjustment;

import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Logger;

import org.testng.Assert;
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

public class InvAdjSohUnavailableDB {
	public static Logger logger=Logger.getLogger(InvAdjSohUnavailableDB.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pagefactory=new PageFactory();

	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage RoleMaintenancePage;
	SimBasePage SystemAdministrationPage;
	SimBasePage InventoryAdjustmentPage;

	@BeforeClass
	public void setup(ITestContext context) throws Exception {
		logger.info("TestCase Name: " +logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
		LoginPage=pagefactory.initialize("LoginPage");
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username",LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password",LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");
		HomePage=pagefactory.initialize("HomePage");
		//db connection 
		HomePage.getConnection();
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
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.storeIdCheck();
		HomePage.click("Navigation");
		HomePage.click("Security");
		HomePage.click("RoleMaintenance");
		RoleMaintenancePage=pagefactory.initialize("RoleMaintenancePage");

		//Verifying RoleMaintenance Page Title
		RoleMaintenancePage.explicitWaitForElementToBeClickable("Title");
		RoleMaintenancePage.verifyTextValue("Title",map.get("Title"));

		//Granting the user permission
		String userRole=propReader.getApplicationproperty("UserRole");
		RoleMaintenancePage.grantAllPermissions(userRole);	
	}

	@DataProvider(name = "SystemAdministrationTestData")
	public Object[][] getSubbucketTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("InventoryAdjustmentTestData"),
				"StockOnHandUnavailable");
		return testObjArray;
	}

	@Test(dataProvider="SystemAdministrationTestData",priority=2 ,dependsOnMethods = {"verifyRole"})
	public void grantSubBucketAccess(Map<String,String> map) throws Exception {
		//Navigating to system administration page
		HomePage.click("Admin");
		HomePage.click("ConfigurationMenu");
		HomePage.click("SystemAdministrationMenu");
		SystemAdministrationPage=pagefactory.initialize("SystemAdministration");

		//Filtering sub bucket enable access
		SystemAdministrationPage.explicitWaitForVisibility("Title");
		SystemAdministrationPage.explicitWaitForElementToBeClickable("TableFirstRow");
		SystemAdministrationPage.click("FilterOption");
		SystemAdministrationPage.enterIntoTextBox("FilterOption",map.get("SubBucketAccess"));

		//Grant sub bucket access  
		SystemAdministrationPage.explicitWaitForElementToBeClickable("GridRecord");
		if(SystemAdministrationPage.getText("ValueFirstRowRecord").equals("ValueNo")) {
			//Clicking on a edit button 
			SystemAdministrationPage.click("GridRecord");
			SystemAdministrationPage.explicitWaitForElementToBeClickable("EditButton");
			SystemAdministrationPage.click("EditButton");

			//Clicking on value switch button
			SystemAdministrationPage.explicitWaitForElementToBeClickable("ValueSwitchButton");
			SystemAdministrationPage.click("ValueSwitchButton");

			//Clicking on apply button
			SystemAdministrationPage.explicitWaitForElementToBeClickable("ApplyButton");
			SystemAdministrationPage.click("ApplyButton");

			//Clicking on save button -yes
			SystemAdministrationPage.explicitWaitForElementToBeClickable("SaveButton");
			SystemAdministrationPage.click("SaveButton");
			SystemAdministrationPage.explicitWaitForElementToBeClickable("YesButton");
			SystemAdministrationPage.click("YesButton");
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		}
		else {
			System.out.println("The sub bucket access is enabled");
		}
		SystemAdministrationPage.explicitWaitForElementToBeClickable("BackLink");
		SystemAdministrationPage.click("BackLink");
		SystemAdministrationPage.explicitWaitForElementToBeClickable("BackLink");
		SystemAdministrationPage.click("BackLink");
	}

	@DataProvider(name = "SohUnavialbleTestData")
	public Object[][] getSohUnavialbleTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("InventoryAdjustmentTestData"),
				"StockOnHandUnavailable");
		return testObjArray;
	}

	@Test(dataProvider="SohUnavialbleTestData",priority=3 ,dependsOnMethods = {"grantSubBucketAccess"})	
	public void verifySohUnavailable(Map<String,String> map) throws Exception {
		InventoryAdjustmentPage=pagefactory.initialize("InventoryAdjustmentPage");
		HomePage.explicitWaitForElementToBeClickable("InventoryManagement");
		HomePage.click("InventoryManagement");
		HomePage.click("InventoryAdjustment");
		
		//Verifying page title
		InventoryAdjustmentPage.explicitWaitForVisibility("InventoryAdjustmentListTitle");
		InventoryAdjustmentPage.verifyTextValue("InventoryAdjustmentListTitle",map.get("Title"));

		//Getting Values for db connection
		int storeId=Integer.parseInt(propReader.getApplicationproperty("StoreId"));
		String itemId = map.get("ItemId");
		
		int tsoh=Integer.parseInt(map.get("TSOH"));
		int nonSellableQuantity=Integer.parseInt(map.get("NonSellableQuantity"));
		int inventoryQuantity=Integer.parseInt(map.get("InventoryQuantity"));
		
		int nonSellableTypeId=Integer.parseInt(map.get("NonSellableTypeId"));
		
//		update store_item_stock set quantity_total=30,quantity_non_sellable=20
//		where item_id='100000147' and store_id=1311
		
		String query="update store_item_stock set quantity_total="+tsoh+",quantity_non_sellable="+nonSellableQuantity+
				" where item_id='"+itemId+"' and store_id ="+storeId;
		
		InventoryAdjustmentPage.executeUpdate(query);
		
//		update store_item_stock_nonsell set quantity=5
//		where item_id=100000147 and store_id =1311 and nonsellable_type_id=1
		
		InventoryAdjustmentPage.updateQuery(inventoryQuantity,itemId,storeId,nonSellableTypeId);
		
		//Clicking on create button
		InventoryAdjustmentPage.explicitWaitForElementToBeClickable("CreateButton");
		InventoryAdjustmentPage.click("CreateButton");

		//Verifying detail page title
		InventoryAdjustmentPage.explicitWaitForVisibility("InventoryAdjustmentDetailTitle");
		InventoryAdjustmentPage.verifyTextValue("InventoryAdjustmentDetailTitle",map.get("DetailTitle"));

		//Clicking on add button
		InventoryAdjustmentPage.explicitWaitForElementToBeClickable("AddButton");
		InventoryAdjustmentPage.click("AddButton");

		//Verifying detail panel title
		InventoryAdjustmentPage.explicitWaitForVisibility("DetailPanelTitle");
		InventoryAdjustmentPage.verifyTextValue("DetailPanelTitle",map.get("DetailPanelTitle"));

		//Getting the tem stock values from db
		System.out.println("---------------Before inv adjustment:----------");
		ArrayList<String> list=InventoryAdjustmentPage.calculateAvailableUnavailableSoh(itemId);
		if(list.isEmpty()) {
			logger.info("No records available");
		}else {
			int tsoh1=Integer.parseInt(list.get(1));
			int availableSoh1=Integer.parseInt(list.get(2));
			int unavailableSoh1=Integer.parseInt(list.get(3));
			String maxInventory = Integer.toString(inventoryQuantity+1);
			int adjustQuantity=Integer.parseInt(map.get("MinimumQuantity"));
			
			//Enter the item
			InventoryAdjustmentPage.explicitWaitForElementToBeClickable("ScanItemTextBox");
			InventoryAdjustmentPage.click("ScanItemTextBox");
			InventoryAdjustmentPage.enterIntoTextBox("ScanItemTextBox",itemId); 

			//Click on scan arrow
			InventoryAdjustmentPage.explicitWaitForElementToBeClickable("ScanButton");
			InventoryAdjustmentPage.click("ScanButton");

			//Select reason code from reason drop down
			InventoryAdjustmentPage.explicitWaitForVisibility("ItemIdTextBox");
			InventoryAdjustmentPage.explicitWaitForElementToBeClickable("ReasonDropDown");
			InventoryAdjustmentPage.selectDropDownValue("ReasonDropDown",map.get("ReasonValue"));

			//Enter max quantity
			InventoryAdjustmentPage.explicitWaitForElementToBeClickable("QuantityTextBox");
			InventoryAdjustmentPage.enterIntoTextBox("QuantityTextBox",maxInventory);

			//Clicking on apply button
			InventoryAdjustmentPage.explicitWaitForElementToBeClickable("ApplyButton");
			InventoryAdjustmentPage.click("ApplyButton");

			//verify maximum quantity error message
			InventoryAdjustmentPage.explicitWaitForVisibility("ErrorMessageDialogBox");
			InventoryAdjustmentPage.verifyTextValue("ErrorMessageDialogBox",map.get("QuantityErrorMsg"));

			//Enter acceptable quantity in text box
			InventoryAdjustmentPage.explicitWaitForElementToBeClickable("QuantityTextBox");
			InventoryAdjustmentPage.enterIntoTextBox("QuantityTextBox",map.get("MinimumQuantity"));

			//Clicking on apply button
			InventoryAdjustmentPage.explicitWaitForElementToBeClickable("ApplyButton");
			InventoryAdjustmentPage.click("ApplyButton");

			//Clicking on confirm button
			InventoryAdjustmentPage.explicitWaitForElementToBeClickable("ConfirmButton");
			InventoryAdjustmentPage.click("ConfirmButton");
			InventoryAdjustmentPage.explicitWaitForElementToBeClickable("YesButton");
			InventoryAdjustmentPage.click("YesButton");
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));

			//Getting the updated item stock values from db
			System.out.println("---------------After inv adjustment:----------");
			ArrayList<String> verificationlist=InventoryAdjustmentPage.calculateAvailableUnavailableSoh(itemId);
			if(verificationlist.isEmpty()) {
				logger.info("No records available");
			}else {			
				int tsoh2=Integer.parseInt(verificationlist.get(1));
				int availableSoh2=Integer.parseInt(verificationlist.get(2));
				int unavailableSoh2=Integer.parseInt(verificationlist.get(3));
				
				InventoryAdjustmentPage.verifyIntValuesAreEqual(tsoh1-adjustQuantity,tsoh2);
				InventoryAdjustmentPage.verifyIntValuesAreEqual(availableSoh1,availableSoh2);
				InventoryAdjustmentPage.verifyIntValuesAreEqual(unavailableSoh1-adjustQuantity,unavailableSoh2);
			}
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
			HomePage.closeDBConnection();
			SIMWebdriverBase.close();
		}
	}

}
