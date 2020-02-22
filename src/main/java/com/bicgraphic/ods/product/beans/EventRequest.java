package com.bicgraphic.ods.product.beans;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Event")
public class EventRequest {


    private String EventDateTime;
    private String EventObject;
    private String EventType;
    private String EventSourceSystem;
    private String EventBusinessID;
    private String EventDataBaseName;
    private String EventCollectionName;
    
	public String getEventDateTime() {
		return EventDateTime;
	}

	public void setEventDateTime(String eventDateTime) {
		EventDateTime = eventDateTime;
	}

	public String getEventObject() {
		return EventObject;
	}

	public void setEventObject(String eventObject) {
		EventObject = eventObject;
	}

	public String getEventType() {
		return EventType;
	}

	public void setEventType(String eventType) {
		EventType = eventType;
	}

	public String getEventSourceSystem() {
		return EventSourceSystem;
	}

	public void setEventSourceSystem(String eventSourceSystem) {
		EventSourceSystem = eventSourceSystem;
	}

	public String getEventBusinessID() {
		return EventBusinessID;
	}

	public void setEventBusinessID(String eventBusinessID) {
		EventBusinessID = eventBusinessID;
	}

	public String getEventDataBaseName() {
		return EventDataBaseName;
	}

	public void setEventDataBaseName(String eventDataBaseName) {
		EventDataBaseName = eventDataBaseName;
	}

	public String getEventCollectionName() {
		return EventCollectionName;
	}

	public void setEventCollectionName(String eventCollectionName) {
		EventCollectionName = eventCollectionName;
	}
    
}
