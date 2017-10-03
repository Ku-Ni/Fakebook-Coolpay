package co.uk.coenie.fakebook_coolpay.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CoolpayPayment {
	@JsonProperty("id")
	private String id;
	@JsonProperty("amount")
	private String amount;
	@JsonProperty("currency")
	private String currency;
	@JsonProperty("recipient_id")
	private String recipientId;
	@JsonProperty("status")
	private String status;
	
	
	public String getId() {
		return id;
	}
	public String getAmount() {
		return amount;
	}
	public String getCurrency() {
		return currency;
	}
	public String getRecipientId() {
		return recipientId;
	}
	public String getStatus() {
		return status;
	}

	
}