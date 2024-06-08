package com.gatewayserver;

import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServerApplication.class, args);
	}
	
	@Bean
	public RouteLocator BankRouteConfig(RouteLocatorBuilder routeLocatorBuilder)
	{
		return routeLocatorBuilder.routes()
				.route( p-> 
				         p.path("/accounts/**")
				         .filters(f-> f.rewriteRequestParameter("/accounts/(?<segment>.*)", "${segment}")
				        		 .addResponseHeader("X-Response-Time",LocalDateTime.now().toString()))
						.uri("lb://ACCOUNTS")
						)
				.route( p-> 
		         p.path("/loans/**")
		         .filters(f-> f.rewriteRequestParameter("/loans/(?<segment>.*)", "${segment}")
		        		 .addResponseHeader("X-Response-Time",LocalDateTime.now().toString()))
				.uri("lb://LOANS")
				)
				.route( p-> 
		         p.path("/cards/**")
		         .filters(f-> f.rewriteRequestParameter("/cards/(?<segment>.*)", "${segment}")
		        		 .addResponseHeader("X-Response-Time",LocalDateTime.now().toString()))
				.uri("lb://CARDS")
				).build();
	}

}
