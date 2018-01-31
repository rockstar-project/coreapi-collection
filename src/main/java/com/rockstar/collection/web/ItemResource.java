package com.rockstar.collection.web;

import org.springframework.hateoas.ResourceSupport;

public class ItemResource extends ResourceSupport {
	
	private String name;
	private String location;
	
	public ItemResource() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
