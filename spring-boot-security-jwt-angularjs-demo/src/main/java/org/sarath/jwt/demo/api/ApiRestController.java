package org.sarath.jwt.demo.api;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.sarath.jwt.demo.domain.AppUser;
import org.sarath.jwt.demo.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("/api")
public class ApiRestController {
	@Autowired
	AppUserRepository appUserRepository;

	@RequestMapping("/claims")
	public Claims getClaims(HttpServletRequest request) {
		return (Claims) request.getAttribute("claims");
	}

	@RequestMapping("/principal")
	public Principal getUser(Principal principal) {
		return principal;
	}

	@RequestMapping("/user")
	public AppUser getUser() {
		AppUser appUser = appUserRepository.getAppUser(
				((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
		return appUser;
	}
}
