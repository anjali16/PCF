package com.capgemini.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@SpringBootApplication
public class Demo1Application extends SpringBootServletInitializer{

	final private PersonProperties personProperties;

	@Autowired
	public Demo1Application(PersonProperties personProperties) {
		this.personProperties = personProperties;
	}

	public static void main(String[] args) {	
		SpringApplication.run(Demo1Application.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
		return application.sources(Demo1Application.class);
	}
	
	@Value("${message}")
	public String message;
	 
	/*@RequestMapping("/")
	public String greeting(){
		return message;
	}*/  
	@RequestMapping("/")
	public String greeting(){
	return personProperties.getGreeting() + " Spring Boot!" + personProperties.getFarewell() + " Spring Boot!";
	}
}
