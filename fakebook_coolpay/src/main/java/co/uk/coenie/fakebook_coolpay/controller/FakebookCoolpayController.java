package co.uk.coenie.fakebook_coolpay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import co.uk.coenie.fakebook_coolpay.service.SendMoneyService;
import co.uk.coenie.fakebook_coolpay.service.dto.SendMoneyResponse;

@Controller
public class FakebookCoolpayController {
	private SendMoneyService sendMoneyService;
	
	@Autowired
	public FakebookCoolpayController(SendMoneyService sendMoneyService) {
		this.sendMoneyService = sendMoneyService;
	}
	
	@RequestMapping(value="/send-money",method=RequestMethod.POST)
	public @ResponseBody SendMoneyResponse sendMoney(@RequestBody String jsonString) {
		return null;
	}
	
}
