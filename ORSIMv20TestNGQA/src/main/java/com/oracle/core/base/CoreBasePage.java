package com.oracle.core.base;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import java.util.logging.Logger;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.oracle.core.utils.PropertyReader;

import javafx.util.Pair;

public class CoreBasePage {

	WebDriver driver;
	static int timeOutInSeconds;
	PropertyReader propertyReader = new PropertyReader();
	public HashMap<String, By> ObjRep = new HashMap<String, By>();
	public HashMap<String, String> RelativePathRep = new HashMap<String, String>();
	public static Logger logger = Logger.getLogger(CoreBasePage.class.getName());
	SoftAssert softAssertion= new SoftAssert();
	WebDriverWait explicitWait;
	FluentWait<WebDriver> fluentWait;
   	
	//Constructor initializes WebDriver & timeOutInSeconds
	public CoreBasePage(WebDriver driver) {
		this.driver = driver;
		setTimeOut();
		System.out.println(timeOutInSeconds);		
		explicitWait = new WebDriverWait(driver,timeOutInSeconds);
		fluentWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOutInSeconds))
				.pollingEvery(Duration.ofMillis(25)).ignoring(NoSuchElementException.class);

	}

	//Adds the <logicalName, by > entry in Object Repository
	public void addObject(String logicalName, By by) {
		ObjRep.put(logicalName, by);
	}

	public void addRelativePath(String logicalName, String relativePathString) {
		RelativePathRep.put(logicalName, relativePathString);
	}

	

	public void verifyTextValue(String logicalName, String value)
	{	
		try {
		if(!value.isEmpty()) 
		{
			softAssertion.assertEquals(  getText(logicalName), value );
			logger.info("Logical name : "+ logicalName +" ---- "+"Expected Value : "+ value +" ---- "+"Actual Value : "+ getText(logicalName) );
			softAssertion.assertAll();
		}	
		else if(value.isEmpty())
		{
			verifyElementIsNotPresent(logicalName);
		}
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		}
		
	}

	//Enter text in element using logicalName
	public void enterIntoTextBox(String logicalName, String value) {
		enterText(getWebElement(logicalName), value);
		logger.info("Logical name : "+ logicalName +" ---- "+"Value Entered: "+ value);
	}

	public String  getText(String logicalName) {
		return(getText(getWebElement(logicalName)));
	}

	//Click element using logicalName
	public void click(String logicalName) {	
		
		click(getWebElement(logicalName));	
		
	}
	
	//Click element using logicalName
	public void clickByJs(String logicalName) {
		
		 clickByJS(getWebElement(logicalName));
		logger.info("---clickedbyJS "+logicalName);
	}

	public void  verifyElementIsPresent(String logicalName) {	
		softAssertion.assertTrue( isElementPresent(logicalName));
		logger.info("verifyElementIsPresent "+logicalName);
	}

	public void  verifyElementIsNotPresent(String logicalName) {	
		softAssertion.assertFalse(isElementPresent(logicalName));
		logger.info("verifyElementIsPresent "+logicalName);
	}

	public void selectValueFromDropdown(String logicalName, String value) {
		waitAndselectValueFromDropDown(getWebElement(logicalName), value);
		logger.info("selectValueFromDropdown :"+logicalName+ " -> "+value);		
		
	}

	public void pressEnterKey(String logicalName) {
		pressEnterKey(getWebElement(logicalName));	
	}
	
	
	private void pressEnterKey(WebElement element) {
		element.sendKeys(Keys.ENTER);
	}
	
	
	public void verifyCheckBoxIsSelected(String logicalName ) {	
		logger.info("Verify Check Box is selected:"+logicalName);
		explicitWaitForVisibility(logicalName);
		softAssertion.assertTrue(getWebElement(logicalName).isSelected());
	}

	
	public void verifyCheckBoxIsNotSelected(String logicalName) {	
		logger.info("Verify Check Box is selected:"+logicalName);
		explicitWaitForVisibility(logicalName);
		softAssertion.assertFalse(getWebElement(logicalName).isSelected());
	}
	
	public void selectCheckBox(String logicalName,String value ) {	
		if(value.equalsIgnoreCase("Yes")) {
			logger.info("Selected Check Box :"+logicalName+ " -> "+value);	
			explicitWaitForElementToBeClickable(logicalName);
		click(getWebElement(logicalName));	
	}}
	
	public void deselectCheckBox(String logicalName,String value ) {	
		if(value.equalsIgnoreCase("No")) {

			logger.info("De-Selected Check Box :"+logicalName+ " -> "+value);
			explicitWaitForElementToBeClickable(logicalName);
			click(getWebElement(logicalName));	
		}}
	

	
	public void scrollToView(String logicalName) {
		scrollToView(getWebElement(logicalName));
	}

	public String getProperty(String propertyName) {
		String propertyValue=null;
		try {
			propertyValue=propertyReader.getApplicationproperty(propertyName);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return propertyValue;
	}
	
	public String getTestProperty(String propertyName) {
		String propertyValue=null;
		try {
			propertyValue=propertyReader.getApplicationproperty(propertyName);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return propertyValue;
	}
	
	
	
	public void  setTestproperty(String propertyName, String propertyValue) {
		
		try {
		propertyReader.updateTestproperty(propertyName,propertyValue );
		
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void verifyEmptyValue(String logicalName)
	{	
		softAssertion.assertEquals(  "", getText(logicalName) );
		logger.info("Logical name : "+ logicalName +" ---- "+"Expected Value : "+" "+" ---- "+"Actual Value : "+ getText(logicalName) );

	}


	public void clickTableRowUsingColumnValue(String logicalName, String value) {
		explicitWaitForVisibility(logicalName) ;
		//waitForDisplayed(logicalName, timeOutInSeconds);
	
		List <WebElement> searchResultsList=getWebElementList(logicalName);
		System.out.println(searchResultsList.size());

		//	Iterator<WebElement> itr = searchResultsList.iterator();

		for(int i=0; i<searchResultsList.size(); i++) {
			WebElement row =searchResultsList.get(i) ;
			
			logger.info("Logical name : "+ logicalName +" ---- "+"Expected Value : "+value+" ---- "+"Actual Value : "+ row.getText());

			if(row.getText().equalsIgnoreCase(value)) {
				logger.info("Matched Logical name : "+ logicalName +" ---- "+"Expected Value : "+value+" ---- "+"Actual Value : "+ row.getText());
				
				row.click();
				break;
			}				
		}	
	}


	public void acceptAlert() {
		try {
		
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (Exception e) {

		}
	}

	

	 /* public void implicitWait() {
	    	driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
	    }*/


	  
	  public String  fetchValueFromTableRow(String logicalName,  String value, String relativePathLogicalName ) {
		  WebElement row=FetchRowAndIndexFromTable(logicalName,value);
		  String columnValue=row.findElement(By.xpath(RelativePathRep.get(relativePathLogicalName))).getText();
		  return columnValue;
	  }
	  
	  public void  verifyValueFromTableRow(String logicalName,  String value, String relativeColumnName, String columnValue  ) {
		  WebElement row=FetchRowAndIndexFromTable(logicalName,value);
		  softAssertion.assertEquals(row.findElement(By.xpath(RelativePathRep.get(relativeColumnName))).getText(), columnValue);
		  //return columnValue;
	  }
	  
	  
	  public void clickOnTableRow(String logicalName,  String value, String relativeColumnName, String columnValue  ) {
		  WebElement row=FetchRowAndIndexFromTable(logicalName,value);
		  row.findElement(By.xpath(RelativePathRep.get(relativeColumnName))).click();
		  //return columnValue;
	  }
	  
	  
	  
	  public void clickOnTableRow(String logicalName,  String value ) {
		  WebElement row=FetchRowAndIndexFromTable(logicalName,value);
		  row.click();
	  }
	  
		public WebElement FetchRowAndIndexFromTable( String logicalName,  String value )
		{ 
			WebElement row =null;
			
		List <WebElement> searchResultsList=getWebElementList(logicalName);
		System.out.println(searchResultsList.size());
		for(int i=0; i<searchResultsList.size(); i++) {
			
		 row =searchResultsList.get(i) ;
			logger.info("Logical name : "+ logicalName +" ---- "+"Expected Value : "+value+" ---- "+"Actual Value : "+ row.getText());
			
			if(row.getText().equalsIgnoreCase(value)) {
				
				logger.info("Matched Logical name : "+ logicalName +" ---- "+"Expected Value : "+value+" ---- "+"Actual Value : "+ row.getText());
				return row;
				//break;		
				
			}}
		
		return row;
		}
		
		public void AssertAll() {
			softAssertion.assertAll();
		}
		

	  

	//---------------------------------------------------------------private methods--------------------------------------------------------------------	

	private int setTimeOut() {
		try {
			if (propertyReader.getApplicationproperty("timOutInSeconds") == null) {
				timeOutInSeconds = 30;
			} else {
				timeOutInSeconds = Integer.parseInt(propertyReader.getApplicationproperty("timOutInSeconds"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("Aplication  time out  is set to =" + timeOutInSeconds + "seconds");
		return timeOutInSeconds;
	}


	private void enterText(WebElement element, String text) {
		try {
			waitForDisplayed(element, timeOutInSeconds);
			element.clear();
			Thread.sleep(50);
			System.out.println("Inside set Text after Clear");
			element.sendKeys(text);

		} catch (UnhandledAlertException ar) {
			driver.switchTo().alert().dismiss();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private boolean click(WebElement element) throws Error {
		try {
			waitForDisplayed(element, timeOutInSeconds);
			if (isDisplayed(element)) {
				element.click();
				
				return true;
			}
			return false;
		} catch (UnhandledAlertException ar) {
			driver.switchTo().alert().dismiss();
			return false;
		} catch (Exception e) {
			return false;
		}

	}

	private String getText( WebElement element){
		String text="";
		try {
			waitForDisplayed(element, timeOutInSeconds);
			if(!(element.getText().isEmpty())) {
				text=element.getText();		
			}
		} catch (UnhandledAlertException ar) {
			driver.switchTo().alert().dismiss();
		} catch (Exception e) {

		}
		return text;
	}

	private boolean isDisplayed(WebElement element) throws Error {
		try {
			return element.isDisplayed();
		} catch (UnhandledAlertException ar) {
			driver.switchTo().alert().dismiss();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean isElementPresent(String logicalName){
		try{
			if(getWebElement(logicalName)!=null)
			{
				return true;
			}
		} catch(org.openqa.selenium.NoSuchElementException e){

		} catch(org.openqa.selenium.TimeoutException e){

		} catch(NullPointerException e){

		} catch(org.openqa.selenium.StaleElementReferenceException e) {

		}
		return false;
	}


	private void scrollToView(WebElement element) {
		try {
			/*JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].scrollIntoView(true);", element);*/
			// element.scrollIntoView();


			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", element);
		} catch (UnhandledAlertException ar) {
			driver.switchTo().alert().dismiss();
		}
	}


	//Returns the WebElement corresponding to a logicalName
	private WebElement getWebElement(String logicalName) {
		By by = null;
		WebElement element = null;
		//To fetch By value corresponding to logicalName from Object Repository
		try {
			if (ObjRep.get(logicalName) != null) {
				by = ObjRep.get(logicalName);
			} else {
				logger.info(logicalName + "  is not present in the given page class, PLease check");
			}
		} catch (NullPointerException ne) {
			//ne.printStackTrace();
			logger.info(logicalName + "  is not present in the page");
		}

		try {
			//Wait for element to be displayed for timeOutInSeconds seconds
			waitForDisplayed(logicalName, timeOutInSeconds);
			//Finds the WebElement corresponding to By values
			if (driver.findElement(by) != null) {
				element = driver.findElement(by);
			} else {
				//if null is returned in WebElement
				logger.info("Unable to do findElement opertation on object -" + logicalName);
				Assert.fail("Unable to do findElement opertation on object -" + logicalName);
			}
		}catch(org.openqa.selenium.NoSuchElementException e) {
			//e.printStackTrace();
			logger.info("NoSuchElementException:  "+logicalName + "  is not present");
		}catch(org.openqa.selenium.TimeoutException e) {
			//e.printStackTrace();
			logger.info("TimeoutException: "+ logicalName + "  is not shown in given time");       
		}catch(org.openqa.selenium.StaleElementReferenceException e) {
			logger.info("StaleElementReferenceException: "+ logicalName + " is not shown in given time");  
		}
		return element;
	}



	public List <WebElement> getWebElementList(String logicalName) {
		By by = null;
		List <WebElement> element = null;

		//To fetch By value corresponding to logicalName from Object Repository
		try {
			if (ObjRep.get(logicalName) != null) {
				by = ObjRep.get(logicalName);
			} else {
				logger.info(logicalName + "is not present in the given page class, PLease check");
			}

		} catch (NullPointerException ne) {
			//ne.printStackTrace();
			logger.info(logicalName + "is not present in the page");
		}

		//Wait for element to be displayed for timeOutInSeconds seconds
		waitForDisplayed(logicalName, timeOutInSeconds);

		//Finds the WebElement corresponding to By values
		if (driver.findElement(by) != null) {
			element = driver.findElements(by);
		} else {
			//if null is returned in WebElement
			logger.info("Unable to do findElement opertation on object -" + logicalName);
			Assert.fail("Unable to do findElement opertation on object -" + logicalName);

		}

		return element;
	}


	private WebElement waitForDisplayed(final String logicalName, int timeOutSeconds) {

	

		return  fluentWait.ignoring(WebDriverException.class).ignoring(NoSuchElementException.class).
				until(new Function<WebDriver, WebElement>() {
					public WebElement apply(WebDriver driver) {
						return driver.findElement(ObjRep.get(logicalName)).isDisplayed() ? driver.findElement(ObjRep.get(logicalName)) : null;
					}
				});				
	}


	private WebElement waitForDisplayed(final WebElement webElement, int timeOutInSeconds) {	

	

		return  fluentWait.ignoring(WebDriverException.class).ignoring(NoSuchElementException.class).
				until(new Function<WebDriver, WebElement>() {
					public WebElement apply(WebDriver driver) {
						return webElement.isDisplayed() ? webElement: null;
					}
				});
	}

	private void waitAndselectValueFromDropDown(WebElement element, String value) {
		try {

			waitForDisplayed(element, timeOutInSeconds);
			waitForValuesToLoadinDropdown(element, timeOutInSeconds);
			Select select = new Select(element);
			select.selectByVisibleText(value);

		} catch (UnhandledAlertException ar) {
			driver.switchTo().alert().dismiss();
		}
	}

	private WebElement waitForValuesToLoadinDropdown(final WebElement webElement, int timeOutInSeconds) {
		final Select droplist = new Select(webElement);
	
		return fluentWait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver d) {
				return (!droplist.getOptions().isEmpty() ? webElement : null);
			}
		});

	}
	
	private By getByObject(String logicalName) {
		By by = null;
		WebElement element = null;
		//To fetch By value corresponding to logicalName from Object Repository
		try {
			if (ObjRep.get(logicalName) != null) {
				by = ObjRep.get(logicalName);
				return by;
			} else {
				logger.info(logicalName + "  is not present in the given page class, PLease check");
			}
		} catch (NullPointerException ne) {
			//ne.printStackTrace();
			logger.info(logicalName + "  is not present in the page");
		}
		return by;
	}
	
	public void explicitWaitForVisibility(String logicalName) {
	   //WebDriverWait wait = new WebDriverWait(driver,timeOutInSeconds);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByObject(logicalName)));
	}
	

	public void explicitWaitForInvisibility(String logicalName) {
	    explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByObject(logicalName)));
	}
	
	
	/*
	    public void waitForPageLoad(WebDriver driver) {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(pageLoadCondition);
    }*/
    
 
    private boolean clickByJS(WebElement element) {

    	try {
    		waitForDisplayed(element, timeOutInSeconds);
    		JavascriptExecutor executor = (JavascriptExecutor)driver;
    		executor.executeScript("arguments[0].click();", element);
    		return true;
    	} catch (UnhandledAlertException ar) {
    		driver.switchTo().alert().dismiss();
    		
    	}
    	return false;
    }

    
    public void verifyPartialTextValue(String logicalName, String value) throws InterruptedException
    {    
        try {
    	if(!value.isEmpty()) {
            Thread.sleep(10);
            softAssertion.assertEquals(  getText(logicalName).contains(value), value );
            System.out.println(getText(logicalName).contains(value));
            //logger.info("Logical name : "+ logicalName +" ---- "+"Expected Value : "+ value +" ---- "+"Actual Value : "+ getText(logicalName) );
            //softAssertion.assertAll();
        }    
        else if(value.isEmpty()) {
            verifyElementIsNotPresent(logicalName);
        }
        }
        catch (org.openqa.selenium.NoSuchElementException e)
        {
			e.printStackTrace();
		}
    }
    


    public void selectDate(String datePickerYearMonth,String datePickerDays, String previousMonthButton, String nextMonthButton, String desiredDate){
        // day, month , year
        Date current = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        try {

            Date selected = sd.parse(desiredDate);

            String day = new SimpleDateFormat("dd").format(selected);

            String month = new SimpleDateFormat("MMM").format(selected);

            String year = new SimpleDateFormat("yyyy").format(selected);

            System.out.println(day+" --- "+month+" --- "+ year);

            String desiredYearMonth=year+" "+month;

            while(true){

                String displayedYearMonth=getWebElement(datePickerYearMonth).getText();

                if(desiredYearMonth.equals(displayedYearMonth)){

                    // select the day
                      String desiredDay=RelativePathRep.get("datePickerDays")+"[@tabindex=0 and text()='"+Integer.parseInt(day)+"']";
                    driver.findElement(By.xpath(desiredDay)).click();

                    break;

                }else{

                    

                    if(selected.compareTo(current) > 0)
                    	
                    	getWebElement("nextMonthButton").click();


                    else if(selected.compareTo(current) < 0)

                    	getWebElement("previousMonthButton").click();



                }

            }



        } catch (ParseException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }

        

    }
	public void explicitWaitForElementToBeClickable(String logicalName) {
		   //WebDriverWait wait = new WebDriverWait(driver,timeOutInSeconds);
			explicitWait.until(ExpectedConditions.elementToBeClickable(getByObject(logicalName)));
		}


	  public void implicitWait() {
	    	driver.manage().timeouts().implicitlyWait(timeOutInSeconds, TimeUnit.SECONDS);
	    }
}









