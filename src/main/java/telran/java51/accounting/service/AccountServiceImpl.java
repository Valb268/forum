package telran.java51.accounting.service;

import org.modelmapper.ModelMapper;
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
public class AccountServiceImpl implements AccountService {

	final AccountRepository accountRepository;
	final ModelMapper modelMapper;
	enum UserRole {
	    ADMIN,
	    MODERATOR,
	    USER
	}

	@Override
	public UserDto registerUser(NewUserDto newUser) {
		User user = modelMapper.map(newUser, User.class);
		if (!accountRepository.existsById(newUser.getLogin())) {
			user = accountRepository.save(user);
		} else {
			throw new AccountAlreadyExistsException();
		}
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto loginUser() {
		// TODO Auto-generated method stub
		return null;
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
			if (role.equals(roleEnum.name())) {
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
	public boolean changePassword() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserDto getUser(String login) {
		User user = accountRepository.findById(login).orElseThrow(UserNotFoundException::new);
		return modelMapper.map(user, UserDto.class);
	}

}
