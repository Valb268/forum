package telran.java51.forum.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Document(collection = "posts")
@EqualsAndHashCode(of = "id")
public class Post  {
	String id;
	@Setter
	String content;
	String author;
	Set<String> tags;
	@Setter
	String title;
	List<Comment> comments;
	LocalDateTime dateCreated;
	int likes;
	
	public Post() {
		System.out.println("custom constructor is invoked");
		this.dateCreated = LocalDateTime.now();
		this.comments = new ArrayList<Comment>();
		this.tags = new HashSet<>();
	}
	
	
	public Post(String author, String title, String content, Set<String> tags) {
		this();
		this.title = title;
		this.author = author;
		this.content = content;
		this.tags = tags;
	}
	
	public boolean addComment(String user, String message) {
		return this.comments.add(new Comment(user, message));
	}
	
	public boolean removeComment(Comment comment) {
		return this.comments.remove(comment);
	}
	
	public boolean addTag(String tag) {
		return this.tags.add(tag);
	}
	
	public boolean removeTag(String tag) {
		return this.tags.remove(tag);
	}
	
	public void addLike() {
		this.likes = this.likes < Integer.MAX_VALUE - 1 ? this.likes++ : this.likes;
	}
	
	public void removeLike() {
		this.likes = this.likes > 0 ? this.likes-- : this.likes;
	}
	
}
