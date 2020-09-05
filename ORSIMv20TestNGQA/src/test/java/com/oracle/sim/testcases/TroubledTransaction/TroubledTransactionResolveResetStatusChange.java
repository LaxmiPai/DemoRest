package com.oracle.sim.testcases.TroubledTransaction;

/**
 * @author vidhsank
 *
 * 
 */

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

public class TroubledTransactionResolveResetStatusChange {
	public static Logger logger=Logger.getLogger(TroubledTransactionResolveResetStatusChange.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pagefactory=new PageFactory();

	SimBasePage LoginPage;
	SimBasePage HomePage;
	SimBasePage TroubledTransactionPage;
	int size,flag=0;
	String firstItem,firstUin,firstLastUpdate,firstResolve,lastItem,lastUin,lastLastUpdate,lastResolve;

	@BeforeClass()
	public void setup(ITestContext context) throws Exception {
		logger.info("TestCase Name: " +logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
		LoginPage=pagefactory.initialize("LoginPage");
		HomePage=pagefactory.initialize("HomePage");
		TroubledTransactionPage=pagefactory.initialize("TroubledTransactionPage");
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username",LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password",LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");
		HomePage.verifyTextValue("Title", "Store Inventory Operations Cloud Service");
		HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
		HomePage.storeIdCheck();
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.click("Navigation");
		HomePage.explicitWaitForElementToBeClickable("InventoryManagement");
		HomePage.click("InventoryManagement");
		HomePage.explicitWaitForElementToBeClickable("TroubledTransaction");
		HomePage.click("TroubledTransaction");

	}

	@DataProvider(name = "ResolveResetTestData")
	public Object[][] getResolveTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("TroubledTransactionTestData"),
				"ResolveReset");
		return testObjArray;
	}

