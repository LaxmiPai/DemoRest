package com.oracle.sim.testcases.CodeInfo;

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

public class CodeInfoGridBar {

	public static Logger logger = Logger.getLogger(CodeInfoGridBar.class.getName());
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
		HomePage = pageFactory.initialize("HomePage");
		CodeInfoPage = pageFactory.initialize("CodeInfoPage");
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

	@DataProvider(name = "CodeInfoGridBarVerify")
	public Object[][] getTestDataForCodeInfoGridBar() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("CodeInfo"),
				"CodeInfoGridBar");
		return testObjArray;

	}

	@Test(dataProvider = "CodeInfoGridBarVerify")
	public void codeInfoGridBarVerify(Map<String, String> map) throws Exception {

		CodeInfoPage.verifyTextValue("Title", map.get("Title"));
		CodeInfoPage.verifyTextValue("SaveText", map.get("SaveText"));
		CodeInfoPage.verifyElementIsDisabled("Save");
		CodeInfoPage.verifyTextValue("RefreshButton", map.get("RefreshButton"));
		CodeInfoPage.verifyElementIsEnabled("RefreshButton");
		CodeInfoPage.click("RefreshButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
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
		CodeInfoPage.verifyElementIsPresent("Save");
		CodeInfoPage.click("RefreshButton");
		CodeInfoPage.click("CancelButton");
		CodeInfoPage.click("RefreshButton");
		CodeInfoPage.click("OkButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("High")));
		CodeInfoPage.click("FilterName");
		CodeInfoPage.enterIntoTextBox("FilterName", map.get("Code"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		CodeInfoPage.explicitWaitForElementToBeClickable("RefreshButton");
		CodeInfoPage.click("RefreshButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		CodeInfoPage.verifyElementIsEnabled("AddButton");
		CodeInfoPage.verifyElementIsEnabled("DeleteButton");
		CodeInfoPage.verifyElementIsPresent("FilterName");
	    logger.info("Filter option is present :");
		CodeInfoPage.verifyElementIsEnabled("GridViewMenu");
		CodeInfoPage.explicitWaitForElementToBeClickable("GridViewMenu");
		CodeInfoPage.click("GridViewMenu");
		CodeInfoPage.explicitWaitForElementToBeClickable("RowSelector");
		CodeInfoPage.click("RowSelector");
		CodeInfoPage.explicitWaitForElementToBeClickable("RowSelectorColumnCheckBox");
		CodeInfoPage.explicitWaitForElementToBeClickable("RowSelectorColumnCheckBox");
		CodeInfoPage.click("RowSelectorColumnCheckBox");
		CodeInfoPage.verifyCheckBoxIsSelected("RowSelectorCheckBox1");
		CodeInfoPage.verifyCheckBoxIsSelected("RowSelectorCheckBox2");

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
		} finally {
			SIMWebdriverBase.close();
		}
	}
}
