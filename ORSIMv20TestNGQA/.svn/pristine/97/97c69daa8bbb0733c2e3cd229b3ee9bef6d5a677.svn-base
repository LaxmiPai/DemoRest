/* @author lrathnak */
package com.oracle.sim.testcases.OperationsOperationalViews;

import java.util.Map;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
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

public class NewItemFilterAndSort {
	
	public static Logger logger = Logger.getLogger(NewItemFilterAndSort.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pagefactory = new PageFactory();

	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage RoleMaintenancePage;
	SimBasePage OperationalPage;
	static WebDriver driver;

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
				
				//DB method
				HomePage.getConnection();
			}
			
			@DataProvider(name="ItemFilterSort")
			public Object[][] getTestDataForItemBasketPermission() throws Exception {
				Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("NewItemsTestData"),
						"ItemFilterSort");
				return testObjArray;

			}
			
			@Test(dataProvider="ItemFilterSort", priority=1)
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
			
			@Test(dataProvider="ItemFilterSort", priority=2)
			public void FilterAndSort(Map<String,String> map) throws Exception {
				
				logger.info("MethodName: FilterAndSort");
				
				//Navigation steps
				HomePage.click("Operations");
				HomePage.click("OperationalViews");
				HomePage.click("NewItem");
				
				//Title Verification
				OperationalPage.explicitWaitForElementToBeClickable("NewItemTitle");
				OperationalPage.verifyTextValue("NewItemTitle",map.get("NewItemTitle"));
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				
				//Getting the count of the record
				OperationalPage.click("SearchButton");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				boolean ErrMsg = OperationalPage.verifyElementIsDisplayed("ErrMsgNoRcrd");
				if(ErrMsg==true) {
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				OperationalPage.click("ErrMasgOkButton");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				OperationalPage.updateDateQuery(map.get("StoreId"),map.get("ItemId"));
				}
				
				else {
				
				//To verify whether there are few rows available.
			int rowCount=OperationalPage.getTableRowCountWitoutRefresh("TableRows", "LastColumnName", "ItemFilter");
			if(rowCount==0) {
				//To chose another date where the records are present,DB method To update the date column
				OperationalPage.updateDateQuery(map.get("StoreId"),map.get("ItemId"));
			}
			
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
				OperationalPage.click("SearchButton");
				
				//To get  table row count after the click of search
				int rowCount1=OperationalPage.getTableRowCountWitoutRefresh("TableRows", "LastColumnName", "ItemFilter");
				if(rowCount1>0) {
					logger.info("The search results has listed the records");
				}
				
				//To check whether the button for ascending order is available in the grid
				OperationalPage.verifyHoverOverValue("ItemColumnSortIcon", map.get("Value"));
				
				//To click on the column sort icon
				OperationalPage.click("ItemColumnSortIcon");
				
				//To verify if the column is sorted in ascending order
				
				if(rowCount1>1) {
				OperationalPage.columnSortingWioutRefrsh("AllDecClmRcds", "ItemGridMenu",map.get("SortingOrder"),map.get("DataType"));
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				}
				
				//To check whether the button for ascending order is available in the grid
				OperationalPage.verifyHoverOverValue("ItemColumnSortIcon", map.get("Value1"));
				
				//To click on the column sort icon
				OperationalPage.click("ItemColumnSortIcon");
				
				//To verify if the column is sorted in ascending order
				if(rowCount1>1) {
				OperationalPage.columnSortingWioutRefrsh("AllDecClmRcds", "ItemGridMenu",map.get("SortingOrder"),map.get("DataType"));
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				}
				
				OperationalPage.click("ResetButton");
				OperationalPage.click("SearchButton");
				
				HomePage.click("Navigation");
				
				//To filter by item
				String Item = OperationalPage.getText("FrstItemID");
				OperationalPage.verifyTableRowUsingColumnValue("FrstItemID", Item);
				
				//To filter by description
				String Description = OperationalPage.getText("FrstDescrColumn");
				OperationalPage.verifyTableRowUsingColumnValue("FrstDescrColumn", Description);
				
				
				//To filter by department
				String Dept=OperationalPage.getText("FrstDepartmntClm");
				OperationalPage.verifyTableRowUsingColumnValue("FrstDepartmntClm", Dept);
				
				
				//To filter by class
				String Cls=OperationalPage.getText("ClassFrstClm");
				OperationalPage.verifyTableRowUsingColumnValue("ClassFrstClm", Cls);
				
				
				//To filter by subclass
				String SubCls=OperationalPage.getText("SubClsFrstClm");
				OperationalPage.verifyTableRowUsingColumnValue("SubClsFrstClm", SubCls);
				
				
				//To filter By Primary Location
				String PrimarLoc=OperationalPage.getText("PrimaryLocFrstClm");
				if(!(PrimarLoc.equalsIgnoreCase(null))){
				OperationalPage.verifyTableRowUsingColumnValue("PrimaryLocFrstClm", PrimarLoc);
				
				}else {
					logger.info("The primary loctaion is null");
				}
				
				}
				
				//To filter by available SOH
				String AvblSoh=OperationalPage.getText("AvlbSOhFrstClm");
				OperationalPage.verifyTableRowUsingColumnValue("AvlbSOhFrstClm", AvblSoh);
				
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

