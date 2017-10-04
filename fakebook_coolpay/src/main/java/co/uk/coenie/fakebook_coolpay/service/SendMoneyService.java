package co.uk.coenie.fakebook_coolpay.service;

import co.uk.coenie.fakebook_coolpay.service.dto.PaymentStatusRequest;
import co.uk.coenie.fakebook_coolpay.service.dto.PaymentStatusResponse;
import co.uk.coenie.fakebook_coolpay.service.dto.SendMoneyRequest;
import co.uk.coenie.fakebook_coolpay.service.dto.SendMoneyResponse;

public interface SendMoneyService {
	
	/**
	 * Calls Coolpay API to make payment for specified amount to recipient
	 * 
	 * @param request
	 * @return
	 * @throws LoginException
	 * @throws RecipientException
	 * @throws PaymentException
	 * @throws InvalidRequestException
	 */
	public SendMoneyResponse sendMoney(SendMoneyRequest request) 
			throws LoginException, RecipientException, PaymentException, InvalidRequestException;

	
	/**
	 * Retrieves latest payment status from Coolpay API
	 * 
	 * @param request
	 * @return
	 * @throws LoginException
	 */
	public PaymentStatusResponse retrievePaymentStatus(PaymentStatusRequest request) throws LoginException;


}
