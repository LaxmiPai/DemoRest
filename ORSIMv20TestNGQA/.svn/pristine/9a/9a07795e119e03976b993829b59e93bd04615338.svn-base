package com.oracle.sim.testcases.JobScheduler;

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

public class BatchSchedulerUi {
	public static Logger logger=Logger.getLogger(BatchSchedulerUi.class.getName());
	protected static PropertyReader propReader=new PropertyReader();
	PageFactory pagefactory=new PageFactory();

	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage JobSchedulerPage;
	@BeforeClass()
	public void setup(ITestContext context) throws Exception {
		logger.info("TestCase Name: " +logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
		LoginPage=pagefactory.initialize("LoginPage");
		HomePage=pagefactory.initialize("HomePage");
		JobSchedulerPage=pagefactory.initialize("JobSchedulerPage");
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username",LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password",LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");		
		HomePage.verifyTextValue("Title", "Store Inventory Operations Cloud Service");
		HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
		HomePage.storeIdCheck();
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.click("Navigation");
	}

	@DataProvider(name = "BatchSchedulerUiTestData")
	public Object[][] getBatchSchedulerUiTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("JobSchedulerTestData"),
				"BatchSchedulerUi");
		return testObjArray;
	}

	@Test(dataProvider="BatchSchedulerUiTestData")
	public void verifyBatchSchedulerUi(Map<String,String> map) throws Exception {
		//Navigating to the job scheduler screen
		logger.info("Method Name: verifyBatchSchedulerUi");
		HomePage.click("Admin");
		HomePage.click("TechnicalMaintenance");
		HomePage.click("JobScheduler");

		//Verifying job scheduler screen is present 
		JobSchedulerPage.explicitWaitForElementToBeClickable("Title");
		JobSchedulerPage.verifyElementIsPresent("Title");

		//verifying the title of the screen
		JobSchedulerPage.verifyTextValue("Title",map.get("Title"));

		//verifying the save button is disabled
		JobSchedulerPage.explicitWaitForElementToBeClickable("JobNameColumnData");
		JobSchedulerPage.verifyElementIsDisabled("SaveButton");
		JobSchedulerPage.implicitWait();

		//verifying the refresh button is enabled
		JobSchedulerPage.verifyElementIsEnabled("RefreshButton");

		//Verifying the filter is available
		JobSchedulerPage.explicitWaitForElementToBeClickable("GridViewMenu");
		JobSchedulerPage.click("GridViewMenu");
		JobSchedulerPage.explicitWaitForElementToBeClickable("ColumnFilter");
		JobSchedulerPage.verifyElementIsPresent("ColumnFilter");

		//Verifying the row selector is not available
		JobSchedulerPage.verifyElementIsNotPresent("RowSelector");
		JobSchedulerPage.click("GridViewMenu");

		//Verifying the List field available
		JobSchedulerPage.verifyTextValue("JobName",map.get("JobName"));
		JobSchedulerPage.verifyTextValue("Enabled",map.get("Enabled"));
		JobSchedulerPage.verifyTextValue("Interval",map.get("Interval"));
		JobSchedulerPage.verifyTextValue("ExecutionTime",map.get("ExecutionTime"));

		//Verifying the fields available in Detail Block
		JobSchedulerPage.verifyTextValue("DetailJobName",map.get("JobName"));
		JobSchedulerPage.verifyTextValue("DetailDescription",map.get("Description"));
		JobSchedulerPage.verifyTextValue("DetailInterval",map.get("Interval"));
		JobSchedulerPage.verifyTextValue("DetailEnabled",map.get("Enabled"));

		//Clicking on Filter grid button and verifying filter option is disabled
		JobSchedulerPage.explicitWaitForElementToBeClickable("GridViewMenu");
		JobSchedulerPage.click("GridViewMenu");
		JobSchedulerPage.explicitWaitForElementToBeClickable("ColumnFilter");
		JobSchedulerPage.click("ColumnFilter");
		JobSchedulerPage.verifyElementIsNotPresent("FilterJobName");

		//Clicking on Filter grid button and verifying filter option is enabled
		JobSchedulerPage.explicitWaitForElementToBeClickable("GridViewMenu");
		JobSchedulerPage.click("GridViewMenu");
		JobSchedulerPage.explicitWaitForElementToBeClickable("ColumnFilter");
		JobSchedulerPage.click("ColumnFilter");
		JobSchedulerPage.verifyElementIsPresent("FilterJobName");

		//Verifying the Number of Jobs listed in Batch Scheduler
		int rowCount=JobSchedulerPage.getTableRowCount("JobNameColumnData","ExecutionTime","FilterJobName");
		if(rowCount==0) {
			System.out.println("No table records found");
		}
		else {
			System.out.println("Table Records Count: "+rowCount);

			//Below all steps are not in test steps 
			//Selecting a job name 
			JobSchedulerPage.explicitWaitForElementToBeClickable("FilterJobName");
			JobSchedulerPage.explicitWaitForElementToBeClickable("JobNameColumnData");
			JobSchedulerPage.click("FilterJobName");
			JobSchedulerPage.enterIntoTextBox("FilterJobName",map.get("JobNameData"));

			//Clicking on grid record
			JobSchedulerPage.explicitWaitForElementToBeClickable("GridRecordJobName");
			JobSchedulerPage.click("GridRecordJobName");

			//Verifying detail text fields
			JobSchedulerPage.verifyElementIsPresent("JobNameTextBox");
			JobSchedulerPage.verifyElementIsPresent("DescriptionTextBox");
			JobSchedulerPage.verifyElementIsPresent("IntervalDropDown");
			JobSchedulerPage.verifyElementIsPresent("EnabledSwitchButton");

			//Verifying Detail buttons
			JobSchedulerPage.verifyElementIsPresent("EditButton");
			JobSchedulerPage.verifyElementIsPresent("ApplyButton");
			JobSchedulerPage.verifyElementIsPresent("CancelButton");
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
