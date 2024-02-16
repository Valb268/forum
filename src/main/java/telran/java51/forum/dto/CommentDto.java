package telran.java51.forum.dto;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class CommentDto {
	String user;
	String message;
	LocalDateTime dateCreated;
	int likes;
}
