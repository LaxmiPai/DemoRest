package com.oracle.sim.pages.Lookup;

import org.openqa.selenium.By;

import com.oracle.sim.pages.Base.SimBasePage;

public class IsnLookUpPage  extends SimBasePage {
	public IsnLookUpPage () throws Exception {
		super();
		{
			addObject("ISNLookUpTitle",By.xpath("//h3[@id='sim-screen-title']"));
			addObject("ISNSearchCriteTitle",By.xpath("//h1[@class='oj-dialog-title' and text()='Item Scan Number Lookup Search Criteria']"));
			
		}
		
	}
}
