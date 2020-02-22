package com.bicgraphic.ods.product.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bicgraphic.ods.product.beans.Products;
import com.bicgraphic.ods.product.beans.PushRequest;
import com.bicgraphic.ods.product.common.ApplicationUtilities;
import com.bicgraphic.ods.product.common.ServiceResponse;
import com.bicgraphic.ods.product.beans.Event;
import com.bicgraphic.ods.product.beans.EventResponse;
import com.bicgraphic.ods.product.constants.OdsConstants;
import com.bicgraphic.ods.product.exception.ODSProductException;
import com.bicgraphic.ods.product.service.IProductIngestionService;
import com.bicgraphic.ods.product.serviceimpl.ProductIngestionAPI;
import com.bicgraphic.ods.product.serviceimpl.SalesforceProductServiceImpl;

@RestController
public class SalesForceProductController {
	private static final Logger logger = LoggerFactory.getLogger(SalesForceProductController.class);

	@Autowired
	SalesforceProductServiceImpl salesforceServiceImpl;

	@Autowired
	ApplicationUtilities applicationUtilities;
	
	@Autowired
	IProductIngestionService productService;

	/**
	 * 
	 * @throws Exception
	 */
	/**
	 * @param eventRequest
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addProduct", consumes = {
			"application/json" }, produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<ServiceResponse> addProduct(@Valid @RequestBody PushRequest pushRequest) throws Exception {
		String response = null;
		ServiceResponse serviceResponse = null;
		Event eventRequest = pushRequest.getEventRequest();
		try {

			if (eventRequest != null) {
				//Products product = eventRequest.getProduct();
				String productId=eventRequest.getEventBusinessID();
				boolean isMadarotrypams = ProductIngestionAPI.mandatoryParams(eventRequest);
				Products product=productService.fetchByProductId(productId);
				if (isMadarotrypams) {
					String eventType = pushRequest.getEventRequest().getEventType();
					response = salesforceServiceImpl.postProductMetaDataToSF(product);
					/* SECOND ITERATION */
					if (response == null || !response.equalsIgnoreCase(OdsConstants.PRODUCT_SUCCESS)) {
						// return new ResponseEntity<>(new EventResponse(OdsConstants.INV_INPUT_CODE,
						// OdsConstants.INV_INPUT_MSG), HttpStatus.OK);
						serviceResponse = applicationUtilities.prepareCommonResponse(OdsConstants.INV_INPUT_CODE,
								OdsConstants.INV_INPUT_MSG, eventRequest);
					}

				} else {
					logger.error("Required paramaters missed in event request object");
					serviceResponse = applicationUtilities.prepareCommonResponse(OdsConstants.MANDATORY_MISS_CODE,
							OdsConstants.MANDATORY_MISS_MSG, eventRequest);
				}

			} else {
				logger.error("Product object is empty");
				serviceResponse = applicationUtilities.prepareCommonResponse(OdsConstants.INV_STRUCTURE_CODE,
						OdsConstants.INV_STRUCTURE_MSG, eventRequest);
			}
		} catch (ODSProductException exception) {
			// return new ResponseEntity<>(new
			// EventResponse(exception.getCustomerErrorCode(),
			// exception.getCustomerErrorMessage()), HttpStatus.OK);
			serviceResponse = applicationUtilities.prepareCommonResponse(exception.getCustomerErrorCode(),
					exception.getCustomerErrorMessage(), eventRequest);
		} catch (Exception e) {
			logger.error(e.getMessage());
			serviceResponse = applicationUtilities.prepareCommonResponse(OdsConstants.UNKNOWN_ERR_CODE,
					OdsConstants.UNKNOWN_ERR_MSG, eventRequest);

		}

		return new ResponseEntity<>(
				applicationUtilities.prepareCommonResponse(OdsConstants.PRODUCT_SUCCESS, eventRequest), HttpStatus.OK);
	}

	@RequestMapping(value = "/pushProductToSalesForce", consumes = {
			"application/json" }, produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<ServiceResponse> pushProductToSalesForce(@Valid @RequestBody PushRequest pushRequest)
			throws Exception {
		String response = null;
		 Event eventRequest=null;
		ServiceResponse serviceResponse = null;
		try {
          eventRequest=pushRequest.getEventRequest();
			if (eventRequest != null) {
				Products product = eventRequest.getProduct();
				boolean isMadarotrypams = ProductIngestionAPI.mandatoryParams(eventRequest);
				if (isMadarotrypams) {
					String eventType = eventRequest.getEventType();
					response = salesforceServiceImpl.postProductMetaDataToSF(product);
					/* SECOND ITERATION */
					if (response == null || !response.equalsIgnoreCase(OdsConstants.PRODUCT_SUCCESS)) {
						serviceResponse = applicationUtilities.prepareCommonResponse(OdsConstants.INV_INPUT_CODE,
								OdsConstants.INV_INPUT_MSG, eventRequest);
					}

				} else {
					logger.error("Required paramaters missed in event request object");
					serviceResponse = applicationUtilities.prepareCommonResponse(OdsConstants.MANDATORY_MISS_CODE,
							OdsConstants.MANDATORY_MISS_MSG, eventRequest);
				}
			} else {
				logger.error("Product object is empty");
				serviceResponse = applicationUtilities.prepareCommonResponse(OdsConstants.INV_STRUCTURE_CODE,
						OdsConstants.INV_STRUCTURE_MSG, eventRequest);
			}
		} catch (ODSProductException exception) {
			serviceResponse = applicationUtilities.prepareCommonResponse(exception.getCustomerErrorCode(),
					exception.getCustomerErrorMessage(), eventRequest);
		} catch (Exception e) {
			logger.error(e.getMessage());
			serviceResponse = applicationUtilities.prepareCommonResponse(OdsConstants.UNKNOWN_ERR_CODE,
					OdsConstants.UNKNOWN_ERR_MSG, eventRequest);
		}

		return new ResponseEntity<>(
				applicationUtilities.prepareCommonResponse(OdsConstants.PRODUCT_SUCCESS, eventRequest), HttpStatus.OK);
	}

	@RequestMapping(value = "/pushProductToSalesForce/{productId}", consumes = {
			"application/json" }, produces = "application/json", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteProduct(@PathVariable String productId) throws Exception {
		String response = null;
		try {

			if (productId != null) {
				// Products product = eventRequest.getProduct();
				boolean isMadarotrypams = true; // ProductIngestionAPI.mandatoryParams(eventRequest);
				if (isMadarotrypams) {
					// String eventType = eventRequest.getEventType();
					response = salesforceServiceImpl.DeleteProductMetaDataToSF(productId);
					/* SECOND ITERATION */
					if (response == null || !response.equalsIgnoreCase(OdsConstants.PRODUCT_SUCCESS)) {
						return new ResponseEntity<>(
								new EventResponse(OdsConstants.INV_INPUT_CODE, OdsConstants.INV_INPUT_MSG),
								HttpStatus.OK);

					}

				} else {
					logger.error("Required paramaters missed in event request object");
					return new ResponseEntity<>(
							new EventResponse(OdsConstants.MANDATORY_MISS_CODE, OdsConstants.MANDATORY_MISS_MSG),
							HttpStatus.OK);
				}
			} else {
				logger.error("Product object is empty");
				return new ResponseEntity<>(
						new EventResponse(OdsConstants.INV_STRUCTURE_CODE, OdsConstants.INV_STRUCTURE_MSG),
						HttpStatus.OK);
			}
		} catch (ODSProductException ods) {
			return new ResponseEntity<>(new EventResponse(ods.getCustomerErrorCode(), ods.getCustomerErrorMessage()),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(
					new EventResponse(OdsConstants.INV_STRUCTURE_CODE, OdsConstants.INV_STRUCTURE_MSG), HttpStatus.OK);
		}

		return new ResponseEntity<>(
				new EventResponse(OdsConstants.PRODUCTSUCCESS_CODE, OdsConstants.PRODUCT_SUCCESS_MSG), HttpStatus.OK);
	}

	@RequestMapping(value = "/pushProductToSalesForce/{productId}", consumes = {
			"application/json" }, produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity<Object> getProduct(@PathVariable String productId) throws Exception {
		String response = null;
		try {

			if (productId != null) {
				// Products product = eventRequest.getProduct();
				// boolean isMadarotrypams = ProductIngestionAPI.mandatoryParams(eventRequest);
				boolean isMadarotrypams = true;
				if (isMadarotrypams) {
					// String eventType = eventRequest.getEventType();
					response = salesforceServiceImpl.getProduct(productId);
					/* SECOND ITERATION */
					if (response == null || !response.equalsIgnoreCase(OdsConstants.PRODUCT_SUCCESS)) {
						return new ResponseEntity<>(
								new EventResponse(OdsConstants.INV_INPUT_CODE, OdsConstants.INV_INPUT_MSG),
								HttpStatus.OK);
					}

				} else {
					logger.error("Required paramaters missed in event request object");
					return new ResponseEntity<>(
							new EventResponse(OdsConstants.MANDATORY_MISS_CODE, OdsConstants.MANDATORY_MISS_MSG),
							HttpStatus.OK);
				}
			} else {
				logger.error("Product object is empty");
				return new ResponseEntity<>(
						new EventResponse(OdsConstants.INV_STRUCTURE_CODE, OdsConstants.INV_STRUCTURE_MSG),
						HttpStatus.OK);
			}
		} catch (ODSProductException ods) {
			return new ResponseEntity<>(new EventResponse(ods.getCustomerErrorCode(), ods.getCustomerErrorMessage()),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(
					new EventResponse(OdsConstants.INV_STRUCTURE_CODE, OdsConstants.INV_STRUCTURE_MSG), HttpStatus.OK);
		}

		return new ResponseEntity<>(
				new EventResponse(OdsConstants.PRODUCTSUCCESS_CODE, OdsConstants.PRODUCT_SUCCESS_MSG), HttpStatus.OK);
	}

}
