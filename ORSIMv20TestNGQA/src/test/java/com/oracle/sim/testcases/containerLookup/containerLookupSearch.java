//Author : shhg

package com.oracle.sim.testcases.containerLookup;

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

public class containerLookupSearch {

	public static Logger logger=Logger.getLogger(containerLookupSearch.class.getName());
	public static PropertyReader propReader = new PropertyReader();
	PageFactory pageFactory = new PageFactory();
	
	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage RoleMaintenancePage;
	SimBasePage containerLookupPage;
	
	@BeforeClass()
	public void setup(ITestContext context) throws Exception {
		logger.info("Testcase Name: "+logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
		LoginPage=pageFactory.initialize("LoginPage");
		HomePage=pageFactory.initialize("HomePage");
		RoleMaintenancePage=pageFactory.initialize("RoleMaintenancePage");
		containerLookupPage=pageFactory.initialize("containerLookupPage");
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username",LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password", LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");
		HomePage.verifyTextValue("Title", HomePage.getProperty("Title"));
		HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
		HomePage.storeIdCheck();
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.click("Navigation");
}
	
	@DataProvider (name ="containerLookupRoleTestData")
	public Object[][] getcontainerLookupRoleTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("containerLookupTestData"), "PermissionData");
		return testObjArray;
}
	
	@Test(dataProvider="containerLookupRoleTestData", priority=1)
	public void verifyRole(Map<String,String> map) throws Exception {
		//Navigating to role maintenance page
		logger.info("Method Name: verifyRole");
		HomePage.click("SecurityPage");
		HomePage.click("RoleMaintenancePage");
		
		//Verifying RoleMaintenance Page Title
		RoleMaintenancePage.explicitWaitForElementToBeClickable("Title");
		RoleMaintenancePage.verifyTextValue("Title",map.get("Title"));
		
		//Verifying a user permission
		String roleName=propReader.getApplicationproperty("UserRole");
		logger.info(roleName);
		RoleMaintenancePage.verifyUserRole(roleName, map.get("AccessContainerLookup"), map.get("AssignedDataNo"));
		
	}
	
	@DataProvider (name ="containerLookupSearchTestData")
	public Object[][] containerLookupSearchTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("containerLookupTestData"), "ContainerSearchUI");
		return testObjArray;
	}	
	
	@Test(dataProvider = "containerLookupSearchTestData", priority =2)
	public void verifyContainerLookupSearch(Map<String, String> map) throws Exception {
		
		logger.info("Method Name: verifyContainerLookupSearch");
		containerLookupPage.click("LookupsMenu");
		containerLookupPage.click("ContainerLookup");
		containerLookupPage.click("Search");
		
		//Verifying search popup fields
		containerLookupPage.verifyTextValue("SearchPopupHeading", map.get("SearchPopupHeader"));
		containerLookupPage.verifyTextValue("IDField", map.get("ID"));
		containerLookupPage.verifyTextValue("ContainerIDField", map.get("ContainerID"));
		containerLookupPage.verifyTextValue("ASNField", map.get("ASN"));
		containerLookupPage.verifyTextValue("StatusField", map.get("Status"));
		String DefaultType = containerLookupPage.getText("TypeDropDown");
		containerLookupPage.verifyValuesAreEqual(DefaultType, map.get("TypeDropdownDefault"));
		String b=map.get("TypeDropdown");
		containerLookupPage.explicitWaitForElementToBeClickable("TypeDropDown");
		containerLookupPage.click("TypeDropDown");
		containerLookupPage.verifyDropDownValuesWithExcel("TypeDropDownValues",b);
		String DefaultStatus = containerLookupPage.getText("StatusDropdown");
		containerLookupPage.verifyValuesAreEqual(DefaultStatus, map.get("StatusDropdownDefault"));
		String a=map.get("StatusDropdown");
		containerLookupPage.explicitWaitForElementToBeClickable("StatusDropdown");
		containerLookupPage.click("StatusDropdown");
		containerLookupPage.verifyDropDownValuesWithExcel("StatusDropdownValues",a); 
		containerLookupPage.click("ASNInput");
		containerLookupPage.verifyTextValue("ItemField", map.get("Item"));
		containerLookupPage.verifyTextValue("TypeField", map.get("Type"));
		containerLookupPage.verifyTextValue("StoreField", map.get("Store"));
		String DefaultStore = containerLookupPage.getText("StoreDropdown");
		containerLookupPage.verifyValuesAreEqual(DefaultStore, map.get("StoreDropdownDefault"));
		containerLookupPage.click("StoreDropdown");
		containerLookupPage.click("AllStoresValue");
		containerLookupPage.verifyTextValue("SearchLimitField", map.get("SearchLimit"));
		//containerLookupPage.explicitWaitForElementToBeClickable("SearchLimitEntry");
		//containerLookupPage.enterIntoTextBox("SearchLimitEntry", map.get("SearchLimitValue"));
		containerLookupPage.verifyElementIsPresent("ResetButton");
		containerLookupPage.verifyElementIsPresent("CancelButton");
		containerLookupPage.click("PopupSubmitButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
	}
	
		@DataProvider (name ="containerLookupUISummaryTestData")
		public Object[][] getcontainerLookupUISummaryTestData() throws Exception{
			Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("containerLookupTestData"), "ContainerSummary");
			return testObjArray;
		}	
		
		@Test(dataProvider = "containerLookupUISummaryTestData", priority =3)
		public void verifyContainerLookupUISummary(Map<String, String> map) throws Exception {
		
		//Sorting of ID column for 13 records
		logger.info("Method Name: verifyContainerLookupUISummary");
		containerLookupPage.click("Search");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		containerLookupPage.verifyTextValue("SearchPopupHeading", map.get("SearchPopupHeader"));
		containerLookupPage.explicitWaitForElementToBeClickable("StoreDropdown");
		containerLookupPage.click("StoreDropdown");
		containerLookupPage.click("AllStoresValue");
		containerLookupPage.click("SearchLimitEntry");
		containerLookupPage.clearElement("SearchLimitEntry");
		containerLookupPage.enterIntoTextBox("SearchLimitEntry", map.get("SearchLimitSort"));
		containerLookupPage.click("PopupSubmitButton");
		
		//To filter the data in the ID column in ascending order
		containerLookupPage.explicitWaitForElementToBeClickable("IDColumn");
		containerLookupPage.click("IDColumn");
		containerLookupPage.implicitWait();
		containerLookupPage.click("IDColumn");
		containerLookupPage.implicitWait();
		containerLookupPage.click("IDColumn");
		containerLookupPage.implicitWait();
//		containerLookupPage.refreshPage();
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
//		containerLookupPage.scrollToViewElement("IDColumn");
//		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		containerLookupPage.explicitWaitForElementToBeClickable("Navigation");
		containerLookupPage.click("Navigation");
		containerLookupPage.intSorting("IDColumnValues", "IDColumn", "ascending");
		
		//Verifying grid view menu options
		containerLookupPage.explicitWaitForElementToBeClickable("GridViewMenu");
		containerLookupPage.click("GridViewMenu");
		containerLookupPage.explicitWaitForElementToBeClickable("ResetView");
		containerLookupPage.verifyTextValue("ResetView",map.get("ResetView"));
		containerLookupPage.verifyTextValue("ColumnFilter",map.get("ColumnFilter"));
		containerLookupPage.verifyTextValue("ExportToCsv",map.get("ExportToCsv"));
		containerLookupPage.click("GridViewMenu");
		
		//Verifying summary table columns
		containerLookupPage.verifyTextValue("IDColumn",map.get("ID"));
		containerLookupPage.verifyTextValue("ContainerIDColumn",map.get("ContainerID"));
		containerLookupPage.verifyTextValue("StatusColumn",map.get("Status"));
		containerLookupPage.verifyTextValue("DirectionColumn",map.get("Direction"));
		containerLookupPage.verifyTextValue("SourceTypeColumn",map.get("SourceType"));
		containerLookupPage.verifyTextValue("SourceColumn",map.get("Source"));
		containerLookupPage.verifyTextValue("DestinationTypeColumn",map.get("DestinationType"));
//		containerLookupPage.explicitWaitForElementToBeClickable("Navigation");
//		containerLookupPage.click("Navigation");
		containerLookupPage.verifyTextValue("DestinationColumn",map.get("Destination"));
		containerLookupPage.verifyTextValue("DispatchDateColumn",map.get("DispatchDate"));
		containerLookupPage.verifyTextValue("SKUColumn",map.get("SKU"));
		containerLookupPage.verifyTextValue("CustomerOrderColumn",map.get("CustomerOrder"));
		
	}
		@DataProvider (name="containerLookupUIDetail")
		public Object[][] getcontainerLookupUIDetail() throws Exception{
			Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("containerLookupTestData"), "ContainerDetailIn");
			return testObjArray;
		}
		
		@Test(dataProvider="containerLookupUIDetail", priority=4, dependsOnMethods = {"verifyContainerLookupUISummary"})
		public void verifyContainerLookupUIDetailIn(Map<String,String> map) throws Exception{
			logger.info("Method Name: verifyContainerLookupUIDetailIn");
		
			containerLookupPage.click("Search");
			containerLookupPage.explicitWaitForElementToBeClickable("SearchPopupHeading");
			containerLookupPage.verifyTextValue("SearchPopupHeading", map.get("SearchPopupHeader"));
			containerLookupPage.click("SearchLimitEntry");
			containerLookupPage.clearElement("SearchLimitEntry");
			containerLookupPage.enterIntoTextBox("SearchLimitEntry", map.get("SearchLimitDetail"));
			containerLookupPage.click("PopupSubmitButton");
			
			//Click on first record and verify detail screen items
			containerLookupPage.click("DirectionFilter");
			containerLookupPage.enterIntoTextBox("DirectionFilter", map.get("DirectionIn"));
			containerLookupPage.explicitWaitForElementToBeClickable("IDInLink");
			containerLookupPage.click("IDInLink");
			
			//Header details verification
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
			containerLookupPage.verifyTextValue("InDetailHeading", map.get("DetailHeading"));
			containerLookupPage.verifyElementIsPresent("BackButton");
			containerLookupPage.verifyElementIsPresent("Infobutton");
			containerLookupPage.verifyTextValue("ID", map.get("ID"));
			containerLookupPage.verifyTextValue("ASN", map.get("ASN"));
			containerLookupPage.verifyTextValue("ContainerID", map.get("ContainerID"));
			containerLookupPage.verifyTextValue("Status", map.get("Status"));
			containerLookupPage.verifyElementIsPresent("ExtAttbutton");
			containerLookupPage.verifyElementIsPresent("UINButton");
			containerLookupPage.explicitWaitForElementToBeClickable("GridViewMenu");
			containerLookupPage.click("GridViewMenu");
			containerLookupPage.explicitWaitForElementToBeClickable("ResetView");
			containerLookupPage.verifyTextValue("ResetView",map.get("ResetView"));
			containerLookupPage.verifyTextValue("ColumnFilter",map.get("ColumnFilter"));
			containerLookupPage.verifyTextValue("ExportToCsv",map.get("ExportToCsv"));
			containerLookupPage.click("GridViewMenu");
			
			//Info popup verification
			containerLookupPage.explicitWaitForElementToBeClickable("Infobutton");
			containerLookupPage.click("Infobutton");
			containerLookupPage.verifyTextValue("InfoHeader",map.get("InfoHeading"));
			containerLookupPage.verifyTextValue("DeliveryID", map.get("DeliveryID"));
			containerLookupPage.verifyTextValue("ExpectedDate", map.get("ExpectedDate"));
			containerLookupPage.verifyTextValue("Source", map.get("Source"));
			containerLookupPage.verifyTextValue("SourceType", map.get("SourceType"));
			containerLookupPage.verifyTextValue("Destination", map.get("Destination"));
			containerLookupPage.verifyTextValue("DestinationType", map.get("DestinationType"));
			containerLookupPage.verifyTextValue("UpdateDate", map.get("UpdateDate"));
			containerLookupPage.verifyTextValue("UpdateUser", map.get("UpdateUser"));
			containerLookupPage.verifyTextValue("AdjContainer", map.get("AdjContainer"));
			containerLookupPage.verifyTextValue("ReceiveOnSF", map.get("ReceiveOnSF"));
			containerLookupPage.verifyTextValue("DamageReason", map.get("DamageReason"));
			containerLookupPage.verifyTextValue("SSCC", map.get("SSCC"));
			containerLookupPage.verifyTextValue("TrackingID", map.get("TrackingID"));
			containerLookupPage.isElementPresent("CloseButton");
			containerLookupPage.click("CloseButton");
			
			//Column values verification
			containerLookupPage.verifyTextValue("Item",map.get("Item"));
			containerLookupPage.verifyTextValue("Desc",map.get("ItemDescription"));
			containerLookupPage.verifyTextValue("UOM",map.get("UOM"));
			containerLookupPage.verifyTextValue("PackSize",map.get("PackSize"));
			containerLookupPage.verifyTextValue("QtyReceived",map.get("Received"));
			containerLookupPage.verifyTextValue("QtyDamaged",map.get("Damaged"));
			containerLookupPage.verifyTextValue("QtyExpected",map.get("Expected"));
			containerLookupPage.verifyTextValue("Variance",map.get("Variance"));
			containerLookupPage.click("BackButton");
		}
		
		@DataProvider (name="containerLookupUIDetailOut")
		public Object[][] getcontainerLookupUIDetailOut() throws Exception{
			Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("containerLookupTestData"), "ContainerDetailOut");
			return testObjArray;
		}
		
		@Test(dataProvider="containerLookupUIDetailOut", priority=5, dependsOnMethods = {"verifyContainerLookupUIDetailIn"})
		public void verifyContainerLookupUIDetailOut(Map<String,String> map) throws Exception{
			logger.info("Method Name: verifyContainerLookupUIDetailOut");
			
			//Click on first record and verify detail screen items
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("High")));
			containerLookupPage.explicitWaitForElementToBeClickable("DirectionFilter");
			containerLookupPage.click("DirectionFilter");
			containerLookupPage.enterIntoTextBox("DirectionFilter", map.get("DirectionOut"));
			containerLookupPage.explicitWaitForElementToBeClickable("IDInLink");
			containerLookupPage.click("IDInLink");
			
			//Header details verification
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
			containerLookupPage.verifyTextValue("InDetailHeading", map.get("DetailHeading"));
			containerLookupPage.verifyElementIsPresent("BackButton");
			containerLookupPage.verifyElementIsPresent("Infobutton");
			containerLookupPage.verifyTextValue("ID", map.get("ID"));
			containerLookupPage.verifyTextValue("ASN", map.get("ASN"));
			containerLookupPage.verifyTextValue("ContainerID", map.get("ContainerID"));
			containerLookupPage.verifyTextValue("Status", map.get("Status"));
			containerLookupPage.verifyElementIsPresent("ExtAttbutton");
			containerLookupPage.verifyElementIsPresent("UINButton");
			containerLookupPage.explicitWaitForElementToBeClickable("GridViewMenu");
			containerLookupPage.click("GridViewMenu");
			containerLookupPage.explicitWaitForElementToBeClickable("ResetView");
			containerLookupPage.verifyTextValue("ResetView",map.get("ResetView"));
			containerLookupPage.verifyTextValue("ColumnFilter",map.get("ColumnFilter"));
			containerLookupPage.verifyTextValue("ExportToCsv",map.get("ExportToCsv"));
			containerLookupPage.click("GridViewMenu");
			
			//Column heading verification
			containerLookupPage.verifyTextValue("Item",map.get("Item"));
			containerLookupPage.verifyTextValue("Desc",map.get("ItemDescription"));
			containerLookupPage.verifyTextValue("UOM",map.get("UOM"));
			containerLookupPage.verifyTextValue("PackSize",map.get("PackSize"));
			containerLookupPage.verifyTextValue("QtyShipped",map.get("Shipped"));
		
