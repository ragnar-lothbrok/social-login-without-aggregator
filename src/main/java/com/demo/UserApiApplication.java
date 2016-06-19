package com.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
// @EnableDiscoveryClient
@EnableResourceServer
public class UserApiApplication {
	
	@Value("${facebook.appID}")
	private String fbAppId;
	
	@Value("${facebook.appSecret}")
	private String fbAppSecret;
	
	@Value("${google.appID}")
	private String googleClientId;
	
	@Value("${google.appSecret}")
	private String googleClientSecret;

	@Bean
	public FacebookConnectionFactory facebookConnectionFactory() {
		return new FacebookConnectionFactory(fbAppId, fbAppSecret);
	}

	@Bean
	public GoogleConnectionFactory googleConnectionFactory() {
		return new GoogleConnectionFactory(googleClientId, googleClientSecret);
	}

	public static void main(String[] args) {
		SpringApplication.run(UserApiApplication.class, args);
	}

}
