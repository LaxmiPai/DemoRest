package com.oracle.sim.testcases.InventoryAdjustment;
/**
 * * @author dsorthiy
 *
 */
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


public class AddUINInventory {
	
	
	public static Logger logger = Logger.getLogger(AddUINInventory.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pageFactory = new PageFactory();
	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage InventoryAdjustmentPage;
	@BeforeClass()
	public void setup(ITestContext context) throws Exception {
		SIMWebdriverBase.loadInitialURL(context);
		
		LoginPage = pageFactory.initialize("LoginPage");
		HomePage = pageFactory.initialize("HomePage");
		InventoryAdjustmentPage= pageFactory.initialize("InventoryAdjustmentPage");
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username", LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password", LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");
		HomePage.verifyTextValue("Title", HomePage.getProperty("Title"));
		HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
		HomePage.storeIdCheck();
		
	}
		
	//DataProvider for Add UIN Inventory  
	@DataProvider(name = "AddInventoryAdjustTestData")
	public Object[][] getTestDataForUI() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("InventoryAdjustmentTestData"),"AddInventory");
		return testObjArray;
	}
		
		//UI verification for Inventory Adjustment Screen
		@Test(dataProvider = "AddInventoryAdjustTestData", priority = 1)
		public void AddUINInventoryAdjustment(Map<String, String> map) throws Exception {
			
		
			HomePage.click("Navigation");
			//Verify Home Page is Present 
			InventoryAdjustmentPage.verifyElementIsPresent("HomeMenuu");
			HomePage.click("InventoryManagement");
			//Navigate to Inventory Adjustment Page
			HomePage.click("InventoryAdjustment");
		
			System.out.println("Verify the Title of Inventory Adjustment Screen");
			InventoryAdjustmentPage.explicitWaitForVisibility("Title");
			
			//verify the Title of Inventory Adjustment
			InventoryAdjustmentPage.verifyTextValue("Title", map.get("Title"));
			//Verify the Refresh Button
			System.out.println("Verify the Refresh Button");
			InventoryAdjustmentPage.verifyElementIsPresent("RefreshButton");
			

			logger.info("Create Inventory Adjustment Test case started..!");
			InventoryAdjustmentPage.explicitWaitForElementToBeClickable("CreateButton");
			InventoryAdjustmentPage.click("CreateButton");
			//Verify Inventory Adjustment Detail Screen Title and other Enabled fields
			InventoryAdjustmentPage.verifyTextValue("IMDetailTitle",map.get("IMDetailTitle"));
			InventoryAdjustmentPage.verifyElementIsEnabled("ConfirmButton");
			InventoryAdjustmentPage.verifyElementIsEnabled("DeleteButton");
			InventoryAdjustmentPage.verifyElementIsEnabled("InfoIcon");
			InventoryAdjustmentPage.verifyElementIsEnabled("AddIcon");
			
			InventoryAdjustmentPage.searchDropDownValue("ReasonDropdown",map.get("Reason"));
			InventoryAdjustmentPage.explicitWaitForVisibility("AddIcon");
			InventoryAdjustmentPage.click("AddIcon");
			InventoryAdjustmentPage.verifyElementIsEnabled("CancelButton");
			System.out.println("Inventory Adjustment Id:"+InventoryAdjustmentPage.getText("AdjustmentId"));
			InventoryAdjustmentPage.enterIntoTextBox("ScanItemBox",map.get("ItemId"));
			
			InventoryAdjustmentPage.explicitWaitForElementToBeClickable("ClickScanBox");
			InventoryAdjustmentPage.click("ClickScanBox");
			
			//click on AddUIN Dialog
			InventoryAdjustmentPage.explicitWaitForElementToBeClickable("AddUinDialog");
			InventoryAdjustmentPage.click("AddUinDialog");
			InventoryAdjustmentPage.explicitWaitForElementToBeClickable("UinAddButton");
			InventoryAdjustmentPage.click("UinAddButton");
			InventoryAdjustmentPage.verifyElementIsDisplayed("UinDialog");
			InventoryAdjustmentPage.explicitWaitForElementToBeClickable("UinInputBoxTable");
			InventoryAdjustmentPage.pressEnterKey("UinInputBoxTable");
			InventoryAdjustmentPage.enterIntoTextBox("UinInputBox",map.get("SerialNumber"));
			InventoryAdjustmentPage.click("UinValidateButton");
			InventoryAdjustmentPage.verifyTextValue("VerifyStatus",map.get("Status"));
			// click on UIN Apply Button
			InventoryAdjustmentPage.explicitWaitForElementToBeClickable("UinApplyButton");
			InventoryAdjustmentPage.click("UinApplyButton");
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));

			InventoryAdjustmentPage.explicitWaitForElementToBeClickable("ApplyButton");
			InventoryAdjustmentPage.click("ApplyButton");
			
			InventoryAdjustmentPage.explicitWaitForElementToBeClickable("SaveIcon");
			InventoryAdjustmentPage.click("SaveIcon");
			
			InventoryAdjustmentPage.explicitWaitForElementToBeClickable("YesButton");
			InventoryAdjustmentPage.click("YesButton");
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
			logger.info("UIN Item Added Successfully in Inventory Adjustment List!!!");
			
			
			
		}
		                                                                                                                                                           
	
		 @AfterClass()
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

	


