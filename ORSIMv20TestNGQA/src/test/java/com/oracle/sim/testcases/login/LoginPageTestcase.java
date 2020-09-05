package com.oracle.sim.testcases.login;

import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.oracle.sim.pages.Base.SimBasePage;
import com.oracle.core.utils.PropertyReader;
import com.oracle.sim.pageFactory.PageFactory;
import com.oracle.sim.utils.SIMWebdriverBase;

public class LoginPageTestcase {

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
		// LoginPage.switchToFrame("LoginIframe");
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username", LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password", LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");
		HomePage.verifyTextValue("Title", "Enterprise Inventory Cloud Service");
	}

	@Test()
	public void userLogin() throws Exception {
		System.out.println("TestingStarted");
	}

	@AfterMethod
	public void tearDown() {

		try {

			SIMWebdriverBase.close();

		} finally {
		}
	}

}
