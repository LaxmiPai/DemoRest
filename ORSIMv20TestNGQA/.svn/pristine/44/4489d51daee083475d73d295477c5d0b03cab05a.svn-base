//Author : shhg

package com.oracle.sim.testcases.ticketFilter;

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

public class TicketFilterUI {
	
	public static Logger logger=Logger.getLogger(TicketFilterUI.class.getName());
	public static PropertyReader propReader = new PropertyReader();
	PageFactory pageFactory = new PageFactory();
	
	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage RoleMaintenancePage;
	SimBasePage ticketFilterPage;
		
	@BeforeClass()
	public void setup(ITestContext context) throws Exception{
		logger.info("TestCase Name: "+logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
		LoginPage=pageFactory.initialize("LoginPage");
		HomePage=pageFactory.initialize("HomePage");
		RoleMaintenancePage=pageFactory.initialize("RoleMaintenancePage");
		ticketFilterPage=pageFactory.initialize("ticketFilterPage");
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username", LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password", LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");
		HomePage.verifyTextValue("Title", HomePage.getProperty("Title"));
		HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
		HomePage.storeIdCheck();
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.click("Navigation");
	}
	
		@DataProvider (name="ticketFilterAccessTestData")
		public Object[][] getticketFilterAccessTestData() throws Exception{
			Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("ticketFilterTestData"), "Permission");
			return testObjArray;
		}
		
		@Test(dataProvider="ticketFilterAccessTestData", priority=1)
		public void verifyRole(Map<String,String> map) throws Exception{
		//Navigating to role maintenance page
		logger.info("Method Name: verifyRole");
		HomePage.click("Security");
		HomePage.click("RoleMaintenance");
			
		//Verifying RoleMaintenance Page Title
     	RoleMaintenancePage.explicitWaitForElementToBeClickable("Title");
		RoleMaintenancePage.verifyTextValue("Title",map.get("Title"));
			
		//Verifying a user permission
		String roleName=propReader.getApplicationproperty("UserRole");
		logger.info(roleName);
		RoleMaintenancePage.verifyUserRole(roleName, map.get("AccessTicketFilter"), map.get("AssignedDataNo"));
		
		}	
			
