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

public class SetupExtndAtriDetailApply {
	public static Logger logger = Logger.getLogger(SetupExtndAtriDetailApply.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pagefactory = new PageFactory();

	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage RoleMaintenancePage;
	SimBasePage ExtendedAttributepage;
	SimBasePage ShipmentReasonsPage;
	SoftAssert softAssertion = new SoftAssert();
	public static String typeVal=null;
	public static String name=null;

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
			
			@DataProvider(name="ExtdAtriDetlApply")
			public Object[][] getTestDataForItemBasketPermission() throws Exception {
				Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("SetUpExtendedAttributes"),
						"ExtdAtriDetlApply");
				return testObjArray;

			}
			
			@Test(dataProvider="ExtdAtriDetlApply", priority=1)
			public void systemPrmsnCheck(Map<String,String> map) throws Exception {
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
			
			@Test(dataProvider="ExtdAtriDetlApply", priority=2)
			public void storePrmsnChck(Map<String,String> map) throws Exception{
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
	
			@Test(dataProvider="ExtdAtriDetlApply", priority=3)
			public void PermisnCheck(Map<String,String> map) throws Exception {
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
		
			@Test(dataProvider="ExtdAtriDetlApply", priority=4)
			public void DetailExtdAtriApply(Map<String,String> map) throws Exception {
				logger.info("MethodName: DetailExtdAtriApply");
				
				//Navigation steps
				HomePage.click("Admin");
				HomePage.click("ConfigurationMenu");
				HomePage.click("ExtendedAttribute");
				HomePage.click("StUpExtendedAttributes");
				
				//Title Verification
				ExtendedAttributepage.explicitWaitForElementToBeClickable("SetUpExtndAtriTitle");
				ExtendedAttributepage.verifyTextValue("SetUpExtndAtriTitle",map.get("SetupTitle"));
				typeVal=ExtendedAttributepage.getText("FrstTypeCellTxt");
				 name=ExtendedAttributepage.getText("FrstNameTxt");
				
				//To click on first row and verify 
				ExtendedAttributepage.click("Frstrow");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
				ExtendedAttributepage.verifyElementIsEnabled("DetailEditButton");
				ExtendedAttributepage.click("DetailEditButton");
				
				//To check for editable and buttons those are enabled
				ExtendedAttributepage.verifyElementIsEnabled("DetailApplyButton");
				ExtendedAttributepage.verifyElementIsEnabled("DetailCancelButton");
				ExtendedAttributepage.verifyElementIsEditable("DetailNameInputFld");
				ExtendedAttributepage.verifyElementIsEditable("DetailDecsInputFld");
				ExtendedAttributepage.verifyElementIsEditable("DetalTypeInputFld");
				
				//Clear the name field and apply to verify the error
				ExtendedAttributepage.clearElement("DetailNameInputFld");
				ExtendedAttributepage.click("DetailApplyButton");
				
				//Verify the error message 
				ExtendedAttributepage.verifyTextValue("NameErrorMsg",map.get("ErrValue"));
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
				
				//To click on Cancel button and verify other fields
				ExtendedAttributepage.click("DetailCancelButton");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				ExtendedAttributepage.verifyElementIsEnabled("DetailEditButton");
				ExtendedAttributepage.verifyElementIsDisabled("DetailApplyButton");
				ExtendedAttributepage.verifyElementIsDisabled("DetailCancelButton");
				
				//To filter it with old value
				
			}
			@Test(dataProvider="ExtdAtriDetlApply", priority=5)
			public void DetailExtdAtriApplyDesc(Map<String,String> map) throws Exception {
				logger.info("MethodName: DetailExtdAtriApplyDesc");
				
				//Clearing the value in type and description
				ExtendedAttributepage.click("RefreshButton");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				ExtendedAttributepage.click("Frstrow");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
				ExtendedAttributepage.click("DetailEditButton");
				
				//To capture the original value of the record
				ExtendedAttributepage.click("DetalTypeInputFld");
				
				
				//Blank out type and description fields in the details field
				ExtendedAttributepage.clearElement("DetalTypeInputFld");
				ExtendedAttributepage.clearElement("DetailDecsInputFld");
				//Verify for the message not present
				
				//To click on apply
				ExtendedAttributepage.click("DetailApplyButton");
				ExtendedAttributepage.click("RefreshButton");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
				
				//To verify the warning
				ExtendedAttributepage.verifyElementIsPresent("RefreshWarning");
				ExtendedAttributepage.click("RefreshWarningOkButton");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				
				//To filter button with original value to verify no changes are made
				ExtendedAttributepage.enterIntoTextBox("ListTypeFilerFld",typeVal);
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
				
				//To check whether the filtered record gets highlighted
				ExtendedAttributepage.verifyPartialTextValue("ListTypeFilerFld", typeVal);
				
				
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
		HomePage.closeDBConnection();
	}
}
}


