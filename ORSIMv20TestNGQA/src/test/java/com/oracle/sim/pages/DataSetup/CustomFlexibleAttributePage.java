package com.oracle.sim.pages.DataSetup;

import org.openqa.selenium.By;

import com.oracle.sim.pages.Base.SimBasePage;

public class CustomFlexibleAttributePage extends SimBasePage {
	public CustomFlexibleAttributePage() throws Exception {
		super();
		addObject("Title",By.xpath("//h3[@id='sim-screen-title']"));
		addObject("EditButton",By.xpath("//oj-button[@id='detail-edit-button']"));
		addObject("AddButton",By.xpath("//oj-button[@id='add-button']"));
		addObject("DetailTitle",By.xpath("//h3[@id='sim-screen-detail-title']"));
		addObject("DisplayLabel",By.xpath("//textarea[@id='input-display-label|input']"));
		addObject("DataType",By.xpath("//div[@id='oj-select-choice-input-data-type']"));
		addObject("DetailNewTitle",By.xpath("//h3[text()='Detail New']"));
		addObject("FunctionalArea",By.cssSelector("#oj-select-choice-input-functional-area"));
		addObject("Required",By.xpath("//oj-switch[@id='input-required']"));
		addObject("PublishAttribute",By.xpath("//oj-switch[@id='input-publish']"));
		addObject("ApplyButton",By.xpath("//oj-button[@id='detail-apply-button']"));
		addObject("EditButton",By.xpath("//oj-button[@id='detail-edit-button']"));
		addObject("SaveButton",By.xpath("//oj-button[@id='save-button']//button[@class='oj-button-button oj-component-initnode']"));		
		addObject("CancelButton",By.xpath("//oj-button[@id='detail-cancel-button']"));
		addObject("RemoveButton",By.xpath("//oj-button[@id='remove-button']"));
		addObject("ConfirmationText",By.xpath("//span[contains(text(),'Are you sure you want to save the changes?')]"));
		addObject("RemoveConfirmation",By.xpath("//span[contains(text(),'The selected records will be deleted. Do you want')]"));
		addObject("NorowsText",By.xpath("//span[@class='rowset-message-text']"));
		addObject("YesButton",By.xpath("//oj-dialog[@id='sim-main-message-dialog']//span[text()='Yes']"));
		addObject("NoButton",By.xpath("//oj-dialog[@id='sim-main-message-dialog']//span[text()='No']"));
		addObject("RefreshButton",By.xpath("//oj-button[@id='refresh-button']"));
		addObject("FilterDisplayLabel",By.xpath("//input[contains(@id,'input-column-filter-displayLabel')]"));
		addObject("FilterDataType",By.xpath("//input[contains(@id,'input-column-filter-dataType')]"));
		addObject("FilterFunctionalArea",By.xpath("//input[contains(@id,'input-column-filter-functionalArea')]"));
		addObject("FilterRequired",By.xpath("//input[contains(@id,'input-column-filter-required')]"));	
		addObject("Vertical",By.xpath("//div[contains(text(),'962')]"));
		addObject("GridRecord", By.xpath("//mark[contains(@class,'grid-highlight')]"));
		addObject("OkButton",By.xpath("//oj-button[contains(@class,'oj-button-primary oj-button oj-component oj-enabled')]"));
		addObject("DisplayLabelBorder",By.xpath("//oj-text-area[@id='input-display-label']//div[@class='oj-text-field-container']"));
		addObject("DataTypeBorder",By.xpath("//oj-select-one[@id='input-data-type']//div[@class='oj-text-field-container']"));
		addObject("FunctionalAreaBorder",By.xpath("//oj-select-one[@id='input-functional-area']//div[@class='oj-text-field-container']"));
		addObject("DisplayLabelMsg",By.xpath("//oj-text-area[@id='input-display-label']"));
		addObject("DataTypeLabelMsg",By.xpath("//oj-select-one[@id='input-data-type']//span[@class='oj-message-content']"));
		addObject("FunctionalAreaLabelMsg",By.xpath("//oj-select-one[@id='input-functional-area']//span[@class='oj-message-content']"));
		addObject("LongMinValue",By.xpath("//input[@id='input-minimum-long-value|input']"));
		addObject("LongMaxValue",By.xpath("//input[@id='input-maximum-long-value|input']"));
		addObject("LongMinValueMsg",By.xpath("//oj-input-number[@id='input-minimum-long-value']//span[@class='oj-message-content']"));
		addObject("LongMaxValueMsg",By.xpath("//oj-input-number[@id='input-maximum-long-value']//span[@class='oj-message-content']"));
		addObject("DecimalMinValue",By.xpath("//input[@id='input-minimum-decimal-value|input']"));
		addObject("DecimalMaxValue",By.xpath("//input[@id='input-maximum-decimal-value|input']"));
		addObject("DecimalMinValueMsg",By.xpath("//oj-input-number[@id='input-minimum-decimal-value']//span[@class='oj-message-content']"));
		addObject("DecimalMaxValueMsg",By.xpath("//oj-input-number[@id='input-maximum-decimal-value']//span[@class='oj-message-content']"));
		addObject("TextMinValue",By.xpath("//input[@id='input-minimum-text-value|input']"));
		addObject("TextMaxValue",By.xpath("//input[@id='input-maximum-text-value|input']"));
		addObject("TextMinValueMsg",By.xpath("//oj-input-number[@id='input-minimum-text-value']//span[@class='oj-message-content']"));
		addObject("TextMaxValueMsg",By.xpath("//oj-input-number[@id='input-maximum-text-value']//span[@class='oj-message-content']"));	
		addObject("FunctionalAreaColRecords",By.xpath("//td[contains(@id,'-col-functionalArea')]"));
	}
}
