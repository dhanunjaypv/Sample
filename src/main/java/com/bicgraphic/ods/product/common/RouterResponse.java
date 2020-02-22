/**
 * 
 */
package com.bicgraphic.ods.product.common;

import org.springframework.stereotype.Component;

import com.bicgraphic.ods.product.beans.EventResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author dhanunjaya.potteti
 *
 */
@Component
@JsonInclude(Include.NON_NULL)
public class RouterResponse {

	private String resCode;
	private String resMsg;

	@JsonProperty("EventResponse")
	private EventResponse eventResponse;

	public String getResCode() {
		return resCode;
	}

	public void setResCode(String resCode) {
		this.resCode = resCode;
	}

	public String getResMsg() {
		return resMsg;
	}

	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}

	public EventResponse getEventResponse() {
		return eventResponse;
	}

	public void setEventResponse(EventResponse eventResponse) {
		this.eventResponse = eventResponse;
	}

}
