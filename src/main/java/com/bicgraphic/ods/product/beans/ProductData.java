//
//package com.bicgraphic.ods.product.beans;
//
//import java.io.Serializable;
//import java.util.HashMap;
//import java.util.Map;
//import com.fasterxml.jackson.annotation.JsonAnyGetter;
//import com.fasterxml.jackson.annotation.JsonAnySetter;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.annotation.JsonPropertyDescription;
//import com.fasterxml.jackson.annotation.JsonPropertyOrder;
//
//
///**
// * The Product Data model /Schema to ODS
// * <p>
// * This object identifies the fields required to describe product data object to ODS
// * 
// */
//@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonPropertyOrder({
//    "ProductId",
//    "Product"
//})
//public class ProductData implements Serializable
//{
//
//    /**
//     * The product name within the source system
//     * (Required)
//     * 
//     */
//    @JsonProperty("ProductId")
//    @JsonPropertyDescription("The product name within the source system")
//    private String productId;
//    @JsonProperty("Product")
//    private Product product;
//    @JsonIgnore
//    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
//    private final static long serialVersionUID = 3129277589040622598L;
//
//    /**
//     * The product name within the source system
//     * (Required)
//     * 
//     */
//    @JsonProperty("ProductId")
//    public String getProductId() {
//        return productId;
//    }
//
//    /**
//     * The product name within the source system
//     * (Required)
//     * 
//     */
//    @JsonProperty("ProductId")
//    public void setProductId(String productId) {
//        this.productId = productId;
//    }
//
//    @JsonProperty("Product")
//    public Product getProduct() {
//        return product;
//    }
//
//    @JsonProperty("Product")
//    public void setProduct(Product product) {
//        this.product = product;
//    }
//
//    @JsonAnyGetter
//    public Map<String, Object> getAdditionalProperties() {
//        return this.additionalProperties;
//    }
//
//    @JsonAnySetter
//    public void setAdditionalProperty(String name, Object value) {
//        this.additionalProperties.put(name, value);
//    }
//
//}
