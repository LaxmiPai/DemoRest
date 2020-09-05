package com.oracle.sim.pages.InventoryManagement;

import org.openqa.selenium.By;

import com.oracle.sim.pages.Base.SimBasePage;

public class TransactionHistoryPage extends SimBasePage{
	public TransactionHistoryPage() throws Exception {
		super();
		//------------------------Transaction History Search Criteria Navigation and UI----------------
		addObject("Title",By.xpath("//h3[text()='Transaction History']"));		
		addObject("SearchCriteriaDialogTitle",By.xpath("//h1[text()='Transaction History Search Criteria']"));
		addObject("TypeDropDown",By.xpath("//oj-select-one[@id='input-filter-tran-type']"));
		addObject("ReasonDropDown",By.xpath("//oj-select-one[@id='input-filter-reason']"));
		addObject("ItemTextBox",By.xpath("//oj-input-text[@id='input-filter-item']"));
		addObject("UserTextBox",By.xpath("//oj-input-text[@id='input-filter-user']"));
		addObject("FromDate",By.xpath("//oj-input-date[@id='input-filter-from-date']"));
		addObject("ToDate",By.xpath("//oj-input-date[@id='input-filter-to-date']"));
		addObject("SearchLimit",By.xpath("//oj-input-number[@id='input-filter-search-limit']"));
		addObject("SearchButton",By.xpath("//oj-button[@id='filter-search-button']"));
		addObject("ResetButton",By.xpath("//oj-button[@id='filter-reset-button']"));
		addObject("CancelButton",By.xpath("//oj-button[@id='filter-cancel-button']"));
		addObject("CloseButton",By.xpath("//oj-button[contains(@class,'oj-dialog-header-close-wrapper')]"));
		//------------------------------Transaction History Record Validation---------------------------------
		addObject("Date",By.xpath("//div[contains(text(),'Date')]"));
		addObject("Type",By.xpath("//div[contains(text(),'Type')]"));
		addObject("TransactionId",By.xpath("//div[contains(text(),'Transaction ID')]"));
		addObject("Item",By.xpath("//div[contains(text(),'Item')]"));
		addObject("Description",By.xpath("//div[contains(text(),'Description')]"));
		addObject("Reason",By.xpath("//div[contains(text(),'Reason')]"));
		addObject("SOH",By.xpath("//div[contains(text(),'SOH')]"));
		addObject("Unavailable",By.xpath("//div[contains(text(),'Unavailable')]"));
		addObject("User",By.xpath("//div[contains(text(),'User')]"));
		addObject("FromDateTextBox",By.xpath("//span[text()='From Date']//ancestor::div[@class='oj-flex']//input"));
		addObject("DateRecord",By.xpath("//td[contains(@id,'-col-timestamp')]"));
		addObject("RefreshButton",By.xpath("//oj-button[@id='refresh-button']"));
		addObject("FocusedCell",By.xpath("//tr//*[contains(@class,'cell-focused')]"));
		addObject("AllColumnHeading",By.xpath("//oj-lux-grid[@id='sim-screen-grid-comp']//thead//th[@role='columnheader']"));
		//--------------------------------Transaction History List Navigation and UI---------------------
		addObject("SearchToolBarText",By.xpath("//oj-toolbar[@id='sim-screen-search-filter-toolbar']/span[contains(text(),'From Date') and contains(text(),'Search Limit')]"));
		addObject("SearchIcon",By.xpath("//oj-button[@id='search-button']"));
		//--------------------------------Transaction History List Navigation to actual transaction-----------
		addObject("FilterType",By.xpath("//input[contains(@id,'input-column-filter-historyType')]"));
		addObject("FilterTransactionId",By.xpath("//input[contains(@id,'input-column-filter-transactionId')]"));
		addObject("NoTransactionHistoryDialogBoxText",By.xpath("//span[contains(text(),'The transaction history record does not have an or')]"));
		addObject("GridRecordTransactionId",By.xpath("//td[contains(@id,'-col-transactionId')]//mark[@class='grid-highlight']"));
		addObject("GridRecord",By.xpath("//mark[@class='grid-highlight']"));
		addObject("OkButton",By.xpath("//oj-dialog[@id='sim-main-message-dialog']//span[text()='OK']"));
		addObject("StockCountAuthorizationDetailTitle",By.xpath("//h3[text()='Stock Count Authorization Detail']"));
		addObject("BackButton",By.xpath("//oj-button[@id='back-button']"));
		addObject("FilterDate",By.xpath("//input[contains(@id,'input-column-filter-timestamp')]"));
		addObject("GridViewMenu",By.xpath("//oj-menu-button[@title='Grid View Menu']"));
		addObject("ResetView",By.xpath("//a[text()='Reset View']"));
		addObject("ColumnFilter",By.xpath("//a[text()='Column Filter']"));
		addObject("RowSelector",By.xpath("//a[text()='Row Selector']"));
		addObject("ExportToCsv",By.xpath("//a[text()='Export to CSV']"));
		//---------------------Transaction History Search.Search using From and To dates ----------------------
		addObject("ToDateTextBox",By.xpath("//span[text()='To Date']//ancestor::div[@class='oj-flex']//input"));
		addObject("ToDateErrorMessage",By.xpath("//oj-input-date[@id='input-filter-to-date']//span[@class='oj-message-content']"));
		//-----------------------Column name--------------------------------------------------------
		addObject("DateColumnName",By.xpath("//div[contains(@class,'cell-align-center')][contains(text(),'Date')]"));
		addObject("TypeColumnName",By.xpath("//div[contains(@class,'cell-align-center')][contains(text(),'Type')]"));
		addObject("TransactionIdColumnName",By.xpath("//div[contains(@class,'cell-align-center')][contains(text(),'Transaction ID')]"));
		addObject("ItemColumnName",By.xpath("//div[contains(@class,'cell-align-center')][contains(text(),'Item')]"));
		addObject("DescriptionColumnName",By.xpath("//div[contains(@class,'cell-align-center')][contains(text(),'Description')]"));
		addObject("ReasonColumnName",By.xpath("//div[contains(@class,'cell-align-center')][contains(text(),'Reason')]"));
		addObject("SohColumnName",By.xpath("//div[contains(@class,'cell-align-center')][contains(text(),'SOH')]"));
		addObject("UnavailableColumnName",By.xpath("//div[contains(@class,'cell-align-center')][contains(text(),'Unavailable')]"));
		addObject("UserColumnName",By.xpath("//div[contains(@class,'cell-align-center')][contains(text(),'User')]"));	
		addObject("TransactionIdFirstRecord",By.xpath("//tbody//tr[1]//td[contains(@id,'-col-transactionId')]/div/div/a"));
		addObject("TransactionIdRecord",By.xpath("//td[contains(@id,'-col-transactionId')]"));
		addObject("PurgedTransactionId",By.xpath("//td[contains(@id,'-col-transactionId')]/div/div/a"));
	}
}
