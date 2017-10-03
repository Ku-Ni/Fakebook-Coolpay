package co.uk.coenie.fakebook_coolpay.client.dto;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CoolpayDataTransferObjectsTester {
	ObjectMapper objectMapper;
	
	@Before
	public void before() {
		objectMapper = new ObjectMapper();
	}

	@Test
	public final void testCoolpayLoginRequest() throws JsonProcessingException {
		String expected = "{\"username\":\"your_username\",\"apikey\":\"5up3r$ecretKey!\"}";
		CoolpayLoginRequest test = new CoolpayLoginRequest("your_username", "5up3r$ecretKey!");
		
		assertEquals(expected, objectMapper.writeValueAsString(test));
	}

	@Test
	public final void testCoolpayLoginResponse() throws JsonProcessingException {
		String expected = "{\"token\":\"aff06fec-e041-4994-849e-223f0569e0bc\"}";
		CoolpayLoginResponse test = new CoolpayLoginResponse();
		test.setApiToken("aff06fec-e041-4994-849e-223f0569e0bc");
		
		assertEquals(expected, objectMapper.writeValueAsString(test));
	}

	@Test
	public final void testCoolpayPaymentListResponse() throws IOException {
		String expected = "{\"payments\":[{\"id\":\"31db334f-9ac0-42cb-804b-09b2f899d4d2\",\"amount\":\"10.50\",\"currency\":\"GBP\",\"recipient_id\":\"6e7b146e-5957-11e6-8b77-86f30ca893d3\",\"status\":\"paid\"}]}";
		CoolpayPaymentListResponse test = objectMapper.readValue(expected, CoolpayPaymentListResponse.class);
		
		assertEquals(expected, objectMapper.writeValueAsString(test));
	}

	@Test
	public final void testCoolpayPaymentRequest() throws IOException {
		String expected = "{\"payment\":{\"amount\":10.5,\"currency\":\"GBP\",\"recipient_id\":\"6e7b146e-5957-11e6-8b77-86f30ca893d3\"}}";
		CoolpayPaymentRequest test = objectMapper.readValue(expected, CoolpayPaymentRequest.class);
		
		assertEquals(expected, objectMapper.writeValueAsString(test));
	}

	@Test
	public final void testCoolpayPaymentResponse() throws IOException {
		String expected = "{\"payment\":{\"id\":\"31db334f-9ac0-42cb-804b-09b2f899d4d2\",\"amount\":\"10.5\",\"currency\":\"GBP\",\"recipient_id\":\"6e7b146e-5957-11e6-8b77-86f30ca893d3\",\"status\":\"processing\"}}";
		CoolpayPaymentResponse test = objectMapper.readValue(expected, CoolpayPaymentResponse.class);
		
		assertEquals(expected, objectMapper.writeValueAsString(test));
	}

	@Test
	public final void testCoolpayRecipientListResponse() throws IOException {
		String expected = "{\"recipients\":[{\"id\":\"6e7b4cea-5957-11e6-8b77-86f30ca893d3\",\"name\":\"Jake McFriend\"}]}";
		CoolpayRecipientListResponse test = objectMapper.readValue(expected, CoolpayRecipientListResponse.class);
		
		assertEquals(expected, objectMapper.writeValueAsString(test));
	}
	
	@Test
	public final void testCoolpayRecipientResponse() throws IOException {
		String expected = "{\"recipient\":{\"id\":\"e9a0336b-d81d-4009-9ad1-8fa1eb43418c\",\"name\":\"Jake McFriend\"}}";
		CoolpayRecipientResponse test = objectMapper.readValue(expected, CoolpayRecipientResponse.class);
		
		assertEquals(expected, objectMapper.writeValueAsString(test));
	}
	
	@Test
	public final void testCoolpayRecipientRequest() throws IOException {
		String expected = "{\"recipient\":{\"name\":\"Jake McFriend\"}}";
		CoolpayRecipientRequest test = objectMapper.readValue(expected, CoolpayRecipientRequest.class);
		
		assertEquals(expected, objectMapper.writeValueAsString(test));
	}
}
