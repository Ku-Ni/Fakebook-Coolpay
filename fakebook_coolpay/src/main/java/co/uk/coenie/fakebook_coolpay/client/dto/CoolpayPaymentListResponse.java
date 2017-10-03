package co.uk.coenie.fakebook_coolpay.client.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CoolpayPaymentListResponse {
	@JsonProperty("payments")
	private List<CoolpayPaymentResponse> payments;
	
	public List<CoolpayPaymentResponse> getPayments(){
		return payments;
	}
}
