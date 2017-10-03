package co.uk.coenie.fakebook_coolpay.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CoolpayPaymentRequest {

	@JsonProperty("payment")
	private RequestPayment payment;

	public CoolpayPaymentRequest() {
		payment = new RequestPayment();
	}
	

	public void setAmount(double amount) {
		payment.amount = amount;
	}
	
	public void setCurrency(String currency) {
		payment.currency = currency;
	}
	
	public void setRecipientID(String recipientID) {
		payment.recipientID = recipientID;
	}

	
	public class RequestPayment {
		@JsonProperty("amount")
		private double amount;
		@JsonProperty("currency")
		private String currency;
		@JsonProperty("recipient_id")
		private String recipientID;
	}
}
