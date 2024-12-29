package com.products.bean;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.MessageFormat;
import java.util.Map;
import java.util.ResourceBundle;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.FacesException;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named
@SessionScoped
public class SessionBean implements Serializable {
	private static final long serialVersionUID = 3835416126235456397L;
	
	public static final String MODE_INSERT = "ADD";
	public static final String MODE_VIEW = "SHOW";
	public static final String MODE_EDIT = "MODIFY";

	//private Long lastId = null;
	private Authentication sessionAuthentication;

	private String currentMode;

	private Long currentRowId;

	@PostConstruct
	public void init() {
		sessionAuthentication = SecurityContextHolder.getContext().getAuthentication();		
	}



	public String getCurrentMode() {
		return currentMode;
	}



	public void setCurrentMode(String currentMode) {
		this.currentMode = currentMode;
	}



	public Long getCurrentRowId() {
		return this.currentRowId;
	}

	public void setCurrentRowId(Long currentRowId) {
		this.currentRowId = currentRowId;
	}


	public static void displayMessage(String tagLibParent, FacesMessage.Severity severity, String keyTitle,
			String keyMsg) {
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle bundleMsgs = ResourceBundle.getBundle("messages.messages", context.getViewRoot().getLocale());
		java.util.Set<String> keys = bundleMsgs.keySet();
        for (String key : keys) {
            String value = bundleMsgs.getString(key);
        }
		
		FacesContext.getCurrentInstance().addMessage(tagLibParent,
				new FacesMessage(severity, bundleMsgs.getString(keyTitle), bundleMsgs.getString(keyMsg)));
	}

	public static void displayErrors(Map<String, String> errors) {
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle bundleExceptions = ResourceBundle.getBundle("messages.exceptions", context.getViewRoot().getLocale());
		ResourceBundle bundleLanguage = context.getApplication().getResourceBundle(context, "labels");

		for (Map.Entry<String, String> error : errors.entrySet()) {
			String itemError = error.getKey();
			String messageError = error.getValue();
			String[] convertedArgs = new String[1];
			convertedArgs[0] = bundleLanguage.getString(itemError);
			String errorDetails = MessageFormat.format(bundleExceptions.getString(messageError),
					(Object[]) convertedArgs);
			context.addMessage("msgs", new FacesMessage(FacesMessage.SEVERITY_ERROR,
					bundleLanguage.getString(itemError).concat(":"), errorDetails));
		}
	}

	public void navigateToRoot() {
		try {
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			externalContext.redirect(String.valueOf(getApplicationUri()) + "/logout");
		} catch (Exception ex) {
			displayMessage("messages", FacesMessage.SEVERITY_ERROR, "error", "failedToRedirect");
		}
	}

	public String getApplicationUri() {
		try {
			FacesContext ctxt = FacesContext.getCurrentInstance();
			ExternalContext ext = ctxt.getExternalContext();
			URI uri = new URI(ext.getRequestScheme(), /* 143 */ null, ext.getRequestServerName(),
					ext.getRequestServerPort(), /* 144 */ ext.getRequestContextPath(), null, null);
			return uri.toASCIIString();
		} catch (URISyntaxException e) {
			throw new FacesException(e);
		}
	}

	public Authentication getSessionAuthentication() {
		return sessionAuthentication;
	}
	
	public String getModeInsert() {
		return MODE_INSERT;
	}
	public String getModeEdit() {
		return MODE_EDIT;
	}
	public String getModeView() {
		return MODE_VIEW;
	}
}
