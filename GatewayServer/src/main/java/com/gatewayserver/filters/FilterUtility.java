package com.gatewayserver.filters;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;



@Component
public class FilterUtility {
	
	
	public static final String correlation_Id="shantanu-correlation-id";
	
	public String getCorrelationId(HttpHeaders requestHeaders)
	{
		if(requestHeaders.get(correlation_Id)!=null)
		{
			List<String> requestHeadersList=requestHeaders.get(correlation_Id);
			return requestHeadersList.stream().findFirst().get();
		}
		else
		{
			return null;
		}
	}
	
	public ServerWebExchange setRequestHeader(ServerWebExchange exchange,String name,String value)
	{
		return exchange.mutate().request(exchange.getRequest().mutate().header(name,value).build()).build();
	}
	
	public ServerWebExchange setCorrelationId(ServerWebExchange exchange, String correlationId)
	{
		return this.setRequestHeader(exchange, correlation_Id, correlationId);
	}

}
