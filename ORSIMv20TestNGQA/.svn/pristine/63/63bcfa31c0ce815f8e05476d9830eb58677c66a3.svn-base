package com.oracle.sim.testcases.MPSStagedMessage;

/**
 * @author vidhsank
 *
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

public class MpsStagedMessageDelete {
	public static Logger logger=Logger.getLogger(MpsStagedMessageDelete.class.getName());
	protected static PropertyReader propReader=new PropertyReader();
	PageFactory pagefactory=new PageFactory();

	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage RoleMaintenancePage;
	SimBasePage MpsStagedMessagePage;

	@BeforeClass()
	public void setup(ITestContext context) throws Exception {
		logger.info("TestCase Name: " +logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
		LoginPage=pagefactory.initialize("LoginPage");
		HomePage=pagefactory.initialize("HomePage");
		RoleMaintenancePage=pagefactory.initialize("RoleMaintenancePage");
		MpsStagedMessagePage=pagefactory.initialize("MpsStagedMessagePage");
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username",LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password",LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");	
		HomePage.verifyTextValue("Title", "Store Inventory Operations Cloud Service");
		HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
		HomePage.storeIdCheck();
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.click("Navigation");
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
		HomePage.click("Security");
		HomePage.click("RoleMaintenance");		

		//Verifying RoleMaintenance Page Title
		RoleMaintenancePage.explicitWaitForElementToBeClickable("Title");
		RoleMaintenancePage.verifyTextValue("Title",map.get("Title"));

		//Granting the user permission
		String userRole=propReader.getApplicationproperty("UserRole");
		RoleMaintenancePage.verifyUserRole(userRole, map.get("AccessMpsStagedMessages"), map.get("AssignedDataNo"));
	}

	@DataProvider(name = "DeleteMessageTestData")
	public Object[][] getDeleteMessageTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("MpsStagedMessageTestData"),
				"DeleteMessage");
		return testObjArray;
	}

	@Test(dataProvider="DeleteMessageTestData", priority=2,dependsOnMethods= { "verifyRole" })
	public void deleteMpsStagedMessage(Map<String,String> map) throws Exception {
		//Navigating to MPS staged message screen
		logger.info("Method Name: deleteMpsStagedMessage");
		HomePage.click("Admin");
		HomePage.click("TechnicalMaintenance");
		HomePage.click("MpsStagedMessage");		
		HomePage.click("Navigation");

		//Verifying the page title
		MpsStagedMessagePage.explicitWaitForElementToBeClickable("Title");
		MpsStagedMessagePage.verifyTextValue("Title",map.get("Title"));

		//clicking on search icon to display MPS staged message
		MpsStagedMessagePage.explicitWaitForElementToBeClickable("SearchIcon");
		MpsStagedMessagePage.click("SearchIcon");

		//Selecting show pending status as 'yes'
		MpsStagedMessagePage.explicitWaitForElementToBeClickable("SearchCriteriaTitle");
		MpsStagedMessagePage.explicitWaitForElementToBeClickable("ShowPendingButton");		
		if((MpsStagedMessagePage.getAttributeValue("ShowPendingButton",map.get("ValueAttribute"))).equalsIgnoreCase(map.get("ShowPendingNo"))){
			MpsStagedMessagePage.click("ShowPendingButton");
		}

		//Clicking on search button
		MpsStagedMessagePage.explicitWaitForElementToBeClickable("SearchButton");
		MpsStagedMessagePage.click("SearchButton");

		//Verifying MPS staged messages count
		int rowCount=MpsStagedMessagePage.getTableRowCount("RecordIdColumnData","Description","FilterRecordId");

		//Do not select any MPS message, click on Delete button
		MpsStagedMessagePage.explicitWaitForElementToBeClickable("DeleteSelectedButton");
		MpsStagedMessagePage.click("DeleteSelectedButton");

		//Verifying the delete message
		MpsStagedMessagePage.explicitWaitForElementToBeClickable("DeleteAlertText");
		MpsStagedMessagePage.verifyTextValue("DeleteAlertText",map.get("DeleteAlertText"));
		MpsStagedMessagePage.click("OkButton");

		//Verifying the messages count
		if(rowCount==0) {
			System.out.println("No table records found");
		}
		else {
			System.out.println("Table Records Count: "+rowCount);
			//Selecting a message to perform delete operation
			MpsStagedMessagePage.explicitWaitForElementToBeClickable("FirstTableRecord");
			MpsStagedMessagePage.click("FirstTableRecord");
			String recordId=MpsStagedMessagePage.getText("FirstCellRecordId");

			//Clicking on a delete selected button
			MpsStagedMessagePage.explicitWaitForElementToBeClickable("DeleteSelectedButton");
			MpsStagedMessagePage.click("DeleteSelectedButton");

			//Verifying the delete confirmation pop up
			MpsStagedMessagePage.explicitWaitForElementToBeClickable("DeleteConfirmationMessage");
			MpsStagedMessagePage.verifyTextValue("DeleteConfirmationMessage",map.get("DeleteConfirmationMessage"));
			MpsStagedMessagePage.verifyElementIsPresent("YesButton");
			MpsStagedMessagePage.verifyElementIsPresent("NoButton");

			//Clicking on a no button in delete pop up
			MpsStagedMessagePage.click("NoButton");
			MpsStagedMessagePage.implicitWait();

			//Filtering the selected record
			MpsStagedMessagePage.explicitWaitForElementToBeClickable("FilterRecordId");
			MpsStagedMessagePage.click("FilterRecordId");
			MpsStagedMessagePage.enterIntoTextBox("FilterRecordId",recordId);

			//Verifying the record is not deleted
			MpsStagedMessagePage.explicitWaitForElementToBeClickable("GridRecord");
			MpsStagedMessagePage.verifyTextValue("GridRecord",recordId);

			//Selecting a message to perform delete operation
			MpsStagedMessagePage.clearElement("FilterRecordId");
			MpsStagedMessagePage.implicitWait();
			MpsStagedMessagePage.explicitWaitForElementToBeClickable("FirstTableRecord");
			MpsStagedMessagePage.click("FirstTableRecord");

			//Clicking on a delete selected button
			MpsStagedMessagePage.explicitWaitForElementToBeClickable("DeleteSelectedButton");
			MpsStagedMessagePage.click("DeleteSelectedButton");

			//Clicking on a yes button in delete pop up
			MpsStagedMessagePage.click("YesButton");
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));

			//Filtering the selected record
			MpsStagedMessagePage.explicitWaitForElementToBeClickable("FilterRecordId");
			MpsStagedMessagePage.click("FilterRecordId");
			MpsStagedMessagePage.enterIntoTextBox("FilterRecordId",recordId);
			MpsStagedMessagePage.click("FilterRecordId");

			//Verifying the record is deleted
			MpsStagedMessagePage.explicitWaitForElementToBeClickable("NoRowsMatchText");
			MpsStagedMessagePage.verifyTextValue("NoRowsMatchText",map.get("NoRowsMatchText"));
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
