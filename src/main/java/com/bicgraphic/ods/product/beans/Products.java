
package com.bicgraphic.ods.product.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;

import com.bicgraphic.ods.product.constants.OdsConstants;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * The Product Data model /Schema to ODS
 * <p>
 * This object identifies the fields required to describe product data object to ODS
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = OdsConstants.PRODUCTS_COLLECTION)
@JsonPropertyOrder({
    "ProductId",
    "ProductName",
    "ProductDescription",
    "ProductDescriptionCanada",
    "Keywords",
    "ProductSubstatus",
    "SubStatusReason",
    "VisibleFlag",
    "IsHazardous",
    "ActiveDate",
    "IntroDate",
    "OldProductId"
})
public class Products implements Serializable
{

    /**
     * The product name within the source system
     * (Required)
     * 
     */
    @JsonProperty("ProductId")
    @JsonPropertyDescription("The product name within the source system")
    private String productId;
    /**
     * The unique source system ID for a product
     * 
     */
    @JsonProperty("ProductName")
    @JsonPropertyDescription("The unique source system ID for a product")
    @NotNull(message = "{validation.ProductName.notNull}")
    private String productName;
    /**
     * Defines product description within the source system
     * 
     */
    @NotNull(message = "{validation.productDescription.notNull}")
    @JsonProperty("ProductDescription")
    @JsonPropertyDescription("Defines product description within the source system")
    private String productDescription;
    /**
     * Defines description for Canadian based products within the source system
     * 
     */
    //@NotNull(message = "{validation.productDescriptionCanada.notNull}")
    @JsonProperty("ProductDescriptionCanada")
    @JsonPropertyDescription("Defines description for Canadian based products within the source system")
    private String productDescriptionCanada;
    /**
     * Define searchable characteristics of a product within the source system
     * 
     */
    @JsonProperty("Keywords")
    @JsonPropertyDescription("Define searchable characteristics of a product within the source system")
    private String keywords;
    /**
     * Identifies if a product is: Discontinued, New, or Sold Out.
     * (Required)
     * 
     */
    @JsonProperty("ProductSubstatus")
    @JsonPropertyDescription("Identifies if a product is: Discontinued, New, or Sold Out.")
    private String productSubstatus;
    /**
     * Identifies the reason why the product is in SubStatusReason state: Created in Error ,Never Launched, Other � See Notes, Patent Issues, Pending Launch, Poor Sales ,Quality,Sold Out ,Vending Discontinued,Vendor Went Out Of Business
     * 
     */
    @JsonProperty("SubStatusReason")
    @JsonPropertyDescription("Identifies the reason why the product is in SubStatusReason state: Created in Error ,Never Launched, Other \u2013 See Notes, Patent Issues, Pending Launch, Poor Sales ,Quality,Sold Out ,Vending Discontinued,Vendor Went Out Of Business")
    private String subStatusReason;
    /**
     * Identifies if a product is visible on a public site.Available Options: Y/N. within the source system
     * (Required)
     * 
     */
    @JsonProperty("VisibleFlag")
    @JsonPropertyDescription("Identifies if a product is visible on a public site.Available Options: Y/N. within the source system")
    private String visibleFlag;
    /**
     * Identifies whether a product contains hazardous materials, ie, lithium batteries.
     * 
     */
    @JsonProperty("IsHazardous")
    @JsonPropertyDescription("Identifies whether a product contains hazardous materials, ie, lithium batteries.")
    private String isHazardous;
    /**
     * Date visible on the web.
     * (Required)
     * 
     */
    @JsonProperty("ActiveDate")
    @JsonPropertyDescription("Date visible on the web.")
    private String activeDate;
    /**
     * Date a product was introduced or a launch date.
     * (Required)
     * 
     */
    @JsonProperty("IntroDate")
    @JsonPropertyDescription("Date a product was introduced or a launch date.")
    private String introDate;
    /**
     * Date a product was introduced or a launch date.
     * (Required)
     * 
     */
    @JsonProperty("OldProductId")
    @JsonPropertyDescription("Date a product was introduced or a launch date.")
    private String oldProductId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -1553328677996372101L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Products() {
    }

    /**
     * 
     * @param keywords
     * @param productSubstatus
     * @param productDescription
     * @param oldProductId
     * @param introDate
     * @param activeDate
     * @param subStatusReason
     * @param productDescriptionCanada
     * @param visibleFlag
     * @param productName
     * @param isHazardous
     * @param productId
     */
    public Products(String productId, String productName, String productDescription, String productDescriptionCanada, String keywords, String productSubstatus, String subStatusReason, String visibleFlag, String isHazardous, String activeDate, String introDate, String oldProductId) {
        super();
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productDescriptionCanada = productDescriptionCanada;
        this.keywords = keywords;
        this.productSubstatus = productSubstatus;
        this.subStatusReason = subStatusReason;
        this.visibleFlag = visibleFlag;
        this.isHazardous = isHazardous;
        this.activeDate = activeDate;
        this.introDate = introDate;
        this.oldProductId = oldProductId;
    }

