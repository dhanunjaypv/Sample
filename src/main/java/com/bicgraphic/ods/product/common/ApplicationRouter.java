/**
 * 
 */
package com.bicgraphic.ods.product.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ApplicationRouter {
	private static final Logger logger = LoggerFactory.getLogger(ApplicationRouter.class);

	@Autowired
	private EventProperties eventProperties;

	/* It will invoke external service with Request body & Return RouterResponse
	 * 
	 *  @Request:serviceName, it have specific service value 
	 *  @Request:requestObj , it post request Body 
	 *  @Return: routerResponse, this response coming from external service
	 */
	public RouterResponse invokePostService(String serviceName, Object requestObj) {
		logger.info("Router Request {}#",requestObj);
		RouterResponse routerResponse=null;
		RestTemplate restTemplate = new RestTemplate();
		ObjectMapper objectMapper=new ObjectMapper();
		try {
			ResponseEntity<String> eventResponse = restTemplate.exchange(eventProperties.getEventEndpoint() + serviceName,
					HttpMethod.POST, new HttpEntity<>(requestObj,prepareHttpHeaders()), String.class);
			if (eventResponse.getStatusCode() == HttpStatus.OK) {
				logger.debug("Event API post() service successfully done");
				
				 routerResponse=objectMapper.readValue(eventResponse.getBody(),RouterResponse.class);
				 logger.info("Router Response {}#",routerResponse);
			}
		} catch (HttpStatusCodeException e) {
			logger.error("Router Bad Response {}#",e.getResponseBodyAsString());
			// routerResponse=objectMapper.readValue(e.getResponseBodyAsString(),RouterResponse.class);
			
			
			/*throw new
			OdsOrderLineException(OdsConstants.ORDERLINE_EVENT_NETWORK_ERR_CODE,
			"EVENT RESP:" + e.getResponseBodyAsString());*/
			
				}catch (Exception e) {
			
			/*throw new OdsOrderLineException(OdsConstants.ORDERLINE_INV_STRUCTURE_CODE,
			"Data Convertion failed");*/
			
		}
		 return routerResponse;
	}
	
	
	/* It will invoke external service with Request body & Return RouterResponse
	 * 
	 *  @Request:serviceName, it have specific service value with query or pathvariables and values
	 * 
	 *  @Return: routerResponse, this response coming from external service
	 */
	public RouterResponse invokeGetService(String serviceName) {
		logger.info("Router service name {}#",serviceName);
		RouterResponse routerResponse=null;
		RestTemplate restTemplate = new RestTemplate();
		ObjectMapper objectMapper=new ObjectMapper();
		try {
			ResponseEntity<String> eventResponse = restTemplate.exchange(eventProperties.getEventEndpoint() + serviceName,
					HttpMethod.POST, new HttpEntity<>(prepareHttpHeaders()), String.class);
			if (eventResponse.getStatusCode() == HttpStatus.OK) {
				logger.debug("Event API post() service successfully done");
				
				 routerResponse=objectMapper.readValue(eventResponse.getBody(),RouterResponse.class);
				 logger.info("Router Response {}#",routerResponse);
			}
		} catch (HttpStatusCodeException e) {
			logger.error("Router Bad Response {}#",e.getResponseBodyAsString());
			// routerResponse=objectMapper.readValue(e.getResponseBodyAsString(),RouterResponse.class);
			
			
			/*throw new
			OdsOrderLineException(OdsConstants.ORDERLINE_EVENT_NETWORK_ERR_CODE,
			"EVENT RESP:" + e.getResponseBodyAsString());*/
			
				}catch (Exception e) {
			
			/*throw new OdsOrderLineException(OdsConstants.ORDERLINE_INV_STRUCTURE_CODE,
			"Data Convertion failed");*/
			
		}
		 return routerResponse;
	}



	/* It will prepare  Http headers
	 * 
	 * @Return : headers
	 */
	private HttpHeaders prepareHttpHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type","application/json");
		return headers;
	}
}
