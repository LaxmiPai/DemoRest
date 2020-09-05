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

public class NewItemSrchFldCrteriaDeprtment {
	public static Logger logger = Logger.getLogger(NewItemSrchFldCrteriaDeprtment.class.getName());
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
			
			@DataProvider(name="NewItemDept")
			public Object[][] getTestDataForItemBasketPermission() throws Exception {
				Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("NewItemsTestData"),
						"NewItemDept");
				return testObjArray;

			}
			
			@Test(dataProvider="NewItemDept",priority=1)
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
			
			@Test(dataProvider="NewItemDept",priority=2)
			public void NwItemUINavigation(Map<String,String> map) throws Exception {
				logger.info("MethodName: NwItemUINavigation");
				//Navigation steps
				HomePage.click("Operations");
				HomePage.click("OperationalViews");
				HomePage.click("NewItem");
				
				//Title Verification
				OperationalPage.explicitWaitForElementToBeClickable("NewItemTitle");
				OperationalPage.verifyTextValue("NewItemTitle",map.get("NewItemTitle"));
				
				//To select Hierarchy option from the search mode drop down
				OperationalPage.selectDropDownValue("SearchModeDropDown",map.get("Value"));
				
				//To verify the search fields are present
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
			
			@Test(dataProvider="NewItemDept",priority=3)
			public void SelectOneModeVerify(Map<String,String> map) throws Exception {
				
				//To select Hierarchy option from the search mode drop down
				OperationalPage.selectDropDownValue("SearchModeDropDown",map.get("Value"));
				
				//To verify if the element is drop down
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
				OperationalPage.verifyElementIsDropdown("SearchFieldDepartmentDropDown");
				
				//To Verify the default value of the department drop down
				OperationalPage.verifyTextValue("SearchFieldDeprtTxtBx", map.get("DfaultValue"));
				
				//To verify the list of values in ascending order
				OperationalPage.click("SearchFieldDepartmentDropDown");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
				OperationalPage.verifyDropDownSortingOrder("SearchDeptDrpDwnValues");
				
				//To verify partial, full,description alone and the numeric for of department field
				OperationalPage.enterIntoTextBox("DepartmentDropDownTxtBx",map.get("PartialDesc"));
				
				//Verify the highlighted value
				OperationalPage.enterIntoTextBox("DepartmentDropDownTxtBx",map.get("Numeric"));
				OperationalPage.verifyElementIsPresent("DrpDwnHilightedOption");
				OperationalPage.enterIntoTextBox("DepartmentDropDownTxtBx",map.get("Desc"));
				OperationalPage.verifyElementIsPresent("DrpDwnHilightedOption");
				OperationalPage.enterIntoTextBox("DepartmentDropDownTxtBx",map.get("Complete"));
				OperationalPage.verifyElementIsPresent("DrpDwnHilightedOption");
				
				//Checking for no results
				int rowCount=OperationalPage.getTableRowCountWitoutRefresh("TableRows", "LastColumnName", "ItemFilter");
				if(rowCount==0) {
					logger.info("No records found for the particular search");
				}
				
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



