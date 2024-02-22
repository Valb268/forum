package telran.java51.forum.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import lombok.Singular;
import telran.java51.forum.model.Comment;

@Getter
public class PostDto {
	
	String id;
	@Setter
	String author;
	String title;
	@Singular
	Set<String> tags;
	String content;
	@Singular
	List<Comment> comments;
	LocalDateTime dateCreated;
	int likes;
	
	}
	
	

