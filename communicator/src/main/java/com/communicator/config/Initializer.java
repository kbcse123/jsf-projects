package com.communicator.config;

import jakarta.faces.webapp.FacesServlet;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


//@Configuration
public class Initializer implements ServletContextInitializer {

    @Override
    public void onStartup(ServletContext sc) throws ServletException {
        sc.setInitParameter("primefaces.CLIENT_SIDE_VALIDATION", "true");
        sc.setInitParameter("primefaces.THEME", "midnight");
        sc.setInitParameter("javax.faces.PROJECT_STAGE", "Development");
        sc.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
        sc.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", "true");
    }
    @Bean
    public static CustomScopeConfigurer viewScope() {
        CustomScopeConfigurer configurer = new CustomScopeConfigurer();
        Map<String, Object> scopeMap = new HashMap<>();
        scopeMap.put("view", new ViewScope());
        configurer.setScopes(scopeMap);
        return configurer;
    }

    @Bean
    public ServletRegistrationBean<FacesServlet> servletRegistrationBean() {
        FacesServlet servlet = new FacesServlet();
        ServletRegistrationBean<FacesServlet> srb = new ServletRegistrationBean<>(servlet, "*.xhtml");
        srb.setLoadOnStartup(1);
        return srb;
    }
   /* @Bean
    public FilterRegistrationBean prettyFilter() {
        FilterRegistrationBean prettyFilter = new FilterRegistrationBean<>();
        prettyFilter.setDispatcherTypes(DispatcherType.FORWARD, DispatcherType.REQUEST,
                DispatcherType.ASYNC, DispatcherType.ERROR);
        prettyFilter.addUrlPatterns("/*");
        return prettyFilter;
    }*/

}