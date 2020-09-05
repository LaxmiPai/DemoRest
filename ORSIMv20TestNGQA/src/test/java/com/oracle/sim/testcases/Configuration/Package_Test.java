package com.oracle.sim.testcases.Configuration;

import java.util.Map;
import java.util.logging.Logger;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.oracle.core.utils.PropertyReader;
import com.oracle.sim.pageFactory.PageFactory;
import com.oracle.sim.pages.Base.SimBasePage;
import com.oracle.sim.utils.SIMWebdriverBase;

public class Package_Test {

	public static Logger logger = Logger.getLogger(PackageSizeSetup.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pageFactory = new PageFactory();

	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage PackageSizePage;

	@BeforeClass()
	public void setup(ITestContext context) throws Exception {
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
		HomePage.verifyTextValue("User_Menu", HomePage.getProperty("Username"));
		HomePage.storeIdCheck();
	}

	@Test(priority = 1)
	public void packageSizeAdd() throws Exception {
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.click("Navigation");
		HomePage.click("AdminMenu");
		HomePage.click("ConfigurationMenu");

		// Go to Printer Setup Page
		HomePage.click("PackageSizeMenu");
		PackageSizePage.explicitWaitForElementToBeClickable("Title");

		// Title verification
//		PackageSizePage.verifyTextValue("Title", map.get("Title"));
		logger.info("Tile Verified");
		PackageSizePage.explicitWaitForElementToBeClickable("GridViewMenu");
		PackageSizePage.click("GridViewMenu");
		Thread.sleep(3000);

		PackageSizePage.explicitWaitForElementToBeClickable("Row Selector");
		// PackageSizePage.selectMenuItems("Row Selector");
		System.out.println(PackageSizePage.getText("Row Selector"));

		// PackageSizePage.click("Row Selector");
		PackageSizePage.selectMenuItems("Row Selector");

	}

}
