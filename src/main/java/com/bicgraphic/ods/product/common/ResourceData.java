package com.bicgraphic.ods.product.common;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bicgraphic.ods.product.constants.OdsConstants;

public class ResourceData {

	private static final Logger logger = LoggerFactory.getLogger(ResourceData.class);

	public static Properties getEventProperties() throws Exception {
		Properties eventProperties = new Properties();
		InputStream inputStream = null;
		try {
			inputStream = ResourceData.class.getClassLoader().getResourceAsStream(OdsConstants.EVENT_PROPERTIES_FILE);
			if (inputStream == null) {
				logger.info("ods_events.properties file not found");
			}

			// load a properties file from class path, inside static method
			eventProperties.load(inputStream);

			// get the property value
		} catch (Exception e) {
			logger.error("Exception : ", e);
			throw new Exception("Cannot find ods_events.properties file to create event object : " + e.getMessage());
		} finally {

			if (null != inputStream) {
				inputStream.close();
			}
			logger.info("closed event InputStream.");
		}
		return eventProperties;
	}

}
