package org.sarath.myaddressbook.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@Configuration
@EnableOAuth2Client
public class OAuth2Config {
	private String ACCESSTOKEN_URI = "https://accounts.google.com/o/oauth2/token";
	private String USER_AUTHORIZATION_URI = "https://accounts.google.com/o/oauth2/auth";
	private String CLIENT_ID = "981495540834-10q2tbohd52dpd88n89iemmnq8rp1n4j.apps.googleusercontent.com";
	private String CLIENT_SECRET = "kIY3GIB3N-TGC7CFgZ7Uheti";

	@Bean
	public OAuth2RestOperations restTemplate(OAuth2ClientContext oauth2ClientContext) {
		return new OAuth2RestTemplate(resource(), oauth2ClientContext);
	}

	@Bean
	public OAuth2ProtectedResourceDetails resource() {
		AuthorizationCodeResourceDetails resource = new AuthorizationCodeResourceDetails();
		resource.setClientId(CLIENT_ID);
		resource.setClientSecret(CLIENT_SECRET);
		resource.setAccessTokenUri(ACCESSTOKEN_URI);
		resource.setUserAuthorizationUri(USER_AUTHORIZATION_URI);
		resource.setPreEstablishedRedirectUri("http://localhost:8080/redirected");
		resource.setUseCurrentUri(false);
		String scopes[] = { "https://www.googleapis.com/auth/contacts.readonly", "email" };
		resource.setScope(Arrays.asList(scopes));
		return resource;
	}
}
