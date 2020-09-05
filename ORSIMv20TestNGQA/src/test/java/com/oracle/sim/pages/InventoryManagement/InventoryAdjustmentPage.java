package com.oracle.sim.pages.InventoryManagement;
import org.openqa.selenium.By;

import com.oracle.sim.pageFactory.PageFactory;
import com.oracle.sim.pages.Base.SimBasePage;


public class InventoryAdjustmentPage extends SimBasePage{


	public InventoryAdjustmentPage() throws Exception {


		super();

		addObject("Title", By.xpath("//h3[@id='sim-screen-title' and text()='Inventory Adjustment List']"));
		addObject("RefreshButton", By.xpath("//span[text()='Refresh']"));
		addObject("HomeMenuu",By.xpath("//span[text()='Home']"));
		addObject("IMDetailTitle",By.xpath("//h3[@id='sim-screen-title' and text()='Inventory Adjustment Detail']"));

		addObject("CreateButton",By.id("create-button"));
		addObject("AddIcon",By.id("add-button"));
		addObject("ScanItemBox",By.xpath("//input[@id='input-item-value|input']"));
		addObject("ClickScanBox",By.xpath("//span[contains(@class,'sim-item-scan-icon')]"));
		addObject("AddUinDialog",By.id("add-uin-dialog-button"));
		addObject("UinAddButton",By.id("uin-add-button"));
		addObject("ReasonDropdown",By.xpath("//a[contains(@class,'oj-select-arrow')]"));
		addObject("UinValidateButton",By.id("uin-validate-button"));
		addObject("DeleteButton",By.xpath("//oj-button[@id='delete-button']//div[@class='oj-button-label']"));
		addObject("InfoIcon",By.xpath("//span[contains(@class,'sim-toolbar-info-icon')]"));

		addObject("ReasonSearchbar",By.xpath("//span[contains(@class,'oj-component-icon oj-clickable-icon-nocontext oj-listbox-search-icon')]"));
		addObject("ReasonSearchTextBox",By.xpath("//div[contains(@class,'message-text oj-')]"));

		addObject("UinApplyButton",By.id("uin-apply-button"));
		addObject("UinCancelButton",By.id("uin-apply-button"));
		addObject("ExtAttributeIcon",By.xpath("//span[contains(@class,'sim-ext-attr-icon')]"));
		addObject("ApplyButton",By.id("detail-apply-button"));
		addObject("CancelButton",By.id("detail-cancel-button"));
		addObject("SaveIcon",By.xpath("//oj-button[@id='save-button']"));
		addObject("UinInputBoxTable",By.xpath("//*[@class='row-new row-selected row-selected-top row-selected-bottom row-focused']/td[1]"));
		//addObject("UinInputBoxTable",By.xpath("//td[contains(@class,'pgbu-cell')]/following::input[1]"));
		addObject("UinInputBox",By.xpath("//input[@class='oj-inputtext-input']"));

		addObject("VerifyStatus",By.xpath("//div[contains(text(),'Unconfirmed')]"));
		addObject("YesButton", By.xpath("//span[text()='Yes']"));
		addObject("NoButton", By.xpath("//span[text()='No']"));
		addObject("AdjustmentId",By.xpath("//input[@id='header-input-adjustment-id|input']"));
		addObject("ExtendedAttributeIcon",By.xpath("//span[contains(@class, 'sim-ext-attr-icon')]"));
		addObject("UinDialog",By.xpath("//oj-dialog[@id='sim-screen-uin-dialog']"));

		addObject("ExtendedAttributeTitle",By.xpath("//h1[text()='Inventory Adjustment - Extended Attributes']"));

		addObject("AddExtendedAttributeButton",By.xpath("//oj-button[@on-click='[[addClick]]']"));
		addObject("PricePerUnitField",By.xpath("//input[contains(@id,'input-attr-code')]"));
		addObject("QuantityField",By.xpath("//input[contains(@id,'sim-uid-') and contains(@class,'oj-inputnumber-input')]"));
		addObject("AddtoListButton",By.xpath("//oj-button[@on-click='[[detailApplyClick]]']"));
		addObject("ExtendedApplyButton",By.xpath("//oj-button[@on-click='[[applyClick]]']"));
		addObject("ExtendedCancelButton",By.xpath("//oj-button[@on-click='[[cancelClick]]']"));

		addObject("ConfirmButton",By.xpath("//oj-button[@id='confirm-button']"));
		addObject("ConfirmationMsg",By.xpath("//span[contains(text(),'Are you sure you want to confirm this inventory ad')]"));
		addObject("QuantityInputField",By.xpath("//input[@id='input-quantity-value|input']"));
		// Notes Fields Menu...

		addObject("FilterByStatus",By.xpath("//div[text()='Status']//following ::input[3]"));
		addObject("FilterByUser",By.xpath("//div[text()='User']//following ::input[4]"));
		addObject("FilterByAdjustmentId",By.xpath("//div[text()='Adjustment ID']//following ::input[1]"));
		addObject("NotesIcon",By.xpath("//span[contains(@class,'sim-note-icon')]"));
		addObject("NotesInputTextField",By.xpath("//textarea[contains(@class,'oj-textarea-input')]"));
		addObject("PostIcon",By.xpath("//span[contains(@class,'sim-toolbar-post-icon')]"));
		addObject("CloseButton",By.xpath("//span[contains(@class,'oj-fwk-icon-cross oj-button-icon')]"));
		addObject("BackButton",By.xpath("//oj-button[@id='back-button']"));
		addObject("GridHighLight",By.xpath("//mark[contains(@class,'grid-highlight')]"));

		addObject("InventoryAdjustmentIdLink",By.xpath("//a[contains(@id,'col-id-link')]"));

		addObject("PackSizeField",By.xpath("//input[@id='input-case-size-value|input']"));

		addObject("SearchButton",By.xpath("//oj-button[@id='search-button']"));
		addObject("ReasonDropdown2",By.xpath("//a[contains(@class,'oj-select-arrow')]"));
		addObject("ReasonDropDownInput",By.xpath("//div[@id='oj-listbox-drop']//input"));
		addObject("SubbucketDropdown",By.xpath("//a[contains(@class,'oj-select-arrow')]//following::a[1]"));
		addObject("StatusDropdown",By.xpath("//a[contains(@class,'oj-select-arrow')]//following::a[2]"));
		addObject("SearchBoxHilighted",By.xpath("//span[@class='oj-listbox-highlighter']"));
		addObject("StatusLabel",By.xpath("//span[text()='Status']"));
		addObject("ReasonLabel",By.xpath("//span[text()='Reason']"));
		addObject("SubbucketLabel",By.xpath("//span[text()='Sub-bucket']"));
		addObject("UserLabel",By.xpath("//label[text()='User']"));
		addObject("SearchIcon",By.cssSelector("div.oj-dialog-footer oj-button.oj-button-primary.oj-button.oj-component.oj-enabled.oj-button-full-chrome.oj-button-text-icon-start.oj-complete.oj-default:nth-child(1) "));

		//Delete Buttons 
		addObject("DeleteButton",By.xpath("//span[contains(@class,'sim-toolbar-delete-icon')]"));
		addObject("DeleteConfirmationMsg",By.xpath("//span[contains(text(),'Are you sure you want to delete the inventory adju')]"));
		addObject("SearchCriteriaTitle",By.xpath("//h1[text()='Inventory Adjustment Search Criteria']"));

		//Navigation and Security Pages
		addObject("Navigation",By.xpath("//a[@class='jraf-menu-pin-link']"));
		addObject("BackLink",By.xpath("//a[@class='jraf-hierarchy-back-link']"));
		addObject("SecurityPage",By.xpath("//span[text()='Security']"));
		addObject("RoleMaintenancePage",By.xpath("//span[text()='Role Maintenance']"));
		addObject("FilterByRoleName",By.xpath("//div[text()='Role Name']//following ::input[1]"));
		addObject("RoleNameLink",By.xpath("//a[contains(@class,'cell-descendant cell-link')]"));
		addObject("FilterByDataValue",By.xpath("//div[contains(text(),'Data Value')]//following:: input[3]"));
		addObject("FilterByPermission",By.xpath("//div[contains(text(),'Permission')]//following:: input[2]"));
		addObject("FilterByTopic",By.xpath("//div[contains(text(),'Topic')]//following:: input[1]"));
		addObject("Table",By.xpath("//table[@class='pgbu-grid']"));

		addObject("AssignedCheckBox",By.xpath("//label[contains(@class,'sim-icon-font')]"));
		addObject("AssignedData",By.xpath("//td[contains(@id,'-col-assigned')]"));
		addObject("AssigneSelectedButton",By.xpath("//span[contains(@class, 'assign-icon oj-button')]"));
		addObject("RevokeSelectedButton",By.xpath("//span[contains(@class,'revoke-icon oj-button')]"));


		addObject("ErrorMsgForAccess",By.xpath("//span[contains(text(),'User does not has access to perform the requested')]"));
		addObject("OkButton",By.xpath("//span[text()='OK']"));

		addObject("NavigationSearchBar",By.xpath("//input[@class='oj-combobox-input']"));
		addObject("NoResultFound",By.xpath("//span[text() ='No results found.']"));

		addObject("InventoryAdjustmentPageMenu",By.xpath("//span[contains(text(),'Inventory Adjustment')]"));
		addObject("NorecordsMsg", By.xpath("//span[@class='rowset-message-text']"));

		addObject("NotesTitle",By.xpath("//h1[text()='Notes']"));
		addObject("FilterByDate",By.xpath("//input[contains(@id,'input-column-filter-date')]"));
		addObject("FilterByNotes",By.xpath("//input[contains(@id,'input-column-filter-text')]"));
		addObject("DateRecord",By.xpath("//td[contains(@id,'-col-date')]"));
		addObject("DateColumnName",By.xpath("//div[contains(text(),'Date')]"));
		addObject("InventoryAdjusmtentIDColumnData",By.xpath("//tr[contains(@id,'group-all')]"));
		addObject("TotalSKUsColumnName",By.xpath("//div[contains(text(),'Total SKUs')]"));
		addObject("FilterAdjustmentId",By.xpath("//input[contains(@id,'input-column-filter-id')]"));
		addObject("GridViewMenu",By.xpath("//oj-menu-button[@title='Grid View Menu']"));
		addObject("ResetView",By.xpath("//a[text()='Reset View']"));
		addObject("ColumnFilter",By.xpath("//a[text()='Column Filter']"));
		addObject("RowSelector",By.xpath("//a[text()='Row Selector']"));
		addObject("ExportToCsv",By.xpath("//a[text()='Export to CSV']"));
		addObject("FocusedCell",By.xpath("//tr//*[contains(@class,'cell-focused')]"));

		//From Date and To Date Objects
		addObject("FromDateTextbox",By.xpath("//span[text()='From Date']//ancestor::div[@class='oj-flex']//input"));
		addObject("UserTextBox",By.xpath("//label[text()='User']//ancestor::div[@class='oj-flex']//input"));


		//List screen
		addObject("InventoryAdjustmentListTitle",By.xpath("//h3[text()='Inventory Adjustment List']"));
		addObject("InventoryAdjustmentDetailTitle",By.xpath("//h3[text()='Inventory Adjustment Detail']"));

		//Detail screen
		addObject("AddButton",By.xpath("//oj-button[@id='add-button']"));

		//Detail panel
		addObject("DetailPanelTitle",By.xpath("//h3[text()='Detail']"));
		addObject("ScanItemTextBox",By.xpath("//input[@id='input-item-value|input']"));
		addObject("ScanButton",By.xpath("//oj-button[@id='item-scan-button']"));
		addObject("DetailReasonDropDown",By.xpath("//oj-select-one[@id='input-allowed-reason']"));
		addObject("ItemIdTextBox",By.xpath("//input[@id='input-item|input']"));
		addObject("QuantityTextBox",By.xpath("//input[@id='input-quantity-value|input']"));
		addObject("ErrorMessageDialogBox",By.xpath("//span[@class='oj-message-content']"));
		
		//UIN detail dialog
		addObject("UinButton",By.id("add-uin-dialog-button"));
		addObject("UinAddButton",By.xpath("//oj-button[@id='uin-add-button']"));
		addObject("FrtsUinRow",By.xpath("//td[contains(@class,'first-visible-cell ')]"));
		addObject("FirstUinInput",By.xpath("//div[contains(@id,'id-cell-editor')]//input[1]"));
		addObject("UinApplyButton",By.xpath("//oj-button[@id='uin-apply-button']"));
		
		addObject("ReasonSelect",By.xpath("//span[@id='ojChoiceId_input-allowed-reason_selected']"));
		addObject("DropDownFilter",By.xpath("//div[@id='oj-listbox-drop']//input[contains(@class,'oj-listbox-input')]"));
		//addObject("DropDownHighlightedValue",By.xpath("//span[@class='oj-listbox-highlighter']"));
		addObject("DropDownHighlightedValue",By.xpath("//span[@class='oj-highlighttext-highlighter']"));
	}
	
	//To perform inventory adjustment reason item and UIN are passed
		 public void performUinInventoryAdjustment(String reasonCode,String Uin,String Item) throws InterruptedException {
			 PageFactory pagefactory=new PageFactory();
			 pagefactory.initialize("InventoryAdjustmentPage");
			 //Click on the create button
			 click("AddIcon");
				
				//Input an item in to the scan box
				enterIntoTextBox("ScanItemBox", Item);
				click("ClickScanBox");
				
				//Select the reason from the Reason Drop down
				selectDropDownValue("DetailReasonDropDown", reasonCode);
				
				//String ObtainedUin=Integer.toString(Uin);
				click("UinButton");
				Thread.sleep(5000);
				click("UinAddButton");
				Thread.sleep(5000);
				doubleClick();
				enterIntoTextBox("FirstUinInput",Uin );
				click("UinApplyButton");
				Thread.sleep(5000);
				click("ApplyButton");
				click("ConfirmButton");
				verifyElementIsDisplayed("ConfirmationMsg");
				explicitWaitForElementToBeClickable("YesButton");
				click("YesButton");
	 }
}
