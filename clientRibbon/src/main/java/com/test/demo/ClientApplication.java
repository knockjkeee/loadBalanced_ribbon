package com.test.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@RibbonClient(name = "portbook", configuration = RibbonConfiguration.class)
public class ClientApplication {

	@Autowired
	private RestTemplate template;

    @GetMapping("/invoke")
	public String invokePortBook(){
    	String url = "http://portbook/book/port";
		return template.getForObject(url , String.class);
//		return "asd";
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
