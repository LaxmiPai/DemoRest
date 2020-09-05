/**
 * * @author lapai
 * */
package com.oracle.sim.testcases.CodeInfo;

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

public class CodeInfoUnique {

	
	public static Logger logger = Logger.getLogger(CodeInfoUnique.class.getName());
	protected static PropertyReader propReader= new PropertyReader();
	PageFactory pageFactory = new PageFactory();
	
	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage CodeInfoPage;
	
	@BeforeClass
	public void setUp(ITestContext context) throws Exception { 
		logger.info("TestCase Name: " + logger.getName());
		//logger.info("Before Class");
		SIMWebdriverBase.loadInitialURL(context);
		//Login Steps
		
		LoginPage = pageFactory.initialize("LoginPage");
		CodeInfoPage = pageFactory.initialize("CodeInfoPage");
		HomePage = pageFactory.initialize("HomePage");
		//	LoginPage.switchToFrame("LoginIframe");
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username", LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password", LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");
		HomePage.verifyTextValue("Title", HomePage.getProperty("Title"));
		HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
		HomePage.storeIdCheck();
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.click("Navigation");
		HomePage.click("Admin");
		HomePage.click("DataSetup");
		HomePage.click("CodeInfo");
}
	
	@DataProvider(name = "CodeInfoUnique")
	public Object[][] getTestDataForCodeInfoUnique() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("CodeInfo"),
				"CodeInfoUnique");
		return testObjArray;
	}
	@Test(dataProvider="CodeInfoUnique", priority=1)
	public void codeInfoUnique(Map<String, String> map)throws Exception {
		
		CodeInfoPage.click("RefreshButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		CodeInfoPage.explicitWaitForElementToBeClickable("AddButton");
		CodeInfoPage.click("AddButton");
		
		//Input already existing code info Values in Detail section
		
		CodeInfoPage.searchDropDownValue("CodeDropdown", map.get("CodeType"));
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		CodeInfoPage.enterIntoTextBox("CodeTextBox", map.get("Code"));
		CodeInfoPage.enterIntoTextBox("DescriptionTextBox", map.get("Description"));
		CodeInfoPage.enterIntoTextBox("SequenceTextBox", map.get("Sequence"));
		CodeInfoPage.click("ApplyButton");
		CodeInfoPage.click("ApplyButton");
		CodeInfoPage.verifyTextValue("CodeError", map.get("CodeErrorMsg"));
		CodeInfoPage.click("CancelButton2");
		
				
	}
	
	@AfterClass
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



