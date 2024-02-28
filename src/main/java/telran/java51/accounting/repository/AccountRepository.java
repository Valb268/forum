package telran.java51.accounting.repository;

import org.springframework.data.repository.CrudRepository;

import telran.java51.accounting.model.UserAccount;

public interface AccountRepository extends CrudRepository<UserAccount, String> {

}
