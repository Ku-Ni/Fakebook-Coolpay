package co.uk.coenie.fakebook_coolpay.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages= {
		"co.uk.coenie.fakebook_coolpay.client"
		,"co.uk.coenie.fakebook_coolpay.config"
		,"co.uk.coenie.fakebook_coolpay.controller"
		,"co.uk.coenie.fakebook_coolpay.service"
})
public class TestSpringConfig {

}
