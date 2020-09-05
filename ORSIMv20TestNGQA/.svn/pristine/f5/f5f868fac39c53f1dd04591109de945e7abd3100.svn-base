
package com.oracle.sim.pages.Lookup;

import org.openqa.selenium.By;

import com.oracle.sim.pages.Base.SimBasePage;

public class LookupPage extends SimBasePage {

	
	public LookupPage() throws Exception {
		// TODO Auto-generated constructor stub
		super();
		addObject("FinisherLookup", By.xpath("//span[text()='Finisher Lookup']"));
		//addObject("SearchHeading", By.xpath("//span[text()='Search' and @class='oj-button-text' and @data-bind='text: searchLabel']"));
		addObject("SearchHeading", By.xpath("//oj-button[@id='search-button']//div[@class='oj-button-label']"));
		
		//addObject("FinisherLookupSearch", By.xpath("//span[text()='Finisher Lookup Search Criteria']"));
		addObject("FinisherLookupSearch", By.xpath("//h1[text()='Finisher Lookup Search Criteria']"));
		addObject("FinisherIdTextBox", By.xpath("//span[text()='Finisher ID']//ancestor::div[2]//following-sibling::div//input"));
		addObject("FinisherNameTextBox", By.xpath("//span[text()='Finisher Name']//ancestor::div[2]//following-sibling::div//input"));
		addObject("FinisherItemIdTextBox", By.xpath("//span[text()='Item']//ancestor::div[2]//following-sibling::div//input"));
		
		//addObject("SearchButton", By.xpath("//span[text()='Search' and @class='oj-button-text' and @data-bind='text: filterSearchLabel']"));
		addObject("SearchButton", By.xpath("//oj-button[@id='filter-search-button']//div[@class='oj-button-label']"));
		
		//addObject("SearchButton", By.xpath("//span[@class='oj-button-text' and text()='Search']"));
		//addObject("FinisherIDValue", By.xpath("//span[text()='Finisher ID']//ancestor::div[2]//following-sibling::div//oj-input-text"));
		//addObject("FinisherNameValue", By.xpath("//span[text()='Finisher Name']//ancestor::div[2]//following-sibling::div//oj-input-text"));
		
		addObject("FinisherDetailHeading", By.xpath("//h3[text()='Finisher Detail']"));
		
		addObject("MainSearchHeading", By.xpath("//oj-toolbar//span[@data-bind='text: filterText']"));
		
		addObject("ItemSearchHeading", By.xpath("//span[text()='Item = 100000024 | Search Limit = 40']"));
		addObject("FinisherIdSearchHeading", By.xpath("//span[text()='Finisher ID = 1779 | Search Limit = 40']"));
		addObject("RecordFoundHeading", By.xpath("//span[contains(text(),'Records Found')]"));
		
		addObject("FinisherLookupSearchHeading", By.xpath("//span[contains(text(),'Include Inactive = true | Search Limit = 40')]"));
		
		addObject("FinisherIdValue", By.xpath("//div//a[@class='cell-descendant cell-link' and text()='1779']"));
		addObject("FinisherNameValue", By.xpath("//div [@class='cell-renderer  cell-align-start' and text()='Wash and Pack']"));
		
		
		addObject("IncludeInactiveDefaultValue", By.xpath("//div[@class='oj-flex-item']//div//span[contains(text(),'No')]"));
		
		addObject("IncludeInactiveOption", By.xpath("//div[@class='oj-switch-container oj-form-control-container']//div[@class='oj-switch-track']//div"));
		
		addObject("IncludeInactiveNewValue", By.xpath("//div[@class='oj-flex-item']//div//span[contains(text(),'Yes')]"));
		
		addObject("BackButton", By.xpath("//span[text()='Back']"));
	}

}