		@DataProvider (name="ticketFilterSearchTestData")
		public Object[][] getticketFilterSearchTestData() throws Exception{
			Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("ticketFilterTestData"), "FilterScreen");
			return testObjArray;
		}	
		
		@Test(dataProvider = "ticketFilterSearchTestData", priority=2)
		public void verifyTicketFilterSearch(Map<String, String> map) throws Exception {
			
			logger.info("Method Name: verifyTicketFilterSearch");
			ticketFilterPage.explicitWaitForElementToBeClickable("InventoryMgmt");
			ticketFilterPage.click("InventoryMgmt");
			ticketFilterPage.explicitWaitForElementToBeClickable("TicketMenu");
			ticketFilterPage.click("TicketMenu");
			ticketFilterPage.verifyTextValue("SearchHeading", map.get("SearchHeader"));
			ticketFilterPage.verifyTextValue("FromPrintD", map.get("FromPrintDate"));
			ticketFilterPage.verifyTextValue("ToPrintD", map.get("ToPrintDate"));
			ticketFilterPage.verifyTextValue("FromActiveD", map.get("FromActiveDate"));
			ticketFilterPage.verifyTextValue("ToActiveD", map.get("ToActiveDate"));
			ticketFilterPage.verifyTextValue("FromEndD", map.get("FromEndDate"));
			ticketFilterPage.verifyTextValue("ToEndD", map.get("ToEndDate"));
			ticketFilterPage.verifyTextValue("Item", map.get("Item"));
			ticketFilterPage.verifyTextValue("Dept", map.get("Dept"));
			ticketFilterPage.verifyTextValue("Class", map.get("Class"));
			ticketFilterPage.verifyTextValue("Sub-Class", map.get("Sub-Class"));
			ticketFilterPage.verifyTextValue("FormatType", map.get("FormatType"));
			ticketFilterPage.verifyTextValue("Format", map.get("Format"));
			ticketFilterPage.verifyTextValue("OriginType", map.get("OriginType"));
			ticketFilterPage.verifyTextValue("PriceType", map.get("PriceType"));
			ticketFilterPage.verifyTextValue("Printed", map.get("Printed"));
			ticketFilterPage.verifyTextValue("User", map.get("User"));
			ticketFilterPage.verifyTextValue("SearchLimit", map.get("SearchLimit"));
			ticketFilterPage.isElementPresent("SearchButton");
			ticketFilterPage.isElementPresent("ResetButton");
			ticketFilterPage.isElementPresent("CancelButton");	
			
		}
		
		
		//Test to verify format type dropdown values and search
		
		@DataProvider (name="ticketFilterDDTestDataFormatType")
		public Object[][] getticketFilterDDTestDataFormatType() throws Exception{
			Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("ticketFilterTestData"), "FormatType");
			return testObjArray;
		}	
		
		@Test(dataProvider = "ticketFilterDDTestDataFormatType", priority=3)
		public void verifyDropDownFormatType(Map<String, String> map) throws Exception{
		
		logger.info("Method Name: verifyDropDownFormatType");
		ticketFilterPage.explicitWaitForElementToBeClickable("SummarySearch");
		ticketFilterPage.click("SummarySearch");
		ticketFilterPage.verifyTextValue("SearchHeading", map.get("SearchHeader"));
		String a=map.get("FormatType");
		ticketFilterPage.explicitWaitForElementToBeClickable("FormatTypeDropdown");
		ticketFilterPage.click("FormatTypeDropdown");
		ticketFilterPage.verifyDropDownValuesWithExcel("FormatTypeDropdownValues",a); 
		ticketFilterPage.click("FormatShelfLabel");
		ticketFilterPage.click("PrintedDropdown");
		ticketFilterPage.click("PintedAll");
		ticketFilterPage.clearElement("FromPrintDValue");
		ticketFilterPage.clearElement("ToPrintDValue");
		ticketFilterPage.clearElement("FromActiveDValue");
		ticketFilterPage.clearElement("ToActiveDValue");
		ticketFilterPage.clearElement("FromEndDValue");
		ticketFilterPage.clearElement("ToEndDValue");
		ticketFilterPage.clearElement("FromPrintedDValue");
		ticketFilterPage.clearElement("ToPrintedDValue");
		ticketFilterPage.click("SearchLimitValue");
		ticketFilterPage.enterIntoTextBox("SearchLimitValue", map.get("SearchLimit"));
		ticketFilterPage.click("SearchButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		ticketFilterPage.verifyTableRowContentsOfColumn("FormatTypeDataIndex", map.get("FormatValue"));

			}

		//Test to verify origin type dropdown values and search
		
		@DataProvider (name="ticketFilterDDTestDataOriginType")
			public Object[][] getticketFilterDDTestDataOriginType() throws Exception{
			Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("ticketFilterTestData"), "OriginType");
	        return testObjArray;
			}	

			@Test(dataProvider = "ticketFilterDDTestDataOriginType", priority=4)
			public void verifyDropDownOriginType(Map<String, String> map) throws Exception{
				
				logger.info("Method Name: verifyDropDownOriginType");
				ticketFilterPage.explicitWaitForElementToBeClickable("SummarySearch");
				ticketFilterPage.click("SummarySearch");
				ticketFilterPage.verifyTextValue("SearchHeading", map.get("SearchHeader"));
				ticketFilterPage.explicitWaitForElementToBeClickable("ResetButton");
				ticketFilterPage.click("ResetButton");
				String DefaultOriginType = ticketFilterPage.getText("OriginTypeDropdown");
				ticketFilterPage.verifyValuesAreEqual(DefaultOriginType, map.get("OriginTypeDropdownDefault"));
				String a=map.get("OriginType");
				ticketFilterPage.explicitWaitForElementToBeClickable("OriginTypeDropdown");
				ticketFilterPage.click("OriginTypeDropdown");
				ticketFilterPage.verifyDropDownValuesWithExcel("OriginTypeDropdownValues",a); 
				ticketFilterPage.click("OriginManual");
				ticketFilterPage.click("PrintedDropdown");
				ticketFilterPage.click("PintedAll");
				ticketFilterPage.clearElement("FromPrintDValue");
				ticketFilterPage.clearElement("ToPrintDValue");
				ticketFilterPage.clearElement("FromActiveDValue");
				ticketFilterPage.clearElement("ToActiveDValue");
				ticketFilterPage.clearElement("FromEndDValue");
				ticketFilterPage.clearElement("ToEndDValue");
				ticketFilterPage.clearElement("FromPrintedDValue");
				ticketFilterPage.clearElement("ToPrintedDValue");
				ticketFilterPage.click("SearchLimitValue");
				ticketFilterPage.enterIntoTextBox("SearchLimitValue", map.get("SearchLimit"));
				ticketFilterPage.click("SearchButton");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
				ticketFilterPage.verifyTableRowContentsOfColumn("OriginTypeDataIndex", map.get("OriginValue"));
			}

			//Test to verify Price Type dropdown values and search
			
			@DataProvider (name="ticketFilterDDTestDataPriceType")
				public Object[][] getticketFilterDDTestDataPriceType() throws Exception{
				Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("ticketFilterTestData"), "PriceType");
		        return testObjArray;
				}	

				@Test(dataProvider = "ticketFilterDDTestDataPriceType", priority=5)
				public void verifyDropDownPriceType(Map<String, String> map) throws Exception{
					
					logger.info("Method Name: verifyDropDownPriceType");
					ticketFilterPage.explicitWaitForElementToBeClickable("SummarySearch");
					ticketFilterPage.click("SummarySearch");
					ticketFilterPage.verifyTextValue("SearchHeading", map.get("SearchHeader"));
					ticketFilterPage.explicitWaitForElementToBeClickable("ResetButton");
					ticketFilterPage.click("ResetButton");
					String DefaultPriceType = ticketFilterPage.getText("PriceTypeDropdown");
					ticketFilterPage.verifyValuesAreEqual(DefaultPriceType, map.get("PriceTypeDropdownDefault"));
					String a=map.get("PriceType");
					ticketFilterPage.explicitWaitForElementToBeClickable("PriceTypeDropdown");
					ticketFilterPage.click("PriceTypeDropdown");
					ticketFilterPage.verifyDropDownValuesWithExcel("PriceTypeDropdownValues",a); 
					ticketFilterPage.click("PricePermanent");
					ticketFilterPage.click("PrintedDropdown");
					ticketFilterPage.click("PintedAll");
					ticketFilterPage.clearElement("FromPrintDValue");
					ticketFilterPage.clearElement("ToPrintDValue");
					ticketFilterPage.clearElement("FromActiveDValue");
					ticketFilterPage.clearElement("ToActiveDValue");
					ticketFilterPage.clearElement("FromEndDValue");
					ticketFilterPage.clearElement("ToEndDValue");
					ticketFilterPage.clearElement("FromPrintedDValue");
					ticketFilterPage.clearElement("ToPrintedDValue");
					ticketFilterPage.click("SearchLimitValue");
					ticketFilterPage.enterIntoTextBox("SearchLimitValue", map.get("SearchLimit"));
					ticketFilterPage.click("SearchButton");
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
					ticketFilterPage.verifyTableRowContentsOfColumn("PriceTypeDataIndex", map.get("PriceValue"));
	

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
		
		

