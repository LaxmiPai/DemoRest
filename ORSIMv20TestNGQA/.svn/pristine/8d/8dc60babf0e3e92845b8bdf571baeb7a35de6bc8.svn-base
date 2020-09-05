/**
 * * @author vsurti 
 *
 */
package com.oracle.sim.testcases.FinisherLookup;

import java.util.Map;
import java.util.logging.Logger;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.oracle.core.dataProvider.ExcelReader;
import com.oracle.core.utils.PropertyReader;
import com.oracle.sim.pageFactory.PageFactory;
import com.oracle.sim.pages.Base.SimBasePage;
import com.oracle.sim.utils.SIMWebdriverBase;

public class FilterCriteriaValidateFinisherID {
	public static Logger logger = Logger.getLogger(FilterCriteriaValidateFinisherID.class.getName());
	protected static PropertyReader propReader= new PropertyReader();
	PageFactory pageFactory = new PageFactory();
	
	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage LookupPage;
	
	@BeforeClass()
	public void setup(ITestContext context) throws Exception {
		logger.info("TestCase Name: " +logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
		LoginPage= pageFactory.initialize("LoginPage");
		HomePage = pageFactory.initialize("HomePage");
		LookupPage = pageFactory.initialize("LookupPage");
		
		//SIM application login
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username", LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password", LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");
		HomePage.verifyTextValue("Title", HomePage.getProperty("Title"));
		HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
		HomePage.storeIdCheck();
	}
	
	@DataProvider(name = "ValidateFinisherIDTestData")
	public Object[][] getTestDataForCustomerPhone() throws Exception{
		
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("FinisherLookupTestData"),"ValidateFinisherID");
		return testObjArray;
	}
	
	@Test(dataProvider="ValidateFinisherIDTestData", priority=1)
	public void validateFinisherID(Map<String, String> map) throws Exception {
		
		//Go to Finisher Lookup screen
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.click("Navigation");
		HomePage.click("Lookup");
		LookupPage.click("FinisherLookup");
		
		//Search for FinisherID
		LookupPage.click("SearchHeading");
		LookupPage.explicitWaitForVisibility("FinisherLookupSearch");
		LookupPage.verifyElementIsPresent("FinisherLookupSearch");
		LookupPage.click("FinisherIdTextBox");
		LookupPage.enterIntoTextBox("FinisherIdTextBox", map.get("FinisherID"));
		LookupPage.explicitWaitForVisibility("SearchButton");
		LookupPage.click("SearchButton");
		
		//Validate Finisher detail screen
		LookupPage.explicitWaitForVisibility("FinisherDetailHeading");
		LookupPage.verifyTextValue("FinisherDetailHeading", map.get("FinisherDetail"));
		LookupPage.click("BackButton");
		
		//Validate Finisher ID on FinisherLookup screen
		LookupPage.verifyTextValue("FinisherIdValue", map.get("FinisherID"));
		System.out.println("Finisher ID verified");
		
	}

	
	@AfterClass()
	public void tearDown()
	{
		logger.info("After Test");
		try {
			//SIM application logout
			HomePage.click("UserMenu");
			HomePage.explicitWaitForVisibility("Logout");
			HomePage.click("Logout");
			HomePage.explicitWaitForVisibility("Yes");
			HomePage.click("Yes");



		} finally
		{
			SIMWebdriverBase.close();
		}
	}


}
