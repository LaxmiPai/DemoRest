/**
	 * @author lrathnak
	 *
	 */

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
public class TicketFormatUIAndNavigation {
public static Logger logger = Logger.getLogger(TicketFormatUIAndNavigation.class.getName());
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
		
		@DataProvider(name = "TicketFormatUIAndNavigation")
		public Object[][] getTestDataForTicketPermission() throws Exception {
			Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("TicketPrintFormat"),
					"TicketFormatUiAndNavigation");
			return testObjArray;
		}
		
		@Test(dataProvider="TicketFormatUIAndNavigation", priority=1)
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

			//Granting the user permission
			String userRole=propReader.getApplicationproperty("UserRole");
			RoleMaintenancePage.verifyUserRole(userRole, map.get("AccessPermission"), map.get("AssignedDataNo"));	
		}
		
		@Test(dataProvider="TicketFormatUIAndNavigation", priority=2)
		public void TicketFormatUiAndNavigation(Map<String,String> map) throws Exception {
			logger.info("Method Name: TicketFormatUiAndNavigation Started..!");
			HomePage.click("AdminMenu");
			HomePage.click("TechnicalMaintenance");
			HomePage.click("PrintFormat");
			//Check for the option under technical
			//Verify the title
			TicketPage.explicitWaitForElementToBeClickable("TicketFormatTitle");
			TicketPage.verifyTextValue("TicketFormatTitle",map.get("TicketFormatTitle"));
			
			//To verify whether the columns are sorted in the ascending order
			TicketPage.columnSorting("TypeAllRows", map.get("Value"),map.get("Order"),map.get("Datatype"));
			
			//Verify Refresh Button is present
			TicketPage.verifyElementIsPresent("TicketFormatRefreshButton");
			TicketPage.verifyElementIsEnabled("TicketFormatRefreshButton");
			
			//Verify Save button is present
			TicketPage.verifyElementIsPresent("TicketFormatSaveButton");
			TicketPage.verifyElementIsDisabled("TicketFormatSaveButton");
			
			//Verify Add button is present
			TicketPage.verifyElementIsPresent("TicketFormatAddButton");
			
			//Verify Delete button is present
			TicketPage.verifyElementIsPresent("TicketFormatDeleteButton");
			
			//Verify detail block is present
			TicketPage.verifyElementIsPresent("TicketFormatDetailScreen");
			
			//verify detail block edit button is present
			TicketPage.verifyElementIsPresent("TicketFormtDetlEditButton");
			TicketPage.verifyElementIsDisabled("TicketFormtDetlEditButton");
			
			//Verify detail block apply button is present
			TicketPage.verifyElementIsPresent("TicketFormatDetailapplyButton");
			TicketPage.verifyElementIsDisabled("TicketFormatDetailapplyButton");
			
			//Verify detail block cancel button is present
			TicketPage.verifyElementIsPresent("TicketFormatDetailCancelButton");
			TicketPage.verifyElementIsDisabled("TicketFormatDetailCancelButton");
			
			//Verify the grid fields are present
			TicketPage.verifyElementIsPresent("TicketFormatDetailDescLabel");
			TicketPage.verifyElementIsPresent("TicketFormatDetailTypeLabel");
			TicketPage.verifyElementIsPresent("TicketFormatDetailDefaultLabel");
			TicketPage.verifyElementIsPresent("TicketFormatDefaultPrntLabel");
			TicketPage.verifyElementIsPresent("TicketFormatDetailFrmtType");
			TicketPage.verifyElementIsPresent("TicketFormatDetailZpl");
			
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

