package com.oracle.sim.testcases.CustomFlexibleAttribute;

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

public class ApplyCustomFlexibleAttribute {
	public static Logger logger = Logger.getLogger(ApplyCustomFlexibleAttribute.class.getName());
	protected static PropertyReader propReader= new PropertyReader();
	PageFactory pageFactory = new PageFactory();

	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage CustomFlexibleAttributePage;

	@BeforeClass()
	public void setup(ITestContext context) throws Exception {
		logger.info("TestCase Name: " +logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
		LoginPage= pageFactory.initialize("LoginPage");
		HomePage = pageFactory.initialize("HomePage");
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username", LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password", LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");

		HomePage.verifyTextValue("Title", "Store Inventory Operations Cloud Service");
		HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
		HomePage.storeIdCheck();
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.click("Navigation");
		HomePage.click("Admin");
		HomePage.click("DataSetup");
		HomePage.click("CustomFlexibleAttributePage");
		CustomFlexibleAttributePage = pageFactory.initialize("CustomFlexibleAttributePage");
	}

	@DataProvider(name = "AddAttributeTestData")
	public Object[][] getAddAttributeTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("CustomFlexibleAttributeTestData"),"AddAttribute");
		return testObjArray;
	}

	@Test(dataProvider="AddAttributeTestData", priority=1)
	public void applyAttribute(Map<String, String> map) {
		logger.info("Method Name: applyAttribute");
		CustomFlexibleAttributePage.explicitWaitForElementToBeClickable("Title");
		CustomFlexibleAttributePage.verifyTextValue("Title",map.get("CFATitle"));
		CustomFlexibleAttributePage.explicitWaitForElementToBeClickable("AddButton");
		CustomFlexibleAttributePage.click("AddButton");
		CustomFlexibleAttributePage.click("ApplyButton");
		//verifying error msg and border color
		CustomFlexibleAttributePage.verifyTextValue("DisplayLabelMsg",map.get("EmptyFieldErrorMessage"));
		CustomFlexibleAttributePage.verifyTextValue("DataTypeLabelMsg",map.get("EmptyFieldErrorMessage"));
		CustomFlexibleAttributePage.verifyTextValue("FunctionalAreaLabelMsg",map.get("EmptyFieldErrorMessage"));
		CustomFlexibleAttributePage.verifyBorderColor("DisplayLabelBorder",map.get("FieldBorderColorCode"));
		CustomFlexibleAttributePage.verifyBorderColor("DataTypeBorder",map.get("FieldBorderColorCode"));
		CustomFlexibleAttributePage.verifyBorderColor("FunctionalAreaBorder",map.get("FieldBorderColorCode"));	
		//verifying attributes
		CustomFlexibleAttributePage.click("CancelButton");
		CustomFlexibleAttributePage.click("AddButton");
		CustomFlexibleAttributePage.enterIntoTextBox("DisplayLabel",map.get("DisplayLabel"));
		CustomFlexibleAttributePage.selectDropDownValue("DataType",map.get("DataType"));
		CustomFlexibleAttributePage.explicitWaitForElementToBeClickable("FunctionalArea");
		CustomFlexibleAttributePage.selectDropDownValue("FunctionalArea",map.get("FunctionalArea"));
		CustomFlexibleAttributePage.click("ApplyButton");
		CustomFlexibleAttributePage.explicitWaitForElementToBeClickable("FilterDisplayLabel");
		CustomFlexibleAttributePage.click("FilterDisplayLabel");
		CustomFlexibleAttributePage.enterIntoTextBox("FilterDisplayLabel", map.get("DisplayLabel"));
		CustomFlexibleAttributePage.verifyTextValue("GridRecord", map.get("DisplayLabel"));
		CustomFlexibleAttributePage.click("RefreshButton");
		CustomFlexibleAttributePage.click("OkButton");			
	}

	@DataProvider(name = "ApplyAttributeTestData")
	public Object[][] getApplyAttributeTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("CustomFlexibleAttributeTestData"),"ApplyAttribute");
		return testObjArray;
	}

	@Test(dataProvider="ApplyAttributeTestData", priority=2)
	public void verifyNumericField(Map<String, String> map) throws Exception {
		logger.info("Method Name: verifyNumericField");
		CustomFlexibleAttributePage.explicitWaitForElementToBeClickable("AddButton");
		CustomFlexibleAttributePage.click("AddButton");
		CustomFlexibleAttributePage.explicitWaitForElementToBeClickable("DisplayLabel");
		CustomFlexibleAttributePage.enterIntoTextBox("DisplayLabel",map.get("DisplayLabel"));
		CustomFlexibleAttributePage.selectDropDownValue("DataType",map.get("DataType"));
		CustomFlexibleAttributePage.explicitWaitForElementToBeClickable("FunctionalArea");		
		CustomFlexibleAttributePage.selectDropDownValue("FunctionalArea",map.get("FunctionalArea"));
		if(map.get("DataType").equals("Long")||map.get("DataType").equals("Decimal")||map.get("DataType").equals("Text")) {
			CustomFlexibleAttributePage.enterIntoTextBox(map.get("DataType")+"MinValue",map.get("MinimumValue"));
			CustomFlexibleAttributePage.pressEnterKey(map.get("DataType")+"MinValue");
			CustomFlexibleAttributePage.enterIntoTextBox(map.get("DataType")+"MaxValue",map.get("MaximumValue"));
			CustomFlexibleAttributePage.pressEnterKey(map.get("DataType")+"MaxValue");
			CustomFlexibleAttributePage.explicitWaitForElementToBeClickable("ApplyButton");
			CustomFlexibleAttributePage.click("ApplyButton");
			if(!map.get("MinimumValueErrorMessage").equals("")||!map.get("MaximumValueErrorMessage").equals("")) {
				CustomFlexibleAttributePage.verifyTextValue(map.get("DataType")+"MinValueMsg",map.get("MinimumValueErrorMessage"));
				if(!map.get("MaximumValueErrorMessage").equals("")) {
					CustomFlexibleAttributePage.verifyTextValue(map.get("DataType")+"MaxValueMsg",map.get("MaximumValueErrorMessage"));
				}
				CustomFlexibleAttributePage.click("CancelButton");
				CustomFlexibleAttributePage.click("RefreshButton");
			}
			else
			{
				CustomFlexibleAttributePage.explicitWaitForElementToBeClickable("FilterDisplayLabel");
				CustomFlexibleAttributePage.click("FilterDisplayLabel");
				CustomFlexibleAttributePage.enterIntoTextBox("FilterDisplayLabel", map.get("DisplayLabel"));
				CustomFlexibleAttributePage.verifyTextValue("GridRecord", map.get("DisplayLabel"));
				CustomFlexibleAttributePage.click("RefreshButton");
				CustomFlexibleAttributePage.click("OkButton");	
				//Refresh needs time to fetch details from DB
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
			}	
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
