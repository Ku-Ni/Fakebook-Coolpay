package co.uk.coenie.fakebook_coolpay.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SendMoneyResponse {
	@JsonProperty("CoolpayPaymentID")
	private String coolpayPaymentId;
	@JsonProperty("CoolpayRecipientID")
	private String coolpayRecipientId;
	@JsonProperty("RecipientName")
	private String recipientName;
	@JsonProperty("Amount")
	private String amount;
	@JsonProperty("Currency")
	private String currency;
	@JsonProperty("status")
	private String status;
}
