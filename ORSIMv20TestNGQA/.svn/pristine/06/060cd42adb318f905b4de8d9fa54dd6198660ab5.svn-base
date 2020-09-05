package com.oracle.sim.testcases.InventoryAdjustment;

import java.util.ArrayList;
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

public class InvtAdjustmntDispPlusStockOnHand {
	public static Logger logger=Logger.getLogger(InvtAdjustmntDispPlusStockOnHand.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pagefactory=new PageFactory();

	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage RoleMaintenancePage;
	SimBasePage SystemAdministrationPage;
	SimBasePage InventoryAdjustmentPage;

	@BeforeClass
	public void setup(ITestContext context) throws Exception {
		logger.info("TestCase Name: " +logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
		LoginPage=pagefactory.initialize("LoginPage");
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username",LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password",LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");
		HomePage=pagefactory.initialize("HomePage");
		
	}
	
	@DataProvider(name = "InvntAdjstmntDisp")
	public Object[][] getSubbucketTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("InventoryAdjustmentTestData"),
				"InvtAdustmntDisp");
		return testObjArray;
	}
	
	@Test(dataProvider="InvntAdjstmntDisp", priority=1)
	public void verifyRole(Map<String,String> map) throws Exception {
		//Navigating to role maintenance page
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.storeIdCheck();
		HomePage.click("Navigation");
		HomePage.click("Security");
		HomePage.click("RoleMaintenance");
		RoleMaintenancePage=pagefactory.initialize("RoleMaintenancePage");
		
		//Verifying RoleMaintenance Page Title
		RoleMaintenancePage.explicitWaitForElementToBeClickable("Title");
		RoleMaintenancePage.verifyTextValue("Title",map.get("Title"));

		//Granting the user permission
		String userRole=propReader.getApplicationproperty("UserRole");
		RoleMaintenancePage.grantAllPermissions(userRole);
	}
	
	@Test(dataProvider="InvntAdjstmntDisp", priority=2)
	public void CreateInvntAdjustment(Map<String,String> map) throws Exception {
		InventoryAdjustmentPage=pagefactory.initialize("InventoryAdjustmentPage");
		HomePage.click("InventoryManagement");
		HomePage.click("InventoryAdjustment");
		
		//db connection 
		HomePage.getConnection();
		
		//Initially getting all the values SOH,TSOH,ASOH.
		
		System.out.println("---------------Before inv adjustment:----------");
		ArrayList<String> list=InventoryAdjustmentPage.calculateAvailableUnavailableSoh(map.get("Item"));
		if(list.isEmpty()) {
			logger.info("No records available");
		}else {
			int tsoh1=Integer.parseInt(list.get(1));
			int availableSoh1=Integer.parseInt(list.get(2));
			int unavailableSoh1=Integer.parseInt(list.get(3));
//			int adjustQuantity=Integer.parseInt(map.get("MinimumQuantity"));
		
		InventoryAdjustmentPage.verifyTextValue("InventoryAdjustmentListTitle",map.get("ListTitle"));
		
		//Click on Create button
		InventoryAdjustmentPage.click("CreateButton");
		
		//To verify the detail screen Title
		InventoryAdjustmentPage.verifyTextValue("InventoryAdjustmentDetailTitle", map.get("DetailTitle"));
		
		//Click on the create button
		InventoryAdjustmentPage.click("AddIcon");
		
		//Input an item in to the scan box
		InventoryAdjustmentPage.enterIntoTextBox("ScanItemBox", map.get("Item"));
		InventoryAdjustmentPage.click("ClickScanBox");
		
		//Select the reason from the Reason Drop down
		InventoryAdjustmentPage.selectDropDownValue("DetailReasonDropDown", map.get("Reason"));
		InventoryAdjustmentPage.enterIntoTextBox("QuantityInputField", map.get("Quantity"));
		
		InventoryAdjustmentPage.click("ApplyButton");
		InventoryAdjustmentPage.click("ConfirmButton");
		InventoryAdjustmentPage.verifyElementIsDisplayed("ConfirmationMsg");
		InventoryAdjustmentPage.explicitWaitForElementToBeClickable("YesButton");
		InventoryAdjustmentPage.click("YesButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		
		//Closing the DB Connection
		HomePage.closeDBConnection();
		
		//To verify the TSOH and the SOH unavailable after inventory adjustment
		//DB Connection
		HomePage.getConnection();
		InventoryAdjustmentPage.calculateAvailableUnavailableSoh("Item");
		//DB Connection closed
		HomePage.closeDBConnection();
				
		//To Assert if the values are increased by the quantity that was added
		//DB Connection
		HomePage.getConnection();
		System.out.println("---------------After inv adjustment:----------");
		ArrayList<String> verificationlist=InventoryAdjustmentPage.calculateAvailableUnavailableSoh(map.get("Item"));
		if(verificationlist.isEmpty()) {
		logger.info("No records available");
		}else {			
		int tsoh2=Integer.parseInt(verificationlist.get(1));
		int availableSoh2=Integer.parseInt(verificationlist.get(2));
		int unavailableSoh2=Integer.parseInt(verificationlist.get(3));
		int FvalueTSOH=tsoh2-tsoh1;
		int FavailableValue=availableSoh2-availableSoh1;
		int Evalue=Integer.parseInt(map.get("Quantity"));
		InventoryAdjustmentPage.verifyIntValuesAreEqual(FvalueTSOH,Evalue);
		InventoryAdjustmentPage.verifyIntValuesAreEqual(FavailableValue,Evalue);
		InventoryAdjustmentPage.verifyIntValuesAreEqual(unavailableSoh1,unavailableSoh2);
			}
		}
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
				HomePage.closeDBConnection();
				SIMWebdriverBase.close();
			}
		}
	
}
