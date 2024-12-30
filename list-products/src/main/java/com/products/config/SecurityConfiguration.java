package com.products.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {


	@Bean
	public UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("user").password("{noop}password").roles("USER").build());
		manager.createUser(User.withUsername("admin").password("{noop}adminpassword").roles("ADMIN").build());
		return manager;
	}

	  @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	                .csrf(AbstractHttpConfigurer::disable)
	                .authorizeHttpRequests(req ->
	                        req.requestMatchers("/admin/**").hasRole("ADMIN")
	                                .anyRequest()
	                                .authenticated()
	                )/* .formLogin(form -> form
	            			.loginPage("/login")
	            			.permitAll()
	            		);*/
	                    .formLogin(AbstractAuthenticationFilterConfigurer::permitAll);


	        return http.build();
	    }

}