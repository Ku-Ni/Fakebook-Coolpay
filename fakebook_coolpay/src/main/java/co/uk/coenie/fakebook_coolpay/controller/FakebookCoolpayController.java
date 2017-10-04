package co.uk.coenie.fakebook_coolpay.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.uk.coenie.fakebook_coolpay.service.InvalidRequestException;
import co.uk.coenie.fakebook_coolpay.service.LoginException;
import co.uk.coenie.fakebook_coolpay.service.PaymentException;
import co.uk.coenie.fakebook_coolpay.service.RecipientException;
import co.uk.coenie.fakebook_coolpay.service.SendMoneyService;
import co.uk.coenie.fakebook_coolpay.service.dto.PaymentStatusRequest;
import co.uk.coenie.fakebook_coolpay.service.dto.PaymentStatusResponse;
import co.uk.coenie.fakebook_coolpay.service.dto.SendMoneyRequest;
import co.uk.coenie.fakebook_coolpay.service.dto.SendMoneyResponse;

@Controller
public class FakebookCoolpayController {
	private SendMoneyService sendMoneyService;
	private ObjectMapper objectMapper;

	@Autowired
	public FakebookCoolpayController(SendMoneyService sendMoneyService, ObjectMapper objectMapper) {
		this.sendMoneyService = sendMoneyService;
		this.objectMapper = objectMapper;
	}



	/**
	 * Sends money to recipient takes json input from request body
	 * 
	 * @param jsonString Keys: RecipientName, Currency, Amount
	 * @return
	 */
	@RequestMapping(value="/send-money",method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<SendMoneyResponse> sendMoney(@RequestBody String jsonString) {

		try {
			SendMoneyRequest request = objectMapper.readValue(jsonString, SendMoneyRequest.class);

			try {
				SendMoneyResponse response = sendMoneyService.sendMoney(request);
				return new ResponseEntity<SendMoneyResponse>(response, HttpStatus.CREATED);
			} catch (LoginException e) {
				return new ResponseEntity<SendMoneyResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
			} catch (RecipientException e) {
				return new ResponseEntity<SendMoneyResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
			} catch (PaymentException e) {
				return new ResponseEntity<SendMoneyResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
			} catch (InvalidRequestException e) {
				return new ResponseEntity<SendMoneyResponse>(HttpStatus.BAD_REQUEST);
			} catch (Exception e) {
				return new ResponseEntity<SendMoneyResponse>(HttpStatus.INTERNAL_SERVER_ERROR);				
			}
		} catch (IOException e) {
			return new ResponseEntity<SendMoneyResponse>(HttpStatus.BAD_REQUEST);
		}
	}


	@RequestMapping(value="/payment-status",method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<PaymentStatusResponse> paymentStatus(@RequestBody String jsonString) {

		try {
			PaymentStatusRequest request = objectMapper.readValue(jsonString, PaymentStatusRequest.class);

			try {
				PaymentStatusResponse response = sendMoneyService.retrievePaymentStatus(request);
				return new ResponseEntity<PaymentStatusResponse>(response, HttpStatus.OK);
			} catch (LoginException e) {
				return new ResponseEntity<PaymentStatusResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
			} catch (Exception e) {
				return new ResponseEntity<PaymentStatusResponse>(HttpStatus.INTERNAL_SERVER_ERROR);				
			}
		} catch (IOException e) {
			return new ResponseEntity<PaymentStatusResponse>(HttpStatus.BAD_REQUEST);
		}
	}

}
