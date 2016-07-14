package com.test;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//(collectionResourceRel = "account", path = "account")
@RepositoryRestResource 
public interface AccountRepository extends CrudRepository<Account, Long> {
	List<Account> findById(@Param("id") Long id);
	List<Account> findAll();
	@SuppressWarnings("unchecked")
	Account save(Account account);
	void delete(Long id);
}
