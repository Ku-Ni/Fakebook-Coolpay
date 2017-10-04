package co.uk.coenie.fakebook_coolpay.service;

import co.uk.coenie.fakebook_coolpay.service.dto.PaymentStatusRequest;
import co.uk.coenie.fakebook_coolpay.service.dto.PaymentStatusResponse;
import co.uk.coenie.fakebook_coolpay.service.dto.SendMoneyRequest;
import co.uk.coenie.fakebook_coolpay.service.dto.SendMoneyResponse;

public interface SendMoneyService {
	
	public SendMoneyResponse sendMoney(SendMoneyRequest request) 
			throws LoginException, RecipientException, PaymentException, InvalidRequestException;

	
	public PaymentStatusResponse retrievePaymentStatus(PaymentStatusRequest request) throws LoginException;


}
