package org.sarath.myaddressbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public String loginPage() {
		return "login/login";
	}

	@RequestMapping(value = "/app/home", method = RequestMethod.GET)
	public String homePage() {

		return "secured/home";
	}

	@RequestMapping(value = "/app/addressBookList", method = RequestMethod.GET)
	public String addressBookList() {
		return "secured/template/addressBookList";
	}

	@RequestMapping(value = "/app/addAddress", method = RequestMethod.GET)
	public String addAddress() {
		return "secured/template/addAddress";
	}

	@RequestMapping(value = "/app/settings", method = RequestMethod.GET)
	public String settings() {
		return "secured/template/settings";
	}

	@RequestMapping(value = "/app/addUser", method = RequestMethod.GET)
	public String signUp() {
		return "secured/template/addUser";
	}

	@RequestMapping(value = "/app/editUser", method = RequestMethod.GET)
	public String editUser() {
		return "secured/template/editUser";
	}

	@RequestMapping(value = "/app/editAddress", method = RequestMethod.GET)
	public String editAddress() {
		return "secured/template/editAddress";
	}

	@RequestMapping(value = "/app/listUser", method = RequestMethod.GET)
	public String listUser() {
		return "secured/template/listUser";
	}

}
