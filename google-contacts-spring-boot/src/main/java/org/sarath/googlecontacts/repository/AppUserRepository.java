package org.sarath.googlecontacts.repository;

import javax.transaction.Transactional;

import org.sarath.googlecontacts.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
	AppUser findOneByUsername(String username);
}
