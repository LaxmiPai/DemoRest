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

public class NewItemSearchButton {
	
	public static Logger logger = Logger.getLogger(NewItemSearchButton.class.getName());
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
			
			@DataProvider(name="NewItemSearchButton")
			public Object[][] getTestDataForItemBasketPermission() throws Exception {
				Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("NewItemsTestData"),
						"NewItemSearchButton");
				return testObjArray;

			}
			
			@Test(dataProvider="NewItemSearchButton", priority=1)
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
			
			@Test(dataProvider="NewItemSearchButton",priority=2)
			public void InvalidSrch(Map<String,String> map) throws Exception {
				logger.info("MethodName: InvalidSrc");
				//Navigation steps
				HomePage.click("Operations");
				HomePage.click("OperationalViews");
				HomePage.click("NewItem");
				
				//Title Verification
				OperationalPage.explicitWaitForElementToBeClickable("NewItemTitle");
				OperationalPage.verifyTextValue("NewItemTitle",map.get("NewItemTitle"));
				
				//Getting the count of the record
				OperationalPage.click("SearchButton");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				boolean ErrMsg = OperationalPage.verifyElementIsDisplayed("ErrMsgNoRcrd");
				if(ErrMsg==true) {
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				OperationalPage.click("ErrMasgOkButton");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				}
				
				//To verify whether there are few rows available.
				int rowCount=OperationalPage.getTableRowCountWitoutRefresh("TableRows", "LastColumnName", "ItemFilter");
				if(rowCount==0) {
					//To chose another date where the records are present,DB method To update the date column
					OperationalPage.updateDateQuery(map.get("StoreId"),map.get("ItemId"));
				}
				
				//To check whether the search button is present and enabled
				OperationalPage.verifyElementIsPresent("SearchButton");
				OperationalPage.verifyElementIsEnabled("SearchButton");
				
				//Search for an incorrect value
				String CurrentDate=OperationalPage.getPresentDate();
				OperationalPage.enterIntoTextBox("FromDateInputFld", CurrentDate);
				
				//Click on search button
				OperationalPage.click("SearchButton");
				
				//To inspect the error pop-up
				OperationalPage.verifyElementIsDisplayed("ErrMsgNoRcrd");
				OperationalPage.click("ErrMasgOkButton");
				
				
				//Title Verification
				OperationalPage.explicitWaitForElementToBeClickable("NewItemTitle");
				OperationalPage.verifyTextValue("NewItemTitle",map.get("NewItemTitle"));
				
			}
			
			@Test(dataProvider="NewItemSearchButton",priority=3)
			public void EnterAValueScrch(Map<String,String> map) throws Exception {
				
				//To enter a date in the To Date field
				OperationalPage.enterIntoTextBox("FromDateInputFld",map.get("Date"));
				
				//Click on search
				OperationalPage.click("SearchButton");
				
				//To verify the search results
				int rowCount=OperationalPage.getTableRowCountWitoutRefresh("TableRows", "LastColumnName", "ItemFilter");
				if(rowCount>0) {
					logger.info("The search results have listed the records");
				}
				
				//To refresh the browser
				OperationalPage.RefreshWebPage();
				
			}
			
			@Test(dataProvider="NewItemSearchButton",priority=4)
			public void DefaultValueScrch(Map<String,String> map) throws Exception {
				
				//Click on search button with default values
				OperationalPage.click("SearchButton");
				
				
				//To refresh the browser to load the data 
				OperationalPage.RefreshWebPage();
				
				
				//To verify the search results
				int rowCount=OperationalPage.getTableRowCountWitoutRefresh("TableRows", "LastColumnName", "ItemFilter");
				if(rowCount<=50) {
					logger.info("The search results have listed the records");
				}
				if(rowCount==0){
					logger.info("The search do not have any records to show");
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

