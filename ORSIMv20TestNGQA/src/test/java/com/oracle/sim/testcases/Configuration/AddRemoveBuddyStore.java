package com.oracle.sim.testcases.Configuration;

import java.util.Map;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class AddRemoveBuddyStore {
	public static Logger logger = Logger.getLogger(AddRemoveBuddyStore.class.getName());
	protected static PropertyReader propReader= new PropertyReader();
	PageFactory pageFactory = new PageFactory();
	static WebDriver driver;
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
	
	@Test(dataProvider="AddBuddyStoreTestData",priority=1)
	public void AddBuddyStore(Map<String, String> map)throws Exception {
		
		HomePage = pageFactory.initialize("HomePage");
		HomePage.explicitWaitForElementToBeClickable("Navigation");
		HomePage.click("Navigation");
		HomePage.storeIdCheck();
		HomePage.click("Admin");
		HomePage.click("ConfigurationMenu");
		HomePage.click("BuddyStoreOption");
		
		BuddyStore = pageFactory.initialize("BuddyStore");
		
		
		//Adding the buddy store.
		BuddyStore = pageFactory.initialize("BuddyStore");
		String value=map.get("BuddyStore");
		Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		String TitleNw=BuddyStore.getText("Title");
		System.out.println(TitleNw);
		String SplicedTitle=TitleNw.replace("Buddy Stores:", "");
		BuddyStore.clkBuddyStore(value,SplicedTitle);
		BuddyStore.explicitWaitForElementToBeClickable("AssignSelected");
		BuddyStore.click("AssignSelected");
		try{
		WebElement ErrorDilog=driver.findElement(By.xpath(("//oj-dialog[@id='sim-main-message-dialog']")));
		if(ErrorDilog.isDisplayed()){
			BuddyStore.click("ErrorDialogOKButon");
		}
		}catch(NullPointerException e) {
			logger.info("The error Dialog was not present");
			BuddyStore.implicitWait();
			BuddyStore.click("Save");
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
			BuddyStore.click("Yes");
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
//			BuddyStore.implicitWait();
			logger.info("Buddy store addded successfully");
			
			//Validation for the buddy store added
			String displayedBudyStre=BuddyStore.getText("Title");
			String userValueBudystre=map.get("Store Number");
			String Title=map.get("Title");
			String validBudyStr=Title+" "+userValueBudystre+ " "+ "/";
			BuddyStore.checkBuddystore(validBudyStr,displayedBudyStre);
		}
		
	}
		@Test(dataProvider="AddBuddyStoreTestData", priority=2)
		public void RemoveBuddyStore(Map<String, String> map)throws Exception {
			
			
			//Removing the buddy store added
			BuddyStore = pageFactory.initialize("BuddyStore");
			String value=map.get("BuddyStore");
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
			String TitleNw=BuddyStore.getText("Title");
			System.out.println(TitleNw);
			String SplicedTitle=TitleNw.replace("Buddy Stores:", "");
			BuddyStore.clkRemoveBuddyStore(value,SplicedTitle);
			BuddyStore.click("RevokeSelected");
			try{
				WebElement ErrorDilog=driver.findElement(By.xpath(("//oj-dialog[@id='sim-main-message-dialog']")));
				if(ErrorDilog.isDisplayed()){
					BuddyStore.click("ErrorDialogOKButon");
				}}catch(NullPointerException e) {
					logger.info("The error Dialog was not present");
			BuddyStore.implicitWait();
			BuddyStore.click("Save");
			BuddyStore.implicitWait();
			BuddyStore.click("Yes");
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
			BuddyStore.click("Refresh");
			logger.info("Buddy store removed successfully");
			
			//validation for buddy store remove
			String displayedBudyStre=BuddyStore.getText("Title");
			String userValueBudystre=map.get("Store Number");
			String Title=map.get("Title");
			String validBudyStr=Title+" "+userValueBudystre+ " "+ "/";
			BuddyStore.checkBuddystore(validBudyStr,displayedBudyStre);
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



