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


public class RefreshInventoryAdjustmentReasons {
	
	public static Logger logger = Logger.getLogger(RefreshInventoryAdjustmentReasons.class.getName());
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
	// DataProvider for Remove Reasons Inventory  
	@DataProvider(name ="RefreshReasonsTestData")
	public Object[][] getTestDataForUI() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("InventoryAdjustmentReasonsTestData")
				,"RefreshReasons");
		return testObjArray;
	}
	@Test(dataProvider = "RefreshReasonsTestData")
	public void reasonsIMRefresh(Map<String, String> map) throws Exception {
		
		HomePage.click("Navigation");
		HomePage.click("Admin");
		HomePage.click("DataSetup");
		HomePage.click("InventoryAdjustmentReason");
		
		//Add the Inventory Adjustment Reasons
		InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("AddIcon");
		InventoryAdjustmentReasonsPage.click("AddIcon");
		InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("CodeField");
		InventoryAdjustmentReasonsPage.enterIntoTextBox("CodeField", map.get("Code"));
		InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("DescriptionField");
		InventoryAdjustmentReasonsPage.enterIntoTextBox("DescriptionField",map.get("Description"));
		InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("UseUISwitchMenu");
		InventoryAdjustmentReasonsPage.click("UseUISwitchMenu");
		InventoryAdjustmentReasonsPage.selectDropDownValue("DispositionDropdown",map.get("Disposition"));
		
		String Disposition = map.get("Disposition");
		if(Disposition.contains("+ Unavailable"))
		{
			InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("ToSubBucketDropdown");
			InventoryAdjustmentReasonsPage.selectDropDownValue("ToSubBucketDropdown", map.get("ToSubBucket"));
		
		}
		else if(Disposition.contains("- Unavailable") || Disposition.contains("- Stock On Hand & - Unavailable"))
			
		{
			InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("FromSubBucketDropdown");
			InventoryAdjustmentReasonsPage.selectDropDownValue("FromSubBucketDropdown",map.get("FromSubBucket"));
		}
		else if(Disposition.contains("- Unavailable & + Unavailable"))
		{
			InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("ToSubBucketDropdown");
			InventoryAdjustmentReasonsPage.selectDropDownValue("ToSubBucketDropdown",map.get("ToSubBucket"));
			InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("FromSubBucketDropdown");
			InventoryAdjustmentReasonsPage.selectDropDownValue("FromSubBucketDropdown",map.get("FromSubBucket"));
		}
		InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("PublishSwitchMenu");
		InventoryAdjustmentReasonsPage.click("PublishSwitchMenu");
		//Click on Apply Button 
		InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("ApplyButton");
		InventoryAdjustmentReasonsPage.click("ApplyButton");
		
		//Check whether Edit,Save and Refresh Button should be enabled.
		InventoryAdjustmentReasonsPage.verifyElementIsEnabled("EditIcon");
		InventoryAdjustmentReasonsPage.verifyElementIsEnabled("RefreshButton");
		InventoryAdjustmentReasonsPage.verifyElementIsEnabled("SaveIcon");
		
		InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("RefreshButton");
		InventoryAdjustmentReasonsPage.click("RefreshButton");
		
		InventoryAdjustmentReasonsPage.verifyTextValue("RefreshWarningMsg", map.get("RefreshWarningMsg"));
		//Click on Cancel button to verify Record is Present in Grid Table
		InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("CancelfooterButton");
		InventoryAdjustmentReasonsPage.click("CancelfooterButton");
		//Search through the Description
		InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("FilterByDescription");
		//Enter Description into the FilterBox
		InventoryAdjustmentReasonsPage.click("FilterByDescription");
		InventoryAdjustmentReasonsPage.enterIntoTextBox("FilterByDescription",map.get("Description"));
		InventoryAdjustmentReasonsPage.verifyTextValue("GridHighLight",map.get("Description"));
		System.out.println("Refresh Button with Cancel Button Verified.");
		
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("RefreshButton");
		InventoryAdjustmentReasonsPage.click("RefreshButton");
		//Click on OK Button to Verify Record is not Present in Grid Table
		InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("OKfooterButton");
		InventoryAdjustmentReasonsPage.click("OKfooterButton");
		//Enter Description into the FilterBox
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		InventoryAdjustmentReasonsPage.click("FilterByDescription");
		InventoryAdjustmentReasonsPage.enterIntoTextBox("FilterByDescription",map.get("Description"));
		InventoryAdjustmentReasonsPage.explicitWaitForVisibility("NorecordsMsg");
		InventoryAdjustmentReasonsPage.verifyTextValue("NorecordsMsg",map.get("EM1"));
		System.out.println("Refresh Button with Ok Button Verified.");
		
		logger.info("Refresh the Inventory Adjustment Reasons Code..!");
		
		
		
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


