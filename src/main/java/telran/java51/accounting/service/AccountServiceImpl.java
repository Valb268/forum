package telran.java51.accounting.service;

import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java51.accounting.dto.NewUserDto;
import telran.java51.accounting.dto.RolesDto;
import telran.java51.accounting.dto.UpdateUserDto;
import telran.java51.accounting.dto.UserDto;
import telran.java51.accounting.exceptions.AccountAlreadyExistsException;
import telran.java51.accounting.exceptions.UserNotFoundException;
import telran.java51.accounting.exceptions.WrongRoleException;
import telran.java51.accounting.model.User;
import telran.java51.accounting.repository.AccountRepository;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService, CommandLineRunner {

	final AccountRepository accountRepository;
	final ModelMapper modelMapper;
	enum UserRole {
	    ADMINISTRATOR,
	    MODERATOR,
	    USER
	}

	@Override
	public UserDto registerUser(NewUserDto newUser) {
		User user = modelMapper.map(newUser, User.class);
		if (!accountRepository.existsById(newUser.getLogin())) {
			String password = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
			user.setPassword(password);
			user = accountRepository.save(user);
		} else {
			throw new AccountAlreadyExistsException();
		}
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto deleteUser(String login) {
		User user = accountRepository.findById(login).orElseThrow(UserNotFoundException::new);
		accountRepository.deleteById(login);
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto updateUser(String login, UpdateUserDto updateUser) {
		User user = accountRepository.findById(login).orElseThrow(UserNotFoundException::new);
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
		if (!isRoleCorrect(role)) {
			throw new WrongRoleException("Wrong role");
		}
		User user = accountRepository.findById(login).orElseThrow(UserNotFoundException::new);
		user.addRole(role);
		user = accountRepository.save(user);
		return modelMapper.map(user, RolesDto.class);
	}
	
	boolean isRoleCorrect(String role) {
		
		for (UserRole roleEnum : UserRole.values()) {
			if (role.toUpperCase().equals(roleEnum.name())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public RolesDto deleteRole(String login, String role) {
		if (!isRoleCorrect(role)) {
			throw new WrongRoleException("Wrong role");
		}
		User user = accountRepository.findById(login).orElseThrow(UserNotFoundException::new);
		user.deleteRole(role);
		user = accountRepository.save(user);
		return modelMapper.map(user, RolesDto.class);
	}

	@Override
	public void changePassword(String login, String newPassword) {
		User user = accountRepository.findById(login).orElseThrow(UserNotFoundException::new);
		String password = BCrypt.hashpw(newPassword, BCrypt.gensalt());
		user.setPassword(password);
		accountRepository.save(user);
	}

	@Override
	public UserDto getUser(String login) {
		User user = accountRepository.findById(login).orElseThrow(UserNotFoundException::new);
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public void run(String... args) throws Exception {
		if(!accountRepository.existsById("admin")) {
			String password = BCrypt.hashpw("admin", BCrypt.gensalt());
			User user = new User("admin", "", "", password);
			user.addRole("MODERATOR");
			user.addRole("ADMINISTRATOR");
			accountRepository.save(user);
		}
		
	}

	
}
