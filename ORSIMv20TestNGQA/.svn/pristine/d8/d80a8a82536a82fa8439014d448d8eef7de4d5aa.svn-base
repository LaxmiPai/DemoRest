package com.oracle.sim.testcases.InventoryAdjustment;

/**
 * * @author dsorthiy
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
import com.oracle.core.dataProvider.ExcelReader;
import com.oracle.core.utils.PropertyReader;
import com.oracle.sim.pageFactory.PageFactory;
import com.oracle.sim.pages.Base.SimBasePage;
import com.oracle.sim.utils.SIMWebdriverBase;

public class NotesFieldInventoryAdjustmentVerify {

	public static Logger logger = Logger.getLogger(NotesFieldInventoryAdjustmentVerify.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pageFactory = new PageFactory();
	Random random = new Random();
	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage InventoryAdjustmentPage;

	@BeforeClass()
	public void setup(ITestContext context) throws Exception {
		logger.info("TestCase Name: " + logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
		LoginPage = pageFactory.initialize("LoginPage");
		HomePage = pageFactory.initialize("HomePage");
		InventoryAdjustmentPage = pageFactory.initialize("InventoryAdjustmentPage");
		LoginPage.explicitWaitForVisibility("Username");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		LoginPage.enterIntoTextBox("Username", LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password", LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");

		HomePage = pageFactory.initialize("HomePage");
		HomePage.verifyTextValue("Title", HomePage.getProperty("Title"));
		HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
		HomePage.storeIdCheck();
		HomePage.click("Navigation");
		// Navigate to Inventory Management
		HomePage.click("InventoryManagement");
		// Navigate to Inventory Adjustment Page
		HomePage.click("InventoryAdjustment");

	}

	@DataProvider(name = "NotesFieldInventoryAdjustTestData")
	public Object[][] getTestDataForUI() throws Exception {
		Object[][] testObjArray = ExcelReader
				.loadExcel(propReader.getApplicationproperty("InventoryAdjustmentTestData"), "CreateNewNotes");
		return testObjArray;
	}

	@Test(dataProvider = "NotesFieldInventoryAdjustTestData", priority = 1)
	public void createNewNotesInventoryAdjustment(Map<String, String> map) throws Exception {

		// verify the Title of Inventory Adjustment
		logger.info("Create Notes");
		InventoryAdjustmentPage.verifyTextValue("Title", map.get("Title"));
		// click on Create Button
		InventoryAdjustmentPage.explicitWaitForElementToBeClickable("CreateButton");
		InventoryAdjustmentPage.click("CreateButton");
		InventoryAdjustmentPage.verifyElementIsEnabled("ConfirmButton");
		InventoryAdjustmentPage.verifyElementIsEnabled("DeleteButton");
		InventoryAdjustmentPage.verifyElementIsEnabled("InfoIcon");
		InventoryAdjustmentPage.verifyElementIsEnabled("AddIcon");
		InventoryAdjustmentPage.searchDropDownValue("ReasonDropdown", map.get("Reason"));
		InventoryAdjustmentPage.explicitWaitForVisibility("AddIcon");
		InventoryAdjustmentPage.click("AddIcon");
		InventoryAdjustmentPage.verifyElementIsEnabled("CancelButton");
		InventoryAdjustmentPage.enterIntoTextBox("ScanItemBox", map.get("ItemId"));
		InventoryAdjustmentPage.explicitWaitForElementToBeClickable("ClickScanBox");
		InventoryAdjustmentPage.click("ClickScanBox");
		InventoryAdjustmentPage.enterIntoTextBox("PackSizeField", map.get("PackSize"));
		InventoryAdjustmentPage.enterIntoTextBox("QuantityInputField", map.get("Quantity"));
		InventoryAdjustmentPage.click("ApplyButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		InventoryAdjustmentPage.isElementPresent("NotesIcon");
		InventoryAdjustmentPage.click("NotesIcon");
		InventoryAdjustmentPage.verifyTextValue("NotesTitle", map.get("NotesTitle"));
		// Enter into the Notes Input Text field..
		int Randomnumber = random.nextInt(12345);
		String randnum = Integer.toString(Randomnumber);
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		InventoryAdjustmentPage.explicitWaitForElementToBeClickable("NotesInputTextField");
		InventoryAdjustmentPage.enterIntoTextBox("NotesInputTextField", randnum);
		// click on Post Button
		InventoryAdjustmentPage.explicitWaitForElementToBeClickable("PostIcon");
		InventoryAdjustmentPage.click("PostIcon");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		InventoryAdjustmentPage.explicitWaitForElementToBeClickable("RefreshButton");
		InventoryAdjustmentPage.click("RefreshButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));

		// Scroll the Note pop-up to view Grid-fields
		InventoryAdjustmentPage.scrollToViewElement("FilterByNotes");
		// Verify the Notes should be descending order of Date
		// InventoryAdjustmentPage.columnSorting("DateRecord","DateColumnName",map.get("SortingOrder"),map.get("DataType"));

		InventoryAdjustmentPage.scrollToViewElement("FilterByNotes");
		InventoryAdjustmentPage.click("FilterByNotes");

		InventoryAdjustmentPage.enterIntoTextBox("FilterByNotes", randnum);
		InventoryAdjustmentPage.verifyTextValue("GridHighLight", randnum);

		// Click on Close Button
		InventoryAdjustmentPage.explicitWaitForElementToBeClickable("CloseButton");
		InventoryAdjustmentPage.click("CloseButton");

		InventoryAdjustmentPage.explicitWaitForElementToBeClickable("SaveIcon");
		InventoryAdjustmentPage.click("SaveIcon");
		InventoryAdjustmentPage.explicitWaitForElementToBeClickable("YesButton");
		InventoryAdjustmentPage.click("YesButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		logger.info("Notes Added Successfully in Newly created Inventory Adjustment List!!!");

	}

	@Test(dataProvider = "NotesFieldInventoryAdjustTestData", priority = 2)
	public void CompletedNotesInventoryAdjustment(Map<String, String> map) throws Exception {

		logger.info("Complete Notes");
		InventoryAdjustmentPage.click("SearchButton");
		// Enter Data in Search Criteria Fields
		InventoryAdjustmentPage.verifyTextValue("SearchCriteriaTitle", map.get("SearchTitle"));
		InventoryAdjustmentPage.selectDateFromDatePicker("FromDateTextbox", "4/13/20");
		InventoryAdjustmentPage.click("SearchCriteriaTitle");
		InventoryAdjustmentPage.selectDropDownValue("ReasonDropdown", "All");
		InventoryAdjustmentPage.selectDropDownValue("SubbucketDropdown", "All");
		InventoryAdjustmentPage.explicitWaitForVisibility("StatusLabel");
		InventoryAdjustmentPage.selectDropDownValue("StatusDropdown", "Completed");
		InventoryAdjustmentPage.enterIntoTextBox("UserTextBox", map.get("User"));

		// click on Search Icon
		InventoryAdjustmentPage.verifyElementIsEnabled("SearchIcon");
		InventoryAdjustmentPage.click("SearchIcon");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		// Count the Total number of Records
		int rowCount = InventoryAdjustmentPage.getTableRowCount("InventoryAdjusmtentIDColumnData",
				"TotalSKUsColumnName", "FilterAdjustmentId");
		if (rowCount == 0) {
			System.out.println("No table records found");
		} else {

			System.out.println("Table Records Count: " + rowCount);
			// InventoryAdjustmentPage.verifyTableRecordsCount("InventoryAdjusmtentIDColumnData","TotalSKUsColumnName","FilterAdjustmentId");
			// Filter By Status of Inventory Adjustment
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
			InventoryAdjustmentPage.explicitWaitForElementToBeClickable("FilterByStatus");
			InventoryAdjustmentPage.click("FilterByStatus");
			InventoryAdjustmentPage.enterIntoTextBox("FilterByStatus", "Completed");
			// Filter By User of Inventory Adjustment
			InventoryAdjustmentPage.explicitWaitForElementToBeClickable("FilterByUser");
			InventoryAdjustmentPage.click("FilterByUser");
			InventoryAdjustmentPage.enterIntoTextBox("FilterByUser", map.get("User"));
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
			// Click on Inventory Adjustment Id
			InventoryAdjustmentPage.click("InventoryAdjustmentIdLink");
			InventoryAdjustmentPage.isElementPresent("NotesIcon");
			InventoryAdjustmentPage.click("NotesIcon");
			// Enter into the Notes Input Text field and generate the Random
			// Number
			int Randomnumber2 = random.nextInt(12345);
			String randnum2 = Integer.toString(Randomnumber2);
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
			InventoryAdjustmentPage.explicitWaitForElementToBeClickable("NotesInputTextField");
			InventoryAdjustmentPage.enterIntoTextBox("NotesInputTextField", randnum2);
			// click on Post Button
			InventoryAdjustmentPage.explicitWaitForElementToBeClickable("PostIcon");
			InventoryAdjustmentPage.click("PostIcon");
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
			InventoryAdjustmentPage.explicitWaitForElementToBeClickable("RefreshButton");
			InventoryAdjustmentPage.click("RefreshButton");
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));

			// Scroll the Note pop-up to view Grid-fields
			InventoryAdjustmentPage.scrollToViewElement("FilterByNotes");
			// Verify the Notes should be descending order of Date
			// InventoryAdjustmentPage.columnSorting("DateRecord","DateColumnName",map.get("SortingOrder"),map.get("DataType"));

			// Scroll the Note pop-up to view Grid-fields
			InventoryAdjustmentPage.scrollToViewElement("FilterByNotes");
			// Verify the Entered Notes
			InventoryAdjustmentPage.enterIntoTextBox("FilterByNotes", randnum2);
			InventoryAdjustmentPage.verifyTextValue("GridHighLight", randnum2);
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));

			// Click on Close Button
			InventoryAdjustmentPage.explicitWaitForElementToBeClickable("CloseButton");
			InventoryAdjustmentPage.click("CloseButton");

			// Click on Back Button
			InventoryAdjustmentPage.explicitWaitForElementToBeClickable("BackButton");
			InventoryAdjustmentPage.click("BackButton");

			// Click on Refresh Button
			InventoryAdjustmentPage.explicitWaitForElementToBeClickable("RefreshButton");
			InventoryAdjustmentPage.click("RefreshButton");
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));

		}

	}

	@Test(dataProvider = "NotesFieldInventoryAdjustTestData", priority = 3)
	public void InprogressNotesInventoryAdjustment(Map<String, String> map) throws Exception { 
		
		logger.info("In Progress Notes");
		InventoryAdjustmentPage.click("SearchButton");
		// Enter Data in Search Criteria Fields
		InventoryAdjustmentPage.verifyTextValue("SearchCriteriaTitle", map.get("SearchTitle"));
		InventoryAdjustmentPage.selectDateFromDatePicker("FromDateTextbox", "4/17/20");
		InventoryAdjustmentPage.click("SearchCriteriaTitle");
		InventoryAdjustmentPage.selectDropDownValue("ReasonDropdown", "All");
		InventoryAdjustmentPage.selectDropDownValue("SubbucketDropdown", "All");
		InventoryAdjustmentPage.explicitWaitForVisibility("StatusLabel");
		InventoryAdjustmentPage.selectDropDownValue("StatusDropdown", "In-Progress");
		InventoryAdjustmentPage.enterIntoTextBox("UserTextBox", map.get("User"));

		// click on Search Icon
		InventoryAdjustmentPage.verifyElementIsEnabled("SearchIcon");
		InventoryAdjustmentPage.click("SearchIcon");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		// Count the Total number of Records
		int rowCount = InventoryAdjustmentPage.getTableRowCount("InventoryAdjusmtentIDColumnData",
				"TotalSKUsColumnName", "FilterAdjustmentId");
		if (rowCount == 0) {
			System.out.println("No table records found");
		} else {

			System.out.println("Table Records Count: " + rowCount);
			// InventoryAdjustmentPage.verifyTableRecordsCount("InventoryAdjusmtentIDColumnData","TotalSKUsColumnName","FilterAdjustmentId");
			// Filter By Status of Inventory Adjustment
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
			InventoryAdjustmentPage.explicitWaitForElementToBeClickable("FilterByStatus");
			InventoryAdjustmentPage.click("FilterByStatus");
			InventoryAdjustmentPage.enterIntoTextBox("FilterByStatus", "In-Progress");
			// Filter By User of Inventory Adjustment
			InventoryAdjustmentPage.explicitWaitForElementToBeClickable("FilterByUser");
			InventoryAdjustmentPage.click("FilterByUser");
			InventoryAdjustmentPage.enterIntoTextBox("FilterByUser", map.get("User"));
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
			// Click on Inventory Adjustment Id
			InventoryAdjustmentPage.click("InventoryAdjustmentIdLink");
			InventoryAdjustmentPage.isElementPresent("NotesIcon");
			InventoryAdjustmentPage.click("NotesIcon");
			// Enter into the Notes Input Text field and generate the Random
			// Number
			int Randomnumber3 = random.nextInt(12345);
			String randnum3 = Integer.toString(Randomnumber3);
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
			InventoryAdjustmentPage.explicitWaitForElementToBeClickable("NotesInputTextField");
			InventoryAdjustmentPage.enterIntoTextBox("NotesInputTextField", randnum3);
			// click on Post Button
			InventoryAdjustmentPage.explicitWaitForElementToBeClickable("PostIcon");
			InventoryAdjustmentPage.click("PostIcon");
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
			InventoryAdjustmentPage.explicitWaitForElementToBeClickable("RefreshButton");
			InventoryAdjustmentPage.click("RefreshButton");
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));

			// Scroll the Note pop-up to view Grid-fields
			InventoryAdjustmentPage.scrollToViewElement("FilterByNotes");
			// Verify the Notes should be descending order of Date
			// InventoryAdjustmentPage.columnSorting("DateRecord","DateColumnName",map.get("SortingOrder"),map.get("DataType"));

			// Verify the Entered Notes
			InventoryAdjustmentPage.enterIntoTextBox("FilterByNotes", randnum3);
			InventoryAdjustmentPage.verifyTextValue("GridHighLight", randnum3);
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));

			// Click on Close Button
			InventoryAdjustmentPage.explicitWaitForElementToBeClickable("CloseButton");
			InventoryAdjustmentPage.click("CloseButton");

			// Click on Back Button
			InventoryAdjustmentPage.explicitWaitForElementToBeClickable("BackButton");
			InventoryAdjustmentPage.click("BackButton");

			// Click on Refresh Button
			InventoryAdjustmentPage.explicitWaitForElementToBeClickable("RefreshButton");
			InventoryAdjustmentPage.click("RefreshButton");
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		}

	}

	@Test(dataProvider = "NotesFieldInventoryAdjustTestData", priority = 4)
	public void CanceledNotesInventoryAdjustment(Map<String, String> map) throws Exception {
        
		logger.info("Cancelled Notes");
		InventoryAdjustmentPage.click("SearchButton");
		// Enter Data in Search Criteria Fields
		InventoryAdjustmentPage.verifyTextValue("SearchCriteriaTitle", map.get("SearchTitle"));
		InventoryAdjustmentPage.selectDateFromDatePicker("FromDateTextbox", "3/1/20");
		InventoryAdjustmentPage.click("SearchCriteriaTitle");
		InventoryAdjustmentPage.selectDropDownValue("ReasonDropdown", "All");
		InventoryAdjustmentPage.selectDropDownValue("SubbucketDropdown", "All");
		InventoryAdjustmentPage.explicitWaitForVisibility("StatusLabel");
		InventoryAdjustmentPage.selectDropDownValue("StatusDropdown", "Canceled");
		InventoryAdjustmentPage.enterIntoTextBox("UserTextBox", map.get("User"));

		// click on Search Icon
		InventoryAdjustmentPage.verifyElementIsEnabled("SearchIcon");
		InventoryAdjustmentPage.click("SearchIcon");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		// Count the Total number of Records
		int rowCount = InventoryAdjustmentPage.getTableRowCount("InventoryAdjusmtentIDColumnData",
				"TotalSKUsColumnName", "FilterAdjustmentId");
		if (rowCount == 0) {
			System.out.println("No table records found");
		} else {

			System.out.println("Table Records Count: " + rowCount);
			// InventoryAdjustmentPage.verifyTableRecordsCount("InventoryAdjusmtentIDColumnData","TotalSKUsColumnName","FilterAdjustmentId");
			// Filter By Status of Inventory Adjustment
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
			InventoryAdjustmentPage.explicitWaitForElementToBeClickable("FilterByStatus");
			InventoryAdjustmentPage.click("FilterByStatus");
			InventoryAdjustmentPage.enterIntoTextBox("FilterByStatus", "Canceled");
			// Filter By User of Inventory Adjustment
			InventoryAdjustmentPage.explicitWaitForElementToBeClickable("FilterByUser");
			InventoryAdjustmentPage.click("FilterByUser");
			InventoryAdjustmentPage.enterIntoTextBox("FilterByUser", map.get("User"));
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
			// Click on Inventory Adjustment Id
			InventoryAdjustmentPage.click("InventoryAdjustmentIdLink");
			InventoryAdjustmentPage.isElementPresent("NotesIcon");
			InventoryAdjustmentPage.click("NotesIcon");
			// Enter into the Notes Input Text field and generate the Random
			// Number
			int Randomnumber4 = random.nextInt(12345);
			String randnum4 = Integer.toString(Randomnumber4);
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
			InventoryAdjustmentPage.explicitWaitForElementToBeClickable("NotesInputTextField");
			InventoryAdjustmentPage.enterIntoTextBox("NotesInputTextField", randnum4);
			// click on Post Button
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
			InventoryAdjustmentPage.explicitWaitForElementToBeClickable("PostIcon");
			InventoryAdjustmentPage.click("PostIcon");
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
			InventoryAdjustmentPage.explicitWaitForElementToBeClickable("RefreshButton");
			InventoryAdjustmentPage.click("RefreshButton");
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));

			// Scroll the Note pop-up to view Grid-fields
			InventoryAdjustmentPage.scrollToViewElement("FilterByNotes");
			// Verify the Notes should be descending order of Date
			// InventoryAdjustmentPage.columnSorting("DateRecord","DateColumnName",map.get("SortingOrder"),map.get("DataType"));

			// Verify the Entered Notes
			InventoryAdjustmentPage.enterIntoTextBox("FilterByNotes", randnum4);
			InventoryAdjustmentPage.verifyTextValue("GridHighLight", randnum4);
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));

			// Click on Close Button
			InventoryAdjustmentPage.explicitWaitForElementToBeClickable("CloseButton");
			InventoryAdjustmentPage.click("CloseButton");

			// Click on Back Button
			InventoryAdjustmentPage.explicitWaitForElementToBeClickable("BackButton");
			InventoryAdjustmentPage.click("BackButton");

			// Click on Refresh Button
			InventoryAdjustmentPage.explicitWaitForElementToBeClickable("RefreshButton");
			InventoryAdjustmentPage.click("RefreshButton");
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		}

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
