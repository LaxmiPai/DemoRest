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
import org.testng.Assert;


import com.oracle.core.dataProvider.ExcelReader;
import com.oracle.core.utils.PropertyReader;
import com.oracle.sim.pageFactory.PageFactory;
import com.oracle.sim.pages.Base.SimBasePage;
import com.oracle.sim.testcases.ShipmentReason.ShipmentReasons;
import com.oracle.sim.utils.SIMWebdriverBase;


public class ShipmentReasons {
	public static Logger logger = Logger.getLogger(ShipmentReasons.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pageFactory = new PageFactory();

	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage ShipmentReasonsPage;
	private String GridRecoedValue;
	//private String GridRecoedValue1;

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
		HomePage.verifyTextValue("Title", HomePage.getProperty("Title"));
		HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
		HomePage.storeIdCheck();
		HomePage.click("Navigation");
		HomePage.click("Admin");
		HomePage.click("DataSetup");
		HomePage.click("ShipmentReason");

	}
	//Data Provider for UI Shipment reasons 
	@DataProvider(name = "UIShipmentTestData")
	public Object[][] getTestDataUIShipment() throws Exception {

		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("ShipmentReasonTestData"),"UIShipment");
		return testObjArray;
	}

	//UI Verification of UI of Shipment Reasons Screen
	@Test(dataProvider = "UIShipmentTestData", priority = 1)
	public void shipmentUI(Map<String, String> map) throws Exception {

		ShipmentReasonsPage = pageFactory.initialize("ShipmentReasonsPage");
		System.out.println("Verify the Title of Sub-Buckets Screen");
		ShipmentReasonsPage.explicitWaitForVisibility("Title");// verify the Title of Sub-Buckets
		
		ShipmentReasonsPage.verifyTextValue("Title",map.get("Title"));

		// Verify the Refresh Button
		System.out.println("Verify the Refresh Button");
		ShipmentReasonsPage.verifyElementIsPresent("RefreshButton");
		System.out.println("Verify the Add Button");
		ShipmentReasonsPage.verifyElementIsPresent("AddIcon");
		System.out.println("Verify the Delete Button");
		ShipmentReasonsPage.verifyElementIsPresent("DeleteIcon");
		System.out.println("Verify the Grid View Menu");
		ShipmentReasonsPage.verifyElementIsPresent("Grid View Menu");
		System.out.println("Verify the Apply Button");
		ShipmentReasonsPage.click("AddIcon");
		ShipmentReasonsPage.verifyElementIsPresent("ApplyButton");
		System.out.println("Verify the Cancel Button");
		ShipmentReasonsPage.verifyElementIsPresent("CancelButton");
		ShipmentReasonsPage.click("CancelButton");

	}
	
	
	@DataProvider(name = "AddShipmentTestData")
	public Object[][] getTestDataShipment() throws Exception {

		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("ShipmentReasonTestData"),"AddShipment");
		return testObjArray;
	}

	// Add Shipment Reason
	@Test(dataProvider = "AddShipmentTestData", priority = 2)
	public void shipmentAdd(Map<String, String> map) throws Exception {

		// Go to Shipment Reason Setup Page
		ShipmentReasonsPage.explicitWaitForElementToBeClickable("Title");
		
		// add Shipment Reason
		ShipmentReasonsPage.explicitWaitForElementToBeClickable("AddIcon");
		ShipmentReasonsPage.click("AddIcon");
		// Input Values in Detail section
		ShipmentReasonsPage.explicitWaitForElementToBeClickable("Type");
		ShipmentReasonsPage.selectDropDownValue("Type", map.get("Type"));
		ShipmentReasonsPage.enterIntoTextBox("Code", map.get("Code"));
		ShipmentReasonsPage.enterIntoTextBox("Description", map.get("Description"));

		// Finisher , Warehouse, Store Shipment Reasons.
		String value = map.get("Type");
		if (value.contains("Finisher") || value.contains("Warehouse") || value.contains("Store")) {

			ShipmentReasonsPage.explicitWaitForElementToBeClickable("Sub-bucket");
			ShipmentReasonsPage.selectDropDownValue("Sub-bucket", map.get("Subbucket"));
			ShipmentReasonsPage.click("ApplyButton");

		}
		String value1 = map.get("InventoryStatus");

		if (value.contentEquals("Supplier")) {

			if (value1.contentEquals("Available")) {
				ShipmentReasonsPage.verifyCheckBoxIsSelected("InventoryStatus");

				ShipmentReasonsPage.explicitWaitForElementToBeClickable("ApplyButtton");
				ShipmentReasonsPage.click("ApplyButton");

			} else {

				ShipmentReasonsPage.explicitWaitForElementToBeClickable("InventoryStatus");
				ShipmentReasonsPage.click("InventoryStatus");

				ShipmentReasonsPage.explicitWaitForElementToBeClickable("Sub-bucket");
				ShipmentReasonsPage.selectDropDownValue("Sub-bucket", map.get("Subbucket"));
				ShipmentReasonsPage.click("ApplyButton");

			}
		}

		ShipmentReasonsPage.click("SaveIcon");
		
		
		
		ShipmentReasonsPage.explicitWaitForElementToBeClickable("YesButton");
		// Click Yes in Confirmation pop up
		ShipmentReasonsPage.click("YesButton");
		

	}
	@DataProvider(name = "SearchShipmentTestData")
	public Object[][] getSubucketData() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("ShipmentReasonTestData"),"AddShipment");
		return testObjArray;
	}

	//Search Shipment Reasons
	@Test(dataProvider = "SearchShipmentTestData", priority =3)
	public void shipmentSearch(Map<String, String> map) throws Exception {

		ShipmentReasonsPage.explicitWaitForElementToBeClickable("Title");
		ShipmentReasonsPage.explicitWaitForElementToBeClickable("AddIcon");
		ShipmentReasonsPage.explicitWaitForElementToBeClickable("RefreshButton");
		ShipmentReasonsPage.explicitWaitForElementToBeClickable("DeleteIcon");

		// Verifying the inserted record
		ShipmentReasonsPage.explicitWaitForElementToBeClickable("FilterName");
		ShipmentReasonsPage.click("FilterName");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		ShipmentReasonsPage.enterIntoTextBox("FilterName", map.get("Description"));
		String GridRecordValue= ShipmentReasonsPage.getText("GridRecord");
		System.out.println("Grid Record Value :"+GridRecoedValue);
		
		//ShipmentReasonsPage.verifyTextValue("GridRecord", map.get("Description"));
		Assert.assertEquals(GridRecordValue, map.get("Description"));
	}
	@DataProvider(name = "EditShipmentData")
	public Object[][] getEditSubbucketData() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("ShipmentReasonTestData"),"EditShipment");
		return testObjArray;
	}

	//Edit Shipment Reasons
	@Test(dataProvider = "EditShipmentData", priority = 4)
	public void shipmentEdit(Map<String, String> map) throws Exception {
		ShipmentReasonsPage.explicitWaitForElementToBeClickable("GridRecord");
		ShipmentReasonsPage.click("GridRecord");
		ShipmentReasonsPage.explicitWaitForElementToBeClickable("EditIcon");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));

		ShipmentReasonsPage.click("EditIcon");

		ShipmentReasonsPage.explicitWaitForVisibility("Description");
		
		ShipmentReasonsPage.enterIntoTextBox("Description", map.get("Description"));
		ShipmentReasonsPage.click("ApplyButton");
		ShipmentReasonsPage.explicitWaitForElementToBeClickable("SaveIcon");
		ShipmentReasonsPage.click("SaveIcon");

		// Click Yes in Confirmation pop up
		ShipmentReasonsPage.click("YesButton");
		ShipmentReasonsPage.explicitWaitForElementToBeClickable("FilterName");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		ShipmentReasonsPage.click("FilterName");
		ShipmentReasonsPage.enterIntoTextBox("FilterName", map.get("Description"));
		String GridRecordValue= ShipmentReasonsPage.getText("GridRecord");
		System.out.println("Grid Record Value :"+GridRecordValue);
		Assert.assertEquals(GridRecordValue, map.get("Description"));
		logger.info("Edited the Existing Shipment-Reason Value");
		

	}
	
	//Delete the Shipment Reasons 
	@Test(dataProvider = "EditShipmentData", priority = 5)
	public void shipmentDelete(Map<String, String> map) throws Exception {

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
		ShipmentReasonsPage.explicitWaitForElementToBeClickable("SaveIcon");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		ShipmentReasonsPage.click("SaveIcon");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		// Click Yes in Confirmation pop up
		
		ShipmentReasonsPage.click("YesButton");
		

		ShipmentReasonsPage.explicitWaitForElementToBeClickable("FilterName");
		ShipmentReasonsPage.click("FilterName");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		ShipmentReasonsPage.enterIntoTextBox("FilterName", map.get("Description"));
		ShipmentReasonsPage.explicitWaitForVisibility("NorecordsMsg");
		//ShipmentReasonsPage.verifyTextValue("NorecordsMsg", "No rows match the current filter(s).");
		String ErrorMsgValue= ShipmentReasonsPage.getText("NorecordsMsg");
		System.out.println("Error Message Value :"+ErrorMsgValue);
		String ErrorMessage= map.get("ER1");
		Assert.assertEquals(ErrorMsgValue,ErrorMessage);
		logger.info("Deleted the Existing Shipment-Reason Value");
		
		
		

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
