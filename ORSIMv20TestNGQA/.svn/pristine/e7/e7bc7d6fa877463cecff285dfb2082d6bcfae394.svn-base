package com.oracle.sim.testcases.Configuration;
/**
 * @author dsorthiy
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


public class PackageSizeValidations {

	public static Logger logger = Logger.getLogger(PackageSizeValidations.class.getName());	
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pageFactory = new PageFactory();
	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage PackageSizePage;

	@BeforeClass()
	public void setup(ITestContext context) throws Exception 
	{
		logger.info("TestCase Name: " +logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
		LoginPage = pageFactory.initialize("LoginPage");
		HomePage = pageFactory.initialize("HomePage");
		PackageSizePage = pageFactory.initialize("PackageSizePage");
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username", LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password", LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");
		HomePage.verifyTextValue("Title", HomePage.getProperty("Title"));
		HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
		HomePage.storeIdCheck();
	}
	
	@DataProvider(name = "ValidationsPackageSize")
	public Object[][] getTestDataForPackageAdd() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("PackageTestData"),"Validations");
		return testObjArray;
	}

	@Test(dataProvider = "ValidationsPackageSize", priority = 1)
	public void packageSizeAdd(Map<String, String> map) throws Exception {
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.click("Navigation");
		HomePage.click("AdminMenu");
		HomePage.click("ConfigurationMenu");
		HomePage.click("PackageSizeMenu");
		logger.info("PackageSize Validation test case");
		
		PackageSizePage.click("AddNewIcon");
		//Verify the Detail New Block is Enabled or not..
		PackageSizePage.verifyElementIsEnabled("ApplyButton");
		PackageSizePage.verifyElementIsEnabled("CancelButton");
		PackageSizePage.enterIntoTextBox("HeightTxtBox", map.get("Height"));
		PackageSizePage.enterIntoTextBox("WidthTxtBox", map.get("Width"));
		PackageSizePage.enterIntoTextBox("LengthTxtBox", map.get("Length"));
		PackageSizePage.searchDropDownValue("UOMDropdown", map.get("UOM"));
		PackageSizePage.explicitWaitForElementToBeClickable("ApplyButton");
		PackageSizePage.click("ApplyButton");
		//Verify the Error Message without entering Description value 
		PackageSizePage.verifyTextValue("Valueisrequired",map.get("EM1"));
		PackageSizePage.click("CancelButton");
		
		PackageSizePage.click("AddNewIcon");
		PackageSizePage.enterIntoTextBox("DescriptionTxtBox", map.get("Description"));
		PackageSizePage.enterIntoTextBox("WidthTxtBox", map.get("Width"));
		PackageSizePage.enterIntoTextBox("LengthTxtBox", map.get("Length"));
		PackageSizePage.selectDropDownValue("UOMDropdown", map.get("UOM"));
		PackageSizePage.explicitWaitForElementToBeClickable("ApplyButton");
		PackageSizePage.click("ApplyButton");
		//Verify the Error Message without entering Height value 
		PackageSizePage.verifyTextValue("Valueisrequired",map.get("EM1"));
		PackageSizePage.click("CancelButton");

		PackageSizePage.click("AddNewIcon");
		PackageSizePage.enterIntoTextBox("DescriptionTxtBox", map.get("Description"));
		PackageSizePage.enterIntoTextBox("HeightTxtBox", map.get("Height"));
		PackageSizePage.enterIntoTextBox("LengthTxtBox", map.get("Length"));
		PackageSizePage.selectDropDownValue("UOMDropdown", map.get("UOM"));
		PackageSizePage.explicitWaitForElementToBeClickable("ApplyButton");
		PackageSizePage.click("ApplyButton");
		//Verify the Error Message without entering Width value 
		PackageSizePage.verifyTextValue("Valueisrequired",map.get("EM1"));
		PackageSizePage.click("CancelButton");

		PackageSizePage.click("AddNewIcon");
		PackageSizePage.enterIntoTextBox("DescriptionTxtBox", map.get("Description"));
		PackageSizePage.enterIntoTextBox("HeightTxtBox", map.get("Height"));
		PackageSizePage.enterIntoTextBox("WidthTxtBox", map.get("Width"));
		PackageSizePage.selectDropDownValue("UOMDropdown", map.get("UOM"));
		PackageSizePage.explicitWaitForElementToBeClickable("ApplyButton");
		PackageSizePage.click("ApplyButton");
		//Verify the Error Message without entering Length value 
		PackageSizePage.verifyTextValue("Valueisrequired",map.get("EM1"));
		PackageSizePage.click("CancelButton");
		
		
		PackageSizePage.click("AddNewIcon");
		PackageSizePage.enterIntoTextBox("DescriptionTxtBox", map.get("Description"));
		PackageSizePage.enterIntoTextBox("HeightTxtBox", map.get("Height"));
		PackageSizePage.enterIntoTextBox("WidthTxtBox", map.get("Width"));
		PackageSizePage.enterIntoTextBox("LengthTxtBox", map.get("Length"));
		PackageSizePage.explicitWaitForElementToBeClickable("ApplyButton");
		PackageSizePage.click("ApplyButton");
		//Verify the Error Message without entering Length value 
		PackageSizePage.verifyTextValue("Valueisrequired",map.get("EM1"));
		PackageSizePage.click("CancelButton");
		
		//Do not Enter any value in Detail New Field 
		PackageSizePage.click("AddNewIcon");
		PackageSizePage.click("ApplyButton"); 
		PackageSizePage.verifyTextValue("Valueisrequired",map.get("EM1"));
		PackageSizePage.click("CancelButton");
		
		PackageSizePage.click("AddNewIcon");

		// Enter any Non-Unique Description field
		PackageSizePage.explicitWaitForElementToBeClickable("DescriptionTxtBox");
		PackageSizePage.enterIntoTextBox("DescriptionTxtBox",map.get("Description"));
		PackageSizePage.enterIntoTextBox("HeightTxtBox", map.get("Height"));
		PackageSizePage.enterIntoTextBox("WidthTxtBox", map.get("Width"));
		PackageSizePage.enterIntoTextBox("LengthTxtBox", map.get("Length"));
		PackageSizePage.selectDropDownValue("UOMDropdown", map.get("UOM"));
		PackageSizePage.explicitWaitForElementToBeClickable("ApplyButton");
		PackageSizePage.click("ApplyButton");
		PackageSizePage.click("SaveButton");

		// Click Yes in Confirmation pop up
		PackageSizePage.explicitWaitForElementToBeClickable("YesButton");
		PackageSizePage.click("YesButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		
		//Delete the Added Package Size
		PackageSizePage.explicitWaitForElementToBeClickable("DescriptionFilter");
		PackageSizePage.click("DescriptionFilter");
		PackageSizePage.enterIntoTextBox("DescriptionFilter", map.get("Description"));
		PackageSizePage.click("GridRecord");
		PackageSizePage.explicitWaitForElementToBeClickable("DeleteIcon");
		PackageSizePage.click("DeleteIcon");
		// Click Yes in Confirmation pop up
		PackageSizePage.explicitWaitForElementToBeClickable("YesButton");
		PackageSizePage.click("YesButton");
		// Saving the changes
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		PackageSizePage.explicitWaitForElementToBeClickable("SaveButton");
		PackageSizePage.click("SaveButton");
		// Click Yes in Confirmation pop up
		PackageSizePage.explicitWaitForElementToBeClickable("YesButton");
		PackageSizePage.click("YesButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		
		
		
		//Verify the Negative Numbers in  all Height ,width,Length
	    PackageSizePage.click("AddNewIcon");
	    //Thread.sleep(3000);
	    PackageSizePage.explicitWaitForElementToBeClickable("DescriptionTxtBox");
	    PackageSizePage.enterIntoTextBox("DescriptionTxtBox",map.get("Description"));
	    Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
	    PackageSizePage.enterIntoTextBox("HeightTxtBox",map.get("Common"));
	    PackageSizePage.enterIntoTextBox("WidthTxtBox",map.get("Common"));
		PackageSizePage.enterIntoTextBox("LengthTxtBox",map.get("Common"));
	    PackageSizePage.verifyTextValue("NumbertooLow",map.get("EM2"));
	    PackageSizePage.click("ApplyButton");
	    PackageSizePage.click("CancelButton");
	    
	  //Verify the Special Character in  all Height ,width,Length
	    PackageSizePage.click("AddNewIcon");
		PackageSizePage.explicitWaitForElementToBeClickable("DescriptionTxtBox");
		PackageSizePage.enterIntoTextBox("DescriptionTxtBox",map.get("Description"));
		PackageSizePage.enterIntoTextBox("HeightTxtBox",map.get("Common2"));
		PackageSizePage.enterIntoTextBox("WidthTxtBox", map.get("Common2"));
		PackageSizePage.enterIntoTextBox("LengthTxtBox", map.get("Common2"));
		
		PackageSizePage.verifyInvalidNumberFormatErrorMsg();
		//PackageSizePage.click("ApplyButton");
		PackageSizePage.click("CancelButton");
		
		
		//Verify AlphaNumeric Value in All Height , Width ,Length
		PackageSizePage.click("AddNewIcon");
		PackageSizePage.explicitWaitForElementToBeClickable("DescriptionTxtBox");
		PackageSizePage.enterIntoTextBox("DescriptionTxtBox",map.get("Description"));
		PackageSizePage.enterIntoTextBox("HeightTxtBox",map.get("Common3"));
		PackageSizePage.enterIntoTextBox("WidthTxtBox", map.get("Common3"));
		PackageSizePage.enterIntoTextBox("LengthTxtBox",map.get("Common3"));
		PackageSizePage.verifyInvalidNumberFormatErrorMsg();
		PackageSizePage.click("ApplyButton");
		PackageSizePage.click("CancelButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		
		//Verify the Decimal number with greater than 3
		PackageSizePage.click("AddNewIcon");
		PackageSizePage.explicitWaitForElementToBeClickable("DescriptionTxtBox");
		PackageSizePage.enterIntoTextBox("DescriptionTxtBox",map.get("Description"));
		PackageSizePage.enterIntoTextBox("HeightTxtBox",map.get("DecimalValue"));
		PackageSizePage.enterIntoTextBox("WidthTxtBox", map.get("DecimalValue"));
		PackageSizePage.enterIntoTextBox("LengthTxtBox",map.get("DecimalValue"));
		PackageSizePage.selectDropDownValue("UOMDropdown", map.get("UOM"));
		
		PackageSizePage.click("ApplyButton");
		PackageSizePage.click("SaveButton");

		// Click Yes in Confirmation pop up
		PackageSizePage.explicitWaitForElementToBeClickable("YesButton");
		PackageSizePage.click("YesButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		
		PackageSizePage.explicitWaitForElementToBeClickable("FilterByHeight");
		PackageSizePage.click("FilterByHeight");
		PackageSizePage.enterIntoTextBox("FilterByHeight", map.get("newdecimal"));
		System.out.println(PackageSizePage.getText("GridRecord"));
		PackageSizePage.verifyTextValue("GridRecord", map.get("newdecimal"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		
		PackageSizePage.explicitWaitForElementToBeClickable("FilterByWidth");
		PackageSizePage.click("FilterByWidth");
		PackageSizePage.enterIntoTextBox("FilterByWidth", map.get("newdecimal"));
		System.out.println(PackageSizePage.getText("GridRecord"));
		PackageSizePage.verifyTextValue("GridRecord", map.get("newdecimal"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		//Delete the Existing Package 
		PackageSizePage.click("GridRecord");
		PackageSizePage.explicitWaitForElementToBeClickable("DeleteIcon");
		PackageSizePage.click("DeleteIcon");
		// Click Yes in Confirmation pop up
		PackageSizePage.explicitWaitForElementToBeClickable("YesButton");
		PackageSizePage.click("YesButton");
		// Saving the changes
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		PackageSizePage.explicitWaitForElementToBeClickable("SaveButton");
		PackageSizePage.click("SaveButton");
		// Click Yes in Confirmation pop up
		PackageSizePage.explicitWaitForElementToBeClickable("YesButton");
		PackageSizePage.click("YesButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		
		
		
		
		


				
		
						
				


		
		
		



	}
	
	@AfterClass()
	public void tearDown() throws Exception {

		try {
			logger.info("After Test: Logging out");
			HomePage.click("UserMenu");
			HomePage.explicitWaitForVisibility("Logout");
			HomePage.click("Logout");
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
			HomePage.explicitWaitForVisibility("Yes");
			HomePage.click("Yes");
		}

		finally {

			SIMWebdriverBase.close();

		}

	}
}
