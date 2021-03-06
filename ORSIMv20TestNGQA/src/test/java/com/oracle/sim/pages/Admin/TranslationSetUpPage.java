package com.oracle.sim.pages.Admin;

import org.openqa.selenium.By;

import com.oracle.sim.pages.Base.SimBasePage;

public class TranslationSetUpPage extends SimBasePage {
	public TranslationSetUpPage() throws Exception {
		// TODO Auto-generated constructor stub
		super();
	
		
	
		addObject("Translations", By.xpath("//span[text()='Translations']"));
		addObject("TranslationSetUp", By.xpath("//span[text()='Translation Setup']"));
		addObject("LocaleDropdown", By.xpath("//div[@id='oj-select-choice-input-locale-selection']//a[@class='oj-select-arrow oj-component-icon oj-clickable-icon-nocontext oj-select-open-icon']"));
		addObject("BundleDropdown", By.xpath("//div[@id='oj-select-choice-input-bundle-type-selection']//a[@class='oj-select-arrow oj-component-icon oj-clickable-icon-nocontext oj-select-open-icon']"));
		addObject("ApplyButton", By.xpath("//oj-button[@id='bundle-selection-apply-button']//button[@class='oj-button-button oj-component-initnode']"));
		addObject("TransSetUpTitle", By.xpath("//h3[@id='sim-screen-title']"));
		
		addObject("TransFilter", By.xpath("//input[contains(@id,'input-column-filter-value')]"));
		addObject("TopicFilter", By.xpath("//input[contains(@id,'input-column-filter-topic')]"));
		addObject("KeyFilter", By.xpath("//input[contains(@id,'input-column-filter-key')]"));
		addObject("DescFilter", By.xpath("//oj-lux-grid[@id='sim-screen-grid-comp']//input[contains(@id,'input-column-filter-description')]"));
		
		addObject("GridRecord", By.xpath("//mark[contains(@class,'grid-highlight')]"));
		addObject("TransRecord", By.xpath("//td[contains(@id,'-col-value')]"));
		addObject("TopicRecord", By.xpath("//td[contains(@id,'-col-topic')]"));
		addObject("KeyRecord", By.xpath("//td[contains(@id,'-col-key')]"));
		addObject("DescRecord", By.xpath("//td[contains(@id,'-col-description')]"));
		
		addObject("RefreshButton", By.xpath("//oj-button[@id='refresh-button']"));
		addObject("FocusedCell",By.xpath("//tr//*[contains(@class,'cell-focused')]"));
		addObject("SelectLocaleDropdown", By.xpath("//div[@id='oj-select-choice-input-locale-selection']//div[@class='oj-text-field-middle']//span[text()='Select']"));
		addObject("SelectBundleDropdown", By.xpath("//div[@id='oj-select-choice-input-bundle-type-selection']//div[@class='oj-text-field-middle']//span[text()='Select']"));
		addObject("DropDownValues",By.xpath("//div[@id='__oj_zorder_container']//li"));
		 addObject("DropDropDownList",By.xpath("//ul[@id='oj-listbox-results-input-locale-selection']//li"));
		 addObject("DropDownFilter",By.xpath("//div[@id='oj-listbox-drop']//input[contains(@class,'oj-listbox-input')]"));
		 addObject("DropDownHighlightedValue",By.xpath("//span[@class='oj-highlighttext-highlighter']"));
		 addObject("FirstRow", By.xpath("//tbody//tr[1][contains(@id,'-group-all')]"));
		 addObject("FirstCell", By.xpath("//tbody//tr[1]//td[contains(@id,'-col-topic')]"));
		 addObject("SecondCell", By.xpath("//tbody//tr[1]//td[contains(@id,'-col-key')]"));
		 addObject("ThirdCell", By.xpath("//tbody//tr[1]//td[contains(@id,'-col-value')]"));
		 addObject("FourthCell", By.xpath("//tbody//tr[1]//td[contains(@id,'-col-description')]"));
		 
		 addObject("EditButton", By.xpath("//oj-button[@id='detail-edit-button']"));
		 addObject("TopicField", By.xpath("//oj-select-one[@id='input-topic']"));
		 addObject("KeyField", By.xpath("//oj-text-area[@id='input-key']"));
		 addObject("TransField", By.xpath("//textarea[@id='input-value|input']"));
		 addObject("DescField", By.xpath("//textarea[@id='input-description|input']"));
		 addObject("AplyButton", By.xpath("//oj-button[@id='detail-apply-button']"));
		 addObject("CnclButton", By.xpath("//oj-button[@id='detail-cancel-button']"));
		 addObject("SaveButton", By.xpath("//oj-button[@id='save-button']"));
		 
		 addObject("YesButton",By.xpath("//oj-dialog[@id='sim-main-message-dialog']//span[text()='Yes']"));
		 addObject("NoButton",By.xpath("//oj-dialog[@id='sim-main-message-dialog']//span[text()='No']"));
		 
		 addObject("ConfirmationMsg",By.xpath("//span[contains(text(),'Are you sure you want to save the changes?')]"));
		 addObject("ErrorMsg",By.xpath("//span[@class='oj-sm-margin-1x-end']"));
		 addObject("LocaleHeader",By.xpath("//span[@class='oj-sm-margin-1x-end']"));
		 
		 addObject("TopicLogic", By.xpath("//td[contains(@id,'-col-topic')]"));
		 addObject("TopicCol", By.xpath("//div[contains(text(),'Topic')] "));
		
		
		 
		
	}
}
