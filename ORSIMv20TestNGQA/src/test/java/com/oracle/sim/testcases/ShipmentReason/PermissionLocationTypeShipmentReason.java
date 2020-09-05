package com.oracle.sim.testcases.ShipmentReason;
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

public class PermissionLocationTypeShipmentReason {
	
	public static Logger logger = Logger.getLogger(PermissionLocationTypeShipmentReason.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pageFactory = new PageFactory();

	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage ShipmentReasonsPage;
	SimBasePage RoleMaintenancePage;
	
	@BeforeClass()
	public void setup(ITestContext context) throws Exception {
		logger.info("TestCase Name: " +logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
		LoginPage = pageFactory.initialize("LoginPage");
		HomePage = pageFactory.initialize("HomePage");
		ShipmentReasonsPage = pageFactory.initialize("ShipmentReasonsPage");
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username", LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password", LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");

		HomePage = pageFactory.initialize("HomePage");
		
		HomePage.verifyTextValue("Title", HomePage.getProperty("Title"));
		HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
		HomePage.storeIdCheck();
	}
	
	//Data Provider for Security Permission Shipment reasons :Location Type
		@DataProvider(name = "PermissionLocationTypeShipmentTestData")
		public Object[][] getTestDataUIShipment() throws Exception {

			Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("ShipmentReasonTestData"),"PermissionLocationType");
			return testObjArray;
		}
		@Test(dataProvider = "PermissionLocationTypeShipmentTestData")
		public void shipmentReasonsPermissionLocationType(Map<String, String> map) throws Exception {
			

			HomePage.click("Navigation");
			HomePage.click("SecurityPage");
			HomePage.click("RoleMaintenancePage");
			String userRole=propReader.getApplicationproperty("UserRole");
			ShipmentReasonsPage.explicitWaitForElementToBeClickable("RoleNameLink");
			ShipmentReasonsPage.click("FilterByRoleName");
			ShipmentReasonsPage.enterIntoTextBox("FilterByRoleName",userRole);
			ShipmentReasonsPage.explicitWaitForElementToBeClickable("RoleNameLink");
			
			ShipmentReasonsPage.click("RoleNameLink");
			ShipmentReasonsPage.explicitWaitForElementToBeClickable("FilterByPermission");
			ShipmentReasonsPage.explicitWaitForElementToBeClickable("FilterByDataValue");
			ShipmentReasonsPage.explicitWaitForElementToBeClickable("AssignedData");
			
			ShipmentReasonsPage.click("FilterByPermission");
			ShipmentReasonsPage.enterIntoTextBox("FilterByPermission", map.get("Permission"));
			ShipmentReasonsPage.enterIntoTextBox("FilterByDataValue", map.get("DataValue"));
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
			ShipmentReasonsPage.click("GridRecord");
			//Remove the permission for the Location Type of Shipment Reasons
			ShipmentReasonsPage.explicitWaitForElementToBeClickable("AssignedData");
					if(ShipmentReasonsPage.getTitle("AssignedData").equals(map.get("AssignedDataYes"))) {
						ShipmentReasonsPage.explicitWaitForElementToBeClickable("RevokeSelectedButton");
						ShipmentReasonsPage.click("RevokeSelectedButton");
						ShipmentReasonsPage.click("SaveIcon");
						ShipmentReasonsPage.click("YesButton");
						//wait for DB commit to perform 
						Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
						ShipmentReasonsPage.explicitWaitForElementToBeClickable("FilterByPermission");
						ShipmentReasonsPage.click("FilterByPermission");
						ShipmentReasonsPage.enterIntoTextBox("FilterByPermission", map.get("Permission"));
						ShipmentReasonsPage.enterIntoTextBox("FilterByDataValue", map.get("DataValue"));
						ShipmentReasonsPage.explicitWaitForElementToBeClickable("AssignedData");
						System.out.println("Revoke Permission:"+ShipmentReasonsPage.getTitle("AssignedData"));
					}
			
					ShipmentReasonsPage.click("BackLink");
					ShipmentReasonsPage.RefreshWebPage();

					HomePage.click("Navigation");
					HomePage.click("Admin");
					HomePage.click("DataSetup");
					HomePage.click("ShipmentReason");
					ShipmentReasonsPage.verifyTextValue("Title",map.get("Title"));
					ShipmentReasonsPage.click("AddIcon");
					// Input Values in Detail section
					ShipmentReasonsPage.explicitWaitForElementToBeClickable("Type");
					ShipmentReasonsPage.VerifyDropDownOption("Type", map.get("Type"));
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
					
					ShipmentReasonsPage.click("BackLink");
					ShipmentReasonsPage.click("BackLink");
					
					HomePage.click("SecurityPage");
					HomePage.click("RoleMaintenancePage");
					ShipmentReasonsPage.explicitWaitForElementToBeClickable("RoleNameLink");
					ShipmentReasonsPage.click("FilterByRoleName");
					ShipmentReasonsPage.enterIntoTextBox("FilterByRoleName",userRole);
					ShipmentReasonsPage.explicitWaitForElementToBeClickable("RoleNameLink");
					
					ShipmentReasonsPage.click("RoleNameLink");
					ShipmentReasonsPage.explicitWaitForElementToBeClickable("FilterByPermission");
					ShipmentReasonsPage.explicitWaitForElementToBeClickable("AssignedData");
					
					//Assign the Data Value 
					ShipmentReasonsPage.click("FilterByPermission");
					ShipmentReasonsPage.enterIntoTextBox("FilterByPermission", map.get("Permission"));
					//ShipmentReasonsPage.verifyTextValue("GridRecord",map.get("Permission"));
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
					ShipmentReasonsPage.click("GridRecord");
					//Remove the permission for the user
					ShipmentReasonsPage.explicitWaitForElementToBeClickable("AssignedData");
					if(ShipmentReasonsPage.getTitle("AssignedData").equals(map.get("AssignedDataNo"))) {
						ShipmentReasonsPage.explicitWaitForElementToBeClickable("AssigneSelectedButton");
						ShipmentReasonsPage.click("AssigneSelectedButton");
						ShipmentReasonsPage.click("SaveIcon");
						ShipmentReasonsPage.click("YesButton");
						//wait for DB commit to perform 
						Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
						ShipmentReasonsPage.explicitWaitForElementToBeClickable("FilterByPermission");
						ShipmentReasonsPage.click("FilterByPermission");
						ShipmentReasonsPage.enterIntoTextBox("FilterByPermission", map.get("Permission"));
						ShipmentReasonsPage.enterIntoTextBox("FilterByDataValue", map.get("DataValue"));
						ShipmentReasonsPage.explicitWaitForElementToBeClickable("AssignedData");
						System.out.println("Assign Permission:"+ShipmentReasonsPage.getTitle("AssignedData"));
					}
					
					// Refresh the Web-Page..
					ShipmentReasonsPage.RefreshWebPage();
					//Navigate to Shipment Reasons page...
					
					HomePage.click("Navigation");
					HomePage.click("Admin");
					HomePage.click("DataSetup");
					HomePage.click("ShipmentReason");
					//Verify the Title of the Screen..
					ShipmentReasonsPage.verifyTextValue("Title", map.get("Title"));
					System.out.println("Verify the Refresh Button");
					// add Shipment Reason
					ShipmentReasonsPage.explicitWaitForElementToBeClickable("AddIcon");
					ShipmentReasonsPage.click("AddIcon");
					ShipmentReasonsPage.verifyElementIsEnabled("ApplyButton");
					ShipmentReasonsPage.verifyElementIsEnabled("CancelButton");
					ShipmentReasonsPage.verifyElementIsEnabled("Type");
					
					// Input Values in Detail section
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
					ShipmentReasonsPage.explicitWaitForElementToBeClickable("Type");
					ShipmentReasonsPage.click("Type");
					ShipmentReasonsPage.verifyTextValue("FinisherdropdownValue", map.get("Type"));
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
					//Click on Cancel Button
					ShipmentReasonsPage.click("CancelButton");
					
					//verify Sub-Bucket in Edit Mode..
					ShipmentReasonsPage.explicitWaitForElementToBeClickable("FilterByType");
					ShipmentReasonsPage.click("FilterByType");
					ShipmentReasonsPage.enterIntoTextBox("FilterByType","Supplier");
					ShipmentReasonsPage.explicitWaitForElementToBeClickable("GridRecord");
					ShipmentReasonsPage.click("GridRecord");
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
					ShipmentReasonsPage.click("EditIcon");
					ShipmentReasonsPage.verifyElementIsDisabled("TypeinEditMode");
					
					ShipmentReasonsPage.click("CancelButton");
					

					
				
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
