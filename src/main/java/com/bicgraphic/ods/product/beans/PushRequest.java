/**
 * 
 */
package com.bicgraphic.ods.product.beans;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author dhanunjaya.potteti
 *
 */
public class PushRequest {
	

    @Valid
    @JsonProperty("EventRequest")
    private Event eventRequest;

    public Event getEventRequest() {
        return eventRequest;
    }

    public void setEventRequest(Event eventRequest) {
        this.eventRequest = eventRequest;
    }

}
