package com.communicator.model;

public class Tab {

	private String name;
	private String requestType;
	private String requestUrl;

	public Tab() {
	}

	public Tab(String name) {
		this.name = name;
	}

	public Tab(String name, String requestType, String requestUrl) {
		this.name = name;
		this.requestType = requestType;
		this.requestUrl = requestUrl;
	}

	public Tab(Tab tab) {
		this(tab.name, tab.requestType, tab.requestUrl);
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Tab [name=" + name + ", requestType=" + requestType + ", requestUrl=" + requestUrl + "]";
	}

	public static Tab emptyTab() {
		return new Tab("New Tab","get","");
	}

	
}
