/**
 * 
 */
package com.bicgraphic.ods.product.common;

import com.bicgraphic.ods.product.beans.EventResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author dhanunjaya.potteti
 *
 */
public class ServiceResponse {
	
	@JsonProperty("EventResponse")
	private EventResponse eventResponse;

	public EventResponse getEventResponse() {
		return eventResponse;
	}

	public void setEventResponse(EventResponse eventResponse) {
		this.eventResponse = eventResponse;
	}
	

}
