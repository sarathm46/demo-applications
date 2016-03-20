package org.sarath.spring.oauth2;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sarath.spring.oauth2.model.AppUser;
import org.sarath.spring.oauth2.repository.AppUserRepository;
import org.sarath.spring.oauth2.utils.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@Autowired
	AppUserRepository appUserRepository;
	@Autowired
	Environment env;

	@RequestMapping({ "/", "/login" })
	public String login(Principal principal) {
		return handlePrincipal(principal);
	}

	@RequestMapping("/app/home")
	public String home(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		AppUser appUser = appUserRepository.findOneByUsername(authentication.getName());
		if (appUser == null) {
			return "redirect:/app/logout?error=social site issue";
		}
		model.addAttribute("appUser", appUser);
		return "secured/home";
	}

	public String handlePrincipal(Principal principal) {

		if (principal != null) {
			if (principal instanceof OAuth2Authentication) {
				OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) principal;
				OAuth2Request oAuth2Request = (OAuth2Request) oAuth2Authentication.getOAuth2Request();

				UsernamePasswordAuthenticationToken upAuth = (UsernamePasswordAuthenticationToken) oAuth2Authentication
						.getUserAuthentication();

				String imageUrl = null;
				String name = null;
				String email = null;
				String socialType = null;

				if (env.getProperty("google.client.clientId").equals(oAuth2Request.getClientId())) {
					HashMap<String, Object> googleUser = (HashMap<String, Object>) upAuth.getDetails();
					System.out.println("fbUser : " + googleUser);
					imageUrl = ((Map<String, String>) googleUser.get("image")).get("url");
					name = googleUser.get("displayName").toString();
					email = ((Map<String, String>) (((ArrayList<Map<String, String>>) googleUser.get("emails")).get(0)))
							.get("value");
					socialType = "google";
				}
				if (env.getProperty("facebook.client.clientId").equals(oAuth2Request.getClientId())) {
					HashMap<String, String> fbUser = (HashMap<String, String>) upAuth.getDetails();
					System.out.println("fbUser : " + fbUser);
					imageUrl = "https://graph.facebook.com/" + fbUser.get("id") + "/picture?type=large";
					name = fbUser.get("name");
					email = fbUser.get("email");
					socialType = "facebook";
				}

				AppUser appUser = appUserRepository.findOneByUsername(email);

				if (appUser == null) {
					appUser = new AppUser();
					appUser.setUsername(email);
					appUser.setPassword(new RandomString(6).nextString());
					appUser.setName(name);
				}

				try {
					appUser.setSocialType(socialType);
					appUser.setImageUrl(imageUrl);
					appUserRepository.save(appUser);
				} catch (Exception e) {
					return "redirect:/app/logout?error=social site issue";
				}

				addUserIntoSecurity(appUser);

			}
			return "redirect:/app/home";
		} else {
			System.out.println("principal : is Empty");
		}
		return "index";
	}

	public void addUserIntoSecurity(AppUser appUser) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		UserDetails user = new User(appUser.getUsername(), appUser.getPassword(), authorities);
		Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}
