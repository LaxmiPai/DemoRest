/**
 * 
 */
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

/**
 * @author lapai
 *
 */
public class CodeInfoEditAndRemove {

	public static Logger logger = Logger.getLogger(CodeInfoEditAndRemove.class.getName());
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
		HomePage.verifyTextValue("Title", HomePage.getProperty("Title"));
		HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
		HomePage.storeIdCheck();
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.click("Navigation");
		HomePage.click("Admin");
		HomePage.click("DataSetup");
		HomePage.click("CodeInfo");

	}

	@DataProvider(name = "CodeInfoEdit")
	public Object[][] getTestDataForCodeInfoEdit() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("CodeInfo"),
				"CodeInfoEditAndRemove");
		return testObjArray;
	}

	@Test(dataProvider = "CodeInfoEdit", priority = 1)
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
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		CodeInfoPage.explicitWaitForElementToBeClickable("YesButton");
		CodeInfoPage.click("YesButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("High")));
		CodeInfoPage.explicitWaitForElementToBeClickable("FilterName");
		CodeInfoPage.click("FilterName");
		CodeInfoPage.enterIntoTextBox("FilterName", map.get("Code"));
		CodeInfoPage.explicitWaitForElementToBeClickable("GridRecordCodeType");
		CodeInfoPage.click("GridRecordCodeType");
		CodeInfoPage.verifyElementIsEnabled("EditButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		CodeInfoPage.explicitWaitForElementToBeClickable("EditButton");
		CodeInfoPage.click("EditButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		CodeInfoPage.verifyElementIsEditable("DescriptionTextBox");
		CodeInfoPage.verifyElementIsEditable("SequenceTextBox");
		CodeInfoPage.click("DescriptionTextBox");
		CodeInfoPage.enterIntoTextBox("DescriptionTextBox", map.get("DescriptionNew"));
		CodeInfoPage.click("SequenceTextBox");
		CodeInfoPage.enterIntoTextBox("SequenceTextBox", map.get("SequenceNew"));
		CodeInfoPage.click("ApplyButton");
		CodeInfoPage.explicitWaitForElementToBeClickable("SaveButton");
		CodeInfoPage.click("SaveButton");
		CodeInfoPage.click("YesButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("High")));
		CodeInfoPage.explicitWaitForElementToBeClickable("FilterName");
		CodeInfoPage.click("FilterName");
		CodeInfoPage.enterIntoTextBox("FilterName", map.get("Code"));
		CodeInfoPage.explicitWaitForElementToBeClickable("DescriptionGridRecord");
		CodeInfoPage.verifyTextValue("DescriptionGridRecord", map.get("DescriptionNew"));
		CodeInfoPage.verifyTextValue("SequenceGridRecord", map.get("SequenceNew"));
		CodeInfoPage.click("GridRecordCodeType");
		CodeInfoPage.click("DeleteButton");
		CodeInfoPage.click("YesButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		CodeInfoPage.explicitWaitForElementToBeClickable("SaveButton");
		CodeInfoPage.click("SaveButton");
		CodeInfoPage.explicitWaitForElementToBeClickable("YesButton");
		CodeInfoPage.click("YesButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));

	}

	@DataProvider(name = "CodeInfoRemove")
	public Object[][] getTestDataForCodeInfoRemove() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("CodeInfo"),
				"CodeInfoEditAndRemove");
		return testObjArray;
	}

	@Test(dataProvider = "CodeInfoRemove", priority = 2)
	public void codeInfoRemove(Map<String, String> map) throws Exception {

		CodeInfoPage.explicitWaitForElementToBeClickable("GridViewMenu");
		CodeInfoPage.click("GridViewMenu");
		CodeInfoPage.explicitWaitForElementToBeClickable("RowSelector");
		CodeInfoPage.click("RowSelector");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		CodeInfoPage.enterIntoTextBox("System", map.get("SystemYes"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		CodeInfoPage.click("RowSelectorCheckBox1");
		CodeInfoPage.click("RowSelectorCheckBox2");
		CodeInfoPage.click("DeleteButton");
		CodeInfoPage.explicitWaitForElementToBeClickable("YesButton");
		CodeInfoPage.click("YesButton");
		CodeInfoPage.verifyTextValue("ErrorMessage1", map.get("ErrorMessage1"));
		CodeInfoPage.click("ErrorOKButton");
		CodeInfoPage.click("RowSelectorCheckBox2");
		CodeInfoPage.click("DeleteButton");
		CodeInfoPage.explicitWaitForElementToBeClickable("YesButton");
		CodeInfoPage.click("YesButton");
		CodeInfoPage.verifyTextValue("ErrorMessage1", map.get("ErrorMessage1"));
		CodeInfoPage.click("ErrorOKButton");
		CodeInfoPage.click("RowSelectorCheckBox1");
		CodeInfoPage.click("DeleteButton");
		CodeInfoPage.verifyTextValue("ErrorMessage2", map.get("ErrorMessage2"));
		CodeInfoPage.click("ErrorOKButton");

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
