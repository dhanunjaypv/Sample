package com.bicgraphic.ods.product.exception;

import org.springframework.stereotype.Component;

@Component
public class ODSProductException extends Exception{

	private String productErrorCode = null;
	private String productErrorMessage = null;

	public ODSProductException(String customerErrorMessage) {
		super();
		this.productErrorMessage = customerErrorMessage;
	}

	public ODSProductException() {
		super();
	}

	public ODSProductException(String customerErrorCode, String customerErrorMessage) {
		super();
		this.productErrorCode = customerErrorCode;
		this.productErrorMessage = customerErrorMessage;
	}

	public String getCustomerErrorCode() {
		return productErrorCode;
	}

	public void setCustomerErrorCode(String customerErrorCode) {
		this.productErrorCode = customerErrorCode;
	}

	public String getCustomerErrorMessage() {
		return productErrorMessage;
	}

	public void setCustomerErrorMessage(String customerErrorMessage) {
		this.productErrorMessage = customerErrorMessage;
	}

}
