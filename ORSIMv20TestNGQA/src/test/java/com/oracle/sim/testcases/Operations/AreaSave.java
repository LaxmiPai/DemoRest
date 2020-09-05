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

public class AreaSave {
	public static Logger logger = Logger.getLogger(AreaUIAndNavigation.class.getName());
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
	
	
	@DataProvider(name="AreaConfirm")
	public Object[][] getTestDataForItemBasketPermission() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("AreaTestData"),
				"AreaSave");
		return testObjArray;

	}
	
	@Test(dataProvider="AreaConfirm",priority=1)
	public void verifyRole(Map<String,String> map) throws Exception {
		logger.info("TestCase Name : "+ logger.getName());
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
	
	@Test(dataProvider="AreaConfirm",priority=2)
	public void areaSave(Map<String,String> map) throws NumberFormatException, InterruptedException, IOException {
	logger.info("TestCase Name : "+ logger.getName());
	logger.info("Method Name: areaConfirm");
	
	//Navigation to the area page
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
	
	//Verify the default value of Item field
	 String AreaId=AreaPage.getAttributeValue("DetailAreaIDInput", "id");
	 softAssertion.assertNotNull(AreaId, "The AreaId field is not null");
	 
	 //To get the area ID to check post creation
	 AreaIdValue=AreaPage.getAttributeValue("DetailAreaIDInput", "aria-valuenow");
	 
	 String status=AreaPage.getAttributeValue("DetailStatusInput","text");
	 softAssertion.assertNotNull(status, "The status field is not null");
	 
	 String CreateDate=AreaPage.getAttributeValue("DetailCreateDateInput", "value");
	 softAssertion.assertNotNull(CreateDate, "The create date is not null");
	 
	 //to check current date is equal to the create date
	 AreaPage.verifyDefaultDateWithCurrentDate("DetailCreateDateInput");
	 
	 //to enter description into the description box
	 AreaPage.enterIntoTextBox("DetailDescriptionInput", map.get("DescValue"));
	 
	//To select first of option of the drop down always 
	 AreaPage.click("DetailItembasketDropDown");
	 AreaPage.click("DetailItemBasketFrstInput");
	 
	 //To get the Area ID
	 AreaIdValue= AreaPage.getAttributeValue("DetailAreaIDInput", "aria-valuenow");
	 
	 //to select the store which is available
	 Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
	 AreaPage.click("AssignFrstCheckBox");
	 AreaPage.click("DetailAssignSelectedButton");
	 
	 //Click on save button
	  AreaPage.click("DetailSaveButton");
	  
	  //To verify if the dialog is displayed
	  AreaPage.click("DetailConfirmationDialog");
	  AreaPage.click("ConfirmYesButton");
	  Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
	  
	  //Verification of the List title
	  AreaPage.explicitWaitForVisibility("AreaListScreenTitle");
	  AreaPage.verifyTextValue("AreaListScreenTitle",map.get("ListScreenTitle"));
	  
	  //To click on the search button and verify the saved Item basket and delete the same
	  AreaPage.click("ListSearchButton");
	  AreaPage.enterIntoTextBox("AreaIdInput", AreaIdValue);
	  AreaPage.click("SearchButton");
	  AreaPage.click("ListRefreshButton");
	  Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
	  
	  
	  //To click on the record that is displayed
	  AreaPage.click("ListFirstRow");
	  
	  //To delete the Area created
	  AreaPage.click("ListDeleteButton");
	  
	  AreaPage.verifyElementIsDisplayed("DeleteConfirmationDialog");
	  AreaPage.click("ConfirmYesButton");
	  
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
