package com.serviceorder.api.config.security.filters;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.serviceorder.api.config.security.CustomAuthenticationToken;


public class BasicAuthenticationProcessingFilter extends BaseAuthenticationProcessingFilter {

	public BasicAuthenticationProcessingFilter(RequestMatcher requiresAuthenticationRequestMatcher,
			AuthenticationManager authenticationManager) {
		super(requiresAuthenticationRequestMatcher, authenticationManager);
	}

	@Override
	protected CustomAuthenticationToken getCustomAuthenticationToken(HttpServletRequest request) {
		// Extract from request
		Map<String,String> principal = new HashMap<>();
		principal.put("Authorization", request.getHeader("Authorization"));
		principal.put("URI", request.getRequestURI());
		
		// Create a token object or pass to authentication Provider
		return new CustomAuthenticationToken(principal, null);
	}
}