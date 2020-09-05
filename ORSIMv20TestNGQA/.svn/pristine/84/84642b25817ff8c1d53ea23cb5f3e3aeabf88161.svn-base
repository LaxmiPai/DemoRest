package com.oracle.sim.pages.Security;

import org.openqa.selenium.By;

import com.oracle.sim.pages.Base.SimBasePage;

public class UserAssignmentPage extends SimBasePage{
 public UserAssignmentPage() throws Exception{
 super();

 	//Navigation
 	addObject("MainMenu",By.xpath("//div[@class='jraf-menu-pin']/a[@class='jraf-menu-pin-link']"));
 	addObject("SecurityMainMenu",By.xpath("//span[contains(@class, 'oj-navigationlist-item-title') and text()='Security']"));
 	addObject("UserassignmentMainMenu",By.xpath("//span[contains(@class, 'oj-navigationlist-item-title') and text()='User Assignment']"));
 	addObject("Title",By.xpath("//h3[@id='sim-screen-title']"));
 	addObject("UserNameFilter",By.xpath("//input[(contains(@id,'input-column-filter-userName'))]"));
 	addObject("FirstTableRecord",By.xpath("//a[contains(@id,'-col-userName-link')]"));
	
 	//Role Update
 	addObject("RoleButton",By.xpath("//span[@class='oj-tabbar-item-label'][contains(text(),'Roles')]"));	
	addObject("CreateNewRoleButton",By.xpath("//div[@class='oj-flex-item oj-sm-flex-initial']//oj-button[@id='roles-create-button']"));
	addObject("CreateRoleHeading",By.xpath("//h1[@class='oj-dialog-title'][contains(text(), 'New Role')]"));
	addObject("NewRoleAssignmentHeader",By.xpath("//oj-dialog[@id='sim-user-preferences-dialog']"));
	addObject("ScopeSelect",By.xpath("//oj-option[text()='Select from available stores']//ancestor::span[@class='oj-choice-item oj-enabled']//span[@class='oj-radiocheckbox-icon']"));
	addObject("StoreCheckBox",By.xpath("//oj-lux-grid[@id='sim-screen-role-stores-grid-comp']//tr[1]//td[contains(@id,'-col-row-selector')]//input"));
	addObject("RoleSelect",By.xpath("//oj-lux-grid[@id='sim-screen-role-roles-grid-comp']//tr[1]//td[contains(@id,'-col-row-selector')]//input"));
	addObject("UpdateExistingSwitch", By.xpath("//oj-switch[@id='role-input-update-existing']/following-sibling::span"));
	addObject("ApplyButton",By.xpath("//oj-button[@id='role-apply-button']"));
	addObject("GridMenuRole", By.xpath("//oj-toolbar[@id='sim-screen-roles-grid-toolbar']//oj-menu-button[@title='Grid View Menu']"));
	addObject("FirstRecordRole",By.xpath("//td[contains(@class,'cell-renderer-selector-checkbox')]//input"));
	addObject("DeleteRole",By.xpath("//oj-button[@id='roles-delete-button']"));
	addObject("BackButton",By.xpath("//oj-button[@id='back-button']"));
	
	//Revoke - Assign Store
	addObject("StoresButton", By.xpath("//span[@class='oj-tabbar-item-label'][contains(text(),'Stores')]"));
	addObject("RevokeError", By.xpath("//oj-dialog[@id='sim-main-message-dialog']//span[contains(text(), 'No records')]"));
	addObject("AssignError", By.xpath("//oj-dialog[@id='sim-main-message-dialog']//span[contains(text(), 'No records')]"));
	addObject("ErrorOK",By.xpath("//oj-dialog[@id='sim-main-message-dialog']//span[text()='OK']"));
	addObject("GridMenu", By.xpath("//oj-toolbar[@id='sim-screen-stores-grid-toolbar']//oj-menu-button[@title='Grid View Menu']"));
	addObject("RowSelector",By.xpath("//div[contains(@class,'oj-menu-layer oj-focus-within')]//oj-option[3]"));
	addObject("FirstRecord",By.xpath("//tr[1]//td[1]//div[1]//div[1]//input[1]"));
	addObject("SecondRecord",By.xpath("//tr[2]//td[1]//div[1]//div[1]//input[1]"));
	addObject("AssignSelect", By.xpath("//span[@class='oj-button-text'][contains(text(),'Assign')]"));
	addObject("RevokeSelect", By.xpath("//span[@class='oj-button-text'][contains(text(),'Revoke')]"));
	addObject("StoresTable", By.xpath("//div[@class='scrolly-grid vertical-lined-grid']//div[@class='grid-rowset rowset-flex rowset-rowset']//table[@class='pgbu-grid']"));
	addObject("FilterBox", By.xpath("//oj-lux-grid[@id='sim-screen-stores-grid-comp']//th[contains(@class,'pgbu-cell undefined')]//input[contains(@id,'input-column-filter-description')]"));		
	addObject("SelectButton", By.xpath("//tr[contains(@id, '-group-all')]"));
	addObject("RevokePermission", By.xpath("//span[@class='oj-button-text'][contains (text(), 'Revoke')]"));	
	addObject("AssignPermission", By.xpath("//span[@class='oj-button-text'][contains (text(), 'Assign')]"));
	addObject("SaveButton", By.xpath(" //oj-button[@id='save-button']"));
	addObject("MessageConfirm", By.xpath("//oj-dialog[@id='sim-main-message-dialog']//span[text()='Yes']"));
	addObject("DetailUserScreenTitle",By.xpath("//h3[@id='sim-screen-title']"));
 }
}


