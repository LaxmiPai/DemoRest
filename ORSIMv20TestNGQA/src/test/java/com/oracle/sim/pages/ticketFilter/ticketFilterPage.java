//Author : shhg

package com.oracle.sim.pages.ticketFilter;

import org.openqa.selenium.By;

import com.oracle.sim.pages.Base.SimBasePage;

public class ticketFilterPage extends SimBasePage{
	public ticketFilterPage() throws Exception{
		super();
		addObject("InventoryMgmt",By.xpath("//span[text()='Inventory Management']"));
		addObject("TicketMenu",By.xpath("//span[text()='Ticket']"));
		addObject("SearchButton",By.xpath("//oj-button[@id='search-button']//span[contains(text(),'Search')]"));
		addObject("SearchHeading",By.xpath("//h1[contains(text(),'Ticket Search Criteria')]"));
		addObject("FromPrintD",By.xpath("//label[@class='oj-component-initnode']//span[contains(text(),'From Print Date')]"));
		addObject("ToPrintD",By.xpath("//label[@class='oj-component-initnode']//span[contains(text(),'To Print Date')]"));
		addObject("FromPrintedD",By.xpath("//label[@class='oj-component-initnode']//span[contains(text(),'From Printed Date')]"));
		addObject("ToPrintedD",By.xpath("//label[@class='oj-component-initnode']//span[contains(text(),'To Printed Date')]"));
		addObject("FromActiveD",By.xpath("//label[@class='oj-component-initnode']//span[contains(text(),'From Active Date')]"));
		addObject("ToActiveD",By.xpath("//label[@class='oj-component-initnode']//span[contains(text(),'To Active Date')]"));
		addObject("FromEndD",By.xpath("//label[@class='oj-component-initnode']//span[contains(text(),'From End Date')]"));
		addObject("ToEndD",By.xpath("//label[@class='oj-component-initnode']//span[contains(text(),'To End Date')]"));
		addObject("Item",By.xpath("//label[@class='oj-component-initnode']//span[contains(text(),'Item')]"));
		addObject("ItemDataIndex",By.xpath("//td[@data-index='itemId']"));
		addObject("Dept",By.xpath("//label[@class='oj-component-initnode']//span[contains(text(),'Department')]"));
		addObject("Class",By.xpath("//label[@class='oj-component-initnode']//span[contains(text(),'Class')]"));
		addObject("Sub-Class",By.xpath("//label[@class='oj-component-initnode']//span[contains(text(),'Sub-Class')]"));
		addObject("FormatType",By.xpath("//label[@class='oj-component-initnode']//span[contains(text(),'Format Type')]"));
		addObject("FormatTypeDropdown", By.xpath("//oj-dialog//span[text()='Format Type']/ancestor::div[@class='oj-flex']//oj-select-one"));
		addObject("FormatColumnName",By.xpath("//th[contains(@id,'-header-ticketFormatType')]"));
		addObject("FormatColumnValues",By.xpath("//td[contains[@id, '-col-ticketFormatType']"));
		addObject("Format",By.xpath("//div[12]//div[1]//oj-label[1]//div[1]//label[1]//span[1]"));
		addObject("OriginType",By.xpath("//label[@class='oj-component-initnode']//span[contains(text(),'Origin Type')]"));
		addObject("PriceType",By.xpath("//label[@class='oj-component-initnode']//span[contains(text(),'Price Type')]"));
		addObject("Printed",By.xpath("//label[@class='oj-component-initnode']//span[contains(text(),'Printed')]"));
		//addObject("PrintedDropdown",By.xpath("//oj-dialog//span[text()='Printed']/ancestor::div[@class='oj-flex']//oj-select-one"));
		addObject("PrintedDropdown",By.xpath("//span[text()='Printed']//following::input[1]"));
		addObject("PintedYes",By.xpath("//div[@class='oj-listbox-drop']//div[text()='Yes']"));
		addObject("PintedAll",By.xpath("//div[@class='oj-listbox-drop']//div[text()='All']"));		
		addObject("User",By.xpath("//label[text()='User']"));
		addObject("SearchLimit",By.xpath("//label[@class='oj-component-initnode']//span[contains(text(),'Search Limit')]"));
		addObject("SearchButton",By.xpath("//div[@class='oj-dialog-footer']//span[contains(text(),'Search')]"));
		addObject("ResetButton",By.xpath("//div[@class='oj-dialog-footer']//span[contains(text(),'Reset')]"));
		addObject("CancelButton",By.xpath("//div[@class='oj-dialog-footer']//span[contains(text(),'Cancel')]"));
		addObject("SummarySearch",By.xpath("//oj-button[@id='search-button']"));
		addObject("PrintedDropDownbox",By.xpath("//oj-form-layout//div[15]//a[contains(@class,'oj-select-arrow')]"));
		addObject("SearchLimitTextBox",By.xpath("//span[text()='Search Limit']//ancestor::div[@class='oj-flex']//input"));
		addObject("RefreshButton",By.xpath("//oj-button[@id='refresh-button']"));
		
		//For filtering format type values
		addObject("FormatTypeDropdownValues",By.xpath("//ul[contains(@id,'oj-listbox-results-sim-uid-')]//li//div"));
		addObject("FromPrintDValue",By.xpath("//span[text()='From Print Date']//ancestor::div[@class='oj-flex']//child::input"));
		addObject("ToPrintDValue",By.xpath("//span[text()='To Print Date']//ancestor::div[@class='oj-flex']//child::input"));
		addObject("ToPrintDatePicker",By.xpath("//span[text()='To Print Date']//ancestor::div[@class='oj-flex']//child::oj-input-date"));
		addObject("FromActiveDValue",By.xpath("//span[text()='From Active Date']//ancestor::div[@class='oj-flex']//child::input"));
		addObject("ToActiveDValue",By.xpath("//span[text()='To Active Date']//ancestor::div[@class='oj-flex']//child::input"));
		addObject("FromEndDValue",By.xpath("//span[text()='From End Date']//ancestor::div[@class='oj-flex']//child::input"));
		addObject("ToEndDValue",By.xpath("//span[text()='To End Date']//ancestor::div[@class='oj-flex']//child::input"));
		addObject("FromPrintedDValue",By.xpath("//span[text()='From Printed Date']//ancestor::div[@class='oj-flex']//child::input"));
		addObject("ToPrintedDValue",By.xpath("//span[text()='To Printed Date']//ancestor::div[@class='oj-flex']//child::input"));
		addObject("SearchLimitValue",By.xpath("//input[@class='oj-inputnumber-input oj-text-field-input oj-component-initnode']"));
		addObject("FormatTypeDataIndex",By.xpath("//td[@data-index='ticketFormatType']"));
		addObject("FormatItemTicket",By.xpath("//div[@class='oj-listbox-result-label'][text()='Item Ticket']"));
		addObject("FormatShelfLabel",By.xpath("//div[@class='oj-listbox-result-label'][text()='Shelf Label']"));
		
		//For filtering Origin type values
		addObject("OriginTypeDropdownValues",By.xpath("//ul[contains(@id,'oj-listbox-results-sim-uid-')]//li//div"));
		addObject("OriginTypeDropdown",By.xpath("//oj-dialog//span[text()='Origin Type']/ancestor::div[@class='oj-flex']//oj-select-one"));
		addObject("OriginTypeDataIndex",By.xpath("//td[@data-index='originType']"));
		addObject("OriginManual",By.xpath("//div[@class='oj-listbox-result-label'][text()='Manual']"));
		
		//For filtering Price type values
		addObject("PriceTypeDropdownValues",By.xpath("//ul[contains(@id,'oj-listbox-results-sim-uid-')]//li//div"));
		addObject("PriceTypeDropdown",By.xpath("//oj-dialog//span[text()='Price Type']/ancestor::div[@class='oj-flex']//oj-select-one"));
		addObject("PriceTypeDataIndex",By.xpath("//td[@data-index='PriceType']"));
		addObject("PricePermanent",By.xpath("//div[@class='oj-listbox-result-label'][text()='Permanent']"));
		
		//For verifying item field filtering and validations
		addObject("ItemInput",By.xpath("//span[text()='Item']/ancestor::div[@class='oj-flex']//child::input"));
		addObject("ItemError",By.xpath("//div[@class='oj-message-detail']"));
		addObject("ItemColumnIndex",By.xpath("//td[@data-index='itemId']"));
		
		//For verifying user field filtering and validations
		addObject("UserInput",By.xpath("//div//label[text()='User']//ancestor::div[@class='oj-flex']//child::input"));
		addObject("TotalResult", By.xpath("//span[contains(@class, 'oj-sm-margin')]"));		
		
		//For verifying filtered data using Last updated date
		addObject("FromPrintedDatePicker",By.xpath("//span[text()='From Printed Date']//ancestor::div[@class='oj-flex']//child::oj-input-date"));
		addObject("ToPrintDatePicker",By.xpath("//span[text()='To Printed Date']//ancestor::div[@class='oj-flex']//child::oj-input-date"));
	}
}

