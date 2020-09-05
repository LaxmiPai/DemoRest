package com.oracle.sim.pages.TechnicalMaintenance;

/**
 * @author vidhsank
 *
 * 
 */

import org.openqa.selenium.By;

import com.oracle.sim.pages.Base.SimBasePage;

public class MpsStagedMessagePage extends SimBasePage{
	public MpsStagedMessagePage() throws Exception {
		super();
		//----------------MPS Staged Messages.Message Dialog----------------------------------
		addObject("Title",By.xpath("//h3[text()='MPS Staged Messages']"));
		addObject("RecordIdColumnData",By.xpath("//td[contains(@id,'-col-id')]"));
		addObject("RecordId",By.xpath("//div[text()='Record ID']"));
		addObject("SearchIcon",By.xpath("//oj-button[@id='search-button']"));
		addObject("ShowPendingSwitchButton",By.xpath("//oj-switch[@id='input-filter-pending']"));
		addObject("SearchButton",By.xpath("//oj-button[@id='filter-search-button']"));
		addObject("FilterRecordId",By.xpath("//input[contains(@id,'input-column-filter-id')]"));
		addObject("FilterExecutionCount",By.xpath("//input[contains(@id,'input-column-filter-retryCount')]"));
		addObject("GridRecord",By.xpath("//mark[contains(@class,'grid-highlight')]"));
		addObject("ExecutionCountGridRecord",By.xpath("//td[contains(@id,'-col-retryCount')]//mark[contains(@class,'grid-highlight')]"));
		addObject("MessageDataTab",By.xpath("//span[text()='Message Data']"));
		addObject("ErrorMessageTab",By.xpath("//span[text()='Error Message']"));
		addObject("MessageDataTextArea",By.xpath("//textarea[@id='input-data|input']"));
		addObject("TabCancelButton",By.xpath("//oj-button[@id='detail-cancel-button']"));
		addObject("TabSaveButton",By.xpath("//oj-button[@id='detail-save-button']"));
		addObject("ConfirmationText",By.xpath("//span[contains(text(),'Are you sure you want to save the changes?')]"));
		addObject("YesButton",By.xpath("//oj-dialog[@id='sim-main-message-dialog']//span[text()='Yes']"));
		addObject("NoButton",By.xpath("//oj-dialog[@id='sim-main-message-dialog']//span[text()='No']"));
		addObject("InvalidMessage",By.xpath("//span[contains(text(),'Invalid message data.')]"));
		addObject("InvalidMessageOkButton",By.xpath("//oj-dialog[@id='sim-main-message-dialog']//span[text()='OK']"));
		//-----------------MPS Staged Messages.Delete-----------------------------------------
		addObject("DeleteSelectedButton",By.xpath("//oj-button[@id='delete-button']"));
		addObject("DeleteAlertText",By.xpath("//span[contains(text(),'No records selected. Select one or more records to')]"));
		addObject("OkButton",By.xpath("//oj-dialog[@id='sim-main-message-dialog']//span[text()='OK']"));
		addObject("GridViewMenu",By.xpath("//oj-menu-button[contains(@title,'Grid View Menu')]"));
		addObject("RowSelector",By.xpath("//a[text()='Row Selector']"));
		addObject("DeleteConfirmationMessage",By.xpath("//span[contains(text(),'The selected records will be deleted. Do you want')]"));
		//---------------------MPS Staged Messages.Retry-----------------------------------------
		addObject("RetryButton",By.xpath("//oj-button[@id='reset-button']"));
		addObject("RetryAlertText",By.xpath("//span[contains(text(),'You must have one or more rows selected.')]"));
		//--------------------------------Filter Search Criteria---------------------
		addObject("SearchCriteriaTitle",By.xpath("//h1[text()='MPS Staged Message Search Criteria']"));
		addObject("FamilyDropDown",By.xpath("//span[text()='Family']//ancestor::div[@class='oj-flex']//oj-select-one"));
		addObject("InOutDropDown",By.xpath("//span[text()='In/Out']//ancestor::div[@class='oj-flex']//oj-select-one"));
		addObject("SearchLimitTextBox",By.xpath("//span[text()='Search Limit']//ancestor::div[@class='oj-flex']//input"));
		addObject("ShowPendingButton",By.xpath("//label[text()='Show Pending']//ancestor::div[@class='oj-flex']//oj-switch"));
		addObject("ShowRetryButton",By.xpath("//label[text()='Show Retry']//ancestor::div[@class='oj-flex']//oj-switch"));
		addObject("SearchButton",By.id("filter-search-button"));
		addObject("ResetButton",By.id("filter-reset-button"));
		addObject("CancelButton",By.id("filter-cancel-button"));
		addObject("StatusText",By.xpath("//oj-toolbar[@id='sim-screen-toolbar']//span[@class='oj-toolbar-separator']//following-sibling::span"));
		addObject("ColumnFilter",By.xpath("//a[text()='Column Filter']"));
		addObject("FocusedCell",By.xpath("//tr//*[contains(@class,'cell-focused')]"));
		addObject("RefreshButton",By.xpath("//oj-button[@id='refresh-button']"));
		addObject("Description",By.xpath("//div[contains(text(),'Description')]"));
		addObject("FamilyDropDownValues",By.xpath("//ul[@id='oj-listbox-results-input-filter-families']//li"));
		addObject("ResetView",By.xpath("//a[text()='Reset View']"));
		addObject("ExportToCsv",By.xpath("//a[text()='Export to CSV']"));

		//----------------column names-------------------------------------------
		addObject("InOutColumnName",By.xpath("//div[text()='In/Out']"));
		addObject("TypeColumnName",By.xpath("//div[text()='Type']"));
		addObject("FamilyColumnName",By.xpath("//div[text()='Family']"));
		addObject("CreateTimeColumnName",By.xpath("//div[text()='Create Time']"));
		addObject("UpdateTimeColumnName",By.xpath("//div[text()='Update Time']"));
		addObject("ExecutionCountColumnName",By.xpath("//div[text()='Execution Count']"));
		addObject("BusinessIdColumnName",By.xpath("//div[text()='Business ID']"));
		addObject("StoreIdColumnName",By.xpath("//div[text()='Store ID']"));
		addObject("JobIdColumnName",By.xpath("//div[text()='Job ID']"));
		addObject("FirstTableRecord",By.xpath("//oj-lux-grid[@id='sim-screen-grid-comp']//tbody//tr[1]"));
		addObject("FirstCellRecordId",By.xpath("//oj-lux-grid[@id='sim-screen-grid-comp']//tbody//tr[1]//td[contains(@id,'-col-id')]"));
		addObject("NoRowsMatchText",By.xpath("//span[@class='rowset-message-text']"));
		addObject("FirstRowSelectorCheckBox",By.xpath("//oj-lux-grid[@id='sim-screen-grid-comp']//tbody//tr[1]//td[contains(@id,'-col-row-selector')]//input"));
		addObject("FirstCellExecutionCount",By.xpath("//oj-lux-grid[@id='sim-screen-grid-comp']//tbody//tr[1]//td[contains(@id,'-col-retryCount')]"));
		addObject("FamilyLabel",By.xpath("//span[text()='Family']"));
		addObject("InOutLabel",By.xpath("//span[text()='In/Out']"));
		addObject("SearchLimitLabel",By.xpath("//span[text()='Search Limit']"));
		addObject("ShowPendingLabel",By.xpath("//label[text()='Show Pending']"));
		addObject("ShowRetryLabel",By.xpath("//label[text()='Show Retry']"));
		addObject("ShowPendingButtonValue",By.xpath("//oj-switch[@id='input-filter-pending']//following-sibling::span"));
		addObject("ShowRetryButtonValue",By.xpath("//oj-switch[@id='input-filter-retry']//following-sibling::span"));
		addObject("SearchLimitValue",By.xpath("//oj-input-number[@id='input-filter-search-limit']"));
		addObject("SelectedTableFamilyRecord",By.xpath("//tr[contains(@class,'row-selected ')]//td[contains(@id,'-col-messageFamily')]"));
		addObject("FirstTableCellRecord",By.xpath("//oj-lux-grid[@id='sim-screen-grid-comp']//tbody//tr[1]//td[1]"));
	}
}
