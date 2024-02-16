package telran.java51.forum.dto;

import java.util.Set;

import lombok.Getter;

@Getter
public class NewPostDto {
	String title;
	Set<String> tags;
	String content;
}
