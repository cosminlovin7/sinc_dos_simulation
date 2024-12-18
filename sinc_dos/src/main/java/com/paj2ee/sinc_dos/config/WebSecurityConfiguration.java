package com.paj2ee.sinc_dos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http
			.securityMatcher("/**")
			.authorizeHttpRequests((configurer) ->
				configurer
					.requestMatchers(
						"/home",
						"/login",
						"/login**"
					).permitAll()
					.requestMatchers(
						"/dashboard"
					).authenticated()
					.anyRequest().permitAll()
			)
			.httpBasic(Customizer.withDefaults())
			.formLogin((configurer) ->
				configurer
					.defaultSuccessUrl("/home", true) // Redirect to /home after successful login
			);

		return http.build();
	}

}