    /**
     * The product name within the source system
     * (Required)
     * 
     */
    @JsonProperty("ProductId")
    public String getProductId() {
        return productId;
    }

    /**
     * The product name within the source system
     * (Required)
     * 
     */
    @JsonProperty("ProductId")
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * The unique source system ID for a product
     * 
     */
    @JsonProperty("ProductName")
    public String getProductName() {
        return productName;
    }

    /**
     * The unique source system ID for a product
     * 
     */
    @JsonProperty("ProductName")
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * Defines product description within the source system
     * 
     */
    @JsonProperty("ProductDescription")
    public String getProductDescription() {
        return productDescription;
    }

    /**
     * Defines product description within the source system
     * 
     */
    @JsonProperty("ProductDescription")
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    /**
     * Defines description for Canadian based products within the source system
     * 
     */
    @JsonProperty("ProductDescriptionCanada")
    public String getProductDescriptionCanada() {
        return productDescriptionCanada;
    }

    /**
     * Defines description for Canadian based products within the source system
     * 
     */
    @JsonProperty("ProductDescriptionCanada")
    public void setProductDescriptionCanada(String productDescriptionCanada) {
        this.productDescriptionCanada = productDescriptionCanada;
    }

    /**
     * Define searchable characteristics of a product within the source system
     * 
     */
    @JsonProperty("Keywords")
    public String getKeywords() {
        return keywords;
    }

    /**
     * Define searchable characteristics of a product within the source system
     * 
     */
    @JsonProperty("Keywords")
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    /**
     * Identifies if a product is: Discontinued, New, or Sold Out.
     * (Required)
     * 
     */
    @JsonProperty("ProductSubstatus")
    public String getProductSubstatus() {
        return productSubstatus;
    }

    /**
     * Identifies if a product is: Discontinued, New, or Sold Out.
     * (Required)
     * 
     */
    @JsonProperty("ProductSubstatus")
    public void setProductSubstatus(String productSubstatus) {
        this.productSubstatus = productSubstatus;
    }

    /**
     * Identifies the reason why the product is in SubStatusReason state: Created in Error ,Never Launched, Other � See Notes, Patent Issues, Pending Launch, Poor Sales ,Quality,Sold Out ,Vending Discontinued,Vendor Went Out Of Business
     * 
     */
    @JsonProperty("SubStatusReason")
    public String getSubStatusReason() {
        return subStatusReason;
    }

    /**
     * Identifies the reason why the product is in SubStatusReason state: Created in Error ,Never Launched, Other � See Notes, Patent Issues, Pending Launch, Poor Sales ,Quality,Sold Out ,Vending Discontinued,Vendor Went Out Of Business
     * 
     */
    @JsonProperty("SubStatusReason")
    public void setSubStatusReason(String subStatusReason) {
        this.subStatusReason = subStatusReason;
    }

    /**
     * Identifies if a product is visible on a public site.Available Options: Y/N. within the source system
     * (Required)
     * 
     */
    @JsonProperty("VisibleFlag")
    public String getVisibleFlag() {
        return visibleFlag;
    }

    /**
     * Identifies if a product is visible on a public site.Available Options: Y/N. within the source system
     * (Required)
     * 
     */
    @JsonProperty("VisibleFlag")
    public void setVisibleFlag(String visibleFlag) {
        this.visibleFlag = visibleFlag;
    }

    /**
     * Identifies whether a product contains hazardous materials, ie, lithium batteries.
     * 
     */
    @JsonProperty("IsHazardous")
    public String getIsHazardous() {
        return isHazardous;
    }

    /**
     * Identifies whether a product contains hazardous materials, ie, lithium batteries.
     * 
     */
    @JsonProperty("IsHazardous")
    public void setIsHazardous(String isHazardous) {
        this.isHazardous = isHazardous;
    }

    /**
     * Date visible on the web.
     * (Required)
     * 
     */
    @JsonProperty("ActiveDate")
    public String getActiveDate() {
        return activeDate;
    }

    /**
     * Date visible on the web.
     * (Required)
     * 
     */
    @JsonProperty("ActiveDate")
    public void setActiveDate(String activeDate) {
        this.activeDate = activeDate;
    }

    /**
     * Date a product was introduced or a launch date.
     * (Required)
     * 
     */
    @JsonProperty("IntroDate")
    public String getIntroDate() {
        return introDate;
    }

    /**
     * Date a product was introduced or a launch date.
     * (Required)
     * 
     */
    @JsonProperty("IntroDate")
    public void setIntroDate(String introDate) {
        this.introDate = introDate;
    }

    /**
     * Date a product was introduced or a launch date.
     * (Required)
     * 
     */
    @JsonProperty("OldProductId")
    public String getOldProductId() {
        return oldProductId;
    }

    /**
     * Date a product was introduced or a launch date.
     * (Required)
     * 
     */
    @JsonProperty("OldProductId")
    public void setOldProductId(String oldProductId) {
        this.oldProductId = oldProductId;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
