package com.bicgraphic.ods.product.constants;

public class OdsConstants {

	// Product properties
	public static final String REQUEST_MAPPING_APPLICATION_JSON = "application/json";
	public static final String PRODUCTS_COLLECTION = "ProductData";
	public static final String PRODUCTDATA = "ProductData";
	public static final String PRODUCT_SUCCESS = "Success";
	public static final String PRODUCT_RESPONSE = "Successfully deleted product record";
	public static final String PRODUCT_UPDATE_RESPONSE = "Successfully updated product record";
	public static final String PRODUCT_POST_OPERATION = "CREATE";
	public static final String PRODUCT_UPDATE_OPERATION = "UPDATE";
	public static final String PRODUCT_DELETE_OPERATION = "DELETE";

	public static final String PRODUCT_NUMBER = "productNumber";
	public static final String PRODUCTID = "productId";
	public static final String PRODUCTNAME = "productName";
	public static final String PRODUCTNUMBER = "old productNumber";
	public static final String PRODUCT_STATUS = "productStatus";
	public static final String PRODUCTCLASSCODE = "productClassCode";
	public static final String PRODUCT_LASTUPDATEDATE = "productLastUpdateDate";
	public static final String PRODUCT_CREATIONDATE = "productCreationDate";

	// Mongodb properties
	public static final String MONGO_PROPERTIES_FILE = "mongo.properties";
	public static final String MONGO_HOST = "mongo.host";
	public static final String MONGO_PORT = "mongo.port";
	public static final String MONGO_DATABASE = "mongo.database";

	// Event properties
	public static final String EVENT_PROPERTIES_FILE = "ods_events.properties";
	public static final String EVENT_URL = "service.event.endpoint";

	// Response Codes
	public static final String INV_STRUCTURE_CODE = "INTERR-111";
	public static final String MANDATORY_MISS_CODE = "INTERR-112";
	public static final String INV_INPUT_CODE = "INTERR-113";
	public static final String DATA_NOT_FOUND_CODE = "INTERR-114";
	public static final String EVENT_NETWORK_ERR_CODE = "EVENT-ERR";
	public static final String UNKNOWN_ERR_CODE = "INTERR-115";
	public static final String PRODUCTSUCCESS_CODE = "INTERR-00";
	public static final String PRODUCT_ALREADYEXITS_CODE = "INTERR-00";
	public static final String SALESFORCE_CONNECTION_FAILURE_CODE = "INTERR-116";

	// Response Messages
	public static final String INV_STRUCTURE_MSG = "Invalid Data Structure in Request.";
	public static final String PRODUCT_ALREADYEXITS_MSG = "Product Already Exits";
	public static final String MANDATORY_MISS_MSG = "Mandatory request field missing.";
	public static final String UNKNOWN_ERR_MSG = "OOPS! Something went wrong";
	public static final String INV_INPUT_MSG = "Incorrect request field value ";
	public static final String DATA_NOT_FOUND_MSG = "No Records found for given input";
	public static final String EVENT_NETWORK_ERR_MSG ="Event insertion failed";
	public static final String PUSHEVENTTOODSFAILED ="Product saved successfully,Push Event To ODS failed, ";
	public static final String PRODUCT_SUCCESS_MSG = "SUCCESS";
	public static final String SALESFORCE_CONNECTION_FAILURE_MSG = "Failed to connect Salesforce";
	
	public static final String SFDC_INSERTION_FAILED_CODE = "INTERR-117";
	public static final String SFDC_INSERTION_FAILED_MSG = "Salesforce record insertion failed";
	public static final String SFDC_INSERTION_FAILED_ORIGINAL_CODE = "INTERR-118";

}
