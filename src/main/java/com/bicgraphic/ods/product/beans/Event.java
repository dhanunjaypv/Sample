package com.bicgraphic.ods.product.beans;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Event {
	@JsonProperty("EventObject")
	@NotNull(message = "{validation.Event.notNull}")
	private String eventObject = null;

	@JsonProperty("EventType")
	@NotNull(message = "{validation.eventType.notNull}")
	private String eventType = null;

	@JsonProperty("EventDateTime")
	@NotNull(message = "{validation.eventDateTime.notNull}")
	private String eventDateTime = null;

	@JsonProperty("EventSourceSystem")
	@NotNull(message = "{validation.eventSourceSystem.notNull}")
	private String eventSourceSystem = null;

	@JsonProperty("EventBusinessID")
	@NotNull(message = "{validation.eventBusinessID.notNull}")
	private String eventBusinessID = null;

	@JsonProperty("EventDatabaseName")
	private String eventDatabaseName = null;

	@JsonProperty("EventCollectionName")
	private String eventCollectionName = null;

	@JsonProperty("Product")
	@Valid
	private Products product = null;

	public Event() {
		super();
	}

	public Event(String eventObject, String eventType, String eventDateTime, String eventSourceSystem,
			String eventBusinessID, Products product) {
		super();
		this.eventObject = eventObject;
		this.eventType = eventType;
		this.eventDateTime = eventDateTime;
		this.eventSourceSystem = eventSourceSystem;
		this.eventBusinessID = eventBusinessID;
		this.product = product;
	}

	// This constructor used for to expose event object to event api
	public Event(String eventObject, String eventType, String eventDateTime, String eventSourceSystem,
			String eventBusinessID, String eventDatabaseName, String eventCollectionName) {
		super();
		this.eventObject = eventObject;
		this.eventType = eventType;
		this.eventDateTime = eventDateTime;
		this.eventSourceSystem = eventSourceSystem;
		this.eventBusinessID = eventBusinessID;
		this.eventDatabaseName = eventDatabaseName;
		this.eventCollectionName = eventCollectionName;
	}

	public String getEventObject() {
		return eventObject;
	}

	public void setEventObject(String eventObject) {
		this.eventObject = eventObject;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventDateTime() {
		return eventDateTime;
	}

	public void setEventDateTime(String eventDateTime) {
		this.eventDateTime = eventDateTime;
	}

	public String getEventSourceSystem() {
		return eventSourceSystem;
	}

	public void setEventSourceSystem(String eventSourceSystem) {
		this.eventSourceSystem = eventSourceSystem;
	}

	public String getEventBusinessID() {
		return eventBusinessID;
	}

	public void setEventBusinessID(String eventBusinessID) {
		this.eventBusinessID = eventBusinessID;
	}

	public String getEventDatabaseName() {
		return eventDatabaseName;
	}

	public void setEventDatabaseName(String eventDatabaseName) {
		this.eventDatabaseName = eventDatabaseName;
	}

	public String getEventCollectionName() {
		return eventCollectionName;
	}

	public void setEventCollectionName(String eventCollectionName) {
		this.eventCollectionName = eventCollectionName;
	}

	public Products getProduct() {
		return product;
	}

	public void setProduct(Products product) {
		this.product = product;
	}
}
