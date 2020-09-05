package com.oracle.sim.testcases.Config.ExtendedAttributes;

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

public class SetupExtendAttributesEdit {
	public static Logger logger = Logger.getLogger(SetupExtendAttributesEdit.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pagefactory = new PageFactory();

	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage RoleMaintenancePage;
	SimBasePage ExtendedAttributepage;

			@BeforeClass
			public void setup(ITestContext context) throws Exception {
				logger.info("TestCase Name: " + logger.getName());
				SIMWebdriverBase.loadInitialURL(context);
				LoginPage = pagefactory.initialize("LoginPage");
				HomePage = pagefactory.initialize("HomePage");
				RoleMaintenancePage = pagefactory.initialize("RoleMaintenancePage");
				ExtendedAttributepage = pagefactory.initialize("SetUpExtendedAttributesPage");
				LoginPage.explicitWaitForVisibility("Username");
				LoginPage.enterIntoTextBox("Username", LoginPage.getProperty("Username"));
				LoginPage.enterIntoTextBox("Password", LoginPage.getProperty("Password"));
				LoginPage.click("SignIn");
				HomePage.verifyTextValue("Title", HomePage.getProperty("Title"));
				HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
				HomePage.storeIdCheck();
			}
			
			@DataProvider(name="ExtendeAtriEdit")
			public Object[][] getTestDataForItemBasketPermission() throws Exception {
				Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("SetUpExtendedAttributes"),
						"ExtendeAtriEdit");
				return testObjArray;

			}
			
			@Test(dataProvider="ExtendeAtriEdit", priority=1)
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
			
			@Test(dataProvider="ExtendeAtriEdit", priority=2)
			public void EnterValueswitoutSave(Map<String,String> map) throws Exception {
				logger.info("Method Name: EnterValueswitoutSave");
				//Navigation steps
				HomePage.click("Admin");
				HomePage.click("ConfigurationMenu");
				HomePage.click("ExtendedAttribute");
				HomePage.click("StUpExtendedAttributes");
				
				//Title Verification
				ExtendedAttributepage.explicitWaitForElementToBeClickable("SetUpExtndAtriTitle");
				ExtendedAttributepage.verifyTextValue("SetUpExtndAtriTitle",map.get("SetupTitle"));
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				
				//To click on the first record
				ExtendedAttributepage.click("Frstrow");
				String frstVal=ExtendedAttributepage.getText("FrstNameTxt");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
				
				//To verify the details fields after click on edit
				ExtendedAttributepage.verifyElementIsEnabled("DetailEditButton");
				ExtendedAttributepage.click("DetailEditButton");
				
				//Enter the necessary values in the field
				ExtendedAttributepage.enterIntoTextBox("DetailNameInputFld",map.get("Name"));
				ExtendedAttributepage.clearElement("DetalTypeInputFld");
				ExtendedAttributepage.enterIntoTextBox("DetailDecsInputFld",map.get("Description"));
				
				//Click on Cancel button
				ExtendedAttributepage.click("DetailCancelButton");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				ExtendedAttributepage.enterIntoTextBox("NameFilterField",map.get("Name"));
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				ExtendedAttributepage.click("RefreshButton");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				
				//To filter by original name to check no changes are updated
				ExtendedAttributepage.enterIntoTextBox("NameFilterField",frstVal);
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
				
			}
			
