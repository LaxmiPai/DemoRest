package com.oracle.sim.testcases.Config.ExtendedAttributes;

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

public class SetUpExtendedAttriEditSaveCancel {
	public static Logger logger = Logger.getLogger(SetUpExtendedAttriEditSaveCancel.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pagefactory = new PageFactory();

	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage RoleMaintenancePage;
	SimBasePage ExtendedAttributepage;
	SimBasePage ShipmentReasonsPage;
	SoftAssert softAssertion = new SoftAssert();

			@BeforeClass
			public void setup(ITestContext context) throws Exception {
				logger.info("TestCase Name: " + logger.getName());
				SIMWebdriverBase.loadInitialURL(context);
				LoginPage = pagefactory.initialize("LoginPage");
				HomePage = pagefactory.initialize("HomePage");
				RoleMaintenancePage = pagefactory.initialize("RoleMaintenancePage");
				ExtendedAttributepage = pagefactory.initialize("SetUpExtendedAttributesPage");
				ShipmentReasonsPage = pagefactory.initialize("ShipmentReasonsPage");
				LoginPage.explicitWaitForVisibility("Username");
				LoginPage.enterIntoTextBox("Username", LoginPage.getProperty("Username"));
				LoginPage.enterIntoTextBox("Password", LoginPage.getProperty("Password"));
				LoginPage.click("SignIn");
				HomePage.verifyTextValue("Title", HomePage.getProperty("Title"));
				HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
				HomePage.storeIdCheck();
			}
			
			@DataProvider(name="ExtndAttriEditSavCan")
			public Object[][] getTestDataForItemBasketPermission() throws Exception {
				Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("SetUpExtendedAttributes"),
						"ExtndAttriEditSavCan");
				return testObjArray;

			}
			
			@Test(dataProvider="ExtndAttriEditSavCan", priority=1)
			public void systemPrmsnCheck(Map<String,String> map) throws Exception {
				logger.info("Method Name: systemPrmsnCheck");
				HomePage.click("Navigation");
				HomePage.click("Admin");
				HomePage.click("ConfigurationMenu");
				HomePage.click("SystemAdministrationPage");
				
				ShipmentReasonsPage.explicitWaitForElementToBeClickable("FilterByOption");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
				ShipmentReasonsPage.click("FilterByOption");
				ShipmentReasonsPage.enterIntoTextBox("FilterByOption",map.get("Option"));
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
				ShipmentReasonsPage.click("GridRecord");
				
				//To check if the user has enabled the permission
				String value=ShipmentReasonsPage.getText("ValueSwitch");
				softAssertion.assertEquals(value, map.get("Assigned"));
				logger.info("The user has enabled th enecessary system permissions");
				
				//Refresh the web browser
				ShipmentReasonsPage.RefreshWebPage();
			}
			
			@Test(dataProvider="ExtndAttriEditSavCan", priority=2)
			public void storePrmsnChck(Map<String,String> map) throws Exception{
				logger.info("Method Name:  storePrmsnChck");
				HomePage.click("Navigation");
				HomePage.click("Admin");
				HomePage.click("ConfigurationMenu");
				HomePage.click("StoreAdministrationPage");
				
				ShipmentReasonsPage.explicitWaitForElementToBeClickable("FilterByOption");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
				ShipmentReasonsPage.click("FilterByOption");
				ShipmentReasonsPage.enterIntoTextBox("FilterByOption",map.get("Permission"));
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
				ShipmentReasonsPage.click("GridRecord");
				
				//To check if the user has enabled the permission
				String value1=ShipmentReasonsPage.getText("ValueSwitch");
				softAssertion.assertEquals(value1, map.get("Assigned"));
				logger.info("The user has enabled th enecessary system permissions");
				
				//Refresh the web browser
				ShipmentReasonsPage.RefreshWebPage();
			}
	
			
			
