package com.oracle.sim.pages.DataSetup;
/**
 * @author dsorthiy
 *
 */
import org.openqa.selenium.By;

import com.oracle.sim.pages.Base.SimBasePage;


public class SubBucketsPage extends SimBasePage{
	
	
	public SubBucketsPage() throws Exception {
		// TODO Auto-generated constructor stub
		super();
	addObject("RefreshButton", By.xpath("//span[text()='Refresh']"));
	addObject("Title", By.xpath(" //h3[@id='sim-screen-title' and text()='Sub-Buckets']"));
	addObject("AddIcon", By.id("add-button"));
	//addObject("Navigation",By.xpath("//span[@class='oj-navigationlist-item-icon jraf-sidebar-menu-icon jraf-tasks-icon']"));
	
	addObject("Description",By.xpath("//textarea[@id='input-description|input']"));
	
	addObject("ApplyButton", By.id("detail-apply-button"));
	addObject("CancelButton",By.id("detail-cancel-button"));
	
	addObject("Valueisrequired",By.xpath("//div[@class='oj-message-summary'][contains(text(),'Value is required.')]"));
	addObject("SaveIcon", By.id("save-button"));
	
	addObject("YesButton", By.xpath("//span[text()='Yes']"));
	addObject("NoButton", By.xpath("//span[text()='No']"));
	addObject("DeleteIcon", By.xpath("//oj-button[@id='remove-button']//div[@class='oj-button-label']"));
	//Grid objects

	addObject("FilterName", By.xpath("//div[text()='Description']/following::input[2]"));
	addObject("GridRecord", By.xpath("//mark[contains(@class,'grid-highlight')]"));

    addObject("NorecordsMsg", By.xpath("//span[@class='rowset-message-text']"));
	//Detail section objects
	
	addObject("EditButton", By.id("detail-edit-button"));
	addObject("DetailDescription", By.xpath("//textarea[@id='input-description|input']"));
	addObject("Grid View Menu",By.xpath("//span[contains(@class,'sim-icon-font sim-toolbar-grid-view-icon oj-button-icon oj-start')]"));
	addObject("Row Selector",By.xpath("/html[1]/body[1]/div[1]/div[1]/oj-menu[1]/oj-option[3]/a[1]"));
	addObject("CheckboxMenu",By.xpath("//td[ends-with(@id,'-col-row-selector']//input"));
	addObject("Confomration",By.xpath("//span[text()='Confirmation']"));

	addObject("DetailTitle", By.xpath("//h3[text()='Detail']"));
	addObject("Tablerow",By.xpath("//td"));
	
	}
}
