package co.uk.coenie.fakebook_coolpay.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import co.uk.coenie.fakebook_coolpay.client.dto.CoolpayRecipientListResponse;
import co.uk.coenie.fakebook_coolpay.client.dto.CoolpayRecipientRequest;
import co.uk.coenie.fakebook_coolpay.client.dto.CoolpayRecipientResponse;

@Service("CoolpayClient")
public class CoolpayClientImpl implements CoolpayClient {
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
	public boolean login() {
		CoolpayLoginResponse response = restTemplate.postForObject(loginUrlString, new CoolpayLoginRequest(apiUserName, apiKey), CoolpayLoginResponse.class);
		String apiToken = response.getApiToken();
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
		interceptors.add(new BearerAuthInterceptor(apiToken));
		restTemplate.setInterceptors(interceptors);

		if (apiToken != null)
			return true;

		return false;
	}


	@Override
	public CoolpayRecipientResponse checkAndCreateRecipient(CoolpayRecipientRequest request) throws TooManyResultsException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", request.getName());

		CoolpayRecipientListResponse recipients = restTemplate.getForObject(searchRecipientsUrl, CoolpayRecipientListResponse.class, params);

		// More than one recipient returned, cannot proceed
		if (recipients.getRecipients().size() > 1) {
			throw new TooManyResultsException(request.getName()+" returned "+recipients.getRecipients().size()+" results");
		}

		// Only 1 recipient found, will not add new recipient.
		if (recipients.getRecipients().size() == 1)
			return recipients.getRecipients().get(0);

		// No recipients found, add and return new recipient.
		return restTemplate.postForObject(createRecipientUrl, request, CoolpayRecipientResponse.class, params);
	}


	public CoolpayPaymentResponse makePayment(CoolpayPaymentRequest request) {
		return restTemplate.postForObject(createPaymentUrl, request, CoolpayPaymentResponse.class);
	}


	public CoolpayPaymentListResponse listPayments() {
		return restTemplate.getForObject(listPaymentUrl, CoolpayPaymentListResponse.class);
	}

}
