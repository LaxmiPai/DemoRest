package com.oracle.sim.testcases.Ticket;

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

public class TicketPrintFormatSecPermisn {
	public static Logger logger = Logger.getLogger(TicketPrintFormatSecPermisn.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pagefactory = new PageFactory();

	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage RoleMaintenancePage;
	SimBasePage TicketPage;

			@BeforeClass
			public void setup(ITestContext context) throws Exception {
				logger.info("TestCase Name: " + logger.getName());
				SIMWebdriverBase.loadInitialURL(context);
				LoginPage = pagefactory.initialize("LoginPage");
				HomePage = pagefactory.initialize("HomePage");
				RoleMaintenancePage = pagefactory.initialize("RoleMaintenancePage");
				TicketPage = pagefactory.initialize("TicketPage");
				LoginPage.explicitWaitForVisibility("Username");
				LoginPage.enterIntoTextBox("Username", LoginPage.getProperty("Username"));
				LoginPage.enterIntoTextBox("Password", LoginPage.getProperty("Password"));
				LoginPage.click("SignIn");
				HomePage.verifyTextValue("Title", HomePage.getProperty("Title"));
				HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
				HomePage.storeIdCheck();
			}
			@DataProvider(name = "SecurityPrmsn")
			public Object[][] getTestDataForTicketPermission() throws Exception {
				Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("TicketPrintFormat"),
						"SecurityPrmsn");
				return testObjArray;
			}
			
			@Test(dataProvider="SecurityPrmsn", priority=1)
			public void verifyRole(Map<String,String> map) throws Exception {
				//Navigating to role maintenance page
				logger.info("Method Name: verifyRole");
				HomePage.explicitWaitForElementToBeClickable("Navigation");
				HomePage.click("Navigation");
				HomePage.click("Security");
				HomePage.click("RoleMaintenance");

				//Verifying RoleMaintenance Page Title
				RoleMaintenancePage.explicitWaitForElementToBeClickable("Title");
				RoleMaintenancePage.verifyTextValue("Title",map.get("Title"));
				String userRole=propReader.getApplicationproperty("UserRole");

				
				//Clicking on a user Role Name
				RoleMaintenancePage.explicitWaitForVisibility("RoleNameColumnRecords");
				RoleMaintenancePage.explicitWaitForElementToBeClickable("FilterRoleName");
				RoleMaintenancePage.explicitWaitForElementToBeClickable("RoleNameColumnRecords");
				RoleMaintenancePage.click("FilterRoleName");
				RoleMaintenancePage.implicitWait();
				RoleMaintenancePage.enterIntoTextBox("FilterRoleName",userRole);
				RoleMaintenancePage.click("GridRecord");
				
				//Verifying RoleDetail Page Title
				RoleMaintenancePage.explicitWaitForVisibility("DetailTitle");
				RoleMaintenancePage.verifyTextValue("DetailTitle",map.get("DetailTitle"));
				
				//Clicking on a permission
				RoleMaintenancePage.explicitWaitForElementToBeClickable("FilterPermission");
				RoleMaintenancePage.explicitWaitForElementToBeClickable("AssignedData");		
				RoleMaintenancePage.click("FilterPermission");
				RoleMaintenancePage.enterIntoTextBox("FilterPermission",map.get("AccessPermission"));
				RoleMaintenancePage.click("GridRecord");
				
				//Remove the permission for the user
				RoleMaintenancePage.explicitWaitForElementToBeClickable("AssignedData");
				if(RoleMaintenancePage.getTitle("AssignedData").equals(map.get("AssignedDataYes"))) {
					RoleMaintenancePage.click("RevokeSelected");
					RoleMaintenancePage.click("SaveButton");
					RoleMaintenancePage.click("YesButton");
					//wait for DB commit to perform 
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
					RoleMaintenancePage.click("FilterPermission");
					RoleMaintenancePage.enterIntoTextBox("FilterPermission",map.get("AccessPermission"));
					System.out.println(RoleMaintenancePage.getTitle("AssignedData"));
						}
				
				RoleMaintenancePage.RefreshWebPage();
				HomePage.click("Navigation");
				HomePage.click("AdminMenu");
				HomePage.click("TechnicalMaintenance");
				
				//To verify print format menu is not present
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				TicketPage.VerifyPageMenuIsNotPresent(map.get("PrintFormat"));
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
			
			}
			
			@Test(dataProvider="SecurityPrmsn", priority=2)
			public void verifyRoleWithPermsn(Map<String,String> map) throws Exception {
				//Navigating to role maintenance page
				logger.info("Method Name: verifyRoleWithPermsn");
				//Clicking on a Access User Assignment Info permission
				RoleMaintenancePage.explicitWaitForElementToBeClickable("DetailTitle");
				RoleMaintenancePage.explicitWaitForElementToBeClickable("FirstTableRecord");
				RoleMaintenancePage.explicitWaitForElementToBeClickable("FilterPermission");
				RoleMaintenancePage.click("FilterPermission");
				RoleMaintenancePage.enterIntoTextBox("FilterPermission",map.get("AccessPermission"));
				RoleMaintenancePage.click("GridRecord");

				//Grant the permission for the user 
				RoleMaintenancePage.explicitWaitForElementToBeClickable("AssignedData");
				if(RoleMaintenancePage.getTitle("AssignedData").equals(map.get("AssignedDataNo"))) {
					RoleMaintenancePage.click("AssignSelected");
					RoleMaintenancePage.click("SaveButton");
					RoleMaintenancePage.click("YesButton");
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
					RoleMaintenancePage.click("FilterPermission");
					RoleMaintenancePage.enterIntoTextBox("FilterPermission",map.get("AccessPermission"));
					RoleMaintenancePage.RefreshWebPage();
					System.out.println(RoleMaintenancePage.getTitle("AssignedData"));
					RoleMaintenancePage.RefreshWebPage();
				
				//To navigate and check whether the print format option is available on the screen
				HomePage.click("Navigation");
				HomePage.click("AdminMenu");
				HomePage.click("TechnicalMaintenance");
				HomePage.click("PrintFormat");
				
				//To verify the title 
				TicketPage.verifyTextValue("TicketFormatTitle",map.get("TicketFormatTitle"));
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
