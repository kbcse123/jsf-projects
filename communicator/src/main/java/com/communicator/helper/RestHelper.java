/**
 * 
 */
package com.communicator.helper;

import java.io.IOException;


import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.util.Timeout;

import com.communicator.model.ClientRequest;
import com.communicator.model.ClientResponse;
import com.communicator.util.AppUtil;

/**
 * @author shabasha
 *
 */
@Named
@Singleton
public class RestHelper {

	private RequestConfig config = RequestConfig.custom()
			.setConnectTimeout(Timeout.ofMilliseconds(5000L))
			.setConnectionRequestTimeout(Timeout.ofMilliseconds(10000L))
			.setResponseTimeout(Timeout.ofMilliseconds(5000L))
			.build();

	public ClientResponse executeRequest(ClientRequest clientRequest) {
		ClientResponse clientResponse = null;
		try {
			switch (clientRequest.getType()) {
			case "Get":
				clientResponse = get(clientRequest);
				break;
			case "Post":
				clientResponse = post(clientRequest);
				break;
			default:
				return clientResponse;
			}
		} catch (IOException e) {
			clientResponse = new ClientResponse(e.getMessage());
			e.printStackTrace();
		}
		return clientResponse;
	}

	private ClientResponse post(ClientRequest clientRequest) throws IOException {
		ClientResponse clientResponse = new ClientResponse();
		HttpPost post = new HttpPost(clientRequest.getUrl());
        post.setEntity(new StringEntity(clientRequest.getInput(), ContentType.APPLICATION_JSON));
      
		try (CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(config).build();
				CloseableHttpResponse response = httpclient.execute(post)) {
			clientResponse.setStatus("Status : " + response.getCode());
			clientResponse.setOutput(AppUtil.beautify(EntityUtils.toString(response.getEntity())));
		} catch (IOException | ParseException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unable to call the service", e.getMessage()));
		}
		return clientResponse;
	}

	private ClientResponse get(ClientRequest clientRequest) throws IOException {
		ClientResponse clientResponse = new ClientResponse();
		try (CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(config).build();
				CloseableHttpResponse response = httpclient.execute(new HttpGet(clientRequest.getUrl()))) {
			clientResponse.setStatus("Status : " + response.getCode());
			clientResponse.setOutput(AppUtil.beautify(EntityUtils.toString(response.getEntity())));
		} catch (IOException | ParseException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Unable to call the service",e.getMessage()));
		}
		
		return clientResponse;
	}

}
