package com.serviceorder.api.config.security.providers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.serviceorder.api.config.properties.AuthenticationProperties;
import com.serviceorder.api.config.security.CustomAuthenticationToken;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class BasicAuthenticationProvider implements AuthenticationProvider {
	
	private static final String AUTHENTICATION_SCHEME = "Basic";
	
	private final AuthenticationProperties properties;

	@Value("${server.servlet.context-path}")
	private String contextPath;
	
	@Override
	public Authentication authenticate(Authentication authentication) {
		@SuppressWarnings("unchecked")
		var principal = (Map<String, String>) authentication.getPrincipal();
		
		var authorizationHeader = principal.get("Authorization");
		
		// validate the authorization header
		if (!isTokenBasedAuthentication(authorizationHeader)) {
			throw new BadCredentialsException("Auth token required.");
		}
		
		// Extract the token from the Authorization header
		var token = authorizationHeader
				.substring(AUTHENTICATION_SCHEME.length()).trim();
				
		var uri = principal.get("URI");
		var requestUriBase = contextPath.concat("/v1");
		
		var accessToken = properties.getBasic();

		if (uri.startsWith(requestUriBase) && token.equals(accessToken)) {
			return new CustomAuthenticationToken(principal, null);
		}
		throw new BadCredentialsException("Invalid auth token");
	}
	
	private boolean isTokenBasedAuthentication(String authorizationHeader) {
		// Check if the Authorization header is valid
		// It must not be null and must be prefixed with "Basic" plus a whitespace
		// The authentication scheme comparison must be case-insensitive
		return authorizationHeader != null && authorizationHeader
				.toLowerCase().startsWith(AUTHENTICATION_SCHEME.toLowerCase() + " ");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return CustomAuthenticationToken.class.equals(authentication);
	}
}