package org.sarath.googlecontacts.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.filter.CompositeFilter;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

@Configuration
@EnableOAuth2Client
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	OAuth2ClientContext oauth2ClientContext;
	@Autowired
	AppUserDetailsService appUserDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder registry) throws Exception {

		// registry.inMemoryAuthentication().withUser("admin")//
		// .password("admin")//
		// .roles("USER");

		registry.userDetailsService(appUserDetailsService);

	}
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**"); //
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/**")//
				.authorizeRequests()//
				.antMatchers("/", "/login**", "/webjars/**")//
				.permitAll().antMatchers("/app/**").hasRole("USER") //
				.anyRequest().authenticated()//
				.and()//
				.logout()//
				.logoutSuccessUrl("/")//
				.permitAll()//
				.and()//
				.csrf().disable()//
				// .csrfTokenRepository(csrfTokenRepository())//
				// .and()//
				.formLogin() //
				.loginPage("/login?login").usernameParameter("username").passwordParameter("password")
				.loginProcessingUrl("/app/login").defaultSuccessUrl("/app/home", false).failureUrl("/login?error")
				.permitAll().and().rememberMe().key("123456798").rememberMeParameter("remember-me").and()//
				.logout().logoutUrl("/app/logout").logoutSuccessUrl("/login?success").permitAll().and()
				// .addFilterAfter(csrfHeaderFilter(), CsrfFilter.class)//
				.addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
	}

	@Bean
	FilterRegistrationBean oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(filter);
		registration.setOrder(-100);
		return registration;
	}

	Filter ssoFilter() {
		CompositeFilter filter = new CompositeFilter();
		List<Filter> filters = new ArrayList<>();
		filters.add(ssoFilter(google(), "/login/google"));
		filter.setFilters(filters);
		return filter;
	}

	private Filter ssoFilter(ClientResources client, String path) {
		OAuth2ClientAuthenticationProcessingFilter facebookFilter = new OAuth2ClientAuthenticationProcessingFilter(
				path);
		OAuth2RestTemplate facebookTemplate = new OAuth2RestTemplate(client.getClient(), oauth2ClientContext);
		facebookFilter.setRestTemplate(facebookTemplate);
		facebookFilter.setTokenServices(
				new UserInfoTokenServices(client.getResource().getUserInfoUri(), client.getClient().getClientId()));
		return facebookFilter;
	}

	@Bean
	@ConfigurationProperties("google")
	ClientResources google() {
		return new ClientResources();
	}

	Filter csrfHeaderFilter() {
		return new OncePerRequestFilter() {
			@Override
			protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
					FilterChain filterChain) throws ServletException, IOException {
				CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
				if (csrf != null) {
					Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
					String token = csrf.getToken();
					if (cookie == null || token != null && !token.equals(cookie.getValue())) {
						cookie = new Cookie("XSRF-TOKEN", token);
						cookie.setPath("/");
						response.addCookie(cookie);
					}
				}
				filterChain.doFilter(request, response);
			}
		};
	}

	CsrfTokenRepository csrfTokenRepository() {
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName("X-XSRF-TOKEN");
		return repository;
	}
}
