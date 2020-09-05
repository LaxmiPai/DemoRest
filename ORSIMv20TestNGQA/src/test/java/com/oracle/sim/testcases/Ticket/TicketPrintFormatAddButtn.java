
// ** @author lrathnak//
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

public class TicketPrintFormatAddButtn {
	
	public static Logger logger = Logger.getLogger(TicketPrintFormatAddButtn.class.getName());
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
			
			@DataProvider(name = "TicketFormatAdd")
			public Object[][] getTestDataForTicketPermission() throws Exception {
				Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("TicketPrintFormat"),
						"TicketFormatAdd");
				return testObjArray;
			}
			
			@Test(dataProvider="TicketFormatAdd", priority=1)
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
			
			@Test(dataProvider="TicketFormatAdd", priority=2)
			public void TicketFormatAddButton(Map<String,String> map) throws Exception {
				logger.info("Method Name: TicketFormatAddButton");
				
				HomePage.click("AdminMenu");
				HomePage.click("TechnicalMaintenance");
				HomePage.click("PrintFormat");
				
				//Verify the title
				TicketPage.verifyTextValue("TicketFormatTitle",map.get("TicketFormatTitle"));
				
				//Click on the add format button
				TicketPage.click("TicketFormatAddButton");
				
				//To add all the values and click on apply button
				//Entering a valid value
				TicketPage.selectDropDownValue("DetaileTypeDropDwn", map.get("Value"));
				TicketPage.enterIntoTextBox("DescriptionInput",map.get("Description"));
				TicketPage.click("TicketFormatDetailapplyButton");
				TicketPage.enterIntoTextBox("DetailFormatInput", map.get("Format"));
				TicketPage.click("DetailsDefaultPrntsDropDown");
				TicketPage.click("DefaultPrinterFrstOption");
				TicketPage.click("DetailZplDrpDwn");
				TicketPage.click("DetailZplFrstOption");
				TicketPage.click("TicketFormatDetailapplyButton");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				TicketPage.clickByJs("TicketFormatSaveButton");
				TicketPage.verifyElementIsDisplayed("DetailConfirmationDialog");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				TicketPage.click("ConfirmYesButton");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
				TicketPage.click("TicketFormatRefreshButton");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				
				//To check for the format created and delete
				TicketPage.enterIntoTextBox("DiscriptionFilter", map.get("Description"));
				TicketPage.click("GridHighLightRecord");
				TicketPage.click("TicketFormatDeleteButton");
				TicketPage.verifyElementIsDisplayed("DetailConfirmationDialog");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				TicketPage.click("ConfirmYesButton");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				TicketPage.clickByJs("TicketFormatSaveButton");
				TicketPage.verifyElementIsDisplayed("DetailConfirmationDialog");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				TicketPage.click("ConfirmYesButton");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
				TicketPage.click("TicketFormatRefreshButton");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				TicketPage.RefreshWebPage();
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
	

