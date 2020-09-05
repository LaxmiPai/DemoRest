package com.oracle.sim.testcases.Homepage;

import java.util.logging.Logger;

import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.oracle.core.utils.PropertyReader;
import com.oracle.sim.pageFactory.PageFactory;
import com.oracle.sim.pages.Base.SimBasePage;
import com.oracle.sim.pageFactory.PageFactory;
import com.oracle.sim.utils.SIMWebdriverBase;

public class HomePageTestcase {

	public static Logger logger = Logger.getLogger(HomePageTestcase.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pageFactory = new PageFactory();

	SimBasePage basepage;
	SimBasePage LoginPage;
	SimBasePage HomePage;

	@BeforeMethod
	public void setUp(ITestContext context) throws Exception {
		// logger.info("Before Test");
		SIMWebdriverBase.loadInitialURL(context);
		// Login Steps
		LoginPage = pageFactory.initialize("LoginPage");
		// LoginPage.switchToFrame("LoginIframe");
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username", LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password", LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");
	}

	@Test
	public void verifyHomepage() {
		HomePage = pageFactory.initialize("HomePage");
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.click("Navigation");
		HomePage.verifyTextValue("Title", "Enterprise Inventory Cloud Service");
		HomePage.verifyTextValue("User", HomePage.getProperty("Username"));

	}

	@AfterMethod
	public void teardown() {
		SIMWebdriverBase.close();
	}

}