	@Test(dataProvider="ResolveResetTestData", priority=1)
	public void verifyResolve(Map<String, String> map) throws Exception {
		//Navigating to troubled transaction page
		logger.info("Method Name: verifyResolve");

		//Verifying the title of the screen
		TroubledTransactionPage.explicitWaitForVisibility("TroubledTransactionListTitle");
		TroubledTransactionPage.verifyTextValue("TroubledTransactionListTitle",map.get("Title"));

		//Clearing from date 
		TroubledTransactionPage.explicitWaitForElementToBeClickable("SearchCriteriaTitle");
		TroubledTransactionPage.clearElement("FromDateDatePicker");

		//Clicking on a search button
		TroubledTransactionPage.explicitWaitForElementToBeClickable("SearchButton");
		TroubledTransactionPage.click("SearchButton");

		//clicking on row selector
		TroubledTransactionPage.explicitWaitForVisibility("ItemColumnName");
		HomePage.click("Navigation");

		//Verifying troubled transaction records count		
		size=TroubledTransactionPage.getTableRowCount("TableRows","ResolvedColumnName","FilterItem");
		if(size<=1) {
			System.out.println("No sufficient records found");
		}else {
			//Verifying both resolve status no and yes records are available or not
			TroubledTransactionPage.explicitWaitForElementToBeClickable("FilterResolve");
			TroubledTransactionPage.click("FilterResolve");
			TroubledTransactionPage.enterIntoTextBox("FilterResolve",map.get("ResolveNo"));
			TroubledTransactionPage.explicitWaitForElementToBeClickable("GridRecord");

			if(TroubledTransactionPage.isVisible("GridRecord")) {
				TroubledTransactionPage.explicitWaitForElementToBeClickable("FilterResolve");
				TroubledTransactionPage.click("FilterResolve");
				TroubledTransactionPage.enterIntoTextBox("FilterResolve",map.get("ResolveYes"));
				TroubledTransactionPage.explicitWaitForElementToBeClickable("GridRecord");
				if(TroubledTransactionPage.isVisible("GridRecord")) {
					flag=1;
				}else {
					System.out.println("No sufficient records found");
				}
			}else {
				System.out.println("No sufficient records found");
			}
			if(flag==1) {				
				//Enable row selector option 
				TroubledTransactionPage.explicitWaitForElementToBeClickable("GridViewMenu");
				TroubledTransactionPage.click("GridViewMenu");
				TroubledTransactionPage.explicitWaitForElementToBeClickable("RowSelector");
				TroubledTransactionPage.click("RowSelector");
				TroubledTransactionPage.explicitWaitForElementToBeClickable("RowSelectorCheckBox");

				//Selecting a first record
				TroubledTransactionPage.explicitWaitForVisibility("FirstResolveRecord");
				TroubledTransactionPage.explicitWaitForElementToBeClickable("FirstResolveRecord");
				TroubledTransactionPage.click("FirstResolveRecord");
				TroubledTransactionPage.click("FocusedRowSelectorCheckBox");

				//Getting values of a first record for verification
				firstItem=TroubledTransactionPage.getText("FocusedItemRecord");
				firstUin=TroubledTransactionPage.getText("FocusedUinRecord");
				firstLastUpdate=TroubledTransactionPage.getText("FocusedLastUpdate");
				firstResolve=TroubledTransactionPage.getText("FirstResolveRecord");
				System.out.println("First Item: "+firstItem);
				System.out.println("First Uin: "+firstUin);
				System.out.println("First LastUpdate: "+firstLastUpdate);
				System.out.println("First Resolve: "+firstResolve);

				//Selecting last record
				TroubledTransactionPage.scrollToTableEnd();
				TroubledTransactionPage.click("FocusedRowSelectorCheckBox");

				//Getting values of a last record for  verification
				lastItem=TroubledTransactionPage.getText("FocusedItemRecord");
				lastUin=TroubledTransactionPage.getText("FocusedUinRecord");
				lastLastUpdate=TroubledTransactionPage.getText("FocusedLastUpdate");
				lastResolve=TroubledTransactionPage.getText("FocusedResolveRecord");
				System.out.println("Last Item: "+lastItem);
				System.out.println("Last Uin: "+lastUin);
				System.out.println("Last LastUpdate: "+lastLastUpdate);
				System.out.println("Last Resolve: "+lastResolve);

				//Clicking on a resolve button
				TroubledTransactionPage.explicitWaitForElementToBeClickable("ResolveButton");
				TroubledTransactionPage.click("ResolveButton");

				//Verifying resolve pop up
				TroubledTransactionPage.explicitWaitForElementToBeClickable("ResolutionStatusMessage");
				TroubledTransactionPage.verifyTextValue("ResolutionStatusMessage",map.get("ResolutionStatusMessage"));
				TroubledTransactionPage.verifyElementIsPresent("YesButton");
				TroubledTransactionPage.verifyElementIsPresent("NoButton");

				//Clicking on a no button in resolve pop up
				TroubledTransactionPage.explicitWaitForElementToBeClickable("NoButton");
				TroubledTransactionPage.click("NoButton");
				System.out.println("Clicked on Resolve no button sucessfully");

				//Verifying the records are still in troubled transaction list screen
				TroubledTransactionPage.explicitWaitForVisibility("TroubledTransactionListTitle");
				TroubledTransactionPage.verifyTextValue("TroubledTransactionListTitle",map.get("Title"));

				//Clicking on a refresh button
				TroubledTransactionPage.explicitWaitForElementToBeClickable("RefreshButton");
				TroubledTransactionPage.click("RefreshButton");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));

				//Verifying there is no change in selected first record having resolve status 'no'
				TroubledTransactionPage.explicitWaitForVisibility("ResolveColumnRecord");
				TroubledTransactionPage.explicitWaitForElementToBeClickable("FilterItem");
				TroubledTransactionPage.click("FilterItem");
				TroubledTransactionPage.enterIntoTextBox("FilterItem",firstItem);
				TroubledTransactionPage.enterIntoTextBox("FilterItem",firstItem);

				TroubledTransactionPage.explicitWaitForElementToBeClickable("FilterUin");
				TroubledTransactionPage.click("FilterUin");
				TroubledTransactionPage.enterIntoTextBox("FilterUin",firstUin);

				TroubledTransactionPage.explicitWaitForElementToBeClickable("FilterLastUpdate");
				TroubledTransactionPage.click("FilterLastUpdate");
				TroubledTransactionPage.enterIntoTextBox("FilterLastUpdate",firstLastUpdate);

				TroubledTransactionPage.explicitWaitForElementToBeClickable("FilterResolve");
				TroubledTransactionPage.click("FilterResolve");
				TroubledTransactionPage.enterIntoTextBox("FilterResolve",firstResolve);

				TroubledTransactionPage.explicitWaitForElementToBeClickable("GridResolveRecord");
				TroubledTransactionPage.verifyTextValue("GridResolveRecord",firstResolve);
				TroubledTransactionPage.implicitWait();	

				//Verifying there is no change in selected last record having resolve status 'yes'
				TroubledTransactionPage.explicitWaitForVisibility("ResolveColumnRecord");
				TroubledTransactionPage.explicitWaitForElementToBeClickable("FilterItem");
				TroubledTransactionPage.click("FilterItem");
				TroubledTransactionPage.enterIntoTextBox("FilterItem",lastItem);

				TroubledTransactionPage.explicitWaitForElementToBeClickable("FilterUin");
				TroubledTransactionPage.click("FilterUin");
				TroubledTransactionPage.enterIntoTextBox("FilterUin",lastUin);

				TroubledTransactionPage.explicitWaitForElementToBeClickable("FilterLastUpdate");
				TroubledTransactionPage.click("FilterLastUpdate");
				TroubledTransactionPage.enterIntoTextBox("FilterLastUpdate",lastLastUpdate);

				TroubledTransactionPage.explicitWaitForElementToBeClickable("FilterResolve");
				TroubledTransactionPage.click("FilterResolve");
				TroubledTransactionPage.enterIntoTextBox("FilterResolve",lastResolve);

				TroubledTransactionPage.explicitWaitForElementToBeClickable("GridResolveRecord");
				TroubledTransactionPage.verifyTextValue("GridResolveRecord",lastResolve);
				TroubledTransactionPage.implicitWait();

				//Clicking on a refresh button
				TroubledTransactionPage.explicitWaitForElementToBeClickable("RefreshButton");
				TroubledTransactionPage.click("RefreshButton");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));				

				//Selecting a first record
				TroubledTransactionPage.explicitWaitForVisibility("FirstResolveRecord");
				TroubledTransactionPage.explicitWaitForElementToBeClickable("FirstResolveRecord");
				TroubledTransactionPage.click("FirstResolveRecord");
				TroubledTransactionPage.click("FocusedRowSelectorCheckBox");

				//Getting values of a first record for  verification
				firstItem=TroubledTransactionPage.getText("FocusedItemRecord");
				firstUin=TroubledTransactionPage.getText("FocusedUinRecord");
				firstLastUpdate=TroubledTransactionPage.getText("FocusedLastUpdate");
				firstResolve=TroubledTransactionPage.getText("FirstResolveRecord");
				System.out.println("First Item: "+firstItem);
				System.out.println("First Uin: "+firstUin);
				System.out.println("First LastUpdate: "+firstLastUpdate);
				System.out.println("First Resolve: "+firstResolve);

				//Selecting last record
				TroubledTransactionPage.scrollToTableEnd();
				TroubledTransactionPage.click("FocusedRowSelectorCheckBox");

				//Getting values of a last record for  verification
				lastItem=TroubledTransactionPage.getText("FocusedItemRecord");
				lastUin=TroubledTransactionPage.getText("FocusedUinRecord");
				lastLastUpdate=TroubledTransactionPage.getText("FocusedLastUpdate");
				lastResolve=TroubledTransactionPage.getText("FocusedResolveRecord");
				System.out.println("Last Item: "+lastItem);
				System.out.println("Last Uin: "+lastUin);
				System.out.println("Last LastUpdate: "+lastLastUpdate);
				System.out.println("Last Resolve: "+lastResolve);

				//Clicking on a resolve button
				TroubledTransactionPage.explicitWaitForElementToBeClickable("ResolveButton");
				TroubledTransactionPage.click("ResolveButton");

				//Clicking on a yes button in resolve pop up
				TroubledTransactionPage.explicitWaitForElementToBeClickable("YesButton");
				TroubledTransactionPage.click("YesButton");
				System.out.println("Clicked on Resolve yes button sucessfully");

				//Clicking on refresh button 
				TroubledTransactionPage.explicitWaitForElementToBeClickable("RefreshButton");
				TroubledTransactionPage.click("RefreshButton");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));

				//Verifying there is no change in selected last record having resolve status 'yes'
				TroubledTransactionPage.explicitWaitForVisibility("ResolveColumnRecord");
				TroubledTransactionPage.explicitWaitForElementToBeClickable("FilterItem");
				TroubledTransactionPage.click("FilterItem");
				TroubledTransactionPage.enterIntoTextBox("FilterItem",lastItem);
				TroubledTransactionPage.enterIntoTextBox("FilterItem",lastItem);

				TroubledTransactionPage.explicitWaitForElementToBeClickable("FilterUin");
				TroubledTransactionPage.click("FilterUin");
				TroubledTransactionPage.enterIntoTextBox("FilterUin",lastUin);

				TroubledTransactionPage.explicitWaitForElementToBeClickable("FilterLastUpdate");
				TroubledTransactionPage.click("FilterLastUpdate");
				TroubledTransactionPage.enterIntoTextBox("FilterLastUpdate",lastLastUpdate);

				TroubledTransactionPage.explicitWaitForElementToBeClickable("FilterResolve");
				TroubledTransactionPage.click("FilterResolve");
				TroubledTransactionPage.enterIntoTextBox("FilterResolve",lastResolve);

				TroubledTransactionPage.explicitWaitForElementToBeClickable("GridResolveRecord");
				TroubledTransactionPage.verifyTextValue("GridResolveRecord",lastResolve);
				TroubledTransactionPage.implicitWait();

				//Click on search icon to verify the first record
				TroubledTransactionPage.explicitWaitForElementToBeClickable("SearchIcon");
				TroubledTransactionPage.click("SearchIcon");
				TroubledTransactionPage.implicitWait();

				//Selecting resolve drop down value to view resolve status records
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
				TroubledTransactionPage.explicitWaitForElementToBeClickable("ShowResolvedDropDown");
				TroubledTransactionPage.selectDropDownValue("ShowResolvedDropDown",map.get("ResolvedDropDownValue"));
				TroubledTransactionPage.explicitWaitForElementToBeClickable("SearchButton");
				TroubledTransactionPage.click("SearchButton");
				TroubledTransactionPage.explicitWaitForInvisibility("SearchCriteriaTitle");

				//Verifying the resolve status has been changed to yes for the selected first record
				TroubledTransactionPage.explicitWaitForVisibility("ResolveColumnRecord");
				TroubledTransactionPage.explicitWaitForElementToBeClickable("FirstResolveRecord");
				TroubledTransactionPage.explicitWaitForElementToBeClickable("FilterItem");
				TroubledTransactionPage.click("FilterItem");
				TroubledTransactionPage.enterIntoTextBox("FilterItem",firstItem);

				TroubledTransactionPage.explicitWaitForElementToBeClickable("FilterUin");
				TroubledTransactionPage.click("FilterUin");
				TroubledTransactionPage.enterIntoTextBox("FilterUin",firstUin);

				TroubledTransactionPage.explicitWaitForElementToBeClickable("FilterResolve");
				TroubledTransactionPage.click("FilterResolve");
				TroubledTransactionPage.enterIntoTextBox("FilterResolve",map.get("ResolveYes"));

				TroubledTransactionPage.explicitWaitForElementToBeClickable("GridResolveRecord");
				TroubledTransactionPage.verifyTextValue("GridResolveRecord",map.get("ResolveYes"));
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
			}
		}
	}

	@DataProvider(name = "UnresolveResetTestData")
	public Object[][] getUnresolveTestData() throws Exception{
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("TroubledTransactionTestData"),
				"ResolveReset");
		return testObjArray;
	}

	@Test(dataProvider="UnresolveResetTestData", priority=2)
	public void verifyUnresolve(Map<String, String> map) throws Exception {
		//Click on search icon to verify the first record
		logger.info("Method Name: verifyUnresolve");
		TroubledTransactionPage.explicitWaitForElementToBeClickable("SearchIcon");
		TroubledTransactionPage.click("SearchIcon");
		TroubledTransactionPage.implicitWait();

		//Selecting resolve drop down value as all to view resolve status records
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
		TroubledTransactionPage.explicitWaitForElementToBeClickable("ShowResolvedDropDown");
		TroubledTransactionPage.selectDropDownValue("ShowResolvedDropDown",map.get("ShowResolvedDropDownValue"));

		//Clicking on a search button
		TroubledTransactionPage.explicitWaitForElementToBeClickable("SearchButton");
		TroubledTransactionPage.click("SearchButton");
		TroubledTransactionPage.explicitWaitForVisibility("ResolvedColumnName");

		flag=0;
		size=TroubledTransactionPage.getTableRowCount("TableRows","ResolvedColumnName","FilterItem");
		if(size<=1) {
			System.out.println("No sufficient records found");
		}else {
			//Verifying both resolve status no and yes records are available or not
			TroubledTransactionPage.explicitWaitForVisibility("FilterResolve");
			TroubledTransactionPage.explicitWaitForElementToBeClickable("FilterResolve");
			TroubledTransactionPage.click("FilterResolve");
			TroubledTransactionPage.enterIntoTextBox("FilterResolve",map.get("ResolveNo"));
			TroubledTransactionPage.explicitWaitForElementToBeClickable("GridRecord");

			if(TroubledTransactionPage.isVisible("GridRecord")) {
				TroubledTransactionPage.explicitWaitForElementToBeClickable("FilterResolve");
				TroubledTransactionPage.click("FilterResolve");
				TroubledTransactionPage.enterIntoTextBox("FilterResolve",map.get("ResolveYes"));
				TroubledTransactionPage.explicitWaitForElementToBeClickable("GridRecord");
				if(TroubledTransactionPage.isVisible("GridRecord")) {
					flag=1;
				}else {
					System.out.println("No sufficient records found");
				}
			}
			if(flag==1) {
				//Clicking on a refresh button
				TroubledTransactionPage.explicitWaitForElementToBeClickable("RefreshButton");
				TroubledTransactionPage.click("RefreshButton");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));

				//Clicking on a first record
				TroubledTransactionPage.explicitWaitForElementToBeClickable("FirstResolveRecord");
				TroubledTransactionPage.click("FirstResolveRecord");
				TroubledTransactionPage.explicitWaitForElementToBeClickable("FocusedRowSelectorCheckBox");

				//Getting values of a first record for verification
				firstItem=TroubledTransactionPage.getText("FocusedItemRecord");
				firstUin=TroubledTransactionPage.getText("FocusedUinRecord");
				firstLastUpdate=TroubledTransactionPage.getText("FocusedLastUpdate");
				firstResolve=TroubledTransactionPage.getText("FirstResolveRecord");
				System.out.println("First Item: "+firstItem);
				System.out.println("First Uin: "+firstUin);
				System.out.println("First LastUpdate: "+firstLastUpdate);
				System.out.println("First Resolve: "+firstResolve);		

				//Clicking on a row selector column check box to select all check box
				TroubledTransactionPage.click("RowSelectorColumnCheckBox");

				//Deselect all check box except last one for selecting last record with resolve status yes
				TroubledTransactionPage.clickMultipleRowSelectorCheckBoxes("FocusedRowSelectorCheckBox");

				//Selecting first record with resolve status no
				TroubledTransactionPage.explicitWaitForElementToBeClickable("FirstResolveRecord");
				TroubledTransactionPage.click("FirstResolveRecord");
				TroubledTransactionPage.click("FocusedRowSelectorCheckBox");

				//Scroll to table end to get last records values 
				TroubledTransactionPage.scrollToTableEnd();		

				//Getting values of a last record for verification
				lastItem=TroubledTransactionPage.getText("FocusedItemRecord");
				lastUin=TroubledTransactionPage.getText("FocusedUinRecord");
				lastLastUpdate=TroubledTransactionPage.getText("FocusedLastUpdate");
				lastResolve=TroubledTransactionPage.getText("FocusedResolveRecord");
				System.out.println("Last Item: "+lastItem);
				System.out.println("Last Uin: "+lastUin);
				System.out.println("Last LastUpdate: "+lastLastUpdate);
				System.out.println("Last Resolve: "+lastResolve);

				//Clicking on a unresolve button
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
				TroubledTransactionPage.explicitWaitForElementToBeClickable("UnResolveButton");
				TroubledTransactionPage.click("UnResolveButton");

				//Verifying unresolve pop up
				TroubledTransactionPage.explicitWaitForElementToBeClickable("ResolutionStatusMessage");
				TroubledTransactionPage.verifyTextValue("ResolutionStatusMessage",map.get("ResolutionStatusMessage"));
				TroubledTransactionPage.verifyElementIsPresent("YesButton");
				TroubledTransactionPage.verifyElementIsPresent("NoButton");

				//Clicking on a no button in resolve pop up
				TroubledTransactionPage.explicitWaitForElementToBeClickable("NoButton");
				TroubledTransactionPage.click("NoButton");
				System.out.println("Clicked on Unresolve no button sucessfully");

				//Verifying the records are still in troubled transaction list screen
				TroubledTransactionPage.explicitWaitForVisibility("TroubledTransactionListTitle");
				TroubledTransactionPage.verifyTextValue("TroubledTransactionListTitle",map.get("Title"));

				//Clicking on a refresh button
				TroubledTransactionPage.explicitWaitForElementToBeClickable("RefreshButton");
				TroubledTransactionPage.click("RefreshButton");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));

				//Verifying there is no change in selected first record having resolve status 'no'
				TroubledTransactionPage.explicitWaitForElementToBeClickable("FilterItem");
				TroubledTransactionPage.click("FilterItem");
				TroubledTransactionPage.enterIntoTextBox("FilterItem",firstItem);
				TroubledTransactionPage.enterIntoTextBox("FilterItem",firstItem);

				TroubledTransactionPage.explicitWaitForElementToBeClickable("FilterUin");
				TroubledTransactionPage.click("FilterUin");
				TroubledTransactionPage.enterIntoTextBox("FilterUin",firstUin);

				TroubledTransactionPage.explicitWaitForElementToBeClickable("FilterLastUpdate");
				TroubledTransactionPage.click("FilterLastUpdate");
				TroubledTransactionPage.enterIntoTextBox("FilterLastUpdate",firstLastUpdate);

				TroubledTransactionPage.explicitWaitForElementToBeClickable("FilterResolve");
				TroubledTransactionPage.click("FilterResolve");
				TroubledTransactionPage.enterIntoTextBox("FilterResolve",firstResolve);

				TroubledTransactionPage.explicitWaitForElementToBeClickable("GridResolveRecord");
				TroubledTransactionPage.verifyTextValue("GridResolveRecord",firstResolve);
				TroubledTransactionPage.implicitWait();

				//Verifying there is no change in selected last record having resolve status 'yes'
				TroubledTransactionPage.explicitWaitForElementToBeClickable("FilterItem");
				TroubledTransactionPage.click("FilterItem");
				TroubledTransactionPage.enterIntoTextBox("FilterItem",lastItem);

				TroubledTransactionPage.explicitWaitForElementToBeClickable("FilterUin");
				TroubledTransactionPage.click("FilterUin");
				TroubledTransactionPage.enterIntoTextBox("FilterUin",lastUin);

				TroubledTransactionPage.explicitWaitForElementToBeClickable("FilterLastUpdate");
				TroubledTransactionPage.click("FilterLastUpdate");
				TroubledTransactionPage.enterIntoTextBox("FilterLastUpdate",lastLastUpdate);

				TroubledTransactionPage.explicitWaitForElementToBeClickable("FilterResolve");
				TroubledTransactionPage.click("FilterResolve");
				TroubledTransactionPage.enterIntoTextBox("FilterResolve",lastResolve);

				TroubledTransactionPage.explicitWaitForElementToBeClickable("GridResolveRecord");
				TroubledTransactionPage.verifyTextValue("GridResolveRecord",lastResolve);
				TroubledTransactionPage.implicitWait();

				//Clicking on a refresh button
				TroubledTransactionPage.explicitWaitForElementToBeClickable("RefreshButton");
				TroubledTransactionPage.click("RefreshButton");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));				

				//Clicking on a first record
				TroubledTransactionPage.explicitWaitForElementToBeClickable("FirstResolveRecord");
				TroubledTransactionPage.click("FirstResolveRecord");
				TroubledTransactionPage.explicitWaitForElementToBeClickable("FocusedRowSelectorCheckBox");

				//Getting values of a first record for verification
				firstItem=TroubledTransactionPage.getText("FocusedItemRecord");
				firstUin=TroubledTransactionPage.getText("FocusedUinRecord");
				firstLastUpdate=TroubledTransactionPage.getText("FocusedLastUpdate");
				firstResolve=TroubledTransactionPage.getText("FirstResolveRecord");
				System.out.println("First Item: "+firstItem);
				System.out.println("First Uin: "+firstUin);
				System.out.println("First LastUpdate: "+firstLastUpdate);
				System.out.println("First Resolve: "+firstResolve);		

				//Clicking on a row selector column check box to select all check box
				TroubledTransactionPage.click("RowSelectorColumnCheckBox");

				//Deselect all check box except last one for selecting last record with resolve status yes
				TroubledTransactionPage.clickMultipleRowSelectorCheckBoxes("FocusedRowSelectorCheckBox");

				//Selecting first record with resolve status no
				TroubledTransactionPage.explicitWaitForElementToBeClickable("FirstResolveRecord");
				TroubledTransactionPage.click("FirstResolveRecord");
				TroubledTransactionPage.click("FocusedRowSelectorCheckBox");

				//Scroll to table end to get last records values 
				TroubledTransactionPage.scrollToTableEnd();		

				//Getting values of a last record for verification
				lastItem=TroubledTransactionPage.getText("FocusedItemRecord");
				lastUin=TroubledTransactionPage.getText("FocusedUinRecord");
				lastLastUpdate=TroubledTransactionPage.getText("FocusedLastUpdate");
				lastResolve=TroubledTransactionPage.getText("FocusedResolveRecord");
				System.out.println("Last Item: "+lastItem);
				System.out.println("Last Uin: "+lastUin);
				System.out.println("Last LastUpdate: "+lastLastUpdate);
				System.out.println("Last Resolve: "+lastResolve);

				//Clicking on a unresolve button
				TroubledTransactionPage.explicitWaitForElementToBeClickable("UnResolveButton");
				TroubledTransactionPage.click("UnResolveButton");

				//Clicking on a yes button in resolve pop up
				TroubledTransactionPage.explicitWaitForElementToBeClickable("YesButton");
				TroubledTransactionPage.click("YesButton");
				System.out.println("Clicked on Unresolve yes button sucessfully");

				//Clicking on refresh button 
				TroubledTransactionPage.explicitWaitForElementToBeClickable("RefreshButton");
				TroubledTransactionPage.click("RefreshButton");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));

				//Verifying there is no change in selected first record having resolve status 'no'
				TroubledTransactionPage.explicitWaitForElementToBeClickable("FilterItem");
				TroubledTransactionPage.click("FilterItem");
				TroubledTransactionPage.enterIntoTextBox("FilterItem",firstItem);
				TroubledTransactionPage.enterIntoTextBox("FilterItem",firstItem);

				TroubledTransactionPage.explicitWaitForElementToBeClickable("FilterUin");
				TroubledTransactionPage.click("FilterUin");
				TroubledTransactionPage.enterIntoTextBox("FilterUin",firstUin);

				TroubledTransactionPage.explicitWaitForElementToBeClickable("FilterLastUpdate");
				TroubledTransactionPage.click("FilterLastUpdate");
				TroubledTransactionPage.enterIntoTextBox("FilterLastUpdate",firstLastUpdate);

				TroubledTransactionPage.explicitWaitForElementToBeClickable("FilterResolve");
				TroubledTransactionPage.click("FilterResolve");
				TroubledTransactionPage.enterIntoTextBox("FilterResolve",firstResolve);

				TroubledTransactionPage.explicitWaitForElementToBeClickable("GridResolveRecord");
				TroubledTransactionPage.verifyTextValue("GridResolveRecord",firstResolve);
				TroubledTransactionPage.implicitWait();

				//Click on search icon to verify the first record
				TroubledTransactionPage.explicitWaitForElementToBeClickable("SearchIcon");
				TroubledTransactionPage.click("SearchIcon");

				//Selecting unresolve drop down value to view unresolve status records
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
				TroubledTransactionPage.explicitWaitForElementToBeClickable("ShowResolvedDropDown");
				TroubledTransactionPage.selectDropDownValue("ShowResolvedDropDown",map.get("UnresolvedDropDownValue"));
				TroubledTransactionPage.explicitWaitForElementToBeClickable("SearchButton");
				TroubledTransactionPage.click("SearchButton");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));

				//Verifying the resolve status has been changed to no for the selected last record 
				TroubledTransactionPage.explicitWaitForElementToBeClickable("FirstResolveRecord");
				TroubledTransactionPage.explicitWaitForElementToBeClickable("FilterItem");
				TroubledTransactionPage.click("FilterItem");
				TroubledTransactionPage.enterIntoTextBox("FilterItem",lastItem);

				TroubledTransactionPage.explicitWaitForElementToBeClickable("FilterUin");
				TroubledTransactionPage.click("FilterUin");
				TroubledTransactionPage.enterIntoTextBox("FilterUin",lastUin);

				TroubledTransactionPage.explicitWaitForElementToBeClickable("FilterResolve");
				TroubledTransactionPage.click("FilterResolve");
				TroubledTransactionPage.enterIntoTextBox("FilterResolve",map.get("ResolveNo"));

				TroubledTransactionPage.explicitWaitForElementToBeClickable("GridResolveRecord");
				TroubledTransactionPage.verifyTextValue("GridResolveRecord",map.get("ResolveNo"));
			}
		}
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
