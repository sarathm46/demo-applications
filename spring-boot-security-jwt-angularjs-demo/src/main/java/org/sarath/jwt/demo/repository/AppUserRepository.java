package org.sarath.jwt.demo.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.sarath.jwt.demo.domain.AppUser;
import org.springframework.stereotype.Repository;

@Repository
public class AppUserRepository {

	private HashMap<String, AppUser> appUsers = new HashMap<>();

	public AppUserRepository() {
		List<String> roles = new ArrayList<>();
		roles.add("ADMIN");
		roles.add("USER");
		appUsers.put("admin", new AppUser(1, "admin", "Sarath Muraleedharan", "admin", roles));
		
		List<String> roles1 = new ArrayList<>();
		roles1.add("USER");
		appUsers.put("user", new AppUser(1, "user", "Sarika Muraleedharan", "user", roles1));
		
	}

	public AppUser getAppUser(String username) {
		return appUsers.get(username);

	}

}
