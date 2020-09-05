/**
 * @author niudupa
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

public class PackageSizeSetup {

	public static Logger logger = Logger.getLogger(PackageSizeSetup.class.getName());	
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pageFactory = new PageFactory();
	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage PackageSizePage;

	@BeforeClass()
	public void setup(ITestContext context) throws Exception {
		logger.info("TestCase Name: " +logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
		LoginPage = pageFactory.initialize("LoginPage");
		HomePage = pageFactory.initialize("HomePage");
		PackageSizePage = pageFactory.initialize("PackageSizePage");
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username", LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password", LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.verifyTextValue("Title", "Store Inventory Operations Cloud Service");
		HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
		HomePage.storeIdCheck();
	}

	@DataProvider(name = "AddPackageSize")
	public Object[][] getTestDataForPackageAdd() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("PackageTestData"),"AddPackageSize");
		return testObjArray;
	}

	@Test(dataProvider = "AddPackageSize", priority = 1)
	public void packageSizeAdd(Map<String, String> map) throws Exception {
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.click("Navigation");
		HomePage.click("AdminMenu");
		HomePage.click("ConfigurationMenu");

		// Go to Printer Setup Page
		HomePage.click("PackageSizeMenu");
		logger.info("printerAdd test case");
		PackageSizePage.explicitWaitForElementToBeClickable("Title");

		// Title verification
		PackageSizePage.verifyTextValue("Title", map.get("Title"));
		logger.info("Tile Verified");
		PackageSizePage.click("AddNewIcon");

		// Input Values in Detail section
		PackageSizePage.explicitWaitForElementToBeClickable("DescriptionTxtBox");
		PackageSizePage.enterIntoTextBox("DescriptionTxtBox", map.get("Description"));
		PackageSizePage.enterIntoTextBox("HeightTxtBox", map.get("Height"));
		PackageSizePage.enterIntoTextBox("WidthTxtBox", map.get("Width"));
		PackageSizePage.enterIntoTextBox("LengthTxtBox", map.get("Length"));
		PackageSizePage.searchDropDownValue("UOMDropdown", map.get("UOM"));
	//	PackageSizePage.SelectDropdownOption(map.get("UOM"));
	//	PackageSizePage.selectDropDownValue("UOMDropdown", map.get("UOM"));
		PackageSizePage.explicitWaitForElementToBeClickable("ApplyButton");
		PackageSizePage.click("ApplyButton");
		PackageSizePage.click("SaveButton");

		// Click Yes in Confirmation pop up
		PackageSizePage.explicitWaitForElementToBeClickable("YesButton");
		PackageSizePage.click("YesButton");
		// PackageSizePage.explicitWaitForInvisibility("SaveButton");
		logger.info("New Package Size added Successfully");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));

		// Refreshing the page
		PackageSizePage.click("RefreshButton");

	}

	@Test(dataProvider = "AddPackageSize", priority = 2, dependsOnMethods = { "packageSizeAdd" })
	public void packageSearch(Map<String, String> map) throws Exception {
		logger.info("printerSearch test case");
		
		//waiting for screen elements
		PackageSizePage.explicitWaitForElementToBeClickable("Title");
		PackageSizePage.explicitWaitForElementToBeClickable("AddNewIcon");
		PackageSizePage.explicitWaitForElementToBeClickable("RefreshButton");
		PackageSizePage.explicitWaitForElementToBeClickable("DeleteIcon");

		// Verifying the inserted record
		PackageSizePage.explicitWaitForElementToBeClickable("DescriptionFilter");
		PackageSizePage.click("DescriptionFilter");
		PackageSizePage.enterIntoTextBox("DescriptionFilter", map.get("Description"));
		System.out.println(PackageSizePage.getText("GridRecord"));
		PackageSizePage.verifyTextValue("GridRecord", map.get("Description"));
		logger.info("Package size search is complete");
	}

	@DataProvider(name = "EditPackageSize")
	public Object[][] getTestDataForPackageEdit() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("PackageTestData"),"EditPackageSize");
		return testObjArray;
	}

	@Test(dataProvider = "EditPackageSize", priority = 3, dependsOnMethods = { "packageSizeAdd" })
	public void packageEdit(Map<String, String> map) throws Exception {
		logger.info("printerEdit test case");
		PackageSizePage.click("RefreshButton");
		Thread.sleep(3000);
		PackageSizePage.explicitWaitForElementToBeClickable("DescriptionFilter");
		String str = map.get("Description");
		PackageSizePage.enterIntoTextBox("DescriptionFilter", str);
		PackageSizePage.explicitWaitForVisibility("GridRecord");
		PackageSizePage.click("GridRecord");
		PackageSizePage.explicitWaitForVisibility("EditButton");
		PackageSizePage.explicitWaitForElementToBeClickable("EditButton");

		// Status of Edit button cannot be verified as it is not present in html tag
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		PackageSizePage.click("EditButton");

		// Input Values in Detail section
		PackageSizePage.explicitWaitForElementToBeClickable("UOMDropdown");
		PackageSizePage.searchDropDownValue("UOMDropdown", map.get("UOM"));
		PackageSizePage.explicitWaitForElementToBeClickable("DescriptionTxtBox");
		PackageSizePage.enterIntoTextBox("DescriptionTxtBox", map.get("NewDescription"));
		PackageSizePage.enterIntoTextBox("HeightTxtBox", map.get("Height"));
		PackageSizePage.enterIntoTextBox("WidthTxtBox", map.get("Width"));
		PackageSizePage.enterIntoTextBox("LengthTxtBox", map.get("Length"));
		PackageSizePage.explicitWaitForElementToBeClickable("ApplyButton");
		PackageSizePage.click("ApplyButton");
		PackageSizePage.click("SaveButton");

		// Click Yes in Confirmation pop up

		PackageSizePage.explicitWaitForElementToBeClickable("YesButton");
		PackageSizePage.click("YesButton");
		// PackageSizePage.explicitWaitForInvisibility("SaveButton");

		logger.info("Package Size Edited Successfully");
		
		//waiting for DB commit is done
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));

		// Verifying the Edited record

		PackageSizePage.explicitWaitForElementToBeClickable("DescriptionFilter");
		PackageSizePage.click("DescriptionFilter");
		PackageSizePage.enterIntoTextBox("DescriptionFilter", map.get("NewDescription"));
		System.out.println(PackageSizePage.getText("GridRecord"));
		PackageSizePage.verifyTextValue("GridRecord", map.get("NewDescription"));
		PackageSizePage.click("RefreshButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));

	}

	@Test(dataProvider = "EditPackageSize", priority = 4, dependsOnMethods = { "packageEdit" })
	public void packageDelete(Map<String, String> map) throws Exception {
		logger.info("printerDelete test case");
		// Selecting Record to delete
		PackageSizePage.explicitWaitForElementToBeClickable("DescriptionFilter");
		PackageSizePage.click("DescriptionFilter");
		PackageSizePage.enterIntoTextBox("DescriptionFilter", map.get("NewDescription"));
		PackageSizePage.click("GridRecord");
		PackageSizePage.explicitWaitForElementToBeClickable("DeleteIcon");
		PackageSizePage.click("DeleteIcon");

		// Click Yes in Confirmation pop up
		PackageSizePage.explicitWaitForElementToBeClickable("YesButton");
		PackageSizePage.click("YesButton");
		// PackageSizePage.explicitWaitForInvisibility("SaveButton");
		logger.info("Package Size Deleted Successfully");

		// Saving the changes
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		PackageSizePage.explicitWaitForElementToBeClickable("SaveButton");
		PackageSizePage.click("SaveButton");

		// Click Yes in Confirmation pop up
		PackageSizePage.explicitWaitForElementToBeClickable("YesButton");
		PackageSizePage.click("YesButton");
		// PackageSizePage.explicitWaitForInvisibility("SaveButton");
		logger.info("Package Delete Saved Successfully");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));

		// Verifying the record after deletion
		PackageSizePage.explicitWaitForElementToBeClickable("DescriptionFilter");
		PackageSizePage.click("DescriptionFilter");
		PackageSizePage.enterIntoTextBox("DescriptionFilter", map.get("NewDescription"));
		PackageSizePage.verifyTextValue("GridMessage", map.get("Grid_Message"));

	}

	@AfterClass()
	public void tearDown() throws Exception {

		try {
			logger.info("After Test: Logging out");
			HomePage.click("UserMenu");
			HomePage.explicitWaitForVisibility("Logout");
			HomePage.click("Logout");
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
			HomePage.explicitWaitForVisibility("Yes");
			HomePage.click("Yes");
		}

		finally {

			SIMWebdriverBase.close();

		}

	}

}
