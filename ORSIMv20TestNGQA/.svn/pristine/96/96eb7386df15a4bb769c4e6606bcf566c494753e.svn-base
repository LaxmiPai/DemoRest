/* @author lrathnak */
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

public class NewItemUIAndnavigation {
	
	public static Logger logger = Logger.getLogger(NewItemUIAndnavigation.class.getName());
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
			}
			
			@DataProvider(name="NwItemUINavigation")
			public Object[][] getTestDataForItemBasketPermission() throws Exception {
				Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("NewItemsTestData"),
						"NwItemUINavigation");
				return testObjArray;

			}
			
			@Test(dataProvider="NwItemUINavigation",priority=1)
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
			
			@Test(dataProvider="NwItemUINavigation",priority=2)
			public void NwItemUINavigation(Map<String,String> map) throws Exception {
				logger.info("MethodName: NwItemUINavigation");
				//Navigation steps
				HomePage.click("Operations");
				HomePage.click("OperationalViews");
				HomePage.click("NewItem");
				
				//Title Verification
				OperationalPage.explicitWaitForElementToBeClickable("NewItemTitle");
				OperationalPage.verifyTextValue("NewItemTitle",map.get("NewItemTitle"));
				
				//Verification of search button
				OperationalPage.verifyElementIsPresent("SearchButton");
				OperationalPage.verifyElementIsEnabled("SearchButton");
				
				//Verification of reset button
				OperationalPage.verifyElementIsPresent("ResetButton");
				OperationalPage.verifyElementIsEnabled("ResetButton");
				
				//Verification of grid menu 
				OperationalPage.verifyElementIsPresent("ItemGridMenu");
				OperationalPage.verifyElementIsPresent("DescriptionGridMenu");
				OperationalPage.verifyElementIsPresent("DepartmentGridMenu");
				OperationalPage.verifyElementIsPresent("ClassGridMenu");
				OperationalPage.verifyElementIsPresent("SubClassGridMenu");
				OperationalPage.verifyElementIsPresent("PrimaryLocationGridMenu");
				
				
			}
			
			@Test(dataProvider="NwItemUINavigation",priority=3)
			public void VerificationOfSearchMode(Map<String,String> map) throws Exception {
				logger.info("MethodName: VerificationOfSearchMode");
				
				//Hierarchy search mode, search field verification 
				OperationalPage.verifyElementIsPresent("FromDateSearchFldLbl");
				OperationalPage.verifyElementIsPresent("DepartmentSearchFldLbl");
				OperationalPage.verifyElementIsPresent("SearchLmtSearchFldLbl");
				OperationalPage.verifyElementIsPresent("ToDateSearchFldLbl");
				
				//To verify default date equal to current date in from date field
				OperationalPage.verifyDefaultDateWithCurrentDate("ToDateInputFld");
				
				OperationalPage.verifyElementIsPresent("ClassSearchFldLbl");
				OperationalPage.verifyElementIsPresent("SearchModeSearchFldLbl");
				OperationalPage.verifyElementIsPresent("SubClassSearchFldLbl");
				
				//Supplier search mode, search field verification
				OperationalPage.selectDropDownValue("SearchModeDropDown",map.get("Mode"));
				OperationalPage.verifyElementIsPresent("FromDateSearchFldLbl");
				OperationalPage.verifyElementIsPresent("DepartmentSearchFldLbl");
				OperationalPage.verifyElementIsPresent("SearchLmtSearchFldLbl");
				OperationalPage.verifyElementIsPresent("ToDateSearchFldLbl");
				OperationalPage.verifyElementIsPresent("ClassSearchFldLbl");
				OperationalPage.verifyElementIsPresent("SearchModeSearchFldLbl");
				OperationalPage.verifyElementIsPresent("SubClassSearchFldLbl");
				OperationalPage.verifyElementIsPresent("SupplierSearchFldLbl");
				OperationalPage.verifyElementIsPresent("PrimarySupplierSearchFldLbl");
				
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
