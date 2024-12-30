package com.communicator.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import com.communicator.model.Tab;
import jakarta.annotation.ManagedBean;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;
import org.springframework.context.annotation.Scope;


@ManagedBean
//@Scope("view")
@SessionScoped
public class HomeHandler implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Tab> tabs;

	public List<Tab> getTabs() {
		return tabs;
	}

	@PostConstruct
	public void init() {
		tabs = new ArrayList<>();
		tabs.add(Tab.emptyTab());
		tabs.add(new Tab("+"));
		System.out.println("in init()");
	}

	/*
	 * public void addTab(Tab tab) { tabs.add(new Tab(tab)); }
	 */
	public void removeTab(Tab tab) {
		System.out.println("in removeTab ");
		if (tabs.size() > 2) {
			System.out.println("removing " + tab);
			tabs.remove(tab);
		}
		//RequestContext requestContext = RequestContext.getCurrentInstance();
		PrimeFaces.current().ajax().update("form:tabs");
	}

	public void sendRequest(Tab tab) {
		System.out.println(tab);
	}

	public void changeTab(TabChangeEvent event) {
		int currentTabIndex = ((TabView) event.getComponent()).getActiveIndex();
		int lastTabIndex = tabs.size() - 1; // The "Add" tab.
		System.out.println("currentTabIndex: " + currentTabIndex + ", lastTabIndex: " + lastTabIndex);
		if (currentTabIndex == lastTabIndex) {
			tabs.add(lastTabIndex, Tab.emptyTab()); // Just a stub. Do your thing to add a new tab.
			PrimeFaces.current().ajax().update("form:tabs"); // Update the p:tabView to show new tab.
			PrimeFaces.current().ajax().addCallbackParam("newTabIndex", lastTabIndex); // Triggers the oncomplete.
		}
	}
}
