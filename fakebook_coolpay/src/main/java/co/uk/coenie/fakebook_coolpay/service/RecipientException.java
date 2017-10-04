package co.uk.coenie.fakebook_coolpay.service;

public class RecipientException extends Exception {
	public RecipientException(String message) {
		super(message);
	}
	
	public RecipientException(String message, Exception e) {
		super(message, e);
	}
}
