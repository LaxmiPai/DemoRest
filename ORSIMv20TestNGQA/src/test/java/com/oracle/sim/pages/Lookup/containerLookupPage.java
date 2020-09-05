//Author : shhg

package com.oracle.sim.pages.Lookup;

import org.openqa.selenium.By;

import com.oracle.sim.pages.Base.SimBasePage;

public class containerLookupPage extends SimBasePage{
	public containerLookupPage() throws Exception{
		super();
		addObject("Menuback", By.xpath("//a[@class='jraf-hierarchy-back-link']"));
		addObject("LookupsMenu",By.xpath("//div[@class='jraf-menu-body']//span[text()='Lookups']"));
		addObject("ContainerLookup", By.xpath("//span[text()='Container Lookup']"));
		addObject("Search", By.xpath("//oj-button[@id='search-button']//div[@class='oj-button-label']"));
		addObject("Navigation",By.xpath("//a[@class='jraf-menu-pin-link']"));
		
		//Search popup
		addObject("SearchPopupHeading", By.xpath("//h1[contains(text(),'Container Lookup Search Criteria')]"));
		addObject("IDField",By.xpath("//span[@id='input-filter-id|hint']"));
		addObject("ContainerIDField",By.xpath("//span[@id='input-filter-carton-ext-id|hint']"));
		addObject("ASNField",By.xpath("//span[@id='input-filter-asn|hint']"));
		addObject("ASNInput",By.xpath("//input[@id='input-filter-asn|input']"));
		addObject("StatusField",By.xpath("//span[@id='input-filter-status|hint']"));
		addObject("StatusDropdown",By.xpath("//oj-select-one[@id='input-filter-status']//div[@class='oj-select-choice']"));
		addObject("StatusDropdownValues",By.xpath("//ul[@id='oj-listbox-results-input-filter-status']//li//div"));
		addObject("ItemField",By.xpath("//span[@id='input-filter-item|hint']"));
		addObject("TypeField",By.xpath("//span[@id='input-filter-type|hint']"));
		addObject("TypeDropDown", By.xpath("//oj-select-one[@id='input-filter-type']"));
		addObject("TypeDropDownValues", By.xpath("//ul[@id='oj-listbox-results-input-filter-type']//li//div"));
		addObject("StoreDropdown", By.xpath("//oj-select-one[@id='input-filter-store']"));
		addObject("AllStoresValue", By.xpath("//div[@class='oj-listbox-result-label'][contains(text(),'All Stores')]"));
		addObject("TypeDropValues",By.xpath("//oj-select-one[@id='input-filter-type']//li"));
		addObject("StoreField",By.xpath("//span[@id='input-filter-store|hint']"));
		addObject("SearchLimitField",By.xpath("//span[@id='input-filter-search-limit|hint']"));
		addObject("SearchLimitEntry", By.xpath("//input[@id='input-filter-search-limit|input']"));
		addObject("PopupSubmitButton",By.xpath("//oj-button[@id='filter-search-button']//span[text()='Search']"));
		addObject("ResetButton",By.xpath("//span[@class='oj-button-text'][text()='Reset']"));
		addObject("CancelButton",By.xpath("//oj-button[@id='filter-cancel-button']"));
		
		//Search summary grid menu
		addObject("GridViewMenu",By.xpath("//oj-menu-button[@title='Grid View Menu']"));
		addObject("ResetView",By.xpath("//a[text()='Reset View']"));
		addObject("ColumnFilter",By.xpath("//a[text()='Column Filter']"));
		addObject("ExportToCsv",By.xpath("//a[text()='Export to CSV']"));
		
		//Summary table Column values
		addObject("IDColumn",By.xpath("//div[text()='ID']"));
		addObject("IDColumnValues",By.xpath("//td[contains(@id,'-col-cartonId')]"));
		addObject("ContainerIDColumn",By.xpath("//th[contains(@id,'grid-header-externalId')]//div[text()='Container ID']"));
		addObject("StatusColumn",By.xpath("//th[contains(@id,'grid-header-status')]//div[text()='Status']"));
		addObject("DirectionColumn",By.xpath("//th[contains(@id,'grid-header-direction')]//div[text()='Direction']"));
		addObject("SourceTypeColumn",By.xpath("//th[contains(@id,'grid-header-sourceType')]//div[text()='Source Type']"));
		addObject("SourceColumn",By.xpath("//th[contains(@id,'grid-header-source')]//div[text()='Source']"));
		addObject("DestinationTypeColumn",By.xpath("//th[contains(@id,'grid-header-destinationType')]//div[text()='Destination Type']"));
		addObject("DestinationColumn",By.xpath("//th[contains(@id,'grid-header-destination')]//div[text()='Destination']"));
		addObject("DispatchDateColumn",By.xpath("//th[contains(@id,'grid-header-statusDate')]//div[text()='Receive/Dispatch Date']"));
		addObject("SKUColumn",By.xpath("//th[contains(@id,'grid-header-numberOfItems')]//div[text()='SKUs']"));
		addObject("CustomerOrderColumn",By.xpath("//th[contains(@id,'grid-header-customerOrder')]//div[text()='Customer Order']"));
		addObject("DirectionFilter",By.xpath("//input[(contains(@id,'input-column-filter-direction'))]"));
		addObject("IDInLink",By.xpath("//tr[1]//a[(contains(@id,'col-cartonId-link'))]"));
		addObject("RefreshButton",By.xpath("//oj-button[@id='refresh-button']"));
		addObject("FocusedCell",By.xpath("//tr//*[contains(@class,'cell-focused')]"));
		
		//Incoming container detail screen header
		addObject("InDetailHeading",By.xpath("//h3[@id='sim-screen-title']"));
		addObject("BackButton",By.xpath("//span[@class='oj-button-text'][contains(text(),'Back')]"));
		addObject("Infobutton",By.xpath("//oj-button[@id='info-button']"));
		addObject("ID",By.xpath("//span[@id='input-item-value|hint']"));
		addObject("ASN",By.xpath("//span[@id='input-info-asn-label|hint']"));
		addObject("ContainerID",By.xpath("//span[@id='input-info-container-id-label|hint']"));
		addObject("Status",By.xpath("//span[@id='input-info-status-label|hint']"));
		addObject("ExtAttbutton",By.xpath("//oj-button[@title='Ext. Attributes']"));
		addObject("UINButton",By.xpath("//oj-button[@id='add-uin-dialog-button']"));
		
		//Info popup
		addObject("InfoHeader",By.xpath("//oj-dialog[@id='sim-screen-info-dialog']//h1[@class='oj-dialog-title']"));
		addObject("DeliveryID",By.xpath("//span[@id='info-transaction-id-label|hint']"));
		addObject("ExpectedDate",By.xpath("//span[@id='input-info-expected-date-label|hint']"));
		addObject("Source",By.xpath("//span[@id='input-info-source|hint']"));
		addObject("SourceType",By.xpath("//span[@id='input-info-source-type|hint']"));
		addObject("Destination",By.xpath("//span[@id='input-info-destination|hint']"));
		addObject("DestinationType",By.xpath("//span[@id='input-info-destination-type|hint']"));
		addObject("UpdateDate",By.xpath("//span[@id='input-info-update-date|hint']"));
		addObject("UpdateUser",By.xpath("//span[@id='input-info-update-user|hint']"));
		addObject("AdjContainer",By.xpath("//span[@id='input-info-adjusted|hint']"));
		addObject("ReceiveOnSF",By.xpath("//span[@id='input-info-recieve-shop|hint']"));
		addObject("DamageReason",By.xpath("//span[@id='input-damage-reason|hint']"));
		addObject("SSCC",By.xpath("//span[@id='input-info-sscc|hint']"));
		addObject("TrackingID",By.xpath("//span[@id='input-info-tracking-id|hint']"));
		addObject("CloseButton",By.xpath("//oj-button[@id='info-close-button']"));
		
		//column values
		addObject("Item",By.xpath("//th[contains(@id,'grid-header-itemId')]"));
		addObject("Desc",By.xpath("//th[contains(@id,'grid-header-shortDescription')]"));
		addObject("UOM",By.xpath("//th[contains(@id,'grid-header-unitOfMeasure')]"));
		addObject("PackSize",By.xpath("//th[contains(@id,'grid-header-caseSize')]"));
		addObject("QtyReceived",By.xpath("//th[contains(@id,'grid-header-quantityReceived')]"));
		addObject("QtyDamaged",By.xpath("//th[contains(@id,'grid-header-quantityDamaged')]"));
		addObject("QtyExpected",By.xpath("//th[contains(@id,'grid-header-quantityExpected')]"));
		addObject("Variance",By.xpath("//th[contains(@id,'grid-header-variance')]"));
		addObject("QtyShipped",By.xpath("//th[contains(@id,'grid-header-quantityShipped')]"));
		
	}

}

