package telran.java51.forum.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = { "user", "dateCreated" })
@AllArgsConstructor
public class Comment {
	String user;
	String message;
	LocalDateTime dateCreated;
	int likes;
	
	public Comment() {
		dateCreated = LocalDateTime.now();
	}

	public Comment(String user, String message) {
		this();
		this.user = user;
		this.message = message;
	}
	
	public void addLike() {
		this.likes = this.likes < Integer.MAX_VALUE - 1 ? this.likes++ : this.likes;
	}
	
	public void removeLike() {
		this.likes = this.likes > 0 ? this.likes-- : this.likes;
	}
	
}
