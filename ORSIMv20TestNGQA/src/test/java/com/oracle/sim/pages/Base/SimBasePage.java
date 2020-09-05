package com.oracle.sim.pages.Base;

import static org.testng.Assert.assertTrue;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.exec.util.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import org.testng.internal.ResultMap;
import java.text.Collator;


import com.oracle.core.base.CoreBasePage;
import com.oracle.core.utils.PropertyReader;
import com.oracle.sim.pageFactory.PageFactory;
import com.oracle.sim.utils.SIMWebdriverBase;

public class SimBasePage extends CoreBasePage {

	private static String obtaineduin=null;
	private static int status = 0;
	static WebDriver driver;
	String headingXpath;
	String textBoxXpath;
	String dropDownBoxXpath;
	String checkBoxXpath;
	String labelValueXpath;
	SimBasePage InventoryAdjustmentPage;
	SimBasePage CoreBasePage;
	protected static PropertyReader propReader = new PropertyReader();
	SoftAssert softAssertion = new SoftAssert();
	private static Connection connection = null;
	private static Statement stmt =null;
	private static PreparedStatement prepStatement = null;
	private static ResultSet rs = null;	
	private static ResultSet rs3 = null;
	
	public SimBasePage() throws Exception {
		super(driver =SIMWebdriverBase.getCurrentDriver());
	}

