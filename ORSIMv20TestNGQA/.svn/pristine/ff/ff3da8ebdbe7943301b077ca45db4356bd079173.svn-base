package com.oracle.sim.testcases.Subbuckets;
/**
 * * @author dsorthiy
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

public class SubBuckets {

	public static Logger logger = Logger.getLogger(SubBuckets.class.getName());
	protected static PropertyReader propReader = new PropertyReader();
	PageFactory pageFactory = new PageFactory();
	SimBasePage LoginPage;
	SimBasePage HomePage;
	//SimBasePage SubBuckets;
	SimBasePage SubBucketsPage;

	

	@BeforeClass()
	public void setup(ITestContext context) throws Exception {
		logger.info("TestCase Name: " +logger.getName());
		SIMWebdriverBase.loadInitialURL(context);
		
		LoginPage = pageFactory.initialize("LoginPage");
		HomePage = pageFactory.initialize("HomePage");
		SubBucketsPage = pageFactory.initialize("SubBucketsPage");
		LoginPage.explicitWaitForVisibility("Username");
		LoginPage.enterIntoTextBox("Username", LoginPage.getProperty("Username"));
		LoginPage.enterIntoTextBox("Password", LoginPage.getProperty("Password"));
		LoginPage.click("SignIn");
		HomePage = pageFactory.initialize("HomePage");
		HomePage.verifyTextValue("Title", HomePage.getProperty("Title"));
		HomePage.verifyTextValue("UserMenu", HomePage.getProperty("Username"));
		HomePage.storeIdCheck();

		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.click("Navigation");
		HomePage.click("Admin");
		HomePage.click("DataSetup");
		// Go to Shipment Reason Setup Page
		HomePage.click("SubBucket");

	}
	//DataProvider for UI & Navigation 
	@DataProvider(name = "UISubbucketTestData")
	public Object[][] getTestDataForUI() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("SubBucketsTestData"),"UISubbucket");
		return testObjArray;
	}
	
	//UI verification for Sub-Bucket Screen
	@Test(dataProvider = "UISubbucketTestData", priority = 1)
	public void uiSubbucket(Map<String, String> map) throws Exception {
		
	
		SubBucketsPage = pageFactory.initialize("SubBucketsPage");
		System.out.println("Verify the Title of Sub-Buckets Screen");
		SubBucketsPage.explicitWaitForVisibility("Title");//verify the Title of Sub-Buckets
		
		String title = map.get("Title");
		SubBucketsPage.verifyTextValue("Title", map.get("Title"));
		
		
		
		//Verify the Refresh Button
		System.out.println("Verify the Refresh Button");
		SubBucketsPage.verifyElementIsPresent("RefreshButton");
		//Verify Add Button
		System.out.println("Verify the Add Button");
		SubBucketsPage.verifyElementIsPresent("AddIcon");
		//Verify Delete Button
		System.out.println("Verify the Delete Button");
		SubBucketsPage.verifyElementIsPresent("DeleteIcon");
		System.out.println("Verify the Grid View Menu");
		SubBucketsPage.verifyElementIsPresent("Grid View Menu");
		System.out.println("Verify the Apply Button");
		SubBucketsPage.click("AddIcon");
		SubBucketsPage.verifyElementIsPresent("ApplyButton");
		System.out.println("Verify the Cancel Button");
		SubBucketsPage.verifyElementIsPresent("CancelButton");
		SubBucketsPage.click("CancelButton");
		
	
	}

	//DataProvider for Add Sub-Bucket 
	@DataProvider(name = "AddSubbucketTestData")
	public Object[][] getTestDataForSubbucket() throws Exception {
		Object[][] testObjArray = ExcelReader.loadExcel(propReader.getApplicationproperty("SubBucketsTestData"),"AddSubbucket");
		return testObjArray;
	}
	
	//Add Sub-Bucket 
	@Test(dataProvider = "AddSubbucketTestData", priority = 2)
	public void addSubbucket(Map<String, String> map) throws Exception {
		SubBucketsPage = pageFactory.initialize("SubBucketsPage");
		//SubBuckets.explicitWaitForElementToBeClickable("Title");
		
		SubBucketsPage.click("AddIcon");
		// Input Values in Detail section
		SubBucketsPage.explicitWaitForElementToBeClickable("Description");
		SubBucketsPage.enterIntoTextBox("Description", map.get("Description"));
		SubBucketsPage.click("ApplyButton");
		SubBucketsPage.explicitWaitForElementToBeClickable("SaveIcon");
		SubBucketsPage.click("SaveIcon");
		
		SubBucketsPage.explicitWaitForVisibility("Confomration");
		SubBucketsPage.verifyTextValue("Confomration", "Confirmation");
		// Click Yes in Confirmation pop up
		SubBucketsPage.verifyElementIsPresent("YesButton");
		SubBucketsPage.explicitWaitForElementToBeClickable("YesButton");
		
		SubBucketsPage.click("YesButton");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		
		// Refreshing the page
		 SubBucketsPage.explicitWaitForElementToBeClickable("RefreshButton");
		SubBucketsPage.click("RefreshButton");

	}

		//DataProvider for Search Sub-Bucket 
	  @DataProvider(name = "SearchSubbucketTestData") 
	  public Object[][] getSubucketData() throws Exception { Object[][] testObjArray =
	  ExcelReader.loadExcel(propReader.getApplicationproperty("SubBucketsTestData"), "AddSubbucket"); 
	  return testObjArray; 
	  }
	  
	  //Search Sub-Bucket 
	  @Test(dataProvider = "SearchSubbucketTestData", priority = 3) 
	  public void searchSubbucket(Map<String, String> map) throws Exception {
	  
	  SubBucketsPage.explicitWaitForElementToBeClickable("Title");
	  SubBucketsPage.explicitWaitForElementToBeClickable("AddIcon");
	  SubBucketsPage.explicitWaitForElementToBeClickable("RefreshButton"); 
	  SubBucketsPage.explicitWaitForElementToBeClickable("DeleteIcon");
	  
	  // Verifying the inserted record
	  SubBucketsPage.explicitWaitForElementToBeClickable("FilterName");
	  SubBucketsPage.click("FilterName"); 
	  SubBucketsPage.enterIntoTextBox("FilterName",map.get("Description"));
	  System.out.println(SubBucketsPage.getText("GridRecord"));
	  SubBucketsPage.verifyTextValue("GridRecord", map.get("Description"));
	  
	  }
	  
	  // Data provider for Edit SubBucket
	  @DataProvider(name = "EditSubbucketData") public Object[][]
	  getEditSubbucketData() throws Exception { Object[][] testObjArray =
	  ExcelReader.loadExcel(propReader.getApplicationproperty("SubBucketsTestData"),"EditSubbucket");
	  return testObjArray; 
	  }
	  //Edit Sub-Bucket 
	  @Test(dataProvider = "EditSubbucketData", priority = 4) 
	  public void subbucketEdit(Map<String, String> map) throws Exception {
		  logger.info("Inside the Edit SubBucket Method..!");
	  SubBucketsPage.explicitWaitForElementToBeClickable("GridRecord");
	  SubBucketsPage.click("GridRecord");
	  SubBucketsPage.implicitWait();
	  SubBucketsPage.explicitWaitForElementToBeClickable("EditButton"); 
	  Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
	  SubBucketsPage.click("EditButton");
	  
	  SubBucketsPage.explicitWaitForElementToBeClickable("Description");
	  SubBucketsPage.enterIntoTextBox("Description", map.get("Description"));
	  SubBucketsPage.click("ApplyButton");
	  SubBucketsPage.explicitWaitForElementToBeClickable("SaveIcon");
	  SubBucketsPage.click("SaveIcon");
	  Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
	  // Click Yes in Confirmation pop up 
	  SubBucketsPage.explicitWaitForElementToBeClickable("YesButton");
	  SubBucketsPage.click("YesButton");
	  Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
	  SubBucketsPage.explicitWaitForElementToBeClickable("RefreshButton");
	  SubBucketsPage.click("RefreshButton"); 
	  
	  SubBucketsPage.explicitWaitForElementToBeClickable("FilterName");
	  SubBucketsPage.click("FilterName");
	  SubBucketsPage.enterIntoTextBox("FilterName",map.get("Description"));
	  logger.info("Sub-Bucket Description entered in Filter column for searching is " +SubBucketsPage.getText("GridRecord")); 
	  SubBucketsPage.verifyTextValue("GridRecord",map.get("Description"));
	  
	  }
	  //Delete the Sub-Bucket
	  @Test(dataProvider = "EditSubbucketData", priority = 5) 
	  public void subbucketDelete(Map<String, String> map) throws Exception {
	  logger.info("inside delete method");
		 
	  		SubBucketsPage.explicitWaitForElementToBeClickable("GridRecord");
			SubBucketsPage.click("GridRecord");
			SubBucketsPage.click("DeleteIcon");
			SubBucketsPage.implicitWait();
			SubBucketsPage.click("YesButton");
			SubBucketsPage.explicitWaitForElementToBeClickable("SaveIcon");
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
			SubBucketsPage.click("SaveIcon");
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
			
			//Click Yes in Confirmation pop up
			SubBucketsPage.click("YesButton");
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
			SubBucketsPage.explicitWaitForElementToBeClickable("RefreshButton");
		
			SubBucketsPage.click("RefreshButton");
			
			SubBucketsPage.explicitWaitForElementToBeClickable("FilterName");	
			SubBucketsPage.click("FilterName");
			SubBucketsPage.enterIntoTextBox("FilterName", map.get("Description"));
			//SubBuckets.explicitWaitForVisibility("GridRecord");
			//SubBuckets.verifyElementIsNotPresent("GridRecord");
			SubBucketsPage.explicitWaitForVisibility("NorecordsMsg");
			SubBucketsPage.verifyTextValue("NorecordsMsg",map.get("EM2"));
		 
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
