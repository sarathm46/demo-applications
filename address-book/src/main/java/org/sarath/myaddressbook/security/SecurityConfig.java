package org.sarath.myaddressbook.security;

import org.sarath.myaddressbook.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	AppUserRepository appUserRepository;
	@Autowired
	AppUserDetailsService appUserDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder registry) throws Exception {

		// registry.inMemoryAuthentication().withUser("admin") //
		// .password("admin").roles("ADMIN");

		registry.userDetailsService(appUserDetailsService);

	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**"); //
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()//
				.antMatchers("/", "/login").permitAll() //
				.antMatchers("/app/**").hasRole("ADMIN") //
				.anyRequest().authenticated() //
				.and()//
				.formLogin() //
				.loginPage("/login?login").usernameParameter("username").passwordParameter("password") //
				.loginProcessingUrl("/app/login").defaultSuccessUrl("/app/home").failureUrl("/login?error").permitAll()
				.and()//
				.logout().logoutUrl("/app/logout").logoutSuccessUrl("/login?success").permitAll().and(); //
	}
}
