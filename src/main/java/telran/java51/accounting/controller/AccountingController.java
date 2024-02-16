package telran.java51.accounting.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java51.accounting.dto.NewUserDto;
import telran.java51.accounting.dto.RolesDto;
import telran.java51.accounting.dto.UpdateUserDto;
import telran.java51.accounting.dto.UserDto;
import telran.java51.accounting.service.AccountService;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountingController {

	final AccountService accountService;
	
	@PostMapping("/register") 
	public UserDto registerUser(@RequestBody NewUserDto newUser) {
		return accountService.registerUser(newUser);
	}
	
	@PostMapping("/login")
	public UserDto loginUser() {
		// TODO
		return null;
		
	}
	
	@DeleteMapping("/user/{user}")
	public UserDto deleteUser(@PathVariable String user) {
		return accountService.deleteUser(user);
	}
	
	@PutMapping("/user/{user}")
	public UserDto updateUser(@PathVariable String login, @RequestBody UpdateUserDto updateUser) {
		return accountService.updateUser(login, updateUser);
	}
	
	@PutMapping("/user/{user}/role/{role}") 
	public RolesDto addRole(@PathVariable String login, @PathVariable String role) {
		return accountService.addRole(login, role);
	}
	
	@DeleteMapping("/user/{user}/role/{role}")
	public RolesDto deleteRole(@PathVariable String login, @PathVariable String role) {
		return accountService.deleteRole(login, role);
	}
	
	@PutMapping("/password")
	public void changePassword() {
		// TODO 
	}
	
	@GetMapping("/user/{login}")
	public UserDto getUser(@PathVariable String login) {
		return accountService.getUser(login);
	}
	
	
}
