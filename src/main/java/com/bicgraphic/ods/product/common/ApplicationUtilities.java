/**
 * 
 */
package com.bicgraphic.ods.product.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.bicgraphic.ods.product.beans.Event;
import com.bicgraphic.ods.product.beans.EventRequest;
import com.bicgraphic.ods.product.beans.EventResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author dhanunjaya.potteti
 *
 */
@Component
public class ApplicationUtilities {
	private static final Logger logger = LoggerFactory.getLogger(ApplicationUtilities.class);

	/*
	 * @param reqJson This parameter contains Request in json format
	 * 
	 * @param clazz This parameter represents our bean class type
	 * 
	 * @return T This returns our param class type object with request values.
	 */
	public <T> T convertTypeReference(String reqJson, Class<T> clazz) {
		try {
			return new ObjectMapper().readValue(reqJson, clazz);
		} catch (Exception exception) {
			logger.error("Error : while convertion request to bean class#{}", exception.getMessage());
		}
		return null;

	}

	// SUCCESS EventResponse preparation
	public ServiceResponse prepareCommonResponse(String customercontactSuccess, Event eventRequest) {
		EventResponse eventResponse = new EventResponse();
		ServiceResponse serviceResponse= new ServiceResponse();
		eventResponse.setEnterpriseEventID(eventRequest.getEventBusinessID());
		eventResponse.setEventBusinessID(eventRequest.getEventBusinessID());
		eventResponse.setEventStatus(customercontactSuccess);
		eventResponse.setEventStatusMessage("Successfully Stored the Event Record in MongoDB");
		eventResponse.setMustRetryFlag("false");
		serviceResponse.setEventResponse(eventResponse);
		return serviceResponse;
	}

	// ERROR EventResponse preparation
	public ServiceResponse prepareCommonResponse(String errorCode, String statusMsg, Event eventRequest) {
		EventResponse eventResponse = new EventResponse();
		ServiceResponse serviceResponse= new ServiceResponse();
		eventResponse.setEnterpriseEventID(eventRequest.getEventBusinessID());
		eventResponse.setEventBusinessID(eventRequest.getEventBusinessID());
		eventResponse.setEventStatus("ERROR");
		eventResponse.setEventErrorCode(errorCode);
		eventResponse.setEventStatusMessage(statusMsg);
		eventResponse.setMustRetryFlag("true");
		serviceResponse.setEventResponse(eventResponse);
		return serviceResponse;
	}

	
}
