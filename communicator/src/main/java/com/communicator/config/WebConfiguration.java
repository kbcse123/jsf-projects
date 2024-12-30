package com.communicator.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {


    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        System.out.println("in addViewControllers()");
      registry.addViewController("/")
          .setViewName("forward:/index.xhtml");
      registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
}
