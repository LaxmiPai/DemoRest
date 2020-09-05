//**@lrathnak**//
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

public class SetUpExtdAttributesGridListFields {
	public static Logger logger = Logger.getLogger(SetUpExtdAttributesGridListFields.class.getName());
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
			
			@DataProvider(name="ExtendsAtriGridLst")
			public Object[][] getTestDataForItemBasketPermission() throws Exception {
				Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("SetUpExtendedAttributes"),
						"ExtendsAtriGridLst");
				return testObjArray;

			}
			
			@Test(dataProvider="ExtendsAtriGridLst", priority=1)
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
			
			@Test(dataProvider="ExtendsAtriGridLst", priority=2)
			public void storePrmsnChck(Map<String,String> map) throws Exception{
				logger.info("Method Name: storePrmsnChck");
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
	
			
			@Test(dataProvider="ExtendsAtriGridLst", priority=3)
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
			
			@Test(dataProvider="ExtendsAtriGridLst", priority=4)
			public void GrdLstFieldVerify(Map<String,String> map) throws Exception {
				logger.info("Method Name: GrdLstFieldVerify");
				//Navigation steps
				HomePage.click("Admin");
				HomePage.click("ConfigurationMenu");
				HomePage.click("ExtendedAttribute");
				HomePage.click("StUpExtendedAttributes");
				
				//Title Verification
				ExtendedAttributepage.explicitWaitForElementToBeClickable("SetUpExtndAtriTitle");
				ExtendedAttributepage.verifyTextValue("SetUpExtndAtriTitle",map.get("SetupTitle"));
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				
				
				//Verify if the Grid fields are present
				HomePage.click("Navigation");
				ExtendedAttributepage.verifyElementIsPresent("GS1HeaderFld");
				ExtendedAttributepage.verifyElementIsPresent("NameHeaderFld");
				ExtendedAttributepage.verifyElementIsPresent("TypeHeaderFld");
				ExtendedAttributepage.verifyElementIsPresent("FormatHeaderFld");
				ExtendedAttributepage.verifyElementIsPresent("LengthHeaderFld");
				ExtendedAttributepage.verifyElementIsPresent("DescriptionHeadrFld");
			
				
				//To verify the sorting order according to the SG1 ID
				ExtendedAttributepage.columnSorting("IdColumnAllRows", "GS1HeaderFld",map.get("sortingOrder"),map.get("Type"));
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
