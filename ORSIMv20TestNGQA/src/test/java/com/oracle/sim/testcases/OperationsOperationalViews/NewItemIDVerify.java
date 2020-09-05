package com.oracle.sim.testcases.OperationsOperationalViews;

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

public class NewItemIDVerify {
	public static Logger logger = Logger.getLogger( NewItemIDVerify.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pagefactory = new PageFactory();

	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage RoleMaintenancePage;
	SimBasePage OperationalPage;

			@BeforeClass
			public void setup(ITestContext context) throws Exception {
				logger.info("TestCase Name: " + logger.getName());
				SIMWebdriverBase.loadInitialURL(context);
				LoginPage = pagefactory.initialize("LoginPage");
				HomePage = pagefactory.initialize("HomePage");
				RoleMaintenancePage = pagefactory.initialize("RoleMaintenancePage");
				OperationalPage = pagefactory.initialize("OperationalVwNewItemPage");
				LoginPage.explicitWaitForVisibility("Username");
				LoginPage.enterIntoTextBox("Username", LoginPage.getProperty("Username"));
				LoginPage.enterIntoTextBox("Password", LoginPage.getProperty("Password"));
				LoginPage.click("SignIn");
				HomePage.verifyTextValue("Title", HomePage.getProperty("Title"));
				HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
				HomePage.storeIdCheck();
				
				//DB method
				HomePage.getConnection();
			}
			
			@DataProvider(name="NewItemID")
			public Object[][] getTestDataForItemBasketPermission() throws Exception {
				Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("NewItemsTestData"),
						"NewItemID");
				return testObjArray;

			}
			
			@Test(dataProvider="NewItemID",priority=3)
			public void verifyRole(Map<String,String> map) throws Exception {
				//Navigating to role maintenance page
				logger.info("Method Name: verifyRole");
				HomePage.click("Navigation");
				HomePage.click("Security");
				HomePage.click("RoleMaintenance");

				//Verifying RoleMaintenance Page Title
				RoleMaintenancePage.explicitWaitForVisibility("Title");
				RoleMaintenancePage.verifyTextValue("Title",map.get("Title"));

				//grant all permissions to the user
				String userRole=propReader.getApplicationproperty("UserRole");
				RoleMaintenancePage.grantAllPermissions(userRole);
			}
		
			@Test(dataProvider="NewItemID",priority=4)
			public void NwItemIDValidation(Map<String,String> map) throws Exception {
				logger.info("MethodName: NwItemIDValidation");
				
				//Navigation steps
				HomePage.click("Operations");
				HomePage.click("OperationalViews");
				HomePage.click("NewItem");
				
				//Title Verification
				OperationalPage.explicitWaitForElementToBeClickable("NewItemTitle");
				OperationalPage.verifyTextValue("NewItemTitle",map.get("NewItemTitle"));
				
				
				//Sometimes the records for the page are not loaded
				OperationalPage.RefreshWebPage();
				
			
				//To verify whether there are few rows available.
			int rowCount=OperationalPage.getTableRowCountWitoutRefresh("TableRows", "LastColumnName", "ItemFilter");
			if(rowCount==0) {
				//To chose another date where the records are present.DB method to update column
				OperationalPage.updateDateQuery(map.get("StoreId"),map.get("ItemId"));
			}
			
			
			//Clicking on the first record or Item available
			OperationalPage.click("FrstItemID");
			
			//To verify the title of the Item detail screen
			String obtainedTitle=OperationalPage.getText("ItemDetailTitle");
			obtainedTitle.contains(map.get("ItemDetailTitle"));
			
			//To scroll to end to verify the tabs
			OperationalPage.scrollToTableEnd();
			
			//Verify the detail screen
			OperationalPage.verifyElementIsPresent("DetailInventoryTab");
			OperationalPage.verifyElementIsPresent("DetailItemAttributeTab");
			OperationalPage.verifyElementIsPresent("DetailRelatedItemsTab");
			OperationalPage.verifyElementIsPresent("DetailUDAsTab");
			OperationalPage.verifyElementIsPresent("DetailPackItemsTab");
			OperationalPage.verifyElementIsPresent("DetailStockLocatorTab");
			OperationalPage.verifyElementIsPresent("DetailSuppliersTab");
			OperationalPage.verifyElementIsPresent("DetailPricingInfoTab");
			
			//Click on the back link
			OperationalPage.click("DetailBackButton");
			
			//Title Verification
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
			OperationalPage.verifyTextValue("NewItemTitle",map.get("NewItemTitle"));
			
			//To refresh the web page
			OperationalPage.RefreshWebPage();
			
	}
			
