package com.oracle.sim.pages.InventoryManagement;

import org.openqa.selenium.By;

import com.oracle.sim.pages.Base.SimBasePage;

public class TroubledTransactionPage extends SimBasePage{
	public TroubledTransactionPage() throws Exception {
		super();
		//----------------------Search criteria UI--------------------------------------
		addObject("TroubledTransactionListTitle",By.xpath("//h3[text()='Troubled Transactions List']"));
		addObject("SearchCriteriaTitle",By.xpath("//h1[text()='Troubled Transactions List Search Criteria']"));
		addObject("StoreLabelName",By.xpath("//span[text()='Store']"));
		addObject("ItemLabelName",By.xpath("//span[text()='Item']"));
		addObject("UinLabelName",By.xpath("//span[text()='UIN']"));
		addObject("StatusLabelName",By.xpath("//span[text()='Status']"));
		addObject("TransactionIdLabelName",By.xpath("//span[text()='Transaction ID']"));
		addObject("FromDateLabelName",By.xpath("//span[text()='From Date']"));
		addObject("ToDateLabelName",By.xpath("//span[text()='To Date']"));
		addObject("ShowResolvedLabelName",By.xpath("//span[text()='Show Resolved']"));
		addObject("SearchLimitLabelName",By.xpath("//span[text()='Search Limit']"));
		addObject("StoreDropDown",By.xpath("//oj-select-one[@id='filter-select-store']"));
		addObject("ItemTextBox",By.xpath("//input[@id='input-filter-item|input']"));
		addObject("UinTextBox",By.xpath("//input[@id='input-filter-uin|input']"));
		addObject("StatusDropDown",By.xpath("//oj-select-one[@id='filter-select-status']"));
		addObject("TransactionIdTextBox",By.xpath("//input[@id='input-filter-transaction-id|input']"));
		addObject("FromDateDatePicker",By.xpath("//input[@id='input-filter-from-date|input']"));
		addObject("ToDateDatePicker",By.xpath("//input[@id='input-filter-to-date|input']"));
		addObject("ShowResolvedDropDown",By.xpath("//oj-select-one[@id='filter-input-resolved']"));
		addObject("SearchLimitTextBox",By.xpath("//input[@id='input-filter-search-limit|input']"));
		addObject("SearchButton",By.xpath("//oj-button[@id='filter-search-button']"));
		addObject("ResetButton",By.xpath("//oj-button[@id='filter-reset-button']"));
		addObject("CancelButton",By.xpath("//oj-button[@id='filter-cancel-button']"));
		//-------------------------Troubled transaction screen UI-------------------------
		addObject("SearchIcon",By.xpath("//oj-button[@id='search-button']"));
		addObject("RefreshButton",By.xpath("//oj-button[@id='refresh-button']"));
		addObject("ViewHistoryButton",By.xpath("//oj-button[@id='view-history-button']"));
		addObject("ResolveButton",By.xpath("//oj-button[@id='resolve-button']"));
		addObject("UnResolveButton",By.xpath("//oj-button[@id='unresolve-button']/button//span[@slot='startIcon']"));
		addObject("GridViewMenu",By.xpath("//oj-menu-button[@title='Grid View Menu']"));
		addObject("ResetView",By.xpath("//a[text()='Reset View']"));
		addObject("ColumnFilter",By.xpath("//a[text()='Column Filter']"));
		addObject("RowSelector",By.xpath("//a[text()='Row Selector']"));
		addObject("ExportToCsv",By.xpath("//a[text()='Export to CSV']"));	
		//----------------table column name--------------------------------------------------
		addObject("ItemColumnName",By.xpath("//div[text()='Item']"));
		addObject("UinColumnName",By.xpath("//div[text()='UIN']"));
		addObject("LastUpdateColumnName",By.xpath("//div[text()='Last Update']"));
		addObject("CurrentStatusColumnName",By.xpath("//div[text()='Current Status']"));
		addObject("NewStatusColumnName",By.xpath("//div[text()='New Status']"));
		addObject("ActionColumnName",By.xpath("//div[text()='Action']"));
		addObject("TransactionIdColumnName",By.xpath("//div[text()='Transaction ID']"));
		addObject("QuantityColumnName",By.xpath("//div[text()='Quantity']"));
		addObject("ResolvedColumnName",By.xpath("//div[text()='Resolved']"));
		addObject("ViewHistoryMessage",By.xpath("//span[contains(text(),'You must select a record to view history.')]"));
		addObject("OkButton",By.xpath("//oj-dialog[@id='sim-main-message-dialog']//span[text()='OK']"));
		addObject("TableFirstRecordLink",By.xpath("//oj-lux-grid[@id='sim-screen-grid-comp']//tbody//tr[1]//td[1]//a"));
		addObject("TableFirstRow",By.xpath("//oj-lux-grid[@id='sim-screen-grid-comp']//tbody//tr[1]"));
		addObject("TableRows",By.xpath("//oj-lux-grid[@id='sim-screen-grid-comp']//tbody//tr"));
		addObject("FilterItem",By.xpath("//th[contains(@id,'grid-column-filter-itemId')]//input[contains(@id,'input-column-filter-itemId')]"));
		addObject("ItemDetailScreenTitle",By.xpath("//h3[contains(text(),'Item Detail')]"));
		addObject("FocusedCell",By.xpath("//tr//*[contains(@class,'cell-focused')]"));
		addObject("BackButton",By.xpath("//oj-button[@id='back-button']"));
		addObject("UinHistoryTitle",By.xpath("//h3[text()='UIN History']"));
		addObject("FilterResolve",By.xpath("//input[contains(@id,'input-column-filter-resolved')]"));
		addObject("ResolutionStatusMessage",By.xpath("//span[contains(text(),'The Resolution status of the selected records will')]"));
		addObject("YesButton",By.xpath("//oj-dialog[@id='sim-main-message-dialog']//span[text()='Yes']"));
		addObject("NoButton",By.xpath("//oj-dialog[@id='sim-main-message-dialog']//span[text()='No']"));
		addObject("RowSelectorCheckBox",By.xpath("//td[contains(@id,'-col-row-selector')]//input"));
		addObject("NoHistoryFoundMessage",By.xpath("//oj-dialog[@id='sim-main-message-dialog']//span[text()='No history found.']"));
		addObject("FocusedResolveRecord",By.xpath("//tbody//tr[contains(@class,'row-focused')]//td[contains(@id,'-col-resolved')]"));
		addObject("FocusedRowSelectorCheckBox",By.xpath("//tr[contains(@class,'row-focused')]//td[contains(@id,'-col-row-selector')]//input"));
		addObject("FocusedItemRecord",By.xpath("//tbody//tr[contains(@class,'row-focused')]//td[contains(@id,'-col-itemId')]"));
		addObject("FocusedUinRecord",By.xpath("//tbody//tr[contains(@class,'row-focused')]//td[contains(@id,'-col-uin')]"));
		addObject("FirstResolveRecord",By.xpath("//tbody//tr[1]//td[contains(@id,'-col-resolved')]"));
		addObject("FilterItem",By.xpath("//input[contains(@id,'input-column-filter-itemId')]"));
		addObject("FilterUin",By.xpath("//input[contains(@id,'input-column-filter-uin')]"));
		addObject("FilterResolve",By.xpath("//input[contains(@id,'input-column-filter-resolved')]"));
		addObject("FocusedLastUpdate",By.xpath("//tbody//tr[contains(@class,'row-focused')]//td[contains(@id,'-col-updateDate')]"));
		addObject("FilterLastUpdate",By.xpath("//input[contains(@id,'input-column-filter-updateDate')]"));
		addObject("GridRecord",By.xpath("//mark[@class='grid-highlight']"));
		addObject("RowSelectorColumnCheckBox",By.xpath("//th[contains(@id,'grid-header-row-selector')]//input"));
		addObject("GridResolveRecord",By.xpath("//td[contains(@id,'-col-resolved')]//mark[@class='grid-highlight']"));
		addObject("DisselectAllExceptFocused",By.xpath("//tr[not (contains(@class,'row-focused'))]//td[contains(@id,'-col-row-selector')]//input"));
		addObject("FocusedColumnCell",By.xpath("//tbody//tr//td[contains(@class,'cell-focused')]"));
		addObject("ResolveColumnRecord",By.xpath("//tbody//tr//td[contains(@id,'-col-resolved')]"));
		addObject("CurrentStore",By.xpath("//h1[text()='Store Inventory Operations Cloud Service']/following::a[@class='jraf-global-header-application-context']"));
		
	}

}
