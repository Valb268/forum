package telran.java51.accounting.service;

import telran.java51.accounting.dto.NewUserDto;
import telran.java51.accounting.dto.RolesDto;
import telran.java51.accounting.dto.UpdateUserDto;
import telran.java51.accounting.dto.UserDto;

public interface AccountService {
	
	UserDto registerUser(NewUserDto newUser);
	
	UserDto deleteUser(String login);
	
	UserDto updateUser(String login, UpdateUserDto updateUser);
	
	RolesDto addRole(String login, String role);
	
	RolesDto deleteRole(String login, String role);

	void changePassword(String login, String newPassword);
	
	UserDto getUser(String login);

	
}