//			Info popup verification
			containerLookupPage.explicitWaitForElementToBeClickable("Infobutton");
			containerLookupPage.click("Infobutton");
			containerLookupPage.verifyTextValue("InfoHeader",map.get("InfoHeading"));
			containerLookupPage.verifyTextValue("DeliveryID", map.get("ShipmentID"));
			containerLookupPage.verifyTextValue("ExpectedDate", map.get("ExpectedDate"));
			containerLookupPage.verifyTextValue("Source", map.get("Source"));
			containerLookupPage.verifyTextValue("SourceType", map.get("SourceType"));
			containerLookupPage.verifyTextValue("Destination", map.get("Destination"));
			containerLookupPage.verifyTextValue("DestinationType", map.get("DestinationType"));
			containerLookupPage.verifyTextValue("UpdateDate", map.get("UpdateDate"));
			containerLookupPage.verifyTextValue("UpdateUser", map.get("UpdateUser"));
			containerLookupPage.verifyTextValue("TrackingID", map.get("TrackingID"));
			containerLookupPage.isElementPresent("CloseButton");
			containerLookupPage.click("CloseButton");			
				
					
	}
	

		@AfterClass()
		public void tearDown() {

			try {
				logger.info("After Test: Logging out");
				HomePage.click("UserMenu");
				HomePage.explicitWaitForVisibility("Logout");
				HomePage.click("Logout");
				HomePage.explicitWaitForVisibility("Yes");
				HomePage.click("Yes");

			}

			finally {

				SIMWebdriverBase.close();


			}
		}

}

	