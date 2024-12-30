package com.communicator.model;
import java.util.ArrayList;
import java.util.List;

import com.communicator.util.AppUtil;

public class ClientRequest {

	private String name;
	private String type;
	private String url;
	private String input;
	private String description;
	private String authType;
	private List<ClientRequestHeader> headers = new ArrayList<>();
	private List<ClientRequestParam> params = new ArrayList<>();
	
	public ClientRequest() {
	}

	public ClientRequest(String name) {
		this.name = name;
	}

	public ClientRequest(String name, String requestType, String requestUrl) {
		this.name = name;
		this.type = requestType;
		this.url = requestUrl;
	}

	public ClientRequest(ClientRequest tab) {
		this(tab.name, tab.type, tab.url);
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getAuthType() {
		return authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType;
	}

	public List<ClientRequestHeader> getHeaders() {
		return headers;
	}

	public void setHeaders(List<ClientRequestHeader> headers) {
		this.headers = headers;
	}

	public List<ClientRequestParam> getParams() {
		return params;
	}

	public void setParams(List<ClientRequestParam> params) {
		this.params = params; 
	}

	public static void formatSavedRequests(List<String> values, List<ClientRequest> savedRequests) {
		values.forEach(value ->	savedRequests.add(AppUtil.toObject(value,ClientRequest.class)));
		
	}

	@Override
	public String toString() {
		return  AppUtil.toJsonString(this);
	}
	
	public static ClientRequest newInstance() {
		return new ClientRequest(); 
	}
	
}
