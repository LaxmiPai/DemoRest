package com.oracle.sim.testcases.TroubledTransaction;

/**
 * @author vidhsank
 *
 * 
 */

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

public class TroubledTransactionDetailFieldValidation {
	public static Logger logger=Logger.getLogger(TroubledTransactionDetailFieldValidation.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pagefactory=new PageFactory();

	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage TroubledTransactionPage;

	@BeforeClass()
	public void setup(ITestContext context) throws Exception {
		logger.info("TestCase Name: " +logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
		LoginPage=pagefactory.initialize("LoginPage");
		HomePage=pagefactory.initialize("HomePage");
		TroubledTransactionPage=pagefactory.initialize("TroubledTransactionPage");
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username",LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password",LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");
		HomePage.verifyTextValue("Title", "Store Inventory Operations Cloud Service");
		HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
		HomePage.storeIdCheck();
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.click("Navigation");
		HomePage.explicitWaitForElementToBeClickable("InventoryManagement");
		HomePage.click("InventoryManagement");
		HomePage.explicitWaitForElementToBeClickable("TroubledTransaction");
		HomePage.click("TroubledTransaction");
	}

	@DataProvider(name = "UiTestData")
	public Object[][] getUiTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("TroubledTransactionTestData"),
				"Ui");
		return testObjArray;
	}

