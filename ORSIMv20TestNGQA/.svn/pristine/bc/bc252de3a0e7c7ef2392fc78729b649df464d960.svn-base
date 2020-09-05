package com.oracle.sim.testcases.login;

import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.oracle.core.utils.PropertyReader;
import com.oracle.sim.pageFactory.PageFactory;
import com.oracle.sim.pages.Base.SimBasePage;
import com.oracle.sim.utils.SIMWebdriverBase;

public class LogoutTestcase {

	SimBasePage LoginPage;
	SimBasePage HomePage;
	PageFactory pageFactory = new PageFactory();
	protected static PropertyReader propReader = new PropertyReader();

	@BeforeMethod
	public void setUp(ITestContext context) throws Exception {
		// logger.info("Before Test");
		SIMWebdriverBase.loadInitialURL(context);
		// Login Steps
		LoginPage = pageFactory.initialize("LoginPage");
		HomePage = pageFactory.initialize("HomePage");
		// LoginPage.switchToFrame("LoginIframe");
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username", LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password", LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");
		HomePage.verifyTextValue("Title", "Store Inventory Operations Cloud Service");
		Thread.sleep(3000);
		HomePage.storeIdCheck();
	}

	@Test()
	public void userLogin() throws Exception {
		System.out.println("TestingStarted");
	}

	@AfterMethod
	public void tearDown() throws InterruptedException {

		try {

			HomePage.explicitWaitForVisibility("UserMenu");
			HomePage.click("UserMenu");
			HomePage.explicitWaitForVisibility("Logout");
			Thread.sleep(3000);
			// HomePage.click("PreferenceButton");
			System.out.println("Text is:" + HomePage.getText("Logout"));
			HomePage.click("Logout");

			// HomePage.explicitWaitForVisibility("Yes");
			// HomePage.click("Yes");

		} finally {
			SIMWebdriverBase.close();
		}
	}

}
