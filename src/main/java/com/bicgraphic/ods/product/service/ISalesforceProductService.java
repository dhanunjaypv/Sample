package com.bicgraphic.ods.product.service;

import com.bicgraphic.ods.product.beans.Products;
import com.bicgraphic.ods.product.exception.ODSProductException;

public interface ISalesforceProductService {
	public String postProductMetaDataToSF(Products product) throws ODSProductException;
	public String getProduct(String productid) throws ODSProductException;
	public String DeleteProductMetaDataToSF(String productid) throws ODSProductException;
}
