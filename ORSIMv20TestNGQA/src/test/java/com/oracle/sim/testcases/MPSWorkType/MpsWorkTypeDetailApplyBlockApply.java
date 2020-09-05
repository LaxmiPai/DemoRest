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

public class MpsWorkTypeDetailApplyBlockApply {
	public static Logger logger=Logger.getLogger(MpsWorkTypeDetailApplyBlockApply.class.getName());
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

	@DataProvider(name = "ApplyTestData")
	public Object[][] getApplyMpsWorkTypeTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("MPSWorkTypeTestData"),
				"DetailApply");
		return testObjArray;
	}

	@Test(dataProvider="ApplyTestData")
	public void applyMpsWorkType(Map<String,String> map) throws Exception {
		logger.info("Method Name: applyMpsWorkType");
		MpsWorkTypePage.explicitWaitForElementToBeClickable("Title");
		MpsWorkTypePage.verifyTextValue("Title",map.get("MPSWorkTypeTitle"));

		//without selecting a record click on apply 
		MpsWorkTypePage.verifyElementIsDisabled("ApplyButton");
		MpsWorkTypePage.explicitWaitForVisibility("WorkTypeRecord");

		//Selecting a record and validating
		MpsWorkTypePage.explicitWaitForElementToBeClickable("WorkTypeRecord");
		MpsWorkTypePage.explicitWaitForElementToBeClickable("FilterWorkType");
		MpsWorkTypePage.click("FilterWorkType");
		MpsWorkTypePage.enterIntoTextBox("FilterWorkType",map.get("WorkTypeData"));		
		MpsWorkTypePage.click("GridRecord");
		MpsWorkTypePage.verifyElementIsDisabled("ApplyButton");

		//displaying the retry limit value before edit
		String record=MpsWorkTypePage.getText("RetryLimitRecord");
		System.out.println("retry limit value before edit "+record);

		//Clicking on edit button to validate
		MpsWorkTypePage.explicitWaitForElementToBeClickable("EditButton");
		MpsWorkTypePage.click("EditButton");

		//Verifying apply button after the click of Edit button
		MpsWorkTypePage.verifyElementIsEnabled("ApplyButton");

		//Clicking on apply after editing a record
		MpsWorkTypePage.explicitWaitForElementToBeClickable("RetryLimitTextbox");
		MpsWorkTypePage.click("RetryLimitTextbox");
		MpsWorkTypePage.enterIntoTextBox("RetryLimitTextbox",map.get("RetryLimitData"));
		MpsWorkTypePage.click("ApplyButton");

		//Clicking yes on save confirmation dialog box after apply
		MpsWorkTypePage.explicitWaitForElementToBeClickable("SaveButton");
		MpsWorkTypePage.click("SaveButton");
		MpsWorkTypePage.explicitWaitForElementToBeClickable("YesButton");
		MpsWorkTypePage.verifyTextValue("SaveConfirmationText",map.get("SaveConfirmationText"));
		MpsWorkTypePage.click("YesButton");
		//db commit takes more time 
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));

		//Verifying a record value is updated after apply
		MpsWorkTypePage.explicitWaitForElementToBeClickable("FilterWorkType");
		MpsWorkTypePage.click("FilterWorkType");
		MpsWorkTypePage.enterIntoTextBox("FilterWorkType",map.get("WorkTypeData"));				 
		MpsWorkTypePage.explicitWaitForElementToBeClickable("FilterRetryLimit");
		MpsWorkTypePage.click("FilterRetryLimit");
		MpsWorkTypePage.enterIntoTextBox("FilterRetryLimit",map.get("RetryLimitData"));
		MpsWorkTypePage.verifyTextValue("GridRecordRetryLimit",map.get("RetryLimitData"));

		//Verifying a record is read only 
		MpsWorkTypePage.verifyReadOnly("RetryLimitRecord");

		//Clicking on a edit button
		MpsWorkTypePage.click("GridRecordRetryLimit");
		MpsWorkTypePage.explicitWaitForElementToBeClickable("EditButton");
		MpsWorkTypePage.click("EditButton");	

		//Clicking on apply after editing a record
		MpsWorkTypePage.explicitWaitForElementToBeClickable("RetryLimitTextbox");
		MpsWorkTypePage.click("RetryLimitTextbox");
		MpsWorkTypePage.enterIntoTextBox("RetryLimitTextbox",record);
		MpsWorkTypePage.click("ApplyButton");

		//Clicking yes on save confirmation dialog box after apply
		MpsWorkTypePage.explicitWaitForElementToBeClickable("SaveButton");
		MpsWorkTypePage.click("SaveButton");
		MpsWorkTypePage.explicitWaitForElementToBeClickable("YesButton");
		MpsWorkTypePage.verifyTextValue("SaveConfirmationText",map.get("SaveConfirmationText"));
		MpsWorkTypePage.click("YesButton");
		//db commit takes more time 
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));

		//Verifying a record value is updated after apply
		MpsWorkTypePage.explicitWaitForElementToBeClickable("FilterWorkType");
		MpsWorkTypePage.click("FilterWorkType");
		MpsWorkTypePage.enterIntoTextBox("FilterWorkType",map.get("WorkTypeData"));		
		MpsWorkTypePage.explicitWaitForElementToBeClickable("FilterRetryLimit");
		MpsWorkTypePage.click("FilterRetryLimit");
		MpsWorkTypePage.enterIntoTextBox("FilterRetryLimit",record);
		MpsWorkTypePage.verifyTextValue("GridRecordRetryLimit",record);

		//Clicking on a edit button
		MpsWorkTypePage.click("GridRecordRetryLimit");
		MpsWorkTypePage.explicitWaitForElementToBeClickable("EditButton");
		MpsWorkTypePage.click("EditButton");	

		//entering smallest values into all Text box and clicking on apply button
		MpsWorkTypePage.enterIntoTextBox("RetryDelaySecsTextbox",map.get("RetryMinVal"));
		MpsWorkTypePage.enterIntoTextBox("RetryDelayMaxSecsTextbox",map.get("RetryMinVal"));
		MpsWorkTypePage.enterIntoTextBox("RetryDelayFactorTextbox",map.get("RetryMinVal"));
		MpsWorkTypePage.enterIntoTextBox("RetryDelayRandomTextbox",map.get("RetryMinVal"));
		MpsWorkTypePage.implicitWait();
		MpsWorkTypePage.click("ApplyButton");	

		//Verifying the error message for smallest values
		MpsWorkTypePage.verifyTextValue("ApplyErrorMsgRetryDelaySecs",map.get("RetryMinErrorMsg"));
		MpsWorkTypePage.verifyTextValue("ApplyErrorMsgRetryDelayMaxSecs",map.get("RetryMinErrorMsg"));
		MpsWorkTypePage.verifyTextValue("ApplyErrorMsgRetryDelayFactor",map.get("RetryMinErrorMsg"));
		MpsWorkTypePage.verifyTextValue("ApplyErrorMsgRetryDelayRandom",map.get("RetryMinErrorMsg"));

		//entering largest values into all Text box and clicking on apply button
		MpsWorkTypePage.enterIntoTextBox("RetryDelaySecsTextbox",map.get("SecondsMaxVal"));
		MpsWorkTypePage.enterIntoTextBox("RetryDelayMaxSecsTextbox",map.get("SecondsMaxVal"));
		MpsWorkTypePage.enterIntoTextBox("RetryDelayFactorTextbox",map.get("FactorRandomMaxVal"));
		MpsWorkTypePage.enterIntoTextBox("RetryDelayRandomTextbox",map.get("FactorRandomMaxVal"));
		MpsWorkTypePage.implicitWait();
		MpsWorkTypePage.click("ApplyButton");

		//Verifying the error message for largest values
		MpsWorkTypePage.verifyTextValue("ApplyErrorMsgRetryDelaySecs",map.get("DelaySecondsMaxErrorMsg"));
		MpsWorkTypePage.verifyTextValue("ApplyErrorMsgRetryDelayMaxSecs",map.get("DelaySecondsMaxErrorMsg"));
		MpsWorkTypePage.verifyTextValue("ApplyErrorMsgRetryDelayFactor",map.get("FactorRandomErrorMsg"));
		MpsWorkTypePage.verifyTextValue("ApplyErrorMsgRetryDelayRandom",map.get("FactorRandomErrorMsg"));
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
