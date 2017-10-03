package co.uk.coenie.fakebook_coolpay.service;

import org.springframework.stereotype.Service;

import co.uk.coenie.fakebook_coolpay.client.CoolpayClient;
import co.uk.coenie.fakebook_coolpay.service.dto.SendMoneyRequest;
import co.uk.coenie.fakebook_coolpay.service.dto.SendMoneyResponse;

@Service
public class SendMoneyServiceImpl implements SendMoneyService{
//	Authenticate to Coolpay API
//	Add recipients
//	Send them money
//	Check whether a payment was successful
	private CoolpayClient coolpayApi;

	public SendMoneyResponse sendMoney(SendMoneyRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
