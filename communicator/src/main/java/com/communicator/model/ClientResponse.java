package com.communicator.model;

public class ClientResponse extends GenericResponse{
	private String output;

	public ClientResponse(String output) {
		this.output = output;
	}

	public ClientResponse() {
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}
}
