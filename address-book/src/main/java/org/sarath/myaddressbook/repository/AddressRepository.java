package org.sarath.myaddressbook.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.sarath.myaddressbook.model.Address;
import org.sarath.myaddressbook.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface AddressRepository extends JpaRepository<Address, Long> {
	List<Address> findByAppUser(AppUser appUser);
}
