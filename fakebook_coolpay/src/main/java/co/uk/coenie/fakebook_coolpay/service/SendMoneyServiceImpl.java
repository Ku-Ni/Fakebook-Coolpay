package co.uk.coenie.fakebook_coolpay.service;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import co.uk.coenie.fakebook_coolpay.client.CoolpayClient;
import co.uk.coenie.fakebook_coolpay.client.dto.CoolpayPayment;
import co.uk.coenie.fakebook_coolpay.client.dto.CoolpayPaymentRequest;
import co.uk.coenie.fakebook_coolpay.client.dto.CoolpayPaymentResponse;
import co.uk.coenie.fakebook_coolpay.client.dto.CoolpayRecipientRequest;
import co.uk.coenie.fakebook_coolpay.service.dto.PaymentStatusRequest;
import co.uk.coenie.fakebook_coolpay.service.dto.PaymentStatusResponse;
import co.uk.coenie.fakebook_coolpay.service.dto.SendMoneyRequest;
import co.uk.coenie.fakebook_coolpay.service.dto.SendMoneyResponse;

@Service
public class SendMoneyServiceImpl implements SendMoneyService{
	private Logger logger = LogManager.getLogger(SendMoneyServiceImpl.class);
	private CoolpayClient coolpayApi;

	@Autowired
	public SendMoneyServiceImpl(CoolpayClient coolpayApi) {
		this.coolpayApi = coolpayApi;
	}

	public SendMoneyResponse sendMoney(SendMoneyRequest request) throws LoginException, RecipientException, PaymentException, InvalidRequestException {
		// TODO: validate request
		validate(request);

		// Log client in
		clientLogin();

		// Retrieve Recipient ID from API
		request.setRecipientId(checkAndCreateRecipient(request.getRecipientName()));

		CoolpayPaymentResponse paymentResponse = makePayment(request);
		SendMoneyResponse sendMoneyResponse = new SendMoneyResponse(request);
		sendMoneyResponse.setCoolpayPaymentId(paymentResponse.getPayment().getId());
		sendMoneyResponse.setStatus(paymentResponse.getPayment().getStatus());

		//	Check whether a payment was successful
		if (!"paid".equals(sendMoneyResponse.getStatus())) //TODO: create Status Enum
			sendMoneyResponse.setStatus(retrieveUpdatedPayment(paymentResponse.getPayment()).getStatus());
		
		return sendMoneyResponse;
	}
	

	/**
	 * Validates the incoming request values
	 * 
	 * @param request
	 * @throws InvalidRequestException
	 */
	private void validate(SendMoneyRequest request) throws InvalidRequestException {
		if (request.getAmount()<=0)
			throw new InvalidRequestException("Invalid amount ["+request.getAmount()+"] supplied");
		
		// TODO: validate currency against valid currency list
	
	}

	
	/**
	 * Attempts to log into client API 
	 * 
	 * @throws LoginException
	 */
	private void clientLogin() throws LoginException {
		try {
			if(!StringUtils.hasText(coolpayApi.login()))
				throw new IOException("Empty or Null API Token returned");
		} catch (Exception e) {
			logger.error("{}:{}",e.getClass(), e.getMessage());
			throw new LoginException(e.getMessage(), e);
		}
	}


	/**
	 * Search API for recipient and adds if doesn't exist.
	 * Returns recipient ID from API
	 * 
	 * @param recipientName
	 * @return API ID for supplied Recipient
	 * @throws RecipientException
	 */
	private String checkAndCreateRecipient(String recipientName) throws RecipientException {
		try {
			return coolpayApi.checkAndCreateRecipient(new CoolpayRecipientRequest(recipientName)).getId();
		} catch (Exception e) {
			logger.error("{}:{}",e.getClass(), e.getMessage());
			throw new RecipientException(e.getMessage(), e);
		}
	}


	/**
	 * Makes requested payment to recipient, returns API response
	 * 
	 * @param request
	 * @return
	 * @throws PaymentException
	 */
	private CoolpayPaymentResponse makePayment(SendMoneyRequest request) throws PaymentException {
		try {
			CoolpayPaymentRequest coolpayPaymentRequest = new CoolpayPaymentRequest();
			coolpayPaymentRequest.setAmount(request.getAmount());
			coolpayPaymentRequest.setCurrency(request.getCurrency());
			coolpayPaymentRequest.setRecipientID(request.getRecipientId());

			return coolpayApi.makePayment(coolpayPaymentRequest);
		} catch (Exception e) {
			logger.error("{}:{}",e.getClass(), e.getMessage());
			throw new PaymentException(e.getMessage(),e);
		}

	}
	
	
	/**
	 * Retrieves most recent paymentStatus from API for supplied payment
	 * 
	 * @param coolpayPayment
	 * @return
	 */
	private CoolpayPayment retrieveUpdatedPayment(CoolpayPayment coolpayPayment) {
		List<CoolpayPayment> payments = coolpayApi.listPayments().getPayments();
		
		return payments.get(payments.indexOf(coolpayPayment));
	}

	
	@Override
	public PaymentStatusResponse retrievePaymentStatus(PaymentStatusRequest request) throws LoginException {
		clientLogin();
		
		// TODO: add checks for invalid payment/recipient IDs
		return new PaymentStatusResponse(retrieveUpdatedPayment(new CoolpayPayment(request.getCoolpayPaymentId(), request.getCoolpayRecipientId())));
	}

}
