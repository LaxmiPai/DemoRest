package com.oracle.sim.pages.Lookup;

import org.openqa.selenium.By;

import com.oracle.sim.pages.Base.SimBasePage;

public class ItemDetailPage extends SimBasePage {
	public ItemDetailPage() throws Exception {
		// TODO Auto-generated constructor stub
		super();
	
		
		addObject("ItemDetailTitle", By.xpath("//h3[@id='sim-screen-title']"));
		addObject("SearchHeading", By.xpath("//oj-button[@id='search-button']//div[@class='oj-button-label']"));
		addObject("StockLocatorTab", By.xpath("//span[@id='oj-collapsible-collapsible-stock-locations-header']"));
		addObject("ItemAttributesTab", By.xpath("//span[@id='oj-collapsible-collapsible-item-attributes-header']"));
		addObject("InventoryTab", By.xpath("//span[@id='oj-collapsible-collapsible-inventory-header']"));
		addObject("PackItemsTab", By.xpath("//span[@id='oj-collapsible-collapsible-pack-items-header']"));
		addObject("BrandDescriptionLabel", By.xpath("//span[text()='Brand Description']"));
		addObject("AvailableSOHLabel", By.xpath("//span[text()='Available SOH']"));
		addObject("PrimaryUPCLabel", By.xpath("//span[text()='Primary UPC']"));
		addObject("LocationTypeHeader", By.xpath("//th[@title='Location Type']//div[contains(@class,'cell-positioner')]"));
		addObject("LocationHeader", By.xpath("//th[@title='Location']//div[contains(@class,'cell-positioner')]"));
		addObject("AvailableSOHHeader", By.xpath("//th[@title='Available SOH']//div[contains(@class,'cell-positioner')]//div[text()='Available SOH']"));
		addObject("ReceivedTodayHeader", By.xpath("//th[@title='Received Today']//div[contains(@class,'cell-positioner')]"));
		addObject("BuddyStoreHeader", By.xpath("//th[@title='Buddy Store']//div[contains(@class,'cell-positioner')]"));
		//addObject("QuantityHeader", By.xpath("//th[@title='Quantity in Pack']//div[contains(@class,'cell-positioner')]//div[text()='Quantity in Pack']"));
		addObject("QuantityHeader", By.xpath("//div[contains(text(),'Quantity in Pack')]"));
		addObject("FilterCell", By.xpath("//th[contains(@id,'-column-filter-locationType')]"));
		addObject("TableRows", By.xpath("//td[contains(@id,'-col-locationType')]"));
		addObject("ItemIdLabel", By.xpath("//span[text()='Item']"));
		addObject("ItemDescriptionLabel", By.xpath("//span[text()='Description']"));
		addObject("FocusedCell",By.xpath("//tr//*[contains(@class,'cell-focused')]"));
		
	}
}
