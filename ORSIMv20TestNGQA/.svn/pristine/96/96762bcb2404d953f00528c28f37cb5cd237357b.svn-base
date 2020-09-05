package com.oracle.sim.testcases.ShipmentReason;
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


public class DetailBlockSubbucketShipmentReasons {
	public static Logger logger = Logger.getLogger(DetailBlockSubbucketShipmentReasons.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pageFactory = new PageFactory();

	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage ShipmentReasonsPage;
	
	@BeforeClass()
	public void setup(ITestContext context) throws Exception {
		logger.info("TestCase Name: " +logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
		LoginPage = pageFactory.initialize("LoginPage");
		HomePage = pageFactory.initialize("HomePage");
		ShipmentReasonsPage = pageFactory.initialize("ShipmentReasonsPage");
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username", LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password", LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");

		HomePage = pageFactory.initialize("HomePage");
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.verifyTextValue("Title", HomePage.getProperty("Title"));
		HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
		HomePage.storeIdCheck();

		
	}

	//Data Provider for Detail Block Sub-bucket Shipment reasons 
		@DataProvider(name = "DetailBlockSubbucketShipmentTestData")
		public Object[][] getTestDataUIShipment() throws Exception {

			Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("ShipmentReasonTestData"),"DetailBlockSubbucket");
			return testObjArray;
		}

		//Test Case of Detail Block Sub-bucket Shipment reasons 
		@Test(dataProvider = "DetailBlockSubbucketShipmentTestData", priority = 1)
		public void shipmentDetailBlockSubbuckets(Map<String, String> map) throws Exception {
				
			HomePage.click("Navigation");
			HomePage.click("Admin");
			HomePage.click("ConfigurationMenu");
			HomePage.click("SystemAdministrationPage");
			
			ShipmentReasonsPage.explicitWaitForElementToBeClickable("FilterByOption");
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
			ShipmentReasonsPage.click("FilterByOption");
			ShipmentReasonsPage.enterIntoTextBox("FilterByOption",map.get("Option"));
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
			ShipmentReasonsPage.click("GridRecord");
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
			ShipmentReasonsPage.explicitWaitForElementToBeClickable("EditIcon");
			ShipmentReasonsPage.click("EditIcon");
			// Disable the Value Switch in Enable Sub-Bucket System Parameter 
			ShipmentReasonsPage.explicitWaitForElementToBeClickable("ValueSwitch");
			ShipmentReasonsPage.click("ValueSwitch");
			ShipmentReasonsPage.click("ApplyButton");
			ShipmentReasonsPage.explicitWaitForElementToBeClickable("SaveIcon");
			ShipmentReasonsPage.click("SaveIcon");
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
			// Click Yes in Confirmation pop up
			ShipmentReasonsPage.click("YesButton");
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));		
			//HomePage.click("Navigation");
			//HomePage.click("Admin");
			ShipmentReasonsPage.click("BackLink");
			HomePage.click("DataSetup");
			HomePage.click("ShipmentReason");

			ShipmentReasonsPage.RefreshWebPage();
			// add Shipment Reason
			ShipmentReasonsPage.explicitWaitForElementToBeClickable("AddIcon");
			ShipmentReasonsPage.click("AddIcon");
			// Input Values in Detail section
			ShipmentReasonsPage.explicitWaitForElementToBeClickable("Type");
			ShipmentReasonsPage.selectDropDownValue("Type", map.get("Type"));
			ShipmentReasonsPage.enterIntoTextBox("Code", map.get("Code"));
			ShipmentReasonsPage.enterIntoTextBox("Description", map.get("Description"));
			//Verify the Unavailable Label is Present 
			ShipmentReasonsPage.verifyElementIsPresent("UnavailabeLabel");
			//ShipmentReasonsPage.verifyElementIsNotPresent("Sub-bucket");
			ShipmentReasonsPage.explicitWaitForElementToBeClickable("ApplyButton");
			ShipmentReasonsPage.click("ApplyButton");
			ShipmentReasonsPage.click("SaveIcon");
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
			//ShipmentReasonsPage.explicitWaitForElementToBeClickable("YesButton");
			// Click Yes in Confirmation pop up
			ShipmentReasonsPage.click("YesButton");
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
			//Delete the Existing One :
			ShipmentReasonsPage.explicitWaitForElementToBeClickable("FilterName");
			ShipmentReasonsPage.click("FilterName");
			ShipmentReasonsPage.enterIntoTextBox("FilterName", map.get("Description"));
			ShipmentReasonsPage.explicitWaitForElementToBeClickable("GridRecord");
			ShipmentReasonsPage.click("GridRecord");
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
			ShipmentReasonsPage.explicitWaitForElementToBeClickable("DeleteIcon");
			ShipmentReasonsPage.click("DeleteIcon");
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
			
