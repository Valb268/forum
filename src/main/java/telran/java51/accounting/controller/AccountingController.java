package telran.java51.accounting.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	
	
	// to show RequestHeader
//	@PostMapping("/login")
//	public UserDto login(@RequestHeader("Authorization") String token) {
//		token = token.split(" ")[1];
//		String credentials = new String(Base64.getDecoder().decode(token));
//		return accountService.getUser(credentials.split(":")[0]);
//	}
	
	
	@PostMapping("/login")
	public UserDto loginUser(Principal principal) {
		return accountService.getUser(principal.getName());
		
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
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void changePassword(Principal principal, @RequestHeader("X-Password") String newPassword) {
		 accountService.changePassword(principal.getName(), newPassword);
	}
	
	@GetMapping("/user/{login}")
	public UserDto getUser(@PathVariable String login) {
		return accountService.getUser(login);
	}
	
	
}
