package co.uk.coenie.fakebook_coolpay.client.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CoolpayRecipientListResponse {
	@JsonProperty("recipients")
	private List<CoolpayRecipient> recipients;

	public List<CoolpayRecipient> getRecipients(){
		return recipients;
	}
}
