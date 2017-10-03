package co.uk.coenie.fakebook_coolpay.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import co.uk.coenie.fakebook_coolpay.client.dto.CoolpayLoginRequest;
import co.uk.coenie.fakebook_coolpay.client.dto.CoolpayLoginResponse;
import co.uk.coenie.fakebook_coolpay.client.dto.CoolpayPaymentListResponse;
import co.uk.coenie.fakebook_coolpay.client.dto.CoolpayPaymentRequest;
import co.uk.coenie.fakebook_coolpay.client.dto.CoolpayPaymentResponse;
import co.uk.coenie.fakebook_coolpay.client.dto.CoolpayRecipient;
import co.uk.coenie.fakebook_coolpay.client.dto.CoolpayRecipientListResponse;
import co.uk.coenie.fakebook_coolpay.client.dto.CoolpayRecipientRequest;
import co.uk.coenie.fakebook_coolpay.client.dto.CoolpayRecipientResponse;

@Service("CoolpayClient")
public class CoolpayClientImpl implements CoolpayClient {
	Logger logger = LogManager.getLogger(CoolpayClientImpl.class);
	@Autowired
	private RestTemplate restTemplate;

	@Value("${api.username}")
	private String apiUserName;
	@Value("${api.key}")
	private String apiKey;

	@Value("${api.url.login}")
	private String loginUrlString;
	@Value("${api.url.searchRecipient}")
	private String searchRecipientsUrl;
	@Value("${api.url.createRecipient}")
	private String createRecipientUrl;
	@Value("${api.url.createPayment}")
	private String createPaymentUrl;
	@Value("${api.url.listPayments}")
	private String listPaymentUrl;


	@Override
	public String login() {
		String apiToken = restTemplate.postForObject(loginUrlString, new CoolpayLoginRequest(apiUserName, apiKey), CoolpayLoginResponse.class).getApiToken();
		setRestTemplateHttpRequestInterceptor(apiToken);
		
		return apiToken;
	}


	private void setRestTemplateHttpRequestInterceptor(String apiToken) {
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
		interceptors.add(new BearerAuthInterceptor(apiToken));
		restTemplate.setInterceptors(interceptors);
	}


	@Override
	public CoolpayRecipient checkAndCreateRecipient(CoolpayRecipientRequest request) throws TooManyResultsException {
		logger.info("Check and Create {}", request.getName());
		
		CoolpayRecipientListResponse recipients = findRecipient(request.getName());
		
		// More than one recipient returned, cannot proceed
		if (recipients.getRecipients().size() > 1) {
			throw new TooManyResultsException(request.getName()+" returned "+recipients.getRecipients().size()+" results");
		}

		// Only 1 recipient found, will not add new recipient.
		if (recipients.getRecipients().size() == 1)
			return recipients.getRecipients().get(0);

		// No recipients found, add and return new recipient.
		return restTemplate.postForObject(createRecipientUrl, request, CoolpayRecipientResponse.class).getRecipient();
	}


	private CoolpayRecipientListResponse findRecipient(String recipientName) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", recipientName);		
		
		return restTemplate.getForObject(searchRecipientsUrl, CoolpayRecipientListResponse.class, params);
	}


	public CoolpayPaymentResponse makePayment(CoolpayPaymentRequest request) {
		return restTemplate.postForObject(createPaymentUrl, request, CoolpayPaymentResponse.class);
	}


	public CoolpayPaymentListResponse listPayments() {
		return restTemplate.getForObject(listPaymentUrl, CoolpayPaymentListResponse.class);
	}

}
