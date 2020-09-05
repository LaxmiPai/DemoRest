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

public class FilterCriteriaIncludeInactiveSwitchOption {
	public static Logger logger = Logger.getLogger(FilterCriteriaIncludeInactiveSwitchOption.class.getName());
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

	@DataProvider(name = "ValidateIncludeInactiveSwitchOptionTestData")
	public Object[][] getTestDataForCustomerPhone() throws Exception{
		
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("FinisherLookupTestData"),"ValidateFilterIncludeInactive");
		
		return testObjArray;
	}

	@Test(dataProvider="ValidateIncludeInactiveSwitchOptionTestData", priority=1)
	public void validateFinisherItemID(Map<String, String> map) throws Exception {

		//Go to Finisher Lookup screen
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.click("Navigation");
		HomePage.click("Lookup");
		LookupPage.click("FinisherLookup");

		//Search for Finisher Item ID
		LookupPage.click("SearchHeading");
		LookupPage.explicitWaitForVisibility("FinisherLookupSearch");
		LookupPage.verifyElementIsPresent("FinisherLookupSearch");

		//Check for Include inactive default value
		LookupPage.verifyTextValue("IncludeInactiveDefaultValue", map.get("SwitchValueDefault"));
		System.out.println("Verified");
				
		//Search for Include inactive Yes value
		LookupPage.click("IncludeInactiveOption");
		LookupPage.verifyTextValue("IncludeInactiveNewValue", map.get("SwitchValueNew"));
		LookupPage.explicitWaitForVisibility("SearchButton");
		LookupPage.click("SearchButton");
				
		//Verifying Finisher lookup screen & Include inactive search result
		LookupPage.verifyElementIsPresent("FinisherLookup");
		LookupPage.verifyPartialTextValue("FinisherLookupSearchHeading", map.get("FinisherLookupSearchValue"));
		System.out.println("Inactive search is included");
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
