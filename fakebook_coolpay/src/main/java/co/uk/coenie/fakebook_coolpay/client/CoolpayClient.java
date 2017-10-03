package co.uk.coenie.fakebook_coolpay.client;

import co.uk.coenie.fakebook_coolpay.client.dto.CoolpayPaymentListResponse;
import co.uk.coenie.fakebook_coolpay.client.dto.CoolpayPaymentRequest;
import co.uk.coenie.fakebook_coolpay.client.dto.CoolpayPaymentResponse;
import co.uk.coenie.fakebook_coolpay.client.dto.CoolpayRecipientRequest;
import co.uk.coenie.fakebook_coolpay.client.dto.CoolpayRecipient;

public interface CoolpayClient {
	
	/**
	 * Logs into API and adds token to restTemplate interceptors
	 * 
	 * @return API Token
	 */
	String login();
	
	/**
	 * Checks if supplied recipient exists and adds recipient if not.
	 * Returns existing or new recipient ID from API
	 * 
	 * @param request
	 * @return
	 * @throws TooManyResultsException If more than one recipient is returned
	 */
	CoolpayRecipient checkAndCreateRecipient(CoolpayRecipientRequest request) throws TooManyResultsException;
	
	/**
	 * Makes payment to specified recipient
	 * 
	 * @param request
	 * @return
	 */
	CoolpayPaymentResponse makePayment(CoolpayPaymentRequest request);
	
	/**
	 * Returns all payments from API
	 * 
	 * @return
	 */
	CoolpayPaymentListResponse listPayments();

	
}