			@Test(dataProvider="ExtndAttriEditSavCan", priority=3)
			public void PermisnCheck(Map<String,String> map) throws Exception {
				logger.info("Method Name: PermisnCheck");
				//Navigating to role maintenance page
				logger.info("Method Name: PermisnCheck");
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
			}
			
			@Test(dataProvider="ExtndAttriEditSavCan", priority=4)
			public void SaveEditCancel(Map<String,String> map) throws Exception {
				
				logger.info("MethodName:SaveEditCancel");
				
				//Navigation steps
				HomePage.click("Admin");
				HomePage.click("ConfigurationMenu");
				HomePage.click("ExtendedAttribute");
				HomePage.click("StUpExtendedAttributes");
				
				//Title Verification
				ExtendedAttributepage.explicitWaitForElementToBeClickable("SetUpExtndAtriTitle");
				ExtendedAttributepage.verifyTextValue("SetUpExtndAtriTitle",map.get("SetupTitle"));
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				
				//Select the first row
				ExtendedAttributepage.click("Frstrow");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
				ExtendedAttributepage.verifyElementIsEnabled("DetailEditButton");
				ExtendedAttributepage.click("DetailEditButton");
				
				//To verify the buttons
				ExtendedAttributepage.verifyElementIsDisabled("DetailEditButton");
				ExtendedAttributepage.verifyElementIsEnabled("DetailApplyButton");
				ExtendedAttributepage.verifyElementIsEnabled("DetailCancelButton");
				
				//To make changes to the name attribute of the selected record
				String name=ExtendedAttributepage.getText("FrstNameTxt");
				ExtendedAttributepage.enterIntoTextBox("DetailNameInputFld",map.get("Name"));
				
				//To click on apply
				ExtendedAttributepage.click("DetailApplyButton");
				
				//To click on save
				ExtendedAttributepage.click("SaveButton");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				
				//To check for the dialog
				ExtendedAttributepage.verifyElementIsDisplayed("SaveConfirmationDialog");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				ExtendedAttributepage.click("ConfirmYesButton");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("High")));
				
				
				
				//To filter by the new name
				ExtendedAttributepage.enterIntoTextBox("NameFilterField", map.get("Name"));
				ExtendedAttributepage.click("Frstrow");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
				ExtendedAttributepage.verifyElementIsEnabled("DetailEditButton");
				ExtendedAttributepage.click("DetailEditButton");
				
				ExtendedAttributepage.enterIntoTextBox("DetailNameInputFld",name);
				

				//To click on apply
				ExtendedAttributepage.click("DetailApplyButton");
				
				//To click on save
				ExtendedAttributepage.click("SaveButton");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				ExtendedAttributepage.verifyElementIsDisplayed("SaveConfirmationDialog");
				ExtendedAttributepage.click("ConfirmYesButton");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
			}	
			
			@Test(dataProvider="ExtndAttriEditSavCan", priority=5)
			public void Cancelchck(Map<String,String> map) throws Exception {
				logger.info("MethodName:Cancelchck");
				
				//Select the first row
				ExtendedAttributepage.click("Frstrow");
				
				//To first fetch all the values
				String frstVal=ExtendedAttributepage.getText("FrstNameTxt");
				
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
				ExtendedAttributepage.verifyElementIsEnabled("DetailEditButton");
				ExtendedAttributepage.click("DetailEditButton");
				
				//To make changes in the name field and click on cancel
				ExtendedAttributepage.enterIntoTextBox("DetailNameInputFld",map.get("Name"));
				ExtendedAttributepage.click("DetailCancelButton");
				
				//To verify the buttons
				ExtendedAttributepage.verifyElementIsEnabled("DetailEditButton");
				ExtendedAttributepage.verifyElementIsDisabled("DetailApplyButton");
				ExtendedAttributepage.verifyElementIsDisabled("DetailCancelButton");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
				
				//To filter by original name to check no changes are updated
				ExtendedAttributepage.enterIntoTextBox("NameFilterField",frstVal);
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
				
				//Select the first row
				ExtendedAttributepage.click("Frstrow");
				
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


