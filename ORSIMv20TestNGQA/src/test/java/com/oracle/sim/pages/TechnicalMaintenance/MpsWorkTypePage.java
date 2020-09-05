package com.oracle.sim.pages.TechnicalMaintenance;

import org.openqa.selenium.By;

import com.oracle.sim.pages.Base.SimBasePage;

public class MpsWorkTypePage extends SimBasePage{
	public MpsWorkTypePage() throws Exception{
		super();
		//-----------------------table fields-------------------------------------
		addObject("Title",By.id("sim-screen-title"));
		addObject("WorkType",By.xpath("//div[contains(text(),'Work Type')]"));
		addObject("Direction",By.xpath("//div[contains(text(),'Direction')]"));
		addObject("Active",By.xpath("//div[contains(text(),'Active')]"));
		addObject("RetryLimit",By.xpath("//div[contains(text(),'Retry Limit')]"));
		addObject("PendingCount",By.xpath("//div[contains(text(),'Pending Count')]"));
		addObject("RetryCount",By.xpath("//div[contains(text(),'Retry Count')]"));
		addObject("FailCount",By.xpath("//div[contains(text(),'Fail Count')]"));
		addObject("LastUpdate",By.xpath("//div[contains(text(),'Last Update')]"));
		addObject("LastNew",By.xpath("//div[contains(text(),'Last New')]"));
		addObject("RetryDelaySecs",By.xpath("//div[contains(text(),'Retry Delay Secs')]"));
		addObject("RetryDelayMax",By.xpath("//div[contains(text(),'Retry Delay Max. Secs')]"));
		addObject("RetryDelayFactor",By.xpath("//div[contains(text(),'Retry Delay Factor')]"));
		addObject("RetryDelayRandom",By.xpath("//div[contains(text(),'Retry Delay Random')]"));
		addObject("PurgeProcessed",By.xpath("//div[contains(text(),'Purge Processed')]"));
		addObject("FilterWorkType",By.xpath("//input[contains(@id,'input-column-filter-messageFamily')]"));
		addObject("RetryLimitRecord",By.xpath("//td[contains(@id,'-col-retryLimit')]"));
		addObject("WorkTypeMsgFamily",By.xpath("//th[contains(@id,'grid-header-messageFamily')]"));
		addObject("FilterRetryLimit",By.xpath("//input[contains(@id,'input-column-filter-retryLimit')]"));
		addObject("GridRecordRetryLimit",By.xpath("//td[contains(@id,'-col-retryLimit')]//mark"));
		addObject("DirectionRecord",By.xpath("//td[contains(@id,'-col-inbound')]"));
		addObject("ActiveRecord",By.xpath("//td[contains(@id,'-col-enabled')]"));
		addObject("RetryDelaySecsRecord",By.xpath("//td[contains(@id,'-col-retryDelaySeconds')]"));
		addObject("RetryDelayMaxSecsRecord",By.xpath("//td[contains(@id,'-col-maxRetryDelaySeconds')]"));
		addObject("RetryDelayFactorRecord",By.xpath("//td[contains(@id,'-col-retryDelayFactor')]"));
		addObject("RetryDelayRandomRecord",By.xpath("//td[contains(@id,'-col-retryDelayRandom')]"));
		addObject("PurgeProcessedRecord",By.xpath("//td[contains(@id,'-col-purgeProcessed')]"));
		addObject("RefreshButton",By.xpath("//oj-button[@id='refresh-button']"));
		addObject("WorkTypeRecord",By.xpath("//td[contains(@id,'-col-messageFamily')]"));
		addObject("FocusedCell",By.xpath("//tr//*[contains(@class,'cell-focused')]"));
		addObject("RowFocused",By.xpath("//tr[contains(@class,'row-focused')]"));
		addObject("AllColumnHeading",By.xpath("//oj-lux-grid[@id='sim-screen-grid-comp']//thead//th[@role='columnheader']"));
		addObject("FirstTableRecord",By.xpath("//tbody//tr[1]//td[1]"));
		addObject("FocusedPendingCount",By.xpath("//tr[contains(@class,'row-focused')]//td[contains(@id,'-col-pendingCount')]"));
		addObject("FocusedRetryCount",By.xpath("//tr[contains(@class,'row-focused')]//td[contains(@id,'-col-retryCount')]"));
		addObject("FocusedFailCount",By.xpath("//tr[contains(@class,'row-focused')]//td[contains(@id,'-col-failedCount')]"));
		addObject("FocusedLastUpdate",By.xpath("//tr[contains(@class,'row-focused')]//td[contains(@id,'-col-lastUpdateDate')]"));
		addObject("FocusedLastNew",By.xpath("//tr[contains(@class,'row-focused')]//td[contains(@id,'-col-lastCreateDate')]"));

		//------------------------MPS Title bar-----------------------------------------------------
		addObject("SaveButton",By.xpath("//oj-button[@id='save-button']"));
		addObject("GridRecord",By.xpath("//mark[contains(@class,'grid-highlight')]"));
		addObject("EditButton",By.xpath("//oj-button[@id='detail-edit-button']//button[contains(@class,'oj-button-button oj-component-initnode')]"));
		addObject("RetryLimitTextbox",By.xpath("//input[@id='input-retry-limit|input']"));
		addObject("ApplyButton",By.xpath("//oj-button[@id='detail-apply-button']"));
		addObject("RefreshDialogboxText",By.xpath("//span[contains(text(),'If you continue, your changes will not be saved. D')]"));
		addObject("RefreshCancelButton",By.xpath("//oj-dialog[@id='sim-main-message-dialog']//span[text()='Cancel']"));
		addObject("OkButton",By.xpath("//span[text()='OK']"));
		addObject("NoRowsMsg",By.xpath("//span[contains(@class,'rowset-message-text')]"));
		addObject("FilterDirection",By.xpath("//input[contains(@id,'input-column-filter-inbound')]"));
		addObject("FilterActive",By.xpath("//input[contains(@id,'input-column-filter-enabled')]"));
		addObject("FilterPendingCount",By.xpath("//input[contains(@id,'input-column-filter-pendingCount')]"));
		addObject("FilterRetryCount",By.xpath("//input[contains(@id,'input-column-filter-retryCount')]"));
		addObject("FilterFailCount",By.xpath("//input[contains(@id,'input-column-filter-failedCount')]"));
		addObject("FilterLastUpdate",By.xpath("//input[contains(@id,'input-column-filter-lastUpdateDate')]"));
		addObject("FilterLastNew",By.xpath("//input[contains(@id,'input-column-filter-lastCreateDate')]"));
		addObject("FilterRetryDelaySecs",By.xpath("//input[contains(@id,'input-column-filter-retryDelaySeconds')]"));
		addObject("FilterRetryDelayMaxSecs",By.xpath("//input[contains(@id,'input-column-filter-maxRetryDelaySeconds')]"));
		addObject("FilterRetryDelayFactor",By.xpath("//input[contains(@id,'input-column-filter-retryDelayFactor')]"));
		addObject("FilterRetryDelayRandom",By.xpath("//input[contains(@id,'input-column-filter-retryDelayRandom')]"));
		addObject("FilterPurgeProcessed",By.xpath("//input[contains(@id,'input-column-filter-purgeProcessed')]"));
		
		addObject("GridViewMenu",By.xpath("//oj-menu-button[contains(@title,'Grid View Menu')]"));
		addObject("RowSelector",By.xpath("//a[text()='Row Selector']"));
		addObject("RowSelectorCheckBox",By.xpath("//tbody//tr//td[contains(@id,'-col-row-selector')]"));
		addObject("SaveConfirmationText",By.xpath("//span[contains(text(),'Are you sure you want to save the changes?')]"));
		addObject("YesButton",By.xpath("//oj-dialog[@id='sim-main-message-dialog']//span[text()='Yes']"));
		addObject("NoButton",By.xpath("//oj-dialog[@id='sim-main-message-dialog']//span[text()='No']"));
		//--------------------------MPS WorkType Cancel--------------------------------------------
		addObject("CancelButton",By.xpath("//oj-button[@id='detail-cancel-button']"));
		//--------------------------MPS WorkType Apply--------------------------------------------
		addObject("SaveConfirmationText",By.xpath("//span[contains(text(),'Are you sure you want to save the changes?')]"));
		addObject("YesButton",By.xpath("//oj-dialog[@id='sim-main-message-dialog']//span[text()='Yes']"));
		addObject("RetryDelaySecsTextbox",By.xpath("//input[@id='input-retry-delay-seconds|input']"));
		addObject("RetryDelayMaxSecsTextbox",By.xpath("//input[@id='input-max-retry-delay-seconds|input']"));
		addObject("RetryDelayFactorTextbox",By.xpath("//input[@id='input-retry-delay-factor|input']"));
		addObject("RetryDelayRandomTextbox",By.xpath("//input[@id='input-retry-delay-random|input']"));
		addObject("ApplyErrorMsgRetryDelaySecs",By.xpath("//oj-input-number[@id='input-retry-delay-seconds']//span[contains(@class,'oj-message-content')]"));
		addObject("ApplyErrorMsgRetryDelayMaxSecs",By.xpath("//oj-input-number[@id='input-max-retry-delay-seconds']//span[contains(@class,'oj-message-content')]"));
		addObject("ApplyErrorMsgRetryDelayFactor",By.xpath("//oj-input-number[@id='input-retry-delay-factor']//span[contains(@class,'oj-message-content')]"));
		addObject("ApplyErrorMsgRetryDelayRandom",By.xpath("//oj-input-number[@id='input-retry-delay-random']//span[contains(@class,'oj-message-content')]"));
		//-----------------MPSWORKTYPE DETAIL EDIT---------------
		addObject("MsgDialog",By.xpath("//span[contains(text(),'Permission denied: mps.work.type.admin.access.')]"));
		addObject("OkButton",By.xpath("//oj-dialog[@id='sim-main-message-dialog']//span[text()='OK']"));
		addObject("BackLink",By.xpath("//a[contains(@class,'jraf-hierarchy-back-link')]"));
		addObject("ColumnFilter",By.xpath("//a[text()='Column Filter']"));
	}
}
