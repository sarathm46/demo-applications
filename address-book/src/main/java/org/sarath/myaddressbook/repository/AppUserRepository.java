package org.sarath.myaddressbook.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.sarath.myaddressbook.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
	AppUser findOneByUsername(String username);

	AppUser save(AppUser appUser);

	void delete(AppUser appUser);

	List<AppUser> findAll();

}