			ShipmentReasonsPage.click("YesButton");
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
			ShipmentReasonsPage.click("SaveIcon");
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
			// Click Yes in Confirmation pop up
			
			ShipmentReasonsPage.click("YesButton");
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
			HomePage.click("Navigation");
			HomePage.click("Admin");
			HomePage.click("ConfigurationMenu");
			HomePage.click("SystemAdministrationPage");
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
			ShipmentReasonsPage.explicitWaitForElementToBeClickable("FilterByOption");
			ShipmentReasonsPage.click("FilterByOption");
			ShipmentReasonsPage.enterIntoTextBox("FilterByOption",map.get("Option"));
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
			ShipmentReasonsPage.click("GridRecord");
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
			ShipmentReasonsPage.explicitWaitForElementToBeClickable("EditIcon");
			ShipmentReasonsPage.click("EditIcon");
			// Disable the Value Switch in Enable Sub-Bucket System Parameter 
			ShipmentReasonsPage.explicitWaitForElementToBeClickable("ValueSwitch");
			ShipmentReasonsPage.click("ValueSwitch");
			ShipmentReasonsPage.click("ApplyButton");
			ShipmentReasonsPage.explicitWaitForElementToBeClickable("SaveIcon");
			ShipmentReasonsPage.click("SaveIcon");
			// Click Yes in Confirmation pop up
			ShipmentReasonsPage.click("YesButton");
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));		
			//HomePage.click("Navigation");
			//HomePage.click("Admin");
			ShipmentReasonsPage.click("BackLink");
			HomePage.click("DataSetup");
			HomePage.click("ShipmentReason");

			ShipmentReasonsPage.RefreshWebPage();
			
			ShipmentReasonsPage.verifyElementIsEnabled("AddIcon");
			ShipmentReasonsPage.verifyElementIsEnabled("RefreshButton");
			ShipmentReasonsPage.verifyElementIsEnabled("DeleteIcon");
			ShipmentReasonsPage.click("AddIcon");
			ShipmentReasonsPage.verifyElementIsEnabled("ApplyButton");
			ShipmentReasonsPage.verifyElementIsEnabled("CancelButton");
			ShipmentReasonsPage.verifyElementIsEnabled("Sub-bucket");
			
		//Click on Cancel Button
			ShipmentReasonsPage.click("CancelButton");
			
			//verify Sub-Bucket in Edit Mode..
			ShipmentReasonsPage.explicitWaitForElementToBeClickable("FilterByType");
			ShipmentReasonsPage.click("FilterByType");
			ShipmentReasonsPage.enterIntoTextBox("FilterByType","Supplier");
			ShipmentReasonsPage.explicitWaitForElementToBeClickable("GridRecord");
			ShipmentReasonsPage.click("GridRecord");
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
			ShipmentReasonsPage.click("EditIcon");
			ShipmentReasonsPage.verifyElementIsDisabled("SubbucketinEditMode");
			
			ShipmentReasonsPage.click("CancelButton");
			
			
			
			

			
			
			
			
			
			
			
			
			
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
