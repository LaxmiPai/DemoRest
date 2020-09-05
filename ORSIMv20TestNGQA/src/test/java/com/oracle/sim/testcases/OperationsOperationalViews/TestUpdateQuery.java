package com.oracle.sim.testcases.OperationsOperationalViews;

import java.util.Map;
import java.util.logging.Logger;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.oracle.core.dataProvider.ExcelReader;
import com.oracle.core.utils.PropertyReader;
import com.oracle.sim.pageFactory.PageFactory;
import com.oracle.sim.pages.Base.SimBasePage;
import com.oracle.sim.utils.SIMWebdriverBase;

public class TestUpdateQuery {
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
				
				//DB method
				HomePage.getConnection();
			}
			
			@DataProvider(name="TestUpdateQuery")
			public Object[][] getTestDataForItemBasketPermission() throws Exception {
				Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("NewItemsTestData"),
						"TestUpdateQuery");
				return testObjArray;

			}
			
			@Test(dataProvider="TestUpdateQuery",priority=2)
			public void NwItemIDValidation(Map<String,String> map) throws Exception {
				logger.info("MethodName: NwItemIDValidation");
				
				//Navigation steps
				HomePage.click("Navigation");
				HomePage.click("Operations");
				HomePage.click("OperationalViews");
				HomePage.click("NewItem");
				
				//Title Verification
				OperationalPage.explicitWaitForElementToBeClickable("NewItemTitle");
				OperationalPage.verifyTextValue("NewItemTitle",map.get("NewItemTitle"));
				
				
				//Sometimes the records for the page are not loaded
				OperationalPage.RefreshWebPage();
				
				Thread.sleep(5000);
				OperationalPage.enterIntoTextBox("FromDateInputFld",map.get("Date"));
				OperationalPage.click("SearchButton");
				Thread.sleep(5000);
				OperationalPage.click("ErrMasgOkButton");
				
				//To verify whether there are few rows available.
			int rowCount=OperationalPage.getTableRowCount("TableRows", "LastColumnName", "ItemFilter");
			if(rowCount==0) {
				//To chose another date where the records are present,DB method To update the date column
				OperationalPage.updateDateQuery(map.get("StoreId"),map.get("ItemId"));
			}
			
			OperationalPage.click("ResetButton");
			OperationalPage.click("SearchButton");
			
			//To count the number of rows
			int rowCount1=OperationalPage.getTableRowCount("TableRows", "LastColumnName", "ItemFilter");
			System.out.println(rowCount1);
			
			
			}	
}
