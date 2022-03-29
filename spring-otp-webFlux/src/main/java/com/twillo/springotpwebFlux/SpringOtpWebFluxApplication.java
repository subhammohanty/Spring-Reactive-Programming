package com.twillo.springotpwebFlux;

import com.twilio.Twilio;
import com.twillo.springotpwebFlux.config.TwilioConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import javax.annotation.PostConstruct;

@SpringBootApplication
public class SpringOtpWebFluxApplication {

	@Autowired
	private TwilioConfig config;

	@PostConstruct
	public void initTwilio(){
		Twilio.init(config.getAccountSid(), config.getAuthToken());

	}

	public static void main(String[] args) {
		SpringApplication.run(SpringOtpWebFluxApplication.class, args);
	}

}
