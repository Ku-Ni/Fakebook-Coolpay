package co.uk.coenie.fakebook_coolpay.service;

public class InvalidRequestException extends Exception {
	public InvalidRequestException (String message) {
		super(message);
	}
	
	public InvalidRequestException (String message, Exception e) {
		super(message, e);
	}
}
