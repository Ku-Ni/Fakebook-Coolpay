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
	
	
	public SendMoneyResponse(SendMoneyRequest request) {
		amount = String.valueOf(request.getAmount());
		currency = request.getCurrency();
		coolpayRecipientId = request.getRecipientId();
		recipientName = request.getRecipientName();
	}
	

	public void setCoolpayPaymentId(String coolpayPaymentId) {
		this.coolpayPaymentId = coolpayPaymentId;
	}
	public String getCoolpayPaymentId() {
		return coolpayPaymentId;
	}
	

	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return status;
	}


	
		
}
