package com.bicgraphic.ods.product.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:sf_configuration.properties")
public class SalesForceProperties {

	@Value("${salesforce.org.username}")
	private String salesforceUsername;

	@Value("${salesforce.org.password}")
	private String salesforcePassword;

	@Value("${salesforce.org.securityToken}")
	private String salesforceSecurityToken;

	@Value("${salesforce.org.endpoint}")
	private String salesforceEndpoint;

	@Value("${salesforce.clientId}")
	private String clientId;
	@Value("${salesforce.clientSecret}")
	private String clientSecret;
	@Value("${salesforce.oauth.url}")
	private String oauthUrl;

	@Value("${salesforce.data.uri}")
	private String dataUri;
	
	@Value("${salesforce.query.uri}")
	private String queryUri;

	public String getSalesforceUsername() {
		return salesforceUsername;
	}

	public void setSalesforceUsername(String salesforceUsername) {
		this.salesforceUsername = salesforceUsername;
	}

	public String getSalesforcePassword() {
		return salesforcePassword;
	}

	public void setSalesforcePassword(String salesforcePassword) {
		this.salesforcePassword = salesforcePassword;
	}

	public String getSalesforceSecurityToken() {
		return salesforceSecurityToken;
	}

	public void setSalesforceSecurityToken(String salesforceSecurityToken) {
		this.salesforceSecurityToken = salesforceSecurityToken;
	}

	public String getSalesforceEndpoint() {
		return salesforceEndpoint;
	}

	public void setSalesforceEndpoint(String salesforceEndpoint) {
		this.salesforceEndpoint = salesforceEndpoint;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getOauthUrl() {
		return oauthUrl;
	}

	public void setOauthUrl(String oauthUrl) {
		this.oauthUrl = oauthUrl;
	}

	public String getDataUri() {
		return dataUri;
	}

	public void setDataUri(String dataUri) {
		this.dataUri = dataUri;
	}

	public String getQueryUri() {
		return queryUri;
	}

	public void setQueryUri(String queryUri) {
		this.queryUri = queryUri;
	}

}
