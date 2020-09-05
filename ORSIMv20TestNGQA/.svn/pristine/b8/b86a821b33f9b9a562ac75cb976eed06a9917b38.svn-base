//Author- lapai
package com.oracle.sim.pages.Lookup;

import org.openqa.selenium.By;

import com.oracle.sim.pages.Base.SimBasePage;
import com.oracle.sim.pages.Lookup.SupplierLookupPage;

public class SupplierLookupPage extends SimBasePage {

	public SupplierLookupPage() throws Exception {
		super();
		addObject("Search", By.xpath("//oj-button[@id='search-button']//span[@class='sim-icon-font sim-toolbar-search-icon oj-button-icon oj-start']"));
		addObject("RefreshButton", By.xpath("//span[text()='Refresh']"));
		addObject("CriteriaScreenName", By.xpath("//h1[text()='Supplier Lookup Search Criteria']"));
		addObject("SupplierId", By.xpath("//input[@id='input-filter-supplier-id|input']"));
		addObject("SearchButton", By.cssSelector(
				"div.oj-dialog-layer.oj-focus-within oj-dialog.oj-dialog.oj-component.oj-component-initnode.oj-complete.oj-draggable.oj-resizable div:nth-child(3) div.oj-dialog-footer oj-button.oj-button-primary.oj-button.oj-component.oj-enabled.oj-button-full-chrome.oj-button-text-icon-start.oj-complete.oj-default:nth-child(1) button.oj-button-button.oj-component-initnode > div.oj-button-label"));
		addObject("DetailSCreen", By.xpath("//h3[text()='Supplier Detail']"));
	
		addObject("SupplierNameFilterBox", By.xpath("//th[2]/input[contains(@class,'column')]"));
		addObject("SupplierIdFilterBox", By.xpath("//th[1]/input[contains(@class,'column')]"));
		addObject("SupplierStatusFilterBox", By.xpath("//input[contains(@id,'status')]"));
		addObject("GridRecordSupplierId", By.xpath("//a[@class='cell-descendant cell-link']"));
		addObject("SupplierStatusColumnElements", By.xpath("//div/mark[contains(@class,'grid-highlight')][contains(text(),'Active')]"));
		
		addObject("SupplierNameColumn",By.xpath("//div[text()='Supplier Name']"));
		addObject("SupplierNameColumnElements",By.xpath("//div[@class='viewport viewport-flex viewport-rowset viewport-all']/table[1]/tbody[1]/tr/td[@data-index='name']"));
		
		addObject("FocusedCell",By.xpath("//tbody//tr/td[contains(@class,'cell-focused')]"));
		
		addObject("SupplierIdDetailScreen", By.xpath("//input[@id='input-supplier-id|input']"));
		addObject("SupplierLookupScreen", By.xpath("//h3[@id='sim-screen-title']"));
		addObject("BackButton", By.xpath("//oj-button[@id='back-button']"));
		addObject("SearchText", By.xpath("//span[contains(text(),'Supplier ID = ')]"));
		addObject("ErrorMessage1", By.xpath("//div[@class='oj-message-summary']"));
		addObject("ErrorMessage2", By.xpath("//span[@class='oj-message-content']"));
		addObject("SupplierNameSearchCriteria", By.xpath("//input[@id='input-filter-supplier-name|input']"));

		addObject("SupplierNameDisplay", By.xpath("//span[contains(text(),'Search Limit ')]"));
		addObject("SupplierIdLabel", By.xpath("//span[text()='Supplier ID']"));
		
		addObject("ItemLabel", By.xpath("//span[text()='Item']"));
		addObject("IncludeInactiveLabel", By.xpath("//label[text()='Include Inactive']"));
		addObject("Toggle", By.xpath("//div[@class='oj-switch-thumb']"));
		addObject("ResetButton", By.cssSelector(
				"div.oj-dialog-layer.oj-focus-within oj-dialog.oj-dialog.oj-component.oj-component-initnode.oj-complete.oj-draggable.oj-resizable div:nth-child(3) div.oj-dialog-footer oj-button.oj-button.oj-component.oj-enabled.oj-button-full-chrome.oj-button-text-icon-start.oj-complete.oj-default:nth-child(2) button.oj-button-button.oj-component-initnode > div.oj-button-label"));
		addObject("CancelButton", By.xpath("/html[1]/body[1]/div[1]/div[2]/oj-dialog[1]/div[3]/div[1]/oj-button[3]/button[1]/div[1]"));
		addObject("SearchLimitLabel", By.xpath("//span[text()='Search Limit']"));
		addObject("SupplierIdLink", By.xpath("//table[1]/tbody[1]/tr[1]/td[@data-index='id']/div/div/a[1]"));
		addObject("SupplierNameLabel", By.xpath("//span[text()='Supplier Name']"));
		addObject("SupplierStatusLabel", By.xpath("//span[@id='input-supplier-status|hint']"));
		addObject("ReturnsAllowedLabel", By.xpath("//span[@id='input-returns-allowed|hint']"));
		addObject("ReturnAuthorizationRequiredLabel", By.xpath("//span[@id='input-returns-auth-req|hint']"));
		addObject("DeliveryDiscrepancyTypeLabel", By.xpath("//span[@id='input-delivery-discrepancy|hint']"));
		addObject("BusinessAddressButton", By.xpath("//oj-collapsible[1]/span[1]/a[1]"));
		addObject("ReturnAddressButton", By.xpath("//oj-collapsible[2]/span[text()='Return Address']"));
		addObject("OrderAddressButton", By.xpath("//oj-collapsible[3]/span[text()='Order Address']"));
		addObject("InvoiceAddressButton", By.xpath("//oj-collapsible[4]/span[text()='Invoice Address']"));
		
		//Access Supplier Lookup
		addObject("FilterByPermission",By.xpath("//div[contains(text(),'Permission')]//following:: input[2]"));
		addObject("RoleMaintenancePage",By.xpath("//span[text()='Role Maintenance']"));
		addObject("AssignedData",By.xpath("//td[contains(@id,'-col-assigned')]"));
		addObject("GridRecord", By.xpath("//mark[contains(@class,'grid-highlight')]"));
		addObject("Title",By.xpath("//h3[@id='sim-screen-title']"));
		addObject("AssigneSelectedButton",By.xpath("//span[contains(@class, 'assign-icon oj-button')]"));
		addObject("RevokeSelectedButton",By.xpath("//span[contains(@class,'revoke-icon oj-button')]"));
		addObject("SaveIcon", By.id("save-button"));
		addObject("YesButton", By.xpath("//oj-button[contains(@class,'oj-button-confirm')]//div[contains(@class,'oj-button-label')]/span[text()='Yes']"));
		addObject("NavigationSearchBar",By.xpath("//input[@class='oj-combobox-input']"));
		addObject("RoleNameLink",By.xpath("//a[contains(@class,'cell-descendant cell-link')]"));
		
		addObject("FilterByRoleName",By.xpath("//div[text()='Role Name']//following ::input[1]"));
		//Business Address
		addObject("BusinessAddressLabel",By.xpath("//span[@id='oj-collapsible-collapsible-address-01-0-header']"));
		addObject("BusinessAddressLine1Label", By.xpath("//label[text()='Address Line 1']"));
		addObject("BusinessAddressLine2Label", By.xpath("//label[text()='Address Line 2']"));
		addObject("BusinessAddressLine3Label", By.xpath("//label[text()='Address Line 3']"));
		addObject("BusinessAddressCityLabel", By.xpath("//label[text()='City']"));
		addObject("BusinessAddressCountyLabel", By.xpath("//label[text()='County']"));
		addObject("BusinessAddressStateLabel", By.xpath("//label[text()='State']"));
		addObject("BusinessAddressCountryLabel", By.xpath("//label[text()='Country']"));
		addObject("BusinessAddressContactLabel", By.xpath("//label[text()='Contact']"));
		addObject("BusinessAddressEmailLabel", By.xpath("//label[text()='Email']"));
		addObject("BusinessAddressPostalCodeLabel", By.xpath("//label[text()='Postal Code']"));
		addObject("BusinessAddressTelephoneLabel", By.xpath("//label[text()='Telephone']"));
		addObject("BusinessAddressFaxLabel", By.xpath("//label[text()='Fax']"));
		
		//Return Address
		addObject("ReturnAddressLabel",By.xpath("//span[@id='oj-collapsible-collapsible-address-03-1-header']"));
		addObject("ReturnAddressLine1Label", By.xpath("//oj-collapsible[@id='collapsible-address-03-1']//div[1]//div[@class='oj-label-group']/label[text()='Address Line 1']"));
		addObject("ReturnAddressLine2Label", By.xpath("//oj-collapsible[@id='collapsible-address-03-1']//div[2]//div[@class='oj-label-group']"));
		addObject("ReturnAddressLine3Label", By.xpath("//oj-collapsible[@id='collapsible-address-03-1']//div[3]//div[@class='oj-label-group']"));
		addObject("ReturnAddressCityLabel", By.xpath("//oj-collapsible[@id='collapsible-address-03-1']//div[4]//div[@class='oj-label-group']"));
		addObject("ReturnAddressCountyLabel", By.xpath("//oj-collapsible[@id='collapsible-address-03-1']//div[5]//div[@class='oj-label-group']"));
		addObject("ReturnAddressStateLabel", By.xpath("//oj-collapsible[@id='collapsible-address-03-1']//div[6]//div[@class='oj-label-group']"));
		addObject("ReturnAddressPostalCodeLabel", By.xpath("//oj-collapsible[@id='collapsible-address-03-1']//div[7]//div[@class='oj-label-group']"));
		addObject("ReturnAddressCountryLabel", By.xpath("//oj-collapsible[@id='collapsible-address-03-1']//div[8]//div[@class='oj-label-group']"));
		addObject("ReturnAddressContactLabel", By.xpath("//oj-collapsible[@id='collapsible-address-03-1']//div[9]//div[@class='oj-label-group']"));
		addObject("ReturnAddressEmailLabel", By.xpath("//oj-collapsible[@id='collapsible-address-03-1']//div[10]//div[@class='oj-label-group']"));
		addObject("ReturnAddressTelephoneLabel", By.xpath("//oj-collapsible[@id='collapsible-address-03-1']//div[11]//div[@class='oj-label-group']"));
		addObject("ReturnAddressFaxLabel", By.xpath("//oj-collapsible[@id='collapsible-address-03-1']//div[12]//div[@class='oj-label-group']"));
		
		//Order Address
		addObject("OrderAddressLabel",By.xpath("//span[@id='oj-collapsible-collapsible-address-04-2-header']"));
		addObject("OrderAddressLine1Label", By.xpath("//oj-collapsible[@id='collapsible-address-04-2']//div[1]//div[@class='oj-label-group']/label[text()='Address Line 1']"));
		addObject("OrderAddressLine2Label", By.xpath("//oj-collapsible[@id='collapsible-address-04-2']//div[1]//div[@class='oj-label-group']/label[text()='Address Line 2']"));
		addObject("OrderAddressLine3Label", By.xpath("//oj-collapsible[@id='collapsible-address-04-2']//div[1]//div[@class='oj-label-group']/label[text()='Address Line 3']"));
		addObject("OrderAddressCityLabel", By.xpath("//oj-collapsible[@id='collapsible-address-04-2']//div[1]//div[@class='oj-label-group']/label[text()='City']"));
		addObject("OrderAddressCountyLabel", By.xpath("//oj-collapsible[@id='collapsible-address-04-2']//div[1]//div[@class='oj-label-group']/label[text()='County']"));
		addObject("OrderAddressStateLabel", By.xpath("//oj-collapsible[@id='collapsible-address-04-2']//div[1]//div[@class='oj-label-group']/label[text()='State']"));
		addObject("OrderAddressPostalCodeLabel", By.xpath("//oj-collapsible[@id='collapsible-address-04-2']//div[1]//div[@class='oj-label-group']/label[text()='Postal Code']"));
		addObject("OrderAddressContactLabel", By.xpath("//oj-collapsible[@id='collapsible-address-04-2']//div[1]//div[@class='oj-label-group']/label[text()='Contact']"));
		addObject("OrderAddressTelephoneLabel", By.xpath("//oj-collapsible[@id='collapsible-address-04-2']//div[1]//div[@class='oj-label-group']/label[text()='Telephone']"));
		addObject("OrderAddressEmailLabel", By.xpath("//oj-collapsible[@id='collapsible-address-04-2']//div[1]//div[@class='oj-label-group']/label[text()='Email']"));
		addObject("OrderAddressFaxLabel", By.xpath("//oj-collapsible[@id='collapsible-address-04-2']//div[1]//div[@class='oj-label-group']/label[text()='Fax']"));
		addObject("OrderAddressCountryLabel", By.xpath("//oj-collapsible[@id='collapsible-address-04-2']//div[1]//div[@class='oj-label-group']/label[text()='Country']"));
		
		
		//Invoice address
		addObject("InvoiceAddressLabel",By.xpath("//span[@id='oj-collapsible-collapsible-address-05-3-header']"));
		addObject("InvoiceAddressLine1Label", By.xpath("//oj-collapsible[@id='collapsible-address-05-3']//div[1]//div[@class='oj-label-group']/label[text()='Address Line 1']"));
		addObject("InvoiceAddressLine2Label", By.xpath("//oj-collapsible[@id='collapsible-address-05-3']//div[1]//div[@class='oj-label-group']/label[text()='Address Line 2']"));
		addObject("InvoiceAddressLine3Label", By.xpath("//oj-collapsible[@id='collapsible-address-05-3']//div[1]//div[@class='oj-label-group']/label[text()='Address Line 3']"));
		addObject("InvoiceAddressCityLabel", By.xpath("//oj-collapsible[@id='collapsible-address-05-3']//div[1]//div[@class='oj-label-group']/label[text()='City']"));
        addObject("InvoiceAddressCountyLabel", By.xpath("//oj-collapsible[@id='collapsible-address-05-3']//div[1]//div[@class='oj-label-group']/label[text()='County']"));
        addObject("InvoiceAddressStateLabel", By.xpath("//oj-collapsible[@id='collapsible-address-05-3']//div[1]//div[@class='oj-label-group']/label[text()='State']"));
        addObject("InvoiceAddressPostalCodeLabel", By.xpath("//oj-collapsible[@id='collapsible-address-05-3']//div[1]//div[@class='oj-label-group']/label[text()='Postal Code']")); 
		addObject("InvoiceAddressCountryLabel", By.xpath("//oj-collapsible[@id='collapsible-address-05-3']//div[1]//div[@class='oj-label-group']/label[text()='Country']"));
		addObject("InvoiceAddressContactLabel", By.xpath("//oj-collapsible[@id='collapsible-address-05-3']//div[1]//div[@class='oj-label-group']/label[text()='Contact']"));
		addObject("InvoiceAddressEmailLabel", By.xpath("//oj-collapsible[@id='collapsible-address-05-3']//div[1]//div[@class='oj-label-group']/label[text()='Email']"));
		addObject("InvoiceAddressTelephoneLabel", By.xpath("//oj-collapsible[@id='collapsible-address-05-3']//div[1]//div[@class='oj-label-group']/label[text()='Telephone']"));
		addObject("InvoiceAddressFaxLabel", By.xpath("//oj-collapsible[@id='collapsible-address-05-3']//div[1]//div[@class='oj-label-group']/label[text()='Fax']"));
	}
}
