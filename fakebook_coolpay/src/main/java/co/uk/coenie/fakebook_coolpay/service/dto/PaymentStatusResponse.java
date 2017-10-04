package co.uk.coenie.fakebook_coolpay.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import co.uk.coenie.fakebook_coolpay.client.dto.CoolpayPayment;

public class PaymentStatusResponse {
	@JsonProperty("CoolpayPaymentID")
	private String coolpayPaymentId;
	@JsonProperty("CoolpayRecipientID")
	private String coolpayRecipientId;
	@JsonProperty("Amount")
	private String amount;
	@JsonProperty("Currency")
	private String currency;
	@JsonProperty("status")
	private String status;
	
	public PaymentStatusResponse() {};
	
	public PaymentStatusResponse(CoolpayPayment payment) {
		coolpayPaymentId = payment.getId();
		coolpayRecipientId = payment.getRecipientId();
		amount = payment.getAmount();
		currency = payment.getCurrency();
		status = payment.getStatus();
	}
}
