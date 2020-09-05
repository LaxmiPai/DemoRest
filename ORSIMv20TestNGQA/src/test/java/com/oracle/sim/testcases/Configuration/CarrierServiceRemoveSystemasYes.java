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

public class CarrierServiceRemoveSystemasYes {
	
	public static Logger logger = Logger.getLogger(CarrierServiceRemoveSystemasYes.class.getName());
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
	@DataProvider(name ="RemoveCarrierService")
	public Object[][] getTestDataForCarrierServiceDelete() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("CarrierServices"),"RemoveCarrierService");
		return testObjArray;

	}

	@Test(dataProvider ="RemoveCarrierService")
	public void CarrierSevicesRemove(Map<String, String> map) throws Exception {
			HomePage.click("Navigation");
			HomePage.click("AdminMenu");
			HomePage.click("ConfigurationMenu");
			HomePage.click("CarrierServiceMenu");
			
			
			CarrierServicesPage.verifyElementIsEnabled("RefreshButton");
			CarrierServicesPage.verifyElementIsEnabled("AddIcon");
			CarrierServicesPage.verifyElementIsEnabled("DeleteIcon");
			
			//No Record Select to Delete
			CarrierServicesPage.explicitWaitForElementToBeClickable("DeleteIcon");
			
			CarrierServicesPage.click("DeleteIcon");
			CarrierServicesPage.explicitWaitForVisibility("NoRecordDelete");
			CarrierServicesPage.verifyTextValue("NoRecordDelete",map.get("EM0"));
			CarrierServicesPage.explicitWaitForElementToBeClickable("OkButton");
			CarrierServicesPage.click("OkButton");
			
		
		// Verifying the inserted record
		  CarrierServicesPage.scrollToViewElement("FilterBySystem");
		  CarrierServicesPage.explicitWaitForElementToBeClickable("FilterBySystem");
		  CarrierServicesPage.click("FilterBySystem");
		  CarrierServicesPage.enterIntoTextBox("FilterBySystem",map.get("System"));
		  CarrierServicesPage.explicitWaitForElementToBeClickable("GridHighLight");
		  CarrierServicesPage.click("GridHighLight");
		  //Click On Delete Button
		  
		  Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		  CarrierServicesPage.click("DeleteIcon");
		  //Verify Delete Confirmation Message
		  CarrierServicesPage.verifyTextValue("DeleteConfirmMsg",map.get("DeleteConfirmMsg"));
		  CarrierServicesPage.click("NoButton");
		  Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		  CarrierServicesPage.click("DeleteIcon");
		  //Verify Delete Confirmation Message
		  CarrierServicesPage.verifyTextValue("DeleteConfirmMsg",map.get("DeleteConfirmMsg"));
		  CarrierServicesPage.click("YesButton");
		  CarrierServicesPage.click("YesButton");
		  CarrierServicesPage.explicitWaitForVisibility("RemoveErMsg");
		  CarrierServicesPage.verifyTextValue("RemoveErMsg",map.get("SystemRequiredMsg"));
		  CarrierServicesPage.explicitWaitForElementToBeClickable("OkButton");
		  CarrierServicesPage.click("OkButton");
		  logger.info("Removing System Parameter set as :Yes Inventory Adjustment Reasons Code..!");
		
		  
		 
		    	
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
