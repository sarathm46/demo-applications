package org.sarath.myaddressbook.security;

import javax.servlet.Filter;

import org.sarath.myaddressbook.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableOAuth2Client
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	AppUserRepository appUserRepository;
	@Autowired
	AppUserDetailsService appUserDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder registry) throws Exception {

		// registry.inMemoryAuthentication().withUser("admin") // //
		// .password("admin").roles("ADMIN");

		registry.userDetailsService(appUserDetailsService);

	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**"); //
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/signInWithgoogle").authenticated();
		http.csrf().disable().authorizeRequests()//
				.antMatchers("/", "/login","/redirected").permitAll() //
				.antMatchers("/app/**").hasRole("ADMIN") //
				.anyRequest().authenticated() //
				.and()//
				.formLogin() //
				.loginPage("/login?login").usernameParameter("username").passwordParameter("password")
				//
				.loginProcessingUrl("/app/login").defaultSuccessUrl("/app/home").failureUrl("/login?error").permitAll()
				.and()//
				.logout().logoutUrl("/app/logout").logoutSuccessUrl("/login?success").permitAll().and()
				.addFilterBefore(ssoFilterGoogle(), BasicAuthenticationFilter.class);
		;
		//
	}

	@Bean
	public FilterRegistrationBean oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(filter);
		registration.setOrder(-100);
		return registration;
	}

	@Autowired
	private OAuth2ClientContext oauth2ClientContext;
	@Autowired
	private OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails;

	private Filter ssoFilterGoogle() {
		OAuth2ClientAuthenticationProcessingFilter googleFilter = new OAuth2ClientAuthenticationProcessingFilter(
				"/signInGoogle");
		OAuth2RestTemplate googleTemplate = new OAuth2RestTemplate(oAuth2ProtectedResourceDetails, oauth2ClientContext);
		googleFilter.setRestTemplate(googleTemplate);
		return googleFilter;
	}

}
