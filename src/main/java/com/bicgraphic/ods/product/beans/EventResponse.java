package com.bicgraphic.ods.product.beans;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author dhanunjaya.potteti
 *
 */
@Component
@JsonInclude(Include.NON_NULL)
public class EventResponse {

	@JsonProperty("EnterpriseEventID")
	private String enterpriseEventID = "";
	@JsonProperty("EventBusinessID")
	private String eventBusinessID = "";
	@JsonProperty("EventStatus")
	private String eventStatus = "";
	@JsonProperty("EventStatusMessage")
	private String eventStatusMessage = "";
	@JsonProperty("MustRetryFlag")
	private String mustRetryFlag = "true";
	@JsonProperty("EventErrorCode")
	private String eventErrorCode = "";
	
	public EventResponse() {
		
	}
	public EventResponse(String eventErrorCode, String eventStatusMessage) {
		this.eventErrorCode=eventErrorCode;
		this.eventStatusMessage=eventStatusMessage;
	}
	
	public EventResponse(String eventErrorCode, String eventStatusMessage,String mustRetryFlag) {
		this.eventErrorCode=eventErrorCode;
		this.eventStatusMessage=eventStatusMessage;
		this.mustRetryFlag = mustRetryFlag;
	}
	
	public String getEnterpriseEventID() {
		return enterpriseEventID;
	}
	public void setEnterpriseEventID(String enterpriseEventID) {
		this.enterpriseEventID = enterpriseEventID;
	}
	public String getEventBusinessID() {
		return eventBusinessID;
	}
	public void setEventBusinessID(String eventBusinessID) {
		this.eventBusinessID = eventBusinessID;
	}
	public String getEventStatus() {
		return eventStatus;
	}
	public void setEventStatus(String eventStatus) {
		this.eventStatus = eventStatus;
	}
	public String getEventStatusMessage() {
		return eventStatusMessage;
	}
	public void setEventStatusMessage(String eventStatusMessage) {
		this.eventStatusMessage = eventStatusMessage;
	}
	public String getMustRetryFlag() {
		return mustRetryFlag;
	}
	public void setMustRetryFlag(String mustRetryFlag) {
		this.mustRetryFlag = mustRetryFlag;
	}
	public String getEventErrorCode() {
		return eventErrorCode;
	}
	public void setEventErrorCode(String eventErrorCode) {
		this.eventErrorCode = eventErrorCode;
	}
	
	
}
