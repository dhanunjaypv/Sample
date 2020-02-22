package com.bicgraphic.ods.product.serviceimpl;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bicgraphic.ods.product.beans.Products;
import com.bicgraphic.ods.product.beans.Event;
import com.bicgraphic.ods.product.common.EventProperties;
import com.bicgraphic.ods.product.common.SalesForceProperties;
import com.bicgraphic.ods.product.constants.OdsConstants;
import com.bicgraphic.ods.product.exception.ODSProductException;
import com.bicgraphic.ods.product.repository.IProductIngestionRepository;
import com.bicgraphic.ods.product.service.IProductIngestionService;

/**
 * @author molivieri
 * @version 1.0
 * @updated 04-Jan-2019 1:50:34 PM
 */
@Service
public class ProductIngestionAPI implements IProductIngestionService {

	private static final Logger logger = LoggerFactory.getLogger(ProductIngestionAPI.class);
	@Autowired
	IProductIngestionRepository IProductIngestionRepository;

	@Autowired
	SalesForceProperties salesForceProperties;

	@Autowired
	private EventProperties eventProperties;

	/**
	 * 
	 * @param product
	 * @throws Exception
	 */
	// Post, update and delete operations in business layer
	public String customerCUD(String eventType, Products product) throws Exception {
		String response = OdsConstants.PRODUCT_SUCCESS;
		product.setProductId(product.getProductId());
		try {
			if (eventType.equals(OdsConstants.PRODUCT_POST_OPERATION)) {
				if (fetchByProductId(product.getProductId()) == null) {
					IProductIngestionRepository.save(product);
				} else {
					throw new ODSProductException(OdsConstants.PRODUCT_ALREADYEXITS_MSG, OdsConstants.PRODUCT_ALREADYEXITS_CODE);
				}
			}
			if (eventType.equals(OdsConstants.PRODUCT_UPDATE_OPERATION)) {
				IProductIngestionRepository.save(product);
			} else if (eventType.equals(OdsConstants.PRODUCT_DELETE_OPERATION)) {
				IProductIngestionRepository.delete(product);
			}
		} catch (ODSProductException e) {
			throw new ODSProductException(e.getCustomerErrorCode(), e.getCustomerErrorCode());
		} catch (Exception e) {
			logger.error("Exception occured - customerCUD():{}", e);
			response = "Product post operation failed";
			throw new ODSProductException(OdsConstants.INV_INPUT_MSG, OdsConstants.INV_INPUT_CODE);
		}
		return response;
	}

	// Check Event and Product object have mandatory fields or not
	public static boolean mandatoryParams(Event event) {

		boolean isMadatorparams = false;
		Products product = event.getProduct();
		if (product != null && product.getProductId() != null && event.getEventType() != null
				&& event.getEventBusinessID() != null && event.getEventObject() != null && event.getEventSourceSystem() != null) {
			isMadatorparams = true;
		}
		return isMadatorparams;
	}

	/**
	 * @param eventRequest
	 * @throws ODSProductException
	 * @throws Exception
	 */
	public void pushEventToODS(Event eventRequest) throws ODSProductException {
		Event eventApiInput;
		try {
			String eventRestEndpoint = eventProperties.getEventEndpoint();
			eventApiInput = new Event(eventRequest.getEventObject(), eventRequest.getEventType(), eventRequest.getEventDateTime(),
					eventRequest.getEventSourceSystem(), eventRequest.getEventBusinessID(), eventRequest.getEventDatabaseName(),
					eventRequest.getEventCollectionName());
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			HttpEntity<Event> entity = new HttpEntity<Event>(eventApiInput, headers);
			ResponseEntity<String> eventResponse = restTemplate.exchange(eventRestEndpoint, HttpMethod.POST, entity, String.class);
			if (eventResponse.getStatusCode() == HttpStatus.OK) {
				logger.debug("Event API post() service successfully done");
			}
		} catch (Exception e) {
			logger.error("Exception occured -  pushEventToODS(Event eventRequest) in ProductIngestionAPI{}", e);
			throw new ODSProductException(OdsConstants.EVENT_NETWORK_ERR_CODE, OdsConstants.PUSHEVENTTOODSFAILED);
		}
	}

	@Override
	public Products fetch(String productid) throws Exception {
		if (productid != null) {
			return IProductIngestionRepository.findByProductID(productid);
		}
		return null;
	}

	@Override
	public Products fetchByProductId(String productID) throws Exception {
		Products product = null;
		try {
			if (productID != null) {
				product = IProductIngestionRepository.findByProductID(productID);
			} else {
				logger.info("Product Id is null");
			}
		} catch (Exception e) {
			logger.error("Exception occured - fetch(String productID) in ProductIngestionAPI{}", e);
		}
		return product;
	}
}
