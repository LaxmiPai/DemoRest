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

public class MpsWorkTypeTableListField {
	public static Logger logger=Logger.getLogger(MpsWorkTypeTableListField.class.getName());
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

	@DataProvider(name = "TableListFieldsTestData")
	public Object[][] getAddAttributeTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("MPSWorkTypeTestData"),
				"TableListFields");
		return testObjArray;
	}

	@Test(dataProvider="TableListFieldsTestData", priority=1)
	public void verifyTableFields(Map<String, String> map) throws Exception {
		logger.info("Method Name: verifyTableFields");

		//Verifying page title
		MpsWorkTypePage.explicitWaitForVisibility("Title");
		MpsWorkTypePage.verifyTextValue("Title",map.get("MPSWorkTypeTitle"));

		//Validate list of fields in the Table
		MpsWorkTypePage.verifyTextValue("WorkType",map.get("WorkType"));
		MpsWorkTypePage.verifyTextValue("Direction",map.get("Direction"));
		MpsWorkTypePage.verifyTextValue("Active",map.get("Active"));
		MpsWorkTypePage.verifyTextValue("RetryLimit",map.get("RetryLimit"));
		MpsWorkTypePage.verifyTextValue("PendingCount",map.get("PendingCount"));
		MpsWorkTypePage.verifyTextValue("RetryCount",map.get("RetryCount"));
		MpsWorkTypePage.verifyTextValue("FailCount",map.get("FailCount"));
		MpsWorkTypePage.verifyTextValue("LastUpdate",map.get("LastUpdate"));
		MpsWorkTypePage.verifyTextValue("LastNew",map.get("LastNew"));
		MpsWorkTypePage.scrollToViewElement("PurgeProcessed");
		MpsWorkTypePage.verifyTextValue("RetryDelaySecs",map.get("RetryDelaySecs"));
		MpsWorkTypePage.verifyTextValue("RetryDelayMax",map.get("RetryDelayMax"));
		MpsWorkTypePage.verifyTextValue("RetryDelayFactor",map.get("RetryDelayFactor"));
		MpsWorkTypePage.verifyTextValue("RetryDelayRandom",map.get("RetryDelayRandom"));
		MpsWorkTypePage.verifyTextValue("PurgeProcessed",map.get("PurgeProcessed"));
		MpsWorkTypePage.scrollToViewElement("WorkType");

		//verifying all table records read only
		MpsWorkTypePage.verifyAllTableRecordsReadOnly("WorkTypeRecord","WorkType","FilterWorkType");

		//Checking field Work Type
		MpsWorkTypePage.verifyID("WorkTypeMsgFamily","messageFamily");

		//selecting a record 
		MpsWorkTypePage.explicitWaitForElementToBeClickable("FilterWorkType");
		MpsWorkTypePage.click("FilterWorkType");
		MpsWorkTypePage.enterIntoTextBox("FilterWorkType",map.get("WorkTypeData"));

		//Selecting a record for field verification
		MpsWorkTypePage.explicitWaitForElementToBeClickable("FilterRetryLimit");
		MpsWorkTypePage.click("FilterRetryLimit");
		MpsWorkTypePage.enterIntoTextBox("FilterRetryLimit",map.get("GlobalRetryLimitVal"));

		//Verifying Retry Limit field
		MpsWorkTypePage.verifyTextValue("GridRecordRetryLimit",map.get("GlobalRetryLimitVal"));

		//Verifying Direction field
		MpsWorkTypePage.verifyDirectionField("DirectionRecord");

		//Verifying Active field
		MpsWorkTypePage.verifyActiveField("ActiveRecord");

		//Verifying Retry Limit field
		MpsWorkTypePage.verifyRetryField("RetryLimitRecord",map.get("RetryMinVal"),map.get("RetryLimitMaxVal"));
		MpsWorkTypePage.scrollToViewElement("RetryDelaySecs");

		//Verifying Retry Delay Seconds field
		MpsWorkTypePage.verifyRetryField("RetryDelaySecsRecord",map.get("RetryMinVal"),map.get("SecondsMaxVal"));

		//Verifying Retry Delay Max Seconds field
		MpsWorkTypePage.verifyRetryField("RetryDelayMaxSecsRecord",map.get("RetryMinVal"),map.get("SecondsMaxVal"));

		//Verifying Retry Delay Factor field
		MpsWorkTypePage.verifyFactorRandomField("RetryDelayFactorRecord",map.get("RetryMinVal"),map.get("FactorRandomMaxVal"));

		//Verifying Retry Delay Random field
		MpsWorkTypePage.verifyFactorRandomField("RetryDelayRandomRecord",map.get("RetryMinVal"),map.get("FactorRandomMaxVal"));

		//Verifying Purge Processed
		MpsWorkTypePage.verifyPurgeProcessedField("PurgeProcessedRecord");
		MpsWorkTypePage.click("RefreshButton");
		//Fetches data from DB
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));

		//Validating default sorting of records
		MpsWorkTypePage.columnSorting("WorkTypeRecord","WorkType","ascending");

		//Fetching the pending ,fail retry count details and last update and last new details
		HomePage.click("Navigation");
		MpsWorkTypePage.getPendingFailRetryCount("WorkTypeRecord","WorkType");
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
