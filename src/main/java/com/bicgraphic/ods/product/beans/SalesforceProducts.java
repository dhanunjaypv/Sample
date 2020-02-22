package com.bicgraphic.ods.product.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class SalesforceProducts 
{
	private String ProductId;
	private String ProductName;
	private String productDescription;
	private String productDescriptionCanada;
    private String keywords;
    private String productSubstatus;
    private String subStatusReason;
    private String visibleFlag;
    private String isHazardous;
    private String activeDate;
    private String introDate;
    private String CatalogPageNumber;
    private String FileCreationDate;
    private String oldProductId;
    
   
    @JsonProperty("Product_ID__c")
    public String getProduct_ID__c() {
        return ProductId;
    }

    /**
     * The product name within the source system
     * (Required)
     * 
     */
    @JsonProperty("Product_ID__c")
    public void setProductId(String productId) {
        this.ProductId = productId;
    }

    /**
     * The unique source system ID for a product
     * 
     */
    @JsonProperty("Name")
    public String getName() {
        return ProductName;
    }

    /**
     * The unique source system ID for a product
     * 
     */
    @JsonProperty("Name")
    public void setProductName(String productName) {
        this.ProductName = productName;
    }

    /**
     * Defines product description within the source system
     * 
     */
    @JsonProperty("Product_Description__c")
    public String getProduct_Description__c() {
        return productDescription;
    }

    /**
     * Defines product description within the source system
     * 
     */
    @JsonProperty("Product_Description__c")
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    /**
     * Defines description for Canadian based products within the source system
     * 
     */
    @JsonProperty("Product_Description_Canada__c")
    public String getProduct_Description_Canada__c() {
        return productDescriptionCanada;
    }

    /**
     * Defines description for Canadian based products within the source system
     * 
     */
    @JsonProperty("Product_Description_Canada__c")
    public void setProductDescriptionCanada(String productDescriptionCanada) {
        this.productDescriptionCanada = productDescriptionCanada;
    }

    /**
     * Define searchable characteristics of a product within the source system
     * 
     */
    @JsonProperty("Keywords__c")
    public String getKeywords__c() {
        return keywords;
    }

    /**
     * Define searchable characteristics of a product within the source system
     * 
     */
    @JsonProperty("Keywords__c")
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    /**
     * Identifies if a product is: Discontinued, New, or Sold Out.
     * (Required)
     * 
     */
    @JsonProperty("Product_Substatus__c")
    public String getProduct_Substatus__c() {
        return productSubstatus;
    }

    /**
     * Identifies if a product is: Discontinued, New, or Sold Out.
     * (Required)
     * 
     */
    @JsonProperty("Product_Substatus__c")
    public void setProductSubstatus(String productSubstatus) {
        this.productSubstatus = productSubstatus;
    }

    /**
     * Identifies the reason why the product is in SubStatusReason state: Created in Error ,Never Launched, Other � See Notes, Patent Issues, Pending Launch, Poor Sales ,Quality,Sold Out ,Vending Discontinued,Vendor Went Out Of Business
     * 
     */
    @JsonProperty("Substatus_Reason__c")
    public String getSubstatus_Reason__c() {
        return subStatusReason;
    }

    /**
     * Identifies the reason why the product is in SubStatusReason state: Created in Error ,Never Launched, Other � See Notes, Patent Issues, Pending Launch, Poor Sales ,Quality,Sold Out ,Vending Discontinued,Vendor Went Out Of Business
     * 
     */
    @JsonProperty("Substatus_Reason__c")
    public void setSubStatusReason(String subStatusReason) {
        this.subStatusReason = subStatusReason;
    }

    /**
     * Identifies if a product is visible on a public site.Available Options: Y/N. within the source system
     * (Required)
     * 
     */
    @JsonProperty("Visible_Flag__c")
    public String getVisible_Flag__c() {
        return visibleFlag;
    }

    /**
     * Identifies if a product is visible on a public site.Available Options: Y/N. within the source system
     * (Required)
     * 
     */
    @JsonProperty("Visible_Flag__c")
    public void setVisibleFlag(String visibleFlag) {
        this.visibleFlag = visibleFlag;
    }

    /**
     * Identifies whether a product contains hazardous materials, ie, lithium batteries.
     * 
     */
    @JsonProperty("Hazardous__c")
    public String getHazardous__c() {
        return isHazardous;
    }

    /**
     * Identifies whether a product contains hazardous materials, ie, lithium batteries.
     * 
     */
    @JsonProperty("Hazardous__c")
    public void setIsHazardous(String isHazardous) {
        this.isHazardous = isHazardous;
    }

    /**
     * Date visible on the web.
     * (Required)
     * 
     */
    @JsonProperty("Active_Date__c")
    public String getActive_Date__c() {
        return activeDate;
    }

    /**
     * Date visible on the web.
     * (Required)
     * 
     */
    @JsonProperty("Active_Date__c")
    public void setActiveDate(String activeDate) {
        this.activeDate = activeDate;
    }

    /**
     * Date a product was introduced or a launch date.
     * (Required)
     * 
     */
    @JsonProperty("intro_Date__c")
    public String getintro_Date__c() {
        return introDate;
    }

    /**
     * Date a product was introduced or a launch date.
     * (Required)
     * 
     */
    @JsonProperty("intro_Date__c")
    public void setIntroDate(String introDate) {
        this.introDate = introDate;
    }

    @JsonProperty("Catalog_Page_Number__c")
    public String getCatalog_Page_Number__c() {
        return CatalogPageNumber;
    }
    
    @JsonProperty("Catalog_Page_Number__c")
    public void setCatalogPageNumber(String CatalogPageNumber) {
        this.CatalogPageNumber = CatalogPageNumber;
    }
    
    @JsonProperty("File_Creation_Date")
    public String getFile_Creation_Date() {
        return FileCreationDate;
    }
    
    @JsonProperty("File_Creation_Date")
    public void setFileCreationDate(String FileCreationDate) {
        this.FileCreationDate = FileCreationDate;
    }
    /**
     * Date a product was introduced or a launch date.
     * (Required)
     * 
     */
    @JsonProperty("Old_Product_ID__c")
    public String getOld_Product_ID__c() {
        return oldProductId;
    }

    /**
     * Date a product was introduced or a launch date.
     * (Required)
     * 
     */
    @JsonProperty("Old_Product_ID__c")
    public void setOldProductId(String oldProductId) {
        this.oldProductId = oldProductId;
    }


}
