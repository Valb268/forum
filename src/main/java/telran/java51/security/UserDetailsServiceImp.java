package telran.java51.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java51.accounting.model.UserAccount;
import telran.java51.accounting.repository.AccountRepository;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {

	final AccountRepository accountRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		UserAccount userAccount = accountRepository.findById(username)
				.orElseThrow(() -> new UsernameNotFoundException(username));
		
		Collection<String> authorities = userAccount.getRoles()
				.stream()
				.map(r -> "ROLE_" + r.toString())
				.collect(Collectors.toList());
		
		return new User(username, userAccount.getPassword(),
				AuthorityUtils.createAuthorityList(authorities));
	}

}
