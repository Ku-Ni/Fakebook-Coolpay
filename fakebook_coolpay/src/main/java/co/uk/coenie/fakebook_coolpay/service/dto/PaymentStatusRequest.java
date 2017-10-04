package co.uk.coenie.fakebook_coolpay.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentStatusRequest {
	@JsonProperty("CoolpayPaymentID")
	private String coolpayPaymentId;
	@JsonProperty("CoolpayRecipientID")
	private String coolpayRecipientId;

	
	public String getCoolpayPaymentId() {
		return coolpayPaymentId;
	}
	public String getCoolpayRecipientId() {
		return coolpayRecipientId;
	}
	
}