			@Test(dataProvider="ExtendeAtriEdit", priority=3)
			public void ClckApplyRefsh(Map<String,String> map) throws Exception {
				logger.info("Method Name: ClckApplyRefsh");
				//To click on the first record
				ExtendedAttributepage.click("Frstrow");
				String frstVal=ExtendedAttributepage.getText("FrstNameTxt");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
				
				//To verify the details fields after click on edit
				ExtendedAttributepage.verifyElementIsEnabled("DetailEditButton");
				ExtendedAttributepage.click("DetailEditButton");
				
				//Enter the necessary values in the field
				ExtendedAttributepage.enterIntoTextBox("DetailNameInputFld",map.get("Name"));
				ExtendedAttributepage.clearElement("DetalTypeInputFld");
				ExtendedAttributepage.enterIntoTextBox("DetailDecsInputFld",map.get("Description"));
				
				//Click on Apply button
				ExtendedAttributepage.click("DetailApplyButton");
				
				//Click on refresh button without saving
				ExtendedAttributepage.click("RefreshButton");
				ExtendedAttributepage.click("RefreshWarningOkButton");
				
				//To filter by original name to check no changes are updated
				ExtendedAttributepage.enterIntoTextBox("NameFilterField",frstVal);
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
				
			}
			
			@Test(dataProvider="ExtendeAtriEdit", priority=4)
			public void GrdLstFieldVerify(Map<String,String> map) throws Exception {
				logger.info("Method Name: GrdLstFieldVerify");
				
				//To click on the first record
				ExtendedAttributepage.click("Frstrow");
				
				//To verify the details fields after click on edit
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
				ExtendedAttributepage.verifyElementIsEnabled("DetailEditButton");
				ExtendedAttributepage.click("DetailEditButton");
				
				ExtendedAttributepage.verifyElementIsEnabled("DetailApplyButton");
				ExtendedAttributepage.verifyElementIsEnabled("DetailCancelButton");
				ExtendedAttributepage.verifyElementIsDisabled("DetailEditButton");
				
				//To check for the fields which are not editable
				ExtendedAttributepage.verifyReadOnly("DetailGS1IDFld");
				ExtendedAttributepage.verifyReadOnly("DetailFormatInputFld");
				ExtendedAttributepage.verifyReadOnly("DetailLengthInputFld");
				
				//To edit the name field
				String frstVal=ExtendedAttributepage.getText("FrstNameTxt");
				ExtendedAttributepage.enterIntoTextBox("DetailNameInputFld",map.get("Name"));
				
				//Keeping the type field blank
				String frstTye=ExtendedAttributepage.getText("FrstTypeCellTxt");
				ExtendedAttributepage.clearElement("DetalTypeInputFld");
				
				//Change the value of the description box
				String frstDec=ExtendedAttributepage.getText("FrstDescTxt");
				ExtendedAttributepage.enterIntoTextBox("DetailDecsInputFld",map.get("Description"));
				
				//To click on apply
				ExtendedAttributepage.click("DetailApplyButton");
				
				//To click on save
				ExtendedAttributepage.click("SaveButton");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				
				//To check for the dialog
				ExtendedAttributepage.verifyElementIsDisplayed("SaveConfirmationDialog");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				ExtendedAttributepage.click("ConfirmYesButton");
				
				//To filter by the edited name to verify if the record is edited
				ExtendedAttributepage.enterIntoTextBox("NameFilterField",map.get("Name"));
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				ExtendedAttributepage.click("Frstrow");
				
				//To verify the details fields after click on edit
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
				ExtendedAttributepage.verifyElementIsEnabled("DetailEditButton");
				ExtendedAttributepage.click("DetailEditButton");
				
				//Assigning back the original values and saving them again
				ExtendedAttributepage.enterIntoTextBox("DetailNameInputFld", frstVal);
				ExtendedAttributepage.enterIntoTextBox("DetalTypeInputFld",frstTye);
				ExtendedAttributepage.enterIntoTextBox("DetailDecsInputFld", frstDec);
				
				//To click on apply
				ExtendedAttributepage.click("DetailApplyButton");
				
				//To click on save
				ExtendedAttributepage.click("SaveButton");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				
				//To check for the dialog
				ExtendedAttributepage.verifyElementIsDisplayed("SaveConfirmationDialog");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				ExtendedAttributepage.click("ConfirmYesButton");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				
				//To check for the original values are displayed
				//To filter by original name to check no changes are updated
				ExtendedAttributepage.enterIntoTextBox("NameFilterField",frstVal);
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
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
