package com.oracle.sim.testcases.UinLookup;

/**
 * @author vidhsank
 *
 * 
 */

import java.text.SimpleDateFormat;
import java.util.Date;
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

public class UinLookupNoDateError {
	public static Logger logger=Logger.getLogger(UinLookupNoDateError.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pagefactory=new PageFactory();

	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage UinLookupPage;

	@BeforeClass()
	public void setup(ITestContext context) throws Exception {
		logger.info("TestCase Name: " +logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
		LoginPage=pagefactory.initialize("LoginPage");
		HomePage=pagefactory.initialize("HomePage");
		UinLookupPage=pagefactory.initialize("UinLookupPage");
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username",LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password",LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");
		HomePage.verifyTextValue("Title", "Store Inventory Operations Cloud Service");
		HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
		HomePage.storeIdCheck();
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.click("Navigation");
		HomePage.click("Lookup");
		HomePage.click("UinLookup");
	}

	@DataProvider(name = "NoDateErrorTestData")
	public Object[][] getUinSearchFieldTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("UinLookupTestData"),
				"NoDateError");
		return testObjArray;
	}

	@Test(dataProvider="NoDateErrorTestData")
	public void verifyDateErrors(Map<String,String> map) throws Exception {
		//Navigating to UIN lookup page
		logger.info("Method Name: verifyDateErrors");

		//Verifying the Page Title
		UinLookupPage.explicitWaitForVisibility("Title");
		UinLookupPage.verifyTextValue("Title",map.get("Title"));
		HomePage.click("Navigation");

		//Verifying default date is equal to today's date
		UinLookupPage.explicitWaitForVisibility("LastUpdateFromDateDatePickerInput");
		UinLookupPage.explicitWaitForElementToBeClickable("LastUpdateFromDateDatePickerInput");
		UinLookupPage.verifyDefaultDateWithCurrentDate("LastUpdateFromDateDatePickerInput");

		//Entering 'Item ID' 
		UinLookupPage.explicitWaitForElementToBeClickable("UinLookupItemTextBox");
		UinLookupPage.click("UinLookupItemTextBox");
		UinLookupPage.enterIntoTextBox("UinLookupItemTextBox",map.get("ItemId"));

		//Clearing from date field
		UinLookupPage.clearElement("LastUpdateFromDateDatePickerInput");

		//Clicking on search button
		UinLookupPage.explicitWaitForElementToBeClickable("SearchButton");
		UinLookupPage.click("SearchButton");

		//Verifying item UIN list gets displayed
		UinLookupPage.explicitWaitForElementToBeClickable("ItemUinListTitle");
		UinLookupPage.verifyTableRecordsCount("StoreColumnData","OpenColumnName","FilterStore");		

		//Clicking on back button to navigate to UIN lookup
		UinLookupPage.explicitWaitForElementToBeClickable("BackButton");
		UinLookupPage.click("BackButton");
		UinLookupPage.explicitWaitForInvisibility("ItemUinListTitle");
		UinLookupPage.explicitWaitForVisibility("Title");

		//Selecting 'To date' from the date picker
		UinLookupPage.selectDateFromDatePicker("LastUpdateToDateDatePickerInput",map.get("ToDate"));

		//Clicking on search button
		UinLookupPage.explicitWaitForElementToBeClickable("SearchButton");
		UinLookupPage.click("SearchButton");
		UinLookupPage.implicitWait();

		//clicking on a last update column name for sorting in ascending order
		UinLookupPage.explicitWaitForElementToBeClickable("ItemUinListTitle");
		UinLookupPage.click("LastUpdateColumnName");		

		//Verifying Item UIN list gets displayed till the date entered in "To Date"
		Date toDate=new SimpleDateFormat("MM/dd/yy").parse(map.get("ToDate"));		
		UinLookupPage.verifyUinListWithinToDate("LastUpdateColumnData","LastUpdateColumnName","FilterLastUpdate",toDate);

		//Clicking on back button to navigate to UIN lookup
		UinLookupPage.explicitWaitForElementToBeClickable("BackButton");
		UinLookupPage.click("BackButton");
		UinLookupPage.explicitWaitForInvisibility("ItemUinListTitle");
		UinLookupPage.explicitWaitForVisibility("Title");

		//Clearing 'To date' field
		UinLookupPage.clearElement("LastUpdateToDateDatePickerInput");

		//Selecting 'From date' from the date picker
		UinLookupPage.selectDateFromDatePicker("LastUpdateFromDateDatePickerInput",map.get("FromDate"));

		//Clicking on a search button
		UinLookupPage.explicitWaitForElementToBeClickable("SearchButton");
		UinLookupPage.click("SearchButton");
		UinLookupPage.explicitWaitForVisibility("ItemUinListTitle");

		//clicking on a last update column name for sorting in ascending order
		UinLookupPage.explicitWaitForElementToBeClickable("ItemUinListTitle");
		UinLookupPage.click("LastUpdateColumnName");		

		//Verifying Item UIN list gets displayed from the date entered in "From Date"
		Date fromDate=new SimpleDateFormat("MM/dd/yy").parse(map.get("FromDate"));
		UinLookupPage.verifyUinListStartsFromDate("LastUpdateColumnData","LastUpdateColumnName","FilterLastUpdate",fromDate);

		//Clicking on back button to navigate to UIN lookup
		UinLookupPage.explicitWaitForElementToBeClickable("BackButton");
		UinLookupPage.click("BackButton");
		UinLookupPage.explicitWaitForInvisibility("ItemUinListTitle");
		UinLookupPage.explicitWaitForVisibility("Title");

		//Entering 'To date' lesser than the from date		
		UinLookupPage.selectDateFromDatePicker("LastUpdateFromDateDatePickerInput",map.get("FromDate"));		
		UinLookupPage.selectDateFromDatePicker("LastUpdateToDateDatePickerInput",map.get("MinimumDate"));

		//Clicking on a search button
		UinLookupPage.explicitWaitForElementToBeClickable("SearchButton");
		UinLookupPage.click("SearchButton");

		//Verifying the error message 
		UinLookupPage.explicitWaitForElementToBeClickable("ToDateErrorMessage");
		UinLookupPage.verifyTextValue("ToDateErrorMessage",map.get("ErrorMessage"));

		//Entering 'From date' equal to 'To date' field
		UinLookupPage.selectDateFromDatePicker("LastUpdateFromDateDatePickerInput",map.get("Date"));
		UinLookupPage.selectDateFromDatePicker("LastUpdateToDateDatePickerInput",map.get("Date"));

		//Clicking on a search button
		UinLookupPage.explicitWaitForElementToBeClickable("SearchButton");
		UinLookupPage.click("SearchButton");

		//clicking on a last update column name for sorting in ascending order
		UinLookupPage.explicitWaitForElementToBeClickable("ItemUinListTitle");
		UinLookupPage.click("LastUpdateColumnName");	

		//Verifying Item UIN list gets displayed from the date entered in "From Date"
		UinLookupPage.explicitWaitForElementToBeClickable("ItemUinListTitle");
		Date date=new SimpleDateFormat("MM/dd/yy").parse(map.get("Date"));
		UinLookupPage.verifyUinListBetweenFromAndToDate("LastUpdateColumnData","LastUpdateColumnName","FilterLastUpdate",date);

		//Clicking on back button to navigate to UIN lookup
		UinLookupPage.explicitWaitForElementToBeClickable("BackButton");
		UinLookupPage.click("BackButton");
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
