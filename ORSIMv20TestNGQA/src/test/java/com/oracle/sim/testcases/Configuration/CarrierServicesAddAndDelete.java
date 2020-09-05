package com.oracle.sim.testcases.Configuration;
/**
 * * @author dsorthiy
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

public class CarrierServicesAddAndDelete {
	
	public static Logger logger = Logger.getLogger(CarrierServicesAddAndDelete.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pageFactory = new PageFactory();

	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage CarrierServicesPage;

	@BeforeClass()
	public void setup(ITestContext context) throws Exception {
		logger.info("TestCase Name: " +logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
		LoginPage = pageFactory.initialize("LoginPage");
		HomePage = pageFactory.initialize("HomePage");
		CarrierServicesPage = pageFactory.initialize("CarrierServicesPage");
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username", LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password", LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");
		HomePage.verifyTextValue("Title", HomePage.getProperty("Title"));
		HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
		HomePage.storeIdCheck();
	}

	@DataProvider(name ="CarrierServicesUI")
	public Object[][] getTestDataForCarrierServiceUi() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("CarrierServices"),"CarrierServicesUI");
		return testObjArray;

	}

	@Test(dataProvider ="CarrierServicesUI", priority = 1)
	public void CarrierSevicesUI(Map<String, String> map) throws Exception {
		
		HomePage.click("Navigation");
		HomePage.click("AdminMenu");
		HomePage.click("ConfigurationMenu");
		HomePage.click("CarrierServiceMenu");
		//Verify the Title of CarrierServices Page 
		CarrierServicesPage.verifyTextValue("Title", map.get("CarrierServiceTitle"));
		
		CarrierServicesPage.verifyElementIsEnabled("RefreshButton");
		CarrierServicesPage.verifyElementIsEnabled("AddIcon");
		CarrierServicesPage.verifyElementIsEnabled("DeleteIcon");

		
		CarrierServicesPage.click("AddIcon");
		//Verify ApplyButton and CancelButton Enabled or Not
		CarrierServicesPage.verifyElementIsEnabled("ApplyButton");
		CarrierServicesPage.verifyElementIsEnabled("CancelButton");
		CarrierServicesPage.click("ApplyButton");
		//Verify the Error Message without entering any value 
		CarrierServicesPage.verifyTextValue("Valueisrequired",map.get("EM1"));
		CarrierServicesPage.click("CancelButton");
		logger.info("UI Verification of Carrier Service Done..!");
	}
	@DataProvider(name ="CarrierServicesAdd")
	public Object[][] getTestDataForCarrierServiceAdd() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("CarrierServices"),"CarrierServicesAdd");
		return testObjArray;

	}

	@Test(dataProvider ="CarrierServicesAdd", priority = 2)
	public void CarrierSevicesAdd(Map<String, String> map) throws Exception {
		
		CarrierServicesPage.click("AddIcon");
		//Verify Detail New Title 
		CarrierServicesPage.selectDropDownValue("CarrierDropdown",map.get("Carrier"));
		//Add Data to all Given Fields 
		CarrierServicesPage.enterIntoTextBox("CodeInputTextBox", map.get("Code"));
		CarrierServicesPage.enterIntoTextBox("DescriptionTextBox",map.get("Description"));
		CarrierServicesPage.enterIntoTextBox("DeliveryDaysField", map.get("DeliveryDays"));
		CarrierServicesPage.click("WeightRequiredSwitch");
		CarrierServicesPage.click("PackageSizeRequiredSwitch");
		//CarrierServicesPage.click("DefaultSwitch");
		
		//Click on Apply Button 
		CarrierServicesPage.click("ApplyButton");
		CarrierServicesPage.explicitWaitForElementToBeClickable("SaveIcon");
		CarrierServicesPage.click("SaveIcon");
		
		CarrierServicesPage.explicitWaitForElementToBeClickable("YesButton");
		CarrierServicesPage.click("YesButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		logger.info("Carrier Service Added Successfully!!!");
	}
	
	@DataProvider(name ="CarrierServicesDelete")
	public Object[][] getTestDataForCarrierServiceDelete() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("CarrierServices"),"CarrierServicesDelete");
		return testObjArray;

	}

	@Test(dataProvider ="CarrierServicesDelete", priority = 3)
	public void CarrierSevicesDelete(Map<String, String> map) throws Exception {
		// Verifying the inserted record
		  CarrierServicesPage.explicitWaitForElementToBeClickable("FilterByCode");
		  CarrierServicesPage.click("FilterByCode"); 
		  Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		  CarrierServicesPage.enterIntoTextBox("FilterByCode",map.get("Code1"));
		  
		  //CarrierServicesPage.verifyTextValue("GridHighLight",map.get("Description"));
		  CarrierServicesPage.explicitWaitForElementToBeClickable("GridHighLight");
		  CarrierServicesPage.click("GridHighLight");
		  //Click On Delete Button
		  
		  Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		  CarrierServicesPage.click("DeleteIcon");
		  //Verify Delete COnfirmation Message
		  CarrierServicesPage.verifyTextValue("DeleteConfirmMsg",map.get("DeleteConfirmMsg"));
		  CarrierServicesPage.click("YesButton");
		  Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		  CarrierServicesPage.explicitWaitForElementToBeClickable("SaveIcon");
		  CarrierServicesPage.click("SaveIcon");
		  CarrierServicesPage.explicitWaitForElementToBeClickable("SaveIcon");
		  CarrierServicesPage.click("YesButton");
		  logger.info("Deleted Successfully Carrier Service Successfully!!");
		    	
	}
	@Test(dataProvider ="CarrierServicesDelete", priority = 3)
	public void CarrierSevicesDeletewithRefresh(Map<String, String> map) throws Exception {
		// Verifying the inserted record
		  CarrierServicesPage.explicitWaitForElementToBeClickable("FilterByCode");
		  CarrierServicesPage.click("FilterByCode"); 
		  Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		  CarrierServicesPage.enterIntoTextBox("FilterByCode",map.get("Code2"));
		  
		  //CarrierServicesPage.verifyTextValue("GridHighLight",map.get("Description"));
		  CarrierServicesPage.explicitWaitForElementToBeClickable("GridHighLight");
		  CarrierServicesPage.click("GridHighLight");
		  //Click On Delete Button
		  
		  Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		  CarrierServicesPage.click("DeleteIcon");
		  CarrierServicesPage.click("YesButton");
		  CarrierServicesPage.click("RefreshButton");
			
		  CarrierServicesPage.verifyTextValue("RefreshWarningMsg", map.get("RefreshWarningMsg"));
			//Click on Ok button to verify Record is Present in Grid Table
		  CarrierServicesPage.explicitWaitForElementToBeClickable("OKfooterButton");
		  CarrierServicesPage.click("OKfooterButton");
			//Search through the Code
		  CarrierServicesPage.explicitWaitForElementToBeClickable("FilterByCode");
			//Enter Code into the FilterBox
		  CarrierServicesPage.click("FilterByCode");
		  CarrierServicesPage.enterIntoTextBox("FilterByCode",map.get("Code2"));
		  //CarrierServicesPage.verifyTextValue("GridHighLight",map.get("Code2"));
		  Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		  CarrierServicesPage.verifyElementIsEnabled("RefreshButton");
		  CarrierServicesPage.verifyElementIsEnabled("AddIcon");
		  CarrierServicesPage.verifyElementIsEnabled("DeleteIcon");

		  System.out.println("Deletion: Refresh with Ok Button Verified.");
		  CarrierServicesPage.click("FilterByCode");
		  Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		  CarrierServicesPage.enterIntoTextBox("FilterByCode",map.get("Code2"));
		 
		  CarrierServicesPage.explicitWaitForElementToBeClickable("GridHighLight");
		  CarrierServicesPage.click("GridHighLight");
		  Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		  CarrierServicesPage.click("DeleteIcon");
		  CarrierServicesPage.click("YesButton");
		  CarrierServicesPage.explicitWaitForElementToBeClickable("RefreshButton");
		  CarrierServicesPage.click("RefreshButton");
			//Click on Cancel Button to Verify Record is not Present in Grid Table
		  CarrierServicesPage.explicitWaitForElementToBeClickable("CancelfooterButton");
		  CarrierServicesPage.click("CancelfooterButton");
		  CarrierServicesPage.click("SaveIcon");
		  CarrierServicesPage.click("YesButton");
			
			//Enter Description into the FilterBox
		  CarrierServicesPage.click("FilterByCode");
		  CarrierServicesPage.enterIntoTextBox("FilterByCode",map.get("Code2"));
		  CarrierServicesPage.explicitWaitForVisibility("NorecordsMsg");
		  CarrierServicesPage.verifyTextValue("NorecordsMsg",map.get("EM2"));
		  System.out.println("Deletion :Refresh with Cancel Button Verified.");
			
			
			
			
	
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
