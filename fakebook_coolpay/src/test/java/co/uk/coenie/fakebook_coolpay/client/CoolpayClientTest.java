package co.uk.coenie.fakebook_coolpay.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.uk.coenie.fakebook_coolpay.client.dto.CoolpayPaymentRequest;
import co.uk.coenie.fakebook_coolpay.client.dto.CoolpayPaymentResponse;
import co.uk.coenie.fakebook_coolpay.client.dto.CoolpayRecipient;
import co.uk.coenie.fakebook_coolpay.client.dto.CoolpayRecipientRequest;
import co.uk.coenie.fakebook_coolpay.config.TestSpringConfig;

public class CoolpayClientTest {
	Logger logger = LogManager.getLogger(CoolpayClientTest.class);
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
		assertTrue(StringUtils.hasText(coolpayClient.login()));
	}

	@Test
	public final void testCheckAndCreateRecipient() throws Exception{
		String expected = "{\"id\":\"7c096bee-3770-484e-9452-1fb2d1f105e1\",\"name\":\"Morty Smith\"}";
		CoolpayRecipientRequest request = new CoolpayRecipientRequest("Morty Smith");
		coolpayClient.login();
		try {
			CoolpayRecipient response = coolpayClient.checkAndCreateRecipient(request);

			assertEquals(expected, new ObjectMapper().writeValueAsString(response));
		} catch (Exception e) {
			logger.error("{}:{}", e.getClass(), e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}

	@Test
	public final void testMakePayment() {
		coolpayClient.login();

		CoolpayPaymentRequest request = new CoolpayPaymentRequest();
		request.setAmount(10);
		request.setCurrency("GBP");
		request.setRecipientID("7c096bee-3770-484e-9452-1fb2d1f105e1");
		CoolpayPaymentResponse response = coolpayClient.makePayment(request);

		assertEquals("Checking Amount", Double.valueOf(10), Double.valueOf(response.getPayment().getAmount()));
		assertEquals("Checking Currency", "GBP", response.getPayment().getCurrency());
		assertEquals("Checking RecipientID","7c096bee-3770-484e-9452-1fb2d1f105e1",response.getPayment().getRecipientId());
	}

	@Test
	public final void testListPayments() {
		fail("Not yet implemented"); // TODO
	}

}
