/* @author lrathnak **/
package com.oracle.sim.testcases.Operations;

import java.io.IOException;
import java.util.Map;
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

public class AreaPermissionDelete {
	public static Logger logger = Logger.getLogger(AreaPermissionDelete.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pageFactory = new PageFactory();
	
	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage RoleMaintenancePage;
	SimBasePage AreaPage;
	SoftAssert softAssertion = new SoftAssert();
	public static  String AreaIdValue=null;
	
	@BeforeClass
	public void setUp(ITestContext context) throws Exception {
		logger.info("TestCase Name: " + logger.getName());
		// logger.info("Before Class");
		SIMWebdriverBase.loadInitialURL(context);
		
		// Login Steps
		LoginPage = pageFactory.initialize("LoginPage");
		AreaPage=pageFactory.initialize("AreaPage");
		HomePage = pageFactory.initialize("HomePage");
		RoleMaintenancePage=pageFactory.initialize("RoleMaintenancePage");
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username", LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password", LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");
		HomePage.verifyTextValue("Title", "Store Inventory Operations Cloud Service");
		HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
		HomePage.storeIdCheck();

	}
	
	
	@DataProvider(name="AreaPermissionDelete")
	public Object[][] getTestDataForItemBasketPermission() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("AreaTestData"),
				"AreaPermissionDelete");
		return testObjArray;

	}
	
	@Test(dataProvider="AreaPermissionDelete", priority=1)
	public void removePermisnDelete(Map<String,String> map) throws Exception {
		//Navigating to role maintenance page
		logger.info("Method Name: removePermisnDelete");
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.click("Navigation");
		HomePage.click("Security");
		HomePage.click("RoleMaintenance");

		//Verifying RoleMaintenance Page Title
		RoleMaintenancePage.explicitWaitForElementToBeClickable("Title");
		RoleMaintenancePage.verifyTextValue("Title",map.get("Title"));

		//Granting the user permission
		String userRole=propReader.getApplicationproperty("UserRole");
		RoleMaintenancePage.verifyUserRole(userRole, map.get("AccessPermission"), map.get("AssignedDataNo"));
		
		//Clicking on a permission
		RoleMaintenancePage.explicitWaitForElementToBeClickable("FilterPermission");
		RoleMaintenancePage.explicitWaitForElementToBeClickable("AssignedData");		
		RoleMaintenancePage.click("FilterPermission");
		RoleMaintenancePage.enterIntoTextBox("FilterPermission",map.get("DeleteAccess"));
		RoleMaintenancePage.click("GridRecord");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		
		RoleMaintenancePage.explicitWaitForElementToBeClickable("AssignedData");
		if(RoleMaintenancePage.getTitle("AssignedData").equals(map.get("AssignedDataYes"))) {
			RoleMaintenancePage.click("RevokeSelected");
			RoleMaintenancePage.click("SaveButton");
			RoleMaintenancePage.click("YesButton");
			//wait for DB commit to perform 
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
			RoleMaintenancePage.click("FilterPermission");
			RoleMaintenancePage.enterIntoTextBox("FilterPermission",map.get("DeleteAccess"));
			System.out.println(RoleMaintenancePage.getTitle("AssignedData"));
		}
		
		HomePage.RefreshWebPage();
		
		//Navigating to the Area List screen
		HomePage.click("Navigation");
		HomePage.click("Operations");
		HomePage.click("Area");
		
		//Title Verification of the list screen
		AreaPage.explicitWaitForElementToBeClickable("AreaListScreenTitle");
		AreaPage.verifyTextValue("AreaListScreenTitle",map.get("ListScreenTitle"));
		
		//Clicking on the create button
		AreaPage.click("ListCreateButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
		
		//Verifying the Area detail title
		AreaPage.explicitWaitForVisibility("AreaDetailScreeTitle");
		AreaPage.verifyTextValue("AreaDetailScreeTitle", map.get("DetailScreenTitle"));
		
		//To verify Confirm button is not available
		AreaPage.checkElementIsNotPresent("DetailDeleteButton");
		
		//To get the area ID to check post creation
		 AreaIdValue=AreaPage.getAttributeValue("DetailAreaIDInput", "aria-valuenow");
		 
		 //to check current date is equal to the create date
		 AreaPage.verifyDefaultDateWithCurrentDate("DetailCreateDateInput");
		 
		 //to enter description into the description box
		 AreaPage.enterIntoTextBox("DetailDescriptionInput", map.get("DescValue"));
		 
		 //To select first of option of the drop down always from the drop down
		 AreaPage.click("DetailItembasketDropDown");
		 AreaPage.click("DetailItemBasketFrstInput");
		 
		 //to select the store which is available
		 Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		 AreaPage.click("AssignFrstCheckBox");
		 AreaPage.click("DetailAssignSelectedButton");
		
		 //Click to save the button
		 AreaPage.click("DetailSaveButton");
		 AreaPage.verifyElementIsDisplayed("DetailConfirmationDialog");
		 AreaPage.click("ConfirmYesButton");
		 Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		
		}
	
	@Test(dataProvider="AreaPermissionDelete", priority=2)
	public void assignPermisnDelete(Map<String,String> map) throws IOException, NumberFormatException, InterruptedException {
		logger.info("Method Name: assignPermisnDelete");
		
		//To Navigate back to the role maintenance page
		AreaPage.click("MenuBackButton");
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
		

		//Granting the user permission
		RoleMaintenancePage.explicitWaitForElementToBeClickable("DetailTitle");
		RoleMaintenancePage.explicitWaitForElementToBeClickable("FirstTableRecord");
		RoleMaintenancePage.explicitWaitForElementToBeClickable("FilterPermission");
		RoleMaintenancePage.click("FilterPermission");
		RoleMaintenancePage.enterIntoTextBox("FilterPermission",map.get("DeleteAccess"));
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
			RoleMaintenancePage.enterIntoTextBox("FilterPermission",map.get("DeleteAccess"));
			RoleMaintenancePage.RefreshWebPage();
			System.out.println(RoleMaintenancePage.getTitle("AssignedData"));
			RoleMaintenancePage.RefreshWebPage();
		}
			
		//Navigating to the Area List screen
		HomePage.click("Navigation");
		HomePage.click("Operations");
		HomePage.click("Area");
		
		
		//Refreshing the browser in order to load the elements
		AreaPage.RefreshWebPage();
								
		
		//To select the status
		AreaPage.click("ListSearchButton");
		AreaPage.enterIntoTextBox("AreaIdInput", AreaIdValue);
		AreaPage.click("SearchButton");
		AreaPage.click("ListRefreshButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
			
		
		//To click on AreaID filter
		AreaPage.click("ListFrstRecrd");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
				
		//To verify the detail screen
		 AreaPage.explicitWaitForVisibility("AreaDetailScreeTitle");
		AreaPage.verifyTextValue("AreaDetailScreeTitle", map.get("DetailScreenTitle"));
		
		//To verify the Delete Button
		AreaPage.verifyElementIsPresent("DetailDeleteButton");
		logger.info("The Detail delete button is present");
		
		
		AreaPage.click("DetailDeleteButton");
		AreaPage.verifyElementIsDisplayed("DeleteConfirmationDialog");
		AreaPage.click("ConfirmYesButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		
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
		} finally {
			SIMWebdriverBase.close();
		}
	}
	
}




