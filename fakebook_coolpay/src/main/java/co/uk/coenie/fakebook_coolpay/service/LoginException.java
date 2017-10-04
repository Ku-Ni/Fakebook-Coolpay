/**
 * 
 */
package co.uk.coenie.fakebook_coolpay.service;

/**
 * @author Coenie
 *
 */
public class LoginException extends Exception {
	public LoginException(String message) {
		super(message);
	}
	
	public LoginException(String message, Exception e) {
		super(message, e);
	}
}