			@Test(dataProvider="NewItemID", priority=1)
			public void RevokeThePermission(Map<String,String> map) throws Exception {
				//Navigating to role maintenance page
				logger.info("Method Name: RevokeThePermission");
				HomePage.explicitWaitForElementToBeClickable("Navigation");
				HomePage.click("Navigation");
				HomePage.click("Security");
				HomePage.click("RoleMaintenance");
				
				

				//Verifying RoleMaintenance Page Title
				RoleMaintenancePage.explicitWaitForElementToBeClickable("Title");
				RoleMaintenancePage.verifyTextValue("Title",map.get("Title"));
				String userRole=propReader.getApplicationproperty("UserRole");

				
				//Clicking on a user Role Name
				RoleMaintenancePage.explicitWaitForVisibility("RoleNameColumnRecords");
				RoleMaintenancePage.explicitWaitForElementToBeClickable("FilterRoleName");
				RoleMaintenancePage.explicitWaitForElementToBeClickable("RoleNameColumnRecords");
				RoleMaintenancePage.click("FilterRoleName");
				RoleMaintenancePage.implicitWait();
				RoleMaintenancePage.enterIntoTextBox("FilterRoleName",userRole);
				RoleMaintenancePage.click("GridRecord");
				
				
				//Verifying RoleDetail Page Title
				RoleMaintenancePage.explicitWaitForVisibility("DetailTitle");
				RoleMaintenancePage.verifyTextValue("DetailTitle",map.get("DetailTitle"));
				
				//Clicking on a permission
				RoleMaintenancePage.explicitWaitForElementToBeClickable("FilterPermission");
				RoleMaintenancePage.explicitWaitForElementToBeClickable("AssignedData");		
				RoleMaintenancePage.click("FilterPermission");
				RoleMaintenancePage.enterIntoTextBox("FilterPermission",map.get("AccessPermission"));
				RoleMaintenancePage.click("GridRecord");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				
				//Remove the permission for the user
				RoleMaintenancePage.explicitWaitForElementToBeClickable("AssignedData");
				if(RoleMaintenancePage.getTitle("AssignedData").equals(map.get("AssignedDataYes"))) {
					RoleMaintenancePage.click("RevokeSelected");
					RoleMaintenancePage.click("SaveButton");
					RoleMaintenancePage.click("YesButton");
					
					//wait for DB commit to perform 
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
					RoleMaintenancePage.click("FilterPermission");
					RoleMaintenancePage.enterIntoTextBox("FilterPermission",map.get("AccessPermission"));
					System.out.println(RoleMaintenancePage.getTitle("AssignedData"));
					OperationalPage.RefreshWebPage();
					
					HomePage.click("Navigation");
					NwItemIDValidation(map);
					
					OperationalPage.RefreshWebPage();
					
					
						}
			}
			
			@Test(dataProvider="NewItemID", priority=2)
			public void GrantRoleWithPermsn(Map<String,String> map) throws Exception {
				
				//Navigating to role maintenance page
				logger.info("Method Name: verifyRole");
				HomePage.explicitWaitForElementToBeClickable("Navigation");
				HomePage.click("Navigation");
				HomePage.click("Security");
				HomePage.click("RoleMaintenance");

				//Verifying RoleMaintenance Page Title
				RoleMaintenancePage.explicitWaitForElementToBeClickable("Title");
				RoleMaintenancePage.verifyTextValue("Title",map.get("Title"));
				String userRole=propReader.getApplicationproperty("UserRole");

				
				//Clicking on a user Role Name
				RoleMaintenancePage.explicitWaitForVisibility("RoleNameColumnRecords");
				RoleMaintenancePage.explicitWaitForElementToBeClickable("FilterRoleName");
				RoleMaintenancePage.explicitWaitForElementToBeClickable("RoleNameColumnRecords");
				RoleMaintenancePage.click("FilterRoleName");
				RoleMaintenancePage.implicitWait();
				RoleMaintenancePage.enterIntoTextBox("FilterRoleName",userRole);
				RoleMaintenancePage.click("GridRecord");
				
				//Verifying RoleDetail Page Title
				RoleMaintenancePage.explicitWaitForVisibility("DetailTitle");
				RoleMaintenancePage.verifyTextValue("DetailTitle",map.get("DetailTitle"));
				
				//Clicking on a permission
				RoleMaintenancePage.explicitWaitForElementToBeClickable("FilterPermission");
				RoleMaintenancePage.explicitWaitForElementToBeClickable("AssignedData");		
				RoleMaintenancePage.click("FilterPermission");
				RoleMaintenancePage.enterIntoTextBox("FilterPermission",map.get("AccessPermission"));
				RoleMaintenancePage.click("GridRecord");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				
				//Grant the permission for the user 
				RoleMaintenancePage.explicitWaitForElementToBeClickable("AssignedData");
				if(RoleMaintenancePage.getTitle("AssignedData").equals(map.get("AssignedDataNo"))) {
					RoleMaintenancePage.click("AssignSelected");
					RoleMaintenancePage.click("SaveButton");
					RoleMaintenancePage.click("YesButton");
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
					RoleMaintenancePage.click("FilterPermission");
					RoleMaintenancePage.enterIntoTextBox("FilterPermission",map.get("AccessPermission"));
					RoleMaintenancePage.RefreshWebPage();
					System.out.println(RoleMaintenancePage.getTitle("AssignedData"));
					RoleMaintenancePage.RefreshWebPage();
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
				} finally {
					SIMWebdriverBase.close();
				}
			}
			}

