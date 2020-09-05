package com.oracle.sim.testcases.TroubledTransaction;

/**
 * @author vidhsank
 *
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

public class TroubledTransactionViewHistoryErrorMessage {
	public static Logger logger=Logger.getLogger(TroubledTransactionViewHistoryErrorMessage.class.getName());
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
		HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
		HomePage.storeIdCheck();
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.click("Navigation");
		HomePage.explicitWaitForElementToBeClickable("InventoryManagement");
		HomePage.click("InventoryManagement");
		HomePage.explicitWaitForElementToBeClickable("TroubledTransaction");
		HomePage.click("TroubledTransaction");
	}

	@DataProvider(name = "ViewHistoryErrorMessageTestData")
	public Object[][] getViewHistoryErrorMessageTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("TroubledTransactionTestData"),
				"ViewHistoryErrorMessage");
		return testObjArray;
	}

	@Test(dataProvider="ViewHistoryErrorMessageTestData")
	public void verifyViewHistoryErrorMessage(Map<String, String> map) throws Exception {
		//Navigating to troubled transaction page
		logger.info("Method Name: verifyViewHistoryErrorMessage");

		//Verifying the title of the screen
		TroubledTransactionPage.explicitWaitForVisibility("TroubledTransactionListTitle");
		TroubledTransactionPage.verifyTextValue("TroubledTransactionListTitle",map.get("Title"));

		//Selecting a from date and to date from search criteria
		TroubledTransactionPage.explicitWaitForElementToBeClickable("SearchCriteriaTitle");
		TroubledTransactionPage.selectDateFromDatePicker("FromDateDatePicker",map.get("FromDate"));
		TroubledTransactionPage.explicitWaitForElementToBeClickable("ToDateDatePicker");
		TroubledTransactionPage.selectDateFromDatePicker("ToDateDatePicker",map.get("ToDate"));

		//Clicking on a search button
		TroubledTransactionPage.explicitWaitForElementToBeClickable("SearchButton");
		TroubledTransactionPage.click("SearchButton");
		HomePage.click("Navigation");

		//Verifying troubled transaction records count
		TroubledTransactionPage.explicitWaitForElementToBeClickable("ResolvedColumnName");
		int size=TroubledTransactionPage.getTableRowCount("TableRows","ResolvedColumnName","FilterItem");
		if(size==0) {
			System.out.println("No records found");
		}else {
			//Sort the last update column values in ascending order
			TroubledTransactionPage.explicitWaitForElementToBeClickable("LastUpdateColumnName");
			TroubledTransactionPage.click("LastUpdateColumnName");

			//Clicking on a record
			TroubledTransactionPage.explicitWaitForElementToBeClickable("TableFirstRow");
			TroubledTransactionPage.click("TableFirstRow");

			//clicking on view history button
			TroubledTransactionPage.explicitWaitForElementToBeClickable("ViewHistoryButton");
			TroubledTransactionPage.click("ViewHistoryButton");

			//Verifying the error message
			TroubledTransactionPage.explicitWaitForElementToBeClickable("NoHistoryFoundMessage");
			TroubledTransactionPage.verifyTextValue("NoHistoryFoundMessage",map.get("NoHistoryFoundMessage"));

			//clicking on a ok button
			TroubledTransactionPage.explicitWaitForElementToBeClickable("OkButton");
			TroubledTransactionPage.click("OkButton");

			//Verifying the title of the screen
			TroubledTransactionPage.explicitWaitForVisibility("TroubledTransactionListTitle");
			TroubledTransactionPage.verifyTextValue("TroubledTransactionListTitle",map.get("Title"));
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
