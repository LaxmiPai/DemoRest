/* @author lrathnak */
package com.oracle.sim.testcases.OperationsOperationalViews;

import java.sql.ResultSet;
import java.util.Map;
import java.util.logging.Logger;

import org.testng.Assert;
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

public class NewItemPrimarySupplierCheck {

	public static Logger logger = Logger.getLogger(NewItemPrimarySupplierCheck.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pagefactory = new PageFactory();

	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage RoleMaintenancePage;
	SimBasePage OperationalPage;
	SoftAssert softAssertion = new SoftAssert();

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
				
				
				//DB connection 
				OperationalPage.getConnection();
			}
			
			@DataProvider(name="NewItemPrmSup")
			public Object[][] getTestDataForItemBasketPermission() throws Exception {
				Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("NewItemsTestData"),
						"NewItemPrmSup");
				return testObjArray;

			}
			
			@Test(dataProvider="NewItemPrmSup", priority=1)
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
			
			@Test(dataProvider="NewItemPrmSup", priority=2)
			public void VerificationPrmrySupplier(Map<String,String> map) throws Exception {
				logger.info("MethodName: VerificationPrmrySupplier");
				//Navigation steps
				HomePage.click("Operations");
				HomePage.click("OperationalViews");
				HomePage.click("NewItem");
				
				//Title Verification
				OperationalPage.explicitWaitForElementToBeClickable("NewItemTitle");
				OperationalPage.verifyTextValue("NewItemTitle",map.get("NewItemTitle"));
				
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
				
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
				
				
				//To verify whether there are few rows available.
				int rowCount=OperationalPage.getTableRowCountWitoutRefresh("TableRows", "LastColumnName", "ItemFilter");
				if(rowCount==0) {
					//To chose another date where the records are present,DB method To update the date column
					OperationalPage.updateDateQuery(map.get("StoreId"),map.get("ItemId"));
				}
				
				//To select the supplier mode
				OperationalPage.selectDropDownValue("SearchModeDropDown",map.get("Mode"));
				
				//To verify if the field is a switch
				OperationalPage.verifyElementIsPresent("PrimarySupplierSwitch");
				
				//To verify if the switch is selected
				OperationalPage.verifyCheckBoxIsNotSelected("PrimarySupplierSwitch");
				
				//Enter a supplier id in the supplier id field
				OperationalPage.enterIntoTextBox("SupplierInputFld", map.get("SupplierId"));
				
				//To click on the search button
				OperationalPage.click("SearchButton");
				
				//To get  table row count after the click of search
				int rowCount1=OperationalPage.getTableRowCountWitoutRefresh("TableRows", "LastColumnName", "ItemFilter");
				if(rowCount1>0) {
					logger.info("The search results has listed the records");
				}
			}
			
			@Test(dataProvider="NewItemPrmSup", priority=3)
			public void VerificationByprmrySup(Map<String,String>map) throws Exception{
				
				//Click on the Primary supplier link to verify the search result
				OperationalPage.click("PrimarySupplierSwitch");
				
				//Enter a supplier id in the supplier id field
				OperationalPage.enterIntoTextBox("SupplierInputFld", map.get("SupplierId"));
				
				int rowCount1=OperationalPage.getTableRowCountWitoutRefresh("TableRows", "LastColumnName", "ItemFilter");
				if(rowCount1==0) {
					logger.info("The search results has no records to  list");
				}
			
				else {
				//To fetch the first item id listed,DB method used
				String itemId = OperationalPage.getText("FrstItemID");
				int itemid=Integer.parseInt(itemId);
				
				String query="Select supplier_id  from Supplier_Item"
						+ " Where Is_Primary= 'Y' AND item_id="+itemid;
				ResultSet Res = OperationalPage.executeSelect(query);
				if(Res.next()) {
					if(Res.equals(null)) {
						logger.info("The items do no have the mentioned supplier as the primary supplier");
					}
				int supplier_id=Res.getInt("supplier_id");
				String actulSup=map.get("SupplierId");
				int acts=Integer.parseInt(actulSup);
				softAssertion.assertEquals(supplier_id, acts); 
				//check for no records
			}
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
