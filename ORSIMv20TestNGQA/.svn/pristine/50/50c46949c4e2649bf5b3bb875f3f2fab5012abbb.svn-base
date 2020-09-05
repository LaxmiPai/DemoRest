package com.oracle.sim.testcases.InventoryAdjustmentReasons;
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

public class AssignPermissionInventoryAdjustmentReasons {

	public static Logger logger = Logger.getLogger(AssignPermissionInventoryAdjustmentReasons.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pageFactory = new PageFactory();
	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage InventoryAdjustmentReasonsPage;
	SimBasePage InventoryAdjustmentPage;
	@BeforeClass()
	public void setup(ITestContext context) throws Exception {
		logger.info("TestCase Name: " +logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
		
		LoginPage = pageFactory.initialize("LoginPage");
		HomePage = pageFactory.initialize("HomePage");
		InventoryAdjustmentReasonsPage= pageFactory.initialize("InventoryAdjustmentReasonsPage");
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username", LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password", LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");
		
		
		
		HomePage = pageFactory.initialize("HomePage");
		HomePage.verifyTextValue("Title", HomePage.getProperty("Title"));
		HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
		HomePage.storeIdCheck();

		
	}
	//DataProvider for Search Inventory Adjustment Reasons code 
	@DataProvider(name ="AssignReasonsTestData")
	public Object[][] getTestDataForAssignReasons() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("InventoryAdjustmentReasonsTestData")
				,"UserAssignment");
		return testObjArray;
	}
	
	@Test(dataProvider = "AssignReasonsTestData")
	public void PermissionAssignInvenotryAdjustmentReasons(Map<String, String> map) throws Exception {
		
		

		HomePage.click("Navigation");
		HomePage.click("SecurityPage");
		HomePage.click("RoleMaintenancePage");
		InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("RoleNameLink");
		InventoryAdjustmentReasonsPage.click("FilterByRoleName");
		InventoryAdjustmentReasonsPage.enterIntoTextBox("FilterByRoleName",map.get("RoleName"));
		InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("RoleNameLink");
		
		InventoryAdjustmentReasonsPage.click("RoleNameLink");
		InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("FilterByDataValue");
		InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("AssignedData");
		
		//Assign the Data Value 
		InventoryAdjustmentReasonsPage.click("FilterByDataValue");
		InventoryAdjustmentReasonsPage.enterIntoTextBox("FilterByDataValue", map.get("DataValue"));
		InventoryAdjustmentReasonsPage.verifyTextValue("GridHighLight",map.get("DataValue"));
		
		InventoryAdjustmentReasonsPage.click("GridHighLight");
		
		//Remove the permission for the user
		InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("AssignedData");
				if(InventoryAdjustmentReasonsPage.getTitle("AssignedData").equals(map.get("AssignedDataYes"))) {
					InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("RevokeSelectedButton");
					InventoryAdjustmentReasonsPage.click("RevokeSelectedButton");
					InventoryAdjustmentReasonsPage.click("SaveIcon");
					InventoryAdjustmentReasonsPage.click("YesButton");
					//wait for DB commit to perform 
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
					InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("FilterByDataValue");
					InventoryAdjustmentReasonsPage.click("FilterByDataValue");
					InventoryAdjustmentReasonsPage.enterIntoTextBox("FilterByDataValue", map.get("DataValue"));
					InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("AssignedData");
					System.out.println("Revoke Permission:"+InventoryAdjustmentReasonsPage.getTitle("AssignedData"));
				}
				
				InventoryAdjustmentReasonsPage.click("BackLink");
				
				//Navigate to Inventory Management 
				HomePage.click("InventoryManagement");
				//Navigate to Inventory Adjustment Page
				HomePage.click("InventoryAdjustment");
				InventoryAdjustmentReasonsPage.RefreshWebPage();
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				//Navigate to Inventory Adjustment Page
				
				InventoryAdjustmentPage= pageFactory.initialize("InventoryAdjustmentPage");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
				InventoryAdjustmentPage.verifyTextValue("Title", map.get("Title"));
				InventoryAdjustmentPage.verifyElementIsPresent("RefreshButton");
				InventoryAdjustmentPage.explicitWaitForElementToBeClickable("CreateButton");
				InventoryAdjustmentPage.click("CreateButton");
				
				InventoryAdjustmentPage.VerifyDropDownOption("ReasonDropdown",map.get("DataValue"));
				
				//InventoryAdjustmentPage.verifyTextValue("NofoundMsg",map.get("NofoundMsg"));
				HomePage.click("Navigation");
				HomePage.click("SecurityPage");
				HomePage.click("RoleMaintenancePage");
				InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("RoleNameLink");
				InventoryAdjustmentReasonsPage.click("FilterByRoleName");
				InventoryAdjustmentReasonsPage.enterIntoTextBox("FilterByRoleName",map.get("RoleName"));
				InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("RoleNameLink");
				
				InventoryAdjustmentReasonsPage.click("RoleNameLink");
				InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("FilterByDataValue");
				InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("AssignedData");
				
				//Filter by Data Value 
				InventoryAdjustmentReasonsPage.click("FilterByDataValue");
				InventoryAdjustmentReasonsPage.enterIntoTextBox("FilterByDataValue", map.get("DataValue"));
				InventoryAdjustmentReasonsPage.verifyTextValue("GridHighLight",map.get("DataValue"));
				
				InventoryAdjustmentReasonsPage.click("GridHighLight");
				
				
				
				//Grant the permission for the user 
				InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("AssignedData");
				if(InventoryAdjustmentReasonsPage.getTitle("AssignedData").equals(map.get("AssignedDataNo"))) {
					InventoryAdjustmentReasonsPage.click("GridHighLight");
					InventoryAdjustmentReasonsPage.click("AssigneSelectedButton");
					InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("SaveIcon");
					InventoryAdjustmentReasonsPage.click("SaveIcon");
					InventoryAdjustmentReasonsPage.click("YesButton");
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
					InventoryAdjustmentReasonsPage.click("FilterByDataValue");
					InventoryAdjustmentReasonsPage.enterIntoTextBox("FilterByDataValue", map.get("DataValue"));
					InventoryAdjustmentReasonsPage.explicitWaitForElementToBeClickable("AssignedData");
					System.out.println("Assign Permission:"+InventoryAdjustmentReasonsPage.getTitle("AssignedData"));
				}
				InventoryAdjustmentReasonsPage.click("BackLink");
				
				//Navigate to Inventory Management 
				HomePage.click("InventoryManagement");
				//Navigate to Inventory Adjustment Page
				HomePage.click("InventoryAdjustment");
			
				InventoryAdjustmentReasonsPage.RefreshWebPage();
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				//Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
				
				InventoryAdjustmentPage= pageFactory.initialize("InventoryAdjustmentPage");
				logger.info("Create Inventory Adjustment Test case started..!");
				//verify the Title of Inventory Adjustment
				InventoryAdjustmentPage.verifyTextValue("Title", map.get("Title"));
				//Verify the Refresh Button
				System.out.println("Verify the Refresh Button");
				InventoryAdjustmentPage.verifyElementIsPresent("RefreshButton");
				InventoryAdjustmentPage.explicitWaitForElementToBeClickable("CreateButton");
				InventoryAdjustmentPage.click("CreateButton");
				
				InventoryAdjustmentPage.searchDropDownValue("ReasonDropdown",map.get("DataValue"));
				logger.info(" Inventory Adjustment  Reasons code is Selected..!");
		
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
