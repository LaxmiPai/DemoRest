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

public class BarCodeProcessorDetail {
	public static Logger logger=Logger.getLogger(BarCodeProcessorUINavigation.class.getName());
	protected static PropertyReader propReader=new PropertyReader();
	PageFactory pagefactory=new PageFactory();

	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage BarcodeProcessor;
	@BeforeClass
	public void setup(ITestContext context) throws Exception {
		logger.info("TestCase Name:BarCodeProcessorDetail"+logger.getName());
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
		
		//To verify the Edit is displayed
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		BarcodeProcessor.verifyElementIsDisabled("DetailEditButton");
		
		//Highlighting the bar code to verify the Edit is enabled
		BarcodeProcessor.explicitWaitForElementToBeClickable("ProcessorNameFrstRow");
		BarcodeProcessor.click("ProcessorNameFrstRow");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("High")));
		BarcodeProcessor.verifyElementIsEnabled("DetailEditButton");
		
		//click edit , Apply is enabled,Cancel is enabled,Edit is disabled
		String actlValue=BarcodeProcessor.getText("DisabledActiveStatus");
		System.out.println(actlValue);
		BarcodeProcessor.click("DetailEditButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("High")));
		BarcodeProcessor.verifyElementIsPresent("DetailApplyButtonEnabled");
		BarcodeProcessor.verifyElementIsPresent("DetailCancelButtonEnabled");
//		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
		BarcodeProcessor.verifyElementIsDisplayed("DetailEditButton");
//		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("High")));
		BarcodeProcessor.verifyElementIsEditable("DetailProcessorNameInput");
//		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("High")));
		BarcodeProcessor.verifyElementIsEditable("DetailSequenceInput");
		
		
		//Change the active button to NO
		BarcodeProcessor.click("DetailActiveSwitch");
		BarcodeProcessor.click("DetailApplyButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
		BarcodeProcessor.verifyElementIsPresent("DetailDisableApplyButton");
		BarcodeProcessor.verifyElementIsDisabled("DetailCancelButton");
		String afterSwitchValueString=BarcodeProcessor.getText("DisabledActiveStatus");
		System.out.println(afterSwitchValueString);
		
		//Verify the save button
		BarcodeProcessor.explicitWaitForElementToBeClickable("SaveButton");
		BarcodeProcessor.verifyElementIsEnabled("SaveButton");
		BarcodeProcessor.click("RefreshButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("High")));
		BarcodeProcessor.explicitWaitForElementToBeClickable("RefreshDialogOkButton");
		BarcodeProcessor.click("RefreshDialogOkButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		BarcodeProcessor.verifyElementIsPresent("DetailDisableApplyButton");
		BarcodeProcessor.verifyElementIsDisabled("DetailCancelButton");
		BarcodeProcessor.verifyElementIsPresent("DetailDisabledEditButton");
		
		//filtering it by process name and getting the active value
		String afterValue=BarcodeProcessor.getText("DisabledActiveStatus");
			if(actlValue.equals(afterValue)) {
				System.out.println(afterValue +"The values are not changed its the same");
			}else {
				System.out.println("The values do not match");
			
			}
			
			//To verify the column sort
			BarcodeProcessor.columnSorting("SequenceCloumnInGrp", "SequenceNameHeader", map.get("SortingOrder"),map.get("DataType"));
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
			
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
