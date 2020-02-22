package com.bicgraphic.ods.product.controller;

import java.io.File;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.util.Properties;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bicgraphic.ods.product.beans.Products;
import com.bicgraphic.ods.product.beans.Event;
import com.bicgraphic.ods.product.beans.Status;
import com.bicgraphic.ods.product.common.APIInfo;
import com.bicgraphic.ods.product.common.ApplicationUtilities;
import com.bicgraphic.ods.product.constants.OdsConstants;
import com.bicgraphic.ods.product.exception.ODSProductException;
import com.bicgraphic.ods.product.service.IProductIngestionService;
import com.bicgraphic.ods.product.serviceimpl.ProductIngestionAPI;
import com.mongodb.DB;
import com.mongodb.Mongo;

/**
 * @author molivieri
 * @version 1.0
 * @updated 04-Jan-2019 1:50:34 PM
 */
@RestController
public class ProductIngestionAPIController {

	private static final Logger logger = LoggerFactory.getLogger(ProductIngestionAPIController.class);

	@Autowired
	IProductIngestionService productService;

	@Autowired
	BuildProperties buildProperties;

	@Autowired
	ApplicationUtilities applicationUtilities;
	
	/**
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/ProductIngestionAPI", consumes = { "application/json" }, produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<Object> store(@Valid @RequestBody Event eventRequest) throws Exception {
		String response = null;
		
		try {

			if (eventRequest != null) {
				Products product = eventRequest.getProduct();
				// Check mandatory params available in event and customer object
				boolean isMadarotrypams = ProductIngestionAPI.mandatoryParams(eventRequest);
				if (isMadarotrypams) {
					String eventType = eventRequest.getEventType();
					/* FIRST ITERATION */
					// Call to customer account Ingestion api to store the customer data
					response = productService.customerCUD(eventType, product);

					/* SECOND ITERATION */
					// Read event rest end point url from properties file
					if (response != null && response.equalsIgnoreCase(OdsConstants.PRODUCT_SUCCESS)) {
						productService.pushEventToODS(eventRequest);
					} else {
						//return new ResponseEntity<>(new Status(OdsConstants.INV_INPUT_CODE, OdsConstants.INV_INPUT_MSG), HttpStatus.OK);
						applicationUtilities.prepareCommonResponse(OdsConstants.INV_INPUT_CODE, OdsConstants.INV_INPUT_MSG,eventRequest);
					}

				} else {
					logger.error("Required paramaters missed in event request object");
					return new ResponseEntity<>(new Status(OdsConstants.MANDATORY_MISS_CODE, OdsConstants.MANDATORY_MISS_MSG), HttpStatus.OK);
				}
			} else {
				logger.error("Product object is empty");
				return new ResponseEntity<>(new Status(OdsConstants.INV_STRUCTURE_CODE, OdsConstants.INV_STRUCTURE_MSG), HttpStatus.OK);
			}
		} catch (ODSProductException ods) {
			return new ResponseEntity<>(new Status(ods.getCustomerErrorCode(), ods.getCustomerErrorMessage()), HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(new Status(OdsConstants.INV_STRUCTURE_CODE, OdsConstants.INV_STRUCTURE_MSG), HttpStatus.OK);
		}

		return new ResponseEntity<>(new Status(OdsConstants.PRODUCTSUCCESS_CODE, OdsConstants.PRODUCT_SUCCESS_MSG), HttpStatus.OK);
	}

	@RequestMapping(value = "/ProductIngestionAPI/{ID}", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<Object> fetchProductByID(@PathVariable String productID) throws Exception {
		Products product = null;
		try {
			// Check account number is mandatory
			if (productID != null) {
				product = productService.fetch(productID);
				// check final response is null then return customer does not exist in db
				if (product == null)
					return new ResponseEntity<>(new Status(OdsConstants.DATA_NOT_FOUND_CODE, OdsConstants.DATA_NOT_FOUND_MSG), HttpStatus.OK);
			} else {
				logger.error("Account number is null");
				return new ResponseEntity<>(new Status(OdsConstants.MANDATORY_MISS_CODE, OdsConstants.MANDATORY_MISS_MSG), HttpStatus.OK);
			}
		} catch (ODSProductException ods) {
			return new ResponseEntity<>(new Status(ods.getCustomerErrorCode(), ods.getCustomerErrorMessage()), HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(new Status(OdsConstants.INV_STRUCTURE_CODE, OdsConstants.INV_STRUCTURE_MSG), HttpStatus.OK);
		}
		return new ResponseEntity<>(product, HttpStatus.OK);

	}

	@RequestMapping(value = "/Product/{productId}", produces = { "application/json" }, method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteproduct(@PathVariable String productId) throws Exception {
		Products product = null;
		try {
			// Check account number is mandatory
			if (productId != null) {
				product = productService.fetchByProductId(productId);
				// check final response is null then return customer does not exist in db
				if (product == null)
					return new ResponseEntity<>(new Status(OdsConstants.DATA_NOT_FOUND_CODE, OdsConstants.DATA_NOT_FOUND_MSG), HttpStatus.OK);
			} else {
				logger.error("Product Id is null");
				return new ResponseEntity<>(new Status(OdsConstants.MANDATORY_MISS_CODE, OdsConstants.MANDATORY_MISS_MSG), HttpStatus.OK);
			}
		} catch (ODSProductException ods) {
			return new ResponseEntity<>(new Status(ods.getCustomerErrorCode(), ods.getCustomerErrorMessage()), HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(new Status(OdsConstants.INV_STRUCTURE_CODE, OdsConstants.INV_STRUCTURE_MSG), HttpStatus.OK);
		}
		return new ResponseEntity<Object>(product, HttpStatus.OK);

	}

	@RequestMapping(value = "/version", produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity<Object> version() throws Exception {
		APIInfo apiInfo = new APIInfo();
		apiInfo.setBuildNumber(buildProperties.getVersion());
		return new ResponseEntity<Object>(apiInfo, HttpStatus.OK);
	}

	@RequestMapping(value = "/diagnostics", produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity<Object> getDiagnostics() throws Exception {
		APIInfo apiInfo = new APIInfo();
		StringBuilder sb = new StringBuilder();
		Properties prop = new Properties();
		InputStream input = null;
		try {
			ClassLoader cl = this.getClass().getClassLoader();
			// Getting the DB config from Properties file and checking the connection
			input = cl.getResourceAsStream("integration_mongo.properties");
			prop.load(input);
			String hostName = prop.getProperty("mongo.hostname");
			String port = prop.getProperty("mongo.port");
			Mongo m = new Mongo(hostName, Integer.parseInt(port));
			input = cl.getResourceAsStream("application.properties");
			prop.load(input);
			DB db = m.getDB(prop.getProperty("odsproduct.dbname"));
			sb.append("MongoDB Connection Good. ");
			sb.append("Application.properties File available.");
			apiInfo.setMessage(sb.toString());
		}  catch (Exception ex) {
			return new ResponseEntity<Object>(new Status(OdsConstants.UNKNOWN_ERR_CODE, OdsConstants.UNKNOWN_ERR_MSG), HttpStatus.OK);
		}
		return new ResponseEntity<Object>(apiInfo, HttpStatus.OK);
	}

}
