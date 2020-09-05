package com.oracle.sim.testcases.CustomFlexibleAttribute;

/**
 * @author vidhsank
 *
 * 
 */

import java.util.Map;
import java.util.Random;
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

public class CustomFlexibleAttribute {
	public static Logger logger = Logger.getLogger(CustomFlexibleAttribute.class.getName());
	protected static PropertyReader propReader= new PropertyReader();
	PageFactory pageFactory = new PageFactory();

	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage CustomFlexibleAttributePage;
	int randomNo;
	
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
	public void addAttribute(Map<String, String> map) throws Exception {	
		logger.info("Method Name: addAttribute");
		CustomFlexibleAttributePage.explicitWaitForVisibility("Title");
		CustomFlexibleAttributePage.verifyTextValue("Title",map.get("CFATitle"));
		//verifying detail panel
		CustomFlexibleAttributePage.explicitWaitForElementToBeClickable("AddButton");
		CustomFlexibleAttributePage.verifyElementIsEnabled("AddButton");
		CustomFlexibleAttributePage.click("AddButton");
		CustomFlexibleAttributePage.explicitWaitForElementToBeClickable("DisplayLabel");
		CustomFlexibleAttributePage.explicitWaitForElementToBeClickable("PublishAttribute");
		CustomFlexibleAttributePage.verifyElementIsEnabled("ApplyButton");
		CustomFlexibleAttributePage.verifyElementIsEnabled("CancelButton");
		CustomFlexibleAttributePage.verifyElementIsEnabled("DisplayLabel");
		CustomFlexibleAttributePage.verifyElementIsEnabled("DataType");
		CustomFlexibleAttributePage.verifyElementIsEnabled("FunctionalArea");
		CustomFlexibleAttributePage.verifyElementIsEnabled("Required");
		CustomFlexibleAttributePage.verifyElementIsEnabled("PublishAttribute");		
		//adding records
		Random r= new Random();
		randomNo=r.nextInt(100);
		CustomFlexibleAttributePage.explicitWaitForElementToBeClickable("DisplayLabel");
		CustomFlexibleAttributePage.click("DisplayLabel");
		CustomFlexibleAttributePage.enterIntoTextBox("DisplayLabel",map.get("DisplayLabel")+randomNo);
		CustomFlexibleAttributePage.selectDropDownValue("DataType",map.get("DataType"));
		CustomFlexibleAttributePage.implicitWait();
		CustomFlexibleAttributePage.selectDropDownValue("FunctionalArea",map.get("FunctionalArea"));
		if(map.get("Required").equals("Yes")) {
			CustomFlexibleAttributePage.click("Required");
		}
		if(map.get("PublishAttribute").equals("Yes")) {
			CustomFlexibleAttributePage.click("PublishAttribute");
		}
		CustomFlexibleAttributePage.click("ApplyButton");		
		//Click Yes in Save confirmation popup
		CustomFlexibleAttributePage.verifyElementIsEnabled("SaveButton");
		CustomFlexibleAttributePage.click("SaveButton");
		CustomFlexibleAttributePage.explicitWaitForElementToBeClickable("YesButton");
		CustomFlexibleAttributePage.click("YesButton");	
		//Wait for DB commit to complete
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
	}

