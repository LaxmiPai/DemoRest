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

public class VariousStatuesToInStock {
	public static Logger logger=Logger.getLogger(VariousStatuesToInStock.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	Random random = new Random();
	PageFactory pagefactory=new PageFactory();

	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage RoleMaintenancePage;
	SimBasePage SystemAdministrationPage;
	SimBasePage InventoryAdjustmentPage;
	SoftAssert softAssertion = new SoftAssert();
	public static String obtuin;
	
	
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
		HomePage.verifyTextValue("Title", HomePage.getProperty("Title"));
		HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
		HomePage.storeIdCheck();
		
		
}
	
	@DataProvider(name = "InvntAdjstmntDisp")
	public Object[][] getSubbucketTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("InventoryAdjustmentTestData"),
				"VariousToInStock");
		return testObjArray;
	}
	
	@Test(dataProvider="InvntAdjstmntDisp", priority=1)
	public void verifyRole(Map<String,String> map) throws Exception {
		//Navigating to role maintenance page
		HomePage.explicitWaitForElementToBeClickable("Navigation");
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
	public void VerifyCreateNewUin(Map<String,String> map) throws Exception {
		InventoryAdjustmentPage=pagefactory.initialize("InventoryAdjustmentPage");
		HomePage.click("InventoryManagement");
		HomePage.click("InventoryAdjustment");
		InventoryAdjustmentPage.verifyTextValue("InventoryAdjustmentListTitle",map.get("ListTitle"));
		
		//db connection 
		HomePage.getConnection();
		 

		InventoryAdjustmentPage.click("CreateButton");
		
		//To verify the detail screen Title
		InventoryAdjustmentPage.verifyTextValue("InventoryAdjustmentDetailTitle", map.get("DetailTitle"));
		
		//To create uin for missing status
		obtuin=InventoryAdjustmentPage.CreateUin(map.get("Reason"),map.get("Item"));
		
		//Gets status from the DB
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
		String query1="Select status from Item_UIN where Uin='"+obtuin+"'";
		
		int ObtainedUinStatus=InventoryAdjustmentPage.specificUINQueryForStatus(query1);
		String actualStatus=Integer.toString(ObtainedUinStatus);
		System.out.println(actualStatus);
		logger.info("After Status: "+ObtainedUinStatus);
		softAssertion.assertEquals(actualStatus, map.get("Expectedstatus"));

		
		//Close the DB Connection()
		HomePage.closeDBConnection();
		}
//		}
	
	
	@Test(dataProvider="InvntAdjstmntDisp", priority=3)
	public void RemoveFrmInvToInStock(Map<String,String> map) throws Exception {
		//db connection 
		HomePage.getConnection();
		
		//Click on Create button
		InventoryAdjustmentPage.click("CreateButton");
		
		//To verify the detail screen Title
		InventoryAdjustmentPage.verifyTextValue("InventoryAdjustmentDetailTitle", map.get("DetailTitle"));
	
		InventoryAdjustmentPage.performUinInventoryAdjustment(map.get("Reason1"), obtuin,map.get("Item"));
		
		
		//gets status for the UIN from DB
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
		String query3="Select status from Item_UIN where Uin='"+obtuin+"'";
		int ObtainedUinStatus=InventoryAdjustmentPage.specificUINQueryForStatus(query3);
		String actualStatus=Integer.toString(ObtainedUinStatus);
		System.out.println(actualStatus);
		logger.info("After Status: "+ObtainedUinStatus);
		softAssertion.assertEquals(actualStatus, map.get("Expected2"));
		
		//Close the DB Connection()
		HomePage.closeDBConnection();
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
