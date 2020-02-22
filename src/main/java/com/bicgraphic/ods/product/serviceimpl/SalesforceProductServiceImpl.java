package com.bicgraphic.ods.product.serviceimpl;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bicgraphic.ods.product.beans.Products;
import com.bicgraphic.ods.product.beans.SalesforceProducts;
import com.bicgraphic.ods.product.common.SalesForceProperties;
import com.bicgraphic.ods.product.constants.OdsConstants;
import com.bicgraphic.ods.product.exception.ODSProductException;
import com.bicgraphic.ods.product.service.ISalesforceProductService;
import com.google.common.io.CharStreams;

@Service
public class SalesforceProductServiceImpl implements ISalesforceProductService {
	private static final Logger logger = LoggerFactory.getLogger(SalesforceProductServiceImpl.class);
	@Autowired
	SalesForceProperties salesForceProperties;
	String accessToken = null;
	String instanceUrl = null;

	@PostConstruct
	public void init() {
		try {
			JSONParser parser = new JSONParser();
			CloseableHttpClient client = HttpClients.createDefault();
			String baseUrl = salesForceProperties.getOauthUrl();
			HttpPost oauthPost = new HttpPost(baseUrl);

			// The request body must contain these 5 values.
			List<BasicNameValuePair> parametersBody = new ArrayList<BasicNameValuePair>();

			parametersBody.add(new BasicNameValuePair("grant_type", "password"));
			parametersBody.add(new BasicNameValuePair("username", salesForceProperties.getSalesforceUsername()));
			parametersBody.add(new BasicNameValuePair("password",
					salesForceProperties.getSalesforcePassword() + salesForceProperties.getSalesforceSecurityToken()));
			parametersBody.add(new BasicNameValuePair("client_id", salesForceProperties.getClientId()));
			parametersBody.add(new BasicNameValuePair("client_secret", salesForceProperties.getClientSecret()));
			oauthPost.setEntity(new UrlEncodedFormEntity(parametersBody, HTTP.UTF_8));
			// Execute the request.
			HttpResponse response = null;
			response = client.execute(oauthPost);
			@SuppressWarnings("unchecked")
			Map<String, String> oauthLoginResponse = (Map<String, String>) parser
					.parse(EntityUtils.toString(response.getEntity()));
			logger.info("OAuth login response:"+oauthLoginResponse);
			// Get OAUTH Info
			accessToken = oauthLoginResponse.get("access_token");
			instanceUrl = oauthLoginResponse.get("instance_url");
		} catch (Exception e) {
			logger.error("Error in generating accessToken, InstanceUrl from Salesforce Oauth: " + e);
		}

	}

	/**
	 * @param objectName
	 * @param accessToken
	 * @param instanceUrl
	 * @param recordReqJson
	 * @return
	 * @throws ODSCustomerException
	 */
	public String postDataToSF(String objectName, String accessToken, String instanceUrl, String recordReqJson)
			throws ODSProductException {
		HttpClient httpclient = new HttpClient();
		//PostMethod post = new PostMethod(instanceUrl + salesForceProperties.getDataUri() + objectName + "/");
		PostMethod post = new PostMethod(instanceUrl) {
			@Override
			  public String getName() {
			    return "PATCH";
			  }
		};
		post.setRequestHeader("Authorization", "OAuth " + accessToken);
		try {
			post.setRequestEntity(new StringRequestEntity(recordReqJson, "application/json", null));
			httpclient.executeMethod(post);
			String result = null;
			if(post.getResponseBodyAsStream()!=null) {
				try (final Reader reader = new InputStreamReader(post.getResponseBodyAsStream())) {
					result = CharStreams.toString(reader);
				}
			}
			if (post.getStatusCode() == HttpStatus.SC_NO_CONTENT) {
				logger.info("Salesforce record inserted/updated successfully");
				return OdsConstants.PRODUCT_SUCCESS_MSG;
			}
			if (post.getStatusCode() == HttpStatus.SC_CREATED) {
				JSONObject response = new JSONObject(new JSONTokener(result));
				if (response.getBoolean("success")) {
					return OdsConstants.PRODUCT_SUCCESS_MSG;
				}
			} else {
				logger.info("Salesforce response : " + result);
				throw new ODSProductException(OdsConstants.SFDC_INSERTION_FAILED_ORIGINAL_CODE, result);
			}
		} catch (ODSProductException ods) {
			throw ods;
		} catch (Exception e) {
			logger.error("Exception in inserting data to Salesforce :" + e);
			throw new ODSProductException(OdsConstants.SFDC_INSERTION_FAILED_CODE,
					OdsConstants.SFDC_INSERTION_FAILED_MSG);
		} finally {
			post.releaseConnection();
		}

		return null;
	}

	
	public String DeleteDataFromSF(String objectName, String accessToken, String instanceUrl, String productid)
			throws ODSProductException {
		HttpClient httpclient = new HttpClient();
		//org.apache.commons.httpclient.methods.DeleteMethodproductid
		
		DeleteMethod delete = new DeleteMethod(instanceUrl + "/services/data/v43.0/sobjects/" + objectName + "/" + productid );
		delete.setRequestHeader("Authorization", "OAuth " + accessToken);
		try {
			//delete.setRequestEntity(new StringRequestEntity(productid, "application/json", null));
			
			httpclient.executeMethod(delete);
			String result = null;
			try (final Reader reader = new InputStreamReader(delete.getResponseBodyAsStream())) {
				result = CharStreams.toString(reader);
			}
			if (delete.getStatusCode() == HttpStatus.SC_CREATED) {
				JSONObject response = new JSONObject(new JSONTokener(result));
				if (response.getBoolean("success")) {
					return OdsConstants.PRODUCT_SUCCESS;
				}
			} else {
				logger.info("Salesforce response : " + result);
				throw new ODSProductException(OdsConstants.SFDC_INSERTION_FAILED_ORIGINAL_CODE, result);
			}

		}
		catch (ODSProductException ods) {
			throw ods;
		}
		catch (Exception e) {
			logger.error("Exception in inserting data to Salesforce :" + e);
			throw new ODSProductException(OdsConstants.SFDC_INSERTION_FAILED_CODE,
					OdsConstants.SFDC_INSERTION_FAILED_MSG);
		} finally {
			delete.releaseConnection();
		}

		return null;
	}
	public String GetDataFromSF(String objectName, String accessToken, String instanceUrl, String productid)
			throws ODSProductException {
		HttpClient httpclient = new HttpClient();
		//org.apache.commons.httpclient.methods.GetMethod
		
		GetMethod get = new GetMethod(instanceUrl + "/services/data/v43.0/sobjects/" + objectName + "/" + productid );
		get.setRequestHeader("Authorization", "OAuth " + accessToken);
		try {
			//delete.setRequestEntity(new StringRequestEntity(productid, "application/json", null));
			
			httpclient.executeMethod(get);
			String result = null;
			try (final Reader reader = new InputStreamReader(get.getResponseBodyAsStream())) {
				result = CharStreams.toString(reader);
			}
			if (get.getStatusCode() == HttpStatus.SC_CREATED) {
				JSONObject response = new JSONObject(new JSONTokener(result));
				if (response.getBoolean("success")) {
					return OdsConstants.PRODUCT_SUCCESS;
				}
			} else {
				logger.info("Salesforce response : " + result);
				throw new ODSProductException(OdsConstants.SFDC_INSERTION_FAILED_ORIGINAL_CODE, result);
			}

		} 
		catch (ODSProductException ods) {
			throw ods;}
		catch (Exception e) {
			logger.error("Exception in inserting data to Salesforce :" + e);
			throw new ODSProductException(OdsConstants.SFDC_INSERTION_FAILED_CODE,
					OdsConstants.SFDC_INSERTION_FAILED_MSG);
		} finally {
			get.releaseConnection();
		}

		return null;
	}

