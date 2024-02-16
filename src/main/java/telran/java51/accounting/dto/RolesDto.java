package telran.java51.accounting.dto;

import java.util.Set;

import lombok.Getter;

@Getter
public class RolesDto {
	String login;
	Set<String> roles;
}
