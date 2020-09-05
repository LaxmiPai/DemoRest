package com.oracle.sim.pages.DataSetup;

import org.openqa.selenium.By;

import com.oracle.sim.pages.Base.SimBasePage;

/**
 * @author dsorthiy
 *
 */
public class ShipmentReasonsPage extends SimBasePage{


		
	
				public ShipmentReasonsPage() throws Exception {
				// TODO Auto-generated constructor stub
				super();
				addObject("RefreshButton", By.xpath("//span[text()='Refresh']"));
				addObject("Title", By.xpath("//h3[@id='sim-screen-title' and text()='Shipment Reasons']"));
				addObject("AddIcon", By.id("add-button"));
				
				
				addObject("Type", By.xpath("//a[contains(@class,'oj-select-arrow')]"));
				addObject("TypeDropdown",By.xpath("//div[@id='oj-select-choice-input-type']//a[contains(@class,'oj-select-arrow')]"));

				addObject("Code",By.xpath("//input[@id='input-code|input']"));
				addObject("Description", By.xpath("//textarea[@id='input-description|input']"));
				addObject("InventoryStatus",By.xpath("//oj-switch[@id='input-use-available']//div[@class='oj-switch-thumb']"));
				//addObject("Sub-bucket", By.xpath("//input[@id='oj-searchselect-input-input-sub-bucket']"));
				addObject("Sub-bucket", By.xpath("//div[@id='oj-select-choice-input-sub-bucket']"));
				addObject("SubbucketLabel",By.xpath("//span[@id='input-sub-bucket|hint']"));
				addObject("SubbucketinEditMode",By.xpath("//div[@id='ojChoiceId_input-type']"));
				addObject("TypeinEditMode",By.xpath("//div[@id='ojChoiceId_input-type']"));
				
				addObject("ApplyButton", By.id("detail-apply-button"));
				addObject("CancelButton",By.id("detail-cancel-button"));
				addObject("SaveIcon", By.id("save-button"));
				addObject("YesButton", By.xpath("//span[text()='Yes']"));
				addObject("NoButton", By.xpath("//span[text()='No']"));
				addObject("DeleteIcon", By.xpath("//oj-button[@id='remove-button']//div[@class='oj-button-label']"));
				//Grid objects
				addObject("Valueisrequired",By.xpath("//div[@class='oj-message-summary'][contains(text(),'Value is required.')]"));
				addObject("FilterName", By.xpath("//div[text()='Description']/following::input[3]"));
				addObject("GridRecord", By.xpath("//mark[contains(@class,'grid-highlight')]"));
				addObject("FilterByType",By.xpath("//div[text()='Type']/following::input[1]"));
			    addObject("NorecordsMsg", By.xpath("//span[@class='rowset-message-text']"));
				//Detail section objects
				
				addObject("EditIcon", By.id("detail-edit-button"));
				addObject("DetailName", By.xpath("//input[@id='input-name|input']"));
				addObject("DetailDescription", By.xpath("//input[@id='input-description|input']"));
				addObject("DetailAddress", By.xpath("//textarea[@id='input-address|input']"));
				addObject("Confirmation_msg",By.xpath("//span[contains(text(),'Are you sure you want to save the changes?')]"));
				
			
				
				//Navigation and Security Pages
				addObject("Navigation",By.xpath("//a[@class='jraf-menu-pin-link']"));
				addObject("BackLink",By.xpath("//a[@class='jraf-hierarchy-back-link']"));
				addObject("SecurityPage",By.xpath("//span[text()='Security']"));
				addObject("RoleMaintenancePage",By.xpath("//span[text()='Role Maintenance']"));
				//addObject("SystemAdministrationPage",By.xpath("//span[contains(text(),'System Administration')]"));
				addObject("FilterByRoleName",By.xpath("//div[text()='Role Name']//following ::input[1]"));
				addObject("RoleNameLink",By.xpath("//a[contains(@class,'cell-descendant cell-link')]"));
				addObject("FilterByDataValue",By.xpath("//div[contains(text(),'Data Value')]//following:: input[3]"));
				addObject("FilterByPermission",By.xpath("//div[contains(text(),'Permission')]//following:: input[2]"));
				addObject("Table",By.xpath("//table[@class='pgbu-grid']"));
				
				addObject("AssignedCheckBox",By.xpath("//label[contains(@class,'sim-icon-font')]"));
				addObject("AssignedData",By.xpath("//td[contains(@id,'-col-assigned')]"));
				addObject("AssigneSelectedButton",By.xpath("//span[contains(@class, 'assign-icon oj-button')]"));
				addObject("RevokeSelectedButton",By.xpath("//span[contains(@class,'revoke-icon oj-button')]"));
				
				//Error Message for Accessing the Shipment Reasons Page
				addObject("ErrorMsgForAccess",By.xpath("//span[contains(text(),'User does not has access to perform the requested')]"));
				addObject("OkButton",By.xpath("//span[text()='OK']"));
				
				addObject("NavigationSearchBar",By.xpath("//input[@class='oj-combobox-input']"));
				addObject("NoResultFound",By.xpath("//span[text() ='No results found.']"));
				
				//System Administration Page Objects
				addObject("FilterByOption",By.xpath("//div[contains(text(),'Option')]//following:: input[2]"));
				addObject("ValueSwitch",By.xpath("//div[@class='oj-switch-thumb']"));
				addObject("BackLink",By.xpath("//a[@class='jraf-hierarchy-back-link']"));
				addObject("UnavailabeLabel",By.xpath("//div[@class='oj-switch-thumb'][contains(text(),'Unavailable')]"));
				addObject("EditIconSystem",By.xpath("//oj-button[@id='detail-edit-button']//div[@class='oj-button-label']"));
				addObject("FinisherdropdownValue",By.xpath("//ul[@id='oj-listbox-results-input-type']//div[text()='Finisher']"));
	}

}
