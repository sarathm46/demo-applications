package org.sarath.myaddressbook.controller;

import java.util.List;

import org.json.JSONObject;
import org.sarath.myaddressbook.model.Address;
import org.sarath.myaddressbook.model.AppUser;
import org.sarath.myaddressbook.repository.AddressRepository;
import org.sarath.myaddressbook.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAddressController {
	@Autowired
	AppUserRepository appUserRepository;

	@Autowired
	AddressRepository addressRepository;

	@RequestMapping(value = "/app/rest/user", method = RequestMethod.POST)
	public AppUser saveOrUpdateUser(@RequestBody AppUser appUser) {
		return appUserRepository.save(appUser);
	}

	@RequestMapping(value = "/app/rest/user/{id}", method = RequestMethod.GET)
	public AppUser get(@PathVariable(value = "id") Long id) {
		return appUserRepository.findOne(id);
	}

	@RequestMapping(value = "/app/rest/user/{id}", method = RequestMethod.POST)
	public void deleteAppUser(@PathVariable(value = "id") Long id) {
		appUserRepository.delete(id);
	}

	@RequestMapping(value = "/app/rest/address/{id}", method = RequestMethod.POST)
	public void deleteAddress(@PathVariable(value = "id") Long id) {
		addressRepository.delete(id);
	}

	@RequestMapping(value = "/app/rest/address/{id}", method = RequestMethod.GET)
	public Address getAddress(@PathVariable(value = "id") Long id) {
		return addressRepository.findOne(id);
	}

	@RequestMapping(value = "/app/rest/users", method = RequestMethod.GET)
	public List<AppUser> getAllUsers() {
		try {
			// Thread.sleep(2000);
		} catch (Exception e) {
		}
		return appUserRepository.findAll();
	}

	@RequestMapping(value = "/app/rest/address", method = RequestMethod.POST)
	public Address saveOrUpdateAddress(@RequestBody Address address) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		address.setAppUser(appUserRepository.findOneByUsername(username));
		return addressRepository.save(address);
	}

	@RequestMapping(value = "/app/rest/address", method = RequestMethod.GET)
	public List<Address> getAllAddress() {
		try {
			// Thread.sleep(2000);
		} catch (Exception e) {
		}
		return addressRepository.findAll();
	}

	@RequestMapping(value = "/app/rest/duplicatecheck", method = RequestMethod.POST)
	public String isDuplicate(@RequestBody AppUser appUser) {
		AppUser appUser2 = appUserRepository.findOneByUsername(appUser.getUsername());
		boolean isDuplicate = appUser2 == null ? false : true;
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("duplicate", isDuplicate);
		return jsonObject.toString();
	}

}
