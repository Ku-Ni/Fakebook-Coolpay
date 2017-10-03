package co.uk.coenie.fakebook_coolpay.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CoolpayRecipient {
	@JsonProperty("id")
	private String id;
	@JsonProperty("name")
	private String name;
	
	
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	
}
