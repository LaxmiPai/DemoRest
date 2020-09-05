package com.oracle.sim.testcases.InventoryAdjustmentReasons;
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


public class RemoveInventoryAdjustmentReasons {
	
	public static Logger logger = Logger.getLogger(RemoveInventoryAdjustmentReasons.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pageFactory = new PageFactory();
	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage InventoryAdjustmentReasonsPage;
	@BeforeClass()
	public void setup(ITestContext context) throws Exception {
		logger.info("TestCase Name: " +logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
		
		LoginPage = pageFactory.initialize("LoginPage");
		HomePage = pageFactory.initialize("HomePage");
		InventoryAdjustmentReasonsPage= pageFactory.initialize("InventoryAdjustmentReasonsPage");
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username", LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password", LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");
		
		
		
		HomePage = pageFactory.initialize("HomePage");
		HomePage.verifyTextValue("Title", HomePage.getProperty("Title"));
		HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
		HomePage.storeIdCheck();

		
		
	}
	
	//DataProvider for Add UIN Inventory  
			@DataProvider(name ="RemoveReasonsTestData")
			public Object[][] getTestDataForUI() throws Exception {
				Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("InventoryAdjustmentReasonsTestData")
						,"RemoveReasons");
				return testObjArray;
			}
			@Test(dataProvider = "RemoveReasonsTestData")
			public void reasonsIMRemove(Map<String, String> map) throws Exception {
				
				HomePage.click("Navigation");
				HomePage.click("Admin");
				HomePage.click("DataSetup");
				HomePage.click("InventoryAdjustmentReason");
				
				InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("Title");
				InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("AddIcon");
				//No Record Select to Delete
				InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("DeleteIcon");
				
				InventoryAdjustmentReasonsPage.click("DeleteIcon");
				InventoryAdjustmentReasonsPage.explicitWaitForVisibility("NoRecordDelete");
				InventoryAdjustmentReasonsPage.verifyTextValue("NoRecordDelete",map.get("EM0"));
				InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("OkButton");
				InventoryAdjustmentReasonsPage.click("OkButton");
				
				//System Parameter set as Yes can not be Deleted
				InventoryAdjustmentReasonsPage.scrollToViewElement("FilterBySystem");
				InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("FilterBySystem");
				//InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("FilterByDescription");
				//Enter Description into the FilterBox
				InventoryAdjustmentReasonsPage.click("FilterBySystem");
				InventoryAdjustmentReasonsPage.enterIntoTextBox("FilterBySystem",map.get("System"));
				
				InventoryAdjustmentReasonsPage.click("GridHighLight");
				
				InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("DeleteIcon");
				InventoryAdjustmentReasonsPage.click("DeleteIcon");
				
				InventoryAdjustmentReasonsPage.click("YesButton");
				InventoryAdjustmentReasonsPage.explicitWaitForVisibility("RemoveErMsg");
				InventoryAdjustmentReasonsPage.verifyTextValue("RemoveErMsg",map.get("EM1"));
				InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("OkButton");
				InventoryAdjustmentReasonsPage.click("OkButton");
				logger.info("Removing System Parameter set as :Yes Inventory Adjustment Reasons Code..!");
			
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
