package com.oracle.sim.pages.DataSetup;
/**
 * @author lapai
 *
 * 
 */

import org.openqa.selenium.By;

import com.oracle.sim.pages.Base.SimBasePage;

public class CodeInfoPage extends SimBasePage {
	public CodeInfoPage() throws Exception {
		super();
		addObject("RefreshButton", By.xpath("//oj-button[@id='refresh-button']"));
		addObject("Title", By.xpath("//h3[@id='sim-screen-title']"));
		addObject("AddButton", By.id("add-button"));
		addObject("DeleteButton", By.id("delete-button"));
		addObject("CodeTypeTextBox", By.xpath("//div[@class='oj-select-choice']"));
		addObject("CodeTextBox", By.xpath("//input[@id='input-code|input']"));
		addObject("DescriptionTextBox", By.xpath("//textarea[@id='input-description|input']"));
		addObject("SequenceTextBox", By.xpath("//input[@id='input-sequence|input']"));
		addObject("System", By.xpath("//oj-switch[@id='input-preshipment']//div[@class='oj-switch-track']"));
		addObject("ApplyButton", By.id("detail-apply-button"));
		addObject("SaveButton", By.xpath("//oj-button[@id='save-button']//div[contains(@class,'oj-button-label')]"));
        addObject("No", By.xpath("/html[1]/body[1]/div[1]/div[2]/oj-dialog[1]/div[3]/div[1]/oj-button[2]/button[1]/div[1]/span[1]"));
        addObject("DeleteConfirmationMessage",By.xpath("//span[contains(text(),'The selected records will be deleted. Do you want')]"));
        addObject("YesButton",By.xpath("//oj-dialog[@id='sim-main-message-dialog']//span[text()='Yes']"));
		addObject("NoButton",By.xpath("//oj-dialog[@id='sim-main-message-dialog']//span[text()='No']"));
		
		//Grid objects
	
		addObject("FilterName", By.xpath("//div[text()='Code']/following::input[2]"));
		addObject("GridRecord", By.xpath("//mark[contains(@class,'grid-highlight')]"));
		addObject("System", By.xpath("//div[text()='System']/following::input[5]"));
		addObject("Desc", By.xpath("//div[text()='Description']/following::input[3]"));
		addObject("GridRecordCodeType",By.xpath("//tr[1]/td[1]/div[1]/div[1]"));
		addObject("GridRecordCodeType2ndRow",By.xpath("//tr[2]/td[1]/div[1]/div"));
		addObject("GridCode",By.xpath("//tr[1]/td[2]/div[1]/div[1]"));
		addObject("FilterByCodeType", By.xpath("//div[text()='Code']/following::input[1]"));
		addObject("GridRecordCodeType3rdRow",By.xpath("//tr[3]/td[1]/div[1]/div"));
		addObject("GridRecordCode3rdRow",By.xpath("//tr[3]/td[2]/div[1]/div"));
		addObject("GridRecordDescription3rdRow",By.xpath("//tr[3]/td[3]/div[1]/div"));
	
		
		//Detail section objects
		addObject("Apply" , By.xpath("//oj-button[@id='detail-apply-button']//button[@class='oj-button-button oj-component-initnode']"));
		addObject("EditButton", By.xpath("//oj-button[@id='detail-edit-button']//div[contains(@class,'oj-button-label')]"));
		addObject("Edit", By.xpath("//oj-button[@id='detail-edit-button']//button[@class='oj-button-button oj-component-initnode']"));
		addObject("Code Type", By.xpath("//a[contains(@class,'oj-select-arrow')]"));
		addObject("Dropdown",By.xpath("//ul[@id='oj-listbox-results-input-code-type']//li//div[text()='Untranslated: Item Default Level']"));
		addObject("CodeDropdown", By.xpath("//div[contains(@id,'oj-select')]"));
	
		//addObject("CodeTextBox", By.className("oj-inputtext oj-form-control oj-component oj-text-field oj-complete oj-required oj-hover"));
		
		addObject("DescriptionTextBox", By.xpath("//textarea[@id='input-description|input']"));
		addObject("SequenceTextBox", By.xpath("//input[@id='input-sequence|input']"));
		
		//MenuBar Details
		addObject("MenuButton",By.xpath("//oj-toolbar[@id='sim-screen-header-toolbar']//oj-menu-button"));
		addObject("Row",By.xpath("//div[contains(@class,'cell-renderer')]"));
		addObject("NoRowsMatchLabel",By.xpath("//span[@class='rowset-message-text']"));
		addObject("RecordDeleted",By.className("rowset-message"));
		addObject("checkBox",By.xpath("//mark[@class='grid-highlight']/preceding::td//input[@type='checkbox']"));
		
		//pop-up details
		addObject("ErrorPopUp",By.xpath("//oj-dialog//div//span[@id='sim-main-message-dialog-title']"));
		
		addObject("NavigationSearchBar",By.xpath("//input[@class='oj-combobox-input']"));
		addObject("RoleNameLink",By.xpath("//a[contains(@class,'cell-descendant cell-link')]"));
		addObject("FilterByDataValue",By.xpath("//div[contains(text(),'Data Value')]//following:: input[3]"));
		addObject("FilterByPermission",By.xpath("//div[contains(text(),'Permission')]//following:: input[2]"));
		addObject("Table",By.xpath("//table[@class='pgbu-grid']"));
		addObject("BackLink",By.xpath("//a[@class='jraf-hierarchy-back-link']"));
		
		addObject("AssignedCheckBox",By.xpath("//label[contains(@class,'sim-icon-font')]"));
		addObject("AssignedData",By.xpath("//td[contains(@id,'-col-assigned')]"));
		addObject("AssigneSelectedButton",By.xpath("//span[contains(@class, 'assign-icon oj-button')]"));
		addObject("RevokeSelectedButton",By.xpath("//span[contains(@class,'revoke-icon oj-button')]"));
		
		//Error Message for Accessing the Code Info Page
		addObject("ErrorMsgForAccess",By.xpath("//span[contains(text(),'User does not has access to perform the requested')]"));
		addObject("OkButton",By.xpath("//oj-button[contains(@class,'oj-button-primary oj-button oj-component oj-enab')]"));
		addObject("NoResultFound",By.xpath("//span[text() ='No results found.']"));
		addObject("RoleMaintenancePage",By.xpath("//span[text()='Role Maintenance']"));
		addObject("FilterByRoleName",By.xpath("//div[text()='Role Name']//following ::input[1]"));
		addObject("SaveIcon", By.id("save-button"));
		addObject("YesButton", By.xpath("//oj-button[contains(@class,'oj-button-confirm')]//div[contains(@class,'oj-button-label')]/span[text()='Yes']"));
		addObject("CancelButton", By.xpath("//div[@class='oj-dialog-footer']//oj-button[contains(@class,'oj-button oj-component')]//span[text()='Cancel']"));
		addObject("SaveText", By.xpath("//span[contains(text(),'Save')]"));
		//addObject("FilterButton",By.xpath("//body//oj-toolbar//span[3]"));
		//addObject("RowSelectorFilter",By.xpath("//span[contains(@class,'sim-icon-font sim-toolbar-selector-icon oj-menu-item-icon')]"));
		addObject("RowSelectorCheckBox2",By.xpath("//tbody/tr[2]/td[contains(@id,'-col-row-selector')]//input"));
		addObject("RowSelectorCheckBox1",By.xpath("//tbody/tr[1]/td[contains(@id,'-col-row-selector')]//input"));
		addObject("GridViewMenu",By.xpath("//oj-menu-button[@title='Grid View Menu']"));
		addObject("RowSelector",By.xpath("//a[text()='Row Selector']"));
		addObject("RowSelectorColumnCheckBox",By.xpath("//th[contains(@id,'grid-header-row-selector')]//input"));
		addObject("CodeError",By.xpath("//span[@class='oj-message-content']"));
		addObject("CodeErrorMessage",By.xpath("//oj-input-text//div[@class='oj-message oj-message-error']"));
		addObject("CodeTypeErrorMessage",By.xpath("//oj-select-one//span[@class='oj-message-content']"));
		addObject("DescriptionErrorMessage",By.xpath("//oj-text-area[@id='input-description']//span[@class='oj-message-content']"));
		addObject("SequenceErrorMessage",By.xpath("//oj-input-number[@id='input-sequence']//span[@class='oj-message-content']"));
		
		addObject("CancelButton2",By.xpath("//oj-button[contains(@class,'oj-button oj-component oj-button-text-only oj-complete')]//span[text()='Cancel']"));
		addObject("DescriptionGridRecord",By.xpath("//tr/td[3]"));
		addObject("SequenceGridRecord",By.xpath("//tr/td[4]"));
		addObject("ErrorMessage1",By.xpath("//span[contains(text(),'The selected records cannot be deleted since one o')]"));
		addObject("ErrorOKButton",By.xpath("//oj-button[contains(@class,'oj-button-default oj-button oj-component oj-enabled oj-button-full')]//span[text()='OK']"));
		addObject("ErrorMessage2",By.xpath("//span[contains(text(),'No records selected. Select one or more records to')]"));
		
	}
	
	}