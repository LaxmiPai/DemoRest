package com.oracle.sim.testcases.InventoryAdjustment;

import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.oracle.core.dataProvider.ExcelReader;
import com.oracle.core.utils.PropertyReader;
import com.oracle.sim.pageFactory.PageFactory;
import com.oracle.sim.pages.Base.SimBasePage;
import com.oracle.sim.utils.SIMWebdriverBase;

public class InventoryAdjmntUinUnavailableToInStock {
	public static Logger logger=Logger.getLogger(InventoryAdjmntUinUnavailableToInStock.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	Random random = new Random();
	PageFactory pagefactory=new PageFactory();

	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage RoleMaintenancePage;
	SimBasePage SystemAdministrationPage;
	SimBasePage InventoryAdjustmentPage;
	private static String Uin;
	SoftAssert softAssertion = new SoftAssert();
	
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
				"InvAdjust");
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
	public void createInStockUinInventoryAdjustment(Map<String,String> map) throws Exception {
		InventoryAdjustmentPage=pagefactory.initialize("InventoryAdjustmentPage");
		HomePage.click("InventoryManagement");
		HomePage.click("InventoryAdjustment");
		InventoryAdjustmentPage.verifyTextValue("InventoryAdjustmentListTitle",map.get("ListTitle"));
		
		//Click on Create button
		InventoryAdjustmentPage.click("CreateButton");
		
		//To create an Uin with in-stock status
		Uin=InventoryAdjustmentPage.CreateUin(map.get("Reason2"), map.get("Item"));
		
	
	}
	
	@Test(dataProvider="InvntAdjstmntDisp", priority=3)
	public void createUinInventoryAdjustment(Map<String,String> map) throws Exception {
		
		//db connection 
		HomePage.getConnection();
		
//		//TO fetch UIN status from DB After Confirmation
//		String query="Select UIN from item_uin where ROWNUM=1 AND store_ID='1311' AND Item_ID='100000024' AND type='1'AND status='0'";
//		Uin=InventoryAdjustmentPage.specificUINQuery(query);
//		System.out.println(Uin);

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
		InventoryAdjustmentPage.click("DetailReasonDropDown");
		InventoryAdjustmentPage.clearElement("DropDownFilter");
		InventoryAdjustmentPage.click("DropDownFilter");
		InventoryAdjustmentPage.enterIntoTextBox("DropDownFilter", map.get("Reason"));
		InventoryAdjustmentPage.explicitWaitForVisibility("DropDownHighlightedValue");
		InventoryAdjustmentPage.click("DropDownHighlightedValue");
					
		
		InventoryAdjustmentPage.click("UinButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("High")));
		InventoryAdjustmentPage.click("UinAddButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("High")));
		InventoryAdjustmentPage.doubleClick();
		InventoryAdjustmentPage.enterIntoTextBox("FirstUinInput",Uin );
		InventoryAdjustmentPage.click("UinApplyButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("High")));		
		InventoryAdjustmentPage.click("ApplyButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		InventoryAdjustmentPage.click("ConfirmButton");
		InventoryAdjustmentPage.verifyElementIsDisplayed("ConfirmationMsg");
		InventoryAdjustmentPage.explicitWaitForElementToBeClickable("YesButton");
		InventoryAdjustmentPage.click("YesButton");
		
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
		String query2="Select status from Item_UIN where Uin='"+ Uin+"'";
		int ObtainedUinStatus=InventoryAdjustmentPage.specificUINQueryForStatus(query2);
		String actualStatus=Integer.toString(ObtainedUinStatus);
		softAssertion.assertEquals(actualStatus, map.get("Reason3"));
		
		//Close the DB Connection()
		HomePage.closeDBConnection();
		
	}
	
	
	@Test(dataProvider="InvntAdjstmntDisp", priority=4)
	public void createUinInventoryAdjustmenttoInStock(Map<String,String> map) throws Exception {

		//db connection 
		HomePage.getConnection();
		
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
				InventoryAdjustmentPage.click("DetailReasonDropDown");
				InventoryAdjustmentPage.clearElement("DropDownFilter");
				InventoryAdjustmentPage.click("DropDownFilter");
				InventoryAdjustmentPage.enterIntoTextBox("DropDownFilter", map.get("Reason1"));
				InventoryAdjustmentPage.explicitWaitForVisibility("DropDownHighlightedValue");
//				InventoryAdjustmentPage.verifyHighlightedTextValue("DropDownHighlightedValue",map.get("Reason1"));
				InventoryAdjustmentPage.click("DropDownHighlightedValue");
							
			//	String ObtainedUin=Integer.toString(Uin);
				InventoryAdjustmentPage.click("UinButton");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("High")));
				InventoryAdjustmentPage.click("UinAddButton");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("High")));
				InventoryAdjustmentPage.doubleClick();
				InventoryAdjustmentPage.enterIntoTextBox("FirstUinInput",Uin );
				InventoryAdjustmentPage.click("UinApplyButton");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("High")));			
				InventoryAdjustmentPage.click("ApplyButton");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				InventoryAdjustmentPage.click("ConfirmButton");
				InventoryAdjustmentPage.verifyElementIsDisplayed("ConfirmationMsg");
				InventoryAdjustmentPage.explicitWaitForElementToBeClickable("YesButton");
				InventoryAdjustmentPage.click("YesButton");
				
	
	Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
	String query1="Select status from Item_UIN where Uin='"+ Uin+"'";
	int ObtainedUinStatus=InventoryAdjustmentPage.specificUINQueryForStatus(query1);
	String actualStatus=Integer.toString(ObtainedUinStatus);
	softAssertion.assertEquals(actualStatus, map.get("Reason1"));

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


	
	
	

