package telran.java51.accounting.dto;

import java.util.Set;

import lombok.Getter;
import lombok.Singular;

@Getter
public class RolesDto {
	String login;
	@Singular
	Set<String> roles;
}
