package com.test.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@SpringBootApplication
@RestController
public class ClientApplication {


	@Autowired
	private LoadBalancerClient loadBalancer;

	private RestTemplate template = new RestTemplate();

    @GetMapping("/invoke")
	public String invokePortBook(){
		ServiceInstance instance = loadBalancer.choose("portbook");
		URI storesUri = URI.create(String.format("http://%s:%s", instance.getHost(), instance.getPort()));

		return template.getForObject(storesUri+"/book/port", String.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}

	@LoadBalanced
	@Bean
	public RestTemplate template(){
		return new RestTemplate();
	}

}
