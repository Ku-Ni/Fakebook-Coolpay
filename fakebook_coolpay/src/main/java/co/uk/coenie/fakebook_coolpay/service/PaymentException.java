package co.uk.coenie.fakebook_coolpay.service;

public class PaymentException extends Exception {
	public PaymentException (String message) {
		super(message);
	}
	
	public PaymentException (String message, Exception e) {
		super(message, e);
	}
}
