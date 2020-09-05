//**@lrathnak**//
package com.oracle.sim.pages.Configuration;

import org.openqa.selenium.By;

import com.oracle.sim.pages.Base.SimBasePage;

public class SetUpExtendedAttributesPage extends SimBasePage {
public SetUpExtendedAttributesPage() throws Exception {
			super();
			
	addObject("SetUpExtndAtriTitle",By.xpath("//h3[@id='sim-screen-title']"));
	addObject("SaveButton",By.xpath("//oj-button[@id='save-button']"));
	addObject("RefreshButton",By.xpath("//oj-button[@id='refresh-button']"));

	//Detail fields
	addObject("ExtAttriDetlTitle",By.xpath("//h3[@id='sim-screen-detail-title']"));
	addObject("DetailEditButton",By.xpath("//oj-button[@id='detail-edit-button']"));
	addObject("DetailApplyButton",By.xpath("//oj-button[@id='detail-apply-button']"));
	addObject("DetailCancelButton",By.xpath("//oj-button[@id='detail-cancel-button']"));
	addObject("DetailBlock",By.xpath("//div[contains(@class,'sim-screen-detail-side')]"));
	addObject("DetailGS1IDFld",By.xpath("//input[@id='input-code|input']"));
	addObject("DetailNameInputFld",By.xpath("//input[@id='input-label|input']"));
	addObject("DetailNameTextFld",By.xpath("//oj-input-text[@id='input-label']"));
	addObject("DetailDecsInputFld",By.xpath("//textarea[@id='input-description|input']"));
	addObject("DetalTypeInputFld",By.xpath("//input[@id='input-type|input']"));
	addObject("DetailFormatInputFld",By.xpath("//input[@id='input-format|input']"));
	addObject("DetailLengthInputFld",By.xpath("//input[@id='input-length|input']"));
	addObject("FocusedCell",By.xpath("//tbody//tr/td[contains(@class,'cell-focused')]"));
	addObject("NameErrorMsg",By.xpath("//div[@class='oj-message-summary']"));
	
	//List Fields
	addObject("Frstrow",By.xpath("//tr[contains(@id,'-group-all')][1]"));
	addObject("GS1HeaderFld",By.xpath("//th[contains(@id,'grid-header-code')]"));
	addObject("NameHeaderFld",By.xpath("//th[contains(@id,'header-label')]"));
	addObject("TypeHeaderFld",By.xpath("//th[contains(@id,'grid-header-type')]"));
	addObject("FormatHeaderFld",By.xpath("//th[contains(@id,'grid-header-dataType')]"));
	addObject("LengthHeaderFld",By.xpath("//th[contains(@id,'grid-header-dataLength')]"));
	addObject("DescriptionHeadrFld",By.xpath("//th[@aria-label='Description']"));
	addObject("IdColumnAllRows",By.xpath("//td[contains(@id,'-col-code')]"));
	addObject("SaveConfirmationDialog",By.xpath("//span[@class='oj-dialog-title'and text()='Confirmation']"));
	addObject("ConfirmYesButton",By.xpath("//oj-dialog[@id='sim-main-message-dialog']//span[text()='Yes']"));
	addObject("ConfirmNoButton",By.xpath("//oj-dialog[@id='sim-main-message-dialog']//span[text()='No']"));	
	addObject("NameFilterField",By.xpath("//input[contains(@id,'input-column-filter-label')]"));
	addObject("RefreshWarning",By.xpath("//div[@class='oj-dialog-body']//span[contains(text(),'your changes will not be saved')]"));
	addObject("RefreshWarningOkButton",By.xpath("//div[@class='oj-dialog-footer']//child::oj-button[contains(@class,'oj-button oj-component')][1]"));
	addObject("ListTypeFilerFld",By.xpath("//input[contains(@id,'-column-filter-type')]"));
	addObject("FrstTypeCellTxt",By.xpath("//td[contains(@id,'-col-type')]//div"));
	addObject("FrstDescTxt",By.xpath("//td[contains(@id,'-00-col-description')]"));
	addObject("FrstNameTxt",By.xpath("//td[contains(@id,'-00-col-label')]"));
	
	
}
}