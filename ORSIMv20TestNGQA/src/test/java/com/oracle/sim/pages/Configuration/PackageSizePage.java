/**
 * 
 */
package com.oracle.sim.pages.Configuration;

import org.openqa.selenium.By;

import com.oracle.sim.pages.Base.SimBasePage;

/**
 * @author niudupa
 *
 */
public class PackageSizePage extends SimBasePage {

	public PackageSizePage() throws Exception {
		super();

		addObject("Title", By.xpath("//h3[text()='Package Size']"));
		addObject("AddNewIcon", By.xpath("//oj-button[contains(@title, 'Add New')]"));
		addObject("DeleteIcon", By.xpath("//oj-button[contains(@id, 'delete')]"));
		addObject("RefreshButton", By.xpath("//span[contains(text(), 'Refresh')]"));
		addObject("SaveButton", By.xpath("//span[contains(text(), 'Save')]"));
		addObject("EditButton", By.xpath("//span[contains(text(), 'Edit')]"));
	//	addObject("ApplyButton", By.xpath("//span[text()='Apply' and contains(@data-bind, 'detailApply')]"));
	//	addObject("ApplyButton", By.xpath("//span[contains(text(), 'Edit')]//following::span[1]"));
		addObject("ApplyButton", By.id("detail-apply-button"));
		addObject("CancelButton",By.id("detail-cancel-button"));
		addObject("DescriptionTxtBox", By.xpath("//input[contains(@id,'name|input')]"));
		addObject("HeightTxtBox", By.xpath("//input[contains(@id,'input-height')]"));
		addObject("WidthTxtBox", By.xpath("//input[contains(@id,'input-width')]"));
		addObject("LengthTxtBox", By.xpath("//input[contains(@id, 'input-length')]"));
		addObject("UOMDropdown", By.xpath("//div[@id='oj-select-choice-input-uom']"));

		
		addObject("YesButton", By.xpath("//oj-dialog[@id='sim-main-message-dialog']//span[text()='Yes']"));
		addObject("NoButton", By.xpath("//oj-dialog[@id='sim-main-message-dialog']//span[text()='No']"));

		// Grid objects
		addObject("GridViewMenu", By.xpath("//oj-button[contains(@id, 'delete')]//following::button[1]"));
		addObject("Row Selector", By.xpath("//oj-menu/oj-option[3]"));
		
		//	addObject("Row Selector", By.xpath("//oj-menu[contains(@id,'ui-id')]//a[text()='Row Selector']"));
		addObject("DescriptionFilter", By.xpath("//th[@title='Description']//following::input[1]"));
		addObject("GridRecord", By.xpath("//mark[contains(@class,'grid-highlight')]"));
		addObject("GridMessage", By.xpath("//span[contains(@class,'rowset-message-text')]"));
		addObject("Valueisrequired",By.xpath("//div[@class='oj-message-summary'][contains(text(),'Value is required.')]"));
		addObject("NumbertooLow",By.xpath("//div[@class='oj-message-summary'][contains(text(),'The number is too low.')]"));
	
		addObject("NumberFormatError",By.xpath("//div[@class='oj-message-summary'][contains(text(),'is not in the expected number format.')]"));
		addObject("FilterByHeight",By.xpath("//div[text()='Height']//following ::input[2]"));
		addObject("FilterByWidth",By.xpath("//div[text()='Width']//following ::input[3]"));
		addObject("FilterByLength",By.xpath("//div[text()='Length']//following ::input[4]"));
		
	
	}

}
