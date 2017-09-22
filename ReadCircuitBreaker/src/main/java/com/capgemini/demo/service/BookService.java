package com.capgemini.demo.service;

import java.net.URI;

import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
@EnableCircuitBreaker
@Service
public class BookService {

	private final RestTemplate restTemplate;

	  public BookService(RestTemplate rest) {
	    this.restTemplate = rest;
	  }

	  @HystrixCommand(fallbackMethod = "reliable")
	  @RequestMapping("/to-read")
	  public String readingList() {
	    URI uri = URI.create("http://localhost:8090/recommended");

	    return this.restTemplate.getForObject(uri, String.class);
	  }

	  public String reliable() {
	    return "Cloud Native Java (O'Reilly)";
	  }
}
