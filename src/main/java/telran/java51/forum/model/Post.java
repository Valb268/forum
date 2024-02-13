package telran.java51.forum.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Document(collection = "posts")
@NoArgsConstructor
public class Post extends ForumMessages {
	String id;
	@Setter
	String content;
	String author;
	List<String> tags;
	@Setter
	String title;
	List<Comment> comments = new ArrayList<Comment>();
	
	public Post(String author, String title, String content, List<String> tags) {
		super();
		this.title = title;
		this.author = author;
		this.content = content;
		this.tags = new ArrayList<String>(tags);
	}
	
	public boolean addComment(String user, String message) {
		return this.comments.add(new Comment(user, message));
	}
	
	public boolean removeComment(String message) {
		return this.comments.removeIf(c -> c.getMessage().equals(message));
	}
	
	public boolean addTag(String tag) {
		return this.tags.add(tag);
	}
	
	public boolean removeTag(String tag) {
		return this.tags.removeIf(t -> t.equals(tag));
	}
	
}
