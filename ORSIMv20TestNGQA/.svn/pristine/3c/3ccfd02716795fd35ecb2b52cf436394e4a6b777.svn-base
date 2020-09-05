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

public class BuddyStoreGridBar {
	public static Logger logger = Logger.getLogger(BuddyStoreGridBar.class.getName());
	protected static PropertyReader propReader= new PropertyReader();
	PageFactory pageFactory = new PageFactory();
	
	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage BuddyStore;
	
	@BeforeClass
	public void setUp(ITestContext context) throws Exception {
		
		//logger.info("Before Test");
		logger.info("TestCase Name:BuddyStoreGridBar"+logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
		LoginPage = pageFactory.initialize("LoginPage");
		
		
		//	LoginPage.switchToFrame("LoginIframe");
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username", LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password", LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");
		
		
}
	
	@DataProvider(name="AddBuddyStoreTestData")
	public Object[][] getAddBuddyStoreTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("BuddyStore"),
				"BuddyStore");
		return testObjArray;
	}
	
	@Test(dataProvider="AddBuddyStoreTestData")
	public void selectBuddyStore(Map<String, String> map)throws Exception {
		
		//Navigation for the test
		HomePage = pageFactory.initialize("HomePage");
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.click("Navigation");
		HomePage.storeIdCheck();
		HomePage.click("Admin");
		HomePage.click("ConfigurationMenu");
		HomePage.click("BuddyStoreOption");
		
		BuddyStore = pageFactory.initialize("BuddyStore");
		
		//Validation of the title
		String title=BuddyStore.getText("Title");
		String TitleValue=map.get("Title");
		if(title.contains(TitleValue)) {
			logger.info("The title validation is successful");
		}else {
			logger.info("The validation for title failed");
		}
		
		//Validating the save button
		BuddyStore.isElementPresent("Save");
		BuddyStore.implicitWait();
		BuddyStore.verifyElementIsEnabled("Save");
		
		//Verify Refresh is present
		BuddyStore.isElementPresent("Refresh");
		
		//Verify AssignSelected and RevokeSelectd is present
		BuddyStore.verifyElementIsPresent("AssignSelected");
		BuddyStore.implicitWait();
		BuddyStore.verifyElementIsPresent("RevokeSelected");
		
		//Make changes and check whether the save button is enable
		String value=map.get("BuddyStore");
		BuddyStore.implicitWait();
		BuddyStore.clkBuddyStore(value);
		BuddyStore.verifyElementIsEnabled("Save");
		
		//To click on refresh button and then click on NO
		BuddyStore.implicitWait();
		BuddyStore.click("Refresh");
		BuddyStore.isElementPresent("RefreshPopUp");
		BuddyStore.click("RefreshCancelButton");
		
		//To click on refresh button and the click on Yes
		BuddyStore.click("Refresh");
		BuddyStore.click("OkButton");
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
