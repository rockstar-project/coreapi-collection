package com.rockstar.collection.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

@Configuration
@EnableHypermediaSupport(type = {EnableHypermediaSupport.HypermediaType.HAL})
public class WebConfig extends WebMvcConfigurerAdapter {
	
	@Autowired private ObjectMapper jacksonObjectMapper;
	
	@PostConstruct
	public void initialize() {
	    this.jacksonObjectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
	    this.jacksonObjectMapper.setSerializationInclusion(Include.NON_NULL);
	}	
}