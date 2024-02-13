package telran.java51.forum.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import telran.java51.forum.model.Comment;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
	
	String id;
	String author;
	String title;
	List<String> tags = new ArrayList<>();
	String content;
	List<Comment> comments = new ArrayList<>();
	String dataCreated = LocalDateTime.now().toString();
	int likes;
	public PostDto(String author, String title, List<String> tags,
			String content) {
		this.author = author;
		this.title = title;
		this.tags = tags;
		this.content = content;
	}
	
	
}
