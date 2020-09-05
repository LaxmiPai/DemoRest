package com.oracle.sim.pages.InventoryManagement;

/**
 * @author vidhsank ,dsorthiy , lapai
 *
 * 
 */

import org.openqa.selenium.By;

import com.oracle.sim.pages.Base.SimBasePage;

public class ItemBasketPage extends SimBasePage{
	public ItemBasketPage() throws Exception {
		super();
		//Item basket list 
		addObject("ItemBasketListTitle",By.xpath("//h3[text()='Item Basket List']"));
		addObject("SearchIcon",By.xpath("//oj-button[@id='search-button']"));
		addObject("TableRows",By.xpath("//td[contains(@id,'-col-updateUser')]"));
		addObject("UserColumnName",By.xpath("//div[contains(text(),'User')]"));
		addObject("FilterItemBasketId",By.xpath("//input[contains(@id,'input-column-filter-id')]"));
		addObject("GridViewMenu",By.xpath("//oj-menu-button[@title='Grid View Menu']"));
		addObject("ColumnFilter",By.xpath("//a[text()='Column Filter']"));
		addObject("FocusedCell",By.xpath("//tr//*[contains(@class,'cell-focused')]"));
		addObject("RefreshButton",By.xpath("//oj-button[@id='refresh-button']"));
		addObject("ItemBasketIdColumnFirstCell",By.xpath("//tbody//tr[1]//td[contains(@id,'-col-id')]//a"));
		addObject("ListScreenCreateButton",By.xpath("//oj-button[@id='create-button']"));
		addObject("FilterDescription",By.xpath("//oj-lux-grid[@id='sim-screen-grid-comp']//input[contains(@id,'input-column-filter-description')]"));
		addObject("FilterType",By.xpath("//input[contains(@id,'input-column-filter-typeDescription')]"));
		addObject("TableFirstRow",By.xpath("//tbody//tr[1]"));
		addObject("NoRowsMsgText",By.xpath("//span[contains(@class,'rowset-message-text')]"));
		addObject("GridHighLightRecord",By.xpath("//mark[contains(@class,'grid-highlight')]"));
		addObject("FilterStatus",By.xpath("//input[contains(@id,'input-column-filter-status')]"));
		addObject("FilterUser",By.xpath("//input[contains(@id,'input-column-filter-updateUser')]"));
		addObject("ItemBasketIdLink",By.xpath("//a[contains(@id,'-col-id-link')]"));

		addObject("StatusListlabel",By.xpath("//span[contains(text(),'Status =')]"));
		addObject("ListResultsLabel",By.xpath("//span[contains(@class,'oj-sm-margin')]"));
		addObject("ItemBasketIDLabel",By.id("header-input-basket-id-labelled-by"));
		addObject("NotesLabel",By.xpath("//label[text()='Notes']"));
		addObject("StatusLabel",By.id("header-input-status-labelled-by|label"));
		addObject("TypeLabel",By.id("header-input-type-labelled-by"));
		addObject("DescriptionLabel",By.id("header-input-description|hint"));
		
		
		//Item basket search criteria
		addObject("SearchCriteriaTitle",By.xpath("//h1[text()='Item Basket Search Criteria']"));
		addObject("DepartmentDropDownButton",By.xpath("//oj-select-one[@id='input-filter-department']"));
		addObject("DepartmentDropDown",By.xpath("//div[contains(@id,'department')]//a[contains(@class,'oj-select-arrow oj-component')]"));
		addObject("DepartmentDropDownList",By.xpath("//ul[@id='oj-listbox-results-input-filter-department']//li"));
		addObject("SearchCriteriaCancelButton",By.xpath("//oj-dialog[@id='sim-screen-filter-dialog']//div[@class='oj-dialog-footer']//span[text()='Cancel']"));
		addObject("DropDownHighLightRecord",By.xpath("//span[@class='oj-highlighttext-highlighter']"));
		addObject("DropDownFilter",By.xpath("//div[@id='oj-listbox-drop']//input[contains(@class,'oj-listbox-input')]"));
		addObject("SearchButton",By.xpath("//div[@class='oj-dialog-footer']//span[text()='Search']"));
		addObject("SearchCriteriaClassDropDown",By.xpath("//div[contains(@id,'class')]//a[contains(@class,'oj-select-arrow oj-component')]"));
		addObject("ErrorMessageDialogBox",By.xpath("//span[contains(@class,'oj-message-content')]"));
		addObject("ItemBasketIdTextBox",By.xpath("//input[@id='input-filter-item-basket-id|input']"));
		addObject("ItemTextBox",By.xpath("//input[@id='input-filter-item|input']"));
		addObject("ItemTextBoxToolTipText",By.xpath("//span[text()='Enter 1 to 25 characters.']"));
		addObject("StatusDropDown",By.xpath("//oj-select-one[@id='input-filter-status']"));
		addObject("StatusDropDownList",By.xpath("//ul[@id='oj-listbox-results-input-filter-status']//li"));

		//Item Basket List Create-Screen menus 
		//addObject("TypeDropDown",By.xpath("//div[@id='oj-select-choice-input-type']"));
		addObject("TypeDropDown",By.xpath("//oj-select-single[@id='input-type']"));
		addObject("TypeDropDownArrow",By.xpath("//oj-input-text[@id='oj-searchselect-filter-input-type']//a"));
		//addObject("StoreDropDown",By.xpath("//div[@id='oj-select-choice-input-store']"));
		addObject("StoreDropDown",By.xpath("//oj-select-single[@id='input-store']"));
		//addObject("StoreDropDownDefault",By.xpath("//span[@id='ojChoiceId_input-store_selected']"));
		addObject("StoreDropDownDefault",By.xpath("//input[@id='input-store|input']"));
		addObject("DescriptionTextBox",By.xpath("//input[@id='input-description|input']"));
		addObject("AlternateIdTextBox",By.xpath("//input[@id='input-alternate-id|input']"));
		addObject("TypeTextLabel",By.xpath("//span[@id='input-type|hint']"));
		addObject("DescriptionTextLabel",By.xpath("//span[@id='input-description|hint']"));
		addObject("AlternateIdTextLabel",By.xpath("//span[@id='input-alternate-id|hint']"));
		addObject("ExpirationDateLabel",By.xpath("//span[@id='input-expiration-date|hint']"));
		addObject("StoreTextLabel",By.xpath("//span[@id='input-store|hint']"));
		
		//Create Button in Create Item basket 
		addObject("CreateButton",By.xpath("//oj-dialog[@id='sim-screen-create-dialog']//span[text()='Create']"));
		addObject("SaveConfirmationDialog",By.xpath("//span[contains(text(),'Are you sure you want to save the item basket?')]"));
		addObject("SaveYesbutton",By.xpath("//oj-dialog[@id='sim-main-message-dialog']//span[text()='Yes']"));
		addObject("DeleteConfirmationDialog",By.xpath("//span[@class='oj-dialog-title'and text()='Confirmation']"));
		addObject("ConfirmYesButton",By.xpath("//oj-dialog[@id='sim-main-message-dialog']//span[text()='Yes']"));
		addObject("ConfirmNoButton",By.xpath("//oj-dialog[@id='sim-main-message-dialog']//span[text()='No']"));
		addObject("CreateDialogTitle",By.xpath("//h1[text()='Create Item Basket']"));
		addObject("CreateCreateButton",By.xpath("//oj-dialog[@id='sim-screen-create-dialog']//span[text()='Create']"));
		
		//Item Basket Detail Screen Components
		addObject("AddComponentsButton",By.xpath("//span[@class='sim-icon-font sim-toolbar-add-icon oj-button-icon oj-start']"));
		addObject("DeleteComponentsButton",By.xpath("//span[@class='sim-icon-font sim-toolbar-remove-icon oj-button-icon oj-start']"));
		addObject("ComponentTypeDropDown",By.xpath("//div[@id='oj-select-choice-detail-input-component']"));
		addObject("ItemBasketDetailTitle",By.xpath("//h3[text()='Item Basket Detail']"));
		addObject("EditButton",By.xpath("//oj-button[@id='detail-edit-button']"));
		addObject("DepartmentColumnFirstCell",By.xpath("//tbody//tr[1]//td[contains(@id,'-col-department')]"));
		addObject("FilterDepartment",By.xpath("//input[contains(@id,'input-column-filter-department')]"));
		addObject("YesButton",By.xpath("//oj-dialog[@id='sim-main-message-dialog']//span[text()='Yes']"));
		addObject("NoButton",By.xpath("//oj-dialog[@id='sim-main-message-dialog']//span[text()='No']"));
		addObject("FilterItem",By.xpath("//oj-lux-grid[@id='sim-screen-grid-comp']//input[contains(@id,'input-column-filter-itemId')]"));
		addObject("BackButton",By.xpath("//oj-button[@id='back-button']"));
		addObject("DepartmentLable",By.xpath("//span[text()='Department']"));
		addObject("DepartmentGridRecord",By.xpath("//td[contains(@id,'-col-department')]"));
		addObject("OkButton",By.xpath("//oj-dialog[@id='sim-main-message-dialog']//span[text()='OK']"));
		addObject("CopyButton",By.xpath("//oj-button[@id='copy-button']//div[@class='oj-button-label']"));
		addObject("CopyConfirmMsg",By.xpath("//span[contains(text(),'Are you sure you want to copy this item basket? Th')]"));
		addObject("ItemIdLink",By.xpath("//a[contains(@id,'itemId-link')]"));       
		addObject("CopyStatus",By.xpath("//span[contains(@id,'header-input-status_selected')]"));
		addObject("ConfirmationMsg",By.xpath("//span[contains(text(),'Are you sure you want to confirm this item basket?')]"));
		addObject("DeleteConfirmationMsg",By.xpath("//span[contains(text(),'Are you sure you want to delete the item basket? T')]"));
		addObject("EditInfoButton",By.xpath("//oj-button[@id='edit-info-button']"));
		addObject("DescriptinEditInfoTextBox",By.xpath("//oj-input-text[@id='info-input-description']"));
		addObject("DescriptinEditTextBox",By.xpath("//input[@id='info-input-description|input']"));
		addObject("TypeEditInfoDropdownBox",By.xpath("//span[@id='info-input-type_selected']"));
		addObject("AlternateIdEditInfoTextBox",By.xpath("//oj-input-text[@id='info-input-alternate-id']"));
		addObject("EditInfoApplyButton",By.id("info-apply-button"));
		addObject("NoComponentsConfirmationMsg",By.xpath("//span[contains(text(),'No components exist on the item basket and the ite')]"));
		addObject("HierarchyExistsErrorMsgDialog",By.xpath("//span[contains(text(),'A hierarchy exists for this item on the item baske')]"));
		addObject("FilterQuantity",By.xpath("//input[contains(@id,'input-column-filter-quantity')]"));
		addObject("QuantityColumnGridRecord",By.xpath("//td[contains(@id,'-col-quantity')]"));		

		//Component Type : Item menus
		addObject("ItemScanBox",By.xpath("//input[@id='input-item-scan-value|input']"));
		addObject("ScanBoxArrow",By.xpath("//span[contains(@class,'sim-item-scan-icon')]"));
		addObject("PackSizeTextBox",By.xpath("//input[@id='input-case-size-value|input']"));
		addObject("QuantityTextBox",By.xpath("//input[@id='input-quantity-value|input']"));

		//Component Type : Supplier menus
		addObject("SupplierTextBox",By.xpath("//input[@id='input-detail-supplier|input']"));

		//Component Type : Style menus
		addObject("StyleTextBox",By.xpath("//input[@id='input-detail-style|input']"));

		//Item Basket Detail Screen Buttons
		addObject("ConfirmButton",By.xpath("//oj-button[@id='confirm-button']"));
		addObject("DeleteButton",By.xpath("//oj-button[@id='delete-button']//div[@class='oj-button-label']"));
		addObject("InfoButton",By.xpath("//oj-button[@id='info-button']//div[@class='oj-button-label']"));
		addObject("SaveButton",By.xpath("//oj-button[@id='save-button']"));
		addObject("EditInfoButton",By.xpath("//oj-button[@id='edit-info-button']"));

		//Item basket new Detail Block Buttons
		addObject("ApplyButton",By.id("detail-apply-button"));
		addObject("CancelButton",By.id("detail-cancel-button"));
		addObject("DetailPanelDepartmentDropDown",By.xpath("//div[@id='oj-select-choice-detail-input-department']"));
		addObject("DetailPanelClassDropdown",By.xpath("//span[@id='detail-input-class_selected']"));
		addObject("DetailPanelSubClassDropDown",By.xpath("//span[@id='detail-input-subclass_selected']"));
		addObject("DetailEditTitle",By.xpath("//h3[text()='Detail Edit']"));
		addObject("ItemBasketId",By.xpath("//input[@id='header-input-basket-id|input']"));

		//Item Basket Info Components
		addObject("ReferenceId",By.xpath("//input[@id='info-input-reference-id|input']"));
		addObject("CloseButton",By.xpath("//oj-dialog[@id='sim-screen-info-dialog']//span[@class='oj-fwk-icon oj-fwk-icon-cross oj-button-icon oj-start']"));

		//Item Basket Permission
		addObject("RoleNameLink",By.xpath("//a[contains(@class,'cell-descendant cell-link')]"));
		addObject("FilterByRoleName",By.xpath("//div[text()='Role Name']//following ::input[1]"));
		addObject("FilterByDataValue",By.xpath("//div[contains(text(),'Data Value')]//following:: input[3]"));
		addObject("FilterByPermission",By.xpath("//div[contains(text(),'Permission')]//following:: input[2]"));
		addObject("AssignedData",By.xpath("//td[contains(@id,'-col-assigned')]"));
		addObject("AssigneSelectedButton",By.xpath("//span[contains(@class, 'assign-icon oj-button')]"));
		addObject("RevokeSelectedButton",By.xpath("//span[contains(@class,'revoke-icon oj-button')]"));		
		addObject("BackLink",By.xpath("//a[@class='jraf-hierarchy-back-link']"));
		addObject("NavigationSearchBar",By.xpath("//input[@class='oj-combobox-input']"));


	}
}
