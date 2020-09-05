package com.oracle.sim.testcases.Configuration;

//**@lrathnak**//

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

public class BarCodeProcessorUINavigation {
	public static Logger logger=Logger.getLogger(BarCodeProcessorUINavigation.class.getName());
	protected static PropertyReader propReader=new PropertyReader();
	PageFactory pagefactory=new PageFactory();

	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage BarcodeProcessor;
	@BeforeClass
	public void setup(ITestContext context) throws Exception {
		SIMWebdriverBase.loadInitialURL(context);
		logger.info("TestCase Name:BarCodeProcessorUINavigation"+logger.getName());
		LoginPage=pagefactory.initialize("LoginPage");
		HomePage=pagefactory.initialize("HomePage");
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username",LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password",LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");		
	}
	
	@DataProvider(name = "BarCodeProcessor")
	public Object[][] getApplyMpsWorkTypeTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("BarCodeProcessor"),
				"BarCodeProcessor");
		return testObjArray;
	}
	
	@Test(dataProvider="BarCodeProcessor")
	public void barCodeProcessor(Map<String,String> map) throws Exception {
		HomePage.explicitWaitForElementToBeClickable("Navigation");
//		HomePage.storeIdCheck();
		HomePage.click("Navigation");
		HomePage.click("Admin");
		HomePage.click("ConfigurationMenu");
		HomePage.click("BarCodeProcessor");
		BarcodeProcessor=pagefactory.initialize("BarcodeProcessor");
		
		//To verify the title
		BarcodeProcessor.explicitWaitForElementToBeClickable("Title");
		BarcodeProcessor.verifyTextValue("Title",map.get("BarcodeProcessorTitle"));
		
		//To verify the column sort
		BarcodeProcessor.columnSorting("SequenceCloumnInGrp",map.get("SortingOrder") ,map.get("DataType"));
		
		//To verify if the save button is present and disabled
		BarcodeProcessor.isElementPresent("SaveButton");
		BarcodeProcessor.implicitWait();
		BarcodeProcessor.verifyElementIsDisabled("SaveButton");
		
		//to verify the refresh button is present and is enabled
		BarcodeProcessor.isElementPresent("RefreshButton");
		BarcodeProcessor.implicitWait();
		BarcodeProcessor.verifyElementIsEnabled("RefreshButton");
		
		//To verify if the detail block is present and its title
		BarcodeProcessor.isElementPresent("DetailBlockTitle");
		BarcodeProcessor.isElementPresent("DetailBlock");
		
		//To verify if the Details blocks Edit button is present and  disabled
		BarcodeProcessor.isElementPresent("DetailEditButton");
		BarcodeProcessor.verifyElementIsDisabled("DetailEditButton");
		
		//To verify if the Details blocks Apply button is disabled
		BarcodeProcessor.isElementPresent("DetailApplyButton");
		BarcodeProcessor.verifyElementIsDisabled("DetailApplyButton");
		
		//To verify if the detail blocks Cancel button is present and disabled
		
		BarcodeProcessor.isElementPresent("DetailCancelButton");
		BarcodeProcessor.verifyElementIsDisabled("DetailCancelButton");
		
		//To verify if the schedule process and Active and Sequence is present
		BarcodeProcessor.verifyElementIsDisplayed("DetailProcessorName");
		BarcodeProcessor.verifyElementIsDisplayed("DetailActiveText");
		BarcodeProcessor.verifyElementIsDisplayed("DetailSequenceText");
		
		
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
		}
		finally {
			SIMWebdriverBase.close();
		}
	
	}
	
	
}
