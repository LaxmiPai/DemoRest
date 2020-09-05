package com.oracle.sim.pages.Configuration;
import org.openqa.selenium.By;

import com.oracle.sim.pages.Base.SimBasePage;

	
public class CarrierServicesPage extends SimBasePage {
	
	public CarrierServicesPage() throws Exception {
		super();
		// TODO Auto-generated constructor stub
		
		//Button and Icons of Carrier Services Page..
		addObject("RefreshButton", By.xpath("//span[text()='Refresh']"));
		addObject("Title", By.xpath("//h3[@id='sim-screen-title'and text()='Carrier Services']"));
		addObject("AddIcon", By.id("add-button"));
		addObject("ApplyButton", By.id("detail-apply-button"));
		addObject("CancelButton",By.id("detail-cancel-button"));
		addObject("SaveIcon", By.id("save-button"));
		addObject("YesButton", By.xpath("//span[text()='Yes']"));
		addObject("NoButton", By.xpath("//span[text()='No']"));
		addObject("DeleteIcon", By.id("delete-button"));
		addObject("EditButton", By.id("detail-edit-button"));
		
		// Labels of Detail New FIelds 
		addObject("CarrierLabel",By.xpath("//span[@id='input-carrier-id|hint']"));
		addObject("CodeLabel",By.xpath("//span[@id='input-code|hint']"));
		addObject("DescriptionLabel",By.xpath("//span[@id='input-code|hint']"));
		
		addObject("FilterByDescription", By.xpath("//div[text()='Description']/following::input[2]"));
		addObject("FilterByCode",By.xpath("//div[text()='Code']/following::input[1]"));
		addObject("GridHighLight",By.xpath("//mark[@class='grid-highlight']"));
		addObject("FilterBySystem",By.xpath("//div[text()='System']/following::input[8]"));
	    
	    //Detail New Block Objects
	    addObject("DetailNewTitle",By.xpath("//h3[@id='sim-screen-title'and text()='Detail New']"));
	    addObject("CarrierDropdown",By.xpath("//a[contains(@class,'oj-select-arrow')]"));
	    addObject("CodeInputTextBox",By.xpath("//input[@id='input-code|input']"));
	    addObject("DescriptionTextBox",By.xpath("//textarea[@id='input-description|input']"));
	    addObject("DeliveryDaysField",By.xpath("//input[@id='input-service-time|input']"));
	    addObject("WeightRequiredSwitch",By.xpath("//oj-switch[@id='input-weight-required']"));
	    addObject("PackageSizeRequiredSwitch",By.xpath("//oj-switch[@id='input-carton-size-required']"));
	    addObject("DefaultSwitch",By.xpath("//oj-switch[@id='input-carrier-default']"));
		
		
	    addObject("NorecordsMsg", By.xpath("//span[@class='rowset-message-text']"));
	    addObject("Valueisrequired",By.xpath("//div[@class='oj-message-summary'][contains(text(),'Value is required.')]"));
	    addObject("DeleteConfirmMsg",By.xpath("//span[contains(text(),'The selected records will be deleted. Do you want')]"));
	    addObject("RefreshWarningMsg",By.xpath("//span[contains(text(),'If you continue, your changes will not be saved. D')]"));
		addObject("OKfooterButton",By.xpath("//div[@class='oj-dialog-footer']//following:: oj-button[1]"));
		addObject("CancelfooterButton",By.xpath("//div[@class='oj-dialog-footer']//following:: oj-button[2]"));
		addObject("RemoveErMsg",By.xpath("//span[contains(text(),'The selected records cannot be deleted since one o')]"));
		addObject("OkButton",By.xpath("//button[@class='oj-button-button oj-component-initnode']"));
		addObject("NoRecordDelete",By.xpath("//span[contains(text(),'No records selected. Select one or more records to')]"));
		
		
	}

	
	
	

}
