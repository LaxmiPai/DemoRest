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

public class BarCodeProcessorSricptValidation {
	public static Logger logger=Logger.getLogger(BarCodeProcessorUINavigation.class.getName());
	protected static PropertyReader propReader=new PropertyReader();
	PageFactory pagefactory=new PageFactory();

	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage BarcodeProcessor;
	@BeforeClass
	public void setup(ITestContext context) throws Exception {
		logger.info("TestCase Name:BarCodeProcessorSricptValidation"+logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
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
		HomePage.storeIdCheck();
		HomePage.click("Navigation");
		HomePage.click("Admin");
		HomePage.click("ConfigurationMenu");
		HomePage.click("BarCodeProcessor");
		BarcodeProcessor=pagefactory.initialize("BarcodeProcessor");
		
		//To verify the title
		BarcodeProcessor.explicitWaitForElementToBeClickable("Title");
		BarcodeProcessor.verifyTextValue("Title",map.get("BarcodeProcessorTitle"));
		
		//To verify the rows present 
		BarcodeProcessor.verifyTableRecordsCount("BarcodeProcessorRows", "ActiveNameHeader", "FilterOfFrstColumn");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		
		//To verify if the fields have yes 
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		BarcodeProcessor.VerifyBarCodeProcessorActiveColumnValue();
		
		//To get the sorting order in terms of Sequence
		BarcodeProcessor.columnSorting("SequenceCloumnInGrp", "SequenceNameHeader", map.get("SortingOrder"),map.get("DataType"));
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
