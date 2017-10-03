package co.uk.coenie.fakebook_coolpay.client;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.uk.coenie.fakebook_coolpay.client.dto.CoolpayRecipientRequest;
import co.uk.coenie.fakebook_coolpay.client.dto.CoolpayRecipientResponse;
import co.uk.coenie.fakebook_coolpay.config.TestSpringConfig;

public class CoolpayClientTest {
	AnnotationConfigApplicationContext context;
	CoolpayClient coolpayClient;
	
	@Before
	public void before() {
		context = new AnnotationConfigApplicationContext(TestSpringConfig.class);
		coolpayClient = context.getBean(CoolpayClientImpl.class);
	}
	
	@After
	public void after() {
		context.close();
	}

	@Test
	public final void testLogin() {
		assertTrue(coolpayClient.login());
	}

	@Test
	public final void testCheckAndCreateRecipient() throws TooManyResultsException, JsonProcessingException {
		CoolpayRecipientRequest request = new CoolpayRecipientRequest();
		request.setName("Rick Sanchez");
		coolpayClient.login();
		CoolpayRecipientResponse response = coolpayClient.checkAndCreateRecipient(request);
		
		System.out.println(new ObjectMapper().writeValueAsString(response));
	}

//	@Test
//	public final void testMakePayment() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	@Test
//	public final void testListPayments() {
//		fail("Not yet implemented"); // TODO
//	}

}
