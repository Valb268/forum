package telran.java51.accounting.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import telran.java51.forum.configuration.UserRole;

@Getter
@Document(collection = "users")
@EqualsAndHashCode(of = "login")
public class User {
	@Id
	String login;
	@Setter
	String firstName;
	@Setter
	String lastName;
	@Setter
	String password;
	Set<String> roles;
	
	public User() {
		this.roles = new HashSet<>();
		this.roles.add(UserRole.USER.toString());
	}

	public User(String login, String firstName, String lastName,
			String password) {
		this();
		this.login = login;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
	}
	
	public boolean addRole(String role) {
		return this.roles.add(role.toUpperCase());
	}

	public boolean deleteRole(String role) {
		return this.roles.remove(role.toUpperCase()) ;
	}
	
}
