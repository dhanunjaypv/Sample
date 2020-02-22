package com.bicgraphic.ods.product.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:ods_event.properties") 
public class EventProperties {
	
	@Value("${service.event.endpoint}")
    private String eventEndpoint;
    
    
    public String getEventEndpoint() {
        return eventEndpoint;
    }
    public void setEventEndpoint(String eventEndpoint) {
        this.eventEndpoint = eventEndpoint;
    }
	
}
