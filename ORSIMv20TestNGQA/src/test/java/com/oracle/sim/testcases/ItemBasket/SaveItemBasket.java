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

public class SaveItemBasket {
	
	public static Logger logger = Logger.getLogger(SaveItemBasket.class.getName());
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
		RoleMaintenancePage.verifyUserRole(userRole, map.get("AccessItemBasket"), map.get("AssignedDataNo"));	
	}

	//DataProvider for add Item Basket  
	@DataProvider(name = "SaveItemBasketTestData")
	public Object[][] getTestDataForUI() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("ItemBasketTestData"),"SaveItemBasket");
		return testObjArray;
	}
	//Verify the Save Button of Item Basket 
			@Test(dataProvider = "SaveItemBasketTestData", priority = 2)
			public void AddComponentsItemBasket(Map<String, String> map) throws Exception {
				
				//HomePage.click("Navigation");
				HomePage.click("InventoryManagement");
				HomePage.click("ItemBasketPage");
				logger.info("Method Name:AddComponentsItemBasket Started!! ");
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
				
				// Enter Alternate Id field and generate the Random Number 
				int Randomnumber2 = random.nextInt(12345);
				String  randnum2 =Integer.toString(Randomnumber2);
				ItemBasketPage.enterIntoTextBox("AlternateIdTextBox",randnum2);
				ItemBasketPage.click("CreateButton");
				
				//Verify the UI of Item Basket Detail Screen
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
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
					ItemBasketPage.explicitWaitForElementToBeClickable("DetailPanelDepartmentDropDown");
					//ItemBasketPage.click("DeptDropDown");
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
					ItemBasketPage.selectDropDownValue("DetailPanelDepartmentDropDown",map.get("Dept"));
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
					ItemBasketPage.click("ApplyButton");
				}
				else if(value.equals("Item"))
				{
					ItemBasketPage.enterIntoTextBox("ScanItemBox",map.get("ItemId"));
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
				}
				else
				{
					System.out.println("Invalid Compoment Type..!");
				}
				
				//click on Save Button
				ItemBasketPage.explicitWaitForElementToBeClickable("SaveButton");
				ItemBasketPage.click("SaveButton");
				
				ItemBasketPage.explicitWaitForElementToBeClickable("YesButton");
				ItemBasketPage.click("YesButton");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				
				ItemBasketPage.verifyTextValue("ItemBasketListTitle", map.get("Title"));
				// Search the In-Progress Item Basket
				ItemBasketPage.click("SearchIcon");
				ItemBasketPage.enterIntoTextBox("ItemBasketIdTextBox",ItemBasketIdd );
				ItemBasketPage.selectDropDownValue("StatusDropDown",map.get("StatusInProgress"));
				ItemBasketPage.click("SearchButton");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
				//Verify the Status of entered Item Basket
				ItemBasketPage.click("FilterStatus");
				ItemBasketPage.enterIntoTextBox("FilterStatus",map.get("StatusInProgress"));
				
				ItemBasketPage.verifyTextValue("GridHighLightRecord","In");
				
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
				ItemBasketPage.click("GridHighLightRecord");
				ItemBasketPage.click("DeleteButton");
				
				//Verify the Delete Confirmation Message
				//Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
				ItemBasketPage.explicitWaitForElementToBeClickable("YesButton");
				ItemBasketPage.click("YesButton");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				
				// Search Item Basket --> Moved to Canceled Status
				ItemBasketPage.click("SearchIcon");
				ItemBasketPage.enterIntoTextBox("ItemBasketIdTextBox",ItemBasketIdd );
				ItemBasketPage.selectDropDownValue("StatusDropDown",map.get("StatusCanceled"));
				ItemBasketPage.click("SearchButton");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
				
				//Verify the Status of entered Item Basket
				ItemBasketPage.click("FilterStatus");
				ItemBasketPage.enterIntoTextBox("FilterStatus",map.get("StatusCanceled"));
				
				ItemBasketPage.verifyTextValue("GridHighLightRecord",map.get("StatusCanceled"));
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
				logger.info("Deleted Item basket Successfully!!!");
				
				//Click on Refresh Button
				ItemBasketPage.explicitWaitForElementToBeClickable("RefreshButton");
				ItemBasketPage.click("RefreshButton");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				
				logger.info("Added Components in Item basket Successfully!!!");
				
				
			
			}
			// Without adding any component in Item Basket ->Item Basket successfully Created.
			@Test(dataProvider = "SaveItemBasketTestData", priority = 3)
			public void noComponentsItemBasket(Map<String, String> map) throws Exception {
				
				logger.info("noComponentsItemBasket Method Started!!");
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
				
				// Enter Alternate Id field and generate the Random Number 
				int Randomnumber2 = random.nextInt(12345);
				String  randnum2 =Integer.toString(Randomnumber2);
				ItemBasketPage.enterIntoTextBox("AlternateIdTextBox",randnum2);
				ItemBasketPage.click("CreateButton");
				
				//Verify the UI of Item Basket Detail Screen
				ItemBasketPage.verifyElementIsEnabled("ConfirmButton");
				ItemBasketPage.verifyElementIsEnabled("DeleteButton");
				ItemBasketPage.verifyElementIsEnabled("InfoButton");
				
				//Get the Item Basket Id at Run time.
				String ItemBasketIdd = ItemBasketPage.getAttributeValue("ItemBasketId","aria-valuenow");
				System.out.println("Item Basket Id :"+ItemBasketIdd);
			
				//Verify the SaveButton is Disable
				ItemBasketPage.verifyElementIsDisabled("SaveButton");
				ItemBasketPage.click("BackButton");
				
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				// Item Basket List Screen
				ItemBasketPage.verifyTextValue("ItemBasketListTitle", map.get("Title"));
				//Verify the Status of entered Item Basket
				
				// Search the In-Progress Item Basket
				ItemBasketPage.click("SearchIcon");
				ItemBasketPage.enterIntoTextBox("ItemBasketIdTextBox",ItemBasketIdd );
				ItemBasketPage.selectDropDownValue("StatusDropDown","In Progress");
				ItemBasketPage.click("SearchButton");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
				//Verify the Status of entered Item Basket
				ItemBasketPage.click("FilterStatus");
				ItemBasketPage.enterIntoTextBox("FilterStatus","In Progress");
				
				ItemBasketPage.verifyTextValue("GridHighLightRecord","In");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
				ItemBasketPage.click("GridHighLightRecord");
				ItemBasketPage.click("DeleteButton");
				
				//Verify the Delete Confirmation Message
				//Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
				ItemBasketPage.explicitWaitForElementToBeClickable("YesButton");
				ItemBasketPage.click("YesButton");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				
				// Search Item Basket --> Moved to Canceled Status
				ItemBasketPage.click("SearchIcon");
				ItemBasketPage.enterIntoTextBox("ItemBasketIdTextBox",ItemBasketIdd );
				ItemBasketPage.selectDropDownValue("StatusDropDown",map.get("StatusCanceled"));
				ItemBasketPage.click("SearchButton");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
				
				//Verify the Status of entered Item Basket
				ItemBasketPage.click("FilterStatus");
				ItemBasketPage.enterIntoTextBox("FilterStatus",map.get("StatusCanceled"));
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
				ItemBasketPage.verifyTextValue("GridHighLightRecord",map.get("StatusCanceled"));
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
				logger.info("Deleted Item basket Successfully!!!");
				
				//Click on Refresh Button
				ItemBasketPage.explicitWaitForElementToBeClickable("RefreshButton");
				ItemBasketPage.click("RefreshButton");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				
				logger.info("New Item Basket without Components Created Successfully!!!");
				
				
				
			
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
