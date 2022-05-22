package com.serviceorder.api.config.security.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.serviceorder.api.config.security.CustomAuthenticationToken;

public abstract class BaseAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

	public BaseAuthenticationProcessingFilter(RequestMatcher requiresAuthenticationRequestMatcher,
			AuthenticationManager authenticationManager) {
		super(requiresAuthenticationRequestMatcher);
		//Set authentication manager
		setAuthenticationManager(authenticationManager);
	}
	
	protected abstract CustomAuthenticationToken 
		getCustomAuthenticationToken(HttpServletRequest request);

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		// Extract from request
		CustomAuthenticationToken token = getCustomAuthenticationToken(request);
		return getAuthenticationManager().authenticate(token);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// Save user principle in security context
		SecurityContextHolder.getContext().setAuthentication(authResult);
		chain.doFilter(request, response);
	}
}