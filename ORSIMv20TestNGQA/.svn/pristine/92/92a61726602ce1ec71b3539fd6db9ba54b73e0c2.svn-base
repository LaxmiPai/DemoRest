package com.oracle.sim.testcases.JobScheduler;

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

public class BatchSchedulerJobExecutionTime {
	public static Logger logger=Logger.getLogger(BatchSchedulerJobExecutionTime.class.getName());
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

	@DataProvider(name = "JobExecutionTimeTestData")
	public Object[][] getBatchSchedulerApplyTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("JobSchedulerTestData"),
				"JobExecutionTime");
		return testObjArray;
	}

	@Test(dataProvider="JobExecutionTimeTestData")
	public void verifyJobExecutionTime(Map<String,String> map) throws Exception {
		//Navigating to the job scheduler screen
		logger.info("Method Name: verifyJobExecutionTime");
		HomePage.click("Admin");
		HomePage.click("TechnicalMaintenance");
		HomePage.click("JobScheduler");

		//verifying the title of the screen
		JobSchedulerPage.explicitWaitForElementToBeClickable("Title");
		JobSchedulerPage.verifyTextValue("Title",map.get("Title"));

		//Selecting a job name 
		JobSchedulerPage.explicitWaitForElementToBeClickable("FilterJobName");
		JobSchedulerPage.explicitWaitForElementToBeClickable("JobNameColumnData");
		JobSchedulerPage.click("FilterJobName");
		JobSchedulerPage.enterIntoTextBox("FilterJobName",map.get("EnabledJobName"));

		//Selecting an enabled job
		JobSchedulerPage.explicitWaitForElementToBeClickable("FilterEnabled");
		JobSchedulerPage.click("FilterEnabled");
		JobSchedulerPage.enterIntoTextBox("FilterEnabled",map.get("EnabledYes"));

		//Verifying the execution time for a job that is enabled
		if(JobSchedulerPage.isVisible("GridRecordJobName")&&JobSchedulerPage.isVisible("GridRecordEnabled")) {
			if(JobSchedulerPage.getText("ExecutionTimeRecord").equals(map.get("ExecutionTimeRecord"))) {
				System.out.println("The job has no execution time");				
				Assert.fail();
			}
			else {
				System.out.println(JobSchedulerPage.getText("ExecutionTimeRecord"));
			}
		}
		else {
			System.out.println("No job is available with enabled status");				
		}

		//Filtering a job name 
		JobSchedulerPage.explicitWaitForElementToBeClickable("FilterJobName");
		JobSchedulerPage.click("FilterJobName");
		JobSchedulerPage.enterIntoTextBox("FilterJobName",map.get("DisabledJobName"));

		//Filtering an enabled value as no
		JobSchedulerPage.explicitWaitForElementToBeClickable("FilterEnabled");
		JobSchedulerPage.click("FilterEnabled");
		JobSchedulerPage.enterIntoTextBox("FilterEnabled",map.get("EnabledNo"));

		//Verifying the execution time for a job that is disabled
		if(JobSchedulerPage.isVisible("GridRecordJobName")&&JobSchedulerPage.isVisible("GridRecordEnabled")) {
			if(JobSchedulerPage.getText("ExecutionTimeRecord").equals(map.get("ExecutionTimeRecord"))) {
				System.out.println("Expected: Actual:\n Execution time is empty");		
			}
			else {
				System.out.println("Expected: Actual:"+JobSchedulerPage.getText("ExecutionTimeRecord"));
				System.out.println("The execution time must be empty for disabled job");
				Assert.fail();
			}
		}
		else {
			System.out.println("No job is available with disabled status");				
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
