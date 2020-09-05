package com.oracle.sim.testcases.ItemBasket;
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
	
public class DeleteItemBasket {
	
	public static Logger logger = Logger.getLogger(DeleteItemBasket.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pageFactory = new PageFactory();
	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage ItemBasketPage;
	private SimBasePage RoleMaintenancePage;
	@BeforeClass()
	public void setup(ITestContext context) throws Exception {
		
		logger.info("TestCase Name: " +logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
		LoginPage = pageFactory.initialize("LoginPage");
		HomePage = pageFactory.initialize("HomePage");
		ItemBasketPage = pageFactory.initialize("ItemBasketPage");
		RoleMaintenancePage = pageFactory.initialize("RoleMaintenancePage");
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username", LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password", LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");
		HomePage.verifyTextValue("Title", HomePage.getProperty("Title"));
		HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
		HomePage.storeIdCheck();

		
	}
	@DataProvider(name = "RoleMaintenanceTestData")
	public Object[][] getRoleMaintenanceTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("SecurityTestData"),
				"RoleMaintenance");
		return testObjArray;
	}

	@Test(dataProvider="RoleMaintenanceTestData", priority=1)
	public void verifyRole(Map<String,String> map) throws Exception {
		//Navigating to role maintenance page
		logger.info("Method Name: verifyRole");
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.storeIdCheck();
		HomePage.click("Navigation");
		HomePage.click("Security");
		HomePage.click("RoleMaintenance");

		//Verifying RoleMaintenance Page Title
		RoleMaintenancePage.explicitWaitForElementToBeClickable("Title");
		RoleMaintenancePage.verifyTextValue("Title",map.get("Title"));

		//Granting the user permission
		String userRole=propReader.getApplicationproperty("UserRole");
		RoleMaintenancePage.verifyUserRole(userRole, map.get("DeleteItemBasket"), map.get("AssignedDataNo"));	
	}


