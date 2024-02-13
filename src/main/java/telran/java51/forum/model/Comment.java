package telran.java51.forum.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Comment extends ForumMessages {
	String user;
	String message;
	
	public Comment(String user, String message) {
		this.user = user;
		this.message = message;
	}
	
}
