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

public class BatchSchedulerApply {
	public static Logger logger=Logger.getLogger(BatchSchedulerApply.class.getName());
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

	@DataProvider(name = "BatchSchedulerApplyTestData")
	public Object[][] getBatchSchedulerApplyTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("JobSchedulerTestData"),
				"BatchSchedulerApply");
		return testObjArray;
	}

	@Test(dataProvider="BatchSchedulerApplyTestData")
	public void applyBatchScheduler(Map<String,String> map) throws Exception {
		//Navigating to the job scheduler screen
		logger.info("Method Name: applyBatchScheduler");
		HomePage.click("Admin");
		HomePage.click("TechnicalMaintenance");
		HomePage.click("JobScheduler");

		//verifying the title of the screen
		JobSchedulerPage.explicitWaitForElementToBeClickable("Title");
		JobSchedulerPage.verifyTextValue("Title",map.get("Title"));
		JobSchedulerPage.implicitWait();

		//Selecting a Job name
		JobSchedulerPage.explicitWaitForElementToBeClickable("JobNameColumnData");
		JobSchedulerPage.explicitWaitForElementToBeClickable("FilterJobName");
		JobSchedulerPage.click("FilterJobName");
		JobSchedulerPage.enterIntoTextBox("FilterJobName",map.get("JobName"));
		JobSchedulerPage.explicitWaitForElementToBeClickable("GridRecord");
		JobSchedulerPage.click("GridRecord");

		//clicking on Edit button in detail pane
		JobSchedulerPage.explicitWaitForElementToBeClickable("EditButton");
		JobSchedulerPage.click("EditButton");

		//Verifying Apply and Cancel option must be enabled
		JobSchedulerPage.explicitWaitForElementToBeClickable("ApplyButton");
		JobSchedulerPage.verifyElementIsEnabled("ApplyButton");
		JobSchedulerPage.verifyElementIsEnabled("CancelButton");
		JobSchedulerPage.implicitWait();

		//Updating interval value
		JobSchedulerPage.explicitWaitForElementToBeClickable("IntervalDropDown");
		JobSchedulerPage.selectDropDownValue("IntervalDropDown",map.get("Interval"));
		JobSchedulerPage.explicitWaitForElementToBeClickable("ApplyButton");
		JobSchedulerPage.click("ApplyButton");

		//Verifying the updated interval value
		JobSchedulerPage.explicitWaitForElementToBeClickable("IntervalRecord");
		JobSchedulerPage.verifyTextValue("IntervalRecord",map.get("Interval"));

		//Clicking on refresh button
		JobSchedulerPage.explicitWaitForElementToBeClickable("RefreshButton");
		JobSchedulerPage.click("RefreshButton");
		JobSchedulerPage.explicitWaitForElementToBeClickable("OkButton");
		JobSchedulerPage.click("OkButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));

		//Selecting a Job name
		JobSchedulerPage.explicitWaitForElementToBeClickable("FilterJobName");
		JobSchedulerPage.click("FilterJobName");
		JobSchedulerPage.enterIntoTextBox("FilterJobName",map.get("JobName"));
		JobSchedulerPage.explicitWaitForElementToBeClickable("GridRecordJobName");
		JobSchedulerPage.click("GridRecordJobName");

		//clicking on Edit button in detail pane
		JobSchedulerPage.explicitWaitForElementToBeClickable("EditButton");
		JobSchedulerPage.click("EditButton");

		//Updating the enabled value
		JobSchedulerPage.explicitWaitForElementToBeClickable("EnabledSwitchValue");
		String enabledValue=JobSchedulerPage.getText("EnabledSwitchValue");
		System.out.println(enabledValue);
		JobSchedulerPage.click("EnabledSwitchButton");
		JobSchedulerPage.explicitWaitForElementToBeClickable("ApplyButton");
		JobSchedulerPage.click("ApplyButton");

		//Verifying the enabled value is updated
		JobSchedulerPage.explicitWaitForElementToBeClickable("EnabledRecord");
		if(enabledValue.equals(map.get("EnabledYes"))) {
			JobSchedulerPage.verifyTextValue("EnabledRecord",map.get("EnabledNo"));
		}
		else {
			JobSchedulerPage.verifyTextValue("EnabledRecord",map.get("EnabledYes"));			
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
