package co.uk.coenie.fakebook_coolpay.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@PropertySource({
	"classpath:credentials.properties","classpath:coolpay_api.properties"
	})
@Configuration
public class SpringConfig {
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public RestTemplate coolpayRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();

		// Message converter for marshalling JSON to objects
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		
		return restTemplate;
	}
	
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
	
}
