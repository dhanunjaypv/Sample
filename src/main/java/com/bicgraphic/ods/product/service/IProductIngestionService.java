package com.bicgraphic.ods.product.service;


import com.bicgraphic.ods.product.beans.Products;
import com.bicgraphic.ods.product.beans.Event;
import com.bicgraphic.ods.product.exception.ODSProductException;

public interface IProductIngestionService {

	String customerCUD(String eventType, Products product) throws Exception;
	public Products fetch(String accountNumber) throws Exception;
	public Products fetchByProductId(String customerId) throws Exception;
	public void pushEventToODS(Event eventRequest) throws ODSProductException;
}
