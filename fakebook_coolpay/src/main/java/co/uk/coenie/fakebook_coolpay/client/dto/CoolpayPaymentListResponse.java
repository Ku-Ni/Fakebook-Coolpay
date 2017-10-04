package co.uk.coenie.fakebook_coolpay.client.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CoolpayPaymentListResponse {
	@JsonProperty("payments")
	private List<CoolpayPayment> payments;
	
	public List<CoolpayPayment> getPayments(){
		return payments;
	}
}
