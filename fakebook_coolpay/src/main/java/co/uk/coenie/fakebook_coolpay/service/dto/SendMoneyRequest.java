package co.uk.coenie.fakebook_coolpay.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SendMoneyRequest {
	@JsonProperty("RecipientName")
	private String recipientName;
	@JsonProperty("Currency")
	private String currency;
	@JsonProperty("Amount")
	private double amount;
	
	private String recipientId;

	
	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getRecipientId() {
		return recipientId;
	}

	public void setRecipientId(String recipientId) {
		this.recipientId = recipientId;
	}
	
	
}
