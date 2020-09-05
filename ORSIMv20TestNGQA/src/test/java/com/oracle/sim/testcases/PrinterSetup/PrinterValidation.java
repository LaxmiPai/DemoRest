package com.oracle.sim.testcases.PrinterSetup;

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

public class PrinterValidation {
	public static Logger logger=Logger.getLogger(PrinterValidation.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pagefactory=new PageFactory();

	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage RoleMaintenancePage;
	SimBasePage PrinterSetupPage;

	@BeforeClass()
	public void setup(ITestContext context) throws Exception {
		logger.info("TestCase Name: " +logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
		LoginPage=pagefactory.initialize("LoginPage");
		HomePage=pagefactory.initialize("HomePage");
		RoleMaintenancePage=pagefactory.initialize("RoleMaintenancePage");
		PrinterSetupPage=pagefactory.initialize("PrinterSetupPage");
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
		RoleMaintenancePage.verifyUserRole(userRole, map.get("AccessPrinterSetup"), map.get("AssignedDataNo"));	
	}

	@DataProvider(name = "PrinterValidationTestData")
	public Object[][] getPrinterValidationTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("PrinterTestData"),"PrinterValidation");
		return testObjArray;
	}

	@Test(dataProvider="PrinterValidationTestData",priority=2 ,dependsOnMethods = {"verifyRole"})
	public void validateErrorMessage(Map<String,String> map) throws Exception {
		//Navigating to printer setup screen
		logger.info("Method Name: validateErrorMessage");
		HomePage.explicitWaitForElementToBeClickable("Admin");
		HomePage.click("Admin");
		HomePage.explicitWaitForElementToBeClickable("TechnicalMaintenance");
		HomePage.click("TechnicalMaintenance");
		HomePage.explicitWaitForElementToBeClickable("PrinterSetup");
		HomePage.click("PrinterSetup");

		//Verifying the Page Title
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.click("Navigation");
		PrinterSetupPage.explicitWaitForElementToBeClickable("Title");
		PrinterSetupPage.verifyTextValue("Title",map.get("Title"));

		//Verifying the records are displayed
		PrinterSetupPage.explicitWaitForVisibility("PreShipmentColumnName");
		PrinterSetupPage.explicitWaitForElementToBeClickable("FilterName");
		int size=PrinterSetupPage.getTableRowCount("NameColumnRecords","PreShipmentColumnName","FilterName");
		if(size==0) {
			System.out.println("No records found");
		}else {
			//Clicking on the add button
			PrinterSetupPage.explicitWaitForElementToBeClickable("Add");
			PrinterSetupPage.click("Add");

			//Verifying detail new title
			PrinterSetupPage.explicitWaitForVisibility("DetailNewTitle");
			PrinterSetupPage.verifyTextValue("DetailNewTitle",map.get("DetailNewTitle"));

			//Except Name input all mandatory fields 
			PrinterSetupPage.explicitWaitForElementToBeClickable("Type_dropdown");
			PrinterSetupPage.selectDropDownValue("Type_dropdown",map.get("Type"));
			PrinterSetupPage.explicitWaitForElementToBeClickable("Address");
			PrinterSetupPage.enterIntoTextBox("Address", map.get("Address"));

			//clicking on apply button
			PrinterSetupPage.explicitWaitForElementToBeClickable("Apply");
			PrinterSetupPage.click("Apply");

			//Verifying 'value is required' error message
			PrinterSetupPage.explicitWaitForElementToBeClickable("ErrorMessage");
			PrinterSetupPage.verifyTextValue("ErrorMessage",map.get("ValueRequiredErrorMessage"));

			//Entering a printer name for verification
			PrinterSetupPage.click("Name");
			PrinterSetupPage.enterIntoTextBox("Name",map.get("Name"));

			//Clicking on apply button
			PrinterSetupPage.explicitWaitForElementToBeClickable("Apply");
			PrinterSetupPage.click("Apply");

			//Clicking on the add button
			PrinterSetupPage.explicitWaitForElementToBeClickable("Add");
			PrinterSetupPage.click("Add");

			//Entering the existing name
			PrinterSetupPage.explicitWaitForElementToBeClickable("Address");
			PrinterSetupPage.click("Name");
			PrinterSetupPage.enterIntoTextBox("Name", map.get("Name"));
			PrinterSetupPage.explicitWaitForElementToBeClickable("Type_dropdown");
			PrinterSetupPage.selectDropDownValue("Type_dropdown",map.get("Type"));
			PrinterSetupPage.explicitWaitForElementToBeClickable("Address");
			PrinterSetupPage.enterIntoTextBox("Address", map.get("Address"));

			//clicking on apply button
			PrinterSetupPage.explicitWaitForElementToBeClickable("Apply");
			PrinterSetupPage.click("Apply");

			//Verifying 'value is not unique' error message
			PrinterSetupPage.explicitWaitForElementToBeClickable("ErrorMessage");
			PrinterSetupPage.verifyTextValue("ErrorMessage",map.get("UniqueValueErrorMessage"));

			//Clicking on cancel button
			PrinterSetupPage.explicitWaitForElementToBeClickable("DetailCancelButton");
			PrinterSetupPage.click("DetailCancelButton");

			//Clicking on refresh ok button
			PrinterSetupPage.explicitWaitForElementToBeClickable("Refresh");
			PrinterSetupPage.click("Refresh");
			PrinterSetupPage.explicitWaitForElementToBeClickable("OkButton");
			PrinterSetupPage.click("OkButton");

			//Clicking on the add button
			PrinterSetupPage.explicitWaitForElementToBeClickable("Add");
			PrinterSetupPage.click("Add");

			//Except Network address input all mandatory fields
			PrinterSetupPage.explicitWaitForElementToBeClickable("Address");
			PrinterSetupPage.click("Name");
			PrinterSetupPage.enterIntoTextBox("Name", map.get("Name"));
			PrinterSetupPage.explicitWaitForElementToBeClickable("Type_dropdown");
			PrinterSetupPage.selectDropDownValue("Type_dropdown",map.get("Type"));

			//clicking on apply button
			PrinterSetupPage.explicitWaitForElementToBeClickable("Apply");
			PrinterSetupPage.click("Apply");

			//Verifying 'value is required' error message
			PrinterSetupPage.explicitWaitForElementToBeClickable("ErrorMessage");
			PrinterSetupPage.verifyTextValue("ErrorMessage",map.get("ValueRequiredErrorMessage"));

			//Clicking on cancel button
			PrinterSetupPage.explicitWaitForElementToBeClickable("DetailCancelButton");
			PrinterSetupPage.click("DetailCancelButton");

			//Clicking on the add button
			PrinterSetupPage.explicitWaitForElementToBeClickable("Add");
			PrinterSetupPage.click("Add");

			//Except type input all mandatory fields
			PrinterSetupPage.explicitWaitForElementToBeClickable("Name");
			PrinterSetupPage.click("Name");
			PrinterSetupPage.enterIntoTextBox("Name", map.get("Name"));
			PrinterSetupPage.explicitWaitForElementToBeClickable("Address");
			PrinterSetupPage.click("Address");
			PrinterSetupPage.enterIntoTextBox("Address", map.get("Address"));

			//clicking on apply button
			PrinterSetupPage.explicitWaitForElementToBeClickable("Apply");
			PrinterSetupPage.click("Apply");

			//Verifying 'value is required' error message
			PrinterSetupPage.explicitWaitForElementToBeClickable("ErrorMessage");
			PrinterSetupPage.verifyTextValue("ErrorMessage",map.get("ValueRequiredErrorMessage"));

			//Clicking on cancel button
			PrinterSetupPage.explicitWaitForElementToBeClickable("DetailCancelButton");
			PrinterSetupPage.click("DetailCancelButton");

			//Without selecting any printer clicking on remove button
			PrinterSetupPage.explicitWaitForElementToBeClickable("RemoveButton");
			PrinterSetupPage.click("RemoveButton");

			//Verifying the error message and clicking on ok button
			PrinterSetupPage.explicitWaitForElementToBeClickable("NoRecordsSelectedErrorMessage");
			PrinterSetupPage.verifyTextValue("NoRecordsSelectedErrorMessage",map.get("RemoveErrorMessage"));
			PrinterSetupPage.explicitWaitForElementToBeClickable("OkButton");
			PrinterSetupPage.click("OkButton");

			//Clicking on the add button
			PrinterSetupPage.explicitWaitForElementToBeClickable("Add");
			PrinterSetupPage.click("Add");

			//Entering values exceeds the max length in name,description,address 
			PrinterSetupPage.explicitWaitForElementToBeClickable("Name");
			PrinterSetupPage.click("Name");
			PrinterSetupPage.enterIntoTextBox("Name", map.get("MaximumLengthName"));
			PrinterSetupPage.explicitWaitForElementToBeClickable("Description");
			PrinterSetupPage.click("Description");
			PrinterSetupPage.enterIntoTextBox("Description", map.get("MaximumLengthDescription"));
			PrinterSetupPage.explicitWaitForElementToBeClickable("Address");
			PrinterSetupPage.click("Address");
			PrinterSetupPage.enterIntoTextBox("Address", map.get("MaximumLengthAddress"));

			//clicking on apply button
			PrinterSetupPage.explicitWaitForElementToBeClickable("Apply");
			PrinterSetupPage.click("Apply");

			//Verifying exceed length error message
			PrinterSetupPage.explicitWaitForElementToBeClickable("MaxLengthNameErrorMsg");
			PrinterSetupPage.verifyTextValue("MaxLengthNameErrorMsg",map.get("MaxLengthNameErrorMsg"));
			PrinterSetupPage.explicitWaitForElementToBeClickable("MaxLengthDescriptionErrorMsg");
			PrinterSetupPage.verifyTextValue("MaxLengthDescriptionErrorMsg",map.get("MaxLengthDescriptionErrorMsg"));
			PrinterSetupPage.explicitWaitForElementToBeClickable("MaxLengthAddressErrorMsg");
			PrinterSetupPage.verifyTextValue("MaxLengthAddressErrorMsg",map.get("MaxLengthAddressErrorMsg"));
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
