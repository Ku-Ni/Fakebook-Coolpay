package co.uk.coenie.fakebook_coolpay.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.uk.coenie.fakebook_coolpay.client.CoolpayClient;
import co.uk.coenie.fakebook_coolpay.client.dto.CoolpayPaymentListResponse;
import co.uk.coenie.fakebook_coolpay.client.dto.CoolpayPaymentRequest;
import co.uk.coenie.fakebook_coolpay.client.dto.CoolpayPaymentResponse;
import co.uk.coenie.fakebook_coolpay.client.dto.CoolpayRecipient;
import co.uk.coenie.fakebook_coolpay.client.dto.CoolpayRecipientRequest;
import co.uk.coenie.fakebook_coolpay.service.dto.PaymentStatusRequest;
import co.uk.coenie.fakebook_coolpay.service.dto.PaymentStatusResponse;
import co.uk.coenie.fakebook_coolpay.service.dto.SendMoneyRequest;
import co.uk.coenie.fakebook_coolpay.service.dto.SendMoneyResponse;

public class SendMoneyServiceImplTest {
	ObjectMapper objectMapper;

	@Mock
	CoolpayClient mockClient; 

	@Rule public MockitoRule mockitoRule = MockitoJUnit.rule(); 

	@Before
	public void before() throws Exception {
		objectMapper = new ObjectMapper();

		// Setting up Mock responses
		String coolpayRecipient = "{\"id\":\"6e7b146e-5957-11e6-8b77-86f30ca893d3\",\"name\":\"BoJack Horseman\"}";
		String coolpayPaymentResponse = "{\"payment\":{\"id\":\"31db334f-9ac0-42cb-804b-09b2f899d4d2\",\"amount\":\"10.5\",\"currency\":\"GBP\",\"recipient_id\":\"6e7b146e-5957-11e6-8b77-86f30ca893d3\",\"status\":\"processing\"}}";
		String coolpayPaymentListResponse = "{\"payments\":[{\"id\":\"31db334f-9ac0-42cb-804b-09b2f899d4d2\",\"amount\":\"10.50\",\"currency\":\"GBP\",\"recipient_id\":\"6e7b146e-5957-11e6-8b77-86f30ca893d3\",\"status\":\"paid\"}]}";
		when(mockClient.login()).thenReturn("aff06fec-e041-4994-849e-223f0569e0bc");
		when(mockClient.checkAndCreateRecipient(any(CoolpayRecipientRequest.class))).thenReturn(objectMapper.readValue(coolpayRecipient, CoolpayRecipient.class));
		when(mockClient.makePayment(any(CoolpayPaymentRequest.class))).thenReturn(objectMapper.readValue(coolpayPaymentResponse, CoolpayPaymentResponse.class));
		when(mockClient.listPayments()).thenReturn(objectMapper.readValue(coolpayPaymentListResponse, CoolpayPaymentListResponse.class));

	}


	@Test
	public final void testSendMoney() throws Exception {
		SendMoneyServiceImpl service = new SendMoneyServiceImpl(mockClient);
		String testRequestJson = "{\"RecipientName\":\"BoJack Horseman\",\"Currency\":\"USD\",\"Amount\":50.50}";
		String expectedResponse = "{\"CoolpayPaymentID\":\"31db334f-9ac0-42cb-804b-09b2f899d4d2\",\"CoolpayRecipientID\":\"6e7b146e-5957-11e6-8b77-86f30ca893d3\",\"RecipientName\":\"BoJack Horseman\",\"Amount\":\"50.5\",\"Currency\":\"USD\",\"status\":\"paid\"}";
		SendMoneyRequest request = objectMapper.readValue(testRequestJson, SendMoneyRequest.class);

		SendMoneyResponse response = service.sendMoney(request);
		assertEquals(expectedResponse, objectMapper.writeValueAsString(response));
	}


	@Test(expected = InvalidRequestException.class)
	public final void testSendInvalidMoney() throws Exception {
		SendMoneyServiceImpl service = new SendMoneyServiceImpl(mockClient);
		String testRequestJson = "{\"RecipientName\":\"BoJack Horseman\",\"Currency\":\"USD\",\"Amount\":0}";
		SendMoneyRequest request = objectMapper.readValue(testRequestJson, SendMoneyRequest.class);

		// should throw InvalidRequestException
		service.sendMoney(request);
	}


	@Test
	public final void testRetrievePaymentStatus() throws Exception{
		SendMoneyServiceImpl service = new SendMoneyServiceImpl(mockClient);
		String requestString = "{\"CoolpayPaymentID\":\"31db334f-9ac0-42cb-804b-09b2f899d4d2\",\"CoolpayRecipientID\":\"6e7b146e-5957-11e6-8b77-86f30ca893d3\"}";
		String expectedResponse = "{\"CoolpayPaymentID\":\"31db334f-9ac0-42cb-804b-09b2f899d4d2\",\"CoolpayRecipientID\":\"6e7b146e-5957-11e6-8b77-86f30ca893d3\",\"Amount\":\"10.50\",\"Currency\":\"GBP\",\"status\":\"paid\"}";
		PaymentStatusRequest request = objectMapper.readValue(requestString, PaymentStatusRequest.class);

		PaymentStatusResponse response = service.retrievePaymentStatus(request);
		assertEquals(expectedResponse, objectMapper.writeValueAsString(response));
	}

}
