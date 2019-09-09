package com.bseg.botapi.service;	


import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
public class BotsServiceMonitoring {

	private RestTemplate restTemplate;
	String ServiceMonitoringEndpointUri = null;

	@HystrixCommand(fallbackMethod = "getDefaultServiceMonitoring")
	public String getRandomService() {
		String uri = "http://bots-service" + ServiceMonitoringEndpointUri;
		String monitoring  = restTemplate.getForObject(uri, String.class);	
		return monitoring;
	}

	public String getDefaultService() {
		return "Houve uma falha na aplicação";
	}

}