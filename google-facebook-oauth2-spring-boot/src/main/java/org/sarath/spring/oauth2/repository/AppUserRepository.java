package org.sarath.spring.oauth2.repository;

import javax.transaction.Transactional;

import org.sarath.spring.oauth2.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
	AppUser findOneByUsername(String username);
}
