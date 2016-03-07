package org.sarath.myaddressbook.security;

import java.util.ArrayList;
import java.util.Collection;

import org.sarath.myaddressbook.model.AppUser;
import org.sarath.myaddressbook.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AppUserDetailsService implements UserDetailsService {
	@Autowired
	AppUserRepository appUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser appUser = appUserRepository.findOneByUsername(username);
		if (appUser == null) {
			throw new UsernameNotFoundException(username + " is not found");
		}
		return new UserDetails() {

			@Override
			public boolean isEnabled() {
				return true;
			}

			@Override
			public boolean isCredentialsNonExpired() {
				return true;
			}

			@Override
			public boolean isAccountNonLocked() {
				return true;
			}

			@Override
			public boolean isAccountNonExpired() {
				return true;
			}

			@Override
			public String getUsername() {
				return appUser.getUsername();
			}

			@Override
			public String getPassword() {
				return appUser.getPassword();
			}

			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {

				Collection<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<SimpleGrantedAuthority>();
				grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
				return grantedAuthorities;
			}
		};
	}
}
