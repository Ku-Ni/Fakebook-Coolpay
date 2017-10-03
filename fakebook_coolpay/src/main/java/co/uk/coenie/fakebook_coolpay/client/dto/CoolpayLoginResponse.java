package co.uk.coenie.fakebook_coolpay.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CoolpayLoginResponse {
	@JsonProperty("token")
	private String apiToken;
	
	public String getApiToken() {
		return apiToken;
	}
	
	public void setApiToken(String apiToken) {
		this.apiToken=apiToken;
	}
}
