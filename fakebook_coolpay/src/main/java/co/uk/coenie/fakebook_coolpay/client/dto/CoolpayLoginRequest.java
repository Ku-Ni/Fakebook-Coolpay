package co.uk.coenie.fakebook_coolpay.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CoolpayLoginRequest {
	@JsonProperty("username")
	private String userName;
	@JsonProperty("apikey")
	private String apiKey;
	
	
	public CoolpayLoginRequest(String userName, String apiKey) {
		this.userName = userName;
		this.apiKey = apiKey;
	}
}
