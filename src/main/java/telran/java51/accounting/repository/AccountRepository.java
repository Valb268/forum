package telran.java51.accounting.repository;

import org.springframework.data.repository.CrudRepository;

import telran.java51.accounting.model.User;

public interface AccountRepository extends CrudRepository<User, String> {

}
