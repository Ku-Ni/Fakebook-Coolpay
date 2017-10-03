package co.uk.coenie.fakebook_coolpay.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CoolpayRecipientRequest {

	@JsonProperty("recipient")
	private Recipient recipient;

	
	public CoolpayRecipientRequest() {
		recipient = new Recipient();
	}

	
	public CoolpayRecipientRequest(String recipientName) {
		this();
		recipient.setName(recipientName);
	}

	@JsonIgnore
	public String getName() {
		return recipient.getName();
	}

	public void setName(String recipientName) {
		recipient.setName(recipientName);
	}
	
	
	public class Recipient{
		@JsonProperty("name")
		private String name;
		
		public void setName(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
		
	}
}
