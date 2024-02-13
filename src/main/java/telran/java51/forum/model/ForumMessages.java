package telran.java51.forum.model;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class ForumMessages {
	String dataCreated;
	int likes;
	
	public ForumMessages() {
		this.dataCreated = LocalDateTime.now().toString();
	}
	
	public void increaseLikes() {
		this.likes = this.likes < Integer.MAX_VALUE - 1 ? this.likes++ : this.likes;
	}
	
	public void decreaseLikes() {
		this.likes = this.likes > 0 ? this.likes-- : this.likes;
	}
	
	
}
