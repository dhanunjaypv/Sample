package com.bicgraphic.ods.product.beans;

import org.springframework.stereotype.Component;

@Component
public class MongodbData {

	private String host = null;
	private Integer port = null;
	private String database = null;

	
	public MongodbData() {
		super();
	}

	public MongodbData(String host, Integer port, String database) {
		super();
		this.host = host;
		this.port = port;
		this.database = database;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

}
