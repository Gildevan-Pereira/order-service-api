package com.serviceorder.api.config.security;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.serviceorder.api.config.security.filters.BasicAuthenticationProcessingFilter;
import com.serviceorder.api.config.security.providers.BasicAuthenticationProvider;

import lombok.RequiredArgsConstructor;

//https://salahuddin-s.medium.com/custom-header-based-authentication-using-spring-security-17f4163d0986
//https://shout.setfive.com/2015/11/02/spring-boot-authentication-with-custom-http-header/
//https://www.baeldung.com/spring-security-authentication-provider
	
@Configuration
@EnableWebSecurity
@Profile({"dev"})
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final BasicAuthenticationProvider basicAuthenticationProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().disable() // no http basic login
			.csrf().disable() // no csrf token
			.formLogin().disable() // no form login
			.logout().disable() // no logout
			// no session
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.authorizeRequests()
			.anyRequest().authenticated()
			.and()
			.addFilterBefore(getBasicFilter(), AnonymousAuthenticationFilter.class);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(basicAuthenticationProvider);
	}
	
	private Filter getBasicFilter() throws Exception {
		return new BasicAuthenticationProcessingFilter(
				getRequestMatchers("/v1/**"), authenticationManager());
	}
	
	private RequestMatcher getRequestMatchers(String pattern) {
		return new AntPathRequestMatcher(pattern);
	}
}