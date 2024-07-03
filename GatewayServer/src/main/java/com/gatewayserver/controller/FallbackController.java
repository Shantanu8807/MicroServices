package com.gatewayserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

//this controller is used to give proper reponse to user when some service is unavailable using circuit breaker

@RestController
public class FallbackController {
	
	
	@RequestMapping("/contactSupport")
	public Mono<String> contactSupport()
	{
		return Mono.just("an error ocured please retry after some time");
	}

}
