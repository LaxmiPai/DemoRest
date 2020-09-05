/**
 * 
 */
package com.oracle.sim.pages.TechnicalMaintenance;

import org.openqa.selenium.By;

import com.oracle.sim.pages.Base.SimBasePage;

/**
 * @author niudupa
 *
 */
public class PrinterSetupPage extends SimBasePage {

	/**
	 * 
	 */
	public PrinterSetupPage() throws Exception {
		// TODO Auto-generated constructor stub
		super();
		addObject("Refresh", By.xpath("//oj-button[@id='refresh-button']"));
		addObject("Title", By.xpath("//h3[@id='sim-screen-title']"));
		addObject("Add", By.id("add-button"));
		addObject("Delete", By.xpath("//oj-button[@id='delete-button']"));
		addObject("Name", By.xpath("//input[@id='input-name|input']"));
		addObject("Description", By.xpath("//input[@id='input-description|input']"));
		addObject("Address", By.xpath("//textarea[@id='input-address|input']"));
		addObject("ManifestDefault", By.xpath("//oj-switch[@id='input-manifest']//div[@class='oj-switch-thumb']"));
		addObject("PreShipmentDefault",
				By.xpath("//oj-switch[@id='input-preshipment']//div[@class='oj-switch-track']"));
		addObject("Apply", By.id("detail-apply-button"));
		addObject("Save", By.id("save-button"));
		addObject("Yes", By.xpath("//oj-dialog[@id='sim-main-message-dialog']//span[text()='Yes']"));
		addObject("No", By.xpath("//oj-dialog[@id='sim-main-message-dialog']//span[text()='No']"));
		addObject("Type_dropdown", By.xpath("//a[contains(@class,'oj-select-arrow')]"));
		addObject("SaveButton",By.xpath("//oj-button[@id='save-button']//div[@class='oj-button-label']"));
		//	addObject("Type_dropdown", By.xpath("//input[contains(@id, 'searchselect-input-input-type')]"));


		// Grid objects
		addObject("GridViewMenu", By.xpath("//oj-button[contains(@id, 'delete')]//following::button[1]"));
		addObject("PrinterNameColumnElements",By.xpath("//div[@class='viewport viewport-flex viewport-rowset viewport-all']/table[1]/tbody[1]/tr/td[@data-index='name']"));
		addObject("PrinterNameColumn",By.xpath("//div[text()='Name']"));
		addObject("NameGrid",By.xpath("//th[contains(@id,'grid-header-name')]//div[@class='cell-positioner']"));
        addObject("ColumnFilter",By.xpath("//span[@class='sim-icon-font sim-toolbar-filter-icon oj-menu-item-icon']"));
        addObject("RowSelector", By.xpath("//span[@class='sim-icon-font sim-toolbar-selector-icon oj-menu-item-icon']"));
        addObject("RowSelectorCheckBox2",By.xpath("//tbody/tr[2]/td[contains(@id,'-col-row-selector')]//input"));
		addObject("RowSelectorCheckBox1",By.xpath("//tbody/tr[1]/td[contains(@id,'-col-row-selector')]//input"));
        addObject("FilterName", By.xpath("//*[contains(@id,'input-column-filter-name')]"));
		addObject("GridRecord", By.xpath("//mark[contains(@class,'grid-highlight')]"));
		addObject("GridNameColumn", By.xpath("//div[contains(@class,'cell-align-center')][contains(text(),'Name')]"));
		addObject("GridDescriptionColumn", By.xpath("//div[contains(@class,'cell-align-center')][contains(text(),'Description')]"));
		addObject("GridTypeColumn", By.xpath("//div[contains(text(),'Type')]"));
		addObject("GridAddressColumn", By.xpath("//div[contains(@class,'cell-align-center')][contains(text(),'Address')]"));

		// Detail section objects
		addObject("Edit", By.xpath("//oj-button[@id='detail-edit-button']"));
		addObject("CancelButton", By.xpath("//span[text()='Cancel' and contains(@data-bind, 'detail')]"));
		addObject("DetailName", By.xpath("//input[@id='input-name|input']"));
		addObject("DetailDescription", By.xpath("//input[@id='input-description|input']"));
		addObject("DetailAddress", By.xpath("//textarea[@id='input-address|input']"));
		addObject("TypeLabel", By.xpath("//span[text()='Type']"));


		// detail block message
		addObject("NorecordsMsg", By.xpath("//span[@class='rowset-message-text']"));

		//-------------------------filter search---------------------------------------
		addObject("NameColumnRecords",By.xpath("//td[contains(@id,'col-name')]"));
		addObject("PreShipmentColumnName",By.xpath("//div[text()='Pre-Shipment Default']"));
		addObject("FilterName",By.xpath("//input[contains(@id,'input-column-filter-name')]"));
		addObject("FilterDescription",By.xpath("//oj-lux-grid[@id='sim-screen-grid-comp']//input[contains(@id,'input-column-filter-description')]"));
		addObject("FilterAddress",By.xpath("//input[contains(@id,'input-column-filter-uri')]"));
		addObject("FilterManifestDefault",By.xpath("//input[contains(@id,'input-column-filter-manifest')]"));
		addObject("FilterPreShipment",By.xpath("//input[contains(@id,'input-column-filter-preShipment')]"));
		addObject("NameGridRecord",By.xpath("//td[contains(@id,'col-name')]//mark[contains(@class,'grid-highlight')]"));
		addObject("DescriptionGridRecord",By.xpath("//td[contains(@id,'-col-description')]//mark[contains(@class,'grid-highlight')]"));
		addObject("AddressGridRecord",By.xpath("//td[contains(@id,'-col-uri')]//mark[contains(@class,'grid-highlight')]"));
		addObject("ManifestDefaultGridRecord",By.xpath("//td[contains(@id,'-col-manifest')]//mark[contains(@class,'grid-highlight')]"));
		addObject("PreShipmentGridRecord",By.xpath("//td[contains(@id,'-col-preShipment')]//mark[contains(@class,'grid-highlight')]"));
		addObject("GridViewMenu",By.xpath("//oj-menu-button[@title='Grid View Menu']"));
		addObject("ColumnFilter",By.xpath("//a[text()='Column Filter']"));
		addObject("FocusedCell",By.xpath("//tr//*[contains(@class,'cell-focused')]"));
		addObject("RefreshButton", By.xpath("//oj-button[@id='refresh-button']"));
		//--------------------printer validations---------------------------------------
		addObject("DetailNewTitle",By.xpath("//h3[text()='Detail New']"));
		addObject("ErrorMessage",By.xpath("//span[@class='oj-message-content']"));
		addObject("NoRecordsSelectedErrorMessage",By.xpath("//span[contains(text(),'No records selected.')]"));
		addObject("OkButton",By.xpath("//oj-dialog[@id='sim-main-message-dialog']//span[text()='OK']"));
		addObject("MaxLengthNameErrorMsg",By.xpath("//oj-input-text[@id='input-name']//span[@class='oj-message-content']"));
		addObject("MaxLengthDescriptionErrorMsg",By.xpath("//oj-input-text[@id='input-description']//span[@class='oj-message-content']"));
		addObject("MaxLengthAddressErrorMsg",By.xpath("//oj-text-area[@id='input-address']//span[@class='oj-message-content']"));
		addObject("DetailCancelButton",By.xpath("//oj-button[@id='detail-cancel-button']"));
		addObject("RemoveButton",By.xpath("//oj-button[@id='delete-button']"));
		addObject("PreshipmentDefaultButton",By.xpath("//oj-switch[@id='input-preshipment']"));
		addObject("SaveMessage",By.xpath("//span[contains(text(),'Are you sure you want to save the changes?')]"));
		addObject("NoRowsMessage",By.xpath("//span[text()='No rows match the current filter(s).']"));	


	}

}
