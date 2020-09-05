/* @author lrathnak */

package com.oracle.sim.pages.Operations;

import org.openqa.selenium.By;

import com.oracle.sim.pages.Base.SimBasePage;

public class OperationalVwNewItemPage extends SimBasePage {
	public OperationalVwNewItemPage() throws Exception {
		super();
		
		addObject("NewItemTitle",By.xpath("//h3[@id='sim-screen-title']"));
		addObject("SearchButton",By.xpath("//oj-button[@id='search-button']"));
		addObject("ResetButton",By.xpath("//oj-button[@id='reset-button']"));
		addObject("ItemGridMenu",By.xpath("//th[contains(@id,'header-itemId')]"));
		addObject("DescriptionGridMenu",By.xpath("//th[contains(@id,'header-longDescription')]"));
		addObject("DepartmentGridMenu",By.xpath("//th[contains(@id,'header-department')]"));
		addObject("ClassGridMenu",By.xpath("//th[contains(@id,'header-clazz')]"));
		addObject("SubClassGridMenu",By.xpath("//th[contains(@id,'header-subclass')]"));
		addObject("PrimaryLocationGridMenu",By.xpath("//th[contains(@id,'header-sequenceDescription')]"));
		addObject("AvailableSOHGridMenu",By.xpath("//th[contains(@id,'header-availableSoh')]"));
		addObject("FromDateSearchFldLbl",By.xpath("//span[text()='From Date']"));
		addObject("DepartmentSearchFldLbl",By.xpath("//span[text()='Department']"));
		addObject("SearchLmtSearchFldLbl",By.xpath("//span[text()='Search Limit']"));
		addObject("ToDateSearchFldLbl",By.xpath("//span[text()='To Date']"));
		addObject("ClassSearchFldLbl",By.xpath("//span[text()='Class']"));
		addObject("SearchModeSearchFldLbl",By.xpath("//span[text()='Search Mode']"));
		addObject("SubClassSearchFldLbl",By.xpath("//span[text()='Sub-Class']"));
		addObject("SupplierSearchFldLbl",By.xpath("//label[text()='Supplier']"));
		addObject("PrimarySupplierSearchFldLbl",By.xpath("//label[text()='Primary Supplier']"));
		addObject("SearchModeDropDown",By.xpath("//div[contains(@id,'oj-select-choice-input-filter-type')]"));
		addObject("FromDateInputFld",By.xpath("//input[@id='input-filter-from-date|input']"));
		addObject("ToDateInputFld",By.xpath("//input[@id='input-filter-to-date|input']"));
		addObject("TableRows",By.xpath("//tr[contains(@id,'-group-all')]"));
		addObject("LastColumnName",By.xpath("//th[contains(@id,'header-availableSoh')]"));
		addObject("ItemFilter",By.xpath("//input[contains(@id,'-filter-itemId')]"));
		addObject("DateInputField",By.xpath("//input[@id='input-filter-to-date|input']"));
		addObject("FrstItemID",By.xpath("//th[contains(@id,'header-itemId')]//following::a[1]"));
		addObject("ItemDetailTitle",By.xpath("//h3[@id='sim-screen-title']"));
		addObject("DetailInventoryTab",By.xpath("//span[text()='Inventory']"));
		addObject("DetailItemAttributeTab",By.xpath("//span[contains(@id,'-item-attributes-header')]"));
		addObject("DetailRelatedItemsTab",By.xpath("//span[contains(@id,'-related-items-header')]"));
		addObject("DetailUDAsTab",By.xpath("//span[contains(@id,'-udas-header')]"));
		addObject("DetailPackItemsTab",By.xpath("//span[contains(@id,'-pack-items-header')]"));
		addObject("DetailStockLocatorTab",By.xpath("//span[contains(@id,'-stock-locations-header')]"));
		addObject("DetailDeleveriesTab",By.xpath("//span[contains(@id,'-delivery-items-header')]"));
		addObject("DetailItemLocatorTab",By.xpath("//span[contains(@id,'-item-locations-header')]"));
		addObject("DetailSuppliersTab",By.xpath("//span[contains(@id,'-additional-suppliers-header')]"));
		addObject("DetailPricingInfoTab",By.xpath("//span[contains(@id,'-pricing-info-header')]"));
		addObject("DetailBackButton",By.xpath("//oj-button[@id='back-button']"));
		addObject("GridViewMenu",By.xpath("//oj-menu-button[@title='Grid View Menu']"));
		addObject("ResetView",By.xpath("//a[text()='Reset View']"));
		addObject("ColumnFilter",By.xpath("//a[text()='Column Filter']"));
		addObject("RowSelector",By.xpath("//a[text()='Row Selector']"));
		addObject("FocusedCell",By.xpath("//tr//*[contains(@class,'cell-focused')]"));
		addObject("SearchFieldDepartmentDropDown",By.xpath("//div[@id='oj-select-choice-input-filter-department']"));
		addObject("SearchFieldDeprtTxtBx",By.xpath("//span[contains(@id,'department_selected')]"));
		addObject("SearchDeptDrpDwnValues",By.xpath("//div[contains(@id,'-result-label')]"));
		addObject("DepartmentDropDownTxtBx",By.xpath("//input[contains(@aria-controls,'input-filter-department')]"));
		addObject("ErrMsgNoRcrd",By.xpath("//div[contains(@class,'oj-dialog-body')]//following::span[contains(text(),'No records were found')]"));
		addObject("ErrMasgOkButton",By.xpath("//oj-button[contains(@class,'oj-button-default')]"));
		addObject("ToolBar",By.xpath("//oj-toolbar[@id='sim-screen-grid-toolbar']"));
		addObject("PrimarySupplierSwitch",By.xpath("//oj-switch[@id='input-filter-primary-supplier']"));
		addObject("ItemColumnSortIcon",By.xpath("//th[contains(@id,'header-itemId')]//div[@class='column-sort-icon']"));
		addObject("AllItemLink",By.xpath("//th[contains(@id,'header-itemId')]//following::a"));
		addObject("FrstDescrColumn",By.xpath("//th[contains(@id,'header-longDescription')]//following::td[contains(@id,'longDescription')][1]"));
		addObject("DescriptionFilter",By.xpath("//input[contains(@id,'filter-longDescription')]"));
		addObject("FrstDepartmntClm",By.xpath("//th[contains(@id,'header-department')]//following::td[contains(@id,'-col-department')][1]"));
		addObject("DepartmentFiltr",By.xpath("//input[contains(@id,'-filter-department')]"));
		
		addObject("ClassFrstClm",By.xpath("//th[contains(@id,'header-clazz')]//following::td[contains(@id,'-col-clazz')][1]"));
		addObject("ClassFilter",By.xpath("//input[contains(@id,'-filter-clazz')]"));
		
		addObject("SubClsFrstClm",By.xpath("//th[contains(@id,'header-subclass')]//following::td[contains(@id,'-col-subclass')][1]"));
		addObject("SubClsFiltr",By.xpath("//input[contains(@id,'-filter-subclass')]"));
		
		addObject("DetailbackButton",By.xpath("//oj-button[@id='back-button']"));

		addObject("SupplierInputFld",By.xpath("//input[@id='input-filter-supplier|input']"));
		
		addObject("GridHighLightRecord",By.xpath("//mark[contains(@class,'grid-highlight')]"));
		
		addObject("DrpDwnHilightedOption",By.xpath("//span[@class='oj-listbox-highlighter']"));
		
		addObject("PrimaryLocFrstClm",By.xpath("//th[contains(@id,'sequenceDescription')]//following::td[contains(@id,'col-sequenceDescription')][1]"));
		addObject("PrimaryLcoFltr",By.xpath("//input[contains(@id,'filter-sequenceDescription')]"));
		
		addObject("AvlbSOhFrstClm",By.xpath("//th[contains(@id,'-header-availableSoh')]//following::td[contains(@id,'col-sequenceDescription')][1]"));
		addObject("AvlbSohFltr",By.xpath("//input[contains(@id,'-filter-availableSoh')]"));
		
		addObject("AllDecClmRcds",By.xpath("//td[contains(@id,'-col-longDescription')]"));
	}

}
