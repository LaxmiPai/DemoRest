package com.oracle.sim.testcases.ItemBasket;
/**
	 * * @author dsorthiy
	 *
	 */
import java.util.Map;
import java.util.Random; 
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
		
		public class ConfirmItemBasket  {
		
		public static Logger logger = Logger.getLogger(ConfirmItemBasket.class.getName());
		protected static PropertyReader propReader = new PropertyReader();
		PageFactory pageFactory = new PageFactory();
		Random random = new Random();  
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
			RoleMaintenancePage.verifyUserRole(userRole, map.get("ConfirmItemBasket"), map.get("AssignedDataNo"));	
		}

			
		//DataProvider for Confirm Item Basket
		@DataProvider(name = "ConfirmItemBasketTestData")
		public Object[][] getTestDataForUI() throws Exception {
			Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("ItemBasketTestData"),"ConfirmItemBasket");
			return testObjArray;
		}
		//Verify the Confirm Button of Item Basket 
				@Test(dataProvider = "ConfirmItemBasketTestData", priority = 2)
				public void AddComponentsCompletedItemBasket(Map<String, String> map) throws Exception {
					
					//HomePage.click("Navigation");
					HomePage.click("InventoryManagement");
					HomePage.click("ItemBasketPage");
					
					logger.info("Add Components in Completed Item Basket Test Method Started!!!");
					System.out.println("Verify the Title of Item Basket List Screen");
					ItemBasketPage.explicitWaitForVisibility("ItemBasketListTitle");
					ItemBasketPage.verifyTextValue("ItemBasketListTitle", map.get("Title"));
					
					//Create the Item Basket 
					ItemBasketPage.click("ListScreenCreateButton");
					ItemBasketPage.selectDropDownValue("TypeDropDown", map.get("Type"));
					
					// Enter Description field and generate the Random Number 
					int Randomnumber1 = random.nextInt(12345);
					String  randnum1 =Integer.toString(Randomnumber1);
					ItemBasketPage.enterIntoTextBox("DescriptionTextBox",randnum1);
					
					// Enter Description field and generate the Random Number 
					int Randomnumber2 = random.nextInt(12345);
					String  randnum2 =Integer.toString(Randomnumber2);
					ItemBasketPage.enterIntoTextBox("AlternateIdTextBox",randnum2);
					ItemBasketPage.click("CreateButton");
					
					//Verify the UI of Item Basket Detail Screen
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
					ItemBasketPage.verifyElementIsEnabled("ConfirmButton");
					ItemBasketPage.verifyElementIsEnabled("DeleteButton");
					ItemBasketPage.verifyElementIsEnabled("InfoButton");
					
					//Get the Item Basket Id at Run time.
					String ItemBasketIdd = ItemBasketPage.getAttributeValue("ItemBasketId","aria-valuenow");
					System.out.println("Item Basket Id :"+ItemBasketIdd);
					
					
					// Add the Components in Item Basket
					ItemBasketPage.explicitWaitForElementToBeClickable("AddComponentsButton");
					ItemBasketPage.click("AddComponentsButton");
					//Verify the Detail Block is Enabled or not
					ItemBasketPage.verifyElementIsEnabled("ApplyButton");
					ItemBasketPage.verifyElementIsEnabled("CancelButton");
					
					//Select the ComponentType Drop-down Value
					ItemBasketPage.explicitWaitForElementToBeClickable("ComponentTypeDropDown");
					ItemBasketPage.selectDropDownValue("ComponentTypeDropDown", map.get("ComponentType"));
					String value = map.get("ComponentType");
					if(value.equals("Hierarchy"))
					{
						ItemBasketPage.explicitWaitForElementToBeClickable("DetailPanelDepartmentDropDown");
						//ItemBasketPage.click("DeptDropDown");
						Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
						ItemBasketPage.selectDropDownValue("DetailPanelDepartmentDropDown",map.get("Dept"));
						Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
						ItemBasketPage.click("ApplyButton");
					}
					else if(value.equals("Item"))
					{
						ItemBasketPage.explicitWaitForElementToBeClickable("ItemScanBox");
						ItemBasketPage.enterIntoTextBox("ItemScanBox",map.get("ItemId"));
						ItemBasketPage.explicitWaitForElementToBeClickable("ScanBoxArrow");
						ItemBasketPage.click("ScanBoxArrow");
						ItemBasketPage.enterIntoTextBox("PackSizeTextBox",map.get("PackSize"));
						ItemBasketPage.enterIntoTextBox("QuantityTextBox",map.get("Quantity"));
						ItemBasketPage.click("ApplyButton");
						
					}
					else if(value.equals("Supplier"))
					{
						ItemBasketPage.enterIntoTextBox("SupplierTextBox",map.get("Supplier"));
						ItemBasketPage.click("ApplyButton");
					}
					else if(value.equals("Style"))
					{
						ItemBasketPage.enterIntoTextBox("StyleTextBox", map.get("Style"));
						ItemBasketPage.click("ApplyButton");
						ItemBasketPage.click("ItemBasketDetailTitle");
						Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
					}
					else
					{
						System.out.println("Invalid Compoment Type..!");
					}
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
					ItemBasketPage.explicitWaitForElementToBeClickable("ConfirmButton");
					ItemBasketPage.click("ConfirmButton");
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
					//Verify the Confirmation Message
					
					ItemBasketPage.verifyTextValue("ConfirmationMsg",map.get("ConfirmationMsg"));
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
					//Click On No Button
					ItemBasketPage.explicitWaitForElementToBeClickable("NoButton");
					ItemBasketPage.click("NoButton");
					//Verify the Title of Item Basket Detail Screen
					ItemBasketPage.verifyTextValue("ItemBasketDetailTitle",map.get("ItemBasketDetailTitle"));
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
					ItemBasketPage.click("ConfirmButton");
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
					//Verify the Confirmation Message
					ItemBasketPage.verifyTextValue("ConfirmationMsg",map.get("ConfirmationMsg"));
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
					//Click on Yes Button 
					ItemBasketPage.explicitWaitForElementToBeClickable("YesButton");
					ItemBasketPage.click("YesButton");
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
					
					//Item Basket moved to Completed Status
					ItemBasketPage.click("SearchIcon");
					ItemBasketPage.enterIntoTextBox("ItemBasketIdTextBox",ItemBasketIdd );
					ItemBasketPage.selectDropDownValue("StatusDropDown",map.get("StatusCompleted"));
					ItemBasketPage.click("SearchButton");
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
					//Verify the Status of entered Item Basket
					ItemBasketPage.click("FilterStatus");
					ItemBasketPage.enterIntoTextBox("FilterStatus",map.get("StatusCompleted"));
					
					ItemBasketPage.verifyTextValue("GridHighLightRecord","Completed");
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
					logger.info("Confirmed Item basket Successfully!!!");
					//Click on Refresh Button
					ItemBasketPage.explicitWaitForElementToBeClickable("RefreshButton");
					ItemBasketPage.click("RefreshButton");
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
					
					
									
				}
				@Test(dataProvider = "ConfirmItemBasketTestData", priority = 3)
				public void VerfiyConfirmButtonCompletedItemBasket(Map<String, String> map) throws Exception {
					logger.info("Verify CofirmButton in Completed Item Basket Started!!");
					ItemBasketPage.click("SearchIcon");
					ItemBasketPage.clearElement("ItemBasketIdTextBox");
					ItemBasketPage.click("SearchCriteriaTitle");
					ItemBasketPage.selectDropDownValue("StatusDropDown",map.get("StatusCompleted"));
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
						ItemBasketPage.enterIntoTextBox("FilterStatus", map.get("StatusCompleted"));
						// Filter By User of Item Basket
						ItemBasketPage.explicitWaitForElementToBeClickable("FilterUser");
						ItemBasketPage.click("FilterUser");
						ItemBasketPage.enterIntoTextBox("FilterUser", map.get("User"));
						Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
						//Click on Inventory Adjustment Id 
						ItemBasketPage.click("ItemBasketIdLink");
						//Verify the UI of Item Basket Detail Screen
						
						ItemBasketPage.verifyElementIsEnabled("InfoButton");
						
						//Verify the Confirm Button is not Present in Completed Item Basket
						ItemBasketPage.checkElementIsNotPresent("ConfirmButton");
						//Click on Back Button 
						ItemBasketPage.explicitWaitForElementToBeClickable("BackButton");
						ItemBasketPage.click("BackButton");
							
						//Click on Refresh Button
						ItemBasketPage.explicitWaitForElementToBeClickable("RefreshButton");
						ItemBasketPage.click("RefreshButton");
						Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
						logger.info("Verified the Confirm Button is not present in Completed Item Basket!!!");
						
					}
				}
				
				
				@Test(dataProvider = "ConfirmItemBasketTestData", priority = 4)
				public void VerfiyConfirmButtonCanceledItemBasket(Map<String, String> map) throws Exception {
					
					ItemBasketPage.click("SearchIcon");
					ItemBasketPage.clearElement("ItemBasketIdTextBox");
					ItemBasketPage.click("SearchCriteriaTitle");
					ItemBasketPage.selectDropDownValue("StatusDropDown",map.get("StatusCanceled"));
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
						ItemBasketPage.enterIntoTextBox("FilterStatus", map.get("StatusCanceled"));
						// Filter By User of Item Basket
						ItemBasketPage.explicitWaitForElementToBeClickable("FilterUser");
						ItemBasketPage.click("FilterUser");
						ItemBasketPage.enterIntoTextBox("FilterUser", map.get("User"));
						Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
						//Click on Inventory Adjustment Id 
						ItemBasketPage.explicitWaitForElementToBeClickable("ItemBasketIdLink");
						ItemBasketPage.click("ItemBasketIdLink");
						Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
						//Verify the UI of Item Basket Detail Screen
						ItemBasketPage.verifyElementIsEnabled("InfoButton");
						
						//Verify the Confirm Button is not Present in Canceled Item Basket
						ItemBasketPage.checkElementIsNotPresent("ConfirmButton");
						
						//Click on Back Button 
						ItemBasketPage.explicitWaitForElementToBeClickable("BackButton");
						ItemBasketPage.click("BackButton");
							
						//Click on Refresh Button
						ItemBasketPage.explicitWaitForElementToBeClickable("RefreshButton");
						ItemBasketPage.click("RefreshButton");
						Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
						logger.info("Verified the Confirm Button is not present in Canceled Item Basket!!!");
						
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