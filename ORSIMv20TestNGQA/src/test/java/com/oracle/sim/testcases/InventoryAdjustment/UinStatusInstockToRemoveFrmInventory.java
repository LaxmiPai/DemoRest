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

public class UinStatusInstockToRemoveFrmInventory {
	public static Logger logger=Logger.getLogger(UinStatusInstockToRemoveFrmInventory.class.getName());
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
		//db connection 
		HomePage.getConnection();
		
	}
	
	@DataProvider(name = "InvntAdjstmntDisp")
	public Object[][] getSubbucketTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("InventoryAdjustmentTestData"),
				"InStockToRemove");
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
	public void createUinInventoryAdjustment(Map<String,String> map) throws Exception {
		InventoryAdjustmentPage=pagefactory.initialize("InventoryAdjustmentPage");
		HomePage.click("InventoryManagement");
		HomePage.click("InventoryAdjustment");
		InventoryAdjustmentPage.verifyTextValue("InventoryAdjustmentListTitle",map.get("ListTitle"));
		
		
		
//		//TO fetch UIN status from DB After Confirmation
//		String query="Select UIN from item_uin where ROWNUM=1 AND store_ID='1311' AND Item_ID='100000024' AND type='1'AND status='0'";
//		Uin=InventoryAdjustmentPage.specificUINQuery(query);
//		System.out.println(Uin);

		//Click on Create button
		InventoryAdjustmentPage.click("CreateButton");
		
		//To verify the detail screen Title
		InventoryAdjustmentPage.verifyTextValue("InventoryAdjustmentDetailTitle", map.get("DetailTitle"));
		
		//To generate an UIN in in-stock status
		Uin=InventoryAdjustmentPage.CreateUin(map.get("Reason1"), map.get("Item"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
		
		//Click on Create button
		InventoryAdjustmentPage.click("CreateButton");
				
		
		//To perform Inventory Adjustment
		InventoryAdjustmentPage.performUinInventoryAdjustment(map.get("Reason"),Uin, map.get("Item"));
		
		
		
	}
	
	
	@Test(dataProvider="InvntAdjstmntDisp", priority=3)
	public void VerifyTheStatus(Map<String,String> map) throws Exception {
	
	Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
	String query1="Select status from Item_UIN where Uin='"+Uin+"'";
	int ObtainedUinStatus=InventoryAdjustmentPage.specificUINQueryForStatus(query1);
	String actualStatus=Integer.toString(ObtainedUinStatus);
	System.out.println(actualStatus);
	softAssertion.assertEquals(actualStatus, map.get("Expectedstatus"));

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


	