	//DataProvider for Delete Item Basket 
	@DataProvider(name = "DeleteItemBasketTestData")
	public Object[][] getTestDataForUI() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("ItemBasketTestData"),"DeleteItemBasket");
		return testObjArray;
	}
	//Verify the Delete Button in In Progress Item Basket 
			@Test(dataProvider = "DeleteItemBasketTestData", priority = 2)
			public void DeleteInProgressItemBasket(Map<String, String> map) throws Exception {
				
				//HomePage.click("Navigation");
				HomePage.click("InventoryManagement");
				HomePage.click("ItemBasketPage");
				logger.info("Method : Delete In Progress ItemBasket Started!!");
				System.out.println("Verify the Title of Item Basket List Screen");
				ItemBasketPage.explicitWaitForVisibility("ItemBasketListTitle");
				ItemBasketPage.verifyTextValue("ItemBasketListTitle", map.get("Title"));
				
				ItemBasketPage.click("SearchIcon");
				ItemBasketPage.clearElement("ItemBasketIdTextBox");
				ItemBasketPage.click("SearchCriteriaTitle");
				ItemBasketPage.selectDropDownValue("StatusDropDown","In Progress");
				ItemBasketPage.click("SearchButton");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
				
				int rowCount=ItemBasketPage.getTableRowCount("TableRows","UserColumnName","FilterItemBasketId");
				if(rowCount==0) {
					System.out.println("No table records found");
				}
				else 
				{
					System.out.println("Table Records Count: "+rowCount);
					// Filter By Status of Item Basket 
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
					ItemBasketPage.explicitWaitForElementToBeClickable("FilterStatus");
					ItemBasketPage.click("FilterStatus");
					ItemBasketPage.enterIntoTextBox("FilterStatus", "In Progress");
					// Filter By User of Item Basket
					ItemBasketPage.explicitWaitForElementToBeClickable("FilterUser");
					ItemBasketPage.click("FilterUser");
					ItemBasketPage.enterIntoTextBox("FilterUser", "sim_qa3");
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
					//Click on Inventory Adjustment Id 
					ItemBasketPage.click("ItemBasketIdLink");
					ItemBasketPage.click("OkButton");
					
					//Verify the UI of Item Basket Detail Screen
					ItemBasketPage.verifyElementIsEnabled("InfoButton");
					//Get the Item Basket Id at Run time.
					String ItemBasketIdd = ItemBasketPage.getAttributeValue("ItemBasketId","aria-valuenow");
					System.out.println("Item Basket Id :"+ItemBasketIdd);
					
					
					
					ItemBasketPage.verifyElementIsPresent("DeleteButton");
					ItemBasketPage.explicitWaitForElementToBeClickable("DeleteButton");
					ItemBasketPage.click("DeleteButton");
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
					//Verify the Confirmation Message
					
					ItemBasketPage.verifyTextValue("DeleteConfirmationMsg",map.get("DeleteConfirmationMsg"));
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
					//Click On No Button
					ItemBasketPage.explicitWaitForElementToBeClickable("NoButton");
					ItemBasketPage.click("NoButton");
					//Verify the Title of Item Basket Detail Screen
					ItemBasketPage.verifyTextValue("ItemBasketDetailTitle",map.get("ItemBasketDetailTitle"));
					
					ItemBasketPage.click("DeleteButton");
					//Verify the Delete Confirmation Message
					
					ItemBasketPage.verifyTextValue("DeleteConfirmationMsg",map.get("DeleteConfirmationMsg"));
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
					ItemBasketPage.explicitWaitForElementToBeClickable("YesButton");
					ItemBasketPage.click("YesButton");
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
					//Item Basket moved to Canceled Status
					ItemBasketPage.click("SearchIcon");
					ItemBasketPage.enterIntoTextBox("ItemBasketIdTextBox",ItemBasketIdd );
					ItemBasketPage.selectDropDownValue("StatusDropDown","Canceled");
					ItemBasketPage.click("SearchButton");
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
					//Verify the Status of entered Item Basket
					ItemBasketPage.click("FilterStatus");
					ItemBasketPage.enterIntoTextBox("FilterStatus","Canceled");
					
					ItemBasketPage.verifyTextValue("GridHighLightRecord","Canceled");
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
					logger.info("Deleted Item basket Successfully!!!");
					//Click on Refresh Button
					ItemBasketPage.explicitWaitForElementToBeClickable("RefreshButton");
					ItemBasketPage.click("RefreshButton");
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
					
					logger.info("Deleted the In Progress Item basket!!!");
					
					
				}
			}
			
			
			@Test(dataProvider = "DeleteItemBasketTestData", priority = 3)
			public void DeleteCompletedItemBasket(Map<String, String> map) throws Exception {
				
				
				logger.info("Method DeleteCompletedItemBasket Started!!");
				ItemBasketPage.click("SearchIcon");
				ItemBasketPage.clearElement("ItemBasketIdTextBox");
				ItemBasketPage.click("SearchCriteriaTitle");
				ItemBasketPage.selectDropDownValue("StatusDropDown","Completed");
				ItemBasketPage.click("SearchButton");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
				
				int rowCount=ItemBasketPage.getTableRowCount("TableRows","UserColumnName","FilterItemBasketId");
				if(rowCount==0) {
					System.out.println("No table records found");
				}
				else 
				{
					System.out.println("Table Records Count: "+rowCount);
					// Filter By Status of Item Basket 
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
					ItemBasketPage.explicitWaitForElementToBeClickable("FilterStatus");
					ItemBasketPage.click("FilterStatus");
					ItemBasketPage.enterIntoTextBox("FilterStatus", "Completed");
					// Filter By User of Item Basket
					ItemBasketPage.explicitWaitForElementToBeClickable("FilterUser");
					ItemBasketPage.click("FilterUser");
					ItemBasketPage.enterIntoTextBox("FilterUser", "sim_qa3");
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
					//Click on Inventory Adjustment Id 
					ItemBasketPage.click("ItemBasketIdLink");
					//ItemBasketPage.click("OkButton");
					
					//Verify the UI of Item Basket Detail Screen
					ItemBasketPage.verifyElementIsEnabled("InfoButton");
					//Get the Item Basket Id at Run time.
					String ItemBasketIdd = ItemBasketPage.getAttributeValue("ItemBasketId","aria-valuenow");
					System.out.println("Item Basket Id :"+ItemBasketIdd);
					
					
					//Verify the Confirm Button is not Present in Canceled Item Basket
					ItemBasketPage.verifyElementIsPresent("DeleteButton");
					ItemBasketPage.click("DeleteButton");
					//Verify the Delete Confirmation Message
					
					ItemBasketPage.verifyTextValue("DeleteConfirmationMsg",map.get("DeleteConfirmationMsg"));
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
					ItemBasketPage.explicitWaitForElementToBeClickable("YesButton");
					ItemBasketPage.click("YesButton");
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
					//Item Basket moved to Canceled Status
					ItemBasketPage.click("SearchIcon");
					ItemBasketPage.enterIntoTextBox("ItemBasketIdTextBox",ItemBasketIdd );
					ItemBasketPage.selectDropDownValue("StatusDropDown","Canceled");
					ItemBasketPage.click("SearchButton");
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
					//Verify the Status of entered Item Basket
					ItemBasketPage.click("FilterStatus");
					ItemBasketPage.enterIntoTextBox("FilterStatus","Canceled");
					
					ItemBasketPage.verifyTextValue("GridHighLightRecord","Canceled");
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
					logger.info("Confirmed Item basket Successfully!!!");
					//Click on Refresh Button
					ItemBasketPage.explicitWaitForElementToBeClickable("RefreshButton");
					ItemBasketPage.click("RefreshButton");
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
					

					logger.info("Verified the Delete Button is not present in Completed Item Basket!!!");
					
				}
			}	
				@Test(dataProvider = "DeleteItemBasketTestData", priority = 4)
				public void VerifyDeleteinCanceledItemBasket(Map<String, String> map) throws Exception {
					
					
					logger.info("Method VerifyDeleteinCanceledItemBasket Started!!");
					ItemBasketPage.click("SearchIcon");
					ItemBasketPage.clearElement("ItemBasketIdTextBox");
					ItemBasketPage.click("SearchCriteriaTitle");
					ItemBasketPage.selectDropDownValue("StatusDropDown","Canceled");
					ItemBasketPage.click("SearchButton");
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
					
					int rowCount=ItemBasketPage.getTableRowCount("TableRows","UserColumnName","FilterItemBasketId");
					if(rowCount==0) {
						System.out.println("No table records found");
					}
					else 
					{
						System.out.println("Table Records Count: "+rowCount);
						// Filter By Status of Item Basket 
						Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
						ItemBasketPage.explicitWaitForElementToBeClickable("FilterStatus");
						ItemBasketPage.click("FilterStatus");
						ItemBasketPage.enterIntoTextBox("FilterStatus", "Canceled");
						// Filter By User of Item Basket
						ItemBasketPage.explicitWaitForElementToBeClickable("FilterUser");
						ItemBasketPage.click("FilterUser");
						ItemBasketPage.enterIntoTextBox("FilterUser", "sim_qa3");
						Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
						//Click on Inventory Adjustment Id 
						ItemBasketPage.click("ItemBasketIdLink");
						//ItemBasketPage.click("OkButton");
						
						//Verify the UI of Item Basket Detail Screen
						ItemBasketPage.verifyElementIsEnabled("InfoButton");
						//Get the Item Basket Id at Run time.
						String ItemBasketIdd = ItemBasketPage.getAttributeValue("ItemBasketId","aria-valuenow");
						System.out.println("Item Basket Id :"+ItemBasketIdd);
						
						
						//Verify the Delete Button is not Present in Canceled Item Basket
						ItemBasketPage.verifyElementIsNotPresent("DeleteButton");
												
						//Click on Back Button 
						ItemBasketPage.explicitWaitForElementToBeClickable("BackButton");
						ItemBasketPage.click("BackButton");
						//Click on Refresh Button
						ItemBasketPage.explicitWaitForElementToBeClickable("RefreshButton");
						ItemBasketPage.click("RefreshButton");
						Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
			
						logger.info("Verified the Delete Button is not present in Canceled Item Basket!!!");
						
					}

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

