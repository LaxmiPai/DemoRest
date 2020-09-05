package com.oracle.sim.pages.Lookup;

import org.openqa.selenium.By;

import com.oracle.sim.pages.Base.SimBasePage;

public class UinLookupPage extends SimBasePage{
	public UinLookupPage() throws Exception {
		super();
		addObject("Title",By.xpath("//h3[@id='sim-screen-title']"));
		addObject("StoreDropDown",By.xpath("//oj-select-one[@id='input-filter-store']"));
		addObject("StoreDropDownValues",By.xpath("//div[@id='__oj_zorder_container']//li"));
		addObject("StoreLabel",By.xpath("//span[@id='input-filter-store|hint']"));
		addObject("UinLookupItemLabel",By.xpath("//span[@id='input-filter-item|hint']"));
		addObject("UinLabel",By.xpath("//span[@id='input-filter-uin|hint']"));
		addObject("StatusLabel",By.xpath("//span[@id='input-filter-status|hint']"));
		addObject("UserLabel",By.xpath("//label[text()='User']"));
		addObject("LastUpdateFromDateLabel",By.xpath("//span[@id='input-filter-from-date|hint']"));
		addObject("LastUpdateToDateLabel",By.xpath("//span[@id='input-filter-to-date|hint']"));
		addObject("SearchLimitLabel",By.xpath("//span[@id='input-filter-search-limit|hint']"));
		addObject("UinLookupItemTextBox",By.xpath("//input[@id='input-filter-item|input']"));
		addObject("UinTextBox",By.xpath("//input[@id='input-filter-uin|input']"));
		addObject("StatusDropDown",By.xpath("//oj-select-one[@id='input-filter-status']"));
		addObject("UserTextBox",By.xpath("//input[@id='input-filter-user|input']"));
		addObject("LastUpdateFromDateDatePicker",By.xpath("//input[@id='input-filter-from-date|input']"));
		addObject("LastUpdateFromDateDatePickerInput",By.xpath("//input[@id='input-filter-from-date|input']"));
		addObject("LastUpdateToDateDatePicker",By.xpath("//input[@id='input-filter-to-date|input']"));
		addObject("LastUpdateToDateDatePickerInput",By.xpath("//input[@id='input-filter-to-date|input']"));
		addObject("SearchLimitTextBox",By.xpath("//oj-input-number[@id='input-filter-search-limit']"));
		addObject("SearchButton",By.xpath("//oj-button[@id='search-button']"));
		addObject("ResetButton",By.xpath("//oj-button[@id='reset-button']"));
		addObject("ItemUinListTitle",By.xpath("//h3[text()='Item UIN List']"));
		addObject("StoreColumnName",By.xpath("//th[contains(@id,'grid-header-storeId')]//div[text()='Store']"));
		addObject("StoreColumnData",By.xpath("//td[contains(@id,'-col-storeId')]"));
		addObject("BackButton",By.xpath("//oj-button[@id='back-button']"));
		addObject("LastUpdateColumnName",By.xpath("//div[contains(text(),'Last Update')]"));
		addObject("LastUpdateColumnData",By.xpath("//td[contains(@id,'-col-updateDate')]"));
		addObject("FocusedCell",By.xpath("//tr//*[contains(@class,'cell-focused')]"));
		addObject("RefreshButton",By.xpath("//oj-button[@id='refresh-button']"));
		addObject("OpenColumnName",By.xpath("//div[contains(text(),'Open')]"));
		addObject("UserColumnName",By.xpath("//div[contains(text(),'User')]"));
		addObject("UinHistoryTitle",By.xpath("//h3[text()='UIN History']"));
		addObject("FilterStore",By.xpath("//input[contains(@id,'input-column-filter-storeId')]"));
		addObject("FilterUin",By.xpath("//input[contains(@id,'input-column-filter-uin')]"));
		addObject("FirstUinLink",By.xpath("//tr[1]//a[contains(@id,'-col-uin-link')]"));
		addObject("CurrentStore",By.xpath("//h1[text()='Store Inventory Operations Cloud Service']/following::a[@class='jraf-global-header-application-context']"));
		//-------------------Item UIN list UI---------------------------------------------
		addObject("ItemLabel",By.xpath("//span[@id='input-item|hint']"));
		addObject("ItemTextBox",By.xpath("//input[@id='input-item|input']"));
		addObject("ItemDescriptionLabel",By.xpath("//span[@id='input-item-description|hint']"));
		addObject("ItemDescriptionTextBox",By.xpath("//input[@id='input-item-description|input']"));
		addObject("UinTypeLabel",By.xpath("//span[@id='input-uin-type|hint']"));
		addObject("UinTypeTextBox",By.xpath("//input[@id='input-uin-type|input']"));
		addObject("SohLabel",By.xpath("//span[@id='input-soh|hint']"));
		addObject("SohTextBox",By.xpath("//input[@id='input-soh|input']"));
		addObject("GridViewMenu",By.xpath("//oj-menu-button[@title='Grid View Menu']"));
		addObject("UinColumnName",By.xpath("//div[text()='UIN']"));
		addObject("StatusColumnName",By.xpath("//div[text()='Status']"));
		addObject("TransactionIdColumnName",By.xpath("//div[text()='Transaction ID']"));
		addObject("FunctionalAreaColumnName",By.xpath("//div[text()='Functional Area']"));		
		addObject("FilterLastUpdate",By.xpath("//input[contains(@id,'input-column-filter-updateDate')]"));
		addObject("FilterOpen",By.xpath("//input[contains(@id,'input-column-filter-open')]"));

		//------------------------UIN History Ui-----------------------------------------------
		addObject("SaveButton",By.xpath("//oj-button[@id='save-button']"));
		addObject("UinHistoryStatusLabel",By.xpath("//span[@id='input-uin-status|hint']"));
		addObject("SerialNumberLabel",By.xpath("//span[text()='Serial Number']"));
		addObject("SerialNumberTextBox",By.xpath("//input[@id='input-uin|input']"));
		addObject("UinHistoryStatusDropDown",By.xpath("//oj-select-one[@id='input-uin-status']"));
		addObject("DateColumnName",By.xpath("//div[text()='Date']"));
		addObject("ResetView",By.xpath("//a[text()='Reset View']"));
		addObject("ColumnFilter",By.xpath("//a[text()='Column Filter']"));
		addObject("RowSelector",By.xpath("//a[text()='Row Selector']"));
		addObject("ExportToCsv",By.xpath("//a[text()='Export to CSV']"));
		addObject("FilterDate",By.xpath("//input[contains(@id,'input-column-filter-updateDate')]"));
		addObject("FilterStatus",By.xpath("//input[contains(@id,'input-column-filter-status')]"));
		addObject("FilterFunctionalArea",By.xpath("//input[contains(@id,'input-column-filter-functionalArea')]"));
		addObject("FilterTransactionId",By.xpath("//input[contains(@id,'input-column-filter-transactionId')]"));
		addObject("FilterUser",By.xpath("//input[contains(@id,'input-column-filter-updateUser')]"));
		addObject("ToDateErrorMessage",By.xpath("//oj-input-date[@id='input-filter-to-date']//span[@class='oj-message-content']"));
	}
}
