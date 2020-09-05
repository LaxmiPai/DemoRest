package com.oracle.sim.testcases.MPSWorkType;

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

public class MpsWorkTypeTitleBar {
	public static Logger logger=Logger.getLogger(MpsWorkTypeTitleBar.class.getName());
	protected static PropertyReader propReader=new PropertyReader();
	PageFactory pagefactory=new PageFactory();

	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage MpsWorkTypePage;

	@BeforeClass()
	public void setup(ITestContext context) throws Exception {
		logger.info("TestCase Name: " +logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
		LoginPage=pagefactory.initialize("LoginPage");
		HomePage=pagefactory.initialize("HomePage");
		MpsWorkTypePage=pagefactory.initialize("MpsWorkTypePage");
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username",LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password",LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");	
		HomePage.verifyTextValue("Title", "Store Inventory Operations Cloud Service");
		HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
		HomePage.storeIdCheck();
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.click("Navigation");
		HomePage.click("Admin");
		HomePage.click("TechnicalMaintenance");
		HomePage.click("MPSWorkType");
	}

	@DataProvider(name = "TitleBarTestData")
	public Object[][] getAddAttributeTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("MPSWorkTypeTestData"),
				"TitleBar");
		return testObjArray;
	}

	@Test(dataProvider="TitleBarTestData")
	public void verifyTitleBar(Map<String,String> map) throws Exception {
		logger.info("Method Name: verifyTitleBar");

		//Validating the title of the page
		MpsWorkTypePage.explicitWaitForVisibility("Title");
		MpsWorkTypePage.verifyTextValue("Title",map.get("MPSWorkTypeTitle"));

		//verify save button is present
		MpsWorkTypePage.verifyElementIsPresent("SaveButton");

		//Validating Save button
		MpsWorkTypePage.verifyElementIsDisabled("SaveButton");

		//verify refresh button is present
		MpsWorkTypePage.verifyElementIsPresent("RefreshButton");

		//Validating Refresh button
		MpsWorkTypePage.verifyElementIsEnabled("RefreshButton");

		//Selecting a record
		MpsWorkTypePage.explicitWaitForElementToBeClickable("FilterWorkType");
		MpsWorkTypePage.click("FilterWorkType");
		MpsWorkTypePage.enterIntoTextBox("FilterWorkType",map.get("WorkTypeData"));
		MpsWorkTypePage.click("GridRecord");

		//Modifying a selected record
		MpsWorkTypePage.explicitWaitForElementToBeClickable("EditButton");
		MpsWorkTypePage.click("EditButton");
		MpsWorkTypePage.explicitWaitForElementToBeClickable("RetryLimitTextbox");
		MpsWorkTypePage.click("RetryLimitTextbox");
		MpsWorkTypePage.enterIntoTextBox("RetryLimitTextbox",map.get("RetryLimitData"));
		MpsWorkTypePage.click("ApplyButton");

		//Clicking on a refresh button
		MpsWorkTypePage.click("RefreshButton");

		//Clicking on a cancel button of refresh dialog box
		MpsWorkTypePage.verifyTextValue("RefreshDialogboxText",map.get("RefreshDialogboxText"));		
		MpsWorkTypePage.click("RefreshCancelButton");

		//Verifying changes are not discarded after click on a cancel button
		MpsWorkTypePage.explicitWaitForElementToBeClickable("FilterRetryLimit");
		MpsWorkTypePage.click("FilterRetryLimit");
		MpsWorkTypePage.enterIntoTextBox("FilterRetryLimit",map.get("RetryLimitData"));
		MpsWorkTypePage.explicitWaitForElementToBeClickable("GridRecord");
		MpsWorkTypePage.verifyTextValue("GridRecordRetryLimit",map.get("RetryLimitData"));

		//Clicking on a refresh button and click on a ok 
		MpsWorkTypePage.click("RefreshButton");
		MpsWorkTypePage.click("OkButton");
		//Fetches data from DB
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));

		//Verifying changes are discarded after click on a ok button
		MpsWorkTypePage.click("FilterRetryLimit");
		MpsWorkTypePage.enterIntoTextBox("FilterRetryLimit",map.get("RetryLimitData"));
		MpsWorkTypePage.verifyTextValue("NoRowsMsg",map.get("NoRowsMsg"));

		//verifying the filter option
		MpsWorkTypePage.verifyElementIsPresent("FilterWorkType");
		MpsWorkTypePage.verifyElementIsPresent("FilterDirection");
		MpsWorkTypePage.verifyElementIsPresent("FilterActive");
		MpsWorkTypePage.verifyElementIsPresent("FilterRetryLimit");
		MpsWorkTypePage.verifyElementIsPresent("FilterPendingCount");
		MpsWorkTypePage.verifyElementIsPresent("FilterRetryCount");
		MpsWorkTypePage.verifyElementIsPresent("FilterFailCount");
		MpsWorkTypePage.verifyElementIsPresent("FilterLastUpdate");
		MpsWorkTypePage.verifyElementIsPresent("FilterLastNew");
		MpsWorkTypePage.scrollToViewElement("FilterPurgeProcessed");
		MpsWorkTypePage.verifyElementIsPresent("FilterRetryDelaySecs");
		MpsWorkTypePage.verifyElementIsPresent("FilterRetryDelayMaxSecs");
		MpsWorkTypePage.verifyElementIsPresent("FilterRetryDelayFactor");
		MpsWorkTypePage.verifyElementIsPresent("FilterRetryDelayRandom");
		MpsWorkTypePage.verifyElementIsPresent("FilterPurgeProcessed");

		//Clicking on a row selector
		MpsWorkTypePage.explicitWaitForElementToBeClickable("GridViewMenu");		
		MpsWorkTypePage.click("GridViewMenu");
		MpsWorkTypePage.click("RowSelector");
		MpsWorkTypePage.implicitWait();

		//Selecting a record to edit
		MpsWorkTypePage.explicitWaitForElementToBeClickable("FilterWorkType");
		MpsWorkTypePage.click("FilterWorkType");
		MpsWorkTypePage.enterIntoTextBox("FilterWorkType",map.get("WorkTypeData"));
		MpsWorkTypePage.verifyElementIsPresent("RowSelectorCheckBox");
		MpsWorkTypePage.click("RowSelectorCheckBox");

		//Modifying a record 
		MpsWorkTypePage.explicitWaitForElementToBeClickable("EditButton");
		MpsWorkTypePage.click("EditButton");
		MpsWorkTypePage.explicitWaitForElementToBeClickable("RetryLimitTextbox");
		MpsWorkTypePage.click("RetryLimitTextbox");
		MpsWorkTypePage.enterIntoTextBox("RetryLimitTextbox",map.get("RetryLimitData"));
		MpsWorkTypePage.click("ApplyButton");

		//Clicking on a save button
		MpsWorkTypePage.verifyElementIsEnabled("SaveButton");
		MpsWorkTypePage.click("SaveButton");
		MpsWorkTypePage.verifyTextValue("SaveConfirmationText",map.get("SaveConfirmationText"));
		MpsWorkTypePage.verifyElementIsPresent("YesButton");
		MpsWorkTypePage.verifyElementIsPresent("NoButton");

		//Clicking on a no button and verifying a record
		MpsWorkTypePage.click("NoButton");
		MpsWorkTypePage.explicitWaitForElementToBeClickable("FilterRetryLimit");
		MpsWorkTypePage.click("FilterRetryLimit");
		MpsWorkTypePage.enterIntoTextBox("FilterRetryLimit",map.get("RetryLimitData"));
		MpsWorkTypePage.explicitWaitForElementToBeClickable("GridRecord");
		MpsWorkTypePage.verifyTextValue("GridRecordRetryLimit",map.get("RetryLimitData"));		
		MpsWorkTypePage.verifyElementIsEnabled("SaveButton");

		//verifying a changes are discarded after refresh 
		MpsWorkTypePage.click("RefreshButton");
		MpsWorkTypePage.click("OkButton");

		//Fetches data from DB
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		MpsWorkTypePage.verifyElementIsDisabled("SaveButton");
		MpsWorkTypePage.explicitWaitForElementToBeClickable("FilterRetryLimit");
		MpsWorkTypePage.click("FilterRetryLimit");
		MpsWorkTypePage.enterIntoTextBox("FilterRetryLimit",map.get("RetryLimitData"));	
		MpsWorkTypePage.verifyTextValue("NoRowsMsg",map.get("NoRowsMsg"));
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
