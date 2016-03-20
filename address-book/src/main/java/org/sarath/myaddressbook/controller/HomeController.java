package org.sarath.myaddressbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	
	
	@Autowired
	OAuth2RestOperations oAuth2RestOperations;
	
	
//	@Autowired
//	@Qualifier("oAuth2RestOperationsFacebook")
//	OAuth2RestOperations oAuth2RestOperationsFacebook;
	
	
	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public String loginPage() {
		return "login/login";
	}

	@RequestMapping(value = "/app/home", method = RequestMethod.GET)
	public String homePage() {

		return "secured/home";
	}



	@RequestMapping(value = "/signInGoogle", method = RequestMethod.GET)
	public String signInWithGoogle() {
		System.out.println("signInWithGoogle");
		try {
			String token = oAuth2RestOperations.getAccessToken().getValue();
			System.out.println(" token >>>>>>>>>>>>>>>>>>>>>> " + token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "login/login";
	}

	@RequestMapping(value = "/redirected", method = RequestMethod.GET)
	@ResponseBody
	public String redirected() {
		String token = "";
		try {
			token = oAuth2RestOperations.getAccessToken().getValue();
			System.out.println(" token >>>>>>>>>>>>>>>>>>>>>> " + token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return token;
	}
	
	

	@RequestMapping(value = "/signInFacebook", method = RequestMethod.GET)
	public String signInWithFacebook() {
		System.out.println("signInWithGoogle");
		try {
			//String token = oAuth2RestOperationsFacebook.getAccessToken().getValue();
			//System.out.println(" token >>>>>>>>>>>>>>>>>>>>>> " + token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "login/login";
	}

	@RequestMapping(value = "/redirectedFacebook", method = RequestMethod.GET)
	@ResponseBody
	public String redirectedFaceBook() {
		String token = "";
		try {
			//token = oAuth2RestOperationsFacebook.getAccessToken().getValue();
			//System.out.println(" token >>>>>>>>>>>>>>>>>>>>>> " + token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return token;
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
