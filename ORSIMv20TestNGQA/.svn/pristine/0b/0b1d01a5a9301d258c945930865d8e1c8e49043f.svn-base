package com.oracle.sim.testcases.InventoryAdjustment;
//import static org.junit.Assert.assertTrue;

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

public class SecurityPermissionInventoryAdjustment {
	
	public static Logger logger = Logger.getLogger(SecurityPermissionInventoryAdjustment.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pageFactory = new PageFactory();
	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage InventoryAdjustmentPage;
	SimBasePage RoleMaintenancePage;
	@BeforeClass()
	public void setup(ITestContext context) throws Exception {
		logger.info("TestCase Name: " +logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
		LoginPage = pageFactory.initialize("LoginPage");
		HomePage = pageFactory.initialize("HomePage");
		InventoryAdjustmentPage= pageFactory.initialize("InventoryAdjustmentPage");
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username", LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password", LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");
		HomePage.verifyTextValue("Title", HomePage.getProperty("Title"));
		HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
		HomePage.storeIdCheck();
		
	}
		
	//DataProvider for Add UIN Inventory  
	@DataProvider(name = "SecurityInventoryAdjustTestData")
	public Object[][] getTestDataForUI() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("InventoryAdjustmentTestData"),"SecurityIM");
		return testObjArray;
	}
		
		//UI verification for Inventory Adjustment Screen
		@Test(dataProvider = "SecurityInventoryAdjustTestData", priority = 1)
		public void SecurityInventoryAdjustment(Map<String, String> map) throws Exception {
			HomePage.click("Navigation");
			HomePage.click("SecurityPage");
			HomePage.click("RoleMaintenancePage");
			String userRole=propReader.getApplicationproperty("UserRole");
			InventoryAdjustmentPage.explicitWaitForElementToBeClickable("RoleNameLink");
			InventoryAdjustmentPage.click("FilterByRoleName");
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
			InventoryAdjustmentPage.enterIntoTextBox("FilterByRoleName",userRole);
			InventoryAdjustmentPage.explicitWaitForElementToBeClickable("RoleNameLink");
			
			InventoryAdjustmentPage.click("RoleNameLink");
			InventoryAdjustmentPage.explicitWaitForElementToBeClickable("FilterByPermission");
			InventoryAdjustmentPage.explicitWaitForElementToBeClickable("AssignedData");
			
			//Assign the Data Value
			InventoryAdjustmentPage.click("FilterByTopic");
			InventoryAdjustmentPage.enterIntoTextBox("FilterByTopic", map.get("Topic"));
			
			InventoryAdjustmentPage.click("FilterByPermission");
			InventoryAdjustmentPage.enterIntoTextBox("FilterByPermission", map.get("Permission"));
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
			InventoryAdjustmentPage.click("GridHighLight");
			//Remove the permission for the user
			InventoryAdjustmentPage.explicitWaitForElementToBeClickable("AssignedData");
					if(InventoryAdjustmentPage.getTitle("AssignedData").equals(map.get("AssignedDataYes"))) {
						InventoryAdjustmentPage.explicitWaitForElementToBeClickable("RevokeSelectedButton");
						InventoryAdjustmentPage.click("RevokeSelectedButton");
						InventoryAdjustmentPage.click("SaveIcon");
						InventoryAdjustmentPage.click("YesButton");
						//wait for DB commit to perform 
						Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
						InventoryAdjustmentPage.click("FilterByTopic");
						InventoryAdjustmentPage.enterIntoTextBox("FilterByTopic", map.get("Topic"));
						InventoryAdjustmentPage.explicitWaitForElementToBeClickable("FilterByPermission");
						InventoryAdjustmentPage.click("FilterByPermission");
						InventoryAdjustmentPage.enterIntoTextBox("FilterByPermission", map.get("Permission"));
						InventoryAdjustmentPage.explicitWaitForElementToBeClickable("AssignedData");
						System.out.println("Revoke Permission:"+InventoryAdjustmentPage.getTitle("AssignedData"));
					}
			
					InventoryAdjustmentPage.click("BackLink");
					InventoryAdjustmentPage.RefreshWebPage();

					HomePage.click("Navigation");
					//Navigate to Inventory Management 
					HomePage.click("InventoryManagement");
					//Navigate to Inventory Adjustment Page
					
					//InventoryAdjustmentPage.VerifyRevokePermissionPage(map.get("PageName"));
					//InventoryAdjustmentPage.verifyElementIsNotPresent("InventoryAdjustmentPageMenu");
					InventoryAdjustmentPage.VerifyPageMenuIsNotPresent("Inventory Adjustment");
					
					
					//Assign the Data Value 
					InventoryAdjustmentPage.click("FilterByTopic");
					InventoryAdjustmentPage.enterIntoTextBox("FilterByTopic", map.get("Topic"));
					
					InventoryAdjustmentPage.click("FilterByPermission");
					InventoryAdjustmentPage.enterIntoTextBox("FilterByPermission", map.get("Permission"));
					InventoryAdjustmentPage.explicitWaitForElementToBeClickable("GridHighLight");
					InventoryAdjustmentPage.click("GridHighLight");
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
					//Assign the permission for the user
					InventoryAdjustmentPage.explicitWaitForElementToBeClickable("AssignedData");
							if(InventoryAdjustmentPage.getTitle("AssignedData").equals(map.get("AssignedDataNo"))) {
								InventoryAdjustmentPage.explicitWaitForElementToBeClickable("AssigneSelectedButton");
								InventoryAdjustmentPage.click("AssigneSelectedButton");
								InventoryAdjustmentPage.click("SaveIcon");
								InventoryAdjustmentPage.click("YesButton");
								//wait for DB commit to perform 
								Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
								InventoryAdjustmentPage.click("FilterByTopic");
								InventoryAdjustmentPage.enterIntoTextBox("FilterByTopic", map.get("Topic"));
								
								InventoryAdjustmentPage.explicitWaitForElementToBeClickable("FilterByPermission");
								InventoryAdjustmentPage.click("FilterByPermission");
								InventoryAdjustmentPage.enterIntoTextBox("FilterByPermission", map.get("Permission"));
								InventoryAdjustmentPage.explicitWaitForElementToBeClickable("AssignedData");
								System.out.println("Assign Permission:"+InventoryAdjustmentPage.getTitle("AssignedData"));
							}
								
							// Refresh the Web-Page..
							InventoryAdjustmentPage.RefreshWebPage();
							//Navigate to Shipment Reasons page...
							
							HomePage.click("Navigation");
							//Navigate to Inventory Management 
							HomePage.click("InventoryManagement");
							//Navigate to Inventory Adjustment Page
							
							HomePage.click("InventoryAdjustment");
							
							
							//Verify the Title of the Screen..
							InventoryAdjustmentPage.verifyTextValue("Title", map.get("Title"));
							System.out.println("Verify the Refresh Button");
							InventoryAdjustmentPage.verifyElementIsPresent("RefreshButton");
		

			
	
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
