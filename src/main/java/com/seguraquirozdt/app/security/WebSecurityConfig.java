package com.seguraquirozdt.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.seguraquirozdt.app.service.security.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	
	//Implementacion de login con usuario de base de datos 
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new MyUserDetailsService();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(4);
	}

	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http
		.authorizeHttpRequests((requests) -> requests
			.requestMatchers("/").permitAll()
			.anyRequest().authenticated()
		)
		.formLogin((form) -> form
			.loginPage("/login")
			.permitAll()
		)
		.logout();

	return http.build();
	
	}

	//Usuario default para ingresar al sistema 

	/*
	@SuppressWarnings("deprecation")
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user =
			 User.withDefaultPasswordEncoder()
				.username("user")
				.password("password")
				.roles("USER")
				.build();

		return new InMemoryUserDetailsManager(user);
	}
	*/
}