	@DataProvider(name = "SaveAttributeTestData")
	public Object[][] getSaveAttributeTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("CustomFlexibleAttributeTestData"),"AddAttribute");
		return testObjArray;
	}

	@Test(dataProvider="SaveAttributeTestData", priority=2, dependsOnMethods = { "addAttribute" })
	public void SaveAttribute(Map<String, String> map) throws Exception {
		logger.info("Method Name: SaveAttribute");
		CustomFlexibleAttributePage.explicitWaitForElementToBeClickable("FilterDisplayLabel");
		CustomFlexibleAttributePage.click("FilterDisplayLabel");
		CustomFlexibleAttributePage.enterIntoTextBox("FilterDisplayLabel", map.get("DisplayLabel")+randomNo);
		CustomFlexibleAttributePage.click("GridRecord");
		CustomFlexibleAttributePage.click("RemoveButton");
		CustomFlexibleAttributePage.explicitWaitForElementToBeClickable("YesButton");
		CustomFlexibleAttributePage.click("YesButton");
		//Wait for DB update to complete
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		CustomFlexibleAttributePage.click("SaveButton");

		//Click No in Save Confirmation pop up
		CustomFlexibleAttributePage.explicitWaitForElementToBeClickable("NoButton");
		CustomFlexibleAttributePage.verifyElementIsDisplayed("NoButton");
		CustomFlexibleAttributePage.click("NoButton");

		//Refreshing the page		
		CustomFlexibleAttributePage.explicitWaitForElementToBeClickable("RefreshButton");
		CustomFlexibleAttributePage.click("RefreshButton");	
		CustomFlexibleAttributePage.click("OkButton");
		//Refresh needs time to fetch details from DB
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		CustomFlexibleAttributePage.explicitWaitForElementToBeClickable("FilterDisplayLabel");
		CustomFlexibleAttributePage.click("FilterDisplayLabel");
		CustomFlexibleAttributePage.enterIntoTextBox("FilterDisplayLabel", map.get("DisplayLabel")+randomNo);
		CustomFlexibleAttributePage.explicitWaitForVisibility("GridRecord");
		CustomFlexibleAttributePage.verifyTextValue("GridRecord",map.get("DisplayLabel")+randomNo);
	}

	@DataProvider(name = "SearchAttributeTestData")
	public Object[][] getEditAttributeTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("CustomFlexibleAttributeTestData"),"AddAttribute");
		return testObjArray;
	}

	@Test(dataProvider="SearchAttributeTestData",priority=3, dependsOnMethods = { "addAttribute" })
	public void searchAttribute(Map<String, String> map) throws Exception {
		//verifying display label filter
		logger.info("Method Name: searchAttribute");
		CustomFlexibleAttributePage.click("RefreshButton");
		//		CustomFlexibleAttributePage.explicitWaitForInvisibility("GridRecord");
		CustomFlexibleAttributePage.explicitWaitForElementToBeClickable("FilterDisplayLabel");
		CustomFlexibleAttributePage.click("FilterDisplayLabel");
		CustomFlexibleAttributePage.enterIntoTextBox("FilterDisplayLabel", map.get("DisplayLabel")+randomNo);
		CustomFlexibleAttributePage.explicitWaitForVisibility("GridRecord");
		CustomFlexibleAttributePage.verifyHighlightedTextValue("GridRecord",map.get("DisplayLabel")+randomNo);
		CustomFlexibleAttributePage.click("RefreshButton");
		//	CustomFlexibleAttributePage.explicitWaitForInvisibility("GridRecord");

		//verifying data type filter
		CustomFlexibleAttributePage.explicitWaitForElementToBeClickable("FilterDataType");
		CustomFlexibleAttributePage.click("FilterDataType");
		CustomFlexibleAttributePage.enterIntoTextBox("FilterDataType", map.get("DataType"));
		CustomFlexibleAttributePage.explicitWaitForVisibility("GridRecord");
		CustomFlexibleAttributePage.verifyHighlightedTextValue("GridRecord",map.get("DataType"));
		CustomFlexibleAttributePage.click("RefreshButton");
		//	CustomFlexibleAttributePage.explicitWaitForInvisibility("GridRecord");

		//verifying functional area filter
		CustomFlexibleAttributePage.explicitWaitForElementToBeClickable("FilterFunctionalArea");
		CustomFlexibleAttributePage.click("FilterFunctionalArea");
		//		int spaceIndex=map.get("FunctionalArea").indexOf(" ");
		CustomFlexibleAttributePage.enterIntoTextBox("FilterFunctionalArea",map.get("FunctionalArea"));		
		CustomFlexibleAttributePage.explicitWaitForVisibility("FunctionalAreaColRecords");
		CustomFlexibleAttributePage.verifyHighlightedTextValue("FunctionalAreaColRecords", map.get("FunctionalArea"));
		CustomFlexibleAttributePage.click("RefreshButton");
		//	CustomFlexibleAttributePage.explicitWaitForInvisibility("GridRecord");

		//verifying required filter
		CustomFlexibleAttributePage.explicitWaitForElementToBeClickable("FilterRequired");
		CustomFlexibleAttributePage.click("FilterRequired");
		CustomFlexibleAttributePage.enterIntoTextBox("FilterRequired", map.get("Required"));
		CustomFlexibleAttributePage.explicitWaitForVisibility("GridRecord");
		CustomFlexibleAttributePage.verifyHighlightedTextValue("GridRecord",map.get("Required"));
		CustomFlexibleAttributePage.click("RefreshButton");
		//	CustomFlexibleAttributePage.explicitWaitForInvisibility("GridRecord");

		//hovering add button
		CustomFlexibleAttributePage.verifyTextValue("AddButton","Add New");
	}

	@DataProvider(name = "RemoveAttributeTestData")
	public Object[][] getRemoveAttributeTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("CustomFlexibleAttributeTestData"),"AddAttribute");
		return testObjArray;
	}

	@Test(dataProvider="RemoveAttributeTestData", priority=4, dependsOnMethods = { "addAttribute" })
	public void removeAttribute(Map<String,String> map) throws Exception {
		//Click on No in the confirmation message
		logger.info("Method Name: removeAttribute");
		CustomFlexibleAttributePage.click("FilterDisplayLabel");
		CustomFlexibleAttributePage.enterIntoTextBox("FilterDisplayLabel", map.get("DisplayLabel")+randomNo);
		CustomFlexibleAttributePage.explicitWaitForVisibility("GridRecord");
		CustomFlexibleAttributePage.click("GridRecord");
		CustomFlexibleAttributePage.click("RemoveButton");
		CustomFlexibleAttributePage.verifyTextValue("RemoveConfirmation","The selected records will be deleted. Do you want to continue?");
		CustomFlexibleAttributePage.verifyElementIsEnabled("YesButton");
		CustomFlexibleAttributePage.verifyElementIsEnabled("NoButton");
		CustomFlexibleAttributePage.click("NoButton");
		CustomFlexibleAttributePage.verifyTextValue("GridRecord", map.get("DisplayLabel")+randomNo);	
		//Click on Yes in the confirmation message
		CustomFlexibleAttributePage.click("GridRecord");
		CustomFlexibleAttributePage.explicitWaitForElementToBeClickable("RemoveButton");
		CustomFlexibleAttributePage.click("RemoveButton");
		CustomFlexibleAttributePage.explicitWaitForElementToBeClickable("YesButton");
		CustomFlexibleAttributePage.click("YesButton");		
		CustomFlexibleAttributePage.implicitWait();
		CustomFlexibleAttributePage.explicitWaitForElementToBeClickable("SaveButton");
		CustomFlexibleAttributePage.explicitWaitForElementToBeClickable("FilterDisplayLabel");
		CustomFlexibleAttributePage.click("FilterDisplayLabel");
		CustomFlexibleAttributePage.enterIntoTextBox("FilterDisplayLabel", map.get("DisplayLabel")+randomNo);
		CustomFlexibleAttributePage.pressEnterKey("FilterDisplayLabel");
		CustomFlexibleAttributePage.verifyTextValue("NorowsText","No rows match the current filter(s).");
		CustomFlexibleAttributePage.verifyElementIsEnabled("RefreshButton");
		CustomFlexibleAttributePage.explicitWaitForElementToBeClickable("RefreshButton");
		CustomFlexibleAttributePage.click("RefreshButton");
		CustomFlexibleAttributePage.click("OkButton");		
		//Refresh needs time to fetch details from DB
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		//Save operation after element removed
		CustomFlexibleAttributePage.explicitWaitForElementToBeClickable("RemoveButton");
		CustomFlexibleAttributePage.explicitWaitForElementToBeClickable("FilterDisplayLabel");
		CustomFlexibleAttributePage.click("FilterDisplayLabel");
		CustomFlexibleAttributePage.enterIntoTextBox("FilterDisplayLabel", map.get("DisplayLabel")+randomNo);
		CustomFlexibleAttributePage.click("FilterDisplayLabel");
		CustomFlexibleAttributePage.verifyTextValue("GridRecord", map.get("DisplayLabel")+randomNo);		
		CustomFlexibleAttributePage.click("GridRecord");
		CustomFlexibleAttributePage.click("RemoveButton");
		CustomFlexibleAttributePage.click("YesButton");		
		CustomFlexibleAttributePage.explicitWaitForElementToBeClickable("SaveButton");
		CustomFlexibleAttributePage.click("SaveButton");		
		CustomFlexibleAttributePage.click("YesButton");
		//Wait for DB commit to complete
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		CustomFlexibleAttributePage.explicitWaitForElementToBeClickable("FilterDisplayLabel");	
		CustomFlexibleAttributePage.click("FilterDisplayLabel");	
		CustomFlexibleAttributePage.enterIntoTextBox("FilterDisplayLabel", map.get("DisplayLabel")+randomNo);		
		CustomFlexibleAttributePage.verifyTextValue("NorowsText","No rows match the current filter(s).");		
		CustomFlexibleAttributePage.verifyTextValue("RemoveButton","Remove Selected");
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
