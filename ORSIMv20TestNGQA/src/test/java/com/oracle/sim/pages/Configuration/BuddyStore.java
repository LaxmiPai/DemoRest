package com.oracle.sim.pages.Configuration;

import org.openqa.selenium.By;

import com.oracle.sim.pages.Base.SimBasePage;
/**
 * @author lrathnak
 *
 */

public class BuddyStore extends SimBasePage {
	
	public BuddyStore() throws Exception {
			super();
			addObject("Refresh", By.xpath("//oj-button[@id='refresh-button']"));
			addObject("Add", By.id("add-button"));
			addObject("AssignSelected",By.xpath("//span[text()='Assign Selected']"));
			addObject("RevokeSelected",By.xpath("//span[text()='Revoke Selected']"));
			addObject("Save",By.xpath("//span[text()='Save']"));
			addObject("Yes",By.xpath("//div[@class='oj-dialog-footer']//span[text()='Yes']"));
			addObject("Title",By.xpath("//h3[contains(@class,'sim-screen-title')]"));
			addObject("RefreshCancelButton",By.xpath("//oj-dialog[@id='sim-main-message-dialog']//span[text()='Cancel']"));
			addObject("RefreshPopUp",By.xpath("//span[contains(text(),'If you continue, your changes will not be saved. D')]"));
			addObject("OkButton",By.xpath("//span[text()='OK']"));
			
	}
		
		

	}



