package org.sarath.spring.oauth2;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuth2RestController {
	@RequestMapping("/user")
	public Principal user(Principal principal) {
		return principal;
	}
}
