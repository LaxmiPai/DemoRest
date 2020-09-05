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

public class SearchIncludeInactiveFinishersVerifyDetailScreenTestcase {
	public static Logger logger = Logger.getLogger(SearchIncludeInactiveFinishersVerifyDetailScreenTestcase.class.getName());
	protected static PropertyReader propReader= new PropertyReader();
	PageFactory pageFactory = new PageFactory();
	
	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage LookupPage;
	SimBasePage FinisherDetailPage;
	
	@BeforeClass()
	public void setup(ITestContext context) throws Exception {
		logger.info("TestCase Name: " +logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
		LoginPage= pageFactory.initialize("LoginPage");
		HomePage = pageFactory.initialize("HomePage");
		LookupPage = pageFactory.initialize("LookupPage");
		FinisherDetailPage = pageFactory.initialize("FinisherDetailPage");
		
		//SIM application login
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username", LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password", LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");
		HomePage.verifyTextValue("Title", HomePage.getProperty("Title"));
		HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
		HomePage.storeIdCheck();
	}
	
	@DataProvider(name = "SearchIncludeInactiveFinishersVerifyDetailScreenTestData")
	public Object[][] getTestDataForCustomerPhone() throws Exception{
		
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("FinisherLookupTestData"),"SearchIncludeInactive");
		
		return testObjArray;
	}
	
	@Test(dataProvider="SearchIncludeInactiveFinishersVerifyDetailScreenTestData", priority=1)
	public void searchIncludeInactiveFinishersVerifyDetailScreen(Map<String, String> map) throws Exception {
		
		//Go to Finisher Lookup screen
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.click("Navigation");
		HomePage.click("Lookup");
		LookupPage.click("FinisherLookup");
		
		//Click on the search button
		LookupPage.click("SearchHeading");
		LookupPage.explicitWaitForVisibility("FinisherLookupSearch");
		LookupPage.verifyElementIsPresent("FinisherLookupSearch");
		
		//Search for Include inactive Yes value
		LookupPage.click("IncludeInactiveOption");
		LookupPage.click("FinisherIdTextBox");
		LookupPage.enterIntoTextBox("FinisherIdTextBox", map.get("FinisherIDValue"));
		LookupPage.explicitWaitForVisibility("SearchButton");
		LookupPage.click("SearchButton");
		
		//Validate Finisher detail screen
		FinisherDetailPage.explicitWaitForVisibility("FinisherDetailHeading");
		FinisherDetailPage.verifyTextValue("FinisherDetailHeading", map.get("FinisherDetail"));
		
		//Validating Fields on FinisherDetail Screen
		FinisherDetailPage.verifyElementIsPresent("BackButton");
		FinisherDetailPage.verifyTextValue("FinisherIdLabel", map.get("FinisherID"));
		FinisherDetailPage.verifyTextValue("FinisherNameLabel", map.get("FinisherName"));
		FinisherDetailPage.verifyTextValue("StatusLabel", map.get("Status"));
		FinisherDetailPage.verifyTextValue("PrincipalCountryLabel", map.get("PrincipalCountry"));
		FinisherDetailPage.verifyTextValue("CurrencyLabel", map.get("Currency"));
		FinisherDetailPage.verifyTextValue("LanguageLabel", map.get("Language"));
		FinisherDetailPage.verifyTextValue("PaymentTermsLabel", map.get("PaymentTerms"));
		FinisherDetailPage.verifyTextValue("VatRegionLabel", map.get("VatRegion"));
		FinisherDetailPage.verifyTextValue("TransferEntityLabel", map.get("TransferEntity"));
		FinisherDetailPage.verifyTextValue("ContactPersonLabel", map.get("ContactPerson"));
		FinisherDetailPage.verifyTextValue("TelephoneLabel", map.get("Telephone"));
		FinisherDetailPage.verifyTextValue("FaxLabel", map.get("Fax"));
		FinisherDetailPage.verifyTextValue("TelexLabel", map.get("Telex"));
		FinisherDetailPage.verifyTextValue("EmailLabel", map.get("Email"));
		
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
