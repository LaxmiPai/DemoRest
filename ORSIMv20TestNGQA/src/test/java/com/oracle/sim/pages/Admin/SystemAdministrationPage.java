//Author : shhg

package com.oracle.sim.pages.Admin;

import org.openqa.selenium.By;
import com.oracle.sim.pages.Base.SimBasePage;

public class SystemAdministrationPage extends SimBasePage{
	public SystemAdministrationPage() throws Exception{
		super();
		//Navigation
		addObject("Menuback", By.xpath("//a[@class='jraf-hierarchy-back-link']"));
		addObject("AdminMenu",By.xpath("//div[@class='jraf-menu-body']//span[text()='Admin']"));
		addObject("ConfigurationMenu", By.xpath("//span[text()='Configuration']"));
		addObject("SystemAdministrationMenu", By.xpath("//span[text()='System Administration']"));
		
		//Screen Details
		addObject("ScreenHeading",By.xpath("//h3[@id='sim-screen-title']"));
		addObject("SaveButton",By.xpath("//oj-button[contains(@id,'save-button')]")); 
		addObject("RefreshButton",By.xpath("//oj-button[contains(@id,'refresh-button')]"));
		addObject("GridViewMenu",By.xpath("//oj-menu-button[@title='Grid View Menu']"));
		addObject("ResetView",By.xpath("//a[text()='Reset View']"));
		addObject("ColumnFilter",By.xpath("//a[text()='Column Filter']"));
		addObject("ExportToCsv",By.xpath("//a[text()='Export to CSV']"));
		
		//Column headings
		addObject("TopicColumn",By.xpath("//th[@title='Topic']"));
		addObject("OptionColumn",By.xpath("//th[@title='Option']"));
		addObject("ValueColumn",By.xpath("//th[@title='Value']"));
		addObject("TopicColumnValues",By.xpath("//td[contains(@id, '-col-topic')]"));
		addObject("OptionColumnValues",By.xpath("//td[contains(@id, '-col-description')]"));
		addObject("ValueColumnValues",By.xpath("//td[contains(@id, '-displayValue')]"));
		addObject("FocusedCell",By.xpath("//tbody//tr/td[contains(@class,'cell-focused')]"));
		addObject("TopicColumnFilter",By.xpath("//input[contains(@id,'input-column-filter-topic')]"));
		addObject("OptionColumnFilter",By.xpath("//table[@class='pgbu-grid']//following-sibling::th[contains(@id,'-filter-description')]//input"));
		addObject("ValueColumnFilter",By.xpath("//input[contains(@id,'input-column-filter-displayValue')]"));
		addObject("FirstRecord",By.xpath("//td[contains(@id, '-col-description')]"));
		
		//Edit grid
		addObject("EditHeading",By.xpath("//h3[@id='sim-screen-detail-title']"));
		addObject("EditButton",By.xpath("//oj-button[contains(@id,'detail-edit-button')]"));
		addObject("ApplyButton",By.xpath("//oj-button[@id='detail-apply-button']"));
		addObject("CancelButton",By.xpath("//oj-button[@id='detail-cancel-button']"));
		addObject("TopicEdit",By.xpath("//span[@id='input-topic|hint']"));
		addObject("OptionEdit",By.xpath("//span[@id='input-description|hint']"));
		addObject("TopicValue",By.xpath("//oj-input-text[@id='input-topic']"));
		addObject("OptionValue",By.xpath("//oj-input-text[@id='input-description']"));
		addObject("DaysValue",By.xpath("//input[@id='input-detail-integer|input']"));
		addObject("Error",By.xpath("//oj-input-number//div[@class='oj-message-detail']//span"));
		addObject("SaveMessage",By.xpath("//oj-dialog[@id='sim-main-message-dialog']//div[@class='oj-dialog-body']"));
		addObject("SaveConfirm",By.xpath("//div[@class='oj-dialog-footer']//span[contains(text(),'Yes')]"));
	
	}		
}
