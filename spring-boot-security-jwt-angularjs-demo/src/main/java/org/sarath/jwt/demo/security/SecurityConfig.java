package org.sarath.jwt.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private Http401UnauthorizedEntryPoint authenticationEntryPoint;

	@Override
	public void configure(WebSecurity web) throws Exception {

		web.ignoring()//
				.antMatchers(HttpMethod.OPTIONS, "/**")//
				.antMatchers("/app/**/*.{js,html}")//
				.antMatchers("/authedicate")//
				.antMatchers("/*")//
				.antMatchers("/index.html");

		super.configure(web);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.exceptionHandling()//
				.authenticationEntryPoint(authenticationEntryPoint)//
				.and()//
				.csrf()//
				.disable()//
				.headers().frameOptions()//
				.disable()//
				.and()//
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//
				.and()//
				.authorizeRequests()//
				.antMatchers("/app/**").authenticated()//
				.antMatchers("/api/**").authenticated()//
				.and()//
				.addFilterBefore(new JWTFilter(), UsernamePasswordAuthenticationFilter.class);
		super.configure(http);
	}
}