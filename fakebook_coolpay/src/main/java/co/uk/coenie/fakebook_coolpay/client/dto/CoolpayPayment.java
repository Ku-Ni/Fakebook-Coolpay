package co.uk.coenie.fakebook_coolpay.client.dto;

import java.util.Objects;

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

	
	public CoolpayPayment() {}
	
	
	public CoolpayPayment(String id, String recipientId){
		this.id = id;
		this.recipientId = recipientId;
	}

	
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


	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof CoolpayPayment)) {
			return false;
		}
		CoolpayPayment castOther = (CoolpayPayment) other;
		return Objects.equals(id, castOther.id) && Objects.equals(recipientId, castOther.recipientId);
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, recipientId);
	}
}