	/* (non-Javadoc)
	 * @see com.bicgraphic.ods.customer.service.ISalesforceCustomerService#postCustomerMetaDataToSF(com.bicgraphic.ods.customer.beans.Customer)
	 */
	public String postProductMetaDataToSF(Products product) throws ODSProductException {
		try {
			if (accessToken == null || instanceUrl == null) {
				init();
			}
			SalesforceProducts productobj = new SalesforceProducts();
			BeanUtils.copyProperties(productobj, product);
			productobj.setProductId(null);
			JSONObject jsonObj = new JSONObject(productobj);
			String objUrl=instanceUrl + salesForceProperties.getDataUri() +"Product2/Product_ID__c/"+product.getProductId();
			return postDataToSF("Product2", accessToken, objUrl, jsonObj.toString());
		} 
		catch (ODSProductException ods) {
			throw ods;}
		catch (Exception e) {
			logger.error("Exception in inserting data to Salesforce :" + e);
			throw new ODSProductException(OdsConstants.SFDC_INSERTION_FAILED_CODE,
					OdsConstants.SFDC_INSERTION_FAILED_MSG);
		}
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getInstanceUrl() {
		return instanceUrl;
	}

	public void setInstanceUrl(String instanceUrl) {
		this.instanceUrl = instanceUrl;
	}

	@Override
	public String getProduct(String productid) throws ODSProductException {
		try {
			if (accessToken == null || instanceUrl == null) {
				init();
			}
			//SalesforceProducts productobj = new SalesforceProducts();
			//BeanUtils.copyProperties(productobj, product);
			//JSONObject jsonObj = new JSONObject(productobj);
			return GetDataFromSF("Product2", accessToken, instanceUrl, productid);
		} catch (Exception e) {
			logger.error("Exception in inserting data to Salesforce :" + e);
			throw new ODSProductException(OdsConstants.SFDC_INSERTION_FAILED_CODE,
					OdsConstants.SFDC_INSERTION_FAILED_MSG);
		}
	}

	@Override
	public String DeleteProductMetaDataToSF(String productid) throws ODSProductException {
		try {
			if (accessToken == null || instanceUrl == null) {
				init();
			}
			//SalesforceProducts productobj = new SalesforceProducts();
			//BeanUtils.copyProperties(productobj, product);
			//JSONObject jsonObj = new JSONObject(productobj);
			return DeleteDataFromSF("Product2", accessToken, instanceUrl, productid);
		} catch (Exception e) {
			logger.error("Exception in inserting data to Salesforce :" + e);
			throw new ODSProductException(OdsConstants.SFDC_INSERTION_FAILED_CODE,
					OdsConstants.SFDC_INSERTION_FAILED_MSG);
		}
	}

}
