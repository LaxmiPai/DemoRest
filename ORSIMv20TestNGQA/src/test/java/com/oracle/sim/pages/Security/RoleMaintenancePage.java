package com.oracle.sim.pages.Security;

import org.openqa.selenium.By;

import com.oracle.sim.pages.Base.SimBasePage;

public class RoleMaintenancePage extends SimBasePage{
	public RoleMaintenancePage() throws Exception {
		super();
		addObject("Title",By.xpath("//h3[text()='Role Maintenance']"));
		addObject("FirstTableRecord",By.xpath("//oj-lux-grid[@id='sim-screen-grid-comp']//tbody//tr[1]"));
		addObject("FilterRoleName",By.xpath("//input[contains(@id,'input-column-filter-name')]"));
		addObject("GridRecord",By.xpath("//mark[contains(@class,'grid-highlight')]"));	
		addObject("FilterPermission",By.xpath("//oj-lux-grid[@id='sim-screen-grid-comp']//input[contains(@id,'input-column-filter-description')]"));
		addObject("AssignedData",By.xpath("//td[contains(@id,'-col-assigned')]"));
		addObject("SaveButton",By.xpath("//oj-button[@id='save-button']"));
		addObject("YesButton",By.xpath("//oj-dialog[@id='sim-main-message-dialog']//span[text()='Yes']"));
		addObject("AssignedCheckBox",By.xpath("//input[contains(@id,'-col-assigned-check')]"));
		addObject("BackLink",By.xpath("//a[contains(@class,'jraf-hierarchy-back-link')]"));		
		addObject("AssignSelected",By.xpath("//oj-button[@id='assign-button']"));
		addObject("RevokeSelected",By.xpath("//oj-button[@id='revoke-button']"));
		addObject("DetailTitle",By.xpath("//h3[text()='Role Detail']"));
		addObject("GridViewMenu",By.xpath("//oj-menu-button[@title='Grid View Menu']"));
		addObject("RowSelector",By.xpath("//a[text()='Row Selector']"));
		addObject("ColumnCheckBox",By.xpath("//th[contains(@id,'grid-header-row-selector')]//input"));	
		addObject("RefreshButton",By.xpath("//oj-button[@id='refresh-button']"));
		addObject("RoleNameColumnRecords",By.xpath("//td[contains(@id,'-col-name')]"));
		addObject("Navigation",By.xpath("//a[@class='jraf-menu-pin-link']"));
	}
}
