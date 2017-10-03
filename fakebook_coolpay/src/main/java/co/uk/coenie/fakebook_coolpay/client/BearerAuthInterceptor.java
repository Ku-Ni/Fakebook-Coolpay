package co.uk.coenie.fakebook_coolpay.client;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class BearerAuthInterceptor implements ClientHttpRequestInterceptor {
	private final String apiToken;
	
	public BearerAuthInterceptor(String apiToken) {
		this.apiToken = apiToken;
	}

	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		//Build the auth-header
		final String authHeader = "Bearer "+apiToken;
		
		//Add the auth-header
        request.getHeaders().add( "Authorization", authHeader );
		
        return execution.execute( request, body );
	}

}
