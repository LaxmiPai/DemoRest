package com.oracle.sim.testcases.Ticket;
/**
 * * @author dsorthiy
 *
 */
import java.util.Map;
import java.util.Random;
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


public class TicketListGridFieldsVerification {

	
	public static Logger logger = Logger.getLogger(TicketListGridFieldsVerification.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pageFactory = new PageFactory();
	Random random = new Random();
	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage TicketPage;
	SimBasePage RoleMaintenancePage;
	@BeforeClass()
	public void setup(ITestContext context) throws Exception {
		
		logger.info("TestCase Name: " +logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
		LoginPage = pageFactory.initialize("LoginPage");
		HomePage = pageFactory.initialize("HomePage");
		TicketPage = pageFactory.initialize("TicketPage");
		RoleMaintenancePage = pageFactory.initialize("RoleMaintenancePage");
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username", LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password", LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");
		HomePage.verifyTextValue("Title", HomePage.getProperty("Title"));
		HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
		HomePage.storeIdCheck();

		
	}
	@DataProvider(name = "RoleMaintenanceTestData")
	public Object[][] getRoleMaintenanceTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("SecurityTestData"),
				"RoleMaintenance");
		return testObjArray;
	}
	@Test(dataProvider="RoleMaintenanceTestData", priority=1)
	public void verifyRole(Map<String,String> map) throws Exception {
		//Navigating to role maintenance page
		logger.info("Method Name: verifyRole");
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.storeIdCheck();
		HomePage.click("Navigation");
		HomePage.click("Security");
		HomePage.click("RoleMaintenance");

		//Verifying RoleMaintenance Page Title
		RoleMaintenancePage.explicitWaitForElementToBeClickable("Title");
		RoleMaintenancePage.verifyTextValue("Title",map.get("Title"));

		//Granting the user permission
		String userRole=propReader.getApplicationproperty("UserRole");
		RoleMaintenancePage.verifyUserRole(userRole, map.get("AccessTicketList"), map.get("AssignedDataNo"));	
	}

	//DataProvider for Security Permission of Ticket  
		@DataProvider(name = "TicketListScreenTestData")
		public Object[][] getTestDataForUI() throws Exception {
			Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("TicketListTestData"),"GridFieldsVerification");
			return testObjArray;
		}
		//verify the Security Permission of Ticket 
				@Test(dataProvider = "TicketListScreenTestData", priority = 2)
				public void verifyGridFieldsTicketList(Map<String, String> map) throws Exception {
					logger.info("TestCase VerifyGridFieldsTIcketList Started..! ");
					HomePage.click("InventoryManagement");
					HomePage.click("TicketPage");
					TicketPage.click("NavigationSearchBar");
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
					//verify the Title of Ticket Search Criteria
					TicketPage.explicitWaitForVisibility("TicketSearchCriteriaTitle");
					TicketPage.verifyTextValue("TicketSearchCriteriaTitle", map.get("SearchCriteriaTitle"));
					
					//Enter the From Print Date and To Print Date 
					TicketPage.clearElement("FromPrintDateTextBox");
					TicketPage.clearElement("ToPrintDateTextBox");
					TicketPage.clearElement("FromActiveDateTextBox");
					TicketPage.clearElement("ToActiveDateTextBox");
					TicketPage.clearElement("FromEndDateTextBox");
					TicketPage.clearElement("ToEndDateTextBox");
					TicketPage.click("SearchButton");
					
					//Verify the Title of Ticket List Screen
					TicketPage.explicitWaitForVisibility("TicketListTitle");
					TicketPage.verifyTextValue("TicketListTitle", map.get("TicketListTitle"));
					
					TicketPage.verifyHoverOverValue("ItemHeader", map.get("Item"));
					TicketPage.verifyHoverOverValue("DescriptionHeader", map.get("Description"));
					TicketPage.verifyHoverOverValue("FormatTypeHeader",map.get("FormatType"));
					TicketPage.verifyHoverOverValue("OriginTypeHeader", map.get("OriginType"));
					TicketPage.verifyHoverOverValue("PrintQuantityHeader", map.get("PrintQuantity"));
					TicketPage.verifyHoverOverValue("PriceTypeHeader",map.get("PriceType"));
					TicketPage.verifyHoverOverValue("ActiveDateHeader", map.get("ActiveDate"));
					TicketPage.verifyHoverOverValue("EndDateHeader",map.get("EndDate"));
					TicketPage.verifyHoverOverValue("PrintedHeader", map.get("Printed"));
					TicketPage.verifyHoverOverValue("PrintDateHeader", map.get("PrintDate"));
					
					// Verify the List Screen Grid Fields for the Printed as Yes Status
					// Verify the History Ticket Record Verification
					TicketPage.click("SearchIcon");
					//verify the Title of Ticket Search Criteria
					TicketPage.explicitWaitForVisibility("TicketSearchCriteriaTitle");
					TicketPage.verifyTextValue("TicketSearchCriteriaTitle", map.get("SearchCriteriaTitle"));
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
					//Enter the From Print Date and To Print Date 
					TicketPage.clearElement("FromPrintDateTextBox");
					TicketPage.clearElement("ToPrintDateTextBox");
					TicketPage.clearElement("FromActiveDateTextBox");
					TicketPage.clearElement("ToActiveDateTextBox");
					TicketPage.clearElement("FromEndDateTextBox");
					TicketPage.clearElement("ToEndDateTextBox");
					TicketPage.selectDropDownValue("PrintedDropDown", "Yes");
					TicketPage.clearElement("FromPrintedDateTextBox");
					TicketPage.clearElement("ToPrintedDateTextBox");
					TicketPage.click("SearchButton");
					//Verify the Title of Ticket List Screen
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
					TicketPage.explicitWaitForVisibility("TicketListTitle");
					TicketPage.verifyHoverOverValue("PrintDateHeader", "Print Date");
					TicketPage.verifyHoverOverValue("PrintedDateHeader", "Printed Date");
					
					logger.info("Successfully executed TestCase VerifyGridFieldsTIcketList..! ");
													
																	
									
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
