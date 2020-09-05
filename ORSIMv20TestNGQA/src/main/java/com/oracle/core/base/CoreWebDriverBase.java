package com.oracle.core.base;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.testng.ITestContext;

import com.oracle.core.utils.PropertyReader;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.WebDriverManagerException;

import static com.oracle.core.utils.Constants.CHROME_DRIVER_PATH;
import static com.oracle.core.utils.Constants.FIREFOX_DRIVER_PATH;
import static com.oracle.core.utils.Constants.IE_DRIVER_PATH;




public class CoreWebDriverBase {
	
	protected static WebDriver driver;
	static String sUrl = null;
	protected static PropertyReader propReader = new PropertyReader();
	
	protected static WebDriver createFirefoxDriver() {
	//	System.setProperty("webdriver.gecko.driver", FIREFOX_DRIVER_PATH);
		
		try {
			WebDriverManager.firefoxdriver().setup();
		} 
		catch (WebDriverManagerException e) {
			WebDriverManager.firefoxdriver().proxy("www-proxy.us.oracle.com:80").setup();
		}
		
		driver = new FirefoxDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		return driver;
	}
	
	protected static WebDriver createChromeDriver() throws MalformedURLException {
	System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
		 ChromeOptions options = new ChromeOptions();
		 options.addArguments("enable-automation");
		 options.addArguments("--headless");
		 options.addArguments("--window-size=1920,1080");
		
		 options.addArguments("--no-sandbox");
		 options.addArguments("--disable-extensions");
		 options.addArguments("--dns-prefetch-disable");
		 options.addArguments("--disable-gpu");
		 options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		 
		/*
		try {
			WebDriverManager.chromedriver().setup();
		} 
		catch (WebDriverManagerException e) {
			WebDriverManager.chromedriver().proxy("www-proxy.us.oracle.com:80").setup();
		}
		*/
		
		 
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		
	/*	URL gridUrl = new URL("http://localhost:4444/wd/hub");

        ChromeOptions chromeOptions = new ChromeOptions();
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities(chromeOptions);
     
        RemoteWebDriver driver = new RemoteWebDriver(gridUrl, desiredCapabilities);
        driver.manage().window().maximize();*/
        
		return driver;
	}
	
	protected static WebDriver createInternetExplorerDriver() {
	//	System.setProperty("webdriver.ie.driver", IE_DRIVER_PATH);
		
		try {
			WebDriverManager.iedriver().setup();
		} 
		catch (WebDriverManagerException e) {
			WebDriverManager.iedriver().proxy("www-proxy.us.oracle.com:80").setup();
		}
		driver = new InternetExplorerDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		return driver;
	}
	
	public static void loadInitialURL(ITestContext context) throws Exception {	
		String sUrl = getAppURLNew();
		driver = getCurrentDriver();
		context.setAttribute("driver", driver);
		driver.get(sUrl);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	protected static String getAppURLNew() {		
		try {
			sUrl = propReader.getApplicationproperty("url").trim();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sUrl;
	}
	
	public synchronized static WebDriver getCurrentDriver() throws Exception {
		if (driver == null) {			
			try {
				String sBrowserName = propReader.getApplicationproperty(
						"browser.type").trim();	
				if (sBrowserName.trim().equalsIgnoreCase("firefox")) {
					driver = createFirefoxDriver();
				} 
				else if (sBrowserName.trim().equalsIgnoreCase("chrome")) {
					driver = createChromeDriver();
				} 
				else if (sBrowserName.trim().equalsIgnoreCase("ie")) {
					driver = createInternetExplorerDriver();
				} 
				else {
					System.out
					.println("Please define the browser type = as firefox or ie and remoteMode = true/false in application.properties inside properties folder");
				}	
			} finally {
				System.out.println("Browser successfully launched");
			}
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	public static void close() {
		try {
			//driver.close();
			driver.quit();
			driver = null;
		} catch (UnreachableBrowserException e) {

		}
	}


	

}