	//Establish db connection	
	public Connection getConnection() {
		try {
			String jdbcDriver = propReader.getApplicationproperty("JdbcDriver");
			final String url = propReader.getApplicationproperty("DBUrl");
			final String userName = propReader.getApplicationproperty("DBUserName");
			final String pwd = propReader.getApplicationproperty("DBPassword");
			
			if (jdbcDriver != null && url != null && userName != null && pwd != null) {
				Class.forName(jdbcDriver);

				connection = DriverManager.getConnection(url, userName, pwd);

				connection.setAutoCommit(false);

			}

		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(connection!=null) {
			logger.info("DB connection established");
		}else {
			logger.info("DB connection not established");
		}
		return connection;
	}

	//Generic update query
	public void executeUpdate(String query) {
		try {
			if(connection != null) {
				stmt = connection.createStatement();
				int rowCount=stmt.executeUpdate(query);
				connection.commit();
				if(rowCount>0) {
					System.out.println("Updated successfully.No of rows affected:  "+rowCount);
				}
			}else {
				logger.info("DB connection not established");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	//Generic select query
		public ResultSet executeSelect(String query) {
			try {
				if(connection != null) {
					stmt = connection.createStatement();
					rs=stmt.executeQuery(query);
				}else {
					logger.info("DB connection not established");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return rs;	
		}
		
	//Method to verify total number of values filtered in the summary table with the number of records available in the database.
		public void resultCountVerification(String query, String ExpectedValueXpath) {
			try {
				String ResultText = driver.findElement(ObjRep.get(ExpectedValueXpath)).getText();
//				String Total= ResultText.replaceAll("[^0-9\\:]", "");
				String Total = ResultText.substring(9);
				int TotalValue = Integer.parseInt(Total);
				System.out.println("Total number of records available in application"  +TotalValue);
				
				if(connection != null) {
					stmt = connection.createStatement();
					rs=stmt.executeQuery(query);
					
					while (rs.next()){
						int count = rs.getInt(1);
	//					System.out.println(count);
						Assert.assertEquals(TotalValue,count);
					}
					
				}else {
					logger.info("DB connection not established");
				}
			} 
		catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
	//Specific for Area 
		public void specificAreaQuery(int statusValue,String query) {
			try {
				if(connection != null) {
					int status = 0;
					ResultSet s=executeSelect(query);
					if(s.next()) {
						int id = rs.getInt("id");		
						int itemBasketId = s.getInt("Item_Basket_Id");		
						String description = s.getString("description");		
						int createStoreId = s.getInt("Create_store_id");		
						status = s.getInt("Status");
						int type = s.getInt("type");
						Date CreateDate = s.getDate("Create_Date");
						Date updateDate = s.getDate("Update_Date");
						String createUser = s.getString("Create_User");
						String updateUser = s.getString("Update_User");
						System.out.println("Id: "+id);
						System.out.println("Item Basket Id: "+itemBasketId);
						System.out.println("description: "+description);
						System.out.println("createStoreId: "+createStoreId);
						System.out.println("status: "+status);
						System.out.println("type: "+type);
						System.out.println("CreateDate: "+CreateDate);
						System.out.println("updateDate: "+updateDate);
						System.out.println("createUser: "+createUser);
						System.out.println("updateUser: "+updateUser);
					}		
					if(status==statusValue) {
						System.out.println("The Basket is confirmed");
					}
				}else {
					logger.info("DB connection not established");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
			
		public void specificSupplierQuery(String query) {
			if(connection != null) {
				ResultSet s=executeSelect(query);
				
			}
		}
		
		
	//get a specific UIN with a particular status
	 public String specificUINQuery(String query) {
					try {
						if(connection != null) {
							
							ResultSet s=executeSelect(query);
							if(s.next()) {
							obtaineduin = rs.getString("uin");	
							System.out.println("Id: "+obtaineduin);
								
							}		
						}else {
							logger.info("No record found for");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					return obtaineduin;
					
	}
	 
	 
	 
	//get a specific UIN with and particular status
		 public int specificUINQueryForStatus(String query) {
						try {
							if(connection != null) {
								
								ResultSet s=executeSelect(query);
								if(s.next()) {
									status = rs.getInt("Status");
									System.out.println("Status: "+status);
									
								}		
							}else {
								
								logger.info("No records found");
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						return status;
						
		}
		 
	//Create UIN for any reason and the item passed
		 public String CreateUin(String reasoncode,String Item) throws NumberFormatException, InterruptedException, IOException {
			 PageFactory pagefactory=new PageFactory();
			 Random random = new Random();
			 int Randomnumber = random.nextInt(50000);
			 String randomUin=Integer.toString(Randomnumber);
			 InventoryAdjustmentPage=pagefactory.initialize("InventoryAdjustmentPage");
			//Click on the create button
			InventoryAdjustmentPage.click("AddIcon");
				
				//Input an item in to the scan box
				InventoryAdjustmentPage.enterIntoTextBox("ScanItemBox", Item);
				InventoryAdjustmentPage.click("ClickScanBox");
				
				//Select the reason from the Reason Drop down
	//			InventoryAdjustmentPage.click("DetailReasonDropDown");
//				InventoryAdjustmentPage.clearElement("DropDownFilter");
//				InventoryAdjustmentPage.click("DropDownFilter");
	//			InventoryAdjustmentPage.click("ResonDropDownTxt");
				InventoryAdjustmentPage.enterIntoTextBox("ResonDropDownTxt", reasoncode);
				InventoryAdjustmentPage.explicitWaitForVisibility("SearchBoxHilighted");
				InventoryAdjustmentPage.click("SearchBoxHilighted");
				
				//String ObtainedUin=Integer.toString(Uin);
				InventoryAdjustmentPage.click("UinButton");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
				InventoryAdjustmentPage.click("UinAddButton");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
				InventoryAdjustmentPage.doubleClick();
				InventoryAdjustmentPage.enterIntoTextBox("FirstUinInput",randomUin );
				InventoryAdjustmentPage.click("UinApplyButton");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
				InventoryAdjustmentPage.click("ApplyButton");
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				InventoryAdjustmentPage.click("ConfirmButton");
				InventoryAdjustmentPage.verifyElementIsDisplayed("ConfirmationMsg");
				InventoryAdjustmentPage.explicitWaitForElementToBeClickable("YesButton");
				InventoryAdjustmentPage.click("YesButton");
				return randomUin;
 }
	 
	 
	 //To perform inventory adjustment reason item and UIN are passed
	 public void performUinInventoryAdjustment(String reasonCode,String Uin,String Item) throws InterruptedException, NumberFormatException, IOException {
		 PageFactory pagefactory=new PageFactory();
		
		 InventoryAdjustmentPage=pagefactory.initialize("InventoryAdjustmentPage");
		//Click on the create button
		InventoryAdjustmentPage.click("AddIcon");
			
			//Input an item in to the scan box
			InventoryAdjustmentPage.enterIntoTextBox("ScanItemBox", Item);
			InventoryAdjustmentPage.click("ClickScanBox");
			
			//Select the reason from the Reason Drop down
			InventoryAdjustmentPage.selectDropDownValue("DetailReasonDropDown", reasonCode);
			
			//String ObtainedUin=Integer.toString(Uin);
			InventoryAdjustmentPage.click("UinButton");
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
			InventoryAdjustmentPage.click("UinAddButton");
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
			InventoryAdjustmentPage.doubleClick();
			InventoryAdjustmentPage.enterIntoTextBox("FirstUinInput",Uin );
			InventoryAdjustmentPage.click("UinApplyButton");
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Medium")));
			InventoryAdjustmentPage.click("ApplyButton");
			InventoryAdjustmentPage.click("ConfirmButton");
			InventoryAdjustmentPage.verifyElementIsDisplayed("ConfirmationMsg");
			InventoryAdjustmentPage.explicitWaitForElementToBeClickable("YesButton");
			InventoryAdjustmentPage.click("YesButton");
			
 }
	 
	//Update any table query
	 public void updateDateQuery(String storeId,String itemId) {
		 
		 try {
			 	
			 java.util.Date date = new java.util.Date();
			 SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
			 String formtDte = dateFormat.format(date);
			 Date date1 = dateFormat.parse(formtDte);
			 java.sql.Date First_Received_Date = new java.sql.Date(date1.getTime());  
			 if(connection != null) {
					String query="UPDATE store_item_stock " + 
									"SET First_Received_Day = ?"+
										"WHERE item_id=? and store_id =?";
									

					prepStatement = connection.prepareStatement(query);
					
					prepStatement.setDate(1,First_Received_Date);
					prepStatement.setString(2,itemId);
					prepStatement.setString(3,storeId);
					
					int rowCount=prepStatement.executeUpdate();	
					connection.commit();
					if(rowCount>0) {
						System.out.println("Update successfully "+rowCount);
					}
				}else {
					logger.info("DB connection not established");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}		
		}



	 
	 //Specific update query
	public void updateQuery(int quantity,String itemId,int storeId,int nonSellableTypeId) {
		try {
			if(connection != null) {
				String query="update store_item_stock_nonsell set quantity=?"+ 
						"where item_id=? and store_id =? and nonsellable_type_id=?";

				prepStatement = connection.prepareStatement(query);

				prepStatement.setInt(1,quantity);
				prepStatement.setString(2,itemId);
				prepStatement.setInt(3,storeId);
				prepStatement.setInt(4,nonSellableTypeId);

				int rowCount=prepStatement.executeUpdate();	
				connection.commit();
				if(rowCount>0) {
					System.out.println("Update successfully "+rowCount);
				}
			}else {
				logger.info("DB connection not established");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	//Calculate the value for available and unavailable soh 
	public ArrayList<String> calculateAvailableUnavailableSoh(String itemId){
		ArrayList<String> valuesList= new ArrayList<String>();
		String query="SELECT * "+
				"FROM store_item_stock "+ 
				"WHERE item_id=? AND store_id =?";

		int totalStockOnHand,reservedTransferQty,customerOrderQty,rtvQty,nonSellableQty;
		int availableSOH,Unavailable;

		try {
			int storeId=Integer.parseInt(propReader.getApplicationproperty("StoreId"));
			if(connection != null) {

				prepStatement = connection.prepareStatement(query);

				prepStatement.setString(1,itemId);
				prepStatement.setInt(2,storeId);

				rs= prepStatement.executeQuery();

				if(rs.next()) {

					itemId=rs.getString("item_id");		
					totalStockOnHand=rs.getInt("quantity_total");		
					reservedTransferQty=rs.getInt("quantity_reserved");		
					customerOrderQty=rs.getInt("quantity_customer_reserve");		
					rtvQty=rs.getInt("quantity_vendor_return");		
					nonSellableQty=rs.getInt("quantity_non_sellable");		

					availableSOH = totalStockOnHand-reservedTransferQty-customerOrderQty-rtvQty-nonSellableQty;
					Unavailable = totalStockOnHand - availableSOH;

					valuesList.add(itemId);
					valuesList.add(Integer.toString(totalStockOnHand));
					valuesList.add(Integer.toString(availableSOH));
					valuesList.add(Integer.toString(Unavailable));

					System.out.println("Item id: " + rs.getString("item_id"));
					System.out.println("TSOH: " + rs.getInt("quantity_total"));
					System.out.println("Reserved Quantity: " + rs.getInt("quantity_reserved"));
					System.out.println("Customer reserved quantity: " + rs.getInt("quantity_customer_reserve"));
					System.out.println("RTV quantity: " + rs.getInt("quantity_vendor_return"));
					System.out.println("Non sellable quantity: " + rs.getInt("quantity_non_sellable"));

				}else {
					logger.info("No records are available");
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return valuesList;
	}
	
	
	//Calculate the value for available and unavailable soh 
		public ArrayList<String> calculateAvailableUnavailableSoh1(String itemId){
			ArrayList<String> valuesList= new ArrayList<String>();
			String query="SELECT * "+
					"FROM store_item_stock "+ 
					"WHERE item_id=? AND store_id =?";

			int totalStockOnHand,reservedTransferQty,customerOrderQty,rtvQty,nonSellableQty;
			int availableSOH,Unavailable;

			try {
				int storeId=Integer.parseInt(propReader.getApplicationproperty("StoreId"));
				if(connection != null) {

					prepStatement = connection.prepareStatement(query);

					prepStatement.setString(1,itemId);
					prepStatement.setInt(2,storeId);

					rs3= prepStatement.executeQuery();

					if(rs3.next()) {

						itemId=rs3.getString("item_id");		
						totalStockOnHand=rs3.getInt("quantity_total");		
						reservedTransferQty=rs3.getInt("quantity_reserved");		
						customerOrderQty=rs3.getInt("quantity_customer_reserve");		
						rtvQty=rs3.getInt("quantity_vendor_return");		
						nonSellableQty=rs3.getInt("quantity_non_sellable");		

						availableSOH = totalStockOnHand-reservedTransferQty-customerOrderQty-rtvQty-nonSellableQty;
						Unavailable = totalStockOnHand - availableSOH;

						valuesList.add(itemId);
						valuesList.add(Integer.toString(totalStockOnHand));
						valuesList.add(Integer.toString(availableSOH));
						valuesList.add(Integer.toString(Unavailable));

						System.out.println("Item id: " + rs3.getString("item_id"));
						System.out.println("TSOH: " + rs3.getInt("quantity_total"));
						System.out.println("Reserved Quantity: " + rs3.getInt("quantity_reserved"));
						System.out.println("Customer reserved quantity: " + rs3.getInt("quantity_customer_reserve"));
						System.out.println("RTV quantity: " + rs3.getInt("quantity_vendor_return"));
						System.out.println("Non sellable quantity: " + rs3.getInt("quantity_non_sellable"));

					}else {
						logger.info("No records are available");
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			return valuesList;
		}
	
	

	//Verifying the int values are equal or not
	public void verifyIntValuesAreEqual(int expectedValue, int actualValue) {
		try {
			logger.info("Verify values are equal Expected: " + expectedValue + "----Actual: " + actualValue);
			softAssertion.assertEquals(expectedValue, actualValue, "Expected: " + expectedValue + "----Actual: " + actualValue + "Both are not equal");
			softAssertion.assertAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Close the db connection
	public void closeDBConnection() {
		try {
			// Close Result Set Object
			if(rs!=null) {
				rs.close();
				System.out.println("Result set object closed");
			}
			// Close Prepared Statement Object      
			if(prepStatement!=null) {
				prepStatement.close();
				System.out.println("Prepared statement object closed");
			}
			// Close Statement Object      
			if(stmt!=null) {
				stmt.close();
				System.out.println("Statement object closed");
			}
			// Close Connection Object      
			if(connection!=null) {
				connection.close();
				System.out.println("Connection object closed");
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}       
	}

	protected String getWizardHeading(String heading) {
		headingXpath="//div[@class='wizardContentHeaderBarTitle' and text()='"+heading+"']";
		return headingXpath;
	}


	protected String getLabelTextBox(String labelName) {
		textBoxXpath="//table/tbody/tr/td[@class='label' and text()='"+labelName+"']/following-sibling::td[1]/input";
		return textBoxXpath;
	}


	//table[@class='mrFormTable']/tbody/tr/td[@class='label' and text()='"+labelName+"']/following-sibling::td[1]/input

	protected String getLabelIndentTextBox(String labelName) {
		textBoxXpath="//table/tbody/tr/td[@class='label labelIndent' and text()='"+labelName+"']/following-sibling::td[1]/input";
		return textBoxXpath;
	}

	protected String getLabelIndentDropDownBox(String labelName) {
		dropDownBoxXpath="//table/tbody/tr/td[@class='label labelIndent' and text()='"+labelName+"']/following-sibling::td[1]/select";
		return dropDownBoxXpath;
	}

	protected String getLabelDropDownBox(String labelName) {
		dropDownBoxXpath="//table/tbody/tr/td[@class='label' and text()='"+labelName+"']/following-sibling::td[1]/select";
		return dropDownBoxXpath;
	}

	protected String getLabelCheckBox(String labelName) {
		checkBoxXpath="//table/tbody/tr/td[@class='label' and text()='"+labelName+"']/following-sibling::td[1]/span/input";
		return checkBoxXpath;
	}


	protected String getLabelIndentCheckBox(String labelName) {
		checkBoxXpath="//table/tbody/tr/td[@class='label labelIndent' and text()='"+labelName+"']/following-sibling::td[1]/span/input";
		return checkBoxXpath;
	}

	protected String getLabelValue(String labelName) {
		labelValueXpath="//td[@class='label' and text()='"+labelName+"']/following::td[1]";
		return labelValueXpath;
	}

	protected String getLabelName(String labelName) {
		labelValueXpath="//td[@class='label' and text()='"+labelName+"']";
		return labelValueXpath;
	}

	protected String getLabelIndentValue(String labelName) {
		labelValueXpath="//td[@class='label labelIndent' and text()='"+labelName+"']/following::td[1]";
		return labelValueXpath;
	}


	protected String getLabelIndentName(String labelName) {
		labelValueXpath="//td[@class='label labelIndent' and text()='"+labelName+"']";
		return labelValueXpath;
	}
	
	


	public void verifyTableRowContents()
	{
		try {
			List<WebElement> list=driver.findElements(By.xpath("//td[@data-index='id']"));

			int s=list.size();
			System.out.println("The List size is : " +s);
			int i=0;
			if(i<list.size())
			{
				System.out.println(list.get(i).getText());
			}
			else if(i==0)
			{
				System.out.println("No rows present");    
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}


	public WebElement ValidateSearchResults(String value)
	{
		WebElement row = null;
		try {

			List<WebElement> list=driver.findElements(By.xpath("//div//mark[@class='grid-highlight'][text()='"+value+"']"));
			int s=list.size();
			if(s==0)
			{

				String k=driver.findElement(By.xpath("//span[@class='rowset-message-text']")).getText();

				if(k.contains("No rows")) {
					System.out.println("No rows displayed");
				}
			}
			System.out.println("The number of rows : "+s);
			for(int i=0; i<s; i++) {


				row =list.get(i) ;

				logger.info(" Supplier Name : "+ row.getText());

				if(row.getText().equalsIgnoreCase(value)) {

					logger.info("Supplier Name :"+ row.getText());
					return row;
					//break;  
					//Test comment

				}}

		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return row;
	}


	/**Method mainly compares the storeID in the Application property and user loggedPage any differece
	 * The method will update the store to the one in test property file**/
	public void storeIdCheck() throws Exception {
		try{
			WebDriver driver = SIMWebdriverBase.getCurrentDriver();
			HomePage homePage=new HomePage();
			PropertyReader propreader=new PropertyReader();
			String store = propreader.getApplicationproperty("StoreId");
			String ID=getText("UserStore");
			if(ID.startsWith(store)){
				System.out.println("The Store ids are same");
				logger.info("The store Id check is successful");
			}
			else {
				explicitWaitForVisibility("Titlelogo");
				Thread.sleep(2000);
				explicitWaitForElementToBeClickable("UserStore");
				click("UserStore");
				Thread.sleep(2000);
				click("TextBoxFilter");
				implicitWait();
				enterIntoTextBox("TextBoxFilter",propreader.getApplicationproperty("StoreId"));
				try {
					explicitWaitForElementToBeClickable("GridRecord");
					click("GridRecord");
					Thread.sleep(2000);
					explicitWaitForElementToBeClickable("Apply");
					click("Apply");
					explicitWaitForElementToBeClickable("StoreIdYes");
					click("StoreIdYes");
				}catch(Exception e) {
					String msgchk=homePage.getText("dialog");
					if(msgchk.contains("No rows")) {
						homePage.click("Cancel");
						logger.info("Please verify the store Id in the TestProperty file");
					}
				}
			}	
		}
		catch(org.openqa.selenium.NoSuchElementException e){
			e.printStackTrace();
		}
		catch(NullPointerException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void selectDropDownValue(String logicalName,String value) {
		try {
			click(logicalName);
			//Tag id got changed , now identifying with id
			addObject("Dropdown",By.xpath("//div[@class='oj-listbox-drop oj-listbox-searchselect']//li//span[text()='"+value+"']"));
			addObject("DropDownoption",By.xpath("//span[@class='oj-listbox-highlighter']"));
			//Tag id got changed, now identifying with class
			//addObject("Dropdown",By.xpath("//ul[contains(@class,'listview')]//li//div//span[text()='"+value+"']")); 
			click("Dropdown");
			
		}catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public void doubleClick() {
		try {
		Actions actions = new Actions(driver);
		WebElement elementLocator = driver.findElement(By.xpath("//td[contains(@id,'col-uin')]"));
		actions.doubleClick(elementLocator).perform();
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		}
		catch(NullPointerException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void searchDropDownValue(String logicalName,String value) {
		try {

			//click(logicalName);
		//	WebElement ListSearchBox =driver.findElement(By.xpath("//div[@id='oj-listbox-drop']//input[@class='oj-listbox-input']"));
			WebElement ListSearchBox=driver.findElement(By.xpath("//input[contains(@id,'oj-searchselect-input-input')]"));
			ListSearchBox.sendKeys(value);
			SelectDropdownOption(value);

		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	//for selecting Drop-down Option
	public void SelectDropdownOption(String value)
	{ 

	//	WebElement DropDownoption = driver.findElement(By.xpath("//span[@class='oj-listbox-highlighter'and text()='"+value+"']"));
	//	WebElement DropDownoption = driver.findElement(By.xpath("//li[1]//span[(@class='oj-highlighttext-highlighter'and text()='"+value+"')]"));
		WebElement DropDownoption = driver.findElement(By.xpath("//span[@class='oj-listbox-highlighter']"));
		DropDownoption.click();
	}

	public void searchDropDownValueEditMode(String logicalName,String value) {
		try {

			click(logicalName);

			WebElement ListSearchBox =driver.findElement(By.xpath("//input[@class='oj-listbox-input']"));
			ListSearchBox.sendKeys(value);
			SelectDropdownOption(value);

		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		}
	}
	//Verify Drop-down menu is not Present 
	public void VerifyDropDownOption(String logicalName, String value)
	{
		click(logicalName);

		WebElement ListSearchBox =driver.findElement(By.xpath("//div[@id='oj-listbox-drop']//input[@class='oj-listbox-input']"));
		ListSearchBox.sendKeys(value);
		WebElement NofoundMsg = driver.findElement(By.xpath("//div[text()='No matches found']"));


		String notfoundmsg ="No matches found";

		Assert.assertTrue(NofoundMsg.getText().equals(notfoundmsg));
		System.out.println("DropDown Value "+value+" not present in Drop down List..!");


	}
	//Verify the Web-Page..
	public void VerifyRevokePermissionPage(String value)
	{
		WebElement NavigationSearchBar =driver.findElement(By.xpath("//input[@class='oj-combobox-input']"));
		NavigationSearchBar.click();
		NavigationSearchBar.sendKeys(value);
		WebElement NoResultFound = driver.findElement(By.xpath("//span[text() ='No results found.']"));
		String NoResultFoundMsg = "No results found.";
		Assert.assertTrue(NoResultFound.getText().equals(NoResultFoundMsg));
		System.out.println("Page "+value+ " is not presnet..!");

	}
	
	
	
	public void VerifyPageMenuIsNotPresent(String value)
	{
		List<WebElement> listOfElements = driver.findElements(By.xpath("//span[text()='"+value+"']"));
		int numberOfElementsFound = listOfElements.size();
		Assert.assertTrue(numberOfElementsFound == 0);
		System.out.println("Page "+value+ " is not presnet..!");

	}

	// Refresh the Browser Web Page
	public void RefreshWebPage()
	{
		driver.navigate().refresh();
	}

	//verify the Delete Confirmation Message in Inventory Adjustment Detail Screen
	public void verifyDeleteConfirmationMsg()
	{
		WebElement DeleteConfirmationMsg = driver.findElement(By.xpath("//span[contains(text(),'Are you sure you want to delete the inventory adju')]"));
		String DeleteConfirmationMessage = "Are you sure you want to delete the inventory adjustment";
		Assert.assertTrue(DeleteConfirmationMsg.getText().contains(DeleteConfirmationMessage));

	}

	public void verifyInvalidNumberFormatErrorMsg()
	{
		WebElement NumberFormatMsg=driver.findElement(By.xpath("//div[@class='oj-message-summary'][contains(text(),'is not in the expected number format.')]"));
		String NumberFormatMessage ="is not in the expected number format." ;
		Assert.assertTrue(NumberFormatMsg.getText().contains(NumberFormatMessage));
		logger.info("It is not in the expected number format.");

	}
	
	public void verifyHoverOverValue(String logicalName, String value)
	{	
		if(!value.isEmpty()) {
			String hangovertext = getTitle(logicalName);
			Assert.assertEquals(hangovertext,value);
			logger.info("Logical name : "+ logicalName +" ---- "+"Expected Value : "+ value +" ---- "+"Actual Value : "+hangovertext);
			
		}	
		else  {
			
			verifyElementIsNotPresent(logicalName);
		}
	}
	
	//To verify if a field is Empty or not. Generally used to check default value.
	
	public void isFieldEmpty(String logicalName)
	{	
		try {
		if(logicalName != null) {
			String Data=driver.findElement(ObjRep.get(logicalName)).getAttribute("value");
			Assert.assertEquals(Data, "","Field is not empty");
		}
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
		}
	
	
	public void scrollToTableEnd() {
		try {
			Actions actions = new Actions(driver);		
			actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
			actions.keyUp(Keys.CONTROL);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// clicking on table last record
	public void clickOnTableLastRecord(String firstColumnData, String firstColumnName) throws Exception {
		String lastRecord = "";
		try {
			if (ObjRep.get(firstColumnData) != null && ObjRep.get(firstColumnName) != null) {
				if (driver.findElements(ObjRep.get(firstColumnData)).size() == 0) {
					System.out.println("No table records are found");
				} else {
					driver.findElements(ObjRep.get(firstColumnData)).get(0).click();
					Actions actions = new Actions(driver);
					scrollToTableEnd(actions);
					int size = driver.findElements(ObjRep.get(firstColumnData)).size();
					System.out.println(size);
					lastRecord = driver.findElements(ObjRep.get(firstColumnData)).get(size - 1)
							.getAttribute("innerText");
					System.out.println("Last Record" + lastRecord);
					driver.findElements(ObjRep.get(firstColumnData)).get(size - 1).click();
					//					driver.findElement(ObjRep.get("RefreshButton")).click();
					//					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				}
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}


	//Method to change the user store
	public void ChangeUserStr(String storeId) throws Exception {
		try {
			HomePage homePage=new HomePage();
			homePage.click("UserStore");
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
			homePage.click("TextBoxFilter");
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
			homePage.enterIntoTextBox("TextBoxFilter", storeId);
			homePage.click("GridRecord");
			Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
			homePage.explicitWaitForElementToBeClickable("Apply");
			homePage.click("Apply");
			homePage.explicitWaitForElementToBeClickable("StoreIdYes");
			homePage.click("StoreIdYes");
		}catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}




	public void selectMenuItems(String value) {
		WebElement MenuItem=driver.findElement(By.xpath("//oj-menu[contains(@id,'ui-id')]//a[text()='"+value+"']"));
		MenuItem.click();
	}


	public void verifyTableRowContentsForName()
	{
		try {

			List<WebElement> list=driver.findElements(By.xpath("//td[@data-index='name']"));

			int listSize=list.size();

			System.out.println("The List size is : " +listSize);
			int i;
			for(i=0;i<list.size();i++)
			{  
				System.out.println(list.get(i).getText());
			}
			if(listSize==0)
			{
				System.out.println("No rows present");	

			}

		}
		catch(StaleElementReferenceException e)
		{
			System.out.println(e);
		}
		catch(IndexOutOfBoundsException e)
		{
			System.out.println(e);
		}

		catch(Exception e)
		{
			System.out.println(e);
		}


	}


	//Method to click on a particular buddy store 
	public void clkBuddyStore(String BuddyStore,String Title) throws NumberFormatException {

		try {
			WebElement BuddyTitle=driver.findElement(By.xpath("//h3[contains(@class,'sim-screen-title')]"));
			WebElement SeleBuddyStore=driver.findElement(By.xpath("//div[contains(text(),'"+BuddyStore+"')]//following::label[@class='sim-icon-font'][1]"));
			SeleBuddyStore.isEnabled();
			if(SeleBuddyStore.isSelected()) {
				logger.info("The selcted buddy store is already selected");

			}
			else {
				SeleBuddyStore.click();
				String AfterBuddyStoreAddn=BuddyTitle.getText();
				//int Stores=Integer.parseInt(AfterBuddyStoreAddn);
				if(Title.equalsIgnoreCase(AfterBuddyStoreAddn)) {

					logger.info("The buddy store did not get added");
					Assert.assertEquals(Title, AfterBuddyStoreAddn);

				}else {
					logger.info("The buddy store was added successfully");
					System.out.println(AfterBuddyStoreAddn +"Are the number of stores added");

				}

			}
		}catch(NullPointerException e) {
			e.printStackTrace();
			//if the object passed is not an array
		}catch(IllegalArgumentException e) {
			e.printStackTrace();
		}catch(ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}catch(org.openqa.selenium.NoSuchElementException e) {

			System.out.println("The store id to be added is not present on the screen");
			//driver.close();

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	//method to remove a buddy store
	public void clkRemoveBuddyStore(String BuddyStore,String Title) throws NumberFormatException {

		try {
			WebElement BuddyTitle=driver.findElement(By.xpath("//h3[contains(@class,'sim-screen-title')]"));
			WebElement SeleBuddyStore=driver.findElement(By.xpath("//div[contains(text(),'"+BuddyStore+"')]//following::label[@class='sim-icon-font'][1]"));
			WebElement Valuefrattri=driver.findElement(By.xpath("//div[contains(text(),'5181')]//following::input[contains(@id,'col-assigned-check')][1]"));
			SeleBuddyStore.isEnabled();
			String Value= Valuefrattri.getAttribute("aria-checked");
			if(Value.equalsIgnoreCase("true")) {
				SeleBuddyStore.click();
				String AfterBuddyStoreAddn=BuddyTitle.getText();
				//int Stores=Integer.parseInt(AfterBuddyStoreAddn);
				if(Title.equalsIgnoreCase(AfterBuddyStoreAddn)) {
					logger.info("The buddy store did not get removed");
					Assert.assertEquals(Title, AfterBuddyStoreAddn);
				}else {
					logger.info("The buddy store was removed successfully");
					System.out.println(AfterBuddyStoreAddn +"Are the number of stores removed");

				}
			}else {
				Assert.assertFalse(false);
				logger.info("The buddy store to be added is a store selecetd");

			}

		}catch(NullPointerException e) {
			e.printStackTrace();
			//if the object passed is not an array
		}catch(IllegalArgumentException e) {
			e.printStackTrace();
		}catch(ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}catch(org.openqa.selenium.NoSuchElementException e) {

			System.out.println("The store id to be added is not present on the screen");
			//driver.close();

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	//To veryfy at the end if the store is added
	public void checkBuddystore(String userBuddyStoreValue,String actualBuddyStoreval ) {
		if(actualBuddyStoreval.contains(userBuddyStoreValue)) {
			logger.info("The buddy store is added successfully");
		}else {
			logger.info("The buddy store is not added");
		}

	}



	//returns true if the element is visible else returns false
	public boolean isVisible(String logicalName) {
		try {
			if(driver.findElements(ObjRep.get(logicalName)).size()>0) {
				return true;
			}			
			else {
				return false;		
			}		
		}catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean verifyElementIsDisplayed(String logicalName) {
		try {
			logger.info("Verify the "+ logicalName +" is displayed");
			return (driver.findElement(ObjRep.get(logicalName)).isDisplayed());
		}catch (org.openqa.selenium.NoSuchElementException e) {
		//	e.printStackTrace();
			return false;
		}
	}

	//verifying highlighted text value-list
	public void verifyHighlightedTextValue(String logicalName,String value) {
		try {
			List<WebElement> elements=driver.findElements(ObjRep.get(logicalName));
			
			System.out.println("Matched records: "+elements.size());
			for (WebElement element : elements) {
				if (element.getText().equalsIgnoreCase(value)) {
					logger.info("Expected Value : " + value + " ---- " + "Actual Value : " + element.getText());
				} else {
					softAssertion.fail("Expected Value : " + value + " ---- " + "Actual Value : " + element.getText());
				}
			}
			softAssertion.assertAll();
		}catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void verifyHighlightedPartialTextValue(String logicalName,String value) {
		try {
			List<WebElement> elements=driver.findElements(ObjRep.get(logicalName));
			System.out.println("Matched records: "+elements.size());
			for (WebElement element : elements) {
				if (element.getText().contains(value)) {
					logger.info("Expected Value : " + value + " ---- " + "Actual Value : " + element.getText());
				} else {
					softAssertion.fail("Expected Value : " + value + " ---- " + "Actual Value : " + element.getText());
				}
			}
		}
				catch(Exception e) {
					e.printStackTrace();
				}
			
		}
	//Verify search contents of the table rows-also supports substring search
	public void verifyTableRowUsingColumnValue(String logicalName, String value)
	{
		try {
		explicitWaitForVisibility(logicalName) ;
		List <WebElement> searchResultsList=getWebElementList(logicalName);
		System.out.println(searchResultsList.size());
		
		for(int i=0; i<searchResultsList.size(); i++) {
			WebElement row =searchResultsList.get(i) ;
			
			if(row.getText().contains(value)) {
				
				logger.info("Matched Logical name : "+ logicalName +" contains "+"Expected Value : "+value);
				
			}
			
			else {
				softAssertion.fail("Matched Logical name : "+ logicalName +" does not contain "+"Expected Value : "+value);
			}
		}	
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		}
	}
	
	//Verifying Element is a dropdown
	public void verifyElementIsDropdown(String logicalName)
	{
		
		try{
			logger.info("Verify the "+ logicalName +" is dropdown");
		 Boolean dropdownPresent = driver.findElement(ObjRep.get(logicalName)).isDisplayed();

	        if(dropdownPresent==true)
	        {
	        	logger.info("The "+ logicalName +" is dropdown");
	        }
	        else
	        	softAssertion.fail("Element is not a dropdown");
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//verifying field border color
	public void verifyBorderColor(String logicalName,String value) {
		try {
			if(!value.isEmpty()) {
				softAssertion.assertEquals(getBorderColor(logicalName),value);
				logger.info("Logical name : "+ logicalName +" ---- "+"Expected Value : "+ value +" ---- "+"Actual Value : "+ getBorderColor(logicalName));
				softAssertion.assertAll();
			}	
			else if(value.isEmpty()) {
				verifyElementIsNotPresent(logicalName);
			}
		}catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}


	//To verify the value of the whole column of a particular value
	public void VerifyBarCodeProcessorActiveColumnValue() {
		WebElement Table=driver.findElement(By.xpath("//table[@class='pgbu-grid']"));	
		List<WebElement> ColumnSize=Table.findElements(By.xpath("//td[contains(@id,'-col-active')]"));
		WebElement ColumnText=driver.findElement(By.xpath("//td[contains(@id,'-col-active')]"));
		for(int i=0;i<ColumnSize.size();i++) {
			if(ColumnText.getText().equals("Yes")) {
				System.out.println("The ProcessorName's Active value is yes");
			}

			else {
				System.out.println("The ProcessorName's Active value is not yes");

			}

		}
	}

	//get border color
	public String getBorderColor(String logicalName) {
		String hex="";
		String[] numbers;
		try {
			String color = driver.findElement(ObjRep.get(logicalName)).getCssValue("border-bottom-color");
			if(color.contains("rgba")) {
				numbers = color.replace("rgba(", "").replace(")", "").split(",");
			}
			else {
				numbers = color.replace("rgb(", "").replace(")", "").split(",");
			}
			String r=Integer.toHexString(Integer.parseInt(numbers[0].trim()));
			String g=Integer.toHexString(Integer.parseInt(numbers[1].trim()));
			String b=Integer.toHexString(Integer.parseInt(numbers[2].trim()));
			hex="#"+r+g+b;
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return hex;
	}

	public void scrollToViewElement(String logicalName) {
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].scrollIntoView();",driver.findElement(ObjRep.get(logicalName)));
		} catch (UnhandledAlertException ar) {
			driver.switchTo().alert().dismiss();
		}
	}

	//-----------------------------MPSWorkTypeDetailEdit-------------------------------
	//fetching the title of the tags
	public String getTitle(String logicalName) {
		String title="";
		try {
			WebElement element=driver.findElement(ObjRep.get(logicalName));
			title=element.getAttribute("title");
		}catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		}	
		catch(org.openqa.selenium.StaleElementReferenceException e) {
			WebElement element=driver.findElement(ObjRep.get(logicalName));
			title=element.getAttribute("title");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return title;
	}

	//verify the element is disabled
	public void verifyElementIsDisabled(String logicalName) {
		try {
			if (ObjRep.get(logicalName) != null) {
				logger.info("Verify the " + logicalName + " is disabled");
				if(driver.findElement(ObjRep.get(logicalName)).getAttribute("disabled")!=null) {
					softAssertion.assertTrue(driver.findElement(ObjRep.get(logicalName)).getAttribute("disabled").equals("true"),"The element is enabled");	
				}
				else {
					softAssertion.assertTrue(driver.findElement(ObjRep.get(logicalName)).getAttribute("class").contains("disabled"),"The element is enabled");
				}
				softAssertion.assertAll();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	//verify element is editable or not
	public void verifyElementIsEditable(String logicalName) {		
		try {
			if(ObjRep.get(logicalName)!=null) {
				softAssertion.assertNull(driver.findElement(ObjRep.get(logicalName)).getAttribute("readonly"),"The element is read-only element");
				softAssertion.assertFalse(driver.findElement(ObjRep.get(logicalName)).getAttribute("class").contains("read-only"),"The element is not editable");
				softAssertion.assertAll();
			}
		}catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		}catch(NullPointerException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		logger.info(logicalName+ "This element is editable");
	}
	//---------------------------------MPS WorkType Title Bar-----------------------------------------
	//verify the element is enabled
	public void verifyElementIsEnabled(String logicalName) {
		try {
			if(ObjRep.get(logicalName)!=null) {
				logger.info("Verify the "+ logicalName +" is enabled");
				softAssertion.assertTrue((driver.findElement(ObjRep.get(logicalName)).isEnabled())&&(!driver.findElement(ObjRep.get(logicalName)).getAttribute("class").contains("disabled")),logicalName+" is not Enabled");
				softAssertion.assertAll();
			}			
		}catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		}catch(NullPointerException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	//verify element is read only or not
	public void verifyReadOnly(String logicalName) {
		try {
			if(ObjRep.get(logicalName)!=null) {
				if(driver.findElement(ObjRep.get(logicalName)).getAttribute("aria-readonly")!=null||driver.findElement(ObjRep.get(logicalName)).getAttribute("readonly")!=null) {
					System.out.println(driver.findElement(ObjRep.get(logicalName)).getText()+" element is read only");
				}
				else {
					softAssertion.assertTrue(driver.findElement(ObjRep.get(logicalName)).getAttribute("class").contains("read-only"),driver.findElement(ObjRep.get(logicalName)).getText()+"  element is editable");
					softAssertion.assertAll();
				}
			}
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		}
		catch(NullPointerException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//---------------------------------MPS WorkType Table List Fields -----------------------------------------
	//verifying id
	public void verifyID(String logicalName,String value) {
		try {
			if(!value.isEmpty()) {
				softAssertion.assertTrue(driver.findElement(ObjRep.get(logicalName)).getAttribute("id").contains(value),"ID not matched");
				logger.info("Verify ID : "+" ---- "+"Expected Value : true"+ " ---- "+"Actual Value : "+ driver.findElement(ObjRep.get(logicalName)).getAttribute("id").contains(value));
				softAssertion.assertAll();
			}	
			else if(value.isEmpty()) {
				verifyElementIsNotPresent(logicalName);
			}
		}catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	//verify direction field
	public void verifyDirectionField(String logicalName) {
		try {
			if(ObjRep.get(logicalName)!=null) {
				softAssertion.assertTrue(driver.findElement(ObjRep.get(logicalName)).getAttribute("innerText").equals("Inbound")||driver.findElement(ObjRep.get(logicalName)).getAttribute("innerText").equals("Outbound"),
						"Direction Field should contains Inbound or Outbound message");	
				logger.info("Verify Direction Field : "+"-----"+"Expected : Inbound/Outbound"+"-----"+"Actual : "+driver.findElement(ObjRep.get(logicalName)).getAttribute("innerText"));
				softAssertion.assertAll();
			}	
		}catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		}catch(NullPointerException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	//verify active field
	public void verifyActiveField(String logicalName){
		try {
			if(ObjRep.get(logicalName)!=null) {
				softAssertion.assertTrue(driver.findElement(ObjRep.get(logicalName)).getAttribute("innerText").equals("Yes")||driver.findElement(ObjRep.get(logicalName)).getAttribute("innerText").equals("No"),
						"Active Field should contains Yes or No message");	
				logger.info("Verify Active Field : "+"-----"+"Expected : Yes/No"+"-----"+"Actual : "+driver.findElement(ObjRep.get(logicalName)).getAttribute("innerText"));
				softAssertion.assertAll();
			}	
		}catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		}catch(NullPointerException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	//verify retry field
	public void verifyRetryField(String logicalName,String minValue,String maxValue){
		try {
			if(ObjRep.get(logicalName)!=null) {
				int value=Integer.parseInt(driver.findElement(ObjRep.get(logicalName)).getAttribute("innerText"));
				softAssertion.assertTrue((value>=Integer.parseInt(minValue)) && (value<=Integer.parseInt(maxValue)),logicalName+" should lies between a range");	
				logger.info("Verify Retry Field : "+"-----"+"Expected : values between "+minValue+" and "+maxValue+" -----"+"Actual : "+value);
				softAssertion.assertAll();
			}	
		}catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		}catch(NullPointerException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	//verify factor random field
	public void verifyFactorRandomField(String logicalName,String minValue,String maxValue){
		try {
			if(ObjRep.get(logicalName)!=null) {
				double value=Double.parseDouble(driver.findElement(ObjRep.get(logicalName)).getAttribute("innerText"));
				softAssertion.assertTrue((value>=Double.parseDouble(minValue)) && (value<=Double.parseDouble(maxValue)),logicalName+" should lies between a range");	
				logger.info("Verify Retry Field : "+"-----"+"Expected : values between "+minValue+" and "+maxValue+" -----"+"Actual : "+value);
				softAssertion.assertAll();
			}	
		}catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		}catch(NullPointerException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	//verify purge processed field
	public void verifyPurgeProcessedField(String logicalName){
		try {
			if(ObjRep.get(logicalName)!=null) {
				softAssertion.assertTrue(driver.findElement(ObjRep.get(logicalName)).getAttribute("innerText").equals("Yes")||driver.findElement(ObjRep.get(logicalName)).getAttribute("innerText").equals("No"),
						"Purge Processed Field should contains Yes or No message");	
				logger.info("Verify Purge Processed Field : "+"-----"+"Expected : Yes/No"+"-----"+"Actual : "+driver.findElement(ObjRep.get(logicalName)).getAttribute("innerText"));
				softAssertion.assertAll();
			}	
		}catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		}catch(NullPointerException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/*sorting the column
	 * logicalName - the path of the column data for which column we want to sort
	 * columnName - the path of the column name for which column we want to sort
	 * sorting order - ascending or descending order 
	 */
	public void columnSorting(String logicalName,String columnName,String sortingOrder) throws InterruptedException {
		try {
			if(ObjRep.get(logicalName)!=null && ObjRep.get(columnName)!=null) {
				JavascriptExecutor js = (JavascriptExecutor) driver;	
				driver.findElements(ObjRep.get(logicalName)).get(0).click();		
				Actions actions = new Actions(driver);
				actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
				actions.keyUp(Keys.CONTROL);
				scrollToViewElement(columnName);
				int size=driver.findElements(ObjRep.get(logicalName)).size();
				//	System.out.println(size);
				String lastRecord =driver.findElements(ObjRep.get(logicalName)).get(size-1).getAttribute("innerText");
				//	System.out.println(lastRecord);
				driver.findElement(ObjRep.get("RefreshButton")).click();
				Thread.sleep(2000);
				driver.findElements(ObjRep.get(logicalName)).get(0).click();
				WebElement element;
				ArrayList<String> obtainedList = new ArrayList<String>();
				ArrayList<String> sortedList = new ArrayList<String>();		
				//	System.out.println("Obtained List :");
				while(!(driver.findElement(ObjRep.get("FocusedCell")).getText()).equals(lastRecord)) {
					element=driver.findElement(ObjRep.get("FocusedCell"));	
					//In SIM application the records are sorted in case-insensitive order i.e)both the small and capital letters are considering as same.So converting to lower case taken place
					//	System.out.println(element.getText().toLowerCase());
					obtainedList.add(element.getText().toLowerCase());
					actions.sendKeys(Keys.ARROW_DOWN).perform();
				}
				obtainedList.add(lastRecord.toLowerCase());
				//	System.out.println(lastRecord.toLowerCase());
				sortedList.addAll(obtainedList);
				if(sortingOrder.contains("ascending")) {
					Collections.sort(sortedList);
				}
				if(sortingOrder.contains("descending")) {
					Collections.sort(sortedList,Collections.reverseOrder());
				}
				//				System.out.println("Sorted List :");
				//				for(String sortedValue:sortedList){
				//					System.out.println(sortedValue);
				//				}
				//				System.out.println("Sorting"+sortedList.equals(obtainedList));
				if(sortedList.equals(obtainedList)) {
					logger.info("The records are in "+sortingOrder+" order");
				}
				softAssertion.assertTrue(sortedList.equals(obtainedList),"The records are not in sorted order");
				driver.findElement(ObjRep.get("RefreshButton")).click();
				Thread.sleep(2000);
				softAssertion.assertAll();
			}
		}catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		}catch(NullPointerException e) {
			e.printStackTrace();
		}

	}

	/**Clicks on the provided value of the Buddy store**/

	public void clkBuddyStore(String BuddyStore) {

		try {

			WebElement SeleBuddyStore=driver.findElement(By.xpath("//div[contains(text(),'"+BuddyStore+"')]//following::label[@class='sim-icon-font'][1]"));
			if(SeleBuddyStore.isEnabled()) {
				SeleBuddyStore.click();
			}
		}catch(NullPointerException e) {
			e.printStackTrace();
			//if the object passed is not an array
		}catch(IllegalArgumentException e) {
			e.printStackTrace();
		}catch(ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}catch(org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/*firstColumnData - common path of all first column data
	 *firstColumnName - path for the first column name
	 *firstColumnFilter -path for the first column filter
	 */
	public void verifyAllTableRecordsReadOnly(String firstColumnData,String firstColumnName,String firstColumnFilter) throws Exception {
		try {
			if(ObjRep.get(firstColumnData)!=null && ObjRep.get(firstColumnName)!=null) {
				int count=0,lastRecordCount=0,size,flag=0;
				if(isVisible(firstColumnFilter)) {
					flag=1;
					explicitWaitForElementToBeClickable("GridViewMenu");
					click("GridViewMenu");
					explicitWaitForElementToBeClickable("ColumnFilter");
					click("ColumnFilter");
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
				}
				String columnName=driver.findElement(ObjRep.get(firstColumnName)).getAttribute("innerText");
				Actions actions = new Actions(driver);
				driver.findElements(ObjRep.get(firstColumnData)).get(0).click();
				String firstRecord=driver.findElements(ObjRep.get(firstColumnData)).get(0).getAttribute("innerText");
				scrollToTableEnd(actions);
				pressHomeKey();								
				size=driver.findElements(ObjRep.get(firstColumnData)).size();
				//	System.out.println(size);
				String lastRecord =driver.findElements(ObjRep.get(firstColumnData)).get(size-1).getAttribute("innerText");
				//	System.out.println("LastRecord: "+lastRecord);
				if(firstRecord.equals(lastRecord)) {
					while(!(driver.findElement(ObjRep.get("FocusedCell")).getText().equals(columnName))){
						explicitWaitForElementToBeClickable("FocusedCell");
						lastRecordCount++;
						actions.sendKeys(Keys.ARROW_UP).perform();
						implicitWait();
					}
				}
				else {
					while((driver.findElement(ObjRep.get("FocusedCell")).getText()).equals(lastRecord)) {
						lastRecordCount++;
						actions.sendKeys(Keys.ARROW_UP).perform();
						implicitWait();
						explicitWaitForElementToBeClickable("FocusedCell");
					}	
				}
				//	System.out.println("LastRecord Repetition Count: "+lastRecordCount);		
				driver.findElement(ObjRep.get("RefreshButton")).click();
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				driver.findElements(ObjRep.get(firstColumnData)).get(0).click();
				int noOfColumns=driver.findElements(ObjRep.get("AllColumnHeading")).size();
				//	System.out.println("No of columns: "+driver.findElements(ObjRep.get("AllColumnHeading")).size());
				while(!(driver.findElement(ObjRep.get("FocusedCell")).getText()).equals(lastRecord)) {
					verifyReadOnly("FocusedCell");
					implicitWait();
					explicitWaitForElementToBeClickable("FocusedCell");
					actions.sendKeys(Keys.ARROW_RIGHT).perform();		
				}
				if(driver.findElement(ObjRep.get("FocusedCell")).getText().equals(lastRecord)){
					while(count<noOfColumns*lastRecordCount) {
						verifyReadOnly("FocusedCell");
						implicitWait();
						explicitWaitForElementToBeClickable("FocusedCell");
						actions.sendKeys(Keys.ARROW_RIGHT).perform();	
						count++;
					}
				}
				if(flag==1) {
					explicitWaitForElementToBeClickable("GridViewMenu");
					click("GridViewMenu");
					explicitWaitForElementToBeClickable("ColumnFilter");
					click("ColumnFilter");
				}
				logger.info("Verified all table records are read only");
				driver.findElement(ObjRep.get("RefreshButton")).click();
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
			}
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		}catch(NullPointerException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	//MPS WORKTYPE table list field method : logicalName -unique column data path
	public void getPendingFailRetryCount(String firstColumnData,String uniqueColumnName) throws Exception {
		try {
			int rowCount;
			rowCount=getTableRowCount("WorkTypeRecord", "PurgeProcessed", "FilterWorkType");
			if(rowCount==0) {
				System.out.println("No table records found");
			}
			else {
				System.out.println("Table Records Count: "+rowCount);			
				//				if(ObjRep.get(firstColumnData)!=null && ObjRep.get(uniqueColumnName)!=null) {
				//					String lastRecord=getTableLastRecord(firstColumnData, uniqueColumnName);
				//					System.out.print("WorkType PendingCount RetryCount FailCount LastUpdate LastNew");	
				//					System.out.println();
				//					Actions actions = new Actions(driver);
				//					while(!(driver.findElement(ObjRep.get("FocusedCell")).getText()).equals(lastRecord)) {
				//						System.out.print(driver.findElement(ObjRep.get("FocusedCell")).getText()+"\t\t");
				//						System.out.print(driver.findElement(ObjRep.get("FocusedPendingCount")).getText()+"\t");
				//						System.out.print(driver.findElement(ObjRep.get("FocusedRetryCount")).getText()+"\t");
				//						System.out.print(driver.findElement(ObjRep.get("FocusedFailCount")).getText()+"\t");
				//						System.out.print(driver.findElement(ObjRep.get("FocusedLastUpdate")).getText()+"\t");
				//						System.out.print(driver.findElement(ObjRep.get("FocusedLastNew")).getText()+"\t");
				//						actions.sendKeys(Keys.ARROW_DOWN).perform();
				//						System.out.println();
				//					}	
				//					if(driver.findElement(ObjRep.get("FocusedCell")).getText().equals(lastRecord)) {
				//						System.out.print(driver.findElement(ObjRep.get("FocusedCell")).getText()+"\t\t");
				//						System.out.print(driver.findElement(ObjRep.get("FocusedPendingCount")).getText()+"\t");
				//						System.out.print(driver.findElement(ObjRep.get("FocusedRetryCount")).getText()+"\t");
				//						System.out.print(driver.findElement(ObjRep.get("FocusedFailCount")).getText()+"\t");
				//						System.out.print(driver.findElement(ObjRep.get("FocusedLastUpdate")).getText()+"\t");
				//						System.out.print(driver.findElement(ObjRep.get("FocusedLastNew")).getText()+"\t");
				//					}
				//					System.out.println();
				//					driver.findElement(ObjRep.get("RefreshButton")).click();
				//					Thread.sleep(2000);
				//				}
			}
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		}catch(NullPointerException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	//scrolling entire table
	public String getTableLastRecord(String firstColumnData,String uniqueColumnName) throws Exception {
		String lastRecord="";
		try {
			if(ObjRep.get(firstColumnData)!=null && ObjRep.get(uniqueColumnName)!=null) {
				JavascriptExecutor js = (JavascriptExecutor) driver;	
				driver.findElements(ObjRep.get(firstColumnData)).get(0).click();		
				Actions actions = new Actions(driver);
				actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
				actions.keyUp(Keys.CONTROL);
				scrollToViewElement(uniqueColumnName);
				int size=driver.findElements(ObjRep.get(firstColumnData)).size();
				//	System.out.println(size);
				lastRecord =driver.findElements(ObjRep.get(firstColumnData)).get(size-1).getAttribute("innerText");
				//	System.out.println(lastRecord);
				driver.findElement(ObjRep.get("RefreshButton")).click();
				Thread.sleep(2000);
				driver.findElements(ObjRep.get(firstColumnData)).get(0).click();
				return lastRecord;
			}
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		}catch(NullPointerException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return lastRecord;
	}

	//-----------------------------------MPS Work Type Detail Apply Block Cancel------------------
	//verifying values are not equal 
	public void verifyValuesAreNotEqual(String logicalName,String value) {
		try {
			logger.info("Verifying the values are not equal");
			if(ObjRep.get(logicalName)!=null && value!=null) {
				logger.info("Logical name : "+ logicalName +" ---- "+"Expected Value : "+ value +" ---- "+"Actual Value : "+driver.findElement(ObjRep.get(logicalName)).getText());
				softAssertion.assertNotEquals(driver.findElement(ObjRep.get(logicalName)).getText(),value,"The value are equal");	
				softAssertion.assertAll();
			}			
		}catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		}catch(NullPointerException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	//--------------------------------------Transaction history-----------------------------------
	//Refreshing the page
	public void refreshPage() {
		try {
			driver.navigate().refresh();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Selecting a date from the date picker
	public void selectDateFromDatePicker(String datePickerLogicalName,String date) {
		try {
			if(ObjRep.get(datePickerLogicalName)!=null) {
				explicitWaitForVisibility(datePickerLogicalName);
				explicitWaitForElementToBeClickable(datePickerLogicalName);
				click(datePickerLogicalName);
				clearElement(datePickerLogicalName);
				enterIntoTextBox(datePickerLogicalName,date);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/*returns no of rows in a table
	 * tablerows:the path of all table rows
	 * lastColumnName:the path of last column name
	 * firstColumnFilter: the path of the first column filter
	 */
	public int getTableRowCount(String tableRows,String lastColumnName,String firstColumnFilter) throws Exception{
		int rowCount=0;
		try {
			if(ObjRep.get(tableRows)!=null && ObjRep.get(lastColumnName)!=null && ObjRep.get(firstColumnFilter)!=null) {
				if(driver.findElements(ObjRep.get(tableRows)).size()==0) {
					return rowCount;
				}
				else {	
					int flag=0;
					if(isVisible(firstColumnFilter)) {
						flag=1;
						explicitWaitForElementToBeClickable("GridViewMenu");
						click("GridViewMenu");
						explicitWaitForElementToBeClickable("ColumnFilter");
						click("ColumnFilter");
						Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
					}
					String columnName=driver.findElement(ObjRep.get(lastColumnName)).getAttribute("innerText");
					Actions actions= new Actions(driver);
					driver.findElements(ObjRep.get(tableRows)).get(0).click();	
					scrollToTableEnd(actions);					
					while(!(driver.findElement(ObjRep.get("FocusedCell")).getText().equals(columnName))){
						explicitWaitForElementToBeClickable("FocusedCell");
						rowCount++;
						actions.sendKeys(Keys.ARROW_UP).perform();
						implicitWait();
					}	
					if(flag==1) {
						explicitWaitForElementToBeClickable("GridViewMenu");
						click("GridViewMenu");
						explicitWaitForElementToBeClickable("ColumnFilter");
						click("ColumnFilter");
					}
					driver.findElement(ObjRep.get("RefreshButton")).click();
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				}			
			}
		}catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		}catch(NullPointerException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return rowCount;
	}
	
	/*returns no of rows in a table
	 * tablerows:the path of all table rows
	 * lastColumnName:the path of last column name
	 * firstColumnFilter: the path of the first column filter
	 */
	public int getTableRowCountWitoutRefresh(String tableRows,String lastColumnName,String firstColumnFilter) throws Exception{
		int rowCount=0;
		try {
			if(ObjRep.get(tableRows)!=null && ObjRep.get(lastColumnName)!=null && ObjRep.get(firstColumnFilter)!=null) {
				if(driver.findElements(ObjRep.get(tableRows)).size()==0) {
					return rowCount;
				}
				else {	
					int flag=0;
					if(isVisible(firstColumnFilter)) {
						flag=1;
						explicitWaitForElementToBeClickable("GridViewMenu");
						click("GridViewMenu");
						explicitWaitForElementToBeClickable("ColumnFilter");
						click("ColumnFilter");
						Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
					}
					String columnName=driver.findElement(ObjRep.get(lastColumnName)).getAttribute("innerText");
					Actions actions= new Actions(driver);
					driver.findElements(ObjRep.get(tableRows)).get(0).click();	
					scrollToTableEnd(actions);					
					while(!(driver.findElement(ObjRep.get("FocusedCell")).getText().equals(columnName))){
						explicitWaitForElementToBeClickable("FocusedCell");
						rowCount++;
						actions.sendKeys(Keys.ARROW_UP).perform();
						implicitWait();
					}	
					if(flag==1) {
						explicitWaitForElementToBeClickable("GridViewMenu");
						click("GridViewMenu");
						explicitWaitForElementToBeClickable("ColumnFilter");
						click("ColumnFilter");
					}
				//	driver.findElement(ObjRep.get("RefreshButton")).click();
				//	Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				}			
			}
		}catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		}catch(NullPointerException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return rowCount;
	}


	//Scroll to table end
	public void scrollToTableEnd(Actions actions) {
		try {
			actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
			actions.keyUp(Keys.CONTROL);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Clicking on home key
	public void pressHomeKey() throws Exception {
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_HOME);
			robot.keyRelease(KeyEvent.VK_HOME);
			//	Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/*sorting the column
	 * logicalName - the path of the column data for which column we want to sort
	 * columnName - the path of the column name for which column we want to sort
	 * sorting order - ascending or descending order as string
	 * dataType - int,Date,String 
	 * Element path needed for this method:RefreshButton addObject("RefreshButton",By.xpath("//oj-button[@id='refresh-button']"));
	 * addObject("FocusedCell",By.xpath("//tbody//tr/td[contains(@class,'cell-focused')]"));
	 */
	//sorting the column
	public void columnSorting(String logicalName,String columnName,String sortingOrder,String dataType) throws InterruptedException, ParseException {
		try {
			System.out.println("Inside the colum sorting method..!");
			if(ObjRep.get(logicalName)!=null && ObjRep.get(columnName)!=null) {
				int size;
				Actions actions = new Actions(driver);
				driver.findElements(ObjRep.get(logicalName)).get(0).click();	
				scrollToTableEnd(actions);
				implicitWait();				
				scrollToViewElement(columnName);
				size=driver.findElements(ObjRep.get(logicalName)).size();
				//	System.out.println(size);
				String lastRecord =driver.findElements(ObjRep.get(logicalName)).get(size-1).getAttribute("innerText");
				//System.out.println(lastRecord);
			//	driver.findElement(ObjRep.get("RefreshButton")).click();
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				driver.findElements(ObjRep.get(logicalName)).get(0).click();
				WebElement element;
				ArrayList<String> obtainedStringList = new ArrayList<String>();
				ArrayList<String> sortedStringList = new ArrayList<String>();	
				ArrayList<Date> obtainedDateList = new ArrayList<Date>();
				ArrayList<Date> sortedDateList = new ArrayList<Date>();	
				ArrayList<Integer> obtainedIntegerList = new ArrayList<Integer>();
				ArrayList<Integer> sortedIntegerList = new ArrayList<Integer>();	
				DateFormat dateFormatter = new SimpleDateFormat("MM/dd/yy");
				//	System.out.println("Obtained List :");
				while(!(driver.findElement(ObjRep.get("FocusedCell")).getText()).equals(lastRecord)) {
					element=driver.findElement(ObjRep.get("FocusedCell"));
					//System.out.println(element.getText().toLowerCase());
					if(dataType.equalsIgnoreCase("int")||dataType.equalsIgnoreCase("Integer")) {
						obtainedIntegerList.add(Integer.parseInt(element.getText()));
					}
					else if(dataType.equalsIgnoreCase("Date")) {
						obtainedDateList.add(dateFormatter.parse(element.getText()));
					}
					else {
						obtainedStringList.add(element.getText().toLowerCase());
					}
					actions.sendKeys(Keys.ARROW_DOWN).perform();
				}
				//	System.out.println(lastRecord.toLowerCase());
				//adding last data and copying to other list
				if(dataType.equalsIgnoreCase("int")||dataType.equalsIgnoreCase("Integer")) {
					obtainedIntegerList.add(Integer.parseInt(lastRecord));
					sortedIntegerList.addAll(obtainedIntegerList);
					if(sortingOrder.equalsIgnoreCase("ascending")) {
						Collections.sort(sortedIntegerList);
					}
					else if(sortingOrder.equalsIgnoreCase("descending")) {
						Collections.sort(sortedIntegerList,Collections.reverseOrder());
					}
					else {
						System.out.println("Enter the sorting order as ascending/descending");
					}
					//					System.out.println("Sorted List :");
					//					for(int sortedRecord:sortedIntegerList){
					//						System.out.println(sortedRecord);
					//					}
					//					System.out.println("Sorting: "+sortedIntegerList.equals(obtainedIntegerList));
					if(sortedIntegerList.equals(obtainedIntegerList)) {
						logger.info("The records are in "+sortingOrder+" order");
					}
					softAssertion.assertTrue(sortedIntegerList.equals(obtainedIntegerList),"The records are not in sorted order");
				}
				else if(dataType.equalsIgnoreCase("Date")) {
					obtainedDateList.add(dateFormatter.parse(lastRecord));
					sortedDateList.addAll(obtainedDateList);
					if(sortingOrder.equalsIgnoreCase("ascending")) {
						Collections.sort(sortedDateList);
					}
					else if(sortingOrder.equalsIgnoreCase("descending")) {
						Collections.sort(sortedDateList,Collections.reverseOrder());
					}
					else {
						System.out.println("Enter the sorting order as ascending/descending");
					}
					//System.out.println("Sorted List :");
					for(Date sortedRecord:sortedDateList){
						System.out.println(dateFormatter.format(sortedRecord));
					}
					System.out.println("Sorting: "+sortedDateList.equals(obtainedDateList));
					if(sortedDateList.equals(obtainedDateList)) {
						logger.info("The records are in "+sortingOrder+" order");
					}
					softAssertion.assertTrue(sortedDateList.equals(obtainedDateList),"The records are not in sorted order");
				}
				else {
					obtainedStringList.add(lastRecord.toLowerCase());
					sortedStringList.addAll(obtainedStringList);
					if(sortingOrder.equalsIgnoreCase("ascending")) {
						Collections.sort(sortedStringList);
					}
					else if(sortingOrder.equalsIgnoreCase("descending")) {
						Collections.sort(sortedStringList,Collections.reverseOrder());
					}
					else {
						System.out.println("Enter the sorting order as ascending/descending");
					}
					//					System.out.println("Sorted List :");
					//					for(String sortedRecord:sortedStringList){
					//						System.out.println(sortedRecord);
					//					}
					//					System.out.println("Sorting: "+sortedStringList.equals(obtainedStringList));
					if(sortedStringList.equals(obtainedStringList)) {
						logger.info("The records are in "+sortingOrder+" order");
					}
					softAssertion.assertTrue(sortedStringList.equals(obtainedStringList),"The records are not in sorted order");
				}								
				driver.findElement(ObjRep.get("RefreshButton")).click();
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				softAssertion.assertAll();
			}
		}catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		}catch(NullPointerException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*sorting the column
	 * logicalName - the path of the column data for which column we want to sort
	 * columnName - the path of the column name for which column we want to sort
	 * sorting order - ascending or descending order as string
	 * dataType - int,Date,String 
	 * Element path needed for this method:RefreshButton addObject("RefreshButton",By.xpath("//oj-button[@id='refresh-button']"));
	 * addObject("FocusedCell",By.xpath("//tbody//tr/td[contains(@class,'cell-focused')]"));
	 */
	//sorting the column
	public void columnSortingWioutRefrsh(String logicalName,String columnName,String sortingOrder,String dataType) throws InterruptedException, ParseException {
		try {
			System.out.println("Inside the colum sorting method..!");
			if(ObjRep.get(logicalName)!=null && ObjRep.get(columnName)!=null) {
				int size;
				Actions actions = new Actions(driver);
				driver.findElements(ObjRep.get(logicalName)).get(0).click();	
				scrollToTableEnd(actions);
				implicitWait();				
				scrollToViewElement(columnName);
				size=driver.findElements(ObjRep.get(logicalName)).size();
				//	System.out.println(size);
				String lastRecord =driver.findElements(ObjRep.get(logicalName)).get(size-1).getAttribute("innerText");
				//	System.out.println(lastRecord);
			//	driver.findElement(ObjRep.get("RefreshButton")).click();
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				driver.findElements(ObjRep.get(logicalName)).get(0).click();
				WebElement element;
				ArrayList<String> obtainedStringList = new ArrayList<String>();
				ArrayList<String> sortedStringList = new ArrayList<String>();	
				ArrayList<Date> obtainedDateList = new ArrayList<Date>();
				ArrayList<Date> sortedDateList = new ArrayList<Date>();	
				ArrayList<Integer> obtainedIntegerList = new ArrayList<Integer>();
				ArrayList<Integer> sortedIntegerList = new ArrayList<Integer>();	
				DateFormat dateFormatter = new SimpleDateFormat("MM/dd/yy");
				//	System.out.println("Obtained List :");
				while(!(driver.findElement(ObjRep.get("FocusedCell")).getText()).equals(lastRecord)) {
					element=driver.findElement(ObjRep.get("FocusedCell"));
					System.out.println(element.getText().toLowerCase());
					if(dataType.equalsIgnoreCase("int")||dataType.equalsIgnoreCase("Integer")) {
						obtainedIntegerList.add(Integer.parseInt(element.getText()));
					}
					else if(dataType.equalsIgnoreCase("Date")) {
						obtainedDateList.add(dateFormatter.parse(element.getText()));
					}
					else {
						obtainedStringList.add(element.getText().toLowerCase());
					}
					actions.sendKeys(Keys.ARROW_DOWN).perform();
				}
				//	System.out.println(lastRecord.toLowerCase());
				//adding last data and copying to other list
				if(dataType.equalsIgnoreCase("int")||dataType.equalsIgnoreCase("Integer")) {
					obtainedIntegerList.add(Integer.parseInt(lastRecord));
					sortedIntegerList.addAll(obtainedIntegerList);
					if(sortingOrder.equalsIgnoreCase("ascending")) {
						Collections.sort(sortedIntegerList);
					}
					else if(sortingOrder.equalsIgnoreCase("descending")) {
						Collections.sort(sortedIntegerList,Collections.reverseOrder());
					}
					else {
						System.out.println("Enter the sorting order as ascending/descending");
					}
					//					System.out.println("Sorted List :");
					//					for(int sortedRecord:sortedIntegerList){
					//						System.out.println(sortedRecord);
					//					}
					//					System.out.println("Sorting: "+sortedIntegerList.equals(obtainedIntegerList));
					if(sortedIntegerList.equals(obtainedIntegerList)) {
						logger.info("The records are in "+sortingOrder+" order");
					}
					softAssertion.assertTrue(sortedIntegerList.equals(obtainedIntegerList),"The records are not in sorted order");
				}
				else if(dataType.equalsIgnoreCase("Date")) {
					obtainedDateList.add(dateFormatter.parse(lastRecord));
					sortedDateList.addAll(obtainedDateList);
					if(sortingOrder.equalsIgnoreCase("ascending")) {
						Collections.sort(sortedDateList);
					}
					else if(sortingOrder.equalsIgnoreCase("descending")) {
						Collections.sort(sortedDateList,Collections.reverseOrder());
					}
					else {
						System.out.println("Enter the sorting order as ascending/descending");
					}
					System.out.println("Sorted List :");
					for(Date sortedRecord:sortedDateList){
						System.out.println(dateFormatter.format(sortedRecord));
					}
					System.out.println("Sorting: "+sortedDateList.equals(obtainedDateList));
					if(sortedDateList.equals(obtainedDateList)) {
						logger.info("The records are in "+sortingOrder+" order");
					}
					softAssertion.assertTrue(sortedDateList.equals(obtainedDateList),"The records are not in sorted order");
				}
				else {
					obtainedStringList.add(lastRecord.toLowerCase());
					sortedStringList.addAll(obtainedStringList);
					if(sortingOrder.equalsIgnoreCase("ascending")) {
						Collections.sort(sortedStringList);
					}
					else if(sortingOrder.equalsIgnoreCase("descending")) {
						Collections.sort(sortedStringList,Collections.reverseOrder());
					}
					else {
						System.out.println("Enter the sorting order as ascending/descending");
					}
					//					System.out.println("Sorted List :");
					//					for(String sortedRecord:sortedStringList){
					//						System.out.println(sortedRecord);
					//					}
					//					System.out.println("Sorting: "+sortedStringList.equals(obtainedStringList));
					if(sortedStringList.equals(obtainedStringList)) {
						logger.info("The records are in "+sortingOrder+" order");
					}
					softAssertion.assertTrue(sortedStringList.equals(obtainedStringList),"The records are not in sorted order");
				}								
			//	driver.findElement(ObjRep.get("RefreshButton")).click();
				Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				softAssertion.assertAll();
			}
		}catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		}catch(NullPointerException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}


	//verifying the drop down values are in ascending order or not
	public void verifyDropDownSortingOrder(String dropDownValuesPath) {
		try {
			if (ObjRep.get(dropDownValuesPath) != null) {
				int i = 0;
				int size = driver.findElements(ObjRep.get(dropDownValuesPath)).size();
				if (size > 0) {
					logger.info("Verifying the dept drop down values are in ascending order or not");
					logger.info("Drop Down Vaules size: "+size);
					ArrayList<String> obtainedList = new ArrayList<String>();
					ArrayList<String> sortedList = new ArrayList<String>();
					String dropDownValue;
					while (i < size) {
						dropDownValue=driver.findElements(ObjRep.get(dropDownValuesPath)).get(i).getAttribute("innerText");
						obtainedList.add(dropDownValue);
						//System.out.println(dropDownValue);
						i++;
					}
					sortedList.addAll(obtainedList);
					Collections.sort(sortedList);
					for (String sortedRecord : sortedList) {
						System.out.println(sortedRecord);
					}
					if(obtainedList.equals(sortedList)) {
						logger.info("Drop down values are in sorted order");
					}else {
						logger.info("The records are not in sorted order");
					}
				}else {
					softAssertion.fail("The values " + dropDownValuesPath + " are not displayed");
				}
				softAssertion.assertAll();
			}            
		} catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/* verifying user role: get the value of roleName from application.properties file
	 get the values for permission ,assignedDataNo from role maintenance page excel*/
	public void verifyUserRole(String roleName, String permission, String assignedDataNo)
			throws NumberFormatException, IOException, InterruptedException {
		try {
			if ((roleName != null) && (permission != null) && (assignedDataNo != null)) {
				//Clicking on the user role
				explicitWaitForVisibility("RoleNameColumnRecords");
				explicitWaitForElementToBeClickable("FilterRoleName");
				explicitWaitForElementToBeClickable("RoleNameColumnRecords");
				click("FilterRoleName");
				enterIntoTextBox("FilterRoleName", roleName);
				explicitWaitForElementToBeClickable("GridRecord");
				click("GridRecord");	
				// Clicking on a Info permission
				explicitWaitForElementToBeClickable("DetailTitle");
				explicitWaitForElementToBeClickable("FilterPermission");
				explicitWaitForElementToBeClickable("AssignedData");
				click("FilterPermission");
				enterIntoTextBox("FilterPermission", permission);
				click("GridRecord");
				// Grant the permission for the user
				explicitWaitForElementToBeClickable("AssignedData");
				if (getTitle("AssignedData").equals(assignedDataNo)) {
					click("AssignSelected");
					click("SaveButton");
					click("YesButton");
					//DB commit takes time
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
					click("FilterPermission");
					enterIntoTextBox("FilterPermission", permission);
					//	System.out.println(getTitle("AssignedData"));
					refreshPage();
					explicitWaitForElementToBeClickable("Navigation");
					click("Navigation");
				} else {
					System.out.println("The user has required Permission");
					// Navigating to home screen
					click("BackLink");
				}
			}
			else {
				System.out.println("The parameter values are null");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//returns date picker default date 
	public Date getDefaultDatefromDatePicker(String logicalName) {
		Date defaultDate=null;
		try {
			if(ObjRep.get(logicalName)!=null ) {
				String date=driver.findElement(ObjRep.get(logicalName)).getAttribute("value");
				System.out.println("Date Picker Value:"+date);
				defaultDate=new SimpleDateFormat("MM/dd/yy").parse(date);  				
			}				
		}catch (NullPointerException e) {
			e.printStackTrace();
		}catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return defaultDate;
	}

	//returns today's date
	public Date getCurrentDate() {
		Date currentSystemDate=null;
		try {
			currentSystemDate=new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
			String date=dateFormat.format(currentSystemDate);
			System.out.println("current date");
			currentSystemDate=dateFormat.parse(date);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return currentSystemDate;		
	}
	
	public String getPresentDate()
	{
		Date currentSystemDate=new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
		String date=dateFormat.format(currentSystemDate);
		String[] newdate=date.split("/");
		int month=Integer.parseInt(newdate[0]);
		String newDate=month+"/"+newdate[1]+"/"+newdate[2];
		System.out.println(newDate);
		return newDate;
	}
	

	//Compare the date picker default date and today's date 
	public void verifyDefaultDateWithCurrentDate(String DatePickerTextBoxLogicalName) {
		try {
			if(ObjRep.get(DatePickerTextBoxLogicalName)!=null) {
				System.out.println("Verifying default date equal to current date");
				softAssertion.assertEquals(getDefaultDatefromDatePicker(DatePickerTextBoxLogicalName),getCurrentDate(),"Expected: "+getDefaultDatefromDatePicker(DatePickerTextBoxLogicalName)+"----Actual: "+getCurrentDate()+"Default date is not equal to the current date");
				softAssertion.assertAll();
			}
		}catch (NullPointerException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}	
	}

	//Clear the text displayed in the element
	public void clearElement(String logicalName) {
		try {
			if(ObjRep.get(logicalName)!=null ) {
				driver.findElement(ObjRep.get(logicalName)).clear();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}	
	}

	//displaying table records count 
	public void verifyTableRecordsCount(String tableRows,String lastColumnName,String firstColumnFilter) throws Exception{
		try {
			if(ObjRep.get(tableRows)!=null && ObjRep.get(lastColumnName)!=null&& ObjRep.get(firstColumnFilter)!=null) {
				int rowCount;
				rowCount=getTableRowCount(tableRows, lastColumnName, firstColumnFilter);
				if(rowCount==0) {
					System.out.println("No table records found");
				}
				else {
					System.out.println("Table Records Count: "+rowCount);
				}
			}
		}catch(NullPointerException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	// Verifying the UIN list records are within the to date
	public void verifyUinListWithinToDate(String columnData, String columnName, String columnFilter, Date toDate)
			throws Exception {
		Date date;
		try {
			if (ObjRep.get(columnData) != null && ObjRep.get(columnName) != null && ObjRep.get(columnFilter) != null) {
				System.out.println("Verifying Uin List Within Todate");
				int size, flag = 0;
				if (isVisible(columnFilter)) {
					flag = 1;
					explicitWaitForElementToBeClickable("GridViewMenu");
					click("GridViewMenu");
					explicitWaitForElementToBeClickable("ColumnFilter");
					click("ColumnFilter");
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
				}
				String column_Name = driver.findElement(ObjRep.get(columnName)).getAttribute("innerText");
				Actions actions = new Actions(driver);
				driver.findElements(ObjRep.get(columnData)).get(0).click();
				scrollToTableEnd(actions);
				implicitWait();
				size = driver.findElements(ObjRep.get(columnData)).size();
				explicitWaitForElementToBeClickable(columnData);
				while (!driver.findElements(ObjRep.get(columnData)).get(size - 1).getAttribute("innerText")
						.equals(driver.findElement(ObjRep.get("FocusedCell")).getText())) {
					actions.sendKeys(Keys.ARROW_LEFT).perform();
				}
				driver.findElements(ObjRep.get(columnData)).get(size - 1).click();
				//				System.out.println("To date: " + new SimpleDateFormat("MM/dd/yy").format(toDate) + "\n" + column_Name);
				while (!(driver.findElement(ObjRep.get("FocusedCell")).getText().equals(column_Name))) {
					explicitWaitForElementToBeClickable("FocusedCell");
					date = new SimpleDateFormat("MM/dd/yy")
							.parse(driver.findElement(ObjRep.get("FocusedCell")).getText());
					if (date.before(toDate) || date.equals(toDate)) {
						//						System.out.println(new SimpleDateFormat("MM/dd/yy").format(date));
					} else {
						softAssertion.fail("Displayed dates are not within the todate");
					}
					actions.sendKeys(Keys.ARROW_UP).perform();
					implicitWait();
				}
				if (flag == 1) {
					explicitWaitForElementToBeClickable("GridViewMenu");
					click("GridViewMenu");
					explicitWaitForElementToBeClickable("ColumnFilter");
					click("ColumnFilter");
				}
				softAssertion.assertAll();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verifying the UIN list records are within the to date
	public void verifyUinListStartsFromDate(String columnData, String columnName, String columnFilter, Date fromDate)
			throws Exception {
		Date date;
		try {
			if (ObjRep.get(columnData) != null && ObjRep.get(columnName) != null && ObjRep.get(columnFilter) != null) {
				System.out.println("Verifying Uin List starts from Fromdate");
				int size, flag = 0;
				if (isVisible(columnFilter)) {
					flag = 1;
					explicitWaitForElementToBeClickable("GridViewMenu");
					click("GridViewMenu");
					explicitWaitForElementToBeClickable("ColumnFilter");
					click("ColumnFilter");
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
				}
				String column_Name = driver.findElement(ObjRep.get(columnName)).getAttribute("innerText");
				Actions actions = new Actions(driver);
				driver.findElements(ObjRep.get(columnData)).get(0).click();
				scrollToTableEnd(actions);
				implicitWait();
				size = driver.findElements(ObjRep.get(columnData)).size();
				explicitWaitForElementToBeClickable(columnData);
				while (!driver.findElements(ObjRep.get(columnData)).get(size - 1).getAttribute("innerText")
						.equals(driver.findElement(ObjRep.get("FocusedCell")).getText())) {
					actions.sendKeys(Keys.ARROW_LEFT).perform();
				}
				driver.findElements(ObjRep.get(columnData)).get(size - 1).click();
				//				System.out.println("From date: " + new SimpleDateFormat("MM/dd/yy").format(fromDate) + "\n" + column_Name);
				while (!(driver.findElement(ObjRep.get("FocusedCell")).getText().equals(column_Name))) {
					explicitWaitForElementToBeClickable("FocusedCell");
					date = new SimpleDateFormat("MM/dd/yy")
							.parse(driver.findElement(ObjRep.get("FocusedCell")).getText());
					if (date.after(fromDate) || date.equals(fromDate)) {
						//						System.out.println(new SimpleDateFormat("MM/dd/yy").format(date));
					} else {
						softAssertion.fail("Displayed dates are not starts from fromdate");
					}
					actions.sendKeys(Keys.ARROW_UP).perform();
					implicitWait();
				}
				if (flag == 1) {
					explicitWaitForElementToBeClickable("GridViewMenu");
					click("GridViewMenu");
					explicitWaitForElementToBeClickable("ColumnFilter");
					click("ColumnFilter");
				}
				softAssertion.assertAll();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// verifying the UIN list item date is given date
	public void verifyUinListBetweenFromAndToDate(String columnData, String columnName, String columnFilter,
			Date dateValue) throws Exception {
		Date date;
		try {
			if (ObjRep.get(columnData) != null && ObjRep.get(columnName) != null && ObjRep.get(columnFilter) != null) {
				System.out.println("Verifying Uin List equals from date and to date");
				int size, flag = 0;
				if (isVisible(columnFilter)) {
					flag = 1;
					explicitWaitForElementToBeClickable("GridViewMenu");
					click("GridViewMenu");
					explicitWaitForElementToBeClickable("ColumnFilter");
					click("ColumnFilter");
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
				}
				String column_Name = driver.findElement(ObjRep.get(columnName)).getAttribute("innerText");
				Actions actions = new Actions(driver);
				driver.findElements(ObjRep.get(columnData)).get(0).click();
				scrollToTableEnd(actions);
				implicitWait();
				size = driver.findElements(ObjRep.get(columnData)).size();
				explicitWaitForElementToBeClickable(columnData);
				while (!driver.findElements(ObjRep.get(columnData)).get(size - 1).getAttribute("innerText")
						.equals(driver.findElement(ObjRep.get("FocusedCell")).getText())) {
					actions.sendKeys(Keys.ARROW_LEFT).perform();
				}
				driver.findElements(ObjRep.get(columnData)).get(size - 1).click();
				//				System.out.println("Date: " + new SimpleDateFormat("MM/dd/yy").format(dateValue) + "\n" + column_Name);
				while (!(driver.findElement(ObjRep.get("FocusedCell")).getText().equals(column_Name))) {
					explicitWaitForElementToBeClickable("FocusedCell");
					date = new SimpleDateFormat("MM/dd/yy")
							.parse(driver.findElement(ObjRep.get("FocusedCell")).getText());
					if (date.equals(dateValue)) {
						//						System.out.println(new SimpleDateFormat("MM/dd/yy").format(date));
					} else {
						softAssertion.fail("Displayed dates are not between from and todate");
					}
					actions.sendKeys(Keys.ARROW_UP).perform();
					implicitWait();
				}
				if (flag == 1) {
					explicitWaitForElementToBeClickable("GridViewMenu");
					click("GridViewMenu");
					explicitWaitForElementToBeClickable("ColumnFilter");
					click("ColumnFilter");
				}
				softAssertion.assertAll();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickOnEscapeButton() {
		try {
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ESCAPE).build().perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getPlaceholderValue(String logicalName) {
		String placeholderValue = null;
		try {
			if (ObjRep.get(logicalName) != null) {
				placeholderValue = driver.findElement(ObjRep.get(logicalName)).getAttribute("placeholder");
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return placeholderValue;
	}

	public void verifyValuesAreEqual(String value1, String value2) {
		try {

			logger.info("Verify values are equal Expected: " + value2 + "----Actual: " + value1);
			softAssertion.assertEquals(value1, value2, "Expected: " + value2 + "----Actual: " + value1 + "Both are not equal");
			softAssertion.assertAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// returns the corresponding attribute value of the given element
	public String getAttributeValue(String logicalName, String attributeName) {
		String value = null;
		try {
			if (ObjRep.get(logicalName) != null) {
				value = driver.findElement(ObjRep.get(logicalName)).getAttribute(attributeName);
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	// Verifying the drop down has values or not
	public void verifyDropDownValues(String logicalName) {
		try {
			int i = 0;
			int size = driver.findElements(ObjRep.get(logicalName)).size();
			if (size > 0) {
				System.out.println("The drop down values " + logicalName + " are displayed\nNo of values :" + size);
				System.out.println("The values are:");
				while (i < size) {
					System.out.println(driver.findElements(ObjRep.get(logicalName)).get(i).getAttribute("innerText"));
					i++;
				}
			} else {
				softAssertion.fail("The values " + logicalName + " are not displayed");
				softAssertion.assertAll();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Grant all permissions to user
	public void grantAllPermissions(String roleName) throws NumberFormatException, InterruptedException, IOException {
		try {
			if ((roleName != null)) {
				// Clicking on a user Role Name
				explicitWaitForVisibility("RoleNameColumnRecords");
				explicitWaitForElementToBeClickable("FilterRoleName");
				explicitWaitForElementToBeClickable("RoleNameColumnRecords");
				click("FilterRoleName");
				enterIntoTextBox("FilterRoleName",roleName);
				explicitWaitForElementToBeClickable("GridRecord");
				click("GridRecord");

				// Selecting row selectors
				explicitWaitForElementToBeClickable("DetailTitle");
				explicitWaitForElementToBeClickable("FilterPermission");
				explicitWaitForElementToBeClickable("AssignedData");
				explicitWaitForElementToBeClickable("GridViewMenu");
				click("GridViewMenu");
				explicitWaitForElementToBeClickable("RowSelector");
				click("RowSelector");

				// Grant all permissions
				explicitWaitForElementToBeClickable("ColumnCheckBox");
				click("ColumnCheckBox");
				explicitWaitForElementToBeClickable("AssignSelected");
				click("AssignSelected");

				// clicking on save button
				if (isEnabled("SaveButton")) {
					click("SaveButton");
					click("YesButton");
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
					refreshPage();
					explicitWaitForElementToBeClickable("Navigation");
					click("Navigation");
				} else {
					System.out.println("The user has all Permission");
					// Navigating to home screen
					click("BackLink");
				}
			}
			else {
				System.out.println("Role name value is null");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// verify the element is enabled
	public boolean isEnabled(String logicalName) {
		try {
			if (ObjRep.get(logicalName) != null) {
				logger.info("Verify the " + logicalName + " is enabled");
				if ((driver.findElement(ObjRep.get(logicalName)).isEnabled())
						&& (!driver.findElement(ObjRep.get(logicalName)).getAttribute("class").contains("disabled"))) {
					return true;
				}
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	//Verifying the element is focused or not 
	public void verifyElementIsFocused(String logicalName) {
		try {
			if (ObjRep.get(logicalName) != null) {				
				softAssertion.assertTrue(driver.findElement(ObjRep.get(logicalName)).getAttribute("class").contains("focused"),"The element is not focused");
				softAssertion.assertAll();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	//returns true if any table record has UIN history else returns false
	public boolean isUinHistoryAvailable(String lastColumnRows) {
		try {
			if (ObjRep.get(lastColumnRows) != null) {
				if (driver.findElements(ObjRep.get(lastColumnRows)).size() == 0) {
					System.out.println("No records found");
					return false;
				} 
				else {
					Actions actions = new Actions(driver);
					driver.findElements(ObjRep.get(lastColumnRows)).get(0).click();
					scrollToTableEnd(actions);
					while (driver.findElements(ObjRep.get("FocusedColumnCell")).size()>0) {
						explicitWaitForElementToBeClickable("FocusedColumnCell");
						explicitWaitForElementToBeClickable("ViewHistoryButton");
						click("ViewHistoryButton");
						if(isVisible("NoHistoryFoundMessage")) {
							explicitWaitForElementToBeClickable("OkButton");
							click("OkButton");
							/**Adding this line after smoke failure, When clicked on OK button of the pop the control is lost
							 and the action class was not performed **/
							click("FocusedColumnCell");
							actions.sendKeys(Keys.ARROW_UP).perform();
						}
						else {
							return true;
						}
					}
					if(driver.findElements(ObjRep.get("FocusedColumnCell")).size()>0) {
						return false;
					}
				}
			}			
		} catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// Verifying the drop down has values or not. logical name: the drop down path ,dropDownValuesActualCount: the total count of the values 
	public void verifyDropDownValues(String dropDownValuesPath,String dropDownValuesActualCount) {
		try {
			int i = 0;
			int size = driver.findElements(ObjRep.get(dropDownValuesPath)).size();
			if (size <= 0) {
				softAssertion.fail("The values " + dropDownValuesPath + " are not displayed");					
			}else if(size > Integer.parseInt(dropDownValuesActualCount)) {
				softAssertion.fail("The drop down values count are exceed than the actual");	
			}else {
				System.out.println("The drop down values " + dropDownValuesPath + " are displayed\nNo of values :" + size);
				System.out.println("The values are:");
				while (i < size) {
					System.out.println(driver.findElements(ObjRep.get(dropDownValuesPath)).get(i).getAttribute("innerText"));
					i++;
				}
			}
			softAssertion.assertAll();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Verifying table should list all the MPS messages which belong to the selected message family
	public void verifyTableRecordsMessageFamily(String tableRows, String lastColumnName, String firstColumnFilter,String messageFamily) {
		try {
			if (ObjRep.get(tableRows) != null && ObjRep.get(lastColumnName) != null && ObjRep.get(firstColumnFilter) != null && messageFamily!=null) {
				if (driver.findElements(ObjRep.get(tableRows)).size() == 0) {
					System.out.println("No records found");
				} else {
					int flag = 0;
					if (isVisible(firstColumnFilter)) {
						flag = 1;
						explicitWaitForElementToBeClickable("GridViewMenu");
						click("GridViewMenu");
						explicitWaitForElementToBeClickable("ColumnFilter");
						click("ColumnFilter");
						Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Lowest")));
					}
					String columnName = driver.findElement(ObjRep.get(lastColumnName)).getAttribute("innerText");
					Actions actions = new Actions(driver);
					driver.findElements(ObjRep.get(tableRows)).get(0).click();
					scrollToTableEnd(actions);
					while (!(driver.findElement(ObjRep.get("FocusedCell")).getText().equals(columnName))) {
						explicitWaitForElementToBeClickable("FocusedCell");
						verifyTextValue("SelectedTableFamilyRecord",messageFamily);
						actions.sendKeys(Keys.ARROW_UP).perform();
						implicitWait();
					}
					if (flag == 1) {
						explicitWaitForElementToBeClickable("GridViewMenu");
						click("GridViewMenu");
						explicitWaitForElementToBeClickable("ColumnFilter");
						click("ColumnFilter");
					}
					driver.findElement(ObjRep.get("RefreshButton")).click();
					Thread.sleep(Long.parseLong(propReader.getApplicationproperty("Low")));
				}
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	//Clicking on multiple row selector check boxes
	public void clickMultipleRowSelectorCheckBoxes(String logicalName) throws Exception {
		try {
			if (ObjRep.get(logicalName) != null) {
				scrollToTableEnd();
				click("FocusedResolveRecord");
				Actions action = new Actions(driver);
				action.sendKeys(Keys.ARROW_UP).build().perform();
				while (true) {
					explicitWaitForElementToBeClickable(logicalName);
					click(logicalName);
					implicitWait();
					action.sendKeys(Keys.ARROW_UP).build().perform();
					if (driver.findElements(ObjRep.get(logicalName)).size() > 0) {
						if (!driver.findElement(ObjRep.get(logicalName)).isSelected()) {
							break;
						}
					}
					else
						break;
				}
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void verifyTableRowContentsForStatus(String value){
		try {
			List<WebElement> list=driver.findElements(By.xpath("//td[@data-index='status']"));

			int s=list.size();
			System.out.println("The List size is : " +s);
			int i;
			for(i=0;i<=list.size();i++)
			{	

				softAssertion.assertEquals(list.get(i).getText() ,value);
			}
			if(i==0)
			{
				System.out.println("No rows present");    
			}
		}
		catch(StaleElementReferenceException e)
		{
			System.out.println(e);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}
	
	//Method to verify there are no contents in the summary table
	public void verifyNoRecordsAreInSummary(String logicalName) {
	
	try {
		List<WebElement> list=driver.findElements(ObjRep.get(logicalName));
		int s=list.size();
//		System.out.println("Total records available :" +s);
		softAssertion.assertEquals(s,0, "Records are available in the summary table");
		logger.info("No records are available in summary table as search is invalid");
		
		softAssertion.assertAll();
	}catch (Exception e) {
		e.printStackTrace();
	}
}
	
	
	//To check element is not present
	public void checkElementIsNotPresent(String logicalName) {
		try {
			softAssertion.assertFalse(isVisible(logicalName),"The element is present");
			logger.info("verifyElementIsNotPresent " + logicalName);
			softAssertion.assertAll();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Verifying the department drop down values are in the specific pattern or not
	public void verifyDepartmentDropDownPattern(String dropDownValuesPath) {
		try {
			if (ObjRep.get(dropDownValuesPath) != null) {
				int i = 1;
				int size = driver.findElements(ObjRep.get(dropDownValuesPath)).size();
				if (size > 0) {
					logger.info("Verifying values are in the correct pattern or not");
					Pattern p = Pattern.compile("^[1-9][0-9]+ - [A-Za-z0-9-,'/ ]+[*]*$");
					Matcher m;
					String dropDownvalue;
					logger.info("Drop Down Vaules size: "+size);
					while (i < size) {
						dropDownvalue=driver.findElements(ObjRep.get(dropDownValuesPath)).get(i).getAttribute("innerText");
						m= p.matcher(dropDownvalue);
						softAssertion.assertTrue(m.matches(),"dropdown value: "+dropDownvalue+"not in the dept - deptid format");
						i++;
					}
				} else {
					softAssertion.fail("The values " + dropDownValuesPath + " are not displayed");
				}
				softAssertion.assertAll();
			}			
		} catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//verifying the dept drop down values are in ascending order or not
	public void verifyDepartmentSortingOrder(String dropDownValuesPath) {
		try {
			if (ObjRep.get(dropDownValuesPath) != null) {
				int i = 1;
				int size = driver.findElements(ObjRep.get(dropDownValuesPath)).size();
				if (size > 0) {
					logger.info("Verifying the dept drop down values are in ascending order or not");
					logger.info("Drop Down Vaules size: "+size);
					ArrayList<Integer> obtainedList = new ArrayList<Integer>();
					ArrayList<Integer> sortedList = new ArrayList<Integer>();
					String dropDownValue,value[];
					while (i < size) {
						dropDownValue=driver.findElements(ObjRep.get(dropDownValuesPath)).get(i).getAttribute("innerText");
						value=dropDownValue.split(" ");
						obtainedList.add(Integer.parseInt(value[0]));
						//	System.out.println(value[0]);
						i++;
					}
					sortedList.addAll(obtainedList);
					Collections.sort(sortedList);
					//					for (int sortedRecord : sortedList) {
					//						System.out.println(sortedRecord);
					//					}
					if(obtainedList.equals(sortedList)) {
						logger.info("Department drop down values are in sorted order");
					}else {
						logger.info("The records are not in sorted order");
					}
				}else {
					softAssertion.fail("The values " + dropDownValuesPath + " are not displayed");
				}
				softAssertion.assertAll();
			}			
		} catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//it sort based on locale. Parameters are same as what we used for other sorting methods.It sorts all data type values and need refresh button in the screen  
	public void sort(String logicalName,String columnName,String sortingOrder) throws InterruptedException {
		try {
			if(ObjRep.get(logicalName)!=null && ObjRep.get(columnName)!=null) {
				JavascriptExecutor js = (JavascriptExecutor) driver;	
				driver.findElements(ObjRep.get(logicalName)).get(0).click();		
				Actions actions = new Actions(driver);
				actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
				actions.keyUp(Keys.CONTROL);
				scrollToViewElement(columnName);
				int size=driver.findElements(ObjRep.get(logicalName)).size();
				//	System.out.println(size);
				String lastRecord =driver.findElements(ObjRep.get(logicalName)).get(size-1).getAttribute("innerText");
				//	System.out.println(lastRecord);
				driver.findElement(ObjRep.get("RefreshButton")).click();
				Thread.sleep(2000);
				driver.findElements(ObjRep.get(logicalName)).get(0).click();
				WebElement element;
				List<Object> obtainedList = new ArrayList<Object>();		
				Object executeScript=null;
				while(!(driver.findElement(ObjRep.get("FocusedCell")).getText()).equals(lastRecord)) {
					element=driver.findElement(ObjRep.get("FocusedCell"));	
					//In SIM application the records are sorted in case-insensitive order i.e)both the small and capital letters are considering as same.So converting to lower case taken place
					obtainedList.add(element.getText());
					actions.sendKeys(Keys.ARROW_DOWN).perform();
				}
				obtainedList.add(lastRecord);
				Object records1[]=obtainedList.toArray();
			//	System.out.println("obtained list:\n"+obtainedList);
				System.out.println("Application values Before sorting:");
				for(Object r1:records1) {
					System.out.println(r1);
				}
				if(sortingOrder.contains("ascending")) {
				  	executeScript = js.executeScript("return [].slice.call(arguments).sort(new Intl.Collator('en').compare);",records1);
				  System.out.println("Script: "+executeScript);
				}
				Object records2[]=(Object[])records1;
				if(sortingOrder.contains("descending")) {
					executeScript = js.executeScript("return [].slice.call(arguments).sort(new Intl.Collator('en').compare).reverse();",records1);
					System.out.println("Script: "+executeScript);
				}
				System.out.println("After sorting");
				for(Object r2:records2) {
					System.out.println(r2);
				}				
				System.out.println("Sorting: "+records1.equals(records2));
				softAssertion.assertTrue(records1.equals(records2),"The records are not in sorted order");
				//if screen doesn't contain refresh button pls modify this code using refresh page otherwise it throws exception
				driver.findElement(ObjRep.get("RefreshButton")).click();
				Thread.sleep(2000);
				softAssertion.assertAll();
			}
		}catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		}catch(NullPointerException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}


//Integer column sorting without refreshing the screen

public void intSorting(String logicalName, String columnName, String sortingOrder)
		throws InterruptedException, ParseException {
	
	try {
		
		if (ObjRep.get(logicalName) != null && ObjRep.get(columnName) != null) {
			int size;
			
			Actions actions = new Actions(driver);
			
				String firstRecord = driver.findElements(ObjRep.get(logicalName)).get(0).getAttribute("innerText");
//				String id=driver.findElements(ObjRep.get(logicalName)).get(0).getAttribute("id");
//				System.out.println(firstRecord);
				driver.findElements(ObjRep.get(logicalName)).get(0).click();
				scrollToTableEnd(actions);
				implicitWait();
				scrollToViewElement(columnName);
				pressHomeKey();
			
//			String firstRecord = driver.findElements(ObjRep.get(logicalName)).get(0).getAttribute("innerText");
			size=driver.findElements(ObjRep.get(logicalName)).size();
			driver.findElements(ObjRep.get(logicalName)).get(size-1).click();
			WebElement element;
			ArrayList<Integer> obtainedIntegerList = new ArrayList<Integer>();
			ArrayList<Integer> sortedIntegerList = new ArrayList<Integer>();
			
			while(!(driver.findElement(ObjRep.get("FocusedCell")).getText()).equals(firstRecord)) { 
//			while (driver.findElements(ObjRep.get("FocusedCell")).size()>0) {	
			element = driver.findElement(ObjRep.get("FocusedCell"));
			obtainedIntegerList.add(Integer.parseInt(element.getText()));
			actions.sendKeys(Keys.ARROW_UP).perform();
			
		}
			obtainedIntegerList.add(Integer.parseInt(firstRecord));
		Collections.reverse(obtainedIntegerList);
			System.out.println("Obtained List :"+obtainedIntegerList);
			
			sortedIntegerList.addAll(obtainedIntegerList);
			if (sortingOrder.equalsIgnoreCase("ascending")) {
				Collections.sort(sortedIntegerList);
			} else if (sortingOrder.equalsIgnoreCase("descending")) {
				Collections.sort(sortedIntegerList, Collections.reverseOrder());
			} else {
				System.out.println("Enter the sorting order as ascending/descending");
			}
			System.out.println("Sorted List :"+sortedIntegerList);				
			System.out.println("Sorting" + sortedIntegerList.equals(obtainedIntegerList));
			Assert.assertTrue(sortedIntegerList.equals(obtainedIntegerList),
					"The records are not in sorted order");
		}

	} catch (org.openqa.selenium.NoSuchElementException e) {
		e.printStackTrace();
	} catch (NullPointerException e) {
		e.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	}
}


//To verify dropdown values comparing expected from excel sheet
//In test : To add the value retrived from excel sheet to string variable, click on the dropdown and pass dropdown values xpath and string variable reference to the method
//	String a=map.get("ExcelColumnName");
//	ticketFilterPage.click("Dropdownxpath");
//	ticketFilterPage.verifyDropDownValuesWithExcel("DropdownValuesxpath",a); 
//In Excel sheet : Enter all the dropdown value in single cell but in new line for each value
//	Eg: DropDown1
//		DropDown2
	
	public void verifyDropDownValuesWithExcel(String logicalName, String expected) {
		// TODO Auto-generated method stub
		try {
			if (ObjRep.get(logicalName) != null && ObjRep.get(expected) != null)
			{
			int size = driver.findElements(ObjRep.get(logicalName)).size();
			if(size>0) {
			ArrayList<String> obtainedList = new ArrayList<String>();
			for(int i=0;i<size;i++)
			{
				obtainedList.add(driver.findElements(ObjRep.get(logicalName)).get(i).getAttribute("innerText"));
			}
			System.out.println("Values from application" + obtainedList);
			System.out.print("Values from excel sheet ="+ expected);			
			String[] values=expected.split("\n");
			List<String> al=Arrays.asList(values);
			Assert.assertEquals(obtainedList, al,"Drop down values are not matched");
			}else {
				System.out.println("Unable to retreive dropdown values from application");
			}
		
		} 
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	//Method to verify if the table column contents are same. 
	//Used to check if records in summary are filtered based on the filter criteria.
	//Search limit to be set as 10 - if the records are out of page coverage, method will not be able to traverse through all the records in the column
		public void verifyTableRowContentsOfColumn(String logicalName, String value){
		try {
			if (ObjRep.get(logicalName) != null && value !=null) 
			{
			List<WebElement> list=driver.findElements(ObjRep.get(logicalName));
			 ArrayList<String> Actual = new ArrayList<String>();
//		//	List<String> Actual = new ArrayList<String>();
//			String[] dropDownValues = new String [list.size()];
			
			for (WebElement a:list)
			{
				Actual.add(a.getText());
			}

			if(list.size()==0) {
				System.out.println("No rows present");
			}else {
				for(int j=0;j<list.size();j++)
				{
					softAssertion.assertEquals(list.get(j).getText() ,value, "Values are not equal");
						
				}
			}
			softAssertion.assertAll();
			}
		}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		}


/*	
	public String ClickOnSegmentRowAndFetchsegmentId( String logicalName,  String value )
	{ String  segmentId=null;
	List <WebElement> searchResultsList=getWebElementList(logicalName);
	System.out.println(searchResultsList.size());
	for(int i=0; i<searchResultsList.size(); i++) {

		WebElement row =searchResultsList.get(i) ;
		logger.info("Logical name : "+ logicalName +" ---- "+"Expected Value : "+value+" ---- "+"Actual Value : "+ row.getText());

		if(row.getText().equalsIgnoreCase(value)) {

			logger.info("Matched Logical name : "+ logicalName +" ---- "+"Expected Value : "+value+" ---- "+"Actual Value : "+ row.getText());
			segmentId= row.findElement(By.xpath("./preceding::td[1]")).getText();
			row.click();
			break;		

		}}

	return segmentId;
	}



public void ClickOnSegmentRowAndAssertCustomerCount( String logicalName,  String value, String customerCount )
{ String  segmentId=null;
List <WebElement> searchResultsList=getWebElementList(logicalName);
System.out.println(searchResultsList.size());
for(int i=0; i<searchResultsList.size(); i++) {

	WebElement row =searchResultsList.get(i) ;
	logger.info("Logical name : "+ logicalName +" ---- "+"Expected Value : "+value+" ---- "+"Actual Value : "+ row.getText());

	if(row.getText().equalsIgnoreCase(value)) {

		logger.info("Matched Logical name : "+ logicalName +" ---- "+"Expected Value : "+value+" ---- "+"Actual Value : "+ row.getText());
		Assert.assertEquals(row.findElement(By.xpath("./following-sibling::td[3]")).getText(),customerCount ); ;
		//row.click();
		break;		

	}}

}*/


