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

public class MpsWorkTypeDetailApplyBlockCancel {
	public static Logger logger=Logger.getLogger(MpsWorkTypeDetailApplyBlockCancel.class.getName());
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

	@DataProvider(name = "MPSWorkTypeTestData")
	public Object[][] getMPSWorkTypeTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("MPSWorkTypeTestData"),
				"DetailCancel");
		return testObjArray;
	}

	@Test(dataProvider="MPSWorkTypeTestData")
	public void cancelMpsWorkType(Map<String,String> map) throws Exception {
		logger.info("Method Name: cancelMpsWorkType");

		//Verifying page title
		MpsWorkTypePage.explicitWaitForElementToBeClickable("Title");
		MpsWorkTypePage.verifyTextValue("Title",map.get("MPSWorkTypeTitle"));
		HomePage.click("Navigation");

		//Without selecting any record click on Cancel button
		MpsWorkTypePage.verifyElementIsDisabled("CancelButton");

		//Select a record and validate
		MpsWorkTypePage.explicitWaitForElementToBeClickable("FilterWorkType");
		MpsWorkTypePage.click("FilterWorkType");
		MpsWorkTypePage.enterIntoTextBox("FilterWorkType",map.get("WorkTypeData"));		
		MpsWorkTypePage.click("GridRecord");
		MpsWorkTypePage.verifyElementIsDisabled("CancelButton");

		//Click on Edit button to validate
		MpsWorkTypePage.explicitWaitForElementToBeClickable("EditButton");
		MpsWorkTypePage.click("EditButton");

		//Verifying cancel button after the click of Edit button
		MpsWorkTypePage.verifyElementIsEnabled("CancelButton");

		//Modifying the retry limit value for a selected record
		MpsWorkTypePage.explicitWaitForElementToBeClickable("RetryLimitTextbox");
		MpsWorkTypePage.click("RetryLimitTextbox");
		MpsWorkTypePage.enterIntoTextBox("RetryLimitTextbox",map.get("RetryLimitData"));
		MpsWorkTypePage.implicitWait();

		//Modifying the Retry Delay Seconds value for a selected record
		MpsWorkTypePage.explicitWaitForElementToBeClickable("RetryDelaySecsTextbox");
		MpsWorkTypePage.click("RetryDelaySecsTextbox");
		MpsWorkTypePage.enterIntoTextBox("RetryDelaySecsTextbox",map.get("RetryDelaySecsData"));
		MpsWorkTypePage.implicitWait();

		//Modifying the Retry Delay Max Seconds value for a selected record
		MpsWorkTypePage.explicitWaitForElementToBeClickable("RetryDelayMaxSecsTextbox");
		MpsWorkTypePage.click("RetryDelayMaxSecsTextbox");
		MpsWorkTypePage.enterIntoTextBox("RetryDelayMaxSecsTextbox",map.get("RetryDelayMaxSecsData"));
		MpsWorkTypePage.implicitWait();

		//Modifying the Retry Delay Factor value for a selected record
		MpsWorkTypePage.explicitWaitForElementToBeClickable("RetryDelayFactorTextbox");
		MpsWorkTypePage.click("RetryDelayFactorTextbox");
		MpsWorkTypePage.enterIntoTextBox("RetryDelayFactorTextbox",map.get("RetryDelayFactorData"));
		MpsWorkTypePage.implicitWait();

		//Modifying the Retry Delay Random value for a selected record
		MpsWorkTypePage.explicitWaitForElementToBeClickable("RetryDelayRandomTextbox");
		MpsWorkTypePage.click("RetryDelayRandomTextbox");
		MpsWorkTypePage.enterIntoTextBox("RetryDelayRandomTextbox",map.get("RetryDelayRandomData"));
		MpsWorkTypePage.implicitWait();

		//Clicking on cancel after editing a record
		MpsWorkTypePage.click("CancelButton");	

		//Select a record and validate
		MpsWorkTypePage.explicitWaitForElementToBeClickable("FilterWorkType");
		MpsWorkTypePage.click("FilterWorkType");
		MpsWorkTypePage.enterIntoTextBox("FilterWorkType",map.get("WorkTypeData"));

		//Verifying a record values are not updated after clicking on cancel
		MpsWorkTypePage.explicitWaitForElementToBeClickable("RetryDelayRandomRecord");
		MpsWorkTypePage.verifyValuesAreNotEqual("RetryLimitRecord",map.get("RetryLimitData"));
		MpsWorkTypePage.verifyValuesAreNotEqual("RetryDelaySecsRecord",map.get("RetryDelaySecsData"));
		MpsWorkTypePage.verifyValuesAreNotEqual("RetryDelayMaxSecsRecord",map.get("RetryDelayMaxSecsData"));
		MpsWorkTypePage.verifyValuesAreNotEqual("RetryDelayFactorRecord",map.get("RetryDelayFactorData"));
		MpsWorkTypePage.verifyValuesAreNotEqual("RetryDelayRandomRecord",map.get("RetryDelayRandomData"));

		//Verifying a record values are read only
		MpsWorkTypePage.verifyReadOnly("RetryLimitRecord");
		MpsWorkTypePage.verifyReadOnly("RetryDelaySecsRecord");
		MpsWorkTypePage.verifyReadOnly("RetryDelayMaxSecsRecord");
		MpsWorkTypePage.verifyReadOnly("RetryDelayFactorRecord");
		MpsWorkTypePage.verifyReadOnly("RetryDelayRandomRecord");		
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
