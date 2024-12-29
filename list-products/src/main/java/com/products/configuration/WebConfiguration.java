package com.products.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {


    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
      registry.addViewController("/")
          .setViewName("forward:/index.xhtml");
      registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
}