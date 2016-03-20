package org.sarath.googlecontacts.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.sarath.googlecontacts.model.AppUser;
import org.sarath.googlecontacts.model.GoogleContact;
import org.sarath.googlecontacts.repository.AppUserRepository;
import org.sarath.googlecontacts.utils.GoogleContactParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gdata.util.ServiceException;

@RestController
public class AppRestController {
	@Autowired
	AppUserRepository appUserRepository;
	@Autowired
	GoogleContactParser googleContactParser;

	@RequestMapping("/app/rest/user")
	public AppUser getPrincipal(Principal principal) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		AppUser appUser = appUserRepository.findOneByUsername(authentication.getName());
		return appUser;
	}

	@RequestMapping("/app/rest/contacts")
	public List<GoogleContact> getContacts(Principal principal) throws ServiceException, IOException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		AppUser appUser = appUserRepository.findOneByUsername(authentication.getName());
		return googleContactParser.getGoogleContacts(appUser.getToken());
	}
}
