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

public class InventoryAdjustmentReasonsCodeAddEditDelete {

	public static Logger logger = Logger.getLogger(InventoryAdjustmentReasonsCodeAddEditDelete.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pageFactory = new PageFactory();
	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage InventoryAdjustmentReasonsPage;

	@BeforeClass()
	public void setup(ITestContext context) throws Exception {
		
		logger.info("TestCase Name: " + logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
		LoginPage = pageFactory.initialize("LoginPage");
		HomePage = pageFactory.initialize("HomePage");
		InventoryAdjustmentReasonsPage = pageFactory.initialize("InventoryAdjustmentReasonsPage");
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username", LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password", LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");
		HomePage.verifyTextValue("Title", HomePage.getProperty("Title"));
		HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
		HomePage.storeIdCheck();

		HomePage.click("Navigation");
		HomePage.click("Admin");
		HomePage.click("DataSetup");
		HomePage.click("InventoryAdjustmentReason");
	}

	// DataProvider for Add UIN Inventory
	@DataProvider(name = "InventoryAdjustmentReasonsTestData")
	public Object[][] getTestDataForUI() throws Exception {
		Object[][] testObjArray = ExcelReader
				.loadExcel(propReader.getApplicationproperty("InventoryAdjustmentReasonsTestData"), "AddReasons");
		return testObjArray;
	}

	// UI Verification of UI of Inventory Adjustment Reasons Screen
	@Test(dataProvider = "InventoryAdjustmentReasonsTestData", priority = 0)
	public void reasonsIMUI(Map<String, String> map) throws Exception {

		InventoryAdjustmentReasonsPage = pageFactory.initialize("InventoryAdjustmentReasonsPage");
		System.out.println("Verify the Title of Inventory Adjustment Reasons Screen");
		InventoryAdjustmentReasonsPage.explicitWaitForVisibility("Title");

		InventoryAdjustmentReasonsPage.verifyTextValue("Title", map.get("Title"));

		// Verify the Refresh Button
		System.out.println("Verify the Refresh Button");
		InventoryAdjustmentReasonsPage.verifyElementIsPresent("RefreshButton");
		System.out.println("Verify the Add Button");
		InventoryAdjustmentReasonsPage.verifyElementIsPresent("AddIcon");
		System.out.println("Verify the Delete Button");
		InventoryAdjustmentReasonsPage.verifyElementIsPresent("DeleteIcon");

	}

	// UI Verification of UI of Shipment Reasons Screen
	@Test(dataProvider = "InventoryAdjustmentReasonsTestData", priority = 1)
	public void reasonsIMAdd(Map<String, String> map) throws Exception {
		// Add the Inventory Adjustment Reasons
		InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("AddIcon");
		InventoryAdjustmentReasonsPage.click("AddIcon");
		InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("CodeField");
		InventoryAdjustmentReasonsPage.enterIntoTextBox("CodeField", map.get("Code"));
		InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("DescriptionField");
		InventoryAdjustmentReasonsPage.enterIntoTextBox("DescriptionField", map.get("Description"));
		InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("UseUISwitchMenu");
		InventoryAdjustmentReasonsPage.click("UseUISwitchMenu");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		InventoryAdjustmentReasonsPage.selectDropDownValue("DispositionDropdown", map.get("Disposition"));

		String Disposition = map.get("Disposition");
		if (Disposition.contains("+ Unavailable")) {
			InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("ToSubBucketDropdown");
			InventoryAdjustmentReasonsPage.selectDropDownValue("ToSubBucketDropdown", map.get("ToSubBucket"));

		} else if (Disposition.contains("- Unavailable") || Disposition.contains("- Stock On Hand & - Unavailable"))

		{
			InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("FromSubBucketDropdown");
			InventoryAdjustmentReasonsPage.selectDropDownValue("FromSubBucketDropdown", map.get("FromSubBucket"));
		} else if (Disposition.contains("- Unavailable & + Unavailable")) {
			InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("ToSubBucketDropdown");
			InventoryAdjustmentReasonsPage.selectDropDownValue("ToSubBucketDropdown", map.get("ToSubBucket"));
			InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("FromSubBucketDropdown");
			InventoryAdjustmentReasonsPage.selectDropDownValue("FromSubBucketDropdown", map.get("FromSubBucket"));
		}
		InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("PublishSwitchMenu");
		InventoryAdjustmentReasonsPage.click("PublishSwitchMenu");

		InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("ApplyButton");
		InventoryAdjustmentReasonsPage.click("ApplyButton");

		InventoryAdjustmentReasonsPage.click("SaveIcon");
		InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("YesButton");
		InventoryAdjustmentReasonsPage.click("YesButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		logger.info("Added Inventory Adjustment Reasons Code..!");
	}

	@Test(dataProvider = "InventoryAdjustmentReasonsTestData", priority = 2)
	public void reasonsIMEdit(Map<String, String> map) throws Exception {

		InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("FilterByDescription");
		// Enter Description into the FilterBox
		InventoryAdjustmentReasonsPage.click("FilterByDescription");
		InventoryAdjustmentReasonsPage.enterIntoTextBox("FilterByDescription", map.get("Description"));
		InventoryAdjustmentReasonsPage.verifyTextValue("GridHighLight", map.get("Description"));
		// Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("GridHighLight");
		InventoryAdjustmentReasonsPage.click("GridHighLight");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("EditIcon");
		InventoryAdjustmentReasonsPage.click("EditIcon");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));

		InventoryAdjustmentReasonsPage.explicitWaitForVisibility("DescriptionField");

		InventoryAdjustmentReasonsPage.enterIntoTextBox("DescriptionField", map.get("EditDescription"));
		InventoryAdjustmentReasonsPage.click("ApplyButton");
		InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("SaveIcon");
		InventoryAdjustmentReasonsPage.click("SaveIcon");
		InventoryAdjustmentReasonsPage.click("YesButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
	}

	@Test(dataProvider = "InventoryAdjustmentReasonsTestData", priority = 3)
	public void reasonsIMDelete(Map<String, String> map) throws Exception {

		// Enter Description into the FilterBox
		InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("RefreshButton");
		InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("AddIcon");
		InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("FilterByDescription");
		InventoryAdjustmentReasonsPage.click("FilterByDescription");
		InventoryAdjustmentReasonsPage.enterIntoTextBox("FilterByDescription", map.get("EditDescription"));
		InventoryAdjustmentReasonsPage.verifyTextValue("GridHighLight", map.get("EditDescription"));
		InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("GridHighLight");
		InventoryAdjustmentReasonsPage.click("GridHighLight");
		// Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("DeleteIcon");
		InventoryAdjustmentReasonsPage.click("DeleteIcon");

		InventoryAdjustmentReasonsPage.click("YesButton");
		InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("SaveIcon");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		InventoryAdjustmentReasonsPage.click("SaveIcon");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));

		// Click Yes in Confirmation pop up
		InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("YesButton");
		InventoryAdjustmentReasonsPage.click("YesButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("FilterByDescription");

		InventoryAdjustmentReasonsPage.click("FilterByDescription");
		InventoryAdjustmentReasonsPage.enterIntoTextBox("FilterByDescription", map.get("EditDescription"));
		InventoryAdjustmentReasonsPage.explicitWaitForVisibility("NorecordsMsg");
		InventoryAdjustmentReasonsPage.verifyTextValue("NorecordsMsg", map.get("EM1"));

		logger.info("Deleted Inventory Adjustment Reasons Code..!");

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
		} finally {
			SIMWebdriverBase.close();
		}
	}

}