	@Test(dataProvider="UiTestData")
	public void verifyListNavigationUi(Map<String, String> map) throws Exception {
		//Navigating to troubled transaction page
		logger.info("Method Name: verifyListNavigationUi");

		//Verifying the title of the screen
		TroubledTransactionPage.explicitWaitForVisibility("TroubledTransactionListTitle");
		TroubledTransactionPage.verifyTextValue("TroubledTransactionListTitle",map.get("Title"));

		//Verifying search criteria title
		TroubledTransactionPage.explicitWaitForElementToBeClickable("SearchCriteriaTitle");
		TroubledTransactionPage.verifyTextValue("SearchCriteriaTitle",map.get("SearchCriteriaTitle"));

		//Verifying search criteria labels
		TroubledTransactionPage.verifyTextValue("StoreLabelName",map.get("StoreLabelName"));
		TroubledTransactionPage.verifyTextValue("ItemLabelName",map.get("ItemLabelName"));
		TroubledTransactionPage.verifyTextValue("UinLabelName",map.get("UinLabelName"));
		TroubledTransactionPage.verifyTextValue("StatusLabelName",map.get("StatusLabelName"));
		TroubledTransactionPage.verifyTextValue("TransactionIdLabelName",map.get("TransactionIdLabelName"));
		TroubledTransactionPage.verifyTextValue("FromDateLabelName",map.get("FromDateLabelName"));
		TroubledTransactionPage.verifyTextValue("ToDateLabelName",map.get("ToDateLabelName"));
		TroubledTransactionPage.verifyTextValue("ShowResolvedLabelName",map.get("ShowResolvedLabelName"));
		TroubledTransactionPage.verifyTextValue("SearchLimitLabelName",map.get("SearchLimitLabelName"));

		//Verifying search criteria input box
		TroubledTransactionPage.verifyElementIsPresent("StoreDropDown");
		TroubledTransactionPage.verifyElementIsPresent("ItemTextBox");
		TroubledTransactionPage.verifyElementIsPresent("UinTextBox");
		TroubledTransactionPage.verifyElementIsPresent("StatusDropDown");
		TroubledTransactionPage.verifyElementIsPresent("TransactionIdTextBox");
		TroubledTransactionPage.verifyElementIsPresent("FromDateDatePicker");
		TroubledTransactionPage.verifyElementIsPresent("ToDateDatePicker");
		TroubledTransactionPage.verifyElementIsPresent("ShowResolvedDropDown");
		TroubledTransactionPage.verifyElementIsPresent("SearchLimitTextBox");

		//Verifying buttons in the search criteria dialog box
		TroubledTransactionPage.verifyElementIsPresent("SearchButton");
		TroubledTransactionPage.verifyElementIsPresent("ResetButton");
		TroubledTransactionPage.verifyElementIsPresent("CancelButton");

		//Clicking on a cancel button
		TroubledTransactionPage.click("CancelButton");
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.click("Navigation");

		//Verifying list screen buttons
		TroubledTransactionPage.explicitWaitForElementToBeClickable("SearchIcon");
		TroubledTransactionPage.verifyElementIsPresent("SearchIcon");
		TroubledTransactionPage.verifyElementIsPresent("RefreshButton");
		TroubledTransactionPage.verifyElementIsPresent("ViewHistoryButton");
		TroubledTransactionPage.verifyElementIsPresent("ResolveButton");
		TroubledTransactionPage.verifyElementIsPresent("UnResolveButton");
		TroubledTransactionPage.verifyElementIsPresent("GridViewMenu");

		//Verifying grid view menu options
		TroubledTransactionPage.explicitWaitForElementToBeClickable("GridViewMenu");
		TroubledTransactionPage.click("GridViewMenu");
		TroubledTransactionPage.explicitWaitForElementToBeClickable("ResetView");
		TroubledTransactionPage.verifyTextValue("ResetView",map.get("ResetView"));
		TroubledTransactionPage.verifyTextValue("ColumnFilter",map.get("ColumnFilter"));
		TroubledTransactionPage.verifyTextValue("RowSelector",map.get("RowSelector"));
		TroubledTransactionPage.verifyTextValue("ExportToCsv",map.get("ExportToCsv"));
		TroubledTransactionPage.click("GridViewMenu");

		//Verifying column name
		TroubledTransactionPage.verifyTextValue("ItemColumnName",map.get("ItemColumnName"));
		TroubledTransactionPage.verifyTextValue("UinColumnName",map.get("UinColumnName"));
		TroubledTransactionPage.verifyTextValue("LastUpdateColumnName",map.get("LastUpdateColumnName"));
		TroubledTransactionPage.verifyTextValue("CurrentStatusColumnName",map.get("CurrentStatusColumnName"));
		TroubledTransactionPage.verifyTextValue("NewStatusColumnName",map.get("NewStatusColumnName"));
		TroubledTransactionPage.verifyTextValue("ActionColumnName",map.get("ActionColumnName"));
		TroubledTransactionPage.verifyTextValue("TransactionIdColumnName",map.get("TransactionIdColumnName"));
		TroubledTransactionPage.scrollToViewElement("ResolvedColumnName");
		TroubledTransactionPage.verifyTextValue("QuantityColumnName",map.get("QuantityColumnName"));
		TroubledTransactionPage.verifyTextValue("ResolvedColumnName",map.get("ResolvedColumnName"));

		//Clicking on a refresh button
		TroubledTransactionPage.explicitWaitForElementToBeClickable("RefreshButton");
		TroubledTransactionPage.click("RefreshButton");

		//Verifying view history message without selecting any record
		TroubledTransactionPage.click("ViewHistoryButton");
		TroubledTransactionPage.verifyTextValue("ViewHistoryMessage",map.get("ViewHistoryMessage"));
		TroubledTransactionPage.click("OkButton");

		//Verifying search criteria title after clicking on search icon
		TroubledTransactionPage.click("SearchIcon");
		TroubledTransactionPage.explicitWaitForElementToBeClickable("SearchCriteriaTitle");
		TroubledTransactionPage.verifyTextValue("SearchCriteriaTitle",map.get("SearchCriteriaTitle"));

		//Verifying from date default to current date or not
		TroubledTransactionPage.verifyDefaultDateWithCurrentDate("FromDateDatePicker");

		//Verifying default values
		String currentStore=TroubledTransactionPage.getText("CurrentStore");
		System.out.println(currentStore);
		TroubledTransactionPage.verifyTextValue("StoreDropDown",currentStore);
		TroubledTransactionPage.verifyValuesAreEqual(TroubledTransactionPage.getPlaceholderValue("StatusDropDown"),map.get("StatusPlaceholder"));
		Assert.assertEquals(TroubledTransactionPage.getAttributeValue("SearchLimitTextBox",map.get("Value")),map.get("SearchLimitValue"),"The search limit value is not correct");

		//Selecting date from from date date picker
		TroubledTransactionPage.selectDateFromDatePicker("FromDateDatePicker",map.get("FromDateValue"));

		//Verifying list screen title after clicking on a search button
		TroubledTransactionPage.click("SearchButton");
		TroubledTransactionPage.explicitWaitForVisibility("TroubledTransactionListTitle");
		TroubledTransactionPage.verifyTextValue("TroubledTransactionListTitle",map.get("Title"));

		//Verifying troubled transaction records count
		TroubledTransactionPage.explicitWaitForElementToBeClickable("ResolvedColumnName");
		int size=TroubledTransactionPage.getTableRowCount("TableRows","ResolvedColumnName","FilterItem");
		if(size==0) {
			System.out.println("No records found");
		}else {
			//Getting first record value
			String firstRecord=TroubledTransactionPage.getText("TableFirstRecordLink");

			//Clicking on table first record
			TroubledTransactionPage.explicitWaitForElementToBeClickable("TableFirstRecordLink");
			TroubledTransactionPage.click("TableFirstRecordLink");

			//Verifying the item detail screen title
			TroubledTransactionPage.explicitWaitForElementToBeClickable("ItemDetailScreenTitle");
			TroubledTransactionPage.verifyTextValue("ItemDetailScreenTitle",map.get("ItemDetailTitle")+" "+firstRecord);

			//clicking on a back button
			TroubledTransactionPage.explicitWaitForElementToBeClickable("BackButton");
			TroubledTransactionPage.click("BackButton");

			//Clicking on a record
			TroubledTransactionPage.explicitWaitForElementToBeClickable("ResolveColumnRecord");
			if(TroubledTransactionPage.isUinHistoryAvailable("ResolveColumnRecord")) {
				//verifying the uin history page title
				TroubledTransactionPage.explicitWaitForElementToBeClickable("UinHistoryTitle");
				TroubledTransactionPage.verifyTextValue("UinHistoryTitle",map.get("UinHistoryTitle"));

				//clicking on a back button
				TroubledTransactionPage.explicitWaitForElementToBeClickable("BackButton");
				TroubledTransactionPage.click("BackButton");
			}
			else {
				System.out.println("All records UIN history got purged");
			}
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
		}
		finally {
			SIMWebdriverBase.close();
		}
	}
}
