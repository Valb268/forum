package telran.java51.forum.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import telran.java51.forum.model.Comment;

@Getter
public class PostDto {
	
	String id;
	@Setter
	String author;
	String title;
	Set<String> tags;
	String content;
	List<Comment> comments;
	LocalDate dataCreated;
	int likes;
	
	}
	
	

