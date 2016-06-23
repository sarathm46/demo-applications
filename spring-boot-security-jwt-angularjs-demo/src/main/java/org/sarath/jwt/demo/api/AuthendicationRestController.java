package org.sarath.jwt.demo.api;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.sarath.jwt.demo.domain.AppUser;
import org.sarath.jwt.demo.domain.LoginRequest;
import org.sarath.jwt.demo.domain.LoginResponse;
import org.sarath.jwt.demo.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class AuthendicationRestController {
	@Autowired
	AppUserRepository appUserRepository;

	@RequestMapping(value = "/authedicate", method = RequestMethod.POST)
	public LoginResponse login(@RequestBody LoginRequest loginRequest, HttpServletResponse response)
			throws IOException {
		String token = null;
		AppUser appUser = appUserRepository.getAppUser(loginRequest.getUsername());

		if (loginRequest != null && appUser != null && appUser.getPassword().equals(loginRequest.getPassword())) {
			token = Jwts.builder().setSubject(loginRequest.getUsername()).claim("roles", appUser.getRoles())
					.setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "secretkey").compact();
		} else {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Username or Password");
		}
		return new LoginResponse(token);
	}
}
