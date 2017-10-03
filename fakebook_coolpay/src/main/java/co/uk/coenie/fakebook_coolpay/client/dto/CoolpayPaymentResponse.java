package co.uk.coenie.fakebook_coolpay.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CoolpayPaymentResponse {

	@JsonProperty("payment")
	private CoolpayPayment payment;

	public CoolpayPayment getPayment() {
		return payment;
	}

}
