/**
Interesting  * @author lapai
 *
 */
/**TO DO
 * Verify the added carrier in Transfer Shipment and RTV Shipment screen
 * 
 */
package com.oracle.sim.testcases.Configuration;

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

public class CarrierAddAndDelete {

	public static Logger logger = Logger.getLogger(CarrierAddAndDelete.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pageFactory = new PageFactory();

	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage CarrierPage;

	@BeforeClass()
	public void setup(ITestContext context) throws Exception {
		logger.info("TestCase Name: " +logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
		LoginPage = pageFactory.initialize("LoginPage");
		HomePage = pageFactory.initialize("HomePage");
		CarrierPage = pageFactory.initialize("CarrierPage");
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username", LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password", LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");
		HomePage.verifyTextValue("Title", HomePage.getProperty("Title"));
		HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
		HomePage.storeIdCheck();
	}

	@DataProvider(name = "CarrierUi")
	public Object[][] getTestDataForCarrierUi() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("CarrierAddAndDelete"),"CarrierUi");
		return testObjArray;

	}

	@Test(dataProvider = "CarrierUi", priority = 1)
	public void CarrierUi(Map<String, String> map) throws Exception {
		// CarrierUiVerify
		HomePage.click("Navigation");
		HomePage.click("AdminMenu");
		HomePage.click("ConfigurationMenu");
		HomePage.click("CarrierMenu");
		CarrierPage.verifyTextValue("CarrierTitle", map.get("CarrierScreenName"));
		CarrierPage.verifyTextValue("DetailTitle", map.get("DetailTitle"));
		CarrierPage.verifyElementIsEnabled("RefreshButton");
		CarrierPage.verifyElementIsEnabled("AddButton");
		CarrierPage.verifyElementIsEnabled("DeleteButton");
		CarrierPage.verifyTextValue("ManifestTypeLabel", map.get("ManifestTypeLabel"));
		CarrierPage.verifyTextValue("CodeLabel", map.get("CodeLabel"));
		CarrierPage.verifyTextValue("DescriptionLabel", map.get("DescriptionLabel"));
		CarrierPage.verifyTextValue("SystemLabel", map.get("SystemLabel"));
		CarrierPage.verifyTextValue("EditButtonLabel", map.get("EditButtonLabel"));
	}

	@DataProvider(name = "CarrierAdd")
	public Object[][] getTestDataForCarrierAdd() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("CarrierAddAndDelete"),"CarrierAddAndDelete");
		return testObjArray;
	}

	@Test(dataProvider = "CarrierAdd", priority = 2)
	public void carrierAdd(Map<String, String> map) throws Exception {

		CarrierPage.click("AddButton");
		CarrierPage.verifyTextValue("DetailNewTitle", map.get("DetailNewTitle"));

		// Apply button when no values are entered
		CarrierPage.verifyElementIsEnabled("ApplyButton");
		CarrierPage.verifyElementIsEnabled("CancelButton1");
		CarrierPage.click("ApplyButton");
		CarrierPage.verifyTextValue("ManifestTypeError", map.get("ErrorMessage"));
		CarrierPage.verifyTextValue("CodeError", map.get("ErrorMessage"));
		CarrierPage.verifyTextValue("DescriptionError", map.get("ErrorMessage"));
		CarrierPage.click("CancelButton1");

		// Verify the dropdown values in Manifest Type
		CarrierPage.click("AddButton");
		CarrierPage.click("ManifestTypeDropdown");
		CarrierPage.verifyTextValue("ManifestType1", map.get("ManifestTypeValue1"));
		CarrierPage.verifyTextValue("ManifestType2", map.get("ManifestTypeValue2"));
		CarrierPage.verifyTextValue("ManifestType3", map.get("ManifestTypeValue3"));
		CarrierPage.click("ManifestTypeDropdown");

		// On clicking cancel button upon entering values
		CarrierPage.selectDropDownValue("ManifestTypeDropdown", "Home Fleet");
		CarrierPage.enterIntoTextBox("CodeText", map.get("Code"));
		CarrierPage.enterIntoTextBox("DescriptionText", map.get("Description"));
		CarrierPage.click("CancelButton1");

		// On clicking apply button on entering values
		CarrierPage.verifyTextValue("DetailTitle", map.get("DetailTitle"));
		CarrierPage.click("AddButton");
		CarrierPage.click("ManifestTypeDropdown");
		CarrierPage.verifyTextValue("ManifestType1", map.get("ManifestTypeValue1"));
		CarrierPage.verifyTextValue("ManifestType2", map.get("ManifestTypeValue2"));
		CarrierPage.verifyTextValue("ManifestType3", map.get("ManifestTypeValue3"));
		CarrierPage.click("ManifestTypeDropdown");
		CarrierPage.selectDropDownValue("ManifestTypeDropdown", "Home Fleet");
		CarrierPage.enterIntoTextBox("CodeText", map.get("Code"));
		CarrierPage.enterIntoTextBox("DescriptionText", map.get("Description"));
		CarrierPage.click("ApplyButton");
		CarrierPage.click("SaveButton");
		CarrierPage.click("YesButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
	}

	@Test(dataProvider = "CarrierAdd", priority = 3)
	public void carrierDelete(Map<String, String> map) throws Exception {

		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		CarrierPage.click("FilterName");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		CarrierPage.enterIntoTextBox("FilterName", map.get("Description"));
		System.out.println(CarrierPage.getText("GridRecord"));
		
		CarrierPage.click("GridRecord");
		CarrierPage.explicitWaitForElementToBeClickable("DeleteButton");
		CarrierPage.click("DeleteButton");
		CarrierPage.click("YesButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		
		//Click on some other screen
		HomePage = pageFactory.initialize("HomePage");
		HomePage.click("SystemAdministrationMenu");
		CarrierPage.click("CancelButton2");

		CarrierPage.verifyTextValue("CarrierTitle", map.get("CarrierScreenName"));
		CarrierPage.click("RefreshButton");
		CarrierPage.click("OkButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		CarrierPage.click("FilterName");
		CarrierPage.enterIntoTextBox("FilterName", map.get("Description"));
		CarrierPage.click("GridRecord");
		CarrierPage.click("DeleteButton");
		CarrierPage.click("YesButton");
		CarrierPage.click("SaveButton");
		CarrierPage.click("YesButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		CarrierPage.click("FilterName");
		CarrierPage.enterIntoTextBox("FilterName", map.get("Description"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		CarrierPage.verifyTextValue("NoRowsMatchLabel", "No rows match the current filter(s).");
		CarrierPage.click("RefreshButton");

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
		} finally {
			SIMWebdriverBase.close();

		}

	}

}
