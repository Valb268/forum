package telran.java51.accounting.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WrongRoleException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3529586397782949758L;

	public WrongRoleException(String message) {
		super(message);
	}
	
}
