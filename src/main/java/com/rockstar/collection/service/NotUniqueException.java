package com.rockstar.collection.service;

public class NotUniqueException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NotUniqueException(String resource) {
		super(String.format("%s already exists", resource));
	}

}
