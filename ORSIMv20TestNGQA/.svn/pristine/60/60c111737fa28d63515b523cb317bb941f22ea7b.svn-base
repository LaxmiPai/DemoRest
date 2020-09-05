/**
 * * @author lapai
 * */
package com.oracle.sim.testcases.CodeInfo;

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

public class CodeInfoApply {

	public static Logger logger = Logger.getLogger(CodeInfoApply.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pageFactory = new PageFactory();

	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage CodeInfoPage;

	@BeforeClass
	public void setUp(ITestContext context) throws Exception {
		logger.info("TestCase Name: " + logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
		// Login Steps

		LoginPage = pageFactory.initialize("LoginPage");
		CodeInfoPage = pageFactory.initialize("CodeInfoPage");
		HomePage = pageFactory.initialize("HomePage");
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username", LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password", LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");
		HomePage.verifyTextValue("Title", HomePage.getProperty("Title"));
		HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
		HomePage.storeIdCheck();
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.click("Navigation");
		HomePage.click("Admin");
		HomePage.click("DataSetup");
		HomePage.click("CodeInfo");
	}

	@DataProvider(name = "CodeInfoApply")
	public Object[][] getTestDataForCodeInfoApply() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("CodeInfo"),
				"CodeInfoApply1");
		return testObjArray;
	}

	@Test(dataProvider = "CodeInfoApply", priority = 1)
	public void codeInfoApply(Map<String, String> map) throws Exception {
          
		
		logger.info("Method Name: CodeInfoApply");
		CodeInfoPage.verifyTextValue("Title", map.get("Title"));
		CodeInfoPage.click("RefreshButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		CodeInfoPage.verifyElementIsDisabled("Apply");
		CodeInfoPage.click("GridRecordCodeType");
		CodeInfoPage.verifyElementIsDisabled("Apply");
		CodeInfoPage.click("RefreshButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		CodeInfoPage.click("AddButton");

		// Click on apply button without entering any values and verify the
		// error message
		CodeInfoPage.click("ApplyButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		CodeInfoPage.verifyTextValue("CodeErrorMessage", map.get("ErrorMessage"));
		CodeInfoPage.verifyTextValue("CodeTypeErrorMessage", map.get("ErrorMessage"));
		CodeInfoPage.verifyTextValue("DescriptionErrorMessage", map.get("ErrorMessage"));
		CodeInfoPage.verifyTextValue("SequenceErrorMessage", map.get("ErrorMessage"));
		CodeInfoPage.click("CancelButton2");

		// Add CodeInfovalues and click on apply
		CodeInfoPage.explicitWaitForElementToBeClickable("AddButton");
		CodeInfoPage.click("AddButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		CodeInfoPage.searchDropDownValue("CodeDropdown", map.get("CodeType"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		CodeInfoPage.enterIntoTextBox("CodeTextBox", map.get("Code"));
		CodeInfoPage.enterIntoTextBox("DescriptionTextBox", map.get("Description"));
		CodeInfoPage.enterIntoTextBox("SequenceTextBox", map.get("Sequence"));
		CodeInfoPage.explicitWaitForElementToBeClickable("ApplyButton");
		CodeInfoPage.click("ApplyButton");
		CodeInfoPage.verifyTextValue("GridCode", map.get("Code"));
		CodeInfoPage.verifyTextValue("DescriptionGridRecord", map.get("Description"));
		CodeInfoPage.verifyTextValue("SequenceGridRecord", map.get("Sequence"));
		CodeInfoPage.explicitWaitForElementToBeClickable("SaveButton");
		CodeInfoPage.click("SaveButton");
		CodeInfoPage.explicitWaitForElementToBeClickable("YesButton");
		CodeInfoPage.click("YesButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("High")));
		CodeInfoPage.explicitWaitForElementToBeClickable("FilterName");
		CodeInfoPage.click("FilterName");
		CodeInfoPage.enterIntoTextBox("FilterName", map.get("Code"));
		CodeInfoPage.explicitWaitForElementToBeClickable("GridRecordCodeType");
		CodeInfoPage.click("GridRecordCodeType");
		CodeInfoPage.explicitWaitForElementToBeClickable("DeleteButton");
		CodeInfoPage.click("DeleteButton");
		CodeInfoPage.explicitWaitForElementToBeClickable("YesButton");
		CodeInfoPage.click("YesButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		CodeInfoPage.explicitWaitForElementToBeClickable("SaveButton");
		CodeInfoPage.click("SaveButton");
		CodeInfoPage.explicitWaitForElementToBeClickable("YesButton");
		CodeInfoPage.click("YesButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("High")));

	}

	@AfterClass
	public void tearDown() {

		try {
			logger.info("After Test: Logging out");
			HomePage.click("UserMenu");
			HomePage.explicitWaitForVisibility("Logout");
			HomePage.click("Logout");
			HomePage.explicitWaitForVisibility("Yes");
			HomePage.click("Yes");
		} finally {
			SIMWebdriverBase.close();
		}
	}

}