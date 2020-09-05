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

public class AddCodeInfo {

	public static Logger logger = Logger.getLogger(AddCodeInfo.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pageFactory = new PageFactory();

	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage CodeInfoPage;

	@BeforeClass
	public void setUp(ITestContext context) throws Exception {
		logger.info("TestCase Name: " + logger.getName());
		// logger.info("Before Class");
		SIMWebdriverBase.loadInitialURL(context);
		// Login Steps

		LoginPage = pageFactory.initialize("LoginPage");
		CodeInfoPage = pageFactory.initialize("CodeInfoPage");
		HomePage = pageFactory.initialize("HomePage");
		// LoginPage.switchToFrame("LoginIframe");
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username", LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password", LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.click("Navigation");
		HomePage.click("Admin");
		HomePage.click("DataSetup");
		HomePage.click("CodeInfo");

	}

	@DataProvider(name = "CodeInfoAdd")
	public Object[][] getTestDataForCodeInfoEdit() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("CodeInfo"),
				"CodeInfoAdd");
		return testObjArray;
	}

	@Test(dataProvider = "CodeInfoAdd", priority = 1)
	public void codeInfoEdit(Map<String, String> map) throws Exception {

		CodeInfoPage.click("RefreshButton");

		// Verify the Title of the Screen..
		CodeInfoPage.verifyTextValue("Title", map.get("Title"));
		CodeInfoPage.verifyElementIsDisabled("Edit");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));

		CodeInfoPage.click("AddButton");

		// Input Code Info values

		CodeInfoPage.searchDropDownValue("CodeDropdown", map.get("CodeType"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		CodeInfoPage.enterIntoTextBox("CodeTextBox", map.get("Code"));
		CodeInfoPage.enterIntoTextBox("DescriptionTextBox", map.get("Description"));
		CodeInfoPage.enterIntoTextBox("SequenceTextBox", map.get("Sequence"));
		CodeInfoPage.explicitWaitForElementToBeClickable("ApplyButton");
		CodeInfoPage.click("ApplyButton");
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
		logger.info("The code info added successfully");
		CodeInfoPage.click("DeleteButton");
		CodeInfoPage.verifyElementIsDisplayed("DeleteConfirmationMessage");
		CodeInfoPage.click("YesButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
		CodeInfoPage.click("SaveButton");
		CodeInfoPage.click("YesButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
		CodeInfoPage.click("RefreshButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
		logger.info("The added CodeInfo is  deleted");
		
	}
	
	@AfterClass
	public void tearDown() {
	SIMWebdriverBase.close();
	}
		
}

