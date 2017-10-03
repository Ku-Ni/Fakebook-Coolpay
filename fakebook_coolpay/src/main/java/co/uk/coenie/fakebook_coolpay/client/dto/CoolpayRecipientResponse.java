package co.uk.coenie.fakebook_coolpay.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CoolpayRecipientResponse {
	@JsonProperty("recipient")
	private CoolpayRecipient recipient;
	
	public CoolpayRecipient getRecipient() {
		return recipient;
	}
}
