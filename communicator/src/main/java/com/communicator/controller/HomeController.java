package com.communicator.controller;

import com.communicator.helper.RestHelper;
import com.communicator.model.ClientRequest;
import com.communicator.model.ClientRequestHeader;
import com.communicator.model.ClientRequestParam;
import com.communicator.model.ClientResponse;
import com.communicator.util.AppUtil;
import com.communicator.util.PropertiesFileUtil;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.logging.log4j.util.Strings;
import org.primefaces.event.SelectEvent;
import org.springframework.context.annotation.Scope;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//@Scope("view")
@SessionScoped
@Named
public class HomeController implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String SAVED_REQUESTS_PATH = "saved-requests.properties";
	private static String EMPTY_STRING = "";
	private ClientRequest request;
	private ClientResponse response;
	private ClientRequestHeader requestHeader;
	private ClientRequestParam requestParam;
	private List<ClientRequest> savedRequests;
	private ClientRequest selectedSavedRequest;

	@Inject
	private RestHelper restHelper;

	@PostConstruct
	public void init() {
		request = new ClientRequest();
		response = new ClientResponse();
		requestHeader = new ClientRequestHeader();
		requestParam = new ClientRequestParam();
		request.getHeaders().add(new ClientRequestHeader("Content-Type", "application/json"));
		loadSavedRequests();
	}

	public ClientRequestHeader getRequestHeader() {
		return requestHeader;
	}

	public void setRequestHeader(ClientRequestHeader requestHeader) {
		this.requestHeader = requestHeader;
	}

	public ClientRequest getRequest() {
		return request;
	}

	public ClientResponse getResponse() {
		return response;
	}

	public ClientRequestParam getRequestParam() {
		return requestParam;
	}

	public void setRequestParam(ClientRequestParam requestParam) {
		this.requestParam = requestParam;
	}

	public List<ClientRequest> getSavedRequests() {
		return savedRequests;
	}

	public void setSavedRequests(List<ClientRequest> savedRequests) {
		this.savedRequests = savedRequests;
	}

	public ClientRequest getSelectedSavedRequest() {
		return selectedSavedRequest;
	}

	public void setSelectedSavedRequest(ClientRequest selectedSavedRequest) {
		this.selectedSavedRequest = selectedSavedRequest;
	}

	public void sendRequest() {
		if (Strings.isBlank(request.getUrl())) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Url cannot be empty", ""));
			return;
		}
		response = restHelper.executeRequest(request);
	}

	public void onRowSelect(SelectEvent event) {
		request = (ClientRequest) event.getObject();
		response.setOutput(EMPTY_STRING);
		response.setTimeTaken(EMPTY_STRING);
	}

	public void reset() {
		request = ClientRequest.newInstance();
		selectedSavedRequest = null;
		response.setOutput(EMPTY_STRING);
		response.setTimeTaken(EMPTY_STRING);
	}

	public void loadSavedRequests() {
		savedRequests = new ArrayList<>();
		List<String> values = PropertiesFileUtil.readAllProperties(SAVED_REQUESTS_PATH);
		ClientRequest.formatSavedRequests(values, savedRequests);
	}

	public void saveRequest() {
		String jsonString = request.toString();
		ClientRequest newRequest = AppUtil.toObject(jsonString, ClientRequest.class);
		PropertiesFileUtil.writeToProperties(SAVED_REQUESTS_PATH, newRequest.getName(), jsonString);
		loadSavedRequests();
		reset();
	}

	public void deleteRequest(ClientRequest selectedRequest) {
		PropertiesFileUtil.deleteFromProperties(SAVED_REQUESTS_PATH, selectedRequest.getName());
		loadSavedRequests();
		reset();
	}

	public void loadRequest(ClientRequest clientRequest) {
		request = clientRequest;
	}

	public void addHeaderRow() {
		request.getHeaders().add(new ClientRequestHeader(requestHeader.getKey(), requestHeader.getValue()));
		requestHeader.setKey(EMPTY_STRING);
		requestHeader.setValue(EMPTY_STRING);
	}

	public void deleteHeaderRow(ClientRequestHeader requestHeader) {
		request.getHeaders().remove(requestHeader);
	}

	public void addParamRow() {
		request.getParams().add(new ClientRequestParam(requestParam.getKey(), requestParam.getValue()));
		requestParam.setKey(EMPTY_STRING);
		requestParam.setValue(EMPTY_STRING);
	}

	public void deleteParamRow(ClientRequestParam requestParam) {
		request.getParams().remove(requestParam);
	}

}
