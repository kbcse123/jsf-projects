package com.communicator.model;

public class ClientRequestParam {
	private String key;
	private String value;
	
	
	
	public ClientRequestParam(String key, String value) {
		 this.key = key;
		 this.value = value;
	}
	public ClientRequestParam() {
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}

}
