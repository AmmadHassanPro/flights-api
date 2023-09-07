package com.atombooking.flightsapi.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class ApiSpecConfig {
	
	@Value("${atombooking.open-api-spec.dev-url}")
	private String devUrl;
	@Value("${atombooking.open-api-spec.prod-url}")
	private String prodUrl;
	
	@Bean
	OpenAPI myOpenAPI() {
		
		Server devServer = new Server();
		devServer.setUrl(devUrl);
		devServer.setDescription("Api URL in Dev Environment");
		
		Server prodServer = new Server();
		prodServer.setUrl(prodUrl);
		prodServer.setDescription("APi URL in Prod Environment");
		
		Contact contact = new Contact();
		contact.setEmail("ammad13k@gmail.com");
		contact.setName("Syed Ammad Hassan");
		contact.setUrl("https://www.linkedin.com/in/syed-ammad-hassan");
		
		License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");
		Info info = new Info();
		info.setTitle("AtomBooking, The Flights Search API");
		info.setVersion("1.0");
		info.setDescription("The AtomBooking api, is a flights search api, which provides its consumers to search flights from origin to destination from all the airlines. It is a consumer of Amadeus Travel APIs https://developers.amadeus.com/");
		info.contact(contact);
		info.setLicense(mitLicense);
		
		List<Server> list = new ArrayList<>();
		list.add(devServer);list.add(prodServer);
		
		return new OpenAPI().info(info).servers(list);
	}
}
