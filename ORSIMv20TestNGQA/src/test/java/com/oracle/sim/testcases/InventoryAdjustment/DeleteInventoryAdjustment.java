package com.oracle.sim.testcases.InventoryAdjustment;

//import static org.junit.Assert.assertTrue;

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

public class DeleteInventoryAdjustment {
	
	public static Logger logger = Logger.getLogger(DeleteInventoryAdjustment.class.getName());
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
		HomePage.click("Navigation");
		//Navigate to Inventory Management 
		HomePage.click("InventoryManagement");
		//Navigate to Inventory Adjustment Page
		HomePage.click("InventoryAdjustment");
		
	}
	//DataProvider for Notes field in Inventory Adjustment Detail Screen 
			@DataProvider(name = "DeleteInventoryAdjustTestData")
			public Object[][] getTestDataForUI() throws Exception {
				Object[][] testObjArray = ExcelReader.loadExcel("src\\test\\resources\\TestData\\InventoryAdjustment.xlsx"
						,"DeleteField");
				return testObjArray;
			}
			
						
		@Test(dataProvider = "DeleteInventoryAdjustTestData", priority = 1)
	public void InProgressDeleteInventoryAdjustment(Map<String, String> map) throws Exception {
					
			
				// Filter By Status of Inventory Adjustment 
			    InventoryAdjustmentPage.click("RefreshButton");
			    Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				InventoryAdjustmentPage.explicitWaitForElementToBeClickable("FilterByStatus");
				InventoryAdjustmentPage.click("FilterByStatus");
				InventoryAdjustmentPage.enterIntoTextBox("FilterByStatus", map.get("Status"));
				
				// Filter By User of Inventory Adjustment
				InventoryAdjustmentPage.explicitWaitForElementToBeClickable("FilterByUser");
				InventoryAdjustmentPage.click("FilterByUser");
				InventoryAdjustmentPage.enterIntoTextBox("FilterByUser", map.get("User"));
			
				//InventoryAdjustmentPage.click("GridHighLight");
				InventoryAdjustmentPage.click("InventoryAdjustmentIdLink");
				//Verify Inventory Adjustment Detail Screen Title and other Enabled fields
				
				String InventoryAdjustmentIdd = InventoryAdjustmentPage.getAttributeValue("AdjustmentId","aria-valuenow");
				System.out.println("Inventory Adjustment Id :"+InventoryAdjustmentIdd);
				InventoryAdjustmentPage.verifyElementIsEnabled("ConfirmButton");
				InventoryAdjustmentPage.verifyElementIsEnabled("DeleteButton");
				InventoryAdjustmentPage.verifyElementIsEnabled("InfoIcon");
				InventoryAdjustmentPage.verifyElementIsEnabled("AddIcon");
				
				
				//Click on Delete Button
				InventoryAdjustmentPage.click("DeleteButton");
				
				//Verify the Delete Confirmation Message 
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				InventoryAdjustmentPage.verifyDeleteConfirmationMsg();
				InventoryAdjustmentPage.click("YesButton");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				InventoryAdjustmentPage.click("SearchButton");
				InventoryAdjustmentPage.verifyTextValue("SearchCriteriaTitle",map.get("SearchTitle"));
				InventoryAdjustmentPage.selectDropDownValue("ReasonDropdown", "All");
				InventoryAdjustmentPage.selectDropDownValue("SubbucketDropdown", "All");
				InventoryAdjustmentPage.explicitWaitForVisibility("StatusLabel");
				InventoryAdjustmentPage.selectDropDownValue("StatusDropdown", "Canceled");
				InventoryAdjustmentPage.enterIntoTextBox("UserTextBox","sim_qa3");
				
				//click on Search Icon
				InventoryAdjustmentPage.verifyElementIsEnabled("SearchIcon");
				InventoryAdjustmentPage.click("SearchIcon");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				InventoryAdjustmentPage.explicitWaitForElementToBeClickable("FilterByAdjustmentId");
				InventoryAdjustmentPage.click("FilterByAdjustmentId");
				InventoryAdjustmentPage.enterIntoTextBox("FilterByAdjustmentId",InventoryAdjustmentIdd);
				InventoryAdjustmentPage.verifyTextValue("GridHighLight",InventoryAdjustmentIdd);
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
				
				
				
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
