package co.uk.coenie.fakebook_coolpay.service;

import co.uk.coenie.fakebook_coolpay.service.dto.SendMoneyRequest;
import co.uk.coenie.fakebook_coolpay.service.dto.SendMoneyResponse;

public interface SendMoneyService {
	
	public SendMoneyResponse sendMoney(SendMoneyRequest request);

}
