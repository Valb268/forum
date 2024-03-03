package telran.java51.accounting.service;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java51.accounting.dto.NewUserDto;
import telran.java51.accounting.dto.RolesDto;
import telran.java51.accounting.dto.UpdateUserDto;
import telran.java51.accounting.dto.UserDto;
import telran.java51.accounting.exceptions.AccountAlreadyExistsException;
import telran.java51.accounting.exceptions.UserNotFoundException;
import telran.java51.accounting.exceptions.WrongRoleException;
import telran.java51.accounting.model.UserAccount;
import telran.java51.accounting.repository.AccountRepository;
import telran.java51.forum.configuration.UserRole;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService, CommandLineRunner {

	final AccountRepository accountRepository;
	final ModelMapper modelMapper;
	final PasswordEncoder passwordEncoder;
	
	@Override
	public UserDto registerUser(NewUserDto newUser) {
		UserAccount user = modelMapper.map(newUser, UserAccount.class);
		if (!accountRepository.existsById(newUser.getLogin())) {
			String password = passwordEncoder.encode(newUser.getPassword());
			user.setPassword(password);
			user = accountRepository.save(user);
		} else {
			throw new AccountAlreadyExistsException();
		}
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto deleteUser(String login) {
		UserAccount user = accountRepository.findById(login).orElseThrow(UserNotFoundException::new);
		accountRepository.deleteById(login);
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto updateUser(String login, UpdateUserDto updateUser) {
		UserAccount user = accountRepository.findById(login).orElseThrow(UserNotFoundException::new);
		String firstName = updateUser.getFirstName();
		if (firstName != null) {
			user.setFirstName(firstName);
		}
		String lastName = updateUser.getLastName();
		if (lastName != null) {
			user.setLastName(lastName);
		}
		user = accountRepository.save(user);
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public RolesDto addRole(String login, String role) {
		UserRole roleEnum = checkAndGetRole(role);
		if (roleEnum == null) {
			throw new WrongRoleException("Wrong role");
		}
		UserAccount user = accountRepository.findById(login).orElseThrow(UserNotFoundException::new);
		user.addRole(roleEnum);
		user = accountRepository.save(user);
		return modelMapper.map(user, RolesDto.class);
	}
	
	UserRole checkAndGetRole(String role) {
		
		for (UserRole roleEnum : UserRole.values()) {
			if (role.toUpperCase().equals(roleEnum.toString())) {
				return roleEnum;
			}
		}
		return null;
	}

	@Override
	public RolesDto deleteRole(String login, String role) {
		UserRole roleEnum = checkAndGetRole(role);
		if (roleEnum == null) {
			throw new WrongRoleException("Wrong role");
		}
		UserAccount user = accountRepository.findById(login).orElseThrow(UserNotFoundException::new);
		user.deleteRole(roleEnum);
		user = accountRepository.save(user);
		return modelMapper.map(user, RolesDto.class);
	}

	@Override
	public void changePassword(String login, String newPassword) {
		UserAccount user = accountRepository.findById(login).orElseThrow(UserNotFoundException::new);
		String password = passwordEncoder.encode(newPassword);
		user.setPassword(password);
		accountRepository.save(user);
	}

	@Override
	public UserDto getUser(String login) {
		UserAccount user = accountRepository.findById(login).orElseThrow(UserNotFoundException::new);
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public void run(String... args) throws Exception {
		if(!accountRepository.existsById("admin")) {
			String password = passwordEncoder.encode("admin");
			UserAccount user = new UserAccount("admin", "", "", password);
			user.addRole(UserRole.MODERATOR);
			user.addRole(UserRole.ADMINISTRATOR);
			accountRepository.save(user);
		}
		
	}

	
}